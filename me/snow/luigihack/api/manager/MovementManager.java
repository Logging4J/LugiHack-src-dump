//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.manager;

import me.snow.luigihack.impl.*;

public class MovementManager extends Feature
{
    public void setMotion(final double motionX, final double n, final double motionZ) {
        if (MovementManager.mc.player != null) {
            if (MovementManager.mc.player.isRiding()) {
                MovementManager.mc.player.ridingEntity.motionX = motionX;
                MovementManager.mc.player.ridingEntity.motionY = n;
                MovementManager.mc.player.ridingEntity.motionZ = motionX;
            }
            else {
                MovementManager.mc.player.motionX = motionX;
                MovementManager.mc.player.motionY = n;
                MovementManager.mc.player.motionZ = motionZ;
            }
        }
    }
    
    public void doStep(final float stepHeight, final boolean b) {
        if (b && (!MovementManager.mc.player.collidedVertically || MovementManager.mc.player.fallDistance > 0.1 || MovementManager.mc.player.isOnLadder() || !MovementManager.mc.player.onGround)) {
            return;
        }
        MovementManager.mc.player.stepHeight = stepHeight;
    }
}
