//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util.skid.oyvey;

import net.minecraft.block.*;
import net.minecraft.util.math.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.init.*;
import net.minecraft.block.state.*;
import net.minecraft.util.*;
import java.util.*;

public class HoleUtilSafety implements Util
{
    private static final /* synthetic */ Block[] NO_BLAST;
    public static /* synthetic */ BlockPos[] holeOffsets;
    private static final /* synthetic */ Vec3i[] OFFSETS_2x2;
    
    public static boolean[] isHole(final BlockPos blockPos, final boolean b) {
        final boolean[] array = { false, true };
        if (!BlockUtil.isAir(blockPos) || !BlockUtil.isAir(blockPos.up()) || (b && !BlockUtil.isAir(blockPos.up(2)))) {
            return array;
        }
        return is1x1(blockPos, array);
    }
    
    public static boolean is2x2(final BlockPos blockPos) {
        return is2x2(blockPos, true);
    }
    
    public static boolean is2x2(final BlockPos blockPos, final boolean b) {
        if (b && !BlockUtil.isAir(blockPos)) {
            return false;
        }
        if (is2x2Partial(blockPos)) {
            return true;
        }
        final BlockPos add = blockPos.add(-1, 0, 0);
        final boolean air = BlockUtil.isAir(add);
        if (air && is2x2Partial(add)) {
            return true;
        }
        final BlockPos add2 = blockPos.add(0, 0, -1);
        final boolean air2 = BlockUtil.isAir(add2);
        return (air2 && is2x2Partial(add2)) || ((air || air2) && is2x2Partial(blockPos.add(-1, 0, -1)));
    }
    
    public static boolean isObbyHole(final BlockPos blockPos) {
        boolean b = true;
        int n = 0;
        for (final BlockPos blockPos2 : HoleUtilSafety.holeOffsets) {
            final Block getBlock = HoleUtilSafety.mc.world.getBlockState(blockPos.add((Vec3i)blockPos2)).getBlock();
            if (!isSafeBlock(blockPos.add((Vec3i)blockPos2))) {
                b = false;
            }
            else if (getBlock == Blocks.OBSIDIAN || getBlock == Blocks.ENDER_CHEST || getBlock == Blocks.ANVIL) {
                ++n;
            }
        }
        if (HoleUtilSafety.mc.world.getBlockState(blockPos.add(0, 2, 0)).getBlock() != Blocks.AIR || HoleUtilSafety.mc.world.getBlockState(blockPos.add(0, 1, 0)).getBlock() != Blocks.AIR) {
            b = false;
        }
        if (n < 1) {
            b = false;
        }
        return b;
    }
    
    public static List<Hole> getHoles(final double n, final BlockPos blockPos, final boolean b) {
        final ArrayList<Hole> list = new ArrayList<Hole>();
        for (final BlockPos blockPos2 : getSphere(n, blockPos, true, false)) {
            if (HoleUtilSafety.mc.world.getBlockState(blockPos2).getBlock() != Blocks.AIR) {
                continue;
            }
            if (isObbyHole(blockPos2)) {
                list.add(new Hole(false, false, blockPos2));
            }
            else if (isBedrockHoles(blockPos2)) {
                list.add(new Hole(true, false, blockPos2));
            }
            else {
                final Hole doubleHole;
                if (!b || (doubleHole = isDoubleHole(blockPos2)) == null) {
                    continue;
                }
                if (HoleUtilSafety.mc.world.getBlockState(doubleHole.pos1.add(0, 1, 0)).getBlock() != Blocks.AIR && HoleUtilSafety.mc.world.getBlockState(doubleHole.pos2.add(0, 1, 0)).getBlock() != Blocks.AIR) {
                    continue;
                }
                list.add(doubleHole);
            }
        }
        return list;
    }
    
    static {
        HoleUtilSafety.holeOffsets = new BlockPos[] { new BlockPos(1, 0, 0), new BlockPos(-1, 0, 0), new BlockPos(0, 0, 1), new BlockPos(0, 0, -1), new BlockPos(0, -1, 0) };
        OFFSETS_2x2 = new Vec3i[] { new Vec3i(0, 0, 0), new Vec3i(1, 0, 0), new Vec3i(0, 0, 1), new Vec3i(1, 0, 1) };
        NO_BLAST = new Block[] { Blocks.BEDROCK, Blocks.OBSIDIAN, Blocks.ANVIL, Blocks.ENDER_CHEST };
    }
    
