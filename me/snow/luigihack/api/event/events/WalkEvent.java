//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.event.events;

import me.snow.luigihack.api.event.*;

public final class WalkEvent extends EventStage
{
    private /* synthetic */ double motionX;
    private /* synthetic */ double motionY;
    private /* synthetic */ double motionZ;
    
    public void setMotionX(final double motionX) {
        this.motionX = motionX;
    }
    
    public final double getMotionY() {
        return this.motionY;
    }
    
    public void setMotionY(final double motionY) {
        this.motionY = motionY;
    }
    
    public final double getMotionX() {
        return this.motionX;
    }
    
    public final double getMotionZ() {
        return this.motionZ;
    }
    
    public WalkEvent(final double motionX, final double motionY, final double motionZ) {
        this.motionX = motionX;
        this.motionY = motionY;
        this.motionZ = motionZ;
    }
    
    public void setMotionZ(final double motionZ) {
        this.motionZ = motionZ;
    }
}
