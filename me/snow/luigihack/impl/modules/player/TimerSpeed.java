//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.player;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;

public class TimerSpeed extends Module
{
    public /* synthetic */ Setting<Float> timer;
    
    public void onDisable() {
        TimerSpeed.mc.timer.tickLength = 50.0f;
    }
    
    public TimerSpeed() {
        super("Timer", "Will speed up the game.", Module.Category.PLAYER, false, false, false);
        this.timer = (Setting<Float>)this.register(new Setting("Speed", (Object)1.0f, (Object)0.1f, (Object)10.0f));
    }
    
    public void onTick() {
        TimerSpeed.mc.timer.tickLength = 50.0f / (float)this.timer.getValue();
    }
    
    public void onEnable() {
        TimerSpeed.mc.timer.tickLength = 50.0f;
    }
}
