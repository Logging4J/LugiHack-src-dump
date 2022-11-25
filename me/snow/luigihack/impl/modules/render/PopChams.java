//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.render;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.client.entity.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraft.network.play.server.*;
import net.minecraft.world.*;
import com.mojang.authlib.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.client.renderer.*;
import java.awt.*;
import net.minecraftforge.client.event.*;
import org.lwjgl.opengl.*;
import me.snow.luigihack.impl.modules.client.*;
import net.minecraft.util.math.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.client.model.*;
import net.minecraft.entity.*;

public class PopChams extends Module
{
    public /* synthetic */ Setting<Integer> rL;
    public /* synthetic */ Setting<Boolean> self;
    public /* synthetic */ Setting<Boolean> colorsync;
    /* synthetic */ ModelPlayer playerModel;
    public /* synthetic */ Setting<Integer> fadestart;
    public /* synthetic */ Setting<Integer> rF;
    public /* synthetic */ Setting<Integer> gF;
    public /* synthetic */ Setting<Integer> gL;
    /* synthetic */ Long startTime;
    public /* synthetic */ Setting<Double> fadetime;
    /* synthetic */ double alphaFill;
    public /* synthetic */ Setting<Integer> bL;
    public /* synthetic */ Setting<Integer> bF;
    public /* synthetic */ Setting<Boolean> onlyOneEsp;
    /* synthetic */ double alphaLine;
    public /* synthetic */ Setting<Integer> aL;
    public /* synthetic */ Setting<Integer> aF;
    /* synthetic */ EntityOtherPlayerMP player;
    public static /* synthetic */ PopChams INSTANCE;
    
    @SubscribeEvent
    public void onPacketReceived(final PacketEvent.Receive receive) {
        if (receive.getPacket() instanceof SPacketEntityStatus) {
            final SPacketEntityStatus sPacketEntityStatus = (SPacketEntityStatus)receive.getPacket();
            if (sPacketEntityStatus.getOpCode() == 35 && sPacketEntityStatus.getEntity((World)PopChams.mc.world) != null && ((boolean)this.self.getValue() || sPacketEntityStatus.getEntity((World)PopChams.mc.world).getEntityId() != PopChams.mc.player.getEntityId())) {
                final EntityOtherPlayerMP player = new EntityOtherPlayerMP((World)PopChams.mc.world, new GameProfile(PopChams.mc.player.getUniqueID(), ""));
                this.player = player;
                player.copyLocationAndAnglesFrom(sPacketEntityStatus.getEntity((World)PopChams.mc.world));
                this.playerModel = new ModelPlayer(0.0f, false);
                this.startTime = System.currentTimeMillis();
                this.playerModel.bipedHead.showModel = false;
                this.playerModel.bipedBody.showModel = false;
                this.playerModel.bipedLeftArmwear.showModel = false;
                this.playerModel.bipedLeftLegwear.showModel = false;
                this.playerModel.bipedRightArmwear.showModel = false;
                this.playerModel.bipedRightLegwear.showModel = false;
                this.alphaFill = (int)this.aF.getValue();
                this.alphaLine = (int)this.aL.getValue();
                if (!(boolean)this.onlyOneEsp.getValue()) {
                    final TotemPopCham totemPopCham = new TotemPopCham(this.player, this.playerModel, this.startTime, this.alphaFill, this.alphaLine);
                }
            }
        }
    }
    
    public static void renderLivingAt(final double n, final double n2, final double n3) {
        GlStateManager.translate((float)n, (float)n2, (float)n3);
    }
    
    public static float handleRotationFloat(final EntityLivingBase entityLivingBase, final float n) {
        return 0.0f;
    }
    
