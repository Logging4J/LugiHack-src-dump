//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.client;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import java.awt.*;
import me.snow.luigihack.*;
import me.snow.luigihack.api.util.*;
import java.util.*;

public class Colors extends Module
{
    public /* synthetic */ Map<Integer, Integer> colorHeightMap;
    public /* synthetic */ Setting<Integer> rainbowBrightness;
    public /* synthetic */ Setting<Integer> rainbowSaturation;
    public /* synthetic */ Setting<Integer> blue;
    public /* synthetic */ Setting<Integer> rainbowSpeed;
    public /* synthetic */ Setting<Integer> green;
    public /* synthetic */ float hue;
    public static /* synthetic */ Colors INSTANCE;
    public /* synthetic */ Setting<Integer> alpha;
    public /* synthetic */ Setting<Integer> red;
    public /* synthetic */ Setting<Boolean> rainbow;
    
    private void setInstance() {
        Colors.INSTANCE = this;
    }
    
    @Override
    public void onTick() {
        final int n = 101 - (int)this.rainbowSpeed.getValue();
        final float hue = System.currentTimeMillis() % (360L * n) / (360.0f * n);
        this.hue = hue;
        float hue2 = hue;
        for (int i = 0; i <= 510; ++i) {
            this.colorHeightMap.put(i, Color.HSBtoRGB(hue2, (int)this.rainbowSaturation.getValue() / 255.0f, (int)this.rainbowBrightness.getValue() / 255.0f));
            hue2 += 0.0013071896f;
        }
        if (ClickGui.getInstance().colorSync.getValue()) {
            LuigiHack.colorManager.setColor(Colors.INSTANCE.getCurrentColor().getRed(), Colors.INSTANCE.getCurrentColor().getGreen(), Colors.INSTANCE.getCurrentColor().getBlue(), (int)ClickGui.getInstance().hoverAlpha.getValue());
        }
    }
    
    public int getCurrentColorHex() {
        if (this.rainbow.getValue()) {
            return Color.HSBtoRGB(this.hue, (int)this.rainbowSaturation.getValue() / 255.0f, (int)this.rainbowBrightness.getValue() / 255.0f);
        }
        return ColorUtil.toARGB((int)this.red.getValue(), (int)this.green.getValue(), (int)this.blue.getValue(), (int)this.alpha.getValue());
    }
    
    public Colors() {
        super("Colors", "Universal colors.", Category.CLIENT, true, false, true);
        this.rainbow = (Setting<Boolean>)this.register(new Setting("Rainbow", (Object)Boolean.FALSE, "Rainbow colors."));
        this.rainbowSpeed = (Setting<Integer>)this.register(new Setting("Speed", (Object)20, (Object)0, (Object)100, p0 -> (boolean)this.rainbow.getValue()));
        this.rainbowSaturation = (Setting<Integer>)this.register(new Setting("Saturation", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.rainbow.getValue()));
        this.rainbowBrightness = (Setting<Integer>)this.register(new Setting("Brightness", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.rainbow.getValue()));
        this.red = (Setting<Integer>)this.register(new Setting("Red", (Object)36, (Object)0, (Object)255, p0 -> !(boolean)this.rainbow.getValue()));
        this.green = (Setting<Integer>)this.register(new Setting("Green", (Object)150, (Object)0, (Object)255, p0 -> !(boolean)this.rainbow.getValue()));
        this.blue = (Setting<Integer>)this.register(new Setting("Blue", (Object)190, (Object)0, (Object)255, p0 -> !(boolean)this.rainbow.getValue()));
        this.alpha = (Setting<Integer>)this.register(new Setting("Alpha", (Object)255, (Object)0, (Object)255, p0 -> !(boolean)this.rainbow.getValue()));
        this.colorHeightMap = new HashMap<Integer, Integer>();
        Colors.INSTANCE = this;
    }
    
    public static Colors getInstance() {
        if (Colors.INSTANCE == null) {
            Colors.INSTANCE = new Colors();
        }
        return Colors.INSTANCE;
    }
    
    public Color getCurrentColor() {
        if (this.rainbow.getValue()) {
            return Color.getHSBColor(this.hue, (int)this.rainbowSaturation.getValue() / 255.0f, (int)this.rainbowBrightness.getValue() / 255.0f);
        }
        return new Color((int)this.red.getValue(), (int)this.green.getValue(), (int)this.blue.getValue(), (int)this.alpha.getValue());
    }
}
