//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.event.events;

import me.snow.luigihack.api.event.*;
import net.minecraftforge.fml.common.eventhandler.*;

@Cancelable
public class ChatEvent extends EventStage
{
    private final /* synthetic */ String msg;
    
    public String getMsg() {
        return this.msg;
    }
    
    public ChatEvent(final String msg) {
        this.msg = msg;
    }
}
