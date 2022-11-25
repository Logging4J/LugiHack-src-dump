//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.combat;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.impl.command.*;
import net.minecraft.block.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import me.snow.luigihack.api.util.*;
import me.snow.luigihack.api.util.skid.oyvey.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import java.util.*;
import net.minecraft.util.math.*;

public class AutoHoleFill extends Module
{
    public final /* synthetic */ Setting<Boolean> smart;
    private final /* synthetic */ Setting<Integer> disableTime;
    public final /* synthetic */ Setting<Boolean> packet;
    private final /* synthetic */ Setting<Boolean> disable;
    public final /* synthetic */ Setting<Boolean> doubleHoles;
    public final /* synthetic */ Setting<Integer> ticks;
    public final /* synthetic */ Setting<Integer> distance;
    public final /* synthetic */ Setting<Integer> bps;
    public final /* synthetic */ Setting<Boolean> predict;
    public final /* synthetic */ Setting<Boolean> toggle;
    private final /* synthetic */ Timer offTimer;
    public final /* synthetic */ Setting<Boolean> rotate;
    public final /* synthetic */ Setting<Integer> validHoleHeight;
    public final /* synthetic */ Setting<Float> validPlayerRange;
    public final /* synthetic */ Setting<Sensitivity> sensitivity;
    public final /* synthetic */ Setting<Integer> range;
    
    @Override
    public void onEnable() {
        if (nullCheck()) {
            return;
        }
        this.offTimer.reset();
    }
    
