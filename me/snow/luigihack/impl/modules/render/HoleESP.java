//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.render;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import me.snow.luigihack.impl.modules.client.*;
import java.awt.*;
import me.snow.luigihack.api.util.*;
import me.snow.luigihack.api.util.skid.oyvey.*;

public class HoleESP extends Module
{
    private /* synthetic */ Setting<Integer> safecRed;
    private /* synthetic */ Setting<Integer> green;
    private /* synthetic */ Setting<Integer> DcAlpha;
    public /* synthetic */ Setting<Boolean> rainbow;
    public /* synthetic */ Setting<Boolean> renderOwn;
    public /* synthetic */ Setting<Boolean> invertGradientBox;
    private /* synthetic */ Setting<Integer> safecGreen;
    private /* synthetic */ Setting<Integer> alpha;
    private /* synthetic */ Setting<Integer> doubleblue;
    public /* synthetic */ Setting<Boolean> fov;
    public /* synthetic */ Setting<Boolean> invertGradientOutline;
    private /* synthetic */ Setting<Integer> DcRed;
    private /* synthetic */ Setting<Integer> cBlue;
    private /* synthetic */ Setting<Integer> cGreen;
    private final /* synthetic */ Setting<Integer> range;
    private /* synthetic */ Setting<Integer> doublegreen;
    private /* synthetic */ int currentAlpha;
    private /* synthetic */ Setting<Integer> safeRed;
    private /* synthetic */ Setting<Integer> doublered;
    private /* synthetic */ Setting<Integer> safeGreen;
    public /* synthetic */ Setting<Double> height;
    public /* synthetic */ Setting<Boolean> box;
    private /* synthetic */ Setting<Integer> red;
    public /* synthetic */ Setting<Boolean> safeColor;
    private /* synthetic */ Setting<Integer> doublealpha;
    public /* synthetic */ Setting<Boolean> gradientBox;
    private /* synthetic */ Setting<Integer> DcGreen;
    private /* synthetic */ Setting<Integer> cAlpha;
    private /* synthetic */ Setting<Integer> safecAlpha;
    private /* synthetic */ Setting<Integer> safeAlpha;
    public /* synthetic */ Setting<Boolean> gradientOutline;
    private /* synthetic */ Setting<Integer> boxAlpha;
    private /* synthetic */ Setting<Integer> safeBlue;
    private final /* synthetic */ Setting<Integer> rangeY;
    private /* synthetic */ Setting<Integer> blue;
    public /* synthetic */ Setting<Boolean> doublehole;
    private /* synthetic */ Setting<Float> lineWidth;
    private /* synthetic */ Setting<Integer> cRed;
    private /* synthetic */ Setting<Integer> safecBlue;
    public /* synthetic */ Setting<Double> Dheight;
    public /* synthetic */ Setting<Boolean> customOutline;
    public /* synthetic */ Setting<Boolean> outline;
    private static /* synthetic */ HoleESP INSTANCE;
    private /* synthetic */ Setting<Integer> DcBlue;
    
