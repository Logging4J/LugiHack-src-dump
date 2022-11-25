//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.misc;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraft.network.play.server.*;
import java.util.*;
import joptsimple.internal.*;
import me.snow.luigihack.impl.command.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.network.play.client.*;
import net.minecraft.util.math.*;
import net.minecraft.network.*;

public class PluginsGrabber extends Module
{
    @SubscribeEvent
    public void onPacketReceive(final PacketEvent.Receive receive) {
        if (receive.getPacket() instanceof SPacketTabComplete) {
            final SPacketTabComplete sPacketTabComplete = (SPacketTabComplete)receive.getPacket();
            final ArrayList<Comparable> list = new ArrayList<Comparable>();
            final String[] getMatches = sPacketTabComplete.getMatches();
            for (int length = getMatches.length, i = 0; i < length; ++i) {
                final String[] split = getMatches[i].split(":");
                if (split.length > 1) {
                    final String replace;
                    if (!list.contains(replace = split[0].replace("/", ""))) {
                        list.add(replace);
                    }
                }
            }
            Collections.sort(list);
            if (!list.isEmpty()) {
                Command.sendMessage(String.valueOf(new StringBuilder().append("Plugins §7(§8").append(list.size()).append("§7): §9").append(Strings.join((String[])list.toArray(new String[0]), "§7, §9"))));
            }
            else {
                Command.sendMessage("Failed to detect Plugins");
            }
            this.disable();
        }
    }
    
    @Override
    public void onEnable() {
        if (nullCheck()) {
            return;
        }
        PluginsGrabber.mc.player.connection.sendPacket((Packet)new CPacketTabComplete("/", (BlockPos)null, false));
    }
    
    public PluginsGrabber() {
        super("PluginsGrabber", "Attempts to grab and display the plugins installed on a server", Category.MISC, true, false, false);
    }
}
