//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.gui.components.items.buttons;

import me.snow.luigihack.api.setting.*;
import org.lwjgl.input.*;
import me.snow.luigihack.impl.modules.client.*;
import me.snow.luigihack.*;
import me.snow.luigihack.api.util.*;
import me.snow.luigihack.impl.gui.*;
import me.snow.luigihack.impl.gui.components.*;
import java.util.*;

public class Slider extends Button
{
    private final /* synthetic */ Number max;
    private final /* synthetic */ int difference;
    private final /* synthetic */ Number min;
    public /* synthetic */ Setting setting;
    
    private float partialMultiplier() {
        return this.part() / this.middle();
    }
    
    private void setSettingFromX(final int n) {
        final float n2 = (n - this.x) / (this.width + 7.4f);
        if (this.setting.getValue() instanceof Double) {
            this.setting.setValue((Object)(Math.round(10.0 * ((double)this.setting.getMin() + this.difference * n2)) / 10.0));
        }
        else if (this.setting.getValue() instanceof Float) {
            this.setting.setValue((Object)(Math.round(10.0f * ((float)this.setting.getMin() + this.difference * n2)) / 10.0f));
        }
        else if (this.setting.getValue() instanceof Integer) {
            this.setting.setValue((Object)((int)this.setting.getMin() + (int)(this.difference * n2)));
        }
    }
    
    private void dragSetting(final int settingFromX, final int n) {
        if (this.isHovering(settingFromX, n) && Mouse.isButtonDown(0)) {
            this.setSettingFromX(settingFromX);
        }
    }
    
    private float middle() {
        return this.max.floatValue() - this.min.floatValue();
    }
    
    public void drawScreen(final int n, final int n2, final float n3) {
        this.dragSetting(n, n2);
        RenderUtil.drawRect(this.x, this.y, this.x + this.width + 7.4f, this.y + this.height - 0.5f, this.isHovering(n, n2) ? -2007673515 : 290805077);
        if (ClickGui.getInstance().rainbowRolling.getValue()) {
            final int changeAlpha = ColorUtil.changeAlpha((int)HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y, 0, this.renderer.scaledHeight)), (int)((ClickGui)LuigiHack.moduleManager.getModuleByClass((Class)ClickGui.class)).hoverAlpha.getValue());
            final int changeAlpha2 = ColorUtil.changeAlpha((int)HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y + this.height, 0, this.renderer.scaledHeight)), (int)((ClickGui)LuigiHack.moduleManager.getModuleByClass((Class)ClickGui.class)).hoverAlpha.getValue());
            RenderUtil.drawGradientRect(this.x, this.y, (((Number)this.setting.getValue()).floatValue() <= this.min.floatValue()) ? 0.0f : ((this.width + 7.4f) * this.partialMultiplier()), this.height - 0.5f, this.isHovering(n, n2) ? changeAlpha : ((int)HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y, 0, this.renderer.scaledHeight))), this.isHovering(n, n2) ? changeAlpha2 : ((int)HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y, 0, this.renderer.scaledHeight))));
        }
        else {
            RenderUtil.drawRect(this.x, this.y, (((Number)this.setting.getValue()).floatValue() <= this.min.floatValue()) ? this.x : (this.x + (this.width + 7.4f) * this.partialMultiplier()), this.y + this.height - 0.5f, this.isHovering(n, n2) ? LuigiHack.colorManager.getColorWithAlpha((int)((ClickGui)LuigiHack.moduleManager.getModuleByClass((Class)ClickGui.class)).alpha.getValue()) : LuigiHack.colorManager.getColorWithAlpha((int)((ClickGui)LuigiHack.moduleManager.getModuleByClass((Class)ClickGui.class)).hoverAlpha.getValue()));
        }
        if (ClickGui.getInstance().sideSettings.getValue()) {
            RenderUtil.drawRect(this.x, this.y, this.x + 1.0f, this.y + this.height + 1.0f, ColorUtil.toARGB((int)ClickGui.getInstance().sidered.getValue(), (int)ClickGui.getInstance().sidegreen.getValue(), (int)ClickGui.getInstance().sideblue.getValue(), (int)ClickGui.getInstance().sidealpha.getValue()));
        }
        LuigiHack.textManager.drawStringWithShadow(String.valueOf(new StringBuilder().append(this.getName()).append(" §7").append((this.setting.getValue() instanceof Float) ? ((Number)this.setting.getValue()) : Double.valueOf(((Number)this.setting.getValue()).doubleValue()))), this.x + 2.3f, this.y - 4.0f - LuigiGui.getClickGui().getTextOffset(), -1);
    }
    
    public boolean isHovering(final int n, final int n2) {
        final Iterator<Component> iterator = LuigiGui.getClickGui().getComponents().iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().drag) {
                continue;
            }
            return false;
        }
        return n >= this.getX() && n <= this.getX() + this.getWidth() + 8.0f && n2 >= this.getY() && n2 <= this.getY() + this.height;
    }
    
    private float part() {
        return ((Number)this.setting.getValue()).floatValue() - this.min.floatValue();
    }
    
    public void update() {
        this.setHidden(this.setting.isVisible());
    }
    
    public Slider(final Setting setting) {
        super(setting.getName());
        this.setting = setting;
        this.min = (Number)setting.getMin();
        this.max = (Number)setting.getMax();
        this.difference = this.max.intValue() - this.min.intValue();
        this.width = 15;
    }
    
    public int getHeight() {
        return 11;
    }
    
    public void mouseClicked(final int settingFromX, final int n, final int n2) {
        super.mouseClicked(settingFromX, n, n2);
        if (this.isHovering(settingFromX, n)) {
            this.setSettingFromX(settingFromX);
        }
    }
}
