//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.misc;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import me.snow.luigihack.impl.command.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;

public class InstantBurrow extends Module
{
    private /* synthetic */ Setting<AMODEA> mode;
    public /* synthetic */ Setting<Boolean> rotate2;
    public /* synthetic */ Setting<Boolean> rotate;
    public /* synthetic */ Setting<Boolean> silent;
    public /* synthetic */ Setting<Boolean> enderchest;
    public /* synthetic */ Setting<Boolean> silent2;
    public /* synthetic */ Setting<Integer> height2;
    /* synthetic */ float oldTickLength;
    public /* synthetic */ Setting<Double> height;
    
    public static int getHotbarSlot(final Block obj) {
        for (int i = 0; i < 9; ++i) {
            final Item getItem = InstantBurrow.mc.player.inventory.getStackInSlot(i).getItem();
            if (getItem instanceof ItemBlock && ((ItemBlock)getItem).getBlock().equals(obj)) {
                return i;
            }
        }
        return -1;
    }
    
    @Override
    public void enable() {
        if (this.mode.getValue() == AMODEA.NormalBurrow) {
            if (InstantBurrow.mc.player == null || InstantBurrow.mc.world == null) {
                return;
            }
            final int currentItem = InstantBurrow.mc.player.inventory.currentItem;
            this.oldTickLength = InstantBurrow.mc.timer.tickLength;
            final BlockPos blockPos = new BlockPos(InstantBurrow.mc.player.posX, InstantBurrow.mc.player.posY, InstantBurrow.mc.player.posZ);
            if (InstantBurrow.mc.world.getBlockState(new BlockPos(InstantBurrow.mc.player.posX, InstantBurrow.mc.player.posY, InstantBurrow.mc.player.posZ)).getBlock().equals(Blocks.OBSIDIAN) || InstantBurrow.mc.world.getBlockState(new BlockPos(InstantBurrow.mc.player.posX, InstantBurrow.mc.player.posY, InstantBurrow.mc.player.posZ)).getBlock().equals(Blocks.ENDER_CHEST)) {
                Command.sendMessage("Bro ur already in burrow");
                this.disable();
                return;
            }
            if (BlockInteractionHelper.isInterceptedByOther(blockPos)) {
                Command.sendMessage("Ur intercepting with another creature.");
                this.disable();
                return;
            }
            if (getHotbarSlot(Blocks.OBSIDIAN) == -1 && getHotbarSlot(Blocks.ENDER_CHEST) == -1) {
                Command.sendMessage("U need echest or obsidian.");
                this.disable();
                return;
            }
            if (!InstantBurrow.mc.world.getBlockState(blockPos.add(0, 1, 0)).getBlock().equals(Blocks.AIR) || !InstantBurrow.mc.world.getBlockState(blockPos.add(0, 2, 0)).getBlock().equals(Blocks.AIR) || !InstantBurrow.mc.world.getBlockState(blockPos.add(0, 3, 0)).getBlock().equals(Blocks.AIR)) {
                Command.sendMessage("need some space!");
                this.disable();
                return;
            }
            if (InstantBurrow.mc.isSingleplayer()) {
                Command.sendMessage("Why are u trying this in singleplayer?");
                this.disable();
                return;
            }
            if (this.silent.getValue()) {
                InstantBurrow.mc.timer.tickLength = 1.0f;
            }
            InstantBurrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(InstantBurrow.mc.player.posX, InstantBurrow.mc.player.posY + 0.41999998688698, InstantBurrow.mc.player.posZ, true));
            InstantBurrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(InstantBurrow.mc.player.posX, InstantBurrow.mc.player.posY + 0.7531999805211997, InstantBurrow.mc.player.posZ, true));
            InstantBurrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(InstantBurrow.mc.player.posX, InstantBurrow.mc.player.posY + 1.00133597911214, InstantBurrow.mc.player.posZ, true));
            InstantBurrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(InstantBurrow.mc.player.posX, InstantBurrow.mc.player.posY + 1.16610926093821, InstantBurrow.mc.player.posZ, true));
            InstantBurrow.mc.player.setPosition(InstantBurrow.mc.player.posX, InstantBurrow.mc.player.posY + 1.16610926093821, InstantBurrow.mc.player.posZ);
            if ((boolean)this.enderchest.getValue() && getHotbarSlot(Blocks.ENDER_CHEST) != -1) {
                InstantBurrow.mc.player.inventory.currentItem = getHotbarSlot(Blocks.ENDER_CHEST);
            }
            else if (getHotbarSlot(Blocks.OBSIDIAN) != -1) {
                InstantBurrow.mc.player.inventory.currentItem = getHotbarSlot(Blocks.OBSIDIAN);
            }
            else {
                InstantBurrow.mc.player.inventory.currentItem = getHotbarSlot(Blocks.ENDER_CHEST);
            }
            BlockInteractionHelper.placeBlock(blockPos, (boolean)this.rotate.getValue(), true, false, true, false);
            InstantBurrow.mc.player.inventory.currentItem = currentItem;
            InstantBurrow.mc.player.setPosition(InstantBurrow.mc.player.posX, InstantBurrow.mc.player.posY - 1.16610926093821, InstantBurrow.mc.player.posZ);
            InstantBurrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(InstantBurrow.mc.player.posX, InstantBurrow.mc.player.posY + (double)this.height.getValue(), InstantBurrow.mc.player.posZ, true));
            InstantBurrow.mc.timer.tickLength = this.oldTickLength;
            this.disable();
        }
        if (this.mode.getValue() == AMODEA.FiveBeeBypass) {
            if (InstantBurrow.mc.player == null || InstantBurrow.mc.world == null) {
                return;
            }
            final int currentItem2 = InstantBurrow.mc.player.inventory.currentItem;
            this.oldTickLength = InstantBurrow.mc.timer.tickLength;
            final BlockPos blockPos2 = new BlockPos(InstantBurrow.mc.player.posX, InstantBurrow.mc.player.posY, InstantBurrow.mc.player.posZ);
            if (InstantBurrow.mc.world.getBlockState(new BlockPos(InstantBurrow.mc.player.posX, InstantBurrow.mc.player.posY, InstantBurrow.mc.player.posZ)).getBlock().equals(Blocks.PISTON)) {
                Command.sendMessage("Bro ur already in burrow");
                this.disable();
                return;
            }
            if (BlockInteractionHelper.isInterceptedByOther(blockPos2)) {
                Command.sendMessage("Ur intercepting with another creature.");
                this.disable();
                return;
            }
            if (getHotbarSlot((Block)Blocks.PISTON) == -1) {
                Command.sendMessage("U need piston");
                this.disable();
                return;
            }
            if (!InstantBurrow.mc.world.getBlockState(blockPos2.add(0, 1, 0)).getBlock().equals(Blocks.AIR) || !InstantBurrow.mc.world.getBlockState(blockPos2.add(0, 2, 0)).getBlock().equals(Blocks.AIR) || !InstantBurrow.mc.world.getBlockState(blockPos2.add(0, 3, 0)).getBlock().equals(Blocks.AIR)) {
                Command.sendMessage("need some space!");
                this.disable();
                return;
            }
            if (InstantBurrow.mc.isSingleplayer()) {
                Command.sendMessage("Why are u trying this in singleplayer?");
                this.disable();
                return;
            }
            if (this.silent2.getValue()) {
                InstantBurrow.mc.timer.tickLength = 1.0f;
            }
            InstantBurrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(InstantBurrow.mc.player.posX, InstantBurrow.mc.player.posY + 0.41999998688698, InstantBurrow.mc.player.posZ, true));
            InstantBurrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(InstantBurrow.mc.player.posX, InstantBurrow.mc.player.posY + 0.7531999805211997, InstantBurrow.mc.player.posZ, true));
            InstantBurrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(InstantBurrow.mc.player.posX, InstantBurrow.mc.player.posY + 1.00133597911214, InstantBurrow.mc.player.posZ, true));
            InstantBurrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(InstantBurrow.mc.player.posX, InstantBurrow.mc.player.posY + 1.16610926093821, InstantBurrow.mc.player.posZ, true));
            InstantBurrow.mc.player.setPosition(InstantBurrow.mc.player.posX, InstantBurrow.mc.player.posY + 1.16610926093821, InstantBurrow.mc.player.posZ);
            if (getHotbarSlot((Block)Blocks.PISTON) != -1) {
                InstantBurrow.mc.player.inventory.currentItem = getHotbarSlot((Block)Blocks.PISTON);
            }
            BlockInteractionHelper.placeBlock(blockPos2, (boolean)this.rotate2.getValue(), true, false, true, false);
            InstantBurrow.mc.player.inventory.currentItem = currentItem2;
            InstantBurrow.mc.player.setPosition(InstantBurrow.mc.player.posX, InstantBurrow.mc.player.posY - 1.16610926093821, InstantBurrow.mc.player.posZ);
            InstantBurrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(InstantBurrow.mc.player.posX, InstantBurrow.mc.player.posY + (int)this.height2.getValue(), InstantBurrow.mc.player.posZ, true));
            InstantBurrow.mc.timer.tickLength = this.oldTickLength;
            this.disable();
        }
    }
    
    public InstantBurrow() {
        super("InstantBurrow", "Lags you into a block instantly.", Category.MISC, true, false, true);
        this.mode = (Setting<AMODEA>)this.register(new Setting("BurrowMode", (Object)AMODEA.NormalBurrow));
        this.rotate = (Setting<Boolean>)this.register(new Setting("Rotate", (Object)true, p0 -> this.mode.getValue() == AMODEA.NormalBurrow));
        this.silent = (Setting<Boolean>)this.register(new Setting("Silent", (Object)true, p0 -> this.mode.getValue() == AMODEA.NormalBurrow));
        this.enderchest = (Setting<Boolean>)this.register(new Setting("EnderChest", (Object)true, p0 -> this.mode.getValue() == AMODEA.NormalBurrow));
        this.height = (Setting<Double>)this.register(new Setting("Height", (Object)1.5, (Object)(-5.0), (Object)10.0, p0 -> this.mode.getValue() == AMODEA.NormalBurrow));
        this.rotate2 = (Setting<Boolean>)this.register(new Setting("5bRotate", (Object)false, p0 -> this.mode.getValue() == AMODEA.FiveBeeBypass));
        this.silent2 = (Setting<Boolean>)this.register(new Setting("5bSilent", (Object)true, p0 -> this.mode.getValue() == AMODEA.FiveBeeBypass));
        this.height2 = (Setting<Integer>)this.register(new Setting("5bHeight", (Object)2, (Object)(-30), (Object)30, p0 -> this.mode.getValue() == AMODEA.FiveBeeBypass));
        this.oldTickLength = InstantBurrow.mc.timer.tickLength;
    }
    
    private enum AMODEA
    {
        NormalBurrow, 
        FiveBeeBypass;
    }
}
