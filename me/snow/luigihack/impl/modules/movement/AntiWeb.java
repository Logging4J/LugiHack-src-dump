//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.movement;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.client.entity.*;

public class AntiWeb extends Module
{
    private final /* synthetic */ Setting<Float> horizontalSpeed;
    public /* synthetic */ Setting<Boolean> onlySnaking;
    private final /* synthetic */ Setting<Float> verticalSpeed;
    public /* synthetic */ boolean isTimering;
    private final /* synthetic */ Setting<Mode> mode;
    private final /* synthetic */ Setting<Float> timerAmount;
    
    public String getDisplayInfo() {
        return String.valueOf(new StringBuilder().append(this.mode.getValue()).append(""));
    }
    
    public void onTick() {
        if (!AntiWeb.mc.player.isInWeb) {
            if (this.isTimering) {
                AntiWeb.mc.timer.tickLength = 50.0f;
                this.isTimering = false;
            }
            return;
        }
        switch ((Mode)this.mode.getValue()) {
            case Vanilla: {
                AntiWeb.mc.player.isInWeb = false;
                break;
            }
            case Motion: {
                final EntityPlayerSP player = AntiWeb.mc.player;
                player.motionX *= 1.0f + (float)this.horizontalSpeed.getValue();
                AntiWeb.mc.player.motionY = -(float)this.verticalSpeed.getValue();
                final EntityPlayerSP player2 = AntiWeb.mc.player;
                player2.motionZ *= 1.0f + (float)this.horizontalSpeed.getValue();
                break;
            }
            case Timer: {
                if ((boolean)this.onlySnaking.getValue() && AntiWeb.mc.player.isSneaking()) {
                    AntiWeb.mc.timer.tickLength = 50.0f / (float)this.timerAmount.getValue();
                    this.isTimering = true;
                }
                if (!(boolean)this.onlySnaking.getValue()) {
                    AntiWeb.mc.timer.tickLength = 50.0f / (float)this.timerAmount.getValue();
                    this.isTimering = true;
                    break;
                }
                break;
            }
        }
    }
    
    public AntiWeb() {
        super("AntiWeb", "", Module.Category.MOVEMENT, true, false, false);
        this.mode = (Setting<Mode>)this.register(new Setting("Mode", (Object)Mode.Vanilla));
        this.horizontalSpeed = (Setting<Float>)this.register(new Setting("Horizontal Speed", (Object)0.1f, (Object)0.1f, (Object)2.0f, p0 -> this.mode.getValue() == Mode.Motion));
        this.verticalSpeed = (Setting<Float>)this.register(new Setting("Vertical Speed", (Object)0.1f, (Object)0.1f, (Object)2.0f, p0 -> this.mode.getValue() == Mode.Motion));
        this.timerAmount = (Setting<Float>)this.register(new Setting("Timer", (Object)0.1f, (Object)0.1f, (Object)10.0f, p0 -> this.mode.getValue() == Mode.Timer));
        this.onlySnaking = (Setting<Boolean>)this.register(new Setting("OnlySnaking", (Object)false));
    }
    
    public enum Mode
    {
        Vanilla, 
        Timer, 
        Motion;
    }
}
