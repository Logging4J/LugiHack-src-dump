//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.player;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.client.entity.*;
import net.minecraft.util.math.*;
import java.util.*;
import net.minecraft.network.*;
import net.minecraft.entity.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraft.network.play.client.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.snow.luigihack.api.util.*;
import java.util.concurrent.*;
import net.minecraft.world.*;

public class Blink extends Module
{
    private /* synthetic */ int packetsCanceled;
    public /* synthetic */ Setting<Boolean> cPacketPlayer;
    public /* synthetic */ Setting<Float> distance;
    private final /* synthetic */ Timer timer;
    public /* synthetic */ Setting<Integer> packetLimit;
    private /* synthetic */ EntityOtherPlayerMP entity;
    private /* synthetic */ BlockPos startPos;
    private final /* synthetic */ Queue<Packet<?>> packets;
    private static /* synthetic */ Blink INSTANCE;
    public /* synthetic */ Setting<Mode> autoOff;
    public /* synthetic */ Setting<Integer> timeLimit;
    
    static {
        Blink.INSTANCE = new Blink();
    }
    
    private void setInstance() {
        Blink.INSTANCE = this;
    }
    
    public void onDisable() {
        if (!fullNullCheck()) {
            Blink.mc.world.removeEntity((Entity)this.entity);
            while (!this.packets.isEmpty()) {
                Blink.mc.player.connection.sendPacket((Packet)this.packets.poll());
            }
        }
        this.startPos = null;
    }
    
    public void onLogout() {
        if (this.isOn()) {
            this.disable();
        }
    }
    
    @SubscribeEvent
    public void onSendPacket(final PacketEvent.Send send) {
        if (send.getStage() == 0 && Blink.mc.world != null && !Blink.mc.isSingleplayer()) {
            final Packet packet = send.getPacket();
            if ((boolean)this.cPacketPlayer.getValue() && packet instanceof CPacketPlayer) {
                send.setCanceled(true);
                this.packets.add((Packet<?>)packet);
                ++this.packetsCanceled;
            }
            if (!(boolean)this.cPacketPlayer.getValue()) {
                if (packet instanceof CPacketChatMessage || packet instanceof CPacketConfirmTeleport || packet instanceof CPacketKeepAlive || packet instanceof CPacketTabComplete || packet instanceof CPacketClientStatus) {
                    return;
                }
                this.packets.add((Packet<?>)packet);
                send.setCanceled(true);
                ++this.packetsCanceled;
            }
        }
    }
    
    public void onUpdate() {
        if (nullCheck() || (this.autoOff.getValue() == Mode.TIME && this.timer.passedS((double)(int)this.timeLimit.getValue())) || (this.autoOff.getValue() == Mode.DISTANCE && this.startPos != null && Blink.mc.player.getDistanceSq(this.startPos) >= MathUtil.square((float)this.distance.getValue())) || (this.autoOff.getValue() == Mode.PACKETS && this.packetsCanceled >= (int)this.packetLimit.getValue())) {
            this.disable();
        }
    }
    
    public static Blink getInstance() {
        if (Blink.INSTANCE == null) {
            Blink.INSTANCE = new Blink();
        }
        return Blink.INSTANCE;
    }
    
    public Blink() {
        super("FakeLag", "Fakelag.", Module.Category.PLAYER, true, false, false);
        this.timer = new Timer();
        this.packets = new ConcurrentLinkedQueue<Packet<?>>();
        this.cPacketPlayer = (Setting<Boolean>)this.register(new Setting("CPacketPlayer", (Object)true));
        this.autoOff = (Setting<Mode>)this.register(new Setting("AutoOff", (Object)Mode.MANUAL));
        this.timeLimit = (Setting<Integer>)this.register(new Setting("Time", (Object)20, (Object)1, (Object)500, p0 -> this.autoOff.getValue() == Mode.TIME));
        this.packetLimit = (Setting<Integer>)this.register(new Setting("Packets", (Object)20, (Object)1, (Object)500, p0 -> this.autoOff.getValue() == Mode.PACKETS));
        this.distance = (Setting<Float>)this.register(new Setting("Distance", (Object)10.0f, (Object)1.0f, (Object)100.0f, p0 -> this.autoOff.getValue() == Mode.DISTANCE));
        this.setInstance();
    }
    
    public void onEnable() {
        if (!fullNullCheck()) {
            this.entity = new EntityOtherPlayerMP((World)Blink.mc.world, Blink.mc.session.getProfile());
            this.entity.copyLocationAndAnglesFrom((Entity)Blink.mc.player);
            this.entity.rotationYaw = Blink.mc.player.rotationYaw;
            this.entity.rotationYawHead = Blink.mc.player.rotationYawHead;
            this.entity.inventory.copyInventory(Blink.mc.player.inventory);
            Blink.mc.world.addEntityToWorld(6942069, (Entity)this.entity);
            this.startPos = Blink.mc.player.getPosition();
        }
        else {
            this.disable();
        }
        this.packetsCanceled = 0;
        this.timer.reset();
    }
    
    public enum Mode
    {
        DISTANCE, 
        TIME, 
        MANUAL, 
        PACKETS;
    }
}
