//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util.ca.util;

import net.minecraft.client.renderer.vertex.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.block.state.*;
import org.lwjgl.opengl.*;
import java.util.function.*;
import net.minecraft.block.material.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.renderer.*;
import java.awt.*;
import me.snow.luigihack.api.util.*;
import java.util.*;
import net.minecraft.client.renderer.culling.*;

public class RenderUtilCa implements Util
{
    static /* synthetic */ Random random;
    public static /* synthetic */ ICamera camera;
    private static final /* synthetic */ Map<Integer, Boolean> glCapMap;
    
    public static void drawBoxESP(final BlockPos blockPos, final Color color, final Color color2, final float n, final boolean b, final boolean b2, final boolean b3, final double n2, final boolean b4, final boolean b5, final boolean b6, final boolean b7, final int n3) {
        if (b2) {
            drawBox(blockPos, new Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()), n2, b4, b6, n3);
        }
        if (b) {
            drawBlockOutline(blockPos, color2, n, b3, n2, b5, b7);
        }
    }
    
    public static void drawBoundingBox(final AxisAlignedBB axisAlignedBB, final double n, final Colour colour, final int n2) {
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        GlStateManager.glLineWidth((float)n);
        colour.glColor();
        getBuffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        colorVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, colour, colour.getAlpha(), getBuffer);
        colorVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ, colour, colour.getAlpha(), getBuffer);
        colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ, colour, colour.getAlpha(), getBuffer);
        colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ, colour, colour.getAlpha(), getBuffer);
        colorVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, colour, colour.getAlpha(), getBuffer);
        colorVertex(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ, colour, n2, getBuffer);
        colorVertex(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ, colour, n2, getBuffer);
        colorVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ, colour, colour.getAlpha(), getBuffer);
        colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ, colour, colour.getAlpha(), getBuffer);
        colorVertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ, colour, n2, getBuffer);
        colorVertex(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ, colour, n2, getBuffer);
        colorVertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ, colour, n2, getBuffer);
        colorVertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ, colour, n2, getBuffer);
        colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ, colour, colour.getAlpha(), getBuffer);
        colorVertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ, colour, n2, getBuffer);
        colorVertex(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ, colour, n2, getBuffer);
        getInstance.draw();
    }
    
    public static void drawOpenGradientBoxBB(final AxisAlignedBB axisAlignedBB, final Color color, final Color color2, final double n) {
        final Vec3d interpolateEntity = EntityUtilCa.interpolateEntity((Entity)RenderUtilCa.mc.player, RenderUtilCa.mc.getRenderPartialTicks());
        final EnumFacing[] values = EnumFacing.values();
        for (int length = values.length, i = 0; i < length; ++i) {
            drawGradientPlaneBB(axisAlignedBB.grow(0.0020000000949949026).offset(-interpolateEntity.x, -interpolateEntity.y, -interpolateEntity.z), values[i], color, color2, n);
        }
    }
    
    public static AxisAlignedBB getBoundingBox(final BlockPos blockPos) {
        return new AxisAlignedBB(blockPos.getX() - RenderUtilCa.mc.getRenderManager().viewerPosX, blockPos.getY() - RenderUtilCa.mc.getRenderManager().viewerPosY, blockPos.getZ() - RenderUtilCa.mc.getRenderManager().viewerPosZ, blockPos.getX() + 1 - RenderUtilCa.mc.getRenderManager().viewerPosX, blockPos.getY() + 1 - RenderUtilCa.mc.getRenderManager().viewerPosY, blockPos.getZ() + 1 - RenderUtilCa.mc.getRenderManager().viewerPosZ);
    }
    
    public static void drawGradientBlockOutline(final BlockPos blockPos, final Color color, final Color color2, final float n, final double n2) {
        final IBlockState getBlockState = RenderUtilCa.mc.world.getBlockState(blockPos);
        final Vec3d interpolateEntity = EntityUtilCa.interpolateEntity((Entity)RenderUtilCa.mc.player, RenderUtilCa.mc.getRenderPartialTicks());
        drawGradientBlockOutline(getBlockState.getSelectedBoundingBox((World)RenderUtilCa.mc.world, blockPos).grow(0.0020000000949949026).offset(-interpolateEntity.x, -interpolateEntity.y, -interpolateEntity.z).expand(0.0, n2, 0.0), color, color2, n);
    }
    
    public void setGlState(final int n, final boolean b) {
        if (b) {
            GL11.glEnable(n);
        }
        else {
            GL11.glDisable(n);
        }
    }
    
    public static void drawSelectionGlowFilledBox(final AxisAlignedBB axisAlignedBB, final double n, final Color color, final Color color2) {
        final Tessellator getInstance = Tessellator.getInstance();
        Tessellator.getInstance().getBuffer().begin(7, DefaultVertexFormats.POSITION_COLOR);
        addChainedGlowBoxVertices(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, axisAlignedBB.maxX, axisAlignedBB.maxY + n, axisAlignedBB.maxZ, color, color2);
        getInstance.draw();
    }
    
    public static void drawRect(final float n, final float n2, final float n3, final float n4, final int n5) {
        final float n6 = (n5 >> 24 & 0xFF) / 255.0f;
        final float n7 = (n5 >> 16 & 0xFF) / 255.0f;
        final float n8 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n9 = (n5 & 0xFF) / 255.0f;
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        getBuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos((double)n, (double)n4, 0.0).color(n7, n8, n9, n6).endVertex();
        getBuffer.pos((double)n3, (double)n4, 0.0).color(n7, n8, n9, n6).endVertex();
        getBuffer.pos((double)n3, (double)n2, 0.0).color(n7, n8, n9, n6).endVertex();
        getBuffer.pos((double)n, (double)n2, 0.0).color(n7, n8, n9, n6).endVertex();
        getInstance.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public static void drawBox(final BlockPos blockPos, final double n, final Colour colour, final int n2) {
        drawBox(blockPos.getX(), blockPos.getY(), blockPos.getZ(), 1.0, n, 1.0, colour, colour.getAlpha(), n2);
    }
    
    public void resetCaps() {
        RenderUtilCa.glCapMap.forEach(this::setGlState);
    }
    
    public static void drawOutlineLine(double n, double n2, double n3, double n4, final double n5, final int n6) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth((float)n5);
        if (n < n3) {
            final double n7 = n;
            n = n3;
            n3 = n7;
        }
        if (n2 < n4) {
            final double n8 = n2;
            n2 = n4;
            n4 = n8;
        }
        final float n9 = (n6 >> 24 & 0xFF) / 255.0f;
        final float n10 = (n6 >> 16 & 0xFF) / 255.0f;
        final float n11 = (n6 >> 8 & 0xFF) / 255.0f;
        final float n12 = (n6 & 0xFF) / 255.0f;
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos(n, n4, 0.0).color(n10, n11, n12, n9).endVertex();
        getBuffer.pos(n3, n4, 0.0).color(n10, n11, n12, n9).endVertex();
        getBuffer.pos(n3, n2, 0.0).color(n10, n11, n12, n9).endVertex();
        getBuffer.pos(n, n2, 0.0).color(n10, n11, n12, n9).endVertex();
        getBuffer.pos(n, n4, 0.0).color(n10, n11, n12, n9).endVertex();
        getInstance.draw();
        GL11.glDisable(2848);
        GlStateManager.depthMask(true);
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
    public static void drawBlockOutline(final BlockPos blockPos, final Color color, final float n, final boolean b, final double n2, final boolean b2, final boolean b3) {
        if (b2) {
            final Color color2 = new Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
            drawGradientBlockOutline(blockPos, b3 ? color2 : color, b3 ? color : color2, n, n2);
            return;
        }
        final IBlockState getBlockState = RenderUtilCa.mc.world.getBlockState(blockPos);
        if ((b || getBlockState.getMaterial() != Material.AIR) && RenderUtilCa.mc.world.getWorldBorder().contains(blockPos)) {
            drawBlockOutline(new AxisAlignedBB(blockPos.getX() - RenderUtilCa.mc.getRenderManager().viewerPosX, blockPos.getY() - RenderUtilCa.mc.getRenderManager().viewerPosY, blockPos.getZ() - RenderUtilCa.mc.getRenderManager().viewerPosZ, blockPos.getX() + 1 - RenderUtilCa.mc.getRenderManager().viewerPosX, blockPos.getY() + 1 - RenderUtilCa.mc.getRenderManager().viewerPosY + n2, blockPos.getZ() + 1 - RenderUtilCa.mc.getRenderManager().viewerPosZ).grow(0.0020000000949949026), color, n);
        }
    }
    
    public static void drawGradientPlaneBB(final AxisAlignedBB axisAlignedBB, final EnumFacing enumFacing, final Color color, final Color color2, final double n) {
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        final float n2 = color.getRed() / 255.0f;
        final float n3 = color.getGreen() / 255.0f;
        final float n4 = color.getBlue() / 255.0f;
        final float n5 = color.getAlpha() / 255.0f;
        final float n6 = color2.getRed() / 255.0f;
        final float n7 = color2.getGreen() / 255.0f;
        final float n8 = color2.getBlue() / 255.0f;
        final float n9 = color2.getAlpha() / 255.0f;
        double n10 = 0.0;
        double n11 = 0.0;
        double n12 = 0.0;
        double n13 = 0.0;
        double n14 = 0.0;
        double n15 = 0.0;
        switch (enumFacing) {
            case DOWN: {
                n10 = axisAlignedBB.minX;
                n13 = axisAlignedBB.maxX;
                n11 = axisAlignedBB.minY;
                n14 = axisAlignedBB.minY;
                n12 = axisAlignedBB.minZ;
                n15 = axisAlignedBB.maxZ;
                break;
            }
            case UP: {
                n10 = axisAlignedBB.minX;
                n13 = axisAlignedBB.maxX;
                n11 = axisAlignedBB.maxY;
                n14 = axisAlignedBB.maxY;
                n12 = axisAlignedBB.minZ;
                n15 = axisAlignedBB.maxZ;
                break;
            }
            case EAST: {
                n10 = axisAlignedBB.maxX;
                n13 = axisAlignedBB.maxX;
                n11 = axisAlignedBB.minY;
                n14 = axisAlignedBB.maxY;
                n12 = axisAlignedBB.minZ;
                n15 = axisAlignedBB.maxZ;
                break;
            }
            case WEST: {
                n10 = axisAlignedBB.minX;
                n13 = axisAlignedBB.minX;
                n11 = axisAlignedBB.minY;
                n14 = axisAlignedBB.maxY;
                n12 = axisAlignedBB.minZ;
                n15 = axisAlignedBB.maxZ;
                break;
            }
            case SOUTH: {
                n10 = axisAlignedBB.minX;
                n13 = axisAlignedBB.maxX;
                n11 = axisAlignedBB.minY;
                n14 = axisAlignedBB.maxY;
                n12 = axisAlignedBB.maxZ;
                n15 = axisAlignedBB.maxZ;
                break;
            }
            case NORTH: {
                n10 = axisAlignedBB.minX;
                n13 = axisAlignedBB.maxX;
                n11 = axisAlignedBB.minY;
                n14 = axisAlignedBB.maxY;
                n12 = axisAlignedBB.minZ;
                n15 = axisAlignedBB.minZ;
                break;
            }
        }
        if (enumFacing != EnumFacing.DOWN) {
            if (enumFacing != EnumFacing.UP) {
                if (enumFacing != EnumFacing.EAST) {
                    if (enumFacing != EnumFacing.WEST) {
                        if (enumFacing != EnumFacing.SOUTH) {
                            if (enumFacing == EnumFacing.NORTH) {}
                        }
                    }
                }
            }
        }
        GlStateManager.pushMatrix();
        GlStateManager.disableDepth();
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.depthMask(false);
        getBuffer.begin(5, DefaultVertexFormats.POSITION_COLOR);
        if (enumFacing == EnumFacing.EAST || enumFacing == EnumFacing.WEST || enumFacing == EnumFacing.NORTH || enumFacing == EnumFacing.SOUTH) {
            getBuffer.pos(n10, n11, n12).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n10, n11, n12).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n10, n11, n12).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n10, n11, n15).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n10, n14, n12).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n10, n14, n15).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n10, n14, n15).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n10, n11, n15).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n13, n14, n15).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n13, n11, n15).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n13, n11, n15).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n13, n11, n12).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n13, n14, n15).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n13, n14, n12).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n13, n14, n12).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n13, n11, n12).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n10, n14, n12).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n10, n11, n12).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n10, n11, n12).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n13, n11, n12).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n10, n11, n15).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n13, n11, n15).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n13, n11, n15).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n10, n14, n12).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n10, n14, n12).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n10, n14, n15).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n13, n14, n12).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n13, n14, n15).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n13, n14, n15).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n13, n14, n15).color(n6, n7, n8, n9).endVertex();
        }
        else if (enumFacing == EnumFacing.UP) {
            getBuffer.pos(n10, n11, n12).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n10, n11, n12).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n10, n11, n12).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n10, n11, n15).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n10, n14, n12).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n10, n14, n15).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n10, n14, n15).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n10, n11, n15).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n13, n14, n15).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n13, n11, n15).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n13, n11, n15).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n13, n11, n12).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n13, n14, n15).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n13, n14, n12).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n13, n14, n12).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n13, n11, n12).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n10, n14, n12).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n10, n11, n12).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n10, n11, n12).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n13, n11, n12).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n10, n11, n15).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n13, n11, n15).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n13, n11, n15).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n10, n14, n12).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n10, n14, n12).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n10, n14, n15).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n13, n14, n12).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n13, n14, n15).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n13, n14, n15).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n13, n14, n15).color(n6, n7, n8, n9).endVertex();
        }
        else if (enumFacing == EnumFacing.DOWN) {
            getBuffer.pos(n10, n11, n12).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n10, n11, n12).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n10, n11, n12).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n10, n11, n15).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n10, n14, n12).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n10, n14, n15).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n10, n14, n15).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n10, n11, n15).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n13, n14, n15).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n13, n11, n15).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n13, n11, n15).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n13, n11, n12).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n13, n14, n15).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n13, n14, n12).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n13, n14, n12).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n13, n11, n12).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n10, n14, n12).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n10, n11, n12).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n10, n11, n12).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n13, n11, n12).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n10, n11, n15).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n13, n11, n15).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n13, n11, n15).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n10, n14, n12).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n10, n14, n12).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n10, n14, n15).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n13, n14, n12).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n13, n14, n15).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n13, n14, n15).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n13, n14, n15).color(n2, n3, n4, n5).endVertex();
        }
        getInstance.draw();
        GlStateManager.depthMask(true);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.enableDepth();
        GlStateManager.popMatrix();
    }
    
    private static AxisAlignedBB getBoundingBox(final BlockPos blockPos, final double n, final double n2, final double n3) {
        final double n4 = blockPos.getX();
        final double n5 = blockPos.getY();
        final double n6 = blockPos.getZ();
        return new AxisAlignedBB(n4, n5, n6, n4 + n, n5 + n2, n6 + n3);
    }
    
    public static void drawBoundingBoxWithSides(final BlockPos blockPos, final int n, final Colour colour, final int n2, final int n3) {
        drawBoundingBoxWithSides(getBoundingBox(blockPos, 1.0, 1.0, 1.0), n, colour, n2, n3);
    }
    
    public static void drawColumn(final float n, final float n2, final float n3, final float n4, final Colour colour, final int n5, final double n6) {
        final double n7 = n6 / n5;
        final float n8 = n4 / n5 * (float)n6;
        final BlockPos blockPos = new BlockPos((double)n, (double)n2, (double)n3);
        AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos.getX() - RenderUtilCa.mc.getRenderManager().viewerPosX, blockPos.getY() - RenderUtilCa.mc.getRenderManager().viewerPosY, blockPos.getZ() - RenderUtilCa.mc.getRenderManager().viewerPosZ, blockPos.getX() + 1 - RenderUtilCa.mc.getRenderManager().viewerPosX, blockPos.getY() + 1 - RenderUtilCa.mc.getRenderManager().viewerPosY, blockPos.getZ() + 1 - RenderUtilCa.mc.getRenderManager().viewerPosZ);
        RenderUtilCa.camera.setPosition(Objects.requireNonNull(RenderUtilCa.mc.getRenderViewEntity()).posX, RenderUtilCa.mc.getRenderViewEntity().posY, RenderUtilCa.mc.getRenderViewEntity().posZ);
        if (RenderUtilCa.camera.isBoundingBoxInFrustum(new AxisAlignedBB(axisAlignedBB.minX + RenderUtilCa.mc.getRenderManager().viewerPosX, axisAlignedBB.minY + RenderUtilCa.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ + RenderUtilCa.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX + RenderUtilCa.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY + RenderUtilCa.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ + RenderUtilCa.mc.getRenderManager().viewerPosZ))) {
            for (int i = 0; i <= n5; ++i) {
                axisAlignedBB = new AxisAlignedBB(axisAlignedBB.minX, axisAlignedBB.minY + n7 * i, axisAlignedBB.minZ, axisAlignedBB.maxX, axisAlignedBB.maxY + n7 * i, axisAlignedBB.maxZ);
                drawCircleVertices(axisAlignedBB, n8 * i, colour);
            }
        }
    }
    
    public static void addChainedGlowBoxVertices(final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final Color color, final Color color2) {
        final BufferBuilder getBuffer = Tessellator.getInstance().getBuffer();
        getBuffer.pos(n, n2, n3).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f).endVertex();
        getBuffer.pos(n4, n2, n3).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f).endVertex();
        getBuffer.pos(n4, n2, n6).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f).endVertex();
        getBuffer.pos(n, n2, n6).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f).endVertex();
        getBuffer.pos(n, n5, n3).color(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, color2.getAlpha() / 255.0f).endVertex();
        getBuffer.pos(n, n5, n6).color(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, color2.getAlpha() / 255.0f).endVertex();
        getBuffer.pos(n4, n5, n6).color(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, color2.getAlpha() / 255.0f).endVertex();
        getBuffer.pos(n4, n5, n3).color(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, color2.getAlpha() / 255.0f).endVertex();
        getBuffer.pos(n, n2, n3).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f).endVertex();
        getBuffer.pos(n, n5, n3).color(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, color2.getAlpha() / 255.0f).endVertex();
        getBuffer.pos(n4, n5, n3).color(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, color2.getAlpha() / 255.0f).endVertex();
        getBuffer.pos(n4, n2, n3).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f).endVertex();
        getBuffer.pos(n4, n2, n3).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f).endVertex();
        getBuffer.pos(n4, n5, n3).color(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, color2.getAlpha() / 255.0f).endVertex();
        getBuffer.pos(n4, n5, n6).color(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, color2.getAlpha() / 255.0f).endVertex();
        getBuffer.pos(n4, n2, n6).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f).endVertex();
        getBuffer.pos(n, n2, n6).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f).endVertex();
        getBuffer.pos(n4, n2, n6).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f).endVertex();
        getBuffer.pos(n4, n5, n6).color(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, color2.getAlpha() / 255.0f).endVertex();
        getBuffer.pos(n, n5, n6).color(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, color2.getAlpha() / 255.0f).endVertex();
        getBuffer.pos(n, n2, n3).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f).endVertex();
        getBuffer.pos(n, n2, n6).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f).endVertex();
        getBuffer.pos(n, n5, n6).color(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, color2.getAlpha() / 255.0f).endVertex();
        getBuffer.pos(n, n5, n3).color(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, color2.getAlpha() / 255.0f).endVertex();
    }
    
    public static void drawBoundingBoxWithSides(final AxisAlignedBB axisAlignedBB, final int n, final Colour colour, final int n2) {
        drawBoundingBoxWithSides(axisAlignedBB, n, colour, colour.getAlpha(), n2);
    }
    
    public static void hexColor(final int n) {
        GL11.glColor4f((n >> 16 & 0xFF) / 255.0f, (n >> 8 & 0xFF) / 255.0f, (n & 0xFF) / 255.0f, (n >> 24 & 0xFF) / 255.0f);
    }
    
    public static void drawBlockOutlineBB(final AxisAlignedBB axisAlignedBB, final Color color, final float n) {
        final Vec3d interpolateEntity = EntityUtilCa.interpolateEntity((Entity)RenderUtilCa.mc.player, RenderUtilCa.mc.getRenderPartialTicks());
        drawBlockOutline(axisAlignedBB.grow(0.0020000000949949026).offset(-interpolateEntity.x, -interpolateEntity.y, -interpolateEntity.z), color, n);
    }
    
    public static void drawGradientPlane(final BlockPos blockPos, final EnumFacing enumFacing, final Color color, final Color color2, final double n) {
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        final IBlockState getBlockState = RenderUtilCa.mc.world.getBlockState(blockPos);
        final Vec3d interpolateEntity = EntityUtilCa.interpolateEntity((Entity)RenderUtilCa.mc.player, RenderUtilCa.mc.getRenderPartialTicks());
        final AxisAlignedBB expand = getBlockState.getSelectedBoundingBox((World)RenderUtilCa.mc.world, blockPos).grow(0.0020000000949949026).offset(-interpolateEntity.x, -interpolateEntity.y, -interpolateEntity.z).expand(0.0, n, 0.0);
        final float n2 = color.getRed() / 255.0f;
        final float n3 = color.getGreen() / 255.0f;
        final float n4 = color.getBlue() / 255.0f;
        final float n5 = color.getAlpha() / 255.0f;
        final float n6 = color2.getRed() / 255.0f;
        final float n7 = color2.getGreen() / 255.0f;
        final float n8 = color2.getBlue() / 255.0f;
        final float n9 = color2.getAlpha() / 255.0f;
        double n10 = 0.0;
        double n11 = 0.0;
        double n12 = 0.0;
        double n13 = 0.0;
        double n14 = 0.0;
        double n15 = 0.0;
        if (enumFacing == EnumFacing.DOWN) {
            n10 = expand.minX;
            n13 = expand.maxX;
            n11 = expand.minY;
            n14 = expand.minY;
            n12 = expand.minZ;
            n15 = expand.maxZ;
        }
        else if (enumFacing == EnumFacing.UP) {
            n10 = expand.minX;
            n13 = expand.maxX;
            n11 = expand.maxY;
            n14 = expand.maxY;
            n12 = expand.minZ;
            n15 = expand.maxZ;
        }
        else if (enumFacing == EnumFacing.EAST) {
            n10 = expand.maxX;
            n13 = expand.maxX;
            n11 = expand.minY;
            n14 = expand.maxY;
            n12 = expand.minZ;
            n15 = expand.maxZ;
        }
        else if (enumFacing == EnumFacing.WEST) {
            n10 = expand.minX;
            n13 = expand.minX;
            n11 = expand.minY;
            n14 = expand.maxY;
            n12 = expand.minZ;
            n15 = expand.maxZ;
        }
        else if (enumFacing == EnumFacing.SOUTH) {
            n10 = expand.minX;
            n13 = expand.maxX;
            n11 = expand.minY;
            n14 = expand.maxY;
            n12 = expand.maxZ;
            n15 = expand.maxZ;
        }
        else if (enumFacing == EnumFacing.NORTH) {
            n10 = expand.minX;
            n13 = expand.maxX;
            n11 = expand.minY;
            n14 = expand.maxY;
            n12 = expand.minZ;
            n15 = expand.minZ;
        }
        GlStateManager.pushMatrix();
        GlStateManager.disableDepth();
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.depthMask(false);
        getBuffer.begin(5, DefaultVertexFormats.POSITION_COLOR);
        if (enumFacing == EnumFacing.EAST || enumFacing == EnumFacing.WEST || enumFacing == EnumFacing.NORTH || enumFacing == EnumFacing.SOUTH) {
            getBuffer.pos(n10, n11, n12).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n10, n11, n12).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n10, n11, n12).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n10, n11, n15).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n10, n14, n12).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n10, n14, n15).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n10, n14, n15).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n10, n11, n15).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n13, n14, n15).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n13, n11, n15).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n13, n11, n15).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n13, n11, n12).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n13, n14, n15).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n13, n14, n12).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n13, n14, n12).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n13, n11, n12).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n10, n14, n12).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n10, n11, n12).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n10, n11, n12).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n13, n11, n12).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n10, n11, n15).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n13, n11, n15).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n13, n11, n15).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n10, n14, n12).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n10, n14, n12).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n10, n14, n15).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n13, n14, n12).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n13, n14, n15).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n13, n14, n15).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n13, n14, n15).color(n6, n7, n8, n9).endVertex();
        }
        else if (enumFacing == EnumFacing.UP) {
            getBuffer.pos(n10, n11, n12).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n10, n11, n12).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n10, n11, n12).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n10, n11, n15).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n10, n14, n12).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n10, n14, n15).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n10, n14, n15).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n10, n11, n15).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n13, n14, n15).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n13, n11, n15).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n13, n11, n15).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n13, n11, n12).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n13, n14, n15).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n13, n14, n12).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n13, n14, n12).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n13, n11, n12).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n10, n14, n12).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n10, n11, n12).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n10, n11, n12).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n13, n11, n12).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n10, n11, n15).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n13, n11, n15).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n13, n11, n15).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n10, n14, n12).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n10, n14, n12).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n10, n14, n15).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n13, n14, n12).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n13, n14, n15).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n13, n14, n15).color(n6, n7, n8, n9).endVertex();
            getBuffer.pos(n13, n14, n15).color(n6, n7, n8, n9).endVertex();
        }
        else if (enumFacing == EnumFacing.DOWN) {
            getBuffer.pos(n10, n11, n12).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n10, n11, n12).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n10, n11, n12).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n10, n11, n15).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n10, n14, n12).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n10, n14, n15).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n10, n14, n15).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n10, n11, n15).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n13, n14, n15).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n13, n11, n15).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n13, n11, n15).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n13, n11, n12).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n13, n14, n15).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n13, n14, n12).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n13, n14, n12).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n13, n11, n12).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n10, n14, n12).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n10, n11, n12).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n10, n11, n12).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n13, n11, n12).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n10, n11, n15).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n13, n11, n15).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n13, n11, n15).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n10, n14, n12).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n10, n14, n12).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n10, n14, n15).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n13, n14, n12).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n13, n14, n15).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n13, n14, n15).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(n13, n14, n15).color(n2, n3, n4, n5).endVertex();
        }
        getInstance.draw();
        GlStateManager.depthMask(true);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.enableDepth();
        GlStateManager.popMatrix();
    }
    
    private static void doVerticies(final AxisAlignedBB axisAlignedBB, final Colour colour, final int n, final BufferBuilder bufferBuilder, final int n2, final boolean b) {
        if ((n2 & 0x20) != 0x0 || n2 == -1) {
            colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ, colour, n, bufferBuilder);
            colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ, colour, n, bufferBuilder);
            colorVertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ, colour, n, bufferBuilder);
            colorVertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ, colour, n, bufferBuilder);
            if (b) {
                colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ, colour, n, bufferBuilder);
            }
        }
        if ((n2 & 0x10) != 0x0 || n2 == -1) {
            colorVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, colour, n, bufferBuilder);
            colorVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ, colour, n, bufferBuilder);
            colorVertex(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ, colour, n, bufferBuilder);
            colorVertex(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ, colour, n, bufferBuilder);
            if (b) {
                colorVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, colour, n, bufferBuilder);
            }
        }
        if ((n2 & 0x4) != 0x0 || n2 == -1) {
            colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ, colour, n, bufferBuilder);
            colorVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, colour, n, bufferBuilder);
            colorVertex(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ, colour, n, bufferBuilder);
            colorVertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ, colour, n, bufferBuilder);
            if (b) {
                colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ, colour, n, bufferBuilder);
            }
        }
        if ((n2 & 0x8) != 0x0 || n2 == -1) {
            colorVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ, colour, n, bufferBuilder);
            colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ, colour, n, bufferBuilder);
            colorVertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ, colour, n, bufferBuilder);
            colorVertex(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ, colour, n, bufferBuilder);
            if (b) {
                colorVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ, colour, n, bufferBuilder);
            }
        }
        if ((n2 & 0x2) != 0x0 || n2 == -1) {
            colorVertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ, colour, n, bufferBuilder);
            colorVertex(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ, colour, n, bufferBuilder);
            colorVertex(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ, colour, n, bufferBuilder);
            colorVertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ, colour, n, bufferBuilder);
            if (b) {
                colorVertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ, colour, n, bufferBuilder);
            }
        }
        if ((n2 & 0x1) != 0x0 || n2 == -1) {
            colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ, colour, n, bufferBuilder);
            colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ, colour, n, bufferBuilder);
            colorVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ, colour, n, bufferBuilder);
            colorVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, colour, n, bufferBuilder);
            if (b) {
                colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ, colour, n, bufferBuilder);
            }
        }
    }
    
    public static void glBillboardDistanceScaled(final float n, final float n2, final float n3, final EntityPlayer entityPlayer, final float n4) {
        glBillboard(n, n2, n3);
        float n5 = (int)entityPlayer.getDistance((double)n, (double)n2, (double)n3) / 2.0f / (2.0f + (2.0f - n4));
        if (n5 < 1.0f) {
            n5 = 1.0f;
        }
        GlStateManager.scale(n5, n5, n5);
    }
    
    public static void drawGlError(final BlockPos blockPos, final double n, final double n2, final double n3, final Color color) {
        final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos.getX() - RenderUtilCa.mc.getRenderManager().viewerPosX, blockPos.getY() - RenderUtilCa.mc.getRenderManager().viewerPosY, blockPos.getZ() - RenderUtilCa.mc.getRenderManager().viewerPosZ, blockPos.getX() + 1 - RenderUtilCa.mc.getRenderManager().viewerPosX, blockPos.getY() + 1 - RenderUtilCa.mc.getRenderManager().viewerPosY, blockPos.getZ() + 1 - RenderUtilCa.mc.getRenderManager().viewerPosZ);
        RenderUtilCa.camera.setPosition(Objects.requireNonNull(RenderUtilCa.mc.getRenderViewEntity()).posX, RenderUtilCa.mc.getRenderViewEntity().posY, RenderUtilCa.mc.getRenderViewEntity().posZ);
        if (RenderUtilCa.camera.isBoundingBoxInFrustum(new AxisAlignedBB(axisAlignedBB.minX + RenderUtilCa.mc.getRenderManager().viewerPosX, axisAlignedBB.minY + RenderUtilCa.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ + RenderUtilCa.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX + RenderUtilCa.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY + RenderUtilCa.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ + RenderUtilCa.mc.getRenderManager().viewerPosZ))) {
            final Tessellator getInstance = Tessellator.getInstance();
            getInstance.getBuffer().begin(3, DefaultVertexFormats.POSITION_COLOR);
            drawCornerVertices(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, axisAlignedBB.maxX + n2, axisAlignedBB.maxY + n, axisAlignedBB.maxZ + n3, color);
            getInstance.draw();
        }
    }
    
    public static void drawCircleVertices(final AxisAlignedBB axisAlignedBB, final float n, final Colour colour) {
        final float n2 = colour.getRed() / 255.0f;
        final float n3 = colour.getGreen() / 255.0f;
        final float n4 = colour.getBlue() / 255.0f;
        final float n5 = colour.getAlpha() / 255.0f;
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(1.0f);
        for (int i = 0; i < 360; ++i) {
            getBuffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
            getBuffer.pos(axisAlignedBB.getCenter().x + Math.sin(i * 3.1415926 / 180.0) * n, axisAlignedBB.minY, axisAlignedBB.getCenter().z + Math.cos(i * 3.1415926 / 180.0) * n).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(axisAlignedBB.getCenter().x + Math.sin((i + 1) * 3.1415926 / 180.0) * n, axisAlignedBB.minY, axisAlignedBB.getCenter().z + Math.cos((i + 1) * 3.1415926 / 180.0) * n).color(n2, n3, n4, n5).endVertex();
            getInstance.draw();
        }
        GL11.glDisable(2848);
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
    public static void drawBoxESP(final BlockPos blockPos, final Color color, final Color color2, final double n, final boolean b, final boolean b2, final Float n2) {
        final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos.getX() - RenderUtilCa.mc.getRenderManager().viewerPosX, blockPos.getY() - RenderUtilCa.mc.getRenderManager().viewerPosY, blockPos.getZ() - RenderUtilCa.mc.getRenderManager().viewerPosZ, blockPos.getX() + 1 - RenderUtilCa.mc.getRenderManager().viewerPosX, blockPos.getY() + 1 - RenderUtilCa.mc.getRenderManager().viewerPosY + n2, blockPos.getZ() + 1 - RenderUtilCa.mc.getRenderManager().viewerPosZ);
        RenderUtilCa.camera.setPosition(Objects.requireNonNull(RenderUtilCa.mc.getRenderViewEntity()).posX, RenderUtilCa.mc.getRenderViewEntity().posY, RenderUtilCa.mc.getRenderViewEntity().posZ);
        if (RenderUtilCa.camera.isBoundingBoxInFrustum(new AxisAlignedBB(axisAlignedBB.minX + RenderUtilCa.mc.getRenderManager().viewerPosX, axisAlignedBB.minY + RenderUtilCa.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ + RenderUtilCa.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX + RenderUtilCa.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY + RenderUtilCa.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ + RenderUtilCa.mc.getRenderManager().viewerPosZ))) {
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.disableDepth();
            GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
            GlStateManager.disableTexture2D();
            GlStateManager.depthMask(false);
            GL11.glEnable(2848);
            GL11.glHint(3154, 4354);
            GL11.glLineWidth((float)n);
            if (b2) {
                RenderGlobal.renderFilledBox(axisAlignedBB, color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
            }
            if (b) {
                RenderGlobal.drawBoundingBox(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ, color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, color2.getAlpha() / 255.0f);
            }
            GL11.glDisable(2848);
            GlStateManager.depthMask(true);
            GlStateManager.enableDepth();
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
    }
    
    public static String getRandomFont() {
        final String[] availableFontFamilyNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames(Locale.ENGLISH);
        return availableFontFamilyNames[RenderUtilCa.random.nextInt(availableFontFamilyNames.length)];
    }
    
    public static void drawBoundingBoxWithSides(final BlockPos blockPos, final int n, final Colour colour, final int n2) {
        drawBoundingBoxWithSides(getBoundingBox(blockPos, 1.0, 1.0, 1.0), n, colour, colour.getAlpha(), n2);
    }
    
    public static void drawCorner(final BlockPos blockPos, final double n, final double n2, final double n3, final Color color) {
        final Tessellator getInstance = Tessellator.getInstance();
        Tessellator.getInstance().getBuffer().begin(3, DefaultVertexFormats.POSITION_COLOR);
        final AxisAlignedBB boundingBox = getBoundingBox(blockPos);
        drawCornerVertices(boundingBox.minX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX + n2, boundingBox.maxY + n, boundingBox.maxZ + n3, color);
        getInstance.draw();
    }
    
    public static Color getContrastColor(final Color color) {
        return ((299.0f * color.getRed() + 587.0f * color.getGreen() + 114.0f * color.getBlue()) / 1000.0f >= 128.0) ? Color.black : Color.white;
    }
    
    public static void drawBox(final AxisAlignedBB axisAlignedBB, final boolean b, final double n, final Colour colour, final int n2) {
        drawBox(axisAlignedBB, b, n, colour, colour.getAlpha(), n2);
    }
    
    public static void drawBoundingBoxWithSides(final AxisAlignedBB axisAlignedBB, final int n, final Colour colour, final int n2, final int n3) {
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        GlStateManager.glLineWidth((float)n);
        getBuffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        doVerticies(axisAlignedBB, colour, n2, getBuffer, n3, true);
        getInstance.draw();
    }
    
    public static void drawBoxESP(final BlockPos blockPos, final Color color, final float n, final boolean b, final boolean b2, final int n2, final Double n3) {
        final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos.getX() - RenderUtilCa.mc.getRenderManager().viewerPosX, blockPos.getY() - RenderUtilCa.mc.getRenderManager().viewerPosY, blockPos.getZ() - RenderUtilCa.mc.getRenderManager().viewerPosZ, blockPos.getX() + 1 - RenderUtilCa.mc.getRenderManager().viewerPosX, blockPos.getY() + 1 - RenderUtilCa.mc.getRenderManager().viewerPosY + n3, blockPos.getZ() + 1 - RenderUtilCa.mc.getRenderManager().viewerPosZ);
        RenderUtilCa.camera.setPosition(Objects.requireNonNull(RenderUtilCa.mc.getRenderViewEntity()).posX, RenderUtilCa.mc.getRenderViewEntity().posY, RenderUtilCa.mc.getRenderViewEntity().posZ);
        if (RenderUtilCa.camera.isBoundingBoxInFrustum(new AxisAlignedBB(axisAlignedBB.minX + RenderUtilCa.mc.getRenderManager().viewerPosX, axisAlignedBB.minY + RenderUtilCa.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ + RenderUtilCa.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX + RenderUtilCa.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY + RenderUtilCa.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ + RenderUtilCa.mc.getRenderManager().viewerPosZ))) {
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.disableDepth();
            GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
            GlStateManager.disableTexture2D();
            GlStateManager.depthMask(false);
            GL11.glEnable(2848);
            GL11.glHint(3154, 4354);
            GL11.glLineWidth(n);
            if (b2) {
                RenderGlobal.renderFilledBox(axisAlignedBB, color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, n2 / 255.0f);
            }
            if (b) {
                RenderGlobal.drawBoundingBox(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ, color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
            }
            GL11.glDisable(2848);
            GlStateManager.depthMask(true);
            GlStateManager.enableDepth();
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
    }
    
    public static void drawBlockOutline(final AxisAlignedBB axisAlignedBB, final Color color, final float n) {
        final float n2 = color.getRed() / 255.0f;
        final float n3 = color.getGreen() / 255.0f;
        final float n4 = color.getBlue() / 255.0f;
        final float n5 = color.getAlpha() / 255.0f;
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(n);
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getInstance.draw();
        GL11.glDisable(2848);
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
    public static void glBillboard(final float n, final float n2, final float n3) {
        final float n4 = 0.02666667f;
        GlStateManager.translate(n - RenderUtilCa.mc.getRenderManager().renderPosX, n2 - RenderUtilCa.mc.getRenderManager().renderPosY, n3 - RenderUtilCa.mc.getRenderManager().renderPosZ);
        GlStateManager.glNormal3f(0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(-RenderUtilCa.mc.player.rotationYaw, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(RenderUtilCa.mc.player.rotationPitch, (RenderUtilCa.mc.gameSettings.thirdPersonView == 2) ? -1.0f : 1.0f, 0.0f, 0.0f);
        GlStateManager.scale(-n4, -n4, n4);
    }
    
    public static void drawOpenGradientBox(final BlockPos blockPos, final Color color, final Color color2, final double n) {
        for (final EnumFacing enumFacing : EnumFacing.values()) {
            if (enumFacing != EnumFacing.UP) {
                drawGradientPlane(blockPos, enumFacing, color, color2, n);
            }
        }
    }
    
    public static void drawBox(final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final Colour colour, final int n7, final int n8) {
        GlStateManager.disableAlpha();
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        colour.glColor();
        getBuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        doVerticies(new AxisAlignedBB(n, n2, n3, n + n4, n2 + n5, n3 + n6), colour, n7, getBuffer, n8, false);
        getInstance.draw();
        GlStateManager.enableAlpha();
    }
    
    public static void drawBox(final BlockPos blockPos, final Color color, final boolean b) {
        final IBlockState getBlockState = RenderUtilCa.mc.world.getBlockState(blockPos);
        if ((b || getBlockState.getMaterial() != Material.AIR) && RenderUtilCa.mc.world.getWorldBorder().contains(blockPos)) {
            final Vec3d interpolateEntity = EntityUtilCa.interpolateEntity((Entity)RenderUtilCa.mc.player, RenderUtilCa.mc.getRenderPartialTicks());
            final AxisAlignedBB offset = getBlockState.getSelectedBoundingBox((World)RenderUtilCa.mc.world, blockPos).grow(0.0020000000949949026).offset(-interpolateEntity.x, -interpolateEntity.y, -interpolateEntity.z);
            RenderUtilCa.camera.setPosition(Objects.requireNonNull(RenderUtilCa.mc.getRenderViewEntity()).posX, RenderUtilCa.mc.getRenderViewEntity().posY, RenderUtilCa.mc.getRenderViewEntity().posZ);
            if (RenderUtilCa.camera.isBoundingBoxInFrustum(new AxisAlignedBB(offset.minX + RenderUtilCa.mc.getRenderManager().viewerPosX, offset.minY + RenderUtilCa.mc.getRenderManager().viewerPosY, offset.minZ + RenderUtilCa.mc.getRenderManager().viewerPosZ, offset.maxX + RenderUtilCa.mc.getRenderManager().viewerPosX, offset.maxY + RenderUtilCa.mc.getRenderManager().viewerPosY, offset.maxZ + RenderUtilCa.mc.getRenderManager().viewerPosZ))) {
                GlStateManager.pushMatrix();
                GlStateManager.enableBlend();
                GlStateManager.disableDepth();
                GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
                GlStateManager.disableTexture2D();
                GlStateManager.depthMask(false);
                GL11.glEnable(2848);
                GL11.glHint(3154, 4354);
                RenderGlobal.renderFilledBox(offset, color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
                GL11.glDisable(2848);
                GlStateManager.depthMask(true);
                GlStateManager.enableDepth();
                GlStateManager.enableTexture2D();
                GlStateManager.disableBlend();
                GlStateManager.popMatrix();
            }
        }
    }
    
    public static void drawBox(final AxisAlignedBB axisAlignedBB, final boolean b, final double n, final Colour colour, final int n2, final int n3) {
        if (b) {
            drawBox(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, axisAlignedBB.maxX - axisAlignedBB.minX, axisAlignedBB.maxY - axisAlignedBB.minY, axisAlignedBB.maxZ - axisAlignedBB.minZ, colour, n2, n3);
        }
        else {
            drawBox(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, axisAlignedBB.maxX - axisAlignedBB.minX, n, axisAlignedBB.maxZ - axisAlignedBB.minZ, colour, n2, n3);
        }
    }
    
    public static void drawGlowBox(final BlockPos blockPos, final double n, final Float n2, final Color color, final Color color2) {
        drawBoxESP(blockPos, color2, n2, true, false, color2.getAlpha(), -1.0);
        final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos.getX() - RenderUtilCa.mc.getRenderManager().viewerPosX, blockPos.getY() - RenderUtilCa.mc.getRenderManager().viewerPosY, blockPos.getZ() - RenderUtilCa.mc.getRenderManager().viewerPosZ, blockPos.getX() + 1 - RenderUtilCa.mc.getRenderManager().viewerPosX, blockPos.getY() + 1 - RenderUtilCa.mc.getRenderManager().viewerPosY, blockPos.getZ() + 1 - RenderUtilCa.mc.getRenderManager().viewerPosZ);
        RenderBuilder.glSetup();
        RenderBuilder.glPrepare();
        drawSelectionGlowFilledBox(axisAlignedBB, n, new Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()), new Color(color.getRed(), color.getGreen(), color.getBlue(), 0));
        RenderBuilder.glRestore();
        RenderBuilder.glRelease();
    }
    
    public static void drawTriangleOutline(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final int n7) {
        final boolean glIsEnabled = GL11.glIsEnabled(3042);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glPushMatrix();
        GL11.glLineWidth(n6);
        hexColor(n7);
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
    
    public static void drawBlockOutline(final BlockPos blockPos, final Color color, final float n, final boolean b) {
        final IBlockState getBlockState = RenderUtilCa.mc.world.getBlockState(blockPos);
        if ((b || getBlockState.getMaterial() != Material.AIR) && RenderUtilCa.mc.world.getWorldBorder().contains(blockPos)) {
            final Vec3d interpolateEntity = EntityUtilCa.interpolateEntity((Entity)RenderUtilCa.mc.player, RenderUtilCa.mc.getRenderPartialTicks());
            drawBlockOutline(getBlockState.getSelectedBoundingBox((World)RenderUtilCa.mc.world, blockPos).grow(0.0020000000949949026).offset(-interpolateEntity.x, -interpolateEntity.y, -interpolateEntity.z), color, n);
        }
    }
    
    static {
        RenderUtilCa.random = new Random();
        glCapMap = new HashMap<Integer, Boolean>();
        RenderUtilCa.camera = (ICamera)new Frustum();
    }
    
    public static void drawBox(final BlockPos blockPos, final Color color, final double n, final boolean b, final boolean b2, final int a) {
        if (b) {
            final Color color2 = new Color(color.getRed(), color.getGreen(), color.getBlue(), a);
            drawOpenGradientBox(blockPos, b2 ? color2 : color, b2 ? color : color2, n);
            return;
        }
        final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos.getX() - RenderUtilCa.mc.getRenderManager().viewerPosX, blockPos.getY() - RenderUtilCa.mc.getRenderManager().viewerPosY, blockPos.getZ() - RenderUtilCa.mc.getRenderManager().viewerPosZ, blockPos.getX() + 1 - RenderUtilCa.mc.getRenderManager().viewerPosX, blockPos.getY() + 1 - RenderUtilCa.mc.getRenderManager().viewerPosY + n, blockPos.getZ() + 1 - RenderUtilCa.mc.getRenderManager().viewerPosZ);
        RenderUtilCa.camera.setPosition(Objects.requireNonNull(RenderUtilCa.mc.getRenderViewEntity()).posX, RenderUtilCa.mc.getRenderViewEntity().posY, RenderUtilCa.mc.getRenderViewEntity().posZ);
        if (RenderUtilCa.camera.isBoundingBoxInFrustum(new AxisAlignedBB(axisAlignedBB.minX + RenderUtilCa.mc.getRenderManager().viewerPosX, axisAlignedBB.minY + RenderUtilCa.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ + RenderUtilCa.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX + RenderUtilCa.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY + RenderUtilCa.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ + RenderUtilCa.mc.getRenderManager().viewerPosZ))) {
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.disableDepth();
            GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
            GlStateManager.disableTexture2D();
            GlStateManager.depthMask(false);
            GL11.glEnable(2848);
            GL11.glHint(3154, 4354);
            RenderGlobal.renderFilledBox(axisAlignedBB, color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
            GL11.glDisable(2848);
            GlStateManager.depthMask(true);
            GlStateManager.enableDepth();
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
    }
    
    public static void drawCircle(final float n, final float n2, final float n3, final float n4, final Colour colour) {
        final BlockPos blockPos = new BlockPos((double)n, (double)n2, (double)n3);
        drawCircleVertices(new AxisAlignedBB(blockPos.getX() - RenderUtilCa.mc.getRenderManager().viewerPosX, blockPos.getY() - RenderUtilCa.mc.getRenderManager().viewerPosY, blockPos.getZ() - RenderUtilCa.mc.getRenderManager().viewerPosZ, blockPos.getX() + 1 - RenderUtilCa.mc.getRenderManager().viewerPosX, blockPos.getY() + 1 - RenderUtilCa.mc.getRenderManager().viewerPosY, blockPos.getZ() + 1 - RenderUtilCa.mc.getRenderManager().viewerPosZ), n4, colour);
    }
    
    public static void drawCornerVertices(final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final Color color) {
        final BufferBuilder getBuffer = Tessellator.getInstance().getBuffer();
        getBuffer.pos(n, n2, n3).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        getBuffer.pos(n, n2, n6 - 0.8).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        getBuffer.pos(n, n2, n6).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        getBuffer.pos(n, n2, n3 + 0.8).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        getBuffer.pos(n4, n2, n3).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        getBuffer.pos(n4, n2, n6 - 0.8).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        getBuffer.pos(n4, n2, n6).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        getBuffer.pos(n4, n2, n3 + 0.8).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        getBuffer.pos(n, n2, n3).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        getBuffer.pos(n4 - 0.8, n2, n3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        getBuffer.pos(n, n2, n6).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        getBuffer.pos(n4 - 0.8, n2, n6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        getBuffer.pos(n4, n2, n3).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        getBuffer.pos(n + 0.8, n2, n3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        getBuffer.pos(n4, n2, n6).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        getBuffer.pos(n + 0.8, n2, n6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        getBuffer.pos(n, n2, n3).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        getBuffer.pos(n, n2 + 0.2, n3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        getBuffer.pos(n, n2, n6).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        getBuffer.pos(n, n2 + 0.2, n6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        getBuffer.pos(n4, n2, n3).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        getBuffer.pos(n4, n2 + 0.2, n3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        getBuffer.pos(n4, n2, n6).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        getBuffer.pos(n4, n2 + 0.2, n6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        getBuffer.pos(n, n5, n3).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        getBuffer.pos(n, n5, n6 - 0.8).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        getBuffer.pos(n, n5, n6).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        getBuffer.pos(n, n5, n3 + 0.8).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        getBuffer.pos(n4, n5, n3).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        getBuffer.pos(n4, n5, n6 - 0.8).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        getBuffer.pos(n4, n5, n6).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        getBuffer.pos(n4, n5, n3 + 0.8).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        getBuffer.pos(n, n5, n3).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        getBuffer.pos(n4 - 0.8, n5, n3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        getBuffer.pos(n, n5, n6).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        getBuffer.pos(n4 - 0.8, n5, n6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        getBuffer.pos(n4, n5, n3).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        getBuffer.pos(n + 0.8, n5, n3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        getBuffer.pos(n4, n5, n6).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        getBuffer.pos(n + 0.8, n5, n6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        getBuffer.pos(n, n5, n3).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        getBuffer.pos(n, n5 - 0.2, n3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        getBuffer.pos(n, n5, n6).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        getBuffer.pos(n, n5 - 0.2, n6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        getBuffer.pos(n4, n5, n3).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        getBuffer.pos(n4, n5 - 0.2, n3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        getBuffer.pos(n4, n5, n6).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        getBuffer.pos(n4, n5 - 0.2, n6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
    }
    
    public static AxisAlignedBB interpolateAxis(final AxisAlignedBB axisAlignedBB) {
        return new AxisAlignedBB(axisAlignedBB.minX - RenderUtilCa.mc.getRenderManager().viewerPosX, axisAlignedBB.minY - RenderUtilCa.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ - RenderUtilCa.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX - RenderUtilCa.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY - RenderUtilCa.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ - RenderUtilCa.mc.getRenderManager().viewerPosZ);
    }
    
    public static void drawBoxESP(final BlockPos blockPos, final Color color, final Color color2, final float n, final boolean b, final boolean b2, final boolean b3) {
        if (b2) {
            drawBox(blockPos, color, b3);
        }
        if (b) {
            drawBlockOutline(blockPos, color2, n, b3);
        }
    }
    
    public static void drawLine(final float n, final float n2, final float n3, final float n4, final float n5, final int n6) {
        final float n7 = (n6 >> 16 & 0xFF) / 255.0f;
        final float n8 = (n6 >> 8 & 0xFF) / 255.0f;
        final float n9 = (n6 & 0xFF) / 255.0f;
        final float n10 = (n6 >> 24 & 0xFF) / 255.0f;
        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.shadeModel(7425);
        GL11.glLineWidth(n5);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos((double)n, (double)n2, 0.0).color(n7, n8, n9, n10).endVertex();
        getBuffer.pos((double)n3, (double)n4, 0.0).color(n7, n8, n9, n10).endVertex();
        getInstance.draw();
        GlStateManager.shadeModel(7424);
        GL11.glDisable(2848);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.popMatrix();
    }
    
    public static void drawGradientBlockOutline(final AxisAlignedBB axisAlignedBB, final Color color, final Color color2, final float n) {
        final float n2 = color.getRed() / 255.0f;
        final float n3 = color.getGreen() / 255.0f;
        final float n4 = color.getBlue() / 255.0f;
        final float n5 = color.getAlpha() / 255.0f;
        final float n6 = color2.getRed() / 255.0f;
        final float n7 = color2.getGreen() / 255.0f;
        final float n8 = color2.getBlue() / 255.0f;
        final float n9 = color2.getAlpha() / 255.0f;
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(n);
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n6, n7, n8, n9).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n6, n7, n8, n9).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n6, n7, n8, n9).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n6, n7, n8, n9).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n6, n7, n8, n9).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n6, n7, n8, n9).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n6, n7, n8, n9).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n6, n7, n8, n9).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getInstance.draw();
        GL11.glDisable(2848);
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
    private static void colorVertex(final double n, final double n2, final double n3, final Colour colour, final int n4, final BufferBuilder bufferBuilder) {
        bufferBuilder.pos(n - RenderUtilCa.mc.getRenderManager().viewerPosX, n2 - RenderUtilCa.mc.getRenderManager().viewerPosY, n3 - RenderUtilCa.mc.getRenderManager().viewerPosZ).color(colour.getRed(), colour.getGreen(), colour.getBlue(), n4).endVertex();
    }
    
    public static void drawBBBox(final AxisAlignedBB axisAlignedBB, final Color color, final int n) {
        final AxisAlignedBB axisAlignedBB2 = new AxisAlignedBB(axisAlignedBB.minX - RenderUtilCa.mc.getRenderManager().viewerPosX, axisAlignedBB.minY - RenderUtilCa.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ - RenderUtilCa.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX - RenderUtilCa.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY - RenderUtilCa.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ - RenderUtilCa.mc.getRenderManager().viewerPosZ);
        RenderUtilCa.camera.setPosition(Objects.requireNonNull(RenderUtilCa.mc.getRenderViewEntity()).posX, RenderUtilCa.mc.getRenderViewEntity().posY, RenderUtilCa.mc.getRenderViewEntity().posZ);
        if (RenderUtilCa.camera.isBoundingBoxInFrustum(new AxisAlignedBB(axisAlignedBB2.minX + RenderUtilCa.mc.getRenderManager().viewerPosX, axisAlignedBB2.minY + RenderUtilCa.mc.getRenderManager().viewerPosY, axisAlignedBB2.minZ + RenderUtilCa.mc.getRenderManager().viewerPosZ, axisAlignedBB2.maxX + RenderUtilCa.mc.getRenderManager().viewerPosX, axisAlignedBB2.maxY + RenderUtilCa.mc.getRenderManager().viewerPosY, axisAlignedBB2.maxZ + RenderUtilCa.mc.getRenderManager().viewerPosZ))) {
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.disableDepth();
            GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
            GlStateManager.disableTexture2D();
            GlStateManager.depthMask(false);
            GL11.glEnable(2848);
            GL11.glHint(3154, 4354);
            RenderGlobal.renderFilledBox(axisAlignedBB2, color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, n / 255.0f);
            GL11.glDisable(2848);
            GlStateManager.depthMask(true);
            GlStateManager.enableDepth();
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
    }
    
    public static void drawBBOutline(final AxisAlignedBB axisAlignedBB, final Colour colour, final int n) {
    }
}
