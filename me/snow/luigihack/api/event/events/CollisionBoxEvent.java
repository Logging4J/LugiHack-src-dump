//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.event.events;

import me.snow.luigihack.api.event.*;
import net.minecraft.world.*;
import java.util.*;
import net.minecraft.util.math.*;
import net.minecraft.block.*;
import net.minecraft.block.state.*;
import net.minecraft.entity.*;

public class CollisionBoxEvent extends EventStage
{
    private /* synthetic */ World world;
    private /* synthetic */ BlockPos pos;
    private /* synthetic */ List<AxisAlignedBB> collidingBoxes;
    private /* synthetic */ AxisAlignedBB entityBox;
    private /* synthetic */ boolean isActualState;
    private /* synthetic */ Block block;
    private static /* synthetic */ CollisionBoxEvent INSTANCE;
    private /* synthetic */ IBlockState state;
    private /* synthetic */ Entity entity;
    
    static {
        CollisionBoxEvent.INSTANCE = new CollisionBoxEvent();
    }
    
    public Block getBlock() {
        return this.block;
    }
    
    public void setEntityBox(final AxisAlignedBB entityBox) {
        this.entityBox = entityBox;
    }
    
    public AxisAlignedBB getEntityBox() {
        return this.entityBox;
    }
    
    public boolean isActualState() {
        return this.isActualState;
    }
    
    public World getWorld() {
        return this.world;
    }
    
    public BlockPos getPos() {
        return this.pos;
    }
    
    public List<AxisAlignedBB> getCollidingBoxes() {
        return this.collidingBoxes;
    }
    
    public Entity getEntity() {
        return this.entity;
    }
    
    public IBlockState getState() {
        return this.state;
    }
    
    public static CollisionBoxEvent get(final Block block, final IBlockState state, final World world, final BlockPos pos, final AxisAlignedBB entityBox, final List<AxisAlignedBB> collidingBoxes, final Entity entity, final boolean isActualState) {
        CollisionBoxEvent.INSTANCE.setCanceled(false);
        CollisionBoxEvent.INSTANCE.block = block;
        CollisionBoxEvent.INSTANCE.state = state;
        CollisionBoxEvent.INSTANCE.world = world;
        CollisionBoxEvent.INSTANCE.pos = pos;
        CollisionBoxEvent.INSTANCE.entityBox = entityBox;
        CollisionBoxEvent.INSTANCE.collidingBoxes = collidingBoxes;
        CollisionBoxEvent.INSTANCE.entity = entity;
        CollisionBoxEvent.INSTANCE.isActualState = isActualState;
        return CollisionBoxEvent.INSTANCE;
    }
}
