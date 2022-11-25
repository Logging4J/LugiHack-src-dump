//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.event.events;

import me.snow.luigihack.api.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.snow.luigihack.impl.*;
import me.snow.luigihack.api.setting.*;

@Cancelable
public class ClientEvent extends EventStage
{
    private /* synthetic */ Feature feature;
    private /* synthetic */ Setting setting;
    
    public Setting getSetting() {
        return this.setting;
    }
    
    public ClientEvent(final Setting setting) {
        super(2);
        this.setting = setting;
    }
    
    public ClientEvent(final int n, final Feature feature) {
        super(n);
        this.feature = feature;
    }
    
    public Feature getFeature() {
        return this.feature;
    }
}
