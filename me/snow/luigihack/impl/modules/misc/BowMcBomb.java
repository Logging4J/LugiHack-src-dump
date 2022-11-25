//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.misc;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.entity.*;
import net.minecraft.network.*;
import me.snow.luigihack.impl.command.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.item.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class BowMcBomb extends Module
{
    public /* synthetic */ Setting<Integer> Timeout;
    public /* synthetic */ Setting<Integer> spoofs;
    private /* synthetic */ boolean shooting;
    public /* synthetic */ Setting<Boolean> debug;
    public /* synthetic */ Setting<Boolean> Bows;
    private /* synthetic */ long lastShootTime;
    public /* synthetic */ Setting<Boolean> pearls;
    public /* synthetic */ Setting<Boolean> snowballs;
    public /* synthetic */ Setting<Boolean> eggs;
    public /* synthetic */ Setting<Boolean> bypass;
    
    private void doSpoofs() {
        if (System.currentTimeMillis() - this.lastShootTime >= (int)this.Timeout.getValue()) {
            this.shooting = true;
            this.lastShootTime = System.currentTimeMillis();
            BowMcBomb.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BowMcBomb.mc.player, CPacketEntityAction.Action.START_SPRINTING));
            for (int i = 0; i < (int)this.spoofs.getValue(); ++i) {
                if (this.bypass.getValue()) {
                    BowMcBomb.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(BowMcBomb.mc.player.posX, BowMcBomb.mc.player.posY + 1.0E-10, BowMcBomb.mc.player.posZ, false));
                    BowMcBomb.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(BowMcBomb.mc.player.posX, BowMcBomb.mc.player.posY - 1.0E-10, BowMcBomb.mc.player.posZ, true));
                }
                else {
                    BowMcBomb.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(BowMcBomb.mc.player.posX, BowMcBomb.mc.player.posY - 1.0E-10, BowMcBomb.mc.player.posZ, true));
                    BowMcBomb.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(BowMcBomb.mc.player.posX, BowMcBomb.mc.player.posY + 1.0E-10, BowMcBomb.mc.player.posZ, false));
                }
            }
            if (this.debug.getValue()) {
                Command.sendMessage("Spoofed");
            }
            this.shooting = false;
        }
    }
    
    public BowMcBomb() {
        super("BowExploit", "Uno hitter w bows", Category.MISC, true, false, false);
        this.Bows = (Setting<Boolean>)this.register(new Setting("Bows", (Object)true));
        this.pearls = (Setting<Boolean>)this.register(new Setting("Pearls", (Object)false));
        this.eggs = (Setting<Boolean>)this.register(new Setting("Eggs", (Object)false));
        this.snowballs = (Setting<Boolean>)this.register(new Setting("SnowBallz", (Object)false));
        this.Timeout = (Setting<Integer>)this.register(new Setting("Timeout", (Object)5000, (Object)100, (Object)20000));
        this.spoofs = (Setting<Integer>)this.register(new Setting("Spoofs", (Object)10, (Object)1, (Object)300));
        this.bypass = (Setting<Boolean>)this.register(new Setting("Bypass", (Object)true));
        this.debug = (Setting<Boolean>)this.register(new Setting("Debug", (Object)false));
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        if (send.getStage() != 0) {
            return;
        }
        if (send.getPacket() instanceof CPacketPlayerDigging) {
            if (((CPacketPlayerDigging)send.getPacket()).getAction() == CPacketPlayerDigging.Action.RELEASE_USE_ITEM) {
                final ItemStack getHeldItem = BowMcBomb.mc.player.getHeldItem(EnumHand.MAIN_HAND);
                if (!getHeldItem.isEmpty() && getHeldItem.getItem() != null && getHeldItem.getItem() instanceof ItemBow && (boolean)this.Bows.getValue()) {
                    this.doSpoofs();
                    if (this.debug.getValue()) {
                        Command.sendMessage("trying to spoof");
                    }
                }
            }
        }
        else if (send.getPacket() instanceof CPacketPlayerTryUseItem && ((CPacketPlayerTryUseItem)send.getPacket()).getHand() == EnumHand.MAIN_HAND) {
            final ItemStack getHeldItem2 = BowMcBomb.mc.player.getHeldItem(EnumHand.MAIN_HAND);
            if (!getHeldItem2.isEmpty() && getHeldItem2.getItem() != null) {
                if (getHeldItem2.getItem() instanceof ItemEgg && (boolean)this.eggs.getValue()) {
                    this.doSpoofs();
                }
                else if (getHeldItem2.getItem() instanceof ItemEnderPearl && (boolean)this.pearls.getValue()) {
                    this.doSpoofs();
                }
                else if (getHeldItem2.getItem() instanceof ItemSnowball && (boolean)this.snowballs.getValue()) {
                    this.doSpoofs();
                }
            }
        }
    }
    
    @Override
    public void onEnable() {
        if (this.isEnabled()) {
            this.shooting = false;
            this.lastShootTime = System.currentTimeMillis();
        }
    }
}
