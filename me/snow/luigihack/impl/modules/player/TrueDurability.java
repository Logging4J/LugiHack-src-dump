//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.player;

import me.snow.luigihack.impl.modules.*;

public class TrueDurability extends Module
{
    private static /* synthetic */ TrueDurability instance;
    
    public static TrueDurability getInstance() {
        if (TrueDurability.instance == null) {
            TrueDurability.instance = new TrueDurability();
        }
        return TrueDurability.instance;
    }
    
    public TrueDurability() {
        super("TrueDurability", "Shows True Durability of items", Module.Category.PLAYER, false, false, false);
        TrueDurability.instance = this;
    }
}
