//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.combat;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.*;
import me.snow.luigihack.api.manager.*;
import net.minecraft.entity.*;
import net.minecraft.network.*;
import net.minecraft.entity.item.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.network.play.client.*;
import java.util.*;
import net.minecraft.util.*;
import net.minecraft.item.*;
import net.minecraft.block.*;

public class AntiCity extends Module
{
    /* synthetic */ boolean hasDisabled;
    /* synthetic */ String caura;
    private /* synthetic */ int totalTicksRunning;
    private /* synthetic */ int offsetStep;
    /* synthetic */ double oldY;
    private /* synthetic */ int lastHotbarSlot;
    private final /* synthetic */ Setting<Boolean> rotate;
    /* synthetic */ int cDelay;
    private final /* synthetic */ Setting<Boolean> turnOffCauras;
    private final /* synthetic */ Setting<Boolean> noGlitchBlocks;
    private /* synthetic */ boolean isSneaking;
    private final /* synthetic */ Setting<Integer> blocksPerTick;
    private /* synthetic */ int delayStep;
    /* synthetic */ boolean isDisabling;
    private /* synthetic */ boolean firstRun;
    private /* synthetic */ int playerHotbarSlot;
    private final /* synthetic */ Setting<Boolean> triggerable;
    private final /* synthetic */ Setting<Integer> timeoutTicks;
    private final /* synthetic */ Setting<Integer> tickDelay;
    
    @Override
    public void onUpdate() {
        if (this.cDelay > 0) {
            --this.cDelay;
        }
        if (this.cDelay == 0 && this.isDisabling) {
            final ModuleManager moduleManager = LuigiHack.moduleManager;
            ModuleManager.getModuleByName(this.caura).toggle();
            this.isDisabling = false;
            this.hasDisabled = true;
        }
        if (Util.mc.player == null || LuigiHack.moduleManager.isModuleEnabled("Freecam")) {
            return;
        }
        final ModuleManager moduleManager2 = LuigiHack.moduleManager;
        if (ModuleManager.getModuleByName("AutoCrystal") != null) {
            final ModuleManager moduleManager3 = LuigiHack.moduleManager;
            if (ModuleManager.getModuleByName("AutoCrystal").isEnabled() && (boolean)this.turnOffCauras.getValue() && !this.hasDisabled) {
                this.caura = "AutoCrystal";
                this.cDelay = 19;
                this.isDisabling = true;
                final ModuleManager moduleManager4 = LuigiHack.moduleManager;
                ModuleManager.getModuleByName(this.caura).toggle();
            }
        }
        if ((boolean)this.triggerable.getValue() && this.totalTicksRunning >= (int)this.timeoutTicks.getValue()) {
            this.totalTicksRunning = 0;
            this.disable();
            return;
        }
        if (AntiCity.mc.player.posY != this.oldY) {
            this.disable();
            return;
        }
        if (!this.firstRun) {
            if (this.delayStep < (int)this.tickDelay.getValue()) {
                ++this.delayStep;
                return;
            }
            this.delayStep = 0;
        }
        if (this.firstRun) {
            this.firstRun = false;
        }
        int i = 0;
        while (i < (int)this.blocksPerTick.getValue()) {
            final Vec3d[] array = new Vec3d[0];
            final Vec3d[] access$000 = Offsets.SURROUND;
            if (this.offsetStep >= Offsets.SURROUND.length) {
                this.offsetStep = 0;
                break;
            }
            final BlockPos blockPos = new BlockPos(access$000[this.offsetStep]);
            if (this.placeBlock(new BlockPos(Util.mc.player.getPositionVector()).add(blockPos.getX(), blockPos.getY(), blockPos.getZ()))) {
                ++i;
            }
            ++this.offsetStep;
        }
        if (i > 0) {
            if (this.lastHotbarSlot != this.playerHotbarSlot && this.playerHotbarSlot != -1) {
                Util.mc.player.inventory.currentItem = this.playerHotbarSlot;
                this.lastHotbarSlot = this.playerHotbarSlot;
            }
            if (this.isSneaking) {
                Util.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Util.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
                this.isSneaking = false;
            }
        }
        ++this.totalTicksRunning;
    }
    
