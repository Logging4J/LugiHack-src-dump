//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util;

import net.minecraft.client.*;
import net.minecraft.potion.*;
import net.minecraft.entity.*;

public class MotionUtil implements Util
{
    public static double getBaseMoveSpeed() {
        double n = 0.2873;
        if (Minecraft.getMinecraft().player != null && Minecraft.getMinecraft().player.isPotionActive(Potion.getPotionById(1))) {
            n *= 1.0 + 0.2 * (Minecraft.getMinecraft().player.getActivePotionEffect(Potion.getPotionById(1)).getAmplifier() + 1);
        }
        return n;
    }
    
    public static boolean isMovings() {
        return Minecraft.getMinecraft().player.moveForward != 0.0 || Minecraft.getMinecraft().player.moveStrafing != 0.0;
    }
    
    public static double[] getMoveSpeed(final double n) {
        float moveForward = MotionUtil.mc.player.movementInput.moveForward;
        float moveStrafe = MotionUtil.mc.player.movementInput.moveStrafe;
        float rotationYaw = MotionUtil.mc.player.rotationYaw;
        if (moveForward != 0.0f) {
            if (moveStrafe >= 1.0f) {
                rotationYaw += ((moveForward > 0.0f) ? -45 : 45);
                moveStrafe = 0.0f;
            }
            else if (moveStrafe <= -1.0f) {
                rotationYaw += ((moveForward > 0.0f) ? 45 : -45);
                moveStrafe = 0.0f;
            }
            if (moveForward > 0.0f) {
                moveForward = 1.0f;
            }
            else if (moveForward < 0.0f) {
                moveForward = -1.0f;
            }
        }
        final double sin = Math.sin(Math.toRadians(rotationYaw + 90.0f));
        final double cos = Math.cos(Math.toRadians(rotationYaw + 90.0f));
        double n2 = moveForward * n * cos + moveStrafe * n * sin;
        double n3 = moveForward * n * sin - moveStrafe * n * cos;
        if (!isMovingMomentum()) {
            n2 = 0.0;
            n3 = 0.0;
        }
        return new double[] { n2, n3 };
    }
    
    public static double[] forward(final double n) {
        float moveForward = Minecraft.getMinecraft().player.movementInput.moveForward;
        float moveStrafe = Minecraft.getMinecraft().player.movementInput.moveStrafe;
        float n2 = Minecraft.getMinecraft().player.prevRotationYaw + (Minecraft.getMinecraft().player.rotationYaw - Minecraft.getMinecraft().player.prevRotationYaw) * Minecraft.getMinecraft().getRenderPartialTicks();
        if (moveForward != 0.0f) {
            if (moveStrafe > 0.0f) {
                n2 += ((moveForward > 0.0f) ? -45 : 45);
            }
            else if (moveStrafe < 0.0f) {
                n2 += ((moveForward > 0.0f) ? 45 : -45);
            }
            moveStrafe = 0.0f;
            if (moveForward > 0.0f) {
                moveForward = 1.0f;
            }
            else if (moveForward < 0.0f) {
                moveForward = -1.0f;
            }
        }
        final double sin = Math.sin(Math.toRadians(n2 + 90.0f));
        final double cos = Math.cos(Math.toRadians(n2 + 90.0f));
        return new double[] { moveForward * n * cos + moveStrafe * n * sin, moveForward * n * sin - moveStrafe * n * cos };
    }
    
    public static boolean isMoving(final EntityLivingBase entityLivingBase) {
        return entityLivingBase.moveForward != 0.0f || entityLivingBase.moveStrafing != 0.0f;
    }
    
    public static boolean isMoving() {
        return MotionUtil.mc.player.moveForward != 0.0f || MotionUtil.mc.player.moveStrafing != 0.0f;
    }
    
    public static boolean isMovingMomentum() {
        return MotionUtil.mc.player.moveForward != 0.0 || MotionUtil.mc.player.moveStrafing != 0.0;
    }
    
    public static void setSpeed(final EntityLivingBase entityLivingBase, final double n) {
        final double[] forward = forward(n);
        entityLivingBase.motionX = forward[0];
        entityLivingBase.motionZ = forward[1];
    }
}
