//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.render;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import java.awt.*;
import me.snow.luigihack.api.util.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraft.entity.*;
import java.util.*;

public class SurroundRender extends Module
{
    private final /* synthetic */ Setting<Integer> range;
    public /* synthetic */ EntityPlayer target;
    
    private void surroundRender(final Vec3d vec3d, final double n, final double n2, final double n3, final boolean b) {
        final BlockPos add = new BlockPos(vec3d).add(n, n2, n3);
        if (SurroundRender.mc.world.getBlockState(add).getBlock() == Blocks.AIR) {
            return;
        }
        if (SurroundRender.mc.world.getBlockState(add).getBlock() == Blocks.FIRE) {
            return;
        }
        if (b) {
            RenderUtil.drawBoxESP(add, new Color(255, 0, 0), false, new Color(255, 0, 0), 1.0f, false, true, 42, true);
            return;
        }
        RenderUtil.drawBoxESP(add, new Color(0, 0, 255), false, new Color(0, 0, 255), 1.0f, false, true, 42, true);
    }
    
    private void surroundRender() {
        if (this.target == null) {
            return;
        }
        final Vec3d getPositionVector = this.target.getPositionVector();
        if (SurroundRender.mc.world.getBlockState(new BlockPos(getPositionVector)).getBlock() == Blocks.OBSIDIAN || SurroundRender.mc.world.getBlockState(new BlockPos(getPositionVector)).getBlock() == Blocks.ENDER_CHEST) {
            RenderUtil.drawBoxESP(new BlockPos(getPositionVector), new Color(255, 255, 0), false, new Color(255, 255, 0), 1.0f, false, true, 42, true);
        }
        if (EntityUtil.getSurroundWeakness(getPositionVector, -1, 1)) {
            this.surroundRender(getPositionVector, -1.0, 0.0, 0.0, true);
        }
        if (EntityUtil.getSurroundWeakness(getPositionVector, -1, 2)) {
            this.surroundRender(getPositionVector, 1.0, 0.0, 0.0, true);
        }
        if (EntityUtil.getSurroundWeakness(getPositionVector, -1, 3)) {
            this.surroundRender(getPositionVector, 0.0, 0.0, -1.0, true);
        }
        if (EntityUtil.getSurroundWeakness(getPositionVector, -1, 4)) {
            this.surroundRender(getPositionVector, 0.0, 0.0, 1.0, true);
        }
        if (EntityUtil.getSurroundWeakness(getPositionVector, -1, 5)) {
            this.surroundRender(getPositionVector, -1.0, 0.0, 0.0, false);
        }
        if (EntityUtil.getSurroundWeakness(getPositionVector, -1, 6)) {
            this.surroundRender(getPositionVector, 1.0, 0.0, 0.0, false);
        }
        if (EntityUtil.getSurroundWeakness(getPositionVector, -1, 7)) {
            this.surroundRender(getPositionVector, 0.0, 0.0, -1.0, false);
        }
        if (!EntityUtil.getSurroundWeakness(getPositionVector, -1, 8)) {
            return;
        }
        this.surroundRender(getPositionVector, 0.0, 0.0, 1.0, false);
    }
    
    public SurroundRender() {
        super("CityESP", "CityESP", Module.Category.RENDER, true, false, false);
        this.range = (Setting<Integer>)this.register(new Setting("Range", (Object)5, (Object)1, (Object)10));
    }
    
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (fullNullCheck()) {
            return;
        }
        this.target = this.getTarget((int)this.range.getValue());
        this.surroundRender();
    }
    
    private EntityPlayer getTarget(final double n) {
        EntityPlayer entityPlayer = null;
        double n2 = n;
        for (final EntityPlayer entityPlayer2 : SurroundRender.mc.world.playerEntities) {
            if (!EntityUtil.isntValid((Entity)entityPlayer2, n)) {
                if (!EntityUtil.isInHole((Entity)entityPlayer2)) {
                    continue;
                }
                if (entityPlayer == null) {
                    entityPlayer = entityPlayer2;
                    n2 = SurroundRender.mc.player.getDistanceSq((Entity)entityPlayer2);
                }
                else {
                    if (SurroundRender.mc.player.getDistanceSq((Entity)entityPlayer2) >= n2) {
                        continue;
                    }
                    entityPlayer = entityPlayer2;
                    n2 = SurroundRender.mc.player.getDistanceSq((Entity)entityPlayer2);
                }
            }
        }
        return entityPlayer;
    }
}
