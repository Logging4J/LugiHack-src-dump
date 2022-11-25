//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.shaders.impl.fill;

import me.snow.luigihack.api.shaders.*;
import org.lwjgl.opengl.*;
import java.util.*;
import java.awt.*;
import net.minecraft.client.renderer.*;

public class FillShader extends FramebufferShader
{
    public /* synthetic */ float time;
    
    public void startShader(final float n, final float n2, final float n3, final float n4) {
        GL11.glPushMatrix();
        GL20.glUseProgram(this.program);
        if (this.uniformsMap == null) {
            this.uniformsMap = new HashMap();
            this.setupUniforms();
        }
        this.updateUniforms(n, n2, n3, n4);
    }
    
    static {
        INSTANCE = new FillShader();
    }
    
    public FillShader() {
        super("fill.frag");
    }
    
    public void setupUniforms() {
        this.setupUniform("color");
    }
    
    public void stopDraw(final Color color) {
        this.mc.gameSettings.entityShadows = this.entityShadows;
        this.framebuffer.unbindFramebuffer();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        this.mc.getFramebuffer().bindFramebuffer(true);
        this.mc.entityRenderer.disableLightmap();
        RenderHelper.disableStandardItemLighting();
        this.startShader(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
        this.mc.entityRenderer.setupOverlayRendering();
        this.drawFramebuffer(this.framebuffer);
        this.stopShader();
        this.mc.entityRenderer.disableLightmap();
        GlStateManager.popMatrix();
        GlStateManager.popAttrib();
    }
    
    public void update(final double n) {
        this.time += (float)n;
    }
    
    public void updateUniforms(final float n, final float n2, final float n3, final float n4) {
        GL20.glUniform4f(this.getUniform("color"), n, n2, n3, n4);
    }
}
