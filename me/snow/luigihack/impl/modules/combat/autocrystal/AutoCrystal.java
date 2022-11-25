//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.combat.autocrystal;

import me.snow.luigihack.impl.modules.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import me.snow.luigihack.api.setting.*;
import java.util.concurrent.atomic.*;
import me.snow.luigihack.impl.modules.player.*;
import me.snow.luigihack.*;
import net.minecraft.entity.item.*;
import net.minecraft.world.*;
import net.minecraft.network.play.server.*;
import me.snow.luigihack.impl.modules.misc.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.init.*;
import net.minecraft.block.state.*;
import me.snow.luigihack.api.util.cc.*;
import java.util.concurrent.*;
import net.minecraft.network.*;
import net.minecraft.util.math.*;
import net.minecraft.item.*;
import java.awt.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.util.*;
import me.snow.luigihack.api.util.skid.oyvey.*;
import me.snow.luigihack.api.event.events.*;
import com.mojang.authlib.*;
import net.minecraft.client.entity.*;
import net.minecraftforge.fml.common.gameevent.*;
import org.lwjgl.input.*;
import me.snow.luigihack.impl.gui.*;
import io.netty.util.internal.*;
import java.util.*;
import net.minecraft.network.play.client.*;

public class AutoCrystal extends Module
{
    public /* synthetic */ BlockPos placePos;
    public /* synthetic */ Setting<Boolean> lethalSwitch;
    public /* synthetic */ Setting<Boolean> box;
    public /* synthetic */ Setting<Boolean> mineSwitch;
    public /* synthetic */ Setting<Float> minMinDmg;
    public /* synthetic */ Setting<Float> dropOff;
    public /* synthetic */ Setting<Float> soundPlayer;
    private final /* synthetic */ Setting<Integer> oGreen;
    private final /* synthetic */ Timer renderTimer;
    public /* synthetic */ Setting<Integer> predictTicks;
    public /* synthetic */ Setting<Raytrace> raytrace;
    public /* synthetic */ Setting<Logic> logic;
    private /* synthetic */ int rotationPacketsSpoofed;
    public final /* synthetic */ Timer yawStepTimer;
    public /* synthetic */ Setting<ACAutoSwitch> autoSwitch;
    public /* synthetic */ Setting<ACRotate> rotate;
    private final /* synthetic */ Setting<Integer> bRed;
    public /* synthetic */ Setting<Integer> facePlaceSpeed;
    public /* synthetic */ Setting<Boolean> noCount;
    private final /* synthetic */ Setting<Boolean> slabFactor;
    private /* synthetic */ boolean switching;
    private /* synthetic */ Map<Entity, Float> crystalMap;
    private /* synthetic */ int minDmgCount;
    public final /* synthetic */ Setting<Boolean> removeAfterAttack;
    public static /* synthetic */ EntityPlayer target;
    public /* synthetic */ Setting<Boolean> doublePop;
    public /* synthetic */ Setting<Float> maxSelfBreak;
    public /* synthetic */ Setting<AntiFriendPop> antiFriendPop;
    private /* synthetic */ PlaceInfo placeInfo;
    private final /* synthetic */ Timer syncTimer;
    public /* synthetic */ Setting<Boolean> hyperSync;
    public /* synthetic */ Setting<Float> maxSelfPlace;
    public /* synthetic */ Setting<Boolean> manualMinDmg;
    public /* synthetic */ Setting<Integer> minArmor;
    public /* synthetic */ Setting<Integer> YawStepVal;
    private final /* synthetic */ Setting<Boolean> fadeFactor;
    private /* synthetic */ BlockPos syncedCrystalPos;
    public /* synthetic */ Setting<Integer> popTime;
    public /* synthetic */ Setting<Boolean> holdFaceBreak;
    private final /* synthetic */ Setting<Float> slabHeight;
    public /* synthetic */ Setting<Boolean> calcEvenIfNoDamage;
    private /* synthetic */ float pitch;
    private /* synthetic */ BlockPos renderPos;
    public /* synthetic */ Setting<Boolean> syncCount;
    public /* synthetic */ Setting<Boolean> holySync;
    private final /* synthetic */ Timer breakTimer;
    public /* synthetic */ Setting<Boolean> superSafe;
    public /* synthetic */ Setting<ThreadMode> threadMode;
    public /* synthetic */ Setting<Boolean> rotateFirst;
    private final /* synthetic */ Setting<Integer> oAlpha;
    public /* synthetic */ Setting<Boolean> exactHand;
    private /* synthetic */ boolean posConfirmed;
    private final /* synthetic */ Setting<Integer> swapdelay;
    public /* synthetic */ Setting<Boolean> actualSlowBreak;
    public /* synthetic */ Setting<Float> placeRange;
    public /* synthetic */ Setting<Boolean> webAttack;
    private final /* synthetic */ Setting<Integer> eventMode;
    public /* synthetic */ Setting<Boolean> limitFacePlace;
    private final /* synthetic */ Setting<Integer> bAlpha;
    public /* synthetic */ Setting<Integer> manualBreak;
    public /* synthetic */ Setting<Integer> threadDelay;
    public /* synthetic */ Setting<Float> placetrace;
    private /* synthetic */ int lastSlot;
    private /* synthetic */ boolean offHand;
    public /* synthetic */ Setting<Target> targetMode;
    public /* synthetic */ Setting<Float> breaktrace;
    private /* synthetic */ BlockPos lastPos;
    public static /* synthetic */ Set<BlockPos> brokenPos;
    private final /* synthetic */ Map<EntityPlayer, Timer> totemPops;
    public /* synthetic */ Setting<Boolean> resetBreakTimer;
    public /* synthetic */ Setting<Boolean> colorSync;
    public /* synthetic */ Setting<Float> predictOffset;
    public /* synthetic */ Setting<Float> breakRange;
    public /* synthetic */ Setting<Boolean> predictFriendDmg;
    private /* synthetic */ BlockPos webPos;
    public /* synthetic */ Setting<Boolean> outline;
    public final /* synthetic */ Setting<Boolean> attackOppositeHand;
    public /* synthetic */ Setting<Float> soundRange;
    private final /* synthetic */ Setting<Integer> oBlue;
    public /* synthetic */ Setting<Boolean> wasteMinDmgCount;
    public /* synthetic */ Setting<Boolean> doublePopOnDamage;
    private static /* synthetic */ AutoCrystal instance;
    private /* synthetic */ float timePassed;
    private /* synthetic */ boolean foundDoublePop;
    public /* synthetic */ Setting<Float> rainbowBrightnessAA22;
    public /* synthetic */ Setting<Bind> switchBind;
    public /* synthetic */ Setting<Integer> packets;
    private final /* synthetic */ Setting<Float> duration;
    public /* synthetic */ Setting<Boolean> extraSelfCalc;
    public /* synthetic */ Setting<Float> facePlace;
    private /* synthetic */ double renderDamage;
    private final /* synthetic */ Setting<ACSettings> setting;
    public /* synthetic */ Setting<DamageSync> damageSync;
    public /* synthetic */ Setting<Boolean> predictRotate;
    public /* synthetic */ Setting<Float> minDamage;
    public /* synthetic */ Entity efficientTarget;
    public /* synthetic */ Setting<Integer> YawStepTicks;
    public /* synthetic */ Setting<Integer> placeDelay;
    private final /* synthetic */ Setting<Integer> max;
    private final /* synthetic */ AtomicBoolean shouldInterrupt;
    public /* synthetic */ Setting<Boolean> predictPos;
    private /* synthetic */ Thread thread;
    private final /* synthetic */ List<RenderPos> positions;
    public /* synthetic */ Setting<Boolean> slowFaceBreak;
    public /* synthetic */ Setting<Boolean> syncThreadBool;
    private /* synthetic */ AxisAlignedBB renderBB;
    private final /* synthetic */ Setting<Float> moveSpeed;
    public /* synthetic */ Setting<Boolean> suicide;
    public /* synthetic */ Setting<Boolean> holdFacePlace;
    public /* synthetic */ Setting<ACYaw> yawstepmode;
    private final /* synthetic */ Setting<Integer> oRed;
    private final /* synthetic */ Setting<Float> lineWidth;
    public /* synthetic */ Setting<ACRenderMode> renderMode;
    public /* synthetic */ Setting<Float> popDamage;
    public /* synthetic */ Setting<Boolean> fullCalc;
    private /* synthetic */ boolean didRotation;
    public /* synthetic */ Setting<Boolean> antiNaked;
    public /* synthetic */ Setting<Boolean> sound;
    public /* synthetic */ Setting<Integer> confirm;
    public /* synthetic */ Setting<Integer> rotations;
    private final /* synthetic */ Setting<Integer> bBlue;
    public /* synthetic */ Setting<Boolean> text;
    public /* synthetic */ Setting<Boolean> fullSync;
    public /* synthetic */ Setting<Boolean> sync;
    public /* synthetic */ Setting<Float> range;
    private final /* synthetic */ Timer syncroTimer;
    public /* synthetic */ Setting<Boolean> antiWeakness;
    private final /* synthetic */ Timer predictTimer;
    public final /* synthetic */ Setting<Boolean> packetswing;
    private /* synthetic */ float yaw;
    private /* synthetic */ ScheduledExecutorService executor;
    private final /* synthetic */ Setting<Boolean> scaleFactor;
    private /* synthetic */ double lastDamage;
    public /* synthetic */ Setting<Integer> wasteAmount;
    public /* synthetic */ Setting<Boolean> syncedFeetPlace;
    private /* synthetic */ boolean shouldSilent;
    private final /* synthetic */ Timer manualTimer;
    public /* synthetic */ Setting<Boolean> oneDot15;
    private final /* synthetic */ Timer switchTimer;
    private final /* synthetic */ Setting<Float> accel;
    public /* synthetic */ Setting<Boolean> enormousSync;
    public /* synthetic */ Setting<Integer> breakDelay;
    public /* synthetic */ Setting<Integer> damageSyncTime;
    public /* synthetic */ Setting<Boolean> antiSurround;
    public /* synthetic */ boolean rotating;
    private /* synthetic */ BlockPos syncedPlayerPos;
    private /* synthetic */ int crystalCount;
    public static /* synthetic */ Set<BlockPos> placedPos;
    private final /* synthetic */ Queue<CPacketUseEntity> packetUseEntities;
    private /* synthetic */ boolean mainHand;
    public /* synthetic */ Setting<Float> rainbowSaturationAA22;
    public /* synthetic */ Setting<Boolean> antiCommit;
    public /* synthetic */ Setting<Double> popHealth;
    public /* synthetic */ Setting<Switch> switchMode;
    public final /* synthetic */ Setting<Boolean> antiBlock;
    private final /* synthetic */ Setting<Boolean> onlyplaced;
    public /* synthetic */ Setting<Boolean> syncySync;
    private /* synthetic */ EntityPlayer currentSyncTarget;
    public /* synthetic */ Setting<Boolean> manual;
    public /* synthetic */ Setting<PredictTimer> instantTimer;
    public /* synthetic */ Setting<ACSwing> swingMode;
    public /* synthetic */ Setting<Integer> syncThreads;
    public /* synthetic */ Setting<Boolean> gigaSync;
    private /* synthetic */ boolean addTolowDmg;
    public /* synthetic */ Setting<Boolean> instant;
    private /* synthetic */ Queue<Entity> attackList;
    public /* synthetic */ Setting<Boolean> soundConfirm;
    private /* synthetic */ BlockPos lastRenderPos;
    public static /* synthetic */ Set<BlockPos> lowDmgPos;
    public /* synthetic */ Setting<Integer> predictDelay;
    private final /* synthetic */ Setting<Integer> bGreen;
    private final /* synthetic */ AtomicBoolean threadOngoing;
    private /* synthetic */ double currentDamage;
    private final /* synthetic */ Timer placeTimer;
    public /* synthetic */ Setting<Boolean> predictCalc;
    
    private void postProcessing() {
        if (this.threadMode.getValue() != ThreadMode.NONE || (int)this.eventMode.getValue() != 2 || this.rotate.getValue() == ACRotate.OFF || !(boolean)this.rotateFirst.getValue()) {
            return;
        }
        switch ((Logic)this.logic.getValue()) {
            case BREAKPLACE: {
                this.postProcessBreak();
                this.postProcessPlace();
                break;
            }
            case PLACEBREAK: {
                this.postProcessPlace();
                this.postProcessBreak();
                break;
            }
        }
    }
    
