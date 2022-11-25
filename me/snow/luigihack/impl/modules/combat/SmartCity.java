//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.combat;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.entity.player.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraft.item.*;
import me.snow.luigihack.api.util.skid.oyvey.*;
import me.snow.luigihack.impl.modules.client.*;
import java.awt.*;
import net.minecraft.util.math.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.block.*;
import net.minecraft.init.*;
import net.minecraft.world.*;
import net.minecraft.block.state.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.util.*;
import me.snow.luigihack.api.util.*;

public class SmartCity extends Module
{
    public final /* synthetic */ Setting<Integer> range;
    public final /* synthetic */ Setting<Boolean> render;
    public final /* synthetic */ Setting<Integer> cGreen;
    public final /* synthetic */ Setting<Integer> cRed;
    public final /* synthetic */ Setting<Boolean> pickOnly;
    public final /* synthetic */ Setting<Boolean> oneFifteen;
    public final /* synthetic */ Setting<Boolean> silentPlace;
    public /* synthetic */ Timer timer;
    public final /* synthetic */ Setting<Integer> boxAlpha;
    public final /* synthetic */ Setting<Integer> cAlpha;
    public final /* synthetic */ Setting<Integer> alpha;
    public final /* synthetic */ Setting<Integer> cBlue;
    public final /* synthetic */ Setting<Integer> red;
    public /* synthetic */ BlockPos renderPos;
    public final /* synthetic */ Setting<Float> lineWidth;
    public final /* synthetic */ Setting<Boolean> prePlaceCrystal;
    public /* synthetic */ Setting<Boolean> box;
    public /* synthetic */ Setting<Boolean> cSync;
    public final /* synthetic */ Setting<Integer> green;
    public /* synthetic */ EntityPlayer target;
    public /* synthetic */ Setting<Boolean> outline;
    public final /* synthetic */ Setting<Boolean> rotate;
    public final /* synthetic */ Setting<Boolean> crystalCheck;
    public final /* synthetic */ Setting<Integer> blue;
    
