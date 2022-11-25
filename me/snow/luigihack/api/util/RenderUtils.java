//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util;

import java.awt.*;
import java.util.*;
import org.lwjgl.opengl.*;
import java.nio.*;
import java.io.*;
import me.snow.luigihack.api.util.gl.*;
import net.minecraft.util.math.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.client.renderer.*;

public class RenderUtils
{
    public static void drawBox(final AxisAlignedBB axisAlignedBB, final Color color, final int n) {
        drawBox(axisAlignedBB, color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha(), n);
    }
    
    public static void drawRoundedRectangle(final double n, final double n2, final double n3, final double n4, final double n5, final GradientDirection gradientDirection, final Color color, final Color color2) {
        if (n3 < n5 * 2.0 || n4 < n5 * 2.0) {
            return;
        }
        GL11.glShadeModel(7425);
        final Color[] array = (Color[])checkColorDirection(gradientDirection, color, color2);
        setColor(array[0]);
        drawArc(n + n3 - n5, n2 + n4 - n5, n5, 0.0, 90.0, 16);
        setColor(array[1]);
        drawArc(n + n5, n2 + n4 - n5, n5, 90.0, 180.0, 16);
        setColor(array[2]);
        drawArc(n + n5, n2 + n5, n5, 180.0, 270.0, 16);
        setColor(array[3]);
        drawArc(n + n3 - n5, n2 + n5, n5, 270.0, 360.0, 16);
        drawGradientRect(n + n5, n2, n3 - n5 * 2.0, n5, GradientDirection.LeftToRight, array[2], array[3]);
        drawGradientRect(n + n5, n2 + n4 - n5, n3 - n5 * 2.0, n5, GradientDirection.LeftToRight, array[1], array[0]);
        drawGradientRect(n, n2 + n5, n5, n4 - n5 * 2.0, GradientDirection.DownToUp, array[1], array[2]);
        drawGradientRect(n + n3 - n5, n2 + n5, n5, n4 - n5 * 2.0, GradientDirection.DownToUp, array[0], array[3]);
        drawGradientRect(n + n5, n2 + n5, n3 - n5 * 2.0, n4 - n5 * 2.0, gradientDirection, color, color2);
        GL11.glShadeModel(7424);
    }
    
    public static void setLineWidth(final float n) {
        GL11.glLineWidth(n);
    }
    
    public static void setColor(final int n, final int n2, final int n3) {
        setColor(n, n2, n3, 255);
    }
    
    public static void drawRect(final double n, final double n2, final double n3, final double n4, final Color color) {
        drawGradientRect(n, n2, n3, n4, GradientDirection.Normal, color, color);
    }
    
    public static void setColorHA(final int rgba) {
        setColor(new Color(rgba, true));
    }
    
    public static void drawArcOutline(final double n, final double n2, final double n3, final double n4, final double n5, final int n6) {
        GL11.glDisable(3553);
        GL11.glEnable(3042);
        GL11.glBegin(3);
        for (int n7 = (int)(n6 / (360.0 / n4)); n7 <= n6 / (360.0 / n5); ++n7) {
            final double n8 = 6.283185307179586 * n7 / n6;
            GL11.glVertex2d(n + Math.cos(n8) * n3, n2 + Math.sin(n8) * n3);
        }
        GL11.glEnd();
        GL11.glDisable(3042);
        GL11.glEnable(3553);
    }
    
    public static void drawArc(final double n, final double n2, final double n3, final double n4, final double n5, final int n6) {
        GL11.glDisable(3553);
        GL11.glEnable(3042);
        GL11.glBegin(4);
        for (int n7 = (int)(n6 / (360.0 / n4)) + 1; n7 <= n6 / (360.0 / n5); ++n7) {
            final double n8 = 6.283185307179586 * (n7 - 1) / n6;
            final double n9 = 6.283185307179586 * n7 / n6;
            GL11.glVertex2d(n, n2);
            GL11.glVertex2d(n + Math.cos(n9) * n3, n2 + Math.sin(n9) * n3);
            GL11.glVertex2d(n + Math.cos(n8) * n3, n2 + Math.sin(n8) * n3);
        }
        if (n5 == 360.0) {
            GL11.glVertex2d(n, n2);
            GL11.glVertex2d(n + Math.cos(360.0) * n3, n2 + Math.sin(360.0) * n3);
        }
        GL11.glEnd();
        GL11.glDisable(3042);
        GL11.glEnable(3553);
    }
    
