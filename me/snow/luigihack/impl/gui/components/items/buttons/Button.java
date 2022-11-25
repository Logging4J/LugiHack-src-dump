//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.gui.components.items.buttons;

import me.snow.luigihack.impl.gui.components.items.*;
import net.minecraft.init.*;
import net.minecraft.client.audio.*;
import me.snow.luigihack.impl.gui.*;
import me.snow.luigihack.impl.gui.components.*;
import java.util.*;
import me.snow.luigihack.impl.modules.client.*;
import me.snow.luigihack.api.util.*;
import me.snow.luigihack.*;

public class Button extends Item
{
    private /* synthetic */ boolean state;
    
    public Button(final String s) {
        super(s);
        this.height = 12;
    }
    
    public boolean getState() {
        return this.state;
    }
    
    public void onMouseClick() {
        this.state = !this.state;
        this.toggle();
        Button.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
    }
    
    @Override
    public int getHeight() {
        return 11;
    }
    
    @Override
    public void mouseClicked(final int n, final int n2, final int n3) {
        if (n3 == 0 && this.isHovering(n, n2)) {
            this.onMouseClick();
        }
    }
    
    public void toggle() {
    }
    
    public boolean isHovering(final int n, final int n2) {
        final Iterator<Component> iterator = LuigiGui.getClickGui().getComponents().iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().drag) {
                continue;
            }
            return false;
        }
        return n >= this.getX() && n <= this.getX() + this.getWidth() && n2 >= this.getY() && n2 <= this.getY() + this.height;
    }
    
    @Override
    public void drawScreen(final int n, final int n2, final float n3) {
        if (ClickGui.getInstance().moduleOutline.getValue()) {
            RenderUtil.drawOutlineRect(this.x, this.y, this.x + this.width, this.y + this.height - 0.2f, this.getState() ? ColorUtil.toRGBA((int)ClickGui.getInstance().moRed.getValue(), (int)ClickGui.getInstance().moGreen.getValue(), (int)ClickGui.getInstance().moBlue.getValue(), (int)ClickGui.getInstance().moAlpha.getValue()) : ColorUtil.toRGBA((int)ClickGui.getInstance().moRed.getValue(), (int)ClickGui.getInstance().moGreen.getValue(), (int)ClickGui.getInstance().moBlue.getValue(), (int)ClickGui.getInstance().moAlpha.getValue()));
        }
        if (ClickGui.getInstance().rainbowRolling.getValue()) {
            final int changeAlpha = ColorUtil.changeAlpha((int)HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y, 0, this.renderer.scaledHeight)), (int)((ClickGui)LuigiHack.moduleManager.getModuleByClass((Class)ClickGui.class)).hoverAlpha.getValue());
            final int changeAlpha2 = ColorUtil.changeAlpha((int)HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y + this.height, 0, this.renderer.scaledHeight)), (int)((ClickGui)LuigiHack.moduleManager.getModuleByClass((Class)ClickGui.class)).hoverAlpha.getValue());
            RenderUtil.drawGradientRect(this.x, this.y, (float)this.width, this.height - 0.5f, this.getState() ? (this.isHovering(n, n2) ? changeAlpha : HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y, 0, this.renderer.scaledHeight))) : (this.isHovering(n, n2) ? -2007673515 : 290805077), this.getState() ? (this.isHovering(n, n2) ? changeAlpha2 : HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y + this.height, 0, this.renderer.scaledHeight))) : (this.isHovering(n, n2) ? -2007673515 : ColorUtil.toRGBA((int)ClickGui.getInstance().d_red.getValue(), (int)ClickGui.getInstance().d_green.getValue(), (int)ClickGui.getInstance().d_blue.getValue(), (int)ClickGui.getInstance().d_alpha.getValue())));
        }
        else {
            RenderUtil.drawRect(this.x, this.y, this.x + this.width, this.y + this.height - 0.5f, this.getState() ? (this.isHovering(n, n2) ? LuigiHack.colorManager.getColorWithAlpha((int)((ClickGui)LuigiHack.moduleManager.getModuleByClass((Class)ClickGui.class)).alpha.getValue()) : LuigiHack.colorManager.getColorWithAlpha((int)((ClickGui)LuigiHack.moduleManager.getModuleByClass((Class)ClickGui.class)).hoverAlpha.getValue())) : (this.isHovering(n, n2) ? -2007673515 : ColorUtil.toRGBA((int)ClickGui.getInstance().d_red.getValue(), (int)ClickGui.getInstance().d_green.getValue(), (int)ClickGui.getInstance().d_blue.getValue(), (int)ClickGui.getInstance().d_alpha.getValue())));
        }
        LuigiHack.textManager.drawStringWithShadow(this.getName(), this.x + 2.3f, this.y - 4.0f - LuigiGui.getClickGui().getTextOffset(), this.getState() ? ColorUtil.toRGBA((int)ClickGui.getInstance().textRed.getValue(), (int)ClickGui.getInstance().textGreen.getValue(), (int)ClickGui.getInstance().textBlue.getValue(), (int)ClickGui.getInstance().textAlpha.getValue()) : ColorUtil.toRGBA((int)ClickGui.getInstance().textRed2.getValue(), (int)ClickGui.getInstance().textGreen2.getValue(), (int)ClickGui.getInstance().textBlue2.getValue(), (int)ClickGui.getInstance().textAlpha2.getValue()));
    }
}
