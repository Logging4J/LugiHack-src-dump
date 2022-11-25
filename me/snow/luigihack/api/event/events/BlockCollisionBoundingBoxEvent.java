//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.event.events;

import me.snow.luigihack.api.event.*;
import net.minecraft.util.math.*;

public class BlockCollisionBoundingBoxEvent extends EventStage
{
    private /* synthetic */ BlockPos pos;
    private /* synthetic */ AxisAlignedBB _boundingBox;
    
    public AxisAlignedBB getBoundingBox() {
        return this._boundingBox;
    }
    
    public void setBoundingBox(final AxisAlignedBB boundingBox) {
        this._boundingBox = boundingBox;
    }
    
    public BlockCollisionBoundingBoxEvent(final BlockPos pos) {
        this.pos = pos;
    }
    
    public BlockPos getPos() {
        return this.pos;
    }
}
