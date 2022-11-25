//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.gui.components.items.buttons;

import me.snow.luigihack.api.setting.*;
import org.lwjgl.input.*;
import java.awt.*;
import java.awt.datatransfer.*;
import net.minecraft.util.*;
import me.snow.luigihack.impl.modules.client.*;
import me.snow.luigihack.*;
import me.snow.luigihack.api.util.*;
import me.snow.luigihack.impl.gui.*;
import net.minecraft.init.*;
import net.minecraft.client.audio.*;

public class StringButton extends Button
{
    private final /* synthetic */ Setting setting;
    private /* synthetic */ CurrentString currentString;
    public /* synthetic */ boolean isListening;
    
    public void toggle() {
        this.isListening = !this.isListening;
    }
    
    public void setString(final String s) {
        this.currentString = new CurrentString(s);
    }
    
    public static String removeLastChar(final String s) {
        String substring = "";
        if (s != null && s.length() > 0) {
            substring = s.substring(0, s.length() - 1);
        }
        return substring;
    }
    
    public void onKeyTyped(final char c, final int n) {
        if (this.isListening) {
            if (n == 1) {
                return;
            }
            if (n == 28) {
                this.enterString();
            }
            else if (n == 14) {
                this.setString(removeLastChar(this.currentString.getString()));
            }
            else {
                Label_0122: {
                    if (n == 47) {
                        if (!Keyboard.isKeyDown(157)) {
                            if (!Keyboard.isKeyDown(29)) {
                                break Label_0122;
                            }
                        }
                        try {
                            this.setString(String.valueOf(new StringBuilder().append(this.currentString.getString()).append(Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor))));
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        return;
                    }
                }
                if (ChatAllowedCharacters.isAllowedCharacter(c)) {
                    this.setString(String.valueOf(new StringBuilder().append(this.currentString.getString()).append(c)));
                }
            }
        }
    }
    
    public void update() {
        this.setHidden(this.setting.isVisible());
    }
    
    public boolean getState() {
        return !this.isListening;
    }
    
    private void enterString() {
        if (this.currentString.getString().isEmpty()) {
            this.setting.setValue(this.setting.getDefaultValue());
        }
        else {
            this.setting.setValue((Object)this.currentString.getString());
        }
        this.setString("");
        super.onMouseClick();
    }
    
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
        if (this.isListening) {
            LuigiHack.textManager.drawStringWithShadow(String.valueOf(new StringBuilder().append(this.currentString.getString()).append(LuigiHack.textManager.getIdleSign())), this.x + 2.3f, this.y - 4.0f - LuigiGui.getClickGui().getTextOffset(), this.getState() ? -1 : -5592406);
        }
        else {
            LuigiHack.textManager.drawStringWithShadow(String.valueOf(new StringBuilder().append(this.setting.shouldRenderName() ? String.valueOf(new StringBuilder().append(this.setting.getName()).append(" �7")) : "").append(this.setting.getValue())), this.x + 2.3f, this.y - 4.0f - LuigiGui.getClickGui().getTextOffset(), this.getState() ? -1 : -5592406);
        }
    }
    
    public void mouseClicked(final int n, final int n2, final int n3) {
        super.mouseClicked(n, n2, n3);
        if (this.isHovering(n, n2)) {
            StringButton.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
        }
    }
    
    public int getHeight() {
        return 11;
    }
    
    public StringButton(final Setting setting) {
        super(setting.getName());
        this.currentString = new CurrentString("");
        this.setting = setting;
        this.width = 15;
    }
    
    public static class CurrentString
    {
        private final /* synthetic */ String string;
        
        public String getString() {
            return this.string;
        }
        
        public CurrentString(final String string) {
            this.string = string;
        }
    }
}