    public static Hole isDoubleHole(final BlockPos blockPos) {
        if (checkOffset(blockPos, 1, 0)) {
            return new Hole(false, true, blockPos, blockPos.add(1, 0, 0));
        }
        if (checkOffset(blockPos, 0, 1)) {
            return new Hole(false, true, blockPos, blockPos.add(0, 0, 1));
        }
        return null;
    }
    
    public static boolean isHole(final BlockPos blockPos) {
        boolean b = false;
        int n = 0;
        final BlockPos[] holeOffsets = HoleUtilSafety.holeOffsets;
        for (int length = holeOffsets.length, i = 0; i < length; ++i) {
            if (!HoleUtilSafety.mc.world.getBlockState(blockPos.add((Vec3i)holeOffsets[i])).getMaterial().isReplaceable()) {
                ++n;
            }
        }
        if (n == 5) {
            b = true;
        }
        return b;
    }
    
    public static boolean is2x1(final BlockPos blockPos, final boolean b) {
        if (b) {
            if (!BlockUtil.isAir(blockPos)) {
                return false;
            }
            if (!BlockUtil.isAir(blockPos.up())) {
                return false;
            }
            if (BlockUtil.isAir(blockPos.down())) {
                return false;
            }
        }
        int n = 0;
        for (final EnumFacing enumFacing : EnumFacing.HORIZONTALS) {
            final BlockPos offset = blockPos.offset(enumFacing);
            if (BlockUtil.isAir(offset)) {
                if (!BlockUtil.isAir(offset.up())) {
                    return false;
                }
                if (BlockUtil.isAir(offset.down())) {
                    return false;
                }
                final EnumFacing[] HORIZONTALS2 = EnumFacing.HORIZONTALS;
                for (int length2 = HORIZONTALS2.length, j = 0; j < length2; ++j) {
                    if (HORIZONTALS2[j] != enumFacing.getOpposite()) {
                        final EnumFacing enumFacing2;
                        if (Arrays.stream(HoleUtilSafety.NO_BLAST).noneMatch(block -> block == HoleUtilSafety.mc.world.getBlockState(offset.offset(enumFacing2)).getBlock())) {
                            return false;
                        }
                    }
                }
                ++n;
            }
            if (n > 0) {
                return false;
            }
        }
        return n == 0;
    }
    
    public static boolean is2x1(final BlockPos blockPos) {
        return is2x1(blockPos, true);
    }
    
    public static boolean is2x2Partial(final BlockPos blockPos) {
        final HashSet<BlockPos> set = new HashSet<BlockPos>();
        final Vec3i[] offsets_2x2 = HoleUtilSafety.OFFSETS_2x2;
        for (int length = offsets_2x2.length, i = 0; i < length; ++i) {
            set.add(blockPos.add(offsets_2x2[i]));
        }
        boolean b = false;
        for (final BlockPos blockPos2 : set) {
            if (!BlockUtil.isAir(blockPos2) || !BlockUtil.isAir(blockPos2.up()) || BlockUtil.isAir(blockPos2.down())) {
                return false;
            }
            if (BlockUtil.isAir(blockPos2.up(2))) {
                b = true;
            }
            final EnumFacing[] HORIZONTALS = EnumFacing.HORIZONTALS;
            for (int length2 = HORIZONTALS.length, j = 0; j < length2; ++j) {
                if (!set.contains(blockPos2.offset(HORIZONTALS[j]))) {
                    final BlockPos blockPos3;
                    if (Arrays.stream(HoleUtilSafety.NO_BLAST).noneMatch(block -> block == HoleUtilSafety.mc.world.getBlockState(blockPos3).getBlock())) {
                        return false;
                    }
                }
            }
        }
        return b;
    }
    
    public static boolean[] is1x1(final BlockPos blockPos) {
        return is1x1(blockPos, new boolean[] { false, true });
    }
    
