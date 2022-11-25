//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.player;

import me.snow.luigihack.impl.modules.*;
import net.minecraft.init.*;
import net.minecraft.util.math.*;
import net.minecraft.util.*;
import net.minecraftforge.event.world.*;
import net.minecraft.item.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class NoGlitchBlocks extends Module
{
    private static /* synthetic */ NoGlitchBlocks INSTANCE;
    
    static {
        NoGlitchBlocks.INSTANCE = new NoGlitchBlocks();
    }
    
    private void removeGlitchBlocks(final BlockPos blockPos) {
        for (int i = -4; i <= 4; ++i) {
            for (int j = -4; j <= 4; ++j) {
                for (int k = -4; k <= 4; ++k) {
                    final BlockPos blockPos2 = new BlockPos(blockPos.getX() + i, blockPos.getY() + j, blockPos.getZ() + k);
                    if (NoGlitchBlocks.mc.world.getBlockState(blockPos2).getBlock().equals(Blocks.AIR)) {
                        NoGlitchBlocks.mc.playerController.processRightClickBlock(NoGlitchBlocks.mc.player, NoGlitchBlocks.mc.world, blockPos2, EnumFacing.DOWN, new Vec3d(0.5, 0.5, 0.5), EnumHand.MAIN_HAND);
                    }
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onBreak(final BlockEvent.BreakEvent breakEvent) {
        if (fullNullCheck()) {
            return;
        }
        if (!(NoGlitchBlocks.mc.player.getHeldItemMainhand().getItem() instanceof ItemBlock)) {
            this.removeGlitchBlocks(NoGlitchBlocks.mc.player.getPosition());
        }
    }
    
    public NoGlitchBlocks() {
        super("NoGlitchBlocks", "Prevents ghost blocks", Module.Category.PLAYER, true, false, false);
        this.setInstance();
    }
    
    public static NoGlitchBlocks getINSTANCE() {
        if (NoGlitchBlocks.INSTANCE == null) {
            NoGlitchBlocks.INSTANCE = new NoGlitchBlocks();
        }
        return NoGlitchBlocks.INSTANCE;
    }
    
    private void setInstance() {
        NoGlitchBlocks.INSTANCE = this;
    }
}