    public static Color newAlpha(final Color color, final int a) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), a);
    }
    
    public static float interpolateRotation(final float n, final float n2, final float n3) {
        float n4;
        for (n4 = n2 - n; n4 < -180.0f; n4 += 360.0f) {}
        while (n4 >= 180.0f) {
            n4 -= 360.0f;
        }
        return n + n3 * n4;
    }
    
    double normalize(final double n, final double n2, final double n3) {
        return (n - n2) / (n3 - n2);
    }
    
    public PopChams() {
        super("PopChams", "Renders when some1 pops", Module.Category.RENDER, true, false, false);
        this.self = (Setting<Boolean>)this.register(new Setting("SelfPop", (Object)false));
        this.colorsync = (Setting<Boolean>)this.register(new Setting("GlobalColor", (Object)false));
        this.rL = (Setting<Integer>)this.register(new Setting("OutLine-Red", (Object)255, (Object)0, (Object)255));
        this.gL = (Setting<Integer>)this.register(new Setting("OutLine-Green", (Object)0, (Object)0, (Object)255));
        this.bL = (Setting<Integer>)this.register(new Setting("OutLine-Blue", (Object)0, (Object)0, (Object)255));
        this.rF = (Setting<Integer>)this.register(new Setting("Fill-Red", (Object)255, (Object)0, (Object)255));
        this.gF = (Setting<Integer>)this.register(new Setting("Fill-Green", (Object)0, (Object)0, (Object)255));
        this.bF = (Setting<Integer>)this.register(new Setting("Fill-Blue", (Object)0, (Object)0, (Object)255));
        this.fadestart = (Setting<Integer>)this.register(new Setting("FadeStart", (Object)2000, (Object)0, (Object)3000));
        this.fadetime = (Setting<Double>)this.register(new Setting("FadeTime", (Object)2.0, (Object)0.0, (Object)2.0));
        this.aF = (Setting<Integer>)this.register(new Setting("FillAlpha", (Object)160, (Object)0, (Object)255));
        this.aL = (Setting<Integer>)this.register(new Setting("OutLineAlpha", (Object)160, (Object)0, (Object)255));
        this.onlyOneEsp = (Setting<Boolean>)this.register(new Setting("OnlyOneEsp", (Object)true));
        PopChams.INSTANCE = this;
    }
    
    public static void prepareTranslate(final EntityLivingBase entityLivingBase, final double n, final double n2, final double n3) {
        renderLivingAt(n - PopChams.mc.getRenderManager().viewerPosX, n2 - PopChams.mc.getRenderManager().viewerPosY, n3 - PopChams.mc.getRenderManager().viewerPosZ);
    }
    
    public static void prepareRotations(final EntityLivingBase entityLivingBase) {
        GlStateManager.rotate(180.0f - entityLivingBase.rotationYaw, 0.0f, 1.0f, 0.0f);
    }
    
    public static float prepareScale(final EntityLivingBase entityLivingBase, final float n) {
        GlStateManager.enableRescaleNormal();
        GlStateManager.scale(-1.0f, -1.0f, 1.0f);
        GlStateManager.scale(n + (entityLivingBase.getRenderBoundingBox().maxX - entityLivingBase.getRenderBoundingBox().minX), (double)(n * entityLivingBase.height), n + (entityLivingBase.getRenderBoundingBox().maxZ - entityLivingBase.getRenderBoundingBox().minZ));
        GlStateManager.translate(0.0f, -1.501f, 0.0f);
        return 0.0625f;
    }
    
    @SubscribeEvent
    public void onRenderWorld(final RenderWorldLastEvent renderWorldLastEvent) {
        if (this.onlyOneEsp.getValue()) {
            if (this.player == null || PopChams.mc.world == null || PopChams.mc.player == null) {
                return;
            }
            GL11.glLineWidth(1.0f);
            final Color color = this.colorsync.getValue() ? Colors.INSTANCE.getCurrentColor() : new Color((int)this.rL.getValue(), (int)this.gL.getValue(), (int)this.bL.getValue(), (int)this.aL.getValue());
            final Color color2 = new Color(color.getRed(), color.getGreen(), color.getBlue(), (int)this.aL.getValue());
            final Color color3 = this.colorsync.getValue() ? Colors.INSTANCE.getCurrentColor() : new Color((int)this.rF.getValue(), (int)this.gF.getValue(), (int)this.bF.getValue(), (int)this.aF.getValue());
            final Color color4 = new Color(color3.getRed(), color3.getGreen(), color3.getBlue(), (int)this.aF.getValue());
            int alpha = color2.getAlpha();
            int alpha2 = color4.getAlpha();
            final long n = System.currentTimeMillis() - this.startTime - ((Number)this.fadestart.getValue()).longValue();
            if (System.currentTimeMillis() - this.startTime > ((Number)this.fadestart.getValue()).longValue()) {
                final double n2 = -MathHelper.clamp(this.normalize((double)n, 0.0, ((Number)this.fadetime.getValue()).doubleValue()), 0.0, 1.0) + 1.0;
                alpha *= (int)n2;
                alpha2 *= (int)n2;
            }
            final Color alpha3 = newAlpha(color2, alpha);
            final Color alpha4 = newAlpha(color4, alpha2);
            if (this.player != null && this.playerModel != null) {
                NordTessellator.prepareGL();
                GL11.glPushAttrib(1048575);
                GL11.glEnable(2881);
                GL11.glEnable(2848);
                if (this.alphaFill > 1.0) {
                    this.alphaFill -= (double)this.fadetime.getValue();
                }
                final Color color5 = new Color(alpha4.getRed(), alpha4.getGreen(), alpha4.getBlue(), (int)this.alphaFill);
                if (this.alphaLine > 1.0) {
                    this.alphaLine -= (double)this.fadetime.getValue();
                }
                final Color color6 = new Color(alpha3.getRed(), alpha3.getGreen(), alpha3.getBlue(), (int)this.alphaLine);
                glColor(color5);
                GL11.glPolygonMode(1032, 6914);
                renderEntity((EntityLivingBase)this.player, (ModelBase)this.playerModel, this.player.limbSwing, this.player.limbSwingAmount, (float)this.player.ticksExisted, this.player.rotationYawHead, this.player.rotationPitch, 1.0f);
                glColor(color6);
                GL11.glPolygonMode(1032, 6913);
                renderEntity((EntityLivingBase)this.player, (ModelBase)this.playerModel, this.player.limbSwing, this.player.limbSwingAmount, (float)this.player.ticksExisted, this.player.rotationYawHead, this.player.rotationPitch, 1.0f);
                GL11.glPolygonMode(1032, 6914);
                GL11.glPopAttrib();
                NordTessellator.releaseGL();
            }
        }
    }
    
    public static void renderEntity(final EntityLivingBase entityLivingBase, final ModelBase modelBase, final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        if (PopChams.mc.getRenderManager() == null) {
            return;
        }
        final float getRenderPartialTicks = PopChams.mc.getRenderPartialTicks();
        final double n7 = entityLivingBase.posX - PopChams.mc.getRenderManager().viewerPosX;
        double n8 = entityLivingBase.posY - PopChams.mc.getRenderManager().viewerPosY;
        final double n9 = entityLivingBase.posZ - PopChams.mc.getRenderManager().viewerPosZ;
        GlStateManager.pushMatrix();
        if (entityLivingBase.isSneaking()) {
            n8 -= 0.125;
        }
        final float n10 = interpolateRotation(entityLivingBase.prevRotationYawHead, entityLivingBase.rotationYawHead, getRenderPartialTicks) - interpolateRotation(entityLivingBase.prevRenderYawOffset, entityLivingBase.renderYawOffset, getRenderPartialTicks);
        final float n11 = entityLivingBase.prevRotationPitch + (entityLivingBase.rotationPitch - entityLivingBase.prevRotationPitch) * getRenderPartialTicks;
        renderLivingAt(n7, n8, n9);
        final float handleRotationFloat = handleRotationFloat(entityLivingBase, getRenderPartialTicks);
        prepareRotations(entityLivingBase);
        final float prepareScale = prepareScale(entityLivingBase, n6);
        GlStateManager.enableAlpha();
        modelBase.setLivingAnimations(entityLivingBase, n, n2, getRenderPartialTicks);
        modelBase.setRotationAngles(n, n2, handleRotationFloat, entityLivingBase.rotationYaw, entityLivingBase.rotationPitch, prepareScale, (Entity)entityLivingBase);
        modelBase.render((Entity)entityLivingBase, n, n2, handleRotationFloat, entityLivingBase.rotationYaw, entityLivingBase.rotationPitch, prepareScale);
        GlStateManager.popMatrix();
    }
    
    public static void glColor(final Color color) {
        GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
    }
}
