//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.render;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.api.event.events.*;
import me.snow.luigihack.impl.modules.client.*;
import java.awt.*;
import net.minecraft.entity.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.entity.item.*;
import java.util.*;
import net.minecraft.util.math.*;

public class ESP extends Module
{
    private final /* synthetic */ Setting<Integer> alpha;
    private final /* synthetic */ Setting<Integer> red;
    private final /* synthetic */ Setting<Boolean> xpbottles;
    private static /* synthetic */ ESP INSTANCE;
    private final /* synthetic */ Setting<Boolean> colorsync;
    private final /* synthetic */ Setting<Boolean> items;
    private final /* synthetic */ Setting<Integer> green;
    private final /* synthetic */ Setting<Integer> boxAlpha;
    private final /* synthetic */ Setting<Boolean> pearl;
    private final /* synthetic */ Setting<Boolean> xporbs;
    private final /* synthetic */ Setting<Integer> blue;
    
    private void setInstance() {
        ESP.INSTANCE = this;
    }
    
    static {
        ESP.INSTANCE = new ESP();
    }
    
    public void onRender3D(final Render3DEvent render3DEvent) {
        final Color color = this.colorsync.getValue() ? Colors.INSTANCE.getCurrentColor() : new Color((int)this.red.getValue(), (int)this.green.getValue(), (int)this.blue.getValue(), (int)this.alpha.getValue());
        if (this.items.getValue()) {
            int n = 0;
            for (final Entity entity : ESP.mc.world.loadedEntityList) {
                if (entity instanceof EntityItem) {
                    if (ESP.mc.player.getDistanceSq(entity) >= 2500.0) {
                        continue;
                    }
                    final Vec3d interpolatedRenderPos = EntityUtil.getInterpolatedRenderPos(entity, ESP.mc.getRenderPartialTicks());
                    final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(entity.getEntityBoundingBox().minX - 0.05 - entity.posX + interpolatedRenderPos.x, entity.getEntityBoundingBox().minY - 0.0 - entity.posY + interpolatedRenderPos.y, entity.getEntityBoundingBox().minZ - 0.05 - entity.posZ + interpolatedRenderPos.z, entity.getEntityBoundingBox().maxX + 0.05 - entity.posX + interpolatedRenderPos.x, entity.getEntityBoundingBox().maxY + 0.1 - entity.posY + interpolatedRenderPos.y, entity.getEntityBoundingBox().maxZ + 0.05 - entity.posZ + interpolatedRenderPos.z);
                    GlStateManager.pushMatrix();
                    GlStateManager.enableBlend();
                    GlStateManager.disableDepth();
                    GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
                    GlStateManager.disableTexture2D();
                    GlStateManager.depthMask(false);
                    GL11.glEnable(2848);
                    GL11.glHint(3154, 4354);
                    GL11.glLineWidth(1.0f);
                    RenderGlobal.renderFilledBox(axisAlignedBB, color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, (int)this.boxAlpha.getValue() / 255.0f);
                    GL11.glDisable(2848);
                    GlStateManager.depthMask(true);
                    GlStateManager.enableDepth();
                    GlStateManager.enableTexture2D();
                    GlStateManager.disableBlend();
                    GlStateManager.popMatrix();
                    RenderUtil.drawBlockOutline(axisAlignedBB, new Color(color.getRed(), color.getGreen(), color.getBlue(), (int)this.alpha.getValue()), 1.0f);
                    if (++n < 50) {
                        continue;
                    }
                    break;
                }
            }
        }
        if (this.xporbs.getValue()) {
            int n2 = 0;
            for (final Entity entity2 : ESP.mc.world.loadedEntityList) {
                if (entity2 instanceof EntityXPOrb) {
                    if (ESP.mc.player.getDistanceSq(entity2) >= 2500.0) {
                        continue;
                    }
                    final Vec3d interpolatedRenderPos2 = EntityUtil.getInterpolatedRenderPos(entity2, ESP.mc.getRenderPartialTicks());
                    final AxisAlignedBB axisAlignedBB2 = new AxisAlignedBB(entity2.getEntityBoundingBox().minX - 0.05 - entity2.posX + interpolatedRenderPos2.x, entity2.getEntityBoundingBox().minY - 0.0 - entity2.posY + interpolatedRenderPos2.y, entity2.getEntityBoundingBox().minZ - 0.05 - entity2.posZ + interpolatedRenderPos2.z, entity2.getEntityBoundingBox().maxX + 0.05 - entity2.posX + interpolatedRenderPos2.x, entity2.getEntityBoundingBox().maxY + 0.1 - entity2.posY + interpolatedRenderPos2.y, entity2.getEntityBoundingBox().maxZ + 0.05 - entity2.posZ + interpolatedRenderPos2.z);
                    GlStateManager.pushMatrix();
                    GlStateManager.enableBlend();
                    GlStateManager.disableDepth();
                    GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
                    GlStateManager.disableTexture2D();
                    GlStateManager.depthMask(false);
                    GL11.glEnable(2848);
                    GL11.glHint(3154, 4354);
                    GL11.glLineWidth(1.0f);
                    RenderGlobal.renderFilledBox(axisAlignedBB2, color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, (int)this.boxAlpha.getValue() / 255.0f);
                    GL11.glDisable(2848);
                    GlStateManager.depthMask(true);
                    GlStateManager.enableDepth();
                    GlStateManager.enableTexture2D();
                    GlStateManager.disableBlend();
                    GlStateManager.popMatrix();
                    RenderUtil.drawBlockOutline(axisAlignedBB2, new Color(color.getRed(), color.getGreen(), color.getBlue(), (int)this.alpha.getValue()), 1.0f);
                    if (++n2 < 50) {
                        continue;
                    }
                    break;
                }
            }
        }
        if (this.pearl.getValue()) {
            int n3 = 0;
            for (final Entity entity3 : ESP.mc.world.loadedEntityList) {
                if (entity3 instanceof EntityEnderPearl) {
                    if (ESP.mc.player.getDistanceSq(entity3) >= 2500.0) {
                        continue;
                    }
                    final Vec3d interpolatedRenderPos3 = EntityUtil.getInterpolatedRenderPos(entity3, ESP.mc.getRenderPartialTicks());
                    final AxisAlignedBB axisAlignedBB3 = new AxisAlignedBB(entity3.getEntityBoundingBox().minX - 0.05 - entity3.posX + interpolatedRenderPos3.x, entity3.getEntityBoundingBox().minY - 0.0 - entity3.posY + interpolatedRenderPos3.y, entity3.getEntityBoundingBox().minZ - 0.05 - entity3.posZ + interpolatedRenderPos3.z, entity3.getEntityBoundingBox().maxX + 0.05 - entity3.posX + interpolatedRenderPos3.x, entity3.getEntityBoundingBox().maxY + 0.1 - entity3.posY + interpolatedRenderPos3.y, entity3.getEntityBoundingBox().maxZ + 0.05 - entity3.posZ + interpolatedRenderPos3.z);
                    GlStateManager.pushMatrix();
                    GlStateManager.enableBlend();
                    GlStateManager.disableDepth();
                    GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
                    GlStateManager.disableTexture2D();
                    GlStateManager.depthMask(false);
                    GL11.glEnable(2848);
                    GL11.glHint(3154, 4354);
                    GL11.glLineWidth(1.0f);
                    RenderGlobal.renderFilledBox(axisAlignedBB3, color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, (int)this.boxAlpha.getValue() / 255.0f);
                    GL11.glDisable(2848);
                    GlStateManager.depthMask(true);
                    GlStateManager.enableDepth();
                    GlStateManager.enableTexture2D();
                    GlStateManager.disableBlend();
                    GlStateManager.popMatrix();
                    RenderUtil.drawBlockOutline(axisAlignedBB3, new Color(color.getRed(), color.getGreen(), color.getBlue(), (int)this.alpha.getValue()), 1.0f);
                    if (++n3 < 50) {
                        continue;
                    }
                    break;
                }
            }
        }
        if (this.xpbottles.getValue()) {
            int n4 = 0;
            for (final Entity entity4 : ESP.mc.world.loadedEntityList) {
                if (entity4 instanceof EntityExpBottle) {
                    if (ESP.mc.player.getDistanceSq(entity4) >= 2500.0) {
                        continue;
                    }
                    final Vec3d interpolatedRenderPos4 = EntityUtil.getInterpolatedRenderPos(entity4, ESP.mc.getRenderPartialTicks());
                    final AxisAlignedBB axisAlignedBB4 = new AxisAlignedBB(entity4.getEntityBoundingBox().minX - 0.05 - entity4.posX + interpolatedRenderPos4.x, entity4.getEntityBoundingBox().minY - 0.0 - entity4.posY + interpolatedRenderPos4.y, entity4.getEntityBoundingBox().minZ - 0.05 - entity4.posZ + interpolatedRenderPos4.z, entity4.getEntityBoundingBox().maxX + 0.05 - entity4.posX + interpolatedRenderPos4.x, entity4.getEntityBoundingBox().maxY + 0.1 - entity4.posY + interpolatedRenderPos4.y, entity4.getEntityBoundingBox().maxZ + 0.05 - entity4.posZ + interpolatedRenderPos4.z);
                    GlStateManager.pushMatrix();
                    GlStateManager.enableBlend();
                    GlStateManager.disableDepth();
                    GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
                    GlStateManager.disableTexture2D();
                    GlStateManager.depthMask(false);
                    GL11.glEnable(2848);
                    GL11.glHint(3154, 4354);
                    GL11.glLineWidth(1.0f);
                    RenderGlobal.renderFilledBox(axisAlignedBB4, color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, (int)this.boxAlpha.getValue() / 255.0f);
                    GL11.glDisable(2848);
                    GlStateManager.depthMask(true);
                    GlStateManager.enableDepth();
                    GlStateManager.enableTexture2D();
                    GlStateManager.disableBlend();
                    GlStateManager.popMatrix();
                    RenderUtil.drawBlockOutline(axisAlignedBB4, new Color(color.getRed(), color.getGreen(), color.getBlue(), (int)this.alpha.getValue()), 1.0f);
                    if (++n4 < 50) {
                        continue;
                    }
                    break;
                }
            }
        }
    }
    
