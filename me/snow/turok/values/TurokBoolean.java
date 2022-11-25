//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.turok.values;

public class TurokBoolean
{
    private /* synthetic */ TurokString tag;
    private /* synthetic */ TurokGeneric<Boolean> value;
    private /* synthetic */ TurokString name;
    
    public TurokBoolean(final TurokString name, final TurokString tag, final boolean b) {
        this.name = name;
        this.tag = tag;
        this.value = new TurokGeneric<Boolean>(b);
    }
    
    public boolean get_value() {
        return this.value.get_value();
    }
    
    public TurokString get_tag() {
        return this.tag;
    }
    
    public void set_value(final boolean b) {
        this.value.set_value(b);
    }
    
    public TurokString get_name() {
        return this.name;
    }
}
