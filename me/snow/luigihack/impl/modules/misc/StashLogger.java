//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.misc;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import java.util.*;
import net.minecraft.nbt.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraft.network.play.server.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.client.*;
import java.io.*;
import net.minecraft.init.*;
import net.minecraft.client.audio.*;
import com.mojang.realmsclient.gui.*;
import me.snow.luigihack.impl.command.*;

public class StashLogger extends Module
{
    private final /* synthetic */ Setting<Boolean> Shulkers;
    final /* synthetic */ Iterator<NBTTagCompound> iterator;
    private final /* synthetic */ Setting<Integer> shulkersValue;
    private final /* synthetic */ Setting<Integer> chestsValue;
    /* synthetic */ File mainFolder;
    private final /* synthetic */ Setting<Boolean> writeToFile;
    private final /* synthetic */ Setting<Boolean> chests;
    
    @SubscribeEvent
    public void onPacket(final PacketEvent packetEvent) {
        if (nullCheck()) {
            return;
        }
        if (packetEvent.getPacket() instanceof SPacketChunkData) {
            final SPacketChunkData sPacketChunkData = (SPacketChunkData)packetEvent.getPacket();
            int i = 0;
            int j = 0;
            final Iterator iterator = sPacketChunkData.getTileEntityTags().iterator();
            while (iterator.hasNext()) {
                final String getString = iterator.next().getString("id");
                if (getString.equals("minecraft:chest") && (boolean)this.chests.getValue()) {
                    ++i;
                }
                else {
                    if (!getString.equals("minecraft:shulker_box")) {
                        continue;
                    }
                    if (!(boolean)this.Shulkers.getValue()) {
                        continue;
                    }
                    ++j;
                }
            }
            if (i >= (int)this.chestsValue.getValue()) {
                this.SendMessage(String.format("%s chests located at X: %s, Z: %s", i, sPacketChunkData.getChunkX() * 16, sPacketChunkData.getChunkZ() * 16), true);
            }
            if (j >= (int)this.shulkersValue.getValue()) {
                this.SendMessage(String.format("%s shulker boxes at X: %s, Z: %s", j, sPacketChunkData.getChunkX() * 16, sPacketChunkData.getChunkZ() * 16), true);
            }
        }
    }
    
    public StashLogger() {
        super("StashLogger", "Logs stashes", Category.MISC, true, false, false);
        this.chests = (Setting<Boolean>)this.register(new Setting("Chests", (Object)true));
        this.chestsValue = (Setting<Integer>)this.register(new Setting("ChestsValue", (Object)4, (Object)1, (Object)30, p0 -> (boolean)this.chests.getValue()));
        this.Shulkers = (Setting<Boolean>)this.register(new Setting("Shulkers", (Object)true));
        this.shulkersValue = (Setting<Integer>)this.register(new Setting("ShulkersValue", (Object)4, (Object)1, (Object)30, p0 -> (boolean)this.Shulkers.getValue()));
        this.writeToFile = (Setting<Boolean>)this.register(new Setting("CoordsSaver", (Object)true));
        this.mainFolder = new File(String.valueOf(new StringBuilder().append(Minecraft.getMinecraft().gameDir).append(File.separator).append("luigihack")));
        this.iterator = null;
    }
    
    private void SendMessage(final String s, final boolean b) {
        final String str = Minecraft.getMinecraft().isSingleplayer() ? "singleplayer".toUpperCase() : StashLogger.mc.getCurrentServerData().serverIP;
        if ((boolean)this.writeToFile.getValue() && b) {
            try {
                final FileWriter fileWriter = new FileWriter(String.valueOf(new StringBuilder().append(this.mainFolder).append("/stashes.txt")), true);
                fileWriter.write(String.valueOf(new StringBuilder().append("[").append(str).append("]: ").append(s).append("\n")));
                fileWriter.close();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        StashLogger.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getRecord(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f));
        Command.sendMessage(String.valueOf(new StringBuilder().append(ChatFormatting.GREEN).append(s)));
    }
}
