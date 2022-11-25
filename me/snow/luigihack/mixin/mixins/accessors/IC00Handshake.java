//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.mixin.mixins.accessors;

import org.spongepowered.asm.mixin.*;
import net.minecraft.network.handshake.client.*;
import org.spongepowered.asm.mixin.gen.*;

@Mixin({ C00Handshake.class })
public interface IC00Handshake
{
    @Accessor("ip")
    String getIp();
    
    @Accessor("ip")
    void setIp(final String p0);
}
