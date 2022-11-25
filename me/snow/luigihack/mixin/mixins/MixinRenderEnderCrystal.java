//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.mixin.mixins;

import net.minecraft.client.renderer.entity.*;
import net.minecraft.util.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.client.model.*;
import net.minecraft.entity.*;
import me.snow.luigihack.impl.modules.render.*;
import net.minecraft.client.renderer.*;
import me.snow.luigihack.api.event.events.*;
import org.lwjgl.opengl.*;
import me.snow.luigihack.impl.modules.client.*;
import java.awt.*;
import me.snow.luigihack.api.util.*;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.entity.item.*;
import javax.annotation.*;

@Mixin({ RenderEnderCrystal.class })
public class MixinRenderEnderCrystal
{
    private static final ResourceLocation glint;
    @Shadow
    @Final
    private static ResourceLocation ENDER_CRYSTAL_TEXTURES;
    @Shadow
    private final ModelBase modelEnderCrystal;
    @Shadow
    private final ModelBase modelEnderCrystalNoBase;
    
    public MixinRenderEnderCrystal() {
        this.modelEnderCrystal = (ModelBase)new ModelEnderCrystal(0.0f, true);
        this.modelEnderCrystalNoBase = (ModelBase)new ModelEnderCrystal(0.0f, false);
    }
    
