//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util.skid.cr;

import net.minecraft.client.*;
import net.minecraft.item.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import net.minecraft.block.*;
import net.minecraft.util.*;

public class BlockUtilsCr
{
    public /* synthetic */ int a;
    public /* synthetic */ double roty;
    public /* synthetic */ double rotx;
    public /* synthetic */ EnumFacing f;
    public /* synthetic */ double dist;
    protected static /* synthetic */ Minecraft mc;
    public /* synthetic */ BlockPos pos;
    
    public static double getDirection2D(final double n, final double n2) {
        double n3;
        if (n2 == 0.0) {
            n3 = ((n > 0.0) ? 90.0 : -90.0);
        }
        else {
            n3 = Math.atan(n / n2) * 57.2957796;
            if (n2 < 0.0) {
                double n4 = 0.0;
                if (n > 0.0) {
                    n4 = n3 + 180.0;
                }
                else if (n < 0.0) {}
                n3 = n4;
            }
        }
        return n3;
    }
    
    public void doBreak() {
        BlockUtilsCr.mc.playerController.onPlayerDamageBlock(new BlockPos(this.pos.getX() - this.f.getDirectionVec().getX(), this.pos.getY() - this.f.getDirectionVec().getY(), this.pos.getZ() - this.f.getDirectionVec().getZ()), this.f);
    }
    
    protected final Vec3d getVectorForRotation(final double n, final double n2) {
        final float cos = MathHelper.cos((float)(-n2 * 0.01745329238474369 - 3.1415927410125732));
        final float sin = MathHelper.sin((float)(-n2 * 0.01745329238474369 - 3.1415927410125732));
        final float n3 = -MathHelper.cos((float)(-n * 0.01745329238474369));
        return new Vec3d((double)(sin * n3), (double)MathHelper.sin((float)(-n * 0.01745329238474369)), (double)(cos * n3));
    }
    
    public BlockUtilsCr(final BlockPos pos, final int a, final EnumFacing f, final double dist) {
        this.pos = pos;
        this.a = a;
        this.f = f;
        this.dist = dist;
    }
    
    protected final Vec3d getVectorForRotation(final float n, final float n2) {
        final float cos = MathHelper.cos(-n2 * 0.017453292f - 3.1415927f);
        final float sin = MathHelper.sin(-n2 * 0.017453292f - 3.1415927f);
        final float n3 = -MathHelper.cos(-n * 0.017453292f);
        return new Vec3d((double)(sin * n3), (double)MathHelper.sin(-n * 0.017453292f), (double)(cos * n3));
    }
    
