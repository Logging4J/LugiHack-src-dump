//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.mixin.mixins;

import net.minecraft.item.*;
import net.minecraft.nbt.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.*;
import me.snow.luigihack.impl.modules.player.*;

@Mixin({ ItemStack.class })
public abstract class MixinItemStack
{
    @Shadow
    private int itemDamage;
    
    @Inject(method = { "<init>(Lnet/minecraft/item/Item;IILnet/minecraft/nbt/NBTTagCompound;)V" }, at = { @At("RETURN") })
    @Dynamic
    private void initHook(final Item item, final int n, final int n2, final NBTTagCompound nbtTagCompound, final CallbackInfo callbackInfo) {
        this.itemDamage = this.checkDurability(ItemStack.class.cast(this), this.itemDamage, n2);
    }
    
    @Inject(method = { "<init>(Lnet/minecraft/nbt/NBTTagCompound;)V" }, at = { @At("RETURN") })
    private void initHook2(final NBTTagCompound nbtTagCompound, final CallbackInfo callbackInfo) {
        this.itemDamage = this.checkDurability(ItemStack.class.cast(this), this.itemDamage, nbtTagCompound.getShort("Damage"));
    }
    
    private int checkDurability(final ItemStack itemStack, final int n, final int n2) {
        int n3 = n;
        if (TrueDurability.getInstance().isOn() && n2 < 0) {
            n3 = n2;
        }
        return n3;
    }
}