    public static void drawCircle(final double n, final double n2, final double n3, final Color color) {
        setColor(color);
        drawArc(n, n2, n3, 0.0, 360.0, 16);
    }
    
    private static Object[] checkColorDirection(final GradientDirection gradientDirection, final Color val, final Color color) {
        final Color[] array = new Color[4];
        if (gradientDirection == GradientDirection.Normal) {
            Arrays.fill(array, val);
        }
        else if (gradientDirection == GradientDirection.DownToUp) {
            array[1] = (array[0] = val);
            array[3] = (array[2] = color);
        }
        else if (gradientDirection == GradientDirection.UpToDown) {
            array[1] = (array[0] = color);
            array[3] = (array[2] = val);
        }
        else if (gradientDirection == GradientDirection.RightToLeft) {
            array[0] = val;
            array[2] = (array[1] = color);
            array[3] = val;
        }
        else if (gradientDirection == GradientDirection.LeftToRight) {
            array[0] = color;
            array[2] = (array[1] = val);
            array[3] = color;
        }
        else {
            Arrays.fill(array, Color.WHITE);
        }
        return array;
    }
    
    public static void drawTriangle(final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final Color color) {
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
        GL11.glBegin(6);
        GL11.glVertex2d(n, n2);
        GL11.glVertex2d(n3, n4);
        GL11.glVertex2d(n5, n6);
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }
    
    public static void drawHalfRoundedRectangle(final double n, final double n2, final double n3, final double n4, double n5, final HalfRoundedDirection halfRoundedDirection, final Color color) {
        setColor(color);
        if (n5 > n4) {
            n5 = n4;
        }
        if (halfRoundedDirection == HalfRoundedDirection.Top) {
            drawArc(n + n5, n2 + n5, n5, 180.0, 270.0, 32);
            drawArc(n + n3 - n5, n2 + n5, n5, 270.0, 360.0, 32);
            drawRect(n, n2 + n5, n5, n4 - n5, color);
            drawRect(n + n3 - n5, n2 + n5, n5, n4 - n5, color);
            drawRect(n + n5, n2, n3 - n5 * 2.0, n4, color);
        }
        else if (halfRoundedDirection == HalfRoundedDirection.Bottom) {
            drawArc(n + n5, n2 + n4 - n5, n5, 90.0, 180.0, 32);
            drawArc(n + n3 - n5, n2 + n4 - n5, n5, 0.0, 90.0, 32);
            drawRect(n, n2, n5, n4 - n5, color);
            drawRect(n + n3 - n5, n2, n5, n4 - n5, color);
            drawRect(n + n5, n2, n3 - n5 * 2.0, n4, color);
        }
        else if (halfRoundedDirection == HalfRoundedDirection.Left) {
            drawArc(n + n5, n2 + n5, n5, 180.0, 270.0, 32);
            drawArc(n + n5, n2 + n4 - n5, n5, 90.0, 180.0, 32);
            drawRect(n, n2 + n5, n3, n4 - n5 * 2.0, color);
            drawRect(n + n5, n2, n3 - n5, n5, color);
            drawRect(n + n5, n2 + n4 - n5, n3 - n5, n5, color);
        }
        else if (halfRoundedDirection == HalfRoundedDirection.Right) {
            drawArc(n + n3 - n5, n2 + n5, n5, 270.0, 360.0, 32);
            drawArc(n + n3 - n5, n2 + n4 - n5, n5, 0.0, 90.0, 32);
            drawRect(n, n2, n3 - n5, n5, color);
            drawRect(n, n2 + n4 - n5, n3 - n5, n5, color);
            drawRect(n, n2 + n5, n3, n4 - n5 * 2.0, color);
        }
    }
    
    public static void clearViewPort() {
        GL41.glClearDepthf(1.0f);
        GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glClear(1280);
        GL11.glDisable(2929);
        GL11.glDepthFunc(515);
        GL11.glDepthMask(false);
    }
    
    public static void drawTexture(final double n, final double n2, final double n3, final double n4) {
        drawTexture(n, n2, n3, n4, 255);
    }
    
