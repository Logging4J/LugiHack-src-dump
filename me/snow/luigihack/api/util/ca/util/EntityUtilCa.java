//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util.ca.util;

import me.snow.luigihack.api.util.*;
import java.util.*;
import net.minecraft.block.*;
import java.awt.*;
import net.minecraft.entity.player.*;
import me.snow.luigihack.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import net.minecraft.block.state.*;
import net.minecraft.item.*;
import net.minecraft.nbt.*;
import net.minecraft.entity.passive.*;
import net.minecraft.network.*;
import net.minecraft.network.play.client.*;
import net.minecraft.util.*;
import net.minecraft.entity.*;
import net.minecraft.entity.monster.*;

public class EntityUtilCa implements Util
{
    public static boolean isInHole(final Entity entity) {
        return isBlockValid(new BlockPos(entity.posX, entity.posY, entity.posZ));
    }
    
    public static List<Vec3d> getUnsafeBlocksFromVec3d(final Vec3d vec3d, final int n, final boolean b) {
        final ArrayList<Vec3d> list = new ArrayList<Vec3d>();
        for (final Vec3d e : getOffsets(n, b)) {
            final Block getBlock = EntityUtilCa.mc.world.getBlockState(new BlockPos(vec3d).add(e.x, e.y, e.z)).getBlock();
            if (getBlock instanceof BlockAir || getBlock instanceof BlockLiquid || getBlock instanceof BlockTallGrass || getBlock instanceof BlockFire || getBlock instanceof BlockDeadBush || getBlock instanceof BlockSnow) {
                list.add(e);
            }
        }
        return list;
    }
    
    public static List<Vec3d> getUnsafeBlocks(final Entity entity, final int n, final boolean b) {
        return getUnsafeBlocksFromVec3d(entity.getPositionVector(), n, b);
    }
    
    public static double predictPos(final double a, final double n) {
        return sigmoid(Math.abs(a) - n);
    }
    
    public static double sigmoid(final double n) {
        return 1.0 / (1.0 + Math.pow(2.718281828459045, -1.0 * n));
    }
    
    public static float getHealth(final Entity entity) {
        if (entity.isEntityAlive()) {
            final EntityLivingBase entityLivingBase = (EntityLivingBase)entity;
            return entityLivingBase.getHealth() + entityLivingBase.getAbsorptionAmount();
        }
        return 0.0f;
    }
    
    public static List<Vec3d> getOffsetList(final int n, final boolean b) {
        final ArrayList<Vec3d> list = new ArrayList<Vec3d>();
        list.add(new Vec3d(-1.0, (double)n, 0.0));
        list.add(new Vec3d(1.0, (double)n, 0.0));
        list.add(new Vec3d(0.0, (double)n, -1.0));
        list.add(new Vec3d(0.0, (double)n, 1.0));
        if (b) {
            list.add(new Vec3d(0.0, (double)(n - 1), 0.0));
        }
        return list;
    }
    
    public static Color getColor(final Entity entity, final int n, final int n2, final int n3, final int n4, final boolean b) {
        Color color = new Color(n / 255.0f, n2 / 255.0f, n3 / 255.0f, n4 / 255.0f);
        if (entity instanceof EntityPlayer) {
            if (b && LuigiHack.friendManager.isFriend(entity.getName())) {
                color = new Color(0.33f, 1.0f, 1.0f, n4 / 255.0f);
            }
            if (b && !LuigiHack.friendManager.isFriend(entity.getName())) {
                color = new Color(1.0f, 0.33f, 1.0f, n4 / 255.0f);
            }
        }
        return color;
    }
    
