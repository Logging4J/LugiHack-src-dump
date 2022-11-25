//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.shaders.impl.fill;

import me.snow.luigihack.api.shaders.*;
import net.minecraft.client.gui.*;
import java.awt.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import java.util.*;

public class FlowShader extends FramebufferShader
{
    public /* synthetic */ float time;
    
    public FlowShader() {
        super("flow.frag");
    }
    
    public void updateUniforms(final float n, final float n2, final float n3, final float n4, final float n5, final int n6, final float n7, final float n8, final float n9, final float n10, final float n11, final float n12, final float n13, final float n14, final int n15) {
        GL20.glUniform2f(this.getUniform("resolution"), new ScaledResolution(this.mc).getScaledWidth() / n, new ScaledResolution(this.mc).getScaledHeight() / n);
        GL20.glUniform1f(this.getUniform("time"), this.time);
        GL20.glUniform4f(this.getUniform("color"), n2, n3, n4, n5);
        GL20.glUniform1i(this.getUniform("iterations"), n6);
        GL20.glUniform1f(this.getUniform("formuparam2"), n7);
        GL20.glUniform1i(this.getUniform("volsteps"), (int)n9);
        GL20.glUniform1f(this.getUniform("stepsize"), n10);
        GL20.glUniform1f(this.getUniform("zoom"), n8);
        GL20.glUniform1f(this.getUniform("tile"), n11);
        GL20.glUniform1f(this.getUniform("distfading"), n12);
        GL20.glUniform1f(this.getUniform("saturation"), n13);
        GL20.glUniform1i(this.getUniform("fadeBol"), n15);
    }
    
    public void update(final double n) {
        this.time += (float)n;
    }
    
    public void stopDraw(final Color color, final float radius, final float quality, final float n, final float n2, final float n3, final float n4, final float n5, final int n6, final float n7, final float n8, final float n9, final float n10, final float n11, final float n12, final float n13, final float n14, final int n15) {
        this.mc.gameSettings.entityShadows = this.entityShadows;
        this.framebuffer.unbindFramebuffer();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        this.mc.getFramebuffer().bindFramebuffer(true);
        this.radius = radius;
        this.quality = quality;
        this.mc.entityRenderer.disableLightmap();
        RenderHelper.disableStandardItemLighting();
        this.startShader(n, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15);
        this.mc.entityRenderer.setupOverlayRendering();
        this.drawFramebuffer(this.framebuffer);
        this.stopShader();
        this.mc.entityRenderer.disableLightmap();
        GlStateManager.popMatrix();
        GlStateManager.popAttrib();
    }
    
    static {
        INSTANCE = new FlowShader();
    }
    
    public void startShader(final float n, final float n2, final float n3, final float n4, final float n5, final int n6, final float n7, final float n8, final float n9, final float n10, final float n11, final float n12, final float n13, final float n14, final int n15) {
        GL11.glPushMatrix();
        GL20.glUseProgram(this.program);
        if (this.uniformsMap == null) {
            this.uniformsMap = new HashMap();
            this.setupUniforms();
        }
        this.updateUniforms(n, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15);
    }
    
    public void setupUniforms() {
        this.setupUniform("resolution");
        this.setupUniform("time");
        this.setupUniform("color");
        this.setupUniform("iterations");
        this.setupUniform("formuparam2");
        this.setupUniform("stepsize");
        this.setupUniform("volsteps");
        this.setupUniform("zoom");
        this.setupUniform("tile");
        this.setupUniform("distfading");
        this.setupUniform("saturation");
        this.setupUniform("fadeBol");
    }
}
