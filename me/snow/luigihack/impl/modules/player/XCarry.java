//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.player;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import java.util.concurrent.atomic.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.network.play.client.*;
import net.minecraftforge.fml.common.gameevent.*;
import me.snow.luigihack.impl.gui.*;
import me.snow.luigihack.impl.command.*;
import org.lwjgl.input.*;
import net.minecraft.inventory.*;
import java.util.*;
import net.minecraft.network.*;
import java.util.concurrent.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.gui.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.snow.luigihack.api.event.events.*;
import java.io.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.entity.player.*;

public class XCarry extends Module
{
    private /* synthetic */ boolean autoDuelOn;
    private final /* synthetic */ Setting<Boolean> store;
    private final /* synthetic */ Setting<Boolean> shiftClicker;
    private final /* synthetic */ Setting<Integer> tasks;
    private /* synthetic */ boolean slot3done;
    private /* synthetic */ boolean guiCloseGuard;
    private /* synthetic */ boolean obbySlotDone;
    private final /* synthetic */ Setting<Bind> keyBind;
    private /* synthetic */ List<Integer> doneSlots;
    private final /* synthetic */ Setting<Boolean> withShift;
    private final /* synthetic */ AtomicBoolean guiNeedsClose;
    private final /* synthetic */ Setting<Integer> slot1;
    private final /* synthetic */ Setting<Boolean> simpleMode;
    private static /* synthetic */ XCarry INSTANCE;
    private /* synthetic */ boolean slot2done;
    private /* synthetic */ boolean slot1done;
    private final /* synthetic */ Setting<Bind> autoStore;
    private final /* synthetic */ Queue<InventoryUtil.Task> taskList;
    private final /* synthetic */ Setting<Integer> obbySlot;
    private final /* synthetic */ Setting<Integer> slot2;
    private final /* synthetic */ Setting<Integer> slot3;
    private /* synthetic */ GuiInventory openedGui;
    
    @SubscribeEvent
    public void onCloseGuiScreen(final PacketEvent.Send send) {
        if ((boolean)this.simpleMode.getValue() && send.getPacket() instanceof CPacketCloseWindow && ((CPacketCloseWindow)send.getPacket()).windowId == XCarry.mc.player.inventoryContainer.windowId) {
            send.setCanceled(true);
        }
    }
    
    private void close() {
        this.openedGui = null;
        this.guiNeedsClose.set(false);
        this.guiCloseGuard = false;
    }
    
    @SubscribeEvent
    public void onKeyInput(final InputEvent.KeyInputEvent keyInputEvent) {
        if (Keyboard.getEventKeyState() && !(XCarry.mc.currentScreen instanceof LuigiGui) && ((Bind)this.autoStore.getValue()).getKey() == Keyboard.getEventKey()) {
            this.autoDuelOn = !this.autoDuelOn;
            Command.sendMessage("<XCarry> §aAutostoring...");
        }
    }
    
    static {
        XCarry.INSTANCE = new XCarry();
    }
    
    private void setInstance() {
        XCarry.INSTANCE = this;
    }
    
    public static XCarry getInstance() {
        if (XCarry.INSTANCE == null) {
            XCarry.INSTANCE = new XCarry();
        }
        return XCarry.INSTANCE;
    }
    
