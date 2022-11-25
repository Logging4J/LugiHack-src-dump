//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.gui.components;

import me.snow.luigihack.impl.*;
import net.minecraft.util.*;
import me.snow.luigihack.impl.gui.components.items.*;
import java.awt.*;
import me.snow.luigihack.impl.modules.client.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;
import me.snow.luigihack.*;
import me.snow.luigihack.impl.gui.*;
import java.util.*;
import net.minecraft.init.*;
import net.minecraft.client.audio.*;
import me.snow.luigihack.impl.gui.components.items.buttons.*;

public class Component extends Feature
{
    private final /* synthetic */ ResourceLocation arrow;
    private /* synthetic */ int width;
    public /* synthetic */ boolean drag;
    private /* synthetic */ int x;
    private /* synthetic */ int y2;
    private final /* synthetic */ ArrayList<Item> items;
    private /* synthetic */ int x2;
    private /* synthetic */ boolean hidden;
    private /* synthetic */ int y;
    private /* synthetic */ int height;
    private /* synthetic */ boolean open;
    
    public boolean isOpen() {
        return this.open;
    }
    
    public void setY(final int y) {
        this.y = y;
    }
    
    public void drawScreen(final int n, final int n2, final float n3) {
        this.drag(n, n2);
        final float n4 = this.open ? (this.getTotalItemHeight() - 2.0f) : 0.0f;
        final int n5 = -7829368;
        if (this.open) {
            RenderUtil.drawRect((float)this.x, this.y + 14.0f, (float)(this.x + this.width), this.y + this.height + n4, 0);
            if (ClickGui.getInstance().outline2.getValue()) {
                this.drawOutline((float)ClickGui.getInstance().outlineThickness.getValue(), n5);
            }
        }
        if (ClickGui.getInstance().frameSettings.getValue()) {
            ColorUtil.toARGB((int)ClickGui.getInstance().frameRed.getValue(), (int)ClickGui.getInstance().frameGreen.getValue(), (int)ClickGui.getInstance().frameBlue.getValue(), (int)ClickGui.getInstance().frameAlpha.getValue());
            RenderUtil.drawRect((float)this.x, this.y + 11.0f, (float)(this.x + this.width), (float)(this.y + this.height - 6), ((boolean)ClickGui.getInstance().colorSync.getValue()) ? Colors.INSTANCE.getCurrentColor().getRGB() : ColorUtil.toARGB((int)ClickGui.getInstance().frameRed.getValue(), (int)ClickGui.getInstance().frameGreen.getValue(), (int)ClickGui.getInstance().frameBlue.getValue(), (int)ClickGui.getInstance().frameAlpha.getValue()));
        }
        final Color color = new Color(ClickGui.getInstance().TcolorSync.getValue() ? Colors.INSTANCE.getCurrentColorHex() : ColorUtil.toARGB((int)ClickGui.getInstance().topRed.getValue(), (int)ClickGui.getInstance().topGreen.getValue(), (int)ClickGui.getInstance().topBlue.getValue(), (int)ClickGui.getInstance().topAlpha.getValue()));
        final int rgba = ColorUtil.toRGBA(color.getRed(), color.getGreen(), color.getBlue(), (int)ClickGui.getInstance().topAlpha.getValue());
        if ((boolean)ClickGui.getInstance().rainbowRolling.getValue() && (boolean)ClickGui.getInstance().colorSync.getValue() && (boolean)Colors.INSTANCE.rainbow.getValue()) {
            RenderUtil.drawGradientRect((float)this.x, this.y - 1.5f, (float)this.width, (float)(this.height - 4), (int)HUD.getInstance().colorMap.get(MathUtil.clamp(this.y, 0, this.renderer.scaledHeight)), (int)HUD.getInstance().colorMap.get(MathUtil.clamp(this.y + this.height - 4, 0, this.renderer.scaledHeight)));
        }
        else {
            RenderUtil.drawRect((float)this.x, this.y - 1.5f, (float)(this.x + this.width), (float)(this.y + this.height - 6), rgba);
        }
        if (this.open) {
            RenderUtil.drawRect((float)this.x, this.y + 12.5f, (float)(this.x + this.width), this.y + this.height + n4, ColorUtil.toRGBA((int)ClickGui.getInstance().b_red.getValue(), (int)ClickGui.getInstance().b_green.getValue(), (int)ClickGui.getInstance().b_blue.getValue(), (int)ClickGui.getInstance().b_alpha.getValue()));
        }
        if (this.open) {
            RenderUtil.drawRect((float)this.x, this.y + 12.5f, (float)(this.x + this.width), this.y + this.height + n4, 0);
            if (ClickGui.getInstance().outline.getValue()) {
                if (ClickGui.getInstance().rainbowRolling.getValue()) {
                    GlStateManager.disableTexture2D();
                    GlStateManager.enableBlend();
                    GlStateManager.disableAlpha();
                    GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                    GlStateManager.shadeModel(7425);
                    GL11.glBegin(1);
                    final Color color2 = new Color(HUD.getInstance().colorMap.get(MathUtil.clamp(this.y, 0, this.renderer.scaledHeight)));
                    GL11.glColor4f(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, color2.getAlpha() / 255.0f);
                    GL11.glVertex3f((float)(this.x + this.width), this.y - 1.5f, 0.0f);
                    GL11.glVertex3f((float)this.x, this.y - 1.5f, 0.0f);
                    GL11.glVertex3f((float)this.x, this.y - 1.5f, 0.0f);
                    float n6 = this.getHeight() - 1.5f;
                    final Iterator<Item> iterator = this.getItems().iterator();
                    while (iterator.hasNext()) {
                        final Color color3 = new Color(HUD.getInstance().colorMap.get(MathUtil.clamp((int)(this.y + (n6 += iterator.next().getHeight() + 1.5f)), 0, this.renderer.scaledHeight)));
                        GL11.glColor4f(color3.getRed() / 255.0f, color3.getGreen() / 255.0f, color3.getBlue() / 255.0f, color3.getAlpha() / 255.0f);
                        GL11.glVertex3f((float)this.x, this.y + n6, 0.0f);
                        GL11.glVertex3f((float)this.x, this.y + n6, 0.0f);
                    }
                    final Color color4 = new Color(HUD.getInstance().colorMap.get(MathUtil.clamp((int)(this.y + this.height + n4), 0, this.renderer.scaledHeight)));
                    GL11.glColor4f(color4.getRed() / 255.0f, color4.getGreen() / 255.0f, color4.getBlue() / 255.0f, color4.getAlpha() / 255.0f);
                    GL11.glVertex3f((float)(this.x + this.width), this.y + this.height + n4, 0.0f);
                    GL11.glVertex3f((float)(this.x + this.width), this.y + this.height + n4, 0.0f);
                    final Iterator<Item> iterator2 = this.getItems().iterator();
                    while (iterator2.hasNext()) {
                        final Color color5 = new Color(HUD.getInstance().colorMap.get(MathUtil.clamp((int)(this.y + (n6 -= iterator2.next().getHeight() + 1.5f)), 0, this.renderer.scaledHeight)));
                        GL11.glColor4f(color5.getRed() / 255.0f, color5.getGreen() / 255.0f, color5.getBlue() / 255.0f, color5.getAlpha() / 255.0f);
                        GL11.glVertex3f((float)(this.x + this.width), this.y + n6, 0.0f);
                        GL11.glVertex3f((float)(this.x + this.width), this.y + n6, 0.0f);
                    }
                    GL11.glVertex3f((float)(this.x + this.width), (float)this.y, 0.0f);
                    GL11.glEnd();
                    GlStateManager.shadeModel(7424);
                    GlStateManager.disableBlend();
                    GlStateManager.enableAlpha();
                    GlStateManager.enableTexture2D();
                }
                else {
                    GlStateManager.disableTexture2D();
                    GlStateManager.enableBlend();
                    GlStateManager.disableAlpha();
                    GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                    GlStateManager.shadeModel(7425);
                    GL11.glBegin(2);
                    final Color color6 = ClickGui.getInstance().colorSync.getValue() ? new Color(Colors.INSTANCE.getCurrentColorHex()) : new Color(LuigiHack.colorManager.getColorAsIntFullAlpha());
                    GL11.glColor4f((float)color6.getRed(), (float)color6.getGreen(), (float)color6.getBlue(), (float)color6.getAlpha());
                    GL11.glVertex3f((float)this.x, this.y - 1.5f, 0.0f);
                    GL11.glVertex3f((float)(this.x + this.width), this.y - 1.5f, 0.0f);
                    GL11.glVertex3f((float)(this.x + this.width), this.y + this.height + n4, 0.0f);
                    GL11.glVertex3f((float)this.x, this.y + this.height + n4, 0.0f);
                    GL11.glEnd();
                    GlStateManager.shadeModel(7424);
                    GlStateManager.disableBlend();
                    GlStateManager.enableAlpha();
                    GlStateManager.enableTexture2D();
                }
            }
        }
        if (ClickGui.getInstance().centerAAA.getValue()) {
            LuigiHack.textManager.drawStringWithShadow(this.getName(), this.x + (float)(this.width / 2) - this.renderer.getStringWidth(this.getName()) / 2, this.y - 4.0f - LuigiGui.getClickGui().getTextOffset(), -1);
        }
        else {
            LuigiHack.textManager.drawStringWithShadow(this.getName(), this.x + 3.0f, this.y - 4.0f - LuigiGui.getClickGui().getTextOffset(), -1);
        }
        if (this.open) {
            if (ClickGui.getInstance().toparrow.getValue()) {
                Component.mc.getTextureManager().bindTexture(this.arrow);
                ModuleButton.drawCompleteImage(this.x - 1.5f + this.width - 12.0f, this.y - 5.0f - LuigiGui.getClickGui().getTextOffset(), 12, 11);
            }
            float n7 = this.getY() + this.getHeight() - 3.0f;
            for (final Item item : this.getItems()) {
                if (item.isHidden()) {
                    continue;
                }
                item.setLocation(this.x + 2.0f, n7);
                item.setWidth(this.getWidth() - 4);
                item.drawScreen(n, n2, n3);
                n7 += item.getHeight() + 2.5f;
            }
        }
    }
    
