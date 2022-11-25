//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.movement;

import me.snow.luigihack.impl.modules.*;
import net.minecraft.entity.item.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.entity.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraft.network.play.server.*;
import net.minecraft.util.*;
import net.minecraft.entity.player.*;
import net.minecraft.network.play.client.*;
import net.minecraftforge.fml.common.eventhandler.*;
import java.util.*;

public class BoatFlyModule extends Module
{
    private /* synthetic */ int packetCounter;
    private /* synthetic */ EntityBoat target;
    private /* synthetic */ int teleportID;
    private /* synthetic */ Setting<Boolean> noKick;
    private /* synthetic */ Setting<Boolean> fixYaw;
    public /* synthetic */ Setting<Double> interact;
    public /* synthetic */ Setting<Double> speed;
    private /* synthetic */ boolean bebra;
    public /* synthetic */ Setting<Double> scale;
    public /* synthetic */ Setting<Double> verticalSpeed;
    
    public void onTick() {
        super.onTick();
        if (this.fixYaw.getValue()) {
            ((EntityBoat)BoatFlyModule.mc.player.getRidingEntity()).rotationYaw = BoatFlyModule.mc.player.rotationYaw;
        }
        if (BoatFlyModule.mc.player == null) {
            return;
        }
        if (BoatFlyModule.mc.world == null || BoatFlyModule.mc.player.getRidingEntity() == null) {
            return;
        }
        if (BoatFlyModule.mc.player.getRidingEntity() instanceof EntityBoat) {
            this.target = (EntityBoat)BoatFlyModule.mc.player.getRidingEntity();
        }
        BoatFlyModule.mc.player.getRidingEntity().setNoGravity(true);
        BoatFlyModule.mc.player.getRidingEntity().motionY = 0.0;
        if (BoatFlyModule.mc.gameSettings.keyBindJump.isKeyDown()) {
            BoatFlyModule.mc.player.getRidingEntity().onGround = false;
            BoatFlyModule.mc.player.getRidingEntity().motionY = (double)this.verticalSpeed.getValue() / 10.0;
        }
        if (BoatFlyModule.mc.gameSettings.keyBindSprint.isKeyDown()) {
            BoatFlyModule.mc.player.getRidingEntity().onGround = false;
            BoatFlyModule.mc.player.getRidingEntity().motionY = -((double)this.verticalSpeed.getValue() / 10.0);
        }
        final double[] directionSpeed = this.directionSpeed((double)this.speed.getValue() / 2.0);
        if (BoatFlyModule.mc.player.movementInput.moveStrafe != 0.0f || BoatFlyModule.mc.player.movementInput.moveForward != 0.0f) {
            BoatFlyModule.mc.player.getRidingEntity().motionX = directionSpeed[0];
            BoatFlyModule.mc.player.getRidingEntity().motionZ = directionSpeed[1];
        }
        else {
            BoatFlyModule.mc.player.getRidingEntity().motionX = 0.0;
            BoatFlyModule.mc.player.getRidingEntity().motionZ = 0.0;
        }
        if (this.noKick.getValue()) {
            if (BoatFlyModule.mc.gameSettings.keyBindJump.isKeyDown()) {
                if (BoatFlyModule.mc.player.ticksExisted % 8 < 2) {
                    BoatFlyModule.mc.player.getRidingEntity().motionY = -0.03999999910593033;
                }
            }
            else if (BoatFlyModule.mc.player.ticksExisted % 8 < 4) {
                BoatFlyModule.mc.player.getRidingEntity().motionY = -0.07999999821186066;
            }
        }
    }
    
    public BoatFlyModule() {
        super("BoatFly", "Automatically walks in a straight line", Module.Category.MOVEMENT, true, false, false);
        this.packetCounter = 0;
        this.bebra = false;
        this.fixYaw = (Setting<Boolean>)this.register(new Setting("FixYaw", (Object)true));
        this.noKick = (Setting<Boolean>)this.register(new Setting("AntiKick", (Object)true));
        this.speed = (Setting<Double>)this.register(new Setting("Speed", (Object)3.0, (Object)1.0, (Object)10.0));
        this.verticalSpeed = (Setting<Double>)this.register(new Setting("VSpeed", (Object)1.0, (Object)0.1, (Object)10.0));
        this.interact = (Setting<Double>)this.register(new Setting("interact", (Object)3.0, (Object)0.1, (Object)10.0));
        this.scale = (Setting<Double>)this.register(new Setting("BoatScale", (Object)1.0, (Object)0.1, (Object)10.0));
    }
    
