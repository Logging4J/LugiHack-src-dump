//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util.skid.cr;

import net.minecraft.client.*;
import java.util.*;
import net.minecraft.item.*;

public class InventoryUtilsCr
{
    protected static /* synthetic */ Minecraft mc;
    
    public static int pickItem(final int n) {
        final ArrayList<Object> list = new ArrayList<Object>();
        for (int i = 0; i < 9; ++i) {
            if (Item.getIdFromItem(((ItemStack)InventoryUtilsCr.mc.player.inventory.mainInventory.get(i)).getItem()) == n) {
                list.add(InventoryUtilsCr.mc.player.inventory.mainInventory.get(i));
            }
        }
        if (list.size() >= 1) {
            return InventoryUtilsCr.mc.player.inventory.mainInventory.indexOf(list.get(0));
        }
        return -1;
    }
    
    public static int getSlot() {
        return InventoryUtilsCr.mc.player.inventory.currentItem;
    }
    
    public static void setSlot(final int currentItem) {
        if (currentItem > 8 || currentItem < 0) {
            return;
        }
        InventoryUtilsCr.mc.player.inventory.currentItem = currentItem;
    }
    
    public static int getPlaceableItem() {
        final ArrayList<Object> list = new ArrayList<Object>();
        for (int i = 0; i < 9; ++i) {
            if (((ItemStack)InventoryUtilsCr.mc.player.inventory.mainInventory.get(i)).getItem() instanceof ItemBlock) {
                list.add(InventoryUtilsCr.mc.player.inventory.mainInventory.get(i));
            }
        }
        return -1;
    }
    
    static {
        InventoryUtilsCr.mc = Minecraft.getMinecraft();
    }
}