    private boolean check() {
        if (fullNullCheck()) {
            return false;
        }
        if (this.syncTimer.passedMs((long)(int)this.damageSyncTime.getValue())) {
            this.currentSyncTarget = null;
            this.syncedCrystalPos = null;
            this.syncedPlayerPos = null;
        }
        else if ((boolean)this.syncySync.getValue() && this.syncedCrystalPos != null) {
            this.posConfirmed = true;
        }
        this.foundDoublePop = false;
        if (this.renderTimer.passedMs(500L)) {
            this.renderPos = null;
            this.renderTimer.reset();
        }
        this.mainHand = (AutoCrystal.mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL);
        if (this.autoSwitch.getValue() == ACAutoSwitch.Spoof && InventoryUtil.getItemHotbar(Items.END_CRYSTAL) != -1) {
            this.mainHand = true;
            this.shouldSilent = true;
        }
        else {
            this.shouldSilent = false;
        }
        this.offHand = (AutoCrystal.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL);
        this.currentDamage = 0.0;
        this.placePos = null;
        if (this.lastSlot != AutoCrystal.mc.player.inventory.currentItem) {
            this.lastSlot = AutoCrystal.mc.player.inventory.currentItem;
            this.switchTimer.reset();
        }
        if (!this.offHand && !this.mainHand) {
            this.placeInfo = null;
            this.packetUseEntities.clear();
        }
        if (this.offHand || this.mainHand) {
            this.switching = false;
        }
        if ((!this.offHand && !this.mainHand && this.switchMode.getValue() == Switch.BREAKSLOT && !this.switching) || !DamageUtil.canBreakWeakness((EntityPlayer)AutoCrystal.mc.player) || !this.switchTimer.passedMs((long)(int)this.swapdelay.getValue())) {
            this.renderPos = null;
            AutoCrystal.target = null;
            this.rotating = false;
            return false;
        }
        if ((boolean)this.mineSwitch.getValue() && Mouse.isButtonDown(0) && (this.switching || this.autoSwitch.getValue() == ACAutoSwitch.Swap) && Mouse.isButtonDown(1) && AutoCrystal.mc.player.getHeldItemMainhand().getItem() instanceof ItemPickaxe) {
            this.switchItem();
        }
        this.mapCrystals();
        if (!this.posConfirmed && this.damageSync.getValue() != DamageSync.NONE && this.syncTimer.passedMs((long)(int)this.confirm.getValue())) {
            this.syncTimer.setMs((long)((int)this.damageSyncTime.getValue() + 1));
        }
        return true;
    }
    
    private void handlePool(final boolean b) {
        if (b || this.executor == null || this.executor.isTerminated() || this.executor.isShutdown() || (this.syncroTimer.passedMs((long)(int)this.syncThreads.getValue()) && (boolean)this.syncThreadBool.getValue())) {
            if (this.executor != null) {
                this.executor.shutdown();
            }
            this.executor = this.getExecutor();
            this.syncroTimer.reset();
        }
    }
    
    private boolean isDoublePoppable(final EntityPlayer entityPlayer, final float n) {
        final float health;
        if ((boolean)this.doublePop.getValue() && (health = EntityUtil.getHealth((Entity)entityPlayer)) <= (double)this.popHealth.getValue() && n > health + 0.5 && n <= (float)this.popDamage.getValue()) {
            final Timer timer = this.totemPops.get(entityPlayer);
            return timer == null || timer.passedMs((long)(int)this.popTime.getValue());
        }
        return false;
    }
    
    public boolean isActiveRotate() {
        return this.isEnabled() && (this.efficientTarget != null || this.placePos != null) && (this.isHoldingCrystal() & this.rotate.getValue() != ACRotate.OFF) && (boolean)Speedmine.INSTANCE.rotate2.getValue();
    }
    
    private void rotateToPos(final BlockPos blockPos) {
        switch ((ACRotate)this.rotate.getValue()) {
            case OFF: {
                this.rotating = false;
            }
            case PLACE:
            case ALL: {
                final float[] calcAngle = MathUtil.calcAngle(AutoCrystal.mc.player.getPositionEyes(AutoCrystal.mc.getRenderPartialTicks()), new Vec3d((double)(blockPos.getX() + 0.5f), (double)(blockPos.getY() - 0.5f), (double)(blockPos.getZ() + 0.5f)));
                if ((int)this.eventMode.getValue() != 2 || this.threadMode.getValue() != ThreadMode.NONE) {
                    this.yaw = calcAngle[0];
                    this.pitch = calcAngle[1];
                    this.rotating = true;
                    break;
                }
                if (this.yawstepmode.getValue() == ACYaw.Semi) {
                    float yaw = LuigiHack.rotationManager.getYaw();
                    while (yaw < calcAngle[1]) {
                        if (AutoCrystal.mc.player.ticksExisted % (int)this.YawStepTicks.getValue() != 0) {
                            continue;
                        }
                        LuigiHack.rotationManager.setPlayerRotations(yaw += (int)this.YawStepVal.getValue(), calcAngle[1]);
                        this.yawStepTimer.reset();
                    }
                    break;
                }
                LuigiHack.rotationManager.setPlayerRotations(calcAngle[0], calcAngle[1]);
                break;
            }
        }
    }
    
    private void postProcessPlace() {
        if (this.placeInfo != null) {
            this.placeInfo.runPlace();
            this.placeTimer.reset();
            this.placeInfo = null;
        }
    }
    