    @Redirect(method = { "doRender" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelBase;render(Lnet/minecraft/entity/Entity;FFFFFF)V"))
    public void renderModelBaseHook(final ModelBase modelBase, final Entity entity, final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        if (CrystalChams.INSTANCE.isEnabled()) {
            if ((boolean)CrystalChams.INSTANCE.animateScale.getValue() && CrystalChams.INSTANCE.scaleMap.containsKey(entity)) {
                GlStateManager.scale((float)CrystalChams.INSTANCE.scaleMap.get(entity), (float)CrystalChams.INSTANCE.scaleMap.get(entity), (float)CrystalChams.INSTANCE.scaleMap.get(entity));
            }
            else {
                GlStateManager.scale((float)CrystalChams.INSTANCE.scale.getValue(), (float)CrystalChams.INSTANCE.scale.getValue(), (float)CrystalChams.INSTANCE.scale.getValue());
            }
        }
        if (CrystalChams.INSTANCE.isEnabled() && (boolean)CrystalChams.INSTANCE.wireframe.getValue()) {
            CrystalChams.INSTANCE.onRenderModel(new RenderEntityModelEvent(0, modelBase, entity, n, n2, n3, n4, n5, n6));
        }
        if (CrystalChams.INSTANCE.isEnabled() && (boolean)CrystalChams.INSTANCE.chams.getValue()) {
            GL11.glPushAttrib(1048575);
            GL11.glDisable(3008);
            GL11.glDisable(3553);
            GL11.glDisable(2896);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            GL11.glLineWidth(1.5f);
            GL11.glEnable(2960);
            if (CrystalChams.INSTANCE.rainbow.getValue()) {
                final Color color = CrystalChams.INSTANCE.colorSync.getValue() ? Colors.INSTANCE.getCurrentColor() : new Color(RenderUtil.getRainbow((int)CrystalChams.INSTANCE.speed.getValue() * 100, 0, (int)CrystalChams.INSTANCE.saturation.getValue() / 100.0f, (int)CrystalChams.INSTANCE.brightness.getValue() / 100.0f));
                final Color color2 = EntityUtil.getColor(entity, color.getRed(), color.getGreen(), color.getBlue(), (int)CrystalChams.INSTANCE.alpha.getValue(), true);
                if (CrystalChams.INSTANCE.throughWalls.getValue()) {
                    GL11.glDisable(2929);
                    GL11.glDepthMask(false);
                }
                GL11.glEnable(10754);
                GL11.glColor4f(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, (int)CrystalChams.INSTANCE.alpha.getValue() / 255.0f);
                modelBase.render(entity, n, n2, n3, n4, n5, n6);
                if (CrystalChams.INSTANCE.throughWalls.getValue()) {
                    GL11.glEnable(2929);
                    GL11.glDepthMask(true);
                }
            }
            else if ((boolean)CrystalChams.INSTANCE.xqz.getValue() && (boolean)CrystalChams.INSTANCE.throughWalls.getValue()) {
                final Color color3 = CrystalChams.INSTANCE.colorSync.getValue() ? EntityUtil.getColor(entity, (int)CrystalChams.INSTANCE.hiddenRed.getValue(), (int)CrystalChams.INSTANCE.hiddenGreen.getValue(), (int)CrystalChams.INSTANCE.hiddenBlue.getValue(), (int)CrystalChams.INSTANCE.hiddenAlpha.getValue(), true) : EntityUtil.getColor(entity, (int)CrystalChams.INSTANCE.hiddenRed.getValue(), (int)CrystalChams.INSTANCE.hiddenGreen.getValue(), (int)CrystalChams.INSTANCE.hiddenBlue.getValue(), (int)CrystalChams.INSTANCE.hiddenAlpha.getValue(), true);
                final Color color4 = CrystalChams.INSTANCE.colorSync.getValue() ? EntityUtil.getColor(entity, (int)CrystalChams.INSTANCE.red.getValue(), (int)CrystalChams.INSTANCE.green.getValue(), (int)CrystalChams.INSTANCE.blue.getValue(), (int)CrystalChams.INSTANCE.alpha.getValue(), true) : EntityUtil.getColor(entity, (int)CrystalChams.INSTANCE.red.getValue(), (int)CrystalChams.INSTANCE.green.getValue(), (int)CrystalChams.INSTANCE.blue.getValue(), (int)CrystalChams.INSTANCE.alpha.getValue(), true);
                if (CrystalChams.INSTANCE.throughWalls.getValue()) {
                    GL11.glDisable(2929);
                    GL11.glDepthMask(false);
                }
                GL11.glEnable(10754);
                GL11.glColor4f(color3.getRed() / 255.0f, color3.getGreen() / 255.0f, color3.getBlue() / 255.0f, (int)CrystalChams.INSTANCE.alpha.getValue() / 255.0f);
                modelBase.render(entity, n, n2, n3, n4, n5, n6);
                if (CrystalChams.INSTANCE.throughWalls.getValue()) {
                    GL11.glEnable(2929);
                    GL11.glDepthMask(true);
                }
                GL11.glColor4f(color4.getRed() / 255.0f, color4.getGreen() / 255.0f, color4.getBlue() / 255.0f, (int)CrystalChams.INSTANCE.alpha.getValue() / 255.0f);
                modelBase.render(entity, n, n2, n3, n4, n5, n6);
            }
            else {
                final Color color5 = CrystalChams.INSTANCE.colorSync.getValue() ? Colors.INSTANCE.getCurrentColor() : EntityUtil.getColor(entity, (int)CrystalChams.INSTANCE.red.getValue(), (int)CrystalChams.INSTANCE.green.getValue(), (int)CrystalChams.INSTANCE.blue.getValue(), (int)CrystalChams.INSTANCE.alpha.getValue(), true);
                if (CrystalChams.INSTANCE.throughWalls.getValue()) {
                    GL11.glDisable(2929);
                    GL11.glDepthMask(false);
                }
                GL11.glEnable(10754);
                GL11.glColor4f(color5.getRed() / 255.0f, color5.getGreen() / 255.0f, color5.getBlue() / 255.0f, (int)CrystalChams.INSTANCE.alpha.getValue() / 255.0f);
                modelBase.render(entity, n, n2, n3, n4, n5, n6);
                if (CrystalChams.INSTANCE.throughWalls.getValue()) {
                    GL11.glEnable(2929);
                    GL11.glDepthMask(true);
                }
            }
            GL11.glEnable(3042);
            GL11.glEnable(2896);
            GL11.glEnable(3553);
            GL11.glEnable(3008);
            GL11.glPopAttrib();
            if (CrystalChams.INSTANCE.glint.getValue()) {
                GL11.glDisable(2929);
                GL11.glDepthMask(false);
                GlStateManager.enableAlpha();
                GlStateManager.color(1.0f, 0.0f, 0.0f, 0.13f);
                modelBase.render(entity, n, n2, n3, n4, n5, n6);
                GlStateManager.disableAlpha();
                GL11.glEnable(2929);
                GL11.glDepthMask(true);
            }
        }
        else {
            modelBase.render(entity, n, n2, n3, n4, n5, n6);
        }
        if (CrystalChams.INSTANCE.isEnabled()) {
            if ((boolean)CrystalChams.INSTANCE.animateScale.getValue() && CrystalChams.INSTANCE.scaleMap.containsKey(entity)) {
                GlStateManager.scale(1.0f / CrystalChams.INSTANCE.scaleMap.get(entity), 1.0f / CrystalChams.INSTANCE.scaleMap.get(entity), 1.0f / CrystalChams.INSTANCE.scaleMap.get(entity));
            }
            else {
                GlStateManager.scale(1.0f / (float)CrystalChams.INSTANCE.scale.getValue(), 1.0f / (float)CrystalChams.INSTANCE.scale.getValue(), 1.0f / (float)CrystalChams.INSTANCE.scale.getValue());
            }
        }
    }
    
    @Nullable
    protected ResourceLocation getEntityTexture(final EntityEnderCrystal entityEnderCrystal) {
        return null;
    }
    
    static {
        glint = new ResourceLocation("textures/glint.png");
    }
}
