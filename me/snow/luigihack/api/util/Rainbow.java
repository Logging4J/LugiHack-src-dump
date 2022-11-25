//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util;

import java.awt.*;

public class Rainbow
{
    public static Color getRainbowColor(final float n, final float n2, final float n3) {
        return new Color(getRainbow(n, n2, n3));
    }
    
    public static Color getRainbowColor(final float n, final float n2, final float n3, final long n4) {
        return new Color(getRainbow(n, n2, n3, n4));
    }
    
    public static int getRainbow(final float n, final float saturation, final float brightness) {
        return Color.HSBtoRGB(System.currentTimeMillis() % 11520L / 11520.0f * n, saturation, brightness);
    }
    
    public static int getRainbow(final float n, final float saturation, final float brightness, final long n2) {
        return Color.HSBtoRGB((System.currentTimeMillis() + n2) % 11520L / 11520.0f * n, saturation, brightness);
    }
}