    @Override
    public void onUpdate() {
        if (nullCheck()) {
            return;
        }
        if ((boolean)this.disable.getValue() && this.offTimer.passedMs((long)(int)this.disableTime.getValue())) {
            Command.sendMessage("<HoleFill> Disable");
            this.disable();
        }
        final int hotbarBlock = InventoryUtil.findHotbarBlock((Class)BlockObsidian.class);
        final int hotbarBlock2 = InventoryUtil.findHotbarBlock((Class)BlockAnvil.class);
        final int hotbarBlock3 = InventoryUtil.findHotbarBlock((Class)BlockEnderChest.class);
        int n = -1;
        final int currentItem = AutoHoleFill.mc.player.inventory.currentItem;
        int n2 = 0;
        final ArrayList holes = HoleUtil.holes((float)(int)this.range.getValue(), (int)this.validHoleHeight.getValue());
        if (this.smart.getValue()) {
            if (EntityUtil2.getTarget(true, false, false, false, false, (double)(float)this.validPlayerRange.getValue(), EntityUtil2.toMode("Closest")) == null) {
                return;
            }
            final EntityPlayer entityPlayer;
            final Vec3d vec3d;
            final Entity entity;
            final HoleUtil.Hole o;
            final ArrayList list;
            holes.removeIf(doubleHole -> {
                vec3d = (this.predict.getValue() ? entityPlayer.getPositionVector() : MathUtil.extrapolatePlayerPosition(entityPlayer, (int)this.ticks.getValue()));
                if (doubleHole instanceof HoleUtil.SingleHole) {
                    return vec3d.squareDistanceTo(new Vec3d((Vec3i)((HoleUtil.SingleHole)doubleHole).pos).add(0.5, 0.5, 0.5)) >= (int)this.distance.getValue() * (int)this.distance.getValue();
                }
                else {
                    HoleUtil.getHole(EntityUtil2.getEntityPosFloored(entity), 1);
                    if (o instanceof HoleUtil.DoubleHole && list.contains(o)) {
                        return true;
                    }
                    else if (vec3d.squareDistanceTo(new Vec3d((Vec3i)doubleHole.pos).add(0.5, 0.5, 0.5)) >= (int)this.distance.getValue() * (int)this.distance.getValue()) {
                        return true;
                    }
                    else {
                        return vec3d.squareDistanceTo(new Vec3d((Vec3i)doubleHole.pos1).add(0.5, 0.5, 0.5)) >= (int)this.distance.getValue() * (int)this.distance.getValue();
                    }
                }
            });
        }
        if (holes.isEmpty()) {
            return;
        }
        for (final HoleUtil.Hole hole : holes) {
            if (this.smart.getValue()) {
                final EntityPlayer entityPlayer2 = (EntityPlayer)EntityUtil2.getTarget(true, false, false, false, false, 10.0, EntityUtil2.toMode("Closest"));
                if (n2 >= (int)this.bps.getValue()) {
                    continue;
                }
                if (hole instanceof HoleUtil.SingleHole && !EntityUtil2.isInHole((Entity)entityPlayer2) && WorldUtils.empty.contains(WorldUtils.getBlock(((HoleUtil.SingleHole)hole).pos)) && (InventoryUtil.findHotbarBlock((Class)BlockObsidian.class) != -1 || InventoryUtil.findHotbarBlock((Class)BlockEnderChest.class) != -1) && !BlockUtil2.isIntercepted(((HoleUtil.SingleHole)hole).pos)) {
                    if (this.sensitivity.getValue() == Sensitivity.Less) {
                        if (InventoryUtil.findHotbarBlock((Class)BlockObsidian.class) != -1) {
                            AutoHoleFill.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(hotbarBlock));
                        }
                        else if (InventoryUtil.findHotbarBlock((Class)BlockAnvil.class) != -1) {
                            AutoHoleFill.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(hotbarBlock2));
                        }
                        else {
                            if (InventoryUtil.findHotbarBlock((Class)BlockEnderChest.class) == -1) {
                                return;
                            }
                            AutoHoleFill.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(hotbarBlock3));
                        }
                    }
                    else if (InventoryUtil.findHotbarBlock((Class)BlockObsidian.class) != -1) {
                        if (hotbarBlock != AutoHoleFill.mc.player.inventory.currentItem) {
                            n = AutoHoleFill.mc.player.inventory.currentItem;
                            AutoHoleFill.mc.player.inventory.currentItem = hotbarBlock;
                        }
                    }
                    else if (InventoryUtil.findHotbarBlock((Class)BlockAnvil.class) != -1) {
                        if (hotbarBlock2 != AutoHoleFill.mc.player.inventory.currentItem) {
                            n = AutoHoleFill.mc.player.inventory.currentItem;
                            AutoHoleFill.mc.player.inventory.currentItem = hotbarBlock2;
                        }
                    }
                    else {
                        if (InventoryUtil.findHotbarBlock((Class)BlockEnderChest.class) == -1) {
                            return;
                        }
                        if (hotbarBlock3 != AutoHoleFill.mc.player.inventory.currentItem) {
                            n = AutoHoleFill.mc.player.inventory.currentItem;
                            AutoHoleFill.mc.player.inventory.currentItem = hotbarBlock3;
                        }
                    }
                    BlockUtil2.placeBlockss(((HoleUtil.SingleHole)hole).pos, false, (boolean)this.packet.getValue(), (boolean)this.rotate.getValue());
                    if (this.sensitivity.getValue() == Sensitivity.Less) {
                        AutoHoleFill.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(currentItem));
                    }
                    else if (this.sensitivity.getValue() == Sensitivity.Strong && n != -1) {
                        AutoHoleFill.mc.player.inventory.currentItem = n;
                    }
                    ++n2;
                }
                if (n2 >= (int)this.bps.getValue() || !(hole instanceof HoleUtil.DoubleHole) || !(boolean)this.doubleHoles.getValue()) {
                    continue;
                }
                if (n2 >= (int)this.bps.getValue()) {
                    continue;
                }
                final HoleUtil.DoubleHole doubleHole2 = (HoleUtil.DoubleHole)hole;
                if (this.getDist(doubleHole2.pos) && !EntityUtil2.isInHole((Entity)entityPlayer2) && !BlockUtil2.isInterceptedByOther(doubleHole2.pos) && WorldUtils.empty.contains(WorldUtils.getBlock(doubleHole2.pos)) && (InventoryUtil.findHotbarBlock((Class)BlockObsidian.class) != -1 || InventoryUtil.findHotbarBlock((Class)BlockEnderChest.class) != -1) && !BlockUtil2.isIntercepted(doubleHole2.pos)) {
                    if (this.sensitivity.getValue() == Sensitivity.Less) {
                        if (InventoryUtil.findHotbarBlock((Class)BlockObsidian.class) != -1) {
                            AutoHoleFill.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(hotbarBlock));
                        }
                        else if (InventoryUtil.findHotbarBlock((Class)BlockAnvil.class) != -1) {
                            AutoHoleFill.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(hotbarBlock2));
                        }
                        else {
                            if (InventoryUtil.findHotbarBlock((Class)BlockEnderChest.class) == -1) {
                                return;
                            }
                            AutoHoleFill.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(hotbarBlock3));
                        }
                    }
                    else if (InventoryUtil.findHotbarBlock((Class)BlockObsidian.class) != -1) {
                        if (hotbarBlock != AutoHoleFill.mc.player.inventory.currentItem) {
                            n = AutoHoleFill.mc.player.inventory.currentItem;
                            AutoHoleFill.mc.player.inventory.currentItem = hotbarBlock;
                        }
                    }
                    else if (InventoryUtil.findHotbarBlock((Class)BlockAnvil.class) != -1) {
                        if (hotbarBlock2 != AutoHoleFill.mc.player.inventory.currentItem) {
                            n = AutoHoleFill.mc.player.inventory.currentItem;
                            AutoHoleFill.mc.player.inventory.currentItem = hotbarBlock2;
                        }
                    }
                    else {
                        if (InventoryUtil.findHotbarBlock((Class)BlockEnderChest.class) == -1) {
                            return;
                        }
                        if (hotbarBlock3 != AutoHoleFill.mc.player.inventory.currentItem) {
                            n = AutoHoleFill.mc.player.inventory.currentItem;
                            AutoHoleFill.mc.player.inventory.currentItem = hotbarBlock3;
                        }
                    }
                    BlockUtil2.placeBlockss(doubleHole2.pos, false, (boolean)this.packet.getValue(), (boolean)this.rotate.getValue());
                    if (this.sensitivity.getValue() == Sensitivity.Less) {
                        AutoHoleFill.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(currentItem));
                    }
                    else if (this.sensitivity.getValue() == Sensitivity.Strong && n != -1) {
                        AutoHoleFill.mc.player.inventory.currentItem = n;
                    }
                    ++n2;
                }
                if (n2 >= (int)this.bps.getValue() || !this.getDist(doubleHole2.pos1) || EntityUtil2.isInHole((Entity)entityPlayer2) || BlockUtil2.isInterceptedByOther(doubleHole2.pos1) || !WorldUtils.empty.contains(WorldUtils.getBlock(doubleHole2.pos1)) || (InventoryUtil.findHotbarBlock((Class)BlockObsidian.class) == -1 && InventoryUtil.findHotbarBlock((Class)BlockEnderChest.class) == -1)) {
                    continue;
                }
                if (BlockUtil2.isIntercepted(doubleHole2.pos1)) {
                    continue;
                }
                if (this.sensitivity.getValue() == Sensitivity.Less) {
                    if (InventoryUtil.findHotbarBlock((Class)BlockObsidian.class) != -1) {
                        AutoHoleFill.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(hotbarBlock));
                    }
                    else if (InventoryUtil.findHotbarBlock((Class)BlockAnvil.class) != -1) {
                        AutoHoleFill.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(hotbarBlock2));
                    }
                    else {
                        if (InventoryUtil.findHotbarBlock((Class)BlockEnderChest.class) == -1) {
                            return;
                        }
                        AutoHoleFill.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(hotbarBlock3));
                    }
                }
                else if (InventoryUtil.findHotbarBlock((Class)BlockObsidian.class) != -1) {
                    if (hotbarBlock != AutoHoleFill.mc.player.inventory.currentItem) {
                        n = AutoHoleFill.mc.player.inventory.currentItem;
                        AutoHoleFill.mc.player.inventory.currentItem = hotbarBlock;
                    }
                }
                else if (InventoryUtil.findHotbarBlock((Class)BlockAnvil.class) != -1) {
                    if (hotbarBlock2 != AutoHoleFill.mc.player.inventory.currentItem) {
                        n = AutoHoleFill.mc.player.inventory.currentItem;
                        AutoHoleFill.mc.player.inventory.currentItem = hotbarBlock2;
                    }
                }
                else {
                    if (InventoryUtil.findHotbarBlock((Class)BlockEnderChest.class) == -1) {
                        return;
                    }
                    if (hotbarBlock3 != AutoHoleFill.mc.player.inventory.currentItem) {
                        n = AutoHoleFill.mc.player.inventory.currentItem;
                        AutoHoleFill.mc.player.inventory.currentItem = hotbarBlock3;
                    }
                }
                BlockUtil2.placeBlockss(doubleHole2.pos1, false, (boolean)this.packet.getValue(), (boolean)this.rotate.getValue());
                if (this.sensitivity.getValue() == Sensitivity.Less) {
                    AutoHoleFill.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(currentItem));
                }
                else if (this.sensitivity.getValue() == Sensitivity.Strong && n != -1) {
                    AutoHoleFill.mc.player.inventory.currentItem = n;
                }
                ++n2;
            }
            else {
                if (n2 >= (int)this.bps.getValue()) {
                    continue;
                }
                if (hole instanceof HoleUtil.SingleHole && WorldUtils.empty.contains(WorldUtils.getBlock(((HoleUtil.SingleHole)hole).pos)) && !BlockUtil2.isIntercepted(((HoleUtil.SingleHole)hole).pos)) {
                    if (this.sensitivity.getValue() == Sensitivity.Less) {
                        if (InventoryUtil.findHotbarBlock((Class)BlockObsidian.class) != -1) {
                            AutoHoleFill.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(hotbarBlock));
                        }
                        else if (InventoryUtil.findHotbarBlock((Class)BlockAnvil.class) != -1) {
                            AutoHoleFill.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(hotbarBlock2));
                        }
                        else {
                            if (InventoryUtil.findHotbarBlock((Class)BlockEnderChest.class) == -1) {
                                return;
                            }
                            AutoHoleFill.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(hotbarBlock3));
                        }
                    }
                    else if (InventoryUtil.findHotbarBlock((Class)BlockObsidian.class) != -1) {
                        if (hotbarBlock != AutoHoleFill.mc.player.inventory.currentItem) {
                            n = AutoHoleFill.mc.player.inventory.currentItem;
                            AutoHoleFill.mc.player.inventory.currentItem = hotbarBlock;
                        }
                    }
                    else if (InventoryUtil.findHotbarBlock((Class)BlockAnvil.class) != -1) {
                        if (hotbarBlock2 != AutoHoleFill.mc.player.inventory.currentItem) {
                            n = AutoHoleFill.mc.player.inventory.currentItem;
                            AutoHoleFill.mc.player.inventory.currentItem = hotbarBlock2;
                        }
                    }
                    else {
                        if (InventoryUtil.findHotbarBlock((Class)BlockEnderChest.class) == -1) {
                            return;
                        }
                        if (hotbarBlock3 != AutoHoleFill.mc.player.inventory.currentItem) {
                            n = AutoHoleFill.mc.player.inventory.currentItem;
                            AutoHoleFill.mc.player.inventory.currentItem = hotbarBlock3;
                        }
                    }
                    BlockUtil2.placeBlockss(((HoleUtil.SingleHole)hole).pos, false, (boolean)this.packet.getValue(), (boolean)this.rotate.getValue());
                    if (this.sensitivity.getValue() == Sensitivity.Less) {
                        AutoHoleFill.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(currentItem));
                    }
                    else if (this.sensitivity.getValue() == Sensitivity.Strong && n != -1) {
                        AutoHoleFill.mc.player.inventory.currentItem = n;
                    }
                    ++n2;
                }
                if (n2 >= (int)this.bps.getValue() || !(hole instanceof HoleUtil.DoubleHole) || !(boolean)this.doubleHoles.getValue()) {
                    continue;
                }
                if (n2 >= (int)this.bps.getValue()) {
                    continue;
                }
                final HoleUtil.DoubleHole doubleHole3 = (HoleUtil.DoubleHole)hole;
                if (this.getDist(doubleHole3.pos) && !BlockUtil2.isInterceptedByOther(doubleHole3.pos) && WorldUtils.empty.contains(WorldUtils.getBlock(doubleHole3.pos)) && !BlockUtil2.isIntercepted(doubleHole3.pos)) {
                    if (this.sensitivity.getValue() == Sensitivity.Less) {
                        if (InventoryUtil.findHotbarBlock((Class)BlockObsidian.class) != -1) {
                            AutoHoleFill.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(hotbarBlock));
                        }
                        else if (InventoryUtil.findHotbarBlock((Class)BlockAnvil.class) != -1) {
                            AutoHoleFill.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(hotbarBlock2));
                        }
                        else {
                            if (InventoryUtil.findHotbarBlock((Class)BlockEnderChest.class) == -1) {
                                return;
                            }
                            AutoHoleFill.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(hotbarBlock3));
                        }
                    }
                    else if (InventoryUtil.findHotbarBlock((Class)BlockObsidian.class) != -1) {
                        if (hotbarBlock != AutoHoleFill.mc.player.inventory.currentItem) {
                            n = AutoHoleFill.mc.player.inventory.currentItem;
                            AutoHoleFill.mc.player.inventory.currentItem = hotbarBlock;
                        }
                    }
                    else if (InventoryUtil.findHotbarBlock((Class)BlockAnvil.class) != -1) {
                        if (hotbarBlock2 != AutoHoleFill.mc.player.inventory.currentItem) {
                            n = AutoHoleFill.mc.player.inventory.currentItem;
                            AutoHoleFill.mc.player.inventory.currentItem = hotbarBlock2;
                        }
                    }
                    else {
                        if (InventoryUtil.findHotbarBlock((Class)BlockEnderChest.class) == -1) {
                            return;
                        }
                        if (hotbarBlock3 != AutoHoleFill.mc.player.inventory.currentItem) {
                            n = AutoHoleFill.mc.player.inventory.currentItem;
                            AutoHoleFill.mc.player.inventory.currentItem = hotbarBlock3;
                        }
                    }
                    BlockUtil2.placeBlockss(doubleHole3.pos, false, (boolean)this.packet.getValue(), (boolean)this.rotate.getValue());
                    if (this.sensitivity.getValue() == Sensitivity.Less) {
                        AutoHoleFill.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(currentItem));
                    }
                    else if (this.sensitivity.getValue() == Sensitivity.Strong && n != -1) {
                        AutoHoleFill.mc.player.inventory.currentItem = n;
                    }
                    ++n2;
                }
                if (n2 >= (int)this.bps.getValue() || !this.getDist(doubleHole3.pos1) || BlockUtil2.isInterceptedByOther(doubleHole3.pos1) || !WorldUtils.empty.contains(WorldUtils.getBlock(doubleHole3.pos1))) {
                    continue;
                }
                if (BlockUtil2.isIntercepted(doubleHole3.pos1)) {
                    continue;
                }
                if (this.sensitivity.getValue() == Sensitivity.Less) {
                    if (InventoryUtil.findHotbarBlock((Class)BlockObsidian.class) != -1) {
                        AutoHoleFill.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(hotbarBlock));
                    }
                    else if (InventoryUtil.findHotbarBlock((Class)BlockAnvil.class) != -1) {
                        AutoHoleFill.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(hotbarBlock2));
                    }
                    else {
                        if (InventoryUtil.findHotbarBlock((Class)BlockEnderChest.class) == -1) {
                            return;
                        }
                        AutoHoleFill.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(hotbarBlock3));
                    }
                }
                else if (InventoryUtil.findHotbarBlock((Class)BlockObsidian.class) != -1) {
                    if (hotbarBlock != AutoHoleFill.mc.player.inventory.currentItem) {
                        n = AutoHoleFill.mc.player.inventory.currentItem;
                        AutoHoleFill.mc.player.inventory.currentItem = hotbarBlock;
                    }
                }
                else if (InventoryUtil.findHotbarBlock((Class)BlockAnvil.class) != -1) {
                    if (hotbarBlock2 != AutoHoleFill.mc.player.inventory.currentItem) {
                        n = AutoHoleFill.mc.player.inventory.currentItem;
                        AutoHoleFill.mc.player.inventory.currentItem = hotbarBlock2;
                    }
                }
                else {
                    if (InventoryUtil.findHotbarBlock((Class)BlockEnderChest.class) == -1) {
                        return;
                    }
                    if (hotbarBlock3 != AutoHoleFill.mc.player.inventory.currentItem) {
                        n = AutoHoleFill.mc.player.inventory.currentItem;
                        AutoHoleFill.mc.player.inventory.currentItem = hotbarBlock3;
                    }
                }
                BlockUtil2.placeBlockss(doubleHole3.pos1, false, (boolean)this.packet.getValue(), (boolean)this.rotate.getValue());
                if (this.sensitivity.getValue() == Sensitivity.Less) {
                    AutoHoleFill.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(currentItem));
                }
                else if (this.sensitivity.getValue() == Sensitivity.Strong && n != -1) {
                    AutoHoleFill.mc.player.inventory.currentItem = n;
                }
                ++n2;
            }
        }
        if (n2 != 0 || !holes.isEmpty() || !(boolean)this.toggle.getValue()) {
            return;
        }
        Command.sendMessage("Finished Holefilling, disabling!");
        this.disable();
    }
    
    public AutoHoleFill() {
        super("AutoHoleFill", "Automatically Fills safe holes near the opponent for Crystal PvP", Category.COMBAT, true, false, false);
        this.doubleHoles = (Setting<Boolean>)this.register(new Setting("Fill Double Holes", (Object)true));
        this.sensitivity = (Setting<Sensitivity>)this.register(new Setting("Sensitivity", (Object)Sensitivity.Less));
        this.validHoleHeight = (Setting<Integer>)this.register(new Setting("Valid Hole Height", (Object)2, (Object)1, (Object)5));
        this.bps = (Setting<Integer>)this.register(new Setting("Blocks Per Tick", (Object)3, (Object)1, (Object)8));
        this.range = (Setting<Integer>)this.register(new Setting("Range", (Object)5, (Object)1, (Object)10));
        this.validPlayerRange = (Setting<Float>)this.register(new Setting("Valid Player Range", (Object)10.0f, (Object)0.1f, (Object)15.0f));
        this.toggle = (Setting<Boolean>)this.register(new Setting("Disables", (Object)false));
        this.packet = (Setting<Boolean>)this.register(new Setting("Packet", (Object)false));
        this.rotate = (Setting<Boolean>)this.register(new Setting("Rotate", (Object)true));
        this.smart = (Setting<Boolean>)this.register(new Setting("Smart", (Object)true));
        this.distance = (Setting<Integer>)this.register(new Setting("Smart Range", (Object)2, (Object)1, (Object)5));
        this.predict = (Setting<Boolean>)this.register(new Setting("Predict", (Object)true));
        this.ticks = (Setting<Integer>)this.register(new Setting("Predict Delay", (Object)3, (Object)1, (Object)5));
        this.disable = (Setting<Boolean>)this.register(new Setting("Disable", (Object)false));
        this.disableTime = (Setting<Integer>)this.register(new Setting("Ms/Disable", (Object)200, (Object)1, (Object)250));
        this.offTimer = new Timer();
    }
    
    private boolean getDist(final BlockPos blockPos) {
        return !nullCheck() && blockPos != null && blockPos.add(0.5, 0.5, 0.5).distanceSq(AutoHoleFill.mc.player.posX, AutoHoleFill.mc.player.posY + AutoHoleFill.mc.player.eyeHeight, AutoHoleFill.mc.player.posZ) < Math.pow((int)this.range.getValue(), 2.0);
    }
    
    public enum Sensitivity
    {
        Less, 
        Strong;
    }
}
