//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.combat;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.impl.command.*;
import net.minecraft.entity.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.potion.*;
import net.minecraft.init.*;
import net.minecraft.network.play.client.*;
import net.minecraft.util.math.*;
import net.minecraft.network.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;
import java.util.*;
import net.minecraft.item.*;

public class Quiver extends Module
{
    private /* synthetic */ int speedSlot;
    private /* synthetic */ ArrayList<Integer> map;
    private final /* synthetic */ Setting<Integer> holdLength;
    private final /* synthetic */ Timer holdTimer;
    private /* synthetic */ int strSlot;
    private final /* synthetic */ Setting<mainEnum> main;
    private /* synthetic */ int oldSlot;
    private final /* synthetic */ Setting<mainEnum> secondary;
    private final /* synthetic */ Timer delayTimer;
    private final /* synthetic */ Setting<Integer> delay;
    private /* synthetic */ int stage;
    
    @Override
    public void onUpdate() {
        if (nullCheck()) {
            return;
        }
        if (Quiver.mc.currentScreen != null) {
            return;
        }
        if (InventoryUtil.findItemInventorySlot((Item)Items.BOW, true) == -1) {
            Command.sendMessage("Couldn't find bow in inventory! Toggling!");
            this.toggle();
        }
        RotationUtil.faceVector(EntityUtil.getInterpolatedPos((Entity)Quiver.mc.player, Quiver.mc.timer.elapsedPartialTicks).add(0.0, 3.0, 0.0), false);
        if (this.stage == 0) {
            this.map = this.mapArrows();
            for (final int intValue : this.map) {
                final ItemStack itemStack = (ItemStack)Quiver.mc.player.inventoryContainer.getInventory().get(intValue);
                if ((PotionUtils.getPotionFromItem(itemStack).equals(PotionTypes.STRENGTH) || PotionUtils.getPotionFromItem(itemStack).equals(PotionTypes.STRONG_STRENGTH) || PotionUtils.getPotionFromItem(itemStack).equals(PotionTypes.LONG_STRENGTH)) && this.strSlot == -1) {
                    this.strSlot = intValue;
                }
                if ((PotionUtils.getPotionFromItem(itemStack).equals(PotionTypes.SWIFTNESS) || PotionUtils.getPotionFromItem(itemStack).equals(PotionTypes.LONG_SWIFTNESS) || PotionUtils.getPotionFromItem(itemStack).equals(PotionTypes.STRONG_SWIFTNESS)) && this.speedSlot == -1) {
                    this.speedSlot = intValue;
                }
            }
            ++this.stage;
        }
        else if (this.stage == 1) {
            if (!this.delayTimer.passedMs((long)(int)this.delay.getValue())) {
                return;
            }
            this.delayTimer.reset();
            ++this.stage;
        }
        else if (this.stage == 2) {
            this.switchTo((Enum<mainEnum>)this.main.getValue());
            ++this.stage;
        }
        else if (this.stage == 3) {
            if (!this.delayTimer.passedMs((long)(int)this.delay.getValue())) {
                return;
            }
            this.delayTimer.reset();
            ++this.stage;
        }
        else if (this.stage == 4) {
            Quiver.mc.gameSettings.keyBindUseItem.pressed = true;
            this.holdTimer.reset();
            ++this.stage;
        }
        else if (this.stage == 5) {
            if (!this.holdTimer.passedMs((long)(int)this.holdLength.getValue())) {
                return;
            }
            this.holdTimer.reset();
            ++this.stage;
        }
        else if (this.stage == 6) {
            Quiver.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, Quiver.mc.player.getHorizontalFacing()));
            Quiver.mc.player.resetActiveHand();
            Quiver.mc.gameSettings.keyBindUseItem.pressed = false;
            ++this.stage;
        }
        else if (this.stage == 7) {
            if (!this.delayTimer.passedMs((long)(int)this.delay.getValue())) {
                return;
            }
            this.delayTimer.reset();
            ++this.stage;
        }
        else if (this.stage == 8) {
            this.map = this.mapArrows();
            this.strSlot = -1;
            this.speedSlot = -1;
            for (final int intValue2 : this.map) {
                final ItemStack itemStack2 = (ItemStack)Quiver.mc.player.inventoryContainer.getInventory().get(intValue2);
                if ((PotionUtils.getPotionFromItem(itemStack2).equals(PotionTypes.STRENGTH) || PotionUtils.getPotionFromItem(itemStack2).equals(PotionTypes.STRONG_STRENGTH) || PotionUtils.getPotionFromItem(itemStack2).equals(PotionTypes.LONG_STRENGTH)) && this.strSlot == -1) {
                    this.strSlot = intValue2;
                }
                if ((PotionUtils.getPotionFromItem(itemStack2).equals(PotionTypes.SWIFTNESS) || PotionUtils.getPotionFromItem(itemStack2).equals(PotionTypes.LONG_SWIFTNESS) || PotionUtils.getPotionFromItem(itemStack2).equals(PotionTypes.STRONG_SWIFTNESS)) && this.speedSlot == -1) {
                    this.speedSlot = intValue2;
                }
            }
            ++this.stage;
        }
        if (this.stage == 9) {
            this.switchTo((Enum<mainEnum>)this.secondary.getValue());
            ++this.stage;
        }
        else if (this.stage == 10) {
            if (!this.delayTimer.passedMs((long)(int)this.delay.getValue())) {
                return;
            }
            ++this.stage;
        }
        else if (this.stage == 11) {
            Quiver.mc.gameSettings.keyBindUseItem.pressed = true;
            this.holdTimer.reset();
            ++this.stage;
        }
        else if (this.stage == 12) {
            if (!this.holdTimer.passedMs((long)(int)this.holdLength.getValue())) {
                return;
            }
            this.holdTimer.reset();
            ++this.stage;
        }
        else if (this.stage == 13) {
            Quiver.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, Quiver.mc.player.getHorizontalFacing()));
            Quiver.mc.player.resetActiveHand();
            Quiver.mc.gameSettings.keyBindUseItem.pressed = false;
            ++this.stage;
        }
        else if (this.stage == 14) {
            final ArrayList<Integer> mapEmpty = this.mapEmpty();
            if (!mapEmpty.isEmpty()) {
                Quiver.mc.playerController.windowClick(Quiver.mc.player.inventoryContainer.windowId, (int)mapEmpty.get(0), 0, ClickType.PICKUP, (EntityPlayer)Quiver.mc.player);
            }
            ++this.stage;
        }
        else if (this.stage == 15) {
            this.setEnabled(false);
        }
    }
    
    private ArrayList<Integer> mapEmpty() {
        final ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 9; i < 45; ++i) {
            if (((ItemStack)Quiver.mc.player.inventoryContainer.getInventory().get(i)).getItem() instanceof ItemAir || Quiver.mc.player.inventoryContainer.getInventory().get(i) == ItemStack.EMPTY) {
                list.add(i);
            }
        }
        return list;
    }
    
    private void switchTo(final Enum<mainEnum> enum1) {
        if (enum1.toString().equalsIgnoreCase("STRENGTH") && this.strSlot != -1) {
            this.switchTo(this.strSlot);
        }
        if (enum1.toString().equalsIgnoreCase("SPEED") && this.speedSlot != -1) {
            this.switchTo(this.speedSlot);
        }
    }
    
    public Quiver() {
        super("Quiver", "Automatically shoots yourself with good effects.", Category.COMBAT, true, false, false);
        this.delay = (Setting<Integer>)this.register(new Setting("Delay", (Object)0, (Object)0, (Object)500));
        this.holdLength = (Setting<Integer>)this.register(new Setting("Hold Length", (Object)240, (Object)100, (Object)1000));
        this.main = (Setting<mainEnum>)this.register(new Setting("Main", (Object)mainEnum.SPEED));
        this.secondary = (Setting<mainEnum>)this.register(new Setting("Secondary", (Object)mainEnum.STRENGTH));
        this.delayTimer = new Timer();
        this.holdTimer = new Timer();
        this.strSlot = -1;
        this.speedSlot = -1;
        this.oldSlot = 1;
    }
    
    private void switchTo(final int n) {
        if (n == 9) {
            return;
        }
        Quiver.mc.playerController.windowClick(Quiver.mc.player.inventoryContainer.windowId, n, 0, ClickType.PICKUP, (EntityPlayer)Quiver.mc.player);
        Quiver.mc.playerController.windowClick(Quiver.mc.player.inventoryContainer.windowId, 9, 0, ClickType.PICKUP, (EntityPlayer)Quiver.mc.player);
        Quiver.mc.playerController.windowClick(Quiver.mc.player.inventoryContainer.windowId, n, 0, ClickType.PICKUP, (EntityPlayer)Quiver.mc.player);
        Quiver.mc.playerController.updateController();
    }
    
    private ArrayList<Integer> mapArrows() {
        final ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 9; i < 45; ++i) {
            if (((ItemStack)Quiver.mc.player.inventoryContainer.getInventory().get(i)).getItem() instanceof ItemTippedArrow) {
                final ItemStack itemStack = (ItemStack)Quiver.mc.player.inventoryContainer.getInventory().get(i);
                if (PotionUtils.getPotionFromItem(itemStack).equals(PotionTypes.STRENGTH) || PotionUtils.getPotionFromItem(itemStack).equals(PotionTypes.STRONG_STRENGTH) || PotionUtils.getPotionFromItem(itemStack).equals(PotionTypes.LONG_STRENGTH)) {
                    list.add(i);
                }
                if (PotionUtils.getPotionFromItem(itemStack).equals(PotionTypes.SWIFTNESS) || PotionUtils.getPotionFromItem(itemStack).equals(PotionTypes.LONG_SWIFTNESS) || PotionUtils.getPotionFromItem(itemStack).equals(PotionTypes.STRONG_SWIFTNESS)) {
                    list.add(i);
                }
            }
        }
        return list;
    }
    
    @Override
    public void onDisable() {
        if (nullCheck()) {
            return;
        }
        InventoryUtil.switchToHotbarSlot(this.oldSlot, false);
        Quiver.mc.gameSettings.keyBindUseItem.pressed = false;
        this.clean();
    }
    
    @Override
    public void onEnable() {
        if (nullCheck()) {
            return;
        }
        InventoryUtil.switchToHotbarSlot((Class)ItemBow.class, false);
        this.clean();
        this.oldSlot = Quiver.mc.player.inventory.currentItem;
        Quiver.mc.gameSettings.keyBindUseItem.pressed = false;
    }
    
    private void clean() {
        this.holdTimer.reset();
        this.delayTimer.reset();
        this.map = null;
        this.speedSlot = -1;
        this.strSlot = -1;
        this.stage = 0;
    }
    
    private enum mainEnum
    {
        STRENGTH, 
        SPEED;
    }
}
