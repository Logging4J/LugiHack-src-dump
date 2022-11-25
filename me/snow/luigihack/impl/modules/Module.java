//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules;

import me.snow.luigihack.impl.*;
import me.snow.luigihack.api.setting.*;
import net.minecraftforge.common.*;
import net.minecraftforge.client.event.*;
import me.snow.luigihack.*;
import me.snow.luigihack.api.manager.*;
import me.snow.luigihack.impl.command.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.snow.luigihack.impl.modules.client.*;
import me.snow.luigihack.api.util.*;
import java.util.concurrent.*;

public class Module extends Feature
{
    public /* synthetic */ Setting<Bind> bind;
    public /* synthetic */ boolean sliding;
    public /* synthetic */ float offset;
    public /* synthetic */ Setting<String> displayName;
    public /* synthetic */ float vOffset;
    public /* synthetic */ Setting<Boolean> drawn;
    public /* synthetic */ Setting<Boolean> enabled;
    private final /* synthetic */ String description;
    public /* synthetic */ boolean hasListener;
    private final /* synthetic */ Category category;
    public /* synthetic */ float arrayListVOffset;
    public /* synthetic */ Animation animation;
    public /* synthetic */ float arrayListOffset;
    public /* synthetic */ boolean alwaysListening;
    public /* synthetic */ boolean hidden;
    
    public boolean isDrawn() {
        return (boolean)this.drawn.getValue();
    }
    
    public String getInfo() {
        return null;
    }
    
    public void enable() {
        this.enabled.setValue((Object)true);
        this.onToggle();
        this.onEnable();
        if (this.isOn() && this.hasListener && !this.alwaysListening) {
            MinecraftForge.EVENT_BUS.register((Object)this);
        }
    }
    
    public void onEnable() {
    }
    
    public Category getCategory() {
        return this.category;
    }
    
    public boolean listening() {
        return (this.hasListener && this.isOn()) || this.alwaysListening;
    }
    
    public String getDisplayName() {
        return (String)this.displayName.getValue();
    }
    
    public void onToggle() {
    }
    
    public void onUpdate() {
    }
    
