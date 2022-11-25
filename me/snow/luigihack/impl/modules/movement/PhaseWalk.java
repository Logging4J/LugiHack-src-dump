//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.movement;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import net.minecraft.network.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.entity.*;

public class PhaseWalk extends Module
{
    public final /* synthetic */ Setting<Double> phaseSpeed;
    public final /* synthetic */ Setting<Boolean> clip;
    public final /* synthetic */ Setting<NoClipMode> noClipMode;
    public final /* synthetic */ Setting<Boolean> downOnShift;
    public final /* synthetic */ Setting<Boolean> instantWalk;
    public final /* synthetic */ Setting<Integer> antiVoidHeight;
    public final /* synthetic */ Setting<Boolean> antiVoid;
    public final /* synthetic */ Setting<Integer> stopMotionDelay;
    public final /* synthetic */ Setting<Boolean> stopMotion;
    public final /* synthetic */ Setting<Double> instantWalkSpeed;
    public final /* synthetic */ Setting<Boolean> fallPacket;
    public final /* synthetic */ Setting<Boolean> phaseCheck;
    public final /* synthetic */ Setting<Boolean> sprintPacket;
    /* synthetic */ int delay;
    
    public void onDisable() {
        PhaseWalk.mc.player.noClip = false;
    }
    
    public void onUpdate() {
        ++this.delay;
        final double n = (double)this.phaseSpeed.getValue() / 1000.0;
        final double n2 = (double)this.instantWalkSpeed.getValue() / 10.0;
        final RayTraceResult rayTraceBlocks;
        if ((boolean)this.antiVoid.getValue() && PhaseWalk.mc.player.posY <= (int)this.antiVoidHeight.getValue() && ((rayTraceBlocks = PhaseWalk.mc.world.rayTraceBlocks(PhaseWalk.mc.player.getPositionVector(), new Vec3d(PhaseWalk.mc.player.posX, 0.0, PhaseWalk.mc.player.posZ), false, false, false)) == null || rayTraceBlocks.typeOfHit != RayTraceResult.Type.BLOCK)) {
            PhaseWalk.mc.player.setVelocity(0.0, 0.0, 0.0);
        }
        if (this.phaseCheck.getValue()) {
            if ((PhaseWalk.mc.gameSettings.keyBindForward.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindRight.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindLeft.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindBack.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindSneak.isKeyDown()) && ((!this.eChestCheck() && !PhaseWalk.mc.world.getBlockState(PlayerUtil.getPlayerPos()).getBlock().equals(Blocks.AIR)) || !PhaseWalk.mc.world.getBlockState(PlayerUtil.getPlayerPos().up()).getBlock().equals(Blocks.AIR))) {
                if (PhaseWalk.mc.player.collidedVertically && PhaseWalk.mc.gameSettings.keyBindSneak.isPressed() && PhaseWalk.mc.player.isSneaking()) {
                    final double[] motion = this.getMotion(n);
                    if ((boolean)this.downOnShift.getValue() && PhaseWalk.mc.player.collidedVertically && PhaseWalk.mc.gameSettings.keyBindSneak.isKeyDown()) {
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(PhaseWalk.mc.player.posX + motion[0], PhaseWalk.mc.player.posY - 0.0424, PhaseWalk.mc.player.posZ + motion[1], PhaseWalk.mc.player.rotationYaw, PhaseWalk.mc.player.rotationPitch, false));
                    }
                    else {
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(PhaseWalk.mc.player.posX + motion[0], PhaseWalk.mc.player.posY, PhaseWalk.mc.player.posZ + motion[1], PhaseWalk.mc.player.rotationYaw, PhaseWalk.mc.player.rotationPitch, false));
                    }
                    if (this.noClipMode.getValue() == NoClipMode.Fall) {
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(PhaseWalk.mc.player.posX, -1300.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.rotationYaw * -5.0f, PhaseWalk.mc.player.rotationPitch * -5.0f, true));
                    }
                    if (this.noClipMode.getValue() == NoClipMode.NoClip) {
                        PhaseWalk.mc.player.setVelocity(0.0, 0.0, 0.0);
                        if (PhaseWalk.mc.gameSettings.keyBindForward.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindBack.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindLeft.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindRight.isKeyDown()) {
                            final double[] directionSpeed = MathUtil.directionSpeed(0.05999999865889549);
                            PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX + directionSpeed[0], PhaseWalk.mc.player.posY, PhaseWalk.mc.player.posZ + directionSpeed[1], PhaseWalk.mc.player.onGround));
                            PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, 0.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                        }
                        if (PhaseWalk.mc.gameSettings.keyBindSneak.isKeyDown()) {
                            PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, PhaseWalk.mc.player.posY - 0.05999999865889549, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                            PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, 0.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                        }
                        if (PhaseWalk.mc.gameSettings.keyBindJump.isKeyDown()) {
                            PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, PhaseWalk.mc.player.posY + 0.05999999865889549, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                            PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, 0.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                        }
                    }
                    if (this.noClipMode.getValue() == NoClipMode.Bypass) {
                        PhaseWalk.mc.player.noClip = true;
                    }
                    if (this.fallPacket.getValue()) {
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)PhaseWalk.mc.player, CPacketEntityAction.Action.STOP_RIDING_JUMP));
                    }
                    if (this.sprintPacket.getValue()) {
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)PhaseWalk.mc.player, CPacketEntityAction.Action.START_SPRINTING));
                    }
                    if ((boolean)this.downOnShift.getValue() && PhaseWalk.mc.player.collidedVertically && PhaseWalk.mc.gameSettings.keyBindSneak.isKeyDown()) {
                        PhaseWalk.mc.player.setPosition(PhaseWalk.mc.player.posX + motion[0], PhaseWalk.mc.player.posY - 0.0424, PhaseWalk.mc.player.posZ + motion[1]);
                    }
                    else {
                        PhaseWalk.mc.player.setPosition(PhaseWalk.mc.player.posX + motion[0], PhaseWalk.mc.player.posY, PhaseWalk.mc.player.posZ + motion[1]);
                    }
                    PhaseWalk.mc.player.motionZ = 0.0;
                    PhaseWalk.mc.player.motionY = 0.0;
                    PhaseWalk.mc.player.motionX = 0.0;
                    PhaseWalk.mc.player.noClip = true;
                }
                if (!PhaseWalk.mc.player.collidedHorizontally || !(boolean)this.clip.getValue() || PhaseWalk.mc.gameSettings.keyBindForward.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindBack.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindLeft.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindRight.isKeyDown()) {}
                Label_2554: {
                    if (PhaseWalk.mc.player.collidedHorizontally && (boolean)this.stopMotion.getValue()) {
                        if (this.delay < (int)this.stopMotionDelay.getValue()) {
                            break Label_2554;
                        }
                    }
                    else if (!PhaseWalk.mc.player.collidedHorizontally) {
                        break Label_2554;
                    }
                    final double[] motion2 = this.getMotion(n);
                    if ((boolean)this.downOnShift.getValue() && PhaseWalk.mc.player.collidedVertically && PhaseWalk.mc.gameSettings.keyBindSneak.isKeyDown()) {
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(PhaseWalk.mc.player.posX + motion2[0], PhaseWalk.mc.player.posY - 0.1, PhaseWalk.mc.player.posZ + motion2[1], PhaseWalk.mc.player.rotationYaw, PhaseWalk.mc.player.rotationPitch, false));
                    }
                    else {
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(PhaseWalk.mc.player.posX + motion2[0], PhaseWalk.mc.player.posY, PhaseWalk.mc.player.posZ + motion2[1], PhaseWalk.mc.player.rotationYaw, PhaseWalk.mc.player.rotationPitch, false));
                    }
                    if (this.noClipMode.getValue() == NoClipMode.Fall) {
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(PhaseWalk.mc.player.posX, -1300.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.rotationYaw * -5.0f, PhaseWalk.mc.player.rotationPitch * -5.0f, true));
                    }
                    if (this.noClipMode.getValue() == NoClipMode.NoClip) {
                        PhaseWalk.mc.player.setVelocity(0.0, 0.0, 0.0);
                        if (PhaseWalk.mc.gameSettings.keyBindForward.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindBack.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindLeft.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindRight.isKeyDown()) {
                            final double[] directionSpeed2 = MathUtil.directionSpeed(0.05999999865889549);
                            PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX + directionSpeed2[0], PhaseWalk.mc.player.posY, PhaseWalk.mc.player.posZ + directionSpeed2[1], PhaseWalk.mc.player.onGround));
                            PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, 0.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                        }
                        if (PhaseWalk.mc.gameSettings.keyBindSneak.isKeyDown()) {
                            PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, PhaseWalk.mc.player.posY - 0.05999999865889549, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                            PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, 0.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                        }
                        if (PhaseWalk.mc.gameSettings.keyBindJump.isKeyDown()) {
                            PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, PhaseWalk.mc.player.posY + 0.05999999865889549, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                            PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, 0.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                        }
                    }
                    if (this.noClipMode.getValue() == NoClipMode.Bypass) {
                        PhaseWalk.mc.player.noClip = true;
                    }
                    if (this.fallPacket.getValue()) {
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)PhaseWalk.mc.player, CPacketEntityAction.Action.STOP_RIDING_JUMP));
                    }
                    if (this.sprintPacket.getValue()) {
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)PhaseWalk.mc.player, CPacketEntityAction.Action.START_SPRINTING));
                    }
                    if ((boolean)this.downOnShift.getValue() && PhaseWalk.mc.player.collidedVertically && PhaseWalk.mc.gameSettings.keyBindSneak.isKeyDown()) {
                        PhaseWalk.mc.player.setPosition(PhaseWalk.mc.player.posX + motion2[0], PhaseWalk.mc.player.posY - 0.1, PhaseWalk.mc.player.posZ + motion2[1]);
                    }
                    else {
                        PhaseWalk.mc.player.setPosition(PhaseWalk.mc.player.posX + motion2[0], PhaseWalk.mc.player.posY, PhaseWalk.mc.player.posZ + motion2[1]);
                    }
                    PhaseWalk.mc.player.motionZ = 0.0;
                    PhaseWalk.mc.player.motionY = 0.0;
                    PhaseWalk.mc.player.motionX = 0.0;
                    PhaseWalk.mc.player.noClip = true;
                    this.delay = 0;
                    return;
                }
                if (this.instantWalk.getValue()) {
                    final double[] directionSpeed3 = MathUtil.directionSpeed(n2);
                    PhaseWalk.mc.player.motionX = directionSpeed3[0];
                    PhaseWalk.mc.player.motionZ = directionSpeed3[1];
                }
            }
        }
        else if (PhaseWalk.mc.gameSettings.keyBindForward.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindRight.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindLeft.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindBack.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindSneak.isKeyDown()) {
            if (PhaseWalk.mc.player.collidedVertically && PhaseWalk.mc.gameSettings.keyBindSneak.isPressed() && PhaseWalk.mc.player.isSneaking()) {
                final double[] motion3 = this.getMotion(n);
                if ((boolean)this.downOnShift.getValue() && PhaseWalk.mc.player.collidedVertically && PhaseWalk.mc.gameSettings.keyBindSneak.isKeyDown()) {
                    PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(PhaseWalk.mc.player.posX + motion3[0], PhaseWalk.mc.player.posY - 0.0424, PhaseWalk.mc.player.posZ + motion3[1], PhaseWalk.mc.player.rotationYaw, PhaseWalk.mc.player.rotationPitch, false));
                }
                else {
                    PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(PhaseWalk.mc.player.posX + motion3[0], PhaseWalk.mc.player.posY, PhaseWalk.mc.player.posZ + motion3[1], PhaseWalk.mc.player.rotationYaw, PhaseWalk.mc.player.rotationPitch, false));
                }
                if (this.noClipMode.getValue() == NoClipMode.Fall) {
                    PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(PhaseWalk.mc.player.posX, -1300.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.rotationYaw * -5.0f, PhaseWalk.mc.player.rotationPitch * -5.0f, true));
                }
                if (this.noClipMode.getValue() == NoClipMode.NoClip) {
                    PhaseWalk.mc.player.setVelocity(0.0, 0.0, 0.0);
                    if (PhaseWalk.mc.gameSettings.keyBindForward.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindBack.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindLeft.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindRight.isKeyDown()) {
                        final double[] directionSpeed4 = MathUtil.directionSpeed(0.05999999865889549);
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX + directionSpeed4[0], PhaseWalk.mc.player.posY, PhaseWalk.mc.player.posZ + directionSpeed4[1], PhaseWalk.mc.player.onGround));
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, 0.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                    }
                    if (PhaseWalk.mc.gameSettings.keyBindSneak.isKeyDown()) {
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, PhaseWalk.mc.player.posY - 0.05999999865889549, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, 0.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                    }
                    if (PhaseWalk.mc.gameSettings.keyBindJump.isKeyDown()) {
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, PhaseWalk.mc.player.posY + 0.05999999865889549, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, 0.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                    }
                }
                if (this.noClipMode.getValue() == NoClipMode.Bypass) {
                    PhaseWalk.mc.player.noClip = true;
                }
                if (this.fallPacket.getValue()) {
                    PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)PhaseWalk.mc.player, CPacketEntityAction.Action.STOP_RIDING_JUMP));
                }
                if (this.sprintPacket.getValue()) {
                    PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)PhaseWalk.mc.player, CPacketEntityAction.Action.START_SPRINTING));
                }
                if ((boolean)this.downOnShift.getValue() && PhaseWalk.mc.player.collidedVertically && PhaseWalk.mc.gameSettings.keyBindSneak.isKeyDown()) {
                    PhaseWalk.mc.player.setPosition(PhaseWalk.mc.player.posX + motion3[0], PhaseWalk.mc.player.posY - 0.0424, PhaseWalk.mc.player.posZ + motion3[1]);
                }
                else {
                    PhaseWalk.mc.player.setPosition(PhaseWalk.mc.player.posX + motion3[0], PhaseWalk.mc.player.posY, PhaseWalk.mc.player.posZ + motion3[1]);
                }
                PhaseWalk.mc.player.motionZ = 0.0;
                PhaseWalk.mc.player.motionY = 0.0;
                PhaseWalk.mc.player.motionX = 0.0;
                PhaseWalk.mc.player.noClip = true;
            }
            Label_4826: {
                if (PhaseWalk.mc.player.collidedHorizontally && (boolean)this.stopMotion.getValue()) {
                    if (this.delay < (int)this.stopMotionDelay.getValue()) {
                        break Label_4826;
                    }
                }
                else if (!PhaseWalk.mc.player.collidedHorizontally) {
                    break Label_4826;
                }
                final double[] motion4 = this.getMotion(n);
                if ((boolean)this.downOnShift.getValue() && PhaseWalk.mc.player.collidedVertically && PhaseWalk.mc.gameSettings.keyBindSneak.isKeyDown()) {
                    PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(PhaseWalk.mc.player.posX + motion4[0], PhaseWalk.mc.player.posY - 0.1, PhaseWalk.mc.player.posZ + motion4[1], PhaseWalk.mc.player.rotationYaw, PhaseWalk.mc.player.rotationPitch, false));
                }
                else {
                    PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(PhaseWalk.mc.player.posX + motion4[0], PhaseWalk.mc.player.posY, PhaseWalk.mc.player.posZ + motion4[1], PhaseWalk.mc.player.rotationYaw, PhaseWalk.mc.player.rotationPitch, false));
                }
                if (this.noClipMode.getValue() == NoClipMode.Fall) {
                    PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(PhaseWalk.mc.player.posX, -1300.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.rotationYaw * -5.0f, PhaseWalk.mc.player.rotationPitch * -5.0f, true));
                }
                if (this.noClipMode.getValue() == NoClipMode.NoClip) {
                    PhaseWalk.mc.player.setVelocity(0.0, 0.0, 0.0);
                    if (PhaseWalk.mc.gameSettings.keyBindForward.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindBack.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindLeft.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindRight.isKeyDown()) {
                        final double[] directionSpeed5 = MathUtil.directionSpeed(0.05999999865889549);
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX + directionSpeed5[0], PhaseWalk.mc.player.posY, PhaseWalk.mc.player.posZ + directionSpeed5[1], PhaseWalk.mc.player.onGround));
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, 0.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                    }
                    if (PhaseWalk.mc.gameSettings.keyBindSneak.isKeyDown()) {
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, PhaseWalk.mc.player.posY - 0.05999999865889549, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, 0.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                    }
                    if (PhaseWalk.mc.gameSettings.keyBindJump.isKeyDown()) {
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, PhaseWalk.mc.player.posY + 0.05999999865889549, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, 0.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                    }
                }
                if (this.noClipMode.getValue() == NoClipMode.Bypass) {
                    PhaseWalk.mc.player.noClip = true;
                }
                if (this.fallPacket.getValue()) {
                    PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)PhaseWalk.mc.player, CPacketEntityAction.Action.STOP_RIDING_JUMP));
                }
                if (this.sprintPacket.getValue()) {
                    PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)PhaseWalk.mc.player, CPacketEntityAction.Action.START_SPRINTING));
                }
                if ((boolean)this.downOnShift.getValue() && PhaseWalk.mc.player.collidedVertically && PhaseWalk.mc.gameSettings.keyBindSneak.isKeyDown()) {
                    PhaseWalk.mc.player.setPosition(PhaseWalk.mc.player.posX + motion4[0], PhaseWalk.mc.player.posY - 0.1, PhaseWalk.mc.player.posZ + motion4[1]);
                }
                else {
                    PhaseWalk.mc.player.setPosition(PhaseWalk.mc.player.posX + motion4[0], PhaseWalk.mc.player.posY, PhaseWalk.mc.player.posZ + motion4[1]);
                }
                PhaseWalk.mc.player.motionZ = 0.0;
                PhaseWalk.mc.player.motionY = 0.0;
                PhaseWalk.mc.player.motionX = 0.0;
                PhaseWalk.mc.player.noClip = true;
                this.delay = 0;
                return;
            }
            if (this.instantWalk.getValue()) {
                final double[] directionSpeed6 = MathUtil.directionSpeed(n2);
                PhaseWalk.mc.player.motionX = directionSpeed6[0];
                PhaseWalk.mc.player.motionZ = directionSpeed6[1];
            }
        }
    }
    
    private boolean eChestCheck() {
        return String.valueOf(PhaseWalk.mc.player.posY).split("\\.")[1].equals("875");
    }
    
    private double[] getMotion(final double n) {
        float moveForward = PhaseWalk.mc.player.movementInput.moveForward;
        float moveStrafe = PhaseWalk.mc.player.movementInput.moveStrafe;
        float n2 = PhaseWalk.mc.player.prevRotationYaw + (PhaseWalk.mc.player.rotationYaw - PhaseWalk.mc.player.prevRotationYaw) * PhaseWalk.mc.getRenderPartialTicks();
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
        return new double[] { moveForward * n * -Math.sin(Math.toRadians(n2)) + moveStrafe * n * Math.cos(Math.toRadians(n2)), moveForward * n * Math.cos(Math.toRadians(n2)) - moveStrafe * n * -Math.sin(Math.toRadians(n2)) };
    }
    
    private double[] getDistance() {
        final float moveForward = PhaseWalk.mc.player.movementInput.moveForward;
        final float moveStrafe = PhaseWalk.mc.player.movementInput.moveStrafe;
        final float n = PhaseWalk.mc.player.prevRotationYaw + (PhaseWalk.mc.player.rotationYaw - PhaseWalk.mc.player.prevRotationYaw) * PhaseWalk.mc.getRenderPartialTicks();
        return new double[] { moveStrafe * (double)moveForward * -Math.sin(Math.toRadians(n)) + moveStrafe * (double)moveForward * Math.cos(Math.toRadians(n)), moveStrafe * (double)moveForward * Math.cos(Math.toRadians(n)) - moveStrafe * (double)moveForward * -Math.sin(Math.toRadians(n)) };
    }
    
    public PhaseWalk() {
        super("PhaseWalk", "Allows you to walk through blocks", Module.Category.MOVEMENT, true, false, false);
        this.phaseCheck = (Setting<Boolean>)this.register(new Setting("Only In Block", (Object)true));
        this.noClipMode = (Setting<NoClipMode>)this.register(new Setting("NoClipMode", (Object)NoClipMode.NoClip));
        this.fallPacket = (Setting<Boolean>)this.register(new Setting("Fall Packet", (Object)true));
        this.sprintPacket = (Setting<Boolean>)this.register(new Setting("Sprint Packet", (Object)true));
        this.instantWalk = (Setting<Boolean>)this.register(new Setting("Instant Walk", (Object)true));
        this.antiVoid = (Setting<Boolean>)this.register(new Setting("Anti Void", (Object)false));
        this.clip = (Setting<Boolean>)this.register(new Setting("Clip", (Object)true));
        this.antiVoidHeight = (Setting<Integer>)this.register(new Setting("Anti Void Height", (Object)5, (Object)1, (Object)100));
        this.instantWalkSpeed = (Setting<Double>)this.register(new Setting("Instant Speed", (Object)1.8, (Object)0.1, (Object)2.0, p0 -> (boolean)this.instantWalk.getValue()));
        this.phaseSpeed = (Setting<Double>)this.register(new Setting("Phase Walk Speed", (Object)42.4, (Object)0.1, (Object)70.0));
        this.downOnShift = (Setting<Boolean>)this.register(new Setting("Phase Down When Crouch", (Object)true));
        this.stopMotion = (Setting<Boolean>)this.register(new Setting("Attempt Clips", (Object)true));
        this.stopMotionDelay = (Setting<Integer>)this.register(new Setting("Attempt Clips Delay", (Object)5, (Object)0, (Object)20, p0 -> (boolean)this.stopMotion.getValue()));
        this.delay = 0;
    }
    
    public enum NoClipMode
    {
        Bypass, 
        NoClip, 
        None, 
        Fall;
    }
}