    public void onDisable() {
        super.onDisable();
        BoatFlyModule.mc.player.getRidingEntity().setNoGravity(false);
    }
    
    @SubscribeEvent
    public void onSendPacket(final PacketEvent.Send send) {
        if (BoatFlyModule.mc.world != null && BoatFlyModule.mc.player != null && BoatFlyModule.mc.player.getRidingEntity() instanceof EntityBoat) {
            if ((boolean)this.noKick.getValue() && send.getPacket() instanceof CPacketInput && !BoatFlyModule.mc.gameSettings.keyBindSneak.isKeyDown() && !BoatFlyModule.mc.player.getRidingEntity().onGround) {
                ++this.packetCounter;
                if (this.packetCounter == 3) {
                    this.NCPPacketTrick();
                }
            }
            if (((boolean)this.noKick.getValue() && send.getPacket() instanceof SPacketPlayerPosLook) || send.getPacket() instanceof SPacketMoveVehicle) {
                send.setCanceled(true);
            }
        }
        if (send.getPacket() instanceof CPacketVehicleMove && BoatFlyModule.mc.player.isRiding() && BoatFlyModule.mc.player.ticksExisted % (double)this.interact.getValue() == 0.0) {
            BoatFlyModule.mc.playerController.interactWithEntity((EntityPlayer)BoatFlyModule.mc.player, BoatFlyModule.mc.player.getRidingEntity(), EnumHand.OFF_HAND);
        }
        if ((send.getPacket() instanceof CPacketPlayer.Rotation || send.getPacket() instanceof CPacketInput) && BoatFlyModule.mc.player.isRiding()) {
            send.setCanceled(true);
        }
    }
    
    @SubscribeEvent
    public void onReceivePacket(final PacketEvent.Receive receive) {
        if (receive.getPacket() instanceof SPacketMoveVehicle && BoatFlyModule.mc.player.isRiding()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketPlayerPosLook) {
            this.teleportID = ((SPacketPlayerPosLook)receive.getPacket()).getTeleportId();
        }
    }
    
    private void NCPPacketTrick() {
        this.packetCounter = 0;
        BoatFlyModule.mc.player.getRidingEntity().dismountRidingEntity();
        final Entity entity3 = (Entity)BoatFlyModule.mc.world.loadedEntityList.stream().filter(entity -> entity instanceof EntityBoat).min(Comparator.comparing(entity2 -> BoatFlyModule.mc.player.getDistance(entity2))).orElse(null);
        if (entity3 != null) {
            BoatFlyModule.mc.playerController.interactWithEntity((EntityPlayer)BoatFlyModule.mc.player, entity3, EnumHand.MAIN_HAND);
        }
    }
    
    private double[] directionSpeed(final double n) {
        float moveForward = BoatFlyModule.mc.player.movementInput.moveForward;
        float moveStrafe = BoatFlyModule.mc.player.movementInput.moveStrafe;
        float n2 = BoatFlyModule.mc.player.prevRotationYaw + (BoatFlyModule.mc.player.rotationYaw - BoatFlyModule.mc.player.prevRotationYaw) * BoatFlyModule.mc.getRenderPartialTicks();
        if (moveForward != 0.0f) {
            if (moveStrafe > 0.0f) {
                n2 += ((moveForward > 0.0f) ? -45 : 45);
            }
            else if (moveStrafe < 0.0f) {
                n2 += ((moveForward > 0.0f) ? 45 : -45);
            }
            moveStrafe = 0.0f;
            if (moveForward > 0.0f) {
                moveForward = 1.0f;
            }
            else if (moveForward < 0.0f) {
                moveForward = -1.0f;
            }
        }
        final double sin = Math.sin(Math.toRadians(n2 + 90.0f));
        final double cos = Math.cos(Math.toRadians(n2 + 90.0f));
        return new double[] { moveForward * n * cos + moveStrafe * n * sin, moveForward * n * sin - moveStrafe * n * cos };
    }
}
