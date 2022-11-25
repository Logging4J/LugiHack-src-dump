//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.gui.components.items.buttons;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.impl.gui.components.items.*;
import net.minecraft.util.*;
import net.minecraft.init.*;
import net.minecraft.client.audio.*;
import me.snow.luigihack.impl.modules.client.*;
import me.snow.luigihack.*;
import me.snow.luigihack.impl.gui.*;
import me.snow.luigihack.api.util.*;
import org.lwjgl.opengl.*;
import java.util.*;
import me.snow.luigihack.api.setting.*;

public class ModuleButton extends Button
{
    private final /* synthetic */ Module module;
    private /* synthetic */ boolean subOpen;
    private /* synthetic */ List<Item> items;
    private final /* synthetic */ ResourceLocation gearawa;
    
    public Module getModule() {
        return this.module;
    }
    
    public void mouseClicked(final int n, final int n2, final int n3) {
        super.mouseClicked(n, n2, n3);
        if (!this.items.isEmpty()) {
            if (n3 == 1 && this.isHovering(n, n2)) {
                this.subOpen = !this.subOpen;
                ModuleButton.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
            }
            if (this.subOpen) {
                for (final Item item : this.items) {
                    if (item.isHidden()) {
                        continue;
                    }
                    item.mouseClicked(n, n2, n3);
                }
            }
        }
    }
    
    public void onKeyTyped(final char c, final int n) {
        super.onKeyTyped(c, n);
        if (!this.items.isEmpty() && this.subOpen) {
            for (final Item item : this.items) {
                if (item.isHidden()) {
                    continue;
                }
                item.onKeyTyped(c, n);
            }
        }
    }
    
    public void drawScreen(final int n, final int n2, final float n3) {
        super.drawScreen(n, n2, n3);
        if (!this.items.isEmpty()) {
            final ClickGui clickGui = (ClickGui)LuigiHack.moduleManager.getModuleByClass((Class)ClickGui.class);
            LuigiHack.textManager.drawStringWithShadow(((boolean)clickGui.openCloseChange.getValue()) ? (this.subOpen ? clickGui.close.getValue() : ((String)clickGui.open.getValue())) : ((String)clickGui.moduleButton.getValue()), this.x - 1.5f + this.width - 7.4f, this.y - 4.0f - LuigiGui.getClickGui().getTextOffset(), ColorUtil.toRGBA((int)ClickGui.getInstance().ARed.getValue(), (int)ClickGui.getInstance().AGreen.getValue(), (int)ClickGui.getInstance().ABlue.getValue(), 255));
            if (ClickGui.getInstance().futuregear.getValue()) {
                ModuleButton.mc.getTextureManager().bindTexture(this.gearawa);
                drawCompleteImage(this.x - 4.0f + this.width - 7.4f, this.y - 4.8f - LuigiGui.getClickGui().getTextOffset(), 9, 9);
            }
            if (this.subOpen) {
                float n4 = 1.0f;
                for (final Item item : this.items) {
                    if (!item.isHidden()) {
                        item.setLocation(this.x + 1.0f, this.y + (n4 += 12.0f));
                        item.setHeight(11);
                        item.setWidth(this.width - 9);
                        item.drawScreen(n, n2, n3);
                    }
                    item.update();
                }
            }
        }
    }
    
    public void toggle() {
        this.module.toggle();
    }
    
    public static void drawCompleteImage(final float n, final float n2, final int n3, final int n4) {
        GL11.glPushMatrix();
        GL11.glTranslatef(n, n2, 0.0f);
        GL11.glBegin(7);
        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex3f(0.0f, 0.0f, 0.0f);
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex3f(0.0f, (float)n4, 0.0f);
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex3f((float)n3, (float)n4, 0.0f);
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex3f((float)n3, 0.0f, 0.0f);
        GL11.glEnd();
        GL11.glPopMatrix();
    }
    
    public boolean getState() {
        return this.module.isEnabled();
    }
    
    public ModuleButton(final Module module) {
        super(module.getName());
        this.gearawa = new ResourceLocation("textures/gear.png");
        this.items = new ArrayList<Item>();
        this.module = module;
        this.initSettings();
    }
    
    public int getHeight() {
        if (this.subOpen) {
            int n = 11;
            for (final Item item : this.items) {
                if (item.isHidden()) {
                    continue;
                }
                n += item.getHeight() + 1;
            }
            return n + 2;
        }
        return 11;
    }
    
    public void initSettings() {
        final ArrayList<Item> items = new ArrayList<Item>();
        if (!this.module.getSettings().isEmpty()) {
            for (final Setting setting : this.module.getSettings()) {
                if (setting.getValue() instanceof Boolean && !setting.getName().equals("Enabled")) {
                    items.add((Item)new BooleanButton(setting));
                }
                if (setting.getValue() instanceof Bind && !this.module.getName().equalsIgnoreCase("Hud")) {
                    items.add((Item)new BindButton(setting));
                }
                if (setting.getValue() instanceof String || setting.getValue() instanceof Character) {
                    items.add((Item)new StringButton(setting));
                }
                if (setting.isNumberSetting()) {
                    if (setting.hasRestriction()) {
                        items.add((Item)new Slider(setting));
                        continue;
                    }
                    items.add((Item)new UnlimitedSlider(setting));
                }
                if (!setting.isEnumSetting()) {
                    continue;
                }
                items.add((Item)new EnumButton(setting));
            }
        }
        this.items = items;
    }
}
