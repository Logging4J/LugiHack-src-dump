//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.event.events;

import me.snow.luigihack.api.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.entity.*;

@Cancelable
public final class PlayerApplyCollisionEvent extends EventStage
{
    private final /* synthetic */ Entity entity;
    
    public PlayerApplyCollisionEvent(final int n, final Entity entity) {
        super(n);
        this.entity = entity;
    }
    
    public int hashCode() {
        final Entity entity = this.entity;
        return (entity != null) ? entity.hashCode() : 0;
    }
    
    public final Entity component1() {
        return this.entity;
    }
    
    public final Entity getEntity() {
        return this.entity;
    }
    
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof PlayerApplyCollisionEvent)) {
                return false;
            }
            final PlayerApplyCollisionEvent playerApplyCollisionEvent = (PlayerApplyCollisionEvent)o;
        }
        return true;
    }
    
    public String toString() {
        return String.valueOf(new StringBuilder().append("PlayerApplyCollisionEvent(entity=").append(this.entity).append(")"));
    }
    
    public PlayerApplyCollisionEvent(final Entity entity) {
        this.entity = entity;
    }
    
    public final PlayerApplyCollisionEvent copy(final Entity entity) {
        return new PlayerApplyCollisionEvent(entity);
    }
    
    public static PlayerApplyCollisionEvent copy$default(final PlayerApplyCollisionEvent playerApplyCollisionEvent, Entity entity, final int n, final Object o) {
        if ((n & 0x1) != 0x0) {
            entity = playerApplyCollisionEvent.entity;
        }
        return playerApplyCollisionEvent.copy(entity);
    }
}
