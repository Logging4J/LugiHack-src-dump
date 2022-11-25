//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.mixin.mixins;

import net.minecraft.client.*;
import net.minecraft.client.entity.*;
import net.minecraft.item.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;
import me.snow.luigihack.impl.modules.client.*;
import java.awt.*;
import net.minecraft.util.*;
import net.minecraftforge.common.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraftforge.fml.common.eventhandler.*;
import org.spongepowered.asm.mixin.injection.*;
import me.snow.luigihack.impl.modules.render.*;

@Mixin({ ItemRenderer.class })
public abstract class MixinItemRenderer
{
    public Minecraft mc;
    private boolean injection;
    
    public MixinItemRenderer() {
        this.injection = true;
    }
    
    @Shadow
    public abstract void renderItemInFirstPerson(final AbstractClientPlayer p0, final float p1, final float p2, final EnumHand p3, final float p4, final ItemStack p5, final float p6);
    
    @Inject(method = { "renderItemInFirstPerson(Lnet/minecraft/client/entity/AbstractClientPlayer;FFLnet/minecraft/util/EnumHand;FLnet/minecraft/item/ItemStack;F)V" }, at = { @At("HEAD") }, cancellable = true)
    public void renderItemInFirstPersonHook(final AbstractClientPlayer abstractClientPlayer, final float n, final float n2, final EnumHand enumHand, final float n3, final ItemStack itemStack, final float n4, final CallbackInfo callbackInfo) {
        if (this.injection) {
            callbackInfo.cancel();
            final ItemViewModel instance = ItemViewModel.getINSTANCE();
            float n5 = 0.0f;
            float n6 = 0.0f;
            this.injection = false;
            if (enumHand == EnumHand.MAIN_HAND) {
                if (instance.isOn() && abstractClientPlayer.getHeldItemMainhand() != ItemStack.EMPTY) {
                    n5 = (float)instance.mainX.getValue();
                    n6 = (float)instance.mainY.getValue();
                }
            }
            else if (!(boolean)instance.normalOffset.getValue() && instance.isOn() && abstractClientPlayer.getHeldItemOffhand() != ItemStack.EMPTY) {
                n5 = (float)instance.offX.getValue();
                n6 = (float)instance.offY.getValue();
            }
            this.renderItemInFirstPerson(abstractClientPlayer, n, n2, enumHand, n3 + n5, itemStack, n4 + n6);
            this.injection = true;
        }
        if (HandModifier.getINSTANCE().isOn() && this.injection) {
            callbackInfo.cancel();
            float n7 = 0.0f;
            float n8 = 0.0f;
            this.injection = false;
            if (enumHand == EnumHand.MAIN_HAND) {
                if (HandModifier.getINSTANCE().isOn()) {
                    n7 = ((Double)HandModifier.getINSTANCE().mainX.getValue()).floatValue();
                    n8 = ((Double)HandModifier.getINSTANCE().mainY.getValue()).floatValue();
                }
            }
            else if (HandModifier.getINSTANCE().isOn()) {
                n7 = ((Double)HandModifier.getINSTANCE().offX.getValue()).floatValue();
                n8 = ((Double)HandModifier.getINSTANCE().offY.getValue()).floatValue();
            }
            if (HandModifier.getINSTANCE().isOn() && (boolean)HandModifier.getINSTANCE().chams.getValue() && enumHand == EnumHand.MAIN_HAND && itemStack.isEmpty()) {
                if (((HandModifier.RenderMode)HandModifier.getINSTANCE().mode.getValue()).equals((Object)HandModifier.RenderMode.WIREFRAME) && (boolean)HandModifier.getINSTANCE().chams.getValue()) {
                    this.renderItemInFirstPerson(abstractClientPlayer, n, n2, enumHand, n3 + n7, itemStack, n4 + n8);
                }
                GlStateManager.pushMatrix();
                if (((HandModifier.RenderMode)HandModifier.getINSTANCE().mode.getValue()).equals((Object)HandModifier.RenderMode.WIREFRAME) && (boolean)HandModifier.getINSTANCE().chams.getValue()) {
                    GL11.glPushAttrib(1048575);
                }
                else {
                    GlStateManager.pushAttrib();
                }
                if (((HandModifier.RenderMode)HandModifier.getINSTANCE().mode.getValue()).equals((Object)HandModifier.RenderMode.WIREFRAME) && (boolean)HandModifier.getINSTANCE().chams.getValue()) {
                    GL11.glPolygonMode(1032, 6913);
                }
                GL11.glDisable(3553);
                GL11.glDisable(2896);
                if (!((HandModifier.RenderMode)HandModifier.getINSTANCE().mode.getValue()).equals((Object)HandModifier.RenderMode.WIREFRAME) || (boolean)HandModifier.getINSTANCE().chams.getValue()) {}
                final Color color = HandModifier.getINSTANCE().csync.getValue() ? Colors.INSTANCE.getCurrentColor() : new Color((int)HandModifier.getINSTANCE().red.getValue(), (int)HandModifier.getINSTANCE().green.getValue(), (int)HandModifier.getINSTANCE().blue.getValue(), (int)HandModifier.getINSTANCE().alpha.getValue());
                GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, (int)HandModifier.getINSTANCE().alpha.getValue() / 255.0f);
                this.renderItemInFirstPerson(abstractClientPlayer, n, n2, enumHand, n3 + n7, itemStack, n4 + n8);
                GlStateManager.popAttrib();
                GlStateManager.popMatrix();
            }
            if (HandModifier.getINSTANCE().isOn() && (!itemStack.isEmpty || HandModifier.getINSTANCE().isOff())) {
                this.renderItemInFirstPerson(abstractClientPlayer, n, n2, enumHand, n3 + n7, itemStack, n4 + n8);
            }
            else if (!itemStack.isEmpty || HandModifier.getINSTANCE().isOff()) {
                this.renderItemInFirstPerson(abstractClientPlayer, n, n2, enumHand, n3, itemStack, n4);
            }
            this.injection = true;
        }
    }
    
    @Inject(method = { "transformSideFirstPerson" }, at = { @At("HEAD") }, cancellable = true)
    public void transformSideFirstPerson(final EnumHandSide enumHandSide, final float n, final CallbackInfo callbackInfo) {
        MinecraftForge.EVENT_BUS.post((Event)new RenderItemEvent(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0));
    }
    
    @Redirect(method = { "renderArmFirstPerson" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;translate(FFF)V", ordinal = 0))
    public void translateHook(final float n, final float n2, final float n3) {
        final ItemViewModel instance = ItemViewModel.getINSTANCE();
        final boolean b = Minecraft.getMinecraft().player != null && Minecraft.getMinecraft().player.getHeldItemMainhand() != ItemStack.EMPTY && instance.isOn();
        GlStateManager.translate(n + (b ? instance.mainX.getValue() : 0.0f), n2 + (b ? instance.mainY.getValue() : 0.0f), n3);
    }
    
    @Inject(method = { "renderFireInFirstPerson" }, at = { @At("HEAD") }, cancellable = true)
    public void renderFireInFirstPersonHook(final CallbackInfo callbackInfo) {
        if (NoRender.getInstance().isOn() && (boolean)NoRender.getInstance().fire.getValue()) {
            callbackInfo.cancel();
        }
    }
    
    @Inject(method = { "renderSuffocationOverlay" }, at = { @At("HEAD") }, cancellable = true)
    public void renderSuffocationOverlay(final CallbackInfo callbackInfo) {
        if (NoRender.getInstance().isOn() && (boolean)NoRender.getInstance().blocks.getValue()) {
            callbackInfo.cancel();
        }
    }
}