    public static void setColor(final int r, final int g, final int b, final int a) {
        setColor(new Color(r, g, b, a));
    }
    
    public static void drawGradientRect(final double n, final double n2, final double n3, final double n4, final GradientDirection gradientDirection, final Color color, final Color color2) {
        GL11.glDisable(3553);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glShadeModel(7425);
        final Color[] array = (Color[])checkColorDirection(gradientDirection, color, color2);
        GL11.glBegin(7);
        setColor(array[0]);
        GL11.glVertex2d(n + n3, n2);
        setColor(array[1]);
        GL11.glVertex2d(n, n2);
        setColor(array[2]);
        GL11.glVertex2d(n, n2 + n4);
        setColor(array[3]);
        GL11.glVertex2d(n + n3, n2 + n4);
        GL11.glEnd();
        GL11.glDisable(3042);
        GL11.glEnable(3553);
    }
    
    public static void drawTexture(final double n, final double n2, final double n3, final double n4, final int n5) {
        GL11.glEnable(3553);
        GL11.glEnable(3042);
        setColor(255, 255, 255, n5);
        GL11.glPushMatrix();
        GL11.glBegin(4);
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex2d(n + n3, n2);
        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex2d(n, n2);
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex2d(n, n2 + n4);
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex2d(n, n2 + n4);
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex2d(n + n3, n2 + n4);
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex2d(n + n3, n2);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glDisable(3042);
    }
    
    public static void bindTexture(final int n) {
        GL11.glBindTexture(3553, n);
    }
    
    public static void setViewPort(final double n, final double n2, final double n3, final double n4) {
        GL41.glClearDepthf(1.0f);
        GL11.glClear(256);
        GL11.glColorMask(false, false, false, false);
        GL11.glDepthFunc(513);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        drawRect(n, n2, n3, n4, Color.WHITE);
        GL11.glColorMask(true, true, true, true);
        GL11.glDepthMask(true);
        GL11.glDepthFunc(514);
    }
    
    public static void drawRoundedRectangleOutline(final double n, final double n2, final double n3, final double n4, final double n5, final float n6, final Color color) {
        drawRoundedRectangleOutline(n, n2, n3, n4, n5, n6, GradientDirection.Normal, color, color);
    }
    
    public static void drawBox(final AxisAlignedBB axisAlignedBB, final int n, final int n2, final int n3, final int n4, final int n5) {
        drawBox((float)axisAlignedBB.minX, (float)axisAlignedBB.minY, (float)axisAlignedBB.minZ, (float)axisAlignedBB.maxX - (float)axisAlignedBB.minX, (float)axisAlignedBB.maxY - (float)axisAlignedBB.minY, (float)axisAlignedBB.maxZ - (float)axisAlignedBB.minZ, n, n2, n3, n4, n5);
    }
    
    public static void drawRectOutline(final double n, final double n2, final double n3, final double n4, final Color color) {
        drawGradientRectOutline(n, n2, n3, n4, GradientDirection.Normal, color, color);
    }
    
    public static void drawLine(final double n, final double n2, final double n3, final double n4, final float n5, final Color color, final Color color2) {
        GL11.glDisable(3553);
        GL11.glEnable(3042);
        GL11.glLineWidth(n5);
        GL11.glShadeModel(7425);
        GL11.glBegin(2);
        setColor(color);
        GL11.glVertex2d(n, n2);
        setColor(color2);
        GL11.glVertex2d(n3, n4);
        GL11.glEnd();
        GL11.glShadeModel(7424);
        GL11.glDisable(3042);
        GL11.glEnable(3553);
    }
    
