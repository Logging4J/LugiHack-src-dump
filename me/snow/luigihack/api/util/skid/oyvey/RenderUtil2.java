//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util.skid.oyvey;

import java.nio.*;
import net.minecraft.client.renderer.culling.*;
import me.snow.luigihack.mixin.mixins.accessors.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.vertex.*;
import java.awt.*;
import net.minecraft.block.material.*;
import net.minecraft.block.state.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import net.minecraft.client.shader.*;
import net.minecraft.entity.player.*;
import me.snow.luigihack.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.util.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.*;
import org.lwjgl.opengl.*;
import org.lwjgl.util.glu.*;
import net.minecraft.client.model.*;
import net.minecraft.client.gui.*;
import net.minecraft.world.*;
import java.util.*;

public class RenderUtil2 implements Util
{
    private static /* synthetic */ boolean override;
    public static /* synthetic */ ICamera camera;
    private static /* synthetic */ boolean bind;
    public static /* synthetic */ Tessellator tessellator;
    private static final /* synthetic */ FloatBuffer projection;
    private static final /* synthetic */ IntBuffer viewport;
    private static final /* synthetic */ FloatBuffer modelView;
    public static /* synthetic */ RenderItem itemRender;
    private static final /* synthetic */ FloatBuffer screenCoords;
    private static final /* synthetic */ Frustum frustrum;
    private static /* synthetic */ boolean clean;
    public static /* synthetic */ BufferBuilder builder;
    private static /* synthetic */ boolean depth;
    private static /* synthetic */ boolean texture;
    
    public static double getRenderPosX() {
        return ((IRenderManager)RenderUtil2.mc.getRenderManager()).getRenderPosX();
    }
    
