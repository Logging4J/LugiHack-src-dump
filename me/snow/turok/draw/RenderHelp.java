//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.turok.draw;

import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import java.awt.*;
import java.util.*;
import net.minecraft.util.math.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.client.renderer.vertex.*;

public class RenderHelp extends Tessellator
{
    public static /* synthetic */ RenderHelp INSTANCE;
    
    public static void prepare_gl() {
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
    }
    
    public static void draw_gradiant_outline(final BufferBuilder bufferBuilder, final double n, final double n2, final double n3, final double n4, final Color color, final Color color2, final String s) {
        final List<String> list = Arrays.asList(s.split("-"));
        final boolean equalsIgnoreCase = s.equalsIgnoreCase("all");
        if (list.contains("northwest") || equalsIgnoreCase) {
            draw_gradiant_line(bufferBuilder, n, n2, n3, n, n2 + n4, n3, color, color2);
        }
        if (list.contains("northeast") || equalsIgnoreCase) {
            draw_gradiant_line(bufferBuilder, n + 1.0, n2, n3, n + 1.0, n2 + n4, n3, color, color2);
        }
        if (list.contains("southwest") || equalsIgnoreCase) {
            draw_gradiant_line(bufferBuilder, n, n2, n3 + 1.0, n, n2 + n4, n3 + 1.0, color, color2);
        }
        if (list.contains("southeast") || equalsIgnoreCase) {
            draw_gradiant_line(bufferBuilder, n + 1.0, n2, n3 + 1.0, n + 1.0, n2 + n4, n3 + 1.0, color, color2);
        }
    }
    