    @SubscribeEvent
    @Override
    public void onRender3D(final Render3DEvent render3DEvent) {
        if ((boolean)this.pickOnly.getValue() && !(SmartCity.mc.player.inventory.getCurrentItem().getItem() instanceof ItemPickaxe)) {
            return;
        }
        if (nullCheck()) {
            return;
        }
        this.target = (EntityPlayer)EntityUtil2.getTarget(true, false, false, false, false, 10.0, EntityUtil2.toMode("Closest"));
        if (this.target == null) {
            return;
        }
        final Double value = (Double)this.range.getValue();
        final Vec3d getPositionVector = this.target.getPositionVector();
        if (SmartCity.mc.player.getPositionVector().distanceTo(getPositionVector) <= value) {
            final BlockPos blockPos = new BlockPos(getPositionVector.add(1.0, 0.0, 0.0));
            final BlockPos blockPos2 = new BlockPos(getPositionVector.add(-1.0, 0.0, 0.0));
            final BlockPos blockPos3 = new BlockPos(getPositionVector.add(0.0, 0.0, 1.0));
            final BlockPos blockPos4 = new BlockPos(getPositionVector.add(0.0, 0.0, -1.0));
            final BlockPos blockPos5 = new BlockPos(getPositionVector.add(2.0, 0.0, 0.0));
            final BlockPos blockPos6 = new BlockPos(getPositionVector.add(-2.0, 0.0, 0.0));
            final BlockPos blockPos7 = new BlockPos(getPositionVector.add(0.0, 0.0, 2.0));
            final BlockPos blockPos8 = new BlockPos(getPositionVector.add(0.0, 0.0, -2.0));
            if (!this.isPlayerOccupied() && !(boolean)this.crystalCheck.getValue()) {
                if (isBlockValid(blockPos)) {
                    RenderUtil.drawBoxESP(blockPos, ((boolean)this.cSync.getValue()) ? Colors.getInstance().getCurrentColor() : new Color((int)this.red.getValue(), (int)this.green.getValue(), (int)this.blue.getValue(), (int)this.alpha.getValue()), (boolean)this.outline.getValue(), ((boolean)this.cSync.getValue()) ? Colors.getInstance().getCurrentColor() : new Color((int)this.cRed.getValue(), (int)this.cGreen.getValue(), (int)this.cBlue.getValue(), (int)this.cAlpha.getValue()), (float)this.lineWidth.getValue(), (boolean)this.outline.getValue(), (boolean)this.box.getValue(), (int)this.boxAlpha.getValue(), false);
                }
                if (!isBlockValid(blockPos) && isBlockValid(blockPos2)) {
                    RenderUtil.drawBoxESP(blockPos2, ((boolean)this.cSync.getValue()) ? Colors.getInstance().getCurrentColor() : new Color((int)this.red.getValue(), (int)this.green.getValue(), (int)this.blue.getValue(), (int)this.alpha.getValue()), (boolean)this.outline.getValue(), ((boolean)this.cSync.getValue()) ? Colors.getInstance().getCurrentColor() : new Color((int)this.cRed.getValue(), (int)this.cGreen.getValue(), (int)this.cBlue.getValue(), (int)this.cAlpha.getValue()), (float)this.lineWidth.getValue(), (boolean)this.outline.getValue(), (boolean)this.box.getValue(), (int)this.boxAlpha.getValue(), false);
                }
                if (!isBlockValid(blockPos) && !isBlockValid(blockPos2) && isBlockValid(blockPos3)) {
                    RenderUtil.drawBoxESP(blockPos3, ((boolean)this.cSync.getValue()) ? Colors.getInstance().getCurrentColor() : new Color((int)this.red.getValue(), (int)this.green.getValue(), (int)this.blue.getValue(), (int)this.alpha.getValue()), (boolean)this.outline.getValue(), ((boolean)this.cSync.getValue()) ? Colors.getInstance().getCurrentColor() : new Color((int)this.cRed.getValue(), (int)this.cGreen.getValue(), (int)this.cBlue.getValue(), (int)this.cAlpha.getValue()), (float)this.lineWidth.getValue(), (boolean)this.outline.getValue(), (boolean)this.box.getValue(), (int)this.boxAlpha.getValue(), false);
                }
                if (!isBlockValid(blockPos) && !isBlockValid(blockPos2) && !isBlockValid(blockPos3) && isBlockValid(blockPos4)) {
                    RenderUtil.drawBoxESP(blockPos4, ((boolean)this.cSync.getValue()) ? Colors.getInstance().getCurrentColor() : new Color((int)this.red.getValue(), (int)this.green.getValue(), (int)this.blue.getValue(), (int)this.alpha.getValue()), (boolean)this.outline.getValue(), ((boolean)this.cSync.getValue()) ? Colors.getInstance().getCurrentColor() : new Color((int)this.cRed.getValue(), (int)this.cGreen.getValue(), (int)this.cBlue.getValue(), (int)this.cAlpha.getValue()), (float)this.lineWidth.getValue(), (boolean)this.outline.getValue(), (boolean)this.box.getValue(), (int)this.boxAlpha.getValue(), false);
                }
                if ((!isBlockValid(blockPos) && !isBlockValid(blockPos2) && !isBlockValid(blockPos3) && !isBlockValid(blockPos4)) || SmartCity.mc.player.getPositionVector().distanceTo(getPositionVector) > value) {
                    return;
                }
            }
            if ((boolean)this.crystalCheck.getValue() && this.target != null && this.renderPos != null) {
                RenderUtil.drawBoxESP(this.renderPos, ((boolean)this.cSync.getValue()) ? Colors.getInstance().getCurrentColor() : new Color((int)this.red.getValue(), (int)this.green.getValue(), (int)this.blue.getValue(), (int)this.alpha.getValue()), (boolean)this.outline.getValue(), ((boolean)this.cSync.getValue()) ? Colors.getInstance().getCurrentColor() : new Color((int)this.cRed.getValue(), (int)this.cGreen.getValue(), (int)this.cBlue.getValue(), (int)this.cAlpha.getValue()), (float)this.lineWidth.getValue(), (boolean)this.outline.getValue(), (boolean)this.box.getValue(), (int)this.boxAlpha.getValue(), false);
            }
            if ((boolean)this.prePlaceCrystal.getValue() && this.target != null) {
                if (this.canPlaceCrystal(blockPos5, (boolean)this.oneFifteen.getValue())) {
                    RenderUtil.drawBoxESP(blockPos5, ((boolean)this.cSync.getValue()) ? Colors.getInstance().getCurrentColor() : new Color((int)this.red.getValue(), (int)this.green.getValue(), (int)this.blue.getValue(), (int)this.alpha.getValue()), (boolean)this.outline.getValue(), ((boolean)this.cSync.getValue()) ? Colors.getInstance().getCurrentColor() : new Color((int)this.cRed.getValue(), (int)this.cGreen.getValue(), (int)this.cBlue.getValue(), (int)this.cAlpha.getValue()), (float)this.lineWidth.getValue(), (boolean)this.outline.getValue(), (boolean)this.box.getValue(), (int)this.boxAlpha.getValue(), false);
                }
                if (!this.canPlaceCrystal(blockPos5, (boolean)this.oneFifteen.getValue()) && this.canPlaceCrystal(blockPos6, (boolean)this.oneFifteen.getValue())) {
                    RenderUtil.drawBoxESP(blockPos6, ((boolean)this.cSync.getValue()) ? Colors.getInstance().getCurrentColor() : new Color((int)this.red.getValue(), (int)this.green.getValue(), (int)this.blue.getValue(), (int)this.alpha.getValue()), (boolean)this.outline.getValue(), ((boolean)this.cSync.getValue()) ? Colors.getInstance().getCurrentColor() : new Color((int)this.cRed.getValue(), (int)this.cGreen.getValue(), (int)this.cBlue.getValue(), (int)this.cAlpha.getValue()), (float)this.lineWidth.getValue(), (boolean)this.outline.getValue(), (boolean)this.box.getValue(), (int)this.boxAlpha.getValue(), false);
                }
                if (!this.canPlaceCrystal(blockPos5, (boolean)this.oneFifteen.getValue()) && !this.canPlaceCrystal(blockPos6, (boolean)this.oneFifteen.getValue()) && this.canPlaceCrystal(blockPos7, (boolean)this.oneFifteen.getValue())) {
                    RenderUtil.drawBoxESP(blockPos7, ((boolean)this.cSync.getValue()) ? Colors.getInstance().getCurrentColor() : new Color((int)this.red.getValue(), (int)this.green.getValue(), (int)this.blue.getValue(), (int)this.alpha.getValue()), (boolean)this.outline.getValue(), ((boolean)this.cSync.getValue()) ? Colors.getInstance().getCurrentColor() : new Color((int)this.cRed.getValue(), (int)this.cGreen.getValue(), (int)this.cBlue.getValue(), (int)this.cAlpha.getValue()), (float)this.lineWidth.getValue(), (boolean)this.outline.getValue(), (boolean)this.box.getValue(), (int)this.boxAlpha.getValue(), false);
                }
                if (!this.canPlaceCrystal(blockPos5, (boolean)this.oneFifteen.getValue()) && !this.canPlaceCrystal(blockPos6, (boolean)this.oneFifteen.getValue()) && !this.canPlaceCrystal(blockPos7, (boolean)this.oneFifteen.getValue()) && this.canPlaceCrystal(blockPos8, (boolean)this.oneFifteen.getValue())) {
                    RenderUtil.drawBoxESP(blockPos8, ((boolean)this.cSync.getValue()) ? Colors.getInstance().getCurrentColor() : new Color((int)this.red.getValue(), (int)this.green.getValue(), (int)this.blue.getValue(), (int)this.alpha.getValue()), (boolean)this.outline.getValue(), ((boolean)this.cSync.getValue()) ? Colors.getInstance().getCurrentColor() : new Color((int)this.cRed.getValue(), (int)this.cGreen.getValue(), (int)this.cBlue.getValue(), (int)this.cAlpha.getValue()), (float)this.lineWidth.getValue(), (boolean)this.outline.getValue(), (boolean)this.box.getValue(), (int)this.boxAlpha.getValue(), false);
                }
                if ((!this.canPlaceCrystal(blockPos5, (boolean)this.oneFifteen.getValue()) && !this.canPlaceCrystal(blockPos6, (boolean)this.oneFifteen.getValue()) && !this.canPlaceCrystal(blockPos7, (boolean)this.oneFifteen.getValue()) && !this.canPlaceCrystal(blockPos8, (boolean)this.oneFifteen.getValue())) || SmartCity.mc.player.getPositionVector().distanceTo(getPositionVector) > value) {
                    return;
                }
            }
        }
    }
    
