//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.event.events;

import net.minecraft.network.*;
import me.snow.luigihack.api.event.*;

public class EventPacketRecieve extends PhobosEvent
{
    private final /* synthetic */ Packet<?> packet;
    
    public Packet<?> getPacket() {
        return this.packet;
    }
    
    public EventPacketRecieve(final EnumStages enumStages, final Packet<?> packet) {
        super(enumStages);
        this.packet = packet;
    }
}
