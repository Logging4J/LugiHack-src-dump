//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.manager;

import net.minecraft.launchwrapper.*;
import me.snow.luigihack.api.util.*;

public class ClassManager
{
    public ClassManager() {
        try {
            if (Launch.classLoader.findClass("cat.yoink.dumper.Main") != null || Launch.classLoader.findClass("me.crystallinqq.dumper") != null || Launch.classLoader.findClass("tech.mmmax.dumper") != null || Launch.classLoader.findClass("fuck.you.multihryack") != null) {
                throw new ReflectUtil();
            }
        }
        catch (ClassNotFoundException ex) {}
    }
}
