//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util.ca.event;

import me.snow.luigihack.api.event.*;
import net.minecraft.network.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class PacketEventWp extends EventStage
{
    private final /* synthetic */ Packet<?> packet;
    
    public <T extends Packet<?>> T getPacket() {
        return (T)this.packet;
    }
    
    public PacketEventWp(final int n, final Packet<?> packet) {
        super(n);
        this.packet = packet;
    }
    
    @Cancelable
    public static class Send extends PacketEventWp
    {
        public Send(final int n, final Packet<?> packet) {
            super(n, packet);
        }
    }
    
    @Cancelable
    public static class Receive extends PacketEventWp
    {
        public Receive(final int n, final Packet<?> packet) {
            super(n, packet);
        }
    }
}
