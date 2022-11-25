//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.manager;

import me.snow.luigihack.impl.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.*;
import net.minecraft.entity.*;
import java.util.*;
import net.minecraft.util.math.*;

public class SpeedManager extends Feature
{
    public /* synthetic */ boolean wasFirstJump;
    public static /* synthetic */ boolean didJumpThisTick;
    public /* synthetic */ double firstJumpSpeed;
    public /* synthetic */ double jumpSpeedChanged;
    public static /* synthetic */ boolean isJumping;
    public /* synthetic */ HashMap<EntityPlayer, Double> playerSpeeds;
    public /* synthetic */ boolean didJumpLastTick;
    public /* synthetic */ long jumpInfoStartTime;
    public /* synthetic */ double lastJumpSpeed;
    public /* synthetic */ double speedometerCurrentSpeed;
    public /* synthetic */ double percentJumpSpeedChanged;
    
    public SpeedManager() {
        this.wasFirstJump = true;
        this.playerSpeeds = new HashMap<EntityPlayer, Double>();
    }
    
    public double getSpeedMpS() {
        return Math.round(10.0 * (this.turnIntoKpH(this.speedometerCurrentSpeed) / 3.6)) / 10.0;
    }
    
    public static void setDidJumpThisTick(final boolean didJumpThisTick) {
        SpeedManager.didJumpThisTick = didJumpThisTick;
    }
    
    public float lastJumpInfoTimeRemaining() {
        return (Minecraft.getSystemTime() - this.jumpInfoStartTime) / 1000.0f;
    }
    
    public void updatePlayers() {
        for (final EntityPlayer key : SpeedManager.mc.world.playerEntities) {
            final int n = 20;
            if (SpeedManager.mc.player.getDistanceSq((Entity)key) >= n * n) {
                continue;
            }
            final double n2 = key.posX - key.prevPosX;
            final double n3 = key.posZ - key.prevPosZ;
            this.playerSpeeds.put(key, n2 * n2 + n3 * n3);
        }
    }
    
    public double getPlayerSpeed(final EntityPlayer entityPlayer) {
        if (this.playerSpeeds.get(entityPlayer) == null) {
            return 0.0;
        }
        return this.turnIntoKpH(this.playerSpeeds.get(entityPlayer));
    }
    
    public void updateValues() {
        final double n = SpeedManager.mc.player.posX - SpeedManager.mc.player.prevPosX;
        final double n2 = SpeedManager.mc.player.posZ - SpeedManager.mc.player.prevPosZ;
        this.speedometerCurrentSpeed = n * n + n2 * n2;
        if (SpeedManager.didJumpThisTick && (!SpeedManager.mc.player.onGround || SpeedManager.isJumping)) {
            if (!this.didJumpLastTick) {
                this.wasFirstJump = (this.lastJumpSpeed == 0.0);
                this.percentJumpSpeedChanged = ((this.speedometerCurrentSpeed != 0.0) ? (this.speedometerCurrentSpeed / this.lastJumpSpeed - 1.0) : -1.0);
                this.jumpSpeedChanged = this.speedometerCurrentSpeed - this.lastJumpSpeed;
                this.jumpInfoStartTime = Minecraft.getSystemTime();
                this.lastJumpSpeed = this.speedometerCurrentSpeed;
                this.firstJumpSpeed = (this.wasFirstJump ? this.lastJumpSpeed : 0.0);
            }
            this.didJumpLastTick = SpeedManager.didJumpThisTick;
        }
        else {
            this.didJumpLastTick = false;
            this.lastJumpSpeed = 0.0;
        }
        this.updatePlayers();
    }
    
    public double getSpeedKpH() {
        return Math.round(10.0 * this.turnIntoKpH(this.speedometerCurrentSpeed)) / 10.0;
    }
    
    public static void setIsJumping(final boolean isJumping) {
        SpeedManager.isJumping = isJumping;
    }
    
    public double turnIntoKpH(final double n) {
        return MathHelper.sqrt(n) * 71.2729367892;
    }
}
