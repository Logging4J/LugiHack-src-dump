//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.event.events;

import me.snow.luigihack.api.event.*;
import me.snow.luigihack.api.setting.*;

public class ValueChangeEvent extends EventStage
{
    public /* synthetic */ Setting setting;
    public /* synthetic */ Object value;
    
    public ValueChangeEvent(final Setting setting, final Object value) {
        this.setting = setting;
        this.value = value;
    }
}
