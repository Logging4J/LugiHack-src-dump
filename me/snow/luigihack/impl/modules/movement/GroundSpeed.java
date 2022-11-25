//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.movement;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.*;
import net.minecraft.client.entity.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.init.*;
import net.minecraft.potion.*;
import java.util.*;
import net.minecraft.entity.*;
import me.snow.luigihack.api.util.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraft.util.*;

public class GroundSpeed extends Module
{
    private /* synthetic */ float move;
    public /* synthetic */ Setting<Boolean> stepyport;
    public /* synthetic */ Setting<Double> blocked;
    private /* synthetic */ double bounceHeight;
    public /* synthetic */ boolean changeY;
    public /* synthetic */ double startY;
    public /* synthetic */ Setting<Double> speed;
    private /* synthetic */ double highChainVal;
    public /* synthetic */ boolean antiShake;
    private /* synthetic */ float stepheight;
    private static /* synthetic */ GroundSpeed INSTANCE;
    public /* synthetic */ Setting<Boolean> noShake;
    public /* synthetic */ double minY;
    public /* synthetic */ Setting<Boolean> useTimerAA;
    private /* synthetic */ int vanillaCounter;
    public /* synthetic */ Setting<Mode> mode;
    private /* synthetic */ double lowChainVal;
    public /* synthetic */ Setting<Double> unblocked;
    private final /* synthetic */ Setting<Double> yPortSpeed;
    public /* synthetic */ Setting<Boolean> useTimer;
    private /* synthetic */ Timer timer;
    public /* synthetic */ Setting<Double> zeroSpeed;
    public /* synthetic */ Setting<Boolean> strafeJump;
    private /* synthetic */ boolean oneTime;
    
    private boolean shouldReturn() {
        return LuigiHack.moduleManager.isModuleEnabled("Freecam") || LuigiHack.moduleManager.isModuleEnabled("Phase") || LuigiHack.moduleManager.isModuleEnabled("ElytraFlight") || LuigiHack.moduleManager.isModuleEnabled("Strafe") || LuigiHack.moduleManager.isModuleEnabled("Flight");
    }
    
