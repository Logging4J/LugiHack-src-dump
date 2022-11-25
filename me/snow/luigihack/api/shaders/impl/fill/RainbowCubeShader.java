//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.shaders.impl.fill;

import me.snow.luigihack.api.shaders.*;
import java.awt.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.gui.*;
import org.lwjgl.opengl.*;
import java.util.*;

public class RainbowCubeShader extends FramebufferShader
{
    public /* synthetic */ float time;
    
    static {
        INSTANCE = new RainbowCubeShader();
    }
    
    public void stopDraw(final Color color, final float radius, final float quality, final float n, final Color color2, final int n2, final int n3, final int n4, final int n5) {
        this.mc.gameSettings.entityShadows = this.entityShadows;
        this.framebuffer.unbindFramebuffer();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        this.mc.getFramebuffer().bindFramebuffer(true);
        this.red = color.getRed() / 255.0f;
        this.green = color.getGreen() / 255.0f;
        this.blue = color.getBlue() / 255.0f;
        this.alpha = color.getAlpha() / 255.0f;
        this.radius = radius;
        this.quality = quality;
        this.mc.entityRenderer.disableLightmap();
        RenderHelper.disableStandardItemLighting();
        this.startShader(n, color2, n2, n3, n4, n5);
        this.mc.entityRenderer.setupOverlayRendering();
        this.drawFramebuffer(this.framebuffer);
        this.stopShader();
        this.mc.entityRenderer.disableLightmap();
        GlStateManager.popMatrix();
        GlStateManager.popAttrib();
    }
    
    public RainbowCubeShader() {
        super("rainbowCube.frag");
    }
    
    public void setupUniforms() {
        this.setupUniform("resolution");
        this.setupUniform("time");
        this.setupUniform("alpha");
        this.setupUniform("WAVELENGTH");
        this.setupUniform("R");
        this.setupUniform("G");
        this.setupUniform("B");
        this.setupUniform("RSTART");
        this.setupUniform("GSTART");
        this.setupUniform("BSTART");
    }
    
    public void updateUniforms(final float n, final Color color, final int n2, final int n3, final int n4, final int n5) {
        GL20.glUniform2f(this.getUniform("resolution"), new ScaledResolution(this.mc).getScaledWidth() / n, new ScaledResolution(this.mc).getScaledHeight() / n);
        GL20.glUniform1f(this.getUniform("time"), this.time);
        GL20.glUniform1f(this.getUniform("alpha"), color.getAlpha() / 255.0f);
        GL20.glUniform1f(this.getUniform("WAVELENGTH"), (float)n2);
        GL20.glUniform1i(this.getUniform("R"), color.getRed());
        GL20.glUniform1i(this.getUniform("G"), color.getGreen());
        GL20.glUniform1i(this.getUniform("B"), color.getBlue());
        GL20.glUniform1i(this.getUniform("RSTART"), n3);
        GL20.glUniform1i(this.getUniform("GSTART"), n4);
        GL20.glUniform1i(this.getUniform("BSTART"), n5);
    }
    
    public void update(final double n) {
        this.time += (float)n;
    }
    
    public void startShader(final float n, final Color color, final int n2, final int n3, final int n4, final int n5) {
        GL11.glPushMatrix();
        GL20.glUseProgram(this.program);
        if (this.uniformsMap == null) {
            this.uniformsMap = new HashMap();
            this.setupUniforms();
        }
        this.updateUniforms(n, color, n2, n3, n4, n5);
    }
}
