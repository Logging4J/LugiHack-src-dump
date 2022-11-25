//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.command.commands;

import me.snow.luigihack.impl.command.*;
import net.minecraft.client.audio.*;
import net.minecraftforge.fml.common.*;

public class ReloadSoundCommand extends Command
{
    public ReloadSoundCommand() {
        super("reloadsound", new String[0]);
    }
    
    public void execute(final String[] array) {
        try {
            ((SoundManager)ObfuscationReflectionHelper.getPrivateValue((Class)SoundHandler.class, (Object)ReloadSoundCommand.mc.getSoundHandler(), new String[] { "sndManager", "sndManager" })).reloadSoundSystem();
            sendMessage("§aReloaded Sound System.");
        }
        catch (Exception obj) {
            System.out.println(String.valueOf(new StringBuilder().append("Could not restart sound manager: ").append(obj)));
            obj.printStackTrace();
            sendMessage("§cCouldn't Reload Sound System!");
        }
    }
}