    public void onUpdate() {
        if ((boolean)this.shiftClicker.getValue() && XCarry.mc.currentScreen instanceof GuiInventory) {
            final boolean b = ((Bind)this.keyBind.getValue()).getKey() != -1 && Keyboard.isKeyDown(((Bind)this.keyBind.getValue()).getKey()) && !Keyboard.isKeyDown(42);
            final Slot slotUnderMouse;
            if (((Keyboard.isKeyDown(42) && (boolean)this.withShift.getValue()) || b) && Mouse.isButtonDown(0) && (slotUnderMouse = ((GuiInventory)XCarry.mc.currentScreen).getSlotUnderMouse()) != null && InventoryUtil.getEmptyXCarry() != -1) {
                final int slotNumber = slotUnderMouse.slotNumber;
                if (slotNumber > 4 && b) {
                    this.taskList.add(new InventoryUtil.Task(slotNumber));
                    this.taskList.add(new InventoryUtil.Task(InventoryUtil.getEmptyXCarry()));
                }
                else if (slotNumber > 4 && (boolean)this.withShift.getValue()) {
                    boolean b2 = true;
                    boolean b3 = true;
                    for (final int intValue : InventoryUtil.findEmptySlots(false)) {
                        if (intValue > 4 && intValue < 36) {
                            b3 = false;
                        }
                        else {
                            if (intValue <= 35) {
                                continue;
                            }
                            if (intValue >= 45) {
                                continue;
                            }
                            b2 = false;
                        }
                    }
                    if (slotNumber > 35 && slotNumber < 45) {
                        if (b3) {
                            this.taskList.add(new InventoryUtil.Task(slotNumber));
                            this.taskList.add(new InventoryUtil.Task(InventoryUtil.getEmptyXCarry()));
                        }
                    }
                    else if (b2) {
                        this.taskList.add(new InventoryUtil.Task(slotNumber));
                        this.taskList.add(new InventoryUtil.Task(InventoryUtil.getEmptyXCarry()));
                    }
                }
            }
        }
        if (this.autoDuelOn) {
            this.doneSlots = new ArrayList<Integer>();
            if (InventoryUtil.getEmptyXCarry() == -1 || (this.obbySlotDone && this.slot1done && this.slot2done && this.slot3done)) {
                this.autoDuelOn = false;
            }
            if (this.autoDuelOn) {
                if (!this.obbySlotDone && !XCarry.mc.player.inventory.getStackInSlot((int)this.obbySlot.getValue() - 1).isEmpty) {
                    this.addTasks(36 + (int)this.obbySlot.getValue() - 1);
                }
                this.obbySlotDone = true;
                if (!this.slot1done && !XCarry.mc.player.inventoryContainer.inventorySlots.get((int)this.slot1.getValue()).getStack().isEmpty) {
                    this.addTasks((int)this.slot1.getValue());
                }
                this.slot1done = true;
                if (!this.slot2done && !XCarry.mc.player.inventoryContainer.inventorySlots.get((int)this.slot2.getValue()).getStack().isEmpty) {
                    this.addTasks((int)this.slot2.getValue());
                }
                this.slot2done = true;
                if (!this.slot3done && !XCarry.mc.player.inventoryContainer.inventorySlots.get((int)this.slot3.getValue()).getStack().isEmpty) {
                    this.addTasks((int)this.slot3.getValue());
                }
                this.slot3done = true;
            }
        }
        else {
            this.obbySlotDone = false;
            this.slot1done = false;
            this.slot2done = false;
            this.slot3done = false;
        }
        if (!this.taskList.isEmpty()) {
            for (int i = 0; i < (int)this.tasks.getValue(); ++i) {
                final InventoryUtil.Task task = this.taskList.poll();
                if (task != null) {
                    task.run();
                }
            }
        }
    }
    
    public void onLogout() {
        this.onDisable();
    }
    
    public void onDisable() {
        if (fullNullCheck()) {
            return;
        }
        if (!(boolean)this.simpleMode.getValue()) {
            this.closeGui();
            this.close();
        }
        else {
            XCarry.mc.player.connection.sendPacket((Packet)new CPacketCloseWindow(XCarry.mc.player.inventoryContainer.windowId));
        }
    }
    
    public XCarry() {
        super("XCarry", "Uses the crafting inventory for storage", Module.Category.PLAYER, true, false, false);
        this.simpleMode = (Setting<Boolean>)this.register(new Setting("Simple", (Object)false));
        this.autoStore = (Setting<Bind>)this.register(new Setting("AutoDuel", (Object)new Bind(-1)));
        this.obbySlot = (Setting<Integer>)this.register(new Setting("ObbySlot", (Object)2, (Object)1, (Object)9, p0 -> ((Bind)this.autoStore.getValue()).getKey() != -1));
        this.slot1 = (Setting<Integer>)this.register(new Setting("Slot1", (Object)22, (Object)9, (Object)44, p0 -> ((Bind)this.autoStore.getValue()).getKey() != -1));
        this.slot2 = (Setting<Integer>)this.register(new Setting("Slot2", (Object)23, (Object)9, (Object)44, p0 -> ((Bind)this.autoStore.getValue()).getKey() != -1));
        this.slot3 = (Setting<Integer>)this.register(new Setting("Slot3", (Object)24, (Object)9, (Object)44, p0 -> ((Bind)this.autoStore.getValue()).getKey() != -1));
        this.tasks = (Setting<Integer>)this.register(new Setting("Actions", (Object)3, (Object)1, (Object)12, p0 -> ((Bind)this.autoStore.getValue()).getKey() != -1));
        this.store = (Setting<Boolean>)this.register(new Setting("Store", (Object)false));
        this.shiftClicker = (Setting<Boolean>)this.register(new Setting("ShiftClick", (Object)false));
        this.withShift = (Setting<Boolean>)this.register(new Setting("WithShift", (Object)Boolean.TRUE, p0 -> (boolean)this.shiftClicker.getValue()));
        this.keyBind = (Setting<Bind>)this.register(new Setting("ShiftBind", (Object)new Bind(-1), p0 -> (boolean)this.shiftClicker.getValue()));
        this.guiNeedsClose = new AtomicBoolean(false);
        this.taskList = new ConcurrentLinkedQueue<InventoryUtil.Task>();
        this.doneSlots = new ArrayList<Integer>();
        this.setInstance();
    }
    
