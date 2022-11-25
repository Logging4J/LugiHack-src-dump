//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util.cc;

import me.snow.luigihack.api.util.*;
import net.minecraft.block.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import java.util.*;

public class BlockUtilCC implements Util
{
    public static final /* synthetic */ List<Block> unbreakableBlocks;
    public static final /* synthetic */ List<Block> resistantBlocks;
    
    public static boolean isReplaceable(final BlockPos blockPos) {
        return BlockUtilCC.mc.world.getBlockState(blockPos).getMaterial().isReplaceable();
    }
    
    public static boolean isBreakable(final BlockPos blockPos) {
        return !getResistance(blockPos).equals(Resistance.UNBREAKABLE);
    }
    
    public static double getDistanceToCenter(final EntityPlayer entityPlayer, final BlockPos blockPos) {
        final double n = blockPos.getX() + 0.5 - entityPlayer.posX;
        final double n2 = blockPos.getY() + 0.5 - entityPlayer.posY;
        final double n3 = blockPos.getZ() + 0.5 - entityPlayer.posZ;
        return StrictMath.sqrt(n * n + n2 * n2 + n3 * n3);
    }
    
    public static Resistance getResistance(final BlockPos blockPos) {
        final Block getBlock = BlockUtilCC.mc.world.getBlockState(blockPos).getBlock();
        if (getBlock == null) {
            return Resistance.NONE;
        }
        if (BlockUtilCC.resistantBlocks.contains(getBlock)) {
            return Resistance.RESISTANT;
        }
        if (BlockUtilCC.unbreakableBlocks.contains(getBlock)) {
            return Resistance.UNBREAKABLE;
        }
        if (getBlock.getDefaultState().getMaterial().isReplaceable()) {
            return Resistance.REPLACEABLE;
        }
        return Resistance.BREAKABLE;
    }
    
    public static List<BlockPos> getBlocksInArea(final EntityPlayer entityPlayer, final AxisAlignedBB axisAlignedBB) {
        if (entityPlayer != null) {
            final ArrayList<BlockPos> list = new ArrayList<BlockPos>();
            for (double floor = StrictMath.floor(axisAlignedBB.minX); floor <= StrictMath.ceil(axisAlignedBB.maxX); ++floor) {
                for (double floor2 = StrictMath.floor(axisAlignedBB.minY); floor2 <= StrictMath.ceil(axisAlignedBB.maxY); ++floor2) {
                    for (double floor3 = StrictMath.floor(axisAlignedBB.minZ); floor3 <= StrictMath.ceil(axisAlignedBB.maxZ); ++floor3) {
                        final BlockPos add = entityPlayer.getPosition().add(floor, floor2, floor3);
                        if (getDistanceToCenter(entityPlayer, add) < axisAlignedBB.maxX) {
                            list.add(add);
                        }
                    }
                }
            }
            return list;
        }
        return new ArrayList<BlockPos>();
    }
    
    static {
        resistantBlocks = Arrays.asList(Blocks.OBSIDIAN, Blocks.ANVIL, Blocks.ENCHANTING_TABLE, Blocks.ENDER_CHEST, (Block)Blocks.BEACON);
        unbreakableBlocks = Arrays.asList(Blocks.BEDROCK, Blocks.COMMAND_BLOCK, Blocks.CHAIN_COMMAND_BLOCK, Blocks.END_PORTAL_FRAME, Blocks.BARRIER, (Block)Blocks.PORTAL);
    }
    
    public enum Resistance
    {
        REPLACEABLE, 
        UNBREAKABLE, 
        NONE, 
        RESISTANT, 
        BREAKABLE;
    }
}
