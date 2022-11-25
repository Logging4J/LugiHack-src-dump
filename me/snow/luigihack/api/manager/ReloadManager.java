//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.manager;

import me.snow.luigihack.impl.*;
import net.minecraftforge.common.*;
import me.snow.luigihack.impl.command.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraft.network.play.client.*;
import me.snow.luigihack.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class ReloadManager extends Feature
{
    public /* synthetic */ String prefix;
    
    public void unload() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
    
    public void init(final String s) {
        this.prefix = s;
        MinecraftForge.EVENT_BUS.register((Object)this);
        if (!Feature.fullNullCheck()) {
            Command.sendMessage(String.valueOf(new StringBuilder().append("§cPhobos has been unloaded. Type ").append(s).append("reload to reload.")));
        }
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        final CPacketChatMessage cPacketChatMessage;
        if (send.getPacket() instanceof CPacketChatMessage && (cPacketChatMessage = (CPacketChatMessage)send.getPacket()).getMessage().startsWith(this.prefix) && cPacketChatMessage.getMessage().contains("reload")) {
            LuigiHack.load();
            send.setCanceled(true);
        }
    }
}