    public boolean canPlaceCrystal(final BlockPos blockPos, final boolean b) {
        return b ? ((SmartCity.mc.world.getBlockState(blockPos).getBlock() instanceof BlockAir && SmartCity.mc.world.getBlockState(new BlockPos(blockPos.getX(), blockPos.getY() - 1, blockPos.getZ())).getBlock() == Blocks.OBSIDIAN) || SmartCity.mc.world.getBlockState(new BlockPos(blockPos.getX(), blockPos.getY() - 1, blockPos.getZ())).getBlock() == Blocks.BEDROCK) : ((SmartCity.mc.world.getBlockState(blockPos).getBlock() instanceof BlockAir && SmartCity.mc.world.getBlockState(new BlockPos(blockPos.getX(), blockPos.getY() + 1, blockPos.getZ())).getBlock() instanceof BlockAir && SmartCity.mc.world.getBlockState(new BlockPos(blockPos.getX(), blockPos.getY() - 1, blockPos.getZ())).getBlock() == Blocks.OBSIDIAN) || SmartCity.mc.world.getBlockState(new BlockPos(blockPos.getX(), blockPos.getY() - 1, blockPos.getZ())).getBlock() == Blocks.BEDROCK);
    }
    
    public SmartCity() {
        super("CityBoss", "Automatically mines city blocks of opponents", Category.COMBAT, true, false, false);
        this.range = (Setting<Integer>)this.register(new Setting("Range", (Object)5, (Object)1, (Object)10));
        this.rotate = (Setting<Boolean>)this.register(new Setting("Rotate", (Object)false));
        this.oneFifteen = (Setting<Boolean>)this.register(new Setting("1.15", (Object)false));
        this.render = (Setting<Boolean>)this.register(new Setting("Render", (Object)true));
        this.crystalCheck = (Setting<Boolean>)this.register(new Setting("Crystal Check", (Object)true));
        this.pickOnly = (Setting<Boolean>)this.register(new Setting("Pickaxe Check", (Object)true));
        this.silentPlace = (Setting<Boolean>)this.register(new Setting("Silent Place", (Object)true));
        this.prePlaceCrystal = (Setting<Boolean>)this.register(new Setting("PrePlace Crystals", (Object)false));
        this.outline = (Setting<Boolean>)this.register(new Setting("Outline", (Object)true, p0 -> (boolean)this.render.getValue()));
        this.cSync = (Setting<Boolean>)this.register(new Setting("Color Sync", (Object)true, p0 -> (boolean)this.render.getValue()));
        this.cRed = (Setting<Integer>)this.register(new Setting("OL-Red", (Object)0, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue()));
        this.cGreen = (Setting<Integer>)this.register(new Setting("OL-Green", (Object)0, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue()));
        this.cBlue = (Setting<Integer>)this.register(new Setting("OL-Blue", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue()));
        this.cAlpha = (Setting<Integer>)this.register(new Setting("OL-Alpha", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue()));
        this.red = (Setting<Integer>)this.register(new Setting("Red", (Object)0, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue()));
        this.green = (Setting<Integer>)this.register(new Setting("Green", (Object)0, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue()));
        this.blue = (Setting<Integer>)this.register(new Setting("Blue", (Object)155, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue()));
        this.alpha = (Setting<Integer>)this.register(new Setting("Alpha", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue()));
        this.box = (Setting<Boolean>)this.register(new Setting("Box", (Object)true, p0 -> (boolean)this.render.getValue()));
        this.boxAlpha = (Setting<Integer>)this.register(new Setting("BoxAlpha", (Object)125, (Object)0, (Object)255, p0 -> (boolean)this.render.getValue()));
        this.lineWidth = (Setting<Float>)this.register(new Setting("LineWidth", (Object)1.0f, (Object)0.1f, (Object)5.0f, p0 -> (boolean)this.render.getValue()));
        this.renderPos = null;
        this.timer = new Timer();
    }
    
