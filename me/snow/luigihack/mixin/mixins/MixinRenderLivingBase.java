//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.mixin.mixins;

import net.minecraft.util.*;
import net.minecraft.client.model.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraftforge.common.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import me.snow.luigihack.impl.modules.render.*;
import me.snow.luigihack.impl.modules.client.*;
import java.awt.*;
import org.lwjgl.opengl.*;
import me.snow.luigihack.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.*;
import org.spongepowered.asm.mixin.*;

@Mixin({ RenderLivingBase.class })
public abstract class MixinRenderLivingBase<T extends EntityLivingBase> extends Render<T>
{
    private static final ResourceLocation glint;
    @Shadow
    protected ModelBase mainModel;
    @Shadow
    protected boolean renderMarker;
    float red;
    float green;
    float blue;
    
    public MixinRenderLivingBase(final RenderManager renderManager, final ModelBase modelBase, final float n) {
        super(renderManager);
    }
    
    @Overwrite
    public void doRender(final T t, final double n, final double n2, final double n3, final float n4, final float n5) {
        if (!MinecraftForge.EVENT_BUS.post((Event)new RenderLivingEvent.Pre((EntityLivingBase)t, (RenderLivingBase)RenderLivingBase.class.cast(this), n5, n, n2, n3))) {
            GlStateManager.pushMatrix();
            GlStateManager.disableCull();
            this.mainModel.swingProgress = this.getSwingProgress(t, n5);
            final boolean isRiding = t.isRiding() && t.getRidingEntity() != null && t.getRidingEntity().shouldRiderSit();
            this.mainModel.isRiding = isRiding;
            this.mainModel.isChild = t.isChild();
            try {
                float interpolateRotation = this.interpolateRotation(t.prevRenderYawOffset, t.renderYawOffset, n5);
                final float interpolateRotation2 = this.interpolateRotation(t.prevRotationYawHead, t.rotationYawHead, n5);
                float n6 = interpolateRotation2 - interpolateRotation;
                if (isRiding && t.getRidingEntity() instanceof EntityLivingBase) {
                    final EntityLivingBase entityLivingBase = (EntityLivingBase)t.getRidingEntity();
                    float wrapDegrees = MathHelper.wrapDegrees(interpolateRotation2 - this.interpolateRotation(entityLivingBase.prevRenderYawOffset, entityLivingBase.renderYawOffset, n5));
                    if (wrapDegrees < -85.0f) {
                        wrapDegrees = -85.0f;
                    }
                    if (wrapDegrees >= 85.0f) {
                        wrapDegrees = 85.0f;
                    }
                    interpolateRotation = interpolateRotation2 - wrapDegrees;
                    if (wrapDegrees * wrapDegrees > 2500.0f) {
                        interpolateRotation += wrapDegrees * 0.2f;
                    }
                    n6 = interpolateRotation2 - interpolateRotation;
                }
                final float n7 = t.prevRotationPitch + (t.rotationPitch - t.prevRotationPitch) * n5;
                this.renderLivingAt(t, n, n2, n3);
                final float handleRotationFloat = this.handleRotationFloat(t, n5);
                this.applyRotations(t, handleRotationFloat, interpolateRotation, n5);
                final float prepareScale = this.prepareScale(t, n5);
                float n8 = 0.0f;
                float n9 = 0.0f;
                if (!t.isRiding()) {
                    n8 = t.prevLimbSwingAmount + (t.limbSwingAmount - t.prevLimbSwingAmount) * n5;
                    n9 = t.limbSwing - t.limbSwingAmount * (1.0f - n5);
                    if (t.isChild()) {
                        n9 *= 3.0f;
                    }
                    if (n8 > 1.0f) {
                        n8 = 1.0f;
                    }
                    n6 = interpolateRotation2 - interpolateRotation;
                }
                GlStateManager.enableAlpha();
                this.mainModel.setLivingAnimations((EntityLivingBase)t, n9, n8, n5);
                this.mainModel.setRotationAngles(n9, n8, handleRotationFloat, n6, n7, prepareScale, (Entity)t);
                if (this.renderOutlines) {
                    final boolean setScoreTeamColor = this.setScoreTeamColor(t);
                    GlStateManager.enableColorMaterial();
                    GlStateManager.enableOutlineMode(this.getTeamColor((Entity)t));
                    if (!this.renderMarker) {
                        this.renderModel(t, n9, n8, handleRotationFloat, n6, n7, prepareScale);
                    }
                    if (!(t instanceof EntityPlayer) || !((EntityPlayer)t).isSpectator()) {
                        this.renderLayers(t, n9, n8, n5, handleRotationFloat, n6, n7, prepareScale);
                    }
                    GlStateManager.disableOutlineMode();
                    GlStateManager.disableColorMaterial();
                    if (setScoreTeamColor) {
                        this.unsetScoreTeamColor();
                    }
                }
                else {
                    if (Chams.getInstance().isOn() && t instanceof EntityPlayer && (boolean)Chams.getInstance().solidawa.getValue()) {
                        final Color color = Chams.getInstance().colorsync.getValue() ? Colors.INSTANCE.getCurrentColor() : new Color((int)Chams.getInstance().red.getValue(), (int)Chams.getInstance().green.getValue(), (int)Chams.getInstance().blue.getValue(), ((Float)Chams.getInstance().alpha.getValue()).intValue());
                        GlStateManager.pushMatrix();
                        GL11.glPushAttrib(1048575);
                        GL11.glDisable(3553);
                        GL11.glDisable(2896);
                        GL11.glEnable(2848);
                        GL11.glEnable(3042);
                        GL11.glBlendFunc(770, 771);
                        GL11.glDisable(2929);
                        GL11.glDepthMask(false);
                        if (LuigiHack.friendManager.isFriend(t.getName()) || t == Minecraft.getMinecraft().player) {
                            GL11.glColor4f((int)Chams.getInstance().Fred.getValue() / 255.0f, (int)Chams.getInstance().Fgreen.getValue() / 255.0f, (int)Chams.getInstance().Fblue.getValue() / 255.0f, (float)Chams.getInstance().alpha.getValue() / 255.0f);
                        }
                        else {
                            GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, (float)Chams.getInstance().alpha.getValue() / 255.0f);
                        }
                        this.renderModel(t, n9, n8, handleRotationFloat, n6, n7, prepareScale);
                        GL11.glDisable(2896);
                        GL11.glEnable(2929);
                        GL11.glDepthMask(true);
                        if (LuigiHack.friendManager.isFriend(t.getName()) || t == Minecraft.getMinecraft().player) {
                            GL11.glColor4f((int)Chams.getInstance().Fred.getValue() / 255.0f, (int)Chams.getInstance().Fgreen.getValue() / 255.0f, (int)Chams.getInstance().Fblue.getValue() / 255.0f, (float)Chams.getInstance().alpha.getValue() / 255.0f);
                        }
                        else {
                            GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, (float)Chams.getInstance().alpha.getValue() / 255.0f);
                        }
                        this.renderModel(t, n9, n8, handleRotationFloat, n6, n7, prepareScale);
                        GL11.glEnable(2896);
                        GlStateManager.popAttrib();
                        GlStateManager.popMatrix();
                    }
                    final boolean setDoRenderBrightness = this.setDoRenderBrightness(t, n5);
                    if (!(t instanceof EntityPlayer) || (Chams.getInstance().isOn() && (boolean)Chams.getInstance().wireframeawa.getValue() && (boolean)Chams.getInstance().playerModel.getValue()) || Chams.getInstance().isOff()) {
                        this.renderModel(t, n9, n8, handleRotationFloat, n6, n7, prepareScale);
                    }
                    if (setDoRenderBrightness) {
                        this.unsetBrightness();
                    }
                    GlStateManager.depthMask(true);
                    if (!(t instanceof EntityPlayer) || !((EntityPlayer)t).isSpectator()) {
                        this.renderLayers(t, n9, n8, n5, handleRotationFloat, n6, n7, prepareScale);
                    }
                    if (Chams.getInstance().isOn() && t instanceof EntityPlayer && (boolean)Chams.getInstance().wireframeawa.getValue()) {
                        final Color color2 = Chams.getInstance().colorsync.getValue() ? Colors.INSTANCE.getCurrentColor() : new Color((int)Chams.getInstance().red.getValue(), (int)Chams.getInstance().green.getValue(), (int)Chams.getInstance().blue.getValue(), ((Float)Chams.getInstance().alpha.getValue()).intValue());
                        GlStateManager.pushMatrix();
                        GL11.glPushAttrib(1048575);
                        GL11.glPolygonMode(1032, 6913);
                        GL11.glDisable(3553);
                        GL11.glDisable(2896);
                        GL11.glDisable(2929);
                        GL11.glEnable(2848);
                        GL11.glEnable(3042);
                        GL11.glBlendFunc(770, 771);
                        if (LuigiHack.friendManager.isFriend(t.getName()) || t == Minecraft.getMinecraft().player) {
                            GL11.glColor4f((int)Chams.getInstance().Fred.getValue() / 255.0f, (int)Chams.getInstance().Fgreen.getValue() / 255.0f, (int)Chams.getInstance().Fblue.getValue() / 255.0f, (float)Chams.getInstance().alpha.getValue() / 255.0f);
                        }
                        else {
                            GL11.glColor4f(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, (float)Chams.getInstance().alpha.getValue() / 255.0f);
                        }
                        GL11.glLineWidth((float)Chams.getInstance().lineWidth.getValue());
                        this.renderModel(t, n9, n8, handleRotationFloat, n6, n7, prepareScale);
                        GL11.glEnable(2896);
                        GlStateManager.popAttrib();
                        GlStateManager.popMatrix();
                    }
                }
                GlStateManager.disableRescaleNormal();
            }
            catch (Exception ex) {
                LuigiHack.LOGGER.error("Couldn't render entity", (Throwable)ex);
            }
            GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
            GlStateManager.enableTexture2D();
            GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
            GlStateManager.enableCull();
            GlStateManager.popMatrix();
            super.doRender((Entity)t, n, n2, n3, n4, n5);
            MinecraftForge.EVENT_BUS.post((Event)new RenderLivingEvent.Post((EntityLivingBase)t, (RenderLivingBase)RenderLivingBase.class.cast(this), n5, n, n2, n3));
        }
    }
    
    @Shadow
    protected abstract boolean isVisible(final EntityLivingBase p0);
    
    @Shadow
    protected abstract float getSwingProgress(final T p0, final float p1);
    
    @Shadow
    protected abstract float interpolateRotation(final float p0, final float p1, final float p2);
    
    @Shadow
    protected abstract float handleRotationFloat(final T p0, final float p1);
    
    @Shadow
    protected abstract void applyRotations(final T p0, final float p1, final float p2, final float p3);
    
    @Shadow
    public abstract float prepareScale(final T p0, final float p1);
    
    @Shadow
    protected abstract void unsetScoreTeamColor();
    
    @Shadow
    protected abstract boolean setScoreTeamColor(final T p0);
    
    @Shadow
    protected abstract void renderLivingAt(final T p0, final double p1, final double p2, final double p3);
    
    @Shadow
    protected abstract void unsetBrightness();
    
    @Shadow
    protected abstract void renderModel(final T p0, final float p1, final float p2, final float p3, final float p4, final float p5, final float p6);
    
    @Shadow
    protected abstract void renderLayers(final T p0, final float p1, final float p2, final float p3, final float p4, final float p5, final float p6, final float p7);
    
    @Shadow
    protected abstract boolean setDoRenderBrightness(final T p0, final float p1);
    
    static {
        glint = new ResourceLocation("textures/shinechams.png");
    }
}