    public void onKeyTyped(final char c, final int n) {
        if (!this.open) {
            return;
        }
        this.getItems().forEach(item -> item.onKeyTyped(c, n));
    }
    
    public void mouseClicked(final int n, final int n2, final int n3) {
        if (n3 == 0 && this.isHovering(n, n2)) {
            this.x2 = this.x - n;
            this.y2 = this.y - n2;
            LuigiGui.getClickGui().getComponents().forEach(component -> {
                if (component.drag) {
                    component.drag = false;
                }
                return;
            });
            this.drag = true;
            return;
        }
        if (n3 == 1 && this.isHovering(n, n2)) {
            this.open = !this.open;
            Component.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
            return;
        }
        if (!this.open) {
            return;
        }
        this.getItems().forEach(item -> item.mouseClicked(n, n2, n3));
    }
    
    private void drag(final int n, final int n2) {
        if (!this.drag) {
            return;
        }
        this.x = this.x2 + n;
        this.y = this.y2 + n2;
    }
    
    private boolean isHovering(final int n, final int n2) {
        return n >= this.getX() && n <= this.getX() + this.getWidth() && n2 >= this.getY() && n2 <= this.getY() + this.getHeight() - (this.open ? 2 : 0);
    }
    
    public void setWidth(final int width) {
        this.width = width;
    }
    
