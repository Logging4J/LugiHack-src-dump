//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.command.commands;

import me.snow.luigihack.impl.command.*;
import java.io.*;
import java.util.stream.*;
import me.snow.luigihack.*;
import java.util.*;

public class ConfigCommand extends Command
{
    public void execute(final String[] array) {
        if (array.length == 1) {
            sendMessage("You`ll find the config files in your gameProfile directory under luigihack/config");
            return;
        }
        if (array.length == 2) {
            if ("list".equals(array[0])) {
                final String str = "Configs: ";
                final List<? super Object> list = Arrays.stream((Object[])Objects.requireNonNull((T[])new File("luigihack/").listFiles())).filter(File::isDirectory).filter(file -> !file.getName().equals("util")).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList());
                final StringBuilder obj = new StringBuilder(str);
                final Iterator<File> iterator = list.iterator();
                while (iterator.hasNext()) {
                    obj.append(iterator.next().getName()).append(", ");
                }
                sendMessage(String.valueOf(new StringBuilder().append("§a").append(String.valueOf(obj))));
            }
            else {
                sendMessage("§cNot a valid command... Possible usage: <list>");
            }
        }
        if (array.length >= 3) {
            final String s = array[0];
            switch (s) {
                case "save": {
                    LuigiHack.configManager.saveConfig(array[1]);
                    sendMessage("§aConfig has been saved.");
                    break;
                }
                case "load": {
                    LuigiHack.moduleManager.onUnload();
                    LuigiHack.configManager.loadConfig(array[1]);
                    LuigiHack.moduleManager.onLoad();
                    sendMessage("§aConfig has been loaded.");
                    break;
                }
                default: {
                    sendMessage("§cNot a valid command... Possible usage: <save/load>");
                    break;
                }
            }
        }
    }
    
    public ConfigCommand() {
        super("config", new String[] { "<save/load>" });
    }
}
