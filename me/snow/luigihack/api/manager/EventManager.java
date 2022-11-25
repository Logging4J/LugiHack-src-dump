//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.manager;

import me.snow.luigihack.impl.*;
import java.util.concurrent.atomic.*;
import me.snow.luigihack.*;
import net.minecraft.client.gui.*;
import net.minecraft.network.play.client.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.fml.common.network.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.entity.player.*;
import me.snow.luigihack.impl.modules.combat.autocrystal.*;
import me.snow.luigihack.impl.modules.client.*;
import net.minecraftforge.event.entity.living.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.*;
import java.nio.*;
import net.minecraft.world.*;
import java.util.*;
import java.util.function.*;
import com.google.common.base.*;
import net.minecraft.network.play.server.*;
import net.minecraft.init.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraftforge.client.event.*;
import me.snow.luigihack.impl.command.*;

public class EventManager extends Feature
{
    private final /* synthetic */ Timer timer;
    private final /* synthetic */ Timer logoutTimer;
    private final /* synthetic */ Timer chorusTimer;
    private final /* synthetic */ AtomicBoolean tickOngoing;
    private final /* synthetic */ Timer switchTimer;
    
    @SubscribeEvent(priority = EventPriority.LOW)
    public void onRenderGameOverlayEvent(final RenderGameOverlayEvent.Text text) {
        if (text.getType().equals((Object)RenderGameOverlayEvent.ElementType.TEXT)) {
            LuigiHack.moduleManager.onRender2D(new Render2DEvent(text.getPartialTicks(), new ScaledResolution(EventManager.mc)));
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        }
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        if (send.getPacket() instanceof CPacketHeldItemChange) {
            this.switchTimer.reset();
        }
    }
    
    public void onUnload() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
    
