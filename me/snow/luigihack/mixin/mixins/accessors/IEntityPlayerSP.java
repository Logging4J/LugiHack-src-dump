//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.mixin.mixins.accessors;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.entity.*;
import org.spongepowered.asm.mixin.gen.*;

@Mixin({ EntityPlayerSP.class })
public interface IEntityPlayerSP
{
    @Accessor("serverSprintState")
    boolean getServerSprintState();
    
    @Accessor("serverSneakState")
    boolean getServerSneakState();
    
    @Accessor("positionUpdateTicks")
    int getPositionUpdateTicks();
    
    @Accessor("lastReportedPosX")
    double getLastReportedPosX();
    
    @Accessor("lastReportedPosY")
    double getLastReportedPosY();
    
    @Accessor("lastReportedPosZ")
    double getLastReportedPosZ();
    
    @Accessor("lastReportedYaw")
    float getLastReportedYaw();
    
    @Accessor("lastReportedPitch")
    float getLastReportedPitch();
    
    @Accessor("prevOnGround")
    boolean getPreviousOnGround();
    
    @Accessor("serverSprintState")
    void setServerSprintState(final boolean p0);
    
    @Accessor("serverSneakState")
    void setServerSneakState(final boolean p0);
    
    @Accessor("positionUpdateTicks")
    void setPositionUpdateTicks(final int p0);
    
    @Accessor("lastReportedPosX")
    void setLastReportedPosX(final double p0);
    
    @Accessor("lastReportedPosY")
    void setLastReportedPosY(final double p0);
    
    @Accessor("lastReportedPosZ")
    void setLastReportedPosZ(final double p0);
    
    @Accessor("lastReportedYaw")
    void setLastReportedYaw(final float p0);
    
    @Accessor("lastReportedPitch")
    void setLastReportedPitch(final float p0);
    
    @Accessor("prevOnGround")
    void setPreviousOnGround(final boolean p0);
}
