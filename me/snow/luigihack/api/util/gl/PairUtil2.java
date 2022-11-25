//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util.gl;

public class PairUtil2<T, S>
{
    /* synthetic */ S value;
    /* synthetic */ T key;
    
    public PairUtil2(final T key, final S value) {
        this.key = key;
        this.value = value;
    }
    
    public S getValue() {
        return this.value;
    }
    
    public void setValue(final S value) {
        this.value = value;
    }
    
    public T getKey() {
        return this.key;
    }
    
    public void setKey(final T key) {
        this.key = key;
    }
}
