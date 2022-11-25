//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util.skid.oyvey;

import net.minecraft.client.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.block.*;
import java.util.*;
import net.minecraft.entity.*;

public class HoleUtilStay
{
    private static /* synthetic */ Minecraft mc;
    public static final /* synthetic */ List<BlockPos> holeBlocks;
    
    static {
        holeBlocks = Arrays.asList(new BlockPos(0, -1, 0), new BlockPos(0, 0, -1), new BlockPos(-1, 0, 0), new BlockPos(1, 0, 0), new BlockPos(0, 0, 1));
        HoleUtilStay.mc = Minecraft.getMinecraft();
        cityOffsets = new Vec3d[] { new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, -1.0) };
    }
    
    public static boolean is2securityHole(final BlockPos blockPos) {
        if (is2Hole(blockPos) == null) {
            return false;
        }
        final BlockPos is2Hole = is2Hole(blockPos);
        int n = 0;
        final Iterator<BlockPos> iterator = HoleUtilStay.holeBlocks.iterator();
        while (iterator.hasNext()) {
            if (HoleUtilStay.mc.world.getBlockState(blockPos.add((Vec3i)iterator.next())).getBlock() != Blocks.BEDROCK) {
                continue;
            }
            ++n;
        }
        final Iterator<BlockPos> iterator2 = HoleUtilStay.holeBlocks.iterator();
        while (iterator2.hasNext()) {
            if (HoleUtilStay.mc.world.getBlockState(is2Hole.add((Vec3i)iterator2.next())).getBlock() != Blocks.BEDROCK) {
                continue;
            }
            ++n;
        }
        return n == 8;
    }
    
    public static boolean isHole(final BlockPos blockPos) {
        int n = 0;
        final Iterator<BlockPos> iterator = HoleUtilStay.holeBlocks.iterator();
        while (iterator.hasNext()) {
            if (!CombatUtil.isHard(HoleUtilStay.mc.world.getBlockState(blockPos.add((Vec3i)iterator.next())).getBlock())) {
                continue;
            }
            ++n;
        }
        return n == 5;
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
    
    public static HashMap<BlockOffset, BlockSafety> getUnsafeSides(final BlockPos blockPos) {
        final HashMap<BlockOffset, BlockSafety> hashMap = new HashMap<BlockOffset, BlockSafety>();
        final BlockSafety blockSafe = isBlockSafe(HoleUtilStay.mc.world.getBlockState(BlockOffset.DOWN.offset(blockPos)).getBlock());
        if (blockSafe != BlockSafety.UNBREAKABLE) {
            hashMap.put(BlockOffset.DOWN, blockSafe);
        }
        final BlockSafety blockSafe2;
        if ((blockSafe2 = isBlockSafe(HoleUtilStay.mc.world.getBlockState(BlockOffset.NORTH.offset(blockPos)).getBlock())) != BlockSafety.UNBREAKABLE) {
            hashMap.put(BlockOffset.NORTH, blockSafe2);
        }
        final BlockSafety blockSafe3;
        if ((blockSafe3 = isBlockSafe(HoleUtilStay.mc.world.getBlockState(BlockOffset.SOUTH.offset(blockPos)).getBlock())) != BlockSafety.UNBREAKABLE) {
            hashMap.put(BlockOffset.SOUTH, blockSafe3);
        }
        final BlockSafety blockSafe4;
        if ((blockSafe4 = isBlockSafe(HoleUtilStay.mc.world.getBlockState(BlockOffset.EAST.offset(blockPos)).getBlock())) != BlockSafety.UNBREAKABLE) {
            hashMap.put(BlockOffset.EAST, blockSafe4);
        }
        final BlockSafety blockSafe5;
        if ((blockSafe5 = isBlockSafe(HoleUtilStay.mc.world.getBlockState(BlockOffset.WEST.offset(blockPos)).getBlock())) != BlockSafety.UNBREAKABLE) {
            hashMap.put(BlockOffset.WEST, blockSafe5);
        }
        return hashMap;
    }
    
    public static boolean isInHole() {
        final Vec3d interpolateEntity = CombatUtil.interpolateEntity((Entity)HoleUtilStay.mc.player);
        final BlockPos blockPos = new BlockPos(interpolateEntity.x, interpolateEntity.y, interpolateEntity.z);
        int n = 0;
        final Iterator<BlockPos> iterator = HoleUtilStay.holeBlocks.iterator();
        while (iterator.hasNext()) {
            if (!CombatUtil.isHard(HoleUtilStay.mc.world.getBlockState(blockPos.add((Vec3i)iterator.next())).getBlock())) {
                continue;
            }
            ++n;
        }
        return n == 5;
    }
    
    public static BlockPos is2Hole(final BlockPos blockPos) {
        if (isHole(blockPos)) {
            return null;
        }
        BlockPos add = null;
        int n = 0;
        int n2 = 0;
        if (HoleUtilStay.mc.world.getBlockState(blockPos).getBlock() != Blocks.AIR) {
            return null;
        }
        for (final BlockPos blockPos2 : HoleUtilStay.holeBlocks) {
            if (HoleUtilStay.mc.world.getBlockState(blockPos.add((Vec3i)blockPos2)).getBlock() == Blocks.AIR) {
                if (blockPos.add((Vec3i)blockPos2) == new BlockPos(blockPos2.getX(), blockPos2.getY() - 1, blockPos2.getZ())) {
                    continue;
                }
                add = blockPos.add((Vec3i)blockPos2);
                ++n;
            }
        }
        if (n == 1) {
            for (final BlockPos blockPos3 : HoleUtilStay.holeBlocks) {
                if (HoleUtilStay.mc.world.getBlockState(blockPos.add((Vec3i)blockPos3)).getBlock() != Blocks.BEDROCK && HoleUtilStay.mc.world.getBlockState(blockPos.add((Vec3i)blockPos3)).getBlock() != Blocks.OBSIDIAN) {
                    continue;
                }
                ++n2;
            }
            for (final BlockPos blockPos4 : HoleUtilStay.holeBlocks) {
                if (HoleUtilStay.mc.world.getBlockState(add.add((Vec3i)blockPos4)).getBlock() != Blocks.BEDROCK && HoleUtilStay.mc.world.getBlockState(add.add((Vec3i)blockPos4)).getBlock() != Blocks.OBSIDIAN) {
                    continue;
                }
                ++n2;
            }
        }
        if (n2 == 8) {
            return add;
        }
        return null;
    }
    
    public enum BlockOffset
    {
        WEST(-1, 0, 0);
        
        private final /* synthetic */ int x;
        
        EAST(1, 0, 0), 
        SOUTH(0, 0, 1);
        
        private final /* synthetic */ int z;
        
        NORTH(0, 0, -1), 
        UP(0, 1, 0), 
        DOWN(0, -1, 0);
        
        private final /* synthetic */ int y;
        
        public BlockPos forward(final BlockPos blockPos, final int n) {
            return blockPos.add(this.x * n, 0, this.z * n);
        }
        
        public BlockPos offset(final BlockPos blockPos) {
            return blockPos.add(this.x, this.y, this.z);
        }
        
        public BlockPos backward(final BlockPos blockPos, final int n) {
            return blockPos.add(-this.x * n, 0, -this.z * n);
        }
        
        private BlockOffset(final int x, final int y, final int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
        
        public BlockPos right(final BlockPos blockPos, final int n) {
            return blockPos.add(-this.z * n, 0, this.x * n);
        }
        
        public BlockPos left(final BlockPos blockPos, final int n) {
            return blockPos.add(this.z * n, 0, -this.x * n);
        }
    }
    
    public enum BlockSafety
    {
        BREAKABLE, 
        RESISTANT, 
        UNBREAKABLE;
    }
}
