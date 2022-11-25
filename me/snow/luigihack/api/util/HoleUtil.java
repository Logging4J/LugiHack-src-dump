//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util;

import net.minecraft.util.math.*;
import net.minecraft.block.*;
import net.minecraft.init.*;
import net.minecraft.util.*;
import java.util.*;

public class HoleUtil implements Util
{
    public static ArrayList<Hole> holes(final float n, final int n2) {
        final ArrayList<DoubleHole> list = (ArrayList<DoubleHole>)new ArrayList<Hole>();
        final Iterator<BlockPos> iterator = WorldUtils.getSphere(PlayerUtil.getPlayerPosFloored(), n, (int)n, false, true, 0).iterator();
        while (iterator.hasNext()) {
            final Hole hole = getHole(iterator.next(), n2);
            if (hole instanceof QuadHole) {
                boolean b = false;
                for (final DoubleHole doubleHole : list) {
                    if (doubleHole instanceof QuadHole && ((QuadHole)doubleHole).contains((QuadHole)hole)) {
                        b = true;
                        break;
                    }
                }
                if (b) {
                    continue;
                }
            }
            if (hole instanceof DoubleHole) {
                boolean b2 = false;
                for (final DoubleHole doubleHole2 : list) {
                    if (doubleHole2 instanceof DoubleHole && doubleHole2.contains((DoubleHole)hole)) {
                        b2 = true;
                        break;
                    }
                }
                if (b2) {
                    continue;
                }
            }
            if (hole != null) {
                list.add((DoubleHole)hole);
            }
        }
        return (ArrayList<Hole>)list;
    }
    
    public static Hole getHole(final BlockPos blockPos, final int n) {
        boolean b = false;
        for (int i = 0; i < n; ++i) {
            if (!WorldUtils.empty.contains(WorldUtils.getBlock(blockPos.add(0, i + 1, 0)))) {
                b = true;
            }
        }
        if (b) {
            return null;
        }
        if (WorldUtils.empty.contains(WorldUtils.getBlock(blockPos)) && !WorldUtils.empty.contains(WorldUtils.getBlock(blockPos.down()))) {
            if ((WorldUtils.getBlock(blockPos.north()) instanceof BlockObsidian || WorldUtils.getBlock(blockPos.north()) == Blocks.BEDROCK) && (WorldUtils.getBlock(blockPos.south()) instanceof BlockObsidian || WorldUtils.getBlock(blockPos.south()) == Blocks.BEDROCK) && (WorldUtils.getBlock(blockPos.east()) instanceof BlockObsidian || WorldUtils.getBlock(blockPos.east()) == Blocks.BEDROCK) && (WorldUtils.getBlock(blockPos.west()) instanceof BlockObsidian || WorldUtils.getBlock(blockPos.west()) == Blocks.BEDROCK)) {
                if (WorldUtils.getBlock(blockPos.north()) instanceof BlockObsidian || WorldUtils.getBlock(blockPos.east()) instanceof BlockObsidian || WorldUtils.getBlock(blockPos.south()) instanceof BlockObsidian || WorldUtils.getBlock(blockPos.west()) instanceof BlockObsidian) {
                    return new SingleHole(blockPos, material.OBSIDIAN);
                }
                return new SingleHole(blockPos, material.BEDROCK);
            }
            else {
                final BlockPos[] array = { blockPos.west(), blockPos.north(), blockPos.east(), blockPos.south() };
                BlockPos blockPos2 = null;
                for (final BlockPos blockPos3 : array) {
                    if (WorldUtils.empty.contains(WorldUtils.getBlock(blockPos3))) {
                        blockPos2 = blockPos3;
                        break;
                    }
                }
                if (blockPos2 == null || WorldUtils.empty.contains(WorldUtils.getBlock(blockPos2.down()))) {
                    return null;
                }
                final BlockPos[] array3 = { blockPos2.west(), blockPos2.north(), blockPos2.east(), blockPos2.south() };
                int n2 = 0;
                boolean b2 = false;
                EnumFacing enumFacing = null;
                for (final BlockPos blockPos4 : array3) {
                    if (blockPos4 != blockPos) {
                        if (WorldUtils.getBlock(blockPos4) instanceof BlockObsidian) {
                            b2 = true;
                            ++n2;
                        }
                        if (WorldUtils.getBlock(blockPos4) == Blocks.BEDROCK) {
                            ++n2;
                        }
                    }
                }
                for (final BlockPos blockPos5 : array) {
                    if (blockPos5 != blockPos2) {
                        if (WorldUtils.getBlock(blockPos5) instanceof BlockObsidian) {
                            b2 = true;
                            ++n2;
                        }
                        if (WorldUtils.getBlock(blockPos5) == Blocks.BEDROCK) {
                            ++n2;
                        }
                    }
                }
                for (final EnumFacing enumFacing2 : EnumFacing.values()) {
                    if (blockPos.add(enumFacing2.getXOffset(), enumFacing2.getYOffset(), enumFacing2.getZOffset()).equals((Object)blockPos2)) {
                        enumFacing = enumFacing2;
                    }
                }
                if (n2 >= 6) {
                    return new DoubleHole(blockPos, blockPos2, b2 ? material.OBSIDIAN : material.BEDROCK, enumFacing);
                }
            }
        }
        return null;
    }
    
