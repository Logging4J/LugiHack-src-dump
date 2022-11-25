//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.command.commands;

import me.snow.luigihack.impl.command.*;

public class ClearRamCommand extends Command
{
    public void execute(final String[] array) {
        System.gc();
        Command.sendMessage("Finished clearing the ram.", false);
    }
    
    public ClearRamCommand() {
        super("clearram");
    }
}
