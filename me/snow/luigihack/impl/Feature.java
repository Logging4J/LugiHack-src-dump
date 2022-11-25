//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl;

import me.snow.luigihack.api.util.*;
import me.snow.luigihack.api.manager.*;
import me.snow.luigihack.api.setting.*;
import java.util.*;
import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.impl.gui.*;
import me.snow.luigihack.*;

public class Feature implements Util
{
    public /* synthetic */ TextManager renderer;
    private /* synthetic */ String name;
    public /* synthetic */ List<Setting> settings;
    
    public boolean hasSettings() {
        return !this.settings.isEmpty();
    }
    
    public Setting getSettingByName(final String anotherString) {
        for (final Setting setting : this.settings) {
            if (!setting.getName().equalsIgnoreCase(anotherString)) {
                continue;
            }
            return setting;
        }
        return null;
    }
    
    public String getName() {
        return this.name;
    }
    
    public boolean isDisabled() {
        return !this.isEnabled();
    }
    
    public List<Setting> getSettings() {
        return this.settings;
    }
    
    public void clearSettings() {
        this.settings = new ArrayList<Setting>();
    }
    
    public static boolean nullCheck() {
        return Feature.mc.player == null;
    }
    
    public void unregister(final Setting obj) {
        final ArrayList<Setting> list = new ArrayList<Setting>();
        for (final Setting e : this.settings) {
            if (!e.equals(obj)) {
                continue;
            }
            list.add(e);
        }
        if (!list.isEmpty()) {
            this.settings.removeAll(list);
        }
        if (this instanceof Module && Feature.mc.currentScreen instanceof LuigiGui) {
            LuigiGui.getInstance().updateModule((Module)this);
        }
    }
    
    public Setting register(final Setting setting) {
        setting.setFeature(this);
        this.settings.add(setting);
        if (this instanceof Module && Feature.mc.currentScreen instanceof LuigiGui) {
            LuigiGui.getInstance().updateModule((Module)this);
        }
        return setting;
    }
    
    public void reset() {
        for (final Setting setting : this.settings) {
            setting.setValue(setting.getDefaultValue());
        }
    }
    
    public boolean megaNullCheck() {
        return Feature.mc.player == null || Feature.mc.world == null || this.isDisabled();
    }
    
    public Feature() {
        this.settings = new ArrayList<Setting>();
        this.renderer = LuigiHack.textManager;
    }
    
    public static boolean fullNullCheck() {
        return Feature.mc.player == null || Feature.mc.world == null;
    }
    
    public Feature(final String name) {
        this.settings = new ArrayList<Setting>();
        this.renderer = LuigiHack.textManager;
        this.name = name;
    }
    
    public boolean isEnabled() {
        return this instanceof Module && ((Module)this).isOn();
    }
}
