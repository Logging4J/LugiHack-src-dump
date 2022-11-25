//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.manager;

import me.snow.luigihack.impl.*;
import me.snow.luigihack.api.util.ca.util.*;
import net.minecraft.entity.*;
import net.minecraft.network.play.server.*;
import me.snow.luigihack.api.util.ca.event.*;
import net.minecraft.network.play.client.*;
import net.minecraft.util.math.*;

public class NewRotationManager extends Feature
{
    private /* synthetic */ float spoofedPitch;
    private /* synthetic */ float yaw;
    private /* synthetic */ float pitch;
    private /* synthetic */ float spoofedYaw;
    
    public void setPlayerRotations(final float n, final float rotationPitch) {
        NewRotationManager.mc.player.rotationYaw = n;
        NewRotationManager.mc.player.rotationYawHead = n;
        NewRotationManager.mc.player.rotationPitch = rotationPitch;
    }
    
    public void restoreRotations() {
        NewRotationManager.mc.player.rotationYaw = this.yaw;
        NewRotationManager.mc.player.rotationYawHead = this.yaw;
        NewRotationManager.mc.player.rotationPitch = this.pitch;
    }
    
    public int getDirection4D() {
        return RotationUtilCa.getDirection4D();
    }
    
    public float getSpoofedYaw() {
        return MathsUtilCa.wrapDegrees(this.spoofedYaw);
    }
    
    public void updateRotations() {
        this.yaw = NewRotationManager.mc.player.rotationYaw;
        this.pitch = NewRotationManager.mc.player.rotationPitch;
    }
    
    public void setPlayerYaw(final float n) {
        NewRotationManager.mc.player.rotationYaw = n;
        NewRotationManager.mc.player.rotationYawHead = n;
    }
    
    public void lookAtEntity(final Entity entity) {
        final float[] calcAngle = MathsUtilCa.calcAngle(NewRotationManager.mc.player.getPositionEyes(NewRotationManager.mc.getRenderPartialTicks()), entity.getPositionEyes(NewRotationManager.mc.getRenderPartialTicks()));
        this.setPlayerRotations(calcAngle[0], calcAngle[1]);
    }
    
    public void onPacketReceive(final SPacketPlayerPosLook sPacketPlayerPosLook) {
        this.spoofedPitch = sPacketPlayerPosLook.getPitch();
        this.spoofedYaw = sPacketPlayerPosLook.getYaw();
    }
    
    public String getDirection4D(final boolean b) {
        return RotationUtilCa.getDirection4D(b);
    }
    
    public void setPlayerPitch(final float rotationPitch) {
        NewRotationManager.mc.player.rotationPitch = rotationPitch;
    }
    
    public void onPacketSend(final PacketEventWp packetEventWp) {
        if (packetEventWp.getPacket() instanceof CPacketPlayer.Rotation || packetEventWp.getPacket() instanceof CPacketPlayer.PositionRotation) {
            this.spoofedPitch = packetEventWp.getPacket().getPitch(0.0f);
            this.spoofedYaw = packetEventWp.getPacket().getYaw(0.0f);
        }
    }
    
    public void lookAtPos(final BlockPos blockPos) {
        final float[] calcAngle = MathsUtilCa.calcAngle(NewRotationManager.mc.player.getPositionEyes(NewRotationManager.mc.getRenderPartialTicks()), new Vec3d((double)(blockPos.getX() + 0.5f), (double)(blockPos.getY() + 0.5f), (double)(blockPos.getZ() + 0.5f)));
        this.setPlayerRotations(calcAngle[0], calcAngle[1]);
    }
    
    public float getPitch() {
        return this.pitch;
    }
    
    public float getSpoofedPitch() {
        return this.spoofedPitch;
    }
    
    public float getYaw() {
        return this.yaw;
    }
    
    public void lookAtVec3d(final double n, final double n2, final double n3) {
        this.lookAtVec3d(new Vec3d(n, n2, n3));
    }
    
    public void setPitch(final float pitch) {
        this.pitch = pitch;
    }
    
    public void setYaw(final float yaw) {
        this.yaw = yaw;
    }
    
    public void lookAtVec3d(final Vec3d vec3d) {
        final float[] calcAngle = MathsUtilCa.calcAngle(NewRotationManager.mc.player.getPositionEyes(NewRotationManager.mc.getRenderPartialTicks()), new Vec3d(vec3d.x, vec3d.y, vec3d.z));
        this.setPlayerRotations(calcAngle[0], calcAngle[1]);
    }
}
