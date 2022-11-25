//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.movement;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.init.*;
import net.minecraft.util.math.*;
import com.google.common.eventbus.*;

public class Anchor extends Module
{
    private final /* synthetic */ Setting<Boolean> pull;
    public static /* synthetic */ boolean Anchoring;
    /* synthetic */ int holeblocks;
    private final /* synthetic */ Setting<Integer> pitch;
    
    public Anchor() {
        super("Anchor", "For disabled people that can't move into holes on their own.", Module.Category.MOVEMENT, false, false, false);
        this.pitch = (Setting<Integer>)this.register(new Setting("Pitch", (Object)60, (Object)0, (Object)90));
        this.pull = (Setting<Boolean>)this.register(new Setting("Pull", (Object)true));
    }
    
    public BlockPos getPlayerPos() {
        return new BlockPos(Math.floor(Anchor.mc.player.posX), Math.floor(Anchor.mc.player.posY), Math.floor(Anchor.mc.player.posZ));
    }
    
    public boolean isBlockHole(final BlockPos blockPos) {
        this.holeblocks = 0;
        if (Anchor.mc.world.getBlockState(blockPos.add(0, 3, 0)).getBlock() == Blocks.AIR) {
            ++this.holeblocks;
        }
        if (Anchor.mc.world.getBlockState(blockPos.add(0, 2, 0)).getBlock() == Blocks.AIR) {
            ++this.holeblocks;
        }
        if (Anchor.mc.world.getBlockState(blockPos.add(0, 1, 0)).getBlock() == Blocks.AIR) {
            ++this.holeblocks;
        }
        if (Anchor.mc.world.getBlockState(blockPos.add(0, 0, 0)).getBlock() == Blocks.AIR) {
            ++this.holeblocks;
        }
        if (Anchor.mc.world.getBlockState(blockPos.add(0, -1, 0)).getBlock() == Blocks.OBSIDIAN || Anchor.mc.world.getBlockState(blockPos.add(0, -1, 0)).getBlock() == Blocks.BEDROCK) {
            ++this.holeblocks;
        }
        if (Anchor.mc.world.getBlockState(blockPos.add(1, 0, 0)).getBlock() == Blocks.OBSIDIAN || Anchor.mc.world.getBlockState(blockPos.add(1, 0, 0)).getBlock() == Blocks.BEDROCK) {
            ++this.holeblocks;
        }
        if (Anchor.mc.world.getBlockState(blockPos.add(-1, 0, 0)).getBlock() == Blocks.OBSIDIAN || Anchor.mc.world.getBlockState(blockPos.add(-1, 0, 0)).getBlock() == Blocks.BEDROCK) {
            ++this.holeblocks;
        }
        if (Anchor.mc.world.getBlockState(blockPos.add(0, 0, 1)).getBlock() == Blocks.OBSIDIAN || Anchor.mc.world.getBlockState(blockPos.add(0, 0, 1)).getBlock() == Blocks.BEDROCK) {
            ++this.holeblocks;
        }
        if (Anchor.mc.world.getBlockState(blockPos.add(0, 0, -1)).getBlock() == Blocks.OBSIDIAN || Anchor.mc.world.getBlockState(blockPos.add(0, 0, -1)).getBlock() == Blocks.BEDROCK) {
            ++this.holeblocks;
        }
        return this.holeblocks >= 9;
    }
    
    public void onDisable() {
        Anchor.Anchoring = false;
        this.holeblocks = 0;
    }
    
    public Vec3d GetCenter(final double a, final double a2, final double a3) {
        return new Vec3d(Math.floor(a) + 0.5, Math.floor(a2), Math.floor(a3) + 0.5);
    }
    
    @Subscribe
    public void onUpdate() {
        if (Anchor.mc.world == null) {
            return;
        }
        if (Anchor.mc.player.rotationPitch >= (int)this.pitch.getValue()) {
            if (this.isBlockHole(this.getPlayerPos().down(1)) || this.isBlockHole(this.getPlayerPos().down(2)) || this.isBlockHole(this.getPlayerPos().down(3)) || this.isBlockHole(this.getPlayerPos().down(4))) {
                Anchor.Anchoring = true;
                if (!(boolean)this.pull.getValue()) {
                    Anchor.mc.player.motionX = 0.0;
                    Anchor.mc.player.motionZ = 0.0;
                }
                else {
                    final Vec3d getCenter = this.GetCenter(Anchor.mc.player.posX, Anchor.mc.player.posY, Anchor.mc.player.posZ);
                    final double abs = Math.abs(getCenter.x - Anchor.mc.player.posX);
                    final double abs2 = Math.abs(getCenter.z - Anchor.mc.player.posZ);
                    if (abs > 0.1 || abs2 > 0.1) {
                        final double n = getCenter.x - Anchor.mc.player.posX;
                        final double n2 = getCenter.z - Anchor.mc.player.posZ;
                        Anchor.mc.player.motionX = n / 2.0;
                        Anchor.mc.player.motionZ = n2 / 2.0;
                    }
                }
            }
            else {
                Anchor.Anchoring = false;
            }
        }
    }
}
