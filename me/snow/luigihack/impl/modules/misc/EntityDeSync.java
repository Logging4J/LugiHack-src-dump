//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.misc;

import me.snow.luigihack.impl.modules.*;
import net.minecraft.entity.*;
import me.snow.luigihack.impl.command.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraft.network.play.server.*;
import net.minecraftforge.event.entity.*;

public class EntityDeSync extends Module
{
    private /* synthetic */ Entity Riding;
    static /* synthetic */ EntityDeSync INSTANCE;
    
    @Override
    public void onEnable() {
        if (EntityDeSync.mc.player == null) {
            this.Riding = null;
            this.toggle();
            return;
        }
        if (!EntityDeSync.mc.player.isRiding()) {
            Command.sendMessage("You are not riding an entity.");
            this.Riding = null;
            this.toggle();
            return;
        }
        Command.sendMessage("Vanished");
        this.Riding = EntityDeSync.mc.player.getRidingEntity();
        EntityDeSync.mc.player.dismountRidingEntity();
        EntityDeSync.mc.world.removeEntity(this.Riding);
    }
    
    @SubscribeEvent
    public void OnPlayerUpdate(final EventPlayerUpdate eventPlayerUpdate) {
        if (this.Riding == null) {
            return;
        }
        if (EntityDeSync.mc.player.isRiding()) {
            return;
        }
        EntityDeSync.mc.player.onGround = true;
        this.Riding.setPosition(EntityDeSync.mc.player.posX, EntityDeSync.mc.player.posY, EntityDeSync.mc.player.posZ);
        EntityDeSync.mc.player.connection.sendPacket((Packet)new CPacketVehicleMove(this.Riding));
    }
    
    @SubscribeEvent
    public void onPacket(final PacketEvent.Receive receive) {
        if (receive.getPacket() instanceof SPacketSetPassengers) {
            if (this.Riding == null) {
                return;
            }
            final SPacketSetPassengers sPacketSetPassengers = (SPacketSetPassengers)receive.getPacket();
            if (EntityDeSync.mc.world.getEntityByID(sPacketSetPassengers.getEntityId()) == this.Riding) {
                final int[] getPassengerIds = sPacketSetPassengers.getPassengerIds();
                for (int length = getPassengerIds.length, i = 0; i < length; ++i) {
                    if (EntityDeSync.mc.world.getEntityByID(getPassengerIds[i]) == EntityDeSync.mc.player) {
                        return;
                    }
                }
                Command.sendMessage("You dismounted");
                this.toggle();
            }
        }
        else if (receive.getPacket() instanceof SPacketDestroyEntities) {
            final int[] getEntityIDs = ((SPacketDestroyEntities)receive.getPacket()).getEntityIDs();
            for (int length2 = getEntityIDs.length, j = 0; j < length2; ++j) {
                if (getEntityIDs[j] == this.Riding.getEntityId()) {
                    Command.sendMessage("Entity is now null!");
                    return;
                }
            }
        }
    }
    
    @Override
    public void onDisable() {
        if (this.Riding != null) {
            this.Riding.isDead = false;
            if (!EntityDeSync.mc.player.isRiding()) {
                EntityDeSync.mc.world.spawnEntity(this.Riding);
                EntityDeSync.mc.player.startRiding(this.Riding, true);
            }
            this.Riding = null;
            Command.sendMessage("Remounted.");
        }
    }
    
    public void onInit() {
        EntityDeSync.INSTANCE = this;
    }
    
    public EntityDeSync() {
        super("EntityDeSync", "Forces shit with entites", Category.MISC, true, false, false);
        this.Riding = null;
    }
    
    @SubscribeEvent
    public void OnWorldEvent(final EntityJoinWorldEvent entityJoinWorldEvent) {
        if (entityJoinWorldEvent.getEntity() == EntityDeSync.mc.player) {
            Command.sendMessage(String.valueOf(new StringBuilder().append("Player ").append(entityJoinWorldEvent.getEntity().getName()).append(" joined the world!")));
        }
    }
}
