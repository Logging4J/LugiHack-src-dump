//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util;

import net.minecraft.util.math.*;

public class TimeVec3d extends Vec3d
{
    private final /* synthetic */ long time;
    
    public long getTime() {
        return this.time;
    }
    
    public TimeVec3d(final double n, final double n2, final double n3, final long time) {
        super(n, n2, n3);
        this.time = time;
    }
}
