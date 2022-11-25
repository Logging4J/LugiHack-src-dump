//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.event.events;

import me.snow.luigihack.api.event.*;

public class ChorusEvent extends EventStage
{
    private final /* synthetic */ double chorusY;
    private final /* synthetic */ double chorusX;
    private final /* synthetic */ double chorusZ;
    
    public double getChorusY() {
        return this.chorusY;
    }
    
    public double getChorusX() {
        return this.chorusX;
    }
    
    public ChorusEvent(final double chorusX, final double chorusY, final double chorusZ) {
        this.chorusX = chorusX;
        this.chorusY = chorusY;
        this.chorusZ = chorusZ;
    }
    
    public double getChorusZ() {
        return this.chorusZ;
    }
}
