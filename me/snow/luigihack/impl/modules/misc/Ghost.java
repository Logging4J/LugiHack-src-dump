//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.misc;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.event.events.*;
import me.zero.alpine.fork.listener.*;
import net.minecraft.client.gui.*;
import net.minecraft.network.play.client.*;
import java.util.function.*;

public class Ghost extends Module
{
    @EventHandler
    private final /* synthetic */ Listener<PacketEvent.Send> netWorkManagerListener;
    /* synthetic */ boolean bypassdeath;
    
    @Override
    public void onDisable() {
        if (Ghost.mc.player != null) {
            Ghost.mc.player.respawnPlayer();
        }
        this.bypassdeath = false;
    }
    
    @Override
    public void onUpdate() {
        if (Ghost.mc.world == null) {
            return;
        }
        if (Ghost.mc.player.getHealth() == 0.0f) {
            Ghost.mc.player.setHealth(20.0f);
            Ghost.mc.player.isDead = false;
            this.bypassdeath = true;
            Ghost.mc.displayGuiScreen((GuiScreen)null);
            Ghost.mc.player.setPositionAndUpdate(Ghost.mc.player.posX, Ghost.mc.player.posY, Ghost.mc.player.posZ);
        }
    }
    
    public Ghost() {
        super("Ghost", "Stay alive after dying", Category.MISC, true, false, false);
        this.netWorkManagerListener = new Listener<PacketEvent.Send>(send -> {
            if (this.bypassdeath && send.getPacket() instanceof CPacketPlayer) {
                send.isCanceled();
            }
        }, (Predicate<PacketEvent.Send>[])new Predicate[0]);
    }
}
