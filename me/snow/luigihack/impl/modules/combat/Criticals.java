//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.combat;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.api.util.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraft.world.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.entity.*;
import java.util.*;
import net.minecraft.client.entity.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Criticals extends Module
{
    public final /* synthetic */ Setting<Mode> mode;
    private final /* synthetic */ Setting<Integer> desyncDelay;
    public /* synthetic */ Setting<Integer> delay32k;
    private final /* synthetic */ Setting<Integer> packets;
    public /* synthetic */ Setting<Boolean> cancelFirst;
    private /* synthetic */ boolean resetTimer;
    private /* synthetic */ boolean firstCanceled;
    private static /* synthetic */ Criticals INSTANCE;
    public /* synthetic */ Setting<Boolean> noDesync;
    private final /* synthetic */ Timer timer;
    private final /* synthetic */ Timer timer32k;
    
    @Override
    public String getDisplayInfo() {
        switch ((Mode)this.mode.getValue()) {
            case JUMP: {
                return "Jump";
            }
            case MINIJUMP: {
                return "MiniJump";
            }
            case PACKET: {
                return "Packet";
            }
            case NCPStrict: {
                return "NCPStrict";
            }
            default: {
                return null;
            }
        }
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        final CPacketUseEntity cPacketUseEntity;
        if (send.getPacket() instanceof CPacketUseEntity && (cPacketUseEntity = (CPacketUseEntity)send.getPacket()).getAction() == CPacketUseEntity.Action.ATTACK) {
            if (this.firstCanceled) {
                this.timer32k.reset();
                this.resetTimer = true;
                this.timer.setMs((long)((int)this.desyncDelay.getValue() + 1));
                this.firstCanceled = false;
                return;
            }
            if (this.resetTimer && !this.timer32k.passedMs((long)(int)this.delay32k.getValue())) {
                return;
            }
            if (this.resetTimer && this.timer32k.passedMs((long)(int)this.delay32k.getValue())) {
                this.resetTimer = false;
            }
            if (!this.timer.passedMs((long)(int)this.desyncDelay.getValue())) {
                return;
            }
            if (Criticals.mc.player.onGround && !Criticals.mc.gameSettings.keyBindJump.isKeyDown() && (cPacketUseEntity.getEntityFromWorld((World)Criticals.mc.world) instanceof EntityLivingBase || !(boolean)this.noDesync.getValue()) && !Criticals.mc.player.isInWater() && !Criticals.mc.player.isInLava()) {
                if (this.mode.getValue() == Mode.PACKET) {
                    switch ((int)this.packets.getValue()) {
                        case 1: {
                            Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY + 0.10000000149011612, Criticals.mc.player.posZ, false));
                            Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY, Criticals.mc.player.posZ, false));
                            break;
                        }
                        case 2: {
                            Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY + 0.0625101, Criticals.mc.player.posZ, false));
                            Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY, Criticals.mc.player.posZ, false));
                            Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY + 1.1E-5, Criticals.mc.player.posZ, false));
                            Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY, Criticals.mc.player.posZ, false));
                            break;
                        }
                        case 3: {
                            Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY + 0.0625101, Criticals.mc.player.posZ, false));
                            Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY, Criticals.mc.player.posZ, false));
                            Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY + 0.0125, Criticals.mc.player.posZ, false));
                            Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY, Criticals.mc.player.posZ, false));
                            break;
                        }
                        case 4: {
                            Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY + 0.05, Criticals.mc.player.posZ, false));
                            Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY, Criticals.mc.player.posZ, false));
                            Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY + 0.03, Criticals.mc.player.posZ, false));
                            Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY, Criticals.mc.player.posZ, false));
                            break;
                        }
                        case 5: {
                            Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY + 0.1625, Criticals.mc.player.posZ, false));
                            Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY, Criticals.mc.player.posZ, false));
                            Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY + 4.0E-6, Criticals.mc.player.posZ, false));
                            Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY, Criticals.mc.player.posZ, false));
                            Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY + 1.0E-6, Criticals.mc.player.posZ, false));
                            Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY, Criticals.mc.player.posZ, false));
                            Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer());
                            Criticals.mc.player.onCriticalHit((Entity)Objects.requireNonNull(cPacketUseEntity.getEntityFromWorld((World)Criticals.mc.world)));
                            break;
                        }
                    }
                }
                if (this.mode.getValue() == Mode.NCPStrict) {
                    Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY + 0.11, Criticals.mc.player.posZ, false));
                    Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY + 0.1100013579, Criticals.mc.player.posZ, false));
                    Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY + 1.3579E-6, Criticals.mc.player.posZ, false));
                    Criticals.mc.player.onCriticalHit((Entity)Objects.requireNonNull(cPacketUseEntity.getEntityFromWorld((World)Criticals.mc.world)));
                }
                if (this.mode.getValue() == Mode.JUMP) {
                    Criticals.mc.player.jump();
                }
                if (this.mode.getValue() == Mode.MINIJUMP) {
                    final EntityPlayerSP player = Criticals.mc.player;
                    player.motionY /= 2.0;
                }
                this.timer.reset();
            }
        }
    }
    
    public Criticals() {
        super("Criticals", "Scores criticals for you", Category.COMBAT, true, false, false);
        this.mode = (Setting<Mode>)this.register(new Setting("Mode", (Object)Mode.PACKET));
        this.packets = (Setting<Integer>)this.register(new Setting("Packets", (Object)2, (Object)1, (Object)5, p0 -> this.mode.getValue() == Mode.PACKET, "Amount of packets you want to send."));
        this.desyncDelay = (Setting<Integer>)this.register(new Setting("DesyncDelay", (Object)10, (Object)0, (Object)500, p0 -> this.mode.getValue() == Mode.PACKET, "Amount of packets you want to send."));
        this.timer = new Timer();
        this.timer32k = new Timer();
        this.noDesync = (Setting<Boolean>)this.register(new Setting("NoDesync", (Object)true));
        this.cancelFirst = (Setting<Boolean>)this.register(new Setting("CancelFirst32k", (Object)true));
        this.delay32k = (Setting<Integer>)this.register(new Setting("32kDelay", (Object)25, (Object)0, (Object)500, p0 -> (boolean)this.cancelFirst.getValue()));
    }
    
    public static Criticals getInstance() {
        if (Criticals.INSTANCE == null) {
            Criticals.INSTANCE = new Criticals();
        }
        return Criticals.INSTANCE;
    }
    
    public enum Mode
    {
        NCPStrict, 
        JUMP, 
        MINIJUMP, 
        PACKET;
    }
}
