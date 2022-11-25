//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.event;

import me.snow.luigihack.api.event.events.*;

public class PhobosEvent implements ICancellable
{
    private final /* synthetic */ EnumStages stage;
    private /* synthetic */ boolean canceled;
    
    public void resume() {
        this.canceled = false;
    }
    
    public EnumStages getStage() {
        return this.stage;
    }
    
    public PhobosEvent(final EnumStages stage) {
        this.stage = stage;
    }
    
    public void cancel() {
        this.canceled = true;
    }
    
    public boolean isCancelled() {
        return this.canceled;
    }
}
