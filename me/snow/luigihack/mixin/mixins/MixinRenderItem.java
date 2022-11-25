//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.mixin.mixins;

import net.minecraft.item.*;
import org.spongepowered.asm.mixin.*;
import me.snow.luigihack.*;
import me.snow.luigihack.impl.modules.render.*;
import java.awt.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.block.model.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraftforge.common.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraftforge.fml.common.eventhandler.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ RenderItem.class })
public class MixinRenderItem
{
    @Shadow
    private void renderModel(final IBakedModel bakedModel, final int n, final ItemStack itemStack) {
    }
    
    @Redirect(method = { "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/block/model/IBakedModel;)V" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderItem;renderModel(Lnet/minecraft/client/renderer/block/model/IBakedModel;Lnet/minecraft/item/ItemStack;)V"))
    private void yes(final RenderItem renderItem, final IBakedModel bakedModel, final ItemStack itemStack) {
        if (LuigiHack.moduleManager.isModuleEnabled((Class)ItemViewModel.class)) {
            this.renderModel(bakedModel, new Color(1.0f, 1.0f, 1.0f, (int)ItemViewModel.getINSTANCE().viewAlpha.getValue() / 255.0f).getRGB(), itemStack);
        }
        else {
            this.renderModel(bakedModel, -1, itemStack);
        }
    }
    
    @Redirect(method = { "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/block/model/IBakedModel;)V" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;color(FFFF)V"))
    private void renderItem(final float n, final float n2, final float n3, final float n4) {
        if (LuigiHack.moduleManager.isModuleEnabled((Class)ItemViewModel.class)) {
            GlStateManager.color(n, n2, n3, (int)ItemViewModel.getINSTANCE().viewAlpha.getValue() / 255.0f);
        }
        else {
            GlStateManager.color(n, n2, n3, n4);
        }
    }
    
    @Inject(method = { "renderItemModel" }, at = { @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderItem;renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/block/model/IBakedModel;)V", shift = At.Shift.BEFORE) })
    private void renderItemModel(final ItemStack itemStack, final IBakedModel bakedModel, final ItemCameraTransforms.TransformType transformType, final boolean b, final CallbackInfo callbackInfo) {
        MinecraftForge.EVENT_BUS.post((Event)new RenderItemEvent(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0));
    }
}