    public HoleESP() {
        super("HoleESP", "Shows safe spots.", Module.Category.RENDER, false, false, false);
        this.range = (Setting<Integer>)this.register(new Setting("RangeX", (Object)10, (Object)0, (Object)10));
        this.rangeY = (Setting<Integer>)this.register(new Setting("RangeY", (Object)6, (Object)0, (Object)10));
        this.renderOwn = (Setting<Boolean>)this.register(new Setting("RenderOwn", (Object)true));
        this.fov = (Setting<Boolean>)this.register(new Setting("InFov", (Object)false));
        this.box = (Setting<Boolean>)this.register(new Setting("Box", (Object)true));
        this.doublehole = (Setting<Boolean>)this.register(new Setting("Double Hole", (Object)true));
        this.rainbow = (Setting<Boolean>)this.register(new Setting("Rainbow", (Object)false));
        this.gradientBox = (Setting<Boolean>)this.register(new Setting("Gradient", (Object)false, p0 -> (boolean)this.box.getValue()));
        this.invertGradientBox = (Setting<Boolean>)this.register(new Setting("ReverseGradient", (Object)Boolean.FALSE, p0 -> (boolean)this.gradientBox.getValue()));
        this.outline = (Setting<Boolean>)this.register(new Setting("Outline", (Object)true));
        this.gradientOutline = (Setting<Boolean>)this.register(new Setting("GradientOutline", (Object)Boolean.FALSE, p0 -> (boolean)this.outline.getValue()));
        this.invertGradientOutline = (Setting<Boolean>)this.register(new Setting("ReverseOutline", (Object)Boolean.FALSE, p0 -> (boolean)this.gradientOutline.getValue()));
        this.height = (Setting<Double>)this.register(new Setting("Height", (Object)0.0, (Object)(-2.0), (Object)2.0));
        this.Dheight = (Setting<Double>)this.register(new Setting("DoubleHole Height", (Object)(-1.0), (Object)(-2.0), (Object)2.0));
        this.red = (Setting<Integer>)this.register(new Setting("Red", (Object)255, (Object)0, (Object)255));
        this.green = (Setting<Integer>)this.register(new Setting("Green", (Object)12, (Object)0, (Object)255));
        this.blue = (Setting<Integer>)this.register(new Setting("Blue", (Object)51, (Object)0, (Object)255));
        this.alpha = (Setting<Integer>)this.register(new Setting("Alpha", (Object)255, (Object)0, (Object)255));
        this.doublered = (Setting<Integer>)this.register(new Setting("DoubleHoleRed", (Object)51, (Object)0, (Object)255));
        this.doublegreen = (Setting<Integer>)this.register(new Setting("DoubleHoleGreen", (Object)12, (Object)0, (Object)255));
        this.doubleblue = (Setting<Integer>)this.register(new Setting("DoubleHoleBlue", (Object)168, (Object)0, (Object)255));
        this.doublealpha = (Setting<Integer>)this.register(new Setting("DoubleHoleAlpha", (Object)255, (Object)0, (Object)255));
        this.boxAlpha = (Setting<Integer>)this.register(new Setting("BoxAlpha", (Object)70, (Object)0, (Object)255, p0 -> (boolean)this.box.getValue()));
        this.lineWidth = (Setting<Float>)this.register(new Setting("LineWidth", (Object)1.0f, (Object)0.1f, (Object)5.0f, p0 -> (boolean)this.outline.getValue()));
        this.safeColor = (Setting<Boolean>)this.register(new Setting("BedrockColor", (Object)true));
        this.safeRed = (Setting<Integer>)this.register(new Setting("BedrockRed", (Object)43, (Object)0, (Object)255, p0 -> (boolean)this.safeColor.getValue()));
        this.safeGreen = (Setting<Integer>)this.register(new Setting("BedrockGreen", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.safeColor.getValue()));
        this.safeBlue = (Setting<Integer>)this.register(new Setting("BedrockBlue", (Object)0, (Object)0, (Object)255, p0 -> (boolean)this.safeColor.getValue()));
        this.safeAlpha = (Setting<Integer>)this.register(new Setting("BedrockAlpha", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.safeColor.getValue()));
        this.customOutline = (Setting<Boolean>)this.register(new Setting("CustomLine", (Object)Boolean.FALSE, p0 -> (boolean)this.outline.getValue()));
        this.cRed = (Setting<Integer>)this.register(new Setting("OL-Red", (Object)0, (Object)0, (Object)255, p0 -> (boolean)this.customOutline.getValue() && (boolean)this.outline.getValue()));
        this.cGreen = (Setting<Integer>)this.register(new Setting("OL-Green", (Object)0, (Object)0, (Object)255, p0 -> (boolean)this.customOutline.getValue() && (boolean)this.outline.getValue()));
        this.cBlue = (Setting<Integer>)this.register(new Setting("OL-Blue", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.customOutline.getValue() && (boolean)this.outline.getValue()));
        this.cAlpha = (Setting<Integer>)this.register(new Setting("OL-Alpha", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.customOutline.getValue() && (boolean)this.outline.getValue()));
        this.DcRed = (Setting<Integer>)this.register(new Setting("OL-DoubleRed", (Object)0, (Object)0, (Object)255, p0 -> (boolean)this.customOutline.getValue() && (boolean)this.outline.getValue()));
        this.DcGreen = (Setting<Integer>)this.register(new Setting("OL-DoubleGreen", (Object)0, (Object)0, (Object)255, p0 -> (boolean)this.customOutline.getValue() && (boolean)this.outline.getValue()));
        this.DcBlue = (Setting<Integer>)this.register(new Setting("OL-DoubleBlue", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.customOutline.getValue() && (boolean)this.outline.getValue()));
        this.DcAlpha = (Setting<Integer>)this.register(new Setting("OL-DoubleAlpha", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.customOutline.getValue() && (boolean)this.outline.getValue()));
        this.safecRed = (Setting<Integer>)this.register(new Setting("OL-SafeRed", (Object)0, (Object)0, (Object)255, p0 -> (boolean)this.customOutline.getValue() && (boolean)this.outline.getValue() && (boolean)this.safeColor.getValue()));
        this.safecGreen = (Setting<Integer>)this.register(new Setting("OL-SafeGreen", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.customOutline.getValue() && (boolean)this.outline.getValue() && (boolean)this.safeColor.getValue()));
        this.safecBlue = (Setting<Integer>)this.register(new Setting("OL-SafeBlue", (Object)0, (Object)0, (Object)255, p0 -> (boolean)this.customOutline.getValue() && (boolean)this.outline.getValue() && (boolean)this.safeColor.getValue()));
        this.safecAlpha = (Setting<Integer>)this.register(new Setting("OL-SafeAlpha", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.customOutline.getValue() && (boolean)this.outline.getValue() && (boolean)this.safeColor.getValue()));
        this.currentAlpha = 0;
        this.setInstance();
    }
    
