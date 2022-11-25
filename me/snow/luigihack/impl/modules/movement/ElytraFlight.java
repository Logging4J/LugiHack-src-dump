//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.movement;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.init.*;
import net.minecraft.client.network.*;
import java.util.*;
import net.minecraft.entity.*;
import net.minecraft.network.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.client.entity.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.util.math.*;
import me.snow.luigihack.*;
import net.minecraft.item.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraft.network.play.client.*;

public class ElytraFlight extends Module
{
    public /* synthetic */ Setting<Float> speed;
    public /* synthetic */ Setting<Boolean> noKick;
    public /* synthetic */ Setting<Float> glide;
    public /* synthetic */ Setting<Mode> mode;
    private final /* synthetic */ Timer timer;
    public /* synthetic */ Setting<Boolean> lockPitch;
    public /* synthetic */ Setting<Float> vSpeed;
    public /* synthetic */ Setting<Float> hSpeed;
    public /* synthetic */ Setting<Integer> devMode;
    public /* synthetic */ Setting<Boolean> infiniteDura;
    public /* synthetic */ Setting<Boolean> autoStart;
    private /* synthetic */ Double posZ;
    private /* synthetic */ Double posX;
    public /* synthetic */ Setting<Boolean> disableInLiquid;
    public /* synthetic */ Setting<Boolean> allowUp;
    private /* synthetic */ Double flyHeight;
    private static /* synthetic */ ElytraFlight INSTANCE;
    public /* synthetic */ Setting<Float> tooBeeSpeed;
    
    private void runNoKick(final EntityPlayer entityPlayer) {
        if ((boolean)this.noKick.getValue() && !entityPlayer.isElytraFlying() && entityPlayer.ticksExisted % 4 == 0) {
            entityPlayer.motionY = -0.03999999910593033;
        }
    }
    
