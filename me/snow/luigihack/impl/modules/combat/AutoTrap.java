//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.combat;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.entity.player.*;
import me.snow.luigihack.*;
import net.minecraft.entity.*;
import java.util.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.item.*;
import me.snow.luigihack.api.util.*;
import me.snow.luigihack.api.util.ca.util.*;
import net.minecraft.util.*;

public class AutoTrap extends Module
{
    public /* synthetic */ Setting<swingA> swing;
    private final /* synthetic */ Vec3d[] offsetsDefault;
    public /* synthetic */ Setting<modeA> mode;
    public /* synthetic */ Setting<Boolean> rotate;
    private /* synthetic */ String lastTickTargetName;
    private /* synthetic */ boolean firstRun;
    private /* synthetic */ int yLevel;
    public /* synthetic */ Setting<Integer> blocksPerTick;
    private /* synthetic */ int offsetStep;
    private final /* synthetic */ Vec3d[] offsetsFeet;
    private final /* synthetic */ Setting<Double> range;
    private final /* synthetic */ Vec3d[] offsetsExtra;
    private final /* synthetic */ Vec3d[] offsetsFace;
    
    public EntityPlayer findClosestTarget() {
        if (AutoTrap.mc.world.playerEntities.isEmpty()) {
            return null;
        }
        Object o = null;
        for (final EntityPlayer entityPlayer : AutoTrap.mc.world.playerEntities) {
            if (entityPlayer != AutoTrap.mc.player) {
                if (!entityPlayer.isEntityAlive()) {
                    continue;
                }
                if (LuigiHack.friendManager.isFriend(entityPlayer.getName())) {
                    continue;
                }
                if (entityPlayer.getHealth() <= 0.0f) {
                    continue;
                }
                if (o != null && AutoTrap.mc.player.getDistance((Entity)entityPlayer) > AutoTrap.mc.player.getDistance((Entity)o)) {
                    continue;
                }
                o = entityPlayer;
            }
        }
        return (EntityPlayer)o;
    }
    
