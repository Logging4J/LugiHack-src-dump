//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.command.commands;

import me.snow.luigihack.impl.command.*;
import me.snow.luigihack.impl.modules.client.*;
import me.snow.luigihack.*;

public class PrefixCommand extends Command
{
    public PrefixCommand() {
        super("prefix", new String[] { "<char>" });
    }
    
    public void execute(final String[] array) {
        if (array.length == 1) {
            Command.sendMessage("§cSpecify a new prefix.");
            return;
        }
        ((ClickGui)LuigiHack.moduleManager.getModuleByClass((Class)ClickGui.class)).prefix.setValue((Object)array[0]);
        Command.sendMessage(String.valueOf(new StringBuilder().append("Prefix set to §a").append(LuigiHack.commandManager.getPrefix())));
    }
}
