//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.misc;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.api.util.*;
import me.snow.luigihack.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import org.apache.commons.lang3.*;

public class SpammerModule extends Module
{
    public /* synthetic */ Setting<Integer> delay;
    public /* synthetic */ Setting<Integer> delayDS;
    public /* synthetic */ Setting<Integer> delayMS;
    private final /* synthetic */ Setting<MessageMode> mode;
    public /* synthetic */ Setting<DelayType> delayType;
    private final /* synthetic */ Setting<String> message22;
    private final /* synthetic */ Setting<String> custom1;
    private final /* synthetic */ Timer timer;
    private final /* synthetic */ Setting<Integer> random;
    
    @Override
    public void onDisable() {
        this.timer.reset();
    }
    
    @Override
    public void onLogin() {
        if (((SpammerModule)LuigiHack.moduleManager.getModuleByClass((Class)SpammerModule.class)).isEnabled()) {
            ((SpammerModule)LuigiHack.moduleManager.getModuleByClass((Class)SpammerModule.class)).disable();
        }
    }
    
    @Override
    public void onTick() {
        if (fullNullCheck()) {
            return;
        }
        if (this.mode.getValue() == MessageMode.Toggle) {
            SpammerModule.mc.player.connection.sendPacket((Packet)new CPacketChatMessage((String)this.message22.getValue()));
            this.disable();
        }
        if (this.mode.getValue() == MessageMode.Spammer) {
            switch ((DelayType)this.delayType.getValue()) {
                case MS: {
                    if (this.timer.passedMs((long)(int)this.delayMS.getValue())) {
                        break;
                    }
                    return;
                }
                case S: {
                    if (this.timer.passedS((double)(int)this.delay.getValue())) {
                        break;
                    }
                    return;
                }
                case DS: {
                    if (this.timer.passedDs((double)(int)this.delayDS.getValue())) {
                        break;
                    }
                    return;
                }
            }
            SpammerModule.mc.player.connection.sendPacket((Packet)new CPacketChatMessage(String.valueOf(new StringBuilder().append((String)this.custom1.getValue()).append("[").append(RandomStringUtils.randomAlphanumeric((int)this.random.getValue())).append("]"))));
            this.timer.reset();
        }
    }
    
    public SpammerModule() {
        super("Spammer", "Message", Category.MISC, true, false, false);
        this.timer = new Timer();
        this.mode = (Setting<MessageMode>)this.register(new Setting("SuicideMode", (Object)MessageMode.Toggle));
        this.message22 = (Setting<String>)this.register(new Setting("Custom", (Object)"/kit 123 ", p0 -> this.mode.getValue() == MessageMode.Toggle));
        this.custom1 = (Setting<String>)this.register(new Setting("SpammerMessage", (Object)"LuigiHack Strong Client Discord:https://discord.gg/EtTGvzt3nS uwu", p0 -> this.mode.getValue() == MessageMode.Spammer));
        this.random = (Setting<Integer>)this.register(new Setting("Random", (Object)1, (Object)1, (Object)20, p0 -> this.mode.getValue() == MessageMode.Spammer));
        this.delayType = (Setting<DelayType>)this.register(new Setting("DelayType", (Object)DelayType.S, p0 -> this.mode.getValue() == MessageMode.Spammer));
        this.delay = (Setting<Integer>)this.register(new Setting("DelayS", (Object)8, (Object)1, (Object)1000, p0 -> this.delayType.getValue() == DelayType.S && this.mode.getValue() == MessageMode.Spammer));
        this.delayDS = (Setting<Integer>)this.register(new Setting("DelayDS", (Object)100, (Object)1, (Object)500, p0 -> this.delayType.getValue() == DelayType.DS && this.mode.getValue() == MessageMode.Spammer));
        this.delayMS = (Setting<Integer>)this.register(new Setting("DelayMS", (Object)100, (Object)1, (Object)1000, p0 -> this.delayType.getValue() == DelayType.MS && this.mode.getValue() == MessageMode.Spammer));
    }
    
    public enum MessageMode
    {
        Spammer, 
        Toggle;
    }
    
    public enum DelayType
    {
        S, 
        DS, 
        MS;
    }
}
