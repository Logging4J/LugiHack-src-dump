//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util.gl;

import java.awt.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;
import net.minecraft.util.math.*;

public class RenderBuilder2
{
    private /* synthetic */ boolean alpha;
    private /* synthetic */ Color color;
    private /* synthetic */ double length;
    private /* synthetic */ AxisAlignedBB axisAlignedBB;
    private /* synthetic */ boolean blend;
    private /* synthetic */ boolean shade;
    private /* synthetic */ Box box;
    private /* synthetic */ double height;
    private /* synthetic */ boolean texture;
    private /* synthetic */ boolean depth;
    private /* synthetic */ boolean cull;
    private /* synthetic */ boolean setup;
    private /* synthetic */ double width;
    
    public AxisAlignedBB getAxisAlignedBB() {
        return this.axisAlignedBB;
    }
    
    public RenderBuilder2 width(final double width) {
        this.width = width;
        return this;
    }
    
    public double getWidth() {
        return this.width;
    }
    
    public RenderBuilder2() {
        this.axisAlignedBB = new AxisAlignedBB(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        this.box = Box.FILL;
        this.color = Color.WHITE;
    }
    
    public double getLength() {
        return this.length;
    }
    
    public RenderBuilder2 setup() {
        GlStateManager.pushMatrix();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        this.setup = true;
        return this;
    }
    
    public double getHeight() {
        return this.height;
    }
    
    public RenderBuilder2 position(final Vec3d vec3d) {
        this.position(new AxisAlignedBB(vec3d.x, vec3d.y, vec3d.z, vec3d.x + 1.0, vec3d.y + 1.0, vec3d.z + 1.0));
        return this;
    }
    
    public RenderBuilder2 shade(final boolean shade) {
        if (shade) {
            GlStateManager.shadeModel(7425);
        }
        this.shade = shade;
        return this;
    }
    
    public RenderBuilder2 cull(final boolean cull) {
        if (this.cull) {
            GlStateManager.disableCull();
        }
        this.cull = cull;
        return this;
    }
    
    public RenderBuilder2 length(final double length) {
        this.length = length;
        return this;
    }
    
    public RenderBuilder2 box(final Box box) {
        this.box = box;
        return this;
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public RenderBuilder2 build() {
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
    
    public RenderBuilder2 line(final float n) {
        GlStateManager.glLineWidth(n);
        return this;
    }
    
    public RenderBuilder2 position(final BlockPos blockPos) {
        this.position(new AxisAlignedBB((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ(), (double)(blockPos.getX() + 1), (double)(blockPos.getY() + 1), (double)(blockPos.getZ() + 1)));
        return this;
    }
    
    public RenderBuilder2 position(final AxisAlignedBB axisAlignedBB) {
        this.axisAlignedBB = axisAlignedBB;
        return this;
    }
    
    public RenderBuilder2 color(final Color color) {
        this.color = color;
        return this;
    }
    
    public RenderBuilder2 texture() {
        GlStateManager.disableTexture2D();
        this.texture = true;
        return this;
    }
    
    public RenderBuilder2 alpha(final boolean alpha) {
        if (this.alpha) {
            GlStateManager.disableAlpha();
        }
        this.alpha = alpha;
        return this;
    }
    
    public RenderBuilder2 depth(final boolean depth) {
        if (depth) {
            GlStateManager.disableDepth();
            GlStateManager.depthMask(false);
        }
        this.depth = depth;
        return this;
    }
    
    public RenderBuilder2 height(final double height) {
        this.height = height;
        return this;
    }
    
    public RenderBuilder2 blend() {
        GlStateManager.enableBlend();
        this.blend = true;
        return this;
    }
    
    public Box getBox() {
        return this.box;
    }
    
    public enum Box
    {
        OUTLINE, 
        GLOW, 
        CLAW, 
        FILL, 
        BOTH, 
        NONE, 
        REVERSE;
    }
}
