//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.event.events;

import net.minecraft.entity.*;
import me.zero.alpine.fork.listener.*;
import me.snow.luigihack.api.event.*;
import java.util.function.*;

public class EventTotemPop extends PhobosEvent
{
    private final /* synthetic */ EntityLivingBase entity;
    private final /* synthetic */ int times;
    @EventHandler
    private final /* synthetic */ Listener<EventTotemPop> packetRecieveListener;
    
    public EventTotemPop(final EnumStages enumStages, final EntityLivingBase entity, final int times) {
        super(enumStages);
        this.packetRecieveListener = new Listener<EventTotemPop>(p0 -> {}, (Predicate<EventTotemPop>[])new Predicate[0]);
        this.entity = entity;
        this.times = times;
    }
    
    public EntityLivingBase getEntity() {
        return this.entity;
    }
    
    public int getTimes() {
        return this.times;
    }
}
