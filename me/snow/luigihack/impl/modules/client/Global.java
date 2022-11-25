//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.client;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.api.util.*;
import me.snow.luigihack.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Global extends Module
{
    public /* synthetic */ Setting<Integer> rainbowHue;
    public /* synthetic */ Setting<Boolean> potions;
    public /* synthetic */ Setting<Float> rainbowSaturation;
    public /* synthetic */ Setting<String> commandBracket;
    public /* synthetic */ Setting<Boolean> tRadarInv;
    public /* synthetic */ Setting<Integer> baritoneTimeOut;
    public /* synthetic */ Setting<String> commandBracket2;
    public /* synthetic */ Setting<String> command;
    public /* synthetic */ Setting<TextUtil.Color> commandColor;
    public /* synthetic */ Setting<Boolean> safety;
    public /* synthetic */ Setting<Boolean> oneDot15;
    public /* synthetic */ Setting<Boolean> rainbowPrefix;
    public /* synthetic */ Setting<Float> rainbowBrightness;
    private static /* synthetic */ Global INSTANCE;
    public /* synthetic */ Setting<TextUtil.Color> bracketColor;
    public /* synthetic */ Setting<Integer> safetyCheck;
    public /* synthetic */ Setting<Integer> respondTime;
    
    private void setInstance() {
        Global.INSTANCE = this;
    }
    
    public Global() {
        super("Global", "ClientGlobal", Category.CLIENT, false, false, true);
        this.commandBracket = (Setting<String>)this.register(new Setting("Bracket", (Object)"["));
        this.commandBracket2 = (Setting<String>)this.register(new Setting("Bracket2", (Object)"]"));
        this.command = (Setting<String>)this.register(new Setting("Command", (Object)"LuigiHack"));
        this.rainbowPrefix = (Setting<Boolean>)this.register(new Setting("RainbowPrefix", (Object)false));
        this.bracketColor = (Setting<TextUtil.Color>)this.register(new Setting("BColor", (Object)TextUtil.Color.GREEN));
        this.commandColor = (Setting<TextUtil.Color>)this.register(new Setting("CColor", (Object)TextUtil.Color.GREEN));
        this.potions = (Setting<Boolean>)this.register(new Setting("Potions", (Object)true));
        this.respondTime = (Setting<Integer>)this.register(new Setting("SeverTime", (Object)500, (Object)0, (Object)1000));
        this.safety = (Setting<Boolean>)this.register(new Setting("SafetyPlayer", (Object)false));
        this.safetyCheck = (Setting<Integer>)this.register(new Setting("SafetyCheck", (Object)50, (Object)1, (Object)150));
        this.oneDot15 = (Setting<Boolean>)this.register(new Setting("1.13+", (Object)false));
        this.tRadarInv = (Setting<Boolean>)this.register(new Setting("TRadarInv", (Object)true));
        this.baritoneTimeOut = (Setting<Integer>)this.register(new Setting("BaritoneTimeOut", (Object)5, (Object)1, (Object)20));
        this.rainbowHue = (Setting<Integer>)this.register(new Setting("Delay", (Object)240, (Object)0, (Object)600));
        this.rainbowBrightness = (Setting<Float>)this.register(new Setting("Brightness ", (Object)150.0f, (Object)1.0f, (Object)255.0f));
        this.rainbowSaturation = (Setting<Float>)this.register(new Setting("Saturation", (Object)150.0f, (Object)1.0f, (Object)255.0f));
        this.setInstance();
    }
    
    static {
        Global.INSTANCE = new Global();
    }
    
    @Override
    public void onLoad() {
        LuigiHack.commandManager.setClientMessage(this.getCommandMessage());
    }
    
    public String getCommandMessage() {
        if (this.rainbowPrefix.getPlannedValue()) {
            final StringBuilder obj = new StringBuilder(this.getRawCommandMessage());
            obj.insert(0, "§+");
            obj.append("§r");
            return String.valueOf(obj);
        }
        return String.valueOf(new StringBuilder().append(TextUtil.coloredString((String)this.commandBracket.getPlannedValue(), (TextUtil.Color)this.bracketColor.getPlannedValue())).append(TextUtil.coloredString((String)this.command.getPlannedValue(), (TextUtil.Color)this.commandColor.getPlannedValue())).append(TextUtil.coloredString((String)this.commandBracket2.getPlannedValue(), (TextUtil.Color)this.bracketColor.getPlannedValue())));
    }
    
    public String getRainbowCommandMessage() {
        final StringBuilder obj = new StringBuilder(this.getRawCommandMessage());
        obj.insert(0, "§+");
        obj.append("§r");
        return String.valueOf(obj);
    }
    
    public String getRawCommandMessage() {
        return String.valueOf(new StringBuilder().append((String)this.commandBracket.getValue()).append((String)this.command.getValue()).append((String)this.commandBracket2.getValue()));
    }
    
    @SubscribeEvent
    public void onSettingChange(final ClientEvent clientEvent) {
        if (clientEvent.getStage() == 2 && clientEvent.getSetting() != null && this.equals(clientEvent.getSetting().getFeature())) {
            LuigiHack.commandManager.setClientMessage(this.getCommandMessage());
        }
    }
    
    public static Global getInstance() {
        if (Global.INSTANCE == null) {
            Global.INSTANCE = new Global();
        }
        return Global.INSTANCE;
    }
}
