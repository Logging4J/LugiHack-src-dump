//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.movement;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.util.math.*;
import net.minecraft.network.play.server.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.snow.luigihack.impl.modules.player.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.client.entity.*;
import net.minecraft.network.play.client.*;
import me.snow.luigihack.api.event.events.*;

public class Jesus extends Module
{
    public /* synthetic */ Setting<Mode> mode;
    private static /* synthetic */ Jesus INSTANCE;
    public /* synthetic */ Setting<Boolean> cancelVehicle;
    public static /* synthetic */ AxisAlignedBB offset;
    public /* synthetic */ Setting<EventMode> eventMode;
    public /* synthetic */ Setting<Boolean> fall;
    private /* synthetic */ boolean grounded;
    
    @SubscribeEvent
    public void onPacketReceived(final PacketEvent.Receive receive) {
        if ((boolean)this.cancelVehicle.getValue() && receive.getPacket() instanceof SPacketMoveVehicle) {
            receive.setCanceled(true);
        }
    }
    
    public String getDisplayInfo() {
        if (this.mode.getValue() == Mode.NORMAL) {
            return null;
        }
        return this.mode.currentEnumName();
    }
    
    public Jesus() {
        super("Jesus", "Allows you to walk on water", Module.Category.PLAYER, true, false, false);
        this.mode = (Setting<Mode>)this.register(new Setting("Mode", (Object)Mode.NORMAL));
        this.cancelVehicle = (Setting<Boolean>)this.register(new Setting("NoVehicle", (Object)false));
        this.eventMode = (Setting<EventMode>)this.register(new Setting("Jump", (Object)EventMode.PRE, p0 -> this.mode.getValue() == Mode.TRAMPOLINE));
        this.fall = (Setting<Boolean>)this.register(new Setting("NoFall", (Object)false, p0 -> this.mode.getValue() == Mode.TRAMPOLINE));
        this.grounded = false;
        Jesus.INSTANCE = this;
    }
    
