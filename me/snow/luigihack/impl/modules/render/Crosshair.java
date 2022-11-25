//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.render;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.client.renderer.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.client.gui.*;
import me.snow.luigihack.impl.modules.client.*;
import me.snow.luigihack.api.util.*;
import me.snow.luigihack.api.event.events.*;

public class Crosshair extends Module
{
    private final /* synthetic */ Setting<Integer> cgreen;
    private final /* synthetic */ Setting<Integer> calpha;
    /* synthetic */ long lastUpdate;
    private final /* synthetic */ Setting<Float> motionWidth;
    private final /* synthetic */ Setting<Integer> cred;
    private final /* synthetic */ Setting<Float> motionSize;
    private final /* synthetic */ Setting<Float> crosshairGap;
    private final /* synthetic */ Setting<Boolean> dot;
    private final /* synthetic */ Setting<Float> motionGap;
    /* synthetic */ float currentMotion;
    /* synthetic */ float prevMotion;
    private final /* synthetic */ Setting<Integer> cblue;
    private final /* synthetic */ Setting<Float> crosshairWidth;
    private final /* synthetic */ Setting<Boolean> colorSync;
    private final /* synthetic */ Setting<Float> crosshairSize;
    
    public static float lerp(final float n, final float n2, final float n3) {
        return n * (1.0f - n3) + n2 * n3;
    }
    