    public static Block isColliding(final double n, final double n2, final double n3) {
        Block getBlock = null;
        if (EntityUtilCa.mc.player != null) {
            final AxisAlignedBB axisAlignedBB = (EntityUtilCa.mc.player.getRidingEntity() != null) ? EntityUtilCa.mc.player.getRidingEntity().getEntityBoundingBox().contract(0.0, 0.0, 0.0).offset(n, n2, n3) : EntityUtilCa.mc.player.getEntityBoundingBox().contract(0.0, 0.0, 0.0).offset(n, n2, n3);
            final int n4 = (int)axisAlignedBB.minY;
            for (int i = MathHelper.floor(axisAlignedBB.minX); i < MathHelper.floor(axisAlignedBB.maxX) + 1; ++i) {
                for (int j = MathHelper.floor(axisAlignedBB.minZ); j < MathHelper.floor(axisAlignedBB.maxZ) + 1; ++j) {
                    getBlock = EntityUtilCa.mc.world.getBlockState(new BlockPos(i, n4, j)).getBlock();
                }
            }
        }
        return getBlock;
    }
    
    public static BlockPos getPlayerPosWithEntity() {
        return new BlockPos((EntityUtilCa.mc.player.getRidingEntity() != null) ? EntityUtilCa.mc.player.getRidingEntity().posX : EntityUtilCa.mc.player.posX, (EntityUtilCa.mc.player.getRidingEntity() != null) ? EntityUtilCa.mc.player.getRidingEntity().posY : EntityUtilCa.mc.player.posY, (EntityUtilCa.mc.player.getRidingEntity() != null) ? EntityUtilCa.mc.player.getRidingEntity().posZ : EntityUtilCa.mc.player.posZ);
    }
    
    public static boolean checkCollide() {
        return !EntityUtilCa.mc.player.isSneaking() && (EntityUtilCa.mc.player.getRidingEntity() == null || EntityUtilCa.mc.player.getRidingEntity().fallDistance < 3.0f) && EntityUtilCa.mc.player.fallDistance < 3.0f;
    }
    
    public static boolean isLiving(final Entity entity) {
        return entity instanceof EntityLivingBase;
    }
    
    public static boolean isSafe(final Entity entity, final int n, final boolean b) {
        return getUnsafeBlocks(entity, n, b).size() == 0;
    }
    
    public static Vec3d[] getOffsets(final int n, final boolean b) {
        final List<Vec3d> offsetList = getOffsetList(n, b);
        return offsetList.toArray(new Vec3d[offsetList.size()]);
    }
    
