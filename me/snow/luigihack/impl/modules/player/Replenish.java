//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.player;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.item.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.init.*;
import java.util.*;
import net.minecraft.client.gui.inventory.*;
import java.util.concurrent.atomic.*;
import java.util.concurrent.*;

public class Replenish extends Module
{
    private final /* synthetic */ Setting<Boolean> pauseInv;
    private final /* synthetic */ Timer timer;
    private /* synthetic */ Map<Integer, ItemStack> hotbar;
    private final /* synthetic */ Setting<Boolean> putBack;
    private final /* synthetic */ Setting<Integer> updates;
    private final /* synthetic */ Setting<Integer> threshold;
    private final /* synthetic */ Timer replenishTimer;
    private final /* synthetic */ Setting<Integer> actions;
    private final /* synthetic */ Setting<Integer> replenishments;
    private final /* synthetic */ Queue<InventoryUtil.Task> taskList;
    
    public void onDisable() {
        this.hotbar.clear();
    }
    
    private void mapHotbar() {
        final ConcurrentHashMap<Integer, ItemStack> concurrentHashMap = new ConcurrentHashMap<Integer, ItemStack>();
        for (int i = 0; i < 9; ++i) {
            concurrentHashMap.put(i, Replenish.mc.player.inventory.getStackInSlot(i));
        }
        if (this.hotbar.isEmpty()) {
            this.hotbar = concurrentHashMap;
            return;
        }
        final ConcurrentHashMap<Integer, Integer> concurrentHashMap2 = new ConcurrentHashMap<Integer, Integer>();
        for (final Map.Entry<Integer, ItemStack> entry : concurrentHashMap.entrySet()) {
            final ItemStack itemStack = entry.getValue();
            final Integer n = entry.getKey();
            if (n != null && itemStack != null) {
                if (!itemStack.isEmpty && itemStack.getItem() != Items.AIR) {
                    if (itemStack.stackSize > (int)this.threshold.getValue()) {
                        continue;
                    }
                    if (itemStack.stackSize >= itemStack.getMaxStackSize()) {
                        continue;
                    }
                }
                ItemStack itemStack2 = entry.getValue();
                if (itemStack.isEmpty || itemStack.getItem() != Items.AIR) {
                    itemStack2 = this.hotbar.get(n);
                }
                if (itemStack2 == null || itemStack2.isEmpty || itemStack2.getItem() == Items.AIR) {
                    continue;
                }
                final int replenishSlot;
                if ((replenishSlot = this.getReplenishSlot(itemStack2)) == -1) {
                    continue;
                }
                concurrentHashMap2.put(replenishSlot, InventoryUtil.convertHotbarToInv((int)n));
            }
        }
        if (!concurrentHashMap2.isEmpty()) {
            for (final Map.Entry<Integer, Integer> entry2 : concurrentHashMap2.entrySet()) {
                this.taskList.add(new InventoryUtil.Task((int)entry2.getKey()));
                this.taskList.add(new InventoryUtil.Task((int)entry2.getValue()));
                this.taskList.add(new InventoryUtil.Task((int)entry2.getKey()));
                this.taskList.add(new InventoryUtil.Task());
            }
        }
        this.hotbar = concurrentHashMap;
    }
    
    public void onUpdate() {
        if (Replenish.mc.currentScreen instanceof GuiContainer && (!(Replenish.mc.currentScreen instanceof GuiInventory) || (boolean)this.pauseInv.getValue())) {
            return;
        }
        if (this.timer.passedMs((long)(int)this.updates.getValue())) {
            this.mapHotbar();
        }
        if (this.replenishTimer.passedMs((long)(int)this.replenishments.getValue())) {
            for (int i = 0; i < (int)this.actions.getValue(); ++i) {
                final InventoryUtil.Task task = this.taskList.poll();
                if (task != null) {
                    task.run();
                }
            }
            this.replenishTimer.reset();
        }
    }
    
    public void onLogout() {
        this.onDisable();
    }
    
    private int getReplenishSlot(final ItemStack itemStack) {
        final AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.set(-1);
        for (final Map.Entry<Integer, V> entry : InventoryUtil.getInventoryAndHotbarSlots().entrySet()) {
            if (entry.getKey() < 36) {
                if (!InventoryUtil.areStacksCompatible(itemStack, (ItemStack)entry.getValue())) {
                    continue;
                }
                atomicInteger.set(entry.getKey());
                return atomicInteger.get();
            }
        }
        return atomicInteger.get();
    }
    
    public Replenish() {
        super("Replenish", "Replenishes your hotbar", Module.Category.PLAYER, false, false, false);
        this.threshold = (Setting<Integer>)this.register(new Setting("Threshold", (Object)0, (Object)0, (Object)63));
        this.replenishments = (Setting<Integer>)this.register(new Setting("RUpdates", (Object)0, (Object)0, (Object)1000));
        this.updates = (Setting<Integer>)this.register(new Setting("HBUpdates", (Object)100, (Object)0, (Object)1000));
        this.actions = (Setting<Integer>)this.register(new Setting("Actions", (Object)2, (Object)1, (Object)30));
        this.pauseInv = (Setting<Boolean>)this.register(new Setting("PauseInv", (Object)true));
        this.putBack = (Setting<Boolean>)this.register(new Setting("PutBack", (Object)true));
        this.timer = new Timer();
        this.replenishTimer = new Timer();
        this.taskList = new ConcurrentLinkedQueue<InventoryUtil.Task>();
        this.hotbar = new ConcurrentHashMap<Integer, ItemStack>();
    }
}
