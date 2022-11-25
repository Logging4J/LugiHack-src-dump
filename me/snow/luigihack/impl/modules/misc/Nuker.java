//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.misc;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.block.state.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.snow.luigihack.api.event.events.*;
import me.snow.luigihack.api.util.*;
import java.util.*;
import net.minecraft.init.*;
import net.minecraft.block.*;

public class Nuker extends Module
{
    public /* synthetic */ Setting<Boolean> hopperNuker;
    public /* synthetic */ Setting<Boolean> nuke;
    public /* synthetic */ Setting<Boolean> antiRegear;
    public /* synthetic */ Setting<Mode> mode;
    private /* synthetic */ Block selected;
    private final /* synthetic */ Setting<Boolean> autoSwitch;
    public /* synthetic */ Setting<Float> distance;
    private /* synthetic */ boolean isMining;
    public /* synthetic */ Setting<Boolean> rotate;
    public /* synthetic */ Setting<Integer> delay;
    private final /* synthetic */ Timer timer;
    public /* synthetic */ Setting<Integer> blockPerTick;
    private /* synthetic */ int oldSlot;
    
    public void breakBlocks(final List<Block> list) {
        final BlockPos nearestBlock = this.getNearestBlock(list);
        if (nearestBlock != null) {
            if (!this.isMining) {
                this.oldSlot = Nuker.mc.player.inventory.currentItem;
                this.isMining = true;
            }
            if (this.rotate.getValue()) {
                final float[] calcAngle = MathUtil.calcAngle(Nuker.mc.player.getPositionEyes(Nuker.mc.getRenderPartialTicks()), new Vec3d((double)(nearestBlock.getX() + 0.5f), (double)(nearestBlock.getY() + 0.5f), (double)(nearestBlock.getZ() + 0.5f)));
                LuigiHack.rotationManager.setPlayerRotations(calcAngle[0], calcAngle[1]);
            }
            if (this.canBreak(nearestBlock)) {
                if (this.autoSwitch.getValue()) {
                    int currentItem = -1;
                    for (int i = 0; i < 9; ++i) {
                        final ItemStack getStackInSlot = Nuker.mc.player.inventory.getStackInSlot(i);
                        if (getStackInSlot != ItemStack.EMPTY && getStackInSlot.getItem() instanceof ItemPickaxe) {
                            currentItem = i;
                            break;
                        }
                    }
                    if (currentItem != -1) {
                        Nuker.mc.player.inventory.currentItem = currentItem;
                    }
                }
                Nuker.mc.playerController.onPlayerDamageBlock(nearestBlock, Nuker.mc.player.getHorizontalFacing());
                Nuker.mc.player.swingArm(EnumHand.MAIN_HAND);
            }
        }
        else if ((boolean)this.autoSwitch.getValue() && this.oldSlot != -1) {
            Nuker.mc.player.inventory.currentItem = this.oldSlot;
            this.oldSlot = -1;
            this.isMining = false;
        }
    }
    
    @Override
    public void onToggle() {
        this.selected = null;
    }
    
    private boolean canBreak(final BlockPos blockPos) {
        final IBlockState getBlockState = Nuker.mc.world.getBlockState(blockPos);
        return getBlockState.getBlock().getBlockHardness(getBlockState, (World)Nuker.mc.world, blockPos) != -1.0f;
    }
    
    public Nuker() {
        super("Nuker", "Mines many blocks.", Category.MISC, true, false, false);
        this.timer = new Timer();
        this.autoSwitch = (Setting<Boolean>)this.register(new Setting("AutoSwitch", (Object)false));
        this.rotate = (Setting<Boolean>)this.register(new Setting("Rotate", (Object)false));
        this.distance = (Setting<Float>)this.register(new Setting("Range", (Object)6.0f, (Object)0.1f, (Object)10.0f));
        this.blockPerTick = (Setting<Integer>)this.register(new Setting("Blocks/Attack", (Object)50, (Object)1, (Object)100));
        this.delay = (Setting<Integer>)this.register(new Setting("Delay/Attack", (Object)50, (Object)1, (Object)1000));
        this.nuke = (Setting<Boolean>)this.register(new Setting("Nuke", (Object)false));
        this.mode = (Setting<Mode>)this.register(new Setting("Mode", (Object)Mode.NUKE, p0 -> (boolean)this.nuke.getValue()));
        this.antiRegear = (Setting<Boolean>)this.register(new Setting("AntiRegear", (Object)false));
        this.hopperNuker = (Setting<Boolean>)this.register(new Setting("HopperAura", (Object)false));
        this.oldSlot = -1;
    }
    
    @SubscribeEvent
    public void onClickBlock(final BlockEvent blockEvent) {
        final Block getBlock;
        if (blockEvent.getStage() == 3 && (this.mode.getValue() == Mode.SELECTION || this.mode.getValue() == Mode.NUKE) && (getBlock = Nuker.mc.world.getBlockState(blockEvent.pos).getBlock()) != null && getBlock != this.selected) {
            this.selected = getBlock;
            blockEvent.setCanceled(true);
        }
    }
    