    public static boolean isBothHole(final BlockPos blockPos) {
        final BlockPos[] array = { blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down() };
        for (int length = array.length, i = 0; i < length; ++i) {
            final IBlockState getBlockState = EntityUtilCa.mc.world.getBlockState(array[i]);
            if (getBlockState.getBlock() == Blocks.AIR || (getBlockState.getBlock() != Blocks.BEDROCK && getBlockState.getBlock() != Blocks.OBSIDIAN)) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean is32k(final ItemStack itemStack) {
        if (itemStack == null) {
            return false;
        }
        if (itemStack.getTagCompound() == null) {
            return false;
        }
        final NBTTagList list = (NBTTagList)itemStack.getTagCompound().getTag("ench");
        if (list == null) {
            return false;
        }
        int i = 0;
        while (i < list.tagCount()) {
            final NBTTagCompound getCompoundTagAt = list.getCompoundTagAt(i);
            if (getCompoundTagAt.getInteger("id") == 16) {
                if (getCompoundTagAt.getInteger("lvl") >= 42) {
                    return true;
                }
                break;
            }
            else {
                ++i;
            }
        }
        return false;
    }
    
    public static boolean holding32k(final EntityPlayer entityPlayer) {
        return is32k(entityPlayer.getHeldItemMainhand());
    }
    
    public static boolean isPassiveMob(final Entity entity) {
        return (!(entity instanceof EntityWolf) || !((EntityWolf)entity).isAngry()) && (entity instanceof EntityAgeable || entity instanceof EntityAmbientCreature || entity instanceof EntitySquid || (entity instanceof EntityIronGolem && ((EntityIronGolem)entity).getRevengeTarget() == null));
    }
    
    public static boolean isAboveLiquid(final Entity entity) {
        if (entity == null) {
            return false;
        }
        final double n = entity.posY + 0.01;
        for (int i = MathHelper.floor(entity.posX); i < MathHelper.ceil(entity.posX); ++i) {
            for (int j = MathHelper.floor(entity.posZ); j < MathHelper.ceil(entity.posZ); ++j) {
                if (EntityUtilCa.mc.world.getBlockState(new BlockPos(i, (int)n, j)).getBlock() instanceof BlockLiquid) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean canEntityFeetBeSeen(final Entity entity) {
        return EntityUtilCa.mc.world.rayTraceBlocks(new Vec3d(EntityUtilCa.mc.player.posX, EntityUtilCa.mc.player.posX + EntityUtilCa.mc.player.getEyeHeight(), EntityUtilCa.mc.player.posZ), new Vec3d(entity.posX, entity.posY, entity.posZ), false, true, false) == null;
    }
    
    public static Vec3d getInterpolatedAmount(final Entity entity, final float n) {
        return getInterpolatedAmount(entity, n, n, n);
    }
    
    public static void setTimer(final float n) {
        EntityUtilCa.mc.timer.tickLength = 50.0f / n;
    }
    
    public static List<BlockPos> getSphere(final BlockPos blockPos, final float n, final int n2, final boolean b, final boolean b2, final int n3) {
        final ArrayList<BlockPos> list = new ArrayList<BlockPos>();
        final int getX = blockPos.getX();
        final int getY = blockPos.getY();
        final int getZ = blockPos.getZ();
        for (int n4 = getX - (int)n; n4 <= getX + n; ++n4) {
            for (int n5 = getZ - (int)n; n5 <= getZ + n; ++n5) {
                for (int n6 = b2 ? (getY - (int)n) : getY; n6 < (b2 ? (getY + n) : ((float)(getY + n2))); ++n6) {
                    final double n7 = (getX - n4) * (getX - n4) + (getZ - n5) * (getZ - n5) + (b2 ? ((getY - n6) * (getY - n6)) : 0);
                    if (n7 < n * n && (!b || n7 >= (n - 1.0f) * (n - 1.0f))) {
                        list.add(new BlockPos(n4, n6 + n3, n5));
                    }
                }
            }
        }
        return list;
    }
    
    public static boolean isBedrockHole(final BlockPos blockPos) {
        final BlockPos[] array = { blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down() };
        for (int length = array.length, i = 0; i < length; ++i) {
            final IBlockState getBlockState = EntityUtilCa.mc.world.getBlockState(array[i]);
            if (getBlockState.getBlock() == Blocks.AIR || getBlockState.getBlock() != Blocks.BEDROCK) {
                return false;
            }
        }
        return true;
    }
    
    public static Vec3d getInterpolatedAmount(final Entity entity, final double n, final double n2, final double n3) {
        return new Vec3d((entity.posX - entity.lastTickPosX) * n, (entity.posY - entity.lastTickPosY) * n2, (entity.posZ - entity.lastTickPosZ) * n3);
    }
    
    public static boolean isInLiquid() {
        if (EntityUtilCa.mc.player == null) {
            return false;
        }
        if (EntityUtilCa.mc.player.fallDistance >= 3.0f) {
            return false;
        }
        boolean b = false;
        final AxisAlignedBB axisAlignedBB = (EntityUtilCa.mc.player.getRidingEntity() != null) ? EntityUtilCa.mc.player.getRidingEntity().getEntityBoundingBox() : EntityUtilCa.mc.player.getEntityBoundingBox();
        final int n = (int)axisAlignedBB.minY;
        for (int i = MathHelper.floor(axisAlignedBB.minX); i < MathHelper.floor(axisAlignedBB.maxX) + 1; ++i) {
            for (int j = MathHelper.floor(axisAlignedBB.minZ); j < MathHelper.floor(axisAlignedBB.maxZ) + 1; ++j) {
                final Block getBlock = EntityUtilCa.mc.world.getBlockState(new BlockPos(i, n, j)).getBlock();
                if (!(getBlock instanceof BlockAir)) {
                    if (!(getBlock instanceof BlockLiquid)) {
                        return false;
                    }
                    b = true;
                }
            }
        }
        return b;
    }
    
    public static boolean checkForLiquid(final Entity entity, final boolean b) {
        if (entity == null) {
            return false;
        }
        final double posY = entity.posY;
        double n;
        if (b) {
            n = 0.03;
        }
        else if (entity instanceof EntityPlayer) {
            n = 0.2;
        }
        else {
            n = 0.5;
        }
        final double n2 = posY - n;
        for (int i = MathHelper.floor(entity.posX); i < MathHelper.ceil(entity.posX); ++i) {
            for (int j = MathHelper.floor(entity.posZ); j < MathHelper.ceil(entity.posZ); ++j) {
                if (EntityUtilCa.mc.world.getBlockState(new BlockPos(i, MathHelper.floor(n2), j)).getBlock() instanceof BlockLiquid) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static Vec3d getInterpolatedPos(final Entity entity, final float n) {
        return new Vec3d(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ).add(getInterpolatedAmount(entity, n));
    }
    
    public static void resetTimer() {
        EntityUtilCa.mc.timer.tickLength = 50.0f;
    }
    
    public static boolean stopSneaking(final boolean b) {
        if (b && EntityUtilCa.mc.player != null) {
            EntityUtilCa.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)EntityUtilCa.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
        }
        return false;
    }
    
    public static Vec3d[] getUnsafeBlockArrayFromVec3d(final Vec3d vec3d, final int n, final boolean b) {
        final List<Vec3d> unsafeBlocksFromVec3d = getUnsafeBlocksFromVec3d(vec3d, n, b);
        return unsafeBlocksFromVec3d.toArray(new Vec3d[unsafeBlocksFromVec3d.size()]);
    }
    
    public static boolean isNeutralMob(final Entity entity) {
        return entity instanceof EntityPigZombie || entity instanceof EntityWolf || entity instanceof EntityEnderman;
    }
    
    public static List<BlockPos> getSphere2(final BlockPos blockPos, final float n, final float n2, final boolean b, final boolean b2, final int n3) {
        final ArrayList<BlockPos> list = new ArrayList<BlockPos>();
        final int getX = blockPos.getX();
        final int getY = blockPos.getY();
        final int getZ = blockPos.getZ();
        for (int n4 = getX - (int)n; n4 <= getX + n; ++n4) {
            for (int n5 = getZ - (int)n; n5 <= getZ + n; ++n5) {
                for (int n6 = b2 ? (getY - (int)n) : getY; n6 < (b2 ? (getY + n) : (getY + n2)); ++n6) {
                    final double n7 = (getX - n4) * (getX - n4) + (getZ - n5) * (getZ - n5) + (b2 ? ((getY - n6) * (getY - n6)) : 0);
                    if (n7 < n * n && (!b || n7 >= (n - 1.0f) * (n - 1.0f))) {
                        list.add(new BlockPos(n4, n6 + n3, n5));
                    }
                }
            }
        }
        return list;
    }
    
    public static void attackEntity(final Entity entity, final boolean b, final boolean b2) {
        if (b) {
            EntityUtilCa.mc.player.connection.sendPacket((Packet)new CPacketUseEntity(entity));
        }
        else {
            EntityUtilCa.mc.playerController.attackEntity((EntityPlayer)EntityUtilCa.mc.player, entity);
        }
        if (b2) {
            EntityUtilCa.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
    }
    
    public static BlockPos getRoundedBlockPos(final Entity entity) {
        return new BlockPos(MathsUtilCa.roundVec(entity.getPositionVector(), 0));
    }
    
    public static boolean isOnLiquid(final double n) {
        if (EntityUtilCa.mc.player.fallDistance >= 3.0f) {
            return false;
        }
        final AxisAlignedBB axisAlignedBB = (EntityUtilCa.mc.player.getRidingEntity() != null) ? EntityUtilCa.mc.player.getRidingEntity().getEntityBoundingBox().contract(0.0, 0.0, 0.0).offset(0.0, -n, 0.0) : EntityUtilCa.mc.player.getEntityBoundingBox().contract(0.0, 0.0, 0.0).offset(0.0, -n, 0.0);
        boolean b = false;
        final int n2 = (int)axisAlignedBB.minY;
        for (int i = MathHelper.floor(axisAlignedBB.minX); i < MathHelper.floor(axisAlignedBB.maxX + 1.0); ++i) {
            for (int j = MathHelper.floor(axisAlignedBB.minZ); j < MathHelper.floor(axisAlignedBB.maxZ + 1.0); ++j) {
                final Block getBlock = EntityUtilCa.mc.world.getBlockState(new BlockPos(i, n2, j)).getBlock();
                if (getBlock != Blocks.AIR) {
                    if (!(getBlock instanceof BlockLiquid)) {
                        return false;
                    }
                    b = true;
                }
            }
        }
        return b;
    }
    
    public static boolean isObbyHole(final BlockPos blockPos) {
        final BlockPos[] array = { blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down() };
        for (int length = array.length, i = 0; i < length; ++i) {
            final IBlockState getBlockState = EntityUtilCa.mc.world.getBlockState(array[i]);
            if (getBlockState.getBlock() == Blocks.AIR || getBlockState.getBlock() != Blocks.OBSIDIAN) {
                return false;
            }
        }
        return true;
    }
    
    public static Vec3d getInterpolatedRenderPos(final Entity entity, final float n) {
        return getInterpolatedPos(entity, n).subtract(EntityUtilCa.mc.getRenderManager().renderPosX, EntityUtilCa.mc.getRenderManager().renderPosY, EntityUtilCa.mc.getRenderManager().renderPosZ);
    }
    
    public static BlockPos getFlooredPos(final Entity entity) {
        return new BlockPos(Math.floor(entity.posX), Math.floor(entity.posY), Math.floor(entity.posZ));
    }
    
    public static boolean isAboveBlock(final Entity entity, final BlockPos blockPos) {
        return entity.posY >= blockPos.getY();
    }
    
    public static Vec3d[] getUnsafeBlockArray(final Entity entity, final int n, final boolean b) {
        final List<Vec3d> unsafeBlocks = getUnsafeBlocks(entity, n, b);
        return unsafeBlocks.toArray(new Vec3d[unsafeBlocks.size()]);
    }
    
    public static boolean isBlockValid(final BlockPos blockPos) {
        return isBedrockHole(blockPos) || isObbyHole(blockPos) || isBothHole(blockPos);
    }
    
    public static boolean isHostileMob(final Entity entity) {
        return (entity.isCreatureType(EnumCreatureType.MONSTER, false) && !isNeutralMob(entity)) || entity instanceof EntitySpider;
    }
    
    public static Vec3d interpolateEntity(final Entity entity, final float n) {
        return new Vec3d(entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * n, entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * n, entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * n);
    }
    
    public static void attackEntity(final Entity entity, final boolean b) {
        if (b) {
            EntityUtilCa.mc.player.connection.sendPacket((Packet)new CPacketUseEntity(entity));
        }
        else {
            EntityUtilCa.mc.playerController.attackEntity((EntityPlayer)EntityUtilCa.mc.player, entity);
        }
    }
    
    public static boolean isntValid(final Entity entity, final double n) {
        return entity == null || !entity.isEntityAlive() || entity.equals((Object)EntityUtilCa.mc.player) || (entity instanceof EntityPlayer && LuigiHack.friendManager.isFriend(entity.getName())) || EntityUtilCa.mc.player.getDistanceSq(entity) > n * n;
    }
}
