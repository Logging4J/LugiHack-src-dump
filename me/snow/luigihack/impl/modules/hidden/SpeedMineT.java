//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.hidden;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.block.*;
import net.minecraft.network.*;
import net.minecraft.util.*;
import net.minecraft.entity.player.*;
import org.jetbrains.annotations.*;
import net.minecraft.world.*;
import net.minecraft.block.state.*;
import me.snow.luigihack.impl.*;
import net.minecraftforge.fml.common.eventhandler.*;
import javafx.util.*;
import net.minecraft.entity.*;
import me.snow.luigihack.*;
import java.util.stream.*;
import net.minecraft.item.*;
import net.minecraft.network.play.client.*;
import java.util.*;
import net.minecraft.init.*;
import net.minecraft.enchantment.*;
import me.snow.luigihack.api.event.events.*;
import java.awt.*;
import me.snow.luigihack.api.util.ca.util.*;
import net.minecraft.util.math.*;

public final class SpeedMineT extends Module
{
    private /* synthetic */ BlockPos lastBreakPos;
    private /* synthetic */ Setting<Boolean> swing;
    private /* synthetic */ boolean firstPacket;
    private /* synthetic */ boolean loopStopPackets;
    private final /* synthetic */ Setting<Integer> instantColorFill0Blue;
    private /* synthetic */ Setting<switchModeA> switchMode;
    private final /* synthetic */ Setting<Integer> breakColorFillBlue;
    private final /* synthetic */ Setting<Integer> breakColor0Red;
    private final /* synthetic */ Setting<Integer> breakColor0Blue;
    private /* synthetic */ BlockPos lastPos;
    private final /* synthetic */ Setting<Integer> doneColor0Alpha;
    private /* synthetic */ Setting<Integer> instantPacketLoop;
    private final /* synthetic */ Setting<Integer> instantColor0Alpha;
    private /* synthetic */ Setting<Boolean> keyMode;
    private /* synthetic */ Setting<Boolean> noDesync;
    private /* synthetic */ Setting<RotateSetting> rotateSetting;
    private /* synthetic */ boolean isActive;
    private final /* synthetic */ Setting<Integer> doneColorRed;
    private /* synthetic */ Setting<Boolean> switchBack;
    private final /* synthetic */ Setting<Integer> doneColorFill0Green;
    private final /* synthetic */ Setting<Integer> breakColorRed;
    private /* synthetic */ int oldSlot;
    private /* synthetic */ Setting<Integer> instantDelay;
    private /* synthetic */ Setting<Boolean> shouldLoop;
    private /* synthetic */ Setting<RenderMode> renderMode;
    private final /* synthetic */ Setting<Integer> breakColorBlue;
    private final /* synthetic */ Setting<Integer> doneColorFill0Red;
    private final /* synthetic */ Setting<Integer> instantColorFillRed;
    private final /* synthetic */ Setting<Integer> breakColorFill0Green;
    private /* synthetic */ double time;
    private final /* synthetic */ Setting<Integer> doneColorFillRed;
    private final /* synthetic */ Setting<Integer> doneColorFillAlpha;
    private final /* synthetic */ Setting<Integer> breakColorFill0Alpha;
    private /* synthetic */ Setting<Boolean> packetBurrow;
    private final /* synthetic */ Setting<Integer> breakColor0Green;
    private final /* synthetic */ Setting<Integer> instantColor0Red;
    private /* synthetic */ int delay;
    private final /* synthetic */ Setting<Integer> doneColorFill0Alpha;
    private /* synthetic */ Setting<Boolean> render;
    private /* synthetic */ Setting<Boolean> packetCity;
    private /* synthetic */ Setting<Boolean> abortPacket;
    private final /* synthetic */ Setting<Integer> doneColorBlue;
    private /* synthetic */ Setting<Boolean> cancel;
    private final /* synthetic */ Setting<Integer> doneColor0Red;
    private /* synthetic */ EnumFacing lastBreakFace;
    private final /* synthetic */ Setting<Integer> instantColorFillBlue;
    private /* synthetic */ Setting<Boolean> rotate;
    private final /* synthetic */ Setting<Integer> instantColorFill0Green;
    private /* synthetic */ int tickCount;
    private /* synthetic */ Setting<Bind> key;
    private final /* synthetic */ Setting<Integer> breakColorFillRed;
    private final /* synthetic */ Setting<Integer> instantColorBlue;
    private final /* synthetic */ Setting<Integer> instantColorFillAlpha;
    private /* synthetic */ Setting<Boolean> instant;
    private final /* synthetic */ Setting<Integer> doneColor0Blue;
    private final /* synthetic */ Setting<Integer> instantColor0Green;
    private final /* synthetic */ Setting<Integer> doneColorGreen;
    private /* synthetic */ Setting<Boolean> correctHit;
    private final /* synthetic */ Setting<Integer> breakColorFillGreen;
    private /* synthetic */ boolean shouldInstant;
    private /* synthetic */ Setting<Boolean> packetLoop;
    private /* synthetic */ Setting<Integer> tickSub;
    private final /* synthetic */ Setting<Integer> doneColorFill0Blue;
    private final /* synthetic */ Setting<Integer> doneColorFillGreen;
    private final /* synthetic */ Setting<Integer> doneColorFillBlue;
    private /* synthetic */ Setting<FillMode> fillMode;
    private /* synthetic */ Setting<Integer> packets;
    private final /* synthetic */ Setting<Integer> breakColorGreen;
    private final /* synthetic */ Setting<Integer> instantColorFill0Alpha;
    private final /* synthetic */ Setting<Integer> breakColorFill0Blue;
    private final /* synthetic */ Setting<Integer> breakColor0Alpha;
    private /* synthetic */ EnumFacing lastFace;
    private final /* synthetic */ Setting<Integer> instantColorGreen;
    private final /* synthetic */ Setting<Integer> breakColorFillAlpha;
    private final /* synthetic */ Setting<Integer> breakColorFill0Red;
    private final /* synthetic */ Setting<Integer> instantColorFill0Red;
    private /* synthetic */ Block lastBlock;
    private /* synthetic */ Setting<Integer> cityRange;
    private /* synthetic */ Setting<Double> range;
    private final /* synthetic */ Setting<Integer> instantColor0Blue;
    private /* synthetic */ Setting<Boolean> rangeCheck;
    private /* synthetic */ int switchedSlot;
    private final /* synthetic */ Setting<Integer> instantColorRed;
    private final /* synthetic */ Setting<Integer> doneColor0Green;
    private final /* synthetic */ Setting<Integer> instantColorFillGreen;
    
