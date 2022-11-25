//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.render;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;

public class HandChams extends Module
{
    public /* synthetic */ Setting<Boolean> rainbow;
    public /* synthetic */ Setting<Integer> green;
    public /* synthetic */ Setting<Integer> alpha;
    public /* synthetic */ Setting<Integer> saturation;
    public /* synthetic */ Setting<Integer> speed;
    public /* synthetic */ Setting<Boolean> colorSync;
    public /* synthetic */ Setting<Integer> red;
    public /* synthetic */ Setting<Integer> brightness;
    public /* synthetic */ Setting<Integer> blue;
    public static /* synthetic */ HandChams INSTANCE;
    
    public HandChams() {
        super("HandChams", "Changes the color of your hands.", Module.Category.RENDER, false, false, false);
        this.colorSync = (Setting<Boolean>)this.register(new Setting("Sync", (Object)false));
        this.rainbow = (Setting<Boolean>)this.register(new Setting("Rainbow", (Object)false));
        this.saturation = (Setting<Integer>)this.register(new Setting("Saturation", (Object)50, (Object)0, (Object)100, p0 -> (boolean)this.rainbow.getValue()));
        this.brightness = (Setting<Integer>)this.register(new Setting("Brightness", (Object)100, (Object)0, (Object)100, p0 -> (boolean)this.rainbow.getValue()));
        this.speed = (Setting<Integer>)this.register(new Setting("Speed", (Object)40, (Object)1, (Object)100, p0 -> (boolean)this.rainbow.getValue()));
        this.red = (Setting<Integer>)this.register(new Setting("Red", (Object)0, (Object)0, (Object)255, p0 -> !(boolean)this.rainbow.getValue()));
        this.green = (Setting<Integer>)this.register(new Setting("Green", (Object)255, (Object)0, (Object)255, p0 -> !(boolean)this.rainbow.getValue()));
        this.blue = (Setting<Integer>)this.register(new Setting("Blue", (Object)0, (Object)0, (Object)255, p0 -> !(boolean)this.rainbow.getValue()));
        this.alpha = (Setting<Integer>)this.register(new Setting("Alpha", (Object)255, (Object)0, (Object)255));
        HandChams.INSTANCE = this;
    }
}