    public boolean isHidden() {
        return this.hidden;
    }
    
    public void setX(final int x) {
        this.x = x;
    }
    
    private float getTotalItemHeight() {
        float n = 0.0f;
        final Iterator<Item> iterator = this.getItems().iterator();
        while (iterator.hasNext()) {
            n += iterator.next().getHeight() + 2.5f;
        }
        return n;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void addButton(final Button e) {
        this.items.add(e);
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public void mouseReleased(final int n, final int n2, final int n3) {
        if (n3 == 0) {
            this.drag = false;
        }
        if (!this.open) {
            return;
        }
        this.getItems().forEach(item -> item.mouseReleased(n, n2, n3));
    }
    
    public int getX() {
        return this.x;
    }
    
    public final ArrayList<Item> getItems() {
        return this.items;
    }
    
    public void setHidden(final boolean hidden) {
        this.hidden = hidden;
    }
    
    private void drawOutline(final float n, final int n2) {
        float n3 = 0.0f;
        if (this.open) {
            n3 = this.getTotalItemHeight() - 2.0f;
        }
        RenderUtil.drawLine((float)this.x, this.y - 1.5f, (float)this.x, this.y + this.height + n3, n, ((boolean)ClickGui.getInstance().colorSync.getValue()) ? Colors.INSTANCE.getCurrentColorHex() : ColorUtil.toRGBA((int)ClickGui.getInstance().o_red.getValue(), (int)ClickGui.getInstance().o_green.getValue(), (int)ClickGui.getInstance().o_blue.getValue(), (int)ClickGui.getInstance().o_alpha.getValue()));
        RenderUtil.drawLine((float)this.x, this.y - 1.5f, (float)(this.x + this.width), this.y - 1.5f, n, ((boolean)ClickGui.getInstance().colorSync.getValue()) ? Colors.INSTANCE.getCurrentColorHex() : ColorUtil.toRGBA((int)ClickGui.getInstance().o_red.getValue(), (int)ClickGui.getInstance().o_green.getValue(), (int)ClickGui.getInstance().o_blue.getValue(), (int)ClickGui.getInstance().o_alpha.getValue()));
        RenderUtil.drawLine((float)(this.x + this.width), this.y - 1.5f, (float)(this.x + this.width), this.y + this.height + n3, n, ((boolean)ClickGui.getInstance().colorSync.getValue()) ? Colors.INSTANCE.getCurrentColorHex() : ColorUtil.toRGBA((int)ClickGui.getInstance().o_red.getValue(), (int)ClickGui.getInstance().o_green.getValue(), (int)ClickGui.getInstance().o_blue.getValue(), (int)ClickGui.getInstance().o_alpha.getValue()));
        RenderUtil.drawLine((float)this.x, this.y + this.height + n3, (float)(this.x + this.width), this.y + this.height + n3, n, ((boolean)ClickGui.getInstance().colorSync.getValue()) ? Colors.INSTANCE.getCurrentColorHex() : ColorUtil.toRGBA((int)ClickGui.getInstance().o_red.getValue(), (int)ClickGui.getInstance().o_green.getValue(), (int)ClickGui.getInstance().o_blue.getValue(), (int)ClickGui.getInstance().o_alpha.getValue()));
    }
    
    public void setHeight(final int height) {
        this.height = height;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public Component(final String s, final int x, final int y, final boolean open) {
        super(s);
        this.items = new ArrayList<Item>();
        this.arrow = new ResourceLocation("textures/arrow.png");
        this.x = x;
        this.y = y;
        this.width = 110;
        this.height = 18;
        this.open = open;
        this.setupItems();
    }
    
    public void setupItems() {
    }
}
