//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.event.events;

import me.snow.luigihack.api.event.*;
import java.util.*;

public class PlayerConnectEvent extends EventStage
{
    private /* synthetic */ UUID uuid;
    private /* synthetic */ String name;
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public PlayerConnectEvent(final String name, final UUID uuid) {
        this.name = name;
        this.uuid = uuid;
    }
    
    public void setUuid(final UUID uuid) {
        this.uuid = uuid;
    }
    
    public String getName() {
        return this.name;
    }
    
    public UUID getUuid() {
        return this.uuid;
    }
    
    public static class Join extends PlayerConnectEvent
    {
        public Join(final String s, final UUID uuid) {
            super(s, uuid);
        }
    }
    
    public static class Leave extends PlayerConnectEvent
    {
        public Leave(final String s, final UUID uuid) {
            super(s, uuid);
        }
    }
}
