//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util.ca.event;

import me.snow.luigihack.api.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.entity.*;

@Cancelable
public class MoveEventCa extends EventStage
{
    private /* synthetic */ double x;
    private /* synthetic */ double z;
    private /* synthetic */ MoverType type;
    private /* synthetic */ double y;
    
    public double getY() {
        return this.y;
    }
    
    public void setZ(final double z) {
        this.z = z;
    }
    
    public void setX(final double x) {
        this.x = x;
    }
    
    public MoverType getType() {
        return this.type;
    }
    
    public void setType(final MoverType type) {
        this.type = type;
    }
    
    public void setY(final double y) {
        this.y = y;
    }
    
    public MoveEventCa(final int n, final MoverType type, final double x, final double y, final double z) {
        super(n);
        this.type = type;
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public double getZ() {
        return this.z;
    }
    
    public double getX() {
        return this.x;
    }
}
