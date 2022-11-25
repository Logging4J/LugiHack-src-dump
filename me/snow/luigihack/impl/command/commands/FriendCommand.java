//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.command.commands;

import me.snow.luigihack.impl.command.*;
import me.snow.luigihack.*;
import java.util.*;

public class FriendCommand extends Command
{
    public void execute(final String[] array) {
        if (array.length == 1) {
            if (LuigiHack.friendManager.getFriends().isEmpty()) {
                sendMessage("You currently don't have any friends added.");
            }
            else {
                sendMessage("Friends: ");
                final Iterator<Map.Entry<String, V>> iterator = LuigiHack.friendManager.getFriends().entrySet().iterator();
                while (iterator.hasNext()) {
                    sendMessage((String)iterator.next().getKey());
                }
            }
            return;
        }
        if (array.length == 2) {
            if ("reset".equals(array[0])) {
                LuigiHack.friendManager.onLoad();
                sendMessage("Friends got reset.");
            }
            else {
                sendMessage(String.valueOf(new StringBuilder().append(array[0]).append(LuigiHack.friendManager.isFriend(array[0]) ? " is friended." : " isnt friended.")));
            }
            return;
        }
        if (array.length >= 2) {
            final String s = array[0];
            switch (s) {
                case "add": {
                    LuigiHack.friendManager.addFriend(array[1]);
                    sendMessage(String.valueOf(new StringBuilder().append("§b").append(array[1]).append(" has been friended")));
                    break;
                }
                case "del": {
                    LuigiHack.friendManager.removeFriend(array[1]);
                    sendMessage(String.valueOf(new StringBuilder().append("§c").append(array[1]).append(" has been unfriended")));
                    break;
                }
                default: {
                    sendMessage("§cBad Command, try: friend <add/del/name> <name>.");
                    break;
                }
            }
        }
    }
    
    public FriendCommand() {
        super("friend", new String[] { "<add/del/name/clear>", "<name>" });
    }
}