    @SubscribeEvent
    public void onRenderHand(final RenderHandEvent renderHandEvent) {
        LuigiHack.moduleManager.onRenderHand(renderHandEvent);
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onTickHighest(final TickEvent.ClientTickEvent clientTickEvent) {
        if (clientTickEvent.phase == TickEvent.Phase.START) {
            this.tickOngoing.set(true);
        }
    }
    
    public EventManager() {
        this.timer = new Timer();
        this.logoutTimer = new Timer();
        this.switchTimer = new Timer();
        this.chorusTimer = new Timer();
        this.tickOngoing = new AtomicBoolean(false);
    }
    
    @SubscribeEvent
    public void onClientConnect(final FMLNetworkEvent.ClientConnectedToServerEvent clientConnectedToServerEvent) {
        this.logoutTimer.reset();
        LuigiHack.moduleManager.onLogin();
    }
    
    @SubscribeEvent
    public void onClientDisconnect(final FMLNetworkEvent.ClientDisconnectionFromServerEvent clientDisconnectionFromServerEvent) {
        LuigiHack.moduleManager.onLogout();
        LuigiHack.totemPopManager.onLogout();
        LuigiHack.potionManager.onLogout();
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onUpdateWalkingPlayer(final UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (fullNullCheck()) {
            return;
        }
        if (updateWalkingPlayerEvent.getStage() == 0) {
            LuigiHack.baritoneManager.onUpdateWalkingPlayer();
            LuigiHack.speedManager.updateValues();
            LuigiHack.rotationManager.updateRotations();
            LuigiHack.positionManager.updatePosition();
        }
        if (updateWalkingPlayerEvent.getStage() == 1) {
            LuigiHack.rotationManager.restoreRotations();
            LuigiHack.positionManager.restorePosition();
        }
    }
    
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onTickLowest(final TickEvent.ClientTickEvent clientTickEvent) {
        if (clientTickEvent.phase == TickEvent.Phase.END) {
            this.tickOngoing.set(false);
            AutoCrystal.getInstance().postTick();
        }
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent.ClientTickEvent clientTickEvent) {
        if (fullNullCheck()) {
            return;
        }
        LuigiHack.moduleManager.onTick();
        for (final EntityPlayer entityPlayer : EventManager.mc.world.playerEntities) {
            if (entityPlayer != null) {
                if (entityPlayer.getHealth() > 0.0f) {
                    continue;
                }
                MinecraftForge.EVENT_BUS.post((Event)new DeathEvent(entityPlayer));
                TotemPopCounter.getInstance().onDeath(entityPlayer);
            }
        }
    }
    
    @SubscribeEvent
    public void onUpdate(final LivingEvent.LivingUpdateEvent livingUpdateEvent) {
        if (!Feature.fullNullCheck() && livingUpdateEvent.getEntity().getEntityWorld().isRemote && livingUpdateEvent.getEntityLiving().equals((Object)EventManager.mc.player)) {
            LuigiHack.potionManager.update();
            LuigiHack.totemPopManager.onUpdate();
            LuigiHack.inventoryManager.update();
            LuigiHack.holeManager.update();
            LuigiHack.safetyManager.onUpdate();
            LuigiHack.moduleManager.onUpdate();
            LuigiHack.timerManager.update();
            if (this.timer.passedMs(0L)) {
                LuigiHack.moduleManager.sortModules(true);
                LuigiHack.moduleManager.alphabeticallySortModules();
                this.timer.reset();
            }
        }
    }
    
    @SubscribeEvent
    public void onWorldRender(final RenderWorldLastEvent renderWorldLastEvent) {
        if (renderWorldLastEvent.isCanceled()) {
            return;
        }
        EventManager.mc.profiler.startSection("phobos");
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.shadeModel(7425);
        GlStateManager.disableDepth();
        GlStateManager.glLineWidth(1.0f);
        final Render3DEvent render3DEvent = new Render3DEvent(renderWorldLastEvent.getPartialTicks());
        final GLUProjection instance = GLUProjection.getInstance();
        final IntBuffer createDirectIntBuffer = GLAllocation.createDirectIntBuffer(16);
        final FloatBuffer createDirectFloatBuffer = GLAllocation.createDirectFloatBuffer(16);
        final FloatBuffer createDirectFloatBuffer2 = GLAllocation.createDirectFloatBuffer(16);
        GL11.glGetFloat(2982, createDirectFloatBuffer);
        GL11.glGetFloat(2983, createDirectFloatBuffer2);
        GL11.glGetInteger(2978, createDirectIntBuffer);
        final ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        instance.updateMatrices(createDirectIntBuffer, createDirectFloatBuffer, createDirectFloatBuffer2, scaledResolution.getScaledWidth() / (double)Minecraft.getMinecraft().displayWidth, scaledResolution.getScaledHeight() / (double)Minecraft.getMinecraft().displayHeight);
        LuigiHack.moduleManager.onRender3D(render3DEvent);
        GlStateManager.glLineWidth(1.0f);
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.enableDepth();
        GlStateManager.enableCull();
        GlStateManager.enableCull();
        GlStateManager.depthMask(true);
        GlStateManager.enableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.enableDepth();
        EventManager.mc.profiler.endSection();
    }
    
    public boolean isOnSwitchCoolDown() {
        return !this.switchTimer.passedMs(500L);
    }
    
    public void init() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    @SubscribeEvent
    public void onPacketReceive(final PacketEvent.Receive receive) {
        if (receive.getStage() != 0) {
            return;
        }
        LuigiHack.serverManager.onPacketReceived();
        if (receive.getPacket() instanceof SPacketEntityStatus) {
            final SPacketEntityStatus sPacketEntityStatus = (SPacketEntityStatus)receive.getPacket();
            if (sPacketEntityStatus.getOpCode() == 35 && sPacketEntityStatus.getEntity((World)EventManager.mc.world) instanceof EntityPlayer) {
                final EntityPlayer entityPlayer = (EntityPlayer)sPacketEntityStatus.getEntity((World)EventManager.mc.world);
                MinecraftForge.EVENT_BUS.post((Event)new TotemPopEvent(entityPlayer));
                TotemPopCounter.getInstance().onTotemPop(entityPlayer);
                LuigiHack.totemPopManager.onTotemPop(entityPlayer);
                LuigiHack.potionManager.onTotemPop(entityPlayer);
            }
        }
        else if (receive.getPacket() instanceof SPacketPlayerListItem && !Feature.fullNullCheck() && this.logoutTimer.passedS(1.0)) {
            final SPacketPlayerListItem sPacketPlayerListItem = (SPacketPlayerListItem)receive.getPacket();
            if (!SPacketPlayerListItem.Action.ADD_PLAYER.equals((Object)sPacketPlayerListItem.getAction()) && !SPacketPlayerListItem.Action.REMOVE_PLAYER.equals((Object)sPacketPlayerListItem.getAction())) {
                return;
            }
            final SPacketPlayerListItem sPacketPlayerListItem2;
            final UUID uuid;
            final EntityPlayer entityPlayer2;
            sPacketPlayerListItem.getEntries().stream().filter(Objects::nonNull).filter(addPlayerData -> !Strings.isNullOrEmpty(addPlayerData.getProfile().getName()) || addPlayerData.getProfile().getId() != null).forEach(addPlayerData2 -> {
                addPlayerData2.getProfile().getId();
                switch (sPacketPlayerListItem2.getAction()) {
                    case ADD_PLAYER: {
                        MinecraftForge.EVENT_BUS.post((Event)new ConnectionEvent(0, uuid, addPlayerData2.getProfile().getName()));
                        break;
                    }
                    case REMOVE_PLAYER: {
                        EventManager.mc.world.getPlayerEntityByUUID(uuid);
                        if (entityPlayer2 != null) {
                            MinecraftForge.EVENT_BUS.post((Event)new ConnectionEvent(1, entityPlayer2, uuid, entityPlayer2.getName()));
                            break;
                        }
                        else {
                            MinecraftForge.EVENT_BUS.post((Event)new ConnectionEvent(2, uuid, (String)null));
                            break;
                        }
                        break;
                    }
                }
            });
        }
        else if (receive.getPacket() instanceof SPacketTimeUpdate) {
            LuigiHack.serverManager.update();
        }
        else if (receive.getPacket() instanceof SPacketSoundEffect && ((SPacketSoundEffect)receive.getPacket()).getSound() == SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT) {
            if (!this.chorusTimer.passedMs(100L)) {
                MinecraftForge.EVENT_BUS.post((Event)new ChorusEvent(((SPacketSoundEffect)receive.getPacket()).getX(), ((SPacketSoundEffect)receive.getPacket()).getY(), ((SPacketSoundEffect)receive.getPacket()).getZ()));
            }
            this.chorusTimer.reset();
        }
    }
    
    @SubscribeEvent
    public void renderHUD(final RenderGameOverlayEvent.Post post) {
        if (post.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
            LuigiHack.textManager.updateResolution();
        }
    }
    
    public boolean ticksOngoing() {
        return this.tickOngoing.get();
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onChatSent(final ClientChatEvent clientChatEvent) {
        if (clientChatEvent.getMessage().startsWith(Command.getCommandPrefix())) {
            clientChatEvent.setCanceled(true);
            try {
                EventManager.mc.ingameGUI.getChatGUI().addToSentMessages(clientChatEvent.getMessage());
                if (clientChatEvent.getMessage().length() > 1) {
                    LuigiHack.commandManager.executeCommand(clientChatEvent.getMessage().substring(Command.getCommandPrefix().length() - 1));
                }
                else {
                    Command.sendMessage("Please enter a command.");
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
                Command.sendMessage("§cAn error occurred while running this command. Check the log!");
            }
            clientChatEvent.setMessage("");
        }
    }
}
