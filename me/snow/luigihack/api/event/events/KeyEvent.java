//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.event.events;

import me.snow.luigihack.api.event.*;

public class KeyEvent extends EventStage
{
    public /* synthetic */ boolean pressed;
    public /* synthetic */ boolean info;
    
    public KeyEvent(final int n, final boolean info, final boolean pressed) {
        super(n);
        this.info = info;
        this.pressed = pressed;
    }
}
