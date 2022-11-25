//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.player;

import me.snow.luigihack.impl.modules.*;

public class LiquidInteract extends Module
{
    private static /* synthetic */ LiquidInteract INSTANCE;
    
    public static LiquidInteract getInstance() {
        if (LiquidInteract.INSTANCE == null) {
            LiquidInteract.INSTANCE = new LiquidInteract();
        }
        return LiquidInteract.INSTANCE;
    }
    
    private void setInstance() {
        LiquidInteract.INSTANCE = this;
    }
    
    public LiquidInteract() {
        super("LiquidInteract", "Interact with liquids", Module.Category.PLAYER, false, false, false);
        this.setInstance();
    }
    
    static {
        LiquidInteract.INSTANCE = new LiquidInteract();
    }
}
