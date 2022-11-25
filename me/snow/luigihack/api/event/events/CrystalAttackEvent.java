//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.event.events;

import me.snow.luigihack.api.event.*;
import net.minecraft.entity.*;

public class CrystalAttackEvent extends EventStage
{
    /* synthetic */ int entityId;
    /* synthetic */ Entity entity;
    
    public Entity getEntity() {
        return this.entity;
    }
    
    public int getEntityId() {
        return this.entityId;
    }
    
    public CrystalAttackEvent(final int entityId, final Entity entity) {
        this.entityId = entityId;
        this.entity = entity;
    }
}
