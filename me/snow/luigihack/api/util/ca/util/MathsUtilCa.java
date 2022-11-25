//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util.ca.util;

import me.snow.luigihack.api.util.*;
import java.util.*;
import net.minecraft.entity.player.*;
import java.math.*;
import net.minecraft.util.math.*;

public class MathsUtilCa implements Util
{
    /* synthetic */ Random random;
    
    public static double[] directionSpeed(final double n) {
        float moveForward = MathsUtilCa.mc.player.movementInput.moveForward;
        float moveStrafe = MathsUtilCa.mc.player.movementInput.moveStrafe;
        float n2 = MathsUtilCa.mc.player.prevRotationYaw + (MathsUtilCa.mc.player.rotationYaw - MathsUtilCa.mc.player.prevRotationYaw) * MathsUtilCa.mc.getRenderPartialTicks();
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
    
    public static int floor(final double n) {
        final int n2 = (int)n;
        return (n < n2) ? (n2 - 1) : n2;
    }
    
    public static double clamp(final double a, final double n, final double b) {
        return (a < n) ? n : Math.min(a, b);
    }
    
    public static float wrapDegrees(float n) {
        n %= 360.0f;
        if (n >= 180.0f) {
            n -= 360.0f;
        }
        if (n < -180.0f) {
            n += 360.0f;
        }
        return n;
    }
    
    public static double incrementRound(final double n, final double n2) {
        return Math.floor(n) + Math.round((n - Math.floor(n)) * (1.0 / n2)) / (1.0 / n2);
    }
    
    public static Vec3d roundVec(final Vec3d vec3d, final int n) {
        return new Vec3d(round(vec3d.x, n), round(vec3d.y, n), round(vec3d.z, n));
    }
    
    public static double square(final float n) {
        return n * n;
    }
    
    public int random(final int n, final int n2) {
        return this.random.nextInt(n2 - n) + n;
    }
    
    public static Vec3d extrapolatePlayerPosition(final EntityPlayer entityPlayer, final int n) {
        final Vec3d calculateLine = calculateLine(new Vec3d(entityPlayer.lastTickPosX, entityPlayer.lastTickPosY, entityPlayer.lastTickPosZ), new Vec3d(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ), (square((float)entityPlayer.motionX) + square((float)entityPlayer.motionY) + square((float)entityPlayer.motionZ)) * n);
        return new Vec3d(calculateLine.x, entityPlayer.posY, calculateLine.z);
    }
    
    public static double round(final double val, final int newScale) {
        if (newScale < 0) {
            throw new IllegalArgumentException();
        }
        return BigDecimal.valueOf(val).setScale(newScale, RoundingMode.FLOOR).doubleValue();
    }
    
    public static int clamp(final int a, final int n, final int b) {
        return (a < n) ? n : Math.min(a, b);
    }
    
    public static float[] calcAngle(final Vec3d vec3d, final Vec3d vec3d2) {
        final double x = vec3d2.x - vec3d.x;
        final double y = (vec3d2.y - vec3d.y) * -1.0;
        final double y2 = vec3d2.z - vec3d.z;
        return new float[] { (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(y2, x)) - 90.0), (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(y, MathHelper.sqrt(x * x + y2 * y2)))) };
    }
    
    public static Vec3d calculateLine(final Vec3d vec3d, final Vec3d vec3d2, final double n) {
        final double sqrt = Math.sqrt(square((float)(vec3d2.x - vec3d.x)) + square((float)(vec3d2.y - vec3d.y)) + square((float)(vec3d2.z - vec3d.z)));
        return new Vec3d(vec3d.x + (vec3d2.x - vec3d.x) / sqrt * n, vec3d.y + (vec3d2.y - vec3d.y) / sqrt * n, vec3d.z + (vec3d2.z - vec3d.z) / sqrt * n);
    }
    
    public MathsUtilCa() {
        this.random = new Random();
    }
    
    public static double roundAvoid(final double n, final int n2) {
        final double pow = Math.pow(10.0, n2);
        return Math.round(n * pow) / pow;
    }
    
    public static double degToRad(final double n) {
        return n * 0.01745329238474369;
    }
    
    public static float clamp(final float a, final float n, final float b) {
        return (a < n) ? n : Math.min(a, b);
    }
}
