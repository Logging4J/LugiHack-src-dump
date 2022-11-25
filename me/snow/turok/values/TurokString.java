//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.turok.values;

public class TurokString
{
    private /* synthetic */ String value;
    private /* synthetic */ String tag;
    private /* synthetic */ String name;
    
    public TurokString(final String name, final String tag, final String value) {
        this.name = name;
        this.tag = tag;
        this.value = value;
    }
    
    public static String to_string(final float f) {
        return Float.toString(f);
    }
    
    public String get_value() {
        return this.value;
    }
    
    public static String to_string(final int i) {
        return Integer.toString(i);
    }
    
    public void set_value(final String value) {
        this.value = value;
    }
    
    public static String to_string(final String s) {
        return s;
    }
    
    public static String to_string(final double d) {
        return Double.toString(d);
    }
    
    public static String to_string(final boolean b) {
        return Boolean.toString(b);
    }
    
    public String get_tag() {
        return this.tag;
    }
    
    public String get_name() {
        return this.name;
    }
}
