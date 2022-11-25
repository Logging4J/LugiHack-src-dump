//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.shaders.impl.outline;

import me.snow.luigihack.api.shaders.*;
import java.awt.*;
import org.lwjgl.opengl.*;
import java.util.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.*;

public final class AstralOutlineShader extends FramebufferShader
{
    public /* synthetic */ float time;
    
    static {
        INSTANCE = new AstralOutlineShader();
    }
    
    public void startShader(final Color color, final float n, final float n2, final boolean b, final int n3, final float n4, final float n5, final float n6, final float n7, final float n8, final int n9, final float n10, final float n11, final float n12, final float n13, final float n14, final float n15, final float n16, final float n17, final int n18) {
        GL11.glPushMatrix();
        GL20.glUseProgram(this.program);
        if (this.uniformsMap == null) {
            this.uniformsMap = new HashMap();
            this.setupUniforms();
        }
        this.updateUniforms(color, n, n2, b, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16, n17, n18);
    }
    
    public void updateUniforms(final Color color, final float n, final float n2, final boolean b, final int n3, final float n4, final float n5, final float n6, final float n7, final float n8, final int n9, final float n10, final float n11, final float n12, final float n13, final float n14, final float n15, final float n16, final float n17, final int n18) {
        GL20.glUniform1i(this.getUniform("texture"), 0);
        GL20.glUniform2f(this.getUniform("texelSize"), 1.0f / this.mc.displayWidth * (n * n2), 1.0f / this.mc.displayHeight * (n * n2));
        GL20.glUniform1f(this.getUniform("divider"), 140.0f);
        GL20.glUniform1f(this.getUniform("radius"), n);
        GL20.glUniform1f(this.getUniform("maxSample"), 10.0f);
        GL20.glUniform1f(this.getUniform("alpha0"), b ? -1.0f : (n3 / 255.0f));
        GL20.glUniform2f(this.getUniform("resolution"), new ScaledResolution(this.mc).getScaledWidth() / n4, new ScaledResolution(this.mc).getScaledHeight() / n4);
        GL20.glUniform1f(this.getUniform("time"), this.time);
        GL20.glUniform4f(this.getUniform("color"), n5, n6, n7, n8);
        GL20.glUniform1i(this.getUniform("iterations"), n9);
        GL20.glUniform1f(this.getUniform("formuparam2"), n10);
        GL20.glUniform1i(this.getUniform("volsteps"), (int)n12);
        GL20.glUniform1f(this.getUniform("stepsize"), n13);
        GL20.glUniform1f(this.getUniform("zoom"), n11);
        GL20.glUniform1f(this.getUniform("tile"), n14);
        GL20.glUniform1f(this.getUniform("distfading"), n15);
        GL20.glUniform1f(this.getUniform("saturation"), n16);
        GL20.glUniform1i(this.getUniform("fadeBol"), n18);
    }
    
    public AstralOutlineShader() {
        super("astralOutline.frag");
        this.time = 0.0f;
    }
    
    public void stopDraw(final Color color, final float n, final float n2, final boolean b, final int n3, final float n4, final float n5, final float n6, final float n7, final float n8, final int n9, final float n10, final float n11, final float n12, final float n13, final float n14, final float n15, final float n16, final float n17, final int n18) {
        this.mc.gameSettings.entityShadows = this.entityShadows;
        this.framebuffer.unbindFramebuffer();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        this.mc.getFramebuffer().bindFramebuffer(true);
        this.mc.entityRenderer.disableLightmap();
        RenderHelper.disableStandardItemLighting();
        this.startShader(color, n, n2, b, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16, n17, n18);
        this.mc.entityRenderer.setupOverlayRendering();
        this.drawFramebuffer(this.framebuffer);
        this.stopShader();
        this.mc.entityRenderer.disableLightmap();
        GlStateManager.popMatrix();
        GlStateManager.popAttrib();
    }
    
    public void setupUniforms() {
        this.setupUniform("texture");
        this.setupUniform("texelSize");
        this.setupUniform("color");
        this.setupUniform("divider");
        this.setupUniform("radius");
        this.setupUniform("maxSample");
        this.setupUniform("alpha0");
        this.setupUniform("time");
        this.setupUniform("iterations");
        this.setupUniform("formuparam2");
        this.setupUniform("stepsize");
        this.setupUniform("volsteps");
        this.setupUniform("zoom");
        this.setupUniform("tile");
        this.setupUniform("distfading");
        this.setupUniform("saturation");
        this.setupUniform("fadeBol");
        this.setupUniform("resolution");
    }
    
    public void update(final double n) {
        this.time += (float)n;
    }
}
