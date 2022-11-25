//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.command.commands;

import me.snow.luigihack.impl.command.*;
import me.snow.luigihack.api.util.*;
import java.util.*;

public class HistoryCommand extends Command
{
    public void execute(final String[] array) {
        if (array.length == 1 || array.length == 0) {
            sendMessage("§cPlease specify a player.");
        }
        UUID uuidFromName;
        try {
            uuidFromName = PlayerUtil.getUUIDFromName(array[0]);
        }
        catch (Exception ex) {
            sendMessage("An error occured.");
            return;
        }
        List historyOfNames;
        try {
            historyOfNames = PlayerUtil.getHistoryOfNames(uuidFromName);
        }
        catch (Exception ex2) {
            sendMessage("An error occured.");
            return;
        }
        if (historyOfNames != null) {
            sendMessage(String.valueOf(new StringBuilder().append(array[0]).append("´s name history:")));
            final Iterator<String> iterator = historyOfNames.iterator();
            while (iterator.hasNext()) {
                sendMessage((String)iterator.next());
            }
        }
        else {
            sendMessage("No names found.");
        }
    }
    
    public HistoryCommand() {
        super("history", new String[] { "<player>" });
    }
}
