//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util.cc;

import java.awt.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;
import net.minecraft.util.math.*;

public class RenderBuilderCC
{
    private /* synthetic */ double width;
    private /* synthetic */ boolean alpha;
    private /* synthetic */ boolean blend;
    private /* synthetic */ boolean cull;
    private /* synthetic */ Box box;
    private /* synthetic */ double height;
    private /* synthetic */ AxisAlignedBB axisAlignedBB;
    private /* synthetic */ boolean setup;
    private /* synthetic */ boolean texture;
    private /* synthetic */ boolean depth;
    private /* synthetic */ Color color;
    private /* synthetic */ double length;
    private /* synthetic */ boolean shade;
    
    public Box getBox() {
        return this.box;
    }
    
    public RenderBuilderCC cull(final boolean cull) {
        if (this.cull) {
            GlStateManager.disableCull();
        }
        this.cull = cull;
        return this;
    }
    
    public RenderBuilderCC height(final double height) {
        this.height = height;
        return this;
    }
    
    public RenderBuilderCC build() {
        if (this.depth) {
            GlStateManager.depthMask(true);
            GlStateManager.enableDepth();
        }
        if (this.texture) {
            GlStateManager.enableTexture2D();
        }
        if (this.blend) {
            GlStateManager.disableBlend();
        }
        if (this.cull) {
            GlStateManager.enableCull();
        }
        if (this.alpha) {
            GlStateManager.enableAlpha();
        }
        if (this.shade) {
            GlStateManager.shadeModel(7424);
        }
        if (this.setup) {
            GL11.glDisable(2848);
            GlStateManager.popMatrix();
        }
        return this;
    }
    
    public double getHeight() {
        return this.height;
    }
    
    public RenderBuilderCC depth(final boolean depth) {
        if (depth) {
            GlStateManager.disableDepth();
            GlStateManager.depthMask(false);
        }
        this.depth = depth;
        return this;
    }
    
    public RenderBuilderCC line(final float n) {
        GlStateManager.glLineWidth(n);
        return this;
    }
    
    public RenderBuilderCC setup() {
        GlStateManager.pushMatrix();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        this.setup = true;
        return this;
    }
    
    public RenderBuilderCC position(final BlockPos blockPos) {
        this.position(new AxisAlignedBB((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ(), (double)(blockPos.getX() + 1), (double)(blockPos.getY() + 1), (double)(blockPos.getZ() + 1)));
        return this;
    }
    
    public RenderBuilderCC color(final Color color) {
        this.color = color;
        return this;
    }
    
    public double getLength() {
        return this.length;
    }
    
    public RenderBuilderCC() {
        this.axisAlignedBB = new AxisAlignedBB(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        this.box = Box.FILL;
        this.color = Color.WHITE;
    }
    
    public AxisAlignedBB getAxisAlignedBB() {
        return this.axisAlignedBB;
    }
    
    public RenderBuilderCC position(final AxisAlignedBB axisAlignedBB) {
        this.axisAlignedBB = axisAlignedBB;
        return this;
    }
    
    public RenderBuilderCC length(final double length) {
        this.length = length;
        return this;
    }
    
    public RenderBuilderCC width(final double width) {
        this.width = width;
        return this;
    }
    
    public RenderBuilderCC position(final Vec3d vec3d) {
        this.position(new AxisAlignedBB(vec3d.x, vec3d.y, vec3d.z, vec3d.x + 1.0, vec3d.y + 1.0, vec3d.z + 1.0));
        return this;
    }
    
    public double getWidth() {
        return this.width;
    }
    
    public RenderBuilderCC shade(final boolean shade) {
        if (shade) {
            GlStateManager.shadeModel(7425);
        }
        this.shade = shade;
        return this;
    }
    
    public RenderBuilderCC texture() {
        GlStateManager.disableTexture2D();
        this.texture = true;
        return this;
    }
    
    public RenderBuilderCC alpha(final boolean alpha) {
        if (this.alpha) {
            GlStateManager.disableAlpha();
        }
        this.alpha = alpha;
        return this;
    }
    
    public RenderBuilderCC box(final Box box) {
        this.box = box;
        return this;
    }
    
    public RenderBuilderCC blend() {
        GlStateManager.enableBlend();
        this.blend = true;
        return this;
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public enum Box
    {
        OUTLINE, 
        FILL, 
        CLAW, 
        NONE, 
        REVERSE, 
        GLOW, 
        BOTH;
    }
}
