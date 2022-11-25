//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.render;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Aspect extends Module
{
    public /* synthetic */ Setting<Float> aspect;
    
    @SubscribeEvent
    public void onPerspectiveEvent(final PerspectiveEvent perspectiveEvent) {
        perspectiveEvent.setAspect((float)this.aspect.getValue());
    }
    
    public Aspect() {
        super("Aspect", "Cool.", Module.Category.RENDER, true, false, false);
        this.aspect = (Setting<Float>)this.register(new Setting("Alpha", (Object)1.0f, (Object)0.1f, (Object)5.0f));
    }
}