    @SubscribeEvent
    public void onLiquidCollision(final JesusEvent jesusEvent) {
        if (fullNullCheck() || Freecam.getInstance().isOn()) {
            return;
        }
        if (jesusEvent.getStage() == 0 && (this.mode.getValue() == Mode.BOUNCE || this.mode.getValue() == Mode.VANILLA || this.mode.getValue() == Mode.NORMAL) && Jesus.mc.world != null && Jesus.mc.player != null && EntityUtil.checkCollide() && Jesus.mc.player.motionY < 0.10000000149011612 && jesusEvent.getPos().getY() < Jesus.mc.player.posY - 0.05000000074505806) {
            if (Jesus.mc.player.getRidingEntity() != null) {
                jesusEvent.setBoundingBox(new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.949999988079071, 1.0));
            }
            else {
                jesusEvent.setBoundingBox(Block.FULL_BLOCK_AABB);
            }
            jesusEvent.setCanceled(true);
        }
    }
    
    private void doTrampoline() {
        if (Jesus.mc.player.isSneaking()) {
            return;
        }
        if (EntityUtil.isAboveLiquid((Entity)Jesus.mc.player) && !Jesus.mc.player.isSneaking() && !Jesus.mc.gameSettings.keyBindJump.pressed) {
            Jesus.mc.player.motionY = 0.1;
            return;
        }
        if (Jesus.mc.player.onGround || Jesus.mc.player.isOnLadder()) {
            this.grounded = false;
        }
        if (Jesus.mc.player.motionY > 0.0) {
            if (Jesus.mc.player.motionY < 0.03 && this.grounded) {
                final EntityPlayerSP player = Jesus.mc.player;
                player.motionY += 0.06713;
            }
            else if (Jesus.mc.player.motionY <= 0.05 && this.grounded) {
                final EntityPlayerSP player2 = Jesus.mc.player;
                player2.motionY *= 1.20000000999;
                final EntityPlayerSP player3 = Jesus.mc.player;
                player3.motionY += 0.06;
            }
            else if (Jesus.mc.player.motionY <= 0.08 && this.grounded) {
                final EntityPlayerSP player4 = Jesus.mc.player;
                player4.motionY *= 1.20000003;
                final EntityPlayerSP player5 = Jesus.mc.player;
                player5.motionY += 0.055;
            }
            else if (Jesus.mc.player.motionY <= 0.112 && this.grounded) {
                final EntityPlayerSP player6 = Jesus.mc.player;
                player6.motionY += 0.0535;
            }
            else if (this.grounded) {
                final EntityPlayerSP player7 = Jesus.mc.player;
                player7.motionY *= 1.000000000002;
                final EntityPlayerSP player8 = Jesus.mc.player;
                player8.motionY += 0.0517;
            }
        }
        if (this.grounded && Jesus.mc.player.motionY < 0.0 && Jesus.mc.player.motionY > -0.3) {
            final EntityPlayerSP player9 = Jesus.mc.player;
            player9.motionY += 0.045835;
        }
        if (!(boolean)this.fall.getValue()) {
            Jesus.mc.player.fallDistance = 0.0f;
        }
        if (!EntityUtil.checkForLiquid((Entity)Jesus.mc.player, true)) {
            return;
        }
        if (EntityUtil.checkForLiquid((Entity)Jesus.mc.player, true)) {
            Jesus.mc.player.motionY = 0.5;
        }
        this.grounded = true;
    }
    
    public static Jesus getInstance() {
        if (Jesus.INSTANCE == null) {
            Jesus.INSTANCE = new Jesus();
        }
        return Jesus.INSTANCE;
    }
    
    @SubscribeEvent
    public void sendPacket(final PacketEvent.Send send) {
        if (send.getPacket() instanceof CPacketPlayer && Freecam.getInstance().isOff() && (this.mode.getValue() == Mode.BOUNCE || this.mode.getValue() == Mode.NORMAL) && Jesus.mc.player.getRidingEntity() == null && !Jesus.mc.gameSettings.keyBindJump.isKeyDown()) {
            final CPacketPlayer cPacketPlayer = (CPacketPlayer)send.getPacket();
            if (!EntityUtil.isInLiquid() && EntityUtil.isOnLiquid(0.05000000074505806) && EntityUtil.checkCollide() && Jesus.mc.player.ticksExisted % 3 == 0) {
                final CPacketPlayer cPacketPlayer2 = cPacketPlayer;
                cPacketPlayer2.y -= 0.05000000074505806;
            }
        }
    }
    
    static {
        Jesus.offset = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.9999, 1.0);
        Jesus.INSTANCE = new Jesus();
    }
    
    @SubscribeEvent
    public void updateWalkingPlayer(final UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (fullNullCheck() || Freecam.getInstance().isOn()) {
            return;
        }
        if (updateWalkingPlayerEvent.getStage() == 0 && (this.mode.getValue() == Mode.BOUNCE || this.mode.getValue() == Mode.VANILLA || this.mode.getValue() == Mode.NORMAL) && !Jesus.mc.player.isSneaking() && !Jesus.mc.player.noClip && !Jesus.mc.gameSettings.keyBindJump.isKeyDown() && EntityUtil.isInLiquid()) {
            Jesus.mc.player.motionY = 0.10000000149011612;
        }
        if (updateWalkingPlayerEvent.getStage() == 0 && this.mode.getValue() == Mode.TRAMPOLINE && (this.eventMode.getValue() == EventMode.ALL || this.eventMode.getValue() == EventMode.PRE)) {
            this.doTrampoline();
        }
        else if (updateWalkingPlayerEvent.getStage() == 1 && this.mode.getValue() == Mode.TRAMPOLINE && (this.eventMode.getValue() == EventMode.ALL || this.eventMode.getValue() == EventMode.POST)) {
            this.doTrampoline();
        }
    }
    
    public enum EventMode
    {
        POST, 
        PRE, 
        ALL;
    }
    
    public enum Mode
    {
        BOUNCE, 
        NORMAL, 
        TRAMPOLINE, 
        VANILLA;
    }
}
