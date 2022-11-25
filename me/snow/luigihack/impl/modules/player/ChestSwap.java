//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.player;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.init.*;
import net.minecraft.enchantment.*;
import net.minecraft.item.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;

public class ChestSwap extends Module
{
    private /* synthetic */ Setting<Boolean> Curse;
    private /* synthetic */ Setting<Boolean> PreferElytra;
    
    private int FindChestItem(final boolean b) {
        int n = -1;
        float n2 = 0.0f;
        for (int i = 0; i < ChestSwap.mc.player.inventoryContainer.getInventory().size(); ++i) {
            if (i != 0 && i != 5 && i != 6 && i != 7) {
                if (i != 8) {
                    final ItemStack itemStack = (ItemStack)ChestSwap.mc.player.inventoryContainer.getInventory().get(i);
                    if (itemStack != null && itemStack.getItem() != Items.AIR) {
                        if (itemStack.getItem() instanceof ItemArmor) {
                            final ItemArmor itemArmor = (ItemArmor)itemStack.getItem();
                            if (itemArmor.armorType == EntityEquipmentSlot.CHEST) {
                                final float n3 = (float)(itemArmor.damageReduceAmount + EnchantmentHelper.getEnchantmentLevel(Enchantments.PROTECTION, itemStack));
                                final boolean b2 = (boolean)this.Curse.getValue() && EnchantmentHelper.hasBindingCurse(itemStack);
                                if (n3 > n2 && !b2) {
                                    n2 = n3;
                                    n = i;
                                }
                            }
                        }
                        else if (b && itemStack.getItem() instanceof ItemElytra) {
                            return i;
                        }
                    }
                }
            }
        }
        return n;
    }
    
    public ChestSwap() {
        super("ElytraReplace", "Will attempt to instantly swap your chestplate with an elytra or vice versa, depending on what is already equipped", Module.Category.PLAYER, true, false, false);
        this.PreferElytra = (Setting<Boolean>)this.register(new Setting("PreferElytra", (Object)true));
        this.Curse = (Setting<Boolean>)this.register(new Setting("Curse", (Object)false));
    }
    
    public void onEnable() {
        super.onEnable();
        if (ChestSwap.mc.player == null) {
            return;
        }
        final ItemStack getStack = ChestSwap.mc.player.inventoryContainer.getSlot(6).getStack();
        if (getStack.isEmpty()) {
            int n = this.FindChestItem((boolean)this.PreferElytra.getValue());
            if (!(boolean)this.PreferElytra.getValue() && n == -1) {
                n = this.FindChestItem(true);
            }
            if (n != -1) {
                ChestSwap.mc.playerController.windowClick(ChestSwap.mc.player.inventoryContainer.windowId, n, 0, ClickType.PICKUP, (EntityPlayer)ChestSwap.mc.player);
                ChestSwap.mc.playerController.windowClick(ChestSwap.mc.player.inventoryContainer.windowId, 6, 0, ClickType.PICKUP, (EntityPlayer)ChestSwap.mc.player);
                ChestSwap.mc.playerController.windowClick(ChestSwap.mc.player.inventoryContainer.windowId, n, 0, ClickType.PICKUP, (EntityPlayer)ChestSwap.mc.player);
            }
            this.toggle();
            return;
        }
        final int findChestItem = this.FindChestItem(getStack.getItem() instanceof ItemArmor);
        if (findChestItem != -1) {
            ChestSwap.mc.playerController.windowClick(ChestSwap.mc.player.inventoryContainer.windowId, findChestItem, 0, ClickType.PICKUP, (EntityPlayer)ChestSwap.mc.player);
            ChestSwap.mc.playerController.windowClick(ChestSwap.mc.player.inventoryContainer.windowId, 6, 0, ClickType.PICKUP, (EntityPlayer)ChestSwap.mc.player);
            ChestSwap.mc.playerController.windowClick(ChestSwap.mc.player.inventoryContainer.windowId, findChestItem, 0, ClickType.PICKUP, (EntityPlayer)ChestSwap.mc.player);
        }
        this.toggle();
    }
}
