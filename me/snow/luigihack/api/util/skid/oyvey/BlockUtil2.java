//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util.skid.oyvey;

import net.minecraft.network.*;
import net.minecraft.init.*;
import net.minecraft.network.play.client.*;
import net.minecraft.util.math.*;
import com.google.common.util.concurrent.*;
import java.util.concurrent.atomic.*;
import net.minecraft.block.state.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.player.*;
import java.util.stream.*;
import java.util.function.*;
import net.minecraft.entity.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.item.*;
import net.minecraft.world.*;
import net.minecraft.util.*;
import net.minecraft.block.*;
import me.snow.luigihack.impl.command.*;
import me.snow.luigihack.*;
import net.minecraftforge.fml.relauncher.*;
import java.util.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.*;
import java.lang.reflect.*;
import net.minecraft.block.material.*;

public class BlockUtil2 implements Util
{
    public static /* synthetic */ List<Block> rightclickableBlocks;
    public static /* synthetic */ List<Block> unSolidBlocks;
    public static final /* synthetic */ List<Block> blackList;
    public static final /* synthetic */ List<Block> unSafeBlocks;
    public static final /* synthetic */ List<Block> shulkerList;
    public static /* synthetic */ List<Block> emptyBlocks;
    
    private static float[] getNeededRotations2(final Vec3d vec3d) {
        final Vec3d eyesPos = getEyesPos();
        final double x = vec3d.x - eyesPos.x;
        final double y = vec3d.y - eyesPos.y;
        final double y2 = vec3d.z - eyesPos.z;
        return new float[] { BlockUtil2.mc.player.rotationYaw + MathHelper.wrapDegrees((float)Math.toDegrees(Math.atan2(y2, x)) - 90.0f - BlockUtil2.mc.player.rotationYaw), BlockUtil2.mc.player.rotationPitch + MathHelper.wrapDegrees((float)(-Math.toDegrees(Math.atan2(y, Math.sqrt(x * x + y2 * y2)))) - BlockUtil2.mc.player.rotationPitch) };
    }
    
