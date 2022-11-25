//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util.ca.util;

import java.awt.*;
import net.minecraft.client.renderer.*;

public class Colour extends Color
{
    public Colour(final int rgb) {
        super(rgb);
    }
    
    public Colour(final int r, final int g, final int b, final int a) {
        super(r, g, b, a);
    }
    
    public Colour(final int rgba, final boolean hasalpha) {
        super(rgba, hasalpha);
    }
    
    public float getBrightness() {
        return Color.RGBtoHSB(this.getRed(), this.getGreen(), this.getBlue(), null)[2];
    }
    
    public Colour(final Colour colour, final int a) {
        super(colour.getRed(), colour.getGreen(), colour.getBlue(), a);
    }
    
    public void glColor() {
        GlStateManager.color(this.getRed() / 255.0f, this.getGreen() / 255.0f, this.getBlue() / 255.0f, this.getAlpha() / 255.0f);
    }
    
    public static Colour fromHSB(final float h, final float s, final float b) {
        return new Colour(Color.getHSBColor(h, s, b));
    }
    
    public Colour(final Color color) {
        super(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }
    
    public Colour(final int r, final int g, final int b) {
        super(r, g, b);
    }
    
    public float getHue() {
        return Color.RGBtoHSB(this.getRed(), this.getGreen(), this.getBlue(), null)[0];
    }
    
    public float getSaturation() {
        return Color.RGBtoHSB(this.getRed(), this.getGreen(), this.getBlue(), null)[1];
    }
}
