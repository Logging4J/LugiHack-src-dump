//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.mixin.mixins.accessors;

import org.spongepowered.asm.mixin.*;
import net.minecraft.entity.*;
import java.util.*;
import net.minecraft.potion.*;
import org.spongepowered.asm.mixin.gen.*;

@Mixin({ EntityLivingBase.class })
public interface IEntityLivingBase
{
    @Accessor("activePotionsMap")
    Map<Potion, PotionEffect> getActivePotionMap();
    
    @Invoker("onNewPotionEffect")
    void hookOnNewPotionEffect(final PotionEffect p0);
    
    @Invoker("onChangedPotionEffect")
    void hookOnChangedPotionEffect(final PotionEffect p0, final boolean p1);
    
    @Invoker("getArmSwingAnimationEnd")
    int hookGetArmSwingAnimationEnd();
    
    @Invoker("getArmSwingAnimationEnd")
    int getArmSwingAnimationEnd();
}