    public static boolean placeBlock(final BlockPos blockPos, final int currentItem, final boolean b, final boolean b2, final boolean b3) {
        if (TestUtil.isBlockEmpty(blockPos)) {
            int currentItem2 = -1;
            if (currentItem != BlockUtil2.mc.player.inventory.currentItem) {
                currentItem2 = BlockUtil2.mc.player.inventory.currentItem;
                BlockUtil2.mc.player.inventory.currentItem = currentItem;
            }
            for (final EnumFacing enumFacing : EnumFacing.values()) {
                final Block getBlock = BlockUtil2.mc.world.getBlockState(blockPos.offset(enumFacing)).getBlock();
                final Vec3d vec3d = new Vec3d(blockPos.getX() + 0.5 + enumFacing.getXOffset() * 0.5, blockPos.getY() + 0.5 + enumFacing.getYOffset() * 0.5, blockPos.getZ() + 0.5 + enumFacing.getZOffset() * 0.5);
                if (!BlockUtil2.emptyBlocks.contains(getBlock) && BlockUtil2.mc.player.getPositionEyes(BlockUtil2.mc.getRenderPartialTicks()).distanceTo(vec3d) <= 4.25) {
                    final float[] array = { BlockUtil2.mc.player.rotationYaw, BlockUtil2.mc.player.rotationPitch };
                    if (b) {
                        rotatePacket(vec3d.x, vec3d.y, vec3d.z);
                    }
                    if (BlockUtil2.rightclickableBlocks.contains(getBlock)) {
                        BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil2.mc.player, CPacketEntityAction.Action.START_SNEAKING));
                    }
                    BlockUtil2.mc.playerController.processRightClickBlock(BlockUtil2.mc.player, BlockUtil2.mc.world, blockPos.offset(enumFacing), enumFacing.getOpposite(), new Vec3d((Vec3i)blockPos), EnumHand.MAIN_HAND);
                    if (BlockUtil2.rightclickableBlocks.contains(getBlock)) {
                        BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil2.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
                    }
                    if (b2) {
                        BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(array[0], array[1], BlockUtil2.mc.player.onGround));
                    }
                    if (b3) {
                        BlockUtil2.mc.player.swingArm(EnumHand.MAIN_HAND);
                    }
                    if (currentItem2 != -1) {
                        BlockUtil2.mc.player.inventory.currentItem = currentItem2;
                    }
                    return true;
                }
            }
            if (currentItem2 != -1) {
                BlockUtil2.mc.player.inventory.currentItem = currentItem2;
            }
        }
        return false;
    }
    
    public static boolean canPlaceCrystal(final BlockPos blockPos) {
        final BlockPos add = blockPos.add(0, 1, 0);
        final BlockPos add2 = blockPos.add(0, 2, 0);
        try {
            return (BlockUtil2.mc.world.getBlockState(blockPos).getBlock() == Blocks.BEDROCK || BlockUtil2.mc.world.getBlockState(blockPos).getBlock() == Blocks.OBSIDIAN) && BlockUtil2.mc.world.getBlockState(add).getBlock() == Blocks.AIR && BlockUtil2.mc.world.getBlockState(add2).getBlock() == Blocks.AIR && BlockUtil2.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(add)).isEmpty() && BlockUtil2.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(add2)).isEmpty();
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public static void placeCrystalOnBlock(final BlockPos blockPos, final EnumHand enumHand, final boolean b, final boolean b2, final boolean b3) {
        final RayTraceResult rayTraceBlocks = BlockUtil2.mc.world.rayTraceBlocks(new Vec3d(BlockUtil2.mc.player.posX, BlockUtil2.mc.player.posY + BlockUtil2.mc.player.getEyeHeight(), BlockUtil2.mc.player.posZ), new Vec3d(blockPos.getX() + 0.5, blockPos.getY() - 0.5, blockPos.getZ() + 0.5));
        final EnumFacing enumFacing = (rayTraceBlocks == null || rayTraceBlocks.sideHit == null) ? EnumFacing.UP : rayTraceBlocks.sideHit;
        final int currentItem = BlockUtil2.mc.player.inventory.currentItem;
        final int itemHotbar = InventoryUtil.getItemHotbar(Items.END_CRYSTAL);
        if (enumHand == EnumHand.MAIN_HAND && b3 && itemHotbar != -1 && itemHotbar != BlockUtil2.mc.player.inventory.currentItem) {
            BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(itemHotbar));
        }
        BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos, enumFacing, enumHand, 0.0f, 0.0f, 0.0f));
        if (enumHand == EnumHand.MAIN_HAND && b3 && itemHotbar != -1 && itemHotbar != BlockUtil2.mc.player.inventory.currentItem) {
            BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(currentItem));
        }
        if (b) {
            BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketAnimation(b2 ? enumHand : EnumHand.MAIN_HAND));
        }
    }
    
    public static void rightClickBlockLegit(final BlockPos blockPos, final float n, final boolean b, final EnumHand enumHand, final AtomicDouble atomicDouble, final AtomicDouble atomicDouble2, final AtomicBoolean atomicBoolean) {
        final Vec3d eyesPos = RotationUtil.getEyesPos();
        final Vec3d add = new Vec3d((Vec3i)blockPos).add(0.5, 0.5, 0.5);
        final double squareDistanceTo = eyesPos.squareDistanceTo(add);
        for (final EnumFacing enumFacing : EnumFacing.values()) {
            final Vec3d add = add.add(new Vec3d(enumFacing.getDirectionVec()).scale(0.5));
            final double squareDistanceTo2 = eyesPos.squareDistanceTo(add);
            if (squareDistanceTo2 <= MathUtil.square(n) && squareDistanceTo2 < squareDistanceTo && BlockUtil2.mc.world.rayTraceBlocks(eyesPos, add, false, true, false) == null) {
                if (b) {
                    final float[] legitRotations = RotationUtil.getLegitRotations(add);
                    atomicDouble.set((double)legitRotations[0]);
                    atomicDouble2.set((double)legitRotations[1]);
                    atomicBoolean.set(true);
                }
                BlockUtil2.mc.playerController.processRightClickBlock(BlockUtil2.mc.player, BlockUtil2.mc.world, blockPos, enumFacing, add, enumHand);
                BlockUtil2.mc.player.swingArm(enumHand);
                BlockUtil2.mc.rightClickDelayTimer = 4;
                break;
            }
        }
    }
    
    public static boolean isBothHole(final BlockPos blockPos) {
        final BlockPos[] touchingBlocks = getTouchingBlocks(blockPos);
        for (int length = touchingBlocks.length, i = 0; i < length; ++i) {
            final IBlockState getBlockState = BlockUtil2.mc.world.getBlockState(touchingBlocks[i]);
            if (getBlockState.getBlock() == Blocks.AIR || (getBlockState.getBlock() != Blocks.BEDROCK && getBlockState.getBlock() != Blocks.OBSIDIAN)) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean hasNeighbour(final BlockPos blockPos) {
        final EnumFacing[] values = EnumFacing.values();
        for (int length = values.length, i = 0; i < length; ++i) {
            if (!BlockUtil2.mc.world.getBlockState(blockPos.offset(values[i])).getMaterial().isReplaceable()) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean isScaffoldPos(final BlockPos blockPos) {
        return BlockUtil2.mc.world.isAirBlock(blockPos) || BlockUtil2.mc.world.getBlockState(blockPos).getBlock() == Blocks.SNOW_LAYER || BlockUtil2.mc.world.getBlockState(blockPos).getBlock() == Blocks.TALLGRASS || BlockUtil2.mc.world.getBlockState(blockPos).getBlock() instanceof BlockLiquid;
    }
    
    public static List<BlockPos> getSphere(final float n, final boolean b) {
        final ArrayList<BlockPos> list = new ArrayList<BlockPos>();
        final BlockPos blockPos = new BlockPos(BlockUtil2.mc.player.getPositionVector());
        final int getX = blockPos.getX();
        final int getY = blockPos.getY();
        final int getZ = blockPos.getZ();
        final int n2 = (int)n;
        for (int n3 = getX - n2; n3 <= getX + n; ++n3) {
            for (int n4 = getZ - n2; n4 <= getZ + n; ++n4) {
                for (int n5 = getY - n2; n5 < getY + n; ++n5) {
                    if ((getX - n3) * (getX - n3) + (getZ - n4) * (getZ - n4) + (getY - n5) * (getY - n5) < n * n) {
                        final BlockPos e = new BlockPos(n3, n5, n4);
                        if (!b || BlockUtil2.mc.world.getBlockState(e).getBlock() != Blocks.AIR) {
                            list.add(e);
                        }
                    }
                }
            }
        }
        return list;
    }
    
    public static boolean isBlockBelowEntitySolid(final Entity entity) {
        return entity != null && isBlockSolid(new BlockPos(entity.posX, entity.posY - 1.0, entity.posZ));
    }
    
    public static boolean isBlockSolid(final BlockPos blockPos) {
        return !isBlockUnSolid(blockPos);
    }
    
    public static int isPositionPlaceable(final BlockPos blockPos, final boolean b, final boolean b2) {
        final Block getBlock = BlockUtil2.mc.world.getBlockState(blockPos).getBlock();
        if (!(getBlock instanceof BlockAir) && !(getBlock instanceof BlockLiquid) && !(getBlock instanceof BlockTallGrass) && !(getBlock instanceof BlockFire) && !(getBlock instanceof BlockDeadBush) && !(getBlock instanceof BlockSnow)) {
            return 0;
        }
        if (!rayTracePlaceCheck(blockPos, b, 0.0f)) {
            return -1;
        }
        if (b2) {
            for (final Entity entity : BlockUtil2.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(blockPos))) {
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
    
    public static BlockPos GetLocalPlayerPosFloored() {
        return new BlockPos(Math.floor(BlockUtil2.mc.player.posX), Math.floor(BlockUtil2.mc.player.posY), Math.floor(BlockUtil2.mc.player.posZ));
    }
    
    public static boolean canBeClicked(final BlockPos blockPos) {
        return getBlock(blockPos).canCollideCheck(getState(blockPos), false);
    }
    
    public static boolean placeBlockM1nt(final BlockPos blockPos, final EnumHand enumHand, final boolean b, final boolean b2, final boolean b3, final boolean b4, final EnumHand enumHand2) {
        boolean b5 = false;
        final EnumFacing firstFacing = getFirstFacing(blockPos);
        if (firstFacing == null) {
            return b3;
        }
        final BlockPos offset = blockPos.offset(firstFacing);
        final EnumFacing getOpposite = firstFacing.getOpposite();
        final Vec3d add = new Vec3d((Vec3i)offset).add(0.5, 0.5, 0.5).add(new Vec3d(getOpposite.getDirectionVec()).scale(0.5));
        final Block getBlock = Util.mc.world.getBlockState(offset).getBlock();
        if (!Util.mc.player.isSneaking() && (BlockUtil2.blackList.contains(getBlock) || BlockUtil2.shulkerList.contains(getBlock))) {
            Util.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Util.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            Util.mc.player.setSneaking(true);
            b5 = true;
        }
        if (b) {
            PlayerUtil.faceVector(add, true);
        }
        rightClickBlock(offset, add, enumHand, getOpposite, b2);
        if (b4) {
            Util.mc.player.swingArm(enumHand2);
        }
        Util.mc.rightClickDelayTimer = 4;
        return b5 || b3;
    }
    
    public static List<BlockPos> getSphere(final double n, final BlockPos blockPos, final boolean b, final boolean b2) {
        final ArrayList<BlockPos> list = new ArrayList<BlockPos>();
        final int getX = blockPos.getX();
        final int getY = blockPos.getY();
        final int getZ = blockPos.getZ();
        for (int n2 = getX - (int)n; n2 <= getX + n; ++n2) {
            for (int n3 = getZ - (int)n; n3 <= getZ + n; ++n3) {
                for (int n4 = b ? (getY - (int)n) : getY; n4 < (b ? (getY + n) : (getY + n)); ++n4) {
                    final double n5 = (getX - n2) * (getX - n2) + (getZ - n3) * (getZ - n3) + (b ? ((getY - n4) * (getY - n4)) : 0);
                    if (n5 < n * n && (!b2 || n5 >= (n - 1.0) * (n - 1.0))) {
                        list.add(new BlockPos(n2, n4, n3));
                    }
                }
            }
        }
        return list;
    }
    
    public static boolean isBlockBreakable(final BlockPos blockPos) {
        final Block block = getBlock(blockPos);
        return !(block instanceof BlockAir) && !(block instanceof BlockLiquid);
    }
    
    public static int isPositionPlaceable(final BlockPos blockPos, final boolean b) {
        return isPositionPlaceable(blockPos, b, true);
    }
    
    public static boolean isBlockUnSafe(final Block block) {
        return BlockUtil2.unSafeBlocks.contains(block);
    }
    
    public static boolean isPositionPlaceable(final BlockPos blockPos, final boolean b, final boolean b2, final boolean b3) {
        if (!BlockUtil2.mc.world.getBlockState(blockPos).getBlock().isReplaceable((IBlockAccess)BlockUtil2.mc.world, blockPos)) {
            return false;
        }
        if (b) {
            for (final Entity entity : BlockUtil2.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(blockPos))) {
                if (!(entity instanceof EntityItem) && !(entity instanceof EntityXPOrb)) {
                    if (entity instanceof EntityEnderCrystal && b3) {
                        continue;
                    }
                    return false;
                }
            }
        }
        return !b2 || getPlaceableSide(blockPos) != null;
    }
    
    public static List<BlockPos> getBlockSphere(final float n, final Class clazz) {
        final NonNullList create = NonNullList.create();
        create.addAll((Collection)getSphere(EntityUtil.getPlayerPos((EntityPlayer)BlockUtil2.mc.player), n, (int)n, false, true, 0).stream().filter(blockPos -> clazz.isInstance(BlockUtil2.mc.world.getBlockState(blockPos).getBlock())).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList()));
        return (List<BlockPos>)create;
    }
    
    public static List<BlockPos> possiblePlacePositions(final float n, final boolean b) {
        final NonNullList create = NonNullList.create();
        create.addAll((Collection)getSphere(EntityUtil.getPlayerPos((EntityPlayer)BlockUtil2.mc.player), n, (int)n, false, true, 0).stream().filter(blockPos -> canPlaceCrystal(blockPos, b)).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList()));
        return (List<BlockPos>)create;
    }
    
    public static BlockPos vec3dToPos(final Vec3d vec3d) {
        return new BlockPos(vec3d);
    }
    
    public static boolean placeBlocks(final BlockPos blockPos, final EnumHand enumHand, final boolean b, final boolean b2, final boolean b3) {
        final Block getBlock = BlockUtil2.mc.world.getBlockState(blockPos).getBlock();
        boolean b4 = false;
        if (!(getBlock instanceof BlockAir) && !(getBlock instanceof BlockLiquid)) {
            return false;
        }
        final EnumFacing placeableSide = getPlaceableSide(blockPos);
        if (placeableSide == null) {
            return false;
        }
        final BlockPos offset = blockPos.offset(placeableSide);
        final Vec3d add = new Vec3d((Vec3i)offset).add(new Vec3d(0.5, 0.5, 0.5)).add(new Vec3d(placeableSide.getOpposite().getDirectionVec()).scale(0.5));
        final Block getBlock2 = BlockUtil2.mc.world.getBlockState(offset).getBlock();
        if (!BlockUtil2.mc.player.isSneaking() && (BlockUtil2.blackList.contains(getBlock2) || BlockUtil2.shulkerList.contains(getBlock2))) {
            BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil2.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            BlockUtil2.mc.player.setSneaking(true);
            b4 = true;
        }
        if (b) {
            RotationUtil.faceVectorPacketInstant(add);
        }
        rightClickBlock(blockPos, add, enumHand, placeableSide, b2);
        BlockUtil2.mc.player.swingArm(EnumHand.MAIN_HAND);
        BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil2.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
        return b4 || b3;
    }
    
    public static void rotatePacket(final double n, final double n2, final double n3) {
        final double x = n - BlockUtil2.mc.player.posX;
        final double y = n2 - (BlockUtil2.mc.player.posY + BlockUtil2.mc.player.getEyeHeight());
        final double y2 = n3 - BlockUtil2.mc.player.posZ;
        BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation((float)Math.toDegrees(Math.atan2(y2, x)) - 90.0f, (float)(-Math.toDegrees(Math.atan2(y, Math.sqrt(x * x + y2 * y2)))), BlockUtil2.mc.player.onGround));
    }
    
    public static List<BlockPos> getSphereRealth(final float n, final boolean b) {
        final ArrayList<BlockPos> list = new ArrayList<BlockPos>();
        final BlockPos blockPos = new BlockPos(BlockUtil2.mc.player.getPositionVector());
        final int getX = blockPos.getX();
        final int getY = blockPos.getY();
        final int getZ = blockPos.getZ();
        final int n2 = (int)n;
        for (int n3 = getX - n2; n3 <= getX + n; ++n3) {
            for (int n4 = getZ - n2; n4 <= getZ + n; ++n4) {
                for (int n5 = getY - n2; n5 < getY + n; ++n5) {
                    if ((getX - n3) * (getX - n3) + (getZ - n4) * (getZ - n4) + (getY - n5) * (getY - n5) < n * n) {
                        final BlockPos e = new BlockPos(n3, n5, n4);
                        if (!b || BlockUtil2.mc.world.getBlockState(e).getBlock() != Blocks.AIR) {
                            list.add(e);
                        }
                    }
                }
            }
        }
        return list;
    }
    
    public static boolean rayTracePlaceCheck(final BlockPos blockPos, final boolean b, final float n) {
        return !b || BlockUtil2.mc.world.rayTraceBlocks(new Vec3d(BlockUtil2.mc.player.posX, BlockUtil2.mc.player.posY + BlockUtil2.mc.player.getEyeHeight(), BlockUtil2.mc.player.posZ), new Vec3d((double)blockPos.getX(), (double)(blockPos.getY() + n), (double)blockPos.getZ()), false, true, false) == null;
    }
    
    public static void rightClickBlock3(final BlockPos blockPos, final Vec3d vec3d, final EnumHand enumHand, final EnumFacing enumFacing) {
        BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos, enumFacing, enumHand, (float)(vec3d.x - blockPos.getX()), (float)(vec3d.y - blockPos.getY()), (float)(vec3d.z - blockPos.getZ())));
        BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketAnimation(enumHand));
        BlockUtil2.mc.rightClickDelayTimer = 4;
    }
    
    public static Vec3d[] convertVec3ds(final Vec3d vec3d, final Vec3d[] array) {
        final Vec3d[] array2 = new Vec3d[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = vec3d.add(array[i]);
        }
        return array2;
    }
    
    public static void rightClickBlock(final BlockPos blockPos, final Vec3d vec3d, final EnumHand enumHand, final EnumFacing enumFacing, final boolean b) {
        if (b) {
            BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos, enumFacing, enumHand, (float)(vec3d.x - blockPos.getX()), (float)(vec3d.y - blockPos.getY()), (float)(vec3d.z - blockPos.getZ())));
        }
        else {
            BlockUtil2.mc.playerController.processRightClickBlock(BlockUtil2.mc.player, BlockUtil2.mc.world, blockPos, enumFacing, vec3d, enumHand);
        }
        BlockUtil2.mc.player.swingArm(EnumHand.MAIN_HAND);
        BlockUtil2.mc.rightClickDelayTimer = 4;
    }
    
    public static void placeBlocksss(final BlockPos blockPos, final EnumHand enumHand, final boolean b) {
        if (!BlockUtil2.mc.world.getBlockState(blockPos).getBlock().isReplaceable((IBlockAccess)BlockUtil2.mc.world, blockPos)) {
            return;
        }
        if (getPlaceableSide(blockPos) == null) {
            return;
        }
        clickBlock(blockPos, getPlaceableSide(blockPos), enumHand, b);
        BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketAnimation(enumHand));
    }
    
    public static BlockPos placeBlockSHGR(final BlockPos blockPos, final boolean b) {
        final Block getBlock = BlockUtil2.mc.world.getBlockState(blockPos).getBlock();
        if (!(getBlock instanceof BlockAir) && !(getBlock instanceof BlockLiquid)) {
            return null;
        }
        final EnumFacing placeableSide = getPlaceableSide(blockPos);
        if (placeableSide == null) {
            return null;
        }
        final BlockPos offset = blockPos.offset(placeableSide);
        final EnumFacing getOpposite = placeableSide.getOpposite();
        final Vec3d add = new Vec3d((Vec3i)offset).add(0.5, 0.5, 0.5).add(new Vec3d(getOpposite.getDirectionVec()).scale(0.5));
        if (b) {
            rightClickBlock3(offset, add, EnumHand.MAIN_HAND, getOpposite);
        }
        else {
            BlockUtil2.mc.playerController.processRightClickBlock(BlockUtil2.mc.player, BlockUtil2.mc.world, offset, getOpposite, add, EnumHand.MAIN_HAND);
            BlockUtil2.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
        return offset;
    }
    
    public static boolean canPlaceInPosition(final BlockPos blockPos, final boolean b, final boolean b2) {
        if (!BlockUtil2.mc.world.getBlockState(blockPos).getBlock().isReplaceable((IBlockAccess)BlockUtil2.mc.world, blockPos)) {
            return false;
        }
        if (b) {
            for (final Entity entity : BlockUtil2.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(blockPos))) {
                if (!(entity instanceof EntityItem)) {
                    if (entity instanceof EntityXPOrb) {
                        continue;
                    }
                    return false;
                }
            }
        }
        return !b2 || getPlaceableSide(blockPos) != null;
    }
    
    public static boolean isBlockUnSolid(final Block block) {
        return BlockUtil2.unSolidBlocks.contains(block);
    }
    
    public static List<EnumFacing> getPossibleSides(final BlockPos blockPos) {
        final ArrayList<EnumFacing> list = new ArrayList<EnumFacing>();
        for (final EnumFacing e : EnumFacing.values()) {
            final BlockPos offset = blockPos.offset(e);
            if (BlockUtil2.mc.world.getBlockState(offset).getBlock().canCollideCheck(BlockUtil2.mc.world.getBlockState(offset), false)) {
                if (!BlockUtil2.mc.world.getBlockState(offset).getMaterial().isReplaceable()) {
                    list.add(e);
                }
            }
        }
        return list;
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
        final Block getBlock = BlockUtil2.mc.world.getBlockState(offset).getBlock();
        if (!BlockUtil2.mc.player.isSneaking() && (BlockUtil2.blackList.contains(getBlock) || BlockUtil2.shulkerList.contains(getBlock))) {
            BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil2.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            BlockUtil2.mc.player.setSneaking(true);
            b4 = true;
        }
        if (b) {
            RotationUtil.faceVector(add, true);
        }
        rightClickBlock(offset, add, enumHand, getOpposite, b2);
        BlockUtil2.mc.player.swingArm(EnumHand.MAIN_HAND);
        BlockUtil2.mc.rightClickDelayTimer = 4;
        return b4 || b3;
    }
    
    public static boolean isBlockAboveEntitySolid(final Entity entity) {
        return entity != null && isBlockSolid(new BlockPos(entity.posX, entity.posY + 2.0, entity.posZ));
    }
    
    public static void rightClickBlock2(final BlockPos blockPos, final EnumFacing enumFacing, final boolean b) {
        final Vec3d add = new Vec3d((Vec3i)blockPos).add(0.5, 0.5, 0.5).add(new Vec3d(enumFacing.getDirectionVec()).scale(0.5));
        if (b) {
            rightClickBlock3(blockPos, add, EnumHand.MAIN_HAND, enumFacing);
        }
        else {
            BlockUtil2.mc.playerController.processRightClickBlock(BlockUtil2.mc.player, BlockUtil2.mc.world, blockPos, enumFacing, add, EnumHand.MAIN_HAND);
            BlockUtil2.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
    }
    
    public static boolean placeBlock2(final BlockPos blockPos, final EnumHand enumHand, final boolean b, final boolean b2, final boolean b3) {
        boolean b4 = false;
        final EnumFacing firstFacing = getFirstFacing(blockPos);
        if (firstFacing == null) {
            return b3;
        }
        final BlockPos offset = blockPos.offset(firstFacing);
        final EnumFacing getOpposite = firstFacing.getOpposite();
        final Vec3d add = new Vec3d((Vec3i)offset).add(0.5, 0.5, 0.5).add(new Vec3d(getOpposite.getDirectionVec()).scale(0.5));
        if (!BlockUtil2.mc.player.isSneaking() && b3) {
            BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil2.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            BlockUtil2.mc.player.setSneaking(true);
            b4 = true;
        }
        rightClickBlock(offset, add, enumHand, getOpposite, b2);
        BlockUtil2.mc.player.swingArm(EnumHand.MAIN_HAND);
        BlockUtil2.mc.rightClickDelayTimer = 4;
        if (!BlockUtil2.mc.player.isSneaking() && b3) {
            BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil2.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
            BlockUtil2.mc.player.setSneaking(false);
            b4 = false;
        }
        return b4 || b3;
    }
    
    public static boolean placeBlock(final BlockPos blockPos, final EnumHand enumHand, final boolean b, final boolean b2, final boolean b3, final boolean b4) {
        boolean b5 = false;
        final EnumFacing calculateSide = calculateSide(blockPos);
        if (calculateSide == null) {
            return b4;
        }
        final BlockPos offset = blockPos.offset(calculateSide);
        final EnumFacing getOpposite = calculateSide.getOpposite();
        final Vec3d add = new Vec3d((Vec3i)offset).add(0.5, 0.5, 0.5).add(new Vec3d(getOpposite.getDirectionVec()).scale(0.5));
        final Block getBlock = BlockUtil2.mc.world.getBlockState(offset).getBlock();
        final float n = (float)(add.x - blockPos.getX());
        final float n2 = (float)(add.y - blockPos.getY());
        final float n3 = (float)(add.z - blockPos.getZ());
        if (!BlockUtil2.mc.player.isSneaking() && (BlockUtil2.blackList.contains(getBlock) || BlockUtil2.shulkerList.contains(getBlock))) {
            BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil2.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            BlockUtil2.mc.player.setSneaking(true);
            b5 = true;
        }
        if (b) {
            RotationUtil.faceVector(add, true);
        }
        if (b3) {
            BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(offset, getOpposite, enumHand, n, n2, n3));
        }
        BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil2.mc.player, CPacketEntityAction.Action.START_SNEAKING));
        if (TestUtil.isBlockEmpty(blockPos)) {
            placeClient(offset, enumHand, getOpposite, (float)add.x, (float)add.y, (float)add.z);
            BlockUtil2.mc.playerController.processRightClickBlock(BlockUtil2.mc.player, BlockUtil2.mc.world, offset, getOpposite, add, enumHand);
            BlockUtil2.mc.playerController.processRightClickBlock(BlockUtil2.mc.player, BlockUtil2.mc.world, offset, getOpposite, add, enumHand);
            final EnumActionResult success = EnumActionResult.SUCCESS;
            rightClickBlock(offset, add, enumHand, getOpposite, b2);
            BlockUtil2.mc.playerController.processRightClickBlock(BlockUtil2.mc.player, BlockUtil2.mc.world, offset, getOpposite, add, enumHand);
        }
        else {
            placeClient(offset, enumHand, getOpposite, (float)add.x, (float)add.y, (float)add.z);
            BlockUtil2.mc.playerController.processRightClickBlock(BlockUtil2.mc.player, BlockUtil2.mc.world, offset, getOpposite, add, enumHand);
            BlockUtil2.mc.playerController.processRightClickBlock(BlockUtil2.mc.player, BlockUtil2.mc.world, offset, getOpposite, add, enumHand);
            final EnumActionResult success2 = EnumActionResult.SUCCESS;
            rightClickBlock(offset, add, enumHand, getOpposite, b2);
            BlockUtil2.mc.playerController.processRightClickBlock(BlockUtil2.mc.player, BlockUtil2.mc.world, offset, getOpposite, add, enumHand);
        }
        BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil2.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
        BlockUtil2.mc.player.swingArm(EnumHand.MAIN_HAND);
        return b5 || b4;
    }
    
    public static Vec3d[] getOffsets(final int n, final boolean b) {
        final List<Vec3d> offsetList = getOffsetList(n, b);
        final Vec3d[] array = new Vec3d[offsetList.size()];
        return offsetList.toArray(new Vec3d[0]);
    }
    
    public static Vec3d[] getHelpingBlocks(final Vec3d vec3d) {
        return new Vec3d[] { new Vec3d(vec3d.x, vec3d.y - 1.0, vec3d.z), new Vec3d((vec3d.x != 0.0) ? (vec3d.x * 2.0) : vec3d.x, vec3d.y, (vec3d.x != 0.0) ? vec3d.z : (vec3d.z * 2.0)), new Vec3d((vec3d.x == 0.0) ? (vec3d.x + 1.0) : vec3d.x, vec3d.y, (vec3d.x == 0.0) ? vec3d.z : (vec3d.z + 1.0)), new Vec3d((vec3d.x == 0.0) ? (vec3d.x - 1.0) : vec3d.x, vec3d.y, (vec3d.x == 0.0) ? vec3d.z : (vec3d.z - 1.0)), new Vec3d(vec3d.x, vec3d.y + 1.0, vec3d.z) };
    }
    
    public static List<BlockPos> possiblePlacePositions(final float n) {
        final NonNullList create = NonNullList.create();
        create.addAll((Collection)getSphere(EntityUtil.getPlayerPos((EntityPlayer)BlockUtil2.mc.player), n, (int)n, false, true, 0).stream().filter((Predicate<? super Object>)BlockUtil2::canPlaceCrystal).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList()));
        return (List<BlockPos>)create;
    }
    
    public static boolean isBlockNotEmpty(final BlockPos blockPos) {
        if (BlockUtil2.emptyBlocks.contains(BlockUtil2.mc.world.getBlockState(blockPos).getBlock())) {
            final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos);
            final Iterator<Entity> iterator = BlockUtil2.mc.world.loadedEntityList.iterator();
            while (iterator.hasNext()) {
                final Entity entity;
                if ((entity = iterator.next()) instanceof EntityLivingBase && axisAlignedBB.intersects(entity.getEntityBoundingBox())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    public static void rightClickBlock1(final BlockPos blockPos, final EnumFacing enumFacing, final Vec3d vec3d, final boolean b) {
        final Vec3d add = new Vec3d((Vec3i)blockPos).add(vec3d).add(new Vec3d(enumFacing.getDirectionVec()).scale(0.5));
        if (b) {
            rightClickBlock3(blockPos, add, EnumHand.MAIN_HAND, enumFacing);
        }
        else {
            BlockUtil2.mc.playerController.processRightClickBlock(BlockUtil2.mc.player, BlockUtil2.mc.world, blockPos, enumFacing, add, EnumHand.MAIN_HAND);
            BlockUtil2.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
    }
    
    public static Boolean isPosInFov(final BlockPos blockPos) {
        final int direction4D = RotationUtil.getDirection4D();
        if (direction4D == 0 && blockPos.getZ() - BlockUtil2.mc.player.getPositionVector().z < 0.0) {
            return false;
        }
        if (direction4D == 1 && blockPos.getX() - BlockUtil2.mc.player.getPositionVector().x > 0.0) {
            return false;
        }
        if (direction4D == 2 && blockPos.getZ() - BlockUtil2.mc.player.getPositionVector().z > 0.0) {
            return false;
        }
        return direction4D != 3 || blockPos.getX() - BlockUtil2.mc.player.getPositionVector().x >= 0.0;
    }
    
    public static List<BlockPos> getCock(final float n, final boolean b) {
        final ArrayList<BlockPos> list = new ArrayList<BlockPos>();
        final BlockPos blockPos = new BlockPos(BlockUtil.mc.player.getPositionVector());
        final int getX = blockPos.getX();
        final int getY = blockPos.getY();
        final int getZ = blockPos.getZ();
        final int n2 = (int)n;
        for (int n3 = getX - n2; n3 <= getX + n; ++n3) {
            for (int n4 = getZ - n2; n4 <= getZ + n; ++n4) {
                for (int n5 = getY - n2; n5 < getY + n; ++n5) {
                    final BlockPos e;
                    if ((getX - n3) * (getX - n3) + (getZ - n4) * (getZ - n4) + (getY - n5) * (getY - n5) < (double)(n * n) && (BlockUtil.mc.world.getBlockState(e = new BlockPos(n3, n5, n4)).getBlock() != Blocks.AIR || !b)) {
                        list.add(e);
                    }
                }
            }
        }
        return list;
    }
    
    public static List<BlockPos> possiblePlacePosition(final float n, final boolean b, final boolean b2) {
        final NonNullList create = NonNullList.create();
        create.addAll((Collection)getSphere(EntityUtil.getPlayerPos((EntityPlayer)BlockUtil2.mc.player), n, (int)n, false, true, 0).stream().filter(blockPos -> canPlaceCrystal(blockPos, b, b2)).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList()));
        return (List<BlockPos>)create;
    }
    
    public static int findObiInHotbar() {
        for (int i = 0; i < 9; ++i) {
            final ItemStack getStackInSlot = BlockUtil2.mc.player.inventory.getStackInSlot(i);
            if (getStackInSlot != ItemStack.EMPTY) {
                if (getStackInSlot.getItem() instanceof ItemBlock) {
                    final Block getBlock = ((ItemBlock)getStackInSlot.getItem()).getBlock();
                    if (getBlock instanceof BlockEnderChest) {
                        return i;
                    }
                    if (getBlock instanceof BlockObsidian) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }
    
    public static List<BlockPos> getSphere(final BlockPos blockPos, final float n, final int n2, final boolean b, final boolean b2, final int n3) {
        final ArrayList<BlockPos> list = new ArrayList<BlockPos>();
        final int getX = blockPos.getX();
        final int getY = blockPos.getY();
        final int getZ = blockPos.getZ();
        for (int n4 = getX - (int)n; n4 <= getX + n; ++n4) {
            for (int n5 = getZ - (int)n; n5 <= getZ + n; ++n5) {
                int n6 = b2 ? (getY - (int)n) : getY;
                while (true) {
                    final float n7 = (float)n6;
                    float n9 = 0.0f;
                    if (b2) {
                        final float n8 = getY + n;
                    }
                    else {
                        n9 = (float)(getY + n2);
                    }
                    if (n7 >= n9) {
                        break;
                    }
                    final double n10 = (getX - n4) * (getX - n4) + (getZ - n5) * (getZ - n5) + (b2 ? ((getY - n6) * (getY - n6)) : 0);
                    if (n10 < n * n && (!b || n10 >= (n - 1.0f) * (n - 1.0f))) {
                        list.add(new BlockPos(n4, n6 + n3, n5));
                    }
                    ++n6;
                }
            }
        }
        return list;
    }
    
    static {
        BlockUtil2.rightclickableBlocks = Arrays.asList((Block)Blocks.CHEST, Blocks.TRAPPED_CHEST, Blocks.ENDER_CHEST, Blocks.WHITE_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.SILVER_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.ANVIL, Blocks.WOODEN_BUTTON, Blocks.STONE_BUTTON, (Block)Blocks.UNPOWERED_COMPARATOR, (Block)Blocks.UNPOWERED_REPEATER, (Block)Blocks.POWERED_REPEATER, (Block)Blocks.POWERED_COMPARATOR, Blocks.OAK_FENCE_GATE, Blocks.SPRUCE_FENCE_GATE, Blocks.BIRCH_FENCE_GATE, Blocks.JUNGLE_FENCE_GATE, Blocks.DARK_OAK_FENCE_GATE, Blocks.ACACIA_FENCE_GATE, Blocks.BREWING_STAND, Blocks.DISPENSER, Blocks.DROPPER, Blocks.LEVER, Blocks.NOTEBLOCK, Blocks.JUKEBOX, (Block)Blocks.BEACON, Blocks.BED, Blocks.FURNACE, (Block)Blocks.OAK_DOOR, (Block)Blocks.SPRUCE_DOOR, (Block)Blocks.BIRCH_DOOR, (Block)Blocks.JUNGLE_DOOR, (Block)Blocks.ACACIA_DOOR, (Block)Blocks.DARK_OAK_DOOR, Blocks.CAKE, Blocks.ENCHANTING_TABLE, Blocks.DRAGON_EGG, (Block)Blocks.HOPPER, Blocks.REPEATING_COMMAND_BLOCK, Blocks.COMMAND_BLOCK, Blocks.CHAIN_COMMAND_BLOCK, Blocks.CRAFTING_TABLE);
        blackList = Arrays.asList(Blocks.ENDER_CHEST, (Block)Blocks.CHEST, Blocks.TRAPPED_CHEST, Blocks.CRAFTING_TABLE, Blocks.ANVIL, Blocks.BREWING_STAND, (Block)Blocks.HOPPER, Blocks.DROPPER, Blocks.DISPENSER, Blocks.TRAPDOOR, Blocks.ENCHANTING_TABLE);
        shulkerList = Arrays.asList(Blocks.WHITE_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.SILVER_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.BLACK_SHULKER_BOX);
        unSafeBlocks = Arrays.asList(Blocks.OBSIDIAN, Blocks.BEDROCK, Blocks.ENDER_CHEST, Blocks.ANVIL);
        BlockUtil2.unSolidBlocks = Arrays.asList((Block)Blocks.FLOWING_LAVA, Blocks.FLOWER_POT, Blocks.SNOW, Blocks.CARPET, Blocks.END_ROD, (Block)Blocks.SKULL, Blocks.FLOWER_POT, Blocks.TRIPWIRE, (Block)Blocks.TRIPWIRE_HOOK, Blocks.WOODEN_BUTTON, Blocks.LEVER, Blocks.STONE_BUTTON, Blocks.LADDER, (Block)Blocks.UNPOWERED_COMPARATOR, (Block)Blocks.POWERED_COMPARATOR, (Block)Blocks.UNPOWERED_REPEATER, (Block)Blocks.POWERED_REPEATER, Blocks.UNLIT_REDSTONE_TORCH, Blocks.REDSTONE_TORCH, (Block)Blocks.REDSTONE_WIRE, Blocks.AIR, (Block)Blocks.PORTAL, Blocks.END_PORTAL, (Block)Blocks.WATER, (Block)Blocks.FLOWING_WATER, (Block)Blocks.LAVA, (Block)Blocks.FLOWING_LAVA, Blocks.SAPLING, (Block)Blocks.RED_FLOWER, (Block)Blocks.YELLOW_FLOWER, (Block)Blocks.BROWN_MUSHROOM, (Block)Blocks.RED_MUSHROOM, Blocks.WHEAT, Blocks.CARROTS, Blocks.POTATOES, Blocks.BEETROOTS, (Block)Blocks.REEDS, Blocks.PUMPKIN_STEM, Blocks.MELON_STEM, Blocks.WATERLILY, Blocks.NETHER_WART, Blocks.COCOA, Blocks.CHORUS_FLOWER, Blocks.CHORUS_PLANT, (Block)Blocks.TALLGRASS, (Block)Blocks.DEADBUSH, Blocks.VINE, (Block)Blocks.FIRE, Blocks.RAIL, Blocks.ACTIVATOR_RAIL, Blocks.DETECTOR_RAIL, Blocks.GOLDEN_RAIL, Blocks.TORCH);
        BlockUtil2.emptyBlocks = Arrays.asList(Blocks.AIR, (Block)Blocks.FLOWING_LAVA, (Block)Blocks.LAVA, (Block)Blocks.FLOWING_WATER, (Block)Blocks.WATER, Blocks.VINE, Blocks.SNOW_LAYER, (Block)Blocks.TALLGRASS, (Block)Blocks.FIRE);
    }
    
    public static boolean canPlaceCrystal(final BlockPos blockPos, final boolean b, final boolean b2) {
        final BlockPos add = blockPos.add(0, 1, 0);
        final BlockPos add2 = blockPos.add(0, 2, 0);
        try {
            if (BlockUtil2.mc.world.getBlockState(blockPos).getBlock() != Blocks.BEDROCK && BlockUtil2.mc.world.getBlockState(blockPos).getBlock() != Blocks.OBSIDIAN) {
                return false;
            }
            if ((!b2 && BlockUtil2.mc.world.getBlockState(add2).getBlock() != Blocks.AIR) || BlockUtil2.mc.world.getBlockState(add).getBlock() != Blocks.AIR) {
                return false;
            }
            for (final Entity entity : BlockUtil2.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(add))) {
                if (!entity.isDead) {
                    if (b && entity instanceof EntityEnderCrystal) {
                        continue;
                    }
                    return false;
                }
            }
            if (!b2) {
                for (final Entity entity2 : BlockUtil2.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(add2))) {
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
    
    public static Vec3d posToVec3d(final BlockPos blockPos) {
        return new Vec3d((Vec3i)blockPos);
    }
    
    public static boolean canPlaceCrystal(final BlockPos blockPos, final boolean b) {
        final BlockPos add = blockPos.add(0, 1, 0);
        final BlockPos add2 = blockPos.add(0, 2, 0);
        try {
            if (BlockUtil2.mc.world.getBlockState(blockPos).getBlock() != Blocks.BEDROCK && BlockUtil2.mc.world.getBlockState(blockPos).getBlock() != Blocks.OBSIDIAN) {
                return false;
            }
            if (BlockUtil2.mc.world.getBlockState(add).getBlock() != Blocks.AIR || BlockUtil2.mc.world.getBlockState(add2).getBlock() != Blocks.AIR) {
                return false;
            }
            if (!b) {
                return BlockUtil2.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(add)).isEmpty() && BlockUtil2.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(add2)).isEmpty();
            }
            final Iterator iterator = BlockUtil2.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(add)).iterator();
            while (iterator.hasNext()) {
                if (iterator.next() instanceof EntityEnderCrystal) {
                    continue;
                }
                return false;
            }
            final Iterator iterator2 = BlockUtil2.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(add2)).iterator();
            while (iterator2.hasNext()) {
                if (iterator2.next() instanceof EntityEnderCrystal) {
                    continue;
                }
                return false;
            }
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }
    
    public static EnumFacing getRayTraceFacing(final BlockPos blockPos) {
        final RayTraceResult rayTraceBlocks = BlockUtil2.mc.world.rayTraceBlocks(new Vec3d(BlockUtil2.mc.player.posX, BlockUtil2.mc.player.posY + BlockUtil2.mc.player.getEyeHeight(), BlockUtil2.mc.player.posZ), new Vec3d(blockPos.getX() + 0.5, blockPos.getX() - 0.5, blockPos.getX() + 0.5));
        if (rayTraceBlocks == null || rayTraceBlocks.sideHit == null) {
            return EnumFacing.UP;
        }
        return rayTraceBlocks.sideHit;
    }
    
    public static EnumFacing getDirection(final BlockPos blockPos) {
        final RayTraceResult rayTraceBlocks = BlockUtil2.mc.world.rayTraceBlocks(new Vec3d(BlockUtil2.mc.player.posX, BlockUtil2.mc.player.posY + BlockUtil2.mc.player.eyeHeight, BlockUtil2.mc.player.posZ), new Vec3d((Vec3i)blockPos));
        if (rayTraceBlocks == null) {
            return EnumFacing.UP;
        }
        return rayTraceBlocks.sideHit;
    }
    
    public static BlockPos[] toBlockPos(final Vec3d[] array) {
        final BlockPos[] array2 = new BlockPos[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = new BlockPos(array[i]);
        }
        return array2;
    }
    
    public static Block getBlock(final BlockPos blockPos) {
        return getState(blockPos).getBlock();
    }
    
    public static boolean rayTracePlaceCheck(final BlockPos blockPos) {
        return rayTracePlaceCheck(blockPos, true);
    }
    
    private static IBlockState getState(final BlockPos blockPos) {
        return BlockUtil2.mc.world.getBlockState(blockPos);
    }
    
    public static BlockPos[] getTouchingBlocks(final BlockPos blockPos) {
        return new BlockPos[] { blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down() };
    }
    
    public static void placeBlockStopSneaking(final BlockPos blockPos, final EnumHand enumHand, final boolean b, final boolean b2, final boolean b3) {
        final boolean placeBlockSmartRotate = placeBlockSmartRotate(blockPos, enumHand, b, b2, b3);
        if (!b3 && placeBlockSmartRotate) {
            BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil2.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
        }
    }
    
    public static void placeClient(BlockPos offset, final EnumHand enumHand, final EnumFacing enumFacing, final float n, final float n2, final float n3) {
        final ItemStack getHeldItemMainhand = BlockUtil2.mc.player.getHeldItemMainhand();
        if (getHeldItemMainhand.getItem() instanceof ItemBlock) {
            final ItemBlock itemBlock = (ItemBlock)getHeldItemMainhand.getItem();
            final Block getBlock = itemBlock.getBlock();
            if (!BlockUtil2.mc.world.getBlockState(offset).getBlock().isReplaceable((IBlockAccess)BlockUtil2.mc.world, offset)) {
                offset = offset.offset(enumFacing);
            }
            if (!getHeldItemMainhand.isEmpty() && BlockUtil2.mc.player.canPlayerEdit(offset, enumFacing, getHeldItemMainhand) && BlockUtil2.mc.world.mayPlace(getBlock, offset, false, enumFacing, (Entity)null) && itemBlock.placeBlockAt(getHeldItemMainhand, (EntityPlayer)BlockUtil2.mc.player, (World)BlockUtil2.mc.world, offset, enumFacing, n, n2, n3, getBlock.getStateForPlacement((World)BlockUtil2.mc.world, offset, enumFacing, n, n2, n3, itemBlock.getMetadata(getHeldItemMainhand.getMetadata()), (EntityLivingBase)BlockUtil2.mc.player, enumHand))) {
                final IBlockState getBlockState = BlockUtil2.mc.world.getBlockState(offset);
                final SoundType soundType = getBlockState.getBlock().getSoundType(getBlockState, (World)BlockUtil2.mc.world, offset, (Entity)BlockUtil2.mc.player);
                BlockUtil2.mc.world.playSound((EntityPlayer)BlockUtil2.mc.player, offset, soundType.getPlaceSound(), SoundCategory.BLOCKS, (soundType.getVolume() + 1.0f) / 2.0f, soundType.getPitch() * 0.8f);
                if (!BlockUtil2.mc.player.isCreative()) {
                    getHeldItemMainhand.shrink(1);
                }
            }
        }
    }
    
    public static boolean placeBlockSmartRotate(final BlockPos blockPos, final EnumHand enumHand, final boolean b, final boolean b2, final boolean b3) {
        boolean b4 = false;
        final EnumFacing firstFacing = getFirstFacing(blockPos);
        Command.sendMessage(firstFacing.toString());
        if (firstFacing == null) {
            return b3;
        }
        final BlockPos offset = blockPos.offset(firstFacing);
        final EnumFacing getOpposite = firstFacing.getOpposite();
        final Vec3d add = new Vec3d((Vec3i)offset).add(0.5, 0.5, 0.5).add(new Vec3d(getOpposite.getDirectionVec()).scale(0.5));
        final Block getBlock = BlockUtil2.mc.world.getBlockState(offset).getBlock();
        if (!BlockUtil2.mc.player.isSneaking() && (BlockUtil2.blackList.contains(getBlock) || BlockUtil2.shulkerList.contains(getBlock))) {
            BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil2.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            b4 = true;
        }
        if (b) {
            LuigiHack.rotationManager.lookAtVec3d(add);
        }
        rightClickBlock(offset, add, enumHand, getOpposite, b2);
        BlockUtil2.mc.player.swingArm(EnumHand.MAIN_HAND);
        BlockUtil2.mc.rightClickDelayTimer = 4;
        return b4 || b3;
    }
    
    public static void placeBlockss(final BlockPos blockPos, final boolean b, final boolean b2, final boolean b3) {
        for (final EnumFacing enumFacing : EnumFacing.values()) {
            if (!BlockUtil2.mc.world.getBlockState(blockPos.offset(enumFacing)).getBlock().equals(Blocks.AIR) && !isIntercepted(blockPos)) {
                if (b2) {
                    BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos.offset(enumFacing), enumFacing.getOpposite(), EnumHand.MAIN_HAND, Float.intBitsToFloat(Float.floatToIntBits(2.7f)), Float.intBitsToFloat(Float.floatToIntBits(3.8f)), Float.intBitsToFloat(Float.floatToIntBits(30.0f))));
                }
                else {
                    BlockUtil2.mc.playerController.processRightClickBlock(BlockUtil2.mc.player, BlockUtil2.mc.world, blockPos.offset(enumFacing), enumFacing.getOpposite(), new Vec3d((Vec3i)blockPos), EnumHand.MAIN_HAND);
                }
                if (b) {
                    BlockUtil2.mc.player.swingArm(EnumHand.MAIN_HAND);
                }
                if (b3) {
                    RotationUtil.faceVector(new Vec3d((Vec3i)blockPos), true);
                }
                return;
            }
        }
    }
    
    public static Vec3d[] convertVec3ds(final EntityPlayer entityPlayer, final Vec3d[] array) {
        return convertVec3ds(entityPlayer.getPositionVector(), array);
    }
    
    public static void debugPos(final String str, final BlockPos blockPos) {
        Command.sendMessage(String.valueOf(new StringBuilder().append(str).append(blockPos.getX()).append("x, ").append(blockPos.getY()).append("y, ").append(blockPos.getZ()).append("z")));
    }
    
    public static boolean isInterceptedByOther(final BlockPos blockPos) {
        for (final Entity entity : BlockUtil2.mc.world.loadedEntityList) {
            if (!entity.equals((Object)BlockUtil2.mc.player)) {
                if (!new AxisAlignedBB(blockPos).intersects(entity.getEntityBoundingBox())) {
                    continue;
                }
                return true;
            }
        }
        return false;
    }
    
    public static Vec3d getEyesPos() {
        return new Vec3d(BlockUtil2.mc.player.posX, BlockUtil2.mc.player.posY + BlockUtil2.mc.player.getEyeHeight(), BlockUtil2.mc.player.posZ);
    }
    
    public static void clickBlock(final BlockPos blockPos, final EnumFacing enumFacing, final EnumHand enumHand, final boolean b) {
        if (b) {
            BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos.offset(enumFacing), enumFacing.getOpposite(), enumHand, Float.intBitsToFloat(Float.floatToIntBits(17.0f)), Float.intBitsToFloat(Float.floatToIntBits(26.0f)), Float.intBitsToFloat(Float.floatToIntBits(3.0f))));
        }
        else {
            BlockUtil2.mc.playerController.processRightClickBlock(BlockUtil2.mc.player, BlockUtil2.mc.world, blockPos.offset(enumFacing), enumFacing.getOpposite(), new Vec3d((Vec3i)blockPos), enumHand);
        }
    }
    
    public static boolean isBlocking(final BlockPos blockPos, final EntityPlayer entityPlayer) {
        return entityPlayer.getEntityBoundingBox().expand(-0.0625, -0.0625, -0.0625).intersects(new AxisAlignedBB(blockPos));
    }
    
    public static boolean rayTracePlaceCheck(final BlockPos blockPos, final boolean b) {
        return rayTracePlaceCheck(blockPos, b, 1.0f);
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
    
    public static boolean canBlockBeSeen(final double n, final double n2, final double n3) {
        return BlockUtil.mc.world.rayTraceBlocks(new Vec3d(BlockUtil.mc.player.posX, BlockUtil.mc.player.posY + BlockUtil.mc.player.getEyeHeight(), BlockUtil.mc.player.posZ), new Vec3d(n, n2 + 1.7, n3), false, true, false) == null;
    }
    
    public static boolean canPlaceCrystal(final BlockPos blockPos, final boolean b, final boolean b2, final boolean b3) {
        if (CrystalUtil.mc.world.getBlockState(blockPos).getBlock() != Blocks.BEDROCK && CrystalUtil.mc.world.getBlockState(blockPos).getBlock() != Blocks.OBSIDIAN) {
            return false;
        }
        if (CrystalUtil.mc.world.getBlockState(blockPos.add(0, 1, 0)).getBlock() != Blocks.AIR || (!b2 && CrystalUtil.mc.world.getBlockState(blockPos.add(0, 2, 0)).getBlock() != Blocks.AIR)) {
            return false;
        }
        if (b) {
            return CrystalUtil.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(blockPos.add(0, 1, 0))).isEmpty() && !b2 && CrystalUtil.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(blockPos.add(0, 2, 0))).isEmpty();
        }
        final Iterator<Entity> iterator = CrystalUtil.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(blockPos.add(0, 1, 0))).iterator();
        while (iterator.hasNext()) {
            if (iterator.next() instanceof EntityEnderCrystal) {
                continue;
            }
            return false;
        }
        if (!b2) {
            for (final Entity entity : CrystalUtil.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(blockPos.add(0, 2, 0)))) {
                if (!(entity instanceof EntityEnderCrystal)) {
                    if (b2 && entity instanceof EntityPlayer) {
                        continue;
                    }
                    return false;
                }
            }
        }
        return true;
    }
    
    public static boolean isPositionPlaceable(final BlockPos blockPos, final boolean b, final double n) {
        final Block getBlock = BlockUtil2.mc.world.getBlockState(blockPos).getBlock();
        if (!(getBlock instanceof BlockAir) && !(getBlock instanceof BlockLiquid) && !(getBlock instanceof BlockTallGrass) && !(getBlock instanceof BlockFire) && !(getBlock instanceof BlockDeadBush) && !(getBlock instanceof BlockSnow)) {
            return false;
        }
        if (b) {
            for (final Entity entity : BlockUtil2.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(blockPos))) {
                if (BlockUtil2.mc.player.getDistance(entity) <= n && !(entity instanceof EntityItem)) {
                    if (entity instanceof EntityXPOrb) {
                        continue;
                    }
                    return false;
                }
            }
        }
        return true;
    }
    
    public static BlockPos placeBlockS(final BlockPos blockPos, final boolean b) {
        final Block getBlock = BlockUtil2.mc.world.getBlockState(blockPos).getBlock();
        if (!(getBlock instanceof BlockAir) && !(getBlock instanceof BlockLiquid)) {
            return null;
        }
        final EnumFacing placeableSide = getPlaceableSide(blockPos);
        if (placeableSide == null) {
            return null;
        }
        final BlockPos offset = blockPos.offset(placeableSide);
        final EnumFacing getOpposite = placeableSide.getOpposite();
        final Vec3d add = new Vec3d((Vec3i)offset).add(0.5, 0.5, 0.5).add(new Vec3d(getOpposite.getDirectionVec()).scale(0.5));
        if (b) {
            rightClickBlock3(offset, add, EnumHand.MAIN_HAND, getOpposite);
        }
        else {
            BlockUtil2.mc.playerController.processRightClickBlock(BlockUtil2.mc.player, BlockUtil2.mc.world, offset, getOpposite, add, EnumHand.MAIN_HAND);
            BlockUtil2.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
        return offset;
    }
    
    public static float getBlockDamage(final BlockPos blockPos) {
        try {
            final Field field = ReflectionHelper.findField((Class)RenderGlobal.class, new String[] { "damagedBlocks", "damagedBlocks" });
            field.setAccessible(true);
            for (final DestroyBlockProgress destroyBlockProgress : ((HashMap)field.get(Minecraft.getMinecraft().renderGlobal)).values()) {
                if (destroyBlockProgress.getPosition().equals((Object)blockPos) && destroyBlockProgress.getPartialBlockDamage() >= 0 && destroyBlockProgress.getPartialBlockDamage() <= 10) {
                    return destroyBlockProgress.getPartialBlockDamage() / 10.0f;
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0.0f;
    }
    
    public static void faceVectorPacketInstant(final Vec3d vec3d) {
        final float[] neededRotations2 = getNeededRotations2(vec3d);
        BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(neededRotations2[0], neededRotations2[1], BlockUtil2.mc.player.onGround));
    }
    
    public static EnumFacing calculateSide(final BlockPos blockPos) {
        for (final EnumFacing enumFacing : EnumFacing.values()) {
            final IBlockState getBlockState = BlockUtil2.mc.world.getBlockState(blockPos.offset(enumFacing));
            if (getBlockState.getBlock().onBlockActivated((World)BlockUtil2.mc.world, blockPos, getBlockState, (EntityPlayer)BlockUtil2.mc.player, EnumHand.MAIN_HAND, enumFacing, 0.0f, 0.0f, 0.0f)) {
                BlockUtil2.mc.getConnection().sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil2.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            }
            if (getBlockState.getBlock().canCollideCheck(getBlockState, false) && !getBlockState.getMaterial().isReplaceable()) {
                return enumFacing;
            }
        }
        return null;
    }
    
    public static boolean isValidBlock(final BlockPos blockPos) {
        final Block getBlock = BlockUtil2.mc.world.getBlockState(blockPos).getBlock();
        return !(getBlock instanceof BlockLiquid) && getBlock.getMaterial((IBlockState)null) != Material.AIR;
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
    
    public static boolean isBlockUnSolid(final BlockPos blockPos) {
        return isBlockUnSolid(BlockUtil2.mc.world.getBlockState(blockPos).getBlock());
    }
    
    public static ValidResult valid(final BlockPos blockPos) {
        if (!BlockUtil2.mc.world.checkNoEntityCollision(new AxisAlignedBB(blockPos))) {
            return ValidResult.NoEntityCollision;
        }
        if (!checkForNeighbours(blockPos)) {
            return ValidResult.NoNeighbors;
        }
        if (BlockUtil2.mc.world.getBlockState(blockPos).getBlock() == Blocks.AIR) {
            final BlockPos[] array = { blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.up(), blockPos.down() };
            for (int length = array.length, i = 0; i < length; ++i) {
                if (BlockUtil2.mc.world.getBlockState(array[i]).getBlock() != Blocks.AIR) {
                    final EnumFacing[] values = EnumFacing.values();
                    for (int length2 = values.length, j = 0; j < length2; ++j) {
                        final BlockPos offset = blockPos.offset(values[j]);
                        if (BlockUtil2.mc.world.getBlockState(offset).getBlock().canCollideCheck(BlockUtil2.mc.world.getBlockState(offset), false)) {
                            return ValidResult.Ok;
                        }
                    }
                }
            }
            return ValidResult.NoNeighbors;
        }
        return ValidResult.AlreadyBlockThere;
    }
    
    public static EnumFacing getFirstFacing(final BlockPos blockPos) {
        final Iterator<EnumFacing> iterator = getPossibleSides(blockPos).iterator();
        if (iterator.hasNext()) {
            return iterator.next();
        }
        return null;
    }
    
    public static boolean isBlockAir(final BlockPos blockPos) {
        final Block block = getBlock(blockPos);
        return block instanceof BlockAir || block instanceof BlockLiquid;
    }
    
    public static void placeCrystalOnBlock(final BlockPos blockPos, final EnumHand enumHand) {
        final RayTraceResult rayTraceBlocks = BlockUtil2.mc.world.rayTraceBlocks(new Vec3d(BlockUtil2.mc.player.posX, BlockUtil2.mc.player.posY + BlockUtil2.mc.player.getEyeHeight(), BlockUtil2.mc.player.posZ), new Vec3d(blockPos.getX() + 0.5, blockPos.getY() - 0.5, blockPos.getZ() + 0.5));
        BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos, (rayTraceBlocks == null || rayTraceBlocks.sideHit == null) ? EnumFacing.UP : rayTraceBlocks.sideHit, enumHand, 0.0f, 0.0f, 0.0f));
    }
    
    public static boolean isIntercepted(final BlockPos blockPos) {
        for (final Entity entity : BlockUtil2.mc.world.loadedEntityList) {
            if (!(entity instanceof EntityItem) && !(entity instanceof EntityEnderCrystal)) {
                if (!new AxisAlignedBB(blockPos).intersects(entity.getEntityBoundingBox())) {
                    continue;
                }
                return true;
            }
        }
        return false;
    }
    
    public static boolean canRightClickBlock(final BlockPos blockPos) {
        return isBlockBreakable(blockPos) && !BlockUtil2.blackList.contains(getBlock(blockPos));
    }
    
    public static BlockPos getPlayerPos() {
        return new BlockPos(Math.floor(BlockUtil2.mc.player.posX), Math.floor(BlockUtil2.mc.player.posY), Math.floor(BlockUtil2.mc.player.posZ));
    }
    
    public static EnumFacing getPlaceableSide(final BlockPos blockPos) {
        for (final EnumFacing enumFacing : EnumFacing.values()) {
            final BlockPos offset = blockPos.offset(enumFacing);
            if (BlockUtil2.mc.world.getBlockState(offset).getBlock().canCollideCheck(BlockUtil2.mc.world.getBlockState(offset), false) && !BlockUtil2.mc.world.getBlockState(offset).getMaterial().isReplaceable()) {
                return enumFacing;
            }
        }
        return null;
    }
    
    public static boolean canBreak(final BlockPos blockPos) {
        final IBlockState getBlockState = BlockUtil2.mc.world.getBlockState(blockPos);
        return getBlockState.getBlock().getBlockHardness(getBlockState, (World)BlockUtil2.mc.world, blockPos) != -1.0f;
    }
    
    public enum ValidResult
    {
        AlreadyBlockThere, 
        NoEntityCollision, 
        Ok, 
        NoNeighbors;
    }
}
