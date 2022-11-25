//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.combat;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.player.*;
import java.awt.*;
import net.minecraft.entity.*;
import org.jetbrains.annotations.*;
import me.snow.luigihack.api.event.processor.*;
import net.minecraft.potion.*;
import me.snow.luigihack.impl.command.*;
import net.minecraft.item.*;
import net.minecraft.network.*;
import me.snow.luigihack.*;
import me.snow.luigihack.impl.modules.combat.autocrystal.*;
import me.snow.luigihack.api.util.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraft.network.play.client.*;
import net.minecraft.world.*;
import net.minecraft.init.*;
import net.minecraft.util.*;
import net.minecraft.network.play.server.*;
import me.snow.luigihack.api.util.ca.util.*;
import net.minecraft.util.math.*;
import java.util.*;
import java.util.stream.*;

public final class CrystalAura extends Module
{
    private final /* synthetic */ Setting<Double> height;
    private /* synthetic */ long start;
    private final /* synthetic */ Setting<Integer> fadeTime;
    private final /* synthetic */ Setting<Boolean> threaded;
    public /* synthetic */ Setting<Aswing> swing;
    private /* synthetic */ int rotationPacketsSpoofed;
    private /* synthetic */ boolean placeTimeoutFlag;
    private final /* synthetic */ Setting<Integer> minBreak;
    private final /* synthetic */ Setting<Boolean> fade;
    private final /* synthetic */ Setting<Double> targetRange;
    private final /* synthetic */ TimerCa crystalsPlacedTimer;
    private final /* synthetic */ Setting<Integer> breakDelay;
    public /* synthetic */ Setting<Integer> renderBoxColourgreen;
    public /* synthetic */ Setting<Integer> renderFillColouralpha;
    private final /* synthetic */ Setting<Boolean> placeSwing;
    private final /* synthetic */ Setting<Integer> maxSelfPlace;
    private final /* synthetic */ Setting<Integer> facePlaceDelay;
    public final /* synthetic */ Setting<Bind> fpbind;
    private final /* synthetic */ Setting<Boolean> antiStuck;
    public /* synthetic */ Setting<AautoSwitch> autoSwitch;
    private /* synthetic */ int crystalsPlaced;
    private final /* synthetic */ List<EntityEnderCrystal> attemptedCrystals;
    private /* synthetic */ int placeDelayCounter;
    private final /* synthetic */ Setting<Double> breakRange;
    private final /* synthetic */ Setting<Boolean> antiWeakness;
    private final /* synthetic */ Setting<Integer> maxCrystals;
    private /* synthetic */ EntityEnderCrystal stuckCrystal;
    public /* synthetic */ Setting<Rotate> rotate;
    private final /* synthetic */ Setting<Integer> predictedTicks;
    private /* synthetic */ boolean rotating;
    private final /* synthetic */ Setting<Integer> maxSelfBreak;
    private final /* synthetic */ Setting<Integer> width;
    private final /* synthetic */ Setting<Boolean> ignoreTerrain;
    private final /* synthetic */ Setting<Boolean> predictCrystal;
    public /* synthetic */ Setting<ApredictTeleport> predictTeleport;
    private /* synthetic */ int breakTimeout;
    public /* synthetic */ EntityPlayer ezTarget;
    public /* synthetic */ ArrayList<BlockPos> staticPos;
    private final /* synthetic */ Setting<Boolean> renderDamage;
    public /* synthetic */ Setting<Integer> renderFillColourblue;
    public /* synthetic */ Setting<Integer> renderBoxColouralpha;
    private final /* synthetic */ Setting<Boolean> antiSuicide;
    private final /* synthetic */ Setting<Boolean> noBreakCalcs;
    private /* synthetic */ boolean didAnything;
    private final /* synthetic */ Setting<Integer> placeDelay;
    private final /* synthetic */ Setting<Boolean> ObiYCheck;
    private /* synthetic */ boolean facePlacing;
    public /* synthetic */ Setting<Amode> mode;
    private final /* synthetic */ Setting<Double> placeRange;
    public /* synthetic */ Setting<Integer> renderFillColourred;
    private final /* synthetic */ Setting<Boolean> attackPacket;
    private final /* synthetic */ Setting<Integer> minPlace;
    private final /* synthetic */ Setting<Boolean> silentSwitchHand;
    private final /* synthetic */ Setting<Boolean> raytrace;
    public /* synthetic */ Setting<Integer> renderBoxColourred;
    private final /* synthetic */ Setting<Boolean> ignoreSelfDamage;
    private final /* synthetic */ Setting<Boolean> rotateObiFeet;
    public /* synthetic */ Setting<AfastMode> fastMode;
    private /* synthetic */ int placeTimeout;
    public /* synthetic */ Setting<Integer> renderFillColourgreen;
    private final /* synthetic */ Setting<Boolean> flat;
    public static /* synthetic */ CrystalAura INSTANCE;
    public /* synthetic */ Setting<AcrystalLogic> crystalLogic;
    private final /* synthetic */ ArrayList<BlockPos> currentTargets;
    private final /* synthetic */ Setting<Boolean> noMP;
    private final /* synthetic */ Setting<Boolean> sortBlocks;
    private final /* synthetic */ ArrayList<RenderPos> renderMap;
    public /* synthetic */ Setting<Awhen> when;
    public /* synthetic */ Setting<ArotateMode> rotateMode;
    private final /* synthetic */ Setting<Integer> facePlaceHP;
    private final /* synthetic */ Setting<Boolean> palceObiFeet;
    private final /* synthetic */ Setting<Boolean> debug;
    private final /* synthetic */ Setting<Integer> maxYaw;
    private /* synthetic */ boolean alreadyAttacking;
    private final /* synthetic */ Setting<Double> placeRangeWall;
    public /* synthetic */ Setting<AarrayListMode> arrayListMode;
    private final /* synthetic */ Setting<Boolean> thirteen;
    private /* synthetic */ float pitch;
    private /* synthetic */ long crystalLatency;
    private final /* synthetic */ Setting<Integer> fuckArmourHP;
    private /* synthetic */ int facePlaceDelayCounter;
    private final /* synthetic */ Setting<Double> breakRangeWall;
    private /* synthetic */ int obiFeetCounter;
    private final /* synthetic */ Setting<Boolean> predictBlock;
    private final /* synthetic */ Setting<Boolean> entityPredict;
    public /* synthetic */ Setting<Integer> rotations;
    public /* synthetic */ Setting<Integer> renderBoxColourblue;
    private final /* synthetic */ Setting<Integer> timeoutTicksObiFeet;
    private /* synthetic */ float yaw;
    private final /* synthetic */ Setting<Integer> maxAntiStuckDamage;
    private /* synthetic */ int breakDelayCounter;
    private final /* synthetic */ Setting<Boolean> packetSafe;
    private /* synthetic */ boolean hasPacketBroke;
    public /* synthetic */ EntityEnderCrystal staticEnderCrystal;
    
