//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.player;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.math.*;
import me.snow.luigihack.*;
import me.snow.luigihack.impl.modules.combat.*;
import net.minecraft.init.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.entity.*;
import java.util.*;

public class Automine extends Module
{
    private final /* synthetic */ Setting<Boolean> disable;
    public /* synthetic */ EntityPlayer target;
    public /* synthetic */ Setting<Float> TargetRRRRange;
    
    public Automine() {
        super("AutoMine", "AutoMine awa", Module.Category.COMBAT, false, false, false);
        this.disable = (Setting<Boolean>)this.register(new Setting("AutoDisable", (Object)true));
        this.TargetRRRRange = (Setting<Float>)this.register(new Setting("TargetRange", (Object)6.0f, (Object)3.0f, (Object)8.0f));
    }
    
    private void surroundMine(final Vec3d vec3d, final double n, final double n2, final double n3) {
        final BlockPos add = new BlockPos(vec3d).add(n, n2, n3);
        if (InstantMine.getInstance().isOff()) {
            InstantMine.getInstance().enable();
            return;
        }
        if (!InstantMine.getInstance().isOn()) {
            return;
        }
        if (InstantMine.breakPos != null) {
            if (InstantMine.breakPos.equals((Object)add)) {
                return;
            }
            if (InstantMine.breakPos.equals((Object)new BlockPos(this.target.posX, this.target.posY, this.target.posZ)) && Automine.mc.world.getBlockState(new BlockPos(this.target.posX, this.target.posY, this.target.posZ)).getBlock() != Blocks.AIR) {
                return;
            }
        }
        Automine.mc.playerController.onPlayerDamageBlock(add, BlockUtil.getRayTraceFacing(add));
    }
    
    public String getDisplayInfo() {
        return "Combat";
    }
    
    public void onTick() {
        if (fullNullCheck()) {
            return;
        }
        if (Objects.requireNonNull((CevBreaker)LuigiHack.moduleManager.getModuleByClass((Class)CevBreaker.class)).isEnabled()) {
            return;
        }
        if (Objects.requireNonNull(((HeadCrystal)LuigiHack.moduleManager.getModuleByClass((Class)HeadCrystal.class)).isEnabled())) {
            return;
        }
        if (this.disable.getValue()) {
            this.disable();
        }
        if (InventoryUtil.getItemHotbar(Items.DIAMOND_PICKAXE) == -1) {
            return;
        }
        this.target = this.getTarget((float)this.TargetRRRRange.getValue());
        this.surroundMine();
    }
    
    private EntityPlayer getTarget(final double n) {
        EntityPlayer entityPlayer = null;
        double n2 = n;
        for (final EntityPlayer entityPlayer2 : Automine.mc.world.playerEntities) {
            if (!EntityUtil.isntValid((Entity)entityPlayer2, n)) {
                if (!EntityUtil.isInHole((Entity)entityPlayer2) && !LuigiHack.friendManager.isFriend(entityPlayer2.getName())) {
                    continue;
                }
                if (entityPlayer == null) {
                    entityPlayer = entityPlayer2;
                    n2 = Automine.mc.player.getDistanceSq((Entity)entityPlayer2);
                }
                else {
                    if (Automine.mc.player.getDistanceSq((Entity)entityPlayer2) >= n2) {
                        continue;
                    }
                    entityPlayer = entityPlayer2;
                    n2 = Automine.mc.player.getDistanceSq((Entity)entityPlayer2);
                }
            }
        }
        return entityPlayer;
    }
    
    private void surroundMine() {
        if (this.target == null) {
            return;
        }
        final Vec3d getPositionVector = this.target.getPositionVector();
        if (EntityUtil.getSurroundWeakness(getPositionVector, 1, -1)) {
            this.surroundMine(getPositionVector, -1.0, 0.0, 0.0);
            return;
        }
        if (EntityUtil.getSurroundWeakness(getPositionVector, 2, -1)) {
            this.surroundMine(getPositionVector, 1.0, 0.0, 0.0);
            return;
        }
        if (EntityUtil.getSurroundWeakness(getPositionVector, 3, -1)) {
            this.surroundMine(getPositionVector, 0.0, 0.0, -1.0);
            return;
        }
        if (EntityUtil.getSurroundWeakness(getPositionVector, 4, -1)) {
            this.surroundMine(getPositionVector, 0.0, 0.0, 1.0);
            return;
        }
        if (EntityUtil.getSurroundWeakness(getPositionVector, 5, -1)) {
            this.surroundMine(getPositionVector, -1.0, 0.0, 0.0);
            return;
        }
        if (EntityUtil.getSurroundWeakness(getPositionVector, 6, -1)) {
            this.surroundMine(getPositionVector, 1.0, 0.0, 0.0);
            return;
        }
        if (EntityUtil.getSurroundWeakness(getPositionVector, 7, -1)) {
            this.surroundMine(getPositionVector, 0.0, 0.0, -1.0);
            return;
        }
        if (!EntityUtil.getSurroundWeakness(getPositionVector, 8, -1)) {
            return;
        }
        this.surroundMine(getPositionVector, 0.0, 0.0, 1.0);
    }
}
