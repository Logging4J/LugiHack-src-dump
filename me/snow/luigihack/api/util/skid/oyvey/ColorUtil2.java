//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util.skid.oyvey;

import java.awt.*;
import me.snow.luigihack.impl.modules.combat.autocrystal.*;
import me.snow.luigihack.impl.modules.client.*;

public class ColorUtil2
{
    public static int toARGB(final int r, final int g, final int b, final int a) {
        return new Color(r, g, b, a).getRGB();
    }
    
    public static int toRGBA(final double[] array) {
        if (array.length != 4) {
            throw new IllegalArgumentException("colors[] must have a length of 4!");
        }
        return toRGBA((float)array[0], (float)array[1], (float)array[2], (float)array[3]);
    }
    
    public static Color rainbowTEST22(final int n) {
        return Color.getHSBColor((float)(Math.ceil((System.currentTimeMillis() + n) / 20.0) % 360.0 / 360.0), (float)AutoCrystal.getInstance().rainbowSaturationAA22.getValue() / 255.0f, (float)AutoCrystal.getInstance().rainbowBrightnessAA22.getValue() / 255.0f);
    }
    
    public static int toRGBA(final float n, final float n2, final float n3, final float n4) {
        return toRGBA((int)(n * 255.0f), (int)(n2 * 255.0f), (int)(n3 * 255.0f), (int)(n4 * 255.0f));
    }
    
    public ColorUtil2(final int n, final int n2, final int n3, final int n4) {
    }
    
    public static Color rainbow(final int n) {
        return Color.getHSBColor((float)(Math.ceil((System.currentTimeMillis() + n) / 20.0) % 360.0 / 360.0), (float)Global.getInstance().rainbowSaturation.getValue() / 255.0f, (float)Global.getInstance().rainbowBrightness.getValue() / 255.0f);
    }
    
    public static int toRGBA(final int n, final int n2, final int n3, final int n4) {
        return (n << 16) + (n2 << 8) + n3 + (n4 << 24);
    }
    
    public static int toRGBA(final float[] array) {
        if (array.length != 4) {
            throw new IllegalArgumentException("colors[] must have a length of 4!");
        }
        return toRGBA(array[0], array[1], array[2], array[3]);
    }
    
    public static int toRGBA(final Color color) {
        return toRGBA(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }
    
    public static int toRGBA(final int n, final int n2, final int n3) {
        return toRGBA(n, n2, n3, 255);
    }
    
    public static Color invert(final Color color) {
        final int alpha = color.getAlpha();
        final int r = 255 - color.getRed();
        final int g = 255 - color.getGreen();
        final int b = 255 - color.getBlue();
        if (r + g + b > 740 || r + g + b < 20) {
            return new Color(255, 255, 40, alpha);
        }
        return new Color(r, g, b, alpha);
    }
}
