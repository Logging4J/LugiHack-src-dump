//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.combat;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.init.*;
import net.minecraft.block.state.*;
import net.minecraft.entity.*;
import me.snow.luigihack.api.util.ca.util.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.item.*;
import net.minecraft.block.*;

public class SelfTrap extends Module
{
    public /* synthetic */ Setting<Boolean> rotate;
    private /* synthetic */ BlockPos trapPos;
    public /* synthetic */ Setting<swingA> swing;
    
    @Override
    public void onEnable() {
        if (this.findInHotbar() == -1) {
            this.disable();
        }
    }
    
    public boolean isTrapped() {
        if (this.trapPos == null) {
            return false;
        }
        final IBlockState getBlockState = SelfTrap.mc.world.getBlockState(this.trapPos);
        return getBlockState.getBlock() != Blocks.AIR && getBlockState.getBlock() != Blocks.WATER && getBlockState.getBlock() != Blocks.LAVA;
    }
    
    @Override
    public void onUpdate() {
        final Vec3d interpolateEntity = EntityUtilCa.interpolateEntity((Entity)SelfTrap.mc.player, SelfTrap.mc.getRenderPartialTicks());
        this.trapPos = new BlockPos(interpolateEntity.x, interpolateEntity.y + 2.0, interpolateEntity.z);
        if (this.isTrapped()) {
            if (!this.isEnabled()) {
                this.enable();
                return;
            }
            this.disable();
        }
        final BlockUtilCa.ValidResult valid = BlockUtilCa.valid(this.trapPos);
        if (valid == BlockUtilCa.ValidResult.AlreadyBlockThere && !SelfTrap.mc.world.getBlockState(this.trapPos).getMaterial().isReplaceable()) {
            return;
        }
        if (valid == BlockUtilCa.ValidResult.NoNeighbors) {
            for (final BlockPos blockPos : new BlockPos[] { this.trapPos.north(), this.trapPos.south(), this.trapPos.east(), this.trapPos.west(), this.trapPos.up(), this.trapPos.down().west() }) {
                final BlockUtilCa.ValidResult valid2 = BlockUtilCa.valid(blockPos);
                if (valid2 != BlockUtilCa.ValidResult.NoNeighbors) {
                    if (valid2 != BlockUtilCa.ValidResult.NoEntityCollision) {
                        if (BlockUtilCa.placeBlock(blockPos, this.findInHotbar(), (boolean)this.rotate.getValue(), (boolean)this.rotate.getValue())) {
                            if (this.swing.getValue() == swingA.Mainhand) {
                                SelfTrap.mc.player.swingArm(EnumHand.MAIN_HAND);
                            }
                            if (this.swing.getValue() == swingA.Offhand) {
                                SelfTrap.mc.player.swingArm(EnumHand.OFF_HAND);
                            }
                            return;
                        }
                    }
                }
            }
            return;
        }
        BlockUtilCa.placeBlock(this.trapPos, this.findInHotbar(), (boolean)this.rotate.getValue(), (boolean)this.rotate.getValue());
        if (this.swing.getValue() == swingA.Mainhand) {
            SelfTrap.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
        if (this.swing.getValue() == swingA.Offhand) {
            SelfTrap.mc.player.swingArm(EnumHand.OFF_HAND);
        }
    }
    
    private int findInHotbar() {
        for (int i = 0; i < 9; ++i) {
            final ItemStack getStackInSlot = SelfTrap.mc.player.inventory.getStackInSlot(i);
            if (getStackInSlot != ItemStack.EMPTY && getStackInSlot.getItem() instanceof ItemBlock) {
                final Block getBlock = ((ItemBlock)getStackInSlot.getItem()).getBlock();
                if (getBlock instanceof BlockEnderChest) {
                    return i;
                }
                if (getBlock instanceof BlockObsidian) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    public SelfTrap() {
        super("SelfTrap", "Will speed up the game.", Category.COMBAT, false, false, false);
        this.rotate = (Setting<Boolean>)this.register(new Setting("Rotate", (Object)false));
        this.swing = (Setting<swingA>)this.register(new Setting("SwingHand", (Object)swingA.Mainhand));
    }
    
    public enum swingA
    {
        Offhand, 
        None, 
        Mainhand;
    }
}
