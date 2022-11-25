//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.render;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.common.*;
import java.awt.*;

public class SkyColor extends Module
{
    private /* synthetic */ Setting<Boolean> fog;
    private /* synthetic */ Setting<Boolean> rainbow;
    private static /* synthetic */ SkyColor INSTANCE;
    private /* synthetic */ Setting<Integer> green;
    private /* synthetic */ Setting<Integer> red;
    private /* synthetic */ Setting<Integer> blue;
    
    @SubscribeEvent
    public void fog_density(final EntityViewRenderEvent.FogDensity fogDensity) {
        if (this.fog.getValue()) {
            fogDensity.setDensity(0.0f);
            fogDensity.setCanceled(true);
        }
    }
    
    @SubscribeEvent
    public void fogColors(final EntityViewRenderEvent.FogColors fogColors) {
        fogColors.setRed((int)this.red.getValue() / 255.0f);
        fogColors.setGreen((int)this.green.getValue() / 255.0f);
        fogColors.setBlue((int)this.blue.getValue() / 255.0f);
    }
    
    static {
        SkyColor.INSTANCE = new SkyColor();
    }
    
    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
    
    public static SkyColor getInstance() {
        if (SkyColor.INSTANCE == null) {
            SkyColor.INSTANCE = new SkyColor();
        }
        return SkyColor.INSTANCE;
    }
    
    public SkyColor() {
        super("SkyColor", "Changes the color of the sky", Module.Category.RENDER, false, false, false);
        this.red = (Setting<Integer>)this.register(new Setting("Red", (Object)135, (Object)0, (Object)255));
        this.green = (Setting<Integer>)this.register(new Setting("Green", (Object)0, (Object)0, (Object)255));
        this.blue = (Setting<Integer>)this.register(new Setting("Blue", (Object)255, (Object)0, (Object)255));
        this.rainbow = (Setting<Boolean>)this.register(new Setting("Rainbow", (Object)false));
        this.fog = (Setting<Boolean>)this.register(new Setting("Fog", (Object)true));
    }
    
    private void setInstance() {
        SkyColor.INSTANCE = this;
    }
    
    public void onEnable() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    public void onUpdate() {
        if (this.rainbow.getValue()) {
            this.doRainbow();
        }
    }
    
    public void doRainbow() {
        final int hsBtoRGB = Color.HSBtoRGB((new float[] { System.currentTimeMillis() % 11520L / 11520.0f })[0], 0.8f, 0.8f);
        this.red.setValue((Object)(hsBtoRGB >> 16 & 0xFF));
        this.green.setValue((Object)(hsBtoRGB >> 8 & 0xFF));
        this.blue.setValue((Object)(hsBtoRGB & 0xFF));
    }
}
