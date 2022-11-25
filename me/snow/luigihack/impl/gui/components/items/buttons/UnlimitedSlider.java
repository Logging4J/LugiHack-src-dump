//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.gui.components.items.buttons;

import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.impl.modules.client.*;
import me.snow.luigihack.*;
import me.snow.luigihack.api.util.*;
import me.snow.luigihack.impl.gui.*;
import net.minecraft.init.*;
import net.minecraft.client.audio.*;

public class UnlimitedSlider extends Button
{
    public /* synthetic */ Setting setting;
    
    public int getHeight() {
        return 11;
    }
    
    public boolean getState() {
        return true;
    }
    
    public void drawScreen(final int n, final int n2, final float n3) {
        if (ClickGui.getInstance().rainbowRolling.getValue()) {
            RenderUtil.drawGradientRect((float)(int)this.x, (float)(int)this.y, this.width + 7.4f, (float)this.height, ColorUtil.changeAlpha((int)HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y, 0, this.renderer.scaledHeight)), (int)((ClickGui)LuigiHack.moduleManager.getModuleByClass((Class)ClickGui.class)).hoverAlpha.getValue()), ColorUtil.changeAlpha((int)HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y + this.height, 0, this.renderer.scaledHeight)), (int)((ClickGui)LuigiHack.moduleManager.getModuleByClass((Class)ClickGui.class)).hoverAlpha.getValue()));
        }
        else {
            RenderUtil.drawRect(this.x, this.y, this.x + this.width + 7.4f, this.y + this.height - 0.5f, this.isHovering(n, n2) ? LuigiHack.colorManager.getColorWithAlpha((int)((ClickGui)LuigiHack.moduleManager.getModuleByClass((Class)ClickGui.class)).alpha.getValue()) : LuigiHack.colorManager.getColorWithAlpha((int)((ClickGui)LuigiHack.moduleManager.getModuleByClass((Class)ClickGui.class)).hoverAlpha.getValue()));
        }
        LuigiHack.textManager.drawStringWithShadow(String.valueOf(new StringBuilder().append(" - ").append(this.setting.getName()).append(" §7").append(this.setting.getValue()).append("§r +")), this.x + 2.3f, this.y - 6.0f - LuigiGui.getClickGui().getTextOffset(), this.getState() ? -1 : -5592406);
    }
    
    public UnlimitedSlider(final Setting setting) {
        super(setting.getName());
        this.setting = setting;
        this.width = 15;
    }
    
    public void update() {
        this.setHidden(this.setting.isVisible());
    }
    
    public boolean isRight(final int n) {
        return n > this.x + (this.width + 7.4f) / 2.0f;
    }
    
    public void toggle() {
    }
    
    public void mouseClicked(final int n, final int n2, final int n3) {
        super.mouseClicked(n, n2, n3);
        if (this.isHovering(n, n2)) {
            UnlimitedSlider.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
            if (this.isRight(n)) {
                if (this.setting.getValue() instanceof Double) {
                    this.setting.setValue((Object)((double)this.setting.getValue() + 1.0));
                }
                else if (this.setting.getValue() instanceof Float) {
                    this.setting.setValue((Object)((float)this.setting.getValue() + 1.0f));
                }
                else if (this.setting.getValue() instanceof Integer) {
                    this.setting.setValue((Object)((int)this.setting.getValue() + 1));
                }
            }
            else if (this.setting.getValue() instanceof Double) {
                this.setting.setValue((Object)((double)this.setting.getValue() - 1.0));
            }
            else if (this.setting.getValue() instanceof Float) {
                this.setting.setValue((Object)((float)this.setting.getValue() - 1.0f));
            }
            else if (this.setting.getValue() instanceof Integer) {
                this.setting.setValue((Object)((int)this.setting.getValue() - 1));
            }
        }
    }
}
