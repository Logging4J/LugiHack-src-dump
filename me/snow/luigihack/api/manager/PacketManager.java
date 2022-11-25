//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.manager;

import me.snow.luigihack.impl.*;
import net.minecraft.network.play.server.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.network.*;
import java.util.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.fml.common.gameevent.*;

public class PacketManager extends Feature
{
    /* synthetic */ SPacketPlayerPosLook pPlayerPosLook;
    /* synthetic */ boolean caughtPlayerPosLook;
    /* synthetic */ SPacketExplosion pExplosion;
    /* synthetic */ Timer timerPlayerPosLook;
    /* synthetic */ Timer timerExplosion;
    private final /* synthetic */ List<Packet<?>> noEventPackets;
    /* synthetic */ boolean caughtPExplosion;
    
    public PacketManager() {
        this.noEventPackets = new ArrayList<Packet<?>>();
        this.pExplosion = null;
        this.timerExplosion = new Timer();
        this.caughtPExplosion = false;
        this.pPlayerPosLook = null;
        this.timerPlayerPosLook = new Timer();
        this.caughtPlayerPosLook = false;
    }
    
    public boolean caughtPlayerPosLook() {
        return this.caughtPlayerPosLook;
    }
    
    public SPacketExplosion pExplosion() {
        return this.pExplosion;
    }
    
    public void sendPacketNoEvent(final Packet<?> packet) {
        if (packet != null && !Feature.nullCheck()) {
            this.noEventPackets.add(packet);
            PacketManager.mc.player.connection.sendPacket((Packet)packet);
        }
    }
    
    public SPacketPlayerPosLook pPlayerPosLook() {
        return this.pPlayerPosLook;
    }
    
    @SubscribeEvent
    public void onPacketReceive(final PacketEvent.Receive receive) {
        if (fullNullCheck()) {
            return;
        }
        if (receive.getPacket() instanceof SPacketPlayerPosLook) {
            this.pPlayerPosLook = (SPacketPlayerPosLook)receive.getPacket();
            this.timerPlayerPosLook.reset();
            this.caughtPlayerPosLook = true;
        }
        if (receive.getPacket() instanceof SPacketExplosion) {
            this.pExplosion = (SPacketExplosion)receive.getPacket();
            this.timerExplosion.reset();
            this.caughtPExplosion = true;
        }
    }
    
    public boolean shouldSendPacket(final Packet<?> packet) {
        if (this.noEventPackets.contains(packet)) {
            this.noEventPackets.remove(packet);
            return false;
        }
        return true;
    }
    
    public Timer timerExplosion() {
        return this.timerExplosion;
    }
    
    @SubscribeEvent
    public void onUpdate(final TickEvent.ClientTickEvent clientTickEvent) {
        if (fullNullCheck()) {
            return;
        }
        if (this.timerExplosion.passedMs(250L)) {
            this.pExplosion = null;
            this.caughtPExplosion = false;
        }
        if (this.timerPlayerPosLook.passedMs(250L)) {
            this.pPlayerPosLook = null;
            this.caughtPlayerPosLook = false;
        }
    }
    
    public boolean caughtPExplosion() {
        return this.caughtPExplosion;
    }
    
    public Timer timerPlayerPosLook() {
        return this.timerPlayerPosLook;
    }
}
