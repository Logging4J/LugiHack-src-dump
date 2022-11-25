//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util.gl;

import me.snow.luigihack.api.util.*;
import net.minecraft.client.gui.*;
import java.awt.*;
import org.lwjgl.opengl.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.util.math.*;
import net.minecraftforge.common.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class RenderUtilGL implements Util
{
    private static /* synthetic */ ScaledResolution resolution;
    public static /* synthetic */ Tessellator tessellator;
    public static /* synthetic */ BufferBuilder bufferBuilder;
    
    public static void drawBox(final RenderBuilder2 renderBuilder2) {
        if (RenderUtilGL.mc.getRenderViewEntity() != null) {
            final AxisAlignedBB offset = renderBuilder2.getAxisAlignedBB().offset(-RenderUtilGL.mc.getRenderManager().viewerPosX, -RenderUtilGL.mc.getRenderManager().viewerPosY, -RenderUtilGL.mc.getRenderManager().viewerPosZ);
            switch (renderBuilder2.getBox()) {
                case FILL: {
                    drawSelectionBox(offset, renderBuilder2.getHeight(), renderBuilder2.getLength(), renderBuilder2.getWidth(), renderBuilder2.getColor());
                    break;
                }
                case OUTLINE: {
                    drawSelectionBoundingBox(offset, renderBuilder2.getHeight(), renderBuilder2.getLength(), renderBuilder2.getWidth(), new Color(renderBuilder2.getColor().getRed(), renderBuilder2.getColor().getGreen(), renderBuilder2.getColor().getBlue(), 144));
                    break;
                }
                case BOTH: {
                    drawSelectionBox(offset, renderBuilder2.getHeight(), renderBuilder2.getLength(), renderBuilder2.getWidth(), renderBuilder2.getColor());
                    drawSelectionBoundingBox(offset, renderBuilder2.getHeight(), renderBuilder2.getLength(), renderBuilder2.getWidth(), new Color(renderBuilder2.getColor().getRed(), renderBuilder2.getColor().getGreen(), renderBuilder2.getColor().getBlue(), 144));
                    break;
                }
                case GLOW: {
                    drawSelectionGlowFilledBox(offset, renderBuilder2.getHeight(), renderBuilder2.getLength(), renderBuilder2.getWidth(), renderBuilder2.getColor(), new Color(renderBuilder2.getColor().getRed(), renderBuilder2.getColor().getGreen(), renderBuilder2.getColor().getBlue(), 0));
                    break;
                }
                case REVERSE: {
                    drawSelectionGlowFilledBox(offset, renderBuilder2.getHeight(), renderBuilder2.getLength(), renderBuilder2.getWidth(), new Color(renderBuilder2.getColor().getRed(), renderBuilder2.getColor().getGreen(), renderBuilder2.getColor().getBlue(), 0), renderBuilder2.getColor());
                    break;
                }
                case CLAW: {
                    drawClawBox(offset, renderBuilder2.getHeight(), renderBuilder2.getLength(), renderBuilder2.getWidth(), new Color(renderBuilder2.getColor().getRed(), renderBuilder2.getColor().getGreen(), renderBuilder2.getColor().getBlue(), 255));
                    break;
                }
            }
            renderBuilder2.build();
        }
    }
    
    public static void addChainedBoundingBoxVertices(final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final Color color) {
        RenderUtilGL.bufferBuilder.pos(n, n2, n3).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n2, n3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n2, n3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n2, n6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n2, n6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n2, n3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n5, n3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n5, n3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n5, n6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n5, n6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n5, n3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n5, n6).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n2, n6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n5, n6).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n2, n6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n5, n3).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n2, n3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n2, n3).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
    }
    
    public static void addChainedClawBoxVertices(final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final Color color) {
        RenderUtilGL.bufferBuilder.pos(n, n2, n3).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n2, n6 - 0.8).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n2, n6).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n2, n3 + 0.8).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n2, n3).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n2, n6 - 0.8).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n2, n6).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n2, n3 + 0.8).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n2, n3).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4 - 0.8, n2, n3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n2, n6).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4 - 0.8, n2, n6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n2, n3).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        RenderUtilGL.bufferBuilder.pos(n + 0.8, n2, n3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n2, n6).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        RenderUtilGL.bufferBuilder.pos(n + 0.8, n2, n6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n2, n3).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n2 + 0.2, n3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n2, n6).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n2 + 0.2, n6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n2, n3).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n2 + 0.2, n3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n2, n6).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n2 + 0.2, n6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n5, n3).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n5, n6 - 0.8).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n5, n6).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n5, n3 + 0.8).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n5, n3).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n5, n6 - 0.8).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n5, n6).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n5, n3 + 0.8).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n5, n3).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4 - 0.8, n5, n3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n5, n6).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4 - 0.8, n5, n6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n5, n3).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        RenderUtilGL.bufferBuilder.pos(n + 0.8, n5, n3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n5, n6).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        RenderUtilGL.bufferBuilder.pos(n + 0.8, n5, n6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n5, n3).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n5 - 0.2, n3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n5, n6).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n5 - 0.2, n6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n5, n3).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n5 - 0.2, n3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n5, n6).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n5 - 0.2, n6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
    }
    
    public static void addChainedFilledBoxVertices(final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final Color color) {
        RenderUtilGL.bufferBuilder.pos(n, n2, n3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n2, n3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n2, n3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n2, n6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n5, n3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n5, n6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n5, n6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n2, n6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n5, n6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n2, n6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n2, n6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n2, n3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n5, n6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n5, n3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n5, n3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n2, n3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n5, n3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n2, n3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n2, n3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n2, n3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n2, n6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n2, n6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n2, n6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n5, n3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n5, n3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n5, n6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n5, n3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n5, n6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n5, n6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n5, n6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
    }
    
    public static void drawRect(final float n, final float n2, final float n3, final float n4, final Color color) {
        GL11.glPushMatrix();
        GL11.glDisable(3553);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glShadeModel(7425);
        GL11.glBegin(7);
        GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
        GL11.glVertex2f(n, n2);
        GL11.glVertex2f(n, n2 + n4);
        GL11.glVertex2f(n + n3, n2 + n4);
        GL11.glVertex2f(n + n3, n2);
        GL11.glColor4f(0.0f, 0.0f, 0.0f, 1.0f);
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
    
    public static void glBillboardDistanceScaled(final float n, final float n2, final float n3, final EntityPlayer entityPlayer, final float n4) {
        glBillboard(n, n2, n3);
        float n5 = (int)entityPlayer.getDistance((double)n, (double)n2, (double)n3) / 2.0f / (2.0f + (2.0f - n4));
        if (n5 < 1.0f) {
            n5 = 1.0f;
        }
        GlStateManager.scale(n5, n5, n5);
    }
    
    public static void drawSelectionGlowFilledBox(final AxisAlignedBB axisAlignedBB, final double n, final double n2, final double n3, final Color color, final Color color2) {
        RenderUtilGL.bufferBuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        addChainedGlowBoxVertices(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, axisAlignedBB.maxX + n2, axisAlignedBB.maxY + n, axisAlignedBB.maxZ + n3, color, color2);
        RenderUtilGL.tessellator.draw();
    }
    
    public static double getDisplayWidth() {
        return RenderUtilGL.resolution.getScaledWidth_double();
    }
    
    public static void drawClawBox(final AxisAlignedBB axisAlignedBB, final double n, final double n2, final double n3, final Color color) {
        RenderUtilGL.bufferBuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        addChainedClawBoxVertices(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, axisAlignedBB.maxX + n2, axisAlignedBB.maxY + n, axisAlignedBB.maxZ + n3, color);
        RenderUtilGL.tessellator.draw();
    }
    
    public static void drawBorder(final float n, final float n2, final float n3, final float n4, final Color color) {
        drawRect(n - 0.5f, n2 - 0.5f, 0.5f, n4 + 1.0f, color);
        drawRect(n + n3, n2 - 0.5f, 0.5f, n4 + 1.0f, color);
        drawRect(n, n2 - 0.5f, n3, 0.5f, color);
        drawRect(n, n2 + n4, n3, 0.5f, color);
    }
    
    public static double getDisplayHeight() {
        return RenderUtilGL.resolution.getScaledHeight_double();
    }
    
    public static void drawPolygon(final double n, final double n2, final float n3, final int n4, final Color color) {
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
        RenderUtilGL.bufferBuilder.begin(6, DefaultVertexFormats.POSITION);
        RenderUtilGL.bufferBuilder.pos(n, n2, 0.0).endVertex();
        final double n5 = 6.283185307179586;
        for (int i = 0; i <= n4; ++i) {
            final double n6 = n5 * i / n4 + Math.toRadians(180.0);
            RenderUtilGL.bufferBuilder.pos(n + Math.sin(n6) * n3, n2 + Math.cos(n6) * n3, 0.0).endVertex();
        }
        RenderUtilGL.tessellator.draw();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }
    
    public static void addChainedGlowBoxVertices(final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final Color color, final Color color2) {
        RenderUtilGL.bufferBuilder.pos(n, n2, n3).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n2, n3).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n2, n6).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n2, n6).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n5, n3).color(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, color2.getAlpha() / 255.0f).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n5, n6).color(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, color2.getAlpha() / 255.0f).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n5, n6).color(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, color2.getAlpha() / 255.0f).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n5, n3).color(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, color2.getAlpha() / 255.0f).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n2, n3).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n5, n3).color(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, color2.getAlpha() / 255.0f).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n5, n3).color(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, color2.getAlpha() / 255.0f).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n2, n3).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n2, n3).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n5, n3).color(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, color2.getAlpha() / 255.0f).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n5, n6).color(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, color2.getAlpha() / 255.0f).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n2, n6).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n2, n6).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n2, n6).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f).endVertex();
        RenderUtilGL.bufferBuilder.pos(n4, n5, n6).color(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, color2.getAlpha() / 255.0f).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n5, n6).color(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, color2.getAlpha() / 255.0f).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n2, n3).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n2, n6).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n5, n6).color(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, color2.getAlpha() / 255.0f).endVertex();
        RenderUtilGL.bufferBuilder.pos(n, n5, n3).color(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, color2.getAlpha() / 255.0f).endVertex();
    }
    
    public static void glBillboard(final float n, final float n2, final float n3) {
        final float n4 = 0.02666667f;
        GlStateManager.translate(n - Util.mc.getRenderManager().viewerPosX, n2 - Util.mc.getRenderManager().viewerPosY, n3 - Util.mc.getRenderManager().viewerPosZ);
        GlStateManager.glNormal3f(0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(-Util.mc.player.rotationYaw, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(Util.mc.player.rotationPitch, (Util.mc.gameSettings.thirdPersonView == 2) ? -1.0f : 1.0f, 0.0f, 0.0f);
        GlStateManager.scale(-n4, -n4, n4);
    }
    
    public static void renderCircle(final BufferBuilder bufferBuilder, final Vec3d vec3d, final double n, final double n2, final Color color) {
        GlStateManager.disableCull();
        GlStateManager.disableAlpha();
        GlStateManager.shadeModel(7425);
        bufferBuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        for (int i = 0; i < 361; ++i) {
            bufferBuilder.pos(vec3d.x + Math.sin(Math.toRadians(i)) * n - Util.mc.getRenderManager().viewerPosX, vec3d.y + n2 - Util.mc.getRenderManager().viewerPosY, vec3d.z + Math.cos(Math.toRadians(i)) * n - Util.mc.getRenderManager().viewerPosZ).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, 1.0f).endVertex();
        }
        RenderUtilGL.tessellator.draw();
        GlStateManager.enableCull();
        GlStateManager.enableAlpha();
        GlStateManager.shadeModel(7424);
    }
    
    public static void drawSelectionBox(final AxisAlignedBB axisAlignedBB, final double n, final double n2, final double n3, final Color color) {
        RenderUtilGL.bufferBuilder.begin(5, DefaultVertexFormats.POSITION_COLOR);
        addChainedFilledBoxVertices(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, axisAlignedBB.maxX + n2, axisAlignedBB.maxY + n, axisAlignedBB.maxZ + n3, color);
        RenderUtilGL.tessellator.draw();
    }
    
    public static void drawRoundedRect(double n, double n2, double n3, double n4, final double n5, final Color color) {
        GL11.glPushAttrib(0);
        GL11.glScaled(0.5, 0.5, 0.5);
        n *= 2.0;
        n2 *= 2.0;
        n3 *= 2.0;
        n4 *= 2.0;
        n3 += n;
        n4 += n2;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
        GL11.glEnable(2848);
        GL11.glBegin(9);
        for (int i = 0; i <= 90; ++i) {
            GL11.glVertex2d(n + n5 + Math.sin(i * 3.141592653589793 / 180.0) * n5 * -1.0, n2 + n5 + Math.cos(i * 3.141592653589793 / 180.0) * n5 * -1.0);
        }
        for (int j = 90; j <= 180; ++j) {
            GL11.glVertex2d(n + n5 + Math.sin(j * 3.141592653589793 / 180.0) * n5 * -1.0, n4 - n5 + Math.cos(j * 3.141592653589793 / 180.0) * n5 * -1.0);
        }
        for (int k = 0; k <= 90; ++k) {
            GL11.glVertex2d(n3 - n5 + Math.sin(k * 3.141592653589793 / 180.0) * n5, n4 - n5 + Math.cos(k * 3.141592653589793 / 180.0) * n5);
        }
        for (int l = 90; l <= 180; ++l) {
            GL11.glVertex2d(n3 - n5 + Math.sin(l * 3.141592653589793 / 180.0) * n5, n2 + n5 + Math.cos(l * 3.141592653589793 / 180.0) * n5);
        }
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glDisable(3042);
        GL11.glEnable(3553);
        GL11.glScaled(2.0, 2.0, 2.0);
        GL11.glPopAttrib();
    }
    
    static {
        RenderUtilGL.tessellator = Tessellator.getInstance();
        RenderUtilGL.bufferBuilder = RenderUtilGL.tessellator.getBuffer();
        RenderUtilGL.resolution = new ScaledResolution(RenderUtilGL.mc);
        MinecraftForge.EVENT_BUS.register((Object)new Object() {
            @SubscribeEvent
            public void onRenderGameOverlay(final RenderGameOverlayEvent.Post post) {
                RenderUtilGL.resolution = post.getResolution();
            }
        });
    }
    
    public static void drawSelectionBoundingBox(final AxisAlignedBB axisAlignedBB, final double n, final double n2, final double n3, final Color color) {
        RenderUtilGL.bufferBuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        addChainedBoundingBoxVertices(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, axisAlignedBB.maxX + n2, axisAlignedBB.maxY + n, axisAlignedBB.maxZ + n3, color);
        RenderUtilGL.tessellator.draw();
    }
    
    public static void drawRect(final float n, final float n2, final float n3, final float n4, final int rgba) {
        final Color color = new Color(rgba, true);
        GL11.glPushMatrix();
        GL11.glDisable(3553);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glShadeModel(7425);
        GL11.glBegin(7);
        GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
        GL11.glVertex2f(n, n2);
        GL11.glVertex2f(n, n2 + n4);
        GL11.glVertex2f(n + n3, n2 + n4);
        GL11.glVertex2f(n + n3, n2);
        GL11.glColor4f(0.0f, 0.0f, 0.0f, 1.0f);
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
    
    public static void drawLine3D(final Vec3d vec3d, final Vec3d vec3d2, final Color color, final double n) {
        GL11.glDepthMask(false);
        GL11.glDisable(2929);
        GL11.glDisable(3008);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(0.1f);
        GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
        GL11.glLineWidth((float)n);
        GL11.glBegin(1);
        GL11.glVertex3d(vec3d.x, vec3d.y, vec3d.z);
        GL11.glVertex3d(vec3d2.x, vec3d2.y, vec3d2.z);
        GL11.glEnd();
        GL11.glDepthMask(true);
        GL11.glEnable(2929);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glEnable(3008);
        GL11.glDisable(2848);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }
}
