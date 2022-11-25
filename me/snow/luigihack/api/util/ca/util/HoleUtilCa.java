//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util.ca.util;

import me.snow.luigihack.api.util.*;
import net.minecraft.block.*;
import net.minecraft.init.*;
import net.minecraft.util.math.*;
import java.util.*;

public class HoleUtilCa implements Util
{
    public static HashMap<BlockOffset, BlockSafety> getUnsafeSides(final BlockPos blockPos) {
        final HashMap<BlockOffset, BlockSafety> hashMap = new HashMap<BlockOffset, BlockSafety>();
        final BlockSafety blockSafe = isBlockSafe(HoleUtilCa.mc.world.getBlockState(BlockOffset.DOWN.offset(blockPos)).getBlock());
        if (blockSafe != BlockSafety.UNBREAKABLE) {
            hashMap.put(BlockOffset.DOWN, blockSafe);
        }
        final BlockSafety blockSafe2 = isBlockSafe(HoleUtilCa.mc.world.getBlockState(BlockOffset.NORTH.offset(blockPos)).getBlock());
        if (blockSafe2 != BlockSafety.UNBREAKABLE) {
            hashMap.put(BlockOffset.NORTH, blockSafe2);
        }
        final BlockSafety blockSafe3 = isBlockSafe(HoleUtilCa.mc.world.getBlockState(BlockOffset.SOUTH.offset(blockPos)).getBlock());
        if (blockSafe3 != BlockSafety.UNBREAKABLE) {
            hashMap.put(BlockOffset.SOUTH, blockSafe3);
        }
        final BlockSafety blockSafe4 = isBlockSafe(HoleUtilCa.mc.world.getBlockState(BlockOffset.EAST.offset(blockPos)).getBlock());
        if (blockSafe4 != BlockSafety.UNBREAKABLE) {
            hashMap.put(BlockOffset.EAST, blockSafe4);
        }
        final BlockSafety blockSafe5 = isBlockSafe(HoleUtilCa.mc.world.getBlockState(BlockOffset.WEST.offset(blockPos)).getBlock());
        if (blockSafe5 != BlockSafety.UNBREAKABLE) {
            hashMap.put(BlockOffset.WEST, blockSafe5);
        }
        return hashMap;
    }
    
    public static BlockSafety isBlockSafe(final Block block) {
        if (block == Blocks.BEDROCK) {
            return BlockSafety.UNBREAKABLE;
        }
        if (block == Blocks.OBSIDIAN || block == Blocks.ENDER_CHEST || block == Blocks.ANVIL) {
            return BlockSafety.RESISTANT;
        }
        return BlockSafety.BREAKABLE;
    }
    
    private static HoleInfo isDoubleHole(final HoleInfo holeInfo, final BlockPos blockPos, final BlockOffset blockOffset) {
        final BlockPos offset = blockOffset.offset(blockPos);
        final HashMap<BlockOffset, BlockSafety> unsafeSides = getUnsafeSides(offset);
        final int size = unsafeSides.size();
        unsafeSides.entrySet().removeIf(entry -> entry.getValue() == BlockSafety.RESISTANT);
        if (unsafeSides.size() != size) {
            holeInfo.setSafety(BlockSafety.RESISTANT);
        }
        if (unsafeSides.containsKey(BlockOffset.DOWN)) {
            holeInfo.setType(HoleType.CUSTOM);
            unsafeSides.remove(BlockOffset.DOWN);
        }
        if (unsafeSides.size() > 1) {
            holeInfo.setType(HoleType.NONE);
            return holeInfo;
        }
        holeInfo.setCentre(new AxisAlignedBB((double)Math.min(blockPos.getX(), offset.getX()), (double)blockPos.getY(), (double)Math.min(blockPos.getZ(), offset.getZ()), (double)(Math.max(blockPos.getX(), offset.getX()) + 1), (double)(blockPos.getY() + 1), (double)(Math.max(blockPos.getZ(), offset.getZ()) + 1)));
        if (holeInfo.getType() != HoleType.CUSTOM) {
            holeInfo.setType(HoleType.DOUBLE);
        }
        return holeInfo;
    }
    
