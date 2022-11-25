//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.entity.*;
import net.minecraft.world.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.snow.luigihack.api.util.*;
import me.snow.luigihack.impl.modules.movement.*;
import org.spongepowered.asm.mixin.injection.*;
import me.snow.luigihack.*;
import me.snow.luigihack.impl.modules.render.*;

@Mixin({ EntityLivingBase.class })
public abstract class MixinEntityLivingBase extends Entity
{
    public MixinEntityLivingBase(final World world) {
        super(world);
    }
    
    @Inject(method = { "isElytraFlying" }, at = { @At("HEAD") }, cancellable = true)
    private void isElytraFlyingHook(final CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if (Util.mc.player != null && Util.mc.player.equals((Object)this) && ElytraFlight.getInstance().isOn() && ElytraFlight.getInstance().mode.getValue() == ElytraFlight.Mode.BETTER) {
            callbackInfoReturnable.setReturnValue((Object)false);
        }
    }
    
    @Inject(method = { "getArmSwingAnimationEnd" }, at = { @At("HEAD") }, cancellable = true)
    private void getArmSwingAnimationEnd(final CallbackInfoReturnable<Integer> callbackInfoReturnable) {
        if (LuigiHack.moduleManager.isModuleEnabled((Class)HandModifier.class) && (boolean)HandModifier.getINSTANCE().slowSwing.getValue()) {
            callbackInfoReturnable.setReturnValue((Object)15);
        }
        if (((ItemViewModel)LuigiHack.moduleManager.getModuleByClass((Class)ItemViewModel.class)).isEnabled() && (boolean)ItemViewModel.getINSTANCE().changeswing.getValue()) {
            callbackInfoReturnable.setReturnValue((Object)(int)ItemViewModel.getINSTANCE().swingspeed.getValue());
        }
    }
}
