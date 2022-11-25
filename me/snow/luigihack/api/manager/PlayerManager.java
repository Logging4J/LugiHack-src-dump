//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.manager;

import net.minecraft.client.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraft.network.play.client.*;

public class PlayerManager
{
    private /* synthetic */ int slot;
    private static final /* synthetic */ Minecraft mc;
    private /* synthetic */ boolean switching;
    private /* synthetic */ boolean shifting;
    
    public void setSwitching(final boolean switching) {
        this.switching = switching;
    }
    
    public int getSlot() {
        return this.slot;
    }
    
    static {
        mc = Minecraft.getMinecraft();
    }
    
    public boolean isShifting() {
        return this.shifting;
    }
    
    public void onPacketSend(final PacketEvent.Send send) {
        if (send.getPacket() instanceof CPacketEntityAction) {
            final CPacketEntityAction cPacketEntityAction = (CPacketEntityAction)send.getPacket();
            if (cPacketEntityAction.getAction() == CPacketEntityAction.Action.START_SNEAKING) {
                this.shifting = true;
            }
            else if (cPacketEntityAction.getAction() == CPacketEntityAction.Action.STOP_SNEAKING) {
                this.shifting = false;
            }
        }
        if (send.getPacket() instanceof CPacketHeldItemChange) {
            this.slot = ((CPacketHeldItemChange)send.getPacket()).getSlotId();
            PlayerManager.mc.player.inventory.currentItem = this.slot;
        }
    }
}
