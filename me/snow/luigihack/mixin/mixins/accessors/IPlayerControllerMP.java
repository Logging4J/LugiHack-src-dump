//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.mixin.mixins.accessors;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.multiplayer.*;
import org.spongepowered.asm.mixin.gen.*;

@Mixin({ PlayerControllerMP.class })
public interface IPlayerControllerMP
{
    @Accessor("curBlockDamageMP")
    void setCurrentBlockDamage(final float p0);
    
    @Accessor("curBlockDamageMP")
    float getCurrentBlockDamage();
    
    @Accessor("blockHitDelay")
    void setBlockHitDelay(final int p0);
    
    @Accessor("currentPlayerItem")
    int getCurrentPlayerItem();
    
    @Accessor("currentPlayerItem")
    void setCurrentPlayerItem(final int p0);
    
    @Invoker("syncCurrentPlayItem")
    void hookSyncCurrentPlayItem();
}
