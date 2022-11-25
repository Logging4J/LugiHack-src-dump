//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.gui;

import me.snow.luigihack.impl.gui.components.*;
import me.snow.luigihack.impl.gui.effect.particles.*;
import net.minecraft.client.gui.*;
import me.snow.luigihack.impl.gui.effect.*;
import me.snow.luigihack.impl.*;
import java.util.function.*;
import org.lwjgl.input.*;
import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.impl.gui.components.items.*;
import java.util.*;
import me.snow.luigihack.impl.modules.client.*;
import java.awt.*;
import net.minecraft.client.*;
import java.io.*;
import me.snow.luigihack.*;
import me.snow.luigihack.impl.gui.components.items.buttons.*;

public class LuigiGui extends GuiScreen
{
    private final /* synthetic */ ArrayList<Component> components;
    public /* synthetic */ ParticleSystem particleSystem;
    private static /* synthetic */ LuigiGui INSTANCE;
    /* synthetic */ Random random;
    final /* synthetic */ ScaledResolution res;
    private /* synthetic */ ArrayList<Snow> _snowList;
    
    public final ArrayList<Component> getComponents() {
        return this.components;
    }
    
    public void checkMouseWheel() {
        final int dWheel = Mouse.getDWheel();
        if (dWheel < 0) {
            this.components.forEach(component -> component.setY(component.getY() - 10));
        }
        else if (dWheel > 0) {
            this.components.forEach(component2 -> component2.setY(component2.getY() + 10));
        }
    }
    
    public void updateScreen() {
        if (this.particleSystem != null) {
            this.particleSystem.update();
        }
    }
    
    public void updateModule(final Module module) {
        final Iterator<Component> iterator = this.components.iterator();
        while (iterator.hasNext()) {
            for (final Item item : iterator.next().getItems()) {
                if (!(item instanceof ModuleButton)) {
                    continue;
                }
                final ModuleButton moduleButton = (ModuleButton)item;
                final Module module2 = moduleButton.getModule();
                if (module == null) {
                    continue;
                }
                if (!module.equals(module2)) {
                    continue;
                }
                moduleButton.initSettings();
                break;
            }
        }
    }
    
    public void drawScreen(final int n, final int n2, final float n3) {
        if (ClickGui.getInstance().dark.getValue()) {
            this.drawDefaultBackground();
        }
        final ScaledResolution scaledResolution = new ScaledResolution(this.mc);
        this.checkMouseWheel();
        this.components.forEach(component -> component.drawScreen(n, n2, n3));
        final ScaledResolution scaledResolution2 = new ScaledResolution(this.mc);
        if (ClickGui.getInstance().gradiant.getValue()) {
            final Color color = ClickGui.getInstance().colorSync.getValue() ? Colors.INSTANCE.getCurrentColor() : new Color((int)ClickGui.getInstance().gradiantred.getValue(), (int)ClickGui.getInstance().gradiantgreen.getValue(), (int)ClickGui.getInstance().gradiantblue.getValue(), (int)ClickGui.getInstance().gradiantalpha.getValue());
            this.drawGradientRect(0, 0, scaledResolution2.getScaledWidth(), scaledResolution2.getScaledHeight(), 0, ((boolean)ClickGui.getInstance().colorSync.getValue()) ? Colors.INSTANCE.getCurrentColorHex() : new Color(color.getRed(), color.getGreen(), color.getBlue(), (int)ClickGui.getInstance().gradiantalpha.getValue() / 2).getRGB());
        }
        this.checkMouseWheel();
        this.components.forEach(component2 -> component2.drawScreen(n, n2, n3));
        if (!this._snowList.isEmpty() && (boolean)ClickGui.getInstance().snowing.getValue()) {
            this._snowList.forEach(snow -> snow.Update(scaledResolution));
        }
        if (this.particleSystem != null && (boolean)ClickGui.getInstance().particles.getValue()) {
            this.particleSystem.render(n, n2);
        }
        else {
            this.particleSystem = new ParticleSystem(new ScaledResolution(this.mc));
        }
    }
    
    public void mouseClicked(final int n, final int n2, final int n3) {
        this.components.forEach(component -> component.mouseClicked(n, n2, n3));
    }
    
    public int getTextOffset() {
        return -6;
    }
    
    public static LuigiGui getClickGui() {
        return getInstance();
    }
    
    private void setInstance() {
        LuigiGui.INSTANCE = this;
    }
    
    public Component getComponentByName(final String anotherString) {
        for (final Component component : this.components) {
            if (!component.getName().equalsIgnoreCase(anotherString)) {
                continue;
            }
            return component;
        }
        return null;
    }
    
    public LuigiGui() {
        this.res = new ScaledResolution(Minecraft.getMinecraft());
        this.components = new ArrayList<Component>();
        this.random = new Random();
        this._snowList = new ArrayList<Snow>();
        this.setInstance();
        this.load();
    }
    
    public void mouseReleased(final int n, final int n2, final int n3) {
        this.components.forEach(component -> component.mouseReleased(n, n2, n3));
    }
    
    static {
        LuigiGui.INSTANCE = new LuigiGui();
    }
    
    public void keyTyped(final char c, final int n) throws IOException {
        super.keyTyped(c, n);
        this.components.forEach(component -> component.onKeyTyped(c, n));
    }
    
    public boolean doesGuiPauseGame() {
        return false;
    }
    
    private void load() {
        for (int i = 0; i < 100; ++i) {
            for (int j = 0; j < 3; ++j) {
                this._snowList.add(new Snow(25 * i, j * -50, this.random.nextInt(3) + 1, this.random.nextInt(2) + 1));
            }
        }
        int n = -112;
        for (final Module.Category category : LuigiHack.moduleManager.getCategories()) {
            final ArrayList<Component> components = this.components;
            final String name = category.getName();
            n += 114;
            components.add(new Component(name, n, 6, true) {
                public void setupItems() {
                    LuigiHack.moduleManager.getModulesByCategory(category).forEach(module -> {
                        if (!module.hidden) {
                            this.addButton((Button)new ModuleButton(module));
                        }
                    });
                }
            });
        }
        this.components.forEach(component -> component.getItems().sort(Comparator.comparing((Function<? super E, ? extends Comparable>)Feature::getName)));
    }
    
    public static LuigiGui getInstance() {
        if (LuigiGui.INSTANCE == null) {
            LuigiGui.INSTANCE = new LuigiGui();
        }
        return LuigiGui.INSTANCE;
    }
}
