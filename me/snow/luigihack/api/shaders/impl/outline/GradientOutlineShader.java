//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.shaders.impl.outline;

import me.snow.luigihack.api.shaders.*;
import java.awt.*;
import org.lwjgl.opengl.*;
import java.util.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.*;

public final class GradientOutlineShader extends FramebufferShader
{
    public /* synthetic */ float time;
    
    public void setupUniforms() {
        this.setupUniform("texture");
        this.setupUniform("texelSize");
        this.setupUniform("color");
        this.setupUniform("divider");
        this.setupUniform("radius");
        this.setupUniform("maxSample");
        this.setupUniform("alpha0");
        this.setupUniform("resolution");
        this.setupUniform("time");
        this.setupUniform("moreGradient");
        this.setupUniform("Creepy");
        this.setupUniform("alpha");
        this.setupUniform("NUM_OCTAVES");
    }
    
    public GradientOutlineShader() {
        super("outlineGradient.frag");
        this.time = 0.0f;
    }
    
    public void startShader(final Color color, final float n, final float n2, final boolean b, final int n3, final float n4, final float n5, final float n6, final float n7, final int n8) {
        GL11.glPushMatrix();
        GL20.glUseProgram(this.program);
        if (this.uniformsMap == null) {
            this.uniformsMap = new HashMap();
            this.setupUniforms();
        }
        this.updateUniforms(color, n, n2, b, n3, n4, n5, n6, n7, n8);
    }
    
    public void updateUniforms(final Color color, final float n, final float n2, final boolean b, final int n3, final float n4, final float n5, final float n6, final float n7, final int n8) {
        GL20.glUniform1i(this.getUniform("texture"), 0);
        GL20.glUniform2f(this.getUniform("texelSize"), 1.0f / this.mc.displayWidth * (n * n2), 1.0f / this.mc.displayHeight * (n * n2));
        GL20.glUniform3f(this.getUniform("color"), color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f);
        GL20.glUniform1f(this.getUniform("divider"), 140.0f);
        GL20.glUniform1f(this.getUniform("radius"), n);
        GL20.glUniform1f(this.getUniform("maxSample"), 10.0f);
        GL20.glUniform1f(this.getUniform("alpha0"), b ? -1.0f : (n3 / 255.0f));
        GL20.glUniform2f(this.getUniform("resolution"), new ScaledResolution(this.mc).getScaledWidth() / n4, new ScaledResolution(this.mc).getScaledHeight() / n4);
        GL20.glUniform1f(this.getUniform("time"), this.time);
        GL20.glUniform1f(this.getUniform("moreGradient"), n5);
        GL20.glUniform1f(this.getUniform("Creepy"), n6);
        GL20.glUniform1f(this.getUniform("alpha"), n7);
        GL20.glUniform1i(this.getUniform("NUM_OCTAVES"), n8);
    }
    
    public void stopDraw(final Color color, final float n, final float n2, final boolean b, final int n3, final float n4, final float n5, final float n6, final float n7, final int n8) {
        this.mc.gameSettings.entityShadows = this.entityShadows;
        this.framebuffer.unbindFramebuffer();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        this.mc.getFramebuffer().bindFramebuffer(true);
        this.mc.entityRenderer.disableLightmap();
        RenderHelper.disableStandardItemLighting();
        this.startShader(color, n, n2, b, n3, n4, n5, n6, n7, n8);
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
    
    static {
        INSTANCE = new GradientOutlineShader();
    }
}
