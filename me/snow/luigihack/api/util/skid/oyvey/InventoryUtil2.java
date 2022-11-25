//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util.skid.oyvey;

import me.snow.luigihack.api.util.*;
import net.minecraft.block.*;
import java.util.concurrent.atomic.*;
import net.minecraft.block.material.*;
import net.minecraft.client.*;
import java.util.*;
import me.snow.luigihack.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.init.*;
import net.minecraft.enchantment.*;
import net.minecraft.entity.player.*;

public class InventoryUtil2 implements Util
{
    private static /* synthetic */ int currentItem;
    public static /* synthetic */ short actionNumber;
    
    public static List<Integer> findEmptySlots(final boolean b) {
        final ArrayList<Object> list = new ArrayList<Object>();
        for (final Map.Entry<Integer, ItemStack> entry : getInventoryAndHotbarSlots().entrySet()) {
            if (!entry.getValue().isEmpty && entry.getValue().getItem() != Items.AIR) {
                continue;
            }
            list.add(entry.getKey());
        }
        if (b) {
            for (int i = 1; i < 5; ++i) {
                final ItemStack getStack = InventoryUtil2.mc.player.inventoryContainer.inventorySlots.get(i).getStack();
                if (getStack.isEmpty() || getStack.getItem() == Items.AIR) {
                    list.add(i);
                }
            }
        }
        return (List<Integer>)list;
    }
    
    public static int findStackInventory(final Item item) {
        return findStackInventory(item, false);
    }
    
    public static int findHotbarBlock(final Block block) {
        for (int i = 0; i < 9; ++i) {
            final ItemStack getStackInSlot = InventoryUtil2.mc.player.inventory.getStackInSlot(i);
            if (getStackInSlot != ItemStack.EMPTY && getStackInSlot.getItem() instanceof ItemBlock && ((ItemBlock)getStackInSlot.getItem()).getBlock() == block) {
                return i;
            }
        }
        return -1;
    }
    
    public static void clickWindow(final int n, final ClickType clickType) {
    }
    
    public static Map<Integer, ItemStack> getInventoryAndHotbarSlots() {
        return getInventorySlots(9, 44);
    }
    
    public static int findFirst(final Class<? extends Item> obj) {
        int n = -1;
        for (int i = 0; i < 9; ++i) {
            if (InventoryUtil2.mc.player.inventory.getStackInSlot(i).getItem().getClass().equals(obj)) {
                n = i;
                break;
            }
        }
        return n;
    }
    
    public static int findInventoryWool(final boolean b) {
        final AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.set(-1);
        for (final Map.Entry<Integer, ItemStack> entry : getInventoryAndHotbarSlots().entrySet()) {
            if (!(entry.getValue().getItem() instanceof ItemBlock)) {
                continue;
            }
            if (((ItemBlock)entry.getValue().getItem()).getBlock().material != Material.CLOTH) {
                continue;
            }
            if (entry.getKey() == 45 && !b) {
                continue;
            }
            atomicInteger.set(entry.getKey());
            return atomicInteger.get();
        }
        return atomicInteger.get();
    }
    
    public static int find(final Class<? extends Item> obj) {
        int n = -1;
        for (int i = 0; i < 9; ++i) {
            if (InventoryUtil2.mc.player.inventory.getStackInSlot(i).getItem().getClass().equals(obj)) {
                n = i;
            }
        }
        return n;
    }
    
    public static boolean areStacksCompatible(final ItemStack itemStack, final ItemStack itemStack2) {
        return itemStack.getItem().equals(itemStack2.getItem()) && (!(itemStack.getItem() instanceof ItemBlock) || !(itemStack2.getItem() instanceof ItemBlock) || ((ItemBlock)itemStack.getItem()).getBlock().material.equals(((ItemBlock)itemStack2.getItem()).getBlock().material)) && itemStack.getDisplayName().equals(itemStack2.getDisplayName()) && itemStack.getItemDamage() == itemStack2.getItemDamage();
    }
    
