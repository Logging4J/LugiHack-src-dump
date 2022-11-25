//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util.cc;

import me.snow.luigihack.api.util.*;
import net.minecraft.block.*;
import me.snow.luigihack.*;
import java.util.*;
import net.minecraft.nbt.*;
import net.minecraft.item.*;
import java.util.function.*;

public class InventoryUtilCC implements Util
{
    public static boolean isInHotbar(final Item item) {
        return LuigiHack.inventoryManagercc.searchSlot(item, InventoryManagerCC.InventoryRegion.HOTBAR) != -1;
    }
    
    public static boolean isHolding(final Class<? extends Item> clazz) {
        return clazz.isInstance(InventoryUtilCC.mc.player.getHeldItemMainhand().getItem()) || clazz.isInstance(InventoryUtilCC.mc.player.getHeldItemOffhand().getItem());
    }
    
    public static boolean isHolding(final Block[] array) {
        return Arrays.stream(array).anyMatch(block -> Item.getItemFromBlock(block).equals(InventoryUtilCC.mc.player.getHeldItemMainhand().getItem())) || Arrays.stream(array).anyMatch(block2 -> Item.getItemFromBlock(block2).equals(InventoryUtilCC.mc.player.getHeldItemOffhand().getItem()));
    }
    
    public static boolean isHolding(final Block block) {
        return InventoryUtilCC.mc.player.getHeldItemMainhand().getItem().equals(Item.getItemFromBlock(block)) || InventoryUtilCC.mc.player.getHeldItemOffhand().getItem().equals(Item.getItemFromBlock(block));
    }
    
    public static boolean isHolding(final Item[] array) {
        return Arrays.stream(array).anyMatch(item -> item.equals(InventoryUtilCC.mc.player.getHeldItemMainhand().getItem())) || Arrays.stream(array).anyMatch(item2 -> item2.equals(InventoryUtilCC.mc.player.getHeldItemOffhand().getItem()));
    }
    
    public static int getHighestEnchantLevel() {
        short getShort = 0;
        for (int i = 0; i < InventoryUtilCC.mc.player.getHeldItemMainhand().getEnchantmentTagList().tagCount(); ++i) {
            final NBTTagCompound getCompoundTagAt = InventoryUtilCC.mc.player.getHeldItemMainhand().getEnchantmentTagList().getCompoundTagAt(i);
            if (getCompoundTagAt.getShort("lvl") > getShort) {
                getShort = getCompoundTagAt.getShort("lvl");
            }
        }
        return getShort;
    }
    
    public static boolean isHolding(final Item item) {
        return InventoryUtilCC.mc.player.getHeldItemMainhand().getItem().equals(item) || InventoryUtilCC.mc.player.getHeldItemOffhand().getItem().equals(item);
    }
    
    public static int getItemCount(final Item obj) {
        if (obj instanceof ItemArmor) {
            return InventoryUtilCC.mc.player.inventory.armorInventory.stream().filter(itemStack -> itemStack.getItem().equals(obj)).mapToInt(ItemStack::getCount).sum();
        }
        return InventoryUtilCC.mc.player.inventory.mainInventory.stream().filter(itemStack2 -> itemStack2.getItem().equals(obj)).mapToInt(ItemStack::getCount).sum() + InventoryUtilCC.mc.player.inventory.offHandInventory.stream().filter(itemStack3 -> itemStack3.getItem().equals(obj)).mapToInt(ItemStack::getCount).sum();
    }
}
