//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util;

import net.minecraft.entity.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.util.math.*;
import me.snow.luigihack.impl.modules.client.*;
import me.snow.luigihack.api.util.skid.oyvey.*;
import java.util.*;

public class RotationUtil implements Util
{
    private static /* synthetic */ float pitch;
    private static /* synthetic */ float yaw;
    
    public static boolean isInFov(final Entity entity) {
        return entity == null || (RotationUtil.mc.player.getDistanceSq(entity) >= 4.0 && yawDist(entity) >= getHalvedfov() + 2.0f);
    }
    
    public static void restoreRotations() {
        RotationUtil.mc.player.rotationYaw = RotationUtil.yaw;
        RotationUtil.mc.player.rotationYawHead = RotationUtil.yaw;
        RotationUtil.mc.player.rotationPitch = RotationUtil.pitch;
    }
    
    public static BlockPos getPlayerPos() {
        return new BlockPos(RotationUtil.mc.player.posX, (RotationUtil.mc.player.posY - Math.floor(RotationUtil.mc.player.posY) > 0.8) ? (Math.floor(RotationUtil.mc.player.posY) + 1.0) : Math.floor(RotationUtil.mc.player.posY), RotationUtil.mc.player.posZ);
    }
    
    public static float getHalvedfov() {
        return getFov() / 2.0f;
    }
    
    public static void faceEntity(final Entity entity) {
        final float[] calcAngle = MathUtil.calcAngle(RotationUtil.mc.player.getPositionEyes(RotationUtil.mc.getRenderPartialTicks()), entity.getPositionEyes(RotationUtil.mc.getRenderPartialTicks()));
        faceYawAndPitch(calcAngle[0], calcAngle[1]);
    }
    
    public static Vec2f getRotationFromVec(final Vec3d vec3d) {
        return new Vec2f((float)normalizeAngle(Math.toDegrees(Math.atan2(vec3d.z, vec3d.x)) - 90.0), (float)normalizeAngle(Math.toDegrees(-Math.atan2(vec3d.y, Math.hypot(vec3d.x, vec3d.z)))));
    }
    
    public static float[] getRotationsBlock(final BlockPos blockPos, final EnumFacing enumFacing, final boolean b) {
        final double x = blockPos.getX() + 0.5 - Wrapper.mc.player.posX + enumFacing.getXOffset() / 2.0;
        final double y = blockPos.getZ() + 0.5 - Wrapper.mc.player.posZ + enumFacing.getZOffset() / 2.0;
        double n = blockPos.getY() + 0.5;
        if (b) {
            n += 0.5;
        }
        final double y2 = Wrapper.mc.player.posY + Wrapper.mc.player.getEyeHeight() - n;
        final double x2 = MathHelper.sqrt(x * x + y * y);
        float n2 = (float)(Math.atan2(y, x) * 180.0 / 3.141592653589793) - 90.0f;
        final float n3 = (float)(Math.atan2(y2, x2) * 180.0 / 3.141592653589793);
        if (n2 < 0.0f) {
            n2 += 360.0f;
        }
        return new float[] { n2, n3 };
    }
    
    public static float[] getLegitRotations(final Vec3d vec3d) {
        final Vec3d eyesPos = getEyesPos();
        final double x = vec3d.x - eyesPos.x;
        final double y = vec3d.y - eyesPos.y;
        final double y2 = vec3d.z - eyesPos.z;
        return new float[] { RotationUtil.mc.player.rotationYaw + MathHelper.wrapDegrees((float)Math.toDegrees(Math.atan2(y2, x)) - 90.0f - RotationUtil.mc.player.rotationYaw), RotationUtil.mc.player.rotationPitch + MathHelper.wrapDegrees((float)(-Math.toDegrees(Math.atan2(y, Math.sqrt(x * x + y2 * y2)))) - RotationUtil.mc.player.rotationPitch) };
    }
    
