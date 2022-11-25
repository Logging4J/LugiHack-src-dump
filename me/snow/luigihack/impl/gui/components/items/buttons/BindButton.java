//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.gui.components.items.buttons;

import net.minecraft.init.*;
import net.minecraft.client.audio.*;
import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.impl.modules.client.*;
import me.snow.luigihack.*;
import me.snow.luigihack.api.util.*;
import me.snow.luigihack.impl.gui.*;
import me.snow.luigihack.api.manager.*;

public class BindButton extends Button
{
    public /* synthetic */ boolean isListening;
    private final /* synthetic */ Setting setting;
    
    @Override
    public void mouseClicked(final int n, final int n2, final int n3) {
        super.mouseClicked(n, n2, n3);
        if (this.isHovering(n, n2)) {
            BindButton.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
        }
    }
    
    @Override
    public void onKeyTyped(final char c, final int n) {
        if (this.isListening) {
            Bind value = new Bind(n);
            if (value.toString().equalsIgnoreCase("Escape")) {
                return;
            }
            if (value.toString().equalsIgnoreCase("Delete")) {
                value = new Bind(-1);
            }
            this.setting.setValue((Object)value);
            super.onMouseClick();
        }
    }
    
    @Override
    public int getHeight() {
        return 11;
    }
    
    @Override
    public void drawScreen(final int n, final int n2, final float n3) {
        if (ClickGui.getInstance().rainbowRolling.getValue()) {
            final int changeAlpha = ColorUtil.changeAlpha((int)HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y, 0, this.renderer.scaledHeight)), (int)((ClickGui)LuigiHack.moduleManager.getModuleByClass((Class)ClickGui.class)).hoverAlpha.getValue());
            final int changeAlpha2 = ColorUtil.changeAlpha((int)HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y + this.height, 0, this.renderer.scaledHeight)), (int)((ClickGui)LuigiHack.moduleManager.getModuleByClass((Class)ClickGui.class)).hoverAlpha.getValue());
            RenderUtil.drawGradientRect(this.x, this.y, this.width + 7.4f, this.height - 0.5f, this.getState() ? (this.isHovering(n, n2) ? changeAlpha : HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y, 0, this.renderer.scaledHeight))) : (this.isHovering(n, n2) ? -2007673515 : 290805077), this.getState() ? (this.isHovering(n, n2) ? changeAlpha2 : HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y + this.height, 0, this.renderer.scaledHeight))) : (this.isHovering(n, n2) ? -2007673515 : 290805077));
        }
        else {
            final float x = this.x;
            final float y = this.y;
            final float n4 = this.x + this.width + 7.4f;
            final float n5 = this.y + this.height - 0.5f;
            int n6;
            if (this.getState()) {
                if (!this.isHovering(n, n2)) {
                    n6 = LuigiHack.colorManager.getColorWithAlpha((int)((ClickGui)LuigiHack.moduleManager.getModuleByClass((Class)ClickGui.class)).hoverAlpha.getValue());
                }
                else {
                    final ColorManager colorManager = LuigiHack.colorManager;
                    final ModuleManager moduleManager = LuigiHack.moduleManager;
                    n6 = colorManager.getColorWithAlpha((int)((ClickGui)ModuleManager.getModuleByName("ClickGui")).alpha.getValue());
                }
            }
            else {
                n6 = (this.isHovering(n, n2) ? -2007673515 : 290805077);
            }
            RenderUtil.drawRect(x, y, n4, n5, n6);
            if (ClickGui.getInstance().sideSettings.getValue()) {
                RenderUtil.drawRect(this.x, this.y, this.x + 1.0f, this.y + this.height + 1.0f, ColorUtil.toARGB((int)ClickGui.getInstance().sidered.getValue(), (int)ClickGui.getInstance().sidegreen.getValue(), (int)ClickGui.getInstance().sideblue.getValue(), (int)ClickGui.getInstance().sidealpha.getValue()));
            }
        }
        if (this.isListening) {
            LuigiHack.textManager.drawStringWithShadow("Press a key...", this.x + 2.3f, this.y - 4.0f - LuigiGui.getClickGui().getTextOffset(), this.getState() ? -1 : -5592406);
        }
        else {
            LuigiHack.textManager.drawStringWithShadow(String.valueOf(new StringBuilder().append(this.setting.getName()).append(" §7").append(this.setting.getValue().toString())), this.x + 2.3f, this.y - 4.0f - LuigiGui.getClickGui().getTextOffset(), this.getState() ? -1 : -5592406);
        }
    }
    
    @Override
    public boolean getState() {
        return !this.isListening;
    }
    
    @Override
    public void update() {
        this.setHidden(this.setting.isVisible());
    }
    
    @Override
    public void toggle() {
        this.isListening = !this.isListening;
    }
    
    public BindButton(final Setting setting) {
        super(setting.getName());
        this.setting = setting;
        this.width = 15;
    }
}
