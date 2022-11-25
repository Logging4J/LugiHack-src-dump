//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.render;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraft.network.play.server.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.init.*;
import net.minecraft.potion.*;

public class Fullbright extends Module
{
    public /* synthetic */ Setting<Boolean> effects;
    public /* synthetic */ Setting<Mode> mode;
    private /* synthetic */ float previousSetting;
    
    @SubscribeEvent
    public void onPacketReceive(final PacketEvent.Receive receive) {
        if (receive.getStage() == 0 && receive.getPacket() instanceof SPacketEntityEffect && (boolean)this.effects.getValue()) {
            final SPacketEntityEffect sPacketEntityEffect = (SPacketEntityEffect)receive.getPacket();
            if (Fullbright.mc.player != null && sPacketEntityEffect.getEntityId() == Fullbright.mc.player.getEntityId() && (sPacketEntityEffect.getEffectId() == 9 || sPacketEntityEffect.getEffectId() == 15)) {
                receive.setCanceled(true);
            }
        }
    }
    
    public void onUpdate() {
        if (this.mode.getValue() == Mode.GAMMA) {
            Fullbright.mc.gameSettings.gammaSetting = 1000.0f;
        }
        if (this.mode.getValue() == Mode.POTION) {
            Fullbright.mc.player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 5210));
        }
    }
    
    public void onDisable() {
        if (this.mode.getValue() == Mode.POTION) {
            Fullbright.mc.player.removePotionEffect(MobEffects.NIGHT_VISION);
        }
        Fullbright.mc.gameSettings.gammaSetting = this.previousSetting;
    }
    
    public String getDisplayInfo() {
        switch ((Mode)this.mode.getValue()) {
            case GAMMA: {
                return "Gamma";
            }
            case POTION: {
                return "Potion";
            }
            default: {
                return null;
            }
        }
    }
    
    public Fullbright() {
        super("Fullbright", "Makes your game brighter.", Module.Category.RENDER, true, false, false);
        this.mode = (Setting<Mode>)this.register(new Setting("Mode", (Object)Mode.GAMMA));
        this.effects = (Setting<Boolean>)this.register(new Setting("Effects", (Object)false));
        this.previousSetting = 1.0f;
    }
    
    public void onEnable() {
        this.previousSetting = Fullbright.mc.gameSettings.gammaSetting;
    }
    
    public enum Mode
    {
        GAMMA, 
        POTION;
    }
}