    public static void faceVector(final Vec3d vec3d, final boolean b) {
        final float[] legitRotations = getLegitRotations(vec3d);
        RotationUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(legitRotations[0], b ? ((float)MathHelper.normalizeAngle((int)legitRotations[1], 360)) : legitRotations[1], RotationUtil.mc.player.onGround));
    }
    
    public static double yawDist(final BlockPos blockPos) {
        if (blockPos != null) {
            final Vec3d subtract = new Vec3d((Vec3i)blockPos).subtract(RotationUtil.mc.player.getPositionEyes(RotationUtil.mc.getRenderPartialTicks()));
            final double n = Math.abs(RotationUtil.mc.player.rotationYaw - (Math.toDegrees(Math.atan2(subtract.z, subtract.x)) - 90.0)) % 360.0;
            return (n > 180.0) ? (360.0 - n) : n;
        }
        return 0.0;
    }
    
    public static void setPlayerRotations(final float n, final float rotationPitch) {
        RotationUtil.mc.player.rotationYaw = n;
        RotationUtil.mc.player.rotationYawHead = n;
        RotationUtil.mc.player.rotationPitch = rotationPitch;
    }
    
    public static float getFov() {
        return (float)(ClickGui.getInstance().customFov.getValue() ? ClickGui.getInstance().fov.getValue() : RotationUtil.mc.gameSettings.fovSetting);
    }
    
    public static float[] simpleFacing(final EnumFacing enumFacing) {
        switch (enumFacing) {
            case DOWN: {
                return new float[] { RotationUtil.mc.player.rotationYaw, 90.0f };
            }
            case UP: {
                return new float[] { RotationUtil.mc.player.rotationYaw, -90.0f };
            }
            case NORTH: {
                return new float[] { 180.0f, 0.0f };
            }
            case SOUTH: {
                return new float[] { 0.0f, 0.0f };
            }
            case WEST: {
                return new float[] { 90.0f, 0.0f };
            }
            default: {
                return new float[] { 270.0f, 0.0f };
            }
        }
    }
    
    public static void updateRotations() {
        RotationUtil.yaw = RotationUtil.mc.player.rotationYaw;
        RotationUtil.pitch = RotationUtil.mc.player.rotationPitch;
    }
    
    public static void faceYawAndPitch(final float n, final float n2) {
        RotationUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(n, n2, RotationUtil.mc.player.onGround));
    }
    
    public static float[] getAngle(final Entity entity) {
        return MathUtil.calcAngle(RotationUtil.mc.player.getPositionEyes(RotationUtil.mc.getRenderPartialTicks()), entity.getPositionEyes(RotationUtil.mc.getRenderPartialTicks()));
    }
    
    public static HoleUtilSafety.Hole getTargetHoleVec3D(final double n) {
        return HoleUtilSafety.getHoles(n, getPlayerPos(), false).stream().filter(hole2 -> RotationUtil.mc.player.getPositionVector().distanceTo(new Vec3d(hole2.pos1.getX() + 0.5, RotationUtil.mc.player.posY, hole2.pos1.getZ() + 0.5)) <= n).min(Comparator.comparingDouble(hole -> RotationUtil.mc.player.getPositionVector().distanceTo(new Vec3d(hole.pos1.getX() + 0.5, RotationUtil.mc.player.posY, hole.pos1.getZ() + 0.5)))).orElse(null);
    }
    
    public static double normalizeAngle(final Double n) {
        final double n2 = 0.0;
        double n3 = n % 360.0;
        if (n2 >= 180.0) {
            n3 -= 360.0;
        }
        if (n3 < -180.0) {
            n3 += 360.0;
        }
        return n3;
    }
    
    public static double yawDist(final Entity entity) {
        if (entity != null) {
            final Vec3d subtract = entity.getPositionVector().add(0.0, (double)(entity.getEyeHeight() / 2.0f), 0.0).subtract(RotationUtil.mc.player.getPositionEyes(RotationUtil.mc.getRenderPartialTicks()));
            final double n = Math.abs(RotationUtil.mc.player.rotationYaw - (Math.toDegrees(Math.atan2(subtract.z, subtract.x)) - 90.0)) % 360.0;
            return (n > 180.0) ? (360.0 - n) : n;
        }
        return 0.0;
    }
    
    public static void faceVectorPacketInstant(final Vec3d vec3d) {
        final float[] legitRotations = getLegitRotations(vec3d);
        RotationUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(legitRotations[0], legitRotations[1], RotationUtil.mc.player.onGround));
    }
    
    public static Vec2f getRotationTo(final Vec3d vec3d, final Vec3d vec3d2) {
        return getRotationFromVec(vec3d.subtract(vec3d2));
    }
    
    public static float[] getRotations(final Vec3d vec3d, final Vec3d vec3d2) {
        final double x = vec3d2.x - vec3d.x;
        final double y = (vec3d2.y - vec3d.y) * -1.0;
        final double y2 = vec3d2.z - vec3d.z;
        return new float[] { (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(y2, x)) - 90.0), (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(y, MathHelper.sqrt(x * x + y2 * y2)))) };
    }
    
    public static int getDirection4D() {
        return MathHelper.floor(RotationUtil.mc.player.rotationYaw * 4.0f / 360.0f + 0.5) & 0x3;
    }
    
    public static float transformYaw() {
        float n = RotationUtil.mc.player.rotationYaw % 360.0f;
        if (RotationUtil.mc.player.rotationYaw > 0.0f) {
            if (n > 180.0f) {
                n = -180.0f + (n - 180.0f);
            }
        }
        else if (n < -180.0f) {
            n = 180.0f + (n + 180.0f);
        }
        if (n < 0.0f) {
            return 180.0f + n;
        }
        return -180.0f + n;
    }
    
    public static boolean isInFov(final Vec3d vec3d, final Vec3d vec3d2) {
        Label_0071: {
            if (RotationUtil.mc.player.rotationPitch > 30.0f) {
                if (vec3d2.y <= RotationUtil.mc.player.posY) {
                    break Label_0071;
                }
            }
            else if (RotationUtil.mc.player.rotationPitch >= -30.0f || vec3d2.y >= RotationUtil.mc.player.posY) {
                break Label_0071;
            }
            return true;
        }
        final float n = MathUtil.calcAngleNoY(vec3d, vec3d2)[0] - transformYaw();
        if (n < -270.0f) {
            return true;
        }
        final float n2 = (float)((ClickGui.getInstance().customFov.getValue() ? ClickGui.getInstance().fov.getValue() : RotationUtil.mc.gameSettings.fovSetting) / 2.0f);
        return n < n2 + 10.0f && n > -n2 - 10.0f;
    }
    
    public static boolean isInFov(final BlockPos blockPos) {
        return blockPos == null || (RotationUtil.mc.player.getDistanceSq(blockPos) >= 4.0 && yawDist(blockPos) >= getHalvedfov() + 2.0f);
    }
    
    public static Vec3d getEyesPos() {
        return new Vec3d(RotationUtil.mc.player.posX, RotationUtil.mc.player.posY + RotationUtil.mc.player.getEyeHeight(), RotationUtil.mc.player.posZ);
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
}
