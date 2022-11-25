//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.combat;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.gui.inventory.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.init.*;
import net.minecraft.inventory.*;
import me.snow.luigihack.impl.modules.player.*;
import me.snow.luigihack.*;
import net.minecraft.item.*;
import java.util.concurrent.*;
import java.util.*;
import net.minecraftforge.fml.common.gameevent.*;
import org.lwjgl.input.*;
import me.snow.luigihack.impl.gui.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class AutoArmor extends Module
{
    private final /* synthetic */ Setting<Integer> closestEnemy;
    private final /* synthetic */ Setting<Integer> chestThreshold;
    private final /* synthetic */ Setting<Integer> helmetThreshold;
    private final /* synthetic */ Setting<Bind> elytraBind;
    private final /* synthetic */ Setting<Integer> bootsThreshold;
    private /* synthetic */ boolean elytraOn;
    private final /* synthetic */ Setting<Boolean> updateController;
    private final /* synthetic */ Setting<Integer> actions;
    private final /* synthetic */ List<Integer> doneSlots;
    private final /* synthetic */ Setting<Integer> delay;
    private final /* synthetic */ Timer elytraTimer;
    private final /* synthetic */ Setting<Boolean> tps;
    private final /* synthetic */ Setting<Integer> legThreshold;
    private final /* synthetic */ Setting<Boolean> shiftClick;
    private final /* synthetic */ Setting<Boolean> curse;
    private final /* synthetic */ Timer timer;
    private final /* synthetic */ Setting<Boolean> mendingTakeOff;
    private final /* synthetic */ Queue<InventoryUtil.Task> taskList;
    
    private boolean isSafe() {
        final EntityPlayer closestEnemy = EntityUtil.getClosestEnemy((double)(int)this.closestEnemy.getValue());
        return closestEnemy == null || AutoArmor.mc.player.getDistanceSq((Entity)closestEnemy) >= MathUtil.square((float)(int)this.closestEnemy.getValue());
    }
    
    @Override
    public void onDisable() {
        this.taskList.clear();
        this.doneSlots.clear();
        this.elytraOn = false;
    }
    
    @Override
    public void onLogout() {
        this.taskList.clear();
        this.doneSlots.clear();
    }
    
    @Override
    public void onTick() {
        if (fullNullCheck() || (AutoArmor.mc.currentScreen instanceof GuiContainer && !(AutoArmor.mc.currentScreen instanceof GuiInventory))) {
            return;
        }
        if (this.taskList.isEmpty()) {
            if ((boolean)this.mendingTakeOff.getValue() && InventoryUtil.holdingItem((Class)ItemExpBottle.class) && AutoArmor.mc.gameSettings.keyBindUseItem.isKeyDown() && (this.isSafe() || EntityUtil.isSafe((Entity)AutoArmor.mc.player, 1, false, true))) {
                final ItemStack getStack = AutoArmor.mc.player.inventoryContainer.getSlot(5).getStack();
                if (!getStack.isEmpty && DamageUtil.getRoundedDamage(getStack) >= (int)this.helmetThreshold.getValue()) {
                    this.takeOffSlot(5);
                }
                final ItemStack getStack2 = AutoArmor.mc.player.inventoryContainer.getSlot(6).getStack();
                if (!getStack2.isEmpty && DamageUtil.getRoundedDamage(getStack2) >= (int)this.chestThreshold.getValue()) {
                    this.takeOffSlot(6);
                }
                final ItemStack getStack3 = AutoArmor.mc.player.inventoryContainer.getSlot(7).getStack();
                if (!getStack3.isEmpty && DamageUtil.getRoundedDamage(getStack3) >= (int)this.legThreshold.getValue()) {
                    this.takeOffSlot(7);
                }
                final ItemStack getStack4 = AutoArmor.mc.player.inventoryContainer.getSlot(8).getStack();
                if (!getStack4.isEmpty && DamageUtil.getRoundedDamage(getStack4) >= (int)this.bootsThreshold.getValue()) {
                    this.takeOffSlot(8);
                }
                return;
            }
            final int armorSlot;
            if (AutoArmor.mc.player.inventoryContainer.getSlot(5).getStack().getItem() == Items.AIR && (armorSlot = InventoryUtil.findArmorSlot(EntityEquipmentSlot.HEAD, (boolean)this.curse.getValue(), XCarry.getInstance().isOn())) != -1) {
                this.getSlotOn(5, armorSlot);
            }
            final ItemStack getStack5;
            if ((getStack5 = AutoArmor.mc.player.inventoryContainer.getSlot(6).getStack()).getItem() == Items.AIR) {
                if (this.taskList.isEmpty()) {
                    if (this.elytraOn && this.elytraTimer.passedMs(500L)) {
                        final int itemInventorySlot = InventoryUtil.findItemInventorySlot(Items.ELYTRA, false, XCarry.getInstance().isOn());
                        if (itemInventorySlot != -1) {
                            if ((itemInventorySlot < 5 && itemInventorySlot > 1) || !(boolean)this.shiftClick.getValue()) {
                                this.taskList.add(new InventoryUtil.Task(itemInventorySlot));
                                this.taskList.add(new InventoryUtil.Task(6));
                            }
                            else {
                                this.taskList.add(new InventoryUtil.Task(itemInventorySlot, true));
                            }
                            if (this.updateController.getValue()) {
                                this.taskList.add(new InventoryUtil.Task());
                            }
                            this.elytraTimer.reset();
                        }
                    }
                    else {
                        final int armorSlot2;
                        if (!this.elytraOn && (armorSlot2 = InventoryUtil.findArmorSlot(EntityEquipmentSlot.CHEST, (boolean)this.curse.getValue(), XCarry.getInstance().isOn())) != -1) {
                            this.getSlotOn(6, armorSlot2);
                        }
                    }
                }
            }
            else if (this.elytraOn && getStack5.getItem() != Items.ELYTRA && this.elytraTimer.passedMs(500L)) {
                if (this.taskList.isEmpty()) {
                    final int itemInventorySlot2 = InventoryUtil.findItemInventorySlot(Items.ELYTRA, false, XCarry.getInstance().isOn());
                    if (itemInventorySlot2 != -1) {
                        this.taskList.add(new InventoryUtil.Task(itemInventorySlot2));
                        this.taskList.add(new InventoryUtil.Task(6));
                        this.taskList.add(new InventoryUtil.Task(itemInventorySlot2));
                        if (this.updateController.getValue()) {
                            this.taskList.add(new InventoryUtil.Task());
                        }
                    }
                    this.elytraTimer.reset();
                }
            }
            else if (!this.elytraOn && getStack5.getItem() == Items.ELYTRA && this.elytraTimer.passedMs(500L) && this.taskList.isEmpty()) {
                int n = InventoryUtil.findItemInventorySlot((Item)Items.DIAMOND_CHESTPLATE, false, XCarry.getInstance().isOn());
                if (n == -1 && (n = InventoryUtil.findItemInventorySlot((Item)Items.IRON_CHESTPLATE, false, XCarry.getInstance().isOn())) == -1 && (n = InventoryUtil.findItemInventorySlot((Item)Items.GOLDEN_CHESTPLATE, false, XCarry.getInstance().isOn())) == -1 && (n = InventoryUtil.findItemInventorySlot((Item)Items.CHAINMAIL_CHESTPLATE, false, XCarry.getInstance().isOn())) == -1) {
                    n = InventoryUtil.findItemInventorySlot((Item)Items.LEATHER_CHESTPLATE, false, XCarry.getInstance().isOn());
                }
                if (n != -1) {
                    this.taskList.add(new InventoryUtil.Task(n));
                    this.taskList.add(new InventoryUtil.Task(6));
                    this.taskList.add(new InventoryUtil.Task(n));
                    if (this.updateController.getValue()) {
                        this.taskList.add(new InventoryUtil.Task());
                    }
                }
                this.elytraTimer.reset();
            }
            final int armorSlot3;
            if (AutoArmor.mc.player.inventoryContainer.getSlot(7).getStack().getItem() == Items.AIR && (armorSlot3 = InventoryUtil.findArmorSlot(EntityEquipmentSlot.LEGS, (boolean)this.curse.getValue(), XCarry.getInstance().isOn())) != -1) {
                this.getSlotOn(7, armorSlot3);
            }
            final int armorSlot4;
            if (AutoArmor.mc.player.inventoryContainer.getSlot(8).getStack().getItem() == Items.AIR && (armorSlot4 = InventoryUtil.findArmorSlot(EntityEquipmentSlot.FEET, (boolean)this.curse.getValue(), XCarry.getInstance().isOn())) != -1) {
                this.getSlotOn(8, armorSlot4);
            }
        }
        if (this.timer.passedMs((long)(int)((int)this.delay.getValue() * (this.tps.getValue() ? LuigiHack.serverManager.getTpsFactor() : 1.0f)))) {
            if (!this.taskList.isEmpty()) {
                for (int i = 0; i < (int)this.actions.getValue(); ++i) {
                    final InventoryUtil.Task task = this.taskList.poll();
                    if (task != null) {
                        task.run();
                    }
                }
            }
            this.timer.reset();
        }
    }
    
    private void takeOffSlot(final int n) {
        if (this.taskList.isEmpty()) {
            int i = -1;
            for (final int intValue : InventoryUtil.findEmptySlots(XCarry.getInstance().isOn())) {
                if (this.doneSlots.contains(i)) {
                    continue;
                }
                i = intValue;
                this.doneSlots.add(intValue);
            }
            if (i != -1) {
                if ((i < 5 && i > 0) || !(boolean)this.shiftClick.getValue()) {
                    this.taskList.add(new InventoryUtil.Task(n));
                    this.taskList.add(new InventoryUtil.Task(i));
                }
                else {
                    this.taskList.add(new InventoryUtil.Task(n, true));
                }
                if (this.updateController.getValue()) {
                    this.taskList.add(new InventoryUtil.Task());
                }
            }
        }
    }
    
    public AutoArmor() {
        super("AutoArmor", "Puts Armor on for you.", Category.COMBAT, true, false, false);
        this.delay = (Setting<Integer>)this.register(new Setting("Delay", (Object)50, (Object)0, (Object)500));
        this.mendingTakeOff = (Setting<Boolean>)this.register(new Setting("AutoMend", (Object)true));
        this.closestEnemy = (Setting<Integer>)this.register(new Setting("Enemy", (Object)8, (Object)1, (Object)20, p0 -> (boolean)this.mendingTakeOff.getValue()));
        this.helmetThreshold = (Setting<Integer>)this.register(new Setting("Helmet%", (Object)100, (Object)1, (Object)100, p0 -> (boolean)this.mendingTakeOff.getValue()));
        this.chestThreshold = (Setting<Integer>)this.register(new Setting("Chest%", (Object)100, (Object)1, (Object)100, p0 -> (boolean)this.mendingTakeOff.getValue()));
        this.legThreshold = (Setting<Integer>)this.register(new Setting("Legs%", (Object)100, (Object)1, (Object)100, p0 -> (boolean)this.mendingTakeOff.getValue()));
        this.bootsThreshold = (Setting<Integer>)this.register(new Setting("Boots%", (Object)100, (Object)1, (Object)100, p0 -> (boolean)this.mendingTakeOff.getValue()));
        this.curse = (Setting<Boolean>)this.register(new Setting("CurseOfBinding", (Object)false));
        this.actions = (Setting<Integer>)this.register(new Setting("Actions", (Object)3, (Object)1, (Object)12));
        this.elytraBind = (Setting<Bind>)this.register(new Setting("Elytra", (Object)new Bind(-1)));
        this.tps = (Setting<Boolean>)this.register(new Setting("TpsSync", (Object)true));
        this.updateController = (Setting<Boolean>)this.register(new Setting("Update", (Object)true));
        this.shiftClick = (Setting<Boolean>)this.register(new Setting("ShiftClick", (Object)false));
        this.timer = new Timer();
        this.elytraTimer = new Timer();
        this.taskList = new ConcurrentLinkedQueue<InventoryUtil.Task>();
        this.doneSlots = new ArrayList<Integer>();
    }
    
    @SubscribeEvent
    public void onKeyInput(final InputEvent.KeyInputEvent keyInputEvent) {
        if (Keyboard.getEventKeyState() && !(AutoArmor.mc.currentScreen instanceof LuigiGui) && ((Bind)this.elytraBind.getValue()).getKey() == Keyboard.getEventKey()) {
            this.elytraOn = !this.elytraOn;
        }
    }
    
    @Override
    public String getDisplayInfo() {
        if (this.elytraOn) {
            return "Elytra";
        }
        return null;
    }
    
    @Override
    public void onLogin() {
        this.timer.reset();
        this.elytraTimer.reset();
    }
    
    private void getSlotOn(final int n, final int i) {
        if (this.taskList.isEmpty()) {
            this.doneSlots.remove((Object)i);
            if ((i < 5 && i > 0) || !(boolean)this.shiftClick.getValue()) {
                this.taskList.add(new InventoryUtil.Task(i));
                this.taskList.add(new InventoryUtil.Task(n));
            }
            else {
                this.taskList.add(new InventoryUtil.Task(i, true));
            }
            if (this.updateController.getValue()) {
                this.taskList.add(new InventoryUtil.Task());
            }
        }
    }
}