    public void setPacketPos(final BlockPos blockPos, final EnumFacing enumFacing) {
        if (!this.canBreakBlockFromPos(blockPos)) {
            return;
        }
        if (blockPos != this.lastBreakPos) {
            this.shouldInstant = false;
        }
        if (this.packetLoop.getValue()) {
            for (int i = 0; i < (int)this.packets.getValue(); ++i) {
                SpeedMineT.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, blockPos, enumFacing));
                if (!(boolean)this.rangeCheck.getValue() && !(boolean)this.correctHit.getValue()) {
                    SpeedMineT.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, blockPos, enumFacing));
                }
            }
        }
        else {
            SpeedMineT.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, blockPos, enumFacing));
            if (!(boolean)this.rangeCheck.getValue() && !(boolean)this.correctHit.getValue()) {
                SpeedMineT.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, blockPos, enumFacing));
            }
        }
        this.isActive = true;
        this.lastFace = enumFacing;
        this.lastPos = blockPos;
        this.lastBreakPos = blockPos;
        this.lastBreakFace = enumFacing;
        this.firstPacket = true;
        this.switchedSlot = -1;
        this.loopStopPackets = true;
        this.lastBlock = SpeedMineT.mc.world.getBlockState(this.lastPos).getBlock();
        ItemStack itemStack;
        if (PlayerUtilCa.getItemStackFromItem(PlayerUtilCa.getBestItem(this.lastBlock)) != null) {
            itemStack = PlayerUtilCa.getItemStackFromItem(PlayerUtilCa.getBestItem(this.lastBlock));
        }
        else {
            itemStack = SpeedMineT.mc.player.getHeldItem(EnumHand.MAIN_HAND);
        }
        if (itemStack == null) {
            return;
        }
        this.time = BlockUtilCa.getMineTime(this.lastBlock, itemStack);
        this.tickCount = 0;
        if ((boolean)this.rotate.getValue() && this.rotateSetting.getValue() == RotateSetting.Hit) {
            RotationUtilCa.rotateHead((double)this.lastPos.getX(), (double)this.lastPos.getY(), (double)this.lastPos.getZ(), (EntityPlayer)SpeedMineT.mc.player);
        }
    }
    
    private boolean canBreakBlockFromPos(@NotNull final BlockPos blockPos) {
        final IBlockState getBlockState = SpeedMineT.mc.world.getBlockState(blockPos);
        return getBlockState.getBlock().getBlockHardness(getBlockState, (World)SpeedMineT.mc.world, blockPos) != -1.0f;
    }
    
    private double normalize(final double n, final double n2, final double n3) {
        return 0.5 * ((n - n3) / (n2 - n3)) + 0.5;
    }
    
    @SubscribeEvent
    public void onBlockEvent(final BlockEvent blockEvent) {
        if (Feature.nullCheck()) {
            return;
        }
        if (blockEvent.getStage() == 3 && SpeedMineT.mc.playerController.curBlockDamageMP > 0.1f) {
            SpeedMineT.mc.playerController.isHittingBlock = true;
        }
        if (blockEvent.getStage() == 4) {
            if (!this.canBreakBlockFromPos(blockEvent.pos)) {
                return;
            }
            if (blockEvent.pos != this.lastBreakPos) {
                this.shouldInstant = false;
            }
            if (this.swing.getValue()) {
                SpeedMineT.mc.player.swingArm(EnumHand.MAIN_HAND);
            }
            if (blockEvent.pos != this.lastPos && (boolean)this.correctHit.getValue() && this.lastPos != null) {
                this.isActive = false;
                SpeedMineT.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, this.lastPos, this.lastFace));
                SpeedMineT.mc.playerController.isHittingBlock = false;
                SpeedMineT.mc.playerController.curBlockDamageMP = 0.0f;
            }
            if (!this.isActive) {
                blockEvent.setCanceled((boolean)this.cancel.getValue());
                this.setPacketPos(blockEvent.pos, blockEvent.facing);
            }
        }
    }
    
    public SpeedMineT() {
        super("SpeedmineTEST", "Speeds up mining.", Category.PLAYER, true, false, false);
        this.rangeCheck = (Setting<Boolean>)this.register(new Setting("RangeCheck", (Object)true));
        this.range = (Setting<Double>)this.register(new Setting("Range", (Object)12.0, (Object)1.0, (Object)60.0, p0 -> (boolean)this.rangeCheck.getValue()));
        this.swing = (Setting<Boolean>)this.register(new Setting("Swing", (Object)true));
        this.rotate = (Setting<Boolean>)this.register(new Setting("Rotate", (Object)false));
        this.rotateSetting = (Setting<RotateSetting>)this.register(new Setting("RotateSettings", (Object)RotateSetting.Break, p0 -> (boolean)this.rotate.getValue()));
        this.cancel = (Setting<Boolean>)this.register(new Setting("CancelEvent", (Object)true));
        this.packetLoop = (Setting<Boolean>)this.register(new Setting("PacketLoop", (Object)false));
        this.packets = (Setting<Integer>)this.register(new Setting("Packets", (Object)1, (Object)1, (Object)25, p0 -> (boolean)this.packetLoop.getValue()));
        this.abortPacket = (Setting<Boolean>)this.register(new Setting("AbortPacket", (Object)true));
        this.correctHit = (Setting<Boolean>)this.register(new Setting("Correction Hit", (Object)true));
        this.tickSub = (Setting<Integer>)this.register(new Setting("Tick Sub", (Object)10, (Object)1, (Object)20, p0 -> (boolean)this.rangeCheck.getValue() || (boolean)this.correctHit.getValue()));
        this.shouldLoop = (Setting<Boolean>)this.register(new Setting("Should Loop", (Object)false, p0 -> (boolean)this.rangeCheck.getValue() || (boolean)this.correctHit.getValue()));
        this.switchMode = (Setting<switchModeA>)this.register(new Setting("SwitchMode", (Object)switchModeA.None));
        this.keyMode = (Setting<Boolean>)this.register(new Setting("KeyOnly", (Object)false));
        this.key = (Setting<Bind>)this.register(new Setting("SwitchKey", (Object)new Bind(-1), p0 -> (boolean)this.keyMode.getValue()));
        this.switchBack = (Setting<Boolean>)this.register(new Setting("SwitchBack", (Object)false, p0 -> this.switchMode.getValue() != switchModeA.None));
        this.noDesync = (Setting<Boolean>)this.register(new Setting("NoDesync", (Object)false, p0 -> this.switchMode.getValue() == switchModeA.Silent));
        this.instant = (Setting<Boolean>)this.register(new Setting("Instant Mine", (Object)false));
        this.instantPacketLoop = (Setting<Integer>)this.register(new Setting("InstantPackets", (Object)2, (Object)2, (Object)25, p0 -> (boolean)this.instant.getValue()));
        this.instantDelay = (Setting<Integer>)this.register(new Setting("InstantDelay", (Object)0, (Object)0, (Object)120, p0 -> (boolean)this.instant.getValue()));
        this.packetCity = (Setting<Boolean>)this.register(new Setting("Packet City", (Object)false));
        this.packetBurrow = (Setting<Boolean>)this.register(new Setting("Packet Burrow", (Object)false));
        this.cityRange = (Setting<Integer>)this.register(new Setting("Combat Range", (Object)5, (Object)1, (Object)15, p0 -> (boolean)this.packetCity.getValue() || (boolean)this.packetBurrow.getValue()));
        this.render = (Setting<Boolean>)this.register(new Setting("Render", (Object)true));
        this.renderMode = (Setting<RenderMode>)this.register(new Setting("RenderMode", (Object)RenderMode.Both, p0 -> (boolean)this.render.getValue()));
        this.fillMode = (Setting<FillMode>)this.register(new Setting("Animation", (Object)FillMode.Expand, p0 -> (boolean)this.render.getValue()));
        this.instantColorRed = (Setting<Integer>)this.register(new Setting("InstantColorOutlineRed", (Object)100, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue() && (boolean)this.instant.getValue()));
        this.instantColorGreen = (Setting<Integer>)this.register(new Setting("InstantColorOutlineGreen", (Object)0, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue() && (boolean)this.instant.getValue()));
        this.instantColorBlue = (Setting<Integer>)this.register(new Setting("InstantColorOutlineBlue", (Object)100, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue() && (boolean)this.instant.getValue()));
        this.instantColor0Red = (Setting<Integer>)this.register(new Setting("InstantColorOutlineTopRed", (Object)10, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue() && (boolean)this.instant.getValue() && this.renderMode.getValue() == RenderMode.Gradient));
        this.instantColor0Green = (Setting<Integer>)this.register(new Setting("InstantColorOutlineTopGreen", (Object)0, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue() && (boolean)this.instant.getValue() && this.renderMode.getValue() == RenderMode.Gradient));
        this.instantColor0Blue = (Setting<Integer>)this.register(new Setting("InstantColorOutlineTopBlue", (Object)100, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue() && (boolean)this.instant.getValue() && this.renderMode.getValue() == RenderMode.Gradient));
        this.instantColor0Alpha = (Setting<Integer>)this.register(new Setting("InstantColorOutlineTopAlpha", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue() && (boolean)this.instant.getValue() && this.renderMode.getValue() == RenderMode.Gradient));
        this.instantColorFillRed = (Setting<Integer>)this.register(new Setting("InstantColorFillRed", (Object)100, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue() && (boolean)this.instant.getValue()));
        this.instantColorFillGreen = (Setting<Integer>)this.register(new Setting("InstantColorFillGreen", (Object)0, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue() && (boolean)this.instant.getValue()));
        this.instantColorFillBlue = (Setting<Integer>)this.register(new Setting("InstantColorFillBlue", (Object)100, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue() && (boolean)this.instant.getValue()));
        this.instantColorFillAlpha = (Setting<Integer>)this.register(new Setting("InstantColorFillAlpha", (Object)40, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue() && (boolean)this.instant.getValue()));
        this.instantColorFill0Red = (Setting<Integer>)this.register(new Setting("InstantColorFillTopRed", (Object)10, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue() && (boolean)this.instant.getValue() && this.renderMode.getValue() == RenderMode.Gradient));
        this.instantColorFill0Green = (Setting<Integer>)this.register(new Setting("InstantColorFillTopGreen", (Object)0, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue() && (boolean)this.instant.getValue() && this.renderMode.getValue() == RenderMode.Gradient));
        this.instantColorFill0Blue = (Setting<Integer>)this.register(new Setting("InstantColorFillTopBlue", (Object)100, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue() && (boolean)this.instant.getValue() && this.renderMode.getValue() == RenderMode.Gradient));
        this.instantColorFill0Alpha = (Setting<Integer>)this.register(new Setting("InstantColorFillTopAlpha", (Object)40, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue() && (boolean)this.instant.getValue() && this.renderMode.getValue() == RenderMode.Gradient));
        this.breakColorRed = (Setting<Integer>)this.register(new Setting("BreakColorOutlineRed", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue()));
        this.breakColorGreen = (Setting<Integer>)this.register(new Setting("BreakColorOutlineGreen", (Object)0, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue()));
        this.breakColorBlue = (Setting<Integer>)this.register(new Setting("BreakColorOutlineBlue", (Object)0, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue()));
        this.breakColor0Red = (Setting<Integer>)this.register(new Setting("BreakColorOutlineTopRed", (Object)10, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue() && this.renderMode.getValue() == RenderMode.Gradient));
        this.breakColor0Green = (Setting<Integer>)this.register(new Setting("BreakColorOutlineTopGreen", (Object)0, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue() && this.renderMode.getValue() == RenderMode.Gradient));
        this.breakColor0Blue = (Setting<Integer>)this.register(new Setting("BreakColorOutlineTopBlue", (Object)0, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue() && this.renderMode.getValue() == RenderMode.Gradient));
        this.breakColor0Alpha = (Setting<Integer>)this.register(new Setting("BreakColorOutlineTopAlpha", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue() && this.renderMode.getValue() == RenderMode.Gradient));
        this.doneColorRed = (Setting<Integer>)this.register(new Setting("FinishedColorOutlineRed", (Object)0, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue()));
        this.doneColorGreen = (Setting<Integer>)this.register(new Setting("FinishedColorOutlineGreen", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue()));
        this.doneColorBlue = (Setting<Integer>)this.register(new Setting("FinishedColorOutlineBlue", (Object)0, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue()));
        this.doneColor0Red = (Setting<Integer>)this.register(new Setting("FinishedColorOutlineTopRed", (Object)0, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue() && this.renderMode.getValue() == RenderMode.Gradient));
        this.doneColor0Green = (Setting<Integer>)this.register(new Setting("FinishedColorOutlineTopGreen", (Object)10, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue() && this.renderMode.getValue() == RenderMode.Gradient));
        this.doneColor0Blue = (Setting<Integer>)this.register(new Setting("FinishedColorOutlineTopBlue", (Object)0, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue() && this.renderMode.getValue() == RenderMode.Gradient));
        this.doneColor0Alpha = (Setting<Integer>)this.register(new Setting("FinishedColorOutlineTopAlpha", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue() && this.renderMode.getValue() == RenderMode.Gradient));
        this.breakColorFillRed = (Setting<Integer>)this.register(new Setting("BreakColorFillRed", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue()));
        this.breakColorFillGreen = (Setting<Integer>)this.register(new Setting("BreakColorFillGreen", (Object)0, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue()));
        this.breakColorFillBlue = (Setting<Integer>)this.register(new Setting("BreakColorFillBlue", (Object)0, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue()));
        this.breakColorFillAlpha = (Setting<Integer>)this.register(new Setting("BreakColorFillAlpha", (Object)40, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue()));
        this.breakColorFill0Red = (Setting<Integer>)this.register(new Setting("BreakColorFillTopRed", (Object)10, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue() && this.renderMode.getValue() == RenderMode.Gradient));
        this.breakColorFill0Green = (Setting<Integer>)this.register(new Setting("BreakColorFillTopGreen", (Object)0, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue() && this.renderMode.getValue() == RenderMode.Gradient));
        this.breakColorFill0Blue = (Setting<Integer>)this.register(new Setting("BreakColorFillTopBlue", (Object)0, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue() && this.renderMode.getValue() == RenderMode.Gradient));
        this.breakColorFill0Alpha = (Setting<Integer>)this.register(new Setting("BreakColorFillTopAlpha", (Object)40, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue() && this.renderMode.getValue() == RenderMode.Gradient));
        this.doneColorFillRed = (Setting<Integer>)this.register(new Setting("FinishedColorFillRed", (Object)0, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue()));
        this.doneColorFillGreen = (Setting<Integer>)this.register(new Setting("FinishedColorFillGreen", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue()));
        this.doneColorFillBlue = (Setting<Integer>)this.register(new Setting("FinishedColorFillBlue", (Object)0, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue()));
        this.doneColorFillAlpha = (Setting<Integer>)this.register(new Setting("FinishedColorFillAlpha", (Object)40, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue()));
        this.doneColorFill0Red = (Setting<Integer>)this.register(new Setting("FinishedColorFillTopRed", (Object)0, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue() && this.renderMode.getValue() == RenderMode.Gradient));
        this.doneColorFill0Green = (Setting<Integer>)this.register(new Setting("FinishedColorFillTopGreen", (Object)10, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue() && this.renderMode.getValue() == RenderMode.Gradient));
        this.doneColorFill0Blue = (Setting<Integer>)this.register(new Setting("FinishedColorFillTopBlue", (Object)0, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue() && this.renderMode.getValue() == RenderMode.Gradient));
        this.doneColorFill0Alpha = (Setting<Integer>)this.register(new Setting("FinishedColorFillTopAlpha", (Object)40, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue() && this.renderMode.getValue() == RenderMode.Gradient));
        this.time = 0.0;
        this.tickCount = 0;
        this.oldSlot = -1;
    }
    
    private boolean isBurrowed(final EntityPlayer entityPlayer) {
        final BlockPos blockPos = new BlockPos(Math.floor(entityPlayer.posX), Math.floor(entityPlayer.posY + 0.2), Math.floor(entityPlayer.posZ));
        return SpeedMineT.mc.world.getBlockState(blockPos).getBlock() == Blocks.ENDER_CHEST || SpeedMineT.mc.world.getBlockState(blockPos).getBlock() == Blocks.OBSIDIAN || SpeedMineT.mc.world.getBlockState(blockPos).getBlock() == Blocks.CHEST;
    }
    
    @Override
    public void onEnable() {
        this.switchedSlot = -1;
        this.shouldInstant = false;
        this.firstPacket = true;
        this.delay = 0;
        this.oldSlot = -1;
        this.loopStopPackets = true;
    }
    
    @Override
    public void onUpdate() {
        if (nullCheck()) {
            return;
        }
        if (!this.isActive && ((boolean)this.packetBurrow.getValue() || (boolean)this.packetCity.getValue())) {
            if (this.packetCity.getValue()) {
                final ArrayList getPlayersReadyToBeCitied = PlayerUtilCa.GetPlayersReadyToBeCitied();
                BlockPos blockPos = null;
                for (final Pair pair : getPlayersReadyToBeCitied) {
                    if (pair.getKey() == SpeedMineT.mc.player) {
                        continue;
                    }
                    if (SpeedMineT.mc.player.getDistance((Entity)pair.getKey()) > (int)this.cityRange.getValue()) {
                        continue;
                    }
                    for (final BlockPos blockPos2 : (ArrayList)pair.getValue()) {
                        if (blockPos == null) {
                            blockPos = blockPos2;
                        }
                        else {
                            if (SpeedMineT.mc.player.getDistance((double)blockPos2.getX(), (double)blockPos2.getY(), (double)blockPos2.getZ()) >= SpeedMineT.mc.player.getDistance((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ())) {
                                continue;
                            }
                            blockPos = blockPos2;
                        }
                    }
                }
                if (blockPos != null) {
                    this.setPacketPos(blockPos, BlockUtilCa.getPlaceableSide(blockPos));
                }
            }
            if ((boolean)this.packetBurrow.getValue() && !this.isActive) {
                for (final EntityPlayer entityPlayer2 : (List)SpeedMineT.mc.world.playerEntities.stream().filter(entityPlayer -> !LuigiHack.friendManager.isFriend(entityPlayer.getName())).collect(Collectors.toList())) {
                    if (entityPlayer2 == SpeedMineT.mc.player) {
                        continue;
                    }
                    if (SpeedMineT.mc.player.getDistance((Entity)entityPlayer2) > (int)this.cityRange.getValue()) {
                        continue;
                    }
                    if (!this.isBurrowed(entityPlayer2)) {
                        continue;
                    }
                    final BlockPos blockPos3 = new BlockPos(Math.floor(entityPlayer2.posX), Math.floor(entityPlayer2.posY), Math.floor(entityPlayer2.posZ));
                    this.setPacketPos(blockPos3, BlockUtilCa.getPlaceableSide(blockPos3));
                }
            }
        }
        if ((boolean)this.instant.getValue() && this.shouldInstant && !this.isActive && this.delay >= (int)this.instantDelay.getValue()) {
            this.delay = 0;
            if (this.firstPacket) {
                this.firstPacket = false;
                for (int i = 0; i < (int)this.instantPacketLoop.getValue(); ++i) {
                    SpeedMineT.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, this.lastBreakPos, this.lastBreakFace));
                }
                SpeedMineT.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, this.lastBreakPos, this.lastBreakFace));
                if (this.abortPacket.getValue()) {
                    SpeedMineT.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, this.lastBreakPos, this.lastBreakFace));
                }
            }
            else if (SpeedMineT.mc.player.getHeldItemMainhand().getItem() == Items.DIAMOND_PICKAXE && SpeedMineT.mc.world.getBlockState(this.lastBreakPos).getBlock() != Blocks.AIR) {
                SpeedMineT.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, this.lastBreakPos, this.lastBreakFace));
            }
        }
        ++this.delay;
        if (this.shouldInstant && (boolean)this.rangeCheck.getValue() && this.lastBreakPos != null) {
            this.shouldInstant = (SpeedMineT.mc.player.getDistanceSq(this.lastBreakPos) <= (double)this.range.getValue());
        }
        int n = 40;
        if (this.lastBlock == Blocks.OBSIDIAN && PlayerUtilCa.getBestItem(this.lastBlock) != null) {
            n = 146;
        }
        else if (this.lastBlock == Blocks.ENDER_CHEST && PlayerUtilCa.getBestItem(this.lastBlock) != null) {
            n = 66;
        }
        if (this.lastPos != null && this.lastBlock != null && this.isActive) {
            if (((boolean)this.rangeCheck.getValue() || (boolean)this.correctHit.getValue()) && this.tickCount > this.time - n - (int)this.tickSub.getValue() && this.loopStopPackets) {
                if (this.packetLoop.getValue()) {
                    for (int j = 0; j < (int)this.packets.getValue(); ++j) {
                        SpeedMineT.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, this.lastPos, this.lastFace));
                    }
                }
                else {
                    SpeedMineT.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, this.lastPos, this.lastFace));
                }
                this.loopStopPackets = (boolean)this.shouldLoop.getValue();
            }
            if ((boolean)this.rotate.getValue() && this.rotateSetting.getValue() == RotateSetting.Constant) {
                RotationUtilCa.rotateHead((double)this.lastPos.getX(), (double)this.lastPos.getY(), (double)this.lastPos.getZ(), (EntityPlayer)SpeedMineT.mc.player);
            }
            if (this.abortPacket.getValue()) {
                if (this.packetLoop.getValue()) {
                    for (int k = 0; k < (int)this.packets.getValue(); ++k) {
                        SpeedMineT.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, this.lastPos, this.lastFace));
                    }
                }
                else {
                    SpeedMineT.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, this.lastPos, this.lastFace));
                }
                this.isActive = true;
            }
            SpeedMineT.mc.playerController.blockHitDelay = 0;
            if (SpeedMineT.mc.world.getBlockState(this.lastPos).getBlock() != this.lastBlock) {
                if ((boolean)this.rotate.getValue() && this.rotateSetting.getValue() == RotateSetting.Break) {
                    RotationUtilCa.rotateHead((double)this.lastPos.getX(), (double)this.lastPos.getY(), (double)this.lastPos.getZ(), (EntityPlayer)SpeedMineT.mc.player);
                }
                this.shouldInstant = true;
                this.isActive = false;
                this.lastBreakPos = this.lastPos;
                this.lastBreakFace = this.lastFace;
                this.lastPos = null;
                this.lastFace = null;
                this.lastBlock = null;
            }
        }
        if (this.switchMode.getValue() != switchModeA.None && this.switchedSlot == -1 && this.isActive && this.lastPos != null && this.tickCount > this.time - n && (!(boolean)this.keyMode.getValue() || ((Bind)this.key.getValue()).isDown())) {
            final int bestTool = this.findBestTool(this.lastBlock.getBlockState().getBaseState());
            if (bestTool == -1) {
                return;
            }
            this.oldSlot = SpeedMineT.mc.player.inventory.currentItem;
            if (this.switchMode.getValue() == switchModeA.Silent) {
                if (!(boolean)this.noDesync.getValue() || !(SpeedMineT.mc.player.getHeldItemMainhand().getItem() instanceof ItemFood) || !SpeedMineT.mc.gameSettings.keyBindUseItem.isKeyDown()) {
                    SpeedMineT.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(bestTool));
                    this.switchedSlot = bestTool;
                }
            }
            else {
                SpeedMineT.mc.player.inventory.currentItem = bestTool;
                SpeedMineT.mc.playerController.syncCurrentPlayItem();
                this.switchedSlot = bestTool;
            }
        }
        if ((boolean)this.switchBack.getValue() && this.switchedSlot != -1 && (!this.isActive || ((boolean)this.keyMode.getValue() && !((Bind)this.key.getValue()).isDown()))) {
            if (this.switchMode.getValue() == switchModeA.Silent) {
                SpeedMineT.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(SpeedMineT.mc.player.inventory.currentItem));
            }
            else if (SpeedMineT.mc.player.inventory.currentItem == this.switchedSlot) {
                SpeedMineT.mc.player.inventory.currentItem = this.oldSlot;
                SpeedMineT.mc.playerController.syncCurrentPlayItem();
            }
            this.switchedSlot = -1;
        }
        if (this.isActive && (boolean)this.rangeCheck.getValue() && Math.sqrt(SpeedMineT.mc.player.getDistanceSq(this.lastPos)) > (double)this.range.getValue()) {
            SpeedMineT.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, this.lastPos, this.lastFace));
            SpeedMineT.mc.playerController.isHittingBlock = false;
            this.isActive = false;
            this.shouldInstant = false;
            this.firstPacket = true;
            this.lastPos = null;
            this.lastFace = null;
            this.lastBlock = null;
        }
        ++this.tickCount;
    }
    
    private int findBestTool(final IBlockState blockState) {
        int n = -1;
        double n2 = 0.0;
        for (int i = 0; i < 9; ++i) {
            final ItemStack getStackInSlot = SpeedMineT.mc.player.inventory.getStackInSlot(i);
            final float getDestroySpeed;
            if (!getStackInSlot.isEmpty && (getDestroySpeed = getStackInSlot.getDestroySpeed(blockState)) > 1.0f) {
                final int getEnchantmentLevel;
                final float n3;
                if ((n3 = (float)(getDestroySpeed + (((getEnchantmentLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.EFFICIENCY, getStackInSlot)) > 0) ? (Math.pow(getEnchantmentLevel, 2.0) + 1.0) : 0.0))) > n2) {
                    n2 = n3;
                    n = i;
                }
            }
        }
        return n;
    }
    
    @Override
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (!(boolean)this.render.getValue()) {
            return;
        }
        if (this.isActive && this.lastPos != null) {
            Colour colour = new Colour((int)this.breakColorRed.getValue(), (int)this.breakColorGreen.getValue(), (int)this.breakColorBlue.getValue());
            final Colour colour2 = new Colour((int)this.breakColorFillRed.getValue(), (int)this.breakColorFillGreen.getValue(), (int)this.breakColorFillBlue.getValue(), (int)this.breakColorFillAlpha.getValue());
            final Colour colour3 = new Colour((int)this.breakColor0Red.getValue(), (int)this.breakColor0Green.getValue(), (int)this.breakColor0Blue.getValue(), (int)this.breakColor0Alpha.getValue());
            final Colour colour4 = new Colour((int)this.breakColorFill0Red.getValue(), (int)this.breakColorFill0Green.getValue(), (int)this.breakColorFill0Blue.getValue(), (int)this.breakColorFill0Alpha.getValue());
            int n = 40;
            if (this.lastBlock == Blocks.OBSIDIAN && PlayerUtilCa.getBestItem(this.lastBlock) != null) {
                n = 146;
            }
            else if (this.lastBlock == Blocks.ENDER_CHEST && PlayerUtilCa.getBestItem(this.lastBlock) != null) {
                n = 66;
            }
            if (this.tickCount > this.time - n) {
                final Colour colour5 = new Colour((int)this.doneColorRed.getValue(), (int)this.doneColorGreen.getValue(), (int)this.doneColorBlue.getValue(), (int)this.doneColor0Alpha.getValue());
                final Colour colour6 = new Colour((int)this.doneColorFillRed.getValue(), (int)this.doneColorFillGreen.getValue(), (int)this.doneColorFillBlue.getValue(), (int)this.doneColorFillAlpha.getValue());
                final Colour colour7 = new Colour((int)this.doneColor0Red.getValue(), (int)this.doneColor0Green.getValue(), (int)this.doneColor0Blue.getValue(), (int)this.doneColor0Alpha.getValue());
                colour = new Colour((int)this.doneColorFill0Red.getValue(), (int)this.doneColorFill0Green.getValue(), (int)this.doneColorFill0Blue.getValue(), (int)this.doneColorFill0Alpha.getValue());
            }
            AxisAlignedBB axisAlignedBB = SpeedMineT.mc.world.getBlockState(this.lastPos).getSelectedBoundingBox((World)SpeedMineT.mc.world, this.lastPos);
            switch ((FillMode)this.fillMode.getValue()) {
                case Expand: {
                    axisAlignedBB = axisAlignedBB.shrink(Math.max(Math.min(this.normalize(this.tickCount, this.time - n, 0.0), 1.0), 0.0));
                    break;
                }
                case Fill: {
                    axisAlignedBB = axisAlignedBB.setMaxY(axisAlignedBB.minY - 0.5 + Math.max(Math.min(this.normalize(this.tickCount * 2, this.time - n, 0.0), 1.5), 0.0));
                    break;
                }
            }
            switch ((RenderMode)this.renderMode.getValue()) {
                case Both: {
                    RenderUtilCa.drawBBBox(axisAlignedBB, (Color)colour2, colour2.getAlpha());
                    RenderUtilCa.drawBlockOutlineBB(axisAlignedBB, new Color(colour.getRed(), colour.getGreen(), colour.getBlue(), 255), 1.0f);
                    break;
                }
                case Outline: {
                    RenderUtilCa.drawBlockOutlineBB(axisAlignedBB, (Color)colour, 1.0f);
                    break;
                }
                case Fill: {
                    RenderUtilCa.drawBBBox(axisAlignedBB, (Color)colour2, colour2.getAlpha());
                    break;
                }
                case Gradient: {
                    final Vec3d interpolateEntity = EntityUtilCa.interpolateEntity((Entity)RenderUtilCa.mc.player, SpeedMineT.mc.getRenderPartialTicks());
                    RenderUtilCa.drawGradientBlockOutline(axisAlignedBB.grow(0.0020000000949949026).offset(-interpolateEntity.x, -interpolateEntity.y, -interpolateEntity.z), (Color)colour3, (Color)colour, 2.0f);
                    RenderUtilCa.drawOpenGradientBoxBB(axisAlignedBB, (Color)colour2, (Color)colour4, 0.0);
                    break;
                }
            }
        }
        if ((boolean)this.instant.getValue() && this.shouldInstant && this.lastBreakPos != null) {
            final Colour colour8 = new Colour((int)this.instantColorRed.getValue(), (int)this.instantColorGreen.getValue(), (int)this.instantColorBlue.getValue(), (int)this.instantColor0Alpha.getValue());
            final Colour colour9 = new Colour((int)this.instantColorFillRed.getValue(), (int)this.instantColorFillGreen.getValue(), (int)this.instantColorFillBlue.getValue(), (int)this.instantColorFillAlpha.getValue());
            final Colour colour10 = new Colour((int)this.instantColor0Red.getValue(), (int)this.instantColor0Green.getValue(), (int)this.instantColor0Blue.getValue(), (int)this.instantColor0Alpha.getValue());
            final Colour colour11 = new Colour((int)this.instantColorFill0Red.getValue(), (int)this.instantColorFill0Green.getValue(), (int)this.instantColorFill0Blue.getValue(), (int)this.instantColorFill0Alpha.getValue());
            switch ((RenderMode)this.renderMode.getValue()) {
                case Both: {
                    RenderUtilCa.drawBlockOutline(this.lastBreakPos, new Color(colour8.getRed(), colour8.getGreen(), colour8.getBlue(), 255), 1.0f, true);
                    RenderUtilCa.drawBox(this.lastBreakPos, (Color)colour9, true);
                    break;
                }
                case Outline: {
                    RenderUtilCa.drawBlockOutline(this.lastBreakPos, (Color)colour8, 1.0f, true);
                    break;
                }
                case Fill: {
                    RenderUtilCa.drawBox(this.lastBreakPos, (Color)colour9, true);
                    break;
                }
                case Gradient: {
                    RenderUtilCa.drawGradientBlockOutline(this.lastBreakPos, (Color)colour10, (Color)colour8, 1.0f, 0.0);
                    RenderUtilCa.drawOpenGradientBox(this.lastBreakPos, (Color)colour9, (Color)colour11, 0.0);
                    break;
                }
            }
        }
    }
    
    public enum switchModeA
    {
        Silent, 
        Normal, 
        None;
    }
    
    public enum RotateSetting
    {
        Break, 
        Constant, 
        Hit;
    }
    
    public enum FillMode
    {
        Fill, 
        Expand, 
        Static;
    }
    
    public enum RenderMode
    {
        Gradient, 
        Outline, 
        Both, 
        Fill;
    }
}
