//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.movement;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.entity.*;
import com.mojang.realmsclient.gui.*;

public class TickShift extends Module
{
    public final /* synthetic */ Setting<Boolean> movementEnable;
    public final /* synthetic */ Setting<Integer> enableTicks;
    private /* synthetic */ int ticksStill;
    public final /* synthetic */ Setting<Double> multiplayer;
    private /* synthetic */ int ticksPassed;
    public final /* synthetic */ Setting<Boolean> Disable;
    public final /* synthetic */ Setting<Integer> DisableTicks;
    private /* synthetic */ boolean timerOn;
    
    public void onDisable() {
        this.timerOn = false;
        this.ticksStill = 0;
        TickShift.mc.timer.tickLength = 50.0f;
    }
    
    public void onTick() {
        if (!this.timerOn) {
            if (isMoving((EntityLivingBase)TickShift.mc.player)) {
                if (this.ticksStill >= 1) {
                    --this.ticksStill;
                }
            }
            else if (!isMoving((EntityLivingBase)TickShift.mc.player)) {
                ++this.ticksStill;
            }
        }
        if (this.ticksStill >= (int)this.enableTicks.getValue()) {
            this.timerOn = true;
            if (this.movementEnable.getValue()) {
                if (TickShift.mc.gameSettings.keyBindJump.isKeyDown() || TickShift.mc.gameSettings.keyBindSneak.isKeyDown() || TickShift.mc.gameSettings.keyBindRight.isKeyDown() || TickShift.mc.gameSettings.keyBindLeft.isKeyDown() || TickShift.mc.gameSettings.keyBindForward.isKeyDown() || TickShift.mc.gameSettings.keyBindBack.isKeyDown()) {
                    TickShift.mc.timer.tickLength = (float)(50.0 / (double)this.multiplayer.getValue());
                    ++this.ticksPassed;
                }
            }
            else {
                TickShift.mc.timer.tickLength = (float)(50.0 / (double)this.multiplayer.getValue());
                ++this.ticksPassed;
            }
        }
        if (this.ticksPassed >= (int)this.DisableTicks.getValue()) {
            this.ticksPassed = 0;
            if (this.Disable.getValue()) {
                this.disable();
            }
            else {
                this.reset();
            }
        }
    }
    
    public String getDisplayInfo() {
        return String.valueOf(new StringBuilder().append(ChatFormatting.WHITE).append(String.valueOf(this.ticksStill)));
    }
    
    public void reset() {
        this.timerOn = false;
        this.ticksStill = 0;
        TickShift.mc.timer.tickLength = 50.0f;
    }
    
    public TickShift() {
        super("TickShift", "placeholder", Module.Category.MOVEMENT, true, false, false);
        this.Disable = (Setting<Boolean>)this.register(new Setting("Disable", (Object)false));
        this.movementEnable = (Setting<Boolean>)this.register(new Setting("MovementEnable", (Object)true));
        this.DisableTicks = (Setting<Integer>)this.register(new Setting("Anti DisableTicks Height", (Object)20, (Object)1, (Object)100));
        this.enableTicks = (Setting<Integer>)this.register(new Setting("EnableTicks", (Object)30, (Object)1, (Object)100));
        this.multiplayer = (Setting<Double>)this.register(new Setting("Multiplayer", (Object)3.0, (Object)1.0, (Object)10.0));
        this.ticksPassed = 0;
        this.ticksStill = 0;
        this.timerOn = false;
    }
    
    public static boolean isMoving(final EntityLivingBase entityLivingBase) {
        return entityLivingBase.moveForward != 0.0f || entityLivingBase.moveStrafing != 0.0f;
    }
}