    @SubscribeEvent(priority = EventPriority.HIGH, receiveCanceled = true)
    public void onPacketReceive(final PacketEvent.Receive receive) {
        if (fullNullCheck()) {
            return;
        }
        if (this.switchTimer.passedMs((long)(int)this.swapdelay.getValue()) && (boolean)this.instant.getValue() && receive.getPacket() instanceof SPacketSpawnObject && (this.syncedCrystalPos == null || !(boolean)this.syncedFeetPlace.getValue() || this.damageSync.getValue() == DamageSync.NONE)) {
            final SPacketSpawnObject sPacketSpawnObject = (SPacketSpawnObject)receive.getPacket();
            if (sPacketSpawnObject.getType() == 51) {
                final BlockPos blockPos = new BlockPos(sPacketSpawnObject.getX(), sPacketSpawnObject.getY(), sPacketSpawnObject.getZ());
                if (AutoCrystal.mc.player.getDistanceSq(blockPos) + (float)this.predictOffset.getValue() <= MathUtil.square((float)this.breakRange.getValue()) && (this.instantTimer.getValue() == PredictTimer.NONE || (this.instantTimer.getValue() == PredictTimer.BREAK && this.breakTimer.passedMs((long)(int)this.breakDelay.getValue())) || (this.instantTimer.getValue() == PredictTimer.PREDICT && this.predictTimer.passedMs((long)(int)this.predictDelay.getValue())))) {
                    if (this.predictSlowBreak(blockPos.down())) {
                        return;
                    }
                    if ((boolean)this.predictFriendDmg.getValue() && (this.antiFriendPop.getValue() == AntiFriendPop.BREAK || this.antiFriendPop.getValue() == AntiFriendPop.ALL) && this.isRightThread()) {
                        for (final EntityPlayer entityPlayer : AutoCrystal.mc.world.playerEntities) {
                            if (entityPlayer != null && !AutoCrystal.mc.player.equals((Object)entityPlayer) && entityPlayer.getDistanceSq(blockPos) <= MathUtil.square((float)this.range.getValue() + (float)this.placeRange.getValue()) && LuigiHack.friendManager.isFriend(entityPlayer)) {
                                if (DamageUtil.calculateDamage(blockPos, (Entity)entityPlayer) <= EntityUtil.getHealth((Entity)entityPlayer) + 0.5) {
                                    continue;
                                }
                                return;
                            }
                        }
                    }
                    if (AutoCrystal.placedPos.contains(blockPos.down())) {
                        Label_0591: {
                            if (this.isRightThread() && (boolean)this.superSafe.getValue()) {
                                if (!DamageUtil.canTakeDamage((boolean)this.suicide.getValue())) {
                                    break Label_0591;
                                }
                                final float calculateDamage;
                                if ((calculateDamage = DamageUtil.calculateDamage(blockPos, (Entity)AutoCrystal.mc.player)) - 0.5 <= EntityUtil.getHealth((Entity)AutoCrystal.mc.player)) {
                                    if (calculateDamage <= (float)this.maxSelfBreak.getValue()) {
                                        break Label_0591;
                                    }
                                }
                            }
                            else if (!(boolean)this.superSafe.getValue()) {
                                break Label_0591;
                            }
                            return;
                        }
                        this.attackCrystalPredict(sPacketSpawnObject.getEntityID(), blockPos);
                    }
                    else if ((boolean)this.predictCalc.getValue() && this.isRightThread()) {
                        float calculateDamage2 = -1.0f;
                        if (DamageUtil.canTakeDamage((boolean)this.suicide.getValue())) {
                            calculateDamage2 = DamageUtil.calculateDamage(blockPos, (Entity)AutoCrystal.mc.player);
                        }
                        if (calculateDamage2 + 0.5 < EntityUtil.getHealth((Entity)AutoCrystal.mc.player) && calculateDamage2 <= (float)this.maxSelfBreak.getValue()) {
                            for (final EntityPlayer entityPlayer2 : AutoCrystal.mc.world.playerEntities) {
                                if (entityPlayer2.getDistanceSq(blockPos) <= MathUtil.square((float)this.range.getValue()) && EntityUtil.isValid((Entity)entityPlayer2, (double)((float)this.range.getValue() + (float)this.breakRange.getValue())) && (!(boolean)this.antiNaked.getValue() || !DamageUtil.isNaked(entityPlayer2))) {
                                    final float calculateDamage3;
                                    if ((calculateDamage3 = DamageUtil.calculateDamage(blockPos, (Entity)entityPlayer2)) <= calculateDamage2 && (calculateDamage3 <= (float)this.minDamage.getValue() || DamageUtil.canTakeDamage((boolean)this.suicide.getValue())) && calculateDamage3 <= EntityUtil.getHealth((Entity)entityPlayer2)) {
                                        continue;
                                    }
                                    if ((boolean)this.predictRotate.getValue() && (int)this.eventMode.getValue() != 2 && (this.rotate.getValue() == ACRotate.BREAK || this.rotate.getValue() == ACRotate.ALL)) {
                                        this.rotateToPos(blockPos);
                                    }
                                    this.attackCrystalPredict(sPacketSpawnObject.getEntityID(), blockPos);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        else if (!(boolean)this.soundConfirm.getValue() && receive.getPacket() instanceof SPacketExplosion) {
            final SPacketExplosion sPacketExplosion = (SPacketExplosion)receive.getPacket();
            this.removePos(new BlockPos(sPacketExplosion.getX(), sPacketExplosion.getY(), sPacketExplosion.getZ()).down());
        }
        else if (receive.getPacket() instanceof SPacketDestroyEntities) {
            final int[] getEntityIDs = ((SPacketDestroyEntities)receive.getPacket()).getEntityIDs();
            for (int length = getEntityIDs.length, i = 0; i < length; ++i) {
                final Entity getEntityByID = AutoCrystal.mc.world.getEntityByID(getEntityIDs[i]);
                if (getEntityByID instanceof EntityEnderCrystal) {
                    AutoCrystal.brokenPos.remove(new BlockPos(getEntityByID.getPositionVector()).down());
                    AutoCrystal.placedPos.remove(new BlockPos(getEntityByID.getPositionVector()).down());
                }
            }
        }
        else if (receive.getPacket() instanceof SPacketEntityStatus) {
            final SPacketEntityStatus sPacketEntityStatus = (SPacketEntityStatus)receive.getPacket();
            if (sPacketEntityStatus.getOpCode() == 35 && sPacketEntityStatus.getEntity((World)AutoCrystal.mc.world) instanceof EntityPlayer) {
                this.totemPops.put((EntityPlayer)sPacketEntityStatus.getEntity((World)AutoCrystal.mc.world), new Timer().reset());
            }
        }
        else {
            final SPacketSoundEffect sPacketSoundEffect;
            if (receive.getPacket() instanceof SPacketSoundEffect && (sPacketSoundEffect = (SPacketSoundEffect)receive.getPacket()).getCategory() == SoundCategory.BLOCKS && sPacketSoundEffect.getSound() == SoundEvents.ENTITY_GENERIC_EXPLODE) {
                final BlockPos blockPos2 = new BlockPos(sPacketSoundEffect.getX(), sPacketSoundEffect.getY(), sPacketSoundEffect.getZ());
                if ((boolean)this.sound.getValue() || this.threadMode.getValue() == ThreadMode.SOUND) {
                    NoSoundLag.removeEntities(sPacketSoundEffect, (float)this.soundRange.getValue());
                }
                if (this.soundConfirm.getValue()) {
                    this.removePos(blockPos2);
                }
                if (this.threadMode.getValue() == ThreadMode.SOUND && this.isRightThread() && AutoCrystal.mc.player != null && AutoCrystal.mc.player.getDistanceSq(blockPos2) < MathUtil.square((float)this.soundPlayer.getValue())) {
                    this.handlePool(true);
                }
            }
        }
    }
    
    private ScheduledExecutorService getExecutor() {
        final ScheduledExecutorService singleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        singleThreadScheduledExecutor.scheduleAtFixedRate(RAutoCrystal.getInstance(this), 0L, (int)this.threadDelay.getValue(), TimeUnit.MILLISECONDS);
        return singleThreadScheduledExecutor;
    }
    
    private void calculateDamage(final EntityPlayer entityPlayer) {
        if (entityPlayer == null && this.targetMode.getValue() != Target.DAMAGE && !(boolean)this.fullCalc.getValue()) {
            return;
        }
        float n = 0.5f;
        EntityPlayer target = null;
        BlockPos blockPos = null;
        float n2 = 0.0f;
        this.foundDoublePop = false;
        BlockPos blockPos2 = null;
        IBlockState getBlockState = null;
        final BlockPos blockPos3;
        if ((boolean)this.webAttack.getValue() && entityPlayer != null && AutoCrystal.mc.world.getBlockState(blockPos3 = new BlockPos(entityPlayer.getPositionVector())).getBlock() == Blocks.WEB) {
            blockPos2 = blockPos3;
            getBlockState = AutoCrystal.mc.world.getBlockState(blockPos3);
            AutoCrystal.mc.world.setBlockToAir(blockPos3);
        }
        for (final BlockPos blockPos4 : BlockUtil.possiblePlacePositions((float)this.placeRange.getValue(), (boolean)this.antiSurround.getValue(), (boolean)this.oneDot15.getValue())) {
            if (!BlockUtil.rayTracePlaceCheck(blockPos4, (this.raytrace.getValue() == Raytrace.PLACE || this.raytrace.getValue() == Raytrace.FULL) && AutoCrystal.mc.player.getDistanceSq(blockPos4) > MathUtil.square((float)this.placetrace.getValue()), 1.0f)) {
                continue;
            }
            float calculateDamage = -1.0f;
            if (DamageUtil.canTakeDamage((boolean)this.suicide.getValue())) {
                calculateDamage = DamageUtil.calculateDamage(blockPos4, (Entity)AutoCrystal.mc.player);
            }
            if (calculateDamage + 0.5 >= EntityUtil.getHealth((Entity)AutoCrystal.mc.player)) {
                continue;
            }
            if (calculateDamage > (float)this.maxSelfPlace.getValue()) {
                continue;
            }
            if (entityPlayer != null) {
                final float calculateDamage2 = DamageUtil.calculateDamage(blockPos4, (Entity)entityPlayer);
                if ((boolean)this.calcEvenIfNoDamage.getValue() && (this.antiFriendPop.getValue() == AntiFriendPop.ALL || this.antiFriendPop.getValue() == AntiFriendPop.PLACE)) {
                    boolean b = false;
                    for (final EntityPlayer entityPlayer2 : AutoCrystal.mc.world.playerEntities) {
                        if (entityPlayer2 != null && !AutoCrystal.mc.player.equals((Object)entityPlayer2) && entityPlayer2.getDistanceSq(blockPos4) <= MathUtil.square((float)this.range.getValue() + (float)this.placeRange.getValue()) && LuigiHack.friendManager.isFriend(entityPlayer2)) {
                            if (DamageUtil.calculateDamage(blockPos4, (Entity)entityPlayer2) <= EntityUtil.getHealth((Entity)entityPlayer2) + 0.5) {
                                continue;
                            }
                            b = true;
                            break;
                        }
                    }
                    if (b) {
                        continue;
                    }
                }
                if (this.isDoublePoppable(entityPlayer, calculateDamage2) && (blockPos == null || entityPlayer.getDistanceSq(blockPos4) < entityPlayer.getDistanceSq(blockPos))) {
                    target = entityPlayer;
                    n = calculateDamage2;
                    blockPos = blockPos4;
                    this.foundDoublePop = true;
                }
                else {
                    if (this.foundDoublePop || (calculateDamage2 <= n && (!(boolean)this.extraSelfCalc.getValue() || calculateDamage2 < n || calculateDamage >= n2))) {
                        continue;
                    }
                    if (calculateDamage2 <= calculateDamage && (calculateDamage2 <= (float)this.minDamage.getValue() || DamageUtil.canTakeDamage((boolean)this.suicide.getValue())) && calculateDamage2 <= EntityUtil.getHealth((Entity)entityPlayer)) {
                        continue;
                    }
                    n = calculateDamage2;
                    target = entityPlayer;
                    blockPos = blockPos4;
                    n2 = calculateDamage;
                }
            }
            else {
                final float n3 = n;
                final EntityPlayer entityPlayer3 = target;
                final BlockPos blockPos5 = blockPos;
                final float n4 = n2;
                for (final EntityPlayer entityPlayer4 : AutoCrystal.mc.world.playerEntities) {
                    if (EntityUtil.isValid((Entity)entityPlayer4, (double)((float)this.placeRange.getValue() + (float)this.range.getValue()))) {
                        if ((boolean)this.antiNaked.getValue() && DamageUtil.isNaked(entityPlayer4)) {
                            continue;
                        }
                        final float calculateDamage3 = DamageUtil.calculateDamage(blockPos4, (Entity)entityPlayer4);
                        if ((boolean)this.doublePopOnDamage.getValue() && this.isDoublePoppable(entityPlayer4, calculateDamage3) && (blockPos == null || entityPlayer4.getDistanceSq(blockPos4) < entityPlayer4.getDistanceSq(blockPos))) {
                            target = entityPlayer4;
                            n = calculateDamage3;
                            blockPos = blockPos4;
                            n2 = calculateDamage;
                            this.foundDoublePop = true;
                            if (this.antiFriendPop.getValue() == AntiFriendPop.BREAK) {
                                break;
                            }
                            if (this.antiFriendPop.getValue() == AntiFriendPop.PLACE) {
                                break;
                            }
                            continue;
                        }
                        else {
                            if (this.foundDoublePop || (calculateDamage3 <= n && (!(boolean)this.extraSelfCalc.getValue() || calculateDamage3 < n || calculateDamage >= n2))) {
                                continue;
                            }
                            if (calculateDamage3 <= calculateDamage && (calculateDamage3 <= (float)this.minDamage.getValue() || DamageUtil.canTakeDamage((boolean)this.suicide.getValue())) && calculateDamage3 <= EntityUtil.getHealth((Entity)entityPlayer4)) {
                                continue;
                            }
                            n = calculateDamage3;
                            target = entityPlayer4;
                            blockPos = blockPos4;
                            n2 = calculateDamage;
                        }
                    }
                    else {
                        if ((this.antiFriendPop.getValue() != AntiFriendPop.ALL && this.antiFriendPop.getValue() != AntiFriendPop.PLACE) || entityPlayer4 == null || entityPlayer4.getDistanceSq(blockPos4) > MathUtil.square((float)this.range.getValue() + (float)this.placeRange.getValue()) || !LuigiHack.friendManager.isFriend(entityPlayer4)) {
                            continue;
                        }
                        if (DamageUtil.calculateDamage(blockPos4, (Entity)entityPlayer4) <= EntityUtil.getHealth((Entity)entityPlayer4) + 0.5) {
                            continue;
                        }
                        n = n3;
                        target = entityPlayer3;
                        blockPos = blockPos5;
                        n2 = n4;
                        break;
                    }
                }
            }
        }
        if (blockPos2 != null) {
            AutoCrystal.mc.world.setBlockState(blockPos2, getBlockState);
            this.webPos = blockPos;
        }
        AutoCrystal.target = target;
        this.currentDamage = n;
        this.placePos = blockPos;
    }
    
    public boolean isHoldingCrystal() {
        return InventoryUtilCC.isHolding(Items.END_CRYSTAL) || (((ACAutoSwitch)this.autoSwitch.getValue()).equals((Object)ACAutoSwitch.Spoof) && InventoryUtilCC.isInHotbar(Items.END_CRYSTAL));
    }
    
    @SubscribeEvent
    public void onUpdateWalkingPlayer(final UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (updateWalkingPlayerEvent.getStage() == 1) {
            this.postProcessing();
        }
        if (updateWalkingPlayerEvent.getStage() != 0) {
            return;
        }
        if ((int)this.eventMode.getValue() == 2) {
            this.doAutoCrystal();
        }
    }
    
    @Override
    public void onToggle() {
        AutoCrystal.brokenPos.clear();
        AutoCrystal.placedPos.clear();
        this.totemPops.clear();
        this.rotating = false;
    }
    
    @Override
    public String getDisplayInfo() {
        if (this.switching) {
            return "§aSwitch";
        }
        if (AutoCrystal.target != null) {
            return AutoCrystal.target.getName();
        }
        return null;
    }
    
    public AutoCrystal() {
        super("AutoCrystal", "Best CA made by snow :D", Category.COMBAT, true, false, false);
        this.switchTimer = new Timer();
        this.manualTimer = new Timer();
        this.breakTimer = new Timer();
        this.placeTimer = new Timer();
        this.syncTimer = new Timer();
        this.predictTimer = new Timer();
        this.renderTimer = new Timer();
        this.shouldInterrupt = new AtomicBoolean(false);
        this.syncroTimer = new Timer();
        this.totemPops = new ConcurrentHashMap<EntityPlayer, Timer>();
        this.packetUseEntities = new LinkedList<CPacketUseEntity>();
        this.threadOngoing = new AtomicBoolean(false);
        this.positions = new ArrayList<RenderPos>();
        this.yawStepTimer = new Timer();
        this.setting = (Setting<ACSettings>)this.register(new Setting("Page", (Object)ACSettings.Place));
        this.placeDelay = (Setting<Integer>)this.register(new Setting("PlaceDelay", (Object)18, (Object)0, (Object)500, p0 -> this.setting.getValue() == ACSettings.Place));
        this.placeRange = (Setting<Float>)this.register(new Setting("PlaceRange", (Object)6.0f, (Object)0.0f, (Object)10.0f, p0 -> this.setting.getValue() == ACSettings.Place));
        this.placetrace = (Setting<Float>)this.register(new Setting("PlaceTrace", (Object)3.5f, (Object)0.0f, (Object)10.0f, p0 -> this.setting.getValue() == ACSettings.Place && this.raytrace.getValue() != Raytrace.NONE && this.raytrace.getValue() != Raytrace.BREAK));
        this.minDamage = (Setting<Float>)this.register(new Setting("MinDamage", (Object)2.0f, (Object)0.1f, (Object)20.0f, p0 -> this.setting.getValue() == ACSettings.Place));
        this.maxSelfPlace = (Setting<Float>)this.register(new Setting("MaxSelfPlace", (Object)8.0f, (Object)0.1f, (Object)36.0f, p0 -> this.setting.getValue() == ACSettings.Place));
        this.wasteAmount = (Setting<Integer>)this.register(new Setting("WasteAmount", (Object)5, (Object)1, (Object)5, p0 -> this.setting.getValue() == ACSettings.Place));
        this.wasteMinDmgCount = (Setting<Boolean>)this.register(new Setting("CountMinDmg", (Object)Boolean.TRUE, p0 -> this.setting.getValue() == ACSettings.Place));
        this.facePlace = (Setting<Float>)this.register(new Setting("Face Place Health", (Object)36.0f, (Object)0.1f, (Object)36.0f, p0 -> this.setting.getValue() == ACSettings.Place));
        this.antiSurround = (Setting<Boolean>)this.register(new Setting("AntiSurround", (Object)Boolean.TRUE, p0 -> this.setting.getValue() == ACSettings.Place));
        this.limitFacePlace = (Setting<Boolean>)this.register(new Setting("LimitFacePlace", (Object)Boolean.FALSE, p0 -> this.setting.getValue() == ACSettings.Place));
        this.oneDot15 = (Setting<Boolean>)this.register(new Setting("1.13 Place", (Object)Boolean.FALSE, p0 -> this.setting.getValue() == ACSettings.Place));
        this.doublePop = (Setting<Boolean>)this.register(new Setting("AntiTotem", (Object)Boolean.FALSE, p0 -> this.setting.getValue() == ACSettings.Place));
        this.popHealth = (Setting<Double>)this.register(new Setting("PopHealth", (Object)1.0, (Object)0.0, (Object)3.0, p0 -> this.setting.getValue() == ACSettings.Place && (boolean)this.doublePop.getValue()));
        this.popDamage = (Setting<Float>)this.register(new Setting("PopDamage", (Object)4.0f, (Object)0.0f, (Object)6.0f, p0 -> this.setting.getValue() == ACSettings.Place && (boolean)this.doublePop.getValue()));
        this.popTime = (Setting<Integer>)this.register(new Setting("PopTime", (Object)500, (Object)0, (Object)1000, p0 -> this.setting.getValue() == ACSettings.Place && (boolean)this.doublePop.getValue()));
        this.doublePopOnDamage = (Setting<Boolean>)this.register(new Setting("DamagePop", (Object)Boolean.FALSE, p0 -> this.setting.getValue() == ACSettings.Place && (boolean)this.doublePop.getValue() && this.targetMode.getValue() == Target.DAMAGE));
        this.switchMode = (Setting<Switch>)this.register(new Setting("Attack", (Object)Switch.BREAKSLOT, p0 -> this.setting.getValue() == ACSettings.Break));
        this.breakDelay = (Setting<Integer>)this.register(new Setting("BreakDelay", (Object)18, (Object)0, (Object)500, p0 -> this.setting.getValue() == ACSettings.Break));
        this.breakRange = (Setting<Float>)this.register(new Setting("BreakRange", (Object)6.0f, (Object)0.0f, (Object)10.0f, p0 -> this.setting.getValue() == ACSettings.Break));
        this.breaktrace = (Setting<Float>)this.register(new Setting("BreakTrace", (Object)3.5f, (Object)0.0f, (Object)10.0f, p0 -> this.setting.getValue() == ACSettings.Break && this.raytrace.getValue() != Raytrace.NONE && this.raytrace.getValue() != Raytrace.PLACE));
        this.packets = (Setting<Integer>)this.register(new Setting("Packets", (Object)1, (Object)1, (Object)6, p0 -> this.setting.getValue() == ACSettings.Break));
        this.maxSelfBreak = (Setting<Float>)this.register(new Setting("MaxSelfBreakDmg", (Object)8.0f, (Object)0.1f, (Object)36.0f, p0 -> this.setting.getValue() == ACSettings.Break));
        this.manual = (Setting<Boolean>)this.register(new Setting("ManualBreaker", (Object)Boolean.FALSE, p0 -> this.setting.getValue() == ACSettings.Break));
        this.manualMinDmg = (Setting<Boolean>)this.register(new Setting("ManualMinDmg", (Object)Boolean.TRUE, p0 -> this.setting.getValue() == ACSettings.Break && (boolean)this.manual.getValue()));
        this.manualBreak = (Setting<Integer>)this.register(new Setting("ManualDelay", (Object)500, (Object)0, (Object)500, p0 -> this.setting.getValue() == ACSettings.Break && (boolean)this.manual.getValue()));
        this.sync = (Setting<Boolean>)this.register(new Setting("Sync", (Object)Boolean.FALSE, p0 -> this.setting.getValue() == ACSettings.Break && (boolean)this.manual.getValue()));
        this.instant = (Setting<Boolean>)this.register(new Setting("Predict", (Object)Boolean.TRUE, p0 -> this.setting.getValue() == ACSettings.Break));
        this.instantTimer = (Setting<PredictTimer>)this.register(new Setting("PredictTimer", (Object)PredictTimer.NONE, p0 -> this.setting.getValue() == ACSettings.Break && (boolean)this.instant.getValue()));
        this.resetBreakTimer = (Setting<Boolean>)this.register(new Setting("ResetBreakTimer", (Object)Boolean.TRUE, p0 -> this.setting.getValue() == ACSettings.Break && (boolean)this.instant.getValue()));
        this.predictDelay = (Setting<Integer>)this.register(new Setting("PredictDelay", (Object)12, (Object)0, (Object)500, p0 -> this.setting.getValue() == ACSettings.Break && (boolean)this.instant.getValue() && this.instantTimer.getValue() == PredictTimer.PREDICT));
        this.predictCalc = (Setting<Boolean>)this.register(new Setting("PredictCalc", (Object)Boolean.FALSE, p0 -> this.setting.getValue() == ACSettings.Break && (boolean)this.instant.getValue()));
        this.predictPos = (Setting<Boolean>)this.register(new Setting("PredictPos", (Object)Boolean.FALSE, p0 -> this.setting.getValue() == ACSettings.Break));
        this.predictTicks = (Setting<Integer>)this.register(new Setting("ExtrapolationTicks", (Object)2, (Object)1, (Object)20, p0 -> this.setting.getValue() == ACSettings.Break && (boolean)this.predictPos.getValue()));
        this.predictRotate = (Setting<Boolean>)this.register(new Setting("PredictRotate", (Object)Boolean.FALSE, p0 -> this.setting.getValue() == ACSettings.Break));
        this.predictOffset = (Setting<Float>)this.register(new Setting("PredictOffset", (Object)0.0f, (Object)0.0f, (Object)4.0f, p0 -> this.setting.getValue() == ACSettings.Break));
        this.superSafe = (Setting<Boolean>)this.register(new Setting("SuperSafe", (Object)Boolean.FALSE, p0 -> this.setting.getValue() == ACSettings.Break && (boolean)this.instant.getValue()));
        this.antiCommit = (Setting<Boolean>)this.register(new Setting("AntiOverCommit", (Object)Boolean.FALSE, p0 -> this.setting.getValue() == ACSettings.Break && (boolean)this.instant.getValue()));
        this.antiWeakness = (Setting<Boolean>)this.register(new Setting("AntiWeakness", (Object)Boolean.FALSE, p0 -> this.setting.getValue() == ACSettings.Break));
        this.renderMode = (Setting<ACRenderMode>)this.register(new Setting("RenderMode", (Object)ACRenderMode.STATIC, p0 -> this.setting.getValue() == ACSettings.Render));
        this.rainbowBrightnessAA22 = (Setting<Float>)this.register(new Setting("Brightness ", (Object)150.0f, (Object)1.0f, (Object)255.0f, p0 -> this.setting.getValue() == ACSettings.Render && this.renderMode.getValue() == ACRenderMode.NEWRAINBOW));
        this.rainbowSaturationAA22 = (Setting<Float>)this.register(new Setting("Saturation", (Object)150.0f, (Object)1.0f, (Object)255.0f, p0 -> this.setting.getValue() == ACSettings.Render && this.renderMode.getValue() == ACRenderMode.NEWRAINBOW));
        this.fadeFactor = (Setting<Boolean>)this.register(new Setting("Fade", (Object)true, p0 -> this.setting.getValue() == ACSettings.Render && this.renderMode.getValue() == ACRenderMode.FADE));
        this.scaleFactor = (Setting<Boolean>)this.register(new Setting("Shrink", (Object)false, p0 -> this.setting.getValue() == ACSettings.Render && this.renderMode.getValue() == ACRenderMode.FADE));
        this.slabFactor = (Setting<Boolean>)this.register(new Setting("Slab", (Object)false, p0 -> this.setting.getValue() == ACSettings.Render && this.renderMode.getValue() == ACRenderMode.FADE));
        this.onlyplaced = (Setting<Boolean>)this.register(new Setting("OnlyPlaced", (Object)false, p0 -> this.setting.getValue() == ACSettings.Render && this.renderMode.getValue() == ACRenderMode.FADE));
        this.duration = (Setting<Float>)this.register(new Setting("Duration", (Object)1500.0f, (Object)0.0f, (Object)5000.0f, p0 -> this.setting.getValue() == ACSettings.Render && this.renderMode.getValue() == ACRenderMode.FADE));
        this.max = (Setting<Integer>)this.register(new Setting("MaxPositions", (Object)15, (Object)1, (Object)30, p0 -> this.setting.getValue() == ACSettings.Render && this.renderMode.getValue() == ACRenderMode.FADE));
        this.slabHeight = (Setting<Float>)this.register(new Setting("SlabDepth", (Object)1.0f, (Object)0.1f, (Object)1.0f, p0 -> this.setting.getValue() == ACSettings.Render && (this.renderMode.getValue() == ACRenderMode.STATIC || this.renderMode.getValue() == ACRenderMode.GLIDE)));
        this.moveSpeed = (Setting<Float>)this.register(new Setting("Speed", (Object)900.0f, (Object)0.0f, (Object)1500.0f, p0 -> this.setting.getValue() == ACSettings.Render && this.renderMode.getValue() == ACRenderMode.GLIDE));
        this.accel = (Setting<Float>)this.register(new Setting("Deceleration", (Object)0.8f, (Object)0.0f, (Object)1.0f, p0 -> this.setting.getValue() == ACSettings.Render && this.renderMode.getValue() == ACRenderMode.GLIDE));
        this.colorSync = (Setting<Boolean>)this.register(new Setting("ColorSync", (Object)true, p0 -> this.setting.getValue() == ACSettings.Render));
        this.box = (Setting<Boolean>)this.register(new Setting("Box", (Object)true, p0 -> this.setting.getValue() == ACSettings.Render));
        this.bRed = (Setting<Integer>)this.register(new Setting("BoxRed", (Object)64, (Object)0, (Object)255, p0 -> this.setting.getValue() == ACSettings.Render && (boolean)this.box.getValue()));
        this.bGreen = (Setting<Integer>)this.register(new Setting("BoxGreen", (Object)0, (Object)0, (Object)255, p0 -> this.setting.getValue() == ACSettings.Render && (boolean)this.box.getValue()));
        this.bBlue = (Setting<Integer>)this.register(new Setting("BoxBlue", (Object)145, (Object)0, (Object)255, p0 -> this.setting.getValue() == ACSettings.Render && (boolean)this.box.getValue()));
        this.bAlpha = (Setting<Integer>)this.register(new Setting("BoxAlpha", (Object)105, (Object)0, (Object)255, p0 -> this.setting.getValue() == ACSettings.Render && (boolean)this.box.getValue()));
        this.outline = (Setting<Boolean>)this.register(new Setting("Outline", (Object)true, p0 -> this.setting.getValue() == ACSettings.Render));
        this.oRed = (Setting<Integer>)this.register(new Setting("OutlineRed", (Object)58, (Object)0, (Object)255, p0 -> this.setting.getValue() == ACSettings.Render && (boolean)this.outline.getValue()));
        this.oGreen = (Setting<Integer>)this.register(new Setting("OutlineGreen", (Object)0, (Object)0, (Object)255, p0 -> this.setting.getValue() == ACSettings.Render && (boolean)this.outline.getValue()));
        this.oBlue = (Setting<Integer>)this.register(new Setting("OutlineBlue", (Object)145, (Object)0, (Object)255, p0 -> this.setting.getValue() == ACSettings.Render && (boolean)this.outline.getValue()));
        this.oAlpha = (Setting<Integer>)this.register(new Setting("OutlineAlpha", (Object)111, (Object)0, (Object)255, p0 -> this.setting.getValue() == ACSettings.Render && (boolean)this.outline.getValue()));
        this.lineWidth = (Setting<Float>)this.register(new Setting("LineWidth", (Object)1.8f, (Object)0.1f, (Object)5.0f, p0 -> this.setting.getValue() == ACSettings.Render && (boolean)this.outline.getValue()));
        this.text = (Setting<Boolean>)this.register(new Setting("RenderDmg", (Object)false, p0 -> this.setting.getValue() == ACSettings.Render));
        this.holdFacePlace = (Setting<Boolean>)this.register(new Setting("HoldFacePlace", (Object)Boolean.FALSE, p0 -> this.setting.getValue() == ACSettings.Misc));
        this.holdFaceBreak = (Setting<Boolean>)this.register(new Setting("HoldSlowBreak", (Object)Boolean.FALSE, p0 -> this.setting.getValue() == ACSettings.Misc && (boolean)this.holdFacePlace.getValue()));
        this.slowFaceBreak = (Setting<Boolean>)this.register(new Setting("SlowFaceBreak", (Object)Boolean.FALSE, p0 -> this.setting.getValue() == ACSettings.Misc));
        this.actualSlowBreak = (Setting<Boolean>)this.register(new Setting("ActuallySlow", (Object)Boolean.FALSE, p0 -> this.setting.getValue() == ACSettings.Misc));
        this.facePlaceSpeed = (Setting<Integer>)this.register(new Setting("FaceSpeed", (Object)500, (Object)0, (Object)500, p0 -> this.setting.getValue() == ACSettings.Misc));
        this.antiNaked = (Setting<Boolean>)this.register(new Setting("AntiNaked", (Object)Boolean.FALSE, p0 -> this.setting.getValue() == ACSettings.Misc));
        this.range = (Setting<Float>)this.register(new Setting("TargetRange", (Object)8.0f, (Object)0.1f, (Object)20.0f, p0 -> this.setting.getValue() == ACSettings.Misc));
        this.targetMode = (Setting<Target>)this.register(new Setting("Target", (Object)Target.DAMAGE, p0 -> this.setting.getValue() == ACSettings.Misc));
        this.minArmor = (Setting<Integer>)this.register(new Setting("Armour%", (Object)4, (Object)0, (Object)125, p0 -> this.setting.getValue() == ACSettings.Misc));
        this.autoSwitch = (Setting<ACAutoSwitch>)this.register(new Setting("SwapMode", (Object)ACAutoSwitch.Spoof, p0 -> this.setting.getValue() == ACSettings.Misc));
        this.swapdelay = (Setting<Integer>)this.register(new Setting("SwapDelay", (Object)0, (Object)0, (Object)1000, p0 -> this.setting.getValue() == ACSettings.Misc));
        this.switchBind = (Setting<Bind>)this.register(new Setting("SwitchBind", (Object)new Bind(-1), p0 -> this.setting.getValue() == ACSettings.Misc && this.autoSwitch.getValue() == ACAutoSwitch.Toggle));
        this.lethalSwitch = (Setting<Boolean>)this.register(new Setting("LethalSwitch", (Object)Boolean.FALSE, p0 -> this.setting.getValue() == ACSettings.Misc && this.autoSwitch.getValue() != ACAutoSwitch.None && this.autoSwitch.getValue() != ACAutoSwitch.Spoof));
        this.mineSwitch = (Setting<Boolean>)this.register(new Setting("MineSwitch", (Object)Boolean.TRUE, p0 -> this.setting.getValue() == ACSettings.Misc && this.autoSwitch.getValue() != ACAutoSwitch.None && this.autoSwitch.getValue() != ACAutoSwitch.Spoof));
        this.rotate = (Setting<ACRotate>)this.register(new Setting("Rotate", (Object)ACRotate.OFF, p0 -> this.setting.getValue() == ACSettings.Misc));
        this.rotations = (Setting<Integer>)this.register(new Setting("RotationSpoofs", (Object)1, (Object)1, (Object)20, p0 -> this.setting.getValue() == ACSettings.Misc));
        this.yawstepmode = (Setting<ACYaw>)this.register(new Setting("YawStep", (Object)ACYaw.Full, p0 -> this.setting.getValue() == ACSettings.Misc && this.rotate.getValue() != ACRotate.OFF));
        this.YawStepVal = (Setting<Integer>)this.register(new Setting("YawStepThreshold", (Object)45, (Object)0, (Object)180, p0 -> this.setting.getValue() == ACSettings.Misc && this.rotate.getValue() != ACRotate.OFF && this.yawstepmode.getValue() == ACYaw.Semi));
        this.YawStepTicks = (Setting<Integer>)this.register(new Setting("YawStepTicks", (Object)1, (Object)1, (Object)20, p0 -> this.setting.getValue() == ACSettings.Misc && this.rotate.getValue() != ACRotate.OFF && this.yawstepmode.getValue() == ACYaw.Semi));
        this.suicide = (Setting<Boolean>)this.register(new Setting("IgnoreSelfDamage", (Object)Boolean.FALSE, p0 -> this.setting.getValue() == ACSettings.Misc));
        this.webAttack = (Setting<Boolean>)this.register(new Setting("WebAttack", (Object)Boolean.TRUE, p0 -> this.setting.getValue() == ACSettings.Misc && this.targetMode.getValue() != Target.DAMAGE));
        this.fullCalc = (Setting<Boolean>)this.register(new Setting("ExtraCalc", (Object)Boolean.TRUE, p0 -> this.setting.getValue() == ACSettings.Misc));
        this.logic = (Setting<Logic>)this.register(new Setting("Logic", (Object)Logic.PLACEBREAK, p0 -> this.setting.getValue() == ACSettings.Development));
        this.raytrace = (Setting<Raytrace>)this.register(new Setting("Raytrace", (Object)Raytrace.NONE, p0 -> this.setting.getValue() == ACSettings.Misc));
        this.sound = (Setting<Boolean>)this.register(new Setting("Sound", (Object)Boolean.TRUE, p0 -> this.setting.getValue() == ACSettings.Misc));
        this.soundRange = (Setting<Float>)this.register(new Setting("SoundRange", (Object)12.0f, (Object)0.0f, (Object)12.0f, p0 -> this.setting.getValue() == ACSettings.Misc));
        this.soundPlayer = (Setting<Float>)this.register(new Setting("SoundPlayer", (Object)12.0f, (Object)0.0f, (Object)12.0f, p0 -> this.setting.getValue() == ACSettings.Misc));
        this.soundConfirm = (Setting<Boolean>)this.register(new Setting("SoundConfirm", (Object)Boolean.TRUE, p0 -> this.setting.getValue() == ACSettings.Misc));
        this.extraSelfCalc = (Setting<Boolean>)this.register(new Setting("MinSelfDmg", (Object)Boolean.TRUE, p0 -> this.setting.getValue() == ACSettings.Misc));
        this.antiFriendPop = (Setting<AntiFriendPop>)this.register(new Setting("FriendPop", (Object)AntiFriendPop.NONE, p0 -> this.setting.getValue() == ACSettings.Misc));
        this.noCount = (Setting<Boolean>)this.register(new Setting("AntiCount", (Object)Boolean.FALSE, p0 -> this.setting.getValue() == ACSettings.Misc && (this.antiFriendPop.getValue() == AntiFriendPop.ALL || this.antiFriendPop.getValue() == AntiFriendPop.BREAK)));
        this.calcEvenIfNoDamage = (Setting<Boolean>)this.register(new Setting("BigFriendCalc", (Object)Boolean.FALSE, p0 -> this.setting.getValue() == ACSettings.Misc && (this.antiFriendPop.getValue() == AntiFriendPop.ALL || this.antiFriendPop.getValue() == AntiFriendPop.BREAK) && this.targetMode.getValue() != Target.DAMAGE));
        this.predictFriendDmg = (Setting<Boolean>)this.register(new Setting("PredictFriend", (Object)Boolean.FALSE, p0 -> this.setting.getValue() == ACSettings.Misc && (this.antiFriendPop.getValue() == AntiFriendPop.ALL || this.antiFriendPop.getValue() == AntiFriendPop.BREAK) && (boolean)this.instant.getValue()));
        this.eventMode = (Setting<Integer>)this.register(new Setting("EventUpdates", (Object)3, (Object)1, (Object)3, p0 -> this.setting.getValue() == ACSettings.Development));
        this.attackOppositeHand = (Setting<Boolean>)this.register(new Setting("OppositeHand", (Object)Boolean.FALSE, p0 -> this.setting.getValue() == ACSettings.Development));
        this.removeAfterAttack = (Setting<Boolean>)this.register(new Setting("FastMode", (Object)Boolean.FALSE, p0 -> this.setting.getValue() == ACSettings.Development));
        this.antiBlock = (Setting<Boolean>)this.register(new Setting("AntiFeetPlace", (Object)Boolean.FALSE, p0 -> this.setting.getValue() == ACSettings.Development));
        this.minMinDmg = (Setting<Float>)this.register(new Setting("MinMinDmg", (Object)1.0f, (Object)0.0f, (Object)3.0f, p0 -> this.setting.getValue() == ACSettings.Development));
        this.swingMode = (Setting<ACSwing>)this.register(new Setting("SwingHand", (Object)ACSwing.Both, p0 -> this.setting.getValue() == ACSettings.Development));
        this.exactHand = (Setting<Boolean>)this.register(new Setting("ExactHand", (Object)Boolean.FALSE, p0 -> this.setting.getValue() == ACSettings.Development && (this.swingMode.getValue() == ACSwing.Place || this.swingMode.getValue() == ACSwing.Both)));
        this.packetswing = (Setting<Boolean>)this.register(new Setting("PacketSwing", (Object)Boolean.TRUE, p0 -> this.setting.getValue() == ACSettings.Development));
        this.damageSync = (Setting<DamageSync>)this.register(new Setting("DamageSync", (Object)DamageSync.NONE, p0 -> this.setting.getValue() == ACSettings.Development));
        this.damageSyncTime = (Setting<Integer>)this.register(new Setting("SyncDelay", (Object)0, (Object)0, (Object)500, p0 -> this.setting.getValue() == ACSettings.Development && this.damageSync.getValue() != DamageSync.NONE));
        this.dropOff = (Setting<Float>)this.register(new Setting("DropOff", (Object)5.0f, (Object)0.0f, (Object)10.0f, p0 -> this.setting.getValue() == ACSettings.Development && this.damageSync.getValue() == DamageSync.BREAK));
        this.confirm = (Setting<Integer>)this.register(new Setting("Confirm", (Object)0, (Object)0, (Object)1000, p0 -> this.setting.getValue() == ACSettings.Development && this.damageSync.getValue() != DamageSync.NONE));
        this.syncedFeetPlace = (Setting<Boolean>)this.register(new Setting("FeetSync", (Object)Boolean.FALSE, p0 -> this.setting.getValue() == ACSettings.Development && this.damageSync.getValue() != DamageSync.NONE));
        this.fullSync = (Setting<Boolean>)this.register(new Setting("FullSync", (Object)Boolean.FALSE, p0 -> this.setting.getValue() == ACSettings.Development && this.damageSync.getValue() != DamageSync.NONE && (boolean)this.syncedFeetPlace.getValue()));
        this.syncCount = (Setting<Boolean>)this.register(new Setting("SyncCount", (Object)Boolean.FALSE, p0 -> this.setting.getValue() == ACSettings.Development && this.damageSync.getValue() != DamageSync.NONE && (boolean)this.syncedFeetPlace.getValue()));
        this.hyperSync = (Setting<Boolean>)this.register(new Setting("HyperSync", (Object)Boolean.FALSE, p0 -> this.setting.getValue() == ACSettings.Development && this.damageSync.getValue() != DamageSync.NONE && (boolean)this.syncedFeetPlace.getValue()));
        this.gigaSync = (Setting<Boolean>)this.register(new Setting("GigaSync", (Object)Boolean.FALSE, p0 -> this.setting.getValue() == ACSettings.Development && this.damageSync.getValue() != DamageSync.NONE && (boolean)this.syncedFeetPlace.getValue()));
        this.syncySync = (Setting<Boolean>)this.register(new Setting("SyncySync", (Object)Boolean.FALSE, p0 -> this.setting.getValue() == ACSettings.Development && this.damageSync.getValue() != DamageSync.NONE && (boolean)this.syncedFeetPlace.getValue()));
        this.enormousSync = (Setting<Boolean>)this.register(new Setting("EnormousSync", (Object)Boolean.FALSE, p0 -> this.setting.getValue() == ACSettings.Development && this.damageSync.getValue() != DamageSync.NONE && (boolean)this.syncedFeetPlace.getValue()));
        this.holySync = (Setting<Boolean>)this.register(new Setting("UnbelievableSync", (Object)Boolean.FALSE, p0 -> this.setting.getValue() == ACSettings.Development && this.damageSync.getValue() != DamageSync.NONE && (boolean)this.syncedFeetPlace.getValue()));
        this.rotateFirst = (Setting<Boolean>)this.register(new Setting("FirstRotation", (Object)Boolean.FALSE, p0 -> this.setting.getValue() == ACSettings.Development && this.rotate.getValue() != ACRotate.OFF && (int)this.eventMode.getValue() == 2));
        this.threadMode = (Setting<ThreadMode>)this.register(new Setting("Thread", (Object)ThreadMode.NONE, p0 -> this.setting.getValue() == ACSettings.Development));
        this.threadDelay = (Setting<Integer>)this.register(new Setting("ThreadDelay", (Object)364, (Object)1, (Object)1000, p0 -> this.setting.getValue() == ACSettings.Development && this.threadMode.getValue() != ThreadMode.NONE));
        this.syncThreadBool = (Setting<Boolean>)this.register(new Setting("ThreadSync", (Object)Boolean.TRUE, p0 -> this.setting.getValue() == ACSettings.Development && this.threadMode.getValue() != ThreadMode.NONE));
        this.syncThreads = (Setting<Integer>)this.register(new Setting("SyncThreads", (Object)1000, (Object)1, (Object)10000, p0 -> this.setting.getValue() == ACSettings.Development && this.threadMode.getValue() != ThreadMode.NONE && (boolean)this.syncThreadBool.getValue()));
        this.attackList = new ConcurrentLinkedQueue<Entity>();
        this.crystalMap = new HashMap<Entity, Float>();
        this.lastSlot = -1;
        AutoCrystal.instance = this;
    }
    
    private void mapCrystals() {
        this.efficientTarget = null;
        if ((int)this.packets.getValue() != 1) {
            this.attackList = new ConcurrentLinkedQueue<Entity>();
            this.crystalMap = new HashMap<Entity, Float>();
        }
        this.crystalCount = 0;
        this.minDmgCount = 0;
        Entity efficientTarget = null;
        float n = 0.5f;
        for (final Entity entity : AutoCrystal.mc.world.loadedEntityList) {
            if (!entity.isDead && entity instanceof EntityEnderCrystal) {
                if (!this.isValid(entity)) {
                    continue;
                }
                if ((boolean)this.syncedFeetPlace.getValue() && entity.getPosition().down().equals((Object)this.syncedCrystalPos) && this.damageSync.getValue() != DamageSync.NONE) {
                    ++this.minDmgCount;
                    ++this.crystalCount;
                    if (this.syncCount.getValue()) {
                        this.minDmgCount = (int)this.wasteAmount.getValue() + 1;
                        this.crystalCount = (int)this.wasteAmount.getValue() + 1;
                    }
                    if (!(boolean)this.hyperSync.getValue()) {
                        continue;
                    }
                    efficientTarget = null;
                    break;
                }
                else {
                    boolean b = false;
                    boolean b2 = false;
                    float calculateDamage = -1.0f;
                    if (DamageUtil.canTakeDamage((boolean)this.suicide.getValue())) {
                        calculateDamage = DamageUtil.calculateDamage(entity, (Entity)AutoCrystal.mc.player);
                    }
                    if (calculateDamage + 0.5 < EntityUtil.getHealth((Entity)AutoCrystal.mc.player) && calculateDamage <= (float)this.maxSelfBreak.getValue()) {
                        final Entity entity2 = efficientTarget;
                        final float n2 = n;
                        for (final EntityPlayer entityPlayer : AutoCrystal.mc.world.playerEntities) {
                            if (entityPlayer.getDistanceSq(entity) > MathUtil.square((float)this.range.getValue())) {
                                continue;
                            }
                            if (EntityUtil.isValid((Entity)entityPlayer, (double)((float)this.range.getValue() + (float)this.breakRange.getValue()))) {
                                if ((boolean)this.antiNaked.getValue() && DamageUtil.isNaked(entityPlayer)) {
                                    continue;
                                }
                                final float calculateDamage2;
                                if ((calculateDamage2 = DamageUtil.calculateDamage(entity, (Entity)entityPlayer)) <= calculateDamage && (calculateDamage2 <= (float)this.minDamage.getValue() || DamageUtil.canTakeDamage((boolean)this.suicide.getValue())) && calculateDamage2 <= EntityUtil.getHealth((Entity)entityPlayer)) {
                                    continue;
                                }
                                if (calculateDamage2 > n) {
                                    n = calculateDamage2;
                                    efficientTarget = entity;
                                }
                                if ((int)this.packets.getValue() == 1) {
                                    if (calculateDamage2 >= (float)this.minDamage.getValue() || !(boolean)this.wasteMinDmgCount.getValue()) {
                                        b = true;
                                    }
                                    b2 = true;
                                }
                                else {
                                    if (this.crystalMap.get(entity) != null && this.crystalMap.get(entity) >= calculateDamage2) {
                                        continue;
                                    }
                                    this.crystalMap.put(entity, calculateDamage2);
                                }
                            }
                            else {
                                if ((this.antiFriendPop.getValue() != AntiFriendPop.BREAK && this.antiFriendPop.getValue() != AntiFriendPop.ALL) || !LuigiHack.friendManager.isFriend(entityPlayer.getName())) {
                                    continue;
                                }
                                if (DamageUtil.calculateDamage(entity, (Entity)entityPlayer) <= EntityUtil.getHealth((Entity)entityPlayer) + 0.5) {
                                    continue;
                                }
                                efficientTarget = entity2;
                                n = n2;
                                this.crystalMap.remove(entity);
                                if (!(boolean)this.noCount.getValue()) {
                                    break;
                                }
                                b = false;
                                b2 = false;
                                break;
                            }
                        }
                    }
                    if (!b2) {
                        continue;
                    }
                    ++this.minDmgCount;
                    if (!b) {
                        continue;
                    }
                    ++this.crystalCount;
                }
            }
        }
        if (this.damageSync.getValue() == DamageSync.BREAK && (n > this.lastDamage || this.syncTimer.passedMs((long)(int)this.damageSyncTime.getValue()) || this.damageSync.getValue() == DamageSync.NONE)) {
            this.lastDamage = n;
        }
        if ((boolean)this.enormousSync.getValue() && (boolean)this.syncedFeetPlace.getValue() && this.damageSync.getValue() != DamageSync.NONE && this.syncedCrystalPos != null) {
            if (this.syncCount.getValue()) {
                this.minDmgCount = (int)this.wasteAmount.getValue() + 1;
                this.crystalCount = (int)this.wasteAmount.getValue() + 1;
            }
            return;
        }
        if ((boolean)this.webAttack.getValue() && this.webPos != null) {
            if (AutoCrystal.mc.player.getDistanceSq(this.webPos.up()) > MathUtil.square((float)this.breakRange.getValue())) {
                this.webPos = null;
            }
            else {
                for (final Entity efficientTarget2 : AutoCrystal.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(this.webPos.up()))) {
                    if (!(efficientTarget2 instanceof EntityEnderCrystal)) {
                        continue;
                    }
                    this.attackList.add(efficientTarget2);
                    this.efficientTarget = efficientTarget2;
                    this.webPos = null;
                    this.lastDamage = 0.5;
                    return;
                }
            }
        }
        if (this.shouldSlowBreak(true) && n < (float)this.minDamage.getValue() && (AutoCrystal.target == null || EntityUtil.getHealth((Entity)AutoCrystal.target) > (float)this.facePlace.getValue() || (!this.breakTimer.passedMs((long)(int)this.facePlaceSpeed.getValue()) && (boolean)this.slowFaceBreak.getValue() && Mouse.isButtonDown(0) && (boolean)this.holdFacePlace.getValue() && (boolean)this.holdFaceBreak.getValue()))) {
            this.efficientTarget = null;
            return;
        }
        if ((int)this.packets.getValue() == 1) {
            this.efficientTarget = efficientTarget;
        }
        else {
            this.crystalMap = (Map<Entity, Float>)MathUtil.sortByValue((Map)this.crystalMap, true);
            for (final Map.Entry<Entity, Float> entry : this.crystalMap.entrySet()) {
                final Entity entity3 = entry.getKey();
                if (entry.getValue() >= (float)this.minDamage.getValue() || !(boolean)this.wasteMinDmgCount.getValue()) {
                    ++this.crystalCount;
                }
                this.attackList.add(entity3);
                ++this.minDmgCount;
            }
        }
    }
    
    private boolean predictSlowBreak(final BlockPos blockPos) {
        return (boolean)this.antiCommit.getValue() && AutoCrystal.lowDmgPos.remove(blockPos) && this.shouldSlowBreak(false);
    }
    
    private void attackCrystalPredict(final int entityId, final BlockPos blockPos) {
        if ((boolean)this.predictRotate.getValue() && ((int)this.eventMode.getValue() != 2 || this.threadMode.getValue() != ThreadMode.NONE) && (this.rotate.getValue() == ACRotate.BREAK || this.rotate.getValue() == ACRotate.ALL)) {
            this.rotateToPos(blockPos);
        }
        final CPacketUseEntity cPacketUseEntity = new CPacketUseEntity();
        cPacketUseEntity.entityId = entityId;
        cPacketUseEntity.action = CPacketUseEntity.Action.ATTACK;
        AutoCrystal.mc.player.connection.sendPacket((Packet)cPacketUseEntity);
        if (this.swingMode.getValue() == ACSwing.Break || this.swingMode.getValue() == ACSwing.Both) {
            AutoCrystal.mc.player.connection.sendPacket((Packet)new CPacketAnimation(EnumHand.MAIN_HAND));
        }
        if (this.resetBreakTimer.getValue()) {
            this.breakTimer.reset();
        }
        this.predictTimer.reset();
    }
    
    private void processMultiThreading() {
        if (this.isOff()) {
            return;
        }
        if (this.threadMode.getValue() == ThreadMode.WHILE) {
            this.handleWhile();
        }
        else if (this.threadMode.getValue() != ThreadMode.NONE) {
            this.handlePool(false);
        }
    }
    
    public void postTick() {
        if (this.threadMode.getValue() != ThreadMode.NONE) {
            this.processMultiThreading();
        }
    }
    
    private void breakCrystal() {
        if (this.breakTimer.passedMs((long)(int)this.breakDelay.getValue()) && (this.switchMode.getValue() == Switch.ALWAYS || this.mainHand || this.offHand)) {
            if ((int)this.packets.getValue() == 1 && this.efficientTarget != null) {
                if ((boolean)this.syncedFeetPlace.getValue() && (boolean)this.gigaSync.getValue() && this.syncedCrystalPos != null && this.damageSync.getValue() != DamageSync.NONE) {
                    return;
                }
                this.rotateTo(this.efficientTarget);
                this.attackEntity(this.efficientTarget);
                this.breakTimer.reset();
            }
            else if (!this.attackList.isEmpty()) {
                if ((boolean)this.syncedFeetPlace.getValue() && (boolean)this.gigaSync.getValue() && this.syncedCrystalPos != null && this.damageSync.getValue() != DamageSync.NONE) {
                    return;
                }
                for (int i = 0; i < (int)this.packets.getValue(); ++i) {
                    final Entity entity = this.attackList.poll();
                    if (entity != null) {
                        this.rotateTo(entity);
                        this.attackEntity(entity);
                    }
                }
                this.breakTimer.reset();
            }
        }
    }
    
    public static AutoCrystal getInstance() {
        if (AutoCrystal.instance == null) {
            AutoCrystal.instance = new AutoCrystal();
        }
        return AutoCrystal.instance;
    }
    
    private void removePos(final BlockPos blockPos) {
        if (this.damageSync.getValue() == DamageSync.PLACE) {
            if (AutoCrystal.placedPos.remove(blockPos)) {
                this.posConfirmed = true;
            }
        }
        else if (this.damageSync.getValue() == DamageSync.BREAK && AutoCrystal.brokenPos.remove(blockPos)) {
            this.posConfirmed = true;
        }
    }
    
    private void manualBreaker() {
        if (this.rotate.getValue() != ACRotate.OFF && (int)this.eventMode.getValue() != 2 && this.rotating) {
            if (this.didRotation) {
                AutoCrystal.mc.player.rotationPitch += (float)4.0E-4;
                this.didRotation = false;
            }
            else {
                AutoCrystal.mc.player.rotationPitch -= (float)4.0E-4;
                this.didRotation = true;
            }
        }
        final RayTraceResult objectMouseOver;
        if ((this.offHand || this.mainHand) && (boolean)this.manual.getValue() && this.manualTimer.passedMs((long)(int)this.manualBreak.getValue()) && Mouse.isButtonDown(1) && AutoCrystal.mc.player.getHeldItemOffhand().getItem() != Items.GOLDEN_APPLE && AutoCrystal.mc.player.inventory.getCurrentItem().getItem() != Items.GOLDEN_APPLE && AutoCrystal.mc.player.inventory.getCurrentItem().getItem() != Items.BOW && AutoCrystal.mc.player.inventory.getCurrentItem().getItem() != Items.EXPERIENCE_BOTTLE && (objectMouseOver = AutoCrystal.mc.objectMouseOver) != null) {
            switch (objectMouseOver.typeOfHit) {
                case ENTITY: {
                    final Entity entityHit = objectMouseOver.entityHit;
                    if (!(entityHit instanceof EntityEnderCrystal)) {
                        break;
                    }
                    EntityUtil.attackEntity(entityHit, (boolean)this.sync.getValue(), this.swingMode.getValue() == ACSwing.Break || this.swingMode.getValue() == ACSwing.Both);
                    EntityUtil.OffhandAttack(entityHit, (boolean)this.attackOppositeHand.getValue(), (boolean)this.attackOppositeHand.getValue());
                    this.manualTimer.reset();
                    break;
                }
                case BLOCK: {
                    for (final Entity entity : AutoCrystal.mc.world.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(AutoCrystal.mc.objectMouseOver.getBlockPos().up()))) {
                        if (!(entity instanceof EntityEnderCrystal)) {
                            continue;
                        }
                        EntityUtil.attackEntity(entity, (boolean)this.sync.getValue(), this.swingMode.getValue() == ACSwing.Break || this.swingMode.getValue() == ACSwing.Both);
                        EntityUtil.OffhandAttack(entity, (boolean)this.attackOppositeHand.getValue(), (boolean)this.attackOppositeHand.getValue());
                        this.manualTimer.reset();
                    }
                    break;
                }
            }
        }
    }
    
    private boolean doSwitch() {
        if (AutoCrystal.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) {
            this.mainHand = false;
        }
        else {
            InventoryUtil.switchToHotbarSlot((Class)ItemEndCrystal.class, false);
            this.mainHand = true;
        }
        this.switching = false;
        return true;
    }
    
    private boolean shouldSlowBreak(final boolean b) {
        return (b && (boolean)this.manual.getValue() && (boolean)this.manualMinDmg.getValue() && Mouse.isButtonDown(1) && (!Mouse.isButtonDown(0) || !(boolean)this.holdFacePlace.getValue())) || ((boolean)this.holdFacePlace.getValue() && (boolean)this.holdFaceBreak.getValue() && Mouse.isButtonDown(0) && !this.breakTimer.passedMs((long)(int)this.facePlaceSpeed.getValue())) || ((boolean)this.slowFaceBreak.getValue() && !this.breakTimer.passedMs((long)(int)this.facePlaceSpeed.getValue()));
    }
    
    private void attackEntity(final Entity entity) {
        if (entity != null) {
            if ((int)this.eventMode.getValue() == 2 && this.threadMode.getValue() == ThreadMode.NONE && (boolean)this.rotateFirst.getValue() && this.rotate.getValue() != ACRotate.OFF) {
                this.packetUseEntities.add(new CPacketUseEntity(entity));
            }
            else {
                EntityUtil.attackEntity(entity, (boolean)this.sync.getValue(), this.swingMode.getValue() == ACSwing.Break || this.swingMode.getValue() == ACSwing.Both);
                EntityUtil.OffhandAttack(entity, (boolean)this.attackOppositeHand.getValue(), (boolean)this.attackOppositeHand.getValue());
                AutoCrystal.brokenPos.add(new BlockPos(entity.getPositionVector()).down());
            }
        }
    }
    
    @Override
    public void onRender3D(final Render3DEvent render3DEvent) {
        final Color color = new Color((int)this.bRed.getValue(), (int)this.bGreen.getValue(), (int)this.bBlue.getValue(), (int)this.bAlpha.getValue());
        final Color color2 = new Color((int)this.oRed.getValue(), (int)this.oGreen.getValue(), (int)this.oBlue.getValue(), (int)this.oAlpha.getValue());
        if ((this.offHand || this.mainHand || this.switchMode.getValue() == Switch.CALC) && this.renderPos != null && ((boolean)this.box.getValue() || (boolean)this.outline.getValue())) {
            if (this.renderMode.getValue() == ACRenderMode.FADE) {
                this.positions.removeIf(renderPos -> renderPos.getPos().equals((Object)this.renderPos));
                this.positions.add(new RenderPos(this.renderPos, 0.0f));
            }
            if (this.renderMode.getValue() == ACRenderMode.STATIC) {
                RenderUtil.drawSexyBoxPhobosIsRetardedFuckYouESP(new AxisAlignedBB(this.renderPos), color, color2, (float)this.lineWidth.getValue(), (boolean)this.outline.getValue(), (boolean)this.box.getValue(), (boolean)this.colorSync.getValue(), 1.0f, 1.0f, (float)this.slabHeight.getValue());
            }
            if (this.renderMode.getValue() == ACRenderMode.NEWRAINBOW) {
                final AxisAlignedBB getSelectedBoundingBox = AutoCrystal.mc.world.getBlockState(this.renderPos).getSelectedBoundingBox((World)AutoCrystal.mc.world, this.renderPos);
                final Vec3d interpolateEntity = EntityUtil.interpolateEntity((Entity)RenderUtil.mc.player, AutoCrystal.mc.getRenderPartialTicks());
                final EnumFacing[] values = EnumFacing.values();
                for (int length = values.length, i = 0; i < length; ++i) {
                    RenderUtil.drawGradientPlaneBB(getSelectedBoundingBox.grow(0.0020000000949949026).offset(-interpolateEntity.x, -interpolateEntity.y, -interpolateEntity.z), values[i], new Color(ColorUtil2.rainbowTEST22(50).getRed(), ColorUtil2.rainbowTEST22(50).getGreen(), ColorUtil2.rainbowTEST22(50).getBlue(), 127), ColorUtil2.invert(new Color(ColorUtil2.rainbowTEST22(50).getRed(), ColorUtil2.rainbowTEST22(50).getGreen(), ColorUtil2.rainbowTEST22(50).getBlue(), 127)), 2.0);
                }
                RenderUtil.drawGradientBlockOutline(getSelectedBoundingBox.grow(0.0020000000949949026).offset(-interpolateEntity.x, -interpolateEntity.y, -interpolateEntity.z), ColorUtil2.invert(new Color(ColorUtil2.rainbowTEST22(50).getRed(), ColorUtil2.rainbowTEST22(50).getGreen(), ColorUtil2.rainbowTEST22(50).getBlue(), 255)), new Color(ColorUtil2.rainbowTEST22(50).getRed(), ColorUtil2.rainbowTEST22(50).getGreen(), ColorUtil2.rainbowTEST22(50).getBlue(), 255), 2.0f);
            }
            if (this.renderMode.getValue() == ACRenderMode.GLIDE) {
                if (this.lastRenderPos == null || AutoCrystal.mc.player.getDistance(this.renderBB.minX, this.renderBB.minY, this.renderBB.minZ) > (float)this.range.getValue()) {
                    this.lastRenderPos = this.renderPos;
                    this.renderBB = new AxisAlignedBB(this.renderPos);
                    this.timePassed = 0.0f;
                }
                if (!this.lastRenderPos.equals((Object)this.renderPos)) {
                    this.lastRenderPos = this.renderPos;
                    this.timePassed = 0.0f;
                }
                final double n = this.renderPos.getX() - this.renderBB.minX;
                final double n2 = this.renderPos.getY() - this.renderBB.minY;
                final double n3 = this.renderPos.getZ() - this.renderBB.minZ;
                float n4 = this.timePassed / (float)this.moveSpeed.getValue() * (float)this.accel.getValue();
                if (n4 > 1.0f) {
                    n4 = 1.0f;
                }
                this.renderBB = this.renderBB.offset(n * n4, n2 * n4, n3 * n4);
                RenderUtil.drawSexyBoxPhobosIsRetardedFuckYouESP(this.renderBB, color, color2, (float)this.lineWidth.getValue(), (boolean)this.outline.getValue(), (boolean)this.box.getValue(), (boolean)this.colorSync.getValue(), 1.0f, 1.0f, (float)this.slabHeight.getValue());
                if (this.text.getValue()) {
                    RenderUtil.drawTextWhite(this.renderBB.offset(0.0, 1.0f - (float)this.slabHeight.getValue() / 2.0f - 0.4, 0.0), String.valueOf(new StringBuilder().append((Math.floor(this.renderDamage) == this.renderDamage) ? Integer.valueOf((int)this.renderDamage) : String.format("%.1f", this.renderDamage)).append("")));
                }
                if (this.renderBB.equals((Object)new AxisAlignedBB(this.renderPos))) {
                    this.timePassed = 0.0f;
                }
                else {
                    this.timePassed += 50.0f;
                }
            }
        }
        if (this.renderMode.getValue() == ACRenderMode.FADE) {
            final float n5;
            final Color color3;
            final Color color4;
            this.positions.forEach(renderPos3 -> {
                n5 = ((float)this.duration.getValue() - renderPos3.getRenderTime()) / (float)this.duration.getValue();
                RenderUtil.drawSexyBoxPhobosIsRetardedFuckYouESP(new AxisAlignedBB(renderPos3.getPos()), color3, color4, (float)this.lineWidth.getValue(), (boolean)this.outline.getValue(), (boolean)this.box.getValue(), (boolean)this.colorSync.getValue(), ((boolean)this.fadeFactor.getValue()) ? n5 : 1.0f, ((boolean)this.scaleFactor.getValue()) ? n5 : 1.0f, ((boolean)this.slabFactor.getValue()) ? n5 : 1.0f);
                renderPos3.setRenderTime(renderPos3.getRenderTime() + 50.0f);
                return;
            });
            this.positions.removeIf(renderPos2 -> renderPos2.getRenderTime() >= (float)this.duration.getValue() || AutoCrystal.mc.world.isAirBlock(renderPos2.getPos()) || !AutoCrystal.mc.world.isAirBlock(renderPos2.getPos().offset(EnumFacing.UP)));
            if (this.positions.size() > (int)this.max.getValue()) {
                this.positions.remove(0);
            }
        }
        if ((this.offHand || this.mainHand || this.switchMode.getValue() == Switch.CALC) && this.renderPos != null && (boolean)this.text.getValue() && this.renderMode.getValue() != ACRenderMode.GLIDE) {
            RenderUtil.drawTextWhite(new AxisAlignedBB(this.renderPos).offset(0.0, (this.renderMode.getValue() != ACRenderMode.FADE) ? (1.0f - (float)this.slabHeight.getValue() / 2.0f - 0.4) : 0.1, 0.0), String.valueOf(new StringBuilder().append((Math.floor(this.renderDamage) == this.renderDamage) ? Integer.valueOf((int)this.renderDamage) : String.format("%.1f", this.renderDamage)).append("")));
        }
    }
    
    private void postProcessBreak() {
        while (!this.packetUseEntities.isEmpty()) {
            AutoCrystal.mc.player.connection.sendPacket((Packet)this.packetUseEntities.poll());
            if (this.swingMode.getValue() == ACSwing.Break || this.swingMode.getValue() == ACSwing.Both) {
                AutoCrystal.mc.player.swingArm(EnumHand.MAIN_HAND);
            }
            this.breakTimer.reset();
        }
    }
    
    private boolean shouldHoldFacePlace() {
        this.addTolowDmg = false;
        if ((boolean)this.holdFacePlace.getValue() && Mouse.isButtonDown(0)) {
            this.addTolowDmg = true;
            return true;
        }
        return false;
    }
    
    @Override
    public void onUpdate() {
        if (this.threadMode.getValue() == ThreadMode.NONE && (int)this.eventMode.getValue() == 1) {
            this.doAutoCrystal();
        }
    }
    
    private boolean isRightThread() {
        return AutoCrystal.mc.isCallingFromMinecraftThread() || (!LuigiHack.eventManager.ticksOngoing() && !this.threadOngoing.get());
    }
    
    @SubscribeEvent
    public void onSettingChange(final ClientEvent clientEvent) {
        if (clientEvent.getStage() == 2 && clientEvent.getSetting() != null && clientEvent.getSetting().getFeature() != null && clientEvent.getSetting().getFeature().equals(this) && this.isEnabled() && (clientEvent.getSetting().equals(this.threadDelay) || clientEvent.getSetting().equals(this.threadMode))) {
            if (this.executor != null) {
                this.executor.shutdown();
            }
            if (this.thread != null) {
                this.shouldInterrupt.set(true);
            }
        }
    }
    
    @Override
    public void onEnable() {
        if (this.threadMode.getValue() != ThreadMode.NONE) {
            this.processMultiThreading();
        }
    }
    
    private EntityPlayer getTarget(final boolean b) {
        if (this.targetMode.getValue() == Target.DAMAGE) {
            return null;
        }
        Object o = null;
        for (final EntityPlayer entityPlayer : AutoCrystal.mc.world.playerEntities) {
            if (!EntityUtil.isntValid((Entity)entityPlayer, (double)((float)this.placeRange.getValue() + (float)this.range.getValue())) && (!(boolean)this.antiNaked.getValue() || !DamageUtil.isNaked(entityPlayer))) {
                if (b && EntityUtil.isSafe((Entity)entityPlayer)) {
                    continue;
                }
                if ((int)this.minArmor.getValue() > 0 && DamageUtil.isArmorLow(entityPlayer, (int)this.minArmor.getValue())) {
                    o = entityPlayer;
                    break;
                }
                if (o == null) {
                    o = entityPlayer;
                }
                else {
                    if (AutoCrystal.mc.player.getDistanceSq((Entity)entityPlayer) >= AutoCrystal.mc.player.getDistanceSq((Entity)o)) {
                        continue;
                    }
                    o = entityPlayer;
                }
            }
        }
        if (b && o == null) {
            return this.getTarget(false);
        }
        if ((boolean)this.predictPos.getValue() && o != null) {
            ((EntityPlayer)o).getUniqueID();
            final EntityOtherPlayerMP entityOtherPlayerMP = new EntityOtherPlayerMP((World)AutoCrystal.mc.world, new GameProfile(((EntityPlayer)o).getUniqueID(), ((EntityPlayer)o).getName()));
            final Vec3d extrapolatePlayerPosition = MathUtil.extrapolatePlayerPosition((EntityPlayer)o, (int)this.predictTicks.getValue());
            entityOtherPlayerMP.copyLocationAndAnglesFrom((Entity)o);
            entityOtherPlayerMP.posX = extrapolatePlayerPosition.x;
            entityOtherPlayerMP.posY = extrapolatePlayerPosition.y;
            entityOtherPlayerMP.posZ = extrapolatePlayerPosition.z;
            entityOtherPlayerMP.setHealth(EntityUtil.getHealth((Entity)o));
            entityOtherPlayerMP.inventory.copyInventory(((EntityPlayer)o).inventory);
            o = entityOtherPlayerMP;
        }
        return (EntityPlayer)o;
    }
    
    @SubscribeEvent
    public void onKeyInput(final InputEvent.KeyInputEvent keyInputEvent) {
        if (Keyboard.getEventKeyState() && !(AutoCrystal.mc.currentScreen instanceof LuigiGui) && ((Bind)this.switchBind.getValue()).getKey() == Keyboard.getEventKey()) {
            this.switching = !this.switching;
        }
    }
    
    private boolean isValid(final Entity entity) {
        return entity != null && AutoCrystal.mc.player.getDistanceSq(entity) <= MathUtil.square((float)this.breakRange.getValue()) && (this.raytrace.getValue() == Raytrace.NONE || this.raytrace.getValue() == Raytrace.PLACE || AutoCrystal.mc.player.canEntityBeSeen(entity) || (!AutoCrystal.mc.player.canEntityBeSeen(entity) && AutoCrystal.mc.player.getDistanceSq(entity) <= MathUtil.square((float)this.breaktrace.getValue())));
    }
    
    @Override
    public void onTick() {
        if (this.threadMode.getValue() == ThreadMode.NONE && (int)this.eventMode.getValue() == 3) {
            this.doAutoCrystal();
        }
    }
    
    @Override
    public void onDisable() {
        this.positions.clear();
        this.lastRenderPos = null;
        if (this.thread != null) {
            this.shouldInterrupt.set(true);
        }
        if (this.executor != null) {
            this.executor.shutdown();
        }
    }
    
    private void handleWhile() {
        if (this.thread == null || this.thread.isInterrupted() || !this.thread.isAlive() || (this.syncroTimer.passedMs((long)(int)this.syncThreads.getValue()) && (boolean)this.syncThreadBool.getValue())) {
            if (this.thread == null) {
                this.thread = new Thread(RAutoCrystal.getInstance(this));
            }
            else if (this.syncroTimer.passedMs((long)(int)this.syncThreads.getValue()) && !this.shouldInterrupt.get() && (boolean)this.syncThreadBool.getValue()) {
                this.shouldInterrupt.set(true);
                this.syncroTimer.reset();
                return;
            }
            if (this.thread != null && (this.thread.isInterrupted() || !this.thread.isAlive())) {
                this.thread = new Thread(RAutoCrystal.getInstance(this));
            }
            if (this.thread != null && this.thread.getState() == Thread.State.NEW) {
                try {
                    this.thread.start();
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                this.syncroTimer.reset();
            }
        }
    }
    
    private boolean isEligableForFeetSync(final EntityPlayer entityPlayer, final BlockPos blockPos) {
        if (this.holySync.getValue()) {
            final BlockPos blockPos2 = new BlockPos(entityPlayer.getPositionVector());
            for (final EnumFacing enumFacing : EnumFacing.values()) {
                if (enumFacing != EnumFacing.DOWN && enumFacing != EnumFacing.UP && blockPos.equals((Object)blockPos2.down().offset(enumFacing))) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }
    
    public void doAutoCrystal() {
        if (this.check()) {
            switch ((Logic)this.logic.getValue()) {
                case PLACEBREAK: {
                    this.placeCrystal();
                    this.breakCrystal();
                    break;
                }
                case BREAKPLACE: {
                    this.breakCrystal();
                    this.placeCrystal();
                    break;
                }
            }
            this.manualBreaker();
        }
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        if (send.getStage() == 0 && this.rotate.getValue() != ACRotate.OFF && this.rotating && (int)this.eventMode.getValue() != 2 && send.getPacket() instanceof CPacketPlayer) {
            final CPacketPlayer cPacketPlayer = (CPacketPlayer)send.getPacket();
            cPacketPlayer.yaw = this.yaw;
            cPacketPlayer.pitch = this.pitch;
            ++this.rotationPacketsSpoofed;
            if (this.rotationPacketsSpoofed >= (int)this.rotations.getValue()) {
                this.rotating = false;
                this.rotationPacketsSpoofed = 0;
            }
        }
        BlockPos getPosition = null;
        final CPacketUseEntity cPacketUseEntity;
        if (send.getStage() == 0 && send.getPacket() instanceof CPacketUseEntity && (cPacketUseEntity = (CPacketUseEntity)send.getPacket()).getAction() == CPacketUseEntity.Action.ATTACK && cPacketUseEntity.getEntityFromWorld((World)AutoCrystal.mc.world) instanceof EntityEnderCrystal) {
            getPosition = Objects.requireNonNull(cPacketUseEntity.getEntityFromWorld((World)AutoCrystal.mc.world)).getPosition();
            if (this.removeAfterAttack.getValue()) {
                Objects.requireNonNull(cPacketUseEntity.getEntityFromWorld((World)AutoCrystal.mc.world)).setDead();
                AutoCrystal.mc.world.removeEntityFromWorld(cPacketUseEntity.entityId);
            }
        }
        final CPacketUseEntity cPacketUseEntity2;
        if (send.getStage() == 0 && send.getPacket() instanceof CPacketUseEntity && (cPacketUseEntity2 = (CPacketUseEntity)send.getPacket()).getAction() == CPacketUseEntity.Action.ATTACK && cPacketUseEntity2.getEntityFromWorld((World)AutoCrystal.mc.world) instanceof EntityEnderCrystal) {
            final EntityEnderCrystal entityEnderCrystal = (EntityEnderCrystal)cPacketUseEntity2.getEntityFromWorld((World)AutoCrystal.mc.world);
            if ((boolean)this.antiBlock.getValue() && EntityUtil.isCrystalAtFeet(entityEnderCrystal, (double)(float)this.range.getValue()) && getPosition != null) {
                this.rotateToPos(getPosition);
                BlockUtil.placeCrystalOnBlock(this.placePos, this.offHand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, this.swingMode.getValue() == ACSwing.Place || this.swingMode.getValue() == ACSwing.Both, (boolean)this.exactHand.getValue(), this.shouldSilent);
            }
        }
    }
    
    private boolean switchItem() {
        if (this.offHand || this.mainHand) {
            return true;
        }
        switch ((ACAutoSwitch)this.autoSwitch.getValue()) {
            case None: {
                return false;
            }
            case Toggle: {
                if (!this.switching) {
                    return false;
                }
            }
            case Swap: {
                if (!this.doSwitch()) {
                    break;
                }
                return true;
            }
        }
        return false;
    }
    
    public boolean isActive() {
        return this.isEnabled() && (this.efficientTarget != null || this.placePos != null) && this.isHoldingCrystal();
    }
    
    static {
        AutoCrystal.lowDmgPos = (Set<BlockPos>)new ConcurrentSet();
        AutoCrystal.placedPos = new HashSet<BlockPos>();
        AutoCrystal.brokenPos = new HashSet<BlockPos>();
        AutoCrystal.lowDmgPos = (Set<BlockPos>)new ConcurrentSet();
        AutoCrystal.placedPos = new HashSet<BlockPos>();
        AutoCrystal.brokenPos = new HashSet<BlockPos>();
    }
    
    private void rotateTo(final Entity entity) {
        switch ((ACRotate)this.rotate.getValue()) {
            case OFF: {
                this.rotating = false;
            }
            case BREAK:
            case ALL: {
                final float[] calcAngle = MathUtil.calcAngle(AutoCrystal.mc.player.getPositionEyes(AutoCrystal.mc.getRenderPartialTicks()), entity.getPositionVector());
                if ((int)this.eventMode.getValue() != 2 || this.threadMode.getValue() != ThreadMode.NONE) {
                    this.yaw = calcAngle[0];
                    this.pitch = calcAngle[1];
                    this.rotating = true;
                    break;
                }
                if (this.yawstepmode.getValue() == ACYaw.Semi) {
                    float yaw = LuigiHack.rotationManager.getYaw();
                    while (yaw < calcAngle[1]) {
                        if (AutoCrystal.mc.player.ticksExisted % (int)this.YawStepTicks.getValue() != 0) {
                            continue;
                        }
                        LuigiHack.rotationManager.setPlayerRotations(yaw += (int)this.YawStepVal.getValue(), calcAngle[1]);
                    }
                    break;
                }
                LuigiHack.rotationManager.setPlayerRotations(calcAngle[0], calcAngle[1]);
                break;
            }
        }
    }
    
    private void placeCrystal() {
        int intValue = (int)this.wasteAmount.getValue();
        if (this.placeTimer.passedMs((long)(int)this.placeDelay.getValue()) && (this.offHand || this.mainHand || this.switchMode.getValue() == Switch.CALC || (this.switchMode.getValue() == Switch.BREAKSLOT && this.switching))) {
            if ((this.offHand || this.mainHand || (this.switchMode.getValue() != Switch.ALWAYS && !this.switching)) && this.crystalCount >= intValue && (!(boolean)this.antiSurround.getValue() || this.lastPos == null || !this.lastPos.equals((Object)this.placePos))) {
                return;
            }
            this.calculateDamage(this.getTarget(this.targetMode.getValue() == Target.UNSAFE));
            if (AutoCrystal.target != null && this.placePos != null) {
                if (!this.offHand && !this.mainHand && this.autoSwitch.getValue() != ACAutoSwitch.None && (this.currentDamage > (float)this.minDamage.getValue() || ((boolean)this.lethalSwitch.getValue() && EntityUtil.getHealth((Entity)AutoCrystal.target) <= (float)this.facePlace.getValue())) && !this.switchItem()) {
                    return;
                }
                if (this.currentDamage < (float)this.minDamage.getValue() && (boolean)this.limitFacePlace.getValue()) {
                    intValue = 1;
                }
                if (this.currentDamage >= (float)this.minMinDmg.getValue() && (this.offHand || this.mainHand || this.autoSwitch.getValue() != ACAutoSwitch.None) && (this.crystalCount < intValue || ((boolean)this.antiSurround.getValue() && this.lastPos != null && this.lastPos.equals((Object)this.placePos))) && (this.currentDamage > (float)this.minDamage.getValue() || this.minDmgCount < intValue) && this.currentDamage >= 1.0 && (DamageUtil.isArmorLow(AutoCrystal.target, (int)this.minArmor.getValue()) || EntityUtil.getHealth((Entity)AutoCrystal.target) <= (float)this.facePlace.getValue() || this.currentDamage > (float)this.minDamage.getValue() || this.shouldHoldFacePlace())) {
                    final float n = (this.damageSync.getValue() == DamageSync.BREAK) ? ((float)this.dropOff.getValue() - 5.0f) : 0.0f;
                    boolean b = false;
                    if ((boolean)this.syncedFeetPlace.getValue() && this.placePos.equals((Object)this.lastPos) && this.isEligableForFeetSync(AutoCrystal.target, this.placePos) && !this.syncTimer.passedMs((long)(int)this.damageSyncTime.getValue()) && AutoCrystal.target.equals((Object)this.currentSyncTarget) && AutoCrystal.target.getPosition().equals((Object)this.syncedPlayerPos) && this.damageSync.getValue() != DamageSync.NONE) {
                        this.syncedCrystalPos = this.placePos;
                        this.lastDamage = this.currentDamage;
                        if (this.fullSync.getValue()) {
                            this.lastDamage = 100.0;
                        }
                        b = true;
                    }
                    if (b || this.currentDamage - n > this.lastDamage || this.syncTimer.passedMs((long)(int)this.damageSyncTime.getValue()) || this.damageSync.getValue() == DamageSync.NONE) {
                        if (!b && this.damageSync.getValue() != DamageSync.BREAK) {
                            this.lastDamage = this.currentDamage;
                        }
                        if (!(boolean)this.onlyplaced.getValue()) {
                            this.renderPos = this.placePos;
                        }
                        this.renderDamage = this.currentDamage;
                        if (this.switchItem()) {
                            this.currentSyncTarget = AutoCrystal.target;
                            this.syncedPlayerPos = AutoCrystal.target.getPosition();
                            if (this.foundDoublePop) {
                                this.totemPops.put(AutoCrystal.target, new Timer().reset());
                            }
                            this.rotateToPos(this.placePos);
                            if (this.addTolowDmg || ((boolean)this.actualSlowBreak.getValue() && this.currentDamage < (float)this.minDamage.getValue())) {
                                AutoCrystal.lowDmgPos.add(this.placePos);
                            }
                            AutoCrystal.placedPos.add(this.placePos);
                            if ((int)this.eventMode.getValue() == 2 && this.threadMode.getValue() == ThreadMode.NONE && (boolean)this.rotateFirst.getValue() && this.rotate.getValue() != ACRotate.OFF) {
                                this.placeInfo = new PlaceInfo(this.placePos, this.offHand, this.swingMode.getValue() == ACSwing.Place || this.swingMode.getValue() == ACSwing.Both, (boolean)this.exactHand.getValue(), this.shouldSilent);
                            }
                            else {
                                BlockUtil.placeCrystalOnBlockNew(this.placePos, this.offHand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, this.swingMode.getValue() == ACSwing.Place || this.swingMode.getValue() == ACSwing.Both, (boolean)this.exactHand.getValue(), (boolean)this.packetswing.getValue(), this.shouldSilent);
                                AutoCrystal.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(this.placePos, EnumFacing.UP, AutoCrystal.mc.player.getHeldItemOffhand().getItem().equals(Items.END_CRYSTAL) ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.5f, 1.0f, 0.5f));
                            }
                            this.lastPos = this.placePos;
                            this.placeTimer.reset();
                            this.posConfirmed = false;
                            if (this.syncTimer.passedMs((long)(int)this.damageSyncTime.getValue())) {
                                this.syncedCrystalPos = null;
                                this.syncTimer.reset();
                            }
                        }
                    }
                }
            }
            else {
                this.renderPos = null;
            }
        }
    }
    
    public enum DamageSync
    {
        PLACE, 
        NONE, 
        BREAK;
    }
    
    public enum ThreadMode
    {
        WHILE, 
        SOUND, 
        POOL, 
        NONE;
    }
    
    public enum AntiFriendPop
    {
        PLACE, 
        NONE, 
        BREAK, 
        ALL;
    }
    
    public enum Switch
    {
        CALC, 
        ALWAYS, 
        BREAKSLOT;
    }
    
    public enum Raytrace
    {
        BREAK, 
        NONE, 
        FULL, 
        PLACE;
    }
    
    public enum Target
    {
        UNSAFE, 
        DAMAGE, 
        CLOSEST;
    }
    
    private static class RAutoCrystal implements Runnable
    {
        private static /* synthetic */ RAutoCrystal instance;
        private /* synthetic */ AutoCrystal autoCrystal;
        
        public static RAutoCrystal getInstance(final AutoCrystal autoCrystal) {
            if (RAutoCrystal.instance == null) {
                RAutoCrystal.instance = new RAutoCrystal();
                RAutoCrystal.instance.autoCrystal = autoCrystal;
            }
            return RAutoCrystal.instance;
        }
        
        @Override
        public void run() {
            if (this.autoCrystal.threadMode.getValue() == ThreadMode.WHILE) {
                while (this.autoCrystal.isOn() && this.autoCrystal.threadMode.getValue() == ThreadMode.WHILE) {
                    while (LuigiHack.eventManager.ticksOngoing()) {}
                    if (this.autoCrystal.shouldInterrupt.get()) {
                        this.autoCrystal.shouldInterrupt.set(false);
                        this.autoCrystal.syncroTimer.reset();
                        this.autoCrystal.thread.interrupt();
                        break;
                    }
                    this.autoCrystal.threadOngoing.set(true);
                    LuigiHack.safetyManager.doSafetyCheck();
                    this.autoCrystal.doAutoCrystal();
                    this.autoCrystal.threadOngoing.set(false);
                    try {
                        Thread.sleep((int)this.autoCrystal.threadDelay.getValue());
                    }
                    catch (InterruptedException ex) {
                        this.autoCrystal.thread.interrupt();
                        ex.printStackTrace();
                    }
                }
            }
            else if (this.autoCrystal.threadMode.getValue() != ThreadMode.NONE && this.autoCrystal.isOn()) {
                while (LuigiHack.eventManager.ticksOngoing()) {}
                this.autoCrystal.threadOngoing.set(true);
                LuigiHack.safetyManager.doSafetyCheck();
                this.autoCrystal.doAutoCrystal();
                this.autoCrystal.threadOngoing.set(false);
            }
        }
    }
    
    private class RenderPos
    {
        private /* synthetic */ BlockPos renderPos;
        private /* synthetic */ float renderTime;
        
        public void setRenderTime(final float renderTime) {
            this.renderTime = renderTime;
        }
        
        public float getRenderTime() {
            return this.renderTime;
        }
        
        public void setPos(final BlockPos renderPos) {
            this.renderPos = renderPos;
        }
        
        public BlockPos getPos() {
            return this.renderPos;
        }
        
        public RenderPos(final BlockPos renderPos, final float renderTime) {
            this.renderPos = renderPos;
            this.renderTime = renderTime;
        }
    }
    
    public static class PlaceInfo
    {
        private final /* synthetic */ boolean exactHand;
        private final /* synthetic */ boolean placeSwing;
        private final /* synthetic */ boolean silent;
        private final /* synthetic */ BlockPos pos;
        private final /* synthetic */ boolean offhand;
        
        public void runPlace() {
            BlockUtil.placeCrystalOnBlock(this.pos, this.offhand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, this.placeSwing, this.exactHand, this.silent);
        }
        
        public PlaceInfo(final BlockPos pos, final boolean offhand, final boolean placeSwing, final boolean exactHand, final boolean silent) {
            this.pos = pos;
            this.offhand = offhand;
            this.placeSwing = placeSwing;
            this.exactHand = exactHand;
            this.silent = silent;
        }
    }
    
    public enum PredictTimer
    {
        NONE, 
        PREDICT, 
        BREAK;
    }
    
    public enum Logic
    {
        BREAKPLACE, 
        PLACEBREAK;
    }
}
