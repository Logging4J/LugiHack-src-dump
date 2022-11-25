//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.command.commands;

import me.snow.luigihack.impl.command.*;
import java.awt.*;
import java.io.*;

public class OpenFolderCommand extends Command
{
    public void execute(final String[] array) {
        try {
            Desktop.getDesktop().open(new File("luigihack/"));
            Command.sendMessage("Opened config folder!", false);
        }
        catch (IOException ex) {
            Command.sendMessage("Could not open config folder!", false);
            ex.printStackTrace();
        }
    }
    
    public OpenFolderCommand() {
        super("openfolder", new String[0]);
    }
}