    public static BlockUtilsCr isPlaceable(final BlockPos blockPos, final double n, final boolean b) {
        final BlockUtilsCr blockUtilsCr = new BlockUtilsCr(blockPos, 0, null, n);
        if (!isAir(blockPos)) {
            return null;
        }
        if (!(BlockUtilsCr.mc.player.inventory.getCurrentItem().getItem() instanceof ItemBlock)) {
            return null;
        }
        final AxisAlignedBB getCollisionBoundingBox = ((ItemBlock)BlockUtilsCr.mc.player.inventory.getCurrentItem().getItem()).getBlock().getDefaultState().getCollisionBoundingBox((IBlockAccess)BlockUtilsCr.mc.world, blockPos);
        if (!isAir(blockPos) && BlockUtilsCr.mc.world.getBlockState(blockPos).getBlock() instanceof BlockLiquid) {
            if (BlockUtilsCr.mc.world.getBlockState(blockPos.offset(EnumFacing.UP)).getBlock() instanceof BlockLiquid) {
                blockUtilsCr.f = EnumFacing.DOWN;
                blockUtilsCr.pos.offset(EnumFacing.UP);
                return blockUtilsCr;
            }
            blockUtilsCr.f = EnumFacing.UP;
            blockUtilsCr.pos.offset(EnumFacing.DOWN);
            return blockUtilsCr;
        }
        else {
            final EnumFacing[] values = EnumFacing.values();
            final int length = values.length;
            int i = 0;
            while (i < length) {
                final EnumFacing f = values[i];
                if (isAir(new BlockPos(blockPos.getX() - f.getDirectionVec().getX(), blockPos.getY() - f.getDirectionVec().getY(), blockPos.getZ() - f.getDirectionVec().getZ()))) {
                    ++i;
                }
                else {
                    blockUtilsCr.f = f;
                    if (b && getCollisionBoundingBox != Block.NULL_AABB && !BlockUtilsCr.mc.world.checkNoEntityCollision(getCollisionBoundingBox.offset(blockPos), (Entity)null)) {
                        return null;
                    }
                    return blockUtilsCr;
                }
            }
            if (!isRePlaceable(blockPos)) {
                return null;
            }
            blockUtilsCr.f = EnumFacing.UP;
            blockUtilsCr.pos.offset(EnumFacing.UP);
            blockPos.offset(EnumFacing.DOWN);
            if (b && getCollisionBoundingBox != Block.NULL_AABB && !BlockUtilsCr.mc.world.checkNoEntityCollision(getCollisionBoundingBox.offset(blockPos), (Entity)null)) {
                return null;
            }
            return blockUtilsCr;
        }
    }
    
    public static boolean doBreak(final BlockPos blockPos, final EnumFacing enumFacing) {
        return BlockUtilsCr.mc.playerController.clickBlock(blockPos, enumFacing);
    }
    
    public static boolean isRePlaceable(final BlockPos blockPos) {
        final Block getBlock = BlockUtilsCr.mc.world.getBlockState(blockPos).getBlock();
        return getBlock.isReplaceable((IBlockAccess)BlockUtilsCr.mc.world, blockPos) && !(getBlock instanceof BlockAir);
    }
    
    public static boolean doPlace(final BlockUtilsCr blockUtilsCr, final boolean b) {
        return blockUtilsCr != null && blockUtilsCr.doPlace(b);
    }
    
    static {
        BlockUtilsCr.mc = Minecraft.getMinecraft();
    }
    
    public static boolean isAir(final BlockPos blockPos) {
        return BlockUtilsCr.mc.world.getBlockState(blockPos).getBlock() instanceof BlockAir;
    }
    
    public boolean doPlace(final boolean b) {
        final double n = this.pos.getX() + 0.5 - BlockUtilsCr.mc.player.posX - this.f.getDirectionVec().getX() / 2.0;
        final double n2 = this.pos.getY() + 0.5 - BlockUtilsCr.mc.player.posY - this.f.getDirectionVec().getY() / 2.0 - BlockUtilsCr.mc.player.getEyeHeight();
        final double n3 = this.pos.getZ() + 0.5 - BlockUtilsCr.mc.player.posZ - this.f.getDirectionVec().getZ() / 2.0;
        final double direction2D = getDirection2D(n3, n);
        final double direction2D2 = getDirection2D(n2, Math.sqrt(n * n + n3 * n3));
        final Vec3d vectorForRotation = this.getVectorForRotation(-direction2D2, direction2D - 90.0);
        this.roty = -direction2D2;
        this.rotx = direction2D - 90.0;
        if (BlockUtilsCr.mc.playerController.processRightClickBlock(BlockUtilsCr.mc.player, BlockUtilsCr.mc.world, new BlockPos(this.pos.getX() - this.f.getDirectionVec().getX(), this.pos.getY() - this.f.getDirectionVec().getY(), this.pos.getZ() - this.f.getDirectionVec().getZ()), this.f, vectorForRotation, EnumHand.MAIN_HAND) == EnumActionResult.SUCCESS) {
            if (b) {
                BlockUtilsCr.mc.player.swingArm(EnumHand.MAIN_HAND);
            }
            return true;
        }
        return false;
    }
}