    public void setBind(final int n) {
        this.bind.setValue((Object)new Bind(n));
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void onRender2D(final Render2DEvent render2DEvent) {
    }
    
    public Module(final String s, final String description, final Category category, final boolean hasListener, final boolean hidden, final boolean alwaysListening) {
        super(s);
        this.enabled = (Setting<Boolean>)this.register(new Setting("Enabled", (Object)false));
        this.drawn = (Setting<Boolean>)this.register(new Setting("Drawn", (Object)true));
        this.bind = (Setting<Bind>)this.register(new Setting("Bind", (Object)new Bind(-1)));
        this.arrayListOffset = 0.0f;
        this.arrayListVOffset = 0.0f;
        this.displayName = (Setting<String>)this.register(new Setting("DisplayName", (Object)s));
        this.description = description;
        this.category = category;
        this.hasListener = hasListener;
        this.hidden = hidden;
        this.alwaysListening = alwaysListening;
        this.animation = new Animation(this);
    }
    
    public String getDisplayInfo() {
        return null;
    }
    
    public void onLoad() {
    }
    
    public void onUnload() {
    }
    
    public void onDisable() {
    }
    
    public void onRenderHand(final RenderHandEvent renderHandEvent) {
    }
    
    public void setEnabled(final boolean b) {
        if (b) {
            this.enable();
        }
        else {
            this.disable();
        }
    }
    
    public void disable() {
        if (this.hasListener && !this.alwaysListening) {
            MinecraftForge.EVENT_BUS.unregister((Object)this);
        }
        this.enabled.setValue((Object)false);
        this.onToggle();
        this.onDisable();
    }
    
    public boolean isOff() {
        return !(boolean)this.enabled.getValue();
    }
    
    public void setDisplayName(final String s) {
        final Module moduleByDisplayName = LuigiHack.moduleManager.getModuleByDisplayName(s);
        final ModuleManager moduleManager = LuigiHack.moduleManager;
        final Module moduleByName = ModuleManager.getModuleByName(s);
        if (moduleByDisplayName == null && moduleByName == null) {
            Command.sendMessage(String.valueOf(new StringBuilder().append(this.getDisplayName()).append(", Original name: ").append(this.getName()).append(", has been renamed to: ").append(s)));
            this.displayName.setValue((Object)s);
            return;
        }
        Command.sendMessage("§cA module of this name already exists.");
    }
    
    public void onLogout() {
    }
    
    public String getFullArrayString() {
        return String.valueOf(new StringBuilder().append(this.getDisplayName()).append("§8").append((this.getDisplayInfo() != null) ? String.valueOf(new StringBuilder().append(" [§r").append(this.getDisplayInfo()).append("§8]")) : ""));
    }
    
    public boolean isSliding() {
        return this.sliding;
    }
    
    public void onRender3D(final Render3DEvent render3DEvent) {
    }
    
    public void onTick() {
    }
    
    public void toggle() {
        final ClientEvent clientEvent = new ClientEvent((int)(this.isEnabled() ? 0 : 1), (Feature)this);
        MinecraftForge.EVENT_BUS.post((Event)clientEvent);
        if (!clientEvent.isCanceled()) {
            this.setEnabled(!this.isEnabled());
        }
    }
    
    public void onLogin() {
    }
    
    public void setDrawn(final boolean b) {
        this.drawn.setValue((Object)b);
    }
    
    public boolean isOn() {
        return (boolean)this.enabled.getValue();
    }
    
    public Bind getBind() {
        return (Bind)this.bind.getValue();
    }
    
    public enum Category
    {
        MOVEMENT("Movement"), 
        MISC("Misc");
        
        private final /* synthetic */ String name;
        
        RENDER("Render"), 
        COMBAT("Combat"), 
        PLAYER("Player"), 
        CLIENT("Client");
        
        public String getName() {
            return this.name;
        }
        
        private Category(final String name2) {
            this.name = name2;
        }
    }
    
    public class Animation extends Thread
    {
        /* synthetic */ ScheduledExecutorService service;
        
        @Override
        public void start() {
            System.out.println(String.valueOf(new StringBuilder().append("Starting animation thread for ").append(Module.this.getName())));
            this.service.scheduleAtFixedRate(this, 0L, 1L, TimeUnit.MILLISECONDS);
        }
        
        @Override
        public void run() {
            final String value = String.valueOf(new StringBuilder().append(Module.this.getDisplayName()).append("§7").append((Module.this.getDisplayInfo() != null) ? String.valueOf(new StringBuilder().append(" [§f").append(Module.this.getDisplayInfo()).append("§7]")) : ""));
            Module.this.offset = Module.this.renderer.getStringWidth(value) / (float)HUD.getInstance().animationHorizontalTime.getValue();
            Module.this.vOffset = Module.this.renderer.getFontHeight() / (float)HUD.getInstance().animationVerticalTime.getValue();
            if (Module.this.isEnabled() && (int)HUD.getInstance().animationHorizontalTime.getValue() != 1) {
                if (Module.this.arrayListOffset > Module.this.offset && Util.mc.world != null) {
                    final Module module = Module.this;
                    module.arrayListOffset -= Module.this.offset;
                    Module.this.sliding = true;
                }
            }
            else if (Module.this.isDisabled() && (int)HUD.getInstance().animationHorizontalTime.getValue() != 1) {
                if (Module.this.arrayListOffset < Module.this.renderer.getStringWidth(value) && Util.mc.world != null) {
                    final Module module2 = Module.this;
                    module2.arrayListOffset += Module.this.offset;
                    Module.this.sliding = true;
                }
                else {
                    Module.this.sliding = false;
                }
            }
        }
        
        public Animation() {
            super("Animation");
            this.service = Executors.newSingleThreadScheduledExecutor();
        }
    }
}
