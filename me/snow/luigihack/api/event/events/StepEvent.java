//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.event.events;

import me.snow.luigihack.api.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.entity.*;

@Cancelable
public class StepEvent extends EventStage
{
    private final /* synthetic */ Entity entity;
    private /* synthetic */ float height;
    
    public Entity getEntity() {
        return this.entity;
    }
    
    public StepEvent(final int n, final Entity entity) {
        super(n);
        this.entity = entity;
        this.height = entity.stepHeight;
    }
    
    public void setHeight(final float height) {
        this.height = height;
    }
    
    public float getHeight() {
        return this.height;
    }
}
