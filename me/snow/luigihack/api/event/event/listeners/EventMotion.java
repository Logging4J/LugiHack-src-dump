//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.event.event.listeners;

import me.snow.luigihack.api.event.event.*;
import net.minecraft.util.math.*;

public class EventMotion extends Event<EventMotion>
{
    private /* synthetic */ double lastY;
    public /* synthetic */ double x;
    public /* synthetic */ double y;
    public /* synthetic */ float yaw;
    public /* synthetic */ float pitch;
    private /* synthetic */ double lastZ;
    public /* synthetic */ double z;
    public /* synthetic */ float lastPitch;
    private /* synthetic */ double lastX;
    public /* synthetic */ boolean onGround;
    public /* synthetic */ float lastYaw;
    public /* synthetic */ boolean lastOnGround;
    
    public EventMotion(final double n, final double n2, final double n3, final float n4, final float n5, final boolean b) {
        this.x = n;
        this.y = n2;
        this.z = n3;
        this.yaw = n4;
        this.pitch = n5;
        this.onGround = b;
        this.lastX = n;
        this.lastY = n2;
        this.lastZ = n3;
        this.lastYaw = n4;
        this.lastPitch = n5;
        this.lastOnGround = b;
    }
    
    public void setY(final double y) {
        this.y = y;
    }
    
    public void setOnGround(final boolean onGround) {
        this.onGround = onGround;
    }
    
    public double getZ() {
        return this.z;
    }
    
    public double getY() {
        return this.y;
    }
    
    public float getPitch() {
        return this.pitch;
    }
    
    public void setPosition(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public boolean isOnGround() {
        return this.onGround;
    }
    
    public void setPosition(final BlockPos blockPos) {
        this.x = blockPos.getX() + 0.5;
        this.y = blockPos.getY();
        this.z = blockPos.getZ() + 0.5;
    }
    
    public float getYaw() {
        return this.yaw;
    }
    
    public void setYaw(final float yaw) {
        this.yaw = yaw;
    }
    
    public boolean isModded() {
        return this.lastX != this.x || this.lastY != this.y || this.lastZ != this.z || this.lastYaw != this.yaw || this.lastPitch != this.pitch || this.lastOnGround != this.onGround;
    }
    
    public double getX() {
        return this.x;
    }
    
    public void setPitch(final float pitch) {
        this.pitch = pitch;
    }
    
    public void setX(final double x) {
        this.x = x;
    }
    
    public void setZ(final double z) {
        this.z = z;
    }
}
