//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.player;

import me.snow.luigihack.impl.modules.*;
import org.jetbrains.annotations.*;
import kotlin.jvm.internal.*;
import me.snow.luigihack.impl.command.*;
import kotlin.jvm.*;

public class NoPacketKick extends Module
{
    private static /* synthetic */ NoPacketKick INSTANCE;
    
    public NoPacketKick() {
        super("NoPacketKick", "Suppress network exceptions and prevent getting kicked", Module.Category.PLAYER, true, false, false);
    }
    
    @JvmStatic
    public static final void sendWarning(@NotNull final Throwable obj) {
        Intrinsics.checkNotNullParameter((Object)obj, "throwable");
        Command.sendMessage(String.valueOf(new StringBuilder().append(" Caught exception - \"").append(obj).append("\" check log for more info.")));
        obj.printStackTrace();
    }
    
    public static NoPacketKick getInstance() {
        if (NoPacketKick.INSTANCE == null) {
            NoPacketKick.INSTANCE = new NoPacketKick();
        }
        return NoPacketKick.INSTANCE;
    }
}