    private GuiInventory createGuiWrapper(final GuiInventory guiInventory) {
        try {
            final GuiInventoryWrapper guiInventoryWrapper = new GuiInventoryWrapper();
            ReflectionUtil.copyOf((Object)guiInventory, (Object)guiInventoryWrapper);
            return guiInventoryWrapper;
        }
        catch (IllegalAccessException | NoSuchFieldException ex) {
            final Throwable t;
            t.printStackTrace();
            return null;
        }
    }
    
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onGuiOpen(final GuiOpenEvent guiOpenEvent) {
        if (!(boolean)this.simpleMode.getValue()) {
            if (this.guiCloseGuard) {
                guiOpenEvent.setCanceled(true);
            }
            else if (guiOpenEvent.getGui() instanceof GuiInventory) {
                this.openedGui = this.createGuiWrapper((GuiInventory)guiOpenEvent.getGui());
                guiOpenEvent.setGui((GuiScreen)this.openedGui);
                this.guiNeedsClose.set(false);
            }
        }
    }
    
    @SubscribeEvent
    public void onSettingChange(final ClientEvent clientEvent) {
        if (clientEvent.getStage() == 2 && clientEvent.getSetting() != null && clientEvent.getSetting().getFeature() != null && clientEvent.getSetting().getFeature().equals(this)) {
            final Setting setting = clientEvent.getSetting();
            final String name = clientEvent.getSetting().getName();
            if (setting.equals(this.simpleMode) && setting.getPlannedValue() != setting.getValue()) {
                this.disable();
            }
            else if (name.equalsIgnoreCase("Store")) {
                clientEvent.setCanceled(true);
                this.autoDuelOn = !this.autoDuelOn;
                Command.sendMessage("<XCarry> §aAutostoring...");
            }
        }
    }
    
    private void addTasks(final int n) {
        if (InventoryUtil.getEmptyXCarry() != -1) {
            int emptyXCarry = InventoryUtil.getEmptyXCarry();
            if ((this.doneSlots.contains(emptyXCarry) || !InventoryUtil.isSlotEmpty(emptyXCarry)) && (this.doneSlots.contains(++emptyXCarry) || !InventoryUtil.isSlotEmpty(emptyXCarry)) && (this.doneSlots.contains(++emptyXCarry) || !InventoryUtil.isSlotEmpty(emptyXCarry)) && (this.doneSlots.contains(++emptyXCarry) || !InventoryUtil.isSlotEmpty(emptyXCarry))) {
                return;
            }
            if (emptyXCarry > 4) {
                return;
            }
            this.doneSlots.add(emptyXCarry);
            this.taskList.add(new InventoryUtil.Task(n));
            this.taskList.add(new InventoryUtil.Task(emptyXCarry));
            this.taskList.add(new InventoryUtil.Task());
        }
    }
    
    private void closeGui() {
        if (fullNullCheck()) {
            return;
        }
        if (this.guiNeedsClose.compareAndSet(true, false) && !fullNullCheck()) {
            this.guiCloseGuard = true;
            XCarry.mc.player.closeScreen();
            if (this.openedGui != null) {
                this.openedGui.onGuiClosed();
                this.openedGui = null;
            }
            this.guiCloseGuard = false;
        }
    }
    
    private class GuiInventoryWrapper extends GuiInventory
    {
        public void onGuiClosed() {
            if (XCarry.this.guiCloseGuard || !XCarry.this.isEnabled()) {
                super.onGuiClosed();
            }
        }
        
        protected void keyTyped(final char c, final int n) throws IOException {
            if (XCarry.this.isEnabled() && (n == 1 || this.mc.gameSettings.keyBindInventory.isActiveAndMatches(n))) {
                XCarry.this.guiNeedsClose.set(true);
                this.mc.displayGuiScreen((GuiScreen)null);
            }
            else {
                super.keyTyped(c, n);
            }
        }
        
        GuiInventoryWrapper() {
            super((EntityPlayer)Util.mc.player);
        }
    }
}
