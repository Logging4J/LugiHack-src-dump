//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.turok.draw;

import org.lwjgl.opengl.*;

public class GL
{
    public static void resize(final int n, final int n2, final float n3) {
        GL11.glEnable(3553);
        GL11.glEnable(3042);
        GL11.glTranslatef((float)n, (float)n2, 0.0f);
        GL11.glScalef(n3, n3, 1.0f);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }
    
    public static void start() {
        GL11.glDisable(3553);
    }
    
    public static void finish() {
        GL11.glDisable(3553);
        GL11.glDisable(3042);
    }
    
    public static void resize(final int n, final int n2, final float n3, final String s) {
        GL11.glScalef(1.0f / n3, 1.0f / n3, 1.0f);
        GL11.glTranslatef((float)(-n), (float)(-n2), 0.0f);
        GL11.glDisable(3553);
    }
    
    public static void draw_rect(final int n, final int n2, final int n3, final int n4) {
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glBegin(7);
        GL11.glVertex2d((double)(n + n3), (double)n2);
        GL11.glVertex2d((double)n, (double)n2);
        GL11.glVertex2d((double)n, (double)(n2 + n4));
        GL11.glVertex2d((double)(n + n3), (double)(n2 + n4));
        GL11.glEnd();
    }
    
    public static void color(final float n, final float n2, final float n3, final float n4) {
        GL11.glColor4f(n / 255.0f, n2 / 255.0f, n3 / 255.0f, n4 / 255.0f);
    }
}
