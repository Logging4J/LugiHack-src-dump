//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.item.*;
import net.minecraft.client.renderer.block.model.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.snow.luigihack.impl.modules.render.*;
import net.minecraft.client.*;
import me.snow.luigihack.impl.*;
import net.minecraft.client.renderer.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ RenderItem.class })
public abstract class MixinItemRender
{
    @Inject(method = { "renderItemModel" }, at = { @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderItem;renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/block/model/IBakedModel;)V", shift = At.Shift.BEFORE) })
    private void test(final ItemStack itemStack, final IBakedModel bakedModel, final ItemCameraTransforms.TransformType transformType, final boolean b, final CallbackInfo callbackInfo) {
        if ((boolean)ItemViewModel.getINSTANCE().enabled.getValue() && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && !Feature.fullNullCheck()) {
            GlStateManager.scale((float)ItemViewModel.getINSTANCE().sizeX.getValue(), (float)ItemViewModel.getINSTANCE().sizeY.getValue(), (float)ItemViewModel.getINSTANCE().sizeZ.getValue());
            GlStateManager.rotate((float)ItemViewModel.getINSTANCE().rotationX.getValue() * 360.0f, 1.0f, 0.0f, 0.0f);
            GlStateManager.rotate((float)ItemViewModel.getINSTANCE().rotationY.getValue() * 360.0f, 0.0f, 1.0f, 0.0f);
            GlStateManager.rotate((float)ItemViewModel.getINSTANCE().rotationZ.getValue() * 360.0f, 0.0f, 0.0f, 1.0f);
            GlStateManager.translate((float)ItemViewModel.getINSTANCE().positionX.getValue(), (float)ItemViewModel.getINSTANCE().positionY.getValue(), (float)ItemViewModel.getINSTANCE().positionZ.getValue());
        }
    }
}
