//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util.cc;

import me.snow.luigihack.api.util.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraft.network.play.client.*;
import net.minecraft.entity.player.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.network.*;
import me.snow.luigihack.mixin.mixins.accessors.*;

public class InventoryManagerCC implements Util
{
    private /* synthetic */ int serverSlot;
    
    public void switchToBlock(final Block[] array, final Switch switch1) {
        final Item[] array2 = new Item[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = Item.getItemFromBlock(array[i]);
        }
        this.switchToItem(array2, switch1);
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        if (send.getPacket() instanceof CPacketHeldItemChange) {
            if (!InventoryPlayer.isHotbar(((CPacketHeldItemChange)send.getPacket()).getSlotId())) {
                send.setCanceled(true);
                return;
            }
            this.serverSlot = ((CPacketHeldItemChange)send.getPacket()).getSlotId();
        }
    }
    
    public void switchToItem(final Item[] array, final Switch switch1) {
        int n = -1;
        for (int i = InventoryRegion.HOTBAR.getStart(); i < InventoryRegion.HOTBAR.getBound(); ++i) {
            for (int length = array.length, j = 0; j < length; ++j) {
                if (InventoryManagerCC.mc.player.inventory.getStackInSlot(i).getItem().equals(array[j])) {
                    n = i;
                    break;
                }
            }
            if (n != -1) {
                break;
            }
        }
        this.switchToSlot(n, switch1);
    }
    
    public int searchSlot(final Class<? extends Item> clazz, final InventoryRegion inventoryRegion) {
        int n = -1;
        for (int i = inventoryRegion.getStart(); i <= inventoryRegion.getBound(); ++i) {
            if (clazz.isInstance(InventoryManagerCC.mc.player.inventory.getStackInSlot(i).getItem())) {
                n = i;
                break;
            }
        }
        return n;
    }
    
    public void switchToItem(final Class<? extends Item> clazz, final Switch switch1) {
        this.switchToSlot(this.searchSlot(clazz, InventoryRegion.HOTBAR), switch1);
    }
    
    public void switchToSlot(final int n, final Switch switch1) {
        if (InventoryPlayer.isHotbar(n) && InventoryManagerCC.mc.player.inventory.currentItem != n) {
            switch (switch1) {
                case NORMAL: {
                    InventoryManagerCC.mc.player.inventory.currentItem = n;
                    InventoryManagerCC.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(n));
                    break;
                }
                case PACKET: {
                    InventoryManagerCC.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(n));
                    ((IPlayerControllerMP)InventoryManagerCC.mc.playerController).setCurrentPlayerItem(n);
                    break;
                }
            }
        }
    }
    
    public InventoryManagerCC() {
        this.serverSlot = -1;
    }
    
    public int searchSlot(final Item obj, final InventoryRegion inventoryRegion) {
        int n = -1;
        for (int i = inventoryRegion.getStart(); i < inventoryRegion.getBound(); ++i) {
            if (InventoryManagerCC.mc.player.inventory.getStackInSlot(i).getItem().equals(obj)) {
                n = i;
                break;
            }
        }
        return n;
    }
    
    public int getServerSlot() {
        return this.serverSlot;
    }
    
    public int searchSlot(final Block[] array, final InventoryRegion inventoryRegion) {
        int n = -1;
        for (int i = inventoryRegion.getStart(); i < inventoryRegion.getBound(); ++i) {
            for (final Block block : array) {
                if (n == -1 && InventoryManagerCC.mc.player.inventory.getStackInSlot(i).getItem().equals(Item.getItemFromBlock(block))) {
                    n = i;
                    break;
                }
            }
        }
        return n;
    }
    
    public void switchToItem(final Item item, final Switch switch1) {
        this.switchToSlot(this.searchSlot(item, InventoryRegion.HOTBAR), switch1);
    }
    
    public void syncSlot() {
        if (this.serverSlot != InventoryManagerCC.mc.player.inventory.currentItem) {
            InventoryManagerCC.mc.player.inventory.currentItem = this.serverSlot;
        }
    }
    
    public void switchToBlock(final Block block, final Switch switch1) {
        this.switchToItem(Item.getItemFromBlock(block), switch1);
    }
    
    public enum InventoryRegion
    {
        ARMOR(100, 103), 
        CRAFTING(80, 83);
        
        private final /* synthetic */ int bound;
        
        INVENTORY(0, 45);
        
        private final /* synthetic */ int start;
        
        HOTBAR(0, 8);
        
        public int getStart() {
            return this.start;
        }
        
        public int getBound() {
            return this.bound;
        }
        
        private InventoryRegion(final int start, final int bound) {
            this.start = start;
            this.bound = bound;
        }
    }
    
    public enum Switch
    {
        NORMAL, 
        PACKET, 
        NONE;
    }
}
