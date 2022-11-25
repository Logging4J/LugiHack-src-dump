//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.event.events;

import me.snow.luigihack.api.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.client.*;

@Cancelable
public class UpdateWalkingPlayerEvent extends EventStage
{
    public static /* synthetic */ UpdateWalkingPlayerEvent INSTANCE;
    private static /* synthetic */ UpdateWalkingPlayerEvent instance;
    protected /* synthetic */ float yaw;
    protected /* synthetic */ boolean onGround;
    protected /* synthetic */ double x;
    protected /* synthetic */ double y;
    protected /* synthetic */ double z;
    protected /* synthetic */ float pitch;
    
    public double getZ() {
        return this.z;
    }
    
    public void setPitch(final double n) {
        this.pitch = (float)n;
    }
    
    public void setX(final double x) {
        this.x = x;
    }
    
    public UpdateWalkingPlayerEvent(final int n, final double x, final double y, final double z, final float yaw, final float pitch, final boolean onGround) {
        super(n);
        UpdateWalkingPlayerEvent.INSTANCE = this;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
    }
    
    public void setRotation(final float yaw, final float pitch) {
        if (Minecraft.getMinecraft().player != null) {
            Minecraft.getMinecraft().player.rotationYawHead = yaw;
            Minecraft.getMinecraft().player.renderYawOffset = yaw;
        }
        this.setYaw(yaw);
        this.setPitch(pitch);
    }
    
    public UpdateWalkingPlayerEvent(final int n) {
        super(n);
    }
    
    public void setPitch(final float pitch) {
        this.pitch = pitch;
    }
    
    public double getY() {
        return this.y;
    }
    
    public double getX() {
        return this.x;
    }
    
    public void setOnGround(final boolean onGround) {
        this.onGround = onGround;
    }
    
    public void setY(final double y) {
        this.y = y;
    }
    
    public void setYaw(final float yaw) {
        this.yaw = yaw;
    }
    
    public boolean getOnGround() {
        return this.onGround;
    }
    
    public UpdateWalkingPlayerEvent() {
        UpdateWalkingPlayerEvent.instance = this;
    }
    
    public void setZ(final double z) {
        this.z = z;
    }
    
    public static UpdateWalkingPlayerEvent getInstance() {
        if (UpdateWalkingPlayerEvent.instance == null) {
            UpdateWalkingPlayerEvent.instance = new UpdateWalkingPlayerEvent();
        }
        return UpdateWalkingPlayerEvent.instance;
    }
    
    public float getYaw() {
        return this.yaw;
    }
    
    public void setYaw(final double n) {
        this.yaw = (float)n;
    }
    
    public float getPitch() {
        return this.pitch;
    }
    
    public void setRotation2(final float yaw, final float pitch) {
        if (Minecraft.getMinecraft().player != null) {
            Minecraft.getMinecraft().player.rotationYawHead = yaw;
            Minecraft.getMinecraft().player.renderYawOffset = yaw;
        }
        this.setYaw(yaw);
        this.setPitch(pitch);
    }
}
