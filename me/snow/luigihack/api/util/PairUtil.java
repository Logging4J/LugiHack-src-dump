//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util;

public class PairUtil<F, S>
{
    private /* synthetic */ F first;
    private /* synthetic */ S second;
    
    public void setFirst(final F first) {
        this.first = first;
    }
    
    public F getFirst() {
        return this.first;
    }
    
    public S getSecond() {
        return this.second;
    }
    
    public void setSecond(final S second) {
        this.second = second;
    }
    
    public PairUtil(final F first, final S second) {
        this.first = first;
        this.second = second;
    }
}
