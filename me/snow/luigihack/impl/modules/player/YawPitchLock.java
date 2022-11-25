//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.player;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.entity.*;
import java.util.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class YawPitchLock extends Module
{
    public /* synthetic */ Setting<Boolean> lockPitch;
    public /* synthetic */ Setting<Boolean> byDirection;
    public /* synthetic */ Setting<Integer> pitch;
    public /* synthetic */ Setting<Boolean> lockYaw;
    public /* synthetic */ Setting<Integer> yaw;
    public /* synthetic */ Setting<Direction> direction;
    
    private void setYaw(final int n) {
        if (YawPitchLock.mc.player.isRiding()) {
            Objects.requireNonNull(YawPitchLock.mc.player.getRidingEntity()).rotationYaw = (float)n;
        }
        YawPitchLock.mc.player.rotationYaw = (float)n;
    }
    
    @SubscribeEvent
    public void onUpdateWalkingPlayer(final UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (this.lockYaw.getValue()) {
            if (this.byDirection.getValue()) {
                switch ((Direction)this.direction.getValue()) {
                    case NORTH: {
                        this.setYaw(180);
                        break;
                    }
                    case NE: {
                        this.setYaw(225);
                        break;
                    }
                    case EAST: {
                        this.setYaw(270);
                        break;
                    }
                    case SE: {
                        this.setYaw(315);
                        break;
                    }
                    case SOUTH: {
                        this.setYaw(0);
                        break;
                    }
                    case SW: {
                        this.setYaw(45);
                        break;
                    }
                    case WEST: {
                        this.setYaw(90);
                        break;
                    }
                    case NW: {
                        this.setYaw(135);
                        break;
                    }
                }
            }
            else {
                this.setYaw((int)this.yaw.getValue());
            }
        }
        if (this.lockPitch.getValue()) {
            if (YawPitchLock.mc.player.isRiding()) {
                Objects.requireNonNull(YawPitchLock.mc.player.getRidingEntity()).rotationPitch = (float)(int)this.pitch.getValue();
            }
            YawPitchLock.mc.player.rotationPitch = (float)(int)this.pitch.getValue();
        }
    }
    
    public YawPitchLock() {
        super("YawLock", "Locks your yaw", Module.Category.PLAYER, true, false, false);
        this.lockYaw = (Setting<Boolean>)this.register(new Setting("LockYaw", (Object)false));
        this.byDirection = (Setting<Boolean>)this.register(new Setting("ByDirection", (Object)false));
        this.direction = (Setting<Direction>)this.register(new Setting("Direction", (Object)Direction.NORTH, p0 -> (boolean)this.byDirection.getValue()));
        this.yaw = (Setting<Integer>)this.register(new Setting("Yaw", (Object)0, (Object)(-180), (Object)180, p0 -> !(boolean)this.byDirection.getValue()));
        this.lockPitch = (Setting<Boolean>)this.register(new Setting("LockPitch", (Object)false));
        this.pitch = (Setting<Integer>)this.register(new Setting("Pitch", (Object)0, (Object)(-180), (Object)180));
    }
    
    public enum Direction
    {
        SW, 
        EAST, 
        NW, 
        NORTH, 
        SOUTH, 
        SE, 
        NE, 
        WEST;
    }
}
