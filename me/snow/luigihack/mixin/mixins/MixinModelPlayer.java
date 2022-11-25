//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.model.*;
import net.minecraft.entity.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.client.*;
import net.minecraft.entity.player.*;
import me.snow.luigihack.impl.modules.render.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ ModelPlayer.class })
public class MixinModelPlayer
{
    @Inject(method = { "setRotationAngles" }, at = { @At("RETURN") })
    public void setRotationAngles(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final Entity entity, final CallbackInfo callbackInfo) {
        if (Minecraft.getMinecraft().world != null && Minecraft.getMinecraft().player != null && entity instanceof EntityPlayer) {
            Skeleton.addEntity((EntityPlayer)entity, (ModelPlayer)ModelPlayer.class.cast(this));
        }
    }
}
