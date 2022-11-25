//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.mixin.mixins;

import net.minecraft.client.model.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.client.renderer.entity.layers.*;
import net.minecraft.client.renderer.entity.*;

@Mixin({ LayerBipedArmor.class })
public abstract class MixinLayerBipedArmor extends LayerArmorBase<ModelBiped>
{
    public MixinLayerBipedArmor(final RenderLivingBase<?> renderLivingBase) {
        super((RenderLivingBase)renderLivingBase);
    }
}
