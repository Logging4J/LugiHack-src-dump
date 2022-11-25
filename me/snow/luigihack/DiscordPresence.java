//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack;

import net.minecraft.client.gui.*;
import net.minecraft.client.*;
import me.snow.luigihack.impl.modules.client.*;
import club.minnced.discord.rpc.*;

public class DiscordPresence
{
    private static /* synthetic */ Thread thread;
    public static /* synthetic */ DiscordRichPresence presence;
    private static final /* synthetic */ DiscordRPC rpc;
    private static /* synthetic */ int index;
    
    public static void stop() {
        if (DiscordPresence.thread != null && !DiscordPresence.thread.isInterrupted()) {
            DiscordPresence.thread.interrupt();
        }
        DiscordPresence.rpc.Discord_Shutdown();
    }
    
    public static void start() {
        DiscordPresence.rpc.Discord_Initialize("969887055301705778", new DiscordEventHandlers(), true, "");
        DiscordPresence.presence.startTimestamp = System.currentTimeMillis() / 1000L;
        DiscordPresence.presence.details = ((Minecraft.getMinecraft().currentScreen instanceof GuiMainMenu) ? "In the main menu." : String.valueOf(new StringBuilder().append("Playing ").append((Minecraft.getMinecraft().currentServerData != null) ? (RPC.INSTANCE.showIP.getValue() ? String.valueOf(new StringBuilder().append("on ").append(Minecraft.getMinecraft().currentServerData.serverIP).append(".")) : " multiplayer.") : " singleplayer.")));
        DiscordPresence.presence.state = RPC.INSTANCE.state;
        DiscordPresence.presence.largeImageKey = "LuigiHack";
        DiscordPresence.presence.largeImageText = " LuigiHack+ 1.0.0";
        DiscordPresence.rpc.Discord_UpdatePresence(DiscordPresence.presence);
        DiscordRichPresence presence;
        String value;
        String value2;
        final StringBuilder sb;
        (DiscordPresence.thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                DiscordPresence.rpc.Discord_RunCallbacks();
                presence = DiscordPresence.presence;
                if (Minecraft.getMinecraft().currentScreen instanceof GuiMainMenu) {
                    value = "In the main menu.";
                }
                else {
                    new StringBuilder().append("Playing ");
                    if (Minecraft.getMinecraft().currentServerData != null) {
                        if (RPC.INSTANCE.showIP.getValue()) {
                            value2 = String.valueOf(new StringBuilder().append("on ").append(Minecraft.getMinecraft().currentServerData.serverIP).append("."));
                        }
                        else {
                            value2 = " multiplayer.";
                        }
                    }
                    else {
                        value2 = " singleplayer.";
                    }
                    value = String.valueOf(sb.append(value2));
                }
                presence.details = value;
                DiscordPresence.presence.state = RPC.INSTANCE.state;
                if (RPC.INSTANCE.catMode.getValue()) {
                    if (DiscordPresence.index == 16) {
                        DiscordPresence.index = 1;
                    }
                    DiscordPresence.presence.largeImageKey = String.valueOf(new StringBuilder().append("cat").append(DiscordPresence.index));
                    ++DiscordPresence.index;
                }
                DiscordPresence.rpc.Discord_UpdatePresence(DiscordPresence.presence);
                try {
                    Thread.sleep(2000L);
                }
                catch (InterruptedException ex) {}
            }
        }, "RPC-Callback-Handler")).start();
    }
    
    static {
        DiscordPresence.index = 1;
        rpc = DiscordRPC.INSTANCE;
        DiscordPresence.presence = new DiscordRichPresence();
    }
}