    private void doAccel() {
        this.bounceHeight = 0.4;
        this.move = 0.26f;
        if (GroundSpeed.mc.player.onGround) {
            this.startY = GroundSpeed.mc.player.posY;
        }
        if (EntityUtil.getEntitySpeed((Entity)GroundSpeed.mc.player) <= 1.0) {
            this.lowChainVal = 1.0;
            this.highChainVal = 1.0;
        }
        if (EntityUtil.isEntityMoving((Entity)GroundSpeed.mc.player) && !GroundSpeed.mc.player.collidedHorizontally && !BlockUtil.isBlockAboveEntitySolid((Entity)GroundSpeed.mc.player) && BlockUtil.isBlockBelowEntitySolid((Entity)GroundSpeed.mc.player)) {
            this.oneTime = true;
            this.antiShake = ((boolean)this.noShake.getValue() && GroundSpeed.mc.player.getRidingEntity() == null);
            final boolean nextBoolean = new Random().nextBoolean();
            if (GroundSpeed.mc.player.posY >= this.startY + this.bounceHeight) {
                GroundSpeed.mc.player.motionY = -this.bounceHeight;
                ++this.lowChainVal;
                if (this.lowChainVal == 1.0) {
                    this.move = 0.075f;
                }
                if (this.lowChainVal == 2.0) {
                    this.move = 0.175f;
                }
                if (this.lowChainVal == 3.0) {
                    this.move = 0.275f;
                }
                if (this.lowChainVal == 4.0) {
                    this.move = 0.35f;
                }
                if (this.lowChainVal == 5.0) {
                    this.move = 0.375f;
                }
                if (this.lowChainVal == 6.0) {
                    this.move = 0.4f;
                }
                if (this.lowChainVal == 7.0) {
                    this.move = 0.425f;
                }
                if (this.lowChainVal == 8.0) {
                    this.move = 0.45f;
                }
                if (this.lowChainVal == 9.0) {
                    this.move = 0.475f;
                }
                if (this.lowChainVal == 10.0) {
                    this.move = 0.5f;
                }
                if (this.lowChainVal == 11.0) {
                    this.move = 0.5f;
                }
                if (this.lowChainVal == 12.0) {
                    this.move = 0.525f;
                }
                if (this.lowChainVal == 13.0) {
                    this.move = 0.525f;
                }
                if (this.lowChainVal == 14.0) {
                    this.move = 0.535f;
                }
                if (this.lowChainVal == 15.0) {
                    this.move = 0.535f;
                }
                if (this.lowChainVal == 16.0) {
                    this.move = 0.545f;
                }
                if (this.lowChainVal >= 17.0) {
                    this.move = 0.545f;
                }
                if (this.useTimer.getValue()) {
                    LuigiHack.timerManager.setTimer(1.0f);
                }
            }
            if (GroundSpeed.mc.player.posY == this.startY) {
                GroundSpeed.mc.player.motionY = this.bounceHeight;
                ++this.highChainVal;
                if (this.highChainVal == 1.0) {
                    this.move = 0.075f;
                }
                if (this.highChainVal == 2.0) {
                    this.move = 0.175f;
                }
                if (this.highChainVal == 3.0) {
                    this.move = 0.375f;
                }
                if (this.highChainVal == 4.0) {
                    this.move = 0.6f;
                }
                if (this.highChainVal == 5.0) {
                    this.move = 0.775f;
                }
                if (this.highChainVal == 6.0) {
                    this.move = 0.825f;
                }
                if (this.highChainVal == 7.0) {
                    this.move = 0.875f;
                }
                if (this.highChainVal == 8.0) {
                    this.move = 0.925f;
                }
                if (this.highChainVal == 9.0) {
                    this.move = 0.975f;
                }
                if (this.highChainVal == 10.0) {
                    this.move = 1.05f;
                }
                if (this.highChainVal == 11.0) {
                    this.move = 1.1f;
                }
                if (this.highChainVal == 12.0) {
                    this.move = 1.1f;
                }
                if (this.highChainVal == 13.0) {
                    this.move = 1.15f;
                }
                if (this.highChainVal == 14.0) {
                    this.move = 1.15f;
                }
                if (this.highChainVal == 15.0) {
                    this.move = 1.175f;
                }
                if (this.highChainVal == 16.0) {
                    this.move = 1.175f;
                }
                if (this.highChainVal >= 17.0) {
                    this.move = 1.175f;
                }
                if (this.useTimer.getValue()) {
                    if (nextBoolean) {
                        LuigiHack.timerManager.setTimer(1.3f);
                    }
                    else {
                        LuigiHack.timerManager.setTimer(1.0f);
                    }
                }
            }
            EntityUtil.moveEntityStrafe((double)this.move, (Entity)GroundSpeed.mc.player);
        }
        else {
            if (this.oneTime) {
                GroundSpeed.mc.player.motionY = -0.1;
                this.oneTime = false;
            }
            this.antiShake = false;
            this.highChainVal = 0.0;
            this.lowChainVal = 0.0;
            this.speedOff();
        }
    }
    
    private boolean vanilla() {
        return GroundSpeed.mc.player.onGround;
    }
    
