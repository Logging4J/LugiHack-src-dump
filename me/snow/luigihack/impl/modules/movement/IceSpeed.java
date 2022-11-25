//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.movement;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.init.*;

public class IceSpeed extends Module
{
    private final /* synthetic */ Setting<Float> speed;
    private static /* synthetic */ IceSpeed INSTANCE;
    
    public static IceSpeed getINSTANCE() {
        if (IceSpeed.INSTANCE == null) {
            IceSpeed.INSTANCE = new IceSpeed();
        }
        return IceSpeed.INSTANCE;
    }
    
    public IceSpeed() {
        super("IceSpeed", "Speeds you up on ice.", Module.Category.MOVEMENT, false, false, false);
        this.speed = (Setting<Float>)this.register(new Setting("Speed", (Object)0.4f, (Object)0.1f, (Object)1.5f));
        IceSpeed.INSTANCE = this;
    }
    
    static {
        IceSpeed.INSTANCE = new IceSpeed();
    }
    
    public void onDisable() {
        Blocks.ICE.slipperiness = 0.98f;
        Blocks.PACKED_ICE.slipperiness = 0.98f;
        Blocks.FROSTED_ICE.slipperiness = 0.98f;
    }
    
    public void onUpdate() {
        Blocks.ICE.slipperiness = (float)this.speed.getValue();
        Blocks.PACKED_ICE.slipperiness = (float)this.speed.getValue();
        Blocks.FROSTED_ICE.slipperiness = (float)this.speed.getValue();
    }
}
