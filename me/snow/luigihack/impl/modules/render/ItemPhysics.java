//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.render;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;

public class ItemPhysics extends Module
{
    public final /* synthetic */ Setting<Float> Scaling;
    public static /* synthetic */ ItemPhysics INSTANCE;
    
    private void setInstance() {
        ItemPhysics.INSTANCE = this;
    }
    
    public ItemPhysics() {
        super("ItemPhysics", "Apply physics to items.", Module.Category.RENDER, false, false, false);
        this.Scaling = (Setting<Float>)this.register(new Setting("Scaling", (Object)0.5f, (Object)0.0f, (Object)10.0f));
        this.setInstance();
    }
    
    public static ItemPhysics getInstance() {
        if (ItemPhysics.INSTANCE == null) {
            ItemPhysics.INSTANCE = new ItemPhysics();
        }
        return ItemPhysics.INSTANCE;
    }
    
    static {
        ItemPhysics.INSTANCE = new ItemPhysics();
    }
}