    private void doOnground() {
        this.bounceHeight = 0.4;
        this.move = 0.26f;
        if (GroundSpeed.mc.player.onGround) {
            this.startY = GroundSpeed.mc.player.posY;
        }
        if (EntityUtil.getEntitySpeed((Entity)GroundSpeed.mc.player) <= 1.0) {
            this.lowChainVal = 1.0;
            this.highChainVal = 1.0;
        }
        if (EntityUtil.isEntityMoving((Entity)GroundSpeed.mc.player) && !GroundSpeed.mc.player.collidedHorizontally && !BlockUtil.isBlockAboveEntitySolid((Entity)GroundSpeed.mc.player) && BlockUtil.isBlockBelowEntitySolid((Entity)GroundSpeed.mc.player)) {
            this.oneTime = true;
            this.antiShake = ((boolean)this.noShake.getValue() && GroundSpeed.mc.player.getRidingEntity() == null);
            final boolean nextBoolean = new Random().nextBoolean();
            if (GroundSpeed.mc.player.posY >= this.startY + this.bounceHeight) {
                GroundSpeed.mc.player.motionY = -this.bounceHeight;
                ++this.lowChainVal;
                if (this.lowChainVal == 1.0) {
                    this.move = 0.075f;
                }
                if (this.lowChainVal == 2.0) {
                    this.move = 0.175f;
                }
                if (this.lowChainVal == 3.0) {
                    this.move = 0.275f;
                }
                if (this.lowChainVal == 4.0) {
                    this.move = 0.35f;
                }
                if (this.lowChainVal == 5.0) {
                    this.move = 0.375f;
                }
                if (this.lowChainVal == 6.0) {
                    this.move = 0.4f;
                }
                if (this.lowChainVal == 7.0) {
                    this.move = 0.425f;
                }
                if (this.lowChainVal == 8.0) {
                    this.move = 0.45f;
                }
                if (this.lowChainVal == 9.0) {
                    this.move = 0.475f;
                }
                if (this.lowChainVal == 10.0) {
                    this.move = 0.5f;
                }
                if (this.lowChainVal == 11.0) {
                    this.move = 0.5f;
                }
                if (this.lowChainVal == 12.0) {
                    this.move = 0.525f;
                }
                if (this.lowChainVal == 13.0) {
                    this.move = 0.525f;
                }
                if (this.lowChainVal == 14.0) {
                    this.move = 0.535f;
                }
                if (this.lowChainVal == 15.0) {
                    this.move = 0.535f;
                }
                if (this.lowChainVal == 16.0) {
                    this.move = 0.545f;
                }
                if (this.lowChainVal >= 17.0) {
                    this.move = 0.545f;
                }
                if (this.useTimer.getValue()) {
                    LuigiHack.timerManager.setTimer(1.0f);
                }
            }
            if (GroundSpeed.mc.player.posY == this.startY) {
                GroundSpeed.mc.player.motionY = this.bounceHeight;
                ++this.highChainVal;
                if (this.highChainVal == 1.0) {
                    this.move = 0.075f;
                }
                if (this.highChainVal == 2.0) {
                    this.move = 0.175f;
                }
                if (this.highChainVal == 3.0) {
                    this.move = 0.375f;
                }
                if (this.highChainVal == 4.0) {
                    this.move = 0.6f;
                }
                if (this.highChainVal == 5.0) {
                    this.move = 0.775f;
                }
                if (this.highChainVal == 6.0) {
                    this.move = 0.825f;
                }
                if (this.highChainVal == 7.0) {
                    this.move = 0.875f;
                }
                if (this.highChainVal == 8.0) {
                    this.move = 0.925f;
                }
                if (this.highChainVal == 9.0) {
                    this.move = 0.975f;
                }
                if (this.highChainVal == 10.0) {
                    this.move = 1.05f;
                }
                if (this.highChainVal == 11.0) {
                    this.move = 1.1f;
                }
                if (this.highChainVal == 12.0) {
                    this.move = 1.1f;
                }
                if (this.highChainVal == 13.0) {
                    this.move = 1.15f;
                }
                if (this.highChainVal == 14.0) {
                    this.move = 1.15f;
                }
                if (this.highChainVal == 15.0) {
                    this.move = 1.175f;
                }
                if (this.highChainVal == 16.0) {
                    this.move = 1.175f;
                }
                if (this.highChainVal >= 17.0) {
                    this.move = 1.2f;
                }
                if (this.useTimer.getValue()) {
                    if (nextBoolean) {
                        LuigiHack.timerManager.setTimer(1.3f);
                    }
                    else {
                        LuigiHack.timerManager.setTimer(1.0f);
                    }
                }
            }
            EntityUtil.moveEntityStrafe((double)this.move, (Entity)GroundSpeed.mc.player);
        }
        else {
            if (this.oneTime) {
                GroundSpeed.mc.player.motionY = -0.1;
                this.oneTime = false;
            }
            this.antiShake = false;
            this.highChainVal = 0.0;
            this.lowChainVal = 0.0;
            this.speedOff();
        }
    }
    