    public static void draw_gradiant_line(final BufferBuilder bufferBuilder, final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final Color color, final Color color2) {
        bufferBuilder.pos(n, n2, n3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(n4, n5, n6).color(color2.getRed(), color2.getGreen(), color2.getBlue(), color2.getAlpha()).endVertex();
    }
    
    public static void draw_cube_line(final BlockPos blockPos, final int n, final String s) {
        draw_cube_line(blockPos, n >>> 16 & 0xFF, n >>> 8 & 0xFF, n & 0xFF, n >>> 24 & 0xFF, s);
    }
    
    public static void draw_cube(final BlockPos blockPos, final int n, final int n2, final int n3, final int n4, final String s) {
        draw_cube(RenderHelp.INSTANCE.getBuffer(), (float)blockPos.getX(), (float)blockPos.getY(), (float)blockPos.getZ(), 1.0f, 1.0f, 1.0f, n, n2, n3, n4, s);
    }
    
    public static void draw_cube_line(final BufferBuilder bufferBuilder, final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final int n7, final int n8, final int n9, final int n10, final float n11, final String s) {
        GL11.glLineWidth(n11);
        if (Arrays.asList(s.split("-")).contains("downwest") || s.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)n, (double)n2, (double)n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)n, (double)n2, (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
        }
        if (Arrays.asList(s.split("-")).contains("upwest") || s.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)n, (double)(n2 + n5), (double)n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)n, (double)(n2 + n5), (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
        }
        if (Arrays.asList(s.split("-")).contains("downeast") || s.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)(n + n4), (double)n2, (double)n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)(n + n4), (double)n2, (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
        }
        if (Arrays.asList(s.split("-")).contains("upeast") || s.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)(n + n4), (double)(n2 + n5), (double)n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)(n + n4), (double)(n2 + n5), (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
        }
        if (Arrays.asList(s.split("-")).contains("downnorth") || s.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)n, (double)n2, (double)n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)(n + n4), (double)n2, (double)n3).color(n7, n8, n9, n10).endVertex();
        }
        if (Arrays.asList(s.split("-")).contains("upnorth") || s.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)n, (double)(n2 + n5), (double)n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)(n + n4), (double)(n2 + n5), (double)n3).color(n7, n8, n9, n10).endVertex();
        }
        if (Arrays.asList(s.split("-")).contains("downsouth") || s.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)n, (double)n2, (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)(n + n4), (double)n2, (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
        }
        if (Arrays.asList(s.split("-")).contains("upsouth") || s.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)n, (double)(n2 + n5), (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)(n + n4), (double)(n2 + n5), (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
        }
        if (Arrays.asList(s.split("-")).contains("nortwest") || s.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)n, (double)n2, (double)n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)n, (double)(n2 + n5), (double)n3).color(n7, n8, n9, n10).endVertex();
        }
        if (Arrays.asList(s.split("-")).contains("norteast") || s.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)(n + n4), (double)n2, (double)n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)(n + n4), (double)(n2 + n5), (double)n3).color(n7, n8, n9, n10).endVertex();
        }
        if (Arrays.asList(s.split("-")).contains("southweast") || s.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)n, (double)n2, (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)n, (double)(n2 + n5), (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
        }
        if (Arrays.asList(s.split("-")).contains("southeast") || s.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)(n + n4), (double)n2, (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)(n + n4), (double)(n2 + n5), (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
        }
    }
    
    public static void prepare_gl_2d() {
        GL11.glHint(3154, 4354);
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.shadeModel(7425);
        GlStateManager.depthMask(false);
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.disableTexture2D();
        GlStateManager.disableLighting();
        GlStateManager.disableCull();
        GlStateManager.enableAlpha();
        GL11.glEnable(2848);
        GL11.glEnable(34383);
    }
    
    static {
        RenderHelp.INSTANCE = new RenderHelp();
    }
    
    public static void release() {
        render();
        release_gl();
    }
    
    public static void prepare(final String s) {
        int n = 0;
        if (s.equalsIgnoreCase("quads")) {
            n = 7;
        }
        else if (s.equalsIgnoreCase("lines")) {
            n = 1;
        }
        prepare_gl();
        begin(n);
    }
    
    public static void draw_triangle_line(final BufferBuilder bufferBuilder, final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final int n7, final int n8, final int n9, final int n10, final float n11, final String s) {
        GL11.glLineWidth(n11);
        if (Arrays.asList(s.split("-")).contains("downwest") || s.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)n, (double)n2, (double)n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos(n + 0.5, n2 + 0.5, n3 + 0.5).color(n7, n8, n9, n10).endVertex();
        }
        if (Arrays.asList(s.split("-")).contains("upwest") || s.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)n, (double)(n2 + n5), (double)n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos(n + 0.5, n2 + 0.5, n3 + 0.5).color(n7, n8, n9, n10).endVertex();
        }
        if (Arrays.asList(s.split("-")).contains("downeast") || s.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)n, (double)n2, (double)n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos(n + 0.5, n2 + 0.5, n3 + 0.5).color(n7, n8, n9, n10).endVertex();
        }
        if (Arrays.asList(s.split("-")).contains("upeast") || s.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)(n + n4), (double)(n2 + n5), (double)n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos(n + 0.5, n2 + 0.5, n3 + 0.5).color(n7, n8, n9, n10).endVertex();
        }
        if (Arrays.asList(s.split("-")).contains("downnorth") || s.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)n, (double)n2, (double)n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos(n + 0.5, n2 + 0.5, n3 + 0.5).color(n7, n8, n9, n10).endVertex();
        }
        if (Arrays.asList(s.split("-")).contains("upnorth") || s.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)n, (double)(n2 + n5), (double)n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos(n + 0.5, n2 + 0.5, n3 + 0.5).color(n7, n8, n9, n10).endVertex();
        }
        if (Arrays.asList(s.split("-")).contains("downsouth") || s.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)n, (double)n2, (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos(n + 0.5, n2 + 0.5, n3 + 0.5).color(n7, n8, n9, n10).endVertex();
        }
        if (Arrays.asList(s.split("-")).contains("upsouth") || s.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)n, (double)(n2 + n5), (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos(n + 0.5, n2 + 0.5, n3 + 0.5).color(n7, n8, n9, n10).endVertex();
        }
        if (Arrays.asList(s.split("-")).contains("nortwest") || s.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)n, (double)n2, (double)n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)n, (double)(n2 + n5), (double)n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)n, (double)n2, (double)n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos(n + 0.5, n2 + 0.5, n3 + 0.5).color(n7, n8, n9, n10).endVertex();
        }
        if (Arrays.asList(s.split("-")).contains("norteast") || s.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)(n + n4), (double)n2, (double)n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)(n + n4), (double)(n2 + n5), (double)n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)(n + n4), (double)n2, (double)n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos(n + 0.5, n2 + 0.5, n3 + 0.5).color(n7, n8, n9, n10).endVertex();
        }
        if (Arrays.asList(s.split("-")).contains("southweast") || s.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)n, (double)n2, (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)n, (double)(n2 + n5), (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)n, (double)n2, (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos(n + 0.5, n2 + 0.5, n3 + 0.5).color(n7, n8, n9, n10).endVertex();
        }
        if (Arrays.asList(s.split("-")).contains("southeast") || s.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)(n + n4), (double)n2, (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)(n + n4), (double)(n2 + n5), (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)(n + n4), (double)n2, (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos(n + 0.5, n2 + 0.5, n3 + 0.5).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)(n + n4), (double)(n2 + n5), (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos(n + 0.5, n2 + 0.5, n3 + 0.5).color(n7, n8, n9, n10).endVertex();
        }
    }
    
    public static void draw_cube(final float n, final float n2, final float n3, final int n4, final String s) {
        draw_cube(RenderHelp.INSTANCE.getBuffer(), n, n2, n3, 1.0f, 1.0f, 1.0f, n4 >>> 16 & 0xFF, n4 >>> 8 & 0xFF, n4 & 0xFF, n4 >>> 24 & 0xFF, s);
    }
    
    public RenderHelp() {
        super(2097152);
    }
    
    private static void colorVertex(final double n, final double n2, final double n3, final Color color, final int n4, final BufferBuilder bufferBuilder) {
        bufferBuilder.pos(n - Util.mc.getRenderManager().viewerPosX, n2 - Util.mc.getRenderManager().viewerPosY, n3 - Util.mc.getRenderManager().viewerPosZ).color(color.getRed(), color.getGreen(), color.getBlue(), n4).endVertex();
    }
    
    public static void draw_gradiant_cube(final BufferBuilder bufferBuilder, final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final Color color, final Color color2, final String s) {
        final int red = color.getRed();
        final int green = color.getGreen();
        final int blue = color.getBlue();
        final int alpha = color.getAlpha();
        final int red2 = color2.getRed();
        final int green2 = color2.getGreen();
        final int blue2 = color2.getBlue();
        final int alpha2 = color2.getAlpha();
        final List<String> list = Arrays.asList(s.split("-"));
        if (list.contains("north") || s.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)(n + n4), (double)n2, (double)n3).color(red, green, blue, alpha).endVertex();
            bufferBuilder.pos((double)n, (double)n2, (double)n3).color(red, green, blue, alpha).endVertex();
            bufferBuilder.pos((double)n, (double)(n2 + n5), (double)n3).color(red2, green2, blue2, alpha2).endVertex();
            bufferBuilder.pos((double)(n + n4), (double)(n2 + n5), (double)n3).color(red2, green2, blue2, alpha2).endVertex();
        }
        if (list.contains("south") || s.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)n, (double)n2, (double)(n3 + n6)).color(red, green, blue, alpha).endVertex();
            bufferBuilder.pos((double)(n + n4), (double)n2, (double)(n3 + n6)).color(red, green, blue, alpha).endVertex();
            bufferBuilder.pos((double)(n + n4), (double)(n2 + n5), (double)(n3 + n6)).color(red2, green2, blue2, alpha2).endVertex();
            bufferBuilder.pos((double)n, (double)(n2 + n5), (double)(n3 + n6)).color(red2, green2, blue2, alpha2).endVertex();
        }
        if (list.contains("west") || s.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)n, (double)n2, (double)n3).color(red, green, blue, alpha).endVertex();
            bufferBuilder.pos((double)n, (double)n2, (double)(n3 + n6)).color(red, green, blue, alpha).endVertex();
            bufferBuilder.pos((double)n, (double)(n2 + n5), (double)(n3 + n6)).color(red2, green2, blue2, alpha2).endVertex();
            bufferBuilder.pos((double)n, (double)(n2 + n5), (double)n3).color(red2, green2, blue2, alpha2).endVertex();
        }
        if (list.contains("east") || s.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)(n + n4), (double)n2, (double)(n3 + n6)).color(red, green, blue, alpha).endVertex();
            bufferBuilder.pos((double)(n + n4), (double)n2, (double)n3).color(red, green, blue, alpha).endVertex();
            bufferBuilder.pos((double)(n + n4), (double)(n2 + n5), (double)n3).color(red2, green2, blue2, alpha2).endVertex();
            bufferBuilder.pos((double)(n + n4), (double)(n2 + n5), (double)(n3 + n6)).color(red2, green2, blue2, alpha2).endVertex();
        }
    }
    
    public static void draw_cube(final BufferBuilder bufferBuilder, final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final int n7, final int n8, final int n9, final int n10, final String s) {
        if (Arrays.asList(s.split("-")).contains("down") || s.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)(n + n4), (double)n2, (double)n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)(n + n4), (double)n2, (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)n, (double)n2, (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)n, (double)n2, (double)n3).color(n7, n8, n9, n10).endVertex();
        }
        if (Arrays.asList(s.split("-")).contains("up") || s.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)(n + n4), (double)(n2 + n5), (double)n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)n, (double)(n2 + n5), (double)n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)n, (double)(n2 + n5), (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)(n + n4), (double)(n2 + n5), (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
        }
        if (Arrays.asList(s.split("-")).contains("north") || s.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)(n + n4), (double)n2, (double)n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)n, (double)n2, (double)n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)n, (double)(n2 + n5), (double)n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)(n + n4), (double)(n2 + n5), (double)n3).color(n7, n8, n9, n10).endVertex();
        }
        if (Arrays.asList(s.split("-")).contains("south") || s.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)n, (double)n2, (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)(n + n4), (double)n2, (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)(n + n4), (double)(n2 + n5), (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)n, (double)(n2 + n5), (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
        }
        if (Arrays.asList(s.split("-")).contains("south") || s.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)n, (double)n2, (double)n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)n, (double)n2, (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)n, (double)(n2 + n5), (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)n, (double)(n2 + n5), (double)n3).color(n7, n8, n9, n10).endVertex();
        }
        if (Arrays.asList(s.split("-")).contains("south") || s.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)(n + n4), (double)n2, (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)(n + n4), (double)n2, (double)n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)(n + n4), (double)(n2 + n5), (double)n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)(n + n4), (double)(n2 + n5), (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
        }
    }
    
    public static void release_gl_2d() {
        GL11.glDisable(34383);
        GL11.glDisable(2848);
        GlStateManager.enableAlpha();
        GlStateManager.enableCull();
        GlStateManager.enableTexture2D();
        GlStateManager.enableDepth();
        GlStateManager.disableBlend();
        GlStateManager.depthMask(true);
        GlStateManager.glLineWidth(1.0f);
        GlStateManager.shadeModel(7424);
        GL11.glHint(3154, 4352);
    }
    
    public static void draw_cube_line(final float n, final float n2, final float n3, final int n4, final String s) {
        draw_cube_line(RenderHelp.INSTANCE.getBuffer(), n, n2, n3, 1.0f, 1.0f, 1.0f, n4 >>> 16 & 0xFF, n4 >>> 8 & 0xFF, n4 & 0xFF, n4 >>> 24 & 0xFF, 1.0f, s);
    }
    
    public static void begin(final int n) {
        RenderHelp.INSTANCE.getBuffer().begin(n, DefaultVertexFormats.POSITION_COLOR);
    }
    
    public static void render() {
        RenderHelp.INSTANCE.draw();
    }
    
    public static void draw_cube_line(final BlockPos blockPos, final int n, final int n2, final int n3, final int n4, final String s) {
        draw_cube_line(RenderHelp.INSTANCE.getBuffer(), (float)blockPos.getX(), (float)blockPos.getY(), (float)blockPos.getZ(), 1.0f, 1.0f, 1.0f, n, n2, n3, n4, 1.0f, s);
    }
    
    public static void draw_cube(final BlockPos blockPos, final int n, final String s) {
        draw_cube(blockPos, n >>> 16 & 0xFF, n >>> 8 & 0xFF, n & 0xFF, n >>> 24 & 0xFF, s);
    }
    
    public static void drawTriangleOutline(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final int n7) {
        final boolean glIsEnabled = GL11.glIsEnabled(3042);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glPushMatrix();
        GL11.glLineWidth(n6);
        GL11.glBegin(2);
        GL11.glVertex2d((double)n, (double)n2);
        GL11.glVertex2d((double)(n - n3 / n4), (double)(n2 - n3));
        GL11.glVertex2d((double)n, (double)(n2 - n3 / n5));
        GL11.glVertex2d((double)(n + n3 / n4), (double)(n2 - n3));
        GL11.glVertex2d((double)n, (double)n2);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        if (!glIsEnabled) {
            GL11.glDisable(3042);
        }
        GL11.glDisable(2848);
    }
    
    public static BufferBuilder get_buffer_build() {
        return RenderHelp.INSTANCE.getBuffer();
    }
    
    public static void release_gl() {
        GlStateManager.enableCull();
        GlStateManager.depthMask(true);
        GlStateManager.enableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.enableDepth();
    }
}
