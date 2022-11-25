//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.misc;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.api.util.*;
import me.snow.luigihack.impl.command.*;
import net.minecraft.potion.*;
import java.util.concurrent.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraft.network.play.server.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class AntiEffect extends Module
{
    public /* synthetic */ Setting<Boolean> antivan;
    public /* synthetic */ Setting<Boolean> antilev;
    private final /* synthetic */ Queue<UUID> toLookUp;
    
    @Override
    public void onUpdate() {
        final UUID uuid;
        if (PlayerUtil.timer.passedS(5.0) && (uuid = this.toLookUp.poll()) != null && (boolean)this.antivan.getValue()) {
            try {
                final String nameFromUUID = PlayerUtil.getNameFromUUID(uuid);
                if (nameFromUUID != null) {
                    Command.sendMessage(String.valueOf(new StringBuilder().append("§c").append(nameFromUUID).append(" has gone into vanish.")));
                }
            }
            catch (Exception ex) {}
            PlayerUtil.timer.reset();
        }
        if ((boolean)this.antilev.getValue() && AntiEffect.mc.player.isPotionActive((Potion)Objects.requireNonNull(Potion.getPotionFromResourceLocation("levitation")))) {
            AntiEffect.mc.player.removeActivePotionEffect(Potion.getPotionFromResourceLocation("levitation"));
        }
    }
    
    public AntiEffect() {
        super("AntiEffect", "Notifies you when players vanish", Category.MISC, true, false, false);
        this.toLookUp = new ConcurrentLinkedQueue<UUID>();
        this.antilev = (Setting<Boolean>)this.register(new Setting("AntiLevitate", (Object)false));
        this.antivan = (Setting<Boolean>)this.register(new Setting("AntiVanish", (Object)false));
    }
    
    @Override
    public void onLogout() {
        this.toLookUp.clear();
    }
    
    @SubscribeEvent
    public void onPacketReceive(final PacketEvent.Receive receive) {
        final SPacketPlayerListItem sPacketPlayerListItem;
        if (receive.getPacket() instanceof SPacketPlayerListItem && (sPacketPlayerListItem = (SPacketPlayerListItem)receive.getPacket()).getAction() == SPacketPlayerListItem.Action.UPDATE_LATENCY && (boolean)this.antivan.getValue()) {
            for (final SPacketPlayerListItem.AddPlayerData addPlayerData : sPacketPlayerListItem.getEntries()) {
                try {
                    if (AntiEffect.mc.getConnection().getPlayerInfo(addPlayerData.getProfile().getId()) != null) {
                        continue;
                    }
                    this.toLookUp.add(addPlayerData.getProfile().getId());
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
