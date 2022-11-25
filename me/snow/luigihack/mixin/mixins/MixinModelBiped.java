//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.model.*;
import net.minecraft.entity.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.entity.monster.*;
import me.snow.luigihack.impl.modules.render.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ ModelBiped.class })
public class MixinModelBiped
{
    @Inject(method = { "render" }, at = { @At("HEAD") }, cancellable = true)
    public void render(final Entity entity, final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final CallbackInfo callbackInfo) {
        if (entity instanceof EntityPigZombie && (boolean)NoRender.getInstance().pigmen.getValue()) {
            callbackInfo.cancel();
        }
    }
}
