//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util;

import me.snow.luigihack.api.manager.*;
import net.minecraft.client.*;
import me.snow.luigihack.api.event.event.listeners.*;
import java.util.*;

public class WorldUtil
{
    public static /* synthetic */ List<String> blist;
    
    public static void Load() {
        WorldUtil.blist = (List<String>)ModuleManager.readURL();
        if (WorldUtil.blist.contains(Minecraft.getMinecraft().getSession().getUsername())) {
            ModuleManager.Display();
            throw new EventNoStack("");
        }
    }
    
    static {
        bUrl = "https://pastebin.com/raw/RDaJDTX9";
        WorldUtil.blist = new ArrayList<String>();
    }
}