    public static int amountBlockInHotbar(final Block block) {
        return amountInHotbar(new ItemStack(block).getItem());
    }
    
    public static int getItemSlot(final Item item) {
        for (int i = 0; i < 36; ++i) {
            if (Minecraft.getMinecraft().player.inventory.getStackInSlot(i).getItem() == item) {
                if (i < 9) {
                    i += 36;
                }
                return i;
            }
        }
        return -1;
    }
    
    public static int findItemInHotbar(final Item item) {
        int n = -1;
        for (int i = 0; i < 9; ++i) {
            if (InventoryUtil2.mc.player.inventory.getStackInSlot(i).getItem() == item) {
                n = i;
                break;
            }
        }
        return n;
    }
    
    public static boolean isNull(final ItemStack itemStack) {
        return itemStack == null || itemStack.getItem() instanceof ItemAir;
    }
    
    static {
        InventoryUtil2.actionNumber = 0;
    }
    
    public static boolean holdingItem(final Class clazz) {
        final ItemStack getHeldItemMainhand = InventoryUtil2.mc.player.getHeldItemMainhand();
        boolean b = isInstanceOf(getHeldItemMainhand, clazz);
        if (!b) {
            InventoryUtil2.mc.player.getHeldItemOffhand();
            b = isInstanceOf(getHeldItemMainhand, clazz);
        }
        return b;
    }
    
    public static int findItemInventorySlot(final Item item, final boolean b) {
        final AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.set(-1);
        for (final Map.Entry<Integer, ItemStack> entry : getInventoryAndHotbarSlots().entrySet()) {
            if (entry.getValue().getItem() == item) {
                if (entry.getKey() == 45 && !b) {
                    continue;
                }
                atomicInteger.set(entry.getKey());
                return atomicInteger.get();
            }
        }
        return atomicInteger.get();
    }
    
    public static int amountInHotbar(final Item item, final boolean b) {
        int n = 0;
        for (int i = 44; i > 35; --i) {
            final ItemStack getStack = InventoryUtil2.mc.player.inventoryContainer.getSlot(i).getStack();
            if (getStack.getItem() == item) {
                n += getStack.getCount();
            }
        }
        if (InventoryUtil2.mc.player.getHeldItemOffhand().getItem() == item && b) {
            n += InventoryUtil2.mc.player.getHeldItemOffhand().getCount();
        }
        return n;
    }
    
    public static void pop() {
        InventoryUtil2.mc.player.inventory.currentItem = InventoryUtil2.currentItem;
    }
    
    public static int getItemFromHotbar(final Item item) {
        int n = -1;
        for (int i = 0; i < 9; ++i) {
            if (InventoryUtil2.mc.player.inventory.getStackInSlot(i).getItem() == item) {
                n = i;
                break;
            }
        }
        return n;
    }
    
    public static int pickItem(final int n, final boolean b) {
        final ArrayList<Object> list = new ArrayList<Object>();
        for (int i = 0; i < (b ? InventoryUtil2.mc.player.inventory.mainInventory.size() : 9); ++i) {
            if (Item.getIdFromItem(((ItemStack)InventoryUtil2.mc.player.inventory.mainInventory.get(i)).getItem()) == n) {
                list.add(InventoryUtil2.mc.player.inventory.mainInventory.get(i));
            }
        }
        if (list.size() >= 1) {
            return InventoryUtil2.mc.player.inventory.mainInventory.indexOf(list.get(0));
        }
        return -1;
    }
    
    public static boolean isSlotEmpty(final int n) {
        return InventoryUtil2.mc.player.inventoryContainer.inventorySlots.get(n).getStack().isEmpty();
    }
    
    public static int findFirst(final Item item) {
        int n = -1;
        for (int i = 0; i < 9; ++i) {
            if (InventoryUtil2.mc.player.inventory.getStackInSlot(i).getItem() == item) {
                n = i;
                break;
            }
        }
        return n;
    }
    
