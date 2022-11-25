//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.manager;

import me.snow.luigihack.impl.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import me.snow.luigihack.api.util.*;

public class RotationManager extends Feature
{
    private /* synthetic */ float yaw;
    private /* synthetic */ float pitch;
    
    public void lookAtEntity(final Entity entity) {
        final float[] calcAngle = MathUtil.calcAngle(RotationManager.mc.player.getPositionEyes(RotationManager.mc.getRenderPartialTicks()), entity.getPositionEyes(RotationManager.mc.getRenderPartialTicks()));
        this.setPlayerRotations(calcAngle[0], calcAngle[1]);
    }
    
    public void restoreRotations() {
        RotationManager.mc.player.rotationYaw = this.yaw;
        RotationManager.mc.player.rotationYawHead = this.yaw;
        RotationManager.mc.player.rotationPitch = this.pitch;
    }
    
    public void lookAtPos(final BlockPos blockPos) {
        final float[] calcAngle = MathUtil.calcAngle(RotationManager.mc.player.getPositionEyes(RotationManager.mc.getRenderPartialTicks()), new Vec3d((double)(blockPos.getX() + 0.5f), (double)(blockPos.getY() + 0.5f), (double)(blockPos.getZ() + 0.5f)));
        this.setPlayerRotations(calcAngle[0], calcAngle[1]);
    }
    
    public float getPitch() {
        return this.pitch;
    }
    
    public void updateRotations() {
        this.yaw = RotationManager.mc.player.rotationYaw;
        this.pitch = RotationManager.mc.player.rotationPitch;
    }
    
    public int getDirection4D() {
        return RotationUtil.getDirection4D();
    }
    
    public void setYaw(final float yaw) {
        this.yaw = yaw;
    }
    
    public void lookAtVec3d(final double n, final double n2, final double n3) {
        this.lookAtVec3d(new Vec3d(n, n2, n3));
    }
    
    public float getYaw() {
        return this.yaw;
    }
    
    public void setPitch(final float pitch) {
        this.pitch = pitch;
    }
    
    public void lookAtVec3d(final Vec3d vec3d) {
        final float[] calcAngle = MathUtil.calcAngle(RotationManager.mc.player.getPositionEyes(RotationManager.mc.getRenderPartialTicks()), new Vec3d(vec3d.x, vec3d.y, vec3d.z));
        this.setPlayerRotations(calcAngle[0], calcAngle[1]);
    }
    
    public String getDirection4D(final boolean b) {
        return RotationUtil.getDirection4D(b);
    }
    
    public void setPlayerPitch(final float rotationPitch) {
        RotationManager.mc.player.rotationPitch = rotationPitch;
    }
    
    public void setPlayerRotations(final float n, final float rotationPitch) {
        RotationManager.mc.player.rotationYaw = n;
        RotationManager.mc.player.rotationYawHead = n;
        RotationManager.mc.player.rotationPitch = rotationPitch;
    }
    
    public void setPlayerYaw(final float n) {
        RotationManager.mc.player.rotationYaw = n;
        RotationManager.mc.player.rotationYawHead = n;
    }
}