    @Override
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (this.renderMap.isEmpty()) {
            return;
        }
        boolean b = false;
        boolean b2 = false;
        switch ((Amode)this.mode.getValue()) {
            case Pretty: {
                b = true;
                b2 = true;
                break;
            }
            case Solid: {
                b = false;
                b2 = true;
                break;
            }
            case Outline: {
                b = true;
                b2 = false;
                break;
            }
        }
        final ArrayList<RenderPos> c = new ArrayList<RenderPos>();
        for (final RenderPos renderPos : this.renderMap) {
            int intValue = (int)this.renderFillColouralpha.getValue();
            int intValue2 = (int)this.renderBoxColouralpha.getValue();
            if (this.currentTargets.contains(renderPos.pos)) {
                renderPos.fadeTimer = 0.0;
            }
            else if (!(boolean)this.fade.getValue()) {
                c.add(renderPos);
            }
            else {
                final RenderPos renderPos2 = renderPos;
                ++renderPos2.fadeTimer;
                intValue -= (int)(intValue * (renderPos.fadeTimer / (int)this.fadeTime.getValue()));
                intValue2 -= (int)(intValue2 * (renderPos.fadeTimer / (int)this.fadeTime.getValue()));
            }
            if (renderPos.fadeTimer > (int)this.fadeTime.getValue()) {
                c.add(renderPos);
            }
            if (c.contains(renderPos)) {
                continue;
            }
            RenderUtilCa.drawBoxESP(((boolean)this.flat.getValue()) ? new BlockPos(renderPos.pos.getX(), renderPos.pos.getY() + 1, renderPos.pos.getZ()) : renderPos.pos, (Color)new Colour((int)this.renderFillColourred.getValue(), (int)this.renderFillColourgreen.getValue(), (int)this.renderFillColourblue.getValue(), Math.max(intValue, 0)), (Color)new Colour((int)this.renderBoxColourred.getValue(), (int)this.renderBoxColourgreen.getValue(), (int)this.renderBoxColourblue.getValue(), Math.max(intValue2, 0)), (float)(int)this.width.getValue(), b, b2, true, ((boolean)this.flat.getValue()) ? ((double)this.height.getValue()) : 0.0, false, false, false, false, 0);
        }
        this.renderMap.removeAll(c);
    }
    
    private void blockObiNextToPlayer(final EntityPlayer entityPlayer) {
        if ((boolean)this.ObiYCheck.getValue() && Math.floor(entityPlayer.posY) == Math.floor(CrystalAura.mc.player.posY)) {
            return;
        }
        this.obiFeetCounter = 0;
        final BlockPos down = EntityUtilCa.getFlooredPos((Entity)entityPlayer).down();
        if (EntityUtilCa.isInHole((Entity)entityPlayer) || CrystalAura.mc.world.getBlockState(down).getBlock() == Blocks.AIR) {
            return;
        }
        for (int i = -1; i <= 1; ++i) {
            for (int j = -1; j <= 1; ++j) {
                if (i != 0 || j != 0) {
                    final BlockPos add = down.add(i, 0, j);
                    if (CrystalAura.mc.world.getBlockState(add).getMaterial().isReplaceable()) {
                        BlockUtilCa.placeBlock(add, PlayerUtilCa.findObiInHotbar(), (boolean)this.rotateObiFeet.getValue(), (boolean)this.rotateObiFeet.getValue());
                    }
                }
            }
        }
    }
    
    @CommitEvent(priority = EventPriority.HIGH)
    public void onUpdateWalkingPlayer(@NotNull final UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (updateWalkingPlayerEvent.getStage() == 0 && (this.rotateMode.getValue() == ArotateMode.Break || this.rotateMode.getValue() == ArotateMode.Place || this.rotateMode.getValue() == ArotateMode.Both)) {
            this.doCrystalAura();
        }
    }
    
    public CrystalAura() {
        super("CrystalAura", "best ca i think bro.", Category.COMBAT, true, false, false);
        this.breakRange = (Setting<Double>)this.register(new Setting("Break Range", (Object)5.0, (Object)0.0, (Object)6.0));
        this.placeRange = (Setting<Double>)this.register(new Setting("Place Range", (Object)5.0, (Object)0.0, (Object)6.0));
        this.breakRangeWall = (Setting<Double>)this.register(new Setting("Break Range Wall", (Object)3.0, (Object)0.0, (Object)6.0));
        this.placeRangeWall = (Setting<Double>)this.register(new Setting("Place Range Wall", (Object)3.0, (Object)0.0, (Object)6.0));
        this.targetRange = (Setting<Double>)this.register(new Setting("Target Range", (Object)15.0, (Object)0.0, (Object)20.0));
        this.placeDelay = (Setting<Integer>)this.register(new Setting("Place Delay", (Object)0, (Object)0, (Object)10));
        this.breakDelay = (Setting<Integer>)this.register(new Setting("Break Delay", (Object)0, (Object)0, (Object)10));
        this.sortBlocks = (Setting<Boolean>)this.register(new Setting("Sort Blocks", (Object)true));
        this.ignoreSelfDamage = (Setting<Boolean>)this.register(new Setting("Ignore Self", (Object)false));
        this.minPlace = (Setting<Integer>)this.register(new Setting("MinPlace", (Object)9, (Object)0, (Object)36));
        this.maxSelfPlace = (Setting<Integer>)this.register(new Setting("MaxSelfPlace", (Object)5, (Object)0, (Object)36, p0 -> !(boolean)this.ignoreSelfDamage.getValue()));
        this.minBreak = (Setting<Integer>)this.register(new Setting("MinBreak", (Object)9, (Object)0, (Object)36));
        this.maxSelfBreak = (Setting<Integer>)this.register(new Setting("MaxSelfBreak", (Object)5, (Object)0, (Object)36, p0 -> !(boolean)this.ignoreSelfDamage.getValue()));
        this.antiSuicide = (Setting<Boolean>)this.register(new Setting("Anti Suicide", (Object)true));
        this.rotate = (Setting<Rotate>)this.register(new Setting("ARotate", (Object)Rotate.OFF));
        this.rotations = (Setting<Integer>)this.register(new Setting("RotationSpoofs", (Object)1, (Object)1, (Object)20));
        this.rotateMode = (Setting<ArotateMode>)this.register(new Setting("Rotate", (Object)ArotateMode.Off));
        this.maxYaw = (Setting<Integer>)this.register(new Setting("MaxYaw", (Object)45, (Object)0, (Object)180));
        this.raytrace = (Setting<Boolean>)this.register(new Setting("Raytrace", (Object)false));
        this.fastMode = (Setting<AfastMode>)this.register(new Setting("Fast", (Object)AfastMode.Ignore));
        this.autoSwitch = (Setting<AautoSwitch>)this.register(new Setting("Switch", (Object)AautoSwitch.None));
        this.silentSwitchHand = (Setting<Boolean>)this.register(new Setting("Hand Activation", (Object)true, p0 -> this.autoSwitch.getValue() == AautoSwitch.Silent));
        this.antiWeakness = (Setting<Boolean>)this.register(new Setting("Anti Weakness", (Object)true));
        this.maxCrystals = (Setting<Integer>)this.register(new Setting("MaxCrystal", (Object)1, (Object)1, (Object)4));
        this.ignoreTerrain = (Setting<Boolean>)this.register(new Setting("Terrain Trace", (Object)true));
        this.crystalLogic = (Setting<AcrystalLogic>)this.register(new Setting("Placements", (Object)AcrystalLogic.Damage));
        this.thirteen = (Setting<Boolean>)this.register(new Setting("1.13", (Object)false));
        this.attackPacket = (Setting<Boolean>)this.register(new Setting("AttackPacket", (Object)true));
        this.packetSafe = (Setting<Boolean>)this.register(new Setting("Packet Safe", (Object)true));
        this.noBreakCalcs = (Setting<Boolean>)this.register(new Setting("No Break Calcs", (Object)false));
        this.arrayListMode = (Setting<AarrayListMode>)this.register(new Setting("Array List Mode", (Object)AarrayListMode.Latency));
        this.debug = (Setting<Boolean>)this.register(new Setting("Debug", (Object)false));
        this.threaded = (Setting<Boolean>)this.register(new Setting("Threaded", (Object)false));
        this.antiStuck = (Setting<Boolean>)this.register(new Setting("Anti Stuck", (Object)false));
        this.maxAntiStuckDamage = (Setting<Integer>)this.register(new Setting("Stuck Self Damage", (Object)8, (Object)0, (Object)36, p0 -> (boolean)this.antiStuck.getValue()));
        this.predictCrystal = (Setting<Boolean>)this.register(new Setting("Predict Crystal", (Object)true));
        this.predictBlock = (Setting<Boolean>)this.register(new Setting("Predict Block", (Object)true));
        this.predictTeleport = (Setting<ApredictTeleport>)this.register(new Setting("P Teleport", (Object)ApredictTeleport.Sound));
        this.entityPredict = (Setting<Boolean>)this.register(new Setting("Entity Predict", (Object)true, p0 -> this.rotateMode.getValue() == ArotateMode.Off));
        this.predictedTicks = (Setting<Integer>)this.register(new Setting("Predict Ticks", (Object)2, (Object)0, (Object)5, p0 -> (boolean)this.entityPredict.getValue() && this.rotateMode.getValue() == ArotateMode.Off));
        this.palceObiFeet = (Setting<Boolean>)this.register(new Setting("Obifeet Enabled", (Object)false));
        this.ObiYCheck = (Setting<Boolean>)this.register(new Setting("Obifeet YCheck", (Object)false));
        this.rotateObiFeet = (Setting<Boolean>)this.register(new Setting("Obifeet Rotate", (Object)false));
        this.timeoutTicksObiFeet = (Setting<Integer>)this.register(new Setting("Timeout", (Object)3, (Object)0, (Object)5));
        this.noMP = (Setting<Boolean>)this.register(new Setting("NoMultiPlace", (Object)false));
        this.facePlaceHP = (Setting<Integer>)this.register(new Setting("FacePlaceHP", (Object)30, (Object)0, (Object)36));
        this.facePlaceDelay = (Setting<Integer>)this.register(new Setting("TFacePlaceDelay", (Object)0, (Object)0, (Object)10));
        this.fpbind = (Setting<Bind>)this.register(new Setting("FacePlace Bind", (Object)new Bind(-1)));
        this.fuckArmourHP = (Setting<Integer>)this.register(new Setting("Armour%", (Object)0, (Object)0, (Object)100));
        this.when = (Setting<Awhen>)this.register(new Setting("When", (Object)Awhen.Place));
        this.mode = (Setting<Amode>)this.register(new Setting("Mode", (Object)Amode.Pretty));
        this.fade = (Setting<Boolean>)this.register(new Setting("Fade", (Object)false));
        this.fadeTime = (Setting<Integer>)this.register(new Setting("FadeTime", (Object)200, (Object)0, (Object)1000, p0 -> (boolean)this.fade.getValue()));
        this.flat = (Setting<Boolean>)this.register(new Setting("Flat", (Object)false));
        this.height = (Setting<Double>)this.register(new Setting("FlatHeight", (Object)0.2, (Object)(-2.0), (Object)2.0, p0 -> (boolean)this.flat.getValue()));
        this.width = (Setting<Integer>)this.register(new Setting("Width", (Object)1, (Object)1, (Object)10));
        this.renderFillColourred = (Setting<Integer>)this.register(new Setting("FillColour Red", (Object)0, (Object)0, (Object)255));
        this.renderFillColourgreen = (Setting<Integer>)this.register(new Setting("FillColour Green", (Object)0, (Object)0, (Object)255));
        this.renderFillColourblue = (Setting<Integer>)this.register(new Setting("FillColour Blue", (Object)0, (Object)0, (Object)255));
        this.renderFillColouralpha = (Setting<Integer>)this.register(new Setting("FillColour Alpha", (Object)255, (Object)0, (Object)255));
        this.renderBoxColourred = (Setting<Integer>)this.register(new Setting("BoxColour Red", (Object)255, (Object)0, (Object)255));
        this.renderBoxColourgreen = (Setting<Integer>)this.register(new Setting("BoxColour Green", (Object)255, (Object)0, (Object)255));
        this.renderBoxColourblue = (Setting<Integer>)this.register(new Setting("BoxColour Blue", (Object)255, (Object)0, (Object)255));
        this.renderBoxColouralpha = (Setting<Integer>)this.register(new Setting("BoxColour Alpha", (Object)255, (Object)0, (Object)255));
        this.renderDamage = (Setting<Boolean>)this.register(new Setting("RenderDamage", (Object)true));
        this.swing = (Setting<Aswing>)this.register(new Setting("Swing", (Object)Aswing.Mainhand));
        this.placeSwing = (Setting<Boolean>)this.register(new Setting("Place Swing", (Object)true));
        this.attemptedCrystals = new ArrayList<EntityEnderCrystal>();
        this.renderMap = new ArrayList<RenderPos>();
        this.currentTargets = new ArrayList<BlockPos>();
        this.crystalsPlacedTimer = new TimerCa();
        this.yaw = 0.0f;
        this.pitch = 0.0f;
        this.rotating = false;
        CrystalAura.INSTANCE = this;
    }
    
    private void breakCrystalNoCalcs() {
        for (final Entity entity : CrystalAura.mc.world.loadedEntityList) {
            if (!(entity instanceof EntityEnderCrystal)) {
                continue;
            }
            if (entity.isDead) {
                continue;
            }
            if (CrystalAura.mc.player.getDistance(entity) > (double)this.breakRange.getValue()) {
                continue;
            }
            if (!CrystalAura.mc.player.canEntityBeSeen(entity)) {
                if (this.raytrace.getValue()) {
                    continue;
                }
                if (CrystalAura.mc.player.getDistance(entity) > (double)this.breakRangeWall.getValue()) {
                    continue;
                }
            }
            if ((boolean)this.antiWeakness.getValue() && CrystalAura.mc.player.isPotionActive(MobEffects.WEAKNESS)) {
                boolean b = true;
                if (CrystalAura.mc.player.isPotionActive(MobEffects.STRENGTH) && Objects.requireNonNull(CrystalAura.mc.player.getActivePotionEffect(MobEffects.STRENGTH)).getAmplifier() == 2) {
                    b = false;
                }
                if (b) {
                    if (!this.alreadyAttacking) {
                        this.alreadyAttacking = true;
                    }
                    int currentItem = -1;
                    for (int i = 0; i < 9; ++i) {
                        final ItemStack getStackInSlot = CrystalAura.mc.player.inventory.getStackInSlot(i);
                        if (getStackInSlot.getItem() instanceof ItemSword || getStackInSlot.getItem() instanceof ItemTool) {
                            currentItem = i;
                            CrystalAura.mc.playerController.updateController();
                            break;
                        }
                    }
                    if (currentItem != -1) {
                        CrystalAura.mc.player.inventory.currentItem = currentItem;
                    }
                }
            }
            final EntityEnderCrystal yawPitch = (EntityEnderCrystal)entity;
            if (this.setYawPitch(yawPitch)) {
                EntityUtilCa.attackEntity((Entity)yawPitch, (boolean)this.attackPacket.getValue());
                if (this.swing.getValue() == Aswing.Mainhand || this.swing.getValue() == Aswing.Offhand) {}
                if (this.debug.getValue()) {
                    Command.sendMessage("breaking");
                }
                this.breakDelayCounter = 0;
            }
            else if (this.debug.getValue()) {
                Command.sendMessage("doing yawstep on break");
            }
            this.rotateTo((Entity)yawPitch);
        }
    }
    
    private void placeCrystal() {
        final ArrayList<BlockPos> bestBlocks = this.getBestBlocks();
        this.currentTargets.clear();
        this.currentTargets.addAll(bestBlocks);
        if (bestBlocks == null) {
            return;
        }
        if (bestBlocks.size() > 0) {
            boolean b = false;
            final int hotbarBlock = InventoryUtil.findHotbarBlock((Class)ItemEndCrystal.class);
            final int currentItem = CrystalAura.mc.player.inventory.currentItem;
            EnumHand getActiveHand = null;
            final int crystalCount = this.getCrystalCount(false);
            this.alreadyAttacking = false;
            if (CrystalAura.mc.player.getHeldItemOffhand().getItem() != Items.END_CRYSTAL) {
                if (CrystalAura.mc.player.getHeldItemMainhand().getItem() != Items.END_CRYSTAL && (this.autoSwitch.getValue() == AautoSwitch.Allways || this.autoSwitch.getValue() == AautoSwitch.NoGap)) {
                    if (this.autoSwitch.getValue() == AautoSwitch.NoGap && CrystalAura.mc.player.getHeldItemMainhand().getItem() == Items.GOLDEN_APPLE) {
                        return;
                    }
                    if (this.findCrystalsHotbar() == -1) {
                        return;
                    }
                    CrystalAura.mc.player.inventory.currentItem = this.findCrystalsHotbar();
                    CrystalAura.mc.playerController.syncCurrentPlayItem();
                }
            }
            else {
                b = true;
            }
            if (this.autoSwitch.getValue() == AautoSwitch.Silent && hotbarBlock != -1) {
                if (CrystalAura.mc.player.isHandActive() && (boolean)this.silentSwitchHand.getValue()) {
                    getActiveHand = CrystalAura.mc.player.getActiveHand();
                }
                CrystalAura.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(hotbarBlock));
            }
            this.placeDelayCounter = 0;
            this.facePlaceDelayCounter = 0;
            this.didAnything = true;
            for (final BlockPos yawPitch : bestBlocks) {
                if (CrystalAura.mc.player.getHeldItemMainhand().getItem() instanceof ItemEndCrystal || CrystalAura.mc.player.getHeldItemOffhand().getItem() instanceof ItemEndCrystal || this.autoSwitch.getValue() == AautoSwitch.Silent) {
                    if (!this.setYawPitch(yawPitch)) {
                        continue;
                    }
                    final EntityEnderCrystal crystalStuck = CrystalUtilCa.isCrystalStuck(yawPitch.up());
                    if (crystalStuck != null && (boolean)this.antiStuck.getValue()) {
                        this.stuckCrystal = crystalStuck;
                        if (this.debug.getValue()) {
                            Command.sendMessage("SHITS STUCK");
                        }
                    }
                    this.rotateToPos(yawPitch);
                    BlockUtilCa.placeCrystalOnBlock(yawPitch, b ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, (boolean)this.placeSwing.getValue());
                    if (this.debug.getValue()) {
                        Command.sendMessage("placing");
                    }
                    ++this.crystalsPlaced;
                }
                else {
                    if (!(boolean)this.debug.getValue()) {
                        continue;
                    }
                    Command.sendMessage("doing yawstep on place");
                }
            }
            final int crystalCount2 = this.getCrystalCount(b);
            if (this.autoSwitch.getValue() == AautoSwitch.Silent && hotbarBlock != -1) {
                CrystalAura.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(currentItem));
                if ((boolean)this.silentSwitchHand.getValue() && getActiveHand != null) {
                    CrystalAura.mc.player.setActiveHand(getActiveHand);
                }
            }
            if (crystalCount2 == crystalCount) {
                this.didAnything = false;
            }
        }
    }
    
    private int findCrystalsHotbar() {
        for (int i = 0; i < 9; ++i) {
            if (CrystalAura.mc.player.inventory.getStackInSlot(i).getItem() == Items.END_CRYSTAL) {
                return i;
            }
        }
        return -1;
    }
    
    private ArrayList<BlockPos> getBlockedPositions(final BlockPos blockPos) {
        final ArrayList<BlockPos> list = new ArrayList<BlockPos>();
        list.add(blockPos.add(1, -1, 1));
        list.add(blockPos.add(1, -1, -1));
        list.add(blockPos.add(-1, -1, 1));
        list.add(blockPos.add(-1, -1, -1));
        list.add(blockPos.add(-1, -1, 0));
        list.add(blockPos.add(1, -1, 0));
        list.add(blockPos.add(0, -1, -1));
        list.add(blockPos.add(0, -1, 1));
        list.add(blockPos.add(1, 0, 1));
        list.add(blockPos.add(1, 0, -1));
        list.add(blockPos.add(-1, 0, 1));
        list.add(blockPos.add(-1, 0, -1));
        list.add(blockPos.add(-1, 0, 0));
        list.add(blockPos.add(1, 0, 0));
        list.add(blockPos.add(0, 0, -1));
        list.add(blockPos.add(0, 0, 1));
        list.add(blockPos.add(1, 1, 1));
        list.add(blockPos.add(1, 1, -1));
        list.add(blockPos.add(-1, 1, 1));
        list.add(blockPos.add(-1, 1, -1));
        list.add(blockPos.add(-1, 1, 0));
        list.add(blockPos.add(1, 1, 0));
        list.add(blockPos.add(0, 1, -1));
        list.add(blockPos.add(0, 1, 1));
        return list;
    }
    
    private boolean isPlayerValid(@NotNull final EntityPlayer entityPlayer) {
        if (entityPlayer.getHealth() + entityPlayer.getAbsorptionAmount() <= 0.0f || entityPlayer == CrystalAura.mc.player) {
            return false;
        }
        if (LuigiHack.friendManager.isFriend(entityPlayer.getName())) {
            return false;
        }
        if (entityPlayer.getName().equals(CrystalAura.mc.player.getName())) {
            return false;
        }
        if (entityPlayer.getDistanceSq((Entity)CrystalAura.mc.player) > 169.0) {
            return false;
        }
        if ((boolean)this.palceObiFeet.getValue() && this.obiFeetCounter >= (int)this.timeoutTicksObiFeet.getValue() && CrystalAura.mc.player.getDistance((Entity)entityPlayer) < 5.0f) {
            try {
                this.blockObiNextToPlayer(entityPlayer);
            }
            catch (ConcurrentModificationException ex) {
                ex.printStackTrace();
            }
        }
        return true;
    }
    
    private void rotateTo(final Entity entity) {
        switch ((Rotate)this.rotate.getValue()) {
            case OFF: {
                this.rotating = false;
            }
            case BREAK:
            case ALL: {
                final float[] calcAngle = MathUtil.calcAngle(AutoCrystal.mc.player.getPositionEyes(CrystalAura.mc.getRenderPartialTicks()), entity.getPositionVector());
                this.yaw = calcAngle[0];
                this.pitch = calcAngle[1];
                this.rotating = true;
                break;
            }
        }
    }
    
    private int getCrystalCount(final boolean b) {
        if (b) {
            return CrystalAura.mc.player.getHeldItemOffhand().stackSize;
        }
        return CrystalAura.mc.player.getHeldItemMainhand().stackSize;
    }
    
    private void clearMap(final BlockPos blockPos) {
        final ArrayList<RenderPos> c = new ArrayList<RenderPos>();
        if (blockPos == null || this.renderMap.isEmpty()) {
            return;
        }
        for (final RenderPos renderPos : this.renderMap) {
            if (renderPos.pos.getX() == blockPos.getX() && renderPos.pos.getY() == blockPos.getY() && renderPos.pos.getZ() == blockPos.getZ()) {
                c.add(renderPos);
            }
        }
        this.renderMap.removeAll(c);
    }
    
    public void doCrystalAura() {
        if (nullCheck()) {
            this.disable();
            return;
        }
        this.didAnything = false;
        if (this.placeDelayCounter > this.placeTimeout && (this.facePlaceDelayCounter >= (int)this.facePlaceDelay.getValue() || !this.facePlacing)) {
            this.start = System.currentTimeMillis();
            this.placeCrystal();
        }
        if (this.breakDelayCounter > this.breakTimeout && (!this.hasPacketBroke || !(boolean)this.packetSafe.getValue())) {
            if (this.debug.getValue()) {
                Command.sendMessage("Attempting break");
            }
            if (this.noBreakCalcs.getValue()) {
                this.breakCrystalNoCalcs();
            }
            else if ((boolean)this.antiStuck.getValue() && this.stuckCrystal != null) {
                this.breakCrystal(this.stuckCrystal);
                this.stuckCrystal = null;
            }
            else {
                this.breakCrystal(null);
            }
        }
        if (!this.didAnything) {
            this.hasPacketBroke = false;
        }
        ++this.breakDelayCounter;
        ++this.placeDelayCounter;
        ++this.facePlaceDelayCounter;
        ++this.obiFeetCounter;
    }
    
    private void rotateToPos(final BlockPos blockPos) {
        switch ((Rotate)this.rotate.getValue()) {
            case OFF: {
                this.rotating = false;
            }
            case PLACE:
            case ALL: {
                final float[] calcAngle = MathUtil.calcAngle(AutoCrystal.mc.player.getPositionEyes(CrystalAura.mc.getRenderPartialTicks()), new Vec3d((double)(blockPos.getX() + 0.5f), (double)(blockPos.getY() - 0.5f), (double)(blockPos.getZ() + 0.5f)));
                this.yaw = calcAngle[0];
                this.pitch = calcAngle[1];
                this.rotating = true;
                break;
            }
        }
    }
    
    private void breakCrystal(final EntityEnderCrystal entityEnderCrystal) {
        EntityEnderCrystal yawPitch;
        if (this.threaded.getValue()) {
            new Threads().start();
            yawPitch = this.staticEnderCrystal;
        }
        else {
            yawPitch = this.getBestCrystal();
        }
        if (entityEnderCrystal != null) {
            if (this.debug.getValue()) {
                Command.sendMessage("Overwriting Crystal");
            }
            if (CrystalUtilCa.calculateDamage((Entity)entityEnderCrystal, (Entity)CrystalAura.mc.player, (boolean)this.ignoreTerrain.getValue()) < (int)this.maxAntiStuckDamage.getValue()) {
                yawPitch = entityEnderCrystal;
            }
        }
        if (yawPitch == null) {
            return;
        }
        if ((boolean)this.antiWeakness.getValue() && CrystalAura.mc.player.isPotionActive(MobEffects.WEAKNESS)) {
            boolean b = true;
            if (CrystalAura.mc.player.isPotionActive(MobEffects.STRENGTH) && Objects.requireNonNull(CrystalAura.mc.player.getActivePotionEffect(MobEffects.STRENGTH)).getAmplifier() == 2) {
                b = false;
            }
            if (b) {
                if (!this.alreadyAttacking) {
                    this.alreadyAttacking = true;
                }
                int currentItem = -1;
                for (int i = 0; i < 9; ++i) {
                    final ItemStack getStackInSlot = CrystalAura.mc.player.inventory.getStackInSlot(i);
                    if (getStackInSlot.getItem() instanceof ItemSword || getStackInSlot.getItem() instanceof ItemTool) {
                        currentItem = i;
                        CrystalAura.mc.playerController.updateController();
                        break;
                    }
                }
                if (currentItem != -1) {
                    CrystalAura.mc.player.inventory.currentItem = currentItem;
                }
            }
        }
        this.rotateTo((Entity)yawPitch);
        this.didAnything = true;
        if (this.setYawPitch(yawPitch)) {
            EntityUtilCa.attackEntity((Entity)yawPitch, (boolean)this.attackPacket.getValue());
            if (this.swing.getValue() == Aswing.Mainhand || this.swing.getValue() == Aswing.Offhand) {}
            if (this.debug.getValue()) {
                Command.sendMessage("breaking");
            }
            this.breakDelayCounter = 0;
        }
        else if (this.debug.getValue()) {
            Command.sendMessage("doing yawstep on break");
        }
    }
    
    @Override
    public void onUpdate() {
        if (this.rotateMode.getValue() == ArotateMode.Off) {
            this.doCrystalAura();
        }
    }
    
    @CommitEvent(priority = EventPriority.HIGH)
    public void onPacketSend(@NotNull final PacketEvent.Send send) {
        if (send.getStage() == 0 && this.rotate.getValue() != Rotate.OFF && this.rotating && send.getPacket() instanceof CPacketPlayer) {
            final CPacketPlayer cPacketPlayer = (CPacketPlayer)send.getPacket();
            cPacketPlayer.yaw = this.yaw;
            cPacketPlayer.pitch = this.pitch;
            ++this.rotationPacketsSpoofed;
            if (this.rotationPacketsSpoofed >= (int)this.rotations.getValue()) {
                this.rotating = false;
                this.rotationPacketsSpoofed = 0;
            }
        }
        final CPacketUseEntity cPacketUseEntity;
        if (send.getStage() == 0 && send.getPacket() instanceof CPacketUseEntity && (cPacketUseEntity = (CPacketUseEntity)send.getPacket()).getAction() == CPacketUseEntity.Action.ATTACK && cPacketUseEntity.getEntityFromWorld((World)CrystalAura.mc.world) instanceof EntityEnderCrystal && this.fastMode.getValue() == AfastMode.Ghost) {
            Objects.requireNonNull(cPacketUseEntity.getEntityFromWorld((World)CrystalAura.mc.world)).setDead();
            CrystalAura.mc.world.removeEntityFromWorld(cPacketUseEntity.entityId);
        }
    }
    
    @CommitEvent(priority = EventPriority.HIGH)
    public void onPacketReceive(@NotNull final PacketEvent.Receive receive) {
        final SPacketSpawnObject sPacketSpawnObject;
        if (receive.getPacket() instanceof SPacketSpawnObject && (sPacketSpawnObject = (SPacketSpawnObject)receive.getPacket()).getType() == 51 && (boolean)this.predictCrystal.getValue()) {
            final Iterator<EntityPlayer> iterator = new ArrayList<EntityPlayer>(CrystalAura.mc.world.playerEntities).iterator();
            while (iterator.hasNext()) {
                if (this.isCrystalGood(new EntityEnderCrystal((World)CrystalAura.mc.world, sPacketSpawnObject.getX(), sPacketSpawnObject.getY(), sPacketSpawnObject.getZ()), iterator.next()) != 0.0) {
                    if (this.debug.getValue()) {
                        Command.sendMessage("predict break");
                    }
                    final CPacketUseEntity cPacketUseEntity = new CPacketUseEntity();
                    cPacketUseEntity.entityId = sPacketSpawnObject.getEntityID();
                    cPacketUseEntity.action = CPacketUseEntity.Action.ATTACK;
                    CrystalAura.mc.player.connection.sendPacket((Packet)cPacketUseEntity);
                    if (this.swing.getValue() == Aswing.Mainhand || this.swing.getValue() == Aswing.Offhand) {}
                    if (this.packetSafe.getValue()) {
                        this.hasPacketBroke = true;
                        this.didAnything = true;
                        break;
                    }
                    break;
                }
            }
        }
        if (receive.getPacket() instanceof SPacketEntityTeleport) {
            final SPacketEntityTeleport sPacketEntityTeleport = (SPacketEntityTeleport)receive.getPacket();
            final Entity getEntityByID = CrystalAura.mc.world.getEntityByID(sPacketEntityTeleport.getEntityId());
            if (getEntityByID == CrystalAura.mc.player) {
                return;
            }
            if (getEntityByID instanceof EntityPlayer && this.predictTeleport.getValue() == ApredictTeleport.Packet) {
                getEntityByID.setEntityBoundingBox(getEntityByID.getEntityBoundingBox().offset(sPacketEntityTeleport.getX(), sPacketEntityTeleport.getY(), sPacketEntityTeleport.getZ()));
            }
        }
        if (receive.getPacket() instanceof SPacketSoundEffect) {
            final SPacketSoundEffect sPacketSoundEffect = (SPacketSoundEffect)receive.getPacket();
            if (sPacketSoundEffect.getSound() == SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT && this.predictTeleport.getValue() == ApredictTeleport.Sound) {
                final SPacketSoundEffect sPacketSoundEffect2;
                CrystalAura.mc.world.loadedEntityList.spliterator().forEachRemaining(entityPlayerSP -> {
                    if (entityPlayerSP instanceof EntityPlayer && entityPlayerSP != CrystalAura.mc.player && ((Entity)entityPlayerSP).getDistance(sPacketSoundEffect2.getX(), sPacketSoundEffect2.getY(), sPacketSoundEffect2.getZ()) <= (double)this.targetRange.getValue()) {
                        ((Entity)entityPlayerSP).setEntityBoundingBox(((Entity)entityPlayerSP).getEntityBoundingBox().offset(sPacketSoundEffect2.getX(), sPacketSoundEffect2.getY(), sPacketSoundEffect2.getZ()));
                    }
                    return;
                });
            }
            try {
                if (sPacketSoundEffect.getCategory() == SoundCategory.BLOCKS && sPacketSoundEffect.getSound() == SoundEvents.ENTITY_GENERIC_EXPLODE) {
                    for (final Entity entity : new ArrayList<Entity>(CrystalAura.mc.world.loadedEntityList)) {
                        if (entity instanceof EntityEnderCrystal && entity.getDistance(sPacketSoundEffect.getX(), sPacketSoundEffect.getY(), sPacketSoundEffect.getZ()) <= (double)this.breakRange.getValue()) {
                            this.crystalLatency = System.currentTimeMillis() - this.start;
                            if (this.fastMode.getValue() != AfastMode.Sound) {
                                continue;
                            }
                            entity.setDead();
                        }
                    }
                }
            }
            catch (NullPointerException ex) {}
        }
        if (receive.getPacket() instanceof SPacketExplosion) {
            final SPacketExplosion sPacketExplosion = (SPacketExplosion)receive.getPacket();
            final BlockPos down = new BlockPos(Math.floor(sPacketExplosion.getX()), Math.floor(sPacketExplosion.getY()), Math.floor(sPacketExplosion.getZ())).down();
            if (this.predictBlock.getValue()) {
                final Iterator<EntityPlayer> iterator3 = new ArrayList<EntityPlayer>(CrystalAura.mc.world.playerEntities).iterator();
                while (iterator3.hasNext()) {
                    if (this.isBlockGood(down, iterator3.next()) > 0.0) {
                        BlockUtilCa.placeCrystalOnBlock(down, EnumHand.MAIN_HAND, true);
                    }
                }
            }
        }
    }
    
    private boolean setYawPitch(@NotNull final EntityEnderCrystal entityEnderCrystal) {
        if (this.rotateMode.getValue() == ArotateMode.Off || this.rotateMode.getValue() == ArotateMode.Place) {
            return true;
        }
        final float[] calcAngle = MathsUtilCa.calcAngle(CrystalAura.mc.player.getPositionEyes(CrystalAura.mc.getRenderPartialTicks()), entityEnderCrystal.getPositionEyes(CrystalAura.mc.getRenderPartialTicks()));
        final float n = calcAngle[0];
        final float n2 = calcAngle[1];
        final float spoofedYaw = LuigiHack.newrotationManager.getSpoofedYaw();
        if (Math.abs(spoofedYaw - n) > (int)this.maxYaw.getValue() && Math.abs(spoofedYaw - 360.0f - n) > (int)this.maxYaw.getValue() && Math.abs(spoofedYaw + 360.0f - n) > (int)this.maxYaw.getValue()) {
            LuigiHack.newrotationManager.setPlayerRotations((Math.abs(spoofedYaw - n) < 180.0f) ? ((spoofedYaw > n) ? (spoofedYaw - (int)this.maxYaw.getValue()) : (spoofedYaw + (int)this.maxYaw.getValue())) : ((spoofedYaw > n) ? (spoofedYaw + (int)this.maxYaw.getValue()) : (spoofedYaw - (int)this.maxYaw.getValue())), n2);
            return false;
        }
        LuigiHack.newrotationManager.setPlayerRotations(n, n2);
        return true;
    }
    
    public boolean setYawPitch(@NotNull final BlockPos blockPos) {
        if (this.rotateMode.getValue() == ArotateMode.Off || this.rotateMode.getValue() == ArotateMode.Break) {
            return true;
        }
        final float[] calcAngle = MathsUtilCa.calcAngle(CrystalAura.mc.player.getPositionEyes(CrystalAura.mc.getRenderPartialTicks()), new Vec3d((double)(blockPos.getX() + 0.5f), (double)(blockPos.getY() + 0.5f), (double)(blockPos.getZ() + 0.5f)));
        final float n = calcAngle[0];
        final float n2 = calcAngle[1];
        final float spoofedYaw = LuigiHack.newrotationManager.getSpoofedYaw();
        if (Math.abs(spoofedYaw - n) > (int)this.maxYaw.getValue() && Math.abs(spoofedYaw - 360.0f - n) > (int)this.maxYaw.getValue() && Math.abs(spoofedYaw + 360.0f - n) > (int)this.maxYaw.getValue()) {
            LuigiHack.newrotationManager.setPlayerRotations((Math.abs(spoofedYaw - n) < 180.0f) ? ((spoofedYaw > n) ? (spoofedYaw - (int)this.maxYaw.getValue()) : (spoofedYaw + (int)this.maxYaw.getValue())) : ((spoofedYaw > n) ? (spoofedYaw + (int)this.maxYaw.getValue()) : (spoofedYaw - (int)this.maxYaw.getValue())), n2);
            return false;
        }
        LuigiHack.newrotationManager.setPlayerRotations(n, n2);
        return true;
    }
    
    private ArrayList<BlockPos> getBestBlocks() {
        final ArrayList<RenderPos> list = new ArrayList<RenderPos>();
        if (this.getBestCrystal() != null && this.fastMode.getValue() == AfastMode.Off) {
            this.placeTimeoutFlag = true;
            return null;
        }
        if (this.placeTimeoutFlag) {
            this.placeTimeoutFlag = false;
            return null;
        }
        for (final EntityPlayer entityPlayer : new ArrayList<EntityPlayer>(CrystalAura.mc.world.playerEntities)) {
            if (CrystalAura.mc.player.getDistanceSq((Entity)entityPlayer) > MathsUtilCa.square(((Double)this.targetRange.getValue()).floatValue())) {
                continue;
            }
            if (this.entityPredict.getValue()) {
                final float n = entityPlayer.width / 2.0f;
                entityPlayer.setEntityBoundingBox(new AxisAlignedBB(entityPlayer.posX - n, entityPlayer.posY, entityPlayer.posZ - n, entityPlayer.posX + n, entityPlayer.posY + entityPlayer.height, entityPlayer.posZ + n));
                entityPlayer.setEntityBoundingBox(CrystalUtilCa.getPredictedPosition((Entity)entityPlayer, (double)(int)this.predictedTicks.getValue()).getEntityBoundingBox());
            }
            for (final BlockPos blockPos : CrystalUtilCa.possiblePlacePositions(((Double)this.placeRange.getValue()).floatValue(), true, (boolean)this.thirteen.getValue())) {
                final double blockGood = this.isBlockGood(blockPos, entityPlayer);
                if (blockGood <= 0.0) {
                    continue;
                }
                list.add(new RenderPos(blockPos, blockGood));
            }
        }
        if (this.sortBlocks.getValue()) {
            list.sort(new DamageComparator());
        }
        if ((int)this.maxCrystals.getValue() > 1) {
            final ArrayList<BlockPos> list2 = new ArrayList<BlockPos>();
            final ArrayList<Object> c = new ArrayList<Object>();
            for (final RenderPos renderPos : list) {
                boolean b = false;
                for (final BlockPos blockPos2 : list2) {
                    if (blockPos2.getX() == renderPos.pos.getX() && blockPos2.getY() == renderPos.pos.getY() && blockPos2.getZ() == renderPos.pos.getZ()) {
                        b = true;
                    }
                }
                if (!b) {
                    list2.addAll((Collection<?>)this.getBlockedPositions(renderPos.pos));
                }
                else {
                    c.add(renderPos);
                }
            }
            list.removeAll(c);
        }
        if (this.ezTarget != null) {}
        int intValue = (int)this.maxCrystals.getValue();
        if (this.facePlacing && (boolean)this.noMP.getValue()) {
            intValue = 1;
        }
        final ArrayList<BlockPos> list3 = new ArrayList<BlockPos>();
        final ArrayList<RenderPos> list4;
        final RenderPos e;
        final ArrayList<BlockPos> list5;
        IntStream.range(0, Math.min(intValue, list.size())).forEachOrdered(index -> {
            e = list4.get(index);
            if (this.when.getValue() == Awhen.Both || this.when.getValue() == Awhen.Place) {
                this.clearMap(e.pos);
                if (e.pos != null) {
                    this.renderMap.add(e);
                }
            }
            list5.add(e.pos);
            return;
        });
        return list3;
    }
    
    @Override
    public String getDisplayInfo() {
        switch ((AarrayListMode)this.arrayListMode.getValue()) {
            case Latency: {
                return String.valueOf(new StringBuilder().append(this.crystalLatency).append("ms"));
            }
            case CPS: {
                return String.valueOf(new StringBuilder().append("").append(MathsUtilCa.round((double)this.getCPS(), 2)));
            }
            case Player: {
                return (this.ezTarget != null) ? this.ezTarget.getName() : null;
            }
            default: {
                return "";
            }
        }
    }
    
    @Override
    public void onEnable() {
        this.placeTimeout = (int)this.placeDelay.getValue();
        this.breakTimeout = (int)this.breakDelay.getValue();
        this.placeTimeoutFlag = false;
        this.ezTarget = null;
        this.facePlacing = false;
        this.attemptedCrystals.clear();
        this.hasPacketBroke = false;
        this.alreadyAttacking = false;
        this.obiFeetCounter = 0;
        this.crystalLatency = 0L;
        this.start = 0L;
        this.staticEnderCrystal = null;
        this.staticPos = null;
        this.crystalsPlaced = 0;
        this.crystalsPlacedTimer.reset();
    }
    
    private double isBlockGood(@NotNull final BlockPos blockPos, @NotNull final EntityPlayer entityPlayer) {
        if (this.isPlayerValid(entityPlayer)) {
            if (!CrystalUtilCa.canSeePos(blockPos) && (boolean)this.raytrace.getValue()) {
                return 0.0;
            }
            if (!CrystalUtilCa.canSeePos(blockPos)) {
                if (CrystalAura.mc.player.getDistanceSq(blockPos) > MathsUtilCa.square(((Double)this.placeRangeWall.getValue()).floatValue())) {
                    return 0.0;
                }
            }
            else if (CrystalAura.mc.player.getDistanceSq(blockPos) > MathsUtilCa.square(((Double)this.placeRange.getValue()).floatValue())) {
                return 0.0;
            }
            final double n = CrystalUtilCa.calculateDamage(blockPos, (Entity)entityPlayer, (boolean)this.ignoreTerrain.getValue());
            this.facePlacing = false;
            double n2 = (int)this.minPlace.getValue();
            if ((EntityUtilCa.getHealth((Entity)entityPlayer) <= (int)this.facePlaceHP.getValue() || CrystalUtilCa.getArmourFucker(entityPlayer, (float)(int)this.fuckArmourHP.getValue()) || ((Bind)this.fpbind.getValue()).isDown()) && n < (int)this.minPlace.getValue()) {
                n2 = (EntityUtilCa.isInHole((Entity)entityPlayer) ? 1.0 : 2.0);
                this.facePlacing = true;
            }
            if (n < n2 && EntityUtilCa.getHealth((Entity)entityPlayer) - n > 0.0) {
                return 0.0;
            }
            double n3 = 0.0;
            if (!(boolean)this.ignoreSelfDamage.getValue()) {
                n3 = CrystalUtilCa.calculateDamage(blockPos, (Entity)CrystalAura.mc.player, (boolean)this.ignoreTerrain.getValue());
            }
            if (n3 > (int)this.maxSelfPlace.getValue()) {
                return 0.0;
            }
            if (EntityUtilCa.getHealth((Entity)CrystalAura.mc.player) - n3 <= 0.0 && (boolean)this.antiSuicide.getValue()) {
                return 0.0;
            }
            switch ((AcrystalLogic)this.crystalLogic.getValue()) {
                case Safe: {
                    return n - n3;
                }
                case Damage: {
                    return n;
                }
                case Nearby: {
                    return n - CrystalAura.mc.player.getDistanceSq(blockPos);
                }
            }
        }
        return 0.0;
    }
    
    private double isCrystalGood(@NotNull final EntityEnderCrystal entityEnderCrystal, @NotNull final EntityPlayer entityPlayer) {
        if (this.isPlayerValid(entityPlayer)) {
            if (CrystalAura.mc.player.canEntityBeSeen((Entity)entityEnderCrystal)) {
                if (CrystalAura.mc.player.getDistanceSq((Entity)entityEnderCrystal) > MathsUtilCa.square(((Double)this.breakRange.getValue()).floatValue())) {
                    return 0.0;
                }
            }
            else if (CrystalAura.mc.player.getDistanceSq((Entity)entityEnderCrystal) > MathsUtilCa.square(((Double)this.breakRangeWall.getValue()).floatValue())) {
                return 0.0;
            }
            if (entityEnderCrystal.isDead) {
                return 0.0;
            }
            if (this.attemptedCrystals.contains(entityEnderCrystal)) {
                return 0.0;
            }
            final double n = CrystalUtilCa.calculateDamage((Entity)entityEnderCrystal, (Entity)entityPlayer, (boolean)this.ignoreTerrain.getValue());
            this.facePlacing = false;
            double n2 = (int)this.minBreak.getValue();
            if ((EntityUtilCa.getHealth((Entity)entityPlayer) <= (int)this.facePlaceHP.getValue() || CrystalUtilCa.getArmourFucker(entityPlayer, (float)(int)this.fuckArmourHP.getValue()) || ((Bind)this.fpbind.getValue()).isDown()) && n < (int)this.minBreak.getValue()) {
                n2 = (EntityUtilCa.isInHole((Entity)entityPlayer) ? 1.0 : 2.0);
                this.facePlacing = true;
            }
            if (n < n2 && EntityUtilCa.getHealth((Entity)entityPlayer) - n > 0.0) {
                return 0.0;
            }
            double n3 = 0.0;
            if (!(boolean)this.ignoreSelfDamage.getValue()) {
                n3 = CrystalUtilCa.calculateDamage((Entity)entityEnderCrystal, (Entity)CrystalAura.mc.player, (boolean)this.ignoreTerrain.getValue());
            }
            if (n3 > (int)this.maxSelfBreak.getValue()) {
                return 0.0;
            }
            if (EntityUtilCa.getHealth((Entity)CrystalAura.mc.player) - n3 <= 0.0 && (boolean)this.antiSuicide.getValue()) {
                return 0.0;
            }
            switch ((AcrystalLogic)this.crystalLogic.getValue()) {
                case Safe: {
                    return n - n3;
                }
                case Damage: {
                    return n;
                }
                case Nearby: {
                    return n - CrystalAura.mc.player.getDistanceSq((Entity)entityEnderCrystal);
                }
            }
        }
        return 0.0;
    }
    
    public float getCPS() {
        return this.crystalsPlaced / (this.crystalsPlacedTimer.getPassedTimeMs() / 1000.0f);
    }
    
    public EntityEnderCrystal getBestCrystal() {
        double d = 0.0;
        EntityEnderCrystal entityEnderCrystal = null;
        for (final Entity entity : CrystalAura.mc.world.loadedEntityList) {
            if (!(entity instanceof EntityEnderCrystal)) {
                continue;
            }
            final EntityEnderCrystal entityEnderCrystal2 = (EntityEnderCrystal)entity;
            for (final EntityPlayer ezTarget : new ArrayList<EntityPlayer>(CrystalAura.mc.world.playerEntities)) {
                if (CrystalAura.mc.player.getDistanceSq((Entity)ezTarget) > MathsUtilCa.square(((Double)this.targetRange.getValue()).floatValue())) {
                    continue;
                }
                if ((boolean)this.entityPredict.getValue() && this.rotateMode.getValue() == ArotateMode.Off) {
                    final float n = ezTarget.width / 2.0f;
                    ezTarget.setEntityBoundingBox(new AxisAlignedBB(ezTarget.posX - n, ezTarget.posY, ezTarget.posZ - n, ezTarget.posX + n, ezTarget.posY + ezTarget.height, ezTarget.posZ + n));
                    ezTarget.setEntityBoundingBox(CrystalUtilCa.getPredictedPosition((Entity)ezTarget, (double)(int)this.predictedTicks.getValue()).getEntityBoundingBox());
                }
                final double crystalGood = this.isCrystalGood(entityEnderCrystal2, ezTarget);
                if (crystalGood <= 0.0) {
                    continue;
                }
                if (crystalGood <= d) {
                    continue;
                }
                d = crystalGood;
                this.ezTarget = ezTarget;
                entityEnderCrystal = entityEnderCrystal2;
            }
        }
        if (this.ezTarget != null) {}
        if ((entityEnderCrystal != null && this.when.getValue() == Awhen.Both) || this.when.getValue() == Awhen.Break) {
            final BlockPos down = entityEnderCrystal.getPosition().down();
            this.clearMap(down);
            this.renderMap.add(new RenderPos(down, d));
        }
        return entityEnderCrystal;
    }
    
    public enum AfastMode
    {
        Sound, 
        Ghost, 
        Off, 
        Ignore;
    }
    
    public enum Amode
    {
        Outline, 
        Pretty, 
        Solid;
    }
    
    static class RenderPos
    {
        /* synthetic */ double fadeTimer;
        /* synthetic */ Double damage;
        /* synthetic */ BlockPos pos;
        
        public RenderPos(final BlockPos pos, final Double damage) {
            this.pos = pos;
            this.damage = damage;
        }
    }
    
    static class DamageComparator implements Comparator<RenderPos>
    {
        @Override
        public int compare(final RenderPos renderPos, final RenderPos renderPos2) {
            return renderPos2.damage.compareTo(renderPos.damage);
        }
    }
    
    public enum AarrayListMode
    {
        CPS, 
        Latency, 
        Player;
    }
    
    public enum AcrystalLogic
    {
        Safe, 
        Nearby, 
        Damage;
    }
    
    public enum ArotateMode
    {
        Off, 
        Place, 
        Break, 
        Both;
    }
    
    public enum Rotate
    {
        OFF, 
        ALL, 
        BREAK, 
        PLACE;
    }
    
    public enum Aswing
    {
        Offhand, 
        None, 
        Mainhand;
    }
    
    public enum AautoSwitch
    {
        Allways, 
        None, 
        NoGap, 
        Silent;
    }
    
    public enum ApredictTeleport
    {
        Packet, 
        Sound, 
        None;
    }
    
    public enum Awhen
    {
        Never, 
        Break, 
        Both, 
        Place;
    }
}
