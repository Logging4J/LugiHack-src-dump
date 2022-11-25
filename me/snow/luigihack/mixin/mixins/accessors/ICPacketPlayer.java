//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.mixin.mixins.accessors;

import org.spongepowered.asm.mixin.*;
import net.minecraft.network.play.client.*;
import org.spongepowered.asm.mixin.gen.*;

@Mixin({ CPacketPlayer.class })
public interface ICPacketPlayer
{
    @Accessor("rotating")
    boolean isRotating();
    
    @Accessor("moving")
    boolean isMoving();
    
    @Accessor("x")
    void setX(final double p0);
    
    @Accessor("y")
    void setY(final double p0);
    
    @Accessor("z")
    void setZ(final double p0);
    
    @Accessor("yaw")
    void setYaw(final float p0);
    
    @Accessor("pitch")
    void setPitch(final float p0);
    
    @Accessor("onGround")
    void setOnGround(final boolean p0);
}
