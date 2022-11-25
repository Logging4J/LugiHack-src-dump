//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.turok.task;

public class Rect
{
    private /* synthetic */ int x;
    private /* synthetic */ int width;
    private /* synthetic */ String tag;
    private /* synthetic */ int height;
    private /* synthetic */ int y;
    
    public boolean event_mouse(final int n, final int n2) {
        return n >= this.get_x() && n2 >= this.get_y() && n <= this.get_screen_width() && n2 <= this.get_screen_height();
    }
    
    public int get_screen_height() {
        return this.y + this.height;
    }
    
    public Rect(final String tag, final int width, final int height) {
        this.tag = tag;
        this.width = width;
        this.height = height;
    }
    
    public int get_x() {
        return this.x;
    }
    
    public int get_y() {
        return this.y;
    }
    
    public int get_screen_width() {
        return this.x + this.width;
    }
    
    public int get_height() {
        return this.height;
    }
    
    public void transform(final int x, final int y, final int width, final int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public int get_width() {
        return this.width;
    }
    
    public void transform(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
    
    public String get_tag() {
        return this.tag;
    }
}
