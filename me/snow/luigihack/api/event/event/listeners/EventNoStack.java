//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.event.event.listeners;

import me.snow.luigihack.*;

public class EventNoStack extends RuntimeException
{
    @Override
    public String toString() {
        return String.valueOf(new StringBuilder().append("").append(LuigiHack.getVersion()));
    }
    
    public EventNoStack(final String message) {
        super(message);
        this.setStackTrace(new StackTraceElement[0]);
    }
    
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
