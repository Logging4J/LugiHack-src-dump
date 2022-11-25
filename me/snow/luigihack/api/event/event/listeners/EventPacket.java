//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.event.event.listeners;

import me.snow.luigihack.api.event.event.*;
import net.minecraft.network.*;

public class EventPacket extends Event<EventPacket>
{
    /* synthetic */ Packet packet;
    
    public EventPacket(final Packet packet) {
        this.packet = packet;
    }
    
    public void setPacket(final Packet packet) {
        this.packet = packet;
    }
    
    public Packet getPacket() {
        return this.packet;
    }
}
