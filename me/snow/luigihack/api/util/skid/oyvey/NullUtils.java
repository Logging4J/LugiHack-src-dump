//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util.skid.oyvey;

import me.snow.luigihack.api.util.*;

public class NullUtils
{
    public static boolean fullNullCheck() {
        return Util.mc.player == null || Util.mc.world == null;
    }
    
    public static boolean nullCheck() {
        return Util.mc.player == null || Util.mc.world == null || Util.mc.playerController == null;
    }
}
