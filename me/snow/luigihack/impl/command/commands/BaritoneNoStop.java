//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.command.commands;

import me.snow.luigihack.impl.command.*;
import me.snow.luigihack.*;

public class BaritoneNoStop extends Command
{
    public BaritoneNoStop() {
        super("noStop", new String[] { "<prefix>", "<x>", "<y>", "<z>" });
    }
    
    public void execute(final String[] array) {
        if (array.length == 5) {
            LuigiHack.baritoneManager.setPrefix(array[0]);
            int int1;
            int int2;
            int int3;
            try {
                int1 = Integer.parseInt(array[1]);
                int2 = Integer.parseInt(array[2]);
                int3 = Integer.parseInt(array[3]);
            }
            catch (NumberFormatException ex) {
                sendMessage("Invalid Input for x, y or z!");
                LuigiHack.baritoneManager.stop();
                return;
            }
            LuigiHack.baritoneManager.start(int1, int2, int3);
            return;
        }
        sendMessage("Stoping Baritone-Nostop.");
        LuigiHack.baritoneManager.stop();
    }
}
