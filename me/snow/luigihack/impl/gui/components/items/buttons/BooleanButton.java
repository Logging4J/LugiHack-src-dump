//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.gui.components.items.buttons;

import me.snow.luigihack.api.setting.*;
import net.minecraft.init.*;
import net.minecraft.client.audio.*;
import me.snow.luigihack.impl.modules.client.*;
import me.snow.luigihack.*;
import me.snow.luigihack.api.util.*;
import me.snow.luigihack.impl.gui.*;

public class BooleanButton extends Button
{
    private final /* synthetic */ Setting setting;
    
    @Override
    public void update() {
        this.setHidden(this.setting.isVisible());
    }
    
    @Override
    public boolean getState() {
        return (boolean)this.setting.getValue();
    }
    
    @Override
    public void mouseClicked(final int n, final int n2, final int n3) {
        super.mouseClicked(n, n2, n3);
        if (this.isHovering(n, n2)) {
            BooleanButton.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
        }
    }
    
    @Override
    public void drawScreen(final int n, final int n2, final float n3) {
        if (ClickGui.getInstance().rainbowRolling.getValue()) {
            final int changeAlpha = ColorUtil.changeAlpha((int)HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y, 0, this.renderer.scaledHeight)), (int)((ClickGui)LuigiHack.moduleManager.getModuleByClass((Class)ClickGui.class)).hoverAlpha.getValue());
            final int changeAlpha2 = ColorUtil.changeAlpha((int)HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y + this.height, 0, this.renderer.scaledHeight)), (int)((ClickGui)LuigiHack.moduleManager.getModuleByClass((Class)ClickGui.class)).hoverAlpha.getValue());
            RenderUtil.drawGradientRect(this.x, this.y, this.width + 7.4f, this.height - 0.5f, this.getState() ? (this.isHovering(n, n2) ? changeAlpha : HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y, 0, this.renderer.scaledHeight))) : (this.isHovering(n, n2) ? -2007673515 : 290805077), this.getState() ? (this.isHovering(n, n2) ? changeAlpha2 : HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y + this.height, 0, this.renderer.scaledHeight))) : (this.isHovering(n, n2) ? -2007673515 : 290805077));
        }
        else {
            RenderUtil.drawRect(this.x, this.y, this.x + this.width + 7.4f, this.y + this.height - 0.5f, this.getState() ? (this.isHovering(n, n2) ? LuigiHack.colorManager.getColorWithAlpha((int)((ClickGui)LuigiHack.moduleManager.getModuleByClass((Class)ClickGui.class)).alpha.getValue()) : LuigiHack.colorManager.getColorWithAlpha((int)((ClickGui)LuigiHack.moduleManager.getModuleByClass((Class)ClickGui.class)).hoverAlpha.getValue())) : (this.isHovering(n, n2) ? -2007673515 : 290805077));
        }
        if (ClickGui.getInstance().sideSettings.getValue()) {
            RenderUtil.drawRect(this.x, this.y, this.x + 1.0f, this.y + this.height + 1.0f, ColorUtil.toARGB((int)ClickGui.getInstance().sidered.getValue(), (int)ClickGui.getInstance().sidegreen.getValue(), (int)ClickGui.getInstance().sideblue.getValue(), (int)ClickGui.getInstance().sidealpha.getValue()));
        }
        LuigiHack.textManager.drawStringWithShadow(this.getName(), this.x + 2.3f, this.y - 4.0f - LuigiGui.getClickGui().getTextOffset(), this.getState() ? -1 : -5592406);
    }
    
    public BooleanButton(final Setting setting) {
        super(setting.getName());
        this.setting = setting;
        this.width = 15;
    }
    
    @Override
    public int getHeight() {
        return 11;
    }
    
    @Override
    public void toggle() {
        this.setting.setValue((Object)!(boolean)this.setting.getValue());
    }
}
