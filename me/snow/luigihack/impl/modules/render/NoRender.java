//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.render;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraft.network.play.server.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.event.entity.*;
import net.minecraftforge.client.event.*;
import net.minecraft.entity.passive.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import net.minecraft.util.*;
import net.minecraft.block.state.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.*;
import java.util.function.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.gui.*;
import java.util.*;
import net.minecraft.world.*;
import net.minecraft.item.*;

public class NoRender extends Module
{
    public /* synthetic */ Setting<Boolean> portal;
    public /* synthetic */ Setting<Boss> boss;
    public /* synthetic */ Setting<NoArmor> noArmor;
    public /* synthetic */ Setting<Integer> time;
    public /* synthetic */ Setting<Boolean> hurtcam;
    public /* synthetic */ Setting<Boolean> blocks;
    public /* synthetic */ Setting<Boolean> pigmen;
    public /* synthetic */ Setting<Boolean> totemPops;
    public /* synthetic */ Setting<Boolean> noWeather;
    public /* synthetic */ Setting<Boolean> advancements;
    public /* synthetic */ Setting<Boolean> fire;
    private static /* synthetic */ NoRender INSTANCE;
    public /* synthetic */ Setting<Boolean> pumpkin;
    public /* synthetic */ Setting<Float> scale;
    public /* synthetic */ Setting<Boolean> items;
    public /* synthetic */ Setting<Boolean> timeChange;
    public /* synthetic */ Setting<Boolean> nausea;
    public /* synthetic */ Setting<Boolean> barriers;
    public /* synthetic */ Setting<Boolean> explosions;
    public /* synthetic */ Setting<Boolean> bats;
    
    public static NoRender getInstance() {
        if (NoRender.INSTANCE == null) {
            NoRender.INSTANCE = new NoRender();
        }
        return NoRender.INSTANCE;
    }
    
    @SubscribeEvent
    public void onPacketReceive(final PacketEvent.Receive receive) {
        if (receive.getPacket() instanceof SPacketTimeUpdate & (boolean)this.timeChange.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketExplosion & (boolean)this.explosions.getValue()) {
            receive.setCanceled(true);
        }
    }
    
    @SubscribeEvent
    public void onRenderPre(final RenderGameOverlayEvent.Pre pre) {
        if (pre.getType() == RenderGameOverlayEvent.ElementType.BOSSINFO && this.boss.getValue() != Boss.NONE) {
            pre.setCanceled(true);
        }
    }
    
    public NoRender() {
        super("NoRender", "Allows you to stop rendering stuff", Module.Category.RENDER, true, false, false);
        this.fire = (Setting<Boolean>)this.register(new Setting("Fire", (Object)Boolean.FALSE, "Removes the portal overlay."));
        this.portal = (Setting<Boolean>)this.register(new Setting("Portal", (Object)Boolean.FALSE, "Removes the portal overlay."));
        this.pumpkin = (Setting<Boolean>)this.register(new Setting("Pumpkin", (Object)Boolean.FALSE, "Removes the pumpkin overlay."));
        this.totemPops = (Setting<Boolean>)this.register(new Setting("TotemPop", (Object)Boolean.FALSE, "Removes the Totem overlay."));
        this.items = (Setting<Boolean>)this.register(new Setting("Items", (Object)Boolean.FALSE, "Removes items on the ground."));
        this.nausea = (Setting<Boolean>)this.register(new Setting("Nausea", (Object)Boolean.FALSE, "Removes Portal Nausea."));
        this.hurtcam = (Setting<Boolean>)this.register(new Setting("HurtCam", (Object)Boolean.FALSE, "Removes shaking after taking damage."));
        this.explosions = (Setting<Boolean>)this.register(new Setting("Explosions", (Object)Boolean.FALSE, "Removes crystal explosions."));
        this.noWeather = (Setting<Boolean>)this.register(new Setting("Weather", (Object)Boolean.FALSE, "AntiWeather"));
        this.boss = (Setting<Boss>)this.register(new Setting("BossBars", (Object)Boss.NONE, "Modifies the bossbars."));
        this.scale = (Setting<Float>)this.register(new Setting("Scale", (Object)0.5f, (Object)0.5f, (Object)1.0f, p0 -> this.boss.getValue() == Boss.MINIMIZE || this.boss.getValue() == Boss.STACK, "Scale of the bars."));
        this.bats = (Setting<Boolean>)this.register(new Setting("Bats", (Object)Boolean.FALSE, "Removes bats."));
        this.noArmor = (Setting<NoArmor>)this.register(new Setting("NoArmor", (Object)NoArmor.NONE, "Doesnt Render Armor on players."));
        this.barriers = (Setting<Boolean>)this.register(new Setting("Barriers", (Object)Boolean.FALSE, "Barriers"));
        this.blocks = (Setting<Boolean>)this.register(new Setting("Blocks", (Object)Boolean.FALSE, "Blocks"));
        this.advancements = (Setting<Boolean>)this.register(new Setting("Advancements", (Object)false));
        this.pigmen = (Setting<Boolean>)this.register(new Setting("Pigmen", (Object)false));
        this.timeChange = (Setting<Boolean>)this.register(new Setting("TimeChange", (Object)false));
        this.time = (Setting<Integer>)this.register(new Setting("Time", (Object)0, (Object)0, (Object)23000, p0 -> (boolean)this.timeChange.getValue()));
        this.setInstance();
    }
    
