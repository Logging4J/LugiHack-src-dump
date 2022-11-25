//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.movement;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.network.play.client.*;
import net.minecraft.entity.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.snow.luigihack.*;
import net.minecraft.init.*;
import me.snow.luigihack.mixin.mixins.accessors.*;
import net.minecraft.network.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.network.play.server.*;
import me.snow.luigihack.api.event.events.*;
import me.snow.luigihack.impl.modules.player.*;

public class SpeedRewrite extends Module
{
    private /* synthetic */ int groundStage;
    public static /* synthetic */ SpeedRewrite INSTANCE;
    private /* synthetic */ boolean offsetPackets;
    private /* synthetic */ boolean accelerate;
    public /* synthetic */ Setting<BaseSpeed> speed;
    private /* synthetic */ int strictTicks;
    private final /* synthetic */ Setting<Boolean> strictMotion;
    private final /* synthetic */ Setting<Boolean> timer;
    private /* synthetic */ int boostTicks;
    private /* synthetic */ double moveSpeed;
    private /* synthetic */ double boostSpeed;
    public final /* synthetic */ Setting<Boolean> noLiquids;
    public /* synthetic */ Setting<Mode> mode;
    public final /* synthetic */ Setting<Boolean> noWeb;
    private /* synthetic */ double latestMoveSpeed;
    private /* synthetic */ int strafeStage;
    private final /* synthetic */ Setting<Boolean> potionFactor;
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        if (send.getPacket() instanceof CPacketEntityAction && (((CPacketEntityAction)send.getPacket()).getAction().equals((Object)CPacketEntityAction.Action.STOP_SPRINTING) || ((CPacketEntityAction)send.getPacket()).getAction().equals((Object)CPacketEntityAction.Action.START_SNEAKING)) && (boolean)this.strictMotion.getValue()) {
            send.setCanceled(true);
        }
        if (send.getPacket() instanceof CPacketPlayer && ((ICPacketPlayer)send.getPacket()).isMoving() && this.offsetPackets) {
            ((ICPacketPlayer)send.getPacket()).setY(((CPacketPlayer)send.getPacket()).getY(0.0) + ((SpeedRewrite.mc.world.getCollisionBoxes((Entity)SpeedRewrite.mc.player, SpeedRewrite.mc.player.getEntityBoundingBox().offset(0.0, 0.21, 0.0)).size() > 0) ? 2 : 4));
            this.offsetPackets = false;
        }
    }
    
    @SubscribeEvent
    public void onMove(final MoveEvent moveEvent) {
        if ((boolean)this.noLiquids.getValue() && (Step.mc.player.isInWater() || Step.mc.player.isInLava())) {
            this.resetProcess();
            return;
        }
        if ((boolean)this.noWeb.getValue() && NoSlowDown.mc.player.isInWeb) {
            this.resetProcess();
            return;
        }
        if (SpeedRewrite.mc.player.isOnLadder() || SpeedRewrite.mc.player.capabilities.isFlying || SpeedRewrite.mc.player.isElytraFlying() || SpeedRewrite.mc.player.fallDistance > 2.0f) {
            this.resetProcess();
            return;
        }
        if ((boolean)this.noLiquids.getValue() && (Step.mc.player.isInWater() || Step.mc.player.isInLava())) {
            return;
        }
        if (moveEvent.getStage() != 0 || this.shouldReturn()) {
            return;
        }
        if (SpeedRewrite.mc.player.isSneaking()) {
            return;
        }
        LuigiHack.timerManager.reset();
        double n = 0.2873;
        if (((BaseSpeed)this.speed.getValue()).equals(BaseSpeed.OLD)) {
            n = 0.272;
        }
        if (this.potionFactor.getValue()) {
            if (SpeedRewrite.mc.player.isPotionActive(MobEffects.SPEED)) {
                n *= 1.0 + 0.2 * (SpeedRewrite.mc.player.getActivePotionEffect(MobEffects.SPEED).getAmplifier() + 1.0);
            }
            if (SpeedRewrite.mc.player.isPotionActive(MobEffects.SLOWNESS)) {
                n /= 1.0 + 0.2 * (SpeedRewrite.mc.player.getActivePotionEffect(MobEffects.SLOWNESS).getAmplifier() + 1.0);
            }
        }
        if ((boolean)this.strictMotion.getValue() && (!SpeedRewrite.mc.player.isSprinting() || !((IEntityPlayerSP)SpeedRewrite.mc.player).getServerSprintState()) && SpeedRewrite.mc.getConnection() != null) {
            SpeedRewrite.mc.getConnection().getNetworkManager().sendPacket((Packet)new CPacketEntityAction((Entity)SpeedRewrite.mc.player, CPacketEntityAction.Action.START_SPRINTING));
        }
        switch ((Mode)this.mode.getValue()) {
            case ONGROUND: {
                if (SpeedRewrite.mc.player.onGround && MotionUtil.isMoving()) {
                    if (this.groundStage == 2) {
                        this.offsetPackets = true;
                        this.moveSpeed *= 2.149;
                        this.groundStage = 3;
                    }
                    else if (this.groundStage == 3) {
                        this.moveSpeed = this.latestMoveSpeed - 0.66 * (this.latestMoveSpeed - n);
                        this.groundStage = 2;
                    }
                    if (SpeedRewrite.mc.world.getCollisionBoxes((Entity)SpeedRewrite.mc.player, SpeedRewrite.mc.player.getEntityBoundingBox().offset(0.0, 0.21, 0.0)).size() > 0 || SpeedRewrite.mc.player.collidedVertically) {
                        this.groundStage = 1;
                    }
                }
                this.moveSpeed = Math.max(this.moveSpeed, n);
                float moveForward = SpeedRewrite.mc.player.movementInput.moveForward;
                float moveStrafe = SpeedRewrite.mc.player.movementInput.moveStrafe;
                float n2 = SpeedRewrite.mc.player.prevRotationYaw + (SpeedRewrite.mc.player.rotationYaw - SpeedRewrite.mc.player.prevRotationYaw) * SpeedRewrite.mc.getRenderPartialTicks();
                if (!MotionUtil.isMoving()) {
                    moveEvent.setX(0.0);
                    moveEvent.setZ(0.0);
                }
                else if (moveForward != 0.0f) {
                    if (moveStrafe > 0.0f) {
                        n2 += ((moveForward > 0.0f) ? -45.0f : 45.0f);
                    }
                    else if (moveStrafe < 0.0f) {
                        n2 += ((moveForward > 0.0f) ? 45.0f : -45.0f);
                    }
                    moveStrafe = 0.0f;
                    if (moveForward > 0.0f) {
                        moveForward = 1.0f;
                    }
                    else if (moveForward < 0.0f) {
                        moveForward = -1.0f;
                    }
                }
                final double cos = Math.cos(Math.toRadians(n2));
                final double n3 = -Math.sin(Math.toRadians(n2));
                moveEvent.setX(moveForward * this.moveSpeed * n3 + moveStrafe * this.moveSpeed * cos);
                moveEvent.setZ(moveForward * this.moveSpeed * cos - moveStrafe * this.moveSpeed * n3);
                break;
            }
            case STRAFE: {
                if (MotionUtil.isMoving()) {
                    if (this.timer.getValue()) {
                        LuigiHack.timerManager.setTimer(1.088f);
                    }
                    if (this.strafeStage == 1) {
                        this.moveSpeed = 1.35 * n - 0.01;
                    }
                    else if (this.strafeStage == 2) {
                        double motionY = 0.3999999463558197;
                        if ((boolean)this.potionFactor.getValue() && SpeedRewrite.mc.player.isPotionActive(MobEffects.JUMP_BOOST)) {
                            motionY += (SpeedRewrite.mc.player.getActivePotionEffect(MobEffects.JUMP_BOOST).getAmplifier() + 1.0) * 0.1;
                        }
                        moveEvent.setY(SpeedRewrite.mc.player.motionY = motionY);
                        double n4 = 1.395;
                        if (this.accelerate) {
                            n4 = 1.6835;
                        }
                        this.moveSpeed *= n4;
                    }
                    else if (this.strafeStage == 3) {
                        this.moveSpeed = this.latestMoveSpeed - 0.66 * (this.latestMoveSpeed - n);
                        this.accelerate = !this.accelerate;
                    }
                    else {
                        if ((SpeedRewrite.mc.world.getCollisionBoxes((Entity)SpeedRewrite.mc.player, SpeedRewrite.mc.player.getEntityBoundingBox().offset(0.0, SpeedRewrite.mc.player.motionY, 0.0)).size() > 0 || SpeedRewrite.mc.player.collidedVertically) && this.strafeStage > 0) {
                            this.strafeStage = (MotionUtil.isMoving() ? 1 : 0);
                        }
                        this.moveSpeed = this.latestMoveSpeed - this.latestMoveSpeed / 159.0;
                    }
                    this.moveSpeed = Math.max(this.moveSpeed, n);
                    float moveForward2 = SpeedRewrite.mc.player.movementInput.moveForward;
                    float moveStrafe2 = SpeedRewrite.mc.player.movementInput.moveStrafe;
                    float n5 = SpeedRewrite.mc.player.prevRotationYaw + (SpeedRewrite.mc.player.rotationYaw - SpeedRewrite.mc.player.prevRotationYaw) * SpeedRewrite.mc.getRenderPartialTicks();
                    if (!MotionUtil.isMoving()) {
                        moveEvent.setX(0.0);
                        moveEvent.setZ(0.0);
                    }
                    else if (moveForward2 != 0.0f) {
                        if (moveStrafe2 > 0.0f) {
                            n5 += ((moveForward2 > 0.0f) ? -45.0f : 45.0f);
                        }
                        else if (moveStrafe2 < 0.0f) {
                            n5 += ((moveForward2 > 0.0f) ? 45.0f : -45.0f);
                        }
                        moveStrafe2 = 0.0f;
                        if (moveForward2 > 0.0f) {
                            moveForward2 = 1.0f;
                        }
                        else if (moveForward2 < 0.0f) {
                            moveForward2 = -1.0f;
                        }
                    }
                    final double cos2 = Math.cos(Math.toRadians(n5));
                    final double n6 = -Math.sin(Math.toRadians(n5));
                    moveEvent.setX(moveForward2 * this.moveSpeed * n6 + moveStrafe2 * this.moveSpeed * cos2);
                    moveEvent.setZ(moveForward2 * this.moveSpeed * cos2 - moveStrafe2 * this.moveSpeed * n6);
                    ++this.strafeStage;
                    break;
                }
                break;
            }
            case STRAFESTRICT: {
                if (MotionUtil.isMoving()) {
                    if (this.timer.getValue()) {
                        LuigiHack.timerManager.setTimer(1.088f);
                    }
                    if (this.strafeStage == 1) {
                        this.moveSpeed = 1.35 * n - 0.01;
                    }
                    else if (this.strafeStage == 2) {
                        double motionY2 = 0.3999999463558197;
                        if (this.strictMotion.getValue()) {
                            motionY2 = 0.41999998688697815;
                        }
                        if ((boolean)this.potionFactor.getValue() && SpeedRewrite.mc.player.isPotionActive(MobEffects.JUMP_BOOST)) {
                            motionY2 += (SpeedRewrite.mc.player.getActivePotionEffect(MobEffects.JUMP_BOOST).getAmplifier() + 1.0) * 0.1;
                        }
                        moveEvent.setY(SpeedRewrite.mc.player.motionY = motionY2);
                        this.moveSpeed *= 2.149;
                    }
                    else if (this.strafeStage == 3) {
                        this.moveSpeed = this.latestMoveSpeed - 0.66 * (this.latestMoveSpeed - n);
                    }
                    else {
                        if ((SpeedRewrite.mc.world.getCollisionBoxes((Entity)SpeedRewrite.mc.player, SpeedRewrite.mc.player.getEntityBoundingBox().offset(0.0, SpeedRewrite.mc.player.motionY, 0.0)).size() > 0 || SpeedRewrite.mc.player.collidedVertically) && this.strafeStage > 0) {
                            this.strafeStage = (MotionUtil.isMoving() ? 1 : 0);
                        }
                        this.moveSpeed = this.latestMoveSpeed - this.latestMoveSpeed / 159.0;
                    }
                    this.moveSpeed = Math.max(this.moveSpeed, n);
                    double n7 = 0.465;
                    double n8 = 0.44;
                    if (this.potionFactor.getValue()) {
                        if (SpeedRewrite.mc.player.isPotionActive(MobEffects.SPEED)) {
                            final double n9 = SpeedRewrite.mc.player.getActivePotionEffect(MobEffects.SPEED).getAmplifier();
                            n7 *= 1.0 + 0.2 * (n9 + 1.0);
                            n8 *= 1.0 + 0.2 * (n9 + 1.0);
                        }
                        if (SpeedRewrite.mc.player.isPotionActive(MobEffects.SLOWNESS)) {
                            final double n10 = SpeedRewrite.mc.player.getActivePotionEffect(MobEffects.SLOWNESS).getAmplifier();
                            n7 /= 1.0 + 0.2 * (n10 + 1.0);
                            n8 /= 1.0 + 0.2 * (n10 + 1.0);
                        }
                    }
                    this.moveSpeed = Math.min(this.moveSpeed, (this.strictTicks > 25) ? n7 : n8);
                    ++this.strictTicks;
                    if (this.strictTicks > 50) {
                        this.strictTicks = 0;
                    }
                    float moveForward3 = SpeedRewrite.mc.player.movementInput.moveForward;
                    float moveStrafe3 = SpeedRewrite.mc.player.movementInput.moveStrafe;
                    float n11 = SpeedRewrite.mc.player.prevRotationYaw + (SpeedRewrite.mc.player.rotationYaw - SpeedRewrite.mc.player.prevRotationYaw) * SpeedRewrite.mc.getRenderPartialTicks();
                    if (!MotionUtil.isMoving()) {
                        moveEvent.setX(0.0);
                        moveEvent.setZ(0.0);
                    }
                    else if (moveForward3 != 0.0f) {
                        if (moveStrafe3 >= 1.0f) {
                            n11 += ((moveForward3 > 0.0f) ? -45 : 45);
                            moveStrafe3 = 0.0f;
                        }
                        else if (moveStrafe3 <= -1.0f) {
                            n11 += ((moveForward3 > 0.0f) ? 45 : -45);
                            moveStrafe3 = 0.0f;
                        }
                        if (moveForward3 > 0.0f) {
                            moveForward3 = 1.0f;
                        }
                        else if (moveForward3 < 0.0f) {
                            moveForward3 = -1.0f;
                        }
                    }
                    final double cos3 = Math.cos(Math.toRadians(n11));
                    final double n12 = -Math.sin(Math.toRadians(n11));
                    moveEvent.setX(moveForward3 * this.moveSpeed * n12 + moveStrafe3 * this.moveSpeed * cos3);
                    moveEvent.setZ(moveForward3 * this.moveSpeed * cos3 - moveStrafe3 * this.moveSpeed * n12);
                    ++this.strafeStage;
                    break;
                }
                break;
            }
            case GROUNDSTRAFE: {
                this.moveSpeed = n;
                if (!SpeedRewrite.mc.player.isSprinting()) {
                    this.moveSpeed *= 0.7692307692;
                }
                else if (SpeedRewrite.mc.player.isSneaking()) {
                    this.moveSpeed *= 0.3;
                }
                float moveForward4 = SpeedRewrite.mc.player.movementInput.moveForward;
                float moveStrafe4 = SpeedRewrite.mc.player.movementInput.moveStrafe;
                float n13 = SpeedRewrite.mc.player.prevRotationYaw + (SpeedRewrite.mc.player.rotationYaw - SpeedRewrite.mc.player.prevRotationYaw) * SpeedRewrite.mc.getRenderPartialTicks();
                if (!MotionUtil.isMoving()) {
                    moveEvent.setX(0.0);
                    moveEvent.setZ(0.0);
                }
                if (moveForward4 != 0.0f) {
                    if (moveStrafe4 > 0.0f) {
                        n13 += ((moveForward4 > 0.0f) ? -45 : 45);
                    }
                    else if (moveStrafe4 < 0.0f) {
                        n13 += ((moveForward4 > 0.0f) ? 45 : -45);
                    }
                    moveStrafe4 = 0.0f;
                    if (moveForward4 > 0.0f) {
                        moveForward4 = 1.0f;
                    }
                    else if (moveForward4 < 0.0f) {
                        moveForward4 = -1.0f;
                    }
                }
                final double cos4 = Math.cos(Math.toRadians(n13));
                final double n14 = -Math.sin(Math.toRadians(n13));
                moveEvent.setX(moveForward4 * this.moveSpeed * n14 + moveStrafe4 * this.moveSpeed * cos4);
                moveEvent.setZ(moveForward4 * this.moveSpeed * cos4 - moveStrafe4 * this.moveSpeed * n14);
                break;
            }
        }
    }
    
    public static SpeedRewrite getInstance() {
        if (SpeedRewrite.INSTANCE == null) {
            SpeedRewrite.INSTANCE = new SpeedRewrite();
        }
        return SpeedRewrite.INSTANCE;
    }
    
    public String getDisplayInfo() {
        switch ((Mode)this.mode.getValue()) {
            case STRAFE: {
                return "Strafe";
            }
            case STRAFESTRICT: {
                return "StrafeStrict";
            }
            case GROUNDSTRAFE: {
                return "GroundStrafe";
            }
            case ONGROUND: {
                return "OnGround";
            }
            default: {
                return null;
            }
        }
    }
    
    @SubscribeEvent
    public void onPacketReceive(final PacketEvent.Receive receive) {
        if (receive.getPacket() instanceof SPacketPlayerPosLook) {
            this.resetProcess();
        }
        if (receive.getPacket() instanceof SPacketExplosion) {
            this.boostSpeed = Math.sqrt(StrictMath.pow(((SPacketExplosion)receive.getPacket()).getMotionX() / 8000.0f, 2.0) + StrictMath.pow(((SPacketExplosion)receive.getPacket()).getMotionX() / 8000.0f, 2.0));
            this.boostTicks = 0;
        }
        if (receive.getPacket() instanceof SPacketEntityVelocity && ((SPacketEntityVelocity)receive.getPacket()).getEntityID() == SpeedRewrite.mc.player.getEntityId()) {
            this.boostSpeed = Math.sqrt(StrictMath.pow(((SPacketEntityVelocity)receive.getPacket()).getMotionX() / 8000.0f, 2.0) + StrictMath.pow(((SPacketEntityVelocity)receive.getPacket()).getMotionX() / 8000.0f, 2.0));
            this.boostTicks = 0;
        }
    }
    
    @SubscribeEvent
    public void onUpdateWalkingPlayer(final UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (updateWalkingPlayerEvent.getStage() == 0) {
            this.latestMoveSpeed = Math.sqrt(StrictMath.pow(SpeedRewrite.mc.player.posX - SpeedRewrite.mc.player.prevPosX, 2.0) + StrictMath.pow(SpeedRewrite.mc.player.posZ - SpeedRewrite.mc.player.prevPosZ, 2.0));
        }
    }
    
    private boolean shouldReturn() {
        return LuigiHack.moduleManager.isModuleEnabled((Class)Freecam.class) || LuigiHack.moduleManager.isModuleEnabled((Class)ElytraFlight.class) || LuigiHack.moduleManager.isModuleEnabled((Class)Flight.class) || LuigiHack.moduleManager.isModuleEnabled((Class)PacketFly.class);
    }
    
    public void resetProcess() {
        this.strafeStage = 4;
        this.groundStage = 2;
        this.moveSpeed = 0.0;
        this.latestMoveSpeed = 0.0;
        this.boostSpeed = 0.0;
        this.strictTicks = 0;
        this.boostTicks = 0;
        this.accelerate = false;
        this.offsetPackets = false;
        LuigiHack.timerManager.reset();
    }
    
    public void onDisable() {
        this.resetProcess();
        LuigiHack.timerManager.reset();
    }
    
    public void onEnable() {
        LuigiHack.timerManager.reset();
        this.strafeStage = 4;
        this.groundStage = 2;
    }
    
    public SpeedRewrite() {
        super("Speed", "AirControl etc.", Module.Category.MOVEMENT, true, false, false);
        this.mode = (Setting<Mode>)this.register(new Setting("Mode", (Object)Mode.STRAFE));
        this.speed = (Setting<BaseSpeed>)this.register(new Setting("Speed", (Object)BaseSpeed.NORMAL));
        this.potionFactor = (Setting<Boolean>)this.register(new Setting("PotionCheck", (Object)true));
        this.strictMotion = (Setting<Boolean>)this.register(new Setting("StrictMotion", (Object)false, p0 -> this.mode.getValue() == Mode.STRAFESTRICT));
        this.timer = (Setting<Boolean>)this.register(new Setting("TimerBoost", (Object)false));
        this.noWeb = (Setting<Boolean>)this.register(new Setting("NoWeb", (Object)true));
        this.noLiquids = (Setting<Boolean>)this.register(new Setting("NoSpeedInWater", (Object)true));
        this.strafeStage = 4;
        this.groundStage = 2;
        SpeedRewrite.INSTANCE = this;
    }
    
    public enum Mode
    {
        STRAFESTRICT, 
        ONGROUND, 
        STRAFE, 
        GROUNDSTRAFE;
    }
    
    public enum BaseSpeed
    {
        OLD, 
        NORMAL;
    }
}
