//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.misc;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import java.util.*;
import net.minecraft.network.*;
import java.util.concurrent.*;
import me.snow.luigihack.api.util.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraft.network.play.client.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class PingSpoof extends Module
{
    private /* synthetic */ Setting<Integer> secondDelay;
    private /* synthetic */ Setting<Boolean> offOnLogout;
    private /* synthetic */ Timer timer;
    private /* synthetic */ Queue<Packet<?>> packets;
    private /* synthetic */ boolean receive;
    private /* synthetic */ Setting<Integer> delay;
    private /* synthetic */ Setting<Boolean> seconds;
    
    @Override
    public void onLoad() {
        if (this.offOnLogout.getValue()) {
            this.disable();
        }
    }
    
    @Override
    public void onLogout() {
        if (this.offOnLogout.getValue()) {
            this.disable();
        }
    }
    
    public PingSpoof() {
        super("PingSpoof", "Spoofs your ping!", Category.MISC, true, false, false);
        this.seconds = (Setting<Boolean>)this.register(new Setting("Seconds", (Object)false));
        this.delay = (Setting<Integer>)this.register(new Setting("DelayMS", (Object)20, (Object)0, (Object)1000, p0 -> !(boolean)this.seconds.getValue()));
        this.secondDelay = (Setting<Integer>)this.register(new Setting("DelayS", (Object)5, (Object)0, (Object)30, p0 -> (boolean)this.seconds.getValue()));
        this.offOnLogout = (Setting<Boolean>)this.register(new Setting("Logout", (Object)false));
        this.packets = new ConcurrentLinkedQueue<Packet<?>>();
        this.timer = new Timer();
        this.receive = true;
    }
    
    public void clearQueue() {
        if (PingSpoof.mc.player != null && !PingSpoof.mc.isSingleplayer() && PingSpoof.mc.player.isEntityAlive() && ((!(boolean)this.seconds.getValue() && this.timer.passedMs((long)(int)this.delay.getValue())) || ((boolean)this.seconds.getValue() && this.timer.passedS((double)(int)this.secondDelay.getValue())))) {
            final double incremental = MathUtil.getIncremental(Math.random() * 10.0, 1.0);
            this.receive = false;
            for (int n = 0; n < incremental; ++n) {
                final Packet<?> packet = this.packets.poll();
                if (packet != null) {
                    PingSpoof.mc.player.connection.sendPacket((Packet)packet);
                }
            }
            this.timer.reset();
            this.receive = true;
        }
    }
    
    @Override
    public void onDisable() {
        this.clearQueue();
    }
    
    @Override
    public void onUpdate() {
        this.clearQueue();
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        if (this.receive && PingSpoof.mc.player != null && !PingSpoof.mc.isSingleplayer() && PingSpoof.mc.player.isEntityAlive() && send.getStage() == 0 && send.getPacket() instanceof CPacketKeepAlive) {
            this.packets.add((Packet<?>)send.getPacket());
            send.setCanceled(true);
        }
    }
}
