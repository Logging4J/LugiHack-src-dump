//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util;

import java.nio.*;
import net.minecraft.client.renderer.culling.*;
import java.awt.*;
import net.minecraft.entity.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.block.state.*;
import net.minecraft.client.shader.*;
import net.minecraft.util.math.*;
import org.lwjgl.util.glu.*;
import org.lwjgl.opengl.*;
import net.minecraft.block.material.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.*;
import me.snow.luigihack.*;
import net.minecraft.world.*;
import me.snow.luigihack.impl.modules.client.*;
import org.lwjgl.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.model.*;
import net.minecraft.client.gui.*;
import net.minecraft.util.*;
import java.util.*;

public class RenderUtil implements Util
{
    private static final /* synthetic */ FloatBuffer modelView;
    private static /* synthetic */ boolean clean;
    public static /* synthetic */ Tessellator tessellator;
    private static /* synthetic */ boolean depth;
    private static /* synthetic */ boolean texture;
    private static final /* synthetic */ FloatBuffer screenCoords;
    private static final /* synthetic */ BufferBuilder bufferbuilder;
    private static final /* synthetic */ IntBuffer viewport;
    private static final /* synthetic */ Frustum frustrum;
    private static /* synthetic */ boolean bind;
    private static /* synthetic */ boolean override;
    public static /* synthetic */ RenderItem itemRender;
    public static /* synthetic */ BufferBuilder builder;
    public static /* synthetic */ ICamera camera;
    private static final /* synthetic */ FloatBuffer projection;
    