    public ESP() {
        super("ESP", "Renders a nice ESP.", Module.Category.RENDER, false, false, false);
        this.items = (Setting<Boolean>)this.register(new Setting("Items", (Object)false));
        this.xporbs = (Setting<Boolean>)this.register(new Setting("XpOrbs", (Object)false));
        this.xpbottles = (Setting<Boolean>)this.register(new Setting("XpBottles", (Object)false));
        this.pearl = (Setting<Boolean>)this.register(new Setting("Pearls", (Object)false));
        this.colorsync = (Setting<Boolean>)this.register(new Setting("ColorSync", (Object)true));
        this.red = (Setting<Integer>)this.register(new Setting("Red", (Object)255, (Object)0, (Object)255));
        this.green = (Setting<Integer>)this.register(new Setting("Green", (Object)255, (Object)0, (Object)255));
        this.blue = (Setting<Integer>)this.register(new Setting("Blue", (Object)255, (Object)0, (Object)255));
        this.boxAlpha = (Setting<Integer>)this.register(new Setting("BoxAlpha", (Object)120, (Object)0, (Object)255));
        this.alpha = (Setting<Integer>)this.register(new Setting("Alpha", (Object)255, (Object)0, (Object)255));
        this.setInstance();
    }
    
    public static ESP getInstance() {
        if (ESP.INSTANCE == null) {
            ESP.INSTANCE = new ESP();
        }
        return ESP.INSTANCE;
    }
}