    public void onToggle() {
        if (this.mode.getValue() == Mode.YPORT) {
            Step.mc.player.stepHeight = 0.6f;
            GroundSpeed.mc.player.motionY = -3.0;
        }
    }
    
    @SubscribeEvent
    public void onUpdateWalkingPlayer(final UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (this.mode.getValue() != Mode.VANILLA || nullCheck()) {
            return;
        }
        switch (updateWalkingPlayerEvent.getStage()) {
            case 0: {
                this.vanillaCounter = (this.vanilla() ? (++this.vanillaCounter) : 0);
                if (this.vanillaCounter != 4) {
                    break;
                }
                this.changeY = true;
                this.minY = GroundSpeed.mc.player.getEntityBoundingBox().minY + (GroundSpeed.mc.world.getBlockState(GroundSpeed.mc.player.getPosition()).getMaterial().blocksMovement() ? (-(double)this.blocked.getValue() / 10.0) : ((double)this.unblocked.getValue() / 10.0)) + this.getJumpBoostModifier();
            }
            case 1: {
                if (this.vanillaCounter == 3) {
                    final EntityPlayerSP player = GroundSpeed.mc.player;
                    player.motionX *= (double)this.zeroSpeed.getValue() / 10.0;
                    final EntityPlayerSP player2 = GroundSpeed.mc.player;
                    player2.motionZ *= (double)this.zeroSpeed.getValue() / 10.0;
                    break;
                }
                if (this.vanillaCounter != 4) {
                    break;
                }
                final EntityPlayerSP player3 = GroundSpeed.mc.player;
                player3.motionX /= (double)this.speed.getValue() / 10.0;
                final EntityPlayerSP player4 = GroundSpeed.mc.player;
                player4.motionZ /= (double)this.speed.getValue() / 10.0;
                this.vanillaCounter = 2;
                break;
            }
        }
    }
    
    static {
        GroundSpeed.INSTANCE = new GroundSpeed();
    }
    
    public GroundSpeed() {
        super("GroundSpeed", "Makes you faster", Module.Category.MOVEMENT, true, false, false);
        this.mode = (Setting<Mode>)this.register(new Setting("Mode", (Object)Mode.INSTANT));
        this.useTimerAA = (Setting<Boolean>)this.register(new Setting("YportUseTimer", (Object)true, p0 -> this.mode.getValue() == Mode.YPORT));
        this.yPortSpeed = (Setting<Double>)this.register(new Setting("YportSpeed", (Object)0.1, (Object)0.0, (Object)1.0, p0 -> this.mode.getValue() == Mode.YPORT));
        this.stepyport = (Setting<Boolean>)this.register(new Setting("YportStep", (Object)true, p0 -> this.mode.getValue() == Mode.YPORT));
        this.strafeJump = (Setting<Boolean>)this.register(new Setting("Jump", (Object)Boolean.FALSE, p0 -> this.mode.getValue() == Mode.INSTANT));
        this.noShake = (Setting<Boolean>)this.register(new Setting("NoShake", (Object)Boolean.TRUE, p0 -> this.mode.getValue() != Mode.INSTANT));
        this.useTimer = (Setting<Boolean>)this.register(new Setting("UseTimer", (Object)Boolean.FALSE, p0 -> this.mode.getValue() != Mode.INSTANT));
        this.zeroSpeed = (Setting<Double>)this.register(new Setting("0-Speed", (Object)0.0, (Object)0.0, (Object)100.0, p0 -> this.mode.getValue() == Mode.VANILLA));
        this.speed = (Setting<Double>)this.register(new Setting("Speed", (Object)10.0, (Object)0.1, (Object)100.0, p0 -> this.mode.getValue() == Mode.VANILLA));
        this.blocked = (Setting<Double>)this.register(new Setting("Blocked", (Object)10.0, (Object)0.0, (Object)100.0, p0 -> this.mode.getValue() == Mode.VANILLA));
        this.unblocked = (Setting<Double>)this.register(new Setting("Unblocked", (Object)10.0, (Object)0.0, (Object)100.0, p0 -> this.mode.getValue() == Mode.VANILLA));
        this.bounceHeight = 0.4;
        this.move = 0.26f;
        this.timer = new Timer();
        this.stepheight = 2.0f;
        this.setInstance();
    }
    
