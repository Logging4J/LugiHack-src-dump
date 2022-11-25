//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.render;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;

public class ViewClip extends Module
{
    public /* synthetic */ Setting<Boolean> extend;
    private static /* synthetic */ ViewClip INSTANCE;
    public /* synthetic */ Setting<Double> distance;
    
    private void setInstance() {
        ViewClip.INSTANCE = this;
    }
    
    public ViewClip() {
        super("ViewClip", "Makes your Camera clip.", Module.Category.RENDER, false, false, false);
        this.extend = (Setting<Boolean>)this.register(new Setting("Extend", (Object)true));
        this.distance = (Setting<Double>)this.register(new Setting("Distance", (Object)3.2, (Object)0.0, (Object)50.0, p0 -> (boolean)this.extend.getValue(), "By how much you want to extend the distance."));
        this.setInstance();
    }
    
    static {
        ViewClip.INSTANCE = new ViewClip();
    }
    
    public static ViewClip getInstance() {
        if (ViewClip.INSTANCE == null) {
            ViewClip.INSTANCE = new ViewClip();
        }
        return ViewClip.INSTANCE;
    }
}
