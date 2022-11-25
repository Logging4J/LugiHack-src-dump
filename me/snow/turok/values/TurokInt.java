//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.turok.values;

public class TurokInt
{
    private /* synthetic */ int min;
    private /* synthetic */ TurokString tag;
    private /* synthetic */ TurokString name;
    private /* synthetic */ int value;
    private /* synthetic */ int max;
    
    public void set_slider_value(final int value) {
        if (value >= this.max) {
            this.value = this.max;
        }
        else if (value <= this.min) {
            this.value = this.min;
        }
        else {
            this.value = value;
        }
    }
    
    public int get_value() {
        return this.value;
    }
    
    public void set_value(final int value) {
        this.value = value;
    }
    
    public TurokInt(final TurokString name, final TurokString tag, final int value, final int min, final int max) {
        this.name = name;
        this.tag = tag;
        this.value = value;
        this.max = max;
        this.min = min;
    }
    
    public TurokString get_tag() {
        return this.tag;
    }
    
    public TurokString get_name() {
        return this.name;
    }
}
