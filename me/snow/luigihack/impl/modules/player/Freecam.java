//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.player;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.client.entity.*;
import me.snow.luigihack.impl.*;
import net.minecraft.network.play.server.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.world.*;
import me.snow.luigihack.api.event.events.*;

public class Freecam extends Module
{
    public /* synthetic */ Setting<Boolean> disable;
    public /* synthetic */ Setting<Boolean> view;
    private /* synthetic */ Entity riding;
    public /* synthetic */ Setting<Double> speed;
    private static /* synthetic */ Freecam INSTANCE;
    public /* synthetic */ Setting<Boolean> legit;
    private /* synthetic */ float yaw;
    private /* synthetic */ Vec3d position;
    private /* synthetic */ AxisAlignedBB oldBoundingBox;
    private /* synthetic */ float pitch;
    public /* synthetic */ Setting<Boolean> packet;
    private /* synthetic */ EntityOtherPlayerMP entity;
    
    private void setInstance() {
        Freecam.INSTANCE = this;
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        if ((boolean)this.legit.getValue() && this.entity != null && send.getPacket() instanceof CPacketPlayer) {
            final CPacketPlayer cPacketPlayer = (CPacketPlayer)send.getPacket();
            cPacketPlayer.x = this.entity.posX;
            cPacketPlayer.y = this.entity.posY;
            cPacketPlayer.z = this.entity.posZ;
            return;
        }
        if (this.packet.getValue()) {
            if (send.getPacket() instanceof CPacketPlayer) {
                send.setCanceled(true);
            }
        }
        else if (!(send.getPacket() instanceof CPacketUseEntity) && !(send.getPacket() instanceof CPacketPlayerTryUseItem) && !(send.getPacket() instanceof CPacketPlayerTryUseItemOnBlock) && !(send.getPacket() instanceof CPacketPlayer) && !(send.getPacket() instanceof CPacketVehicleMove) && !(send.getPacket() instanceof CPacketChatMessage) && !(send.getPacket() instanceof CPacketKeepAlive)) {
            send.setCanceled(true);
        }
    }
    
    public void onUpdate() {
        Freecam.mc.player.noClip = true;
        Freecam.mc.player.setVelocity(0.0, 0.0, 0.0);
        Freecam.mc.player.jumpMovementFactor = ((Double)this.speed.getValue()).floatValue();
        final double[] directionSpeed = MathUtil.directionSpeed((double)this.speed.getValue());
        if (Freecam.mc.player.movementInput.moveStrafe != 0.0f || Freecam.mc.player.movementInput.moveForward != 0.0f) {
            Freecam.mc.player.motionX = directionSpeed[0];
            Freecam.mc.player.motionZ = directionSpeed[1];
        }
        else {
            Freecam.mc.player.motionX = 0.0;
            Freecam.mc.player.motionZ = 0.0;
        }
        Freecam.mc.player.setSprinting(false);
        if ((boolean)this.view.getValue() && !Freecam.mc.gameSettings.keyBindSneak.isKeyDown() && !Freecam.mc.gameSettings.keyBindJump.isKeyDown()) {
            Freecam.mc.player.motionY = (double)this.speed.getValue() * -MathUtil.degToRad((double)Freecam.mc.player.rotationPitch) * Freecam.mc.player.movementInput.moveForward;
        }
        if (Freecam.mc.gameSettings.keyBindJump.isKeyDown()) {
            final EntityPlayerSP player = Freecam.mc.player;
            player.motionY += (double)this.speed.getValue();
        }
        if (Freecam.mc.gameSettings.keyBindSneak.isKeyDown()) {
            final EntityPlayerSP player2 = Freecam.mc.player;
            player2.motionY -= (double)this.speed.getValue();
        }
    }
    
    static {
        Freecam.INSTANCE = new Freecam();
    }
    
    public void onDisable() {
        if (!Feature.fullNullCheck()) {
            Freecam.mc.player.setEntityBoundingBox(this.oldBoundingBox);
            if (this.riding != null) {
                Freecam.mc.player.startRiding(this.riding, true);
            }
            if (this.entity != null) {
                Freecam.mc.world.removeEntity((Entity)this.entity);
            }
            if (this.position != null) {
                Freecam.mc.player.setPosition(this.position.x, this.position.y, this.position.z);
            }
            Freecam.mc.player.rotationYaw = this.yaw;
            Freecam.mc.player.rotationPitch = this.pitch;
            Freecam.mc.player.noClip = false;
        }
    }
    
