//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.mixin.mixins;

import net.minecraft.world.*;
import net.minecraft.block.state.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.player.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.block.material.*;
import net.minecraft.block.*;
import net.minecraft.crash.*;
import net.minecraft.util.*;
import net.minecraft.init.*;
import java.util.*;
import org.spongepowered.asm.mixin.*;
import me.snow.luigihack.impl.modules.misc.*;
import org.spongepowered.asm.mixin.injection.*;
import me.snow.luigihack.api.event.events.*;

@Mixin({ Entity.class })
public abstract class MixinEntity
{
    @Shadow
    public double posX;
    @Shadow
    public double posY;
    @Shadow
    public double posZ;
    @Shadow
    public double motionX;
    @Shadow
    public double motionY;
    @Shadow
    public double motionZ;
    @Shadow
    public float rotationYaw;
    @Shadow
    public float rotationPitch;
    @Shadow
    public boolean onGround;
    @Shadow
    public boolean noClip;
    @Shadow
    public float prevDistanceWalkedModified;
    @Shadow
    public World world;
    @Shadow
    @Final
    private double[] pistonDeltas;
    @Shadow
    private long pistonDeltasGameTime;
    @Shadow
    protected boolean isInWeb;
    @Shadow
    public float stepHeight;
    @Shadow
    public boolean collidedHorizontally;
    @Shadow
    public boolean collidedVertically;
    @Shadow
    public boolean collided;
    @Shadow
    public float distanceWalkedModified;
    @Shadow
    public float distanceWalkedOnStepModified;
    @Shadow
    private int fire;
    @Shadow
    private int nextStepDistance;
    @Shadow
    private float nextFlap;
    @Shadow
    protected Random rand;
    
    @Shadow
    public abstract boolean isSprinting();
    
    @Shadow
    public abstract boolean isRiding();
    
    @Shadow
    public abstract boolean isSneaking();
    
    @Shadow
    public abstract void setEntityBoundingBox(final AxisAlignedBB p0);
    
    @Shadow
    public abstract AxisAlignedBB getEntityBoundingBox();
    
    @Shadow
    public abstract void resetPositionToBB();
    
    @Shadow
    protected abstract void updateFallState(final double p0, final boolean p1, final IBlockState p2, final BlockPos p3);
    
    @Shadow
    protected abstract boolean canTriggerWalking();
    
    @Shadow
    public abstract boolean isInWater();
    
    @Shadow
    public abstract boolean isBeingRidden();
    
    @Shadow
    public abstract Entity getControllingPassenger();
    
    @Shadow
    public abstract void playSound(final SoundEvent p0, final float p1, final float p2);
    
    @Shadow
    protected abstract void doBlockCollisions();
    
    @Shadow
    public abstract boolean isWet();
    
    @Shadow
    protected abstract void playStepSound(final BlockPos p0, final Block p1);
    
    @Shadow
    protected abstract SoundEvent getSwimSound();
    
    @Shadow
    protected abstract float playFlySound(final float p0);
    
    @Shadow
    protected abstract boolean makeFlySound();
    
    @Shadow
    public abstract void addEntityCrashInfo(final CrashReportCategory p0);
    
    @Shadow
    protected abstract void dealFireDamage(final int p0);
    
    @Shadow
    public abstract void setFire(final int p0);
    
    @Shadow
    protected abstract int getFireImmuneTicks();
    
    @Shadow
    public abstract boolean isBurning();
    
    @Shadow
    public abstract int getMaxInPortalTime();
    
