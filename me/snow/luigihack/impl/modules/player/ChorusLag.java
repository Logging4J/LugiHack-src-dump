//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.player;

import me.snow.luigihack.impl.modules.*;
import net.minecraft.network.play.client.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.network.*;
import net.minecraft.util.math.*;
import me.snow.luigihack.impl.command.*;
import java.util.*;
import net.minecraftforge.event.entity.living.*;
import net.minecraft.init.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.snow.luigihack.api.event.events.*;

public class ChorusLag extends Module
{
    /* synthetic */ int delay2;
    /* synthetic */ Queue<CPacketConfirmTeleport> packetss;
    /* synthetic */ Queue<CPacketPlayer> packets;
    public final /* synthetic */ Setting<Integer> sDelay;
    /* synthetic */ boolean hackPacket;
    /* synthetic */ boolean posTp;
    /* synthetic */ double posY;
    /* synthetic */ double posZ;
    /* synthetic */ boolean ateChorus;
    /* synthetic */ int delay;
    /* synthetic */ double posX;
    
    public void sendPackets() {
        while (!this.packets.isEmpty()) {
            ChorusLag.mc.player.connection.sendPacket((Packet)this.packets.poll());
        }
        while (!this.packetss.isEmpty()) {
            ChorusLag.mc.player.connection.sendPacket((Packet)this.packetss.poll());
        }
        this.hackPacket = false;
        this.delay2 = 0;
        this.ateChorus = false;
    }
    
    public void onUpdate() {
        if (this.ateChorus) {
            ++this.delay;
            ++this.delay2;
            if (!ChorusLag.mc.player.getPosition().equals((Object)new BlockPos(this.posX, this.posY, this.posZ)) && !this.posTp && ChorusLag.mc.player.getDistance(this.posX, this.posY, this.posZ) > 1.0) {
                ChorusLag.mc.player.setPosition(this.posX, this.posY, this.posZ);
                this.posTp = true;
            }
        }
        if (this.ateChorus && this.delay2 > (int)this.sDelay.getValue()) {
            this.ateChorus = false;
            this.delay = 0;
            this.hackPacket = true;
            this.delay2 = 0;
            this.sendPackets();
        }
        if (this.delay2 == (int)this.sDelay.getValue() - 40) {
            Command.sendMessage("Chorusing In 2 seconds");
        }
    }
    
    public ChorusLag() {
        super("ChorusLag", "Makes your teleport delayed serverside", Module.Category.PLAYER, true, false, false);
        this.sDelay = (Setting<Integer>)this.register(new Setting("Lag Delay", (Object)18, (Object)0, (Object)500));
        this.delay = 0;
        this.delay2 = 0;
        this.ateChorus = false;
        this.hackPacket = false;
        this.posTp = false;
        this.packets = new LinkedList<CPacketPlayer>();
        this.packetss = new LinkedList<CPacketConfirmTeleport>();
    }
    
    @SubscribeEvent
    public void finishEating(final LivingEntityUseItemEvent.Finish finish) {
        if (finish.getEntity() == ChorusLag.mc.player && finish.getResultStack().getItem().equals(Items.CHORUS_FRUIT)) {
            this.posX = ChorusLag.mc.player.posX;
            this.posY = ChorusLag.mc.player.posY;
            this.posZ = ChorusLag.mc.player.posZ;
            this.posTp = false;
            this.ateChorus = true;
        }
    }
    
    @SubscribeEvent
    public void Event(final UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
    }
    
    public void onEnable() {
        this.ateChorus = false;
        this.hackPacket = false;
        this.posTp = false;
    }
    
    @SubscribeEvent
    public void finishEating(final LivingEntityUseItemEvent.Start start) {
    }
    
    @SubscribeEvent
    public void onUpdate(final PacketEvent.Send send) {
        if (send.getPacket() instanceof CPacketConfirmTeleport && this.ateChorus && this.delay2 < (int)this.sDelay.getValue()) {
            this.packetss.add((CPacketConfirmTeleport)send.getPacket());
            send.setCanceled(true);
        }
        if (send.getPacket() instanceof CPacketPlayer && this.ateChorus && this.delay2 < (int)this.sDelay.getValue()) {
            this.packets.add((CPacketPlayer)send.getPacket());
            send.setCanceled(true);
        }
    }
}