    @SubscribeEvent
    public void onUpdateWalkingPlayerPre(final UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (updateWalkingPlayerEvent.getStage() == 0) {
            if (this.nuke.getValue()) {
                BlockPos blockPos = null;
                switch ((Mode)this.mode.getValue()) {
                    case SELECTION:
                    case NUKE: {
                        blockPos = this.getClosestBlockSelection();
                        break;
                    }
                    case ALL: {
                        blockPos = this.getClosestBlockAll();
                        break;
                    }
                }
                if (blockPos != null) {
                    if (this.mode.getValue() == Mode.SELECTION || this.mode.getValue() == Mode.ALL) {
                        if (this.rotate.getValue()) {
                            final float[] calcAngle = MathUtil.calcAngle(Nuker.mc.player.getPositionEyes(Nuker.mc.getRenderPartialTicks()), new Vec3d((double)(blockPos.getX() + 0.5f), (double)(blockPos.getY() + 0.5f), (double)(blockPos.getZ() + 0.5f)));
                            LuigiHack.rotationManager.setPlayerRotations(calcAngle[0], calcAngle[1]);
                        }
                        if (this.canBreak(blockPos)) {
                            Nuker.mc.playerController.onPlayerDamageBlock(blockPos, Nuker.mc.player.getHorizontalFacing());
                            Nuker.mc.player.swingArm(EnumHand.MAIN_HAND);
                        }
                    }
                    else {
                        for (int i = 0; i < (int)this.blockPerTick.getValue(); ++i) {
                            final BlockPos closestBlockSelection = this.getClosestBlockSelection();
                            if (closestBlockSelection != null) {
                                if (this.rotate.getValue()) {
                                    final float[] calcAngle2 = MathUtil.calcAngle(Nuker.mc.player.getPositionEyes(Nuker.mc.getRenderPartialTicks()), new Vec3d((double)(closestBlockSelection.getX() + 0.5f), (double)(closestBlockSelection.getY() + 0.5f), (double)(closestBlockSelection.getZ() + 0.5f)));
                                    LuigiHack.rotationManager.setPlayerRotations(calcAngle2[0], calcAngle2[1]);
                                }
                                if (this.timer.passedMs((long)(int)this.delay.getValue())) {
                                    Nuker.mc.playerController.onPlayerDamageBlock(closestBlockSelection, Nuker.mc.player.getHorizontalFacing());
                                    Nuker.mc.player.swingArm(EnumHand.MAIN_HAND);
                                    this.timer.reset();
                                }
                            }
                        }
                    }
                }
            }
            if (this.antiRegear.getValue()) {
                this.breakBlocks(BlockUtil.shulkerList);
            }
            if (this.hopperNuker.getValue()) {
                final ArrayList<Block> list = new ArrayList<Block>();
                list.add((Block)Blocks.HOPPER);
                this.breakBlocks(list);
            }
        }
    }
    
    private BlockPos getNearestBlock(final List<Block> list) {
        double square = MathUtil.square((float)this.distance.getValue());
        BlockPos blockPos = null;
        for (double n = square; n >= -square; --n) {
            for (double n2 = square; n2 >= -square; --n2) {
                for (double n3 = square; n3 >= -square; --n3) {
                    final BlockPos blockPos2 = new BlockPos(Nuker.mc.player.posX + n, Nuker.mc.player.posY + n2, Nuker.mc.player.posZ + n3);
                    final double getDistanceSq = Nuker.mc.player.getDistanceSq((double)blockPos2.getX(), (double)blockPos2.getY(), (double)blockPos2.getZ());
                    if (getDistanceSq <= square && list.contains(Nuker.mc.world.getBlockState(blockPos2).getBlock())) {
                        if (this.canBreak(blockPos2)) {
                            square = getDistanceSq;
                            blockPos = blockPos2;
                        }
                    }
                }
            }
        }
        return blockPos;
    }
    
    private BlockPos getClosestBlockAll() {
        float floatValue = (float)this.distance.getValue();
        BlockPos blockPos = null;
        for (float n = floatValue; n >= -floatValue; --n) {
            for (float n2 = floatValue; n2 >= -floatValue; --n2) {
                for (float n3 = floatValue; n3 >= -floatValue; --n3) {
                    final BlockPos blockPos2 = new BlockPos(Nuker.mc.player.posX + n, Nuker.mc.player.posY + n2, Nuker.mc.player.posZ + n3);
                    final double getDistance = Nuker.mc.player.getDistance((double)blockPos2.getX(), (double)blockPos2.getY(), (double)blockPos2.getZ());
                    if (getDistance <= floatValue && Nuker.mc.world.getBlockState(blockPos2).getBlock() != Blocks.AIR && !(Nuker.mc.world.getBlockState(blockPos2).getBlock() instanceof BlockLiquid) && this.canBreak(blockPos2)) {
                        if (blockPos2.getY() >= Nuker.mc.player.posY) {
                            floatValue = (float)getDistance;
                            blockPos = blockPos2;
                        }
                    }
                }
            }
        }
        return blockPos;
    }
    
    private BlockPos getClosestBlockSelection() {
        float floatValue = (float)this.distance.getValue();
        BlockPos blockPos = null;
        for (float n = floatValue; n >= -floatValue; --n) {
            for (float n2 = floatValue; n2 >= -floatValue; --n2) {
                for (float n3 = floatValue; n3 >= -floatValue; --n3) {
                    final BlockPos blockPos2 = new BlockPos(Nuker.mc.player.posX + n, Nuker.mc.player.posY + n2, Nuker.mc.player.posZ + n3);
                    final double getDistance = Nuker.mc.player.getDistance((double)blockPos2.getX(), (double)blockPos2.getY(), (double)blockPos2.getZ());
                    if (getDistance <= floatValue && Nuker.mc.world.getBlockState(blockPos2).getBlock() != Blocks.AIR && !(Nuker.mc.world.getBlockState(blockPos2).getBlock() instanceof BlockLiquid) && Nuker.mc.world.getBlockState(blockPos2).getBlock() == this.selected && this.canBreak(blockPos2)) {
                        if (blockPos2.getY() >= Nuker.mc.player.posY) {
                            floatValue = (float)getDistance;
                            blockPos = blockPos2;
                        }
                    }
                }
            }
        }
        return blockPos;
    }
    
    public enum Mode
    {
        ALL, 
        SELECTION, 
        NUKE;
    }
}
