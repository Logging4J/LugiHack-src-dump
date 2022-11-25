//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.turok.values;

import java.math.*;

public class TurokDouble
{
    private /* synthetic */ double value;
    private /* synthetic */ double min;
    private /* synthetic */ TurokString name;
    private /* synthetic */ TurokString tag;
    private /* synthetic */ double max;
    
    public static double floor(double floor, double floor2) {
        floor = Math.floor(floor);
        floor2 = Math.floor(floor2);
        if (floor == 0.0 || floor2 == 0.0) {
            return floor + floor2;
        }
        return floor(floor, floor % floor2);
    }
    
    public static double round(final double val) {
        return new BigDecimal(val).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
    
    public TurokString get_tag() {
        return this.tag;
    }
    
    public double get_value() {
        return this.value;
    }
    
    public TurokDouble(final TurokString name, final TurokString tag, final double value, final double min, final double max) {
        this.name = name;
        this.tag = tag;
        this.value = value;
        this.max = max;
        this.min = min;
    }
    
    public static double step(final double n, final double n2) {
        double floor = floor(n, n2);
        if (floor > n2) {
            floor = n2 / 20.0;
        }
        if (n2 > 10.0) {
            floor = (double)Math.round(floor);
        }
        if (floor == 0.0) {
            floor = n2;
        }
        return floor;
    }
    
    public void set_value(final double value) {
        this.value = value;
    }
    
    public TurokString get_name() {
        return this.name;
    }
    
    public void set_slider_value(final double value) {
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
}