    @SubscribeEvent
    public void onPlaySound(final PlaySoundAtEntityEvent playSoundAtEntityEvent) {
        if (((boolean)this.bats.getValue() && playSoundAtEntityEvent.getSound().equals(SoundEvents.ENTITY_BAT_AMBIENT)) || playSoundAtEntityEvent.getSound().equals(SoundEvents.ENTITY_BAT_DEATH) || playSoundAtEntityEvent.getSound().equals(SoundEvents.ENTITY_BAT_HURT) || playSoundAtEntityEvent.getSound().equals(SoundEvents.ENTITY_BAT_LOOP) || playSoundAtEntityEvent.getSound().equals(SoundEvents.ENTITY_BAT_TAKEOFF)) {
            playSoundAtEntityEvent.setVolume(0.0f);
            playSoundAtEntityEvent.setPitch(0.0f);
            playSoundAtEntityEvent.setCanceled(true);
        }
    }
    
    private void setInstance() {
        NoRender.INSTANCE = this;
    }
    
    @SubscribeEvent
    public void onRenderLiving(final RenderLivingEvent.Pre<?> pre) {
        if ((boolean)this.bats.getValue() && pre.getEntity() instanceof EntityBat) {
            pre.setCanceled(true);
        }
    }
    
    static {
        NoRender.INSTANCE = new NoRender();
        NoRender.INSTANCE = new NoRender();
    }
    
    public void showBarrierParticles(final int n, final int n2, final int n3, final int n4, final Random random, final boolean b, final BlockPos.MutableBlockPos mutableBlockPos) {
        final int n5 = n + NoRender.mc.world.rand.nextInt(n4) - NoRender.mc.world.rand.nextInt(n4);
        final int n6 = n2 + NoRender.mc.world.rand.nextInt(n4) - NoRender.mc.world.rand.nextInt(n4);
        final int n7 = n3 + NoRender.mc.world.rand.nextInt(n4) - NoRender.mc.world.rand.nextInt(n4);
        mutableBlockPos.setPos(n5, n6, n7);
        final IBlockState getBlockState = NoRender.mc.world.getBlockState((BlockPos)mutableBlockPos);
        getBlockState.getBlock().randomDisplayTick(getBlockState, (World)NoRender.mc.world, (BlockPos)mutableBlockPos, random);
        if (!b && getBlockState.getBlock() == Blocks.BARRIER) {
            NoRender.mc.world.spawnParticle(EnumParticleTypes.BARRIER, (double)(n5 + 0.5f), (double)(n6 + 0.5f), (double)(n7 + 0.5f), 0.0, 0.0, 0.0, new int[0]);
        }
    }
    
    public void onUpdate() {
        if (this.items.getValue()) {
            NoRender.mc.world.loadedEntityList.stream().filter(EntityItem.class::isInstance).map(EntityItem.class::cast).forEach(Entity::setDead);
        }
        if ((boolean)this.noWeather.getValue() && NoRender.mc.world.isRaining()) {
            NoRender.mc.world.setRainStrength(0.0f);
        }
        if (this.timeChange.getValue()) {
            NoRender.mc.world.setWorldTime((long)(int)this.time.getValue());
        }
    }
    
