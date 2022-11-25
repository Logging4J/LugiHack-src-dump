//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.player;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;

public class Reach extends Module
{
    public /* synthetic */ Setting<Float> reach;
    public /* synthetic */ Setting<Float> add;
    public /* synthetic */ Setting<Boolean> override;
    private static /* synthetic */ Reach INSTANCE;
    
    private void setInstance() {
        Reach.INSTANCE = this;
    }
    
    static {
        Reach.INSTANCE = new Reach();
    }
    
    public Reach() {
        super("Reach", "Extends your block reach", Module.Category.PLAYER, true, false, false);
        this.override = (Setting<Boolean>)this.register(new Setting("Override", (Object)false));
        this.add = (Setting<Float>)this.register(new Setting("Add", (Object)3.0f, p0 -> !(boolean)this.override.getValue()));
        this.reach = (Setting<Float>)this.register(new Setting("Reach", (Object)6.0f, p0 -> (boolean)this.override.getValue()));
        this.setInstance();
    }
    
    public static Reach getInstance() {
        if (Reach.INSTANCE == null) {
            Reach.INSTANCE = new Reach();
        }
        return Reach.INSTANCE;
    }
    
    public String getDisplayInfo() {
        return this.override.getValue() ? ((Float)this.reach.getValue()).toString() : ((Float)this.add.getValue()).toString();
    }
}