    public void onRender3D(final Render3DEvent render3DEvent) {
        assert HoleESP.mc.renderViewEntity != null;
        final Vec3i vec3i = new Vec3i(HoleESP.mc.renderViewEntity.posX, HoleESP.mc.renderViewEntity.posY, HoleESP.mc.renderViewEntity.posZ);
        for (int i = vec3i.getX() - (int)this.range.getValue(); i < vec3i.getX() + (int)this.range.getValue(); ++i) {
            for (int j = vec3i.getZ() - (int)this.range.getValue(); j < vec3i.getZ() + (int)this.range.getValue(); ++j) {
                for (int k = vec3i.getY() + (int)this.rangeY.getValue(); k > vec3i.getY() - (int)this.rangeY.getValue(); --k) {
                    final BlockPos blockPos = new BlockPos(i, k, j);
                    if (this.doublehole.getValue()) {
                        if (HoleUtilStay.is2securityHole(blockPos) && (HoleESP.mc.world.getBlockState(new BlockPos(blockPos.getX(), blockPos.getY() + 1, blockPos.getZ())).getBlock() == Blocks.AIR || HoleESP.mc.world.getBlockState(new BlockPos(HoleUtilStay.is2Hole(blockPos).getX(), HoleUtilStay.is2Hole(blockPos).getY() + 1, HoleUtilStay.is2Hole(blockPos).getZ())).getBlock() == Blocks.AIR) && HoleESP.mc.world.getBlockState(new BlockPos(blockPos.getX(), blockPos.getY() - 1, blockPos.getZ())).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(new BlockPos(HoleUtilStay.is2Hole(blockPos).getX(), HoleUtilStay.is2Hole(blockPos).getY() - 1, HoleUtilStay.is2Hole(blockPos).getZ())).getBlock() == Blocks.BEDROCK) {
                            RenderUtil.drawBoxESP(blockPos, ((boolean)this.rainbow.getValue()) ? ColorUtil2.rainbow((int)Global.getInstance().rainbowHue.getValue()) : new Color((int)this.doublered.getValue(), (int)this.doublegreen.getValue(), (int)this.doubleblue.getValue(), (int)this.doublealpha.getValue()), (boolean)this.customOutline.getValue(), new Color((int)this.DcRed.getValue(), (int)this.DcGreen.getValue(), (int)this.DcBlue.getValue(), (int)this.DcAlpha.getValue()), (float)this.lineWidth.getValue(), (boolean)this.outline.getValue(), (boolean)this.box.getValue(), (int)this.boxAlpha.getValue(), true, (double)this.Dheight.getValue(), (boolean)this.gradientBox.getValue(), (boolean)this.gradientOutline.getValue(), (boolean)this.invertGradientBox.getValue(), (boolean)this.invertGradientOutline.getValue(), this.currentAlpha);
                            RenderUtil.drawBoxESP(HoleUtilStay.is2Hole(blockPos), ((boolean)this.rainbow.getValue()) ? ColorUtil2.rainbow((int)Global.getInstance().rainbowHue.getValue()) : new Color((int)this.doublered.getValue(), (int)this.doublegreen.getValue(), (int)this.doubleblue.getValue(), (int)this.doublealpha.getValue()), (boolean)this.customOutline.getValue(), new Color((int)this.DcRed.getValue(), (int)this.DcGreen.getValue(), (int)this.DcBlue.getValue(), (int)this.DcAlpha.getValue()), (float)this.lineWidth.getValue(), (boolean)this.outline.getValue(), (boolean)this.box.getValue(), (int)this.boxAlpha.getValue(), true, (double)this.Dheight.getValue(), (boolean)this.gradientBox.getValue(), (boolean)this.gradientOutline.getValue(), (boolean)this.invertGradientBox.getValue(), (boolean)this.invertGradientOutline.getValue(), this.currentAlpha);
                            continue;
                        }
                        if (HoleUtilStay.is2Hole(blockPos) != null && (HoleESP.mc.world.getBlockState(new BlockPos(blockPos.getX(), blockPos.getY() + 1, blockPos.getZ())).getBlock() == Blocks.AIR || HoleESP.mc.world.getBlockState(new BlockPos(HoleUtilStay.is2Hole(blockPos).getX(), HoleUtilStay.is2Hole(blockPos).getY() + 1, HoleUtilStay.is2Hole(blockPos).getZ())).getBlock() == Blocks.AIR) && (HoleESP.mc.world.getBlockState(new BlockPos(blockPos.getX(), blockPos.getY() - 1, blockPos.getZ())).getBlock() == Blocks.BEDROCK || HoleESP.mc.world.getBlockState(new BlockPos(blockPos.getX(), blockPos.getY() - 1, blockPos.getZ())).getBlock() == Blocks.OBSIDIAN) && (HoleESP.mc.world.getBlockState(new BlockPos(HoleUtilStay.is2Hole(blockPos).getX(), HoleUtilStay.is2Hole(blockPos).getY() - 1, HoleUtilStay.is2Hole(blockPos).getZ())).getBlock() == Blocks.BEDROCK || HoleESP.mc.world.getBlockState(new BlockPos(HoleUtilStay.is2Hole(blockPos).getX(), HoleUtilStay.is2Hole(blockPos).getY() - 1, HoleUtilStay.is2Hole(blockPos).getZ())).getBlock() == Blocks.OBSIDIAN)) {
                            RenderUtil.drawBoxESP(blockPos, ((boolean)this.rainbow.getValue()) ? ColorUtil2.rainbow((int)Global.getInstance().rainbowHue.getValue()) : new Color((int)this.doublered.getValue(), (int)this.doublegreen.getValue(), (int)this.doubleblue.getValue(), (int)this.doublealpha.getValue()), (boolean)this.customOutline.getValue(), new Color((int)this.DcRed.getValue(), (int)this.DcGreen.getValue(), (int)this.DcBlue.getValue(), (int)this.DcAlpha.getValue()), (float)this.lineWidth.getValue(), (boolean)this.outline.getValue(), (boolean)this.box.getValue(), (int)this.boxAlpha.getValue(), true, (double)this.Dheight.getValue(), (boolean)this.gradientBox.getValue(), (boolean)this.gradientOutline.getValue(), (boolean)this.invertGradientBox.getValue(), (boolean)this.invertGradientOutline.getValue(), this.currentAlpha);
                            RenderUtil.drawBoxESP(HoleUtilStay.is2Hole(blockPos), ((boolean)this.rainbow.getValue()) ? ColorUtil2.rainbow((int)Global.getInstance().rainbowHue.getValue()) : new Color((int)this.doublered.getValue(), (int)this.doublegreen.getValue(), (int)this.doubleblue.getValue(), (int)this.doublealpha.getValue()), (boolean)this.customOutline.getValue(), new Color((int)this.DcRed.getValue(), (int)this.DcGreen.getValue(), (int)this.DcBlue.getValue(), (int)this.cAlpha.getValue()), (float)this.lineWidth.getValue(), (boolean)this.outline.getValue(), (boolean)this.box.getValue(), (int)this.boxAlpha.getValue(), true, (double)this.Dheight.getValue(), (boolean)this.gradientBox.getValue(), (boolean)this.gradientOutline.getValue(), (boolean)this.invertGradientBox.getValue(), (boolean)this.invertGradientOutline.getValue(), this.currentAlpha);
                            continue;
                        }
                    }
                    if (HoleESP.mc.world.getBlockState(blockPos).getBlock().equals(Blocks.AIR) && HoleESP.mc.world.getBlockState(blockPos.add(0, 1, 0)).getBlock().equals(Blocks.AIR) && HoleESP.mc.world.getBlockState(blockPos.add(0, 2, 0)).getBlock().equals(Blocks.AIR) && (!blockPos.equals((Object)new BlockPos(HoleESP.mc.player.posX, HoleESP.mc.player.posY, HoleESP.mc.player.posZ)) || (boolean)this.renderOwn.getValue())) {
                        if (BlockUtil.isPosInFov(blockPos) || !(boolean)this.fov.getValue()) {
                            if (HoleESP.mc.world.getBlockState(blockPos.north()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(blockPos.east()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(blockPos.west()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(blockPos.south()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(blockPos.down()).getBlock() == Blocks.BEDROCK) {
                                RenderUtil.drawBoxESP(blockPos, ((boolean)this.rainbow.getValue()) ? ColorUtil2.rainbow((int)Global.getInstance().rainbowHue.getValue()) : new Color((int)this.safeRed.getValue(), (int)this.safeGreen.getValue(), (int)this.safeBlue.getValue(), (int)this.safeAlpha.getValue()), (boolean)this.customOutline.getValue(), new Color((int)this.safecRed.getValue(), (int)this.safecGreen.getValue(), (int)this.safecBlue.getValue(), (int)this.safecAlpha.getValue()), (float)this.lineWidth.getValue(), (boolean)this.outline.getValue(), (boolean)this.box.getValue(), (int)this.boxAlpha.getValue(), true, (double)this.height.getValue(), (boolean)this.gradientBox.getValue(), (boolean)this.gradientOutline.getValue(), (boolean)this.invertGradientBox.getValue(), (boolean)this.invertGradientOutline.getValue(), this.currentAlpha);
                            }
                            else if (BlockUtil2.isBlockUnSafe(HoleESP.mc.world.getBlockState(blockPos.down()).getBlock()) && BlockUtil2.isBlockUnSafe(HoleESP.mc.world.getBlockState(blockPos.east()).getBlock()) && BlockUtil2.isBlockUnSafe(HoleESP.mc.world.getBlockState(blockPos.west()).getBlock()) && BlockUtil2.isBlockUnSafe(HoleESP.mc.world.getBlockState(blockPos.south()).getBlock())) {
                                if (BlockUtil2.isBlockUnSafe(HoleESP.mc.world.getBlockState(blockPos.north()).getBlock())) {
                                    RenderUtil.drawBoxESP(blockPos, ((boolean)this.rainbow.getValue()) ? ColorUtil2.rainbow((int)Global.getInstance().rainbowHue.getValue()) : new Color((int)this.red.getValue(), (int)this.green.getValue(), (int)this.blue.getValue(), (int)this.alpha.getValue()), (boolean)this.customOutline.getValue(), new Color((int)this.cRed.getValue(), (int)this.cGreen.getValue(), (int)this.cBlue.getValue(), (int)this.cAlpha.getValue()), (float)this.lineWidth.getValue(), (boolean)this.outline.getValue(), (boolean)this.box.getValue(), (int)this.boxAlpha.getValue(), true, (double)this.height.getValue(), (boolean)this.gradientBox.getValue(), (boolean)this.gradientOutline.getValue(), (boolean)this.invertGradientBox.getValue(), (boolean)this.invertGradientOutline.getValue(), this.currentAlpha);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    static {
        HoleESP.INSTANCE = new HoleESP();
    }
    
    private void setInstance() {
        HoleESP.INSTANCE = this;
    }
    
    public static HoleESP getInstance() {
        if (HoleESP.INSTANCE == null) {
            HoleESP.INSTANCE = new HoleESP();
        }
        return HoleESP.INSTANCE;
    }
}