    public static void drawBoundingBoxBottomBlockPosWest(final BlockPos blockPos, final float n, final int n2, final int n3, final int n4, final int n5) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(n);
        final Minecraft getMinecraft = Minecraft.getMinecraft();
        final double n6 = blockPos.getX() - getMinecraft.getRenderManager().viewerPosX;
        final double n7 = blockPos.getY() - getMinecraft.getRenderManager().viewerPosY;
        final double n8 = blockPos.getZ() - getMinecraft.getRenderManager().viewerPosZ;
        final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(n6, n7, n8, n6 + 1.0, n7 + 1.0, n8 + 1.0);
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5 / 2).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5 / 2).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getInstance.draw();
        GL11.glDisable(2848);
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
    public static void drawRoundedRectangle(final float n, final float n2, final float n3, final float n4, final float n5) {
        GL11.glEnable(3042);
        drawArc(n + n3 - n5, n2 + n4 - n5, n5, 0.0f, 90.0f, 16);
        drawArc(n + n5, n2 + n4 - n5, n5, 90.0f, 180.0f, 16);
        drawArc(n + n5, n2 + n5, n5, 180.0f, 270.0f, 16);
        drawArc(n + n3 - n5, n2 + n5, n5, 270.0f, 360.0f, 16);
        GL11.glBegin(4);
        GL11.glVertex2d((double)(n + n3 - n5), (double)n2);
        GL11.glVertex2d((double)(n + n5), (double)n2);
        GL11.glVertex2d((double)(n + n3 - n5), (double)(n2 + n5));
        GL11.glVertex2d((double)(n + n3 - n5), (double)(n2 + n5));
        GL11.glVertex2d((double)(n + n5), (double)n2);
        GL11.glVertex2d((double)(n + n5), (double)(n2 + n5));
        GL11.glVertex2d((double)(n + n3), (double)(n2 + n5));
        GL11.glVertex2d((double)n, (double)(n2 + n5));
        GL11.glVertex2d((double)n, (double)(n2 + n4 - n5));
        GL11.glVertex2d((double)(n + n3), (double)(n2 + n5));
        GL11.glVertex2d((double)n, (double)(n2 + n4 - n5));
        GL11.glVertex2d((double)(n + n3), (double)(n2 + n4 - n5));
        GL11.glVertex2d((double)(n + n3 - n5), (double)(n2 + n4 - n5));
        GL11.glVertex2d((double)(n + n5), (double)(n2 + n4 - n5));
        GL11.glVertex2d((double)(n + n3 - n5), (double)(n2 + n4));
        GL11.glVertex2d((double)(n + n3 - n5), (double)(n2 + n4));
        GL11.glVertex2d((double)(n + n5), (double)(n2 + n4 - n5));
        GL11.glVertex2d((double)(n + n5), (double)(n2 + n4));
        glEnd();
        GL11.glDisable(3042);
    }
    
    public static void drawBlockOutline(final BlockPos blockPos, final Color color, final float n, final boolean b, final double n2, final boolean b2, final boolean b3, final int a) {
        if (b2) {
            final Color color2 = new Color(color.getRed(), color.getGreen(), color.getBlue(), a);
            drawGradientBlockOutline(blockPos, b3 ? color2 : color, b3 ? color : color2, n, n2);
            return;
        }
        final IBlockState getBlockState = RenderUtil2.mc.world.getBlockState(blockPos);
        if ((b || getBlockState.getMaterial() != Material.AIR) && RenderUtil2.mc.world.getWorldBorder().contains(blockPos)) {
            drawBlockOutline(new AxisAlignedBB(blockPos.getX() - RenderUtil2.mc.getRenderManager().viewerPosX, blockPos.getY() - RenderUtil2.mc.getRenderManager().viewerPosY, blockPos.getZ() - RenderUtil2.mc.getRenderManager().viewerPosZ, blockPos.getX() + 1 - RenderUtil2.mc.getRenderManager().viewerPosX, blockPos.getY() + 1 - RenderUtil2.mc.getRenderManager().viewerPosY + n2, blockPos.getZ() + 1 - RenderUtil2.mc.getRenderManager().viewerPosZ).grow(0.0020000000949949026), color, n);
        }
    }
    
    public static void drawBBClaw(final AxisAlignedBB axisAlignedBB, final float n, final float n2, final Color color) {
        GlStateManager.pushMatrix();
        start1();
        width(n);
        final double minX = axisAlignedBB.minX;
        final double minY = axisAlignedBB.minY;
        final double minZ = axisAlignedBB.minZ;
        final double maxX = axisAlignedBB.maxX;
        final double maxY = axisAlignedBB.maxY;
        final double maxZ = axisAlignedBB.maxZ;
        RenderUtil2.builder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        vertex(minX, minY, minZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        vertex(minX, minY, minZ + n2).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        vertex(minX, minY, maxZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        vertex(minX, minY, maxZ - n2).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        vertex(maxX, minY, minZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        vertex(maxX, minY, minZ + n2).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        vertex(maxX, minY, maxZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        vertex(maxX, minY, maxZ - n2).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        vertex(minX, minY, minZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        vertex(minX + n2, minY, minZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        vertex(minX, minY, maxZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        vertex(minX + n2, minY, maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        vertex(maxX, minY, minZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        vertex(maxX - n2, minY, minZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        vertex(maxX, minY, maxZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        vertex(maxX - n2, minY, maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        vertex(minX, minY, minZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        vertex(minX, minY + n2, minZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        vertex(minX, minY, maxZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        vertex(minX, minY + n2, maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        vertex(maxX, minY, minZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        vertex(maxX, minY + n2, minZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        vertex(maxX, minY, maxZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        vertex(maxX, minY + n2, maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        vertex(minX, maxY, minZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        vertex(minX, maxY, minZ + n2).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        vertex(minX, maxY, maxZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        vertex(minX, maxY, maxZ - n2).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        vertex(maxX, maxY, minZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        vertex(maxX, maxY, minZ + n2).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        vertex(maxX, maxY, maxZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        vertex(maxX, maxY, maxZ - n2).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        vertex(minX, maxY, minZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        vertex(minX + n2, maxY, minZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        vertex(minX, maxY, maxZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        vertex(minX + n2, maxY, maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        vertex(maxX, maxY, minZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        vertex(maxX - n2, maxY, minZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        vertex(maxX, maxY, maxZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        vertex(maxX - n2, maxY, maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        vertex(minX, maxY, minZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        vertex(minX, maxY - n2, minZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        vertex(minX, maxY, maxZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        vertex(minX, maxY - n2, maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        vertex(maxX, maxY, minZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        vertex(maxX, maxY - n2, minZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        vertex(maxX, maxY, maxZ).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        vertex(maxX, maxY - n2, maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtil2.tessellator.draw();
        end1();
        GlStateManager.popMatrix();
    }
    
    public static void renderThree() {
        GL11.glStencilFunc(514, 1, 15);
        GL11.glStencilOp(7680, 7680, 7680);
        GL11.glPolygonMode(1032, 6913);
    }
    
    public static void drawArcOutline(final float n, final float n2, final float n3, final float n4, final float n5, final int n6) {
        GL11.glBegin(2);
        for (int n7 = (int)(n6 / (360.0f / n4)) + 1; n7 <= n6 / (360.0f / n5); ++n7) {
            final double n8 = 6.283185307179586 * n7 / n6;
            GL11.glVertex2d(n + Math.cos(n8) * n3, n2 + Math.sin(n8) * n3);
        }
        glEnd();
    }
    
    public static Vec3d getInterpolatedAmount(final Entity entity, final double n, final double n2, final double n3) {
        return new Vec3d((entity.posX - entity.lastTickPosX) * n, (entity.posY - entity.lastTickPosY) * n2, (entity.posZ - entity.lastTickPosZ) * n3);
    }
    
    public static BufferBuilder getBufferBuilder() {
        return RenderUtil2.tessellator.getBuffer();
    }
    
    private static void vertex(final int n, final int n2, final int n3, final int n4) {
        RenderUtil2.builder.pos(0.0, (double)RenderUtil2.mc.player.getEyeHeight(), 0.0).color(n, n2, n3, n4).endVertex();
    }
    
    public static void drawArc(final float n, final float n2, final float n3, final float n4, final float n5, final int n6) {
        GL11.glBegin(4);
        for (int n7 = (int)(n6 / (360.0f / n4)) + 1; n7 <= n6 / (360.0f / n5); ++n7) {
            final double n8 = 6.283185307179586 * (n7 - 1) / n6;
            final double n9 = 6.283185307179586 * n7 / n6;
            GL11.glVertex2d((double)n, (double)n2);
            GL11.glVertex2d(n + Math.cos(n9) * n3, n2 + Math.sin(n9) * n3);
            GL11.glVertex2d(n + Math.cos(n8) * n3, n2 + Math.sin(n8) * n3);
        }
        glEnd();
    }
    
    public static void drawRectangleXY(final float n, final float n2, final float n3, final float n4) {
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glBegin(2);
        GL11.glVertex2d((double)(n + n3), (double)n2);
        GL11.glVertex2d((double)n, (double)n2);
        GL11.glVertex2d((double)n, (double)(n2 + n4));
        GL11.glVertex2d((double)(n + n3), (double)(n2 + n4));
        glEnd();
    }
    
    public static void drawBoxESP(final BlockPos blockPos, final Color color, final float n, final boolean b, final boolean b2, final int n2) {
        final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos.getX() - RenderUtil2.mc.getRenderManager().viewerPosX, blockPos.getY() - RenderUtil2.mc.getRenderManager().viewerPosY, blockPos.getZ() - RenderUtil2.mc.getRenderManager().viewerPosZ, blockPos.getX() + 1 - RenderUtil2.mc.getRenderManager().viewerPosX, blockPos.getY() + 1 - RenderUtil2.mc.getRenderManager().viewerPosY, blockPos.getZ() + 1 - RenderUtil2.mc.getRenderManager().viewerPosZ);
        RenderUtil2.camera.setPosition(Objects.requireNonNull(RenderUtil2.mc.getRenderViewEntity()).posX, RenderUtil2.mc.getRenderViewEntity().posY, RenderUtil2.mc.getRenderViewEntity().posZ);
        if (RenderUtil2.camera.isBoundingBoxInFrustum(new AxisAlignedBB(axisAlignedBB.minX + RenderUtil2.mc.getRenderManager().viewerPosX, axisAlignedBB.minY + RenderUtil2.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ + RenderUtil2.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX + RenderUtil2.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY + RenderUtil2.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ + RenderUtil2.mc.getRenderManager().viewerPosZ))) {
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.disableDepth();
            GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
            GlStateManager.disableTexture2D();
            GlStateManager.depthMask(false);
            GL11.glEnable(2848);
            GL11.glHint(3154, 4354);
            GL11.glLineWidth(n);
            final double n3 = RenderUtil2.mc.player.getDistance((double)(blockPos.getX() + 0.5f), (double)(blockPos.getY() + 0.5f), (double)(blockPos.getZ() + 0.5f)) * 0.75;
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
    
    public static void checkSetupFBO() {
        final Framebuffer framebuffer = RenderUtil2.mc.framebuffer;
        if (framebuffer != null && framebuffer.depthBuffer > -1) {
            setupFBO(framebuffer);
            framebuffer.depthBuffer = -1;
        }
    }
    
    public static void drawBoundingBox(final AxisAlignedBB axisAlignedBB, final float n, final int n2) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(n);
        final float n3 = (n2 >> 24 & 0xFF) / 255.0f;
        final float n4 = (n2 >> 16 & 0xFF) / 255.0f;
        final float n5 = (n2 >> 8 & 0xFF) / 255.0f;
        final float n6 = (n2 & 0xFF) / 255.0f;
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n4, n5, n6, n3).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n4, n5, n6, n3).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n4, n5, n6, n3).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n4, n5, n6, n3).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n4, n5, n6, n3).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n4, n5, n6, n3).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n4, n5, n6, n3).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n4, n5, n6, n3).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n4, n5, n6, n3).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n4, n5, n6, n3).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n4, n5, n6, n3).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n4, n5, n6, n3).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n4, n5, n6, n3).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n4, n5, n6, n3).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n4, n5, n6, n3).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n4, n5, n6, n3).endVertex();
        getInstance.draw();
        GL11.glDisable(2848);
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
    public static void glBillboardDistanceScaled(final float n, final float n2, final float n3, final EntityPlayer entityPlayer, final float n4) {
        glBillboard(n, n2, n3);
        float n5 = (int)entityPlayer.getDistance((double)n, (double)n2, (double)n3) / 2.0f / (2.0f + (2.0f - n4));
        if (n5 < 1.0f) {
            n5 = 1.0f;
        }
        GlStateManager.scale(n5, n5, n5);
    }
    
    public static void draw_cube_line(final BlockPos blockPos, final int n, final String s) {
        draw_cube_line(blockPos, n >>> 16 & 0xFF, n >>> 8 & 0xFF, n & 0xFF, n >>> 24 & 0xFF, s);
    }
    
    public static void drawTexts(final AxisAlignedBB axisAlignedBB, final String s) {
        if (axisAlignedBB == null || s == null) {
            return;
        }
        GlStateManager.pushMatrix();
        glBillboardDistanceScaled((float)axisAlignedBB.minX + 0.5f, (float)axisAlignedBB.minY + 0.5f, (float)axisAlignedBB.minZ + 0.5f, (EntityPlayer)RenderUtil2.mc.player, 1.0f);
        GlStateManager.disableDepth();
        GlStateManager.translate(-(LuigiHack.textManager.getStringWidth(s) / 2.0), 0.0, 0.0);
        LuigiHack.textManager.drawStringWithShadow(s, 0.0f, 0.0f, -5592406);
        GlStateManager.popMatrix();
    }
    
    private static void vertex(final double n, final double n2, final double n3, final int n4, final int n5, final int n6, final int n7) {
        RenderUtil2.builder.pos(n - RenderUtil2.mc.getRenderManager().viewerPosX, n2 - RenderUtil2.mc.getRenderManager().viewerPosY, n3 - RenderUtil2.mc.getRenderManager().viewerPosZ).color(n4, n5, n6, n7).endVertex();
    }
    
    public static void drawTexturedRect(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        getBuffer.pos((double)(n + 0), (double)(n2 + n6), (double)n7).tex((double)((n3 + 0) * 0.00390625f), (double)((n4 + n6) * 0.00390625f)).endVertex();
        getBuffer.pos((double)(n + n5), (double)(n2 + n6), (double)n7).tex((double)((n3 + n5) * 0.00390625f), (double)((n4 + n6) * 0.00390625f)).endVertex();
        getBuffer.pos((double)(n + n5), (double)(n2 + 0), (double)n7).tex((double)((n3 + n5) * 0.00390625f), (double)((n4 + 0) * 0.00390625f)).endVertex();
        getBuffer.pos((double)(n + 0), (double)(n2 + 0), (double)n7).tex((double)((n3 + 0) * 0.00390625f), (double)((n4 + 0) * 0.00390625f)).endVertex();
        getInstance.draw();
    }
    
    public static int getTextHeight(final double n) {
        return (int)(RenderUtil2.mc.fontRenderer.FONT_HEIGHT * n);
    }
    
    public static void drawBoundingBottomBoxBlockPos(final BlockPos blockPos, final float n, final int n2, final int n3, final int n4, final int n5) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(n);
        final Minecraft getMinecraft = Minecraft.getMinecraft();
        final double n6 = blockPos.getX() - getMinecraft.getRenderManager().viewerPosX;
        final double n7 = blockPos.getY() - getMinecraft.getRenderManager().viewerPosY;
        final double n8 = blockPos.getZ() - getMinecraft.getRenderManager().viewerPosZ;
        final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(n6, n7, n8, n6 + 1.0, n7 + 0.0, n8 + 1.0);
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getInstance.draw();
        getBuffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getInstance.draw();
        getBuffer.begin(1, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getInstance.draw();
        GL11.glDisable(2848);
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
    public static void blockEsp(final BlockPos blockPos, final Color color, final double n, final double n2) {
        final double n3 = blockPos.getX() - RenderUtil2.mc.renderManager.renderPosX;
        final double n4 = blockPos.getY() - RenderUtil2.mc.renderManager.renderPosY;
        final double n5 = blockPos.getZ() - RenderUtil2.mc.renderManager.renderPosZ;
        GL11.glPushMatrix();
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(2.0f);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4d((double)(color.getRed() / 255.0f), (double)(color.getGreen() / 255.0f), (double)(color.getBlue() / 255.0f), 0.25);
        drawColorBox(new AxisAlignedBB(n3, n4, n5, n3 + n2, n4 + 1.0, n5 + n), 0.0f, 0.0f, 0.0f, 0.0f);
        GL11.glColor4d(0.0, 0.0, 0.0, 0.5);
        drawSelectionBoundingBox(new AxisAlignedBB(n3, n4, n5, n3 + n2, n4 + 1.0, n5 + n));
        GL11.glLineWidth(2.0f);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }
    
    public static void drawText(final BlockPos blockPos, final String s) {
        GlStateManager.pushMatrix();
        glBillboardDistanceScaled(blockPos.getX() + 0.5f, blockPos.getY() + 0.5f, blockPos.getZ() + 0.5f, (EntityPlayer)RenderUtil2.mc.player, 1.0f);
        GlStateManager.disableDepth();
        GlStateManager.translate(-(LuigiHack.textManager.getStringWidth(s) / 2.0), 0.0, 0.0);
        LuigiHack.textManager.drawStringWithShadow(s, 0.0f, 0.0f, -5592406);
        GlStateManager.popMatrix();
    }
    
    public static void drawFilledBox(final AxisAlignedBB axisAlignedBB, final int n) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        final float n2 = (n >> 24 & 0xFF) / 255.0f;
        final float n3 = (n >> 16 & 0xFF) / 255.0f;
        final float n4 = (n >> 8 & 0xFF) / 255.0f;
        final float n5 = (n & 0xFF) / 255.0f;
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n3, n4, n5, n2).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n3, n4, n5, n2).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n3, n4, n5, n2).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n3, n4, n5, n2).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n3, n4, n5, n2).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n3, n4, n5, n2).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n3, n4, n5, n2).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n3, n4, n5, n2).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n3, n4, n5, n2).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n3, n4, n5, n2).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n3, n4, n5, n2).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n3, n4, n5, n2).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n3, n4, n5, n2).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n3, n4, n5, n2).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n3, n4, n5, n2).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n3, n4, n5, n2).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n3, n4, n5, n2).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n3, n4, n5, n2).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n3, n4, n5, n2).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n3, n4, n5, n2).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n3, n4, n5, n2).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n3, n4, n5, n2).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n3, n4, n5, n2).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n3, n4, n5, n2).endVertex();
        getInstance.draw();
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
    public static void drawFilledRectangle(final float n, final float n2, final float n3, final float n4) {
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glBegin(7);
        GL11.glVertex2d((double)(n + n3), (double)n2);
        GL11.glVertex2d((double)n, (double)n2);
        GL11.glVertex2d((double)n, (double)(n2 + n4));
        GL11.glVertex2d((double)(n + n3), (double)(n2 + n4));
        glEnd();
    }
    
    public static void hexColor(final int n) {
        GL11.glColor4f((n >> 16 & 0xFF) / 255.0f, (n >> 8 & 0xFF) / 255.0f, (n & 0xFF) / 255.0f, (n >> 24 & 0xFF) / 255.0f);
    }
    
    public static Vec3d to2D(final double n, final double n2, final double n3) {
        GL11.glGetFloat(2982, RenderUtil2.modelView);
        GL11.glGetFloat(2983, RenderUtil2.projection);
        GL11.glGetInteger(2978, RenderUtil2.viewport);
        if (GLU.gluProject((float)n, (float)n2, (float)n3, RenderUtil2.modelView, RenderUtil2.projection, RenderUtil2.viewport, RenderUtil2.screenCoords)) {
            return new Vec3d((double)RenderUtil2.screenCoords.get(0), (double)(Display.getHeight() - RenderUtil2.screenCoords.get(1)), (double)RenderUtil2.screenCoords.get(2));
        }
        return null;
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
    
    public static void drawBorderedRect(final double n, final double n2, final double n3, final double n4, final double n5, final int n6, final int n7) {
        enableGL2D();
        fakeGuiRect(n + n5, n2 + n5, n3 - n5, n4 - n5, n6);
        fakeGuiRect(n + n5, n2, n3 - n5, n2 + n5, n7);
        fakeGuiRect(n, n2, n + n5, n4, n7);
        fakeGuiRect(n3 - n5, n2, n3, n4, n7);
        fakeGuiRect(n + n5, n4 - n5, n3 - n5, n4, n7);
        disableGL2D();
    }
    
    public static void drawLines(final BufferBuilder bufferBuilder, final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final int n7, final int n8, final int n9, final int n10, final int n11) {
        if ((n11 & 0x11) != 0x0) {
            bufferBuilder.pos((double)n, (double)n2, (double)n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)n, (double)n2, (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
        }
        if ((n11 & 0x12) != 0x0) {
            bufferBuilder.pos((double)n, (double)(n2 + n5), (double)n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)n, (double)(n2 + n5), (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
        }
        if ((n11 & 0x21) != 0x0) {
            bufferBuilder.pos((double)(n + n4), (double)n2, (double)n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)(n + n4), (double)n2, (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
        }
        if ((n11 & 0x22) != 0x0) {
            bufferBuilder.pos((double)(n + n4), (double)(n2 + n5), (double)n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)(n + n4), (double)(n2 + n5), (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
        }
        if ((n11 & 0x5) != 0x0) {
            bufferBuilder.pos((double)n, (double)n2, (double)n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)(n + n4), (double)n2, (double)n3).color(n7, n8, n9, n10).endVertex();
        }
        if ((n11 & 0x6) != 0x0) {
            bufferBuilder.pos((double)n, (double)(n2 + n5), (double)n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)(n + n4), (double)(n2 + n5), (double)n3).color(n7, n8, n9, n10).endVertex();
        }
        if ((n11 & 0x9) != 0x0) {
            bufferBuilder.pos((double)n, (double)n2, (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)(n + n4), (double)n2, (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
        }
        if ((n11 & 0xA) != 0x0) {
            bufferBuilder.pos((double)n, (double)(n2 + n5), (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)(n + n4), (double)(n2 + n5), (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
        }
        if ((n11 & 0x14) != 0x0) {
            bufferBuilder.pos((double)n, (double)n2, (double)n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)n, (double)(n2 + n5), (double)n3).color(n7, n8, n9, n10).endVertex();
        }
        if ((n11 & 0x24) != 0x0) {
            bufferBuilder.pos((double)(n + n4), (double)n2, (double)n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)(n + n4), (double)(n2 + n5), (double)n3).color(n7, n8, n9, n10).endVertex();
        }
        if ((n11 & 0x18) != 0x0) {
            bufferBuilder.pos((double)n, (double)n2, (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)n, (double)(n2 + n5), (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
        }
        if ((n11 & 0x28) != 0x0) {
            bufferBuilder.pos((double)(n + n4), (double)n2, (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)(n + n4), (double)(n2 + n5), (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
        }
    }
    
    public static void release() {
        GlStateManager.depthMask(true);
        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
        GlStateManager.enableAlpha();
        GlStateManager.popMatrix();
        GL11.glEnable(3553);
        GL11.glShadeModel(7424);
        GL11.glPolygonMode(1032, 6914);
    }
    
    public static void drawColoredBoundingBox(final AxisAlignedBB axisAlignedBB, final float n, final float n2, final float n3, final float n4, final float n5) {
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
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, 0.0f).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n2, n3, n4, 0.0f).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n2, n3, n4, 0.0f).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n2, n3, n4, 0.0f).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, 0.0f).endVertex();
        getInstance.draw();
        GL11.glDisable(2848);
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
    public static void renderOne(final float n) {
        checkSetupFBO();
        GL11.glPushAttrib(1048575);
        GL11.glDisable(3008);
        GL11.glDisable(3553);
        GL11.glDisable(2896);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glLineWidth(n);
        GL11.glEnable(2848);
        GL11.glEnable(2960);
        GL11.glClear(1024);
        GL11.glClearStencil(15);
        GL11.glStencilFunc(512, 1, 15);
        GL11.glStencilOp(7681, 7681, 7681);
        GL11.glPolygonMode(1032, 6913);
    }
    
    public static void drawBox(final BlockPos blockPos, final Color color, final double n, final boolean b, final boolean b2, final int a) {
        if (b) {
            final Color color2 = new Color(color.getRed(), color.getGreen(), color.getBlue(), a);
            drawOpenGradientBox(blockPos, b2 ? color2 : color, b2 ? color : color2, n);
            return;
        }
        final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos.getX() - RenderUtil2.mc.getRenderManager().viewerPosX, blockPos.getY() - RenderUtil2.mc.getRenderManager().viewerPosY, blockPos.getZ() - RenderUtil2.mc.getRenderManager().viewerPosZ, blockPos.getX() + 1 - RenderUtil2.mc.getRenderManager().viewerPosX, blockPos.getY() + 1 - RenderUtil2.mc.getRenderManager().viewerPosY + n, blockPos.getZ() + 1 - RenderUtil2.mc.getRenderManager().viewerPosZ);
        RenderUtil2.camera.setPosition(Objects.requireNonNull(RenderUtil2.mc.getRenderViewEntity()).posX, RenderUtil2.mc.getRenderViewEntity().posY, RenderUtil2.mc.getRenderViewEntity().posZ);
        if (RenderUtil2.camera.isBoundingBoxInFrustum(new AxisAlignedBB(axisAlignedBB.minX + RenderUtil2.mc.getRenderManager().viewerPosX, axisAlignedBB.minY + RenderUtil2.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ + RenderUtil2.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX + RenderUtil2.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY + RenderUtil2.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ + RenderUtil2.mc.getRenderManager().viewerPosZ))) {
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
    
    public static void render() {
        RenderUtil2.tessellator.draw();
    }
    
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
        GL11.glLineWidth(2.0f);
    }
    
    public static void gradientBox(final BlockPos blockPos, final Color color, final float n, final boolean b, final boolean b2, final int n2, final boolean b3) {
        final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos.getX() - RenderUtil2.mc.getRenderManager().viewerPosX, blockPos.getY() - RenderUtil2.mc.getRenderManager().viewerPosY, blockPos.getZ() - RenderUtil2.mc.getRenderManager().viewerPosZ, blockPos.getX() + 1 - RenderUtil2.mc.getRenderManager().viewerPosX, blockPos.getY() + 1 - RenderUtil2.mc.getRenderManager().viewerPosY, blockPos.getZ() + 1 - RenderUtil2.mc.getRenderManager().viewerPosZ);
        RenderUtil2.camera.setPosition(Objects.requireNonNull(RenderUtil2.mc.getRenderViewEntity()).posX, RenderUtil2.mc.getRenderViewEntity().posY, RenderUtil2.mc.getRenderViewEntity().posZ);
        if (RenderUtil2.camera.isBoundingBoxInFrustum(new AxisAlignedBB(axisAlignedBB.minX + RenderUtil2.mc.getRenderManager().viewerPosX, axisAlignedBB.minY + RenderUtil2.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ + RenderUtil2.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX + RenderUtil2.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY + RenderUtil2.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ + RenderUtil2.mc.getRenderManager().viewerPosZ))) {
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.disableDepth();
            GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
            GlStateManager.disableTexture2D();
            GlStateManager.depthMask(false);
            GL11.glEnable(2848);
            GL11.glHint(3154, 4354);
            GL11.glLineWidth(n);
            final double n3 = RenderUtil2.mc.player.getDistance((double)(blockPos.getX() + 0.5f), (double)(blockPos.getY() + 0.5f), (double)(blockPos.getZ() + 0.5f)) * 0.75;
            GL11.glDisable(2848);
            GlStateManager.depthMask(true);
            GlStateManager.enableDepth();
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
    }
    
    public static void start(final float n) {
        GlStateManager.pushMatrix();
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDisable(2884);
        GL11.glDepthMask(false);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        width(n);
    }
    
    public static AxisAlignedBB fixBB(final AxisAlignedBB axisAlignedBB) {
        return new AxisAlignedBB(axisAlignedBB.minX - RenderUtil2.mc.getRenderManager().viewerPosX, axisAlignedBB.minY - RenderUtil2.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ - RenderUtil2.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX - RenderUtil2.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY - RenderUtil2.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ - RenderUtil2.mc.getRenderManager().viewerPosZ);
    }
    
    private static void GLPre(final boolean b, final boolean b2, final boolean b3, final boolean b4, final boolean b5, final float n) {
        if (b) {
            GL11.glDisable(2896);
        }
        if (!b2) {
            GL11.glEnable(3042);
        }
        GL11.glLineWidth(n);
        if (b3) {
            GL11.glDisable(3553);
        }
        if (b4) {
            GL11.glDisable(2929);
        }
        if (!b5) {
            GL11.glEnable(2848);
        }
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GL11.glHint(3154, 4354);
        GlStateManager.depthMask(false);
    }
    
    public static void drawGradientBlockOutline(final BlockPos blockPos, final Color color, final Color color2, final float n, final double n2) {
        final IBlockState getBlockState = RenderUtil2.mc.world.getBlockState(blockPos);
        final Vec3d interpolateEntity = EntityUtil.interpolateEntity((Entity)RenderUtil2.mc.player, RenderUtil2.mc.getRenderPartialTicks());
        drawGradientBlockOutline(getBlockState.getSelectedBoundingBox((World)RenderUtil2.mc.world, blockPos).grow(0.0020000000949949026).offset(-interpolateEntity.x, -interpolateEntity.y, -interpolateEntity.z).expand(0.0, n2, 0.0), color, color2, n);
    }
    
    public static void draw_cube(final float n, final float n2, final float n3, final int n4, final String s) {
        draw_cube(RenderUtil2.tessellator.getBuffer(), n, n2, n3, 1.0f, 1.0f, 1.0f, n4 >>> 16 & 0xFF, n4 >>> 8 & 0xFF, n4 & 0xFF, n4 >>> 24 & 0xFF, s);
    }
    
    public static void drawFace(final BufferBuilder bufferBuilder, final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final int n7, final int n8, final int n9, final int n10, final int n11) {
        if ((n11 & 0x1) != 0x0) {
            bufferBuilder.pos((double)(n + n4), (double)n2, (double)n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)(n + n4), (double)n2, (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)n, (double)n2, (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos((double)n, (double)n2, (double)n3).color(n7, n8, n9, n10).endVertex();
        }
    }
    
    public static void drawBoundingBoxBottomBlockPosSouth(final BlockPos blockPos, final float n, final int n2, final int n3, final int n4, final int n5) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(n);
        final Minecraft getMinecraft = Minecraft.getMinecraft();
        final double n6 = blockPos.getX() - getMinecraft.getRenderManager().viewerPosX;
        final double n7 = blockPos.getY() - getMinecraft.getRenderManager().viewerPosY;
        final double n8 = blockPos.getZ() - getMinecraft.getRenderManager().viewerPosZ;
        final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(n6, n7, n8, n6 + 1.0, n7 + 1.0, n8 + 1.0);
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getInstance.draw();
        GL11.glDisable(2848);
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
    public static void drawOutlineRect(final float n, final float n2, final float n3, final float n4, final int n5) {
        final float n6 = (n5 >> 24 & 0xFF) / 255.0f;
        final float n7 = (n5 >> 16 & 0xFF) / 255.0f;
        final float n8 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n9 = (n5 & 0xFF) / 255.0f;
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.glLineWidth(1.0f);
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        getBuffer.begin(2, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos((double)n, (double)n4, 0.0).color(n7, n8, n9, n6).endVertex();
        getBuffer.pos((double)n3, (double)n4, 0.0).color(n7, n8, n9, n6).endVertex();
        getBuffer.pos((double)n3, (double)n2, 0.0).color(n7, n8, n9, n6).endVertex();
        getBuffer.pos((double)n, (double)n2, 0.0).color(n7, n8, n9, n6).endVertex();
        getInstance.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public static void drawOutlinedRoundedRectangle(final int n, final int n2, final int n3, final int n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10) {
        drawRoundedRectangle((float)n, (float)n2, (float)n3, (float)n4, n5);
        GL11.glColor4f(n6, n7, n8, n9);
        drawRoundedRectangle(n + n10, n2 + n10, n3 - n10 * 2.0f, n4 - n10 * 2.0f, n5);
    }
    
    public static void renderBB(final int n, AxisAlignedBB updateToCamera, final Color color, final Color color2) {
        GL11.glShadeModel(7425);
        updateToCamera = updateToCamera(updateToCamera);
        prepare();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        (RenderUtil2.builder = RenderUtil2.tessellator.getBuffer()).begin(n, DefaultVertexFormats.POSITION_COLOR);
        buildBBBuffer(RenderUtil2.builder, updateToCamera, color, color2);
        RenderUtil2.tessellator.draw();
        release();
        GL11.glShadeModel(7424);
    }
    
    public static void draw_cube_line(final BufferBuilder bufferBuilder, final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final int n7, final int n8, final int n9, final int n10, final String s) {
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
    
    public static void glEnd() {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glPopMatrix();
        GL11.glEnable(2929);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
    }
    
    public static void fakeGuiRect(double n, double n2, double n3, double n4, final int n5) {
        if (n < n3) {
            final double n6 = n;
            n = n3;
            n3 = n6;
        }
        if (n2 < n4) {
            final double n7 = n2;
            n2 = n4;
            n4 = n7;
        }
        final float n8 = (n5 >> 24 & 0xFF) / 255.0f;
        final float n9 = (n5 >> 16 & 0xFF) / 255.0f;
        final float n10 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n11 = (n5 & 0xFF) / 255.0f;
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.color(n9, n10, n11, n8);
        getBuffer.begin(7, DefaultVertexFormats.POSITION);
        getBuffer.pos(n, n4, 0.0).endVertex();
        getBuffer.pos(n3, n4, 0.0).endVertex();
        getBuffer.pos(n3, n2, 0.0).endVertex();
        getBuffer.pos(n, n2, 0.0).endVertex();
        getInstance.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public static void drawBoxESP(final BlockPos blockPos, final Color color, final boolean b, final Color color2, final float n, final boolean b2, final boolean b3, final int a, final boolean b4) {
        if (b3) {
            drawBox(blockPos, new Color(color.getRed(), color.getGreen(), color.getBlue(), a));
        }
        if (b2) {
            drawBlockOutline(blockPos, b ? color2 : color, n, b4);
        }
    }
    
    public static void drawOutlinedBlockESP(final BlockPos blockPos, final Color color, final float n) {
        final IBlockState getBlockState = RenderUtil2.mc.world.getBlockState(blockPos);
        final Vec3d interpolateEntity = EntityUtil.interpolateEntity((Entity)RenderUtil2.mc.player, RenderUtil2.mc.getRenderPartialTicks());
        drawBoundingBox(getBlockState.getSelectedBoundingBox((World)RenderUtil2.mc.world, blockPos).grow(0.0020000000949949026).offset(-interpolateEntity.x, -interpolateEntity.y, -interpolateEntity.z), n, ColorUtil.toRGBA(color));
    }
    
    public static void glStart(final float n, final float n2, final float n3, final float n4) {
        glrendermethod();
        GL11.glColor4f(n, n2, n3, n4);
    }
    
    public static void drawBoxBottom(final BufferBuilder bufferBuilder, final float n, final float n2, final float n3, final float n4, final float n5, final int n6, final int n7, final int n8, final int n9) {
        bufferBuilder.pos((double)(n + n4), (double)n2, (double)n3).color(n6, n7, n8, n9).endVertex();
        bufferBuilder.pos((double)(n + n4), (double)n2, (double)(n3 + n5)).color(n6, n7, n8, n9).endVertex();
        bufferBuilder.pos((double)n, (double)n2, (double)(n3 + n5)).color(n6, n7, n8, n9).endVertex();
        bufferBuilder.pos((double)n, (double)n2, (double)n3).color(n6, n7, n8, n9).endVertex();
    }
    
    public static Vec3d getInterpolatedAmount(final Entity entity, final double n) {
        return getInterpolatedAmount(entity, n, n, n);
    }
    
    public static void drawFilledBoxESPN(final BlockPos blockPos, final Color color) {
        drawFilledBox(new AxisAlignedBB(blockPos.getX() - RenderUtil2.mc.getRenderManager().viewerPosX, blockPos.getY() - RenderUtil2.mc.getRenderManager().viewerPosY, blockPos.getZ() - RenderUtil2.mc.getRenderManager().viewerPosZ, blockPos.getX() + 1 - RenderUtil2.mc.getRenderManager().viewerPosX, blockPos.getY() + 1 - RenderUtil2.mc.getRenderManager().viewerPosY, blockPos.getZ() + 1 - RenderUtil2.mc.getRenderManager().viewerPosZ), ColorUtil.toRGBA(color));
    }
    
    public static Vec3d updateToCamera(final Vec3d vec3d) {
        return new Vec3d(vec3d.x - RenderUtil2.mc.getRenderManager().viewerPosX, vec3d.y - RenderUtil2.mc.getRenderManager().viewerPosY, vec3d.z - RenderUtil2.mc.getRenderManager().viewerPosZ);
    }
    
    public static void draw_cube(final BlockPos blockPos, final int n, final int n2, final int n3, final int n4, final String s) {
        draw_cube(RenderUtil2.tessellator.getBuffer(), (float)blockPos.getX(), (float)blockPos.getY(), (float)blockPos.getZ(), 1.0f, 1.0f, 1.0f, n, n2, n3, n4, s);
    }
    
    public static double getRenderPosY() {
        return ((IRenderManager)RenderUtil2.mc.getRenderManager()).getRenderPosY();
    }
    
    public static void drawCircle(final float n, final float n2, final float n3, final float n4, final Color color) {
        final BlockPos blockPos = new BlockPos((double)n, (double)n2, (double)n3);
        final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos.getX() - RenderUtil2.mc.getRenderManager().viewerPosX, blockPos.getY() - RenderUtil2.mc.getRenderManager().viewerPosY, blockPos.getZ() - RenderUtil2.mc.getRenderManager().viewerPosZ, blockPos.getX() + 1 - RenderUtil2.mc.getRenderManager().viewerPosX, blockPos.getY() + 1 - RenderUtil2.mc.getRenderManager().viewerPosY, blockPos.getZ() + 1 - RenderUtil2.mc.getRenderManager().viewerPosZ);
        RenderUtil2.camera.setPosition(Objects.requireNonNull(RenderUtil2.mc.getRenderViewEntity()).posX, RenderUtil2.mc.getRenderViewEntity().posY, RenderUtil2.mc.getRenderViewEntity().posZ);
        if (RenderUtil2.camera.isBoundingBoxInFrustum(new AxisAlignedBB(axisAlignedBB.minX + RenderUtil2.mc.getRenderManager().viewerPosX, axisAlignedBB.minY + RenderUtil2.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ + RenderUtil2.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX + RenderUtil2.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY + RenderUtil2.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ + RenderUtil2.mc.getRenderManager().viewerPosZ))) {
            drawCircleVertices(axisAlignedBB, n4, color);
        }
    }
    
    public static void drawCircle(final float n, final float n2, final float n3, final int n4, final int n5, final int n6) {
        drawArc(n, n2, n3, (float)n4, (float)n5, n6);
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
    
    public static void drawOpenGradientBox(final BlockPos blockPos, final Color color, final Color color2, final double n) {
        for (final EnumFacing enumFacing : EnumFacing.values()) {
            if (enumFacing != EnumFacing.UP) {
                drawGradientPlane(blockPos, enumFacing, color, color2, n);
            }
        }
    }
    
    public static void drawRectangle(final float n, final float n2, final float n3, final float n4) {
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glBegin(2);
        GL11.glVertex2d((double)n3, 0.0);
        GL11.glVertex2d(0.0, 0.0);
        GL11.glVertex2d(0.0, (double)n4);
        GL11.glVertex2d((double)n3, (double)n4);
        glEnd();
    }
    
    public static BufferBuilder get_buffer_build() {
        return RenderUtil2.tessellator.getBuffer();
    }
    
    public static AxisAlignedBB interpolateAxis(final AxisAlignedBB axisAlignedBB) {
        return new AxisAlignedBB(axisAlignedBB.minX - RenderUtil2.mc.getRenderManager().viewerPosX, axisAlignedBB.minY - RenderUtil2.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ - RenderUtil2.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX - RenderUtil2.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY - RenderUtil2.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ - RenderUtil2.mc.getRenderManager().viewerPosZ);
    }
    
    public static void drawBoundingBoxBottomBlockPosEast(final BlockPos blockPos, final float n, final int n2, final int n3, final int n4, final int n5) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(n);
        final Minecraft getMinecraft = Minecraft.getMinecraft();
        final double n6 = blockPos.getX() - getMinecraft.getRenderManager().viewerPosX;
        final double n7 = blockPos.getY() - getMinecraft.getRenderManager().viewerPosY;
        final double n8 = blockPos.getZ() - getMinecraft.getRenderManager().viewerPosZ;
        final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(n6, n7, n8, n6 + 1.0, n7 + 1.0, n8 + 1.0);
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getInstance.draw();
        GL11.glDisable(2848);
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
    public static Vec3d getInterpolatedPos(final Entity entity, final float n) {
        return new Vec3d(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ).add(getInterpolatedAmount(entity, n));
    }
    
    private static void disableGL2D() {
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glHint(3155, 4352);
    }
    
    public static void drawBordered(final float n, final float n2, final float n3, final float n4, final float n5, final int n6, final int n7) {
        float n8 = 0.0f;
        if (n5 < 1.0f) {
            n8 = 1.0f;
        }
        drawRect(n + n5, n2 + n5, n3 - n5, n4 - n5, n6);
        drawRect(n, n2 + 1.0f - n8, n + n5, n4, n7);
        drawRect(n, n2, n3 - 1.0f + n8, n2 + n5, n7);
        drawRect(n3 - n5, n2, n3, n4 - 1.0f + n8, n7);
        drawRect(n + 1.0f - n8, n4 - n5, n3, n4, n7);
    }
    
    public static void drawBoundingBoxBlockPos(final BlockPos blockPos, final float n, final int n2, final int n3, final int n4, final int n5) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(n);
        final Minecraft getMinecraft = Minecraft.getMinecraft();
        final double n6 = blockPos.getX() - getMinecraft.getRenderManager().viewerPosX;
        final double n7 = blockPos.getY() - getMinecraft.getRenderManager().viewerPosY;
        final double n8 = blockPos.getZ() - getMinecraft.getRenderManager().viewerPosZ;
        final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(n6, n7, n8, n6 + 1.0, n7 + 1.0, n8 + 1.0);
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getInstance.draw();
        getBuffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getInstance.draw();
        getBuffer.begin(1, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getInstance.draw();
        GL11.glDisable(2848);
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
    public static void blockESP(final BlockPos blockPos, final Color color, final double n, final double n2) {
        blockEsp(blockPos, color, n, n2);
    }
    
    public static void releaseGL() {
        GlStateManager.enableCull();
        GlStateManager.depthMask(true);
        GlStateManager.enableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.enableDepth();
    }
    
    public static void drawRectangle(final float n, final float n2, final float n3, final float n4, final int n5) {
        final float n6 = (n5 >> 16 & 0xFF) / 255.0f;
        final float n7 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n8 = (n5 & 0xFF) / 255.0f;
        final float n9 = (n5 >> 24 & 0xFF) / 255.0f;
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        getBuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos((double)n, (double)n4, 0.0).color(n6, n7, n8, n9).endVertex();
        getBuffer.pos((double)n3, (double)n4, 0.0).color(n6, n7, n8, n9).endVertex();
        getBuffer.pos((double)n3, (double)n2, 0.0).color(n6, n7, n8, n9).endVertex();
        getBuffer.pos((double)n, (double)n2, 0.0).color(n6, n7, n8, n9).endVertex();
        getInstance.draw();
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public static void drawBoundingBoxBottomBlockPosNorth(final BlockPos blockPos, final float n, final int n2, final int n3, final int n4, final int n5) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(n);
        final Minecraft getMinecraft = Minecraft.getMinecraft();
        final double n6 = blockPos.getX() - getMinecraft.getRenderManager().viewerPosX;
        final double n7 = blockPos.getY() - getMinecraft.getRenderManager().viewerPosY;
        final double n8 = blockPos.getZ() - getMinecraft.getRenderManager().viewerPosZ;
        final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(n6, n7, n8, n6 + 1.0, n7 + 1.0, n8 + 1.0);
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5 / 2).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5 / 2).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n2, n3, n4, n5 / 2).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5 / 2).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5 / 2).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getInstance.draw();
        GL11.glDisable(2848);
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
    public static void renderTwo() {
        GL11.glStencilFunc(512, 0, 15);
        GL11.glStencilOp(7681, 7681, 7681);
        GL11.glPolygonMode(1032, 6914);
    }
    
    public static void drawOutlinedBox(final AxisAlignedBB axisAlignedBB) {
        GL11.glBegin(1);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glEnd();
    }
    
    public static void renderBBFog(AxisAlignedBB updateToCamera, final Color color, final Color color2) {
        GL11.glShadeModel(7425);
        updateToCamera = updateToCamera(updateToCamera);
        prepare();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        (RenderUtil2.builder = RenderUtil2.tessellator.getBuffer()).begin(4, DefaultVertexFormats.POSITION_COLOR);
        buildBBBufferFog(RenderUtil2.builder, updateToCamera, color, color2);
        RenderUtil2.tessellator.draw();
        release();
        GL11.glShadeModel(7424);
    }
    
    private static void enableGL2D() {
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glDepthMask(true);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
    }
    
    public static void drawBetterBoxESP(final AxisAlignedBB axisAlignedBB, final Color color, final Color color2, final float n, final boolean b, final boolean b2, final boolean b3, float n2, final float n3, final float n4) {
        final double n5 = 0.5 * (1.0f - n3);
        final AxisAlignedBB interpolateAxis = interpolateAxis(new AxisAlignedBB(axisAlignedBB.minX + n5, axisAlignedBB.minY + n5 + (1.0f - n4), axisAlignedBB.minZ + n5, axisAlignedBB.maxX - n5, axisAlignedBB.maxY - n5, axisAlignedBB.maxZ - n5));
        final float n6 = color.getRed() / 255.0f;
        final float n7 = color.getGreen() / 255.0f;
        final float n8 = color.getBlue() / 255.0f;
        final float n9 = color.getAlpha() / 255.0f;
        final float n10 = color2.getRed() / 255.0f;
        final float n11 = color2.getGreen() / 255.0f;
        final float n12 = color2.getBlue() / 255.0f;
        final float n13 = color2.getAlpha() / 255.0f;
        if (b3) {}
        if (n2 > 1.0f) {
            n2 = 1.0f;
        }
        final float n14 = n9 * n2;
        final float n15 = n13 * n2;
        if (b2) {
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.disableDepth();
            GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
            GlStateManager.disableTexture2D();
            GlStateManager.depthMask(false);
            GL11.glEnable(2848);
            GL11.glHint(3154, 4354);
            RenderGlobal.renderFilledBox(interpolateAxis, n6, n7, n8, n14);
            GL11.glDisable(2848);
            GlStateManager.depthMask(true);
            GlStateManager.enableDepth();
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
        if (b) {
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
            getBuffer.pos(interpolateAxis.minX, interpolateAxis.minY, interpolateAxis.minZ).color(n10, n11, n12, n15).endVertex();
            getBuffer.pos(interpolateAxis.minX, interpolateAxis.minY, interpolateAxis.maxZ).color(n10, n11, n12, n15).endVertex();
            getBuffer.pos(interpolateAxis.maxX, interpolateAxis.minY, interpolateAxis.maxZ).color(n10, n11, n12, n15).endVertex();
            getBuffer.pos(interpolateAxis.maxX, interpolateAxis.minY, interpolateAxis.minZ).color(n10, n11, n12, n15).endVertex();
            getBuffer.pos(interpolateAxis.minX, interpolateAxis.minY, interpolateAxis.minZ).color(n10, n11, n12, n15).endVertex();
            getBuffer.pos(interpolateAxis.minX, interpolateAxis.maxY, interpolateAxis.minZ).color(n10, n11, n12, n15).endVertex();
            getBuffer.pos(interpolateAxis.minX, interpolateAxis.maxY, interpolateAxis.maxZ).color(n10, n11, n12, n15).endVertex();
            getBuffer.pos(interpolateAxis.minX, interpolateAxis.minY, interpolateAxis.maxZ).color(n10, n11, n12, n15).endVertex();
            getBuffer.pos(interpolateAxis.maxX, interpolateAxis.minY, interpolateAxis.maxZ).color(n10, n11, n12, n15).endVertex();
            getBuffer.pos(interpolateAxis.maxX, interpolateAxis.maxY, interpolateAxis.maxZ).color(n10, n11, n12, n15).endVertex();
            getBuffer.pos(interpolateAxis.minX, interpolateAxis.maxY, interpolateAxis.maxZ).color(n10, n11, n12, n15).endVertex();
            getBuffer.pos(interpolateAxis.maxX, interpolateAxis.maxY, interpolateAxis.maxZ).color(n10, n11, n12, n15).endVertex();
            getBuffer.pos(interpolateAxis.maxX, interpolateAxis.maxY, interpolateAxis.minZ).color(n10, n11, n12, n15).endVertex();
            getBuffer.pos(interpolateAxis.maxX, interpolateAxis.minY, interpolateAxis.minZ).color(n10, n11, n12, n15).endVertex();
            getBuffer.pos(interpolateAxis.maxX, interpolateAxis.maxY, interpolateAxis.minZ).color(n10, n11, n12, n15).endVertex();
            getBuffer.pos(interpolateAxis.minX, interpolateAxis.maxY, interpolateAxis.minZ).color(n10, n11, n12, n15).endVertex();
            getInstance.draw();
            GL11.glDisable(2848);
            GlStateManager.depthMask(true);
            GlStateManager.enableDepth();
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
    }
    
    public static void drawBBSlab(final AxisAlignedBB axisAlignedBB, final float n, final Color color) {
        final int red = color.getRed();
        final int green = color.getGreen();
        final int blue = color.getBlue();
        final int alpha = color.getAlpha();
        final double minX = axisAlignedBB.minX;
        final double minY = axisAlignedBB.minY;
        final double minZ = axisAlignedBB.minZ;
        final double maxX = axisAlignedBB.maxX;
        final double n2 = axisAlignedBB.maxY + n;
        final double maxZ = axisAlignedBB.maxZ;
        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GL11.glDisable(2929);
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.disableCull();
        GlStateManager.shadeModel(7425);
        RenderUtil2.builder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        vertex(minX, minY, minZ).color(red, green, blue, alpha).endVertex();
        vertex(maxX, minY, minZ).color(red, green, blue, alpha).endVertex();
        vertex(maxX, minY, maxZ).color(red, green, blue, alpha).endVertex();
        vertex(minX, minY, maxZ).color(red, green, blue, alpha).endVertex();
        vertex(minX, n2, minZ).color(0, 0, 0, 0).endVertex();
        vertex(minX, n2, maxZ).color(0, 0, 0, 0).endVertex();
        vertex(maxX, n2, maxZ).color(0, 0, 0, 0).endVertex();
        vertex(maxX, n2, minZ).color(0, 0, 0, 0).endVertex();
        vertex(minX, minY, minZ).color(red, green, blue, alpha).endVertex();
        vertex(minX, n2, minZ).color(0, 0, 0, 0).endVertex();
        vertex(maxX, n2, minZ).color(0, 0, 0, 0).endVertex();
        vertex(maxX, minY, minZ).color(red, green, blue, alpha).endVertex();
        vertex(maxX, minY, minZ).color(red, green, blue, alpha).endVertex();
        vertex(maxX, n2, minZ).color(0, 0, 0, 0).endVertex();
        vertex(maxX, n2, maxZ).color(0, 0, 0, 0).endVertex();
        vertex(maxX, minY, maxZ).color(red, green, blue, alpha).endVertex();
        vertex(minX, minY, maxZ).color(red, green, blue, alpha).endVertex();
        vertex(maxX, minY, maxZ).color(red, green, blue, alpha).endVertex();
        vertex(maxX, n2, maxZ).color(0, 0, 0, 0).endVertex();
        vertex(minX, n2, maxZ).color(0, 0, 0, 0).endVertex();
        vertex(minX, minY, minZ).color(red, green, blue, alpha).endVertex();
        vertex(minX, minY, maxZ).color(red, green, blue, alpha).endVertex();
        vertex(minX, n2, maxZ).color(0, 0, 0, 0).endVertex();
        vertex(minX, n2, minZ).color(0, 0, 0, 0).endVertex();
        RenderUtil2.tessellator.draw();
        GL11.glEnable(2929);
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableCull();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.popMatrix();
    }
    
    public static boolean isInViewFrustrum(final Entity entity) {
        return isInViewFrustrum(entity.getEntityBoundingBox()) || entity.ignoreFrustumCheck;
    }
    
    public static void drawCircleVertices(final AxisAlignedBB axisAlignedBB, final float n, final Color color) {
        final float n2 = color.getRed() / 255.0f;
        final float n3 = color.getGreen() / 255.0f;
        final float n4 = color.getBlue() / 255.0f;
        final float n5 = color.getAlpha() / 255.0f;
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
    
    public static void setColor(final Color color) {
        GL11.glColor4d(color.getRed() / 255.0, color.getGreen() / 255.0, color.getBlue() / 255.0, color.getAlpha() / 255.0);
    }
    
    public static int getTextWidth(final String s, final double n) {
        return (int)(RenderUtil2.mc.fontRenderer.getStringWidth(s) * n);
    }
    
    public static void drawBox(final BlockPos blockPos, final int n, final int n2) {
        drawBox(blockPos, n >>> 16 & 0xFF, n >>> 8 & 0xFF, n & 0xFF, n >>> 24 & 0xFF, n2);
    }
    
    public static void end2() {
        GlStateManager.enableTexture2D();
        GlStateManager.enableDepth();
        GlStateManager.disableBlend();
        GlStateManager.enableCull();
        GlStateManager.shadeModel(7424);
        GlStateManager.enableAlpha();
        GlStateManager.depthMask(true);
        GL11.glDisable(34383);
        GL11.glDisable(2848);
        GlStateManager.color(1.0f, 1.0f, 1.0f);
        width(1.0f);
        GlStateManager.popMatrix();
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
    
    public static void drawCircleOutline(final float n, final float n2, final float n3, final int n4, final int n5, final int n6) {
        drawArcOutline(n, n2, n3, (float)n4, (float)n5, n6);
    }
    
    public static void glBillboard(final float n, final float n2, final float n3) {
        final float n4 = 0.02666667f;
        GlStateManager.translate(n - RenderUtil2.mc.getRenderManager().renderPosX, n2 - RenderUtil2.mc.getRenderManager().renderPosY, n3 - RenderUtil2.mc.getRenderManager().renderPosZ);
        GlStateManager.glNormal3f(0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(-RenderUtil2.mc.player.rotationYaw, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(RenderUtil2.mc.player.rotationPitch, (RenderUtil2.mc.gameSettings.thirdPersonView == 2) ? -1.0f : 1.0f, 0.0f, 0.0f);
        GlStateManager.scale(-n4, -n4, n4);
    }
    
    public static void prepare(final int n) {
        prepareGL();
        begin(n);
    }
    
    public static void glScissor(final float n, final float n2, final float n3, final float n4, final ScaledResolution scaledResolution) {
        GL11.glScissor((int)(n * scaledResolution.getScaleFactor()), (int)(RenderUtil2.mc.displayHeight - n4 * scaledResolution.getScaleFactor()), (int)((n3 - n) * scaledResolution.getScaleFactor()), (int)((n4 - n2) * scaledResolution.getScaleFactor()));
    }
    
    public static void draw_cube_line(final BlockPos blockPos, final int n, final int n2, final int n3, final int n4, final String s) {
        draw_cube_line(RenderUtil2.tessellator.getBuffer(), (float)blockPos.getX(), (float)blockPos.getY(), (float)blockPos.getZ(), 1.0f, 1.0f, 1.0f, n, n2, n3, n4, s);
    }
    
    private static void GLPost(final boolean b, final boolean b2, final boolean b3, final boolean b4, final boolean b5) {
        GlStateManager.depthMask(true);
        if (!b5) {
            GL11.glDisable(2848);
        }
        if (b4) {
            GL11.glEnable(2929);
        }
        if (b3) {
            GL11.glEnable(3553);
        }
        if (!b2) {
            GL11.glDisable(3042);
        }
        if (b) {
            GL11.glEnable(2896);
        }
    }
    
    public static float getInterpolatedLinWid(final float n, final float n2, final float n3) {
        return n2 * n3 / n;
    }
    
    public static boolean isInViewFrustrum(final AxisAlignedBB axisAlignedBB) {
        final Entity getRenderViewEntity = Minecraft.getMinecraft().getRenderViewEntity();
        RenderUtil2.frustrum.setPosition(getRenderViewEntity.posX, getRenderViewEntity.posY, getRenderViewEntity.posZ);
        return RenderUtil2.frustrum.isBoundingBoxInFrustum(axisAlignedBB);
    }
    
    public static void drawBox(final BlockPos blockPos, final Color color) {
        final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos.getX() - RenderUtil2.mc.getRenderManager().viewerPosX, blockPos.getY() - RenderUtil2.mc.getRenderManager().viewerPosY, blockPos.getZ() - RenderUtil2.mc.getRenderManager().viewerPosZ, blockPos.getX() + 1 - RenderUtil2.mc.getRenderManager().viewerPosX, blockPos.getY() + 1 - RenderUtil2.mc.getRenderManager().viewerPosY, blockPos.getZ() + 1 - RenderUtil2.mc.getRenderManager().viewerPosZ);
        RenderUtil2.camera.setPosition(Objects.requireNonNull(RenderUtil2.mc.getRenderViewEntity()).posX, RenderUtil2.mc.getRenderViewEntity().posY, RenderUtil2.mc.getRenderViewEntity().posZ);
        if (RenderUtil2.camera.isBoundingBoxInFrustum(new AxisAlignedBB(axisAlignedBB.minX + RenderUtil2.mc.getRenderManager().viewerPosX, axisAlignedBB.minY + RenderUtil2.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ + RenderUtil2.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX + RenderUtil2.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY + RenderUtil2.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ + RenderUtil2.mc.getRenderManager().viewerPosZ))) {
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
    
    private static void width(final float n) {
        GlStateManager.glLineWidth(n);
    }
    
    public static void prepare() {
        GlStateManager.pushMatrix();
        GlStateManager.disableDepth();
        GlStateManager.disableLighting();
        GlStateManager.depthMask(false);
        GlStateManager.disableAlpha();
        GlStateManager.disableCull();
        GlStateManager.enableBlend();
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GL11.glShadeModel(7425);
    }
    
    public static void drawSelectionBoundingBox(final AxisAlignedBB axisAlignedBB) {
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(3, DefaultVertexFormats.POSITION);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        getInstance.draw();
        getBuffer.begin(3, DefaultVertexFormats.POSITION);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        getInstance.draw();
        getBuffer.begin(1, DefaultVertexFormats.POSITION);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        getInstance.draw();
    }
    
    public static void renderFour(final Color color) {
        setColor(color);
        GL11.glDepthMask(false);
        GL11.glDisable(2929);
        GL11.glEnable(10754);
        GL11.glPolygonOffset(1.0f, -2000000.0f);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0f, 240.0f);
    }
    
    static {
        frustrum = new Frustum();
        screenCoords = BufferUtils.createFloatBuffer(3);
        viewport = BufferUtils.createIntBuffer(16);
        modelView = BufferUtils.createFloatBuffer(16);
        projection = BufferUtils.createFloatBuffer(16);
        RenderUtil2.itemRender = RenderUtil2.mc.getItemRenderer().itemRenderer;
        RenderUtil2.camera = (ICamera)new Frustum();
        RenderUtil2.depth = GL11.glIsEnabled(2896);
        RenderUtil2.texture = GL11.glIsEnabled(3042);
        RenderUtil2.clean = GL11.glIsEnabled(3553);
        RenderUtil2.bind = GL11.glIsEnabled(2929);
        RenderUtil2.override = GL11.glIsEnabled(2848);
        RenderUtil2.tessellator = Tessellator.getInstance();
        BufferBuilder = RenderUtil2.tessellator.getBuffer();
        RenderUtil2.itemRender = RenderUtil2.mc.getRenderItem();
        RenderUtil2.camera = (ICamera)new Frustum();
        RenderUtil2.depth = GL11.glIsEnabled(2896);
        RenderUtil2.texture = GL11.glIsEnabled(3042);
        RenderUtil2.clean = GL11.glIsEnabled(3553);
        RenderUtil2.bind = GL11.glIsEnabled(2929);
        RenderUtil2.override = GL11.glIsEnabled(2848);
        RenderUtil2.builder = RenderUtil2.tessellator.getBuffer();
    }
    
    public static void draw_cube(final BlockPos blockPos, final int n, final String s) {
        draw_cube(blockPos, n >>> 16 & 0xFF, n >>> 8 & 0xFF, n & 0xFF, n >>> 24 & 0xFF, s);
    }
    
    public static void end() {
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glEnable(2884);
        GlStateManager.popMatrix();
    }
    
    public static void prepareGL() {
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
    
    private static BufferBuilder vertex(final double n, final double n2, final double n3) {
        return RenderUtil2.builder.pos(n - RenderUtil2.mc.getRenderManager().viewerPosX, n2 - RenderUtil2.mc.getRenderManager().viewerPosY, n3 - RenderUtil2.mc.getRenderManager().viewerPosZ);
    }
    
    public static void glrendermethod() {
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glLineWidth(2.0f);
        GL11.glDisable(3553);
        GL11.glEnable(2884);
        GL11.glDisable(2929);
        final double viewerPosX = RenderUtil2.mc.getRenderManager().viewerPosX;
        final double viewerPosY = RenderUtil2.mc.getRenderManager().viewerPosY;
        final double viewerPosZ = RenderUtil2.mc.getRenderManager().viewerPosZ;
        GL11.glPushMatrix();
        GL11.glTranslated(-viewerPosX, -viewerPosY, -viewerPosZ);
    }
    
    public static void drawBoundingBoxBlockPosHalf(final BlockPos blockPos, final float n, final int n2, final int n3, final int n4, final int n5) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(n);
        final Minecraft getMinecraft = Minecraft.getMinecraft();
        final double n6 = blockPos.getX() - getMinecraft.getRenderManager().viewerPosX;
        final double n7 = blockPos.getY() - getMinecraft.getRenderManager().viewerPosY;
        final double n8 = blockPos.getZ() - getMinecraft.getRenderManager().viewerPosZ;
        final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(n6, n7, n8, n6 + 1.0, n7 + 0.5, n8 + 1.0);
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getInstance.draw();
        getBuffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getInstance.draw();
        getBuffer.begin(1, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getInstance.draw();
        GL11.glDisable(2848);
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
    public static void drawBoxBottom(final BlockPos blockPos, final int n, final int n2, final int n3, final int n4) {
        prepare(7);
        drawBoxBottom(RenderUtil2.tessellator.getBuffer(), (float)blockPos.getX(), (float)blockPos.getY(), (float)blockPos.getZ(), 1.0f, 1.0f, n, n2, n3, n4);
        release();
    }
    
    public static AxisAlignedBB updateToCamera(final AxisAlignedBB axisAlignedBB) {
        return new AxisAlignedBB(axisAlignedBB.minX - RenderUtil2.mc.getRenderManager().viewerPosX, axisAlignedBB.minY - RenderUtil2.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ - RenderUtil2.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX - RenderUtil2.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY - RenderUtil2.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ - RenderUtil2.mc.getRenderManager().viewerPosZ);
    }
    
    private static void setupFBO(final Framebuffer framebuffer) {
        EXTFramebufferObject.glDeleteRenderbuffersEXT(framebuffer.depthBuffer);
        final int glGenRenderbuffersEXT = EXTFramebufferObject.glGenRenderbuffersEXT();
        EXTFramebufferObject.glBindRenderbufferEXT(36161, glGenRenderbuffersEXT);
        EXTFramebufferObject.glRenderbufferStorageEXT(36161, 34041, RenderUtil2.mc.displayWidth, RenderUtil2.mc.displayHeight);
        EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36128, 36161, glGenRenderbuffersEXT);
        EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36096, 36161, glGenRenderbuffersEXT);
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
    
    public static void drawBoundingBoxItem(final double n, final double n2, final double n3, final float n4, final int n5, final int n6, final int n7, final int n8) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(n4);
        final Minecraft getMinecraft = Minecraft.getMinecraft();
        final double n9 = n - 0.2 - getMinecraft.getRenderManager().viewerPosX;
        final double n10 = n2 - getMinecraft.getRenderManager().viewerPosY;
        final double n11 = n3 - 0.2 - getMinecraft.getRenderManager().viewerPosZ;
        final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(n9, n10, n11, n9 + 0.4, n10 + 0.4, n11 + 0.4);
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n5, n6, n7, n8).endVertex();
        getInstance.draw();
        getBuffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n5, n6, n7, n8).endVertex();
        getInstance.draw();
        getBuffer.begin(1, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n5, n6, n7, n8).endVertex();
        getInstance.draw();
        GL11.glDisable(2848);
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
    public static void GlPost() {
        GLPost(RenderUtil2.depth, RenderUtil2.texture, RenderUtil2.clean, RenderUtil2.bind, RenderUtil2.override);
    }
    
    public static void drawBBOutline(final AxisAlignedBB axisAlignedBB, final float n, final Color color) {
        start(n);
        final int red = color.getRed();
        final int blue = color.getBlue();
        final int green = color.getGreen();
        final int alpha = color.getAlpha();
        RenderUtil2.builder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        vertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, red, green, blue, alpha);
        vertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ, red, green, blue, alpha);
        vertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ, red, green, blue, alpha);
        vertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ, red, green, blue, alpha);
        vertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, red, green, blue, alpha);
        vertex(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ, red, green, blue, alpha);
        vertex(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ, red, green, blue, alpha);
        vertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ, red, green, blue, alpha);
        vertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ, red, green, blue, alpha);
        vertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ, red, green, blue, alpha);
        vertex(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ, red, green, blue, alpha);
        vertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ, red, green, blue, alpha);
        vertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ, red, green, blue, alpha);
        vertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ, red, green, blue, alpha);
        vertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ, red, green, blue, alpha);
        vertex(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ, red, green, blue, alpha);
        RenderUtil2.tessellator.draw();
        end();
    }
    
    public static void drawBoxSmall(final double n, final double n2, final double n3, final int n4, final int n5) {
        drawBox(RenderUtil2.tessellator.getBuffer(), n, n2, n3, 0.25f, 0.25f, 0.25f, n4 >>> 16 & 0xFF, n4 >>> 8 & 0xFF, n4 & 0xFF, n4 >>> 24 & 0xFF, n5);
    }
    
    public static void buildBBBuffer(final BufferBuilder bufferBuilder, final AxisAlignedBB axisAlignedBB, final Color color, final Color color2) {
        addBuilderVertex(bufferBuilder, axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, color);
        addBuilderVertex(bufferBuilder, axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ, color);
        addBuilderVertex(bufferBuilder, axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ, color);
        addBuilderVertex(bufferBuilder, axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ, color);
        addBuilderVertex(bufferBuilder, axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, color);
        addBuilderVertex(bufferBuilder, axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ, color2);
        addBuilderVertex(bufferBuilder, axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ, color2);
        addBuilderVertex(bufferBuilder, axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ, color);
        addBuilderVertex(bufferBuilder, axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ, color);
        addBuilderVertex(bufferBuilder, axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ, color2);
        addBuilderVertex(bufferBuilder, axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ, color2);
        addBuilderVertex(bufferBuilder, axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ, color);
        addBuilderVertex(bufferBuilder, axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ, color);
        addBuilderVertex(bufferBuilder, axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ, color2);
        addBuilderVertex(bufferBuilder, axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ, color2);
        addBuilderVertex(bufferBuilder, axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ, color);
        addBuilderVertex(bufferBuilder, axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ, color);
        addBuilderVertex(bufferBuilder, axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ, color2);
        addBuilderVertex(bufferBuilder, axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ, color2);
        addBuilderVertex(bufferBuilder, axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, color);
        addBuilderVertex(bufferBuilder, axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ, color2);
        addBuilderVertex(bufferBuilder, axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ, color2);
        addBuilderVertex(bufferBuilder, axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ, color2);
        addBuilderVertex(bufferBuilder, axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ, color2);
        addBuilderVertex(bufferBuilder, axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ, color2);
    }
    
    public static void drawGradientPlane(final BlockPos blockPos, final EnumFacing enumFacing, final Color color, final Color color2, final double n) {
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        final IBlockState getBlockState = RenderUtil2.mc.world.getBlockState(blockPos);
        final Vec3d interpolateEntity = EntityUtil.interpolateEntity((Entity)RenderUtil2.mc.player, RenderUtil2.mc.getRenderPartialTicks());
        final AxisAlignedBB expand = getBlockState.getSelectedBoundingBox((World)RenderUtil2.mc.world, blockPos).grow(0.0020000000949949026).offset(-interpolateEntity.x, -interpolateEntity.y, -interpolateEntity.z).expand(0.0, n, 0.0);
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
    
    public static void drawBoundingBoxChestBlockPos(final BlockPos blockPos, final float n, final int n2, final int n3, final int n4, final int n5) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(n);
        final Minecraft getMinecraft = Minecraft.getMinecraft();
        final double n6 = blockPos.getX() + 0.06 - getMinecraft.getRenderManager().viewerPosX;
        final double n7 = blockPos.getY() - getMinecraft.getRenderManager().viewerPosY;
        final double n8 = blockPos.getZ() + 0.06 - getMinecraft.getRenderManager().viewerPosZ;
        final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(n6, n7, n8, n6 + 0.881, n7 + 0.875, n8 + 0.881);
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getInstance.draw();
        getBuffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getInstance.draw();
        getBuffer.begin(1, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getInstance.draw();
        GL11.glDisable(2848);
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
    public static void release_gl() {
        GlStateManager.enableCull();
        GlStateManager.depthMask(true);
        GlStateManager.enableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.enableDepth();
    }
    
    public static void drawBlockOutline(final BlockPos blockPos, final Color color, final float n, final boolean b) {
        final IBlockState getBlockState = RenderUtil2.mc.world.getBlockState(blockPos);
        if ((b || getBlockState.getMaterial() != Material.AIR) && RenderUtil2.mc.world.getWorldBorder().contains(blockPos)) {
            assert RenderUtil2.mc.renderViewEntity != null;
            final Vec3d interpolateEntity = EntityUtil.interpolateEntity(RenderUtil2.mc.renderViewEntity, RenderUtil2.mc.getRenderPartialTicks());
            drawBlockOutline(getBlockState.getSelectedBoundingBox((World)RenderUtil2.mc.world, blockPos).grow(0.0020000000949949026).offset(-interpolateEntity.x, -interpolateEntity.y, -interpolateEntity.z), color, n);
        }
    }
    
    public static void end1() {
        GL11.glDisable(2848);
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public static void start1() {
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
    }
    
    public static void drawBox(final BlockPos blockPos, final int n, final int n2, final int n3, final int n4, final int n5) {
        drawBox(RenderUtil2.tessellator.getBuffer(), blockPos.getX(), blockPos.getY(), blockPos.getZ(), 1.0f, 1.0f, 1.0f, n, n2, n3, n4, n5);
    }
    
    public static void drawColorBox(final AxisAlignedBB axisAlignedBB, final float n, final float n2, final float n3, final float n4) {
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getInstance.draw();
        getBuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getInstance.draw();
        getBuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getInstance.draw();
        getBuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getInstance.draw();
        getBuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getInstance.draw();
        getBuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getInstance.draw();
    }
    
    public static void drawSphere(final double n, final double n2, final double n3, final float n4, final int n5, final int n6) {
        final Sphere sphere = new Sphere();
        GL11.glPushMatrix();
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(1.2f);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        sphere.setDrawStyle(100013);
        GL11.glTranslated(n - RenderUtil2.mc.renderManager.renderPosX, n2 - RenderUtil2.mc.renderManager.renderPosY, n3 - RenderUtil2.mc.renderManager.renderPosZ);
        sphere.draw(n4, n5, n6);
        GL11.glLineWidth(2.0f);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
    
    public static void start2() {
        GlStateManager.pushMatrix();
        width(1.0f);
        GL11.glEnable(2848);
        GL11.glEnable(34383);
        GL11.glHint(3154, 4354);
        GlStateManager.disableAlpha();
        GlStateManager.shadeModel(7425);
        GlStateManager.disableCull();
        GlStateManager.enableBlend();
        GlStateManager.depthMask(false);
        GlStateManager.disableTexture2D();
        GlStateManager.disableLighting();
    }
    
    public static void drawBoundingBoxFace(final AxisAlignedBB axisAlignedBB, final float n, final int n2, final int n3, final int n4, final int n5) {
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
        getInstance.draw();
        GL11.glDisable(2848);
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
    public static void drawBoxESP(final BlockPos blockPos, final Color color, final boolean b, final Color color2, final float n, final boolean b2, final boolean b3, final int a, final boolean b4, final double n2, final boolean b5, final boolean b6, final boolean b7, final boolean b8, final int n3) {
        if (b3) {
            drawBox(blockPos, new Color(color.getRed(), color.getGreen(), color.getBlue(), a), n2, b5, b7, n3);
        }
        if (b2) {
            drawBlockOutline(blockPos, b ? color2 : color, n, b4, n2, b6, b8, n3);
        }
    }
    
    public static void drawBoundingBoxBottomBlockPos(final BlockPos blockPos, final float n, final int n2, final int n3, final int n4, final int n5) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(n);
        final Minecraft getMinecraft = Minecraft.getMinecraft();
        final double n6 = blockPos.getX() - getMinecraft.getRenderManager().viewerPosX;
        final double n7 = blockPos.getY() - getMinecraft.getRenderManager().viewerPosY;
        final double n8 = blockPos.getZ() - getMinecraft.getRenderManager().viewerPosZ;
        final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(n6, n7, n8, n6 + 1.0, n7 + 1.0, n8 + 1.0);
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getInstance.draw();
        GL11.glDisable(2848);
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
    public static void drawBox(final BufferBuilder bufferBuilder, final double n, final double n2, final double n3, final float n4, final float n5, final float n6, final int n7, final int n8, final int n9, final int n10, final int n11) {
        if ((n11 & 0x1) != 0x0) {
            bufferBuilder.pos(n + n4, n2, n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos(n + n4, n2, n3 + n6).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos(n, n2, n3 + n6).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos(n, n2, n3).color(n7, n8, n9, n10).endVertex();
        }
        if ((n11 & 0x2) != 0x0) {
            bufferBuilder.pos(n + n4, n2 + n5, n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos(n, n2 + n5, n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos(n, n2 + n5, n3 + n6).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos(n + n4, n2 + n5, n3 + n6).color(n7, n8, n9, n10).endVertex();
        }
        if ((n11 & 0x4) != 0x0) {
            bufferBuilder.pos(n + n4, n2, n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos(n, n2, n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos(n, n2 + n5, n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos(n + n4, n2 + n5, n3).color(n7, n8, n9, n10).endVertex();
        }
        if ((n11 & 0x8) != 0x0) {
            bufferBuilder.pos(n, n2, n3 + n6).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos(n + n4, n2, n3 + n6).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos(n + n4, n2 + n5, n3 + n6).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos(n, n2 + n5, n3 + n6).color(n7, n8, n9, n10).endVertex();
        }
        if ((n11 & 0x10) != 0x0) {
            bufferBuilder.pos(n, n2, n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos(n, n2, n3 + n6).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos(n, n2 + n5, n3 + n6).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos(n, n2 + n5, n3).color(n7, n8, n9, n10).endVertex();
        }
        if ((n11 & 0x20) != 0x0) {
            bufferBuilder.pos(n + n4, n2, n3 + n6).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos(n + n4, n2, n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos(n + n4, n2 + n5, n3).color(n7, n8, n9, n10).endVertex();
            bufferBuilder.pos(n + n4, n2 + n5, n3 + n6).color(n7, n8, n9, n10).endVertex();
        }
    }
    
    public static void renderFive() {
        GL11.glPolygonOffset(1.0f, 2000000.0f);
        GL11.glDisable(10754);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(2960);
        GL11.glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glEnable(3042);
        GL11.glEnable(2896);
        GL11.glEnable(3553);
        GL11.glEnable(3008);
        GL11.glPopAttrib();
    }
    
    public static void drawBoundingBoxBottomBlockPosXInMiddle2(final BlockPos blockPos, final float n, final int n2, final int n3, final int n4, final int n5) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(n);
        final Minecraft getMinecraft = Minecraft.getMinecraft();
        final double n6 = blockPos.getX() - getMinecraft.getRenderManager().viewerPosX;
        final double n7 = blockPos.getY() - getMinecraft.getRenderManager().viewerPosY;
        final double n8 = blockPos.getZ() - getMinecraft.getRenderManager().viewerPosZ;
        final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(n6, n7, n8, n6 + 1.0, n7 + 1.0, n8 + 1.0);
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getInstance.draw();
        GL11.glDisable(2848);
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
    public static void drawCircle(final float n, final float n2, final float n3) {
        drawCircle(n, n2, n3, 0, 360, 64);
    }
    
    public static void drawCircle(final float n, final float n2, final float n3, final int n4) {
        final float n5 = (n4 >> 24 & 0xFF) / 255.0f;
        final float n6 = (n4 >> 16 & 0xFF) / 255.0f;
        final float n7 = (n4 >> 8 & 0xFF) / 255.0f;
        final float n8 = (n4 & 0xFF) / 255.0f;
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GL11.glColor4f(n6, n7, n8, n5);
        GL11.glBegin(9);
        for (int i = 0; i <= 360; ++i) {
            GL11.glVertex2d(n + Math.sin(i * 3.141526 / 180.0) * n3, n2 + Math.cos(i * 3.141526 / 180.0) * n3);
        }
        GL11.glEnd();
        GlStateManager.resetColor();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
    public static void drawGradientPlane(final BlockPos blockPos, final EnumFacing enumFacing, final Color color, final Color color2, final boolean b, final boolean b2) {
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        final IBlockState getBlockState = RenderUtil2.mc.world.getBlockState(blockPos);
        final Vec3d interpolateEntity = EntityUtil.interpolateEntity((Entity)RenderUtil2.mc.player, RenderUtil2.mc.getRenderPartialTicks());
        final AxisAlignedBB offset = getBlockState.getSelectedBoundingBox((World)RenderUtil2.mc.world, blockPos).grow(0.0020000000949949026).offset(-interpolateEntity.x, -interpolateEntity.y, -interpolateEntity.z);
        final float n = color.getRed() / 255.0f;
        final float n2 = color.getGreen() / 255.0f;
        final float n3 = color.getBlue() / 255.0f;
        final float n4 = color.getAlpha() / 255.0f;
        final float n5 = color2.getRed() / 255.0f;
        final float n6 = color2.getGreen() / 255.0f;
        final float n7 = color2.getBlue() / 255.0f;
        final float n8 = color2.getAlpha() / 255.0f;
        double n9 = 0.0;
        double n10 = 0.0;
        double n11 = 0.0;
        double n12 = 0.0;
        double n13 = 0.0;
        double n14 = 0.0;
        if (enumFacing == EnumFacing.DOWN) {
            n9 = offset.minX;
            n12 = offset.maxX;
            n10 = offset.minY + (b2 ? 0.5 : 0.0);
            n13 = offset.minY + (b2 ? 0.5 : 0.0);
            n11 = offset.minZ;
            n14 = offset.maxZ;
        }
        else if (enumFacing == EnumFacing.UP) {
            n9 = offset.minX;
            n12 = offset.maxX;
            n10 = offset.maxY / (b ? 2 : 1);
            n13 = offset.maxY / (b ? 2 : 1);
            n11 = offset.minZ;
            n14 = offset.maxZ;
        }
        else if (enumFacing == EnumFacing.EAST) {
            n9 = offset.maxX;
            n12 = offset.maxX;
            n10 = offset.minY + (b2 ? 0.5 : 0.0);
            n13 = offset.maxY / (b ? 2 : 1);
            n11 = offset.minZ;
            n14 = offset.maxZ;
        }
        else if (enumFacing == EnumFacing.WEST) {
            n9 = offset.minX;
            n12 = offset.minX;
            n10 = offset.minY + (b2 ? 0.5 : 0.0);
            n13 = offset.maxY / (b ? 2 : 1);
            n11 = offset.minZ;
            n14 = offset.maxZ;
        }
        else if (enumFacing == EnumFacing.SOUTH) {
            n9 = offset.minX;
            n12 = offset.maxX;
            n10 = offset.minY + (b2 ? 0.5 : 0.0);
            n13 = offset.maxY / (b ? 2 : 1);
            n11 = offset.maxZ;
            n14 = offset.maxZ;
        }
        else if (enumFacing == EnumFacing.NORTH) {
            n9 = offset.minX;
            n12 = offset.maxX;
            n10 = offset.minY + (b2 ? 0.5 : 0.0);
            n13 = offset.maxY / (b ? 2 : 1);
            n11 = offset.minZ;
            n14 = offset.minZ;
        }
        GlStateManager.pushMatrix();
        GlStateManager.disableDepth();
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.depthMask(false);
        getBuffer.begin(5, DefaultVertexFormats.POSITION_COLOR);
        if (enumFacing == EnumFacing.EAST || enumFacing == EnumFacing.WEST || enumFacing == EnumFacing.NORTH || enumFacing == EnumFacing.SOUTH) {
            getBuffer.pos(n9, n10, n11).color(n, n2, n3, n4).endVertex();
            getBuffer.pos(n9, n10, n11).color(n, n2, n3, n4).endVertex();
            getBuffer.pos(n9, n10, n11).color(n, n2, n3, n4).endVertex();
            getBuffer.pos(n9, n10, n14).color(n, n2, n3, n4).endVertex();
            getBuffer.pos(n9, n13, n11).color(n5, n6, n7, n8).endVertex();
            getBuffer.pos(n9, n13, n14).color(n5, n6, n7, n8).endVertex();
            getBuffer.pos(n9, n13, n14).color(n5, n6, n7, n8).endVertex();
            getBuffer.pos(n9, n10, n14).color(n, n2, n3, n4).endVertex();
            getBuffer.pos(n12, n13, n14).color(n5, n6, n7, n8).endVertex();
            getBuffer.pos(n12, n10, n14).color(n, n2, n3, n4).endVertex();
            getBuffer.pos(n12, n10, n14).color(n, n2, n3, n4).endVertex();
            getBuffer.pos(n12, n10, n11).color(n, n2, n3, n4).endVertex();
            getBuffer.pos(n12, n13, n14).color(n5, n6, n7, n8).endVertex();
            getBuffer.pos(n12, n13, n11).color(n5, n6, n7, n8).endVertex();
            getBuffer.pos(n12, n13, n11).color(n5, n6, n7, n8).endVertex();
            getBuffer.pos(n12, n10, n11).color(n, n2, n3, n4).endVertex();
            getBuffer.pos(n9, n13, n11).color(n5, n6, n7, n8).endVertex();
            getBuffer.pos(n9, n10, n11).color(n, n2, n3, n4).endVertex();
            getBuffer.pos(n9, n10, n11).color(n, n2, n3, n4).endVertex();
            getBuffer.pos(n12, n10, n11).color(n, n2, n3, n4).endVertex();
            getBuffer.pos(n9, n10, n14).color(n, n2, n3, n4).endVertex();
            getBuffer.pos(n12, n10, n14).color(n, n2, n3, n4).endVertex();
            getBuffer.pos(n12, n10, n14).color(n, n2, n3, n4).endVertex();
            getBuffer.pos(n9, n13, n11).color(n5, n6, n7, n8).endVertex();
            getBuffer.pos(n9, n13, n11).color(n5, n6, n7, n8).endVertex();
            getBuffer.pos(n9, n13, n14).color(n5, n6, n7, n8).endVertex();
            getBuffer.pos(n12, n13, n11).color(n5, n6, n7, n8).endVertex();
            getBuffer.pos(n12, n13, n14).color(n5, n6, n7, n8).endVertex();
            getBuffer.pos(n12, n13, n14).color(n5, n6, n7, n8).endVertex();
            getBuffer.pos(n12, n13, n14).color(n5, n6, n7, n8).endVertex();
        }
        else if (enumFacing == EnumFacing.UP) {
            getBuffer.pos(n9, n10, n11).color(n5, n6, n7, n8).endVertex();
            getBuffer.pos(n9, n10, n11).color(n5, n6, n7, n8).endVertex();
            getBuffer.pos(n9, n10, n11).color(n5, n6, n7, n8).endVertex();
            getBuffer.pos(n9, n10, n14).color(n5, n6, n7, n8).endVertex();
            getBuffer.pos(n9, n13, n11).color(n5, n6, n7, n8).endVertex();
            getBuffer.pos(n9, n13, n14).color(n5, n6, n7, n8).endVertex();
            getBuffer.pos(n9, n13, n14).color(n5, n6, n7, n8).endVertex();
            getBuffer.pos(n9, n10, n14).color(n5, n6, n7, n8).endVertex();
            getBuffer.pos(n12, n13, n14).color(n5, n6, n7, n8).endVertex();
            getBuffer.pos(n12, n10, n14).color(n5, n6, n7, n8).endVertex();
            getBuffer.pos(n12, n10, n14).color(n5, n6, n7, n8).endVertex();
            getBuffer.pos(n12, n10, n11).color(n5, n6, n7, n8).endVertex();
            getBuffer.pos(n12, n13, n14).color(n5, n6, n7, n8).endVertex();
            getBuffer.pos(n12, n13, n11).color(n5, n6, n7, n8).endVertex();
            getBuffer.pos(n12, n13, n11).color(n5, n6, n7, n8).endVertex();
            getBuffer.pos(n12, n10, n11).color(n5, n6, n7, n8).endVertex();
            getBuffer.pos(n9, n13, n11).color(n5, n6, n7, n8).endVertex();
            getBuffer.pos(n9, n10, n11).color(n5, n6, n7, n8).endVertex();
            getBuffer.pos(n9, n10, n11).color(n5, n6, n7, n8).endVertex();
            getBuffer.pos(n12, n10, n11).color(n5, n6, n7, n8).endVertex();
            getBuffer.pos(n9, n10, n14).color(n5, n6, n7, n8).endVertex();
            getBuffer.pos(n12, n10, n14).color(n5, n6, n7, n8).endVertex();
            getBuffer.pos(n12, n10, n14).color(n5, n6, n7, n8).endVertex();
            getBuffer.pos(n9, n13, n11).color(n5, n6, n7, n8).endVertex();
            getBuffer.pos(n9, n13, n11).color(n5, n6, n7, n8).endVertex();
            getBuffer.pos(n9, n13, n14).color(n5, n6, n7, n8).endVertex();
            getBuffer.pos(n12, n13, n11).color(n5, n6, n7, n8).endVertex();
            getBuffer.pos(n12, n13, n14).color(n5, n6, n7, n8).endVertex();
            getBuffer.pos(n12, n13, n14).color(n5, n6, n7, n8).endVertex();
            getBuffer.pos(n12, n13, n14).color(n5, n6, n7, n8).endVertex();
        }
        else if (enumFacing == EnumFacing.DOWN) {
            getBuffer.pos(n9, n10, n11).color(n, n2, n3, n4).endVertex();
            getBuffer.pos(n9, n10, n11).color(n, n2, n3, n4).endVertex();
            getBuffer.pos(n9, n10, n11).color(n, n2, n3, n4).endVertex();
            getBuffer.pos(n9, n10, n14).color(n, n2, n3, n4).endVertex();
            getBuffer.pos(n9, n13, n11).color(n, n2, n3, n4).endVertex();
            getBuffer.pos(n9, n13, n14).color(n, n2, n3, n4).endVertex();
            getBuffer.pos(n9, n13, n14).color(n, n2, n3, n4).endVertex();
            getBuffer.pos(n9, n10, n14).color(n, n2, n3, n4).endVertex();
            getBuffer.pos(n12, n13, n14).color(n, n2, n3, n4).endVertex();
            getBuffer.pos(n12, n10, n14).color(n, n2, n3, n4).endVertex();
            getBuffer.pos(n12, n10, n14).color(n, n2, n3, n4).endVertex();
            getBuffer.pos(n12, n10, n11).color(n, n2, n3, n4).endVertex();
            getBuffer.pos(n12, n13, n14).color(n, n2, n3, n4).endVertex();
            getBuffer.pos(n12, n13, n11).color(n, n2, n3, n4).endVertex();
            getBuffer.pos(n12, n13, n11).color(n, n2, n3, n4).endVertex();
            getBuffer.pos(n12, n10, n11).color(n, n2, n3, n4).endVertex();
            getBuffer.pos(n9, n13, n11).color(n, n2, n3, n4).endVertex();
            getBuffer.pos(n9, n10, n11).color(n, n2, n3, n4).endVertex();
            getBuffer.pos(n9, n10, n11).color(n, n2, n3, n4).endVertex();
            getBuffer.pos(n12, n10, n11).color(n, n2, n3, n4).endVertex();
            getBuffer.pos(n9, n10, n14).color(n, n2, n3, n4).endVertex();
            getBuffer.pos(n12, n10, n14).color(n, n2, n3, n4).endVertex();
            getBuffer.pos(n12, n10, n14).color(n, n2, n3, n4).endVertex();
            getBuffer.pos(n9, n13, n11).color(n, n2, n3, n4).endVertex();
            getBuffer.pos(n9, n13, n11).color(n, n2, n3, n4).endVertex();
            getBuffer.pos(n9, n13, n14).color(n, n2, n3, n4).endVertex();
            getBuffer.pos(n12, n13, n11).color(n, n2, n3, n4).endVertex();
            getBuffer.pos(n12, n13, n14).color(n, n2, n3, n4).endVertex();
            getBuffer.pos(n12, n13, n14).color(n, n2, n3, n4).endVertex();
            getBuffer.pos(n12, n13, n14).color(n, n2, n3, n4).endVertex();
        }
        getInstance.draw();
        GlStateManager.depthMask(true);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.enableDepth();
        GlStateManager.popMatrix();
    }
    
    public static void begin(final int n) {
        RenderUtil2.tessellator.getBuffer().begin(n, DefaultVertexFormats.POSITION_COLOR);
    }
    
    public static float[][] getBipedRotations(final ModelBiped modelBiped) {
        return new float[][] { { modelBiped.bipedHead.rotateAngleX, modelBiped.bipedHead.rotateAngleY, modelBiped.bipedHead.rotateAngleZ }, { modelBiped.bipedRightArm.rotateAngleX, modelBiped.bipedRightArm.rotateAngleY, modelBiped.bipedRightArm.rotateAngleZ }, { modelBiped.bipedLeftArm.rotateAngleX, modelBiped.bipedLeftArm.rotateAngleY, modelBiped.bipedLeftArm.rotateAngleZ }, { modelBiped.bipedRightLeg.rotateAngleX, modelBiped.bipedRightLeg.rotateAngleY, modelBiped.bipedRightLeg.rotateAngleZ }, { modelBiped.bipedLeftLeg.rotateAngleX, modelBiped.bipedLeftLeg.rotateAngleY, modelBiped.bipedLeftLeg.rotateAngleZ } };
    }
    
    public static void drawBBFill(final AxisAlignedBB axisAlignedBB, final Color color) {
        GlStateManager.pushMatrix();
        start1();
        width(1.0f);
        final int red = color.getRed();
        final int blue = color.getBlue();
        final int green = color.getGreen();
        final int alpha = color.getAlpha();
        RenderUtil2.builder.begin(5, DefaultVertexFormats.POSITION_COLOR);
        final double minX = axisAlignedBB.minX;
        final double minY = axisAlignedBB.minY;
        final double minZ = axisAlignedBB.minZ;
        final double maxX = axisAlignedBB.maxX;
        final double maxY = axisAlignedBB.maxY;
        final double maxZ = axisAlignedBB.maxZ;
        vertex(minX, minY, minZ).color(red, green, blue, alpha).endVertex();
        vertex(minX, minY, minZ).color(red, green, blue, alpha).endVertex();
        vertex(minX, minY, minZ).color(red, green, blue, alpha).endVertex();
        vertex(minX, minY, maxZ).color(red, green, blue, alpha).endVertex();
        vertex(minX, maxY, minZ).color(red, green, blue, alpha).endVertex();
        vertex(minX, maxY, maxZ).color(red, green, blue, alpha).endVertex();
        vertex(minX, maxY, maxZ).color(red, green, blue, alpha).endVertex();
        vertex(minX, minY, maxZ).color(red, green, blue, alpha).endVertex();
        vertex(maxX, maxY, maxZ).color(red, green, blue, alpha).endVertex();
        vertex(maxX, minY, maxZ).color(red, green, blue, alpha).endVertex();
        vertex(maxX, minY, maxZ).color(red, green, blue, alpha).endVertex();
        vertex(maxX, minY, minZ).color(red, green, blue, alpha).endVertex();
        vertex(maxX, maxY, maxZ).color(red, green, blue, alpha).endVertex();
        vertex(maxX, maxY, minZ).color(red, green, blue, alpha).endVertex();
        vertex(maxX, maxY, minZ).color(red, green, blue, alpha).endVertex();
        vertex(maxX, minY, minZ).color(red, green, blue, alpha).endVertex();
        vertex(minX, maxY, minZ).color(red, green, blue, alpha).endVertex();
        vertex(minX, minY, minZ).color(red, green, blue, alpha).endVertex();
        vertex(minX, minY, minZ).color(red, green, blue, alpha).endVertex();
        vertex(maxX, minY, minZ).color(red, green, blue, alpha).endVertex();
        vertex(minX, minY, maxZ).color(red, green, blue, alpha).endVertex();
        vertex(maxX, minY, maxZ).color(red, green, blue, alpha).endVertex();
        vertex(maxX, minY, maxZ).color(red, green, blue, alpha).endVertex();
        vertex(minX, maxY, minZ).color(red, green, blue, alpha).endVertex();
        vertex(minX, maxY, minZ).color(red, green, blue, alpha).endVertex();
        vertex(minX, maxY, maxZ).color(red, green, blue, alpha).endVertex();
        vertex(maxX, maxY, minZ).color(red, green, blue, alpha).endVertex();
        vertex(maxX, maxY, maxZ).color(red, green, blue, alpha).endVertex();
        vertex(maxX, maxY, maxZ).color(red, green, blue, alpha).endVertex();
        vertex(maxX, maxY, maxZ).color(red, green, blue, alpha).endVertex();
        RenderUtil2.tessellator.draw();
        end1();
        GlStateManager.popMatrix();
    }
    
    public static void GLPre(final float n) {
        RenderUtil2.depth = GL11.glIsEnabled(2896);
        RenderUtil2.texture = GL11.glIsEnabled(3042);
        RenderUtil2.clean = GL11.glIsEnabled(3553);
        RenderUtil2.bind = GL11.glIsEnabled(2929);
        RenderUtil2.override = GL11.glIsEnabled(2848);
        GLPre(RenderUtil2.depth, RenderUtil2.texture, RenderUtil2.clean, RenderUtil2.bind, RenderUtil2.override, n);
    }
    
    public static void drawBoundingBoxBottomBlockPosXInMiddle(final BlockPos blockPos, final float n, final int n2, final int n3, final int n4, final int n5) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(n);
        final Minecraft getMinecraft = Minecraft.getMinecraft();
        final double n6 = blockPos.getX() - getMinecraft.getRenderManager().viewerPosX;
        final double n7 = blockPos.getY() - getMinecraft.getRenderManager().viewerPosY;
        final double n8 = blockPos.getZ() - getMinecraft.getRenderManager().viewerPosZ;
        final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(n6, n7, n8, n6 + 1.0, n7 + 1.0, n8 + 1.0);
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getInstance.draw();
        GL11.glDisable(2848);
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
    public static void drawBox(final double n, final double n2, final double n3, final int n4, final int n5) {
        drawBox(RenderUtil2.tessellator.getBuffer(), n, n2, n3, 1.0f, 1.0f, 1.0f, n4 >>> 16 & 0xFF, n4 >>> 8 & 0xFF, n4 & 0xFF, n4 >>> 24 & 0xFF, n5);
    }
    
    public static void drawCircleOutline(final float n, final float n2, final float n3) {
        drawCircleOutline(n, n2, n3, 0, 360, 40);
    }
    
    public static void drawRectangleCorrectly(final int n, final int n2, final int n3, final int n4, final int n5) {
        GL11.glLineWidth(1.0f);
        Gui.drawRect(n, n2, n + n3, n2 + n4, n5);
    }
    
    public static void draw_cube_line(final float n, final float n2, final float n3, final int n4, final String s) {
        draw_cube_line(RenderUtil2.tessellator.getBuffer(), n, n2, n3, 1.0f, 0.5645f, 1.0f, n4 >>> 16 & 0xFF, n4 >>> 8 & 0xFF, n4 & 0xFF, n4 >>> 24 & 0xFF, s);
    }
    
    public static void buildBBBufferFog(final BufferBuilder bufferBuilder, final AxisAlignedBB axisAlignedBB, final Color color, final Color color2) {
        final double n = (axisAlignedBB.maxX - axisAlignedBB.minX) / 2.0;
        final double n2 = (axisAlignedBB.maxY - axisAlignedBB.minY) / 2.0;
        final double n3 = (axisAlignedBB.maxZ - axisAlignedBB.minZ) / 2.0;
        addBuilderVertex(bufferBuilder, axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, color);
        addBuilderVertex(bufferBuilder, axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ, color);
        addBuilderVertex(bufferBuilder, axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ, color);
        addBuilderVertex(bufferBuilder, axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, color);
        addBuilderVertex(bufferBuilder, axisAlignedBB.minX + n, axisAlignedBB.maxY, axisAlignedBB.minZ + n3, color2);
        addBuilderVertex(bufferBuilder, axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ, color);
        addBuilderVertex(bufferBuilder, axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ, color);
        addBuilderVertex(bufferBuilder, axisAlignedBB.minX + n, axisAlignedBB.maxY, axisAlignedBB.minZ + n3, color2);
        addBuilderVertex(bufferBuilder, axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ, color);
        addBuilderVertex(bufferBuilder, axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ, color);
        addBuilderVertex(bufferBuilder, axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ, color);
        addBuilderVertex(bufferBuilder, axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, color);
        addBuilderVertex(bufferBuilder, axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ, color);
        addBuilderVertex(bufferBuilder, axisAlignedBB.minX + n, axisAlignedBB.maxY, axisAlignedBB.minZ + n3, color2);
        addBuilderVertex(bufferBuilder, axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ, color);
        addBuilderVertex(bufferBuilder, axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ, color);
        addBuilderVertex(bufferBuilder, axisAlignedBB.minX + n, axisAlignedBB.maxY, axisAlignedBB.minZ + n3, color2);
        addBuilderVertex(bufferBuilder, axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, color);
    }
    
    public static void drawTracerPointer(final float n, final float n2, final float n3, final float n4, final float n5, final boolean b, final float n6, final int n7) {
        final boolean glIsEnabled = GL11.glIsEnabled(3042);
        final float n8 = (n7 >> 24 & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glPushMatrix();
        hexColor(n7);
        GL11.glBegin(7);
        GL11.glVertex2d((double)n, (double)n2);
        GL11.glVertex2d((double)(n - n3 / n4), (double)(n2 + n3));
        GL11.glVertex2d((double)n, (double)(n2 + n3 / n5));
        GL11.glVertex2d((double)(n + n3 / n4), (double)(n2 + n3));
        GL11.glVertex2d((double)n, (double)n2);
        GL11.glEnd();
        if (b) {
            GL11.glLineWidth(n6);
            GL11.glColor4f(0.0f, 0.0f, 0.0f, n8);
            GL11.glBegin(2);
            GL11.glVertex2d((double)n, (double)n2);
            GL11.glVertex2d((double)(n - n3 / n4), (double)(n2 + n3));
            GL11.glVertex2d((double)n, (double)(n2 + n3 / n5));
            GL11.glVertex2d((double)(n + n3 / n4), (double)(n2 + n3));
            GL11.glVertex2d((double)n, (double)n2);
            GL11.glEnd();
        }
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        if (!glIsEnabled) {
            GL11.glDisable(3042);
        }
        GL11.glDisable(2848);
    }
    
    public static void addBuilderVertex(final BufferBuilder bufferBuilder, final double n, final double n2, final double n3, final Color color) {
        bufferBuilder.pos(n, n2, n3).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f).endVertex();
    }
    
    public static void drawTextShadow(final String s, final int n, final int n2, final int n3, final double n4) {
        RenderUtil2.mc.fontRenderer.drawStringWithShadow(s, (float)n, (float)n2, n3);
    }
    
    public static double getRenderPosZ() {
        return ((IRenderManager)RenderUtil2.mc.getRenderManager()).getRenderPosZ();
    }
    
    public static AxisAlignedBB getBoundingBox(final BlockPos blockPos) {
        return RenderUtil2.mc.world.getBlockState(blockPos).getBoundingBox((IBlockAccess)RenderUtil2.mc.world, blockPos).offset(blockPos);
    }
    
    public static class RenderTesselator extends Tessellator
    {
        public static /* synthetic */ RenderTesselator INSTANCE;
        
        public static void drawBoundingBox(final AxisAlignedBB axisAlignedBB, final float n, final float n2, final float n3, final float n4, final float n5) {
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
        
        static {
            RenderTesselator.INSTANCE = new RenderTesselator();
        }
        
        public static void prepare(final int n) {
            prepareGL();
            begin(n);
        }
        
        public static void release() {
            render();
            releaseGL();
        }
        
        public static void drawBox(final float n, final float n2, final float n3, final int n4, final int n5) {
            drawBox(RenderTesselator.INSTANCE.getBuffer(), n, n2, n3, 1.0f, 1.0f, 1.0f, n4 >>> 16 & 0xFF, n4 >>> 8 & 0xFF, n4 & 0xFF, n4 >>> 24 & 0xFF, n5);
        }
        
        public static void render() {
            RenderTesselator.INSTANCE.draw();
        }
        
        public static void drawBox(final BlockPos blockPos, final int n, final int n2) {
            drawBox(blockPos, n >>> 16 & 0xFF, n >>> 8 & 0xFF, n & 0xFF, n >>> 24 & 0xFF, n2);
        }
        
        public static void drawHalfBox(final BlockPos blockPos, final int n, final int n2) {
            drawHalfBox(blockPos, n >>> 16 & 0xFF, n >>> 8 & 0xFF, n & 0xFF, n >>> 24 & 0xFF, n2);
        }
        
        public static void prepareGL() {
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
        
        public static void drawFullBox(final AxisAlignedBB axisAlignedBB, final BlockPos blockPos, final float n, final int n2, final int n3) {
            drawFullBox(axisAlignedBB, blockPos, n, n2 >>> 16 & 0xFF, n2 >>> 8 & 0xFF, n2 & 0xFF, n2 >>> 24 & 0xFF, n3);
        }
        
        public static void drawHalfBox(final BlockPos blockPos, final int n, final int n2, final int n3, final int n4, final int n5) {
            drawBox(RenderTesselator.INSTANCE.getBuffer(), (float)blockPos.getX(), (float)blockPos.getY(), (float)blockPos.getZ(), 1.0f, 0.5f, 1.0f, n, n2, n3, n4, n5);
        }
        
        public static void drawBox(final BufferBuilder bufferBuilder, final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final int n7, final int n8, final int n9, final int n10, final int n11) {
            if ((n11 & 0x1) != 0x0) {
                bufferBuilder.pos((double)(n + n4), (double)n2, (double)n3).color(n7, n8, n9, n10).endVertex();
                bufferBuilder.pos((double)(n + n4), (double)n2, (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
                bufferBuilder.pos((double)n, (double)n2, (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
                bufferBuilder.pos((double)n, (double)n2, (double)n3).color(n7, n8, n9, n10).endVertex();
            }
            if ((n11 & 0x2) != 0x0) {
                bufferBuilder.pos((double)(n + n4), (double)(n2 + n5), (double)n3).color(n7, n8, n9, n10).endVertex();
                bufferBuilder.pos((double)n, (double)(n2 + n5), (double)n3).color(n7, n8, n9, n10).endVertex();
                bufferBuilder.pos((double)n, (double)(n2 + n5), (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
                bufferBuilder.pos((double)(n + n4), (double)(n2 + n5), (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
            }
            if ((n11 & 0x4) != 0x0) {
                bufferBuilder.pos((double)(n + n4), (double)n2, (double)n3).color(n7, n8, n9, n10).endVertex();
                bufferBuilder.pos((double)n, (double)n2, (double)n3).color(n7, n8, n9, n10).endVertex();
                bufferBuilder.pos((double)n, (double)(n2 + n5), (double)n3).color(n7, n8, n9, n10).endVertex();
                bufferBuilder.pos((double)(n + n4), (double)(n2 + n5), (double)n3).color(n7, n8, n9, n10).endVertex();
            }
            if ((n11 & 0x8) != 0x0) {
                bufferBuilder.pos((double)n, (double)n2, (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
                bufferBuilder.pos((double)(n + n4), (double)n2, (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
                bufferBuilder.pos((double)(n + n4), (double)(n2 + n5), (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
                bufferBuilder.pos((double)n, (double)(n2 + n5), (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
            }
            if ((n11 & 0x10) != 0x0) {
                bufferBuilder.pos((double)n, (double)n2, (double)n3).color(n7, n8, n9, n10).endVertex();
                bufferBuilder.pos((double)n, (double)n2, (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
                bufferBuilder.pos((double)n, (double)(n2 + n5), (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
                bufferBuilder.pos((double)n, (double)(n2 + n5), (double)n3).color(n7, n8, n9, n10).endVertex();
            }
            if ((n11 & 0x20) != 0x0) {
                bufferBuilder.pos((double)(n + n4), (double)n2, (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
                bufferBuilder.pos((double)(n + n4), (double)n2, (double)n3).color(n7, n8, n9, n10).endVertex();
                bufferBuilder.pos((double)(n + n4), (double)(n2 + n5), (double)n3).color(n7, n8, n9, n10).endVertex();
                bufferBuilder.pos((double)(n + n4), (double)(n2 + n5), (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
            }
        }
        
        public static BufferBuilder getBufferBuilder() {
            return RenderTesselator.INSTANCE.getBuffer();
        }
        
        public RenderTesselator() {
            super(2097152);
        }
        
        public static void drawBox(final BlockPos blockPos, final int n, final int n2, final int n3, final int n4, final int n5) {
            drawBox(RenderTesselator.INSTANCE.getBuffer(), (float)blockPos.getX(), (float)blockPos.getY(), (float)blockPos.getZ(), 1.0f, 1.0f, 1.0f, n, n2, n3, n4, n5);
        }
        
        public static void begin(final int n) {
            RenderTesselator.INSTANCE.getBuffer().begin(n, DefaultVertexFormats.POSITION_COLOR);
        }
        
        public static void drawLines(final BufferBuilder bufferBuilder, final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final int n7, final int n8, final int n9, final int n10, final int n11) {
            if ((n11 & 0x11) != 0x0) {
                bufferBuilder.pos((double)n, (double)n2, (double)n3).color(n7, n8, n9, n10).endVertex();
                bufferBuilder.pos((double)n, (double)n2, (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
            }
            if ((n11 & 0x12) != 0x0) {
                bufferBuilder.pos((double)n, (double)(n2 + n5), (double)n3).color(n7, n8, n9, n10).endVertex();
                bufferBuilder.pos((double)n, (double)(n2 + n5), (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
            }
            if ((n11 & 0x21) != 0x0) {
                bufferBuilder.pos((double)(n + n4), (double)n2, (double)n3).color(n7, n8, n9, n10).endVertex();
                bufferBuilder.pos((double)(n + n4), (double)n2, (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
            }
            if ((n11 & 0x22) != 0x0) {
                bufferBuilder.pos((double)(n + n4), (double)(n2 + n5), (double)n3).color(n7, n8, n9, n10).endVertex();
                bufferBuilder.pos((double)(n + n4), (double)(n2 + n5), (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
            }
            if ((n11 & 0x5) != 0x0) {
                bufferBuilder.pos((double)n, (double)n2, (double)n3).color(n7, n8, n9, n10).endVertex();
                bufferBuilder.pos((double)(n + n4), (double)n2, (double)n3).color(n7, n8, n9, n10).endVertex();
            }
            if ((n11 & 0x6) != 0x0) {
                bufferBuilder.pos((double)n, (double)(n2 + n5), (double)n3).color(n7, n8, n9, n10).endVertex();
                bufferBuilder.pos((double)(n + n4), (double)(n2 + n5), (double)n3).color(n7, n8, n9, n10).endVertex();
            }
            if ((n11 & 0x9) != 0x0) {
                bufferBuilder.pos((double)n, (double)n2, (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
                bufferBuilder.pos((double)(n + n4), (double)n2, (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
            }
            if ((n11 & 0xA) != 0x0) {
                bufferBuilder.pos((double)n, (double)(n2 + n5), (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
                bufferBuilder.pos((double)(n + n4), (double)(n2 + n5), (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
            }
            if ((n11 & 0x14) != 0x0) {
                bufferBuilder.pos((double)n, (double)n2, (double)n3).color(n7, n8, n9, n10).endVertex();
                bufferBuilder.pos((double)n, (double)(n2 + n5), (double)n3).color(n7, n8, n9, n10).endVertex();
            }
            if ((n11 & 0x24) != 0x0) {
                bufferBuilder.pos((double)(n + n4), (double)n2, (double)n3).color(n7, n8, n9, n10).endVertex();
                bufferBuilder.pos((double)(n + n4), (double)(n2 + n5), (double)n3).color(n7, n8, n9, n10).endVertex();
            }
            if ((n11 & 0x18) != 0x0) {
                bufferBuilder.pos((double)n, (double)n2, (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
                bufferBuilder.pos((double)n, (double)(n2 + n5), (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
            }
            if ((n11 & 0x28) != 0x0) {
                bufferBuilder.pos((double)(n + n4), (double)n2, (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
                bufferBuilder.pos((double)(n + n4), (double)(n2 + n5), (double)(n3 + n6)).color(n7, n8, n9, n10).endVertex();
            }
        }
        
        public static void drawFullBox(final AxisAlignedBB axisAlignedBB, final BlockPos blockPos, final float n, final int n2, final int n3, final int n4, final int n5, final int n6) {
            prepare(7);
            drawBox(blockPos, n2, n3, n4, n5, 63);
            release();
            drawBoundingBox(axisAlignedBB, n, (float)n2, (float)n3, (float)n4, (float)n6);
        }
        
        public static void releaseGL() {
            GlStateManager.enableCull();
            GlStateManager.depthMask(true);
            GlStateManager.enableTexture2D();
            GlStateManager.enableBlend();
            GlStateManager.enableDepth();
        }
    }
    
    public static final class GeometryMasks
    {
        public static final /* synthetic */ HashMap FACEMAP;
        
        static {
            (FACEMAP = new HashMap()).put(EnumFacing.DOWN, 1);
            GeometryMasks.FACEMAP.put(EnumFacing.WEST, 16);
            GeometryMasks.FACEMAP.put(EnumFacing.NORTH, 4);
            GeometryMasks.FACEMAP.put(EnumFacing.SOUTH, 8);
            GeometryMasks.FACEMAP.put(EnumFacing.EAST, 32);
            GeometryMasks.FACEMAP.put(EnumFacing.UP, 2);
        }
        
        public static final class Line
        {
            static {
                UP_WEST = 18;
                DOWN_SOUTH = 9;
                UP_SOUTH = 10;
                DOWN_NORTH = 5;
                UP_NORTH = 6;
                DOWN_WEST = 17;
                UP_EAST = 34;
                DOWN_EAST = 33;
                SOUTH_WEST = 24;
                NORTH_WEST = 20;
                SOUTH_EAST = 40;
                ALL = 63;
                NORTH_EAST = 36;
            }
        }
        
        public static final class Quad
        {
            static {
                DOWN = 1;
                EAST = 32;
                NORTH = 4;
                ALL = 63;
                UP = 2;
                WEST = 16;
                SOUTH = 8;
            }
        }
    }
}
