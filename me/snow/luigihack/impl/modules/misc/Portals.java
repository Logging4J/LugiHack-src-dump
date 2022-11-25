//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.misc;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraft.network.play.client.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Portals extends Module
{
    public /* synthetic */ Setting<Boolean> portalChat;
    private static /* synthetic */ Portals INSTANCE;
    public /* synthetic */ Setting<Boolean> godmode;
    public /* synthetic */ Setting<Integer> time;
    public /* synthetic */ Setting<Boolean> fastPortal;
    public /* synthetic */ Setting<Integer> cooldown;
    
    @Override
    public String getDisplayInfo() {
        if (this.godmode.getValue()) {
            return "Godmode";
        }
        return null;
    }
    
    public static Portals getInstance() {
        if (Portals.INSTANCE == null) {
            Portals.INSTANCE = new Portals();
        }
        return Portals.INSTANCE;
    }
    
    public Portals() {
        super("Portals", "Tweaks for Portals.", Category.MISC, true, false, false);
        this.portalChat = (Setting<Boolean>)this.register(new Setting("Chat", (Object)true, "Allows you to chat in portals."));
        this.godmode = (Setting<Boolean>)this.register(new Setting("Godmode", (Object)false, "Portal Godmode."));
        this.fastPortal = (Setting<Boolean>)this.register(new Setting("FastPortal", (Object)false));
        this.cooldown = (Setting<Integer>)this.register(new Setting("Cooldown", (Object)5, (Object)1, (Object)10, p0 -> (boolean)this.fastPortal.getValue(), "Portal cooldown."));
        this.time = (Setting<Integer>)this.register(new Setting("Time", (Object)5, (Object)0, (Object)80, p0 -> (boolean)this.fastPortal.getValue(), "Time in Portal"));
        this.setInstance();
    }
    
    private void setInstance() {
        Portals.INSTANCE = this;
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        if (send.getStage() == 0 && (boolean)this.godmode.getValue() && send.getPacket() instanceof CPacketConfirmTeleport) {
            send.setCanceled(true);
        }
    }
    
    static {
        Portals.INSTANCE = new Portals();
    }
}