    public static void addBuilderVertex(final BufferBuilder bufferBuilder, final double n, final double n2, final double n3, final Color color) {
        bufferBuilder.pos(n, n2, n3).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f).endVertex();
    }
    
    public static void glBillboard(final float n, final float n2, final float n3) {
        final float n4 = 0.02666667f;
        GlStateManager.translate(n - RenderUtil.mc.getRenderManager().renderPosX, n2 - RenderUtil.mc.getRenderManager().renderPosY, n3 - RenderUtil.mc.getRenderManager().renderPosZ);
        GlStateManager.glNormal3f(0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(-RenderUtil.mc.player.rotationYaw, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(RenderUtil.mc.player.rotationPitch, (RenderUtil.mc.gameSettings.thirdPersonView == 2) ? -1.0f : 1.0f, 0.0f, 0.0f);
        GlStateManager.scale(-n4, -n4, n4);
    }
    
    public static void drawGradientPlane(final BlockPos blockPos, final EnumFacing enumFacing, final Color color, final Color color2, final double n) {
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        final IBlockState getBlockState = RenderUtil.mc.world.getBlockState(blockPos);
        final Vec3d interpolateEntity = EntityUtil.interpolateEntity((Entity)RenderUtil.mc.player, RenderUtil.mc.getRenderPartialTicks());
        final AxisAlignedBB expand = getBlockState.getSelectedBoundingBox((World)RenderUtil.mc.world, blockPos).grow(0.0020000000949949026).offset(-interpolateEntity.x, -interpolateEntity.y, -interpolateEntity.z).expand(0.0, n, 0.0);
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
    
    public static void drawGradientRainbowOutLine(double n, double n2, double n3, double n4, final float n5) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(n5);
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
    }
    
    public static void drawOutlineRect2(double n, double n2, double n3, double n4, final Color color, final float n5) {
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
        final float n8 = (color.getRGB() >> 24 & 0xFF) / 255.0f;
        final float n9 = (color.getRGB() >> 16 & 0xFF) / 255.0f;
        final float n10 = (color.getRGB() >> 8 & 0xFF) / 255.0f;
        final float n11 = (color.getRGB() & 0xFF) / 255.0f;
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        GlStateManager.enableBlend();
        GL11.glPolygonMode(1032, 6913);
        GL11.glLineWidth(n5);
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
        GL11.glPolygonMode(1032, 6914);
    }
    
    private static void setupFBO(final Framebuffer framebuffer) {
        EXTFramebufferObject.glDeleteRenderbuffersEXT(framebuffer.depthBuffer);
        final int glGenRenderbuffersEXT = EXTFramebufferObject.glGenRenderbuffersEXT();
        EXTFramebufferObject.glBindRenderbufferEXT(36161, glGenRenderbuffersEXT);
        EXTFramebufferObject.glRenderbufferStorageEXT(36161, 34041, RenderUtil.mc.displayWidth, RenderUtil.mc.displayHeight);
        EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36128, 36161, glGenRenderbuffersEXT);
        EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36096, 36161, glGenRenderbuffersEXT);
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
    
    public static void drawClock(final float n, final float n2, final float n3, final int n4, final int n5, final float n6, final boolean b, final Color color) {
        final Disk disk = new Disk();
        final int n7 = 180 + -(Calendar.getInstance().get(10) * 30 + Calendar.getInstance().get(12) / 2);
        final int n8 = 180 + -(Calendar.getInstance().get(12) * 6 + Calendar.getInstance().get(13) / 10);
        final int n9 = 180 + -(Calendar.getInstance().get(13) * 6);
        Calendar.getInstance().get(12);
        Calendar.getInstance().get(10);
        if (b) {
            GL11.glPushMatrix();
            GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
            GL11.glBlendFunc(770, 771);
            GL11.glEnable(3042);
            GL11.glLineWidth(n6);
            GL11.glDisable(3553);
            disk.setOrientation(100020);
            disk.setDrawStyle(100012);
            GL11.glTranslated((double)n, (double)n2, 0.0);
            disk.draw(0.0f, n3, n4, n5);
            GL11.glEnable(3553);
            GL11.glDisable(3042);
            GL11.glPopMatrix();
        }
        else {
            GL11.glPushMatrix();
            GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
            GL11.glEnable(3042);
            GL11.glLineWidth(n6);
            GL11.glDisable(3553);
            GL11.glBegin(3);
            final ArrayList<Vec2f> list = new ArrayList<Vec2f>();
            float hue = System.currentTimeMillis() % 7200L / 7200.0f;
            for (int i = 0; i <= 360; ++i) {
                list.add(new Vec2f(n + (float)Math.sin(i * 3.141592653589793 / 180.0) * n3, n2 + (float)Math.cos(i * 3.141592653589793 / 180.0) * n3));
            }
            Color color2 = new Color(Color.HSBtoRGB(hue, 1.0f, 1.0f));
            for (int j = 0; j < list.size() - 1; ++j) {
                GL11.glColor4f(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, color2.getAlpha() / 255.0f);
                GL11.glVertex3d((double)list.get(j).x, (double)list.get(j).y, 0.0);
                GL11.glVertex3d((double)list.get(j + 1).x, (double)list.get(j + 1).y, 0.0);
                color2 = new Color(Color.HSBtoRGB(hue += 0.0027777778f, 1.0f, 1.0f));
            }
            GL11.glEnd();
            GL11.glEnable(3553);
            GL11.glDisable(3042);
            GL11.glPopMatrix();
        }
        drawLine(n, n2, n + (float)Math.sin(n7 * 3.141592653589793 / 180.0) * (n3 / 2.0f), n2 + (float)Math.cos(n7 * 3.141592653589793 / 180.0) * (n3 / 2.0f), 1.0f, Color.WHITE.getRGB());
        drawLine(n, n2, n + (float)Math.sin(n8 * 3.141592653589793 / 180.0) * (n3 - n3 / 10.0f), n2 + (float)Math.cos(n8 * 3.141592653589793 / 180.0) * (n3 - n3 / 10.0f), 1.0f, Color.WHITE.getRGB());
        drawLine(n, n2, n + (float)Math.sin(n9 * 3.141592653589793 / 180.0) * (n3 - n3 / 10.0f), n2 + (float)Math.cos(n9 * 3.141592653589793 / 180.0) * (n3 - n3 / 10.0f), 1.0f, Color.RED.getRGB());
    }
    
    public static void GLPre(final float n) {
        RenderUtil.depth = GL11.glIsEnabled(2896);
        RenderUtil.texture = GL11.glIsEnabled(3042);
        RenderUtil.clean = GL11.glIsEnabled(3553);
        RenderUtil.bind = GL11.glIsEnabled(2929);
        RenderUtil.override = GL11.glIsEnabled(2848);
        GLPre(RenderUtil.depth, RenderUtil.texture, RenderUtil.clean, RenderUtil.bind, RenderUtil.override, n);
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
        GL11.glTranslated(n - RenderUtil.mc.renderManager.renderPosX, n2 - RenderUtil.mc.renderManager.renderPosY, n3 - RenderUtil.mc.renderManager.renderPosZ);
        sphere.draw(n4, n5, n6);
        GL11.glLineWidth(2.0f);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
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
    
    public static void release() {
        GlStateManager.depthMask(true);
        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
        GlStateManager.enableAlpha();
        GlStateManager.popMatrix();
        GL11.glEnable(3553);
        GL11.glPolygonMode(1032, 6914);
    }
    
    public static boolean isInViewFrustrum(final Entity entity) {
        return isInViewFrustrum(entity.getEntityBoundingBox()) || entity.ignoreFrustumCheck;
    }
    
    public static void glScissor(final float n, final float n2, final float n3, final float n4, final ScaledResolution scaledResolution) {
        GL11.glScissor((int)(n * scaledResolution.getScaleFactor()), (int)(RenderUtil.mc.displayHeight - n4 * scaledResolution.getScaleFactor()), (int)((n3 - n) * scaledResolution.getScaleFactor()), (int)((n4 - n2) * scaledResolution.getScaleFactor()));
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
    
    public static void boxESP(final BlockPos blockPos, final Color color, final float n, final boolean b, final boolean b2, final int n2, final boolean b3) {
        final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos.getX() - RenderUtil.mc.getRenderManager().viewerPosX, blockPos.getY() - RenderUtil.mc.getRenderManager().viewerPosY, blockPos.getZ() - RenderUtil.mc.getRenderManager().viewerPosZ, blockPos.getX() + 1 - RenderUtil.mc.getRenderManager().viewerPosX, blockPos.getY() + 1 - RenderUtil.mc.getRenderManager().viewerPosY, blockPos.getZ() + 1 - RenderUtil.mc.getRenderManager().viewerPosZ);
        RenderUtil.camera.setPosition(Objects.requireNonNull(RenderUtil.mc.getRenderViewEntity()).posX, RenderUtil.mc.getRenderViewEntity().posY, RenderUtil.mc.getRenderViewEntity().posZ);
        if (RenderUtil.camera.isBoundingBoxInFrustum(new AxisAlignedBB(axisAlignedBB.minX + RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB.minY + RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ + RenderUtil.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX + RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY + RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ + RenderUtil.mc.getRenderManager().viewerPosZ))) {
            double n3 = 0.0;
            double n4 = 0.0;
            double n5 = 0.0;
            double n6 = 0.0;
            double n7 = 0.0;
            double n8 = 0.0;
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.disableDepth();
            GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
            GlStateManager.disableTexture2D();
            GlStateManager.depthMask(false);
            GL11.glEnable(2848);
            GL11.glHint(3154, 4354);
            GL11.glLineWidth(n);
            final double n9 = RenderUtil.mc.playerController.curBlockDamageMP;
            if (b3) {
                n8 = axisAlignedBB.minX + 0.5 - n9 / 2.0;
                n7 = axisAlignedBB.minY + 0.5 - n9 / 2.0;
                n6 = axisAlignedBB.minZ + 0.5 - n9 / 2.0;
                n5 = axisAlignedBB.maxX - 0.5 + n9 / 2.0;
                n4 = axisAlignedBB.maxY - 0.5 + n9 / 2.0;
                n3 = axisAlignedBB.maxZ - 0.5 + n9 / 2.0;
            }
            final AxisAlignedBB axisAlignedBB2 = new AxisAlignedBB(n8, n7, n6, n5, n4, n3);
            if (b2) {
                drawFilledBox(axisAlignedBB2, new Color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, n2 / 255.0f).getRGB());
            }
            GL11.glDisable(2848);
            GlStateManager.depthMask(true);
            GlStateManager.enableDepth();
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
    }
    
    public static void drawBorderedRect(final int n, final double n2, final int n3, final double n4, final int n5, int shadeColour, int shadeColour2, final boolean b) {
        if (b) {
            shadeColour = ColorUtil.shadeColour(shadeColour, -20);
            shadeColour2 = ColorUtil.shadeColour(shadeColour2, -20);
        }
        drawRectBase(n + n5, n2 + n5, n3 - n5, n4 - n5, shadeColour);
        drawRectBase(n, n2 + n5, n + n5, n4 - n5, shadeColour2);
        drawRectBase(n3 - n5, n2 + n5, n3, n4 - n5, shadeColour2);
        drawRectBase(n, n2, n3, n2 + n5, shadeColour2);
        drawRectBase(n, n4 - n5, n3, n4, shadeColour2);
    }
    
    public static Vec3d to2D(final double n, final double n2, final double n3) {
        GL11.glGetFloat(2982, RenderUtil.modelView);
        GL11.glGetFloat(2983, RenderUtil.projection);
        GL11.glGetInteger(2978, RenderUtil.viewport);
        if (GLU.gluProject((float)n, (float)n2, (float)n3, RenderUtil.modelView, RenderUtil.projection, RenderUtil.viewport, RenderUtil.screenCoords)) {
            return new Vec3d((double)RenderUtil.screenCoords.get(0), (double)(Display.getHeight() - RenderUtil.screenCoords.get(1)), (double)RenderUtil.screenCoords.get(2));
        }
        return null;
    }
    
    public static void drawBoxESP(final BlockPos blockPos, final Color color, final boolean b, final Color color2, final float n, final boolean b2, final boolean b3, final int a, final boolean b4, final double n2, final boolean b5, final boolean b6, final boolean b7, final boolean b8, final int n3) {
        if (b3) {
            drawBox(blockPos, new Color(color.getRed(), color.getGreen(), color.getBlue(), a), n2, b5, b7, n3);
        }
        if (b2) {
            drawBlockOutline(blockPos, b ? color2 : color, n, b4, n2, b6, b8, n3);
        }
    }
    
    public static void renderFour(final Color color) {
        setColor(color);
        GL11.glDepthMask(false);
        GL11.glDisable(2929);
        GL11.glEnable(10754);
        GL11.glPolygonOffset(1.0f, -2000000.0f);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0f, 240.0f);
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
    
    public static void checkSetupFBO() {
        final Framebuffer framebuffer = RenderUtil.mc.framebuffer;
        if (framebuffer != null && framebuffer.depthBuffer > -1) {
            setupFBO(framebuffer);
            framebuffer.depthBuffer = -1;
        }
    }
    
    public static void drawBlockOutline(final BlockPos blockPos, final Color color, final float n, final boolean b) {
        final IBlockState getBlockState = RenderUtil.mc.world.getBlockState(blockPos);
        if ((b || getBlockState.getMaterial() != Material.AIR) && RenderUtil.mc.world.getWorldBorder().contains(blockPos)) {
            final Vec3d interpolateEntity = EntityUtil.interpolateEntity((Entity)RenderUtil.mc.player, RenderUtil.mc.getRenderPartialTicks());
            drawBlockOutline(getBlockState.getSelectedBoundingBox((World)RenderUtil.mc.world, blockPos).grow(0.0020000000949949026).offset(-interpolateEntity.x, -interpolateEntity.y, -interpolateEntity.z), color, n);
        }
    }
    
    public static void drawOutlinedBlockESP(final BlockPos blockPos, final Color color, final float n) {
        final IBlockState getBlockState = RenderUtil.mc.world.getBlockState(blockPos);
        final Vec3d interpolateEntity = EntityUtil.interpolateEntity((Entity)RenderUtil.mc.player, RenderUtil.mc.getRenderPartialTicks());
        drawBoundingBox(getBlockState.getSelectedBoundingBox((World)RenderUtil.mc.world, blockPos).grow(0.0020000000949949026).offset(-interpolateEntity.x, -interpolateEntity.y, -interpolateEntity.z), n, ColorUtil.toRGBA(color));
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
    
    public static void rotationHelper(final float n, final float n2, final float n3) {
        GlStateManager.rotate(n2, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(n3, 0.0f, 0.0f, 1.0f);
        GlStateManager.rotate(n, 1.0f, 0.0f, 0.0f);
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
    
    public static void drawGradientBlockOutline(final BlockPos blockPos, final Color color, final Color color2, final float n, final double n2) {
        final IBlockState getBlockState = RenderUtil.mc.world.getBlockState(blockPos);
        final Vec3d interpolateEntity = EntityUtil.interpolateEntity((Entity)RenderUtil.mc.player, RenderUtil.mc.getRenderPartialTicks());
        drawGradientBlockOutline(getBlockState.getSelectedBoundingBox((World)RenderUtil.mc.world, blockPos).grow(0.0020000000949949026).offset(-interpolateEntity.x, -interpolateEntity.y, -interpolateEntity.z).expand(0.0, n2, 0.0), color, color2, n);
    }
    
    public static void drawClosedGradientBox(final BlockPos blockPos, final Color color, final Color color2, final double n) {
        final EnumFacing[] values = EnumFacing.values();
        for (int length = values.length, i = 0; i < length; ++i) {
            drawGradientPlane(blockPos, values[i], color, color2, n);
        }
    }
    
    public static void drawEvenBetterGradientBox(final BlockPos blockPos, final Color color, final Color color2, final Color color3) {
        final float n = color.getRed() / 255.0f;
        final float n2 = color.getGreen() / 255.0f;
        final float n3 = color.getBlue() / 255.0f;
        final float n4 = color.getAlpha() / 255.0f;
        final float n5 = color3.getRed() / 255.0f;
        final float n6 = color3.getGreen() / 255.0f;
        final float n7 = color3.getBlue() / 255.0f;
        final float n8 = color3.getAlpha() / 255.0f;
        final float n9 = color2.getRed() / 255.0f;
        final float n10 = color2.getGreen() / 255.0f;
        final float n11 = color2.getBlue() / 255.0f;
        final float n12 = color2.getAlpha() / 255.0f;
        final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos.getX() - RenderUtil.mc.getRenderManager().viewerPosX, blockPos.getY() - RenderUtil.mc.getRenderManager().viewerPosY, blockPos.getZ() - RenderUtil.mc.getRenderManager().viewerPosZ, blockPos.getX() + 1 - RenderUtil.mc.getRenderManager().viewerPosX, blockPos.getY() + 1 - RenderUtil.mc.getRenderManager().viewerPosY, blockPos.getZ() + 1 - RenderUtil.mc.getRenderManager().viewerPosZ);
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
        getBuffer.begin(5, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getInstance.draw();
        GL11.glDisable(2848);
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
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
    
    public static void glStart(final float n, final float n2, final float n3, final float n4) {
        glrendermethod();
        GL11.glColor4f(n, n2, n3, n4);
    }
    
    public static void drawOutlineRect3(double n, double n2, double n3, double n4, final Color color, final float n5) {
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
        final float n8 = (color.getRGB() >> 24 & 0xFF) / 255.0f;
        final float n9 = (color.getRGB() >> 16 & 0xFF) / 255.0f;
        final float n10 = (color.getRGB() >> 8 & 0xFF) / 255.0f;
        final float n11 = (color.getRGB() & 0xFF) / 255.0f;
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        GlStateManager.enableBlend();
        GL11.glPolygonMode(1032, 6913);
        GL11.glLineWidth(n5);
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
        GL11.glPolygonMode(1032, 6914);
    }
    
    public static void drawBlockCrossed(final AxisAlignedBB axisAlignedBB, final Color color, final float n) {
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
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
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
    
    public static void drawCircleOutline(final float n, final float n2, final float n3) {
        drawCircleOutline(n, n2, n3, 0, 360, 40);
    }
    
    public static void drawProperGradientBlockOutline(final BlockPos blockPos, final Color color, final Color color2, final Color color3, final float n) {
        final IBlockState getBlockState = RenderUtil.mc.world.getBlockState(blockPos);
        final Vec3d interpolateEntity = EntityUtil.interpolateEntity((Entity)RenderUtil.mc.player, RenderUtil.mc.getRenderPartialTicks());
        drawProperGradientBlockOutline(getBlockState.getSelectedBoundingBox((World)RenderUtil.mc.world, blockPos).grow(0.0020000000949949026).offset(-interpolateEntity.x, -interpolateEntity.y, -interpolateEntity.z), color, color2, color3, n);
    }
    
    public static void drawGradientPlane(final BlockPos blockPos, final EnumFacing enumFacing, final Color color, final Color color2, final boolean b, final boolean b2) {
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        final IBlockState getBlockState = RenderUtil.mc.world.getBlockState(blockPos);
        final Vec3d interpolateEntity = EntityUtil.interpolateEntity((Entity)RenderUtil.mc.player, RenderUtil.mc.getRenderPartialTicks());
        final AxisAlignedBB offset = getBlockState.getSelectedBoundingBox((World)RenderUtil.mc.world, blockPos).grow(0.0020000000949949026).offset(-interpolateEntity.x, -interpolateEntity.y, -interpolateEntity.z);
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
    
    public static AxisAlignedBB fixBB(final AxisAlignedBB axisAlignedBB) {
        return new AxisAlignedBB(axisAlignedBB.minX - RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB.minY - RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ - RenderUtil.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX - RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY - RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ - RenderUtil.mc.getRenderManager().viewerPosZ);
    }
    
    public static void drawGradientSideways(final double n, final double n2, final double n3, final double n4, final int n5, final int n6) {
        final float n7 = (n5 >> 24 & 0xFF) / 255.0f;
        final float n8 = (n5 >> 16 & 0xFF) / 255.0f;
        final float n9 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n10 = (n5 & 0xFF) / 255.0f;
        final float n11 = (n6 >> 24 & 0xFF) / 255.0f;
        final float n12 = (n6 >> 16 & 0xFF) / 255.0f;
        final float n13 = (n6 >> 8 & 0xFF) / 255.0f;
        final float n14 = (n6 & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glShadeModel(7425);
        GL11.glPushMatrix();
        GL11.glBegin(7);
        GL11.glColor4f(n8, n9, n10, n7);
        GL11.glVertex2d(n, n2);
        GL11.glVertex2d(n, n4);
        GL11.glColor4f(n12, n13, n14, n11);
        GL11.glVertex2d(n3, n4);
        GL11.glVertex2d(n3, n2);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glShadeModel(7424);
    }
    
    public static void drawBox(final BlockPos blockPos, final Color color, final double n, final boolean b, final boolean b2, final int a) {
        if (b) {
            final Color color2 = new Color(color.getRed(), color.getGreen(), color.getBlue(), a);
            drawOpenGradientBox(blockPos, b2 ? color2 : color, b2 ? color : color2, n);
            return;
        }
        final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos.getX() - RenderUtil.mc.getRenderManager().viewerPosX, blockPos.getY() - RenderUtil.mc.getRenderManager().viewerPosY, blockPos.getZ() - RenderUtil.mc.getRenderManager().viewerPosZ, blockPos.getX() + 1 - RenderUtil.mc.getRenderManager().viewerPosX, blockPos.getY() + 1 - RenderUtil.mc.getRenderManager().viewerPosY + n, blockPos.getZ() + 1 - RenderUtil.mc.getRenderManager().viewerPosZ);
        RenderUtil.camera.setPosition(Objects.requireNonNull(RenderUtil.mc.getRenderViewEntity()).posX, RenderUtil.mc.getRenderViewEntity().posY, RenderUtil.mc.getRenderViewEntity().posZ);
        if (RenderUtil.camera.isBoundingBoxInFrustum(new AxisAlignedBB(axisAlignedBB.minX + RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB.minY + RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ + RenderUtil.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX + RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY + RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ + RenderUtil.mc.getRenderManager().viewerPosZ))) {
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
    
    public static void drawCircle(final float n, final float n2, final float n3) {
        drawCircle(n, n2, n3, 0, 360, 64);
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
        GL11.glBlendFunc(770, 771);
    }
    
    public static void drawOutlinedRoundedRectangle(final int n, final int n2, final int n3, final int n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10) {
        drawRoundedRectangle((float)n, (float)n2, (float)n3, (float)n4, n5);
        GL11.glColor4f(n6, n7, n8, n9);
        drawRoundedRectangle(n + n10, n2 + n10, n3 - n10 * 2.0f, n4 - n10 * 2.0f, n5);
    }
    
    public static void blockESP(final BlockPos blockPos, final Color color, final double n, final double n2) {
        blockEsp(blockPos, color, n, n2);
    }
    
    public static void drawProjectedText(final GLUProjection.Projection projection, final float n, final float n2, final String s, final Color color, final boolean b) {
        if (projection.getType() == GLUProjection.Projection.Type.INSIDE) {
            GlStateManager.pushMatrix();
            GlStateManager.translate(projection.getX(), projection.getY(), 0.0);
            LuigiHack.textManager.drawString(s, -(LuigiHack.textManager.getStringWidth(s) / 2.0f) + n, n2, color.getRGB(), b);
            GlStateManager.translate(-projection.getX(), -projection.getY(), 0.0);
            GlStateManager.popMatrix();
        }
    }
    
    public static void glrendermethod() {
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glLineWidth(2.0f);
        GL11.glDisable(3553);
        GL11.glEnable(2884);
        GL11.glDisable(2929);
        final double viewerPosX = RenderUtil.mc.getRenderManager().viewerPosX;
        final double viewerPosY = RenderUtil.mc.getRenderManager().viewerPosY;
        final double viewerPosZ = RenderUtil.mc.getRenderManager().viewerPosZ;
        GL11.glPushMatrix();
        GL11.glTranslated(-viewerPosX, -viewerPosY, -viewerPosZ);
    }
    
    public static AxisAlignedBB getBoundingBox(final BlockPos blockPos) {
        return RenderUtil.mc.world.getBlockState(blockPos).getBoundingBox((IBlockAccess)RenderUtil.mc.world, blockPos).offset(blockPos);
    }
    
    public static void drawGradientFilledBox(final AxisAlignedBB axisAlignedBB, final Color color, final Color color2) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        final float n = color2.getAlpha() / 255.0f;
        final float n2 = color2.getRed() / 255.0f;
        final float n3 = color2.getGreen() / 255.0f;
        final float n4 = color2.getBlue() / 255.0f;
        final float n5 = color.getAlpha() / 255.0f;
        final float n6 = color.getRed() / 255.0f;
        final float n7 = color.getGreen() / 255.0f;
        final float n8 = color.getBlue() / 255.0f;
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n6, n7, n8, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n6, n7, n8, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n6, n7, n8, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n6, n7, n8, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n2, n3, n4, n).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n2, n3, n4, n).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n2, n3, n4, n).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n2, n3, n4, n).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n6, n7, n8, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n2, n3, n4, n).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n2, n3, n4, n).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n6, n7, n8, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n6, n7, n8, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n2, n3, n4, n).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n2, n3, n4, n).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n6, n7, n8, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n6, n7, n8, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n6, n7, n8, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n2, n3, n4, n).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n2, n3, n4, n).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n6, n7, n8, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n6, n7, n8, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n2, n3, n4, n).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n2, n3, n4, n).endVertex();
        getInstance.draw();
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
    public static void drawSexyBoxPhobosIsRetardedFuckYouESP(final AxisAlignedBB axisAlignedBB, final Color color, final Color color2, final float n, final boolean b, final boolean b2, final boolean b3, float n2, final float n3, final float n4) {
        final double n5 = 0.5 * (1.0f - n3);
        final AxisAlignedBB interpolateAxis = interpolateAxis(new AxisAlignedBB(axisAlignedBB.minX + n5, axisAlignedBB.minY + n5 + (1.0f - n4), axisAlignedBB.minZ + n5, axisAlignedBB.maxX - n5, axisAlignedBB.maxY - n5, axisAlignedBB.maxZ - n5));
        float n6 = color.getRed() / 255.0f;
        float n7 = color.getGreen() / 255.0f;
        float n8 = color.getBlue() / 255.0f;
        final float n9 = color.getAlpha() / 255.0f;
        float n10 = color2.getRed() / 255.0f;
        float n11 = color2.getGreen() / 255.0f;
        float n12 = color2.getBlue() / 255.0f;
        final float n13 = color2.getAlpha() / 255.0f;
        if (b3) {
            n6 = Colors.INSTANCE.getCurrentColor().getRed() / 255.0f;
            n7 = Colors.INSTANCE.getCurrentColor().getGreen() / 255.0f;
            n8 = Colors.INSTANCE.getCurrentColor().getBlue() / 255.0f;
            n10 = Colors.INSTANCE.getCurrentColor().getRed() / 255.0f;
            n11 = Colors.INSTANCE.getCurrentColor().getGreen() / 255.0f;
            n12 = Colors.INSTANCE.getCurrentColor().getBlue() / 255.0f;
        }
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
    
    public static void drawOutlineRect1(double n, double n2, double n3, double n4, final Color color, final float n5) {
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
        final float n8 = (color.getRGB() >> 24 & 0xFF) / 255.0f;
        final float n9 = (color.getRGB() >> 16 & 0xFF) / 255.0f;
        final float n10 = (color.getRGB() >> 8 & 0xFF) / 255.0f;
        final float n11 = (color.getRGB() & 0xFF) / 255.0f;
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        GlStateManager.enableBlend();
        GL11.glPolygonMode(1032, 6913);
        GL11.glLineWidth(n5);
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
        GL11.glPolygonMode(1032, 6914);
    }
    
    public static void drawBox(final BlockPos blockPos, final Color color) {
        final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos.getX() - RenderUtil.mc.getRenderManager().viewerPosX, blockPos.getY() - RenderUtil.mc.getRenderManager().viewerPosY, blockPos.getZ() - RenderUtil.mc.getRenderManager().viewerPosZ, blockPos.getX() + 1 - RenderUtil.mc.getRenderManager().viewerPosX, blockPos.getY() + 1 - RenderUtil.mc.getRenderManager().viewerPosY, blockPos.getZ() + 1 - RenderUtil.mc.getRenderManager().viewerPosZ);
        RenderUtil.camera.setPosition(Objects.requireNonNull(RenderUtil.mc.getRenderViewEntity()).posX, RenderUtil.mc.getRenderViewEntity().posY, RenderUtil.mc.getRenderViewEntity().posZ);
        if (RenderUtil.camera.isBoundingBoxInFrustum(new AxisAlignedBB(axisAlignedBB.minX + RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB.minY + RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ + RenderUtil.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX + RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY + RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ + RenderUtil.mc.getRenderManager().viewerPosZ))) {
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
    
    static {
        frustrum = new Frustum();
        screenCoords = BufferUtils.createFloatBuffer(3);
        viewport = BufferUtils.createIntBuffer(16);
        modelView = BufferUtils.createFloatBuffer(16);
        projection = BufferUtils.createFloatBuffer(16);
        RenderUtil.itemRender = RenderUtil.mc.getRenderItem();
        RenderUtil.camera = (ICamera)new Frustum();
        RenderUtil.depth = GL11.glIsEnabled(2896);
        RenderUtil.texture = GL11.glIsEnabled(3042);
        RenderUtil.clean = GL11.glIsEnabled(3553);
        RenderUtil.bind = GL11.glIsEnabled(2929);
        RenderUtil.override = GL11.glIsEnabled(2848);
        RenderUtil.tessellator = Tessellator.getInstance();
        RenderUtil.builder = RenderUtil.tessellator.getBuffer();
        bufferbuilder = Tessellator.getInstance().getBuffer();
    }
    
    public static void drawTexturedRect(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        getBuffer.pos((double)n, (double)(n2 + n6), (double)n7).tex((double)(n3 * 0.00390625f), (double)((n4 + n6) * 0.00390625f)).endVertex();
        getBuffer.pos((double)(n + n5), (double)(n2 + n6), (double)n7).tex((double)((n3 + n5) * 0.00390625f), (double)((n4 + n6) * 0.00390625f)).endVertex();
        getBuffer.pos((double)(n + n5), (double)n2, (double)n7).tex((double)((n3 + n5) * 0.00390625f), (double)(n4 * 0.00390625f)).endVertex();
        getBuffer.pos((double)n, (double)n2, (double)n7).tex((double)(n3 * 0.00390625f), (double)(n4 * 0.00390625f)).endVertex();
        getInstance.draw();
    }
    
    public static void drawRectCol(final float n, final float n2, final float n3, final float n4, final Color color) {
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
    
    public static void drawBetterGradientBox(final BlockPos blockPos, final Color color, final Color color2) {
        final float n = color.getRed() / 255.0f;
        final float n2 = color.getGreen() / 255.0f;
        final float n3 = color.getBlue() / 255.0f;
        final float n4 = color.getAlpha() / 255.0f;
        final float n5 = color2.getRed() / 255.0f;
        final float n6 = color2.getGreen() / 255.0f;
        final float n7 = color2.getBlue() / 255.0f;
        final float n8 = color2.getAlpha() / 255.0f;
        final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos.getX() - RenderUtil.mc.getRenderManager().viewerPosX, blockPos.getY() - RenderUtil.mc.getRenderManager().viewerPosY, blockPos.getZ() - RenderUtil.mc.getRenderManager().viewerPosZ, blockPos.getX() + 1 - RenderUtil.mc.getRenderManager().viewerPosX, blockPos.getY() + 1 - RenderUtil.mc.getRenderManager().viewerPosY, blockPos.getZ() + 1 - RenderUtil.mc.getRenderManager().viewerPosZ);
        final BufferBuilder getBuffer = Tessellator.getInstance().getBuffer();
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        getBuffer.begin(5, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
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
    }
    
    public static void updateModelViewProjectionMatrix() {
        GL11.glGetFloat(2982, RenderUtil.modelView);
        GL11.glGetFloat(2983, RenderUtil.projection);
        GL11.glGetInteger(2978, RenderUtil.viewport);
        final ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        GLUProjection.getInstance().updateMatrices(RenderUtil.viewport, RenderUtil.modelView, RenderUtil.projection, (double)(scaledResolution.getScaledWidth() / (float)Minecraft.getMinecraft().displayWidth), (double)(scaledResolution.getScaledHeight() / (float)Minecraft.getMinecraft().displayHeight));
    }
    
    public static void GlPost() {
        GLPost(RenderUtil.depth, RenderUtil.texture, RenderUtil.clean, RenderUtil.bind, RenderUtil.override);
    }
    
    public static void drawGradientRect(final float n, final float n2, final float n3, final float n4, final int n5, final int n6) {
        final float n7 = (n5 >> 24 & 0xFF) / 255.0f;
        final float n8 = (n5 >> 16 & 0xFF) / 255.0f;
        final float n9 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n10 = (n5 & 0xFF) / 255.0f;
        final float n11 = (n6 >> 24 & 0xFF) / 255.0f;
        final float n12 = (n6 >> 16 & 0xFF) / 255.0f;
        final float n13 = (n6 >> 8 & 0xFF) / 255.0f;
        final float n14 = (n6 & 0xFF) / 255.0f;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos(n + (double)n3, (double)n2, 0.0).color(n8, n9, n10, n7).endVertex();
        getBuffer.pos((double)n, (double)n2, 0.0).color(n8, n9, n10, n7).endVertex();
        getBuffer.pos((double)n, n2 + (double)n4, 0.0).color(n12, n13, n14, n11).endVertex();
        getBuffer.pos(n + (double)n3, n2 + (double)n4, 0.0).color(n12, n13, n14, n11).endVertex();
        getInstance.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
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
    
    public static Color getRainbowAlpha(final int n, final int n2, final float s, final float b, final int a) {
        final Color color = new Color(Color.getHSBColor((System.currentTimeMillis() + n2) % n / (float)n, s, b).getRGB());
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), a);
    }
    
    public static void glEnd() {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glPopMatrix();
        GL11.glEnable(2929);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
    }
    
    public static void drawCircle(final float n, final float n2, final float n3, final int n4, final int n5, final int n6) {
        drawArc(n, n2, n3, (float)n4, (float)n5, n6);
    }
    
    public static void drawBoxESP(final BlockPos blockPos, final Color color, final boolean b, final Color color2, final float n, final boolean b2, final boolean b3, final int a, final boolean b4) {
        if (b3) {
            drawBox(blockPos, new Color(color.getRed(), color.getGreen(), color.getBlue(), a));
        }
        if (b2) {
            drawBlockOutline(blockPos, b ? color2 : color, n, b4);
        }
    }
    
    public static void renderThree() {
        GL11.glStencilFunc(514, 1, 15);
        GL11.glStencilOp(7680, 7680, 7680);
        GL11.glPolygonMode(1032, 6913);
    }
    
    public static void releaseGL() {
        GlStateManager.enableCull();
        GlStateManager.depthMask(true);
        GlStateManager.enableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.enableDepth();
        GlStateManager.color(Float.intBitsToFloat(Float.floatToIntBits(12.552789f) ^ 0x7EC8D839), Float.intBitsToFloat(Float.floatToIntBits(7.122752f) ^ 0x7F63ED96), Float.intBitsToFloat(Float.floatToIntBits(5.4278784f) ^ 0x7F2DB12E));
        GL11.glColor4f(Float.intBitsToFloat(Float.floatToIntBits(10.5715685f) ^ 0x7EA92525), Float.intBitsToFloat(Float.floatToIntBits(4.9474883f) ^ 0x7F1E51D3), Float.intBitsToFloat(Float.floatToIntBits(4.9044757f) ^ 0x7F1CF177), Float.intBitsToFloat(Float.floatToIntBits(9.482457f) ^ 0x7E97B825));
    }
    
    public static void drawCheckmark(final float n, final float n2, final Color color) {
        GL11.glPushMatrix();
        GL11.glEnable(2848);
        GL11.glDisable(3553);
        GL11.glEnable(3042);
        GL11.glColor4f((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), (float)color.getAlpha());
        GL11.glLineWidth(2.0f);
        GL11.glBegin(1);
        GL11.glVertex2d((double)(n + 1.0f), (double)(n2 + 1.0f));
        GL11.glVertex2d((double)(n + 3.0f), (double)(n2 + 4.0f));
        GL11.glEnd();
        GL11.glBegin(1);
        GL11.glVertex2d((double)(n + 3.0f), (double)(n2 + 4.0f));
        GL11.glVertex2d((double)(n + 6.0f), (double)(n2 - 2.0f));
        GL11.glEnd();
        GL11.glDisable(3042);
        GL11.glEnable(3553);
        GL11.glDisable(2848);
        GL11.glPopMatrix();
    }
    
    public static void drawTextWhite(final AxisAlignedBB axisAlignedBB, final String s) {
        if (axisAlignedBB == null || s == null) {
            return;
        }
        GlStateManager.pushMatrix();
        glBillboardDistanceScaled((float)axisAlignedBB.minX + 0.5f, (float)axisAlignedBB.minY + 0.5f, (float)axisAlignedBB.minZ + 0.5f, (EntityPlayer)RenderUtil.mc.player, 1.0f);
        GlStateManager.disableDepth();
        GlStateManager.translate(-(LuigiHack.textManager.getStringWidth(s) / 2.0), 0.0, 0.0);
        LuigiHack.textManager.drawStringWithShadow(s, 0.0f, 0.0f, ColorUtil.toRGBA(255, 255, 255, 255));
        GlStateManager.popMatrix();
    }
    
    public static Vec3d updateToCamera(final Vec3d vec3d) {
        return new Vec3d(vec3d.x - RenderUtil.mc.getRenderManager().viewerPosX, vec3d.y - RenderUtil.mc.getRenderManager().viewerPosY, vec3d.z - RenderUtil.mc.getRenderManager().viewerPosZ);
    }
    
    public static void renderTwo() {
        GL11.glStencilFunc(512, 0, 15);
        GL11.glStencilOp(7681, 7681, 7681);
        GL11.glPolygonMode(1032, 6914);
    }
    
    public static void drawHLineG(final int n, final int n2, final int n3, final int n4, final int n5) {
        drawSidewaysGradientRect(n, n2, n + n3, n2 + 1, n4, n5);
    }
    
    public static void setColor(final Color color) {
        GL11.glColor4d(color.getRed() / 255.0, color.getGreen() / 255.0, color.getBlue() / 255.0, color.getAlpha() / 255.0);
    }
    
    public static void drawBar(final GLUProjection.Projection projection, final float n, final float n2, final float n3, final Color color, final Color color2) {
        if (projection.getType() == GLUProjection.Projection.Type.INSIDE) {
            GlStateManager.pushMatrix();
            GlStateManager.translate(projection.getX(), projection.getY(), 0.0);
            drawOutlineRect(-(n3 / 2.0f), -(n2 / 2.0f), n3, n2, color2.getRGB());
            drawRect(-(n3 / 2.0f), -(n2 / 2.0f), n, n2, color.getRGB());
            GlStateManager.translate(-projection.getX(), -projection.getY(), 0.0);
            GlStateManager.popMatrix();
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
    
    public static void drawProperGradientBlockOutline(final AxisAlignedBB axisAlignedBB, final Color color, final Color color2, final Color color3, final float n) {
        final float n2 = color3.getRed() / 255.0f;
        final float n3 = color3.getGreen() / 255.0f;
        final float n4 = color3.getBlue() / 255.0f;
        final float n5 = color3.getAlpha() / 255.0f;
        final float n6 = color2.getRed() / 255.0f;
        final float n7 = color2.getGreen() / 255.0f;
        final float n8 = color2.getBlue() / 255.0f;
        final float n9 = color2.getAlpha() / 255.0f;
        final float n10 = color.getRed() / 255.0f;
        final float n11 = color.getGreen() / 255.0f;
        final float n12 = color.getBlue() / 255.0f;
        final float n13 = color.getAlpha() / 255.0f;
        final double n14 = (axisAlignedBB.maxY - axisAlignedBB.minY) / 2.0;
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(n);
        GL11.glBegin(1);
        GL11.glColor4d((double)n2, (double)n3, (double)n4, (double)n5);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glColor4d((double)n6, (double)n7, (double)n8, (double)n9);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY + n14, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY + n14, axisAlignedBB.minZ);
        GL11.glColor4f(n10, n11, n12, n13);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glColor4d((double)n2, (double)n3, (double)n4, (double)n5);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glColor4d((double)n6, (double)n7, (double)n8, (double)n9);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY + n14, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY + n14, axisAlignedBB.maxZ);
        GL11.glColor4d((double)n10, (double)n11, (double)n12, (double)n13);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glColor4d((double)n2, (double)n3, (double)n4, (double)n5);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ);
        GL11.glColor4d((double)n6, (double)n7, (double)n8, (double)n9);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY + n14, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY + n14, axisAlignedBB.maxZ);
        GL11.glColor4d((double)n10, (double)n11, (double)n12, (double)n13);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glColor4d((double)n2, (double)n3, (double)n4, (double)n5);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ);
        GL11.glColor4d((double)n6, (double)n7, (double)n8, (double)n9);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY + n14, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY + n14, axisAlignedBB.minZ);
        GL11.glColor4d((double)n10, (double)n11, (double)n12, (double)n13);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glColor4d((double)n10, (double)n11, (double)n12, (double)n13);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ);
        GL11.glEnd();
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
    
    public static int getRainbow(final int n, final int n2, final float s, final float b) {
        return Color.getHSBColor((System.currentTimeMillis() + n2) % n / (float)n, s, b).getRGB();
    }
    
    public static void drawCrossESP(final BlockPos blockPos, final Color color, final float n, final boolean b) {
        drawBlockCrossedESP(blockPos, new Color(color.getRed(), color.getGreen(), color.getBlue()), n, b);
    }
    
    public static void drawBetterGradientBox(final BlockPos blockPos, final Color color, final Color color2, final Color color3) {
        final float n = color.getRed() / 255.0f;
        final float n2 = color.getGreen() / 255.0f;
        final float n3 = color.getBlue() / 255.0f;
        final float n4 = color.getAlpha() / 255.0f;
        final float n5 = color3.getRed() / 255.0f;
        final float n6 = color3.getGreen() / 255.0f;
        final float n7 = color3.getBlue() / 255.0f;
        final float n8 = color3.getAlpha() / 255.0f;
        final float n9 = color2.getRed() / 255.0f;
        final float n10 = color2.getGreen() / 255.0f;
        final float n11 = color2.getBlue() / 255.0f;
        final float n12 = color2.getAlpha() / 255.0f;
        final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos.getX() - RenderUtil.mc.getRenderManager().viewerPosX, blockPos.getY() - RenderUtil.mc.getRenderManager().viewerPosY, blockPos.getZ() - RenderUtil.mc.getRenderManager().viewerPosZ, blockPos.getX() + 1 - RenderUtil.mc.getRenderManager().viewerPosX, blockPos.getY() + 1 - RenderUtil.mc.getRenderManager().viewerPosY, blockPos.getZ() + 1 - RenderUtil.mc.getRenderManager().viewerPosZ);
        final double n13 = (axisAlignedBB.maxY - axisAlignedBB.minY) / 2.0;
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
        getBuffer.begin(5, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY + n13, axisAlignedBB.minZ).color(n9, n10, n11, n12).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY + n13, axisAlignedBB.maxZ).color(n9, n10, n11, n12).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY + n13, axisAlignedBB.maxZ).color(n9, n10, n11, n12).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY + n13, axisAlignedBB.maxZ).color(n9, n10, n11, n12).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n5, n6, n7, n8).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY + n13, axisAlignedBB.minZ).color(n9, n10, n11, n12).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY + n13, axisAlignedBB.minZ).color(n9, n10, n11, n12).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY + n13, axisAlignedBB.minZ).color(n9, n10, n11, n12).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY + n13, axisAlignedBB.maxZ).color(n9, n10, n11, n12).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY + n13, axisAlignedBB.maxZ).color(n9, n10, n11, n12).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY + n13, axisAlignedBB.maxZ).color(n9, n10, n11, n12).endVertex();
        getInstance.draw();
        GL11.glDisable(2848);
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
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
    
    public static void drawGradientFilledBox(final BlockPos blockPos, final Color color, final Color color2) {
        final IBlockState getBlockState = RenderUtil.mc.world.getBlockState(blockPos);
        final Vec3d interpolateEntity = EntityUtil.interpolateEntity((Entity)RenderUtil.mc.player, RenderUtil.mc.getRenderPartialTicks());
        drawGradientFilledBox(getBlockState.getSelectedBoundingBox((World)RenderUtil.mc.world, blockPos).grow(0.0020000000949949026).offset(-interpolateEntity.x, -interpolateEntity.y, -interpolateEntity.z), color, color2);
    }
    
    public static void drawRectBase(int n, double n2, double n3, double n4, final int n5) {
        if (n < n3) {
            final double n6 = n;
            n = (int)n3;
            n3 = (int)n6;
        }
        if (n2 < n4) {
            final double n7 = n2;
            n2 = n4;
            n4 = n7;
        }
        GlStateManager.enableBlend();
        GL11.glDisable(3042);
        GL11.glDisable(3008);
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.color((n5 >> 16 & 0xFF) / 255.0f, (n5 >> 8 & 0xFF) / 255.0f, (n5 & 0xFF) / 255.0f, (n5 >> 24 & 0xFF) / 255.0f);
        RenderUtil.bufferbuilder.begin(7, DefaultVertexFormats.POSITION);
        RenderUtil.bufferbuilder.pos((double)n, n4, 0.0).endVertex();
        RenderUtil.bufferbuilder.pos(n3, n4, 0.0).endVertex();
        RenderUtil.bufferbuilder.pos(n3, n2, 0.0).endVertex();
        RenderUtil.bufferbuilder.pos((double)n, n2, 0.0).endVertex();
        RenderUtil.tessellator.draw();
        GL11.glEnable(3042);
        GL11.glEnable(3008);
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public static void drawGradientRect(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        final float n7 = (n5 >> 24 & 0xFF) / 255.0f;
        final float n8 = (n5 >> 16 & 0xFF) / 255.0f;
        final float n9 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n10 = (n5 & 0xFF) / 255.0f;
        final float n11 = (n6 >> 24 & 0xFF) / 255.0f;
        final float n12 = (n6 >> 16 & 0xFF) / 255.0f;
        final float n13 = (n6 >> 8 & 0xFF) / 255.0f;
        final float n14 = (n6 & 0xFF) / 255.0f;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos(n + (double)n3, (double)n2, 0.0).color(n8, n9, n10, n7).endVertex();
        getBuffer.pos((double)n, (double)n2, 0.0).color(n8, n9, n10, n7).endVertex();
        getBuffer.pos((double)n, n2 + (double)n4, 0.0).color(n12, n13, n14, n11).endVertex();
        getBuffer.pos(n + (double)n3, n2 + (double)n4, 0.0).color(n12, n13, n14, n11).endVertex();
        getInstance.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
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
    
    public static void drawWaypointImage(final BlockPos blockPos, final GLUProjection.Projection projection, final Color color, final String str, final boolean b, final Color color2) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(projection.getX(), projection.getY(), 0.0);
        final String value = String.valueOf(new StringBuilder().append(str).append(": ").append(Math.round(RenderUtil.mc.player.getDistance((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ()))).append("M"));
        LuigiHack.textManager.drawString(value, -(LuigiHack.textManager.getStringWidth(value) / 2.0f), -(LuigiHack.textManager.getFontHeight() / 2.0f) + LuigiHack.textManager.getFontHeight() / 2.0f, color.getRGB(), false);
        GlStateManager.translate(-projection.getX(), -projection.getY(), 0.0);
        GlStateManager.popMatrix();
    }
    
    public static void drawSidewaysGradientRect(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        final float n7 = (n5 >> 24 & 0xFF) / 255.0f;
        final float n8 = (n5 >> 16 & 0xFF) / 255.0f;
        final float n9 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n10 = (n5 & 0xFF) / 255.0f;
        final float n11 = (n6 >> 24 & 0xFF) / 255.0f;
        final float n12 = (n6 >> 16 & 0xFF) / 255.0f;
        final float n13 = (n6 >> 8 & 0xFF) / 255.0f;
        final float n14 = (n6 & 0xFF) / 255.0f;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos((double)n3, (double)n2, 0.0).color(n8, n9, n10, n7).endVertex();
        getBuffer.pos((double)n, (double)n2, 0.0).color(n12, n13, n14, n11).endVertex();
        getBuffer.pos((double)n, (double)n4, 0.0).color(n12, n13, n14, n11).endVertex();
        getBuffer.pos((double)n3, (double)n4, 0.0).color(n8, n9, n10, n7).endVertex();
        getInstance.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }
    
    public static void drawOpenGradientBox(final BlockPos blockPos, final Color color, final Color color2, final double n) {
        for (final EnumFacing enumFacing : EnumFacing.values()) {
            if (enumFacing != EnumFacing.UP) {
                drawGradientPlane(blockPos, enumFacing, color, color2, n);
            }
        }
    }
    
    public static void drawFilledCircle(final double n, final double n2, final double n3, final Color color, final double n4) {
        Tessellator.getInstance().getBuffer().begin(5, DefaultVertexFormats.POSITION_COLOR);
    }
    
    public static void drawTricolorGradientBox(final BlockPos blockPos, final Color color, final Color color2, final Color color3) {
        for (final EnumFacing enumFacing : EnumFacing.values()) {
            if (enumFacing != EnumFacing.UP) {
                drawGradientPlane(blockPos, enumFacing, color, color2, true, false);
            }
        }
        for (final EnumFacing enumFacing2 : EnumFacing.values()) {
            if (enumFacing2 != EnumFacing.DOWN) {
                drawGradientPlane(blockPos, enumFacing2, color2, color3, true, true);
            }
        }
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
    
    public static float[][] getBipedRotations(final ModelBiped modelBiped) {
        return new float[][] { { modelBiped.bipedHead.rotateAngleX, modelBiped.bipedHead.rotateAngleY, modelBiped.bipedHead.rotateAngleZ }, { modelBiped.bipedRightArm.rotateAngleX, modelBiped.bipedRightArm.rotateAngleY, modelBiped.bipedRightArm.rotateAngleZ }, { modelBiped.bipedLeftArm.rotateAngleX, modelBiped.bipedLeftArm.rotateAngleY, modelBiped.bipedLeftArm.rotateAngleZ }, { modelBiped.bipedRightLeg.rotateAngleX, modelBiped.bipedRightLeg.rotateAngleY, modelBiped.bipedRightLeg.rotateAngleZ }, { modelBiped.bipedLeftLeg.rotateAngleX, modelBiped.bipedLeftLeg.rotateAngleY, modelBiped.bipedLeftLeg.rotateAngleZ } };
    }
    
    public static void drawBoxESP(final BlockPos blockPos, final Color color, final float n, final boolean b, final boolean b2, final int n2) {
        final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos.getX() - RenderUtil.mc.getRenderManager().viewerPosX, blockPos.getY() - RenderUtil.mc.getRenderManager().viewerPosY, blockPos.getZ() - RenderUtil.mc.getRenderManager().viewerPosZ, blockPos.getX() + 1 - RenderUtil.mc.getRenderManager().viewerPosX, blockPos.getY() + 1 - RenderUtil.mc.getRenderManager().viewerPosY, blockPos.getZ() + 1 - RenderUtil.mc.getRenderManager().viewerPosZ);
        RenderUtil.camera.setPosition(Objects.requireNonNull(RenderUtil.mc.getRenderViewEntity()).posX, RenderUtil.mc.getRenderViewEntity().posY, RenderUtil.mc.getRenderViewEntity().posZ);
        if (RenderUtil.camera.isBoundingBoxInFrustum(new AxisAlignedBB(axisAlignedBB.minX + RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB.minY + RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ + RenderUtil.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX + RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY + RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ + RenderUtil.mc.getRenderManager().viewerPosZ))) {
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.disableDepth();
            GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
            GlStateManager.disableTexture2D();
            GlStateManager.depthMask(false);
            GL11.glEnable(2848);
            GL11.glHint(3154, 4354);
            GL11.glLineWidth(n);
            final double n3 = RenderUtil.mc.player.getDistance((double)(blockPos.getX() + 0.5f), (double)(blockPos.getY() + 0.5f), (double)(blockPos.getZ() + 0.5f)) * 0.75;
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
    
    public static void drawText(final AxisAlignedBB axisAlignedBB, final String s) {
        if (axisAlignedBB == null || s == null) {
            return;
        }
        GlStateManager.pushMatrix();
        glBillboardDistanceScaled((float)axisAlignedBB.minX + 0.5f, (float)axisAlignedBB.minY + 0.5f, (float)axisAlignedBB.minZ + 0.5f, (EntityPlayer)RenderUtil.mc.player, 1.0f);
        GlStateManager.disableDepth();
        GlStateManager.translate(-(LuigiHack.textManager.getStringWidth(s) / 2.0), 0.0, 0.0);
        LuigiHack.textManager.drawStringWithShadow(s, 0.0f, 0.0f, -5592406);
        GlStateManager.popMatrix();
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
    
    public static void drawBorder(final float n, final float n2, final float n3, final float n4, final Color color) {
        drawRectCol(n - 1.0f, n2 - 1.0f, 1.0f, n4 + 2.0f, color);
        drawRectCol(n + n3, n2 - 1.0f, 1.0f, n4 + 2.0f, color);
        drawRectCol(n, n2 - 1.0f, n3, 1.0f, color);
        drawRectCol(n, n2 + n4, n3, 1.0f, color);
    }
    
    public static void drawBlockCrossedESP(final BlockPos blockPos, final Color color, final float n, final boolean b) {
        final IBlockState getBlockState = RenderUtil.mc.world.getBlockState(blockPos);
        if ((b || getBlockState.getMaterial() != Material.AIR) && RenderUtil.mc.world.getWorldBorder().contains(blockPos)) {
            assert RenderUtil.mc.renderViewEntity != null;
            final Vec3d interpolateEntity = EntityUtil.interpolateEntity(RenderUtil.mc.renderViewEntity, RenderUtil.mc.getRenderPartialTicks());
            drawBlockCrossed(getBlockState.getSelectedBoundingBox((World)RenderUtil.mc.world, blockPos).grow(0.0020000000949949026).offset(-interpolateEntity.x, -interpolateEntity.y, -interpolateEntity.z), color, n);
        }
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
    
    public static void draw3DRect(final float n, final float n2, final float n3, final float n4, final Color color, final Color color2, final float n5) {
        final float n6 = color.getAlpha() / 255.0f;
        final float n7 = color.getRed() / 255.0f;
        final float n8 = color.getGreen() / 255.0f;
        final float n9 = color.getBlue() / 255.0f;
        final float n10 = color2.getAlpha() / 255.0f;
        final float n11 = color2.getRed() / 255.0f;
        final float n12 = color2.getGreen() / 255.0f;
        final float n13 = color2.getBlue() / 255.0f;
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.glLineWidth(n5);
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
    
    public static void drawCompleteImage(final float n, final float n2, final float n3, final float n4) {
        GL11.glPushMatrix();
        GL11.glTranslatef(n, n2, 0.0f);
        GL11.glBegin(7);
        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex3f(0.0f, 0.0f, 0.0f);
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex3f(0.0f, n4, 0.0f);
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex3f(n3, n4, 0.0f);
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex3f(n3, 0.0f, 0.0f);
        GL11.glEnd();
        GL11.glPopMatrix();
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
    
    public static void blockEsp(final BlockPos blockPos, final Color color, final double n, final double n2) {
        final double n3 = blockPos.getX() - RenderUtil.mc.renderManager.renderPosX;
        final double n4 = blockPos.getY() - RenderUtil.mc.renderManager.renderPosY;
        final double n5 = blockPos.getZ() - RenderUtil.mc.renderManager.renderPosZ;
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
    
    public static void renderBorder(final int n, final int n2, final int n3, final int n4, final int n5, final Color color) {
        Gui.drawRect(n, n2, n3, n2 + n5, color.getRGB());
        Gui.drawRect(n, n2, n + n5, n4, color.getRGB());
        Gui.drawRect(n3, n2, n3 - n5, n4, color.getRGB());
        Gui.drawRect(n, n4, n3, n4 - n5, color.getRGB());
    }
    
    public static AxisAlignedBB updateToCamera(final AxisAlignedBB axisAlignedBB) {
        return new AxisAlignedBB(axisAlignedBB.minX - RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB.minY - RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ - RenderUtil.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX - RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY - RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ - RenderUtil.mc.getRenderManager().viewerPosZ);
    }
    
    public static void drawRectangleCorrectly(final int n, final int n2, final int n3, final int n4, final int n5) {
        GL11.glLineWidth(1.0f);
        Gui.drawRect(n, n2, n + n3, n2 + n4, n5);
    }
    
    public static void hexColor(final int n) {
        GL11.glColor4f((n >> 16 & 0xFF) / 255.0f, (n >> 8 & 0xFF) / 255.0f, (n & 0xFF) / 255.0f, (n >> 24 & 0xFF) / 255.0f);
    }
    
    public static void drawFilledBoxESPN(final BlockPos blockPos, final Color color) {
        drawFilledBox(new AxisAlignedBB(blockPos.getX() - RenderUtil.mc.getRenderManager().viewerPosX, blockPos.getY() - RenderUtil.mc.getRenderManager().viewerPosY, blockPos.getZ() - RenderUtil.mc.getRenderManager().viewerPosZ, blockPos.getX() + 1 - RenderUtil.mc.getRenderManager().viewerPosX, blockPos.getY() + 1 - RenderUtil.mc.getRenderManager().viewerPosY, blockPos.getZ() + 1 - RenderUtil.mc.getRenderManager().viewerPosZ), ColorUtil.toRGBA(color));
    }
    
    public static void drawChungusESP(final GLUProjection.Projection projection, final float n, final float n2, final ResourceLocation resourceLocation) {
        if (projection.getType() == GLUProjection.Projection.Type.INSIDE) {
            GlStateManager.pushMatrix();
            GlStateManager.translate(projection.getX(), projection.getY(), 0.0);
            RenderUtil.mc.getTextureManager().bindTexture(resourceLocation);
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            RenderUtil.mc.getTextureManager().bindTexture(resourceLocation);
            drawCompleteImage(0.0f, 0.0f, n, n2);
            RenderUtil.mc.getTextureManager().deleteTexture(resourceLocation);
            GlStateManager.enableBlend();
            GlStateManager.disableTexture2D();
            GlStateManager.translate(-projection.getX(), -projection.getY(), 0.0);
            GlStateManager.popMatrix();
        }
    }
    
    public static boolean isInViewFrustrum(final AxisAlignedBB axisAlignedBB) {
        final Entity getRenderViewEntity = Minecraft.getMinecraft().getRenderViewEntity();
        RenderUtil.frustrum.setPosition(Objects.requireNonNull(getRenderViewEntity).posX, getRenderViewEntity.posY, getRenderViewEntity.posZ);
        return RenderUtil.frustrum.isBoundingBoxInFrustum(axisAlignedBB);
    }
    
    public static void drawBBBox(final AxisAlignedBB axisAlignedBB, final Colour colour, final int n) {
        final AxisAlignedBB axisAlignedBB2 = new AxisAlignedBB(axisAlignedBB.minX - RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB.minY - RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ - RenderUtil.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX - RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY - RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ - RenderUtil.mc.getRenderManager().viewerPosZ);
        RenderUtil.camera.setPosition(Objects.requireNonNull(RenderUtil.mc.getRenderViewEntity()).posX, RenderUtil.mc.getRenderViewEntity().posY, RenderUtil.mc.getRenderViewEntity().posZ);
        if (RenderUtil.camera.isBoundingBoxInFrustum(new AxisAlignedBB(axisAlignedBB2.minX + RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB2.minY + RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB2.minZ + RenderUtil.mc.getRenderManager().viewerPosZ, axisAlignedBB2.maxX + RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB2.maxY + RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB2.maxZ + RenderUtil.mc.getRenderManager().viewerPosZ))) {
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.disableDepth();
            GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
            GlStateManager.disableTexture2D();
            GlStateManager.depthMask(false);
            GL11.glEnable(2848);
            GL11.glHint(3154, 4354);
            RenderGlobal.renderFilledBox(axisAlignedBB2, colour.getRed() / 255.0f, colour.getGreen() / 255.0f, colour.getBlue() / 255.0f, n / 255.0f);
            GL11.glDisable(2848);
            GlStateManager.depthMask(true);
            GlStateManager.enableDepth();
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
    }
    
    public static void drawBlockOutline(final BlockPos blockPos, final Color color, final float n, final boolean b, final double n2, final boolean b2, final boolean b3, final int a) {
        if (b2) {
            final Color color2 = new Color(color.getRed(), color.getGreen(), color.getBlue(), a);
            drawGradientBlockOutline(blockPos, b3 ? color2 : color, b3 ? color : color2, n, n2);
            return;
        }
        final IBlockState getBlockState = RenderUtil.mc.world.getBlockState(blockPos);
        if ((b || getBlockState.getMaterial() != Material.AIR) && RenderUtil.mc.world.getWorldBorder().contains(blockPos)) {
            drawBlockOutline(new AxisAlignedBB(blockPos.getX() - RenderUtil.mc.getRenderManager().viewerPosX, blockPos.getY() - RenderUtil.mc.getRenderManager().viewerPosY, blockPos.getZ() - RenderUtil.mc.getRenderManager().viewerPosZ, blockPos.getX() + 1 - RenderUtil.mc.getRenderManager().viewerPosX, blockPos.getY() + 1 - RenderUtil.mc.getRenderManager().viewerPosY + n2, blockPos.getZ() + 1 - RenderUtil.mc.getRenderManager().viewerPosZ).grow(0.0020000000949949026), color, n);
        }
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
    
    public static AxisAlignedBB interpolateAxis(final AxisAlignedBB axisAlignedBB) {
        return new AxisAlignedBB(axisAlignedBB.minX - RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB.minY - RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ - RenderUtil.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX - RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY - RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ - RenderUtil.mc.getRenderManager().viewerPosZ);
    }
    
    public static void prepareGL() {
        GL11.glBlendFunc(770, 771);
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.glLineWidth(Float.intBitsToFloat(Float.floatToIntBits(5.0675106f) ^ 0x7F22290C));
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.disableLighting();
        GlStateManager.disableCull();
        GlStateManager.enableAlpha();
        GlStateManager.color(Float.intBitsToFloat(Float.floatToIntBits(11.925059f) ^ 0x7EBECD0B), Float.intBitsToFloat(Float.floatToIntBits(18.2283f) ^ 0x7E11D38F), Float.intBitsToFloat(Float.floatToIntBits(9.73656f) ^ 0x7E9BC8F3));
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
    
    public static void drawCircleOutline(final float n, final float n2, final float n3, final int n4, final int n5, final int n6) {
        drawArcOutline(n, n2, n3, (float)n4, (float)n5, n6);
    }
    
    public static void drawGradientBoxTest(final BlockPos blockPos, final Color color, final Color color2) {
    }
    
    public static void drawArcOutline(final float n, final float n2, final float n3, final float n4, final float n5, final int n6) {
        GL11.glBegin(2);
        for (int n7 = (int)(n6 / (360.0f / n4)) + 1; n7 <= n6 / (360.0f / n5); ++n7) {
            final double n8 = 6.283185307179586 * n7 / n6;
            GL11.glVertex2d(n + Math.cos(n8) * n3, n2 + Math.sin(n8) * n3);
        }
        glEnd();
    }
    
    public static class RenderTesselator extends Tessellator
    {
        public static /* synthetic */ RenderTesselator INSTANCE;
        
        public static void begin(final int n) {
            RenderTesselator.INSTANCE.getBuffer().begin(n, DefaultVertexFormats.POSITION_COLOR);
        }
        
        public static void drawFullBox(final AxisAlignedBB axisAlignedBB, final BlockPos blockPos, final float n, final int n2, final int n3) {
            drawFullBox(axisAlignedBB, blockPos, n, n2 >>> 16 & 0xFF, n2 >>> 8 & 0xFF, n2 & 0xFF, n2 >>> 24 & 0xFF, n3);
        }
        
        static {
            RenderTesselator.INSTANCE = new RenderTesselator();
        }
        
        public static void releaseGL() {
            GlStateManager.enableCull();
            GlStateManager.depthMask(true);
            GlStateManager.enableTexture2D();
            GlStateManager.enableBlend();
            GlStateManager.enableDepth();
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
        
        public static void drawHalfBox(final BlockPos blockPos, final int n, final int n2, final int n3, final int n4, final int n5) {
            drawBox(RenderTesselator.INSTANCE.getBuffer(), (float)blockPos.getX(), (float)blockPos.getY(), (float)blockPos.getZ(), 1.0f, 0.5f, 1.0f, n, n2, n3, n4, n5);
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
        
        public RenderTesselator() {
            super(2097152);
        }
        
        public static void drawFullBox(final AxisAlignedBB axisAlignedBB, final BlockPos blockPos, final float n, final int n2, final int n3, final int n4, final int n5, final int n6) {
            prepare(7);
            drawBox(blockPos, n2, n3, n4, n5, 63);
            release();
            drawBoundingBox(axisAlignedBB, n, (float)n2, (float)n3, (float)n4, (float)n6);
        }
        
        public static void drawHalfBox(final BlockPos blockPos, final int n, final int n2) {
            drawHalfBox(blockPos, n >>> 16 & 0xFF, n >>> 8 & 0xFF, n & 0xFF, n >>> 24 & 0xFF, n2);
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
        
        public static void render() {
            RenderTesselator.INSTANCE.draw();
        }
        
        public static void release() {
            render();
            releaseGL();
        }
        
        public static void drawBox(final BlockPos blockPos, final int n, final int n2) {
            drawBox(blockPos, n >>> 16 & 0xFF, n >>> 8 & 0xFF, n & 0xFF, n >>> 24 & 0xFF, n2);
        }
        
        public static void drawBox(final BlockPos blockPos, final int n, final int n2, final int n3, final int n4, final int n5) {
            drawBox(RenderTesselator.INSTANCE.getBuffer(), (float)blockPos.getX(), (float)blockPos.getY(), (float)blockPos.getZ(), 1.0f, 1.0f, 1.0f, n, n2, n3, n4, n5);
        }
        
        public static void drawBox(final float n, final float n2, final float n3, final int n4, final int n5) {
            drawBox(RenderTesselator.INSTANCE.getBuffer(), n, n2, n3, 1.0f, 1.0f, 1.0f, n4 >>> 16 & 0xFF, n4 >>> 8 & 0xFF, n4 & 0xFF, n4 >>> 24 & 0xFF, n5);
        }
        
        public static void prepare(final int n) {
            prepareGL();
            begin(n);
        }
    }
    
    public static final class GeometryMasks
    {
        public static final /* synthetic */ HashMap<EnumFacing, Integer> FACEMAP;
        
        static {
            (FACEMAP = new HashMap<EnumFacing, Integer>()).put(EnumFacing.DOWN, 1);
            GeometryMasks.FACEMAP.put(EnumFacing.WEST, 16);
            GeometryMasks.FACEMAP.put(EnumFacing.NORTH, 4);
            GeometryMasks.FACEMAP.put(EnumFacing.SOUTH, 8);
            GeometryMasks.FACEMAP.put(EnumFacing.EAST, 32);
            GeometryMasks.FACEMAP.put(EnumFacing.UP, 2);
        }
        
        public static final class Quad
        {
            static {
                WEST = 16;
                SOUTH = 8;
                NORTH = 4;
                EAST = 32;
                DOWN = 1;
                ALL = 63;
                UP = 2;
            }
        }
        
        public static final class Line
        {
            static {
                NORTH_EAST = 36;
                UP_EAST = 34;
                DOWN_WEST = 17;
                UP_WEST = 18;
                DOWN_SOUTH = 9;
                UP_NORTH = 6;
                SOUTH_WEST = 24;
                DOWN_EAST = 33;
                NORTH_WEST = 20;
                DOWN_NORTH = 5;
                UP_SOUTH = 10;
                SOUTH_EAST = 40;
                ALL = 63;
            }
        }
    }
}
