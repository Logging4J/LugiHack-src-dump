//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.client;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.api.util.*;

public class ModuleTools extends Module
{
    public /* synthetic */ Setting<TextUtil.Color> Abysscolorrr;
    private static /* synthetic */ ModuleTools INSTANCE;
    public /* synthetic */ Setting<Notifier> notifier;
    public /* synthetic */ Setting<PopNotifier> popNotifier;
    
    public static ModuleTools getInstance() {
        if (ModuleTools.INSTANCE == null) {
            ModuleTools.INSTANCE = new ModuleTools();
        }
        return ModuleTools.INSTANCE;
    }
    
    public ModuleTools() {
        super("ModuleTools", "Change settings", Category.CLIENT, true, false, false);
        this.notifier = (Setting<Notifier>)this.register(new Setting("ModuleNotifier", (Object)Notifier.FUTURE));
        this.popNotifier = (Setting<PopNotifier>)this.register(new Setting("PopNotifier", (Object)PopNotifier.FUTURE));
        this.Abysscolorrr = (Setting<TextUtil.Color>)this.register(new Setting("Abyss text color", (Object)TextUtil.Color.AQUA, p0 -> this.notifier.getValue() == Notifier.ABYSS));
        ModuleTools.INSTANCE = this;
    }
    
    public enum PopNotifier
    {
        FUTURE, 
        NONE, 
        DOTGOD, 
        PHOBOS;
    }
    
    public enum Notifier
    {
        NEWLUIGI, 
        ABYSS, 
        FUTURE, 
        BOLD, 
        OYVEY, 
        MUFFIN, 
        SNOW;
    }
}
