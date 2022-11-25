//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.misc;

import me.snow.luigihack.impl.modules.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.math.*;
import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.api.util.*;
import com.mojang.realmsclient.gui.*;
import me.snow.luigihack.impl.command.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.network.play.client.*;
import net.minecraft.init.*;
import me.snow.luigihack.impl.modules.combat.*;
import me.snow.luigihack.impl.modules.combat.autocrystal.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraft.network.play.server.*;
import net.minecraft.entity.*;
import java.util.*;
import net.minecraft.entity.item.*;

public class Tracker extends Module
{
    private /* synthetic */ boolean shouldEnable;
    private /* synthetic */ EntityPlayer trackedPlayer;
    private final /* synthetic */ Set<BlockPos> manuallyPlaced;
    public /* synthetic */ Setting<Boolean> autoEnable;
    private final /* synthetic */ Timer timer;
    private /* synthetic */ int usedExp;
    public /* synthetic */ Setting<Boolean> autoDisable;
    private static /* synthetic */ Tracker instance;
    private /* synthetic */ int usedCrystals;
    private /* synthetic */ int usedStacks;
    private /* synthetic */ int usedCStacks;
    
    public Tracker() {
        super("Tracker", "Tracks players in 1v1s. Only good in duels tho!", Category.MISC, true, false, true);
        this.timer = new Timer();
        this.manuallyPlaced = new HashSet<BlockPos>();
        this.autoEnable = (Setting<Boolean>)this.register(new Setting("AutoEnable", (Object)false));
        this.autoDisable = (Setting<Boolean>)this.register(new Setting("AutoDisable", (Object)true));
        Tracker.instance = this;
    }
    
    @Override
    public void onUpdate() {
        if (this.isOff()) {
            return;
        }
        if (this.trackedPlayer == null) {
            this.trackedPlayer = EntityUtil.getClosestEnemy(1000.0);
        }
        else {
            if (this.usedStacks != this.usedExp / 64) {
                this.usedStacks = this.usedExp / 64;
                Command.sendMessage(String.valueOf(new StringBuilder().append(ChatFormatting.WHITE).append("<Tracker> ").append(ChatFormatting.DARK_PURPLE).append(this.trackedPlayer.getName()).append(ChatFormatting.LIGHT_PURPLE).append(" used ").append(ChatFormatting.WHITE).append(this.usedStacks).append(ChatFormatting.LIGHT_PURPLE).append(" Stacks of EXP!")));
            }
            if (this.usedCStacks != this.usedCrystals / 64) {
                this.usedCStacks = this.usedCrystals / 64;
                Command.sendMessage(String.valueOf(new StringBuilder().append(ChatFormatting.WHITE).append("<Tracker> ").append(ChatFormatting.DARK_PURPLE).append(this.trackedPlayer.getName()).append(ChatFormatting.LIGHT_PURPLE).append(" used: ").append(ChatFormatting.WHITE).append(this.usedCStacks).append(ChatFormatting.LIGHT_PURPLE).append(" Stacks of Crystals!")));
            }
        }
    }
    
    @SubscribeEvent
    public void onConnection(final ConnectionEvent connectionEvent) {
        if (this.isOff() || connectionEvent.getStage() != 1) {
            return;
        }
        final String name = connectionEvent.getName();
        if (this.trackedPlayer != null && name != null && name.equals(this.trackedPlayer.getName()) && (boolean)this.autoDisable.getValue()) {
            Command.sendMessage(String.valueOf(new StringBuilder().append(name).append(" logged, Tracker disabling.")));
            this.disable();
        }
    }
    
    @SubscribeEvent
    public void onDeath(final DeathEvent deathEvent) {
        if (this.isOn() && (deathEvent.player.equals((Object)this.trackedPlayer) || deathEvent.player.equals((Object)Tracker.mc.player))) {
            this.usedExp = 0;
            this.usedStacks = 0;
            this.usedCrystals = 0;
            this.usedCStacks = 0;
            if (this.autoDisable.getValue()) {
                this.disable();
            }
        }
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        if (!fullNullCheck() && this.isOn() && send.getPacket() instanceof CPacketPlayerTryUseItemOnBlock) {
            final CPacketPlayerTryUseItemOnBlock cPacketPlayerTryUseItemOnBlock = (CPacketPlayerTryUseItemOnBlock)send.getPacket();
            if (Tracker.mc.player.getHeldItem(cPacketPlayerTryUseItemOnBlock.hand).getItem() == Items.END_CRYSTAL && !AntiTrap.placedPos.contains(cPacketPlayerTryUseItemOnBlock.position) && !AutoCrystal.placedPos.contains(cPacketPlayerTryUseItemOnBlock.position)) {
                this.manuallyPlaced.add(cPacketPlayerTryUseItemOnBlock.position);
            }
        }
    }
    
    @SubscribeEvent
    public void onUpdateWalkingPlayer(final UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (this.shouldEnable && this.timer.passedS(5.0) && this.isOff()) {
            this.enable();
        }
    }
    
    @SubscribeEvent
    public void onPacketReceive(final PacketEvent.Receive receive) {
        if (!fullNullCheck() && ((boolean)this.autoEnable.getValue() || (boolean)this.autoDisable.getValue()) && receive.getPacket() instanceof SPacketChat) {
            final String getFormattedText = ((SPacketChat)receive.getPacket()).getChatComponent().getFormattedText();
            if ((boolean)this.autoEnable.getValue() && (getFormattedText.contains("has accepted your duel request") || getFormattedText.contains("Accepted the duel request from")) && !getFormattedText.contains("<")) {
                Command.sendMessage("Tracker will enable in 5 seconds.");
                this.timer.reset();
                this.shouldEnable = true;
            }
            else if ((boolean)this.autoDisable.getValue() && getFormattedText.contains("has defeated") && getFormattedText.contains(Tracker.mc.player.getName()) && !getFormattedText.contains("<")) {
                this.disable();
            }
        }
    }
    
    @Override
    public void onLogout() {
        if (this.autoDisable.getValue()) {
            this.disable();
        }
    }
    
    public static Tracker getInstance() {
        if (Tracker.instance == null) {
            Tracker.instance = new Tracker();
        }
        return Tracker.instance;
    }
    
    public void onSpawnEntity(final Entity entity) {
        if (this.isOff()) {
            return;
        }
        if (entity instanceof EntityExpBottle && Objects.equals(Tracker.mc.world.getClosestPlayerToEntity(entity, 3.0), this.trackedPlayer)) {
            ++this.usedExp;
        }
        if (entity instanceof EntityEnderCrystal) {
            if (AntiTrap.placedPos.contains(entity.getPosition().down())) {
                AntiTrap.placedPos.remove(entity.getPosition().down());
            }
            else if (this.manuallyPlaced.contains(entity.getPosition().down())) {
                this.manuallyPlaced.remove(entity.getPosition().down());
            }
            else if (!AutoCrystal.placedPos.contains(entity.getPosition().down())) {
                ++this.usedCrystals;
            }
        }
    }
    
    @Override
    public void onToggle() {
        this.manuallyPlaced.clear();
        AntiTrap.placedPos.clear();
        this.shouldEnable = false;
        this.trackedPlayer = null;
        this.usedExp = 0;
        this.usedStacks = 0;
        this.usedCrystals = 0;
        this.usedCStacks = 0;
    }
    
    @Override
    public String getDisplayInfo() {
        if (this.trackedPlayer != null) {
            return this.trackedPlayer.getName();
        }
        return null;
    }
}
