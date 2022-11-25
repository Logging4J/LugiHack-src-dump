//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.player;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.init.*;
import net.minecraft.item.*;

public class NoEntityTrace extends Module
{
    public /* synthetic */ boolean noTrace;
    public /* synthetic */ Setting<Boolean> pick;
    public /* synthetic */ Setting<Boolean> obby;
    public /* synthetic */ Setting<Boolean> gap;
    private static /* synthetic */ NoEntityTrace INSTANCE;
    
    public static NoEntityTrace getINSTANCE() {
        if (NoEntityTrace.INSTANCE == null) {
            NoEntityTrace.INSTANCE = new NoEntityTrace();
        }
        return NoEntityTrace.INSTANCE;
    }
    
    public void onUpdate() {
        final Item getItem = NoEntityTrace.mc.player.getHeldItemMainhand().getItem();
        if (getItem instanceof ItemPickaxe && (boolean)this.pick.getValue()) {
            this.noTrace = true;
            return;
        }
        if (getItem == Items.GOLDEN_APPLE && (boolean)this.gap.getValue()) {
            this.noTrace = true;
            return;
        }
        if (getItem instanceof ItemBlock) {
            this.noTrace = (((ItemBlock)getItem).getBlock() == Blocks.OBSIDIAN && (boolean)this.obby.getValue());
            return;
        }
        this.noTrace = false;
    }
    
    private void setInstance() {
        NoEntityTrace.INSTANCE = this;
    }
    
    public NoEntityTrace() {
        super("NoEntityTrace", "Mine through entities", Module.Category.PLAYER, false, false, false);
        this.pick = (Setting<Boolean>)this.register(new Setting("Pick", (Object)true));
        this.gap = (Setting<Boolean>)this.register(new Setting("Gap", (Object)false));
        this.obby = (Setting<Boolean>)this.register(new Setting("Obby", (Object)false));
        this.setInstance();
    }
    
    static {
        NoEntityTrace.INSTANCE = new NoEntityTrace();
    }
}
