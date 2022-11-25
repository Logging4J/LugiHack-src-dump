//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.entity.passive.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.snow.luigihack.impl.modules.movement.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ EntityLlama.class })
public class MixinEntityLlama
{
    @Inject(method = { "canBeSteered" }, at = { @At("RETURN") }, cancellable = true)
    public void canBeSteered(final CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if (EntitySpeed.INSTANCE.isEnabled()) {
            callbackInfoReturnable.setReturnValue((Object)true);
        }
    }
}
