//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.client;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.api.event.events.*;
import me.snow.luigihack.impl.command.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.snow.luigihack.*;
import java.awt.*;

public class CustomFont extends Module
{
    public /* synthetic */ Setting<Boolean> fractionalMetrics;
    public /* synthetic */ Setting<Boolean> full;
    public /* synthetic */ Setting<Boolean> shadow;
    private static /* synthetic */ CustomFont INSTANCE;
    private /* synthetic */ boolean reloadFont;
    public /* synthetic */ Setting<Boolean> showFonts;
    public /* synthetic */ Setting<Integer> fontStyle;
    public /* synthetic */ Setting<Integer> fontSize;
    public /* synthetic */ Setting<String> fontName;
    public /* synthetic */ Setting<Boolean> antiAlias;
    
    @SubscribeEvent
    public void onSettingChange(final ClientEvent clientEvent) {
        final Setting setting;
        if (clientEvent.getStage() == 2 && (setting = clientEvent.getSetting()) != null && setting.getFeature().equals(this)) {
            if (setting.getName().equals("FontName") && !checkFont(setting.getPlannedValue().toString(), false)) {
                Command.sendMessage("§cThat font doesnt exist.");
                clientEvent.setCanceled(true);
                return;
            }
            this.reloadFont = true;
        }
    }
    
    @Override
    public void onTick() {
        if (this.showFonts.getValue()) {
            checkFont("Hello", true);
            Command.sendMessage(String.valueOf(new StringBuilder().append("Current Font: ").append((String)this.fontName.getValue())));
            this.showFonts.setValue((Object)false);
        }
        if (this.reloadFont) {
            LuigiHack.textManager.init(false);
            this.reloadFont = false;
        }
    }
    
    public static boolean checkFont(final String anObject, final boolean b) {
        for (final String s : GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()) {
            if (!b && s.equals(anObject)) {
                return true;
            }
            if (b) {
                Command.sendMessage(s);
            }
        }
        return false;
    }
    
    private void setInstance() {
        CustomFont.INSTANCE = this;
    }
    
    public CustomFont() {
        super("CustomFont", "CustomFont for all of the clients text. Use the font command.", Category.CLIENT, true, false, false);
        this.fontName = (Setting<String>)this.register(new Setting("FontName", (Object)"Arial", "Name of the font."));
        this.fontSize = (Setting<Integer>)this.register(new Setting("FontSize", (Object)18, (Object)12, (Object)25, "Size of the font."));
        this.fontStyle = (Setting<Integer>)this.register(new Setting("FontStyle", (Object)0, "Style of the font."));
        this.antiAlias = (Setting<Boolean>)this.register(new Setting("AntiAlias", (Object)Boolean.TRUE, "Smoother font."));
        this.fractionalMetrics = (Setting<Boolean>)this.register(new Setting("Metrics", (Object)Boolean.TRUE, "Thinner font."));
        this.shadow = (Setting<Boolean>)this.register(new Setting("Shadow", (Object)Boolean.FALSE, "Less shadow offset font."));
        this.showFonts = (Setting<Boolean>)this.register(new Setting("Fonts", (Object)Boolean.FALSE, "Shows all fonts."));
        this.full = (Setting<Boolean>)this.register(new Setting("Full", (Object)false));
        this.setInstance();
    }
    
    public static CustomFont getInstance() {
        if (CustomFont.INSTANCE == null) {
            CustomFont.INSTANCE = new CustomFont();
        }
        return CustomFont.INSTANCE;
    }
    
    static {
        CustomFont.INSTANCE = new CustomFont();
    }
}
