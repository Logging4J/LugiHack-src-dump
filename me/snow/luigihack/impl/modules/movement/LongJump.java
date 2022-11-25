//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.movement;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraftforge.fml.common.gameevent.*;
import me.snow.luigihack.impl.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import me.snow.luigihack.*;
import net.minecraft.client.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import net.minecraft.init.*;
import net.minecraft.potion.*;
import java.util.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.block.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraft.network.play.server.*;

public class LongJump extends Module
{
    private /* synthetic */ double moveSpeed;
    private final /* synthetic */ Setting<Boolean> disableStrafe;
    private final /* synthetic */ Setting<Boolean> step;
    private final /* synthetic */ Setting<Mode> mode;
    private /* synthetic */ boolean beganJump;
    private /* synthetic */ int airTicks;
    private final /* synthetic */ Setting<Integer> timeout;
    private final /* synthetic */ Setting<Boolean> strafeOff;
    private /* synthetic */ double lastDist;
    private /* synthetic */ int groundTicks;
    private final /* synthetic */ Setting<Boolean> lagOff;
    private final /* synthetic */ Timer timer;
    private final /* synthetic */ Setting<Boolean> autoOff;
    private final /* synthetic */ Setting<Float> boost;
    private /* synthetic */ int stage;
    
    @SubscribeEvent
    public void onTickEvent(final TickEvent.ClientTickEvent clientTickEvent) {
        if (Feature.fullNullCheck() || clientTickEvent.phase != TickEvent.Phase.START) {
            return;
        }
        if (SpeedRewrite.getInstance().isOn() && (boolean)this.strafeOff.getValue()) {
            this.disable();
            return;
        }
        if (this.mode.getValue() == Mode.TICK) {
            this.doNormal(null);
        }
    }
    
