//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util.ca.util;

import me.snow.luigihack.api.util.*;
import net.minecraft.network.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.block.state.*;
import net.minecraft.block.*;
import net.minecraft.entity.item.*;
import net.minecraft.init.*;
import java.util.*;
import net.minecraft.entity.*;
import net.minecraft.block.material.*;
import net.minecraft.item.*;
import me.snow.luigihack.*;

public class BlockUtilCa implements Util
{
    public static /* synthetic */ List<Block> emptyBlocks;
    public static /* synthetic */ List<Block> rightclickableBlocks;
    
    public static void rotatePacket(final double n, final double n2, final double n3) {
        final double x = n - BlockUtilCa.mc.player.posX;
        final double y = n2 - (BlockUtilCa.mc.player.posY + BlockUtilCa.mc.player.getEyeHeight());
        final double y2 = n3 - BlockUtilCa.mc.player.posZ;
        BlockUtilCa.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation((float)Math.toDegrees(Math.atan2(y2, x)) - 90.0f, (float)(-Math.toDegrees(Math.atan2(y, Math.sqrt(x * x + y2 * y2)))), BlockUtilCa.mc.player.onGround));
    }
    
    public static void faceVector(final Vec3d vec3d, final boolean b) {
        final float[] legitRotations = getLegitRotations(vec3d);
        BlockUtilCa.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(legitRotations[0], b ? ((float)MathHelper.normalizeAngle((int)legitRotations[1], 360)) : legitRotations[1], BlockUtilCa.mc.player.onGround));
    }
    
    private static Block getBlock(final BlockPos blockPos) {
        return getState(blockPos).getBlock();
    }
    
    public static List<EnumFacing> getPossibleSides(final BlockPos blockPos) {
        final ArrayList<EnumFacing> list = new ArrayList<EnumFacing>();
        for (final EnumFacing enumFacing : EnumFacing.values()) {
            final BlockPos offset = blockPos.offset(enumFacing);
            if (BlockUtilCa.mc.world.getBlockState(offset) == null) {
                return list;
            }
            if (BlockUtilCa.mc.world.getBlockState(offset).getBlock() == null) {
                return list;
            }
            if (BlockUtilCa.mc.world.getBlockState(offset).getBlock().canCollideCheck(BlockUtilCa.mc.world.getBlockState(offset), false) && !BlockUtilCa.mc.world.getBlockState(offset).getMaterial().isReplaceable()) {
                list.add(enumFacing);
            }
        }
        return list;
    }
    
    public static boolean placeBlock(final BlockPos blockPos, final EnumHand enumHand, final boolean b, final boolean b2, final boolean b3) {
        final EnumFacing firstFacing = getFirstFacing(blockPos);
        if (firstFacing == null) {
            return b3;
        }
        final BlockPos offset = blockPos.offset(firstFacing);
        final EnumFacing getOpposite = firstFacing.getOpposite();
        final Vec3d add = new Vec3d((Vec3i)offset).add(0.5, 0.5, 0.5).add(new Vec3d(getOpposite.getDirectionVec()).scale(0.5));
        if (!BlockUtilCa.mc.player.isSneaking()) {
            BlockUtilCa.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtilCa.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            BlockUtilCa.mc.player.setSneaking(true);
        }
        if (b) {
            faceVector(add, true);
        }
        rightClickBlock(offset, add, enumHand, getOpposite, b2);
        BlockUtilCa.mc.player.swingArm(EnumHand.MAIN_HAND);
        BlockUtilCa.mc.rightClickDelayTimer = 4;
        return true;
    }
    
    public static void placeCrystalOnBlock(final BlockPos blockPos, final EnumHand enumHand, final boolean b) {
        final RayTraceResult rayTraceBlocks = BlockUtilCa.mc.world.rayTraceBlocks(new Vec3d(BlockUtilCa.mc.player.posX, BlockUtilCa.mc.player.posY + BlockUtilCa.mc.player.getEyeHeight(), BlockUtilCa.mc.player.posZ), new Vec3d(blockPos.getX() + 0.5, blockPos.getY() - 0.5, blockPos.getZ() + 0.5));
        BlockUtilCa.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos, (rayTraceBlocks == null || rayTraceBlocks.sideHit == null) ? EnumFacing.UP : rayTraceBlocks.sideHit, enumHand, 0.0f, 0.0f, 0.0f));
        if (b) {
            BlockUtilCa.mc.player.connection.sendPacket((Packet)new CPacketAnimation(enumHand));
        }
    }
    
    public static boolean canPlaceBlock(final BlockPos blockPos) {
        if (isBlockEmpty(blockPos)) {
            for (final EnumFacing enumFacing : EnumFacing.values()) {
                if (!BlockUtilCa.emptyBlocks.contains(BlockUtilCa.mc.world.getBlockState(blockPos.offset(enumFacing)).getBlock()) && BlockUtilCa.mc.player.getPositionEyes(BlockUtilCa.mc.getRenderPartialTicks()).distanceTo(new Vec3d(blockPos.getX() + 0.5 + enumFacing.getXOffset() * 0.5, blockPos.getY() + 0.5 + enumFacing.getYOffset() * 0.5, blockPos.getZ() + 0.5 + enumFacing.getZOffset() * 0.5)) <= 4.25) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean placeBlock(final BlockPos blockPos, final int currentItem, final boolean b, final boolean b2) {
        if (isBlockEmpty(blockPos)) {
            int currentItem2 = -1;
            if (currentItem != BlockUtilCa.mc.player.inventory.currentItem) {
                currentItem2 = BlockUtilCa.mc.player.inventory.currentItem;
                BlockUtilCa.mc.player.inventory.currentItem = currentItem;
            }
            for (final EnumFacing enumFacing : EnumFacing.values()) {
                final Block getBlock = BlockUtilCa.mc.world.getBlockState(blockPos.offset(enumFacing)).getBlock();
                final Vec3d vec3d = new Vec3d(blockPos.getX() + 0.5 + enumFacing.getXOffset() * 0.5, blockPos.getY() + 0.5 + enumFacing.getYOffset() * 0.5, blockPos.getZ() + 0.5 + enumFacing.getZOffset() * 0.5);
                if (!BlockUtilCa.emptyBlocks.contains(getBlock) && BlockUtilCa.mc.player.getPositionEyes(BlockUtilCa.mc.getRenderPartialTicks()).distanceTo(vec3d) <= 4.25) {
                    final float[] array = { BlockUtilCa.mc.player.rotationYaw, BlockUtilCa.mc.player.rotationPitch };
                    if (b) {
                        rotatePacket(vec3d.x, vec3d.y, vec3d.z);
                    }
                    if (BlockUtilCa.rightclickableBlocks.contains(getBlock)) {
                        BlockUtilCa.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtilCa.mc.player, CPacketEntityAction.Action.START_SNEAKING));
                    }
                    BlockUtilCa.mc.playerController.processRightClickBlock(BlockUtilCa.mc.player, BlockUtilCa.mc.world, blockPos.offset(enumFacing), enumFacing.getOpposite(), new Vec3d((Vec3i)blockPos), EnumHand.MAIN_HAND);
                    if (BlockUtilCa.rightclickableBlocks.contains(getBlock)) {
                        BlockUtilCa.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtilCa.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
                    }
                    if (b2) {
                        BlockUtilCa.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(array[0], array[1], BlockUtilCa.mc.player.onGround));
                    }
                    if (currentItem2 != -1) {
                        BlockUtilCa.mc.player.inventory.currentItem = currentItem2;
                    }
                    return true;
                }
            }
            if (currentItem2 != -1) {
                BlockUtilCa.mc.player.inventory.currentItem = currentItem2;
            }
        }
        return false;
    }
    
    public static boolean intersectsWithEntity(final BlockPos blockPos) {
        for (final Entity entity : BlockUtilCa.mc.world.loadedEntityList) {
            if (entity.equals((Object)BlockUtilCa.mc.player)) {
                continue;
            }
            if (entity instanceof EntityItem) {
                continue;
            }
            if (new AxisAlignedBB(blockPos).intersects(entity.getEntityBoundingBox())) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean canBreak(final BlockPos blockPos) {
        final IBlockState getBlockState = BlockUtilCa.mc.world.getBlockState(blockPos);
        return getBlockState.getBlock().getBlockHardness(getBlockState, (World)BlockUtilCa.mc.world, blockPos) != -1.0f;
    }
    
    public static int isPositionPlaceable(final BlockPos blockPos, final boolean b, final boolean b2) {
        final Block getBlock = BlockUtilCa.mc.world.getBlockState(blockPos).getBlock();
        if (!(getBlock instanceof BlockAir) && !(getBlock instanceof BlockLiquid) && !(getBlock instanceof BlockTallGrass) && !(getBlock instanceof BlockFire) && !(getBlock instanceof BlockDeadBush) && !(getBlock instanceof BlockSnow)) {
            return 0;
        }
        if (!rayTracePlaceCheck(blockPos, b, 0.0f)) {
            return -1;
        }
        if (b2) {
            for (final Entity entity : BlockUtilCa.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(blockPos))) {
                if (!(entity instanceof EntityItem)) {
                    if (entity instanceof EntityXPOrb) {
                        continue;
                    }
                    return 1;
                }
            }
        }
        final Iterator<EnumFacing> iterator2 = getPossibleSides(blockPos).iterator();
        while (iterator2.hasNext()) {
            if (!canBeClicked(blockPos.offset((EnumFacing)iterator2.next()))) {
                continue;
            }
            return 3;
        }
        return 2;
    }
    
    public static float[] getLegitRotations(final Vec3d vec3d) {
        final Vec3d eyesPos = getEyesPos();
        final double x = vec3d.x - eyesPos.x;
        final double y = vec3d.y - eyesPos.y;
        final double y2 = vec3d.z - eyesPos.z;
        return new float[] { BlockUtilCa.mc.player.rotationYaw + MathHelper.wrapDegrees((float)Math.toDegrees(Math.atan2(y2, x)) - 90.0f - BlockUtilCa.mc.player.rotationYaw), BlockUtilCa.mc.player.rotationPitch + MathHelper.wrapDegrees((float)(-Math.toDegrees(Math.atan2(y, Math.sqrt(x * x + y2 * y2)))) - BlockUtilCa.mc.player.rotationPitch) };
    }
    
    public static boolean isScaffoldPos(final BlockPos blockPos) {
        return BlockUtilCa.mc.world.isAirBlock(blockPos) || BlockUtilCa.mc.world.getBlockState(blockPos).getBlock() == Blocks.SNOW_LAYER || BlockUtilCa.mc.world.getBlockState(blockPos).getBlock() == Blocks.TALLGRASS || BlockUtilCa.mc.world.getBlockState(blockPos).getBlock() instanceof BlockLiquid;
    }
    
    static {
        BlockUtilCa.emptyBlocks = Arrays.asList(Blocks.AIR, (Block)Blocks.FLOWING_LAVA, (Block)Blocks.LAVA, (Block)Blocks.FLOWING_WATER, (Block)Blocks.WATER, Blocks.VINE, Blocks.SNOW_LAYER, (Block)Blocks.TALLGRASS, (Block)Blocks.FIRE);
        BlockUtilCa.rightclickableBlocks = Arrays.asList((Block)Blocks.CHEST, Blocks.TRAPPED_CHEST, Blocks.ENDER_CHEST, Blocks.WHITE_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.SILVER_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.ANVIL, Blocks.WOODEN_BUTTON, Blocks.STONE_BUTTON, (Block)Blocks.UNPOWERED_COMPARATOR, (Block)Blocks.UNPOWERED_REPEATER, (Block)Blocks.POWERED_REPEATER, (Block)Blocks.POWERED_COMPARATOR, Blocks.OAK_FENCE_GATE, Blocks.SPRUCE_FENCE_GATE, Blocks.BIRCH_FENCE_GATE, Blocks.JUNGLE_FENCE_GATE, Blocks.DARK_OAK_FENCE_GATE, Blocks.ACACIA_FENCE_GATE, Blocks.BREWING_STAND, Blocks.DISPENSER, Blocks.DROPPER, Blocks.LEVER, Blocks.NOTEBLOCK, Blocks.JUKEBOX, (Block)Blocks.BEACON, Blocks.BED, Blocks.FURNACE, (Block)Blocks.OAK_DOOR, (Block)Blocks.SPRUCE_DOOR, (Block)Blocks.BIRCH_DOOR, (Block)Blocks.JUNGLE_DOOR, (Block)Blocks.ACACIA_DOOR, (Block)Blocks.DARK_OAK_DOOR, Blocks.CAKE, Blocks.ENCHANTING_TABLE, Blocks.DRAGON_EGG, (Block)Blocks.HOPPER, Blocks.REPEATING_COMMAND_BLOCK, Blocks.COMMAND_BLOCK, Blocks.CHAIN_COMMAND_BLOCK, Blocks.CRAFTING_TABLE);
    }
    
    public static EnumFacing getFirstFacing(final BlockPos blockPos) {
        final Iterator<EnumFacing> iterator = getPossibleSides(blockPos).iterator();
        if (iterator.hasNext()) {
            return iterator.next();
        }
        return null;
    }
    
    public static Vec3d getEyesPos() {
        return new Vec3d(BlockUtilCa.mc.player.posX, BlockUtilCa.mc.player.posY + BlockUtilCa.mc.player.getEyeHeight(), BlockUtilCa.mc.player.posZ);
    }
    
    private static IBlockState getState(final BlockPos blockPos) {
        return BlockUtilCa.mc.world.getBlockState(blockPos);
    }
    
    public static void rightClickBlock(final BlockPos blockPos, final Vec3d vec3d, final EnumHand enumHand, final EnumFacing enumFacing, final boolean b) {
        if (b) {
            BlockUtilCa.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos, enumFacing, enumHand, (float)(vec3d.x - blockPos.getX()), (float)(vec3d.y - blockPos.getY()), (float)(vec3d.z - blockPos.getZ())));
        }
        else {
            BlockUtilCa.mc.playerController.processRightClickBlock(BlockUtilCa.mc.player, BlockUtilCa.mc.world, blockPos, enumFacing, vec3d, enumHand);
        }
    }
    
    public static boolean isBlockEmpty(final BlockPos blockPos) {
        try {
            if (BlockUtilCa.emptyBlocks.contains(BlockUtilCa.mc.world.getBlockState(blockPos).getBlock())) {
                final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos);
                for (final Entity entity : BlockUtilCa.mc.world.loadedEntityList) {
                    if (entity instanceof EntityLivingBase && axisAlignedBB.intersects(entity.getEntityBoundingBox())) {
                        return false;
                    }
                }
                return true;
            }
        }
        catch (Exception ex) {}
        return false;
    }
    
    public static boolean canBeClicked(final BlockPos blockPos) {
        return getBlock(blockPos).canCollideCheck(getState(blockPos), false);
    }
    
    public static int isPositionPlaceable(final BlockPos blockPos, final boolean b) {
        return isPositionPlaceable(blockPos, b, true);
    }
    
    public static boolean rayTracePlaceCheck(final BlockPos blockPos, final boolean b, final float n) {
        return !b || BlockUtilCa.mc.world.rayTraceBlocks(new Vec3d(BlockUtilCa.mc.player.posX, BlockUtilCa.mc.player.posY + BlockUtilCa.mc.player.getEyeHeight(), BlockUtilCa.mc.player.posZ), new Vec3d((double)blockPos.getX(), (double)(blockPos.getY() + n), (double)blockPos.getZ()), false, true, false) == null;
    }
    
    public static Vec3d[] getHelpingBlocks(final Vec3d vec3d) {
        return new Vec3d[] { new Vec3d(vec3d.x, vec3d.y - 1.0, vec3d.z), new Vec3d((vec3d.x != 0.0) ? (vec3d.x * 2.0) : vec3d.x, vec3d.y, (vec3d.x != 0.0) ? vec3d.z : (vec3d.z * 2.0)), new Vec3d((vec3d.x == 0.0) ? (vec3d.x + 1.0) : vec3d.x, vec3d.y, (vec3d.x == 0.0) ? vec3d.z : (vec3d.z + 1.0)), new Vec3d((vec3d.x == 0.0) ? (vec3d.x - 1.0) : vec3d.x, vec3d.y, (vec3d.x == 0.0) ? vec3d.z : (vec3d.z - 1.0)), new Vec3d(vec3d.x, vec3d.y + 1.0, vec3d.z) };
    }
    
    private static boolean hasNeighbour(final BlockPos blockPos) {
        final EnumFacing[] values = EnumFacing.values();
        for (int length = values.length, i = 0; i < length; ++i) {
            if (!BlockUtilCa.mc.world.getBlockState(blockPos.offset(values[i])).getMaterial().isReplaceable()) {
                return true;
            }
        }
        return false;
    }
    
    public static ValidResult valid(final BlockPos blockPos) {
        if (!BlockUtilCa.mc.world.checkNoEntityCollision(new AxisAlignedBB(blockPos))) {
            return ValidResult.NoEntityCollision;
        }
        if (!checkForNeighbours(blockPos)) {
            return ValidResult.NoNeighbors;
        }
        if (BlockUtilCa.mc.world.getBlockState(blockPos).getBlock() == Blocks.AIR) {
            final BlockPos[] array = { blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.up(), blockPos.down() };
            for (int length = array.length, i = 0; i < length; ++i) {
                if (BlockUtilCa.mc.world.getBlockState(array[i]).getBlock() != Blocks.AIR) {
                    final EnumFacing[] values = EnumFacing.values();
                    for (int length2 = values.length, j = 0; j < length2; ++j) {
                        final BlockPos offset = blockPos.offset(values[j]);
                        if (BlockUtilCa.mc.world.getBlockState(offset).getBlock().canCollideCheck(BlockUtilCa.mc.world.getBlockState(offset), false)) {
                            return ValidResult.Ok;
                        }
                    }
                }
            }
            return ValidResult.NoNeighbors;
        }
        return ValidResult.AlreadyBlockThere;
    }
    
    public static boolean checkForNeighbours(final BlockPos blockPos) {
        if (!hasNeighbour(blockPos)) {
            final EnumFacing[] values = EnumFacing.values();
            for (int length = values.length, i = 0; i < length; ++i) {
                if (hasNeighbour(blockPos.offset(values[i]))) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }
    
    public static List<BlockPos> getCircle(final BlockPos blockPos, final int n, final float n2, final boolean b) {
        final ArrayList<BlockPos> list = new ArrayList<BlockPos>();
        final int getX = blockPos.getX();
        final int getZ = blockPos.getZ();
        for (int n3 = getX - (int)n2; n3 <= getX + n2; ++n3) {
            for (int n4 = getZ - (int)n2; n4 <= getZ + n2; ++n4) {
                final double n5 = (getX - n3) * (getX - n3) + (getZ - n4) * (getZ - n4);
                if (n5 < n2 * n2 && (!b || n5 >= (n2 - 1.0f) * (n2 - 1.0f))) {
                    list.add(new BlockPos(n3, n, n4));
                }
            }
        }
        return list;
    }
    
    public static boolean isValidBlock(final BlockPos blockPos) {
        final Block getBlock = BlockUtilCa.mc.world.getBlockState(blockPos).getBlock();
        return !(getBlock instanceof BlockLiquid) && getBlock.getMaterial((IBlockState)null) != Material.AIR;
    }
    
    public static double getMineTime(final Block block, final ItemStack itemStack) {
        float getDestroySpeed = itemStack.getDestroySpeed(block.getDefaultState());
        if (!BlockUtilCa.mc.player.onGround) {
            getDestroySpeed /= 5.0f;
        }
        float n;
        if (itemStack.canHarvestBlock(block.getDefaultState())) {
            n = getDestroySpeed / block.blockHardness / 30.0f;
        }
        else {
            n = getDestroySpeed / block.blockHardness / 100.0f;
        }
        return (float)Math.ceil(1.0f / n) / LuigiHack.serverManager.getTPS();
    }
    
    public static EnumFacing getPlaceableSide(final BlockPos blockPos) {
        for (final EnumFacing enumFacing : EnumFacing.values()) {
            final BlockPos offset = blockPos.offset(enumFacing);
            if (BlockUtilCa.mc.world.getBlockState(offset).getBlock().canCollideCheck(BlockUtilCa.mc.world.getBlockState(offset), false)) {
                if (!BlockUtilCa.mc.world.getBlockState(offset).getMaterial().isReplaceable()) {
                    return enumFacing;
                }
            }
        }
        return null;
    }
    
    public enum ValidResult
    {
        NoNeighbors, 
        AlreadyBlockThere, 
        Ok, 
        NoEntityCollision;
    }
}
