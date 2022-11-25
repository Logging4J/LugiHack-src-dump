//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.manager;

import net.minecraft.client.*;
import me.snow.luigihack.api.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.common.*;
import me.snow.luigihack.api.event.events.*;

public class RotationManager2
{
    public static /* synthetic */ boolean onground;
    public static /* synthetic */ Minecraft mc;
    public static /* synthetic */ double y;
    public static /* synthetic */ RotationManager2 INSTANCE;
    public /* synthetic */ float[] _rotations;
    public static /* synthetic */ double x;
    public /* synthetic */ Timer timer;
    public static /* synthetic */ double z;
    
    @SubscribeEvent
    public void Fuck(final UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        this.timer.reset();
        if (updateWalkingPlayerEvent.getStage() == 0) {
            this.setRotations(updateWalkingPlayerEvent.getYaw(), updateWalkingPlayerEvent.getPitch());
        }
    }
    
    public void resetRotations() {
        this._rotations = null;
    }
    
    public static void restorePosition() {
        RotationManager2.mc.player.posX = RotationManager2.x;
        RotationManager2.mc.player.posY = RotationManager2.y;
        RotationManager2.mc.player.posZ = RotationManager2.z;
        RotationManager2.mc.player.onGround = RotationManager2.onground;
    }
    
    public float getYawForMixin(final float n) {
        return (this._rotations != null) ? this._rotations[0] : n;
    }
    
    public static void updatePosition() {
        RotationManager2.x = RotationManager2.mc.player.posX;
        RotationManager2.y = RotationManager2.mc.player.posY;
        RotationManager2.z = RotationManager2.mc.player.posZ;
        RotationManager2.onground = RotationManager2.mc.player.onGround;
    }
    
    public void setRotations(final float n, final float n2) {
        this._rotations = new float[] { n, n2 };
    }
    
    public float getPitchForMixin(final float n) {
        return (this._rotations != null) ? this._rotations[1] : n;
    }
    
    public static void resetRotation() {
        final float rotationYaw = RotationManager2.mc.player.rotationYaw;
        final float rotationPitch = RotationManager2.mc.player.rotationPitch;
        RotationManager2.mc.player.rotationYaw = rotationYaw;
        RotationManager2.mc.player.rotationPitch = rotationPitch;
    }
    
    static {
        RotationManager2.INSTANCE = new RotationManager2();
        RotationManager2.mc = Minecraft.getMinecraft();
    }
    
    public RotationManager2() {
        this.timer = new Timer();
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    public float[] getRotations() {
        return this._rotations;
    }
    
    @SubscribeEvent
    public void Listener(final EventPlayerUpdate eventPlayerUpdate) {
        if (this.timer.passed(100.0)) {
            this.timer.reset();
            this.resetRotations();
        }
    }
}