    static {
        holeBlocks = Arrays.asList(new BlockPos(0, -1, 0), new BlockPos(0, 0, -1), new BlockPos(-1, 0, 0), new BlockPos(1, 0, 0), new BlockPos(0, 0, 1));
        awacityOffsets = new Vec3d[] { new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, -1.0) };
    }
    
    public static final class DoubleHole extends Hole
    {
        public /* synthetic */ EnumFacing dir;
        public /* synthetic */ BlockPos pos;
        public /* synthetic */ BlockPos pos1;
        
        public DoubleHole(final BlockPos pos, final BlockPos pos2, final material material, final EnumFacing dir) {
            super(type.DOUBLE, material);
            this.pos = pos;
            this.pos1 = pos2;
            this.dir = dir;
        }
        
        public boolean equals(final DoubleHole doubleHole) {
            return (doubleHole.pos1.equals((Object)this.pos) || doubleHole.pos1.equals((Object)this.pos1)) && (doubleHole.pos.equals((Object)this.pos) || doubleHole.pos.equals((Object)this.pos1));
        }
        
        public boolean contains(final DoubleHole doubleHole) {
            return doubleHole.pos.equals((Object)this.pos) || doubleHole.pos.equals((Object)this.pos1) || doubleHole.pos1.equals((Object)this.pos) || doubleHole.pos1.equals((Object)this.pos1);
        }
        
        public boolean contains(final BlockPos blockPos) {
            return this.pos == blockPos || this.pos1 == blockPos;
        }
    }
    
    public static class Hole
    {
        public /* synthetic */ type type;
        public /* synthetic */ material mat;
        
        public Hole(final type type, final material mat) {
            this.type = type;
            this.mat = mat;
        }
    }
    
    public enum material
    {
        OBSIDIAN, 
        BEDROCK;
    }
    
    public enum type
    {
        QUAD, 
        DOUBLE, 
        SINGLE;
    }
    
    public static final class QuadHole extends Hole
    {
        public /* synthetic */ BlockPos pos2;
        public /* synthetic */ EnumFacing dir;
        public /* synthetic */ BlockPos pos1;
        public /* synthetic */ BlockPos pos3;
        public /* synthetic */ BlockPos pos;
        
        public boolean equals(final QuadHole quadHole) {
            return quadHole.pos3.equals((Object)this.pos) || quadHole.pos3.equals((Object)this.pos3) || quadHole.pos2.equals((Object)this.pos) || quadHole.pos2.equals((Object)this.pos2) || quadHole.pos1.equals((Object)this.pos) || (quadHole.pos1.equals((Object)this.pos1) && (quadHole.pos.equals((Object)this.pos) || quadHole.pos.equals((Object)this.pos3) || quadHole.pos.equals((Object)this.pos2) || quadHole.pos.equals((Object)this.pos1)));
        }
        
        public boolean contains(final QuadHole quadHole) {
            return quadHole.pos.equals((Object)this.pos) || quadHole.pos.equals((Object)this.pos1) || quadHole.pos.equals((Object)this.pos2) || quadHole.pos3.equals((Object)this.pos) || quadHole.pos1.equals((Object)this.pos3);
        }
        
        public QuadHole(final BlockPos pos, final BlockPos pos2, final BlockPos pos3, final BlockPos pos4, final material material, final EnumFacing dir) {
            super(type.QUAD, material);
            this.pos = pos;
            this.pos1 = pos2;
            this.pos2 = pos3;
            this.pos3 = pos4;
            this.dir = dir;
        }
        
        public boolean contains(final BlockPos blockPos) {
            return this.pos == blockPos || this.pos1 == this.pos1 || this.pos2 == this.pos2 || this.pos3 == blockPos;
        }
    }
    
    public static final class SingleHole extends Hole
    {
        public /* synthetic */ BlockPos pos;
        
        public SingleHole(final BlockPos pos, final material material) {
            super(type.SINGLE, material);
            this.pos = pos;
        }
    }
}
