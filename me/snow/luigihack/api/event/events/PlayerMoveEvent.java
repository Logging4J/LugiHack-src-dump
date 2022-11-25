//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.event.events;

import me.snow.luigihack.api.event.*;
import net.minecraft.entity.*;

public class PlayerMoveEvent extends EventStage
{
    private /* synthetic */ double x;
    private static /* synthetic */ PlayerMoveEvent INSTANCE;
    private /* synthetic */ double y;
    private /* synthetic */ MoverType type;
    private /* synthetic */ double z;
    
    static {
        PlayerMoveEvent.INSTANCE = new PlayerMoveEvent();
    }
    
    public void setType(final MoverType type) {
        this.type = type;
    }
    
    public double getY() {
        return this.y;
    }
    
    public MoverType getType() {
        return this.type;
    }
    
    public double getX() {
        return this.x;
    }
    
    public double getZ() {
        return this.z;
    }
    
    public static PlayerMoveEvent get(final MoverType type, final double x, final double y, final double z) {
        PlayerMoveEvent.INSTANCE.type = type;
        PlayerMoveEvent.INSTANCE.x = x;
        PlayerMoveEvent.INSTANCE.y = y;
        PlayerMoveEvent.INSTANCE.z = z;
        return PlayerMoveEvent.INSTANCE;
    }
    
    public void setY(final double y) {
        this.y = y;
    }
    
    public void setX(final double x) {
        this.x = x;
    }
    
    public void setZ(final double z) {
        this.z = z;
    }
}
