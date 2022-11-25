//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.command.commands;

import me.snow.luigihack.impl.command.*;
import me.snow.luigihack.*;
import me.snow.luigihack.api.manager.*;
import org.lwjgl.input.*;
import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.impl.modules.*;

public class BindCommand extends Command
{
    public BindCommand() {
        super("bind", new String[] { "<module>", "<bind>" });
    }
    
    public void execute(final String[] array) {
        if (array.length == 1) {
            sendMessage("Please specify a module.");
            return;
        }
        final String str = array[1];
        final String s = array[0];
        final ModuleManager moduleManager = LuigiHack.moduleManager;
        final Module moduleByName = ModuleManager.getModuleByName(s);
        if (moduleByName == null) {
            sendMessage(String.valueOf(new StringBuilder().append("Unknown module '").append(moduleByName).append("'!")));
            return;
        }
        if (str == null) {
            sendMessage(String.valueOf(new StringBuilder().append(moduleByName.getName()).append(" is bound to &b").append(moduleByName.getBind().toString())));
            return;
        }
        int keyIndex = Keyboard.getKeyIndex(str.toUpperCase());
        if (str.equalsIgnoreCase("none")) {
            keyIndex = -1;
        }
        if (keyIndex == 0) {
            sendMessage(String.valueOf(new StringBuilder().append("Unknown key '").append(str).append("'!")));
            return;
        }
        moduleByName.bind.setValue((Object)new Bind(keyIndex));
        sendMessage(String.valueOf(new StringBuilder().append("Bind for &b").append(moduleByName.getName()).append("&r set to &b").append(str.toUpperCase())));
    }
}
