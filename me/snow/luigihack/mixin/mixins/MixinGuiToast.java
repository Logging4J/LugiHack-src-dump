//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.gui.toasts.*;
import net.minecraft.client.gui.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.snow.luigihack.impl.modules.render.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ GuiToast.class })
public class MixinGuiToast
{
    @Inject(method = { "drawToast" }, at = { @At("HEAD") }, cancellable = true)
    public void drawToastHook(final ScaledResolution scaledResolution, final CallbackInfo callbackInfo) {
        if (NoRender.getInstance().isOn() && (boolean)NoRender.getInstance().advancements.getValue()) {
            callbackInfo.cancel();
        }
    }
}
