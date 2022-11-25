//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.event.event.listeners;

import me.snow.luigihack.api.event.event.*;

public class EventRenderWorld extends Event<EventRenderWorld>
{
    /* synthetic */ float partialTicks;
    
    public float getPartialTicks() {
        return this.partialTicks;
    }
    
    public EventRenderWorld(final float partialTicks) {
        this.partialTicks = partialTicks;
    }
    
    public void setPartialTicks(final float partialTicks) {
        this.partialTicks = partialTicks;
    }
}
