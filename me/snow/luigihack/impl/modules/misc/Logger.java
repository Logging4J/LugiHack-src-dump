//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.misc;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraft.client.gui.*;
import me.snow.luigihack.impl.command.*;
import net.minecraft.network.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.util.*;
import java.io.*;
import java.lang.reflect.*;

public class Logger extends Module
{
    public /* synthetic */ Setting<Boolean> chat;
    public /* synthetic */ Setting<Boolean> noPing;
    public /* synthetic */ Setting<Boolean> fullInfo;
    public /* synthetic */ Setting<Packets> packets;
    
    @SubscribeEvent(receiveCanceled = true)
    public void onPacketReceive(final PacketEvent.Receive receive) {
        if ((boolean)this.noPing.getValue() && Logger.mc.currentScreen instanceof GuiMultiplayer) {
            return;
        }
        if (this.packets.getValue() == Packets.INCOMING || this.packets.getValue() == Packets.ALL) {
            if (this.chat.getValue()) {
                Command.sendMessage(receive.getPacket().toString());
            }
            else {
                this.writePacketOnConsole((Packet<?>)receive.getPacket(), true);
            }
        }
    }
    
    @SubscribeEvent(receiveCanceled = true)
    public void onPacketSend(final PacketEvent.Send send) {
        if ((boolean)this.noPing.getValue() && Logger.mc.currentScreen instanceof GuiMultiplayer) {
            return;
        }
        if (this.packets.getValue() == Packets.OUTGOING || this.packets.getValue() == Packets.ALL) {
            if (this.chat.getValue()) {
                Command.sendMessage(send.getPacket().toString());
            }
            else {
                this.writePacketOnConsole((Packet<?>)send.getPacket(), false);
            }
        }
    }
    
    private void writePacketOnConsole(final Packet<?> obj, final boolean b) {
        if (this.fullInfo.getValue()) {
            System.out.println(String.valueOf(new StringBuilder().append(b ? "In: " : "Send: ").append(obj.getClass().getSimpleName()).append(" {")));
            try {
                for (Serializable s = obj.getClass(); s != Object.class; s = ((Class<Object>)s).getSuperclass()) {
                    for (final Field field : ((Class)s).getDeclaredFields()) {
                        if (field != null) {
                            if (!field.isAccessible()) {
                                field.setAccessible(true);
                            }
                            System.out.println(StringUtils.stripControlCodes(String.valueOf(new StringBuilder().append("      ").append(field.getType().getSimpleName()).append(" ").append(field.getName()).append(" : ").append(field.get(obj)))));
                        }
                    }
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("}");
        }
        else {
            System.out.println(obj.toString());
        }
    }
    
    public Logger() {
        super("Logger", "Logs stuff", Category.MISC, true, false, false);
        this.packets = (Setting<Packets>)this.register(new Setting("Packets", (Object)Packets.OUTGOING));
        this.chat = (Setting<Boolean>)this.register(new Setting("Chat", (Object)false));
        this.fullInfo = (Setting<Boolean>)this.register(new Setting("FullInfo", (Object)false));
        this.noPing = (Setting<Boolean>)this.register(new Setting("NoPing", (Object)false));
    }
    
    public enum Packets
    {
        OUTGOING, 
        INCOMING, 
        ALL, 
        NONE;
    }
}
