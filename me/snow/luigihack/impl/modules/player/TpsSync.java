//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.player;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;

public class TpsSync extends Module
{
    public /* synthetic */ Setting<Boolean> mining;
    private static /* synthetic */ TpsSync INSTANCE;
    public /* synthetic */ Setting<Boolean> attack;
    
    static {
        TpsSync.INSTANCE = new TpsSync();
    }
    
    public TpsSync() {
        super("TpsSync", "Syncs your client with the TPS.", Module.Category.PLAYER, true, false, false);
        this.mining = (Setting<Boolean>)this.register(new Setting("Mining", (Object)true));
        this.attack = (Setting<Boolean>)this.register(new Setting("Attack", (Object)false));
        this.setInstance();
    }
    
    public static TpsSync getInstance() {
        if (TpsSync.INSTANCE == null) {
            TpsSync.INSTANCE = new TpsSync();
        }
        return TpsSync.INSTANCE;
    }
    
    private void setInstance() {
        TpsSync.INSTANCE = this;
    }
}
