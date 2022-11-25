//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.event.events;

import me.snow.luigihack.api.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;

@Cancelable
public class BlockEvent extends EventStage
{
    public /* synthetic */ EnumFacing facing;
    public /* synthetic */ BlockPos pos;
    
    public BlockEvent(final int n, final BlockPos pos, final EnumFacing facing) {
        super(n);
        this.pos = pos;
        this.facing = facing;
    }
}
