//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.event.processor;

public class Event
{
    private /* synthetic */ boolean isCancelled;
    
    public Event() {
        this.isCancelled = false;
    }
    
    public final void setCancelled(final boolean isCancelled) {
        this.isCancelled = isCancelled;
    }
    
    public final boolean isCancelled() {
        return this.isCancelled;
    }
}
