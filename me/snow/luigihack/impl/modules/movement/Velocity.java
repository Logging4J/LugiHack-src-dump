//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.movement;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraft.entity.projectile.*;
import net.minecraft.world.*;
import net.minecraft.network.play.server.*;
import net.minecraft.entity.*;
import net.minecraft.init.*;

public class Velocity extends Module
{
    public /* synthetic */ Setting<Boolean> explosions;
    public /* synthetic */ Setting<Boolean> blocks;
    public /* synthetic */ Setting<Boolean> bobbers;
    private static /* synthetic */ Velocity INSTANCE;
    public /* synthetic */ Setting<Float> vertical;
    public /* synthetic */ Setting<Boolean> knockBack;
    public /* synthetic */ Setting<Float> horizontal;
    public /* synthetic */ Setting<Boolean> noPush;
    public /* synthetic */ Setting<Boolean> ice;
    public /* synthetic */ Setting<Boolean> water;
    
    @SubscribeEvent
    public void onPush(final PushEvent pushEvent) {
        if (pushEvent.getStage() == 0 && (boolean)this.noPush.getValue() && pushEvent.entity.equals((Object)Velocity.mc.player)) {
            if ((float)this.horizontal.getValue() == 0.0f && (float)this.vertical.getValue() == 0.0f) {
                pushEvent.setCanceled(true);
                return;
            }
            pushEvent.x = -pushEvent.x * (float)this.horizontal.getValue();
            pushEvent.y = -pushEvent.y * (float)this.vertical.getValue();
            pushEvent.z = -pushEvent.z * (float)this.horizontal.getValue();
        }
        else if (pushEvent.getStage() == 1 && (boolean)this.blocks.getValue()) {
            pushEvent.setCanceled(true);
        }
        else if (pushEvent.getStage() == 2 && (boolean)this.water.getValue() && Velocity.mc.player != null && Velocity.mc.player.equals((Object)pushEvent.entity)) {
            pushEvent.setCanceled(true);
        }
    }
    
    @SubscribeEvent
    public void onPacketReceived(final PacketEvent.Receive receive) {
        if (receive.getStage() == 0 && Velocity.mc.player != null) {
            final SPacketEntityVelocity sPacketEntityVelocity;
            if ((boolean)this.knockBack.getValue() && receive.getPacket() instanceof SPacketEntityVelocity && (sPacketEntityVelocity = (SPacketEntityVelocity)receive.getPacket()).getEntityID() == Velocity.mc.player.entityId) {
                if ((float)this.horizontal.getValue() == 0.0f && (float)this.vertical.getValue() == 0.0f) {
                    receive.setCanceled(true);
                    return;
                }
                sPacketEntityVelocity.motionX *= (int)(float)this.horizontal.getValue();
                sPacketEntityVelocity.motionY *= (int)(float)this.vertical.getValue();
                sPacketEntityVelocity.motionZ *= (int)(float)this.horizontal.getValue();
            }
            final SPacketEntityStatus sPacketEntityStatus;
            final Entity getEntity;
            if (receive.getPacket() instanceof SPacketEntityStatus && (boolean)this.bobbers.getValue() && (sPacketEntityStatus = (SPacketEntityStatus)receive.getPacket()).getOpCode() == 31 && (getEntity = sPacketEntityStatus.getEntity((World)Velocity.mc.world)) instanceof EntityFishHook && ((EntityFishHook)getEntity).caughtEntity == Velocity.mc.player) {
                receive.setCanceled(true);
            }
            if ((boolean)this.explosions.getValue() && receive.getPacket() instanceof SPacketExplosion) {
                if ((float)this.horizontal.getValue() == 0.0f && (float)this.vertical.getValue() == 0.0f) {
                    receive.setCanceled(true);
                    return;
                }
                final SPacketExplosion sPacketExplosion2;
                final SPacketExplosion sPacketExplosion = sPacketExplosion2 = (SPacketExplosion)receive.getPacket();
                sPacketExplosion2.motionX *= (float)this.horizontal.getValue();
                final SPacketExplosion sPacketExplosion3 = sPacketExplosion;
                sPacketExplosion3.motionY *= (float)this.vertical.getValue();
                final SPacketExplosion sPacketExplosion4 = sPacketExplosion;
                sPacketExplosion4.motionZ *= (float)this.horizontal.getValue();
            }
        }
    }
    
    public Velocity() {
        super("Velocity", "Allows you to control your velocity", Module.Category.MOVEMENT, true, false, false);
        this.knockBack = (Setting<Boolean>)this.register(new Setting("KnockBack", (Object)true));
        this.noPush = (Setting<Boolean>)this.register(new Setting("NoPush", (Object)true));
        this.horizontal = (Setting<Float>)this.register(new Setting("Horizontal", (Object)0.0f, (Object)0.0f, (Object)100.0f));
        this.vertical = (Setting<Float>)this.register(new Setting("Vertical", (Object)0.0f, (Object)0.0f, (Object)100.0f));
        this.explosions = (Setting<Boolean>)this.register(new Setting("Explosions", (Object)true));
        this.bobbers = (Setting<Boolean>)this.register(new Setting("Bobbers", (Object)true));
        this.water = (Setting<Boolean>)this.register(new Setting("Water", (Object)false));
        this.blocks = (Setting<Boolean>)this.register(new Setting("Blocks", (Object)false));
        this.ice = (Setting<Boolean>)this.register(new Setting("Ice", (Object)false));
        this.setInstance();
    }
    
    public static Velocity getINSTANCE() {
        if (Velocity.INSTANCE == null) {
            Velocity.INSTANCE = new Velocity();
        }
        return Velocity.INSTANCE;
    }
    
    public void onDisable() {
        if (IceSpeed.getINSTANCE().isOff()) {
            Blocks.ICE.slipperiness = 0.98f;
            Blocks.PACKED_ICE.slipperiness = 0.98f;
            Blocks.FROSTED_ICE.slipperiness = 0.98f;
        }
    }
    
    static {
        Velocity.INSTANCE = new Velocity();
    }
    
    private void setInstance() {
        Velocity.INSTANCE = this;
    }
    
    public String getDisplayInfo() {
        return String.valueOf(new StringBuilder().append("H").append(this.horizontal.getValue()).append("%|V").append(this.vertical.getValue()).append("H"));
    }
    
    public void onUpdate() {
        if (IceSpeed.getINSTANCE().isOff() && (boolean)this.ice.getValue()) {
            Blocks.ICE.slipperiness = 0.6f;
            Blocks.PACKED_ICE.slipperiness = 0.6f;
            Blocks.FROSTED_ICE.slipperiness = 0.6f;
        }
    }
}