    public static boolean isBlockValid(final BlockPos blockPos) {
        final IBlockState getBlockState = SmartCity.mc.world.getBlockState(blockPos);
        return getBlockState.getBlock().getBlockHardness(getBlockState, (World)SmartCity.mc.world, blockPos) != -1.0f;
    }
    
    @Override
    public void onUpdate() {
        if (nullCheck()) {
            return;
        }
        this.target = (EntityPlayer)EntityUtil2.getTarget(true, false, false, false, false, 10.0, EntityUtil2.toMode("Closest"));
        if (this.target == null) {
            return;
        }
        final Double value = (Double)this.range.getValue();
        final Vec3d getPositionVector = this.target.getPositionVector();
        if ((boolean)this.pickOnly.getValue() && !(SmartCity.mc.player.inventory.getCurrentItem().getItem() instanceof ItemPickaxe)) {
            return;
        }
        if (SmartCity.mc.player.getPositionVector().distanceTo(getPositionVector) <= value) {
            final BlockPos renderPos = new BlockPos(getPositionVector.add(1.0, 0.0, 0.0));
            final BlockPos renderPos2 = new BlockPos(getPositionVector.add(-1.0, 0.0, 0.0));
            final BlockPos renderPos3 = new BlockPos(getPositionVector.add(0.0, 0.0, 1.0));
            final BlockPos renderPos4 = new BlockPos(getPositionVector.add(0.0, 0.0, -1.0));
            final BlockPos blockPos = new BlockPos(getPositionVector.add(2.0, 0.0, 0.0));
            final BlockPos blockPos2 = new BlockPos(getPositionVector.add(-2.0, 0.0, 0.0));
            final BlockPos blockPos3 = new BlockPos(getPositionVector.add(0.0, 0.0, 2.0));
            final BlockPos blockPos4 = new BlockPos(getPositionVector.add(0.0, 0.0, -2.0));
            if (!this.isPlayerOccupied() && !(boolean)this.crystalCheck.getValue()) {
                if (isBlockValid(renderPos)) {
                    SmartCity.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, renderPos, EnumFacing.DOWN));
                    SmartCity.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, renderPos, EnumFacing.DOWN));
                }
                if (!isBlockValid(renderPos) && isBlockValid(renderPos2)) {
                    SmartCity.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, renderPos2, EnumFacing.DOWN));
                    SmartCity.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, renderPos2, EnumFacing.DOWN));
                }
                if (!isBlockValid(renderPos) && !isBlockValid(renderPos2) && isBlockValid(renderPos3)) {
                    SmartCity.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, renderPos3, EnumFacing.DOWN));
                    SmartCity.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, renderPos3, EnumFacing.DOWN));
                }
                if (!isBlockValid(renderPos) && !isBlockValid(renderPos2) && !isBlockValid(renderPos3) && isBlockValid(renderPos4)) {
                    SmartCity.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, renderPos4, EnumFacing.DOWN));
                    SmartCity.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, renderPos4, EnumFacing.DOWN));
                }
                if ((!isBlockValid(renderPos) && !isBlockValid(renderPos2) && !isBlockValid(renderPos3) && !isBlockValid(renderPos4)) || SmartCity.mc.player.getPositionVector().distanceTo(getPositionVector) > value) {
                    return;
                }
            }
            if ((boolean)this.crystalCheck.getValue() && this.target != null) {
                if (this.canPlaceCrystal(blockPos, (boolean)this.oneFifteen.getValue()) && isBlockValid(renderPos)) {
                    SmartCity.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, renderPos, EnumFacing.DOWN));
                    SmartCity.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, renderPos, EnumFacing.DOWN));
                    this.renderPos = renderPos;
                }
                else if (this.canPlaceCrystal(blockPos2, (boolean)this.oneFifteen.getValue()) && isBlockValid(renderPos2)) {
                    SmartCity.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, renderPos2, EnumFacing.DOWN));
                    SmartCity.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, renderPos2, EnumFacing.DOWN));
                    this.renderPos = renderPos2;
                }
                else if (this.canPlaceCrystal(blockPos3, (boolean)this.oneFifteen.getValue()) && isBlockValid(renderPos3)) {
                    SmartCity.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, renderPos3, EnumFacing.DOWN));
                    SmartCity.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, renderPos3, EnumFacing.DOWN));
                    this.renderPos = renderPos3;
                }
                else if (this.canPlaceCrystal(blockPos4, (boolean)this.oneFifteen.getValue()) && isBlockValid(renderPos4)) {
                    SmartCity.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, renderPos4, EnumFacing.DOWN));
                    SmartCity.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, renderPos4, EnumFacing.DOWN));
                    this.renderPos = renderPos4;
                }
                else {
                    if (SmartCity.mc.player.getPositionVector().distanceTo(getPositionVector) > value) {
                        this.renderPos = null;
                        return;
                    }
                    this.renderPos = null;
                    return;
                }
            }
            if ((boolean)this.prePlaceCrystal.getValue() && this.target != null) {
                if (this.canPlaceCrystal(blockPos, (boolean)this.oneFifteen.getValue())) {
                    BlockUtil.placeCrystalOnBlock(blockPos, EnumHand.MAIN_HAND, true, false, (boolean)this.silentPlace.getValue());
                }
                if (!this.canPlaceCrystal(blockPos, (boolean)this.oneFifteen.getValue()) && this.canPlaceCrystal(blockPos2, (boolean)this.oneFifteen.getValue())) {
                    BlockUtil.placeCrystalOnBlock(blockPos2, EnumHand.MAIN_HAND, true, false, (boolean)this.silentPlace.getValue());
                }
                if (!this.canPlaceCrystal(blockPos, (boolean)this.oneFifteen.getValue()) && !this.canPlaceCrystal(blockPos2, (boolean)this.oneFifteen.getValue()) && this.canPlaceCrystal(blockPos3, (boolean)this.oneFifteen.getValue())) {
                    BlockUtil.placeCrystalOnBlock(blockPos3, EnumHand.MAIN_HAND, true, false, (boolean)this.silentPlace.getValue());
                }
                if (!this.canPlaceCrystal(blockPos, (boolean)this.oneFifteen.getValue()) && !this.canPlaceCrystal(blockPos2, (boolean)this.oneFifteen.getValue()) && !this.canPlaceCrystal(blockPos3, (boolean)this.oneFifteen.getValue()) && this.canPlaceCrystal(blockPos4, (boolean)this.oneFifteen.getValue())) {
                    BlockUtil.placeCrystalOnBlock(blockPos4, EnumHand.MAIN_HAND, true, false, (boolean)this.silentPlace.getValue());
                }
                if ((!this.canPlaceCrystal(blockPos, (boolean)this.oneFifteen.getValue()) && !this.canPlaceCrystal(blockPos2, (boolean)this.oneFifteen.getValue()) && !this.canPlaceCrystal(blockPos3, (boolean)this.oneFifteen.getValue()) && !this.canPlaceCrystal(blockPos4, (boolean)this.oneFifteen.getValue())) || SmartCity.mc.player.getPositionVector().distanceTo(getPositionVector) > value) {
                    return;
                }
            }
        }
    }
    
    @Override
    public String getDisplayInfo() {
        if (this.target != null) {
            return this.target.getName();
        }
        return null;
    }
    
    public boolean isPlayerOccupied() {
        return SmartCity.mc.player.getHeldItemMainhand().getItem() instanceof ItemPickaxe && SmartCity.mc.player.isHandActive();
    }
}
