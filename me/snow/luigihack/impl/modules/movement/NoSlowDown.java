//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.movement;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.client.settings.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraft.util.math.*;
import net.minecraft.network.*;
import net.minecraft.item.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.network.play.client.*;
import net.minecraft.entity.*;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.client.event.*;
import net.minecraft.util.*;

public class NoSlowDown extends Module
{
    public /* synthetic */ Setting<Boolean> superStrict;
    public /* synthetic */ Setting<Boolean> soulSand;
    public /* synthetic */ Setting<Boolean> sneakPacket;
    private /* synthetic */ boolean sneaking;
    private static /* synthetic */ NoSlowDown INSTANCE;
    public /* synthetic */ Setting<Boolean> noSlow;
    public /* synthetic */ Setting<Boolean> endPortal;
    public /* synthetic */ Setting<Boolean> strict;
    
    public static NoSlowDown getInstance() {
        if (NoSlowDown.INSTANCE == null) {
            NoSlowDown.INSTANCE = new NoSlowDown();
        }
        return NoSlowDown.INSTANCE;
    }
    
    static {
        keys = new KeyBinding[] { NoSlowDown.mc.gameSettings.keyBindForward, NoSlowDown.mc.gameSettings.keyBindBack, NoSlowDown.mc.gameSettings.keyBindLeft, NoSlowDown.mc.gameSettings.keyBindRight, NoSlowDown.mc.gameSettings.keyBindJump, NoSlowDown.mc.gameSettings.keyBindSprint };
        NoSlowDown.INSTANCE = new NoSlowDown();
    }
    
    @SubscribeEvent
    public void onPacket(final PacketEvent.Send send) {
        if (send.getPacket() instanceof CPacketPlayer && (boolean)this.strict.getValue() && (boolean)this.noSlow.getValue() && NoSlowDown.mc.player.isHandActive() && !NoSlowDown.mc.player.isRiding()) {
            NoSlowDown.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, new BlockPos(Math.floor(NoSlowDown.mc.player.posX), Math.floor(NoSlowDown.mc.player.posY), Math.floor(NoSlowDown.mc.player.posZ)), EnumFacing.DOWN));
        }
        if (send.getPacket() instanceof CPacketPlayerTryUseItem || send.getPacket() instanceof CPacketPlayerTryUseItemOnBlock) {
            final Item getItem = NoSlowDown.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem();
            if ((boolean)this.superStrict.getValue() && (getItem instanceof ItemFood || getItem instanceof ItemBow || getItem instanceof ItemPotion)) {
                NoSlowDown.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(NoSlowDown.mc.player.inventory.currentItem));
            }
        }
    }
    
    public void onUpdate() {
        NoSlowDown.mc.player.getActiveItemStack().getItem();
        if (this.sneaking && !NoSlowDown.mc.player.isHandActive() && (boolean)this.sneakPacket.getValue()) {
            NoSlowDown.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)NoSlowDown.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
            this.sneaking = false;
        }
    }
    
    public NoSlowDown() {
        super("NoSlowDown", "Prevents you from getting slowed down.", Module.Category.MOVEMENT, true, false, false);
        this.noSlow = (Setting<Boolean>)this.register(new Setting("NoSlow", (Object)true));
        this.soulSand = (Setting<Boolean>)this.register(new Setting("SoulSand", (Object)true));
        this.strict = (Setting<Boolean>)this.register(new Setting("StrictOld", (Object)false));
        this.sneakPacket = (Setting<Boolean>)this.register(new Setting("SneakPacket", (Object)false));
        this.endPortal = (Setting<Boolean>)this.register(new Setting("EndPortal", (Object)false));
        this.superStrict = (Setting<Boolean>)this.register(new Setting("2b2t", (Object)false));
        this.setInstance();
    }
    
    @SubscribeEvent
    public void onUseItem(final PlayerInteractEvent.RightClickItem rightClickItem) {
        final Item getItem = NoSlowDown.mc.player.getHeldItem(rightClickItem.getHand()).getItem();
        if ((getItem instanceof ItemFood || getItem instanceof ItemBow || (getItem instanceof ItemPotion && (boolean)this.sneakPacket.getValue())) && !this.sneaking) {
            NoSlowDown.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)NoSlowDown.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            this.sneaking = true;
        }
    }
    
    private void setInstance() {
        NoSlowDown.INSTANCE = this;
    }
    
    @SubscribeEvent
    public void onInput(final InputUpdateEvent inputUpdateEvent) {
        if ((boolean)this.noSlow.getValue() && NoSlowDown.mc.player.isHandActive() && !NoSlowDown.mc.player.isRiding()) {
            final MovementInput movementInput = inputUpdateEvent.getMovementInput();
            movementInput.moveStrafe *= 5.0f;
            final MovementInput movementInput2 = inputUpdateEvent.getMovementInput();
            movementInput2.moveForward *= 5.0f;
        }
    }
}
