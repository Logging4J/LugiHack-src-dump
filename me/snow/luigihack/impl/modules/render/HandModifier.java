//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.render;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.*;

public class HandModifier extends Module
{
    public /* synthetic */ Setting<Double> offX;
    public /* synthetic */ Setting<Boolean> swordChange;
    public /* synthetic */ Setting<Double> offY;
    public /* synthetic */ Setting<Boolean> slowSwing;
    public /* synthetic */ Setting<Double> mainX;
    public /* synthetic */ Setting<Integer> alpha;
    public /* synthetic */ Setting<Settings> settings;
    public /* synthetic */ Setting<Boolean> csync;
    public /* synthetic */ Setting<Boolean> chams;
    public /* synthetic */ Setting<Double> mainY;
    public /* synthetic */ Setting<Boolean> anim;
    public /* synthetic */ Setting<Boolean> changeSwing;
    public /* synthetic */ Setting<RenderMode> mode;
    private static /* synthetic */ HandModifier INSTANCE;
    public /* synthetic */ Setting<Integer> green;
    public /* synthetic */ Setting<Swing> swing;
    public /* synthetic */ Setting<Integer> blue;
    public /* synthetic */ Setting<Integer> red;
    
    public static HandModifier getINSTANCE() {
        if (HandModifier.INSTANCE == null) {
            HandModifier.INSTANCE = new HandModifier();
        }
        return HandModifier.INSTANCE;
    }
    
    private void setInstance() {
        HandModifier.INSTANCE = this;
    }
    
    public HandModifier() {
        super("HandModifier", "hand modifier", Module.Category.RENDER, true, false, false);
        this.settings = (Setting<Settings>)this.register(new Setting("Settings", (Object)Settings.GLOBAL));
        this.chams = (Setting<Boolean>)this.register(new Setting("HandChams", (Object)Boolean.FALSE, p0 -> this.settings.getValue() == Settings.GLOBAL));
        this.mode = (Setting<RenderMode>)this.register(new Setting("Mode", (Object)RenderMode.WIREFRAME, p0 -> this.settings.getValue() == Settings.GLOBAL && (boolean)this.chams.getValue()));
        this.changeSwing = (Setting<Boolean>)this.register(new Setting("Swing", (Object)Boolean.FALSE, p0 -> this.settings.getValue() == Settings.GLOBAL));
        this.swing = (Setting<Swing>)this.register(new Setting("SwingHand", (Object)Swing.Mainhand, p0 -> this.settings.getValue() == Settings.GLOBAL && (boolean)this.changeSwing.getValue()));
        this.csync = (Setting<Boolean>)this.register(new Setting("ColorSync", (Object)Boolean.FALSE, p0 -> (boolean)this.chams.getValue() && this.settings.getValue() == Settings.COLORS));
        this.red = (Setting<Integer>)this.register(new Setting("Red", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.chams.getValue() && this.settings.getValue() == Settings.COLORS));
        this.green = (Setting<Integer>)this.register(new Setting("Green", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.chams.getValue() && this.settings.getValue() == Settings.COLORS));
        this.blue = (Setting<Integer>)this.register(new Setting("Blue", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.chams.getValue() && this.settings.getValue() == Settings.COLORS));
        this.alpha = (Setting<Integer>)this.register(new Setting("Alpha", (Object)150, (Object)0, (Object)255, p0 -> (boolean)this.chams.getValue() && this.settings.getValue() == Settings.COLORS));
        this.anim = (Setting<Boolean>)this.register(new Setting("NoSwapDelay", (Object)Boolean.FALSE, p0 -> this.settings.getValue() == Settings.GLOBAL));
        this.swordChange = (Setting<Boolean>)this.register(new Setting("SwordHandSwap", (Object)Boolean.FALSE, p0 -> this.settings.getValue() == Settings.GLOBAL));
        this.slowSwing = (Setting<Boolean>)this.register(new Setting("SlowSwing", (Object)Boolean.FALSE, p0 -> this.settings.getValue() == Settings.GLOBAL));
        this.mainX = (Setting<Double>)this.register(new Setting("MainX", (Object)0.0, (Object)(-1.0), (Object)1.0, p0 -> this.settings.getValue() == Settings.TRANSLATE));
        this.mainY = (Setting<Double>)this.register(new Setting("MainY", (Object)0.0, (Object)(-1.0), (Object)1.0, p0 -> this.settings.getValue() == Settings.TRANSLATE));
        this.offX = (Setting<Double>)this.register(new Setting("OffX", (Object)0.0, (Object)(-1.0), (Object)1.0, p0 -> this.settings.getValue() == Settings.TRANSLATE));
        this.offY = (Setting<Double>)this.register(new Setting("OffY", (Object)0.0, (Object)(-1.0), (Object)1.0, p0 -> this.settings.getValue() == Settings.TRANSLATE));
        this.setInstance();
    }
    
    static {
        HandModifier.INSTANCE = new HandModifier();
    }
    
    public void onUpdate() {
        if ((boolean)this.anim.getValue() && HandModifier.mc.entityRenderer.itemRenderer.prevEquippedProgressMainHand >= 0.9) {
            HandModifier.mc.entityRenderer.itemRenderer.equippedProgressMainHand = 1.0f;
            HandModifier.mc.entityRenderer.itemRenderer.itemStackMainHand = HandModifier.mc.player.getHeldItemMainhand();
        }
        if (this.changeSwing.getValue()) {
            if (this.swing.getValue() == Swing.Offhand) {
                HandModifier.mc.player.swingingHand = EnumHand.OFF_HAND;
            }
            else if (this.swing.getValue() == Swing.Mainhand) {
                HandModifier.mc.player.swingingHand = EnumHand.MAIN_HAND;
            }
        }
        if (this.swordChange.getValue()) {
            if (EntityUtil.holdingWeapon((EntityPlayer)HandModifier.mc.player)) {
                HandModifier.mc.player.setPrimaryHand(EnumHandSide.LEFT);
            }
            else {
                HandModifier.mc.player.setPrimaryHand(EnumHandSide.RIGHT);
            }
        }
    }
    
    public enum RenderMode
    {
        WIREFRAME, 
        SOLID;
    }
    
    private enum Settings
    {
        COLORS, 
        TRANSLATE, 
        GLOBAL;
    }
    
    private enum Swing
    {
        Offhand, 
        Mainhand;
    }
}
