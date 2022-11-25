//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.command.commands;

import me.snow.luigihack.impl.command.*;
import me.snow.luigihack.*;
import java.util.*;

public class HelpCommand extends Command
{
    public void execute(final String[] array) {
        sendMessage("You can use following commands: ");
        final Iterator<Command> iterator = LuigiHack.commandManager.getCommands().iterator();
        while (iterator.hasNext()) {
            sendMessage(String.valueOf(new StringBuilder().append(LuigiHack.commandManager.getPrefix()).append(iterator.next().getName())));
        }
    }
    
    public HelpCommand() {
        super("help");
    }
}