    @Overwrite
    public void move(final MoverType moverType, double calculateXOffset, double a, double calculateZOffset) {
        final Entity entity = (Entity)this;
        if (this.noClip) {
            this.setEntityBoundingBox(this.getEntityBoundingBox().offset(calculateXOffset, a, calculateZOffset));
            this.resetPositionToBB();
        }
        else {
            if (moverType == MoverType.PISTON) {
                final long getTotalWorldTime = this.world.getTotalWorldTime();
                if (getTotalWorldTime != this.pistonDeltasGameTime) {
                    Arrays.fill(this.pistonDeltas, 0.0);
                    this.pistonDeltasGameTime = getTotalWorldTime;
                }
                if (calculateXOffset != 0.0) {
                    final int ordinal = EnumFacing.Axis.X.ordinal();
                    final double clamp = MathHelper.clamp(calculateXOffset + this.pistonDeltas[ordinal], -0.51, 0.51);
                    calculateXOffset = clamp - this.pistonDeltas[ordinal];
                    this.pistonDeltas[ordinal] = clamp;
                    if (Math.abs(calculateXOffset) <= 9.999999747378752E-6) {
                        return;
                    }
                }
                else if (a != 0.0) {
                    final int ordinal2 = EnumFacing.Axis.Y.ordinal();
                    final double clamp2 = MathHelper.clamp(a + this.pistonDeltas[ordinal2], -0.51, 0.51);
                    a = clamp2 - this.pistonDeltas[ordinal2];
                    this.pistonDeltas[ordinal2] = clamp2;
                    if (Math.abs(a) <= 9.999999747378752E-6) {
                        return;
                    }
                }
                else {
                    if (calculateZOffset == 0.0) {
                        return;
                    }
                    final int ordinal3 = EnumFacing.Axis.Z.ordinal();
                    final double clamp3 = MathHelper.clamp(calculateZOffset + this.pistonDeltas[ordinal3], -0.51, 0.51);
                    calculateZOffset = clamp3 - this.pistonDeltas[ordinal3];
                    this.pistonDeltas[ordinal3] = clamp3;
                    if (Math.abs(calculateZOffset) <= 9.999999747378752E-6) {
                        return;
                    }
                }
            }
            this.world.profiler.startSection("move");
            final double posX = this.posX;
            final double posY = this.posY;
            final double posZ = this.posZ;
            if (this.isInWeb) {
                this.isInWeb = false;
                calculateXOffset *= 0.25;
                a *= 0.05000000074505806;
                calculateZOffset *= 0.25;
                this.motionX = 0.0;
                this.motionY = 0.0;
                this.motionZ = 0.0;
            }
            double n = calculateXOffset;
            final double n2 = a;
            double n3 = calculateZOffset;
            if ((moverType == MoverType.SELF || moverType == MoverType.PLAYER) && this.onGround && this.isSneaking() && entity instanceof EntityPlayer) {
                while (calculateXOffset != 0.0 && this.world.getCollisionBoxes(entity, this.getEntityBoundingBox().offset(calculateXOffset, (double)(-this.stepHeight), 0.0)).isEmpty()) {
                    calculateXOffset = (n = ((calculateXOffset < 0.05 && calculateXOffset >= -0.05) ? 0.0 : ((calculateXOffset > 0.0) ? (calculateXOffset -= 0.05) : (calculateXOffset += 0.05))));
                }
                while (calculateZOffset != 0.0 && this.world.getCollisionBoxes(entity, this.getEntityBoundingBox().offset(0.0, (double)(-this.stepHeight), calculateZOffset)).isEmpty()) {
                    calculateZOffset = (n3 = ((calculateZOffset < 0.05 && calculateZOffset >= -0.05) ? 0.0 : ((calculateZOffset > 0.0) ? (calculateZOffset -= 0.05) : (calculateZOffset += 0.05))));
                }
                while (calculateXOffset != 0.0 && calculateZOffset != 0.0 && this.world.getCollisionBoxes(entity, this.getEntityBoundingBox().offset(calculateXOffset, (double)(-this.stepHeight), calculateZOffset)).isEmpty()) {
                    calculateXOffset = (n = ((calculateXOffset < 0.05 && calculateXOffset >= -0.05) ? 0.0 : ((calculateXOffset > 0.0) ? (calculateXOffset -= 0.05) : (calculateXOffset += 0.05))));
                    calculateZOffset = (n3 = ((calculateZOffset < 0.05 && calculateZOffset >= -0.05) ? 0.0 : ((calculateZOffset > 0.0) ? (calculateZOffset -= 0.05) : (calculateZOffset += 0.05))));
                }
            }
            final List getCollisionBoxes = this.world.getCollisionBoxes(entity, this.getEntityBoundingBox().expand(calculateXOffset, a, calculateZOffset));
            final AxisAlignedBB getEntityBoundingBox = this.getEntityBoundingBox();
            if (a != 0.0) {
                for (int size = getCollisionBoxes.size(), i = 0; i < size; ++i) {
                    a = getCollisionBoxes.get(i).calculateYOffset(this.getEntityBoundingBox(), a);
                }
                this.setEntityBoundingBox(this.getEntityBoundingBox().offset(0.0, a, 0.0));
            }
            if (calculateXOffset != 0.0) {
                for (int size2 = getCollisionBoxes.size(), j = 0; j < size2; ++j) {
                    calculateXOffset = getCollisionBoxes.get(j).calculateXOffset(this.getEntityBoundingBox(), calculateXOffset);
                }
                if (calculateXOffset != 0.0) {
                    this.setEntityBoundingBox(this.getEntityBoundingBox().offset(calculateXOffset, 0.0, 0.0));
                }
            }
            if (calculateZOffset != 0.0) {
                for (int size3 = getCollisionBoxes.size(), k = 0; k < size3; ++k) {
                    calculateZOffset = getCollisionBoxes.get(k).calculateZOffset(this.getEntityBoundingBox(), calculateZOffset);
                }
                if (calculateZOffset != 0.0) {
                    this.setEntityBoundingBox(this.getEntityBoundingBox().offset(0.0, 0.0, calculateZOffset));
                }
            }
            int n4 = 0;
            if (!this.onGround && (n2 == a || n2 >= 0.0)) {
                n4 = 0;
            }
            final int n5 = n4;
            if (this.stepHeight > 0.0f && n5 != 0 && (n != calculateXOffset || n3 != calculateZOffset)) {
                final StepEvent stepEvent = new StepEvent(0, entity);
                MinecraftForge.EVENT_BUS.post((Event)stepEvent);
                final double n6 = calculateXOffset;
                final double n7 = a;
                final double n8 = calculateZOffset;
                final AxisAlignedBB getEntityBoundingBox2 = this.getEntityBoundingBox();
                this.setEntityBoundingBox(getEntityBoundingBox);
                a = stepEvent.getHeight();
                final List getCollisionBoxes2 = this.world.getCollisionBoxes(entity, this.getEntityBoundingBox().expand(n, a, n3));
                final AxisAlignedBB getEntityBoundingBox3 = this.getEntityBoundingBox();
                final AxisAlignedBB expand = getEntityBoundingBox3.expand(n, 0.0, n3);
                double calculateYOffset = a;
                for (int size4 = getCollisionBoxes2.size(), l = 0; l < size4; ++l) {
                    calculateYOffset = getCollisionBoxes2.get(l).calculateYOffset(expand, calculateYOffset);
                }
                final AxisAlignedBB offset = getEntityBoundingBox3.offset(0.0, calculateYOffset, 0.0);
                double calculateXOffset2 = n;
                for (int size5 = getCollisionBoxes2.size(), n9 = 0; n9 < size5; ++n9) {
                    calculateXOffset2 = getCollisionBoxes2.get(n9).calculateXOffset(offset, calculateXOffset2);
                }
                final AxisAlignedBB offset2 = offset.offset(calculateXOffset2, 0.0, 0.0);
                double calculateZOffset2 = n3;
                for (int size6 = getCollisionBoxes2.size(), n10 = 0; n10 < size6; ++n10) {
                    calculateZOffset2 = getCollisionBoxes2.get(n10).calculateZOffset(offset2, calculateZOffset2);
                }
                final AxisAlignedBB offset3 = offset2.offset(0.0, 0.0, calculateZOffset2);
                final AxisAlignedBB getEntityBoundingBox4 = this.getEntityBoundingBox();
                double calculateYOffset2 = a;
                for (int size7 = getCollisionBoxes2.size(), n11 = 0; n11 < size7; ++n11) {
                    calculateYOffset2 = getCollisionBoxes2.get(n11).calculateYOffset(getEntityBoundingBox4, calculateYOffset2);
                }
                final AxisAlignedBB offset4 = getEntityBoundingBox4.offset(0.0, calculateYOffset2, 0.0);
                double calculateXOffset3 = n;
                for (int size8 = getCollisionBoxes2.size(), n12 = 0; n12 < size8; ++n12) {
                    calculateXOffset3 = getCollisionBoxes2.get(n12).calculateXOffset(offset4, calculateXOffset3);
                }
                final AxisAlignedBB offset5 = offset4.offset(calculateXOffset3, 0.0, 0.0);
                double calculateZOffset3 = n3;
                for (int size9 = getCollisionBoxes2.size(), n13 = 0; n13 < size9; ++n13) {
                    calculateZOffset3 = getCollisionBoxes2.get(n13).calculateZOffset(offset5, calculateZOffset3);
                }
                final AxisAlignedBB offset6 = offset5.offset(0.0, 0.0, calculateZOffset3);
                if (calculateXOffset2 * calculateXOffset2 + calculateZOffset2 * calculateZOffset2 > calculateXOffset3 * calculateXOffset3 + calculateZOffset3 * calculateZOffset3) {
                    calculateXOffset = calculateXOffset2;
                    calculateZOffset = calculateZOffset2;
                    a = -calculateYOffset;
                    this.setEntityBoundingBox(offset3);
                }
                else {
                    calculateXOffset = calculateXOffset3;
                    calculateZOffset = calculateZOffset3;
                    a = -calculateYOffset2;
                    this.setEntityBoundingBox(offset6);
                }
                for (int size10 = getCollisionBoxes2.size(), n14 = 0; n14 < size10; ++n14) {
                    a = getCollisionBoxes2.get(n14).calculateYOffset(this.getEntityBoundingBox(), a);
                }
                this.setEntityBoundingBox(this.getEntityBoundingBox().offset(0.0, a, 0.0));
                if (n6 * n6 + n8 * n8 >= calculateXOffset * calculateXOffset + calculateZOffset * calculateZOffset) {
                    calculateXOffset = n6;
                    a = n7;
                    calculateZOffset = n8;
                    this.setEntityBoundingBox(getEntityBoundingBox2);
                }
                else {
                    MinecraftForge.EVENT_BUS.post((Event)new StepEvent(1, entity));
                }
            }
            this.world.profiler.endSection();
            this.world.profiler.startSection("rest");
            this.resetPositionToBB();
            this.collidedHorizontally = (n != calculateXOffset || n3 != calculateZOffset);
            this.collidedVertically = (n2 != a);
            this.onGround = (this.collidedVertically && n2 < 0.0);
            this.collided = (this.collidedHorizontally || this.collidedVertically);
            BlockPos blockPos = new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.posY - 0.20000000298023224), MathHelper.floor(this.posZ));
            IBlockState getBlockState = this.world.getBlockState(blockPos);
            final BlockPos down;
            final IBlockState getBlockState2;
            final Block getBlock;
            if (getBlockState.getMaterial() == Material.AIR && ((getBlock = (getBlockState2 = this.world.getBlockState(down = blockPos.down())).getBlock()) instanceof BlockFence || getBlock instanceof BlockWall || getBlock instanceof BlockFenceGate)) {
                getBlockState = getBlockState2;
                blockPos = down;
            }
            this.updateFallState(a, this.onGround, getBlockState, blockPos);
            if (n != calculateXOffset) {
                this.motionX = 0.0;
            }
            if (n3 != calculateZOffset) {
                this.motionZ = 0.0;
            }
            final Block getBlock2 = getBlockState.getBlock();
            if (n2 != a) {
                getBlock2.onLanded(this.world, entity);
            }
            if (this.canTriggerWalking() && (!this.onGround || !this.isSneaking() || !(entity instanceof EntityPlayer)) && !this.isRiding()) {
                final double n15 = this.posX - posX;
                double n16 = this.posY - posY;
                final double n17 = this.posZ - posZ;
                if (getBlock2 != Blocks.LADDER) {
                    n16 = 0.0;
                }
                if (getBlock2 != null && this.onGround) {
                    getBlock2.onEntityWalk(this.world, blockPos, entity);
                }
                this.distanceWalkedModified += (float)(MathHelper.sqrt(n15 * n15 + n17 * n17) * 0.6);
                this.distanceWalkedOnStepModified += (float)(MathHelper.sqrt(n15 * n15 + n16 * n16 + n17 * n17) * 0.6);
                if (this.distanceWalkedOnStepModified > this.nextStepDistance && getBlockState.getMaterial() != Material.AIR) {
                    this.nextStepDistance = (int)this.distanceWalkedOnStepModified + 1;
                    if (this.isInWater()) {
                        final Entity entity2 = (this.isBeingRidden() && this.getControllingPassenger() != null) ? this.getControllingPassenger() : entity;
                        float n18 = MathHelper.sqrt(entity2.motionX * entity2.motionX * 0.20000000298023224 + entity2.motionY * entity2.motionY + entity2.motionZ * entity2.motionZ * 0.20000000298023224) * ((entity2 == entity) ? 0.35f : 0.4f);
                        if (n18 > 1.0f) {
                            n18 = 1.0f;
                        }
                        this.playSound(this.getSwimSound(), n18, 1.0f + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4f);
                    }
                    else {
                        this.playStepSound(blockPos, getBlock2);
                    }
                }
                else if (this.distanceWalkedOnStepModified > this.nextFlap && this.makeFlySound() && getBlockState.getMaterial() == Material.AIR) {
                    this.nextFlap = this.playFlySound(this.distanceWalkedOnStepModified);
                }
            }
            try {
                this.doBlockCollisions();
            }
            catch (Throwable t) {
                final CrashReport makeCrashReport = CrashReport.makeCrashReport(t, "Checking entity block collision");
                this.addEntityCrashInfo(makeCrashReport.makeCategory("Entity being checked for collision"));
                throw new ReportedException(makeCrashReport);
            }
            final boolean isWet = this.isWet();
            if (this.world.isFlammableWithin(this.getEntityBoundingBox().shrink(0.001))) {
                this.dealFireDamage(1);
                if (!isWet) {
                    ++this.fire;
                    if (this.fire == 0) {
                        this.setFire(8);
                    }
                }
            }
            else if (this.fire <= 0) {
                this.fire = -this.getFireImmuneTicks();
            }
            if (isWet && this.isBurning()) {
                this.playSound(SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, 0.7f, 1.6f + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4f);
                this.fire = -this.getFireImmuneTicks();
            }
            this.world.profiler.endSection();
        }
    }
    
    @Redirect(method = { "onEntityUpdate" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;getMaxInPortalTime()I"))
    private int getMaxInPortalTimeHook(final Entity entity) {
        int n = this.getMaxInPortalTime();
        if (Portals.getInstance().isOn() && (boolean)Portals.getInstance().fastPortal.getValue()) {
            n = (int)Portals.getInstance().time.getValue();
        }
        return n;
    }
    
    @Redirect(method = { "applyEntityCollision" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;addVelocity(DDD)V"))
    public void addVelocityHook(final Entity entity, final double n, final double n2, final double n3) {
        final PushEvent pushEvent = new PushEvent(entity, n, n2, n3, true);
        MinecraftForge.EVENT_BUS.post((Event)pushEvent);
        if (!pushEvent.isCanceled()) {
            entity.motionX += pushEvent.x;
            entity.motionY += pushEvent.y;
            entity.motionZ += pushEvent.z;
            entity.isAirBorne = pushEvent.airbone;
        }
    }
}
