//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.command.commands;

import me.snow.luigihack.impl.command.*;
import me.snow.luigihack.*;
import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import com.google.gson.*;
import me.snow.luigihack.api.manager.*;
import me.snow.luigihack.impl.*;
import java.util.*;

public class ModuleCommand extends Command
{
    public void execute(final String[] array) {
        if (array.length == 1) {
            sendMessage("Modules: ");
            for (final Module.Category category : LuigiHack.moduleManager.getCategories()) {
                final StringBuilder obj = new StringBuilder(String.valueOf(new StringBuilder().append(category.getName()).append(": ")));
                for (final Module module : LuigiHack.moduleManager.getModulesByCategory(category)) {
                    obj.append(module.isEnabled() ? "§a" : "§c").append(module.getName()).append("§r").append(", ");
                }
                sendMessage(String.valueOf(obj));
            }
            return;
        }
        final Module moduleByDisplayName = LuigiHack.moduleManager.getModuleByDisplayName(array[0]);
        if (moduleByDisplayName == null) {
            final ModuleManager moduleManager = LuigiHack.moduleManager;
            final Module moduleByName = ModuleManager.getModuleByName(array[0]);
            if (moduleByName == null) {
                sendMessage("§cThis module doesnt exist.");
                return;
            }
            sendMessage(String.valueOf(new StringBuilder().append("§c This is the original name of the module. Its current name is: ").append(moduleByName.getDisplayName())));
        }
        else {
            if (array.length == 2) {
                sendMessage(String.valueOf(new StringBuilder().append(moduleByDisplayName.getDisplayName()).append(" : ").append(moduleByDisplayName.getDescription())));
                for (final Setting setting : moduleByDisplayName.getSettings()) {
                    sendMessage(String.valueOf(new StringBuilder().append(setting.getName()).append(" : ").append(setting.getValue()).append(", ").append(setting.getDescription())));
                }
                return;
            }
            if (array.length == 3) {
                if (array[1].equalsIgnoreCase("set")) {
                    sendMessage("§cPlease specify a setting.");
                }
                else if (array[1].equalsIgnoreCase("reset")) {
                    for (final Setting setting2 : moduleByDisplayName.getSettings()) {
                        setting2.setValue(setting2.getDefaultValue());
                    }
                }
                else {
                    sendMessage("§cThis command doesnt exist.");
                }
                return;
            }
            if (array.length == 4) {
                sendMessage("§cPlease specify a value.");
                return;
            }
            final Setting settingByName;
            if (array.length == 5 && (settingByName = moduleByDisplayName.getSettingByName(array[2])) != null) {
                final JsonParser jsonParser = new JsonParser();
                if (settingByName.getType().equalsIgnoreCase("String")) {
                    settingByName.setValue((Object)array[3]);
                    sendMessage(String.valueOf(new StringBuilder().append("§a").append(moduleByDisplayName.getName()).append(" ").append(settingByName.getName()).append(" has been set to ").append(array[3]).append(".")));
                    return;
                }
                try {
                    if (settingByName.getName().equalsIgnoreCase("Enabled")) {
                        if (array[3].equalsIgnoreCase("true")) {
                            moduleByDisplayName.enable();
                        }
                        if (array[3].equalsIgnoreCase("false")) {
                            moduleByDisplayName.disable();
                        }
                    }
                    ConfigManager.setValueFromJson((Feature)moduleByDisplayName, settingByName, jsonParser.parse(array[3]));
                }
                catch (Exception ex) {
                    sendMessage(String.valueOf(new StringBuilder().append("§cBad Value! This setting requires a: ").append(settingByName.getType()).append(" value.")));
                    return;
                }
                sendMessage(String.valueOf(new StringBuilder().append("§a").append(moduleByDisplayName.getName()).append(" ").append(settingByName.getName()).append(" has been set to ").append(array[3]).append(".")));
            }
        }
    }
    
    public ModuleCommand() {
        super("module", new String[] { "<module>", "<set/reset>", "<setting>", "<value>" });
    }
}
