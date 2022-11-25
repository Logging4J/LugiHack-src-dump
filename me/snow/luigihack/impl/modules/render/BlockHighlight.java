//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.render;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.api.event.events.*;
import java.awt.*;
import me.snow.luigihack.api.util.*;
import me.snow.luigihack.impl.modules.client.*;
import net.minecraft.util.math.*;

public class BlockHighlight extends Module
{
    private final /* synthetic */ Setting<Integer> blue;
    private final /* synthetic */ Setting<Integer> red;
    public /* synthetic */ Setting<Boolean> rolling;
    public /* synthetic */ Setting<Boolean> customOutline;
    private final /* synthetic */ Setting<Integer> cRed;
    private final /* synthetic */ Setting<Integer> cGreen;
    public /* synthetic */ Setting<Boolean> box;
    private final /* synthetic */ Setting<Integer> green;
    private final /* synthetic */ Setting<Integer> cAlpha;
    public /* synthetic */ Setting<Boolean> outline;
    private final /* synthetic */ Setting<Float> lineWidth;
    private final /* synthetic */ Setting<Integer> boxAlpha;
    public /* synthetic */ Setting<Boolean> colorSync;
    private final /* synthetic */ Setting<Integer> alpha;
    private final /* synthetic */ Setting<Integer> cBlue;
    
    public void onRender3D(final Render3DEvent render3DEvent) {
        final RayTraceResult objectMouseOver = BlockHighlight.mc.objectMouseOver;
        if (objectMouseOver != null && objectMouseOver.typeOfHit == RayTraceResult.Type.BLOCK) {
            final BlockPos getBlockPos = objectMouseOver.getBlockPos();
            if (this.rolling.getValue()) {
                RenderUtil.drawProperGradientBlockOutline(getBlockPos, new Color(HUD.getInstance().colorMap.get(0)), new Color(HUD.getInstance().colorMap.get(this.renderer.scaledHeight / 4)), new Color(HUD.getInstance().colorMap.get(this.renderer.scaledHeight / 2)), 1.0f);
            }
            else {
                RenderUtil.drawBoxESP(getBlockPos, ((boolean)this.colorSync.getValue()) ? Colors.INSTANCE.getCurrentColor() : new Color((int)this.red.getValue(), (int)this.green.getValue(), (int)this.blue.getValue(), (int)this.alpha.getValue()), (boolean)this.customOutline.getValue(), new Color((int)this.cRed.getValue(), (int)this.cGreen.getValue(), (int)this.cBlue.getValue(), (int)this.cAlpha.getValue()), (float)this.lineWidth.getValue(), (boolean)this.outline.getValue(), (boolean)this.box.getValue(), (int)this.boxAlpha.getValue(), false);
            }
        }
    }
    
    public BlockHighlight() {
        super("BlockHighlight", "Highlights the block u look at.", Module.Category.RENDER, false, false, false);
        this.red = (Setting<Integer>)this.register(new Setting("Red", (Object)0, (Object)0, (Object)255));
        this.green = (Setting<Integer>)this.register(new Setting("Green", (Object)255, (Object)0, (Object)255));
        this.blue = (Setting<Integer>)this.register(new Setting("Blue", (Object)0, (Object)0, (Object)255));
        this.alpha = (Setting<Integer>)this.register(new Setting("Alpha", (Object)255, (Object)0, (Object)255));
        this.colorSync = (Setting<Boolean>)this.register(new Setting("Sync", (Object)false));
        this.rolling = (Setting<Boolean>)this.register(new Setting("Rolling", (Object)Boolean.FALSE, p0 -> (boolean)this.colorSync.getValue()));
        this.box = (Setting<Boolean>)this.register(new Setting("Box", (Object)false));
        this.boxAlpha = (Setting<Integer>)this.register(new Setting("BoxAlpha", (Object)125, (Object)0, (Object)255, p0 -> (boolean)this.box.getValue()));
        this.outline = (Setting<Boolean>)this.register(new Setting("Outline", (Object)true));
        this.lineWidth = (Setting<Float>)this.register(new Setting("LineWidth", (Object)1.0f, (Object)0.1f, (Object)5.0f, p0 -> (boolean)this.outline.getValue()));
        this.customOutline = (Setting<Boolean>)this.register(new Setting("CustomLine", (Object)Boolean.FALSE, p0 -> (boolean)this.outline.getValue()));
        this.cRed = (Setting<Integer>)this.register(new Setting("OL-Red", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.customOutline.getValue() && (boolean)this.outline.getValue()));
        this.cGreen = (Setting<Integer>)this.register(new Setting("OL-Green", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.customOutline.getValue() && (boolean)this.outline.getValue()));
        this.cBlue = (Setting<Integer>)this.register(new Setting("OL-Blue", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.customOutline.getValue() && (boolean)this.outline.getValue()));
        this.cAlpha = (Setting<Integer>)this.register(new Setting("OL-Alpha", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.customOutline.getValue() && (boolean)this.outline.getValue()));
    }
}
