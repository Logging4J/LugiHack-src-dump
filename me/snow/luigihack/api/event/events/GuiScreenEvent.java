//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.event.events;

import net.minecraft.client.gui.*;

public class GuiScreenEvent
{
    private /* synthetic */ GuiScreen screen;
    
    public void setScreen(final GuiScreen screen) {
        this.screen = screen;
    }
    
    public GuiScreen getScreen() {
        return this.screen;
    }
    
    public GuiScreenEvent(final GuiScreen screen) {
        this.screen = screen;
    }
    
    public static class Displayed extends GuiScreenEvent
    {
        public Displayed(final GuiScreen guiScreen) {
            super(guiScreen);
        }
    }
    
    public static class Closed extends GuiScreenEvent
    {
        public Closed(final GuiScreen guiScreen) {
            super(guiScreen);
        }
    }
}
