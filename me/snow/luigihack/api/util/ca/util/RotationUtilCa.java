//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util.ca.util;

import me.snow.luigihack.api.util.*;
import net.minecraft.util.math.*;
import me.snow.luigihack.*;
import net.minecraft.entity.player.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;

public class RotationUtilCa implements Util
{
    private static /* synthetic */ float yaw;
    private static /* synthetic */ float pitch;
    public static /* synthetic */ boolean isSpoofingAngles;
    public static /* synthetic */ TimerCa rotationTimer;
    
    public static Vec3d getEyesPos() {
        return new Vec3d(RotationUtilCa.mc.player.posX, RotationUtilCa.mc.player.posY + RotationUtilCa.mc.player.getEyeHeight(), RotationUtilCa.mc.player.posZ);
    }
    
    public static float[] getLegitRotations(final Vec3d vec3d) {
        final Vec3d eyesPos = getEyesPos();
        final double x = vec3d.x - eyesPos.x;
        final double y = vec3d.y - eyesPos.y;
        final double y2 = vec3d.z - eyesPos.z;
        return new float[] { RotationUtilCa.mc.player.rotationYaw + MathHelper.wrapDegrees((float)Math.toDegrees(Math.atan2(y2, x)) - 90.0f - RotationUtilCa.mc.player.rotationYaw), RotationUtilCa.mc.player.rotationPitch + MathHelper.wrapDegrees((float)(-Math.toDegrees(Math.atan2(y, Math.sqrt(x * x + y2 * y2)))) - RotationUtilCa.mc.player.rotationPitch) };
    }
    
    public static void resetRotation() {
        if (RotationUtilCa.isSpoofingAngles) {
            RotationUtilCa.yaw = RotationUtilCa.mc.player.rotationYaw;
            RotationUtilCa.pitch = RotationUtilCa.mc.player.rotationPitch;
            RotationUtilCa.isSpoofingAngles = false;
        }
    }
    
    public static void resetRotations() {
        try {
            RotationUtilCa.yaw = RotationUtilCa.mc.player.rotationYaw;
            RotationUtilCa.pitch = RotationUtilCa.mc.player.rotationPitch;
            RotationUtilCa.mc.player.rotationYawHead = RotationUtilCa.mc.player.rotationYaw;
            RotationUtilCa.rotationTimer.reset();
        }
        catch (Exception ex) {
            LuigiHack.LOGGER.info("Failed to reset rotations...");
        }
    }
    
    public static void setYawAndPitch(final float yaw, final float pitch) {
        RotationUtilCa.yaw = yaw;
        RotationUtilCa.pitch = pitch;
        RotationUtilCa.isSpoofingAngles = true;
    }
    
    public static void setMinRotations(final float n, final float rotationPitch) {
        RotationUtilCa.mc.player.rotationYaw = n;
        RotationUtilCa.mc.player.rotationYawHead = n;
        RotationUtilCa.mc.player.rotationPitch = rotationPitch;
    }
    
    public static String getDirection4D(final boolean b) {
        final int direction4D = getDirection4D();
        if (direction4D == 0) {
            return "South (+Z)";
        }
        if (direction4D == 1) {
            return "West (-X)";
        }
        if (direction4D == 2) {
            return String.valueOf(new StringBuilder().append(b ? "§c" : "").append("North (-Z)"));
        }
        if (direction4D == 3) {
            return "East (+X)";
        }
        return "Loading...";
    }
    
    public static double[] calculateLookAt(final double n, final double n2, final double n3, final EntityPlayer entityPlayer) {
        final double n4 = entityPlayer.posX - n;
        final double n5 = entityPlayer.posY - n2;
        final double n6 = entityPlayer.posZ - n3;
        final double sqrt = Math.sqrt(n4 * n4 + n5 * n5 + n6 * n6);
        return new double[] { Math.atan2(n6 / sqrt, n4 / sqrt) * 180.0 / 3.141592653589793 + 90.0, Math.asin(n5 / sqrt) * 180.0 / 3.141592653589793 };
    }
    
    static {
        RotationUtilCa.rotationTimer = new TimerCa();
    }
    
    public static void rotateHead(final double n, final double n2, final double n3, final EntityPlayer entityPlayer) {
        final double[] calculateLook = calculateLookAt(n, n2, n3, entityPlayer);
        RotationUtilCa.mc.player.setRotationYawHead((float)calculateLook[0]);
        setYawAndPitch((float)calculateLook[0], (float)calculateLook[1]);
    }
    
    public static void faceVector(final Vec3d vec3d, final boolean b) {
        final float[] legitRotations = getLegitRotations(vec3d);
        RotationUtilCa.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(legitRotations[0], b ? ((float)MathHelper.normalizeAngle((int)legitRotations[1], 360)) : legitRotations[1], RotationUtilCa.mc.player.onGround));
        setYawAndPitch(legitRotations[0], legitRotations[1]);
    }
    
    public static void vecLookAt(final Vec3d vec3d) {
        final float[] legitRotations = getLegitRotations(vec3d);
        setYawAndPitch(legitRotations[0], legitRotations[1]);
    }
    
    public static int getDirection4D() {
        return MathHelper.floor(RotationUtilCa.mc.player.rotationYaw * 4.0f / 360.0f + 0.5) & 0x3;
    }
}
