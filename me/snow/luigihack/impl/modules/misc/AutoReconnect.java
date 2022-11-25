//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.misc;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.gui.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.event.world.*;
import me.snow.luigihack.impl.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.client.multiplayer.*;

public class AutoReconnect extends Module
{
    private final /* synthetic */ Setting<Integer> delay;
    private static /* synthetic */ AutoReconnect INSTANCE;
    private static /* synthetic */ ServerData serverData;
    
    private void setInstance() {
        AutoReconnect.INSTANCE = this;
    }
    
    public void updateLastConnectedServer() {
        final ServerData getCurrentServerData = AutoReconnect.mc.getCurrentServerData();
        if (getCurrentServerData != null) {
            AutoReconnect.serverData = getCurrentServerData;
        }
    }
    
    public static AutoReconnect getInstance() {
        if (AutoReconnect.INSTANCE == null) {
            AutoReconnect.INSTANCE = new AutoReconnect();
        }
        return AutoReconnect.INSTANCE;
    }
    
    static {
        AutoReconnect.INSTANCE = new AutoReconnect();
    }
    
    @SubscribeEvent
    public void sendPacket(final GuiOpenEvent guiOpenEvent) {
        if (guiOpenEvent.getGui() instanceof GuiDisconnected) {
            this.updateLastConnectedServer();
            if (AutoLog.getInstance().isOff()) {
                guiOpenEvent.setGui((GuiScreen)new GuiDisconnectedHook((GuiDisconnected)guiOpenEvent.getGui()));
            }
        }
    }
    
    public AutoReconnect() {
        super("AutoReconnect", "Reconnects you if you disconnect.", Category.MISC, true, false, false);
        this.delay = (Setting<Integer>)this.register(new Setting("Delay", (Object)5));
        this.setInstance();
    }
    
    @SubscribeEvent
    public void onWorldUnload(final WorldEvent.Unload unload) {
        this.updateLastConnectedServer();
    }
    
    private class GuiDisconnectedHook extends GuiDisconnected
    {
        private final /* synthetic */ Timer timer;
        
        public void drawScreen(final int n, final int n2, final float n3) {
            if (Feature.fullNullCheck()) {
                return;
            }
            super.drawScreen(n, n2, n3);
            final String value = String.valueOf(new StringBuilder().append("Reconnecting in ").append(MathUtil.round(((int)AutoReconnect.this.delay.getValue() * 1000 - this.timer.getPassedTimeMs()) / 1000.0, 1)));
            AutoReconnect.this.renderer.drawString(value, (float)(this.width / 2 - AutoReconnect.this.renderer.getStringWidth(value) / 2), (float)(this.height - 16), 16777215, true);
        }
        
        public GuiDisconnectedHook(final GuiDisconnected guiDisconnected) {
            super(guiDisconnected.parentScreen, guiDisconnected.reason, guiDisconnected.message);
            this.timer = new Timer();
            this.timer.reset();
        }
        
        public void updateScreen() {
            if (this.timer.passedS((double)(int)AutoReconnect.this.delay.getValue())) {
                this.mc.displayGuiScreen((GuiScreen)new GuiConnecting(this.parentScreen, this.mc, (AutoReconnect.serverData == null) ? this.mc.currentServerData : AutoReconnect.serverData));
            }
        }
    }
}
