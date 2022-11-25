//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.client;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import com.mojang.realmsclient.gui.*;
import me.snow.luigihack.impl.command.*;

public class Media extends Module
{
    public final /* synthetic */ Setting<String> ownName;
    private static /* synthetic */ Media instance;
    public final /* synthetic */ Setting<Boolean> changeOwn;
    private /* synthetic */ StringBuffer name;
    
    public static String getPlayerName() {
        if (fullNullCheck()) {
            return Media.mc.getSession().getUsername();
        }
        final String playerName211 = getInstance().getPlayerName211();
        if (playerName211 == null || playerName211.isEmpty()) {
            return Media.mc.getSession().getUsername();
        }
        return playerName211;
    }
    
    @Override
    public void onEnable() {
        Command.sendMessage(String.valueOf(new StringBuilder().append(ChatFormatting.GRAY).append("Success! Name succesfully changed to ").append(ChatFormatting.GREEN).append((String)this.ownName.getValue())));
    }
    
    public static Media getInstance() {
        if (Media.instance == null) {
            Media.instance = new Media();
        }
        return Media.instance;
    }
    
    public Media() {
        super("NameProtect", "Helps with creating Media", Category.CLIENT, false, false, false);
        this.changeOwn = (Setting<Boolean>)this.register(new Setting("MyName", (Object)true));
        this.ownName = (Setting<String>)this.register(new Setting("Name", (Object)"Name here...", p0 -> (boolean)this.changeOwn.getValue()));
        Media.instance = this;
    }
    
    public String getPlayerName211() {
        if (this.name == null) {
            return null;
        }
        return this.name.toString();
    }
}
