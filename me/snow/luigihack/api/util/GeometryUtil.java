//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util;

import java.util.*;
import net.minecraft.util.*;

public class GeometryUtil
{
    public static final /* synthetic */ HashMap<EnumFacing, Integer> FACEMAP;
    
    static {
        (FACEMAP = new HashMap<EnumFacing, Integer>()).put(EnumFacing.DOWN, 1);
        GeometryUtil.FACEMAP.put(EnumFacing.WEST, 16);
        GeometryUtil.FACEMAP.put(EnumFacing.NORTH, 4);
        GeometryUtil.FACEMAP.put(EnumFacing.SOUTH, 8);
        GeometryUtil.FACEMAP.put(EnumFacing.EAST, 32);
        GeometryUtil.FACEMAP.put(EnumFacing.UP, 2);
    }
    
    public static final class Line
    {
        static {
            UP_WEST = 18;
            SOUTH_WEST = 24;
            NORTH_WEST = 20;
            NORTH_EAST = 36;
            DOWN_EAST = 33;
            DOWN_SOUTH = 9;
            ALL = 63;
            DOWN_WEST = 17;
            UP_EAST = 34;
            UP_NORTH = 6;
            UP_SOUTH = 10;
            SOUTH_EAST = 40;
            DOWN_NORTH = 5;
        }
    }
    
    public static final class Quad
    {
        static {
            DOWN = 1;
            EAST = 32;
            SOUTH = 8;
            WEST = 16;
            ALL = 63;
            UP = 2;
            NORTH = 4;
        }
    }
}