    public static void push() {
        InventoryUtil2.currentItem = InventoryUtil2.mc.player.inventory.currentItem;
    }
    
    public static int getHotbarItemSlot(final Item obj) {
        int n = -1;
        for (int i = 0; i < 9; ++i) {
            if (InventoryUtil2.mc.player.inventory.getStackInSlot(i).getItem().equals(obj)) {
                n = i;
                break;
            }
        }
        return n;
    }
    
    private static Map<Integer, ItemStack> getInventorySlots(final int n, final int n2) {
        final HashMap<Integer, ItemStack> hashMap = new HashMap<Integer, ItemStack>();
        for (int i = n; i <= n2; ++i) {
            hashMap.put(i, (ItemStack)InventoryUtil2.mc.player.inventoryContainer.getInventory().get(i));
        }
        return hashMap;
    }
    
    public static int findHotbarBlock(final Class clazz) {
        for (int i = 0; i < 9; ++i) {
            final ItemStack getStackInSlot = InventoryUtil2.mc.player.inventory.getStackInSlot(i);
            if (getStackInSlot != ItemStack.EMPTY) {
                if (clazz.isInstance(getStackInSlot.getItem())) {
                    return i;
                }
                if (getStackInSlot.getItem() instanceof ItemBlock) {
                    if (clazz.isInstance(((ItemBlock)getStackInSlot.getItem()).getBlock())) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }
    
    public static int findInventoryBlock(final Class clazz, final boolean b) {
        final AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.set(-1);
        for (final Map.Entry<Integer, ItemStack> entry : getInventoryAndHotbarSlots().entrySet()) {
            if (isBlock(entry.getValue().getItem(), clazz)) {
                if (entry.getKey() == 45 && !b) {
                    continue;
                }
                atomicInteger.set(entry.getKey());
                return atomicInteger.get();
            }
        }
        return atomicInteger.get();
    }
    
    public static boolean[] switchItem(final boolean b, final int n, final boolean b2, final Switch switch1, final Class clazz) {
        final boolean[] array = { b2, false };
        switch (switch1) {
            case NORMAL: {
                if (!b && !b2) {
                    switchToHotbarSlot(findHotbarBlock(clazz), false);
                    array[0] = true;
                }
                else if (b && b2) {
                    switchToHotbarSlot(n, false);
                    array[0] = false;
                }
                array[1] = true;
                break;
            }
            case SILENT: {
                if (!b && !b2) {
                    switchToHotbarSlot(findHotbarBlock(clazz), true);
                    array[0] = true;
                }
                else if (b && b2) {
                    array[0] = false;
                    LuigiHack.inventoryManager.recoverSilent(n);
                }
                array[1] = true;
                break;
            }
            case NONE: {
                array[1] = (b || InventoryUtil2.mc.player.inventory.currentItem == findHotbarBlock(clazz));
                break;
            }
        }
        return array;
    }
    
    public static void setSlot(final int currentItem) {
        if (currentItem > 8 || currentItem < 0) {
            return;
        }
        InventoryUtil2.mc.player.inventory.currentItem = currentItem;
    }
    
    public static int getSlot() {
        return InventoryUtil2.mc.player.inventory.currentItem;
    }
    
    public static List<Integer> getItemInventory(final Item obj) {
        final ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 9; i < 36; ++i) {
            InventoryUtil2.mc.player.inventory.getStackInSlot(i).getItem();
            if (obj instanceof ItemBlock) {
                if (((ItemBlock)obj).getBlock().equals(obj)) {
                    list.add(i);
                }
            }
        }
        if (list.size() == 0) {
            list.add(-1);
        }
        return list;
    }
    
    public static void switchToSlotGhost(final int n) {
        InventoryUtil2.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(n));
    }
    
    public static boolean switchTo(final Item item) {
        final int find = find(item);
        if (find == -1) {
            return false;
        }
        InventoryUtil2.mc.player.inventory.currentItem = find;
        InventoryUtil2.mc.playerController.updateController();
        return true;
    }
    
    public static int findItemInventorySlot(final Item item, final boolean b, final boolean b2) {
        int itemInventorySlot = findItemInventorySlot(item, b);
        if (itemInventorySlot == -1 && b2) {
            for (int i = 1; i < 5; ++i) {
                final ItemStack getStack = InventoryUtil2.mc.player.inventoryContainer.inventorySlots.get(i).getStack();
                if (getStack.getItem() != Items.AIR) {
                    if (getStack.getItem() == item) {
                        itemInventorySlot = i;
                    }
                }
            }
        }
        return itemInventorySlot;
    }
    
    public static boolean isBlock(final Item item, final Class clazz) {
        return item instanceof ItemBlock && clazz.isInstance(((ItemBlock)item).getBlock());
    }
    
    public static EntityEquipmentSlot getEquipmentFromSlot(final int n) {
        if (n == 5) {
            return EntityEquipmentSlot.HEAD;
        }
        if (n == 6) {
            return EntityEquipmentSlot.CHEST;
        }
        if (n == 7) {
            return EntityEquipmentSlot.LEGS;
        }
        return EntityEquipmentSlot.FEET;
    }
    
    public static int findBlockSlotInventory(final Class clazz, final boolean b, final boolean b2) {
        int inventoryBlock = findInventoryBlock(clazz, b);
        if (inventoryBlock == -1 && b2) {
            for (int i = 1; i < 5; ++i) {
                final ItemStack getStack = InventoryUtil2.mc.player.inventoryContainer.inventorySlots.get(i).getStack();
                if (getStack.getItem() != Items.AIR) {
                    final Item getItem = getStack.getItem();
                    if (clazz.isInstance(getItem)) {
                        inventoryBlock = i;
                    }
                    else if (getItem instanceof ItemBlock) {
                        if (clazz.isInstance(((ItemBlock)getItem).getBlock())) {
                            inventoryBlock = i;
                        }
                    }
                }
            }
        }
        return inventoryBlock;
    }
    
    public static int find(final Item item) {
        int n = -1;
        for (int i = 0; i < 9; ++i) {
            if (InventoryUtil2.mc.player.inventory.getStackInSlot(i).getItem() == item) {
                n = i;
            }
        }
        return n;
    }
    
    public static void switchToHotbarSlot(final Class clazz, final boolean b) {
        final int hotbarBlock = findHotbarBlock(clazz);
        if (hotbarBlock > -1) {
            switchToHotbarSlot(hotbarBlock, b);
        }
    }
    
    public static int amountInHotbar(final Item item) {
        return amountInHotbar(item, true);
    }
    
    public static int getEmptyXCarry() {
        for (int i = 1; i < 5; ++i) {
            final ItemStack getStack = InventoryUtil2.mc.player.inventoryContainer.inventorySlots.get(i).getStack();
            if (getStack.isEmpty() || getStack.getItem() == Items.AIR) {
                return i;
            }
        }
        return -1;
    }
    
    public static int findArmorSlot(final EntityEquipmentSlot entityEquipmentSlot, final boolean b, final boolean b2) {
        int armorSlot = findArmorSlot(entityEquipmentSlot, b);
        if (armorSlot == -1 && b2) {
            float n = 0.0f;
            for (int i = 1; i < 5; ++i) {
                final ItemStack getStack = InventoryUtil2.mc.player.inventoryContainer.inventorySlots.get(i).getStack();
                if (getStack.getItem() != Items.AIR && getStack.getItem() instanceof ItemArmor) {
                    final ItemArmor itemArmor;
                    if ((itemArmor = (ItemArmor)getStack.getItem()).getEquipmentSlot() == entityEquipmentSlot) {
                        final float n2 = (float)(itemArmor.damageReduceAmount + EnchantmentHelper.getEnchantmentLevel(Enchantments.PROTECTION, getStack));
                        final boolean b3 = b && EnchantmentHelper.hasBindingCurse(getStack);
                        if (n2 > n) {
                            if (!b3) {
                                n = n2;
                                armorSlot = i;
                            }
                        }
                    }
                }
            }
        }
        return armorSlot;
    }
    
    public static int findArmorSlot(final EntityEquipmentSlot entityEquipmentSlot, final boolean b) {
        int n = -1;
        float n2 = 0.0f;
        for (int i = 9; i < 45; ++i) {
            final ItemStack getStack = Minecraft.getMinecraft().player.inventoryContainer.getSlot(i).getStack();
            if (getStack.getItem() != Items.AIR && getStack.getItem() instanceof ItemArmor) {
                final ItemArmor itemArmor;
                if ((itemArmor = (ItemArmor)getStack.getItem()).getEquipmentSlot() == entityEquipmentSlot) {
                    final float n3 = (float)(itemArmor.damageReduceAmount + EnchantmentHelper.getEnchantmentLevel(Enchantments.PROTECTION, getStack));
                    final boolean b2 = b && EnchantmentHelper.hasBindingCurse(getStack);
                    if (n3 > n2) {
                        if (!b2) {
                            n2 = n3;
                            n = i;
                        }
                    }
                }
            }
        }
        return n;
    }
    
    public static boolean isInstanceOf(final ItemStack itemStack, final Class clazz) {
        if (itemStack == null) {
            return false;
        }
        final Item getItem = itemStack.getItem();
        return clazz.isInstance(getItem) || (getItem instanceof ItemBlock && clazz.isInstance(Block.getBlockFromItem(getItem)));
    }
    
    public static void moveItem(final int n, final int n2) {
        InventoryUtil2.mc.playerController.windowClick(InventoryUtil2.mc.player.inventoryContainer.windowId, n, 0, ClickType.PICKUP, (EntityPlayer)InventoryUtil2.mc.player);
        InventoryUtil2.mc.playerController.windowClick(InventoryUtil2.mc.player.inventoryContainer.windowId, n2, 0, ClickType.PICKUP, (EntityPlayer)InventoryUtil2.mc.player);
        InventoryUtil2.mc.playerController.windowClick(InventoryUtil2.mc.player.inventoryContainer.windowId, n, 0, ClickType.PICKUP, (EntityPlayer)InventoryUtil2.mc.player);
    }
    
    public static int findStackInventory(final Item item, final boolean b) {
        for (int i = b ? 0 : 9; i < 36; ++i) {
            if (Item.getIdFromItem(item) == Item.getIdFromItem(InventoryUtil2.mc.player.inventory.getStackInSlot(i).getItem())) {
                return i + ((i < 9) ? 36 : 0);
            }
        }
        return -1;
    }
    
    public static void switchToSlot(final int currentItem) {
        if (currentItem != -1 && InventoryUtil2.mc.player.inventory.currentItem != currentItem) {
            InventoryUtil2.mc.player.inventory.currentItem = currentItem;
            InventoryUtil2.mc.playerController.updateController();
        }
    }
    
    public static void switchToSlot(final Block block) {
        if (getBlockInHotbar(block) != -1 && InventoryUtil2.mc.player.inventory.currentItem != getBlockInHotbar(block)) {
            InventoryUtil2.mc.player.inventory.currentItem = getBlockInHotbar(block);
        }
    }
    
    public static int amountBlockInHotbar(final Block block, final boolean b) {
        return amountInHotbar(new ItemStack(block).getItem(), b);
    }
    
    public static int pickItem(final int n) {
        final ArrayList<Object> list = new ArrayList<Object>();
        for (int i = 0; i < 9; ++i) {
            if (Item.getIdFromItem(((ItemStack)InventoryUtil2.mc.player.inventory.mainInventory.get(i)).getItem()) == n) {
                list.add(InventoryUtil2.mc.player.inventory.mainInventory.get(i));
            }
        }
        if (list.size() >= 1) {
            return InventoryUtil2.mc.player.inventory.mainInventory.indexOf(list.get(0));
        }
        return -1;
    }
    
    public static int convertHotbarToInv(final int n) {
        return 36 + n;
    }
    
    public static boolean[] switchItemToItem(final boolean b, final int n, final boolean b2, final Switch switch1, final Item item) {
        final boolean[] array = { b2, false };
        switch (switch1) {
            case NORMAL: {
                if (!b && !b2) {
                    switchToHotbarSlot(getItemHotbar(item), false);
                    array[0] = true;
                }
                else if (b && b2) {
                    switchToHotbarSlot(n, false);
                    array[0] = false;
                }
                array[1] = true;
                break;
            }
            case SILENT: {
                if (!b && !b2) {
                    switchToHotbarSlot(getItemHotbar(item), true);
                    array[0] = true;
                }
                else if (b && b2) {
                    array[0] = false;
                    LuigiHack.inventoryManager.recoverSilent(n);
                }
                array[1] = true;
                break;
            }
            case NONE: {
                array[1] = (b || InventoryUtil2.mc.player.inventory.currentItem == getItemHotbar(item));
                break;
            }
        }
        return array;
    }
    
    public static void switchToHotbarSlot(final int currentItem, final boolean b) {
        if (InventoryUtil2.mc.player.inventory.currentItem == currentItem || currentItem < 0) {
            return;
        }
        if (b) {
            InventoryUtil2.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(currentItem));
            InventoryUtil2.mc.playerController.updateController();
        }
        else {
            InventoryUtil2.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(currentItem));
            InventoryUtil2.mc.player.inventory.currentItem = currentItem;
            InventoryUtil2.mc.playerController.updateController();
        }
    }
    
    public static int getBlockInHotbar(final Block obj) {
        for (int i = 0; i < 9; ++i) {
            final Item getItem = InventoryUtil2.mc.player.inventory.getStackInSlot(i).getItem();
            if (getItem instanceof ItemBlock && ((ItemBlock)getItem).getBlock().equals(obj)) {
                return i;
            }
        }
        return -1;
    }
    
    public static void confirmSlot(final int currentItem) {
        InventoryUtil2.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(currentItem));
        InventoryUtil2.mc.player.inventory.currentItem = currentItem;
        InventoryUtil2.mc.playerController.updateController();
    }
    
    public static int getItemHotbar(final Item item) {
        for (int i = 0; i < 9; ++i) {
            if (Item.getIdFromItem(InventoryUtil2.mc.player.inventory.getStackInSlot(i).getItem()) == Item.getIdFromItem(item)) {
                return i;
            }
        }
        return -1;
    }
    
    public enum Switch
    {
        SILENT, 
        NORMAL, 
        NONE;
    }
    
    public static class Task
    {
        private final /* synthetic */ int slot;
        private final /* synthetic */ boolean update;
        private final /* synthetic */ boolean quickClick;
        
        public Task(final int slot, final boolean quickClick) {
            this.slot = slot;
            this.quickClick = quickClick;
            this.update = false;
        }
        
        public void run() {
            if (this.update) {
                Util.mc.playerController.updateController();
            }
            if (this.slot != -1) {
                Util.mc.playerController.windowClick(0, this.slot, 0, this.quickClick ? ClickType.QUICK_MOVE : ClickType.PICKUP, (EntityPlayer)Util.mc.player);
            }
        }
        
        public Task(final int slot) {
            this.slot = slot;
            this.quickClick = false;
            this.update = false;
        }
        
        public Task() {
            this.update = true;
            this.slot = -1;
            this.quickClick = false;
        }
        
        public boolean isSwitching() {
            return !this.update;
        }
    }
}