    @SubscribeEvent
    public void onRenderPost(final RenderGameOverlayEvent.Post post) {
        if (post.getType() == RenderGameOverlayEvent.ElementType.BOSSINFO && this.boss.getValue() != Boss.NONE) {
            if (this.boss.getValue() == Boss.MINIMIZE) {
                final Map mapBossInfos = NoRender.mc.ingameGUI.getBossOverlay().mapBossInfos;
                if (mapBossInfos == null) {
                    return;
                }
                final int getScaledWidth = new ScaledResolution(NoRender.mc).getScaledWidth();
                int n = 12;
                final Iterator<Map.Entry<K, BossInfoClient>> iterator = mapBossInfos.entrySet().iterator();
                while (iterator.hasNext()) {
                    final BossInfoClient bossInfoClient = iterator.next().getValue();
                    final String getFormattedText = bossInfoClient.getName().getFormattedText();
                    final int n2 = (int)(getScaledWidth / (float)this.scale.getValue() / 2.0f - 91.0f);
                    GL11.glScaled((double)(float)this.scale.getValue(), (double)(float)this.scale.getValue(), 1.0);
                    if (!post.isCanceled()) {
                        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
                        NoRender.mc.getTextureManager().bindTexture(GuiBossOverlay.GUI_BARS_TEXTURES);
                        NoRender.mc.ingameGUI.getBossOverlay().render(n2, n, (BossInfo)bossInfoClient);
                        NoRender.mc.fontRenderer.drawStringWithShadow(getFormattedText, getScaledWidth / (float)this.scale.getValue() / 2.0f - NoRender.mc.fontRenderer.getStringWidth(getFormattedText) / 2, (float)(n - 9), 16777215);
                    }
                    GL11.glScaled(1.0 / (float)this.scale.getValue(), 1.0 / (float)this.scale.getValue(), 1.0);
                    n += 10 + NoRender.mc.fontRenderer.FONT_HEIGHT;
                }
            }
            else if (this.boss.getValue() == Boss.STACK) {
                final Map mapBossInfos2 = NoRender.mc.ingameGUI.getBossOverlay().mapBossInfos;
                final HashMap<Object, Pair<Object, S>> hashMap = new HashMap<Object, Pair<Object, S>>();
                for (final Map.Entry<K, BossInfoClient> entry : mapBossInfos2.entrySet()) {
                    final String getFormattedText2 = entry.getValue().getName().getFormattedText();
                    if (hashMap.containsKey(getFormattedText2)) {
                        final Pair<Object, S> pair = hashMap.get(getFormattedText2);
                        hashMap.put(getFormattedText2, (Pair<Object, S>)new Pair<Object, Object>(pair.getKey(), (int)pair.getValue() + 1));
                    }
                    else {
                        hashMap.put(getFormattedText2, (Pair<Object, S>)new Pair<Object, Object>(entry.getValue(), (S)1));
                    }
                }
                final int getScaledWidth2 = new ScaledResolution(NoRender.mc).getScaledWidth();
                int n3 = 12;
                for (final Map.Entry<String, Pair<Object, S>> entry2 : hashMap.entrySet()) {
                    final String str = entry2.getKey();
                    final BossInfoClient bossInfoClient2 = entry2.getValue().getKey();
                    final String value = String.valueOf(new StringBuilder().append(str).append(" x").append((int)entry2.getValue().getValue()));
                    final int n4 = (int)(getScaledWidth2 / (float)this.scale.getValue() / 2.0f - 91.0f);
                    GL11.glScaled((double)(float)this.scale.getValue(), (double)(float)this.scale.getValue(), 1.0);
                    if (!post.isCanceled()) {
                        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
                        NoRender.mc.getTextureManager().bindTexture(GuiBossOverlay.GUI_BARS_TEXTURES);
                        NoRender.mc.ingameGUI.getBossOverlay().render(n4, n3, (BossInfo)bossInfoClient2);
                        NoRender.mc.fontRenderer.drawStringWithShadow(value, getScaledWidth2 / (float)this.scale.getValue() / 2.0f - NoRender.mc.fontRenderer.getStringWidth(value) / 2, (float)(n3 - 9), 16777215);
                    }
                    GL11.glScaled(1.0 / (float)this.scale.getValue(), 1.0 / (float)this.scale.getValue(), 1.0);
                    n3 += 10 + NoRender.mc.fontRenderer.FONT_HEIGHT;
                }
            }
        }
    }
    
    public void doVoidFogParticles(final int n, final int n2, final int n3) {
        final Random random = new Random();
        final ItemStack getHeldItemMainhand = NoRender.mc.player.getHeldItemMainhand();
        final boolean b = !(boolean)this.barriers.getValue() || (NoRender.mc.playerController.getCurrentGameType() == GameType.CREATIVE && !getHeldItemMainhand.isEmpty() && getHeldItemMainhand.getItem() == Item.getItemFromBlock(Blocks.BARRIER));
        final BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
        for (int i = 0; i < 667; ++i) {
            this.showBarrierParticles(n, n2, n3, 16, random, b, mutableBlockPos);
            this.showBarrierParticles(n, n2, n3, 32, random, b, mutableBlockPos);
        }
    }
    
    public enum Boss
    {
        REMOVE, 
        MINIMIZE, 
        NONE, 
        STACK;
    }
    
    public enum Skylight
    {
        ALL, 
        NONE, 
        ENTITY, 
        WORLD;
    }
    
    public enum NoArmor
    {
        ALL, 
        NONE, 
        HELMET;
    }
    
    public static class Pair<T, S>
    {
        private /* synthetic */ S value;
        private /* synthetic */ T key;
        
        public void setKey(final T key) {
            this.key = key;
        }
        
        public void setValue(final S value) {
            this.value = value;
        }
        
        public T getKey() {
            return this.key;
        }
        
        public S getValue() {
            return this.value;
        }
        
        public Pair(final T key, final S value) {
            this.key = key;
            this.value = value;
        }
    }
}
