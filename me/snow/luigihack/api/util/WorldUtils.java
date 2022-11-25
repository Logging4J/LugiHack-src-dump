//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util;

import net.minecraft.util.*;
import net.minecraft.block.state.*;
import net.minecraft.block.*;
import net.minecraft.network.play.client.*;
import net.minecraft.entity.*;
import net.minecraft.network.*;
import net.minecraft.init.*;
import java.util.*;
import net.minecraft.util.math.*;

public class WorldUtils implements Util
{
    public static final /* synthetic */ List<Block> blackList;
    public static /* synthetic */ List<Block> emptyBlocks;
    
    public static void openBlock(final BlockPos blockPos) {
        for (final EnumFacing enumFacing : EnumFacing.values()) {
            if (WorldUtils.emptyBlocks.contains(WorldUtils.mc.world.getBlockState(blockPos.offset(enumFacing)).getBlock())) {
                WorldUtils.mc.playerController.processRightClickBlock(WorldUtils.mc.player, WorldUtils.mc.world, blockPos, enumFacing.getOpposite(), new Vec3d((Vec3i)blockPos), EnumHand.MAIN_HAND);
                return;
            }
        }
    }
    
    public static boolean isWithin(final double n, final Vec3d vec3d, final Vec3d vec3d2) {
        return vec3d.squareDistanceTo(vec3d2) <= n * n;
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
    
    public static EnumFacing getPlaceableSide(final BlockPos blockPos) {
        for (final EnumFacing enumFacing : EnumFacing.values()) {
            final BlockPos offset = blockPos.offset(enumFacing);
            if (WorldUtils.mc.world.getBlockState(offset).getBlock().canCollideCheck(WorldUtils.mc.world.getBlockState(offset), false)) {
                final IBlockState getBlockState = WorldUtils.mc.world.getBlockState(offset);
                if (!getBlockState.getBlock().getMaterial(getBlockState).isReplaceable()) {
                    return enumFacing;
                }
            }
        }
        return null;
    }
    
    public static Block getBlock(final BlockPos blockPos) {
        return WorldUtils.mc.world.getBlockState(blockPos).getBlock();
    }
    
    public static void placeBlock(final BlockPos blockPos, final int currentItem) {
        if (currentItem == -1) {
            return;
        }
        final int currentItem2 = WorldUtils.mc.player.inventory.currentItem;
        WorldUtils.mc.player.inventory.currentItem = currentItem;
        placeBlock(blockPos);
        WorldUtils.mc.player.inventory.currentItem = currentItem2;
    }
    
    private boolean place(final BlockPos blockPos) {
        final boolean isSneaking = WorldUtils.mc.player.isSneaking();
        final Block getBlock = WorldUtils.mc.world.getBlockState(blockPos).getBlock();
        if (!(getBlock instanceof BlockAir) && !(getBlock instanceof BlockLiquid) && !(getBlock instanceof BlockFire)) {
            return false;
        }
        final EnumFacing placeableSide = getPlaceableSide(blockPos);
        if (placeableSide == null) {
            return false;
        }
        final BlockPos offset = blockPos.offset(placeableSide);
        final EnumFacing getOpposite = placeableSide.getOpposite();
        if (!canBeClicked(offset)) {
            return false;
        }
        final Vec3d add = new Vec3d((Vec3i)offset).add(0.5, 0.5, 0.5).add(new Vec3d(getOpposite.getDirectionVec()).scale(0.5));
        final Block getBlock2 = WorldUtils.mc.world.getBlockState(offset).getBlock();
        if (!isSneaking && WorldUtils.blackList.contains(getBlock2)) {
            WorldUtils.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)WorldUtils.mc.player, CPacketEntityAction.Action.START_SNEAKING));
        }
        WorldUtils.mc.playerController.processRightClickBlock(WorldUtils.mc.player, WorldUtils.mc.world, offset, getOpposite, add, EnumHand.MAIN_HAND);
        WorldUtils.mc.player.swingArm(EnumHand.MAIN_HAND);
        return true;
    }
    
    static {
        empty = new ArrayList<Block>(Arrays.asList(Blocks.AIR, Blocks.VINE, Blocks.SNOW_LAYER, (Block)Blocks.TALLGRASS, (Block)Blocks.FIRE, (Block)Blocks.LAVA, (Block)Blocks.FLOWING_LAVA, (Block)Blocks.FLOWING_WATER, (Block)Blocks.WATER));
        blackList = Arrays.asList(Blocks.ENDER_CHEST, (Block)Blocks.CHEST, Blocks.TRAPPED_CHEST, Blocks.CRAFTING_TABLE, Blocks.ANVIL, Blocks.BREWING_STAND, (Block)Blocks.HOPPER, Blocks.DROPPER, Blocks.DISPENSER, Blocks.TRAPDOOR, Blocks.ENCHANTING_TABLE);
        WorldUtils.emptyBlocks = Arrays.asList(Blocks.AIR, (Block)Blocks.FLOWING_LAVA, (Block)Blocks.LAVA, (Block)Blocks.FLOWING_WATER, (Block)Blocks.WATER, Blocks.VINE, Blocks.SNOW_LAYER, (Block)Blocks.TALLGRASS, (Block)Blocks.FIRE);
    }
    
    public static EnumFacing getEnumFacing(final boolean b, final BlockPos blockPos) {
        final RayTraceResult rayTraceBlocks = WorldUtils.mc.world.rayTraceBlocks(new Vec3d(WorldUtils.mc.player.posX, WorldUtils.mc.player.posY + WorldUtils.mc.player.getEyeHeight(), WorldUtils.mc.player.posZ), new Vec3d(blockPos.getX() + 0.5, blockPos.getY() - 0.5, blockPos.getZ() + 0.5));
        if (blockPos.getY() == 255) {
            return EnumFacing.DOWN;
        }
        if (b) {
            return (rayTraceBlocks == null || rayTraceBlocks.sideHit == null) ? EnumFacing.UP : rayTraceBlocks.sideHit;
        }
        return EnumFacing.UP;
    }
    
    public static boolean placeBlock(final BlockPos blockPos) {
        if (TestUtil.isBlockEmpty(blockPos)) {
            for (final EnumFacing enumFacing : EnumFacing.values()) {
                final Block getBlock = Util.mc.world.getBlockState(blockPos.offset(enumFacing)).getBlock();
                final Vec3d vec3d = new Vec3d(blockPos.getX() + 0.5 + enumFacing.getXOffset() * 0.5, blockPos.getY() + 0.5 + enumFacing.getYOffset() * 0.5, blockPos.getZ() + 0.5 + enumFacing.getZOffset() * 0.5);
                if (!WorldUtils.emptyBlocks.contains(getBlock) && Util.mc.player.getPositionEyes(WorldUtils.mc.getRenderPartialTicks()).distanceTo(vec3d) <= 4.25) {
                    final float[] array = { Util.mc.player.rotationYaw, Util.mc.player.rotationPitch };
                    if (TestUtil.rightclickableBlocks.contains(getBlock)) {
                        Util.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Util.mc.player, CPacketEntityAction.Action.START_SNEAKING));
                    }
                    Util.mc.playerController.processRightClickBlock(Util.mc.player, Util.mc.world, blockPos.offset(enumFacing), enumFacing.getOpposite(), new Vec3d((Vec3i)blockPos), EnumHand.MAIN_HAND);
                    if (TestUtil.rightclickableBlocks.contains(getBlock)) {
                        Util.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Util.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
                    }
                    Util.mc.player.swingArm(EnumHand.MAIN_HAND);
                    return true;
                }
            }
        }
        return false;
    }
    
    public static double getRange(final Vec3d vec3d) {
        return WorldUtils.mc.player.getPositionVector().add(0.0, (double)WorldUtils.mc.player.eyeHeight, 0.0).distanceTo(vec3d);
    }
    
    public static boolean isOutside(final double n, final Vec3d vec3d, final Vec3d vec3d2) {
        return vec3d.squareDistanceTo(vec3d2) > n * n;
    }
    
    public static boolean canBeClicked(final BlockPos blockPos) {
        return WorldUtils.mc.world.getBlockState(blockPos).getBlock().canCollideCheck(WorldUtils.mc.world.getBlockState(blockPos), false);
    }
}