    @Override
    public void onDisable() {
        if (AntiCity.mc.player == null) {
            return;
        }
        if (this.lastHotbarSlot != this.playerHotbarSlot && this.playerHotbarSlot != -1) {
            AntiCity.mc.player.inventory.currentItem = this.playerHotbarSlot;
        }
        if (this.isSneaking) {
            AntiCity.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)AntiCity.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
            this.isSneaking = false;
        }
        this.playerHotbarSlot = -1;
        this.lastHotbarSlot = -1;
    }
    
    private boolean placeBlock(final BlockPos blockPos) {
        final Block getBlock = Util.mc.world.getBlockState(blockPos).getBlock();
        if (!(getBlock instanceof BlockAir) && !(getBlock instanceof BlockLiquid)) {
            return false;
        }
        for (final Entity entity : Util.mc.world.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(blockPos))) {
            if (!(entity instanceof EntityItem) && !(entity instanceof EntityXPOrb)) {
                return false;
            }
        }
        final EnumFacing placeableSide = BlockInteractionHelper.getPlaceableSide(blockPos);
        if (placeableSide == null) {
            return false;
        }
        final BlockPos offset = blockPos.offset(placeableSide);
        final EnumFacing getOpposite = placeableSide.getOpposite();
        if (!BlockInteractionHelper.canBeClicked(offset)) {
            return false;
        }
        final Vec3d add = new Vec3d((Vec3i)offset).add(0.5, 0.5, 0.5).add(new Vec3d(getOpposite.getDirectionVec()).scale(0.5));
        final Block getBlock2 = AntiCity.mc.world.getBlockState(offset).getBlock();
        final int obiInHotbar = this.findObiInHotbar();
        if (obiInHotbar == -1) {
            this.disable();
        }
        if (this.lastHotbarSlot != obiInHotbar) {
            Util.mc.player.inventory.currentItem = obiInHotbar;
            this.lastHotbarSlot = obiInHotbar;
        }
        if ((!this.isSneaking && BlockInteractionHelper.blackList.contains(getBlock2)) || BlockInteractionHelper.shulkerList.contains(getBlock2)) {
            Util.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Util.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            this.isSneaking = true;
        }
        if (this.rotate.getValue()) {
            BlockInteractionHelper.faceVectorPacketInstant(add);
        }
        Util.mc.playerController.processRightClickBlock(Util.mc.player, Util.mc.world, offset, getOpposite, add, EnumHand.MAIN_HAND);
        Util.mc.player.swingArm(EnumHand.MAIN_HAND);
        Util.mc.rightClickDelayTimer = 4;
        if ((boolean)this.noGlitchBlocks.getValue() && !Util.mc.playerController.getCurrentGameType().equals((Object)GameType.CREATIVE)) {
            Util.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, offset, getOpposite));
        }
        return true;
    }
    
    public AntiCity() {
        super("AntiCityBoss", "AntiCity.", Category.COMBAT, true, false, false);
        this.triggerable = (Setting<Boolean>)this.register(new Setting("Triggerable", (Object)true));
        this.turnOffCauras = (Setting<Boolean>)this.register(new Setting("Toggle Other Cauras", (Object)true));
        this.timeoutTicks = (Setting<Integer>)this.register(new Setting("TimeoutTicks", (Object)40, (Object)1, (Object)100));
        this.blocksPerTick = (Setting<Integer>)this.register(new Setting("BlocksPerTick", (Object)4, (Object)1, (Object)9));
        this.tickDelay = (Setting<Integer>)this.register(new Setting("TickDelay", (Object)0, (Object)0, (Object)10));
        this.rotate = (Setting<Boolean>)this.register(new Setting("Rotate", (Object)true));
        this.noGlitchBlocks = (Setting<Boolean>)this.register(new Setting("NoGlitchBlocks", (Object)true));
        this.cDelay = 0;
        this.playerHotbarSlot = -1;
        this.lastHotbarSlot = -1;
        this.offsetStep = 0;
        this.delayStep = 0;
        this.totalTicksRunning = 0;
        this.isSneaking = false;
    }
    
    private int findObiInHotbar() {
        int n = -1;
        for (int i = 0; i < 9; ++i) {
            final ItemStack getStackInSlot = Util.mc.player.inventory.getStackInSlot(i);
            if (getStackInSlot != ItemStack.EMPTY && getStackInSlot.getItem() instanceof ItemBlock && ((ItemBlock)getStackInSlot.getItem()).getBlock() instanceof BlockObsidian) {
                n = i;
                break;
            }
        }
        return n;
    }
    
    @Override
    public void onEnable() {
        if (AntiCity.mc.player == null) {
            this.disable();
            return;
        }
        this.hasDisabled = false;
        this.oldY = AntiCity.mc.player.posY;
        this.firstRun = true;
        this.playerHotbarSlot = AntiCity.mc.player.inventory.currentItem;
        this.lastHotbarSlot = -1;
    }
    
    private enum Mode
    {
        SURROUND, 
        FULL;
    }
    
    private static class Offsets
    {
        private static final /* synthetic */ Vec3d[] SURROUND;
        
        static {
            SURROUND = new Vec3d[] { new Vec3d(2.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 2.0), new Vec3d(-2.0, 0.0, 0.0), new Vec3d(0.0, 0.0, -2.0) };
        }
    }
}
