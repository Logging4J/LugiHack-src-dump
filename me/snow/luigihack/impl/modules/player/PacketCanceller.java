//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.player;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraft.network.play.client.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class PacketCanceller extends Module
{
    private /* synthetic */ Setting<Boolean> input;
    private /* synthetic */ Setting<Boolean> useitemOnblock;
    private /* synthetic */ Setting<Boolean> animation;
    private /* synthetic */ Setting<Boolean> abilities;
    private /* synthetic */ Setting<Boolean> entity;
    private /* synthetic */ Setting<Boolean> digging;
    private /* synthetic */ Setting<Boolean> useitem;
    private /* synthetic */ Setting<Boolean> vehicle;
    private /* synthetic */ Setting<Boolean> player;
    private /* synthetic */ Setting<Boolean> chat;
    private /* synthetic */ Setting<Boolean> useEntity;
    
    public PacketCanceller() {
        super("PacketCancel", "Cancel packets", Module.Category.PLAYER, true, false, false);
        this.input = (Setting<Boolean>)this.register(new Setting("Input", (Object)false));
        this.player = (Setting<Boolean>)this.register(new Setting("Player", (Object)false));
        this.abilities = (Setting<Boolean>)this.register(new Setting("Abilities", (Object)false));
        this.digging = (Setting<Boolean>)this.register(new Setting("Digging", (Object)false));
        this.useitem = (Setting<Boolean>)this.register(new Setting("TryUseItem", (Object)false));
        this.useitemOnblock = (Setting<Boolean>)this.register(new Setting("TryUseItemOnBlock", (Object)false));
        this.entity = (Setting<Boolean>)this.register(new Setting("EntityAction", (Object)false));
        this.useEntity = (Setting<Boolean>)this.register(new Setting("UseEntity", (Object)false));
        this.vehicle = (Setting<Boolean>)this.register(new Setting("Vehicle", (Object)false));
        this.chat = (Setting<Boolean>)this.register(new Setting("SendChat", (Object)false));
        this.animation = (Setting<Boolean>)this.register(new Setting("Animation", (Object)false));
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        if (send.getStage() == 0) {
            if (send.getPacket() instanceof CPacketInput && (boolean)this.input.getValue()) {
                send.setCanceled(true);
            }
            if (send.getPacket() instanceof CPacketPlayer && (boolean)this.player.getValue()) {
                send.setCanceled(true);
            }
            if (send.getPacket() instanceof CPacketPlayerAbilities && (boolean)this.abilities.getValue()) {
                send.setCanceled(true);
            }
            if (send.getPacket() instanceof CPacketPlayerDigging && (boolean)this.digging.getValue()) {
                send.setCanceled(true);
            }
            if (send.getPacket() instanceof CPacketPlayerTryUseItem && (boolean)this.useitem.getValue()) {
                send.setCanceled(true);
            }
            if (send.getPacket() instanceof CPacketPlayerTryUseItemOnBlock && (boolean)this.useitemOnblock.getValue()) {
                send.setCanceled(true);
            }
            if (send.getPacket() instanceof CPacketEntityAction && (boolean)this.entity.getValue()) {
                send.setCanceled(true);
            }
            if (send.getPacket() instanceof CPacketUseEntity && (boolean)this.useEntity.getValue()) {
                send.setCanceled(true);
            }
            if (send.getPacket() instanceof CPacketVehicleMove && (boolean)this.vehicle.getValue()) {
                send.setCanceled(true);
            }
            if (send.getPacket() instanceof CPacketChatMessage && (boolean)this.chat.getValue()) {
                send.setCanceled(true);
            }
            if (send.getPacket() instanceof CPacketAnimation && (boolean)this.animation.getValue()) {
                send.setCanceled(true);
            }
        }
    }
}