    public AutoTrap() {
        super("AutoTrap", "Trap awa.", Category.COMBAT, true, false, true);
        this.mode = (Setting<modeA>)this.register(new Setting("Mode", (Object)modeA.Extra));
        this.range = (Setting<Double>)this.register(new Setting("PlaceRange", (Object)6.0, (Object)0.0, (Object)10.0));
        this.blocksPerTick = (Setting<Integer>)this.register(new Setting("BlockPerTick", (Object)4, (Object)0, (Object)8));
        this.rotate = (Setting<Boolean>)this.register(new Setting("Rotate", (Object)true));
        this.swing = (Setting<swingA>)this.register(new Setting("SwingHand", (Object)swingA.Mainhand));
        this.offsetsDefault = new Vec3d[] { new Vec3d(0.0, 0.0, -1.0), new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 1.0, -1.0), new Vec3d(1.0, 1.0, 0.0), new Vec3d(0.0, 1.0, 1.0), new Vec3d(-1.0, 1.0, 0.0), new Vec3d(0.0, 2.0, -1.0), new Vec3d(1.0, 2.0, 0.0), new Vec3d(0.0, 2.0, 1.0), new Vec3d(-1.0, 2.0, 0.0), new Vec3d(0.0, 3.0, -1.0), new Vec3d(0.0, 3.0, 1.0), new Vec3d(1.0, 3.0, 0.0), new Vec3d(-1.0, 3.0, 0.0), new Vec3d(0.0, 3.0, 0.0) };
        this.offsetsFace = new Vec3d[] { new Vec3d(0.0, 0.0, -1.0), new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 1.0, -1.0), new Vec3d(1.0, 1.0, 0.0), new Vec3d(0.0, 1.0, 1.0), new Vec3d(-1.0, 1.0, 0.0), new Vec3d(0.0, 2.0, -1.0), new Vec3d(0.0, 3.0, -1.0), new Vec3d(0.0, 3.0, 1.0), new Vec3d(1.0, 3.0, 0.0), new Vec3d(-1.0, 3.0, 0.0), new Vec3d(0.0, 3.0, 0.0) };
        this.offsetsFeet = new Vec3d[] { new Vec3d(0.0, 0.0, -1.0), new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 1.0, -1.0), new Vec3d(0.0, 2.0, -1.0), new Vec3d(1.0, 2.0, 0.0), new Vec3d(0.0, 2.0, 1.0), new Vec3d(-1.0, 2.0, 0.0), new Vec3d(0.0, 3.0, -1.0), new Vec3d(0.0, 3.0, 0.0) };
        this.offsetsExtra = new Vec3d[] { new Vec3d(0.0, 0.0, -1.0), new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 1.0, -1.0), new Vec3d(1.0, 1.0, 0.0), new Vec3d(0.0, 1.0, 1.0), new Vec3d(-1.0, 1.0, 0.0), new Vec3d(0.0, 2.0, -1.0), new Vec3d(1.0, 2.0, 0.0), new Vec3d(0.0, 2.0, 1.0), new Vec3d(-1.0, 2.0, 0.0), new Vec3d(0.0, 3.0, -1.0), new Vec3d(0.0, 3.0, 0.0), new Vec3d(0.0, 4.0, 0.0) };
        this.offsetStep = 0;
        this.lastTickTargetName = "";
        this.firstRun = true;
    }
    
    @Override
    public void onEnable() {
        this.yLevel = (int)Math.round(AutoTrap.mc.player.posY);
        this.firstRun = true;
        if (PlayerUtilCa.findObiInHotbar() == -1) {
            this.disable();
        }
        if (PlayerUtilCa.findSandInHotbar() == -1 && this.mode.getValue() == modeA.Sand) {
            this.disable();
        }
    }
    
    @Override
    public void onUpdate() {
        final EntityPlayer closestTarget = this.findClosestTarget();
        if (closestTarget == null) {
            this.disable();
            return;
        }
        if ((int)Math.round(AutoTrap.mc.player.posY) != this.yLevel) {
            this.disable();
            return;
        }
        if (this.firstRun) {
            this.firstRun = false;
            this.lastTickTargetName = closestTarget.getName();
        }
        else if (!this.lastTickTargetName.equals(closestTarget.getName())) {
            this.lastTickTargetName = closestTarget.getName();
            this.offsetStep = 0;
        }
        final ArrayList<Object> list = new ArrayList<Object>();
        if (this.mode.getValue() == modeA.Normal || this.mode.getValue() == modeA.Sand) {
            Collections.addAll(list, this.offsetsDefault);
        }
        else if (this.mode.getValue() == modeA.Extra) {
            Collections.addAll(list, this.offsetsExtra);
        }
        else if (this.mode.getValue() == modeA.Feet) {
            Collections.addAll(list, this.offsetsFeet);
        }
        else {
            Collections.addAll(list, this.offsetsFace);
        }
        int i = 0;
        while (i < (int)this.blocksPerTick.getValue()) {
            boolean b = false;
            if (this.offsetStep >= list.size()) {
                this.offsetStep = 0;
                break;
            }
            if (this.mode.getValue() == modeA.Sand && this.offsetStep == 16) {
                b = true;
            }
            final BlockPos blockPos = new BlockPos((Vec3d)list.get(this.offsetStep));
            final BlockPos add = new BlockPos(closestTarget.getPositionVector()).down().add(blockPos.getX(), blockPos.getY(), blockPos.getZ());
            boolean b2 = true;
            if (!AutoTrap.mc.world.getBlockState(add).getMaterial().isReplaceable()) {
                b2 = false;
            }
            for (final Entity entity : AutoTrap.mc.world.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(add))) {
                if (!(entity instanceof EntityItem) && !(entity instanceof EntityXPOrb)) {
                    b2 = false;
                    break;
                }
            }
            if (AutoTrap.mc.player.getDistanceSq(add) <= MathUtil.square((double)this.range.getValue()) && b2 && BlockUtilCa.placeBlock(add, b ? PlayerUtilCa.findSandInHotbar() : PlayerUtilCa.findObiInHotbar(), (boolean)this.rotate.getValue(), (boolean)this.rotate.getValue())) {
                if (this.swing.getValue() == swingA.Mainhand) {
                    AutoTrap.mc.player.swingArm(EnumHand.MAIN_HAND);
                }
                if (this.swing.getValue() == swingA.Offhand) {
                    AutoTrap.mc.player.swingArm(EnumHand.OFF_HAND);
                }
                ++i;
            }
            ++this.offsetStep;
        }
    }
    
    public enum modeA
    {
        Sand, 
        Normal, 
        Feet, 
        Face, 
        Extra;
    }
    
    public enum swingA
    {
        Mainhand, 
        None, 
        Offhand;
    }
}
