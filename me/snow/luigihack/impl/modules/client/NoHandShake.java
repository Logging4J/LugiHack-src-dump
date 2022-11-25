//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.client;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraftforge.fml.common.network.internal.*;
import net.minecraft.network.play.client.*;
import io.netty.buffer.*;
import net.minecraft.network.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class NoHandShake extends Module
{
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        if (send.getPacket() instanceof FMLProxyPacket && !NoHandShake.mc.isSingleplayer()) {
            send.setCanceled(true);
        }
        final CPacketCustomPayload cPacketCustomPayload;
        if (send.getPacket() instanceof CPacketCustomPayload && (cPacketCustomPayload = (CPacketCustomPayload)send.getPacket()).getChannelName().equals("MC|Brand")) {
            cPacketCustomPayload.data = new PacketBuffer(Unpooled.buffer()).writeString("vanilla");
        }
    }
    
    public NoHandShake() {
        super("NoHandShake", "Doesn't send your mod list to the server.", Category.CLIENT, true, false, false);
    }
}
