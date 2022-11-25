//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util;

import net.minecraft.client.*;
import org.lwjgl.input.*;
import net.minecraft.world.*;
import net.minecraft.client.entity.*;

public class Wrapper
{
    public static int getKey(final String s) {
        return Keyboard.getKeyIndex(s.toUpperCase());
    }
    
    static {
        mc = Minecraft.getMinecraft();
    }
    
    public static Minecraft getMinecraft() {
        return Minecraft.getMinecraft();
    }
    
    public static World getWorld() {
        return (World)getMinecraft().world;
    }
    
    public static EntityPlayerSP getPlayer() {
        return getMinecraft().player;
    }
}
