//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.misc;

import me.snow.luigihack.impl.modules.*;
import net.minecraft.network.play.client.*;
import net.minecraftforge.event.entity.living.*;
import net.minecraft.init.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraft.util.math.*;
import net.minecraft.item.*;
import java.util.*;
import net.minecraft.network.*;

public class ChorusControl extends Module
{
    /* synthetic */ boolean ateChorus;
    /* synthetic */ double posX;
    /* synthetic */ double posY;
    /* synthetic */ Queue<CPacketPlayer> packets;
    /* synthetic */ boolean hackPacket;
    /* synthetic */ Queue<CPacketConfirmTeleport> packetss;
    /* synthetic */ double posZ;
    /* synthetic */ boolean posTp;
    
    @SubscribeEvent
    public void finishEating(final LivingEntityUseItemEvent.Finish finish) {
        if (finish.getEntity() == ChorusControl.mc.player && finish.getResultStack().getItem().equals(Items.CHORUS_FRUIT)) {
            this.posX = ChorusControl.mc.player.posX;
            this.posY = ChorusControl.mc.player.posY;
            this.posZ = ChorusControl.mc.player.posZ;
            this.posTp = false;
            this.ateChorus = true;
        }
    }
    
    @Override
    public void onEnable() {
        this.ateChorus = false;
        this.hackPacket = false;
        this.posTp = false;
    }
    
    @SubscribeEvent
    public void onUpdate(final PacketEvent.Send send) {
        if (send.getPacket() instanceof CPacketConfirmTeleport && this.ateChorus) {
            this.packetss.add((CPacketConfirmTeleport)send.getPacket());
            send.setCanceled(true);
        }
        if (send.getPacket() instanceof CPacketPlayer && this.ateChorus) {
            this.packets.add((CPacketPlayer)send.getPacket());
            send.setCanceled(true);
        }
    }
    
    @SubscribeEvent
    public void Event(final UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
    }
    
    @Override
    public void onUpdate() {
        if (this.ateChorus && !ChorusControl.mc.player.getPosition().equals((Object)new BlockPos(this.posX, this.posY, this.posZ)) && !this.posTp && ChorusControl.mc.player.getDistance(this.posX, this.posY, this.posZ) > 1.0) {
            ChorusControl.mc.player.setPosition(this.posX, this.posY, this.posZ);
            this.posTp = true;
        }
        if (this.ateChorus && ChorusControl.mc.player != null && ChorusControl.mc.player.getHeldItemMainhand().getItem() instanceof ItemChorusFruit && ChorusControl.mc.player.isHandActive()) {
            this.ateChorus = false;
            this.hackPacket = true;
            this.sendPackets();
        }
    }
    
    public ChorusControl() {
        super("ChorusControl", "After eating a chorus fruit instead of teleporting you it will hold the packet and instantly teleport you the next time you right click with chorus", Category.MISC, true, false, false);
        this.ateChorus = false;
        this.hackPacket = false;
        this.posTp = false;
        this.packets = new LinkedList<CPacketPlayer>();
        this.packetss = new LinkedList<CPacketConfirmTeleport>();
    }
    
    public void sendPackets() {
        while (!this.packets.isEmpty()) {
            ChorusControl.mc.player.connection.sendPacket((Packet)this.packets.poll());
        }
        while (!this.packetss.isEmpty()) {
            ChorusControl.mc.player.connection.sendPacket((Packet)this.packetss.poll());
        }
        this.hackPacket = false;
        this.ateChorus = false;
    }
}
