//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util;

import java.util.*;

public class RandomUtil
{
    private static final /* synthetic */ Random random;
    
    public static double nextDouble(final double n, final double n2) {
        return (n == n2 || n2 - n <= 0.0) ? n : (n + (n2 - n) * Math.random());
    }
    
    public static long nextLong(final long n, final long n2) {
        return (n2 - n <= 0L) ? n : ((long)(n + (n2 - n) * Math.random()));
    }
    
    static {
        random = new Random();
    }
    
    public static int nextInt(final int n, final int n2) {
        return (n2 - n <= 0) ? n : (n + new Random().nextInt(n2 - n));
    }
    
    public static float nextFloat(final float n, final float n2) {
        return (n == n2 || n2 - n <= 0.0f) ? n : ((float)(n + (n2 - n) * Math.random()));
    }
    
    public final long randomDelay(final int n, final int n2) {
        return nextInt(n, n2);
    }
    
    public static Random getRandom() {
        return RandomUtil.random;
    }
}
