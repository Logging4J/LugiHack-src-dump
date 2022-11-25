//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util;

import net.minecraft.network.*;
import net.minecraft.entity.player.*;
import java.util.function.*;
import java.util.stream.*;
import net.minecraft.block.state.*;
import com.google.common.util.concurrent.*;
import java.util.concurrent.atomic.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.block.material.*;
import me.snow.luigihack.impl.command.*;
import me.snow.luigihack.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;
import net.minecraft.enchantment.*;
import net.minecraft.init.*;
import java.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.block.*;
import net.minecraft.entity.projectile.*;
import net.minecraft.entity.item.*;

public class BlockUtil implements Util
{
    public static final /* synthetic */ List<Block> blackList;
    public static /* synthetic */ List<Block> emptyBlocks;
    public static /* synthetic */ List<Block> unSolidBlocks;
    public static /* synthetic */ List<Block> rightclickableBlocks;
    private static /* synthetic */ boolean _started;
    public static final /* synthetic */ List<Block> shulkerList;
    private static /* synthetic */ BlockPos _currBlock;
    
    public static void rightClickBlock(final BlockPos blockPos, final Vec3d vec3d, final EnumHand enumHand, final EnumFacing enumFacing, final boolean b) {
        if (b) {
            BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos, enumFacing, enumHand, (float)(vec3d.x - blockPos.getX()), (float)(vec3d.y - blockPos.getY()), (float)(vec3d.z - blockPos.getZ())));
        }
        else {
            BlockUtil.mc.playerController.processRightClickBlock(BlockUtil.mc.player, BlockUtil.mc.world, blockPos, enumFacing, vec3d, enumHand);
        }
        BlockUtil.mc.player.swingArm(EnumHand.MAIN_HAND);
        BlockUtil.mc.rightClickDelayTimer = 4;
    }
    
    public static boolean GetState() {
        return BlockUtil._currBlock != null && IsDoneBreaking(BlockUtil.mc.world.getBlockState(BlockUtil._currBlock));
    }
    
    public static boolean placeBlock(final BlockPos blockPos) {
        if (isBlockEmpty(blockPos)) {
            for (final EnumFacing enumFacing : EnumFacing.values()) {
                final Block getBlock = BlockUtil.mc.world.getBlockState(blockPos.offset(enumFacing)).getBlock();
                final Vec3d vec3d = new Vec3d(blockPos.getX() + 0.5 + enumFacing.getXOffset() * 0.5, blockPos.getY() + 0.5 + enumFacing.getYOffset() * 0.5, blockPos.getZ() + 0.5 + enumFacing.getZOffset() * 0.5);
                if (!BlockUtil.emptyBlocks.contains(getBlock) && BlockUtil.mc.player.getPositionEyes(BlockUtil.mc.getRenderPartialTicks()).distanceTo(vec3d) <= 4.25) {
                    final float[] array = { BlockUtil.mc.player.rotationYaw, BlockUtil.mc.player.rotationPitch };
                    if (BlockUtil.rightclickableBlocks.contains(getBlock)) {
                        BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil.mc.player, CPacketEntityAction.Action.START_SNEAKING));
                    }
                    BlockUtil.mc.playerController.processRightClickBlock(BlockUtil.mc.player, BlockUtil.mc.world, blockPos.offset(enumFacing), enumFacing.getOpposite(), new Vec3d((Vec3i)blockPos), EnumHand.MAIN_HAND);
                    if (BlockUtil.rightclickableBlocks.contains(getBlock)) {
                        BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
                    }
                    BlockUtil.mc.player.swingArm(EnumHand.MAIN_HAND);
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean canPlaceCrystal(final BlockPos blockPos, final boolean b, final boolean b2) {
        final BlockPos add = blockPos.add(0, 1, 0);
        final BlockPos add2 = blockPos.add(0, 2, 0);
        try {
            if (BlockUtil.mc.world.getBlockState(blockPos).getBlock() != Blocks.BEDROCK && BlockUtil.mc.world.getBlockState(blockPos).getBlock() != Blocks.OBSIDIAN) {
                return false;
            }
            if ((!b2 && BlockUtil.mc.world.getBlockState(add2).getBlock() != Blocks.AIR) || BlockUtil.mc.world.getBlockState(add).getBlock() != Blocks.AIR) {
                return false;
            }
            for (final Entity entity : BlockUtil.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(add))) {
                if (!entity.isDead) {
                    if (b && entity instanceof EntityEnderCrystal) {
                        continue;
                    }
                    return false;
                }
            }
            if (!b2) {
                for (final Entity entity2 : BlockUtil.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(add2))) {
                    if (!entity2.isDead) {
                        if (b && entity2 instanceof EntityEnderCrystal) {
                            continue;
                        }
                        return false;
                    }
                }
            }
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }
    
    public static void placeBlock(final BlockPos blockPos, final EnumFacing enumFacing, final boolean b) {
        final BlockPos offset = blockPos.offset(enumFacing);
        final EnumFacing getOpposite = enumFacing.getOpposite();
        if (!Util.mc.player.isSneaking()) {
            Util.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Util.mc.player, CPacketEntityAction.Action.START_SNEAKING));
        }
        final Vec3d add = new Vec3d((Vec3i)offset).add(0.5, 0.5, 0.5).add(new Vec3d(getOpposite.getDirectionVec()).scale(0.5));
        if (b) {
            Util.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos, enumFacing, EnumHand.MAIN_HAND, (float)add.x - blockPos.getX(), (float)add.y - blockPos.getY(), (float)add.z - blockPos.getZ()));
        }
        else {
            Util.mc.playerController.processRightClickBlock(Util.mc.player, Util.mc.world, offset, getOpposite, add, EnumHand.MAIN_HAND);
        }
        Util.mc.player.swingArm(EnumHand.MAIN_HAND);
    }
    
    public static EnumFacing getFacing(final BlockPos blockPos) {
        for (final EnumFacing enumFacing : EnumFacing.values()) {
            final RayTraceResult rayTraceBlocks = BlockUtil.mc.world.rayTraceBlocks(new Vec3d(BlockUtil.mc.player.posX, BlockUtil.mc.player.posY + BlockUtil.mc.player.getEyeHeight(), BlockUtil.mc.player.posZ), new Vec3d(blockPos.getX() + 0.5 + enumFacing.getDirectionVec().getX() / 2.0, blockPos.getY() + 0.5 + enumFacing.getDirectionVec().getY() / 2.0, blockPos.getZ() + 0.5 + enumFacing.getDirectionVec().getZ() / 2.0), false, true, false);
            if (rayTraceBlocks == null || (rayTraceBlocks.typeOfHit == RayTraceResult.Type.BLOCK && rayTraceBlocks.getBlockPos().equals((Object)blockPos))) {
                return enumFacing;
            }
        }
        if (blockPos.getY() > BlockUtil.mc.player.posY + BlockUtil.mc.player.getEyeHeight()) {
            return EnumFacing.DOWN;
        }
        return EnumFacing.UP;
    }
    
    public static List<BlockPos> possiblePlacePositions(final float n) {
        final NonNullList create = NonNullList.create();
        create.addAll((Collection)getSphere(EntityUtil.getPlayerPos((EntityPlayer)BlockUtil.mc.player), n, (int)n, false, true, 0).stream().filter((Predicate<? super Object>)BlockUtil::canPlaceCrystal).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList()));
        return (List<BlockPos>)create;
    }
    
    public static boolean isBlockSolid(final BlockPos blockPos) {
        return !isBlockUnSolid(blockPos);
    }
    
    public static Vec3d[] convertVec3ds(final Vec3d vec3d, final Vec3d[] array) {
        final Vec3d[] array2 = new Vec3d[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = vec3d.add(array[i]);
        }
        return array2;
    }
    
    public static boolean isElseHole(final BlockPos blockPos) {
        final BlockPos[] touchingBlocks = getTouchingBlocks(blockPos);
        for (int length = touchingBlocks.length, i = 0; i < length; ++i) {
            final IBlockState getBlockState = BlockUtil.mc.world.getBlockState(touchingBlocks[i]);
            if (getBlockState.getBlock() == Blocks.AIR || !getBlockState.isFullBlock()) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean canBeClicked(final BlockPos blockPos) {
        return getBlock(blockPos).canCollideCheck(getState(blockPos), false);
    }
    
    private static boolean IsDoneBreaking(final IBlockState blockState) {
        return blockState.getBlock() == Blocks.BEDROCK || blockState.getBlock() == Blocks.AIR || blockState.getBlock() instanceof BlockLiquid;
    }
    
    public static void rightClickBlockLegit(final BlockPos blockPos, final float n, final boolean b, final EnumHand enumHand, final AtomicDouble atomicDouble, final AtomicDouble atomicDouble2, final AtomicBoolean atomicBoolean, final boolean b2) {
        final Vec3d eyesPos = RotationUtil.getEyesPos();
        final Vec3d add = new Vec3d((Vec3i)blockPos).add(0.5, 0.5, 0.5);
        final double squareDistanceTo = eyesPos.squareDistanceTo(add);
        for (final EnumFacing enumFacing : EnumFacing.values()) {
            final Vec3d add = add.add(new Vec3d(enumFacing.getDirectionVec()).scale(0.5));
            final double squareDistanceTo2 = eyesPos.squareDistanceTo(add);
            if (squareDistanceTo2 <= MathUtil.square(n) && squareDistanceTo2 < squareDistanceTo && BlockUtil.mc.world.rayTraceBlocks(eyesPos, add, false, true, false) == null) {
                if (b) {
                    final float[] legitRotations = RotationUtil.getLegitRotations(add);
                    atomicDouble.set((double)legitRotations[0]);
                    atomicDouble2.set((double)legitRotations[1]);
                    atomicBoolean.set(true);
                }
                rightClickBlock(blockPos, add, enumHand, enumFacing, b2);
                BlockUtil.mc.player.swingArm(enumHand);
                BlockUtil.mc.rightClickDelayTimer = 4;
                break;
            }
        }
    }
    
    public static Vec3d[] convertVec3ds(final EntityPlayer entityPlayer, final Vec3d[] array) {
        return convertVec3ds(entityPlayer.getPositionVector(), array);
    }
    
    public static EnumFacing getRayTraceFacing(final BlockPos blockPos) {
        final RayTraceResult rayTraceBlocks = BlockUtil.mc.world.rayTraceBlocks(new Vec3d(BlockUtil.mc.player.posX, BlockUtil.mc.player.posY + BlockUtil.mc.player.getEyeHeight(), BlockUtil.mc.player.posZ), new Vec3d(blockPos.getX() + 0.5, blockPos.getX() - 0.5, blockPos.getX() + 0.5));
        if (rayTraceBlocks == null || rayTraceBlocks.sideHit == null) {
            return EnumFacing.UP;
        }
        return rayTraceBlocks.sideHit;
    }
    
    public static boolean isInterceptedByCrystal(final BlockPos blockPos) {
        for (final Entity entity : BlockUtil.mc.world.loadedEntityList) {
            if (entity instanceof EntityEnderCrystal && entity != BlockUtil.mc.player && !(entity instanceof EntityItem)) {
                if (!new AxisAlignedBB(blockPos).intersects(entity.getEntityBoundingBox())) {
                    continue;
                }
                return true;
            }
        }
        return false;
    }
    
    public static void rightClickBed(final BlockPos blockPos, final float n, final boolean b, final EnumHand enumHand, final AtomicDouble atomicDouble, final AtomicDouble atomicDouble2, final AtomicBoolean atomicBoolean, final boolean b2) {
        final Vec3d add = new Vec3d((Vec3i)blockPos).add(0.5, 0.5, 0.5);
        final RayTraceResult rayTraceBlocks = BlockUtil.mc.world.rayTraceBlocks(new Vec3d(BlockUtil.mc.player.posX, BlockUtil.mc.player.posY + BlockUtil.mc.player.getEyeHeight(), BlockUtil.mc.player.posZ), add);
        final EnumFacing enumFacing = (rayTraceBlocks == null || rayTraceBlocks.sideHit == null) ? EnumFacing.UP : rayTraceBlocks.sideHit;
        RotationUtil.getEyesPos();
        if (b) {
            final float[] legitRotations = RotationUtil.getLegitRotations(add);
            atomicDouble.set((double)legitRotations[0]);
            atomicDouble2.set((double)legitRotations[1]);
            atomicBoolean.set(true);
        }
        rightClickBlock(blockPos, add, enumHand, enumFacing, b2);
        BlockUtil.mc.player.swingArm(enumHand);
        BlockUtil.mc.rightClickDelayTimer = 4;
    }
    
    public static boolean isBlockUnSolid(final Block block) {
        return BlockUtil.unSolidBlocks.contains(block);
    }
    
    public static List<BlockPos> possiblePlacePositions(final float n, final boolean b, final boolean b2, final boolean b3) {
        final NonNullList create = NonNullList.create();
        create.addAll((Collection)getSphere(EntityUtil.getPlayerPos((EntityPlayer)BlockUtil.mc.player), n, (int)n, false, true, 0).stream().filter(blockPos -> canPlaceCrystal(blockPos, b, b2, b3)).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList()));
        return (List<BlockPos>)create;
    }
    
    public static boolean isInHole() {
        final BlockPos blockPos = new BlockPos(BlockUtil.mc.player.posX, BlockUtil.mc.player.posY, BlockUtil.mc.player.posZ);
        return isBlockValid(BlockUtil.mc.world.getBlockState(blockPos), blockPos);
    }
    
    public static int isPositionPlaceable(final BlockPos blockPos, final boolean b) {
        return isPositionPlaceable(blockPos, b, true);
    }
    
    private static float getBlockDensity(final Vec3d vec3d, final AxisAlignedBB axisAlignedBB) {
        final double n = 1.0 / ((axisAlignedBB.maxX - axisAlignedBB.minX) * 2.0 + 1.0);
        final double n2 = 1.0 / ((axisAlignedBB.maxY - axisAlignedBB.minY) * 2.0 + 1.0);
        final double n3 = 1.0 / ((axisAlignedBB.maxZ - axisAlignedBB.minZ) * 2.0 + 1.0);
        final double n4 = (1.0 - Math.floor(1.0 / n) * n) / 2.0;
        final double n5 = (1.0 - Math.floor(1.0 / n3) * n3) / 2.0;
        if (n >= 0.0 && n2 >= 0.0 && n3 >= 0.0) {
            int n6 = 0;
            int n7 = 0;
            for (float n8 = 0.0f; n8 <= 1.0f; n8 += (float)n) {
                for (float n9 = 0.0f; n9 <= 1.0f; n9 += (float)n2) {
                    for (float n10 = 0.0f; n10 <= 1.0f; n10 += (float)n3) {
                        if (rayTraceBlocks(new Vec3d(axisAlignedBB.minX + (axisAlignedBB.maxX - axisAlignedBB.minX) * n8 + n4, axisAlignedBB.minY + (axisAlignedBB.maxY - axisAlignedBB.minY) * n9, axisAlignedBB.minZ + (axisAlignedBB.maxZ - axisAlignedBB.minZ) * n10 + n5), vec3d, false, false, false, true) == null) {
                            ++n6;
                        }
                        ++n7;
                    }
                }
            }
            return n6 / (float)n7;
        }
        return 0.0f;
    }
    
    public static BlockPos GetCurrBlock() {
        return BlockUtil._currBlock;
    }
    
    public static float calculateDamage(final double n, final double n2, final double n3, final Entity entity) {
        final float n4 = 12.0f;
        final double n5 = entity.getDistance(n, n2, n3) / n4;
        final Vec3d vec3d = new Vec3d(n, n2, n3);
        double n6 = 0.0;
        try {
            n6 = getBlockDensity(vec3d, entity.getEntityBoundingBox());
        }
        catch (Exception ex) {}
        final double n7 = (1.0 - n5) * n6;
        final float n8 = (float)(int)((n7 * n7 + n7) / 2.0 * 7.0 * n4 + 1.0);
        double n9 = 1.0;
        if (entity instanceof EntityLivingBase) {
            try {
                n9 = getBlastReduction((EntityLivingBase)entity, getDamageMultiplied(n8), new Explosion((World)BlockUtil.mc.world, (Entity)null, n, n2, n3, 6.0f, false, true));
            }
            catch (Exception ex2) {}
        }
        return (float)n9;
    }
    
    private static IBlockState getState(final BlockPos blockPos) {
        return BlockUtil.mc.world.getBlockState(blockPos);
    }
    
    public static boolean rayTracePlaceCheck(final BlockPos blockPos) {
        return rayTracePlaceCheck(blockPos, true);
    }
    
    public static boolean canBreak(final BlockPos blockPos) {
        final IBlockState getBlockState = BlockUtil.mc.world.getBlockState(blockPos);
        return getBlockState.getBlock().getBlockHardness(getBlockState, (World)BlockUtil.mc.world, blockPos) != -1.0f;
    }
    
    public static BlockPos[] getTouchingBlocks(final BlockPos blockPos) {
        return new BlockPos[] { blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down() };
    }
    
    private static RayTraceResult rayTraceBlocks(Vec3d vec3d, final Vec3d vec3d2, final boolean b, final boolean b2, final boolean b3, final boolean b4) {
        if (Double.isNaN(vec3d.x) || Double.isNaN(vec3d.y) || Double.isNaN(vec3d.z)) {
            return null;
        }
        if (Double.isNaN(vec3d2.x) || Double.isNaN(vec3d2.y) || Double.isNaN(vec3d2.z)) {
            return null;
        }
        int floor = MathHelper.floor(vec3d.x);
        int floor2 = MathHelper.floor(vec3d.y);
        int floor3 = MathHelper.floor(vec3d.z);
        final int floor4 = MathHelper.floor(vec3d2.x);
        final int floor5 = MathHelper.floor(vec3d2.y);
        final int floor6 = MathHelper.floor(vec3d2.z);
        final BlockPos blockPos = new BlockPos(floor, floor2, floor3);
        final IBlockState getBlockState = BlockUtil.mc.world.getBlockState(blockPos);
        final Block getBlock = getBlockState.getBlock();
        final RayTraceResult collisionRayTrace;
        if ((!b2 || getBlockState.getCollisionBoundingBox((IBlockAccess)BlockUtil.mc.world, blockPos) != Block.NULL_AABB) && getBlock.canCollideCheck(getBlockState, b) && (!b4 || !(getBlock instanceof BlockWeb)) && (collisionRayTrace = getBlockState.collisionRayTrace((World)BlockUtil.mc.world, blockPos, vec3d, vec3d2)) != null) {
            return collisionRayTrace;
        }
        RayTraceResult rayTraceResult = null;
        int n = 200;
        while (n-- >= 0) {
            if (Double.isNaN(vec3d.x) || Double.isNaN(vec3d.y) || Double.isNaN(vec3d.z)) {
                return null;
            }
            if (floor == floor4 && floor2 == floor5 && floor3 == floor6) {
                return b3 ? rayTraceResult : null;
            }
            boolean b5 = true;
            boolean b6 = true;
            boolean b7 = true;
            double n2 = 999.0;
            double n3 = 999.0;
            double n4 = 999.0;
            if (floor4 > floor) {
                n2 = floor + 1.0;
            }
            else if (floor4 < floor) {
                n2 = floor + 0.0;
            }
            else {
                b5 = false;
            }
            if (floor5 > floor2) {
                n3 = floor2 + 1.0;
            }
            else if (floor5 < floor2) {
                n3 = floor2 + 0.0;
            }
            else {
                b6 = false;
            }
            if (floor6 > floor3) {
                n4 = floor3 + 1.0;
            }
            else if (floor6 < floor3) {
                n4 = floor3 + 0.0;
            }
            else {
                b7 = false;
            }
            double n5 = 999.0;
            double n6 = 999.0;
            double n7 = 999.0;
            final double n8 = vec3d2.x - vec3d.x;
            final double n9 = vec3d2.y - vec3d.y;
            final double n10 = vec3d2.z - vec3d.z;
            if (b5) {
                n5 = (n2 - vec3d.x) / n8;
            }
            if (b6) {
                n6 = (n3 - vec3d.y) / n9;
            }
            if (b7) {
                n7 = (n4 - vec3d.z) / n10;
            }
            if (n5 == -0.0) {
                n5 = -1.0E-4;
            }
            if (n6 == -0.0) {
                n6 = -1.0E-4;
            }
            if (n7 == -0.0) {
                n7 = -1.0E-4;
            }
            EnumFacing enumFacing;
            if (n5 < n6 && n5 < n7) {
                enumFacing = ((floor4 > floor) ? EnumFacing.WEST : EnumFacing.EAST);
                vec3d = new Vec3d(n2, vec3d.y + n9 * n5, vec3d.z + n10 * n5);
            }
            else if (n6 < n7) {
                enumFacing = ((floor5 > floor2) ? EnumFacing.DOWN : EnumFacing.UP);
                vec3d = new Vec3d(vec3d.x + n8 * n6, n3, vec3d.z + n10 * n6);
            }
            else {
                enumFacing = ((floor6 > floor3) ? EnumFacing.NORTH : EnumFacing.SOUTH);
                vec3d = new Vec3d(vec3d.x + n8 * n7, vec3d.y + n9 * n7, n4);
            }
            floor = MathHelper.floor(vec3d.x) - ((enumFacing == EnumFacing.EAST) ? 1 : 0);
            floor2 = MathHelper.floor(vec3d.y) - ((enumFacing == EnumFacing.UP) ? 1 : 0);
            floor3 = MathHelper.floor(vec3d.z) - ((enumFacing == EnumFacing.SOUTH) ? 1 : 0);
            final BlockPos blockPos2 = new BlockPos(floor, floor2, floor3);
            final IBlockState getBlockState2 = BlockUtil.mc.world.getBlockState(blockPos2);
            final Block getBlock2 = getBlockState2.getBlock();
            if (b2 && getBlockState2.getMaterial() != Material.PORTAL && getBlockState2.getCollisionBoundingBox((IBlockAccess)BlockUtil.mc.world, blockPos2) == Block.NULL_AABB) {
                continue;
            }
            if (getBlock2.canCollideCheck(getBlockState2, b) && (!b4 || !(getBlock2 instanceof BlockWeb))) {
                final RayTraceResult collisionRayTrace2 = getBlockState2.collisionRayTrace((World)BlockUtil.mc.world, blockPos2, vec3d, vec3d2);
                if (collisionRayTrace2 == null) {
                    continue;
                }
                return collisionRayTrace2;
            }
            else {
                rayTraceResult = new RayTraceResult(RayTraceResult.Type.MISS, vec3d, enumFacing, blockPos2);
            }
        }
        return b3 ? rayTraceResult : null;
    }
    
    public static void placeBlockStopSneaking(final BlockPos blockPos, final EnumHand enumHand, final boolean b, final boolean b2, final boolean b3) {
        final boolean placeBlockSmartRotate = placeBlockSmartRotate(blockPos, enumHand, b, b2, b3);
        if (!b3 && placeBlockSmartRotate) {
            BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
        }
    }
    
    public static double getNearestBlockBelow() {
        for (double posY = BlockUtil.mc.player.posY; posY > 0.0; posY -= 0.001) {
            if (!(BlockUtil.mc.world.getBlockState(new BlockPos(BlockUtil.mc.player.posX, posY, BlockUtil.mc.player.posZ)).getBlock() instanceof BlockSlab) && BlockUtil.mc.world.getBlockState(new BlockPos(BlockUtil.mc.player.posX, posY, BlockUtil.mc.player.posZ)).getBlock().getDefaultState().getCollisionBoundingBox((IBlockAccess)BlockUtil.mc.world, new BlockPos(0, 0, 0)) != null) {
                return posY;
            }
        }
        return -1.0;
    }
    
    public static void SetCurrentBlock(final BlockPos currBlock) {
        BlockUtil._currBlock = currBlock;
        BlockUtil._started = false;
    }
    
    public static boolean isBlockEmpty(final BlockPos blockPos) {
        try {
            if (BlockUtil.emptyBlocks.contains(BlockUtil.mc.world.getBlockState(blockPos).getBlock())) {
                final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos);
                for (final Entity entity : BlockUtil.mc.world.loadedEntityList) {
                    if (entity instanceof EntityLivingBase) {
                        if (!axisAlignedBB.intersects(entity.getEntityBoundingBox())) {
                            continue;
                        }
                        return false;
                    }
                }
                return true;
            }
        }
        catch (Exception ex) {}
        return false;
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
    
    public static boolean isValidBlock(final BlockPos blockPos) {
        final Block getBlock = BlockUtil.mc.world.getBlockState(blockPos).getBlock();
        return !(getBlock instanceof BlockLiquid) && getBlock.getMaterial((IBlockState)null) != Material.AIR;
    }
    
    public static void debugPos(final String str, final BlockPos blockPos) {
        Command.sendMessage(String.valueOf(new StringBuilder().append(str).append(blockPos.getX()).append("x, ").append(blockPos.getY()).append("y, ").append(blockPos.getZ()).append("z")));
    }
    
    public static boolean isBlockValid(final IBlockState blockState, final BlockPos blockPos) {
        return blockState.getBlock() == Blocks.AIR && BlockUtil.mc.player.getDistanceSq(blockPos) >= 1.0 && BlockUtil.mc.world.getBlockState(blockPos.up()).getBlock() == Blocks.AIR && BlockUtil.mc.world.getBlockState(blockPos.up(2)).getBlock() == Blocks.AIR && (isBedrockHole(blockPos) || isObbyHole(blockPos) || isBothHole(blockPos) || isElseHole(blockPos));
    }
    
    public static boolean isEnemySafe(final EntityPlayer entityPlayer) {
        final BlockPos blockPos = new BlockPos(Math.floor(entityPlayer.posX), Math.floor(entityPlayer.posY), Math.floor(entityPlayer.posZ));
        return (BlockUtil.mc.world.getBlockState(blockPos.down()).getBlock() == Blocks.OBSIDIAN || BlockUtil.mc.world.getBlockState(blockPos.down()).getBlock() == Blocks.BEDROCK) && (BlockUtil.mc.world.getBlockState(blockPos.north()).getBlock() == Blocks.OBSIDIAN || BlockUtil.mc.world.getBlockState(blockPos.north()).getBlock() == Blocks.BEDROCK) && (BlockUtil.mc.world.getBlockState(blockPos.east()).getBlock() == Blocks.OBSIDIAN || BlockUtil.mc.world.getBlockState(blockPos.east()).getBlock() == Blocks.BEDROCK) && (BlockUtil.mc.world.getBlockState(blockPos.south()).getBlock() == Blocks.OBSIDIAN || BlockUtil.mc.world.getBlockState(blockPos.south()).getBlock() == Blocks.BEDROCK) && (BlockUtil.mc.world.getBlockState(blockPos.west()).getBlock() == Blocks.OBSIDIAN || BlockUtil.mc.world.getBlockState(blockPos.west()).getBlock() == Blocks.BEDROCK);
    }
    
    public static boolean isObbyHole(final BlockPos blockPos) {
        final BlockPos[] touchingBlocks = getTouchingBlocks(blockPos);
        for (int length = touchingBlocks.length, i = 0; i < length; ++i) {
            final IBlockState getBlockState = BlockUtil.mc.world.getBlockState(touchingBlocks[i]);
            if (getBlockState.getBlock() == Blocks.AIR || getBlockState.getBlock() != Blocks.OBSIDIAN) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean canPlaceCrystal(final BlockPos blockPos) {
        final BlockPos add = blockPos.add(0, 1, 0);
        final BlockPos add2 = blockPos.add(0, 2, 0);
        try {
            return (BlockUtil.mc.world.getBlockState(blockPos).getBlock() == Blocks.BEDROCK || BlockUtil.mc.world.getBlockState(blockPos).getBlock() == Blocks.OBSIDIAN) && BlockUtil.mc.world.getBlockState(add).getBlock() == Blocks.AIR && BlockUtil.mc.world.getBlockState(add2).getBlock() == Blocks.AIR && BlockUtil.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(add)).isEmpty() && BlockUtil.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(add2)).isEmpty();
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public static boolean placeBlockSmartRotate(final BlockPos blockPos, final EnumHand enumHand, final boolean b, final boolean b2, final boolean b3) {
        boolean b4 = false;
        final EnumFacing firstFacing = getFirstFacing(blockPos);
        if (firstFacing == null) {
            return b3;
        }
        final BlockPos offset = blockPos.offset(firstFacing);
        final EnumFacing getOpposite = firstFacing.getOpposite();
        final Vec3d add = new Vec3d((Vec3i)offset).add(0.5, 0.5, 0.5).add(new Vec3d(getOpposite.getDirectionVec()).scale(0.5));
        final Block getBlock = BlockUtil.mc.world.getBlockState(offset).getBlock();
        if (!BlockUtil.mc.player.isSneaking() && (BlockUtil.blackList.contains(getBlock) || BlockUtil.shulkerList.contains(getBlock))) {
            BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            b4 = true;
        }
        if (b) {
            LuigiHack.rotationManager.lookAtVec3d(add);
        }
        rightClickBlock(offset, add, enumHand, getOpposite, b2);
        BlockUtil.mc.player.swingArm(EnumHand.MAIN_HAND);
        BlockUtil.mc.rightClickDelayTimer = 4;
        return b4 || b3;
    }
    
    public static BlockPos[] toBlockPos(final Vec3d[] array) {
        final BlockPos[] array2 = new BlockPos[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = new BlockPos(array[i]);
        }
        return array2;
    }
    
    public static boolean Update(final float n, final boolean b) {
        if (BlockUtil._currBlock == null) {
            return false;
        }
        if (!IsDoneBreaking(BlockUtil.mc.world.getBlockState(BlockUtil._currBlock)) && BlockUtil.mc.player.getDistanceSq(BlockUtil._currBlock) <= Math.pow(n, n)) {
            BlockUtil.mc.player.swingArm(EnumHand.MAIN_HAND);
            EnumFacing enumFacing = EnumFacing.UP;
            if (b) {
                final RayTraceResult rayTraceBlocks = BlockUtil.mc.world.rayTraceBlocks(new Vec3d(BlockUtil.mc.player.posX, BlockUtil.mc.player.posY + BlockUtil.mc.player.getEyeHeight(), BlockUtil.mc.player.posZ), new Vec3d(BlockUtil._currBlock.getX() + 0.5, BlockUtil._currBlock.getY() - 0.5, BlockUtil._currBlock.getZ() + 0.5));
                if (rayTraceBlocks != null && rayTraceBlocks.sideHit != null) {
                    enumFacing = rayTraceBlocks.sideHit;
                }
            }
            if (!BlockUtil._started) {
                BlockUtil._started = true;
                BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, BlockUtil._currBlock, enumFacing));
                BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, BlockUtil._currBlock, enumFacing));
            }
            else {
                BlockUtil.mc.playerController.onPlayerDamageBlock(BlockUtil._currBlock, enumFacing);
            }
            return true;
        }
        BlockUtil._currBlock = null;
        return false;
    }
    
    private static float getBlastReduction(final EntityLivingBase entityLivingBase, final float n, final Explosion explosion) {
        if (entityLivingBase instanceof EntityPlayer) {
            final EntityPlayer entityPlayer = (EntityPlayer)entityLivingBase;
            final DamageSource causeExplosionDamage = DamageSource.causeExplosionDamage(explosion);
            final float getDamageAfterAbsorb = CombatRules.getDamageAfterAbsorb(n, (float)entityPlayer.getTotalArmorValue(), (float)entityPlayer.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue());
            int getEnchantmentModifierDamage = 0;
            try {
                getEnchantmentModifierDamage = EnchantmentHelper.getEnchantmentModifierDamage(entityPlayer.getArmorInventoryList(), causeExplosionDamage);
            }
            catch (Exception ex) {}
            float a = getDamageAfterAbsorb * (1.0f - MathHelper.clamp((float)getEnchantmentModifierDamage, 0.0f, 20.0f) / 25.0f);
            if (entityLivingBase.isPotionActive(MobEffects.RESISTANCE)) {
                a -= a / 4.0f;
            }
            return Math.max(a, 0.0f);
        }
        return CombatRules.getDamageAfterAbsorb(n, (float)entityLivingBase.getTotalArmorValue(), (float)entityLivingBase.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue());
    }
    
    public static boolean isInterceptedByOther(final BlockPos blockPos) {
        for (final Entity entity : BlockUtil.mc.world.loadedEntityList) {
            if (!(entity instanceof EntityEnderCrystal) && !(entity instanceof EntityItem)) {
                if (!new AxisAlignedBB(blockPos).intersects(entity.getEntityBoundingBox())) {
                    continue;
                }
                return true;
            }
        }
        return false;
    }
    
    public static boolean isBlockUnSolid(final BlockPos blockPos) {
        return isBlockUnSolid(BlockUtil.mc.world.getBlockState(blockPos).getBlock());
    }
    
    public static boolean isBedrockHole(final BlockPos blockPos) {
        final BlockPos[] touchingBlocks = getTouchingBlocks(blockPos);
        for (int length = touchingBlocks.length, i = 0; i < length; ++i) {
            final IBlockState getBlockState = BlockUtil.mc.world.getBlockState(touchingBlocks[i]);
            if (getBlockState.getBlock() == Blocks.AIR || getBlockState.getBlock() != Blocks.BEDROCK) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean rayTracePlaceCheck(final BlockPos blockPos, final boolean b) {
        return rayTracePlaceCheck(blockPos, b, 1.0f);
    }
    
    public static EnumFacing getFirstFacing(final BlockPos blockPos) {
        final Iterator<EnumFacing> iterator = getPossibleSides(blockPos).iterator();
        if (iterator.hasNext()) {
            return iterator.next();
        }
        return null;
    }
    
    public static void placeCrystalOnBlock(final BlockPos blockPos, final EnumHand enumHand, final boolean b, final boolean b2, final boolean b3) {
        final RayTraceResult rayTraceBlocks = BlockUtil.mc.world.rayTraceBlocks(new Vec3d(BlockUtil.mc.player.posX, BlockUtil.mc.player.posY + BlockUtil.mc.player.getEyeHeight(), BlockUtil.mc.player.posZ), new Vec3d(blockPos.getX() + 0.5, blockPos.getY() - 0.5, blockPos.getZ() + 0.5));
        final EnumFacing enumFacing = (rayTraceBlocks == null || rayTraceBlocks.sideHit == null) ? EnumFacing.UP : rayTraceBlocks.sideHit;
        final int currentItem = BlockUtil.mc.player.inventory.currentItem;
        final int itemHotbar = InventoryUtil.getItemHotbar(Items.END_CRYSTAL);
        if (enumHand == EnumHand.MAIN_HAND && b3 && itemHotbar != -1 && itemHotbar != BlockUtil.mc.player.inventory.currentItem) {
            BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(itemHotbar));
        }
        BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos, enumFacing, enumHand, 0.0f, 0.0f, 0.0f));
        if (enumHand == EnumHand.MAIN_HAND && b3 && itemHotbar != -1 && itemHotbar != BlockUtil.mc.player.inventory.currentItem) {
            BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(currentItem));
        }
        if (b) {
            BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketAnimation(b2 ? enumHand : EnumHand.MAIN_HAND));
        }
    }
    
    public static boolean isBothHole(final BlockPos blockPos) {
        final BlockPos[] touchingBlocks = getTouchingBlocks(blockPos);
        for (int length = touchingBlocks.length, i = 0; i < length; ++i) {
            final IBlockState getBlockState = BlockUtil.mc.world.getBlockState(touchingBlocks[i]);
            if (getBlockState.getBlock() == Blocks.AIR || (getBlockState.getBlock() != Blocks.BEDROCK && getBlockState.getBlock() != Blocks.OBSIDIAN)) {
                return false;
            }
        }
        return true;
    }
    
    public static Vec3d posToVec3d(final BlockPos blockPos) {
        return new Vec3d((Vec3i)blockPos);
    }
    
    public static boolean isBlockBelowEntitySolid(final Entity entity) {
        return entity != null && isBlockSolid(new BlockPos(entity.posX, entity.posY - 1.0, entity.posZ));
    }
    
    public static List<BlockPos> getSphereAutoCrystal(final double n, final boolean b) {
        final ArrayList<BlockPos> list = new ArrayList<BlockPos>();
        final BlockPos blockPos = new BlockPos(Math.floor(BlockUtil.mc.player.posX), Math.floor(BlockUtil.mc.player.posY), Math.floor(BlockUtil.mc.player.posZ));
        for (int n2 = blockPos.getX() - (int)n; n2 <= blockPos.getX() + n; ++n2) {
            for (int n3 = blockPos.getY() - (int)n; n3 < blockPos.getY() + n; ++n3) {
                for (int n4 = blockPos.getZ() - (int)n; n4 <= blockPos.getZ() + n; ++n4) {
                    final double n5 = (blockPos.getX() - n2) * (blockPos.getX() - n2) + (blockPos.getZ() - n4) * (blockPos.getZ() - n4) + (blockPos.getY() - n3) * (blockPos.getY() - n3);
                    final BlockPos e = new BlockPos(n2, n3, n4);
                    if (n5 < n * n && b && !BlockUtil.mc.world.getBlockState(e).getBlock().equals(Blocks.AIR)) {
                        list.add(e);
                    }
                }
            }
        }
        return list;
    }
    
    public static Vec3d[] getHelpingBlocks(final Vec3d vec3d) {
        return new Vec3d[] { new Vec3d(vec3d.x, vec3d.y - 1.0, vec3d.z), new Vec3d((vec3d.x != 0.0) ? (vec3d.x * 2.0) : vec3d.x, vec3d.y, (vec3d.x != 0.0) ? vec3d.z : (vec3d.z * 2.0)), new Vec3d((vec3d.x == 0.0) ? (vec3d.x + 1.0) : vec3d.x, vec3d.y, (vec3d.x == 0.0) ? vec3d.z : (vec3d.z + 1.0)), new Vec3d((vec3d.x == 0.0) ? (vec3d.x - 1.0) : vec3d.x, vec3d.y, (vec3d.x == 0.0) ? vec3d.z : (vec3d.z - 1.0)), new Vec3d(vec3d.x, vec3d.y + 1.0, vec3d.z) };
    }
    
    public static List<BlockPos> getDisc(final BlockPos blockPos, final float n) {
        final ArrayList<BlockPos> list = new ArrayList<BlockPos>();
        final int getX = blockPos.getX();
        final int getY = blockPos.getY();
        final int getZ = blockPos.getZ();
        for (int n2 = getX - (int)n; n2 <= getX + n; ++n2) {
            for (int n3 = getZ - (int)n; n3 <= getZ + n; ++n3) {
                if ((getX - n2) * (getX - n2) + (getZ - n3) * (getZ - n3) < (double)(n * n)) {
                    list.add(new BlockPos(n2, getY, n3));
                }
            }
        }
        return list;
    }
    
    public static boolean placeBlockNotRetarded(final BlockPos blockPos, final EnumHand enumHand, final boolean b, final boolean b2, final boolean b3) {
        final EnumFacing firstFacing = getFirstFacing(blockPos);
        if (firstFacing == null) {
            return false;
        }
        final BlockPos offset = blockPos.offset(firstFacing);
        final EnumFacing getOpposite = firstFacing.getOpposite();
        final Vec3d add = new Vec3d((Vec3i)offset).add(0.5, 0.5, 0.5).add(new Vec3d(getOpposite.getDirectionVec()).scale(0.5));
        final Block getBlock = BlockUtil.mc.world.getBlockState(offset).getBlock();
        if (!BlockUtil.mc.player.isSneaking() && (BlockUtil.blackList.contains(getBlock) || BlockUtil.shulkerList.contains(getBlock))) {
            BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            BlockUtil.mc.player.setSneaking(true);
        }
        if (b) {
            RotationUtil.faceVector(b3 ? new Vec3d((Vec3i)blockPos) : add, true);
        }
        rightClickBlock(offset, add, enumHand, getOpposite, b2);
        BlockUtil.mc.player.swingArm(EnumHand.MAIN_HAND);
        return true;
    }
    
    public static boolean isScaffoldPos(final BlockPos blockPos) {
        return BlockUtil.mc.world.isAirBlock(blockPos) || BlockUtil.mc.world.getBlockState(blockPos).getBlock() == Blocks.SNOW_LAYER || BlockUtil.mc.world.getBlockState(blockPos).getBlock() == Blocks.TALLGRASS || BlockUtil.mc.world.getBlockState(blockPos).getBlock() instanceof BlockLiquid;
    }
    
    public static List<EnumFacing> getPossibleSides(final BlockPos blockPos) {
        final ArrayList<EnumFacing> list = new ArrayList<EnumFacing>();
        if (BlockUtil.mc.world == null || blockPos == null) {
            return list;
        }
        for (final EnumFacing e : EnumFacing.values()) {
            final IBlockState getBlockState = BlockUtil.mc.world.getBlockState(blockPos.offset(e));
            if (getBlockState.getBlock().canCollideCheck(getBlockState, false)) {
                if (!getBlockState.getMaterial().isReplaceable()) {
                    list.add(e);
                }
            }
        }
        return list;
    }
    
    static {
        blackList = Arrays.asList(Blocks.ENDER_CHEST, (Block)Blocks.CHEST, Blocks.TRAPPED_CHEST, Blocks.CRAFTING_TABLE, Blocks.ANVIL, Blocks.BREWING_STAND, (Block)Blocks.HOPPER, Blocks.DROPPER, Blocks.DISPENSER, Blocks.TRAPDOOR, Blocks.ENCHANTING_TABLE);
        shulkerList = Arrays.asList(Blocks.WHITE_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.SILVER_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.BLACK_SHULKER_BOX);
        BlockUtil.emptyBlocks = Arrays.asList(Blocks.AIR, (Block)Blocks.FLOWING_LAVA, (Block)Blocks.LAVA, (Block)Blocks.FLOWING_WATER, (Block)Blocks.WATER, Blocks.VINE, Blocks.SNOW_LAYER, (Block)Blocks.TALLGRASS, (Block)Blocks.FIRE);
        BlockUtil.rightclickableBlocks = Arrays.asList((Block)Blocks.CHEST, Blocks.TRAPPED_CHEST, Blocks.ENDER_CHEST, Blocks.WHITE_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.SILVER_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.ANVIL, Blocks.WOODEN_BUTTON, Blocks.STONE_BUTTON, (Block)Blocks.UNPOWERED_COMPARATOR, (Block)Blocks.UNPOWERED_REPEATER, (Block)Blocks.POWERED_REPEATER, (Block)Blocks.POWERED_COMPARATOR, Blocks.OAK_FENCE_GATE, Blocks.SPRUCE_FENCE_GATE, Blocks.BIRCH_FENCE_GATE, Blocks.JUNGLE_FENCE_GATE, Blocks.DARK_OAK_FENCE_GATE, Blocks.ACACIA_FENCE_GATE, Blocks.BREWING_STAND, Blocks.DISPENSER, Blocks.DROPPER, Blocks.LEVER, Blocks.NOTEBLOCK, Blocks.JUKEBOX, (Block)Blocks.BEACON, Blocks.BED, Blocks.FURNACE, (Block)Blocks.OAK_DOOR, (Block)Blocks.SPRUCE_DOOR, (Block)Blocks.BIRCH_DOOR, (Block)Blocks.JUNGLE_DOOR, (Block)Blocks.ACACIA_DOOR, (Block)Blocks.DARK_OAK_DOOR, Blocks.CAKE, Blocks.ENCHANTING_TABLE, Blocks.DRAGON_EGG, (Block)Blocks.HOPPER, Blocks.REPEATING_COMMAND_BLOCK, Blocks.COMMAND_BLOCK, Blocks.CHAIN_COMMAND_BLOCK, Blocks.CRAFTING_TABLE);
        BlockUtil.unSolidBlocks = Arrays.asList((Block)Blocks.FLOWING_LAVA, Blocks.FLOWER_POT, Blocks.SNOW, Blocks.CARPET, Blocks.END_ROD, (Block)Blocks.SKULL, Blocks.FLOWER_POT, Blocks.TRIPWIRE, (Block)Blocks.TRIPWIRE_HOOK, Blocks.WOODEN_BUTTON, Blocks.LEVER, Blocks.STONE_BUTTON, Blocks.LADDER, (Block)Blocks.UNPOWERED_COMPARATOR, (Block)Blocks.POWERED_COMPARATOR, (Block)Blocks.UNPOWERED_REPEATER, (Block)Blocks.POWERED_REPEATER, Blocks.UNLIT_REDSTONE_TORCH, Blocks.REDSTONE_TORCH, (Block)Blocks.REDSTONE_WIRE, Blocks.AIR, (Block)Blocks.PORTAL, Blocks.END_PORTAL, (Block)Blocks.WATER, (Block)Blocks.FLOWING_WATER, (Block)Blocks.LAVA, (Block)Blocks.FLOWING_LAVA, Blocks.SAPLING, (Block)Blocks.RED_FLOWER, (Block)Blocks.YELLOW_FLOWER, (Block)Blocks.BROWN_MUSHROOM, (Block)Blocks.RED_MUSHROOM, Blocks.WHEAT, Blocks.CARROTS, Blocks.POTATOES, Blocks.BEETROOTS, (Block)Blocks.REEDS, Blocks.PUMPKIN_STEM, Blocks.MELON_STEM, Blocks.WATERLILY, Blocks.NETHER_WART, Blocks.COCOA, Blocks.CHORUS_FLOWER, Blocks.CHORUS_PLANT, (Block)Blocks.TALLGRASS, (Block)Blocks.DEADBUSH, Blocks.VINE, (Block)Blocks.FIRE, Blocks.RAIL, Blocks.ACTIVATOR_RAIL, Blocks.DETECTOR_RAIL, Blocks.GOLDEN_RAIL, Blocks.TORCH);
    }
    
    public static void doBreak(final BlockPos blockPos, final double n) {
        if (!isInterceptedByOther(blockPos)) {
            return;
        }
        if (isInterceptedByCrystal(blockPos)) {
            Object o = null;
            for (final Entity entity : BlockUtil.mc.world.loadedEntityList) {
                final double n2 = calculateDamage(((EntityEnderCrystal)o).posX, ((EntityEnderCrystal)o).posY, ((EntityEnderCrystal)o).posZ, (Entity)BlockUtil.mc.player);
                if (entity != null && n2 >= n && BlockUtil.mc.player.getDistance(entity) <= 2.4 && entity instanceof EntityEnderCrystal) {
                    if (entity.isDead) {
                        continue;
                    }
                    o = entity;
                }
            }
            if (o != null) {
                BlockUtil.mc.getConnection().sendPacket((Packet)new CPacketUseEntity((Entity)o));
                BlockUtil.mc.player.swingArm(EnumHand.MAIN_HAND);
            }
        }
    }
    
    public static boolean rayTracePlaceCheck(final BlockPos blockPos, final boolean b, final float n) {
        return !b || BlockUtil.mc.world.rayTraceBlocks(new Vec3d(BlockUtil.mc.player.posX, BlockUtil.mc.player.posY + BlockUtil.mc.player.getEyeHeight(), BlockUtil.mc.player.posZ), new Vec3d((double)blockPos.getX(), (double)(blockPos.getY() + n), (double)blockPos.getZ()), false, true, false) == null;
    }
    
    private static Block getBlock(final BlockPos blockPos) {
        return getState(blockPos).getBlock();
    }
    
    private static float getDamageMultiplied(final float n) {
        final int getId = BlockUtil.mc.world.getDifficulty().getId();
        return n * ((getId == 0) ? 0.0f : ((getId == 2) ? 1.0f : ((getId == 1) ? 0.5f : 1.5f)));
    }
    
    public static boolean isPosValidForCrystal(final BlockPos blockPos, final boolean b) {
        if (BlockUtil.mc.world.getBlockState(blockPos).getBlock() != Blocks.BEDROCK && BlockUtil.mc.world.getBlockState(blockPos).getBlock() != Blocks.OBSIDIAN) {
            return false;
        }
        if (BlockUtil.mc.world.getBlockState(blockPos.up()).getBlock() != Blocks.AIR || (!b && BlockUtil.mc.world.getBlockState(blockPos.up().up()).getBlock() != Blocks.AIR)) {
            return false;
        }
        for (final Entity entity : BlockUtil.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(blockPos.up()))) {
            if (!entity.isDead) {
                if (entity instanceof EntityEnderCrystal) {
                    continue;
                }
                return false;
            }
        }
        for (final Entity entity2 : BlockUtil.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(blockPos.up().up()))) {
            if (!entity2.isDead) {
                if (entity2 instanceof EntityEnderCrystal) {
                    continue;
                }
                return false;
            }
        }
        return true;
    }
    
    public static void placeCrystalOnBlockNew(final BlockPos blockPos, final EnumHand enumHand, final boolean b, final boolean b2, final boolean b3, final boolean b4) {
        final RayTraceResult rayTraceBlocks = BlockUtil.mc.world.rayTraceBlocks(new Vec3d(BlockUtil.mc.player.posX, BlockUtil.mc.player.posY + BlockUtil.mc.player.getEyeHeight(), BlockUtil.mc.player.posZ), new Vec3d(blockPos.getX() + 0.5, blockPos.getY() - 0.5, blockPos.getZ() + 0.5));
        final EnumFacing enumFacing = (rayTraceBlocks == null || rayTraceBlocks.sideHit == null) ? EnumFacing.UP : rayTraceBlocks.sideHit;
        final int currentItem = BlockUtil.mc.player.inventory.currentItem;
        final int itemHotbar = InventoryUtil.getItemHotbar(Items.END_CRYSTAL);
        if (enumHand == EnumHand.MAIN_HAND && b4 && itemHotbar != -1 && itemHotbar != BlockUtil.mc.player.inventory.currentItem) {
            BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(itemHotbar));
        }
        BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos, enumFacing, enumHand, 0.0f, 0.0f, 0.0f));
        if (enumHand == EnumHand.MAIN_HAND && b4 && itemHotbar != -1 && itemHotbar != BlockUtil.mc.player.inventory.currentItem) {
            BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(currentItem));
        }
        if (b) {
            if (b3) {
                BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketAnimation(b2 ? enumHand : EnumHand.MAIN_HAND));
            }
            else {
                BlockUtil.mc.player.swingArm(b2 ? enumHand : EnumHand.MAIN_HAND);
            }
        }
    }
    
    public static List<BlockPos> getBlockSphere(final float n, final Class<BlockWorkbench> clazz) {
        final NonNullList create = NonNullList.create();
        create.addAll((Collection)getSphere(EntityUtil.getPlayerPos((EntityPlayer)BlockUtil.mc.player), n, (int)n, false, true, 0).stream().filter(blockPos -> clazz.isInstance(BlockUtil.mc.world.getBlockState(blockPos).getBlock())).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList()));
        return (List<BlockPos>)create;
    }
    
    public static boolean isAir(final BlockPos blockPos) {
        return BlockUtil.mc.world.getBlockState(blockPos).getBlock() == Blocks.AIR;
    }
    
    public static Boolean isPosInFov(final BlockPos blockPos) {
        final int direction4D = RotationUtil.getDirection4D();
        if (direction4D == 0 && blockPos.getZ() - BlockUtil.mc.player.getPositionVector().z < 0.0) {
            return false;
        }
        if (direction4D == 1 && blockPos.getX() - BlockUtil.mc.player.getPositionVector().x > 0.0) {
            return false;
        }
        if (direction4D == 2 && blockPos.getZ() - BlockUtil.mc.player.getPositionVector().z > 0.0) {
            return false;
        }
        return direction4D != 3 || blockPos.getX() - BlockUtil.mc.player.getPositionVector().x >= 0.0;
    }
    
    public static boolean placeBlock(final BlockPos blockPos, final EnumHand enumHand, final boolean b, final boolean b2, final boolean b3) {
        boolean b4 = false;
        final EnumFacing firstFacing = getFirstFacing(blockPos);
        if (firstFacing == null) {
            return b3;
        }
        final BlockPos offset = blockPos.offset(firstFacing);
        final EnumFacing getOpposite = firstFacing.getOpposite();
        final Vec3d add = new Vec3d((Vec3i)offset).add(0.5, 0.5, 0.5).add(new Vec3d(getOpposite.getDirectionVec()).scale(0.5));
        final Block getBlock = BlockUtil.mc.world.getBlockState(offset).getBlock();
        if (!BlockUtil.mc.player.isSneaking() && (BlockUtil.blackList.contains(getBlock) || BlockUtil.shulkerList.contains(getBlock))) {
            BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            BlockUtil.mc.player.setSneaking(true);
            b4 = true;
        }
        if (b) {
            RotationUtil.faceVector(add, true);
        }
        rightClickBlock(offset, add, enumHand, getOpposite, b2);
        BlockUtil.mc.player.swingArm(EnumHand.MAIN_HAND);
        BlockUtil.mc.rightClickDelayTimer = 4;
        return b4 || b3;
    }
    
    public static int isPositionPlaceable(final BlockPos blockPos, final boolean b, final boolean b2) {
        final Block getBlock = BlockUtil.mc.world.getBlockState(blockPos).getBlock();
        if (!(getBlock instanceof BlockAir) && !(getBlock instanceof BlockLiquid) && !(getBlock instanceof BlockTallGrass) && !(getBlock instanceof BlockFire) && !(getBlock instanceof BlockDeadBush) && !(getBlock instanceof BlockSnow)) {
            return 0;
        }
        if (!rayTracePlaceCheck(blockPos, b, 0.0f)) {
            return -1;
        }
        if (b2) {
            for (final Entity entity : BlockUtil.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(blockPos))) {
                if (!(entity instanceof EntityItem) && !(entity instanceof EntityXPOrb) && !(entity instanceof EntityArrow)) {
                    if (entity instanceof EntityExpBottle) {
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
    
    public static List<BlockPos> possiblePlacePositions(final float n, final boolean b, final boolean b2) {
        final NonNullList create = NonNullList.create();
        create.addAll((Collection)getSphere(EntityUtil.getPlayerPos((EntityPlayer)BlockUtil.mc.player), n, (int)n, false, true, 0).stream().filter(blockPos -> canPlaceCrystal(blockPos, b, b2)).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList()));
        return (List<BlockPos>)create;
    }
    
    public static boolean canPlaceCrystal(final BlockPos blockPos, final boolean b, final boolean b2, final boolean b3) {
        final BlockPos add = blockPos.add(0, 1, 0);
        final BlockPos add2 = blockPos.add(0, 2, 0);
        try {
            if (BlockUtil.mc.world.getBlockState(blockPos).getBlock() != Blocks.BEDROCK && BlockUtil.mc.world.getBlockState(blockPos).getBlock() != Blocks.OBSIDIAN) {
                return false;
            }
            if ((!b2 && BlockUtil.mc.world.getBlockState(add2).getBlock() != Blocks.AIR) || BlockUtil.mc.world.getBlockState(add).getBlock() != Blocks.AIR) {
                return false;
            }
            for (final Entity entity : BlockUtil.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(add))) {
                if (!entity.isDead) {
                    if (b && entity instanceof EntityEnderCrystal) {
                        continue;
                    }
                    return false;
                }
            }
            if (!b2 && !b3) {
                for (final Entity entity2 : BlockUtil.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(add2))) {
                    if (!entity2.isDead) {
                        if (b && entity2 instanceof EntityEnderCrystal) {
                            continue;
                        }
                        return false;
                    }
                }
            }
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }
    
    public static BlockPos vec3dToPos(final Vec3d vec3d) {
        return new BlockPos(vec3d);
    }
    
    public static boolean isBlockAboveEntitySolid(final Entity entity) {
        return entity != null && isBlockSolid(new BlockPos(entity.posX, entity.posY + 2.0, entity.posZ));
    }
}
