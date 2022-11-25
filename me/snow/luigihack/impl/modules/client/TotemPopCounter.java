//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.client;

import me.snow.luigihack.impl.modules.*;
import java.util.*;
import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.entity.player.*;
import me.snow.luigihack.impl.command.*;
import me.snow.luigihack.impl.*;

public class TotemPopCounter extends Module
{
    public static /* synthetic */ TotemPopCounter INSTANCE;
    public static /* synthetic */ HashMap<String, Integer> TotemPopCounter;
    public /* synthetic */ Setting<TextUtil.Color> popcountC;
    public /* synthetic */ Setting<TextUtil.Color> textC;
    public /* synthetic */ Setting<TextUtil.Color> playernameC;
    
    public void onDeath(final EntityPlayer entityPlayer) {
        if (getInstance().isOn() && me.snow.luigihack.impl.modules.client.TotemPopCounter.TotemPopCounter.containsKey(entityPlayer.getName())) {
            final int intValue = me.snow.luigihack.impl.modules.client.TotemPopCounter.TotemPopCounter.get(entityPlayer.getName());
            me.snow.luigihack.impl.modules.client.TotemPopCounter.TotemPopCounter.remove(entityPlayer.getName());
            if (intValue == 1) {
                Command.sendSilentMessage(String.valueOf(new StringBuilder().append(" ").append(TextUtil.coloredString(entityPlayer.getName(), (TextUtil.Color)this.playernameC.getPlannedValue())).append(TextUtil.coloredString(" died after popping ", (TextUtil.Color)this.textC.getPlannedValue())).append(TextUtil.coloredString(String.valueOf(intValue), (TextUtil.Color)this.popcountC.getPlannedValue())).append(TextUtil.coloredString(" totem.", (TextUtil.Color)this.textC.getPlannedValue()))));
            }
            else {
                Command.sendSilentMessage(String.valueOf(new StringBuilder().append(" ").append(TextUtil.coloredString(entityPlayer.getName(), (TextUtil.Color)this.playernameC.getPlannedValue())).append(TextUtil.coloredString(" died after popping ", (TextUtil.Color)this.textC.getPlannedValue())).append(TextUtil.coloredString(String.valueOf(intValue), (TextUtil.Color)this.popcountC.getPlannedValue())).append(TextUtil.coloredString(" totems.", (TextUtil.Color)this.textC.getPlannedValue()))));
            }
        }
    }
    
    private void setInstance() {
        me.snow.luigihack.impl.modules.client.TotemPopCounter.INSTANCE = this;
    }
    
    public TotemPopCounter() {
        super("TotemPopCounter", "Counts other players totem pops.", Category.CLIENT, true, false, false);
        this.playernameC = (Setting<TextUtil.Color>)this.register(new Setting("PlayerName", (Object)TextUtil.Color.GOLD));
        this.textC = (Setting<TextUtil.Color>)this.register(new Setting("Text", (Object)TextUtil.Color.RED));
        this.popcountC = (Setting<TextUtil.Color>)this.register(new Setting("Pop Amount", (Object)TextUtil.Color.GOLD));
        this.setInstance();
    }
    
    static {
        me.snow.luigihack.impl.modules.client.TotemPopCounter.TotemPopCounter = new HashMap<String, Integer>();
        me.snow.luigihack.impl.modules.client.TotemPopCounter.INSTANCE = new TotemPopCounter();
    }
    
    public static TotemPopCounter getInstance() {
        if (me.snow.luigihack.impl.modules.client.TotemPopCounter.INSTANCE == null) {
            me.snow.luigihack.impl.modules.client.TotemPopCounter.INSTANCE = new TotemPopCounter();
        }
        return me.snow.luigihack.impl.modules.client.TotemPopCounter.INSTANCE;
    }
    
    public void onTotemPop(final EntityPlayer entityPlayer) {
        if (getInstance().isOn()) {
            if (Feature.fullNullCheck()) {
                return;
            }
            if (me.snow.luigihack.impl.modules.client.TotemPopCounter.mc.player.equals((Object)entityPlayer)) {
                return;
            }
            int intValue = 1;
            if (me.snow.luigihack.impl.modules.client.TotemPopCounter.TotemPopCounter.containsKey(entityPlayer.getName())) {
                intValue = me.snow.luigihack.impl.modules.client.TotemPopCounter.TotemPopCounter.get(entityPlayer.getName());
                me.snow.luigihack.impl.modules.client.TotemPopCounter.TotemPopCounter.put(entityPlayer.getName(), ++intValue);
            }
            else {
                me.snow.luigihack.impl.modules.client.TotemPopCounter.TotemPopCounter.put(entityPlayer.getName(), intValue);
            }
            if (intValue == 1) {
                Command.sendSilentMessage(String.valueOf(new StringBuilder().append(" ").append(TextUtil.coloredString(entityPlayer.getName(), (TextUtil.Color)this.playernameC.getPlannedValue())).append(TextUtil.coloredString(" popped ", (TextUtil.Color)this.textC.getPlannedValue())).append(TextUtil.coloredString(String.valueOf(intValue), (TextUtil.Color)this.popcountC.getPlannedValue())).append(TextUtil.coloredString(" totem.", (TextUtil.Color)this.textC.getPlannedValue()))));
            }
            else {
                Command.sendSilentMessage(String.valueOf(new StringBuilder().append(" ").append(TextUtil.coloredString(entityPlayer.getName(), (TextUtil.Color)this.playernameC.getPlannedValue())).append(TextUtil.coloredString(" popped ", (TextUtil.Color)this.textC.getPlannedValue())).append(TextUtil.coloredString(String.valueOf(intValue), (TextUtil.Color)this.popcountC.getPlannedValue())).append(TextUtil.coloredString(" totems.", (TextUtil.Color)this.textC.getPlannedValue()))));
            }
        }
    }
    
    @Override
    public void onEnable() {
        me.snow.luigihack.impl.modules.client.TotemPopCounter.TotemPopCounter.clear();
    }
}