    private void doNormal(final UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if ((boolean)this.autoOff.getValue() && this.beganJump && LongJump.mc.player.onGround) {
            this.disable();
            return;
        }
        switch ((Mode)this.mode.getValue()) {
            case VIRTUE: {
                if (LongJump.mc.player.moveForward != 0.0f || LongJump.mc.player.moveStrafing != 0.0f) {
                    final double n = LongJump.mc.player.posX - LongJump.mc.player.prevPosX;
                    final double n2 = LongJump.mc.player.posZ - LongJump.mc.player.prevPosZ;
                    this.lastDist = Math.sqrt(n * n + n2 * n2);
                    break;
                }
                updateWalkingPlayerEvent.setCanceled(true);
                break;
            }
            case TICK: {
                if (updateWalkingPlayerEvent != null) {
                    return;
                }
            }
            case DIRECT: {
                if (EntityUtil.isInLiquid()) {
                    break;
                }
                if (EntityUtil.isOnLiquid()) {
                    break;
                }
                final float n3 = LongJump.mc.player.rotationYaw + ((LongJump.mc.player.moveForward < 0.0f) ? 180 : 0) + ((LongJump.mc.player.moveStrafing > 0.0f) ? (-90.0f * ((LongJump.mc.player.moveForward < 0.0f) ? -0.5f : ((LongJump.mc.player.moveForward > 0.0f) ? 0.5f : 1.0f))) : 0.0f) - ((LongJump.mc.player.moveStrafing < 0.0f) ? (-90.0f * ((LongJump.mc.player.moveForward < 0.0f) ? -0.5f : ((LongJump.mc.player.moveForward > 0.0f) ? 0.5f : 1.0f))) : 0.0f);
                final float n4 = (float)Math.cos((n3 + 90.0f) * 3.141592653589793 / 180.0);
                final float n5 = (float)Math.sin((n3 + 90.0f) * 3.141592653589793 / 180.0);
                if (!LongJump.mc.player.collidedVertically) {
                    ++this.airTicks;
                    if (LongJump.mc.gameSettings.keyBindSneak.isKeyDown()) {
                        LongJump.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(0.0, 2.147483647E9, 0.0, false));
                    }
                    this.groundTicks = 0;
                    if (!LongJump.mc.player.collidedVertically) {
                        if (LongJump.mc.player.motionY == -0.07190068807140403) {
                            final EntityPlayerSP player = LongJump.mc.player;
                            player.motionY *= 0.3499999940395355;
                        }
                        else if (LongJump.mc.player.motionY == -0.10306193759436909) {
                            final EntityPlayerSP player2 = LongJump.mc.player;
                            player2.motionY *= 0.550000011920929;
                        }
                        else if (LongJump.mc.player.motionY == -0.13395038817442878) {
                            final EntityPlayerSP player3 = LongJump.mc.player;
                            player3.motionY *= 0.6700000166893005;
                        }
                        else if (LongJump.mc.player.motionY == -0.16635183030382) {
                            final EntityPlayerSP player4 = LongJump.mc.player;
                            player4.motionY *= 0.6899999976158142;
                        }
                        else if (LongJump.mc.player.motionY == -0.19088711097794803) {
                            final EntityPlayerSP player5 = LongJump.mc.player;
                            player5.motionY *= 0.7099999785423279;
                        }
                        else if (LongJump.mc.player.motionY == -0.21121925191528862) {
                            final EntityPlayerSP player6 = LongJump.mc.player;
                            player6.motionY *= 0.20000000298023224;
                        }
                        else if (LongJump.mc.player.motionY == -0.11979897632390576) {
                            final EntityPlayerSP player7 = LongJump.mc.player;
                            player7.motionY *= 0.9300000071525574;
                        }
                        else if (LongJump.mc.player.motionY == -0.18758479151225355) {
                            final EntityPlayerSP player8 = LongJump.mc.player;
                            player8.motionY *= 0.7200000286102295;
                        }
                        else if (LongJump.mc.player.motionY == -0.21075983825251726) {
                            final EntityPlayerSP player9 = LongJump.mc.player;
                            player9.motionY *= 0.7599999904632568;
                        }
                        if (LongJump.mc.player.motionY < -0.2 && LongJump.mc.player.motionY > -0.24) {
                            final EntityPlayerSP player10 = LongJump.mc.player;
                            player10.motionY *= 0.7;
                        }
                        if (LongJump.mc.player.motionY < -0.25 && LongJump.mc.player.motionY > -0.32) {
                            final EntityPlayerSP player11 = LongJump.mc.player;
                            player11.motionY *= 0.8;
                        }
                        if (LongJump.mc.player.motionY < -0.35 && LongJump.mc.player.motionY > -0.8) {
                            final EntityPlayerSP player12 = LongJump.mc.player;
                            player12.motionY *= 0.98;
                        }
                        if (LongJump.mc.player.motionY < -0.8 && LongJump.mc.player.motionY > -1.6) {
                            final EntityPlayerSP player13 = LongJump.mc.player;
                            player13.motionY *= 0.99;
                        }
                    }
                    LuigiHack.timerManager.setTimer(0.85f);
                    final double[] array = { 0.420606, 0.417924, 0.415258, 0.412609, 0.409977, 0.407361, 0.404761, 0.402178, 0.399611, 0.39706, 0.394525, 0.392, 0.3894, 0.38644, 0.383655, 0.381105, 0.37867, 0.37625, 0.37384, 0.37145, 0.369, 0.3666, 0.3642, 0.3618, 0.35945, 0.357, 0.354, 0.351, 0.348, 0.345, 0.342, 0.339, 0.336, 0.333, 0.33, 0.327, 0.324, 0.321, 0.318, 0.315, 0.312, 0.309, 0.307, 0.305, 0.303, 0.3, 0.297, 0.295, 0.293, 0.291, 0.289, 0.287, 0.285, 0.283, 0.281, 0.279, 0.277, 0.275, 0.273, 0.271, 0.269, 0.267, 0.265, 0.263, 0.261, 0.259, 0.257, 0.255, 0.253, 0.251, 0.249, 0.247, 0.245, 0.243, 0.241, 0.239, 0.237 };
                    if (LongJump.mc.gameSettings.keyBindForward.pressed) {
                        try {
                            LongJump.mc.player.motionX = n4 * array[this.airTicks - 1] * 3.0;
                            LongJump.mc.player.motionZ = n5 * array[this.airTicks - 1] * 3.0;
                            break;
                        }
                        catch (ArrayIndexOutOfBoundsException ex) {
                            return;
                        }
                    }
                    LongJump.mc.player.motionX = 0.0;
                    LongJump.mc.player.motionZ = 0.0;
                    break;
                }
                LuigiHack.timerManager.setTimer(1.0f);
                this.airTicks = 0;
                ++this.groundTicks;
                final EntityPlayerSP player14 = LongJump.mc.player;
                player14.motionX /= 13.0;
                final EntityPlayerSP player15 = LongJump.mc.player;
                player15.motionZ /= 13.0;
                if (this.groundTicks == 1) {
                    this.updatePosition(LongJump.mc.player.posX, LongJump.mc.player.posY, LongJump.mc.player.posZ);
                    this.updatePosition(LongJump.mc.player.posX + 0.0624, LongJump.mc.player.posY, LongJump.mc.player.posZ);
                    this.updatePosition(LongJump.mc.player.posX, LongJump.mc.player.posY + 0.419, LongJump.mc.player.posZ);
                    this.updatePosition(LongJump.mc.player.posX + 0.0624, LongJump.mc.player.posY, LongJump.mc.player.posZ);
                    this.updatePosition(LongJump.mc.player.posX, LongJump.mc.player.posY + 0.419, LongJump.mc.player.posZ);
                    break;
                }
                if (this.groundTicks <= 2) {
                    break;
                }
                this.groundTicks = 0;
                LongJump.mc.player.motionX = n4 * 0.3;
                LongJump.mc.player.motionZ = n5 * 0.3;
                LongJump.mc.player.motionY = 0.42399999499320984;
                this.beganJump = true;
                break;
            }
        }
    }
    
    private double getDistance(final EntityPlayer entityPlayer, final double n) {
        final List getCollisionBoxes = entityPlayer.world.getCollisionBoxes((Entity)entityPlayer, entityPlayer.getEntityBoundingBox().offset(0.0, -n, 0.0));
        if (getCollisionBoxes.isEmpty()) {
            return 0.0;
        }
        double maxY = 0.0;
        for (final AxisAlignedBB axisAlignedBB : getCollisionBoxes) {
            if (axisAlignedBB.maxY <= maxY) {
                continue;
            }
            maxY = axisAlignedBB.maxY;
        }
        return entityPlayer.posY - maxY;
    }
    
    private double getBaseMoveSpeed() {
        double n = 0.2873;
        if (LongJump.mc.player != null && LongJump.mc.player.isPotionActive(MobEffects.SPEED)) {
            n *= 1.0 + 0.2 * (Objects.requireNonNull(LongJump.mc.player.getActivePotionEffect(MobEffects.SPEED)).getAmplifier() + 1);
        }
        return n;
    }
    
    public void onDisable() {
        LuigiHack.timerManager.setTimer(1.0f);
    }
    
    @SubscribeEvent
    public void onUpdateWalkingPlayer(final UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (updateWalkingPlayerEvent.getStage() != 0) {
            return;
        }
        if (!this.timer.passedMs((long)(int)this.timeout.getValue())) {
            updateWalkingPlayerEvent.setCanceled(true);
            return;
        }
        this.doNormal(updateWalkingPlayerEvent);
    }
    
    @SubscribeEvent
    public void onMove(final MoveEvent moveEvent) {
        if (moveEvent.getStage() != 0) {
            return;
        }
        if (!this.timer.passedMs((long)(int)this.timeout.getValue())) {
            moveEvent.setX(0.0);
            moveEvent.setY(0.0);
            moveEvent.setZ(0.0);
            return;
        }
        if (this.step.getValue()) {
            LongJump.mc.player.stepHeight = 0.6f;
        }
        this.doVirtue(moveEvent);
    }
    
    private void setMoveSpeed(final MoveEvent moveEvent, final double n) {
        final MovementInput movementInput = LongJump.mc.player.movementInput;
        double n2 = movementInput.moveForward;
        double n3 = movementInput.moveStrafe;
        float rotationYaw = LongJump.mc.player.rotationYaw;
        if (n2 == 0.0 && n3 == 0.0) {
            moveEvent.setX(0.0);
            moveEvent.setZ(0.0);
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
            final double cos = Math.cos(Math.toRadians(rotationYaw + 90.0f));
            final double sin = Math.sin(Math.toRadians(rotationYaw + 90.0f));
            moveEvent.setX(n2 * n * cos + n3 * n * sin);
            moveEvent.setZ(n2 * n * sin - n3 * n * cos);
        }
    }
    
    private Block getBlock(final BlockPos blockPos) {
        return LongJump.mc.world.getBlockState(blockPos).getBlock();
    }
    
    private void doVirtue(final MoveEvent moveEvent) {
        if (this.mode.getValue() == Mode.VIRTUE && (LongJump.mc.player.moveForward != 0.0f || (LongJump.mc.player.moveStrafing != 0.0f && !EntityUtil.isOnLiquid() && !EntityUtil.isInLiquid()))) {
            if (this.stage == 0) {
                this.moveSpeed = (float)this.boost.getValue() * this.getBaseMoveSpeed();
            }
            else if (this.stage == 1) {
                moveEvent.setY(LongJump.mc.player.motionY = 0.42);
                this.moveSpeed *= 2.149;
            }
            else if (this.stage == 2) {
                this.moveSpeed = this.lastDist - 0.66 * (this.lastDist - this.getBaseMoveSpeed());
            }
            else {
                this.moveSpeed = this.lastDist - this.lastDist / 159.0;
            }
            this.moveSpeed = Math.max(this.getBaseMoveSpeed(), this.moveSpeed);
            this.setMoveSpeed(moveEvent, this.moveSpeed);
            final List getCollisionBoxes = LongJump.mc.world.getCollisionBoxes((Entity)LongJump.mc.player, LongJump.mc.player.getEntityBoundingBox().offset(0.0, LongJump.mc.player.motionY, 0.0));
            final List getCollisionBoxes2 = LongJump.mc.world.getCollisionBoxes((Entity)LongJump.mc.player, LongJump.mc.player.getEntityBoundingBox().offset(0.0, -0.4, 0.0));
            if (!LongJump.mc.player.collidedVertically && (getCollisionBoxes.size() > 0 || getCollisionBoxes2.size() > 0)) {
                moveEvent.setY(LongJump.mc.player.motionY = -0.001);
            }
            ++this.stage;
        }
        else if (this.stage > 0) {
            this.disable();
        }
    }
    
    public LongJump() {
        super("LongJump", "Jumps long", Module.Category.MOVEMENT, true, false, false);
        this.timeout = (Setting<Integer>)this.register(new Setting("TimeOut", (Object)2000, (Object)0, (Object)5000));
        this.boost = (Setting<Float>)this.register(new Setting("Boost", (Object)4.48f, (Object)1.0f, (Object)20.0f));
        this.mode = (Setting<Mode>)this.register(new Setting("Mode", (Object)Mode.DIRECT));
        this.lagOff = (Setting<Boolean>)this.register(new Setting("LagOff", (Object)false));
        this.autoOff = (Setting<Boolean>)this.register(new Setting("AutoOff", (Object)false));
        this.disableStrafe = (Setting<Boolean>)this.register(new Setting("DisableStrafe", (Object)false));
        this.strafeOff = (Setting<Boolean>)this.register(new Setting("StrafeOff", (Object)false));
        this.step = (Setting<Boolean>)this.register(new Setting("SetStep", (Object)false));
        this.timer = new Timer();
        this.beganJump = false;
    }
    
    public void onEnable() {
        this.timer.reset();
        this.groundTicks = 0;
        this.stage = 0;
        this.beganJump = false;
        if (SpeedRewrite.getInstance().isOn() && (boolean)this.disableStrafe.getValue()) {
            SpeedRewrite.getInstance().disable();
        }
    }
    
    private void updatePosition(final double n, final double n2, final double n3) {
        LongJump.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(n, n2, n3, LongJump.mc.player.onGround));
    }
    
    @SubscribeEvent
    public void onPacketReceive(final PacketEvent.Receive receive) {
        if ((boolean)this.lagOff.getValue() && receive.getPacket() instanceof SPacketPlayerPosLook) {
            this.disable();
        }
    }
    
    public enum Mode
    {
        DIRECT, 
        VIRTUE, 
        TICK;
    }
}
