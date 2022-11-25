//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.event.events;

import me.snow.luigihack.api.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.util.math.*;

@Cancelable
public class JesusEvent extends EventStage
{
    private /* synthetic */ BlockPos pos;
    private /* synthetic */ AxisAlignedBB boundingBox;
    
    public BlockPos getPos() {
        return this.pos;
    }
    
    public AxisAlignedBB getBoundingBox() {
        return this.boundingBox;
    }
    
    public void setPos(final BlockPos pos) {
        this.pos = pos;
    }
    
    public JesusEvent(final int n, final BlockPos pos) {
        super(n);
        this.pos = pos;
    }
    
    public void setBoundingBox(final AxisAlignedBB boundingBox) {
        this.boundingBox = boundingBox;
    }
}
