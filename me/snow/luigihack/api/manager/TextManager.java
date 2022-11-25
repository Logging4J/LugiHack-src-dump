//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.manager;

import me.snow.luigihack.impl.*;
import me.snow.luigihack.impl.gui.font.*;
import me.snow.luigihack.*;
import java.awt.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.util.math.*;

public class TextManager extends Feature
{
    public /* synthetic */ int scaledWidth;
    public /* synthetic */ int scaleFactor;
    private /* synthetic */ boolean idling;
    private /* synthetic */ CustomFont customFont;
    private final /* synthetic */ Timer idleTimer;
    public /* synthetic */ int scaledHeight;
    
    public int getFontHeight() {
        if (LuigiHack.moduleManager.isModuleEnabled((Class)me.snow.luigihack.impl.modules.client.CustomFont.class)) {
            return this.customFont.getStringHeight("A");
        }
        return TextManager.mc.fontRenderer.FONT_HEIGHT;
    }
    
    public int getStringWidth(final String s) {
        if (LuigiHack.moduleManager.isModuleEnabled((Class)me.snow.luigihack.impl.modules.client.CustomFont.class)) {
            return this.customFont.getStringWidth(s);
        }
        return TextManager.mc.fontRenderer.getStringWidth(s);
    }
    
    public TextManager() {
        this.idleTimer = new Timer();
        this.customFont = new CustomFont(new Font("Verdana", 0, 17), true, false);
        this.updateResolution();
    }
    
    public void drawStringWithShadow(final String s, final float n, final float n2, final int n3) {
        this.drawString(s, n, n2, n3, true);
    }
    
    public String getIdleSign() {
        if (this.idleTimer.passedMs(500L)) {
            this.idling = !this.idling;
            this.idleTimer.reset();
        }
        if (this.idling) {
            return "_";
        }
        return "";
    }
    
    public Font getCurrentFont() {
        return this.customFont.getFont();
    }
    
    public void init(final boolean b) {
        final me.snow.luigihack.impl.modules.client.CustomFont customFont = (me.snow.luigihack.impl.modules.client.CustomFont)LuigiHack.moduleManager.getModuleByClass((Class)me.snow.luigihack.impl.modules.client.CustomFont.class);
        try {
            this.setFontRenderer(new Font(customFont.fontName.getValue(), customFont.fontStyle.getValue(), customFont.fontSize.getValue()), customFont.antiAlias.getValue(), customFont.fractionalMetrics.getValue());
        }
        catch (Exception ex) {}
    }
    
    public void drawRainbowString(final String s, final float n, final float n2, final int rgb, final float n3, final boolean b) {
        Color color = new Color(rgb);
        final float n4 = 1.0f / n3;
        s.split("§.");
        float hue = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null)[0];
        final float saturation = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null)[1];
        final float brightness = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null)[2];
        int n5 = 0;
        boolean b2 = true;
        int n6 = 0;
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            final char char2 = s.charAt(MathUtil.clamp(i + 1, 0, s.length() - 1));
            final boolean equals = String.valueOf(new StringBuilder().append(String.valueOf(char1)).append(char2)).equals("§r");
            if (equals) {
                b2 = false;
            }
            else if (String.valueOf(new StringBuilder().append(String.valueOf(char1)).append(char2)).equals("§+")) {
                b2 = true;
            }
            if (n6 != 0) {
                n6 = 0;
            }
            else {
                if (equals) {
                    this.drawString(s.substring(i), n + n5, n2, Color.WHITE.getRGB(), b);
                    break;
                }
                this.drawString(String.valueOf(char1).equals("§") ? "" : String.valueOf(char1), n + n5, n2, b2 ? color.getRGB() : Color.WHITE.getRGB(), b);
                if (String.valueOf(char1).equals("§")) {
                    n6 = 1;
                }
                n5 += this.getStringWidth(String.valueOf(char1));
                if (!String.valueOf(char1).equals(" ")) {
                    color = new Color(Color.HSBtoRGB(hue, saturation, brightness));
                    hue += n4;
                }
            }
        }
    }
    
    public void updateResolution() {
        this.scaledWidth = TextManager.mc.displayWidth;
        this.scaledHeight = TextManager.mc.displayHeight;
        this.scaleFactor = 1;
        final boolean isUnicode = TextManager.mc.isUnicode();
        int guiScale = TextManager.mc.gameSettings.guiScale;
        if (guiScale == 0) {
            guiScale = 1000;
        }
        while (this.scaleFactor < guiScale && this.scaledWidth / (this.scaleFactor + 1) >= 320 && this.scaledHeight / (this.scaleFactor + 1) >= 240) {
            ++this.scaleFactor;
        }
        if (isUnicode && this.scaleFactor % 2 != 0 && this.scaleFactor != 1) {
            --this.scaleFactor;
        }
        final double n = this.scaledWidth / (double)this.scaleFactor;
        final double n2 = this.scaledHeight / (double)this.scaleFactor;
        this.scaledWidth = MathHelper.ceil(n);
        this.scaledHeight = MathHelper.ceil(n2);
    }
    
    public float drawString(final String s, final float n, final float n2, final int n3, final boolean b) {
        if (!LuigiHack.moduleManager.isModuleEnabled((Class)me.snow.luigihack.impl.modules.client.CustomFont.class)) {
            return (float)TextManager.mc.fontRenderer.drawString(s, n, n2, n3, b);
        }
        if (b) {
            return this.customFont.drawStringWithShadow(s, n, n2, n3);
        }
        return this.customFont.drawString(s, n, n2, n3);
    }
    
    public void setFontRenderer(final Font font, final boolean b, final boolean b2) {
        this.customFont = new CustomFont(font, b, b2);
    }
}
