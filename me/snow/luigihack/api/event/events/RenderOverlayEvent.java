//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.event.events;

import me.snow.luigihack.api.event.*;
import net.minecraft.client.gui.*;

public class RenderOverlayEvent extends EventStage
{
    public /* synthetic */ float partialTicks;
    public /* synthetic */ ScaledResolution scaledResolution;
    
    public double getScreenHeight() {
        return this.scaledResolution.getScaledHeight_double();
    }
    
    public void setPartialTicks(final float partialTicks) {
        this.partialTicks = partialTicks;
    }
    
    public RenderOverlayEvent(final float partialTicks, final ScaledResolution scaledResolution) {
        this.partialTicks = partialTicks;
        this.scaledResolution = scaledResolution;
    }
    
    public void setScaledResolution(final ScaledResolution scaledResolution) {
        this.scaledResolution = scaledResolution;
    }
    
    public double getScreenWidth() {
        return this.scaledResolution.getScaledWidth_double();
    }
}