    public static GLTexture loadTexture(final InputStream inputStream) {
        try {
            final PNGDecoder pngDecoder = new PNGDecoder(inputStream);
            final ByteBuffer allocateDirect = ByteBuffer.allocateDirect(4 * pngDecoder.getWidth() * pngDecoder.getHeight());
            pngDecoder.decode(allocateDirect, pngDecoder.getWidth() * 4, PNGDecoder.Format.RGBA);
            allocateDirect.flip();
            final int glGenTextures = GL11.glGenTextures();
            GL11.glBindTexture(3553, glGenTextures);
            GL11.glPixelStorei(3317, 1);
            GL11.glTexParameterf(3553, 10241, 9728.0f);
            GL11.glTexParameterf(3553, 10240, 9728.0f);
            GL11.glTexImage2D(3553, 0, 6408, pngDecoder.getWidth(), pngDecoder.getHeight(), 0, 6408, 5121, allocateDirect);
            GL11.glBindTexture(3553, 0);
            return new GLTexture(glGenTextures);
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public static void setColor(final Color color) {
        GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
    }
    
    public static void drawRoundedRectangle(final double n, final double n2, final double n3, final double n4, final double n5, final Color color) {
        drawRoundedRectangle(n, n2, n3, n4, n5, GradientDirection.Normal, color, color);
    }
    
    public static int toRGBA(final Color color) {
        return color.getRed() | color.getGreen() << 8 | color.getBlue() << 16 | color.getAlpha() << 24;
    }
    
    public static void drawLine(final double n, final double n2, final double n3, final double n4, final float n5, final Color color) {
        drawLine(n, n2, n3, n4, n5, color, color);
    }
    
    public static void drawTriangleOutline(final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final Color color) {
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
        GL11.glBegin(2);
        GL11.glVertex2d(n, n2);
        GL11.glVertex2d(n3, n4);
        GL11.glVertex2d(n5, n6);
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }
    
    public static void prepare3D() {
        GL11.glPushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.shadeModel(7425);
        GlStateManager.disableDepth();
        GlStateManager.glLineWidth(1.0f);
    }
    
    public static void drawRoundedRectangleOutline(final double n, final double n2, final double n3, final double n4, final double n5, final float n6, final GradientDirection gradientDirection, final Color color, final Color color2) {
        final Color[] array = (Color[])checkColorDirection(gradientDirection, color, color2);
        GL11.glLineWidth(n6);
        setColor(array[0]);
        drawArcOutline(n + n3 - n5, n2 + n4 - n5, n5, 0.0, 90.0, 16);
        setColor(array[1]);
        drawArcOutline(n + n5, n2 + n4 - n5, n5, 90.0, 180.0, 16);
        setColor(array[2]);
        drawArcOutline(n + n5, n2 + n5, n5, 180.0, 270.0, 16);
        setColor(array[3]);
        drawArcOutline(n + n3 - n5, n2 + n5, n5, 270.0, 360.0, 16);
        drawLine(n + n5, n2, n + n3 - n5, n2, n6, array[2], array[3]);
        drawLine(n + n5, n2 + n4, n + n3 - n5, n2 + n4, n6, array[1], array[0]);
        drawLine(n, n2 + n5, n, n2 + n4 - n5, n6, array[1], array[2]);
        drawLine(n + n3, n2 + n5, n + n3, n2 + n4 - n5, n6, array[0], array[3]);
    }
    
    public static void drawCircleOutline(final double n, final double n2, final double n3) {
        drawArcOutline(n, n2, n3, 0.0, 360.0, 16);
    }
    
    public static void drawColoredCircle(final double n, final double n2, final double n3, final float saturation, final float n4) {
        GL11.glDisable(3553);
        GL11.glPushMatrix();
        GL11.glLineWidth(1.0f);
        GL11.glEnable(2848);
        GL11.glShadeModel(7425);
        GL11.glBegin(3);
        for (int i = 0; i < 360; ++i) {
            setColor(Color.HSBtoRGB(0.0f, 0.0f, n4));
            GL11.glVertex2d(n, n2);
            setColor(Color.HSBtoRGB(i / 360.0f, saturation, n4));
            GL11.glVertex2d(n + Math.sin(Math.toRadians(i)) * n3, n2 + Math.cos(Math.toRadians(i)) * n3);
        }
        GL11.glEnd();
        GL11.glShadeModel(7424);
        GL11.glDisable(2848);
        GL11.glPopMatrix();
        GL11.glEnable(3553);
    }
    
    public static void drawCircleOutline(final double n, final double n2, final double n3, final Color color) {
        setColor(color);
        drawArcOutline(n, n2, n3, 0.0, 360.0, 16);
    }
    
    public static Color alpha(final Color color, final int a) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), a);
    }
    
    public static void release3D() {
        GlStateManager.glLineWidth(1.0f);
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.enableDepth();
        GlStateManager.enableCull();
        MelonTessellator.releaseGL();
        GL11.glPopMatrix();
    }
    
    public static void drawBoundingBox(final AxisAlignedBB axisAlignedBB, final float n, final Color color) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glDisable(2929);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glDepthMask(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(n);
        setColor(color);
        GL11.glBegin(3);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glEnd();
        GL11.glBegin(3);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glEnd();
        GL11.glBegin(3);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glEnd();
        GL11.glDisable(2848);
        GL11.glDepthMask(true);
        GL11.glEnable(2929);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
    
    public static void drawBlockBox(final BlockPos blockPos, Color alpha, final Boolean b) {
        final double n = blockPos.getX();
        final double n2 = blockPos.getY();
        final double n3 = blockPos.getZ();
        alpha = alpha(alpha, alpha.getAlpha() - 20);
        GlStateManager.pushMatrix();
        final double n4 = n - Minecraft.getMinecraft().getRenderManager().viewerPosX;
        final double n5 = n2 - Minecraft.getMinecraft().getRenderManager().viewerPosY;
        final double n6;
        GlStateManager.translate(n4, n5, n6 = n3 - Minecraft.getMinecraft().getRenderManager().viewerPosZ);
        GlStateManager.disableDepth();
        GlStateManager.enableBlend();
        GlStateManager.disableLighting();
        GlStateManager.disableTexture2D();
        GL11.glBlendFunc(770, 771);
        final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(((boolean)b) ? 1 : 7, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(alpha.getRed(), alpha.getGreen(), alpha.getBlue(), alpha.getAlpha()).endVertex();
        getInstance.draw();
        GlStateManager.enableDepth();
        GlStateManager.depthMask(true);
        GlStateManager.translate(-n4, -n5, -n6);
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
    }
    
    public static void setColor(final int rgb) {
        setColor(new Color(rgb));
    }
    
    public static void drawGradientRectOutline(final double n, final double n2, final double n3, final double n4, final GradientDirection gradientDirection, final Color color, final Color color2) {
        GL11.glDisable(3553);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glShadeModel(7425);
        final Color[] array = (Color[])checkColorDirection(gradientDirection, color, color2);
        GL11.glBegin(2);
        GL11.glColor4f(array[2].getRed() / 255.0f, array[2].getGreen() / 255.0f, array[2].getBlue() / 255.0f, array[2].getAlpha() / 255.0f);
        GL11.glVertex2d(n + n3, n2);
        GL11.glColor4f(array[3].getRed() / 255.0f, array[3].getGreen() / 255.0f, array[3].getBlue() / 255.0f, array[3].getAlpha() / 255.0f);
        GL11.glVertex2d(n, n2);
        GL11.glColor4f(array[0].getRed() / 255.0f, array[0].getGreen() / 255.0f, array[0].getBlue() / 255.0f, array[0].getAlpha() / 255.0f);
        GL11.glVertex2d(n, n2 + n4);
        GL11.glColor4f(array[1].getRed() / 255.0f, array[1].getGreen() / 255.0f, array[1].getBlue() / 255.0f, array[1].getAlpha() / 255.0f);
        GL11.glVertex2d(n + n3, n2 + n4);
        GL11.glEnd();
        GL11.glDisable(3042);
        GL11.glEnable(3553);
    }
    
    public static void drawCircle(final double n, final double n2, final double n3) {
        drawArc(n, n2, n3, 0.0, 360.0, 16);
    }
    
    public static void renderESP(final AxisAlignedBB axisAlignedBB, final float n, final Color color, final RenderMode renderMode) {
        final double n2 = axisAlignedBB.minX + (axisAlignedBB.maxX - axisAlignedBB.minX) / 2.0;
        final double n3 = axisAlignedBB.minY + (axisAlignedBB.maxY - axisAlignedBB.minY) / 2.0;
        final double n4 = axisAlignedBB.minZ + (axisAlignedBB.maxZ - axisAlignedBB.minZ) / 2.0;
        final double n5 = axisAlignedBB.maxX - n2;
        final double n6 = n5 * n;
        final double n7 = n5 * n;
        final double n8 = n5 * n;
        final AxisAlignedBB axisAlignedBB2 = new AxisAlignedBB(n2 - n6, n3 - n7, n4 - n8, n2 + n6, n3 + n7, n4 + n8);
        GL11.glPushMatrix();
        switch (renderMode) {
            case SOLID: {
                drawBox(axisAlignedBB2, color, 63);
                break;
            }
            case OUTLINE: {
                drawBoundingBox(axisAlignedBB2, 1.5f, new Color(color.getRed(), color.getGreen(), color.getBlue(), 255));
                break;
            }
            case SOLID_FLAT: {
                drawBox(axisAlignedBB2, color, 1);
                break;
            }
            case FULL: {
                drawBox(axisAlignedBB2, color, 63);
                drawBoundingBox(axisAlignedBB2, 1.5f, new Color(color.getRed(), color.getGreen(), color.getBlue(), 255));
                break;
            }
        }
        GL11.glPopMatrix();
    }
    
    public static void drawBox(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final int n7, final int n8, final int n9, final int n10, final int n11) {
        GL11.glPushMatrix();
        GL11.glBlendFunc(770, 771);
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.glLineWidth(1.5f);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.disableLighting();
        GlStateManager.disableCull();
        GlStateManager.enableAlpha();
        GlStateManager.color(1.0f, 1.0f, 1.0f);
        setColor(n7, n8, n9, n10);
        GL11.glBegin(7);
        if ((n11 & 0x1) != 0x0) {
            GL11.glVertex3d((double)(n + n4), (double)n2, (double)n3);
            GL11.glVertex3d((double)(n + n4), (double)n2, (double)(n3 + n6));
            GL11.glVertex3d((double)n, (double)n2, (double)(n3 + n6));
            GL11.glVertex3d((double)n, (double)n2, (double)n3);
        }
        if ((n11 & 0x2) != 0x0) {
            GL11.glVertex3d((double)(n + n4), (double)(n2 + n5), (double)n3);
            GL11.glVertex3d((double)n, (double)(n2 + n5), (double)n3);
            GL11.glVertex3d((double)n, (double)(n2 + n5), (double)(n3 + n6));
            GL11.glVertex3d((double)(n + n4), (double)(n2 + n5), (double)(n3 + n6));
        }
        if ((n11 & 0x4) != 0x0) {
            GL11.glVertex3d((double)(n + n4), (double)n2, (double)n3);
            GL11.glVertex3d((double)n, (double)n2, (double)n3);
            GL11.glVertex3d((double)n, (double)(n2 + n5), (double)n3);
            GL11.glVertex3d((double)(n + n4), (double)(n2 + n5), (double)n3);
        }
        if ((n11 & 0x8) != 0x0) {
            GL11.glVertex3d((double)n, (double)n2, (double)(n3 + n6));
            GL11.glVertex3d((double)(n + n4), (double)n2, (double)(n3 + n6));
            GL11.glVertex3d((double)(n + n4), (double)(n2 + n5), (double)(n3 + n6));
            GL11.glVertex3d((double)n, (double)(n2 + n5), (double)(n3 + n6));
        }
        if ((n11 & 0x10) != 0x0) {
            GL11.glVertex3d((double)n, (double)n2, (double)n3);
            GL11.glVertex3d((double)n, (double)n2, (double)(n3 + n6));
            GL11.glVertex3d((double)n, (double)(n2 + n5), (double)(n3 + n6));
            GL11.glVertex3d((double)n, (double)(n2 + n5), (double)n3);
        }
        if ((n11 & 0x20) != 0x0) {
            GL11.glVertex3d((double)(n + n4), (double)n2, (double)(n3 + n6));
            GL11.glVertex3d((double)(n + n4), (double)n2, (double)n3);
            GL11.glVertex3d((double)(n + n4), (double)(n2 + n5), (double)n3);
            GL11.glVertex3d((double)(n + n4), (double)(n2 + n5), (double)(n3 + n6));
        }
        GL11.glEnd();
        GlStateManager.enableCull();
        GlStateManager.depthMask(true);
        GlStateManager.enableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.enableDepth();
        GlStateManager.color(1.0f, 1.0f, 1.0f);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glPopMatrix();
    }
    
    public enum HalfRoundedDirection
    {
        Left, 
        Right, 
        Top, 
        Bottom;
    }
    
    public enum RenderMode
    {
        OUTLINE, 
        FULL, 
        SOLID, 
        SOLID_FLAT;
    }
    
    public enum GradientDirection
    {
        Normal, 
        LeftToRight, 
        UpToDown, 
        RightToLeft, 
        DownToUp;
    }
}
