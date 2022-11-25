//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.client;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.*;

public class RPC extends Module
{
    public /* synthetic */ String state;
    public static /* synthetic */ RPC INSTANCE;
    public /* synthetic */ Setting<Boolean> catMode;
    public /* synthetic */ Setting<Boolean> showIP;
    
    public RPC() {
        super("RPC", "Discord rich presence", Category.CLIENT, false, false, false);
        this.catMode = (Setting<Boolean>)this.register(new Setting("CatMode", (Object)false));
        this.showIP = (Setting<Boolean>)this.register(new Setting("ShowIP", (Object)Boolean.TRUE, "Shows the server IP in your discord presence."));
        this.state = "Luigihack - 1.8.3";
        RPC.INSTANCE = this;
    }
    
    @Override
    public void onDisable() {
        DiscordPresence.stop();
    }
    
    @Override
    public void onEnable() {
        DiscordPresence.start();
    }
}
