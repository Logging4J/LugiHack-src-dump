//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.shaders.impl.outline;

import me.snow.luigihack.api.shaders.*;
import java.awt.*;
import org.lwjgl.opengl.*;
import java.util.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.gui.*;

public final class SmokeOutlineShader extends FramebufferShader
{
    public /* synthetic */ float time;
    
    public SmokeOutlineShader() {
        super("smokeOutline.frag");
        this.time = 0.0f;
    }
    
    public void startShader(final Color color, final float n, final float n2, final boolean b, final int n3, final float n4, final Color color2, final Color color3, final int n5) {
        GL11.glPushMatrix();
        GL20.glUseProgram(this.program);
        if (this.uniformsMap == null) {
            this.uniformsMap = new HashMap();
            this.setupUniforms();
        }
        this.updateUniforms(color, n, n2, b, n3, n4, color2, color3, n5);
    }
    
    public void setupUniforms() {
        this.setupUniform("texture");
        this.setupUniform("texelSize");
        this.setupUniform("divider");
        this.setupUniform("radius");
        this.setupUniform("maxSample");
        this.setupUniform("alpha0");
        this.setupUniform("resolution");
        this.setupUniform("time");
        this.setupUniform("first");
        this.setupUniform("second");
        this.setupUniform("third");
        this.setupUniform("oct");
    }
    
    public void update(final double n) {
        this.time += (float)n;
    }
    
    public void stopDraw(final Color color, final float n, final float n2, final boolean b, final int n3, final float n4, final Color color2, final Color color3, final int n5) {
        this.mc.gameSettings.entityShadows = this.entityShadows;
        this.framebuffer.unbindFramebuffer();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        this.mc.getFramebuffer().bindFramebuffer(true);
        this.mc.entityRenderer.disableLightmap();
        RenderHelper.disableStandardItemLighting();
        this.startShader(color, n, n2, b, n3, n4, color2, color3, n5);
        this.mc.entityRenderer.setupOverlayRendering();
        this.drawFramebuffer(this.framebuffer);
        this.stopShader();
        this.mc.entityRenderer.disableLightmap();
        GlStateManager.popMatrix();
        GlStateManager.popAttrib();
    }
    
    public void updateUniforms(final Color color, final float n, final float n2, final boolean b, final int n3, final float n4, final Color color2, final Color color3, final int n5) {
        GL20.glUniform1i(this.getUniform("texture"), 0);
        GL20.glUniform2f(this.getUniform("texelSize"), 1.0f / this.mc.displayWidth * (n * n2), 1.0f / this.mc.displayHeight * (n * n2));
        GL20.glUniform1f(this.getUniform("divider"), 140.0f);
        GL20.glUniform1f(this.getUniform("radius"), n);
        GL20.glUniform1f(this.getUniform("maxSample"), 10.0f);
        GL20.glUniform1f(this.getUniform("alpha0"), b ? -1.0f : (n3 / 255.0f));
        GL20.glUniform2f(this.getUniform("resolution"), new ScaledResolution(this.mc).getScaledWidth() / n4, new ScaledResolution(this.mc).getScaledHeight() / n4);
        GL20.glUniform1f(this.getUniform("time"), this.time);
        GL20.glUniform4f(this.getUniform("first"), color.getRed() / 255.0f * 5.0f, color.getGreen() / 255.0f * 5.0f, color.getBlue() / 255.0f * 5.0f, color.getAlpha() / 255.0f);
        GL20.glUniform3f(this.getUniform("second"), color2.getRed() / 255.0f * 5.0f, color2.getGreen() / 255.0f * 5.0f, color2.getBlue() / 255.0f * 5.0f);
        GL20.glUniform3f(this.getUniform("third"), color3.getRed() / 255.0f * 5.0f, color3.getGreen() / 255.0f * 5.0f, color3.getBlue() / 255.0f * 5.0f);
        GL20.glUniform1i(this.getUniform("oct"), n5);
    }
    
    static {
        INSTANCE = new SmokeOutlineShader();
    }
}