    @SubscribeEvent
    public void onPacketReceive(final PacketEvent.Receive receive) {
        if (receive.getPacket() instanceof SPacketSetPassengers) {
            final Entity getEntityByID = Freecam.mc.world.getEntityByID(((SPacketSetPassengers)receive.getPacket()).getEntityId());
            if (getEntityByID != null && getEntityByID == this.riding) {
                this.riding = null;
            }
        }
        if (receive.getPacket() instanceof SPacketPlayerPosLook) {
            final SPacketPlayerPosLook sPacketPlayerPosLook = (SPacketPlayerPosLook)receive.getPacket();
            if (this.packet.getValue()) {
                if (this.entity != null) {
                    this.entity.setPositionAndRotation(sPacketPlayerPosLook.getX(), sPacketPlayerPosLook.getY(), sPacketPlayerPosLook.getZ(), sPacketPlayerPosLook.getYaw(), sPacketPlayerPosLook.getPitch());
                }
                this.position = new Vec3d(sPacketPlayerPosLook.getX(), sPacketPlayerPosLook.getY(), sPacketPlayerPosLook.getZ());
                Freecam.mc.player.connection.sendPacket((Packet)new CPacketConfirmTeleport(sPacketPlayerPosLook.getTeleportId()));
                receive.setCanceled(true);
            }
            else {
                receive.setCanceled(true);
            }
        }
    }
    
    public void onEnable() {
        if (!Feature.fullNullCheck()) {
            this.oldBoundingBox = Freecam.mc.player.getEntityBoundingBox();
            Freecam.mc.player.setEntityBoundingBox(new AxisAlignedBB(Freecam.mc.player.posX, Freecam.mc.player.posY, Freecam.mc.player.posZ, Freecam.mc.player.posX, Freecam.mc.player.posY, Freecam.mc.player.posZ));
            if (Freecam.mc.player.getRidingEntity() != null) {
                this.riding = Freecam.mc.player.getRidingEntity();
                Freecam.mc.player.dismountRidingEntity();
            }
            final EntityOtherPlayerMP entity = new EntityOtherPlayerMP((World)Freecam.mc.world, Freecam.mc.session.getProfile());
            this.entity = entity;
            entity.copyLocationAndAnglesFrom((Entity)Freecam.mc.player);
            this.entity.rotationYaw = Freecam.mc.player.rotationYaw;
            this.entity.rotationYawHead = Freecam.mc.player.rotationYawHead;
            this.entity.inventory.copyInventory(Freecam.mc.player.inventory);
            Freecam.mc.world.addEntityToWorld(69420, (Entity)this.entity);
            this.position = Freecam.mc.player.getPositionVector();
            this.yaw = Freecam.mc.player.rotationYaw;
            this.pitch = Freecam.mc.player.rotationPitch;
            Freecam.mc.player.noClip = true;
        }
    }
    
    public void onLogout() {
        if (this.disable.getValue()) {
            this.disable();
        }
    }
    
    public Freecam() {
        super("Freecam", "Look around freely.", Module.Category.PLAYER, true, false, false);
        this.speed = (Setting<Double>)this.register(new Setting("Speed", (Object)0.5, (Object)0.1, (Object)5.0));
        this.view = (Setting<Boolean>)this.register(new Setting("3D", (Object)false));
        this.packet = (Setting<Boolean>)this.register(new Setting("Packet", (Object)true));
        this.disable = (Setting<Boolean>)this.register(new Setting("Logout/Off", (Object)true));
        this.legit = (Setting<Boolean>)this.register(new Setting("Legit", (Object)false));
        this.setInstance();
    }
    
    @SubscribeEvent
    public void onPush(final PushEvent pushEvent) {
        if (pushEvent.getStage() == 1) {
            pushEvent.setCanceled(true);
        }
    }
    
    public static Freecam getInstance() {
        if (Freecam.INSTANCE == null) {
            Freecam.INSTANCE = new Freecam();
        }
        return Freecam.INSTANCE;
    }
}
