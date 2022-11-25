//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.turok.values;

public class TurokFloat
{
    private /* synthetic */ float max;
    private /* synthetic */ float value;
    private /* synthetic */ TurokString name;
    private /* synthetic */ TurokString tag;
    private /* synthetic */ float min;
    
    public TurokFloat(final TurokString name, final TurokString tag, final float n, final float min, final float max) {
        this.name = name;
        this.tag = tag;
        this.max = max;
        this.min = min;
    }
    
    public float get_value() {
        return this.value;
    }
    
    public TurokString get_tag() {
        return this.tag;
    }
    
    public void set_slider_value(final float value) {
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
    
    public void set_value(final float value) {
        this.value = value;
    }
    
    public TurokString get_name() {
        return this.name;
    }
}