    private double getJumpBoostModifier() {
        double n = 0.0;
        if (GroundSpeed.mc.player.isPotionActive(MobEffects.JUMP_BOOST)) {
            n *= 1.0 + 0.2 * Objects.requireNonNull(GroundSpeed.mc.player.getActivePotionEffect(MobEffects.JUMP_BOOST)).getAmplifier();
        }
        return n;
    }
    
    public void onDisable() {
        if (this.mode.getValue() == Mode.ONGROUND || this.mode.getValue() == Mode.BOOST) {
            GroundSpeed.mc.player.motionY = -0.1;
        }
        this.changeY = false;
        LuigiHack.timerManager.setTimer(1.0f);
        this.highChainVal = 0.0;
        this.lowChainVal = 0.0;
        this.antiShake = false;
        if (this.mode.getValue() == Mode.YPORT) {
            this.timer.reset();
            EntityUtil.resetTimer();
        }
    }
    
    public void onUpdate() {
        if (this.mode.getValue() == Mode.YPORT) {
            if (GroundSpeed.mc.player.isSneaking() || GroundSpeed.mc.player.isInWater() || GroundSpeed.mc.player.isInLava() || GroundSpeed.mc.player.isOnLadder() || LuigiHack.moduleManager.isModuleEnabled("Strafe")) {
                return;
            }
            if (GroundSpeed.mc.player == null || GroundSpeed.mc.world == null) {
                this.disable();
                return;
            }
            this.handleYPortSpeed();
            if ((!GroundSpeed.mc.player.isOnLadder() || GroundSpeed.mc.player.isInWater() || GroundSpeed.mc.player.isInLava()) && (boolean)this.stepyport.getValue()) {
                Step.mc.player.stepHeight = this.stepheight;
                return;
            }
        }
        if (this.shouldReturn() || GroundSpeed.mc.player.isSneaking() || GroundSpeed.mc.player.isInWater() || GroundSpeed.mc.player.isInLava()) {
            return;
        }
        switch ((Mode)this.mode.getValue()) {
            case BOOST: {
                this.doBoost();
                break;
            }
            case ACCEL: {
                this.doAccel();
                break;
            }
            case ONGROUND: {
                this.doOnground();
                break;
            }
        }
    }
    
    @SubscribeEvent
    public void onSettingChange(final ClientEvent clientEvent) {
        if (clientEvent.getStage() == 2 && clientEvent.getSetting().equals(this.mode) && this.mode.getPlannedValue() == Mode.INSTANT) {
            GroundSpeed.mc.player.motionY = -0.1;
        }
    }
    
    public String getDisplayInfo() {
        return this.mode.currentEnumName();
    }
    
    private void handleYPortSpeed() {
        if (!MotionUtil.isMoving((EntityLivingBase)GroundSpeed.mc.player) || (GroundSpeed.mc.player.isInWater() && GroundSpeed.mc.player.isInLava()) || GroundSpeed.mc.player.collidedHorizontally) {
            return;
        }
        if (GroundSpeed.mc.player.onGround) {
            if (this.useTimerAA.getValue()) {
                EntityUtil.setTimer(1.15f);
            }
            GroundSpeed.mc.player.jump();
            MotionUtil.setSpeed((EntityLivingBase)GroundSpeed.mc.player, MotionUtil.getBaseMoveSpeed() + (double)this.yPortSpeed.getValue());
        }
        else {
            GroundSpeed.mc.player.motionY = -1.0;
            EntityUtil.resetTimer();
        }
    }
    