    public static boolean checkOffset(final BlockPos blockPos, final int n, final int n2) {
        return HoleUtilSafety.mc.world.getBlockState(blockPos).getBlock() == Blocks.AIR && HoleUtilSafety.mc.world.getBlockState(blockPos.add(n, 0, n2)).getBlock() == Blocks.AIR && isSafeBlock(blockPos.add(0, -1, 0)) && isSafeBlock(blockPos.add(n, -1, n2)) && isSafeBlock(blockPos.add(n * 2, 0, n2 * 2)) && isSafeBlock(blockPos.add(-n, 0, -n2)) && isSafeBlock(blockPos.add(n2, 0, n)) && isSafeBlock(blockPos.add(-n2, 0, -n)) && isSafeBlock(blockPos.add(n, 0, n2).add(n2, 0, n)) && isSafeBlock(blockPos.add(n, 0, n2).add(-n2, 0, -n));
    }
    
    public static boolean[] is1x1(final BlockPos blockPos, final boolean[] array) {
        for (final EnumFacing enumFacing : EnumFacing.values()) {
            if (enumFacing != EnumFacing.UP) {
                final IBlockState getBlockState;
                if ((getBlockState = HoleUtilSafety.mc.world.getBlockState(blockPos.offset(enumFacing))).getBlock() != Blocks.BEDROCK) {
                    if (Arrays.stream(HoleUtilSafety.NO_BLAST).noneMatch(block -> block == getBlockState.getBlock())) {
                        return array;
                    }
                    array[1] = false;
                }
            }
        }
        array[0] = true;
        return array;
    }
    
    public static boolean isBedrockHoles(final BlockPos blockPos) {
        boolean b = true;
        final BlockPos[] holeOffsets = HoleUtilSafety.holeOffsets;
        for (int length = holeOffsets.length, i = 0; i < length; ++i) {
            if (HoleUtilSafety.mc.world.getBlockState(blockPos.add((Vec3i)holeOffsets[i])).getBlock() != Blocks.BEDROCK) {
                b = false;
            }
        }
        if (HoleUtilSafety.mc.world.getBlockState(blockPos.add(0, 2, 0)).getBlock() != Blocks.AIR || HoleUtilSafety.mc.world.getBlockState(blockPos.add(0, 1, 0)).getBlock() != Blocks.AIR) {
            b = false;
        }
        return b;
    }
    
    static boolean isSafeBlock(final BlockPos blockPos) {
        return HoleUtilSafety.mc.world.getBlockState(blockPos).getBlock() == Blocks.OBSIDIAN || HoleUtilSafety.mc.world.getBlockState(blockPos).getBlock() == Blocks.BEDROCK || HoleUtilSafety.mc.world.getBlockState(blockPos).getBlock() == Blocks.ENDER_CHEST;
    }
    
    public static List<BlockPos> getSphere(final double n, final BlockPos blockPos, final boolean b, final boolean b2) {
        final ArrayList<BlockPos> list = new ArrayList<BlockPos>();
        final int getX = blockPos.getX();
        final int getY = blockPos.getY();
        final int getZ = blockPos.getZ();
        for (int n2 = getX - (int)n; n2 <= getX + n; ++n2) {
            for (int n3 = getZ - (int)n; n3 <= getZ + n; ++n3) {
                int n4 = b ? (getY - (int)n) : getY;
                while (true) {
                    final double n5 = n4;
                    double n7 = 0.0;
                    if (b) {
                        final double n6 = getY + n;
                    }
                    else {
                        n7 = getY + n;
                    }
                    if (n5 >= n7) {
                        break;
                    }
                    final double n8 = (getX - n2) * (getX - n2) + (getZ - n3) * (getZ - n3) + (b ? ((getY - n4) * (getY - n4)) : 0);
                    if (n8 < n * n && (!b2 || n8 >= (n - 1.0) * (n - 1.0))) {
                        list.add(new BlockPos(n2, n4, n3));
                    }
                    ++n4;
                }
            }
        }
        return list;
    }
    
    public static class Hole
    {
        public /* synthetic */ BlockPos pos2;
        public /* synthetic */ BlockPos pos1;
        public /* synthetic */ boolean bedrock;
        public /* synthetic */ boolean doubleHole;
        
        public Hole(final boolean bedrock, final boolean doubleHole, final BlockPos pos1) {
            this.bedrock = bedrock;
            this.doubleHole = doubleHole;
            this.pos1 = pos1;
        }
        
        public Hole(final boolean bedrock, final boolean doubleHole, final BlockPos pos1, final BlockPos pos2) {
            this.bedrock = bedrock;
            this.doubleHole = doubleHole;
            this.pos1 = pos1;
            this.pos2 = pos2;
        }
    }
}
