//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util;

import net.minecraft.client.*;
import java.math.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import com.mojang.realmsclient.gui.*;
import java.util.*;
import net.minecraft.entity.player.*;

public class MathUtil implements Util
{
    private static final /* synthetic */ Random random;
    
    public static double length(final Vec3d vec3d) {
        return Math.sqrt(lengthSQ(vec3d));
    }
    
    public static double multiply(final double n) {
        return n * n;
    }
    
    public static float cos(final float n) {
        return MathHelper.cos(n);
    }
    
    public static boolean areVec3dsAlignedRetarded(final Vec3d vec3d, final Vec3d vec3d2) {
        return new BlockPos(vec3d).equals((Object)new BlockPos(vec3d2.x, vec3d.y, vec3d2.z));
    }
    
    public static double square(final double n) {
        return n * n;
    }
    
    public static double getRandom(final double n, final double n2) {
        return MathHelper.clamp(n + MathUtil.random.nextDouble() * n2, n, n2);
    }
    
    public static double[] differentDirectionSpeed(final double n) {
        final Minecraft getMinecraft = Minecraft.getMinecraft();
        float moveForward = getMinecraft.player.movementInput.moveForward;
        float moveStrafe = getMinecraft.player.movementInput.moveStrafe;
        float n2 = getMinecraft.player.prevRotationYaw + (getMinecraft.player.rotationYaw - getMinecraft.player.prevRotationYaw) * getMinecraft.getRenderPartialTicks();
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
    
    public static Vec3d direction(final float n) {
        return new Vec3d(Math.cos(degToRad(n + 90.0f)), 0.0, Math.sin(degToRad(n + 90.0f)));
    }
    
    public static float round(final float n, final int newScale) {
        if (newScale < 0) {
            throw new IllegalArgumentException();
        }
        return BigDecimal.valueOf(n).setScale(newScale, RoundingMode.FLOOR).floatValue();
    }
    
    public static double clamp(final double a, final double n, final double b) {
        return (a < n) ? n : Math.min(a, b);
    }
    
    public static double angleBetweenVecs(final Vec3d vec3d, final Vec3d vec3d2) {
        return -(Math.atan2(vec3d.x - vec3d2.x, vec3d.z - vec3d2.z) / 3.141592653589793) * 360.0 / 2.0 + 180.0;
    }
    
    public static List<Vec3d> getBlockBlocks(final Entity entity) {
        final ArrayList<Vec3d> list = new ArrayList<Vec3d>();
        final AxisAlignedBB getEntityBoundingBox = entity.getEntityBoundingBox();
        final double posY = entity.posY;
        final double round = round(getEntityBoundingBox.minX, 0);
        final double round2 = round(getEntityBoundingBox.minZ, 0);
        final double round3 = round(getEntityBoundingBox.maxX, 0);
        final double round4 = round(getEntityBoundingBox.maxZ, 0);
        if (round != round3) {
            final Vec3d e = new Vec3d(round, posY, round2);
            final Vec3d e2 = new Vec3d(round3, posY, round2);
            final BlockPos blockPos = new BlockPos(e);
            final BlockPos blockPos2 = new BlockPos(e2);
            if (BlockUtil.isBlockUnSolid(blockPos) && BlockUtil.isBlockUnSolid(blockPos2)) {
                list.add(e);
                list.add(e2);
            }
            if (round2 != round4) {
                final Vec3d e3 = new Vec3d(round, posY, round4);
                final Vec3d e4 = new Vec3d(round3, posY, round4);
                final BlockPos blockPos3 = new BlockPos(e);
                final BlockPos blockPos4 = new BlockPos(e2);
                if (BlockUtil.isBlockUnSolid(blockPos3) && BlockUtil.isBlockUnSolid(blockPos4)) {
                    list.add(e3);
                    list.add(e4);
                    return list;
                }
            }
            if (list.isEmpty()) {
                list.add(entity.getPositionVector());
            }
            return list;
        }
        if (round2 != round4) {
            final Vec3d e5 = new Vec3d(round, posY, round2);
            final Vec3d e6 = new Vec3d(round, posY, round4);
            final BlockPos blockPos5 = new BlockPos(e5);
            final BlockPos blockPos6 = new BlockPos(e6);
            if (BlockUtil.isBlockUnSolid(blockPos5) && BlockUtil.isBlockUnSolid(blockPos6)) {
                list.add(e5);
                list.add(e6);
            }
            if (list.isEmpty()) {
                list.add(entity.getPositionVector());
            }
            return list;
        }
        list.add(entity.getPositionVector());
        return list;
    }
    
    public static float sin(final float n) {
        return MathHelper.sin(n);
    }
    
    public static int clamp(final int a, final int n, final int b) {
        return (a < n) ? n : Math.min(a, b);
    }
    
    public static double square(final float n) {
        return n * n;
    }
    
    public static int getRandom(final int n, final int n2) {
        return n + MathUtil.random.nextInt(n2 - n + 1);
    }
    
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(final Map<K, V> map, final boolean b) {
        final LinkedList<Object> list = new LinkedList<Object>(map.entrySet());
        if (b) {
            list.sort((Comparator<? super Object>)Map.Entry.comparingByValue(Comparator.reverseOrder()));
        }
        else {
            list.sort((Comparator<? super Object>)Map.Entry.comparingByValue());
        }
        final LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
        for (final Map.Entry<Object, V> entry : list) {
            linkedHashMap.put(entry.getKey(), entry.getValue());
        }
        return (Map<K, V>)linkedHashMap;
    }
    
    public static String getDirectionFromPlayer(final double n, final double n2) {
        double n3 = Math.toDegrees(Math.atan2(-(MathUtil.mc.player.posX - n), -(MathUtil.mc.player.posZ - n2))) + MathUtil.mc.player.rotationYaw;
        if (n3 < 0.0) {
            n3 += 360.0;
        }
        if (n3 > 315.0 || n3 <= 45.0) {
            return "in front of you";
        }
        if (n3 > 45.0 && n3 <= 135.0) {
            return "to your left";
        }
        if (n3 > 135.0 && n3 <= 225.0) {
            return "behind you";
        }
        if (n3 > 225.0 && n3 <= 315.0) {
            return "to your right";
        }
        return String.valueOf(new StringBuilder().append(ChatFormatting.OBFUSCATED).append("living in your walls"));
    }
    
    public static float roundFloat(final double val, final int newScale) {
        return BigDecimal.valueOf(val).setScale(newScale, RoundingMode.FLOOR).floatValue();
    }
    
    public static float wrap(final float n) {
        float n2 = n % 360.0f;
        if (n2 >= 180.0f) {
            n2 -= 360.0f;
        }
        if (n2 < -180.0f) {
            n2 += 360.0f;
        }
        return n2;
    }
    
    public static Vec3d roundVec(final Vec3d vec3d, final int n) {
        return new Vec3d(round(vec3d.x, n), round(vec3d.y, n), round(vec3d.z, n));
    }
    
    public static double getIncremental(final double n, final double n2) {
        final double n3 = 1.0 / n2;
        return Math.round(n * n3) / n3;
    }
    
    public static Vec3d calculateLine(final Vec3d vec3d, final Vec3d vec3d2, final double n) {
        final double sqrt = Math.sqrt(multiply(vec3d2.x - vec3d.x) + multiply(vec3d2.y - vec3d.y) + multiply(vec3d2.z - vec3d.z));
        return new Vec3d(vec3d.x + (vec3d2.x - vec3d.x) / sqrt * n, vec3d.y + (vec3d2.y - vec3d.y) / sqrt * n, vec3d.z + (vec3d2.z - vec3d.z) / sqrt * n);
    }
    
    public static double round(final double val, final int newScale) {
        if (newScale < 0) {
            throw new IllegalArgumentException();
        }
        return BigDecimal.valueOf(val).setScale(newScale, RoundingMode.FLOOR).doubleValue();
    }
    
    public static boolean areVec3dsAligned(final Vec3d vec3d, final Vec3d vec3d2) {
        return areVec3dsAlignedRetarded(vec3d, vec3d2);
    }
    
    public static String getTimeOfDay() {
        final int value = Calendar.getInstance().get(11);
        if (value < 12) {
            return "Good Morning ";
        }
        if (value < 16) {
            return "Good Afternoon ";
        }
        if (value < 21) {
            return "Good Evening ";
        }
        return "Good Night ";
    }
    
    public static double[] directionSpeed(final double n) {
        float moveForward = MathUtil.mc.player.movementInput.moveForward;
        float moveStrafe = MathUtil.mc.player.movementInput.moveStrafe;
        float n2 = MathUtil.mc.player.prevRotationYaw + (MathUtil.mc.player.rotationYaw - MathUtil.mc.player.prevRotationYaw) * MathUtil.mc.getRenderPartialTicks();
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
    
    public static Vec3d extrapolatePlayerPosition(final EntityPlayer entityPlayer, final int n) {
        final Vec3d calculateLine = calculateLine(new Vec3d(entityPlayer.lastTickPosX, entityPlayer.lastTickPosY, entityPlayer.lastTickPosZ), new Vec3d(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ), (multiply(entityPlayer.motionX) + multiply(entityPlayer.motionY) + multiply(entityPlayer.motionZ)) * n);
        return new Vec3d(calculateLine.x, entityPlayer.posY, calculateLine.z);
    }
    
    public static Vec3d interpolateEntity(final Entity entity, final float n) {
        return new Vec3d(entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * n, entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * n, entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * n);
    }
    
    public static double wrapDegrees(final double n) {
        return MathHelper.wrapDegrees(n);
    }
    
    public static float clamp(final float a, final float n, final float b) {
        return (a < n) ? n : Math.min(a, b);
    }
    
    public static double radToDeg(final double n) {
        return n * 57.295780181884766;
    }
    
    public static Vec3d getInterpolatedRenderPos(final Entity entity, final float n) {
        return interpolateEntity(entity, n).subtract(Minecraft.getMinecraft().getRenderManager().renderPosX, Minecraft.getMinecraft().getRenderManager().renderPosY, Minecraft.getMinecraft().getRenderManager().renderPosZ);
    }
    
    public static double lengthSQ(final Vec3d vec3d) {
        return square(vec3d.x) + square(vec3d.y) + square(vec3d.z);
    }
    
    public static double dot(final Vec3d vec3d, final Vec3d vec3d2) {
        return vec3d.x * vec3d2.x + vec3d.y * vec3d2.y + vec3d.z * vec3d2.z;
    }
    
    public static float[] calcAngleNoY(final Vec3d vec3d, final Vec3d vec3d2) {
        return new float[] { (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(vec3d2.z - vec3d.z, vec3d2.x - vec3d.x)) - 90.0) };
    }
    
    public static float[] calcAngle(final Vec3d vec3d, final Vec3d vec3d2) {
        final double x = vec3d2.x - vec3d.x;
        final double y = (vec3d2.y - vec3d.y) * -1.0;
        final double y2 = vec3d2.z - vec3d.z;
        return new float[] { (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(y2, x)) - 90.0), (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(y, MathHelper.sqrt(x * x + y2 * y2)))) };
    }
    
    static {
        random = new Random();
    }
    
    public static float wrapDegrees(final float n) {
        return MathHelper.wrapDegrees(n);
    }
    
    public static double degToRad(final double n) {
        return n * 0.01745329238474369;
    }
    
    public static float getRandom(final float n, final float n2) {
        return MathHelper.clamp(n + MathUtil.random.nextFloat() * n2, n, n2);
    }
}
