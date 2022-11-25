//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.mixin.mixins.accessors;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.multiplayer.*;
import org.spongepowered.asm.mixin.gen.*;

@Mixin({ ServerAddress.class })
public interface IServerAddress
{
    @Invoker("getServerAddress")
    default String[] getServerAddress(final String s) {
        throw new IllegalStateException("Mixin didn't transform this");
    }
}