    @SubscribeEvent
    public void onUpdateWalkingPlayer(final UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (ElytraFlight.mc.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() != Items.ELYTRA) {
            return;
        }
        switch (updateWalkingPlayerEvent.getStage()) {
            case 0: {
                if ((boolean)this.disableInLiquid.getValue() && (ElytraFlight.mc.player.isInWater() || ElytraFlight.mc.player.isInLava())) {
                    if (ElytraFlight.mc.player.isElytraFlying()) {
                        Objects.requireNonNull(ElytraFlight.mc.getConnection()).sendPacket((Packet)new CPacketEntityAction((Entity)ElytraFlight.mc.player, CPacketEntityAction.Action.START_FALL_FLYING));
                    }
                    return;
                }
                if ((boolean)this.autoStart.getValue() && ElytraFlight.mc.gameSettings.keyBindJump.isKeyDown() && !ElytraFlight.mc.player.isElytraFlying() && ElytraFlight.mc.player.motionY < 0.0 && this.timer.passedMs(250L)) {
                    Objects.requireNonNull(ElytraFlight.mc.getConnection()).sendPacket((Packet)new CPacketEntityAction((Entity)ElytraFlight.mc.player, CPacketEntityAction.Action.START_FALL_FLYING));
                    this.timer.reset();
                }
                if (this.mode.getValue() == Mode.BETTER) {
                    final double[] directionSpeed = MathUtil.directionSpeed(((int)this.devMode.getValue() == 1) ? ((double)(float)this.speed.getValue()) : ((double)(float)this.hSpeed.getValue()));
                    switch ((int)this.devMode.getValue()) {
                        case 1: {
                            ElytraFlight.mc.player.setVelocity(0.0, 0.0, 0.0);
                            ElytraFlight.mc.player.jumpMovementFactor = (float)this.speed.getValue();
                            if (ElytraFlight.mc.gameSettings.keyBindJump.isKeyDown()) {
                                final EntityPlayerSP player = ElytraFlight.mc.player;
                                player.motionY += (float)this.speed.getValue();
                            }
                            if (ElytraFlight.mc.gameSettings.keyBindSneak.isKeyDown()) {
                                final EntityPlayerSP player2 = ElytraFlight.mc.player;
                                player2.motionY -= (float)this.speed.getValue();
                            }
                            if (ElytraFlight.mc.player.movementInput.moveStrafe != 0.0f || ElytraFlight.mc.player.movementInput.moveForward != 0.0f) {
                                ElytraFlight.mc.player.motionX = directionSpeed[0];
                                ElytraFlight.mc.player.motionZ = directionSpeed[1];
                                break;
                            }
                            ElytraFlight.mc.player.motionX = 0.0;
                            ElytraFlight.mc.player.motionZ = 0.0;
                            break;
                        }
                        case 2: {
                            if (ElytraFlight.mc.player.isElytraFlying()) {
                                if (this.flyHeight == null) {
                                    this.flyHeight = ElytraFlight.mc.player.posY;
                                }
                                if (this.noKick.getValue()) {
                                    this.flyHeight -= (Double)(float)this.glide.getValue();
                                }
                                this.posX = 0.0;
                                this.posZ = 0.0;
                                if (ElytraFlight.mc.player.movementInput.moveStrafe != 0.0f || ElytraFlight.mc.player.movementInput.moveForward != 0.0f) {
                                    this.posX = directionSpeed[0];
                                    this.posZ = directionSpeed[1];
                                }
                                if (ElytraFlight.mc.gameSettings.keyBindJump.isKeyDown()) {
                                    this.flyHeight = ElytraFlight.mc.player.posY + (float)this.vSpeed.getValue();
                                }
                                if (ElytraFlight.mc.gameSettings.keyBindSneak.isKeyDown()) {
                                    this.flyHeight = ElytraFlight.mc.player.posY - (float)this.vSpeed.getValue();
                                }
                                ElytraFlight.mc.player.setPosition(ElytraFlight.mc.player.posX + this.posX, (double)this.flyHeight, ElytraFlight.mc.player.posZ + this.posZ);
                                ElytraFlight.mc.player.setVelocity(0.0, 0.0, 0.0);
                                break;
                            }
                            this.flyHeight = null;
                            return;
                        }
                        case 3: {
                            if (ElytraFlight.mc.player.isElytraFlying()) {
                                if (this.flyHeight == null || this.posX == null || this.posX == 0.0 || this.posZ == null || this.posZ == 0.0) {
                                    this.flyHeight = ElytraFlight.mc.player.posY;
                                    this.posX = ElytraFlight.mc.player.posX;
                                    this.posZ = ElytraFlight.mc.player.posZ;
                                }
                                if (this.noKick.getValue()) {
                                    this.flyHeight -= (Double)(float)this.glide.getValue();
                                }
                                if (ElytraFlight.mc.player.movementInput.moveStrafe != 0.0f || ElytraFlight.mc.player.movementInput.moveForward != 0.0f) {
                                    this.posX += directionSpeed[0];
                                    this.posZ += directionSpeed[1];
                                }
                                if ((boolean)this.allowUp.getValue() && ElytraFlight.mc.gameSettings.keyBindJump.isKeyDown()) {
                                    this.flyHeight = ElytraFlight.mc.player.posY + (float)this.vSpeed.getValue() / 10.0f;
                                }
                                if (ElytraFlight.mc.gameSettings.keyBindSneak.isKeyDown()) {
                                    this.flyHeight = ElytraFlight.mc.player.posY - (float)this.vSpeed.getValue() / 10.0f;
                                }
                                ElytraFlight.mc.player.setPosition((double)this.posX, (double)this.flyHeight, (double)this.posZ);
                                ElytraFlight.mc.player.setVelocity(0.0, 0.0, 0.0);
                                break;
                            }
                            this.flyHeight = null;
                            this.posX = null;
                            this.posZ = null;
                            return;
                        }
                    }
                }
                final double radians = Math.toRadians(ElytraFlight.mc.player.rotationYaw);
                if (ElytraFlight.mc.player.isElytraFlying()) {
                    switch ((Mode)this.mode.getValue()) {
                        case VANILLA: {
                            final float n = (float)this.speed.getValue() * 0.05f;
                            if (ElytraFlight.mc.gameSettings.keyBindJump.isKeyDown()) {
                                final EntityPlayerSP player3 = ElytraFlight.mc.player;
                                player3.motionY += n;
                            }
                            if (ElytraFlight.mc.gameSettings.keyBindSneak.isKeyDown()) {
                                final EntityPlayerSP player4 = ElytraFlight.mc.player;
                                player4.motionY -= n;
                            }
                            if (ElytraFlight.mc.gameSettings.keyBindForward.isKeyDown()) {
                                final EntityPlayerSP player5 = ElytraFlight.mc.player;
                                player5.motionX -= Math.sin(radians) * n;
                                final EntityPlayerSP player6 = ElytraFlight.mc.player;
                                player6.motionZ += Math.cos(radians) * n;
                            }
                            if (!ElytraFlight.mc.gameSettings.keyBindBack.isKeyDown()) {
                                break;
                            }
                            final EntityPlayerSP player7 = ElytraFlight.mc.player;
                            player7.motionX += Math.sin(radians) * n;
                            final EntityPlayerSP player8 = ElytraFlight.mc.player;
                            player8.motionZ -= Math.cos(radians) * n;
                            break;
                        }
                        case PACKET: {
                            this.freezePlayer((EntityPlayer)ElytraFlight.mc.player);
                            this.runNoKick((EntityPlayer)ElytraFlight.mc.player);
                            final double[] directionSpeed2 = MathUtil.directionSpeed((double)(float)this.speed.getValue());
                            if (ElytraFlight.mc.player.movementInput.jump) {
                                ElytraFlight.mc.player.motionY = (float)this.speed.getValue();
                            }
                            if (ElytraFlight.mc.player.movementInput.sneak) {
                                ElytraFlight.mc.player.motionY = -(float)this.speed.getValue();
                            }
                            if (ElytraFlight.mc.player.movementInput.moveStrafe != 0.0f || ElytraFlight.mc.player.movementInput.moveForward != 0.0f) {
                                ElytraFlight.mc.player.motionX = directionSpeed2[0];
                                ElytraFlight.mc.player.motionZ = directionSpeed2[1];
                            }
                            Objects.requireNonNull(ElytraFlight.mc.getConnection()).sendPacket((Packet)new CPacketEntityAction((Entity)ElytraFlight.mc.player, CPacketEntityAction.Action.START_FALL_FLYING));
                            ElytraFlight.mc.getConnection().sendPacket((Packet)new CPacketEntityAction((Entity)ElytraFlight.mc.player, CPacketEntityAction.Action.START_FALL_FLYING));
                            break;
                        }
                        case BYPASS: {
                            if ((int)this.devMode.getValue() != 3) {
                                break;
                            }
                            if (ElytraFlight.mc.gameSettings.keyBindJump.isKeyDown()) {
                                ElytraFlight.mc.player.motionY = 0.019999999552965164;
                            }
                            if (ElytraFlight.mc.gameSettings.keyBindSneak.isKeyDown()) {
                                ElytraFlight.mc.player.motionY = -0.20000000298023224;
                            }
                            if (ElytraFlight.mc.player.ticksExisted % 8 == 0 && ElytraFlight.mc.player.posY <= 240.0) {
                                ElytraFlight.mc.player.motionY = 0.019999999552965164;
                            }
                            ElytraFlight.mc.player.capabilities.isFlying = true;
                            ElytraFlight.mc.player.capabilities.setFlySpeed(0.025f);
                            final double[] directionSpeed3 = MathUtil.directionSpeed(0.5199999809265137);
                            if (ElytraFlight.mc.player.movementInput.moveStrafe != 0.0f || ElytraFlight.mc.player.movementInput.moveForward != 0.0f) {
                                ElytraFlight.mc.player.motionX = directionSpeed3[0];
                                ElytraFlight.mc.player.motionZ = directionSpeed3[1];
                                break;
                            }
                            ElytraFlight.mc.player.motionX = 0.0;
                            ElytraFlight.mc.player.motionZ = 0.0;
                            break;
                        }
                    }
                }
                if (!(boolean)this.infiniteDura.getValue()) {
                    break;
                }
                ElytraFlight.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)ElytraFlight.mc.player, CPacketEntityAction.Action.START_FALL_FLYING));
                break;
            }
            case 1: {
                if (!(boolean)this.infiniteDura.getValue()) {
                    break;
                }
                ElytraFlight.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)ElytraFlight.mc.player, CPacketEntityAction.Action.START_FALL_FLYING));
                break;
            }
        }
    }
    
    public void onTick() {
        if (!ElytraFlight.mc.player.isElytraFlying()) {
            return;
        }
        switch ((Mode)this.mode.getValue()) {
            case BOOST: {
                if (ElytraFlight.mc.player.isInWater()) {
                    Objects.requireNonNull(ElytraFlight.mc.getConnection()).sendPacket((Packet)new CPacketEntityAction((Entity)ElytraFlight.mc.player, CPacketEntityAction.Action.START_FALL_FLYING));
                    return;
                }
                if (ElytraFlight.mc.gameSettings.keyBindJump.isKeyDown()) {
                    final EntityPlayerSP player = ElytraFlight.mc.player;
                    player.motionY += 0.08;
                }
                else if (ElytraFlight.mc.gameSettings.keyBindSneak.isKeyDown()) {
                    final EntityPlayerSP player2 = ElytraFlight.mc.player;
                    player2.motionY -= 0.04;
                }
                if (ElytraFlight.mc.gameSettings.keyBindForward.isKeyDown()) {
                    final float n = (float)Math.toRadians(ElytraFlight.mc.player.rotationYaw);
                    final EntityPlayerSP player3 = ElytraFlight.mc.player;
                    player3.motionX -= MathHelper.sin(n) * 0.05f;
                    final EntityPlayerSP player4 = ElytraFlight.mc.player;
                    player4.motionZ += MathHelper.cos(n) * 0.05f;
                    break;
                }
                if (!ElytraFlight.mc.gameSettings.keyBindBack.isKeyDown()) {
                    break;
                }
                final float n2 = (float)Math.toRadians(ElytraFlight.mc.player.rotationYaw);
                final EntityPlayerSP player5 = ElytraFlight.mc.player;
                player5.motionX += MathHelper.sin(n2) * 0.05f;
                final EntityPlayerSP player6 = ElytraFlight.mc.player;
                player6.motionZ -= MathHelper.cos(n2) * 0.05f;
                break;
            }
            case FLY: {
                ElytraFlight.mc.player.capabilities.isFlying = true;
                break;
            }
        }
    }
    
    static {
        ElytraFlight.INSTANCE = new ElytraFlight();
    }
    
    @SubscribeEvent
    public void onMove(final MoveEvent moveEvent) {
        if (this.mode.getValue() == Mode.LOOK && ElytraFlight.mc.player.isElytraFlying()) {
            ElytraFlight.mc.player.motionY = 0.0;
            ElytraFlight.mc.player.motionX = 0.0;
            ElytraFlight.mc.player.motionZ = 0.0;
            if (ElytraFlight.mc.player.movementInput.moveForward != 0.0f) {
                ElytraFlight.mc.player.motionY = (float)this.hSpeed.getValue() * -(ElytraFlight.mc.player.movementInput.moveForward * Math.sin(MathUtil.degToRad((double)ElytraFlight.mc.player.rotationPitch)));
            }
            if (ElytraFlight.mc.player.movementInput.jump) {
                ElytraFlight.mc.player.motionY = (float)this.vSpeed.getValue();
            }
            else if (ElytraFlight.mc.player.movementInput.sneak) {
                ElytraFlight.mc.player.motionY = -1.0f * (float)this.vSpeed.getValue();
            }
            double n = ElytraFlight.mc.player.movementInput.moveForward;
            double n2 = ElytraFlight.mc.player.movementInput.moveStrafe;
            float rotationYaw = ElytraFlight.mc.player.rotationYaw;
            if (n != 0.0) {
                if (n2 > 0.0) {
                    rotationYaw += ((n > 0.0) ? -45 : 45);
                }
                else if (n2 < 0.0) {
                    rotationYaw += ((n > 0.0) ? 45 : -45);
                }
                n2 = 0.0;
                if (n > 0.0) {
                    n = 1.0;
                }
                else if (n < 0.0) {
                    n = -1.0;
                }
            }
            final double cos = Math.cos(Math.toRadians(rotationYaw + 90.0f));
            final double sin = Math.sin(Math.toRadians(rotationYaw + 90.0f));
            if (ElytraFlight.mc.player.movementInput.moveStrafe != 0.0f && ElytraFlight.mc.player.movementInput.moveForward == 0.0f) {
                ElytraFlight.mc.player.motionX = n * (float)this.hSpeed.getValue() * cos + n2 * (float)this.hSpeed.getValue() * sin;
                ElytraFlight.mc.player.motionZ = n * (float)this.hSpeed.getValue() * sin - n2 * (float)this.hSpeed.getValue() * cos;
            }
            else if (ElytraFlight.mc.player.movementInput.moveStrafe != 0.0f || ElytraFlight.mc.player.movementInput.moveForward != 0.0f) {
                ElytraFlight.mc.player.motionX = (n * (float)this.hSpeed.getValue() * cos + n2 * (float)this.hSpeed.getValue() * sin) * Math.cos(Math.abs(MathUtil.degToRad((double)ElytraFlight.mc.player.rotationPitch)));
                ElytraFlight.mc.player.motionZ = (n * (float)this.hSpeed.getValue() * sin - n2 * (float)this.hSpeed.getValue() * cos) * Math.cos(Math.abs(MathUtil.degToRad((double)ElytraFlight.mc.player.rotationPitch)));
            }
            moveEvent.setX(ElytraFlight.mc.player.motionX);
            moveEvent.setY(ElytraFlight.mc.player.motionY);
            moveEvent.setZ(ElytraFlight.mc.player.motionZ);
        }
        if (this.mode.getValue() == Mode.OHARE) {
            final ItemStack getItemStackFromSlot = ElytraFlight.mc.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
            if (getItemStackFromSlot.getItem() == Items.ELYTRA && ItemElytra.isUsable(getItemStackFromSlot) && ElytraFlight.mc.player.isElytraFlying()) {
                moveEvent.setY(ElytraFlight.mc.gameSettings.keyBindJump.isKeyDown() ? ((double)(float)this.vSpeed.getValue()) : (ElytraFlight.mc.gameSettings.keyBindSneak.isKeyDown() ? (-(float)this.vSpeed.getValue()) : 0.0));
                ElytraFlight.mc.player.addVelocity(0.0, ElytraFlight.mc.gameSettings.keyBindJump.isKeyDown() ? ((double)(float)this.vSpeed.getValue()) : (ElytraFlight.mc.gameSettings.keyBindSneak.isKeyDown() ? (-(float)this.vSpeed.getValue()) : 0.0), 0.0);
                ElytraFlight.mc.player.rotateElytraX = 0.0f;
                ElytraFlight.mc.player.rotateElytraY = 0.0f;
                ElytraFlight.mc.player.rotateElytraZ = 0.0f;
                ElytraFlight.mc.player.moveVertical = (float)(ElytraFlight.mc.gameSettings.keyBindJump.isKeyDown() ? this.vSpeed.getValue() : (ElytraFlight.mc.gameSettings.keyBindSneak.isKeyDown() ? (-(float)this.vSpeed.getValue()) : 0.0f));
                double n3 = ElytraFlight.mc.player.movementInput.moveForward;
                double n4 = ElytraFlight.mc.player.movementInput.moveStrafe;
                float rotationYaw2 = ElytraFlight.mc.player.rotationYaw;
                if (n3 == 0.0 && n4 == 0.0) {
                    moveEvent.setX(0.0);
                    moveEvent.setZ(0.0);
                }
                else {
                    if (n3 != 0.0) {
                        if (n4 > 0.0) {
                            rotationYaw2 += ((n3 > 0.0) ? -45 : 45);
                        }
                        else if (n4 < 0.0) {
                            rotationYaw2 += ((n3 > 0.0) ? 45 : -45);
                        }
                        n4 = 0.0;
                        if (n3 > 0.0) {
                            n3 = 1.0;
                        }
                        else if (n3 < 0.0) {
                            n3 = -1.0;
                        }
                    }
                    final double cos2 = Math.cos(Math.toRadians(rotationYaw2 + 90.0f));
                    final double sin2 = Math.sin(Math.toRadians(rotationYaw2 + 90.0f));
                    moveEvent.setX(n3 * (float)this.hSpeed.getValue() * cos2 + n4 * (float)this.hSpeed.getValue() * sin2);
                    moveEvent.setZ(n3 * (float)this.hSpeed.getValue() * sin2 - n4 * (float)this.hSpeed.getValue() * cos2);
                }
            }
        }
        else if (moveEvent.getStage() == 0 && this.mode.getValue() == Mode.BYPASS && (int)this.devMode.getValue() == 3) {
            if (ElytraFlight.mc.player.isElytraFlying()) {
                moveEvent.setX(0.0);
                moveEvent.setY(-1.0E-4);
                moveEvent.setZ(0.0);
                final double n5 = ElytraFlight.mc.player.movementInput.moveForward;
                final double n6 = ElytraFlight.mc.player.movementInput.moveStrafe;
                final double[] forwardStrafeYaw = this.forwardStrafeYaw(n5, n6, ElytraFlight.mc.player.rotationYaw);
                final double n7 = forwardStrafeYaw[0];
                final double n8 = forwardStrafeYaw[1];
                final double n9 = forwardStrafeYaw[2];
                if (n5 != 0.0 || n6 != 0.0) {
                    final double cos3 = Math.cos(Math.toRadians(n9 + 90.0));
                    final double sin3 = Math.sin(Math.toRadians(n9 + 90.0));
                    moveEvent.setX(n7 * (float)this.speed.getValue() * cos3 + n8 * (float)this.speed.getValue() * sin3);
                    moveEvent.setY(n7 * (float)this.speed.getValue() * sin3 - n8 * (float)this.speed.getValue() * cos3);
                }
                if (ElytraFlight.mc.gameSettings.keyBindSneak.isKeyDown()) {
                    moveEvent.setY(-1.0);
                }
            }
        }
        else if (this.mode.getValue() == Mode.TOOBEE) {
            if (!ElytraFlight.mc.player.isElytraFlying()) {
                return;
            }
            if (ElytraFlight.mc.player.movementInput.jump) {
                return;
            }
            if (ElytraFlight.mc.player.movementInput.sneak) {
                ElytraFlight.mc.player.motionY = -((float)this.tooBeeSpeed.getValue() / 2.0f);
                moveEvent.setY((double)(-((float)this.speed.getValue() / 2.0f)));
            }
            else if (moveEvent.getY() != -1.01E-4) {
                moveEvent.setY(-1.01E-4);
                ElytraFlight.mc.player.motionY = -1.01E-4;
            }
            this.setMoveSpeed(moveEvent, (float)this.tooBeeSpeed.getValue());
        }
        else if (this.mode.getValue() == Mode.TOOBEEBYPASS) {
            if (!ElytraFlight.mc.player.isElytraFlying()) {
                return;
            }
            if (ElytraFlight.mc.player.movementInput.jump) {
                return;
            }
            if (this.lockPitch.getValue()) {
                ElytraFlight.mc.player.rotationPitch = 4.0f;
            }
            if (LuigiHack.speedManager.getSpeedKpH() > 180.0) {
                return;
            }
            final double radians = Math.toRadians(ElytraFlight.mc.player.rotationYaw);
            final EntityPlayerSP player = ElytraFlight.mc.player;
            player.motionX -= ElytraFlight.mc.player.movementInput.moveForward * Math.sin(radians) * 0.04;
            final EntityPlayerSP player2 = ElytraFlight.mc.player;
            player2.motionZ += ElytraFlight.mc.player.movementInput.moveForward * Math.cos(radians) * 0.04;
        }
    }
    
    @SubscribeEvent
    public void onSendPacket(final PacketEvent.Send send) {
        if (send.getPacket() instanceof CPacketPlayer && this.mode.getValue() == Mode.TOOBEE) {
            send.getPacket();
            ElytraFlight.mc.player.isElytraFlying();
        }
        if (send.getPacket() instanceof CPacketPlayer && this.mode.getValue() == Mode.TOOBEEBYPASS) {
            send.getPacket();
            ElytraFlight.mc.player.isElytraFlying();
        }
    }
    
    public static ElytraFlight getInstance() {
        if (ElytraFlight.INSTANCE == null) {
            ElytraFlight.INSTANCE = new ElytraFlight();
        }
        return ElytraFlight.INSTANCE;
    }
    
    private double[] forwardStrafeYaw(final double n, final double n2, final double n3) {
        final double[] array = { n, n2, n3 };
        if ((n != 0.0 || n2 != 0.0) && n != 0.0) {
            if (n2 > 0.0) {
                array[2] += ((n > 0.0) ? -45 : 45);
            }
            else if (n2 < 0.0) {
                array[2] += ((n > 0.0) ? 45 : -45);
            }
            array[1] = 0.0;
            if (n > 0.0) {
                array[0] = 1.0;
            }
            else if (n < 0.0) {
                array[0] = -1.0;
            }
        }
        return array;
    }
    
    private void setInstance() {
        ElytraFlight.INSTANCE = this;
    }
    
    public void onUpdate() {
        if (this.mode.getValue() == Mode.BYPASS && (int)this.devMode.getValue() == 1 && ElytraFlight.mc.player.isElytraFlying()) {
            ElytraFlight.mc.player.motionX = 0.0;
            ElytraFlight.mc.player.motionY = -1.0E-4;
            ElytraFlight.mc.player.motionZ = 0.0;
            final double n = ElytraFlight.mc.player.movementInput.moveForward;
            final double n2 = ElytraFlight.mc.player.movementInput.moveStrafe;
            final double[] forwardStrafeYaw = this.forwardStrafeYaw(n, n2, ElytraFlight.mc.player.rotationYaw);
            final double n3 = forwardStrafeYaw[0];
            final double n4 = forwardStrafeYaw[1];
            final double n5 = forwardStrafeYaw[2];
            if (n != 0.0 || n2 != 0.0) {
                final double cos = Math.cos(Math.toRadians(n5 + 90.0));
                final double sin = Math.sin(Math.toRadians(n5 + 90.0));
                ElytraFlight.mc.player.motionX = n3 * (float)this.speed.getValue() * cos + n4 * (float)this.speed.getValue() * sin;
                ElytraFlight.mc.player.motionZ = n3 * (float)this.speed.getValue() * sin - n4 * (float)this.speed.getValue() * cos;
            }
            if (ElytraFlight.mc.gameSettings.keyBindSneak.isKeyDown()) {
                ElytraFlight.mc.player.motionY = -1.0;
            }
        }
    }
    
    public void onDisable() {
        if (fullNullCheck() || ElytraFlight.mc.player.capabilities.isCreativeMode) {
            return;
        }
        ElytraFlight.mc.player.capabilities.isFlying = false;
    }
    
    public String getDisplayInfo() {
        return this.mode.currentEnumName();
    }
    
    public ElytraFlight() {
        super("ElytraFlight", "Makes Elytra Flight better.", Module.Category.MOVEMENT, true, false, false);
        this.timer = new Timer();
        this.mode = (Setting<Mode>)this.register(new Setting("Mode", (Object)Mode.FLY));
        this.devMode = (Setting<Integer>)this.register(new Setting("Type", (Object)2, (Object)1, (Object)3, p0 -> this.mode.getValue() == Mode.BYPASS || this.mode.getValue() == Mode.BETTER, "EventMode"));
        this.speed = (Setting<Float>)this.register(new Setting("Speed", (Object)1.0f, (Object)0.0f, (Object)10.0f, p0 -> this.mode.getValue() != Mode.FLY && this.mode.getValue() != Mode.BOOST && this.mode.getValue() != Mode.BETTER && this.mode.getValue() != Mode.OHARE && this.mode.getValue() != Mode.LOOK, "The Speed."));
        this.vSpeed = (Setting<Float>)this.register(new Setting("VSpeed", (Object)0.3f, (Object)0.0f, (Object)10.0f, p0 -> this.mode.getValue() == Mode.BETTER || this.mode.getValue() == Mode.OHARE || this.mode.getValue() == Mode.LOOK, "Vertical Speed"));
        this.hSpeed = (Setting<Float>)this.register(new Setting("HSpeed", (Object)1.0f, (Object)0.0f, (Object)10.0f, p0 -> this.mode.getValue() == Mode.BETTER || this.mode.getValue() == Mode.OHARE || this.mode.getValue() == Mode.LOOK, "Horizontal Speed"));
        this.glide = (Setting<Float>)this.register(new Setting("Glide", (Object)1.0E-4f, (Object)0.0f, (Object)0.2f, p0 -> this.mode.getValue() == Mode.BETTER, "Glide Speed"));
        this.tooBeeSpeed = (Setting<Float>)this.register(new Setting("TooBeeSpeed", (Object)1.8000001f, (Object)1.0f, (Object)2.0f, p0 -> this.mode.getValue() == Mode.TOOBEE, "Speed for flight on 2b2t"));
        this.autoStart = (Setting<Boolean>)this.register(new Setting("AutoStart", (Object)true));
        this.disableInLiquid = (Setting<Boolean>)this.register(new Setting("NoLiquid", (Object)true));
        this.infiniteDura = (Setting<Boolean>)this.register(new Setting("InfiniteDurability", (Object)false));
        this.noKick = (Setting<Boolean>)this.register(new Setting("NoKick", (Object)Boolean.FALSE, p0 -> this.mode.getValue() == Mode.PACKET));
        this.allowUp = (Setting<Boolean>)this.register(new Setting("AllowUp", (Object)Boolean.TRUE, p0 -> this.mode.getValue() == Mode.BETTER));
        this.lockPitch = (Setting<Boolean>)this.register(new Setting("LockPitch", (Object)false));
        this.setInstance();
    }
    
    public void onEnable() {
        if (this.mode.getValue() == Mode.BETTER && !(boolean)this.autoStart.getValue() && (int)this.devMode.getValue() == 1) {
            ElytraFlight.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)ElytraFlight.mc.player, CPacketEntityAction.Action.START_FALL_FLYING));
        }
        this.flyHeight = null;
        this.posX = null;
        this.posZ = null;
    }
    
    private void setMoveSpeed(final MoveEvent moveEvent, final double n) {
        double n2 = ElytraFlight.mc.player.movementInput.moveForward;
        double n3 = ElytraFlight.mc.player.movementInput.moveStrafe;
        float rotationYaw = ElytraFlight.mc.player.rotationYaw;
        if (n2 == 0.0 && n3 == 0.0) {
            moveEvent.setX(0.0);
            moveEvent.setZ(0.0);
            ElytraFlight.mc.player.motionX = 0.0;
            ElytraFlight.mc.player.motionZ = 0.0;
        }
        else {
            if (n2 != 0.0) {
                if (n3 > 0.0) {
                    rotationYaw += ((n2 > 0.0) ? -45 : 45);
                }
                else if (n3 < 0.0) {
                    rotationYaw += ((n2 > 0.0) ? 45 : -45);
                }
                n3 = 0.0;
                if (n2 > 0.0) {
                    n2 = 1.0;
                }
                else if (n2 < 0.0) {
                    n2 = -1.0;
                }
            }
            final double n4 = n2 * n * -Math.sin(Math.toRadians(rotationYaw)) + n3 * n * Math.cos(Math.toRadians(rotationYaw));
            final double n5 = n2 * n * Math.cos(Math.toRadians(rotationYaw)) - n3 * n * -Math.sin(Math.toRadians(rotationYaw));
            moveEvent.setX(n4);
            moveEvent.setZ(n5);
            ElytraFlight.mc.player.motionX = n4;
            ElytraFlight.mc.player.motionZ = n5;
        }
    }
    
    private void freezePlayer(final EntityPlayer entityPlayer) {
        entityPlayer.motionX = 0.0;
        entityPlayer.motionY = 0.0;
        entityPlayer.motionZ = 0.0;
    }
    
    public enum Mode
    {
        LOOK, 
        FLY, 
        PACKET, 
        TOOBEE, 
        TOOBEEBYPASS, 
        BOOST, 
        BETTER, 
        VANILLA, 
        OHARE, 
        BYPASS;
    }
}
