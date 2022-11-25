//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.manager;

import java.awt.*;
import me.snow.luigihack.api.util.*;

public class ColorManager
{
    private /* synthetic */ float blue2;
    private /* synthetic */ Color color2;
    private /* synthetic */ float green;
    private /* synthetic */ float blue;
    private /* synthetic */ float green2;
    private /* synthetic */ float alpha;
    private /* synthetic */ float red2;
    private /* synthetic */ Color color;
    private /* synthetic */ float alpha2;
    private /* synthetic */ float red;
    
    public void setBlue2(final float blue2) {
        this.blue2 = blue2;
        this.updateColor2();
    }
    
    public void setGreen2(final float green2) {
        this.green2 = green2;
        this.updateColor2();
    }
    
    public void setRed(final float red) {
        this.red = red;
        this.updateColor();
    }
    
    public int getColorAsIntFullAlpha() {
        return ColorUtil.toRGBA(new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue(), 255));
    }
    
    public void updateColor() {
        this.setColor(new Color(this.red, this.green, this.blue, this.alpha));
    }
    
    public void setColor2(final Color color2) {
        this.color2 = color2;
    }
    
    public int getColorAsInt() {
        return ColorUtil.toRGBA(this.color);
    }
    
    public void setBlue(final float blue) {
        this.blue = blue;
        this.updateColor();
    }
    
    public void setAlpha(final float alpha) {
        this.alpha = alpha;
        this.updateColor();
    }
    
    public void setColor2(final int n, final int n2, final int n3, final int n4) {
        this.red2 = n / 255.0f;
        this.green2 = n2 / 255.0f;
        this.blue2 = n3 / 255.0f;
        this.alpha2 = n4 / 255.0f;
        this.updateColor2();
    }
    
    public void setColor(final Color color) {
        this.color = color;
    }
    
    public void setGreen(final float green) {
        this.green = green;
        this.updateColor();
    }
    
    public Color getColor2() {
        return this.color2;
    }
    
    public void updateColor2() {
        this.setColor2(new Color(this.red2, this.green2, this.blue2, this.alpha2));
    }
    
    public void setColor2(final float red2, final float green2, final float blue2, final float alpha2) {
        this.red2 = red2;
        this.green2 = green2;
        this.blue2 = blue2;
        this.alpha2 = alpha2;
        this.updateColor2();
    }
    
    public ColorManager() {
        this.red = 1.0f;
        this.green = 1.0f;
        this.blue = 1.0f;
        this.alpha = 1.0f;
        this.color = new Color(this.red, this.green, this.blue, this.alpha);
        this.red2 = 1.0f;
        this.green2 = 1.0f;
        this.blue2 = 1.0f;
        this.alpha2 = 1.0f;
        this.color2 = new Color(this.red2, this.green2, this.blue2, this.alpha2);
    }
    
    public int getColorWithAlpha(final int n) {
        return ColorUtil.toRGBA(new Color(this.red, this.green, this.blue, n / 255.0f));
    }
    
    public void setRed2(final float red2) {
        this.red2 = red2;
        this.updateColor2();
    }
    
    public int getColorAsIntFullAlpha2() {
        return ColorUtil.toRGBA(new Color(this.color2.getRed(), this.color2.getGreen(), this.color2.getBlue(), 255));
    }
    
    public int getColorWithAlpha2(final int n) {
        return ColorUtil.toRGBA(new Color(this.red2, this.green2, this.blue2, n / 255.0f));
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public void setColor(final float red, final float green, final float blue, final float alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
        this.updateColor();
    }
    
    public int getColorAsInt2() {
        return ColorUtil.toRGBA(this.color2);
    }
    
    public void setAlpha2(final float alpha2) {
        this.alpha2 = alpha2;
        this.updateColor2();
    }
    
    public void setColor(final int n, final int n2, final int n3, final int n4) {
        this.red = n / 255.0f;
        this.green = n2 / 255.0f;
        this.blue = n3 / 255.0f;
        this.alpha = n4 / 255.0f;
        this.updateColor();
    }
}