    private void setInstance() {
        GroundSpeed.INSTANCE = this;
    }
    
    private void doBoost() {
        this.bounceHeight = 0.4;
        this.move = 0.26f;
        if (GroundSpeed.mc.player.onGround) {
            this.startY = GroundSpeed.mc.player.posY;
        }
        if (EntityUtil.getEntitySpeed((Entity)GroundSpeed.mc.player) <= 1.0) {
            this.lowChainVal = 1.0;
            this.highChainVal = 1.0;
        }
        if (EntityUtil.isEntityMoving((Entity)GroundSpeed.mc.player) && !GroundSpeed.mc.player.collidedHorizontally && !BlockUtil.isBlockAboveEntitySolid((Entity)GroundSpeed.mc.player) && BlockUtil.isBlockBelowEntitySolid((Entity)GroundSpeed.mc.player)) {
            this.oneTime = true;
            this.antiShake = ((boolean)this.noShake.getValue() && GroundSpeed.mc.player.getRidingEntity() == null);
            final boolean nextBoolean = new Random().nextBoolean();
            if (GroundSpeed.mc.player.posY >= this.startY + this.bounceHeight) {
                GroundSpeed.mc.player.motionY = -this.bounceHeight;
                ++this.lowChainVal;
                if (this.lowChainVal == 1.0) {
                    this.move = 0.075f;
                }
                if (this.lowChainVal == 2.0) {
                    this.move = 0.15f;
                }
                if (this.lowChainVal == 3.0) {
                    this.move = 0.175f;
                }
                if (this.lowChainVal == 4.0) {
                    this.move = 0.2f;
                }
                if (this.lowChainVal == 5.0) {
                    this.move = 0.225f;
                }
                if (this.lowChainVal == 6.0) {
                    this.move = 0.25f;
                }
                if (this.lowChainVal >= 7.0) {
                    this.move = 0.27895f;
                }
                if (this.useTimer.getValue()) {
                    LuigiHack.timerManager.setTimer(1.0f);
                }
            }
            if (GroundSpeed.mc.player.posY == this.startY) {
                GroundSpeed.mc.player.motionY = this.bounceHeight;
                ++this.highChainVal;
                if (this.highChainVal == 1.0) {
                    this.move = 0.075f;
                }
                if (this.highChainVal == 2.0) {
                    this.move = 0.175f;
                }
                if (this.highChainVal == 3.0) {
                    this.move = 0.325f;
                }
                if (this.highChainVal == 4.0) {
                    this.move = 0.375f;
                }
                if (this.highChainVal == 5.0) {
                    this.move = 0.4f;
                }
                if (this.highChainVal >= 6.0) {
                    this.move = 0.43395f;
                }
                if (this.useTimer.getValue()) {
                    if (nextBoolean) {
                        LuigiHack.timerManager.setTimer(1.3f);
                    }
                    else {
                        LuigiHack.timerManager.setTimer(1.0f);
                    }
                }
            }
            EntityUtil.moveEntityStrafe((double)this.move, (Entity)GroundSpeed.mc.player);
        }
        else {
            if (this.oneTime) {
                GroundSpeed.mc.player.motionY = -0.1;
                this.oneTime = false;
            }
            this.highChainVal = 0.0;
            this.lowChainVal = 0.0;
            this.antiShake = false;
            this.speedOff();
        }
    }
    
