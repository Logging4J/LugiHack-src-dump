//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.combat;

import me.snow.luigihack.impl.modules.*;
import net.minecraft.util.math.*;
import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.impl.command.*;
import net.minecraft.util.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.entity.*;
import net.minecraft.network.*;
import net.minecraft.item.*;
import net.minecraft.block.*;

public class SelfAnvil extends Module
{
    private /* synthetic */ BlockPos placePos;
    private final /* synthetic */ Setting<Boolean> helpingBlocks;
    private final /* synthetic */ Setting<Boolean> onlyHole;
    private /* synthetic */ int obbySlot;
    private /* synthetic */ int lastBlock;
    private /* synthetic */ BlockPos playerPos;
    private final /* synthetic */ Setting<Boolean> packet;
    private /* synthetic */ int blocksThisTick;
    private final /* synthetic */ Setting<Boolean> rotate;
    private final /* synthetic */ Setting<Boolean> chat;
    private /* synthetic */ int blockSlot;
    private final /* synthetic */ Setting<Integer> blocksPerTick;
    
    @Override
    public void onTick() {
        this.blocksThisTick = 0;
        this.doSelfAnvil();
    }
    
    private boolean doFirstChecks() {
        final int positionPlaceable = BlockUtil.isPositionPlaceable(this.placePos, false, true);
        if (fullNullCheck() || !SelfAnvil.mc.world.isAirBlock(this.playerPos)) {
            return false;
        }
        if (!BlockUtil.isBothHole(this.playerPos) && (boolean)this.onlyHole.getValue()) {
            return false;
        }
        if (this.blockSlot == -1) {
            if (this.chat.getValue()) {
                Command.sendMessage(String.valueOf(new StringBuilder().append("<").append(this.getDisplayName()).append("> §cNo Anvils in hotbar.")));
            }
            return false;
        }
        if (positionPlaceable == 2) {
            if (!(boolean)this.helpingBlocks.getValue()) {
                if (this.chat.getValue()) {
                    Command.sendMessage(String.valueOf(new StringBuilder().append("<").append(this.getDisplayName()).append("> §cNowhere to place.")));
                }
                return false;
            }
            if (this.obbySlot == -1) {
                if (this.chat.getValue()) {
                    Command.sendMessage(String.valueOf(new StringBuilder().append("<").append(this.getDisplayName()).append("> §cNo Obsidian in hotbar.")));
                }
                return false;
            }
        }
        else if (positionPlaceable != 3) {
            if (this.chat.getValue()) {
                Command.sendMessage(String.valueOf(new StringBuilder().append("<").append(this.getDisplayName()).append("> §cNot enough room.")));
            }
            return false;
        }
        return true;
    }
    
    public SelfAnvil() {
        super("SelfAnvil", "funne falling block", Category.COMBAT, true, false, false);
        this.rotate = (Setting<Boolean>)this.register(new Setting("Rotate", (Object)true));
        this.onlyHole = (Setting<Boolean>)this.register(new Setting("HoleOnly", (Object)false));
        this.helpingBlocks = (Setting<Boolean>)this.register(new Setting("HelpingBlocks", (Object)true));
        this.chat = (Setting<Boolean>)this.register(new Setting("Chat Msgs", (Object)true));
        this.packet = (Setting<Boolean>)this.register(new Setting("Packet", (Object)false));
        this.blocksPerTick = (Setting<Integer>)this.register(new Setting("Blocks/Tick", (Object)2, (Object)1, (Object)8));
    }
    
    private void doHelpBlocks() {
        if (this.blocksThisTick >= (int)this.blocksPerTick.getValue()) {
            return;
        }
        for (final EnumFacing enumFacing : EnumFacing.values()) {
            if (enumFacing != EnumFacing.DOWN) {
                if (BlockUtil.isPositionPlaceable(this.placePos.offset(enumFacing), false, true) == 3) {
                    BlockUtil.placeBlock(this.placePos.offset(enumFacing), EnumHand.MAIN_HAND, (boolean)this.rotate.getValue(), (boolean)this.packet.getValue(), false);
                    ++this.blocksThisTick;
                    return;
                }
            }
        }
        for (final EnumFacing enumFacing2 : EnumFacing.values()) {
            if (enumFacing2 != EnumFacing.DOWN) {
                for (final EnumFacing enumFacing3 : EnumFacing.values()) {
                    if (BlockUtil.isPositionPlaceable(this.placePos.offset(enumFacing2).offset(enumFacing3), false, true) == 3) {
                        BlockUtil.placeBlock(this.placePos.offset(enumFacing2).offset(enumFacing3), EnumHand.MAIN_HAND, (boolean)this.rotate.getValue(), (boolean)this.packet.getValue(), false);
                        ++this.blocksThisTick;
                        return;
                    }
                }
            }
        }
        for (final EnumFacing enumFacing4 : EnumFacing.values()) {
            for (final EnumFacing enumFacing5 : EnumFacing.values()) {
                for (final EnumFacing enumFacing6 : EnumFacing.values()) {
                    if (BlockUtil.isPositionPlaceable(this.placePos.offset(enumFacing4).offset(enumFacing5).offset(enumFacing6), false, true) == 3) {
                        BlockUtil.placeBlock(this.placePos.offset(enumFacing4).offset(enumFacing5).offset(enumFacing6), EnumHand.MAIN_HAND, (boolean)this.rotate.getValue(), (boolean)this.packet.getValue(), false);
                        ++this.blocksThisTick;
                        return;
                    }
                }
            }
        }
    }
    
    @Override
    public void onEnable() {
        this.playerPos = new BlockPos(SelfAnvil.mc.player.posX, SelfAnvil.mc.player.posY, SelfAnvil.mc.player.posZ);
        this.placePos = this.playerPos.offset(EnumFacing.UP, 2);
        this.blockSlot = this.findBlockSlot();
        this.obbySlot = InventoryUtil.findHotbarBlock((Class)BlockObsidian.class);
        this.lastBlock = SelfAnvil.mc.player.inventory.currentItem;
        if (!this.doFirstChecks()) {
            this.disable();
        }
    }
    
    private void doSelfAnvil() {
        if ((boolean)this.helpingBlocks.getValue() && BlockUtil.isPositionPlaceable(this.placePos, false, true) == 2) {
            InventoryUtil.switchToHotbarSlot(this.obbySlot, false);
            this.doHelpBlocks();
        }
        if (this.blocksThisTick < (int)this.blocksPerTick.getValue() && BlockUtil.isPositionPlaceable(this.placePos, false, true) == 3) {
            InventoryUtil.switchToHotbarSlot(this.blockSlot, false);
            BlockUtil.placeBlock(this.placePos, EnumHand.MAIN_HAND, (boolean)this.rotate.getValue(), (boolean)this.packet.getValue(), false);
            InventoryUtil.switchToHotbarSlot(this.lastBlock, false);
            SelfAnvil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)SelfAnvil.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
            this.disable();
        }
    }
    
    private int findBlockSlot() {
        for (int i = 0; i < 9; ++i) {
            if (SelfAnvil.mc.player.inventory.getStackInSlot(i).getItem() instanceof ItemBlock) {
                if (Block.getBlockFromItem(SelfAnvil.mc.player.inventory.getStackInSlot(i).getItem()) instanceof BlockFalling) {
                    return i;
                }
            }
        }
        return -1;
    }
}