    public static void drawRect(float n, float n2, float n3, float n4, final int n5) {
        if (n < n3) {
            final float n6 = n;
            n = n3;
            n3 = n6;
        }
        if (n2 < n4) {
            final float n7 = n2;
            n2 = n4;
            n4 = n7;
        }
        final float n8 = (n5 >> 24 & 0xFF) / 255.0f;
        final float n9 = (n5 >> 16 & 0xFF) / 255.0f;
        final float n10 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n11 = (n5 & 0xFF) / 255.0f;
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.color(n9, n10, n11, n8);
        getBuffer.begin(7, DefaultVertexFormats.POSITION);
        getBuffer.pos((double)n, (double)n4, 0.0).endVertex();
        getBuffer.pos((double)n3, (double)n4, 0.0).endVertex();
        getBuffer.pos((double)n3, (double)n2, 0.0).endVertex();
        getBuffer.pos((double)n, (double)n2, 0.0).endVertex();
        getInstance.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public Crosshair() {
        super("Crosshair", "Draws a custom crosshair", Module.Category.RENDER, true, false, false);
        this.dot = (Setting<Boolean>)this.register(new Setting("Dot", (Object)false));
        this.crosshairGap = (Setting<Float>)this.register(new Setting("Gap", (Object)2.0f, (Object)0.0f, (Object)10.0f));
        this.motionGap = (Setting<Float>)this.register(new Setting("MotionGap", (Object)0.0f, (Object)0.0f, (Object)5.0f));
        this.crosshairWidth = (Setting<Float>)this.register(new Setting("Width", (Object)1.0f, (Object)0.1f, (Object)5.0f));
        this.motionWidth = (Setting<Float>)this.register(new Setting("MotionWidth", (Object)0.0f, (Object)0.0f, (Object)2.5f));
        this.crosshairSize = (Setting<Float>)this.register(new Setting("Size", (Object)2.0f, (Object)0.1f, (Object)40.0f));
        this.motionSize = (Setting<Float>)this.register(new Setting("MotionSize", (Object)0.0f, (Object)0.0f, (Object)20.0f));
        this.colorSync = (Setting<Boolean>)this.register(new Setting("Sync", (Object)false));
        this.cred = (Setting<Integer>)this.register(new Setting("Red", (Object)0, (Object)0, (Object)255));
        this.cgreen = (Setting<Integer>)this.register(new Setting("Green", (Object)255, (Object)0, (Object)255));
        this.cblue = (Setting<Integer>)this.register(new Setting("Blue", (Object)0, (Object)0, (Object)255));
        this.calpha = (Setting<Integer>)this.register(new Setting("Alpha", (Object)255, (Object)0, (Object)255));
        this.currentMotion = 0.0f;
        this.lastUpdate = -1L;
        this.prevMotion = 0.0f;
    }
    
    @SubscribeEvent
    public void onRenderGameOverlay(final RenderGameOverlayEvent renderGameOverlayEvent) {
        if (this.megaNullCheck()) {
            return;
        }
        if (renderGameOverlayEvent.getType() == RenderGameOverlayEvent.ElementType.CROSSHAIRS) {
            renderGameOverlayEvent.setCanceled(true);
        }
    }
    
    public void onRender2D(final Render2DEvent render2DEvent) {
        final ScaledResolution scaledResolution = new ScaledResolution(Crosshair.mc);
        final float n = (float)(scaledResolution.getScaledWidth_double() / 2.0 + 0.5);
        final float n2 = (float)(scaledResolution.getScaledHeight_double() / 2.0 + 0.5);
        final float floatValue = (float)this.crosshairGap.getValue();
        final float max = Math.max((float)this.crosshairWidth.getValue(), 0.5f);
        final float floatValue2 = (float)this.crosshairSize.getValue();
        final float tickLength = Crosshair.mc.timer.tickLength;
        final float n3;
        final float n4;
        final float n5;
        drawRect(n - (n3 = floatValue + lerp(this.prevMotion, this.currentMotion, Math.min((System.currentTimeMillis() - this.lastUpdate) / tickLength, 1.0f)) * (float)this.motionGap.getValue()) - (n4 = floatValue2 + lerp(this.prevMotion, this.currentMotion, Math.min((System.currentTimeMillis() - this.lastUpdate) / tickLength, 1.0f)) * (float)this.motionSize.getValue()), n2 - (n5 = max + lerp(this.prevMotion, this.currentMotion, Math.min((System.currentTimeMillis() - this.lastUpdate) / tickLength, 1.0f)) * (float)this.motionWidth.getValue()) / 2.0f, n - n3, n2 + n5 / 2.0f, ((boolean)this.colorSync.getValue()) ? Colors.INSTANCE.getCurrentColorHex() : ColorUtil.toRGBA((int)this.cred.getValue(), (int)this.cgreen.getValue(), (int)this.cblue.getValue(), (int)this.calpha.getValue()));
        drawRect(n + n3 + n4, n2 - n5 / 2.0f, n + n3, n2 + n5 / 2.0f, ((boolean)this.colorSync.getValue()) ? Colors.INSTANCE.getCurrentColorHex() : ColorUtil.toRGBA((int)this.cred.getValue(), (int)this.cgreen.getValue(), (int)this.cblue.getValue(), (int)this.calpha.getValue()));
        drawRect(n - n5 / 2.0f, n2 + n3 + n4, n + n5 / 2.0f, n2 + n3, ((boolean)this.colorSync.getValue()) ? Colors.INSTANCE.getCurrentColorHex() : ColorUtil.toRGBA((int)this.cred.getValue(), (int)this.cgreen.getValue(), (int)this.cblue.getValue(), (int)this.calpha.getValue()));
        drawRect(n - n5 / 2.0f, n2 - n3 - n4, n + n5 / 2.0f, n2 - n3, ((boolean)this.colorSync.getValue()) ? Colors.INSTANCE.getCurrentColorHex() : ColorUtil.toRGBA((int)this.cred.getValue(), (int)this.cgreen.getValue(), (int)this.cblue.getValue(), (int)this.calpha.getValue()));
        if (this.dot.getValue()) {
            drawRect(n - n5 / 2.0f, n2 - n5 / 2.0f, n + n5 / 2.0f, n2 + n5 / 2.0f, ((boolean)this.colorSync.getValue()) ? Colors.INSTANCE.getCurrentColorHex() : ColorUtil.toRGBA((int)this.cred.getValue(), (int)this.cgreen.getValue(), (int)this.cblue.getValue(), (int)this.calpha.getValue()));
        }
    }
    
    @SubscribeEvent
    public void onUpdateWalkingPlayer(final UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        this.prevMotion = this.currentMotion;
        final double n = Crosshair.mc.player.posX - Crosshair.mc.player.prevPosX;
        final double n2 = Crosshair.mc.player.posZ - Crosshair.mc.player.prevPosZ;
        this.currentMotion = (float)Math.sqrt(n * n + n2 * n2);
        this.lastUpdate = System.currentTimeMillis();
    }
}
