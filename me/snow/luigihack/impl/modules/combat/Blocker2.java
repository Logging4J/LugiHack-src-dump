//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.combat;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.util.math.*;
import net.minecraft.util.*;
import net.minecraft.network.*;
import net.minecraft.init.*;
import net.minecraft.entity.*;
import net.minecraft.network.play.client.*;
import net.minecraft.entity.item.*;
import java.util.*;
import java.util.function.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.block.state.*;
import net.minecraft.block.*;

public class Blocker2 extends Module
{
    private /* synthetic */ BlockPos b_piston;
    public static final /* synthetic */ List<Block> blackList;
    private /* synthetic */ Setting<Boolean> piston;
    private /* synthetic */ List<BlockPos> cevPositions;
    public /* synthetic */ Setting<Boolean> packetPlace;
    public /* synthetic */ Setting<Float> range;
    private /* synthetic */ ArrayList<BlockPos> queue;
    public /* synthetic */ Setting<Boolean> cevBreaker;
    
    public static void rightClickBlock(final BlockPos blockPos, final EnumFacing enumFacing, final Vec3d vec3d, final boolean b) {
        final Vec3d add = new Vec3d((Vec3i)blockPos).add(vec3d).add(new Vec3d(enumFacing.getDirectionVec()).scale(0.5));
        if (b) {
            rightClickBlock(blockPos, add, EnumHand.MAIN_HAND, enumFacing);
        }
        else {
            Blocker2.mc.playerController.processRightClickBlock(Blocker2.mc.player, Blocker2.mc.world, blockPos, enumFacing, add, EnumHand.MAIN_HAND);
            Blocker2.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
    }
    
    public static EnumFacing getPlaceableSide(final BlockPos blockPos) {
        for (final EnumFacing enumFacing : EnumFacing.values()) {
            final BlockPos offset = blockPos.offset(enumFacing);
            if (Blocker2.mc.world.getBlockState(offset).getBlock().canCollideCheck(Blocker2.mc.world.getBlockState(offset), false) && !Blocker2.mc.world.getBlockState(offset).getMaterial().isReplaceable() && !Blocker2.blackList.contains(getBlock(offset))) {
                return enumFacing;
            }
        }
        return null;
    }
    
    public static void rightClickBlock(final BlockPos blockPos, final Vec3d vec3d, final EnumHand enumHand, final EnumFacing enumFacing) {
        Blocker2.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos, enumFacing, enumHand, (float)(vec3d.x - blockPos.getX()), (float)(vec3d.y - blockPos.getY()), (float)(vec3d.z - blockPos.getZ())));
        Blocker2.mc.rightClickDelayTimer = 4;
    }
    
    public static void rightClickBlock(final BlockPos blockPos, final EnumFacing enumFacing, final boolean b) {
        final Vec3d add = new Vec3d((Vec3i)blockPos).add(0.5, 0.5, 0.5).add(new Vec3d(enumFacing.getDirectionVec()).scale(0.5));
        if (b) {
            rightClickBlock(blockPos, add, EnumHand.MAIN_HAND, enumFacing);
        }
        else {
            Blocker2.mc.playerController.processRightClickBlock(Blocker2.mc.player, Blocker2.mc.world, blockPos, enumFacing, add, EnumHand.MAIN_HAND);
            Blocker2.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
    }
    
    public Blocker2() {
        super("Blocker", "Block gs attack lmao", Category.COMBAT, true, false, false);
        this.piston = (Setting<Boolean>)this.register(new Setting("Piston", (Object)true));
        this.range = (Setting<Float>)this.register(new Setting("Range", (Object)5.0f, (Object)0.5f, (Object)10.0f));
        this.cevBreaker = (Setting<Boolean>)this.register(new Setting("CevBreaker", (Object)true));
        this.packetPlace = (Setting<Boolean>)this.register(new Setting("Packet Place", (Object)true));
        this.queue = new ArrayList<BlockPos>();
        this.b_piston = null;
        this.cevPositions = new ArrayList<BlockPos>();
    }
    
    public static Block getBlock(final BlockPos blockPos) {
        return getBlockState(blockPos).getBlock();
    }
    
    @Override
    public void onDisable() {
        this.cevPositions = new ArrayList<BlockPos>();
    }
    
    static {
        blackList = Arrays.asList(Blocks.ENDER_CHEST, (Block)Blocks.CHEST, Blocks.TRAPPED_CHEST, Blocks.CRAFTING_TABLE, Blocks.ANVIL, Blocks.BREWING_STAND, (Block)Blocks.HOPPER, Blocks.DROPPER, Blocks.DISPENSER, Blocks.TRAPDOOR, Blocks.ENCHANTING_TABLE);
    }
    
    public void blockCev() {
        final BlockPos entityPos = EntityUtil.getEntityPos((Entity)Blocker2.mc.player);
        final BlockPos[] array = { new BlockPos(0, 2, 0), new BlockPos(1, 1, 0), new BlockPos(-1, 1, 0), new BlockPos(0, 1, 1), new BlockPos(0, 1, -1), new BlockPos(1, 1, 1), new BlockPos(1, 1, -1), new BlockPos(-1, 1, 1), new BlockPos(-1, 1, -1) };
        for (int length = array.length, i = 0; i < length; ++i) {
            final Entity crystal = this.getCrystal(entityPos.add((Vec3i)array[i]));
            if (!Objects.isNull(crystal)) {
                final BlockPos add = EntityUtil.getEntityPos(crystal).add(0, -1, 0);
                if (getBlock(add).equals(Blocks.AIR) || getBlock(add).equals(Blocks.OBSIDIAN)) {
                    if (!getBlock(entityPos.add(0, 2, 0)).equals(Blocks.AIR)) {
                        Blocker2.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Blocker2.mc.player.posX, entityPos.getY() + 0.21, Blocker2.mc.player.posZ, false));
                    }
                    Blocker2.mc.player.connection.sendPacket((Packet)new CPacketUseEntity(crystal));
                    if (!this.cevPositions.contains(add)) {
                        this.cevPositions.add(add);
                    }
                }
            }
        }
        final Iterator<BlockPos> iterator = this.cevPositions.iterator();
        while (iterator.hasNext()) {
            final BlockPos blockPos = iterator.next();
            if (!Objects.isNull(this.getCrystal(blockPos))) {
                continue;
            }
            final int blockHotbar = InventoryUtil.getBlockHotbar(Blocks.OBSIDIAN);
            if (blockHotbar == -1) {
                continue;
            }
            InventoryUtil.push();
            Blocker2.mc.player.inventory.currentItem = blockHotbar;
            Blocker2.mc.playerController.updateController();
            if (getBlock(blockPos).equals(Blocks.AIR)) {
                placeBlock(blockPos, (boolean)this.packetPlace.getValue());
                rightClickBlock(blockPos, EnumFacing.UP, (boolean)this.packetPlace.getValue());
            }
            else {
                placeBlock(blockPos.add(0, 1, 0), (boolean)this.packetPlace.getValue());
                InventoryUtil.pop();
            }
            iterator.remove();
        }
    }
    
    private Entity getCrystal(final BlockPos blockPos) {
        return (Entity)Blocker2.mc.world.loadedEntityList.stream().filter(entity -> entity instanceof EntityEnderCrystal).filter(entity2 -> EntityUtil.getEntityPos(entity2).add(0, -1, 0).equals((Object)blockPos)).min(Comparator.comparing((Function<? super T, ? extends Comparable>)PlayerUtil::getDistance)).orElse(null);
    }
    
    @Override
    public void onTick() {
        if (this.cevBreaker.getValue()) {
            this.blockCev();
        }
        final int blockHotbar = InventoryUtil.getBlockHotbar(Blocks.OBSIDIAN);
        if (blockHotbar == -1) {
            return;
        }
        PlayerUtil.getPlayerPos();
        if (this.piston.getValue()) {
            final BlockPos[] array = { new BlockPos(2, 1, 0), new BlockPos(-2, 1, 0), new BlockPos(0, 1, 2), new BlockPos(0, 1, -2) };
            final BlockPos blockPos = new BlockPos(Blocker2.mc.player.posX, Blocker2.mc.player.posY, Blocker2.mc.player.posZ);
            for (int i = 0; i < 4; ++i) {
                for (int j = 0; j < array.length; ++j) {
                    final BlockPos add = blockPos.add((Vec3i)array[j].add(0, i, 0));
                    if (getBlock(add) == Blocks.PISTON || getBlock(add) == Blocks.STICKY_PISTON) {
                        this.b_piston = add;
                    }
                }
            }
            if (this.b_piston != null) {
                if (getBlock(this.b_piston) == Blocks.AIR) {
                    if (Blocker2.mc.player.getDistance((double)this.b_piston.getX(), (double)this.b_piston.getY(), (double)this.b_piston.getZ()) > (float)this.range.getValue()) {
                        return;
                    }
                    final int currentItem = Blocker2.mc.player.inventory.currentItem;
                    Blocker2.mc.player.inventory.currentItem = blockHotbar;
                    Blocker2.mc.playerController.updateController();
                    BlockUtil.placeBlock(this.b_piston, EnumHand.MAIN_HAND, true, (boolean)this.packetPlace.getValue(), false);
                    Blocker2.mc.player.inventory.currentItem = currentItem;
                    Blocker2.mc.playerController.updateController();
                }
                if (getBlock(this.b_piston) == Blocks.OBSIDIAN || Blocker2.mc.player.getDistance((double)this.b_piston.getX(), (double)this.b_piston.getY(), (double)this.b_piston.getZ()) > (float)this.range.getValue()) {
                    this.b_piston = null;
                }
            }
        }
    }
    
    public static IBlockState getBlockState(final BlockPos blockPos) {
        return Blocker2.mc.world.getBlockState(blockPos);
    }
    
    public static boolean placeBlock(final BlockPos blockPos, final boolean b) {
        final Block getBlock = Blocker2.mc.world.getBlockState(blockPos).getBlock();
        if (!(getBlock instanceof BlockAir) && !(getBlock instanceof BlockLiquid)) {
            return false;
        }
        final EnumFacing placeableSide = getPlaceableSide(blockPos);
        if (placeableSide == null) {
            return false;
        }
        final BlockPos offset = blockPos.offset(placeableSide);
        final EnumFacing getOpposite = placeableSide.getOpposite();
        final Vec3d add = new Vec3d((Vec3i)offset).add(0.5, 0.5, 0.5).add(new Vec3d(getOpposite.getDirectionVec()).scale(0.5));
        if (b) {
            rightClickBlock(offset, add, EnumHand.MAIN_HAND, getOpposite);
        }
        else {
            Blocker2.mc.playerController.processRightClickBlock(Blocker2.mc.player, Blocker2.mc.world, offset, getOpposite, add, EnumHand.MAIN_HAND);
            Blocker2.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
        return true;
    }
}