    private void speedOff() {
        final float n = (float)Math.toRadians(GroundSpeed.mc.player.rotationYaw);
        if (BlockUtil.isBlockAboveEntitySolid((Entity)GroundSpeed.mc.player)) {
            if (GroundSpeed.mc.gameSettings.keyBindForward.isKeyDown() && !GroundSpeed.mc.gameSettings.keyBindSneak.isKeyDown() && GroundSpeed.mc.player.onGround) {
                final EntityPlayerSP player = GroundSpeed.mc.player;
                player.motionX -= MathUtil.sin(n) * 0.15;
                final EntityPlayerSP player2 = GroundSpeed.mc.player;
                player2.motionZ += MathUtil.cos(n) * 0.15;
            }
        }
        else if (GroundSpeed.mc.player.collidedHorizontally) {
            if (GroundSpeed.mc.gameSettings.keyBindForward.isKeyDown() && !GroundSpeed.mc.gameSettings.keyBindSneak.isKeyDown() && GroundSpeed.mc.player.onGround) {
                final EntityPlayerSP player3 = GroundSpeed.mc.player;
                player3.motionX -= MathUtil.sin(n) * 0.03;
                final EntityPlayerSP player4 = GroundSpeed.mc.player;
                player4.motionZ += MathUtil.cos(n) * 0.03;
            }
        }
        else if (!BlockUtil.isBlockBelowEntitySolid((Entity)GroundSpeed.mc.player)) {
            if (GroundSpeed.mc.gameSettings.keyBindForward.isKeyDown() && !GroundSpeed.mc.gameSettings.keyBindSneak.isKeyDown() && GroundSpeed.mc.player.onGround) {
                final EntityPlayerSP player5 = GroundSpeed.mc.player;
                player5.motionX -= MathUtil.sin(n) * 0.03;
                final EntityPlayerSP player6 = GroundSpeed.mc.player;
                player6.motionZ += MathUtil.cos(n) * 0.03;
            }
        }
        else {
            GroundSpeed.mc.player.motionX = 0.0;
            GroundSpeed.mc.player.motionZ = 0.0;
        }
    }
    
    public static GroundSpeed getInstance() {
        if (GroundSpeed.INSTANCE == null) {
            GroundSpeed.INSTANCE = new GroundSpeed();
        }
        return GroundSpeed.INSTANCE;
    }
    
    @SubscribeEvent
    public void onMode(final MoveEvent moveEvent) {
        if (!this.shouldReturn() && moveEvent.getStage() == 0 && this.mode.getValue() == Mode.INSTANT && !nullCheck() && !GroundSpeed.mc.player.isSneaking() && !GroundSpeed.mc.player.isInWater() && !GroundSpeed.mc.player.isInLava() && (GroundSpeed.mc.player.movementInput.moveForward != 0.0f || GroundSpeed.mc.player.movementInput.moveStrafe != 0.0f)) {
            if (GroundSpeed.mc.player.onGround && (boolean)this.strafeJump.getValue()) {
                moveEvent.setY(GroundSpeed.mc.player.motionY = 0.4);
            }
            final MovementInput movementInput = GroundSpeed.mc.player.movementInput;
            final float moveForward = movementInput.moveForward;
            float moveStrafe = movementInput.moveStrafe;
            float rotationYaw = GroundSpeed.mc.player.rotationYaw;
            if (moveForward == 0.0 && moveStrafe == 0.0) {
                moveEvent.setX(0.0);
                moveEvent.setZ(0.0);
            }
            else {
                if (moveForward != 0.0) {
                    if (moveStrafe > 0.0) {
                        rotationYaw += ((moveForward > 0.0) ? -45 : 45);
                    }
                    else if (moveStrafe < 0.0) {
                        rotationYaw += ((moveForward > 0.0) ? 45 : -45);
                    }
                    moveStrafe = 0.0f;
                }
                final float n = (moveStrafe == 0.0f) ? moveStrafe : ((moveStrafe > 0.0) ? 1.0f : -1.0f);
                final double cos = Math.cos(Math.toRadians(rotationYaw + 90.0f));
                final double sin = Math.sin(Math.toRadians(rotationYaw + 90.0f));
                moveEvent.setX(moveForward * EntityUtil.getMaxSpeed() * cos + n * EntityUtil.getMaxSpeed() * sin);
                moveEvent.setZ(moveForward * EntityUtil.getMaxSpeed() * sin - n * EntityUtil.getMaxSpeed() * cos);
            }
        }
    }
    
    public enum Mode
    {
        ACCEL, 
        VANILLA, 
        ONGROUND, 
        YPORT, 
        INSTANT, 
        BOOST;
    }
}
