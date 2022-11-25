//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.player;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraft.network.play.client.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.snow.luigihack.mixin.mixins.accessors.*;
import net.minecraft.entity.*;
import net.minecraft.network.*;

public class AntiHunger extends Module
{
    public /* synthetic */ Setting<Boolean> stopSprint;
    private /* synthetic */ boolean previousSprint;
    public /* synthetic */ Setting<Boolean> groundSpoof;
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        if (send.getPacket() instanceof CPacketPlayer) {
            if ((boolean)this.groundSpoof.getValue() && !AntiHunger.mc.player.isRiding() && !AntiHunger.mc.player.isElytraFlying()) {
                ((ICPacketPlayer)send.getPacket()).setOnGround(true);
            }
        }
        else if (send.getPacket() instanceof CPacketEntityAction) {
            final CPacketEntityAction cPacketEntityAction = (CPacketEntityAction)send.getPacket();
            if ((cPacketEntityAction.getAction().equals((Object)CPacketEntityAction.Action.START_SPRINTING) || cPacketEntityAction.getAction().equals((Object)CPacketEntityAction.Action.STOP_SPRINTING)) && (boolean)this.stopSprint.getValue()) {
                send.setCanceled(true);
            }
        }
    }
    
    public AntiHunger() {
        super("AntiHunger", "Prevents you from getting Hungry.", Module.Category.PLAYER, true, false, false);
        this.stopSprint = (Setting<Boolean>)this.register(new Setting("CancelSprint", (Object)true));
        this.groundSpoof = (Setting<Boolean>)this.register(new Setting("Ground", (Object)true));
    }
    
    public void onEnable() {
        if (AntiHunger.mc.player.isSprinting() || ((IEntityPlayerSP)AntiHunger.mc.player).getServerSprintState()) {
            this.previousSprint = true;
            AntiHunger.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)AntiHunger.mc.player, CPacketEntityAction.Action.STOP_SPRINTING));
        }
        super.onEnable();
    }
    
    public void onDisable() {
        super.onDisable();
        if (this.previousSprint) {
            this.previousSprint = false;
            AntiHunger.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)AntiHunger.mc.player, CPacketEntityAction.Action.START_SPRINTING));
        }
    }
}
