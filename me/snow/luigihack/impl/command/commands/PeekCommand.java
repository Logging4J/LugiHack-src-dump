//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.command.commands;

import me.snow.luigihack.impl.command.*;
import me.snow.luigihack.impl.modules.misc.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import java.util.*;

public class PeekCommand extends Command
{
    public PeekCommand() {
        super("peek", new String[] { "<player>" });
    }
    
    public void execute(final String[] array) {
        if (array.length == 1) {
            final ItemStack getHeldItemMainhand = PeekCommand.mc.player.getHeldItemMainhand();
            if (!(getHeldItemMainhand.getItem() instanceof ItemShulkerBox)) {
                Command.sendMessage("§cYou need to hold a Shulker in your mainhand.");
                return;
            }
            ToolTips.displayInv(getHeldItemMainhand, null);
        }
        if (array.length > 1) {
            if (ToolTips.getInstance().isOn() && (boolean)ToolTips.getInstance().shulkerSpy.getValue()) {
                for (final Map.Entry<EntityPlayer, ItemStack> entry : ToolTips.getInstance().spiedPlayers.entrySet()) {
                    if (!entry.getKey().getName().equalsIgnoreCase(array[0])) {
                        continue;
                    }
                    ToolTips.displayInv(entry.getValue(), entry.getKey().getName());
                    break;
                }
            }
            else {
                Command.sendMessage("§cYou need to turn on Tooltips - ShulkerSpy");
            }
        }
    }
}