    public static HoleInfo isHole(final BlockPos blockPos, final boolean b, final boolean b2) {
        final HoleInfo holeInfo = new HoleInfo();
        final HashMap<BlockOffset, BlockSafety> unsafeSides = getUnsafeSides(blockPos);
        if (unsafeSides.containsKey(BlockOffset.DOWN) && unsafeSides.remove(BlockOffset.DOWN, BlockSafety.BREAKABLE) && !b2) {
            holeInfo.setSafety(BlockSafety.BREAKABLE);
            return holeInfo;
        }
        final int size = unsafeSides.size();
        unsafeSides.entrySet().removeIf(entry -> entry.getValue() == BlockSafety.RESISTANT);
        if (unsafeSides.size() != size) {
            holeInfo.setSafety(BlockSafety.RESISTANT);
        }
        final int size2 = unsafeSides.size();
        if (size2 == 0) {
            holeInfo.setType(HoleType.SINGLE);
            holeInfo.setCentre(new AxisAlignedBB(blockPos));
            return holeInfo;
        }
        if (size2 == 1 && !b) {
            return isDoubleHole(holeInfo, blockPos, unsafeSides.keySet().stream().findFirst().get());
        }
        holeInfo.setSafety(BlockSafety.BREAKABLE);
        return holeInfo;
    }
    
    public enum BlockSafety
    {
        RESISTANT, 
        BREAKABLE, 
        UNBREAKABLE;
    }
    
    public enum BlockOffset
    {
        WEST(-1, 0, 0);
        
        private final /* synthetic */ int y;
        
        NORTH(0, 0, -1), 
        EAST(1, 0, 0), 
        DOWN(0, -1, 0), 
        UP(0, 1, 0);
        
        private final /* synthetic */ int x;
        
        SOUTH(0, 0, 1);
        
        private final /* synthetic */ int z;
        
        public BlockPos left(final BlockPos blockPos, final int n) {
            return blockPos.add(this.z * n, 0, -this.x * n);
        }
        
        public BlockPos right(final BlockPos blockPos, final int n) {
            return blockPos.add(-this.z * n, 0, this.x * n);
        }
        
        public BlockPos backward(final BlockPos blockPos, final int n) {
            return blockPos.add(-this.x * n, 0, -this.z * n);
        }
        
        private BlockOffset(final int x, final int y, final int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
        
        public BlockPos offset(final BlockPos blockPos) {
            return blockPos.add(this.x, this.y, this.z);
        }
        
        public BlockPos forward(final BlockPos blockPos, final int n) {
            return blockPos.add(this.x * n, 0, this.z * n);
        }
    }
    
    public enum HoleType
    {
        NONE, 
        DOUBLE, 
        SINGLE, 
        CUSTOM;
    }
    
    public static class HoleInfo
    {
        private /* synthetic */ BlockSafety safety;
        private /* synthetic */ AxisAlignedBB centre;
        private /* synthetic */ HoleType type;
        
        public HoleInfo(final BlockSafety safety, final HoleType type) {
            this.type = type;
            this.safety = safety;
        }
        
        public BlockSafety getSafety() {
            return this.safety;
        }
        
        public void setCentre(final AxisAlignedBB centre) {
            this.centre = centre;
        }
        
        public void setSafety(final BlockSafety safety) {
            this.safety = safety;
        }
        
        public AxisAlignedBB getCentre() {
            return this.centre;
        }
        
        public HoleType getType() {
            return this.type;
        }
        
        public void setType(final HoleType type) {
            this.type = type;
        }
        
        public HoleInfo() {
            this(BlockSafety.UNBREAKABLE, HoleType.NONE);
        }
    }
}
