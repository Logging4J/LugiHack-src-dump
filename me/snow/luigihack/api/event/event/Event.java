//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.event.event;

public class Event<T>
{
    public /* synthetic */ boolean cancelled;
    public /* synthetic */ EventDirection direction;
    public /* synthetic */ EventType type;
    
    public void setCancelled(final boolean cancelled) {
        this.cancelled = cancelled;
    }
    
    public boolean isOutgoing() {
        return this.direction != null && this.direction == EventDirection.OUTGOING;
    }
    
    public boolean isPost() {
        return this.type != null && this.type == EventType.POST;
    }
    
    public boolean isPre() {
        return this.type != null && this.type == EventType.PRE;
    }
    
    public void setDirection(final EventDirection direction) {
        this.direction = direction;
    }
    
    public void cancel() {
        this.cancelled = true;
    }
    
    public void setType(final EventType type) {
        this.type = type;
    }
    
    public boolean isIncoming() {
        return this.direction != null && this.direction == EventDirection.INCOMING;
    }
    
    public boolean isCancelled() {
        return this.cancelled;
    }
    
    public EventType getType() {
        return this.type;
    }
    
    public EventDirection getDirection() {
        return this.direction;
    }
}
