//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util.skid.oyvey;

import net.minecraft.entity.player.*;
import java.util.stream.*;
import me.snow.luigihack.*;
import net.minecraft.block.state.*;
import net.minecraft.client.entity.*;
import net.minecraft.client.network.*;
import net.minecraft.potion.*;
import net.minecraft.network.*;
import javax.annotation.*;
import net.minecraft.util.*;
import me.snow.luigihack.mixin.mixins.accessors.*;
import net.minecraft.item.*;
import net.minecraft.util.math.*;
import net.minecraft.enchantment.*;
import net.minecraft.block.*;
import net.minecraft.init.*;
import net.minecraft.entity.item.*;
import java.awt.*;
import me.snow.luigihack.api.util.*;
import java.util.function.*;
import net.minecraft.network.play.client.*;
import java.util.*;
import java.text.*;
import java.math.*;
import com.mojang.realmsclient.gui.*;
import net.minecraft.entity.projectile.*;
import net.minecraft.entity.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;

public class EntityUtil2 implements Util
{
    public static final /* synthetic */ Vec3d[] platformOffsetList;
    public static final /* synthetic */ Vec3d[] antiScaffoldOffsetList;
    public static final /* synthetic */ Vec3d[] antiStepOffsetList;
    public static final /* synthetic */ Vec3d[] doubleLegOffsetList;
    private static final /* synthetic */ BlockPos[] offsets;
    public static final /* synthetic */ Vec3d[] OffsetList;
    public static final /* synthetic */ Vec3d[] antiDropOffsetList;
    public static final /* synthetic */ Vec3d[] legOffsetList;
    
    public static List<EntityPlayer> getNearbyPlayers(final double n) {
        if (EntityUtil2.mc.world.getLoadedEntityList().size() == 0) {
            return null;
        }
        final List list = (List)EntityUtil2.mc.world.playerEntities.stream().filter(entityPlayerSP -> EntityUtil2.mc.player != entityPlayerSP).filter(entity2 -> EntityUtil2.mc.player.getDistance(entity2) <= n).filter(entity -> getHealth(entity) >= 0.0f).collect(Collectors.toList());
        list.removeIf(entityPlayer -> LuigiHack.friendManager.isFriend(entityPlayer.getName()));
        return (List<EntityPlayer>)list;
    }
    
    public static void moveEntityStrafe(final double n, final Entity entity) {
        if (entity != null) {
            final MovementInput movementInput = EntityUtil2.mc.player.movementInput;
            double n2 = movementInput.moveForward;
            double n3 = movementInput.moveStrafe;
            float rotationYaw = EntityUtil2.mc.player.rotationYaw;
            if (n2 == 0.0 && n3 == 0.0) {
                entity.motionX = 0.0;
                entity.motionZ = 0.0;
            }
            else {
                if (n2 != 0.0) {
                    if (n3 > 0.0) {
                        rotationYaw += ((n2 > 0.0) ? -45 : 45);
                    }
                    else if (n3 < 0.0) {
                        rotationYaw += ((n2 > 0.0) ? 45 : -45);
                    }
                    n3 = 0.0;
                    if (n2 > 0.0) {
                        n2 = 1.0;
                    }
                    else if (n2 < 0.0) {
                        n2 = -1.0;
                    }
                }
                entity.motionX = n2 * n * Math.cos(Math.toRadians(rotationYaw + 90.0f)) + n3 * n * Math.sin(Math.toRadians(rotationYaw + 90.0f));
                entity.motionZ = n2 * n * Math.sin(Math.toRadians(rotationYaw + 90.0f)) - n3 * n * Math.cos(Math.toRadians(rotationYaw + 90.0f));
            }
        }
    }
    
    public static boolean isAlive(final Entity entity) {
        return isLiving(entity) && !entity.isDead && ((EntityLivingBase)entity).getHealth() > 0.0f;
    }
    
    public static boolean isBothHole(final BlockPos blockPos) {
        final BlockPos[] array = { blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down() };
        for (int length = array.length, i = 0; i < length; ++i) {
            final IBlockState getBlockState = EntityUtil2.mc.world.getBlockState(array[i]);
            if (getBlockState.getBlock() == Blocks.AIR || (getBlockState.getBlock() != Blocks.BEDROCK && getBlockState.getBlock() != Blocks.OBSIDIAN)) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean canEntityFeetBeSeen(final Entity entity) {
        return EntityUtil2.mc.world.rayTraceBlocks(new Vec3d(EntityUtil2.mc.player.posX, EntityUtil2.mc.player.posX + EntityUtil2.mc.player.getEyeHeight(), EntityUtil2.mc.player.posZ), new Vec3d(entity.posX, entity.posY, entity.posZ), false, true, false) == null;
    }
    
    public static boolean isDrivenByPlayer(final Entity entity) {
        return EntityUtil2.mc.player != null && entity != null && entity.equals((Object)EntityUtil2.mc.player.getRidingEntity());
    }
    
    public static Vec3d[] getUnsafeBlockArrayFromVec3d(final Vec3d vec3d, final int n, final boolean b) {
        final List<Vec3d> unsafeBlocksFromVec3d = getUnsafeBlocksFromVec3d(vec3d, n, b);
        return unsafeBlocksFromVec3d.toArray(new Vec3d[unsafeBlocksFromVec3d.size()]);
    }
    
    public static boolean isPlayerInHole() {
        final BlockPos localPlayerPosFloored = getLocalPlayerPosFloored();
        if (EntityUtil2.mc.world.getBlockState(localPlayerPosFloored).getBlock() != Blocks.AIR) {
            return false;
        }
        if (EntityUtil2.mc.world.getBlockState(localPlayerPosFloored.up()).getBlock() != Blocks.AIR) {
            return false;
        }
        if (EntityUtil2.mc.world.getBlockState(localPlayerPosFloored.down()).getBlock() == Blocks.AIR) {
            return false;
        }
        final BlockPos[] array = { localPlayerPosFloored.north(), localPlayerPosFloored.south(), localPlayerPosFloored.east(), localPlayerPosFloored.west() };
        int n = 0;
        final BlockPos[] array2 = array;
        for (int length = array2.length, i = 0; i < length; ++i) {
            final IBlockState getBlockState = EntityUtil2.mc.world.getBlockState(array2[i]);
            if (getBlockState.getBlock() != Blocks.AIR) {
                if (getBlockState.isFullBlock()) {
                    ++n;
                }
            }
        }
        return n >= 4;
    }
    
    public static double getDirection() {
        float n = 0.0f;
        final EntityPlayerSP player = EntityUtil2.mc.player;
        float rotationYaw = player.rotationYaw;
        if (player.moveForward < 0.0f) {
            rotationYaw += 180.0f;
        }
        if (player.moveForward >= 0.0f) {
            n = ((player.moveForward > 0.0f) ? 0.5f : 1.0f);
        }
        if (player.moveStrafing > 0.0f) {
            rotationYaw -= 90.0f * n;
        }
        else if (player.moveStrafing < 0.0f) {
            rotationYaw += 90.0f * n;
        }
        return rotationYaw * 0.017453292f;
    }
    
    public static Vec3d[] getHeightOffsets(final int n, final int n2) {
        final ArrayList<Vec3d> list = new ArrayList<Vec3d>();
        for (int i = n; i <= n2; ++i) {
            list.add(new Vec3d(0.0, (double)i, 0.0));
        }
        return list.toArray(new Vec3d[list.size()]);
    }
    
    public static ArrayList<BlockPos> getPos(final double n, final double n2, final double n3, final Entity entity) {
        final ArrayList<BlockPos> list = new ArrayList<BlockPos>();
        if (entity != null) {
            final AxisAlignedBB axisAlignedBB = (entity.ridingEntity != null) ? entity.ridingEntity.getEntityBoundingBox().contract(0.0, 0.0, 0.0).offset(n, n2, n3) : entity.getEntityBoundingBox().contract(0.01, 1.0, 0.01).offset(n, n2, n3);
            final int n4 = (int)axisAlignedBB.minY;
            for (int n5 = (int)Math.floor(axisAlignedBB.minX); n5 < Math.floor(axisAlignedBB.maxX) + 1.0; ++n5) {
                for (int n6 = (int)Math.floor(axisAlignedBB.minZ); n6 < Math.floor(axisAlignedBB.maxZ) + 1.0; ++n6) {
                    list.add(new BlockPos(n5, n4, n6));
                }
            }
        }
        return list;
    }
    
    public static int getEntityPing(final EntityPlayer entityPlayer) {
        int clamp = 0;
        try {
            clamp = MathUtil.clamp(Objects.requireNonNull(EntityUtil2.mc.getConnection()).getPlayerInfo(entityPlayer.getUniqueID()).getResponseTime(), 1, 99999);
        }
        catch (NullPointerException ex) {}
        return clamp;
    }
    
    public static double getMaxSpeed() {
        double n = 0.2873;
        if (EntityUtil2.mc.player.isPotionActive((Potion)Objects.requireNonNull(Potion.getPotionById(1)))) {
            n *= 1.0 + 0.2 * (Objects.requireNonNull(EntityUtil2.mc.player.getActivePotionEffect((Potion)Objects.requireNonNull(Potion.getPotionById(1)))).getAmplifier() + 1);
        }
        return n;
    }
    
    static {
        antiDropOffsetList = new Vec3d[] { new Vec3d(0.0, -2.0, 0.0) };
        platformOffsetList = new Vec3d[] { new Vec3d(0.0, -1.0, 0.0), new Vec3d(0.0, -1.0, -1.0), new Vec3d(0.0, -1.0, 1.0), new Vec3d(-1.0, -1.0, 0.0), new Vec3d(1.0, -1.0, 0.0) };
        legOffsetList = new Vec3d[] { new Vec3d(-1.0, 0.0, 0.0), new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, -1.0), new Vec3d(0.0, 0.0, 1.0) };
        doubleLegOffsetList = new Vec3d[] { new Vec3d(-1.0, 0.0, 0.0), new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, -1.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-2.0, 0.0, 0.0), new Vec3d(2.0, 0.0, 0.0), new Vec3d(0.0, 0.0, -2.0), new Vec3d(0.0, 0.0, 2.0) };
        OffsetList = new Vec3d[] { new Vec3d(1.0, 1.0, 0.0), new Vec3d(-1.0, 1.0, 0.0), new Vec3d(0.0, 1.0, 1.0), new Vec3d(0.0, 1.0, -1.0), new Vec3d(0.0, 2.0, 0.0) };
        headpiece = new Vec3d[] { new Vec3d(0.0, 2.0, 0.0) };
        offsetsNoHead = new Vec3d[] { new Vec3d(1.0, 1.0, 0.0), new Vec3d(-1.0, 1.0, 0.0), new Vec3d(0.0, 1.0, 1.0), new Vec3d(0.0, 1.0, -1.0) };
        antiStepOffsetList = new Vec3d[] { new Vec3d(-1.0, 2.0, 0.0), new Vec3d(1.0, 2.0, 0.0), new Vec3d(0.0, 2.0, 1.0), new Vec3d(0.0, 2.0, -1.0) };
        antiScaffoldOffsetList = new Vec3d[] { new Vec3d(0.0, 3.0, 0.0) };
        offsets = new BlockPos[] { new BlockPos(0, 0, -1), new BlockPos(1, 0, 0), new BlockPos(0, 0, 1), new BlockPos(-1, 0, 0) };
    }
    
    public static float getHealth(final Entity entity, final boolean b) {
        if (isLiving(entity)) {
            final EntityLivingBase entityLivingBase = (EntityLivingBase)entity;
            return entityLivingBase.getHealth() + (b ? entityLivingBase.getAbsorptionAmount() : 0.0f);
        }
        return 0.0f;
    }
    
    public static boolean isNeutralMob(final Entity entity) {
        return entity instanceof EntityPigZombie || entity instanceof EntityWolf || entity instanceof EntityEnderman;
    }
    
    public static boolean stopSneaking(final boolean b) {
        if (b && EntityUtil2.mc.player != null) {
            EntityUtil2.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)EntityUtil2.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
        }
        return false;
    }
    
    public static boolean isValid(final Entity entity, final double n) {
        return !isntValid(entity, n);
    }
    
    public static boolean holdingWeapon(final EntityPlayer entityPlayer) {
        return entityPlayer.getHeldItemMainhand().getItem() instanceof ItemSword || entityPlayer.getHeldItemMainhand().getItem() instanceof ItemAxe;
    }
    
    public static Vec3d getInterpolatedRenderPos(final Vec3d vec3d) {
        return new Vec3d(vec3d.x, vec3d.y, vec3d.z).subtract(EntityUtil2.mc.getRenderManager().renderPosX, EntityUtil2.mc.getRenderManager().renderPosY, EntityUtil2.mc.getRenderManager().renderPosZ);
    }
    
    private static boolean isValid(final Entity entity, final boolean b, final boolean b2, final boolean b3, final boolean b4, final boolean b5, final double n) {
        if (entity.isDead) {
            return false;
        }
        if (!(entity instanceof EntityLivingBase) || entity == EntityUtil2.mc.player || entity.getDistanceSq((Entity)EntityUtil2.mc.player) > n * n) {
            return false;
        }
        if (entity instanceof EntityPlayer && b) {
            return b3 || !LuigiHack.friendManager.isFriend((EntityPlayer)entity);
        }
        return (isHostileMob(entity) && b4) || (isNeutralMob(entity) && b2) || (isPassive(entity) && b5);
    }
    
    public static Vec3d[] getOffsets(final int n, final boolean b) {
        final List<Vec3d> offsetList = getOffsetList(n, b);
        return offsetList.toArray(new Vec3d[offsetList.size()]);
    }
    
    public static BlockPos getPositionVectors(final Entity entity, @Nullable final BlockPos blockPos) {
        final Vec3d getPositionVector = entity.getPositionVector();
        if (blockPos == null) {
            return new BlockPos(getPositionVector.x, getPositionVector.y, getPositionVector.z);
        }
        return new BlockPos(getPositionVector.x, getPositionVector.y, getPositionVector.z).add((Vec3i)blockPos);
    }
    
    public static boolean isMoving(final EntityPlayerSP entityPlayerSP) {
        return EntityUtil2.mc.player.moveForward != 0.0 || EntityUtil2.mc.player.moveStrafing != 0.0;
    }
    
    public static boolean isVehicle(final Entity entity) {
        return entity instanceof EntityBoat || entity instanceof EntityMinecart;
    }
    
    public static ArrayList<PairUtil<EntityPlayer, ArrayList<BlockPos>>> getCityablePlayers() {
        final ArrayList<PairUtil> list = (ArrayList<PairUtil>)new ArrayList<PairUtil<EntityPlayer, ArrayList<BlockPos>>>();
        for (final EntityPlayer entityPlayer2 : Objects.requireNonNull(getNearbyPlayers(6.0)).stream().filter(entityPlayer -> !LuigiHack.friendManager.isFriend(entityPlayer)).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList())) {
            final ArrayList<BlockPos> list2 = new ArrayList<BlockPos>();
            for (int i = 0; i < 4; ++i) {
                final BlockPos positionVectors = getPositionVectors((Entity)entityPlayer2, EntityUtil2.offsets[i]);
                if (EntityUtil2.mc.world.getBlockState(positionVectors).getBlock() == Blocks.OBSIDIAN) {
                    boolean b = false;
                    switch (i) {
                        case 0: {
                            b = BlockUtil.canPlaceCrystal(positionVectors.north(2), true, false);
                            break;
                        }
                        case 1: {
                            b = BlockUtil.canPlaceCrystal(positionVectors.east(2), true, false);
                            break;
                        }
                        case 2: {
                            b = BlockUtil.canPlaceCrystal(positionVectors.south(2), true, false);
                            break;
                        }
                        case 3: {
                            b = BlockUtil.canPlaceCrystal(positionVectors.west(2), true, false);
                            break;
                        }
                    }
                    if (b) {
                        list2.add(positionVectors);
                    }
                }
            }
            if (list2.isEmpty()) {
                continue;
            }
            list.add(new PairUtil((Object)entityPlayer2, (Object)list2));
        }
        return (ArrayList<PairUtil<EntityPlayer, ArrayList<BlockPos>>>)list;
    }
    
    public static boolean isSafe(final Entity entity, final int n, final boolean b, final boolean b2) {
        return getUnsafeBlocks(entity, n, b, b2).size() == 0;
    }
    
    public static Vec3d[] getTrapOffsets(final boolean b, final boolean b2, final boolean b3, final boolean b4, final boolean b5) {
        final List<Vec3d> trapOffsetsList = getTrapOffsetsList(b, b2, b3, b4, b5);
        return trapOffsetsList.toArray(new Vec3d[trapOffsetsList.size()]);
    }
    
    public static double getDst(final Vec3d vec3d) {
        return EntityUtil2.mc.player.getPositionVector().distanceTo(vec3d);
    }
    
    public static void swingArmNoPacket(final EnumHand swingingHand, final EntityLivingBase entityLivingBase) {
        final ItemStack getHeldItem = entityLivingBase.getHeldItem(swingingHand);
        if (!getHeldItem.isEmpty() && getHeldItem.getItem().onEntitySwing(entityLivingBase, getHeldItem)) {
            return;
        }
        if (!entityLivingBase.isSwingInProgress || entityLivingBase.swingProgressInt >= ((IEntityLivingBase)entityLivingBase).getArmSwingAnimationEnd() / 2 || entityLivingBase.swingProgressInt < 0) {
            entityLivingBase.swingProgressInt = -1;
            entityLivingBase.isSwingInProgress = true;
            entityLivingBase.swingingHand = swingingHand;
        }
    }
    
    public static float getHealth(final Entity entity) {
        if (isLiving(entity)) {
            final EntityLivingBase entityLivingBase = (EntityLivingBase)entity;
            return entityLivingBase.getHealth() + entityLivingBase.getAbsorptionAmount();
        }
        return 0.0f;
    }
    
    public static double getEntitySpeed(final Entity entity) {
        if (entity != null) {
            final double n = entity.posX - entity.prevPosX;
            final double n2 = entity.posZ - entity.prevPosZ;
            return MathHelper.sqrt(n * n + n2 * n2) * 20.0;
        }
        return 0.0;
    }
    
    public static EntityPlayer getTargetDouble(final double n) {
        Object o = null;
        for (int size = EntityUtil2.mc.world.playerEntities.size(), i = 0; i < size; ++i) {
            final EntityPlayer entityPlayer = EntityUtil2.mc.world.playerEntities.get(i);
            if (!isntValid((Entity)entityPlayer, n)) {
                if (o == null) {
                    o = entityPlayer;
                }
                else if (EntityUtil2.mc.player.getDistanceSq((Entity)entityPlayer) < EntityUtil2.mc.player.getDistanceSq((Entity)o)) {
                    o = entityPlayer;
                }
            }
        }
        return (EntityPlayer)o;
    }
    
    public static List<Vec3d> getOffsetList(final int n, final boolean b, final boolean b2) {
        final ArrayList<Vec3d> list = new ArrayList<Vec3d>();
        if (b2) {
            list.add(new Vec3d(-1.0, (double)n, 0.0));
            list.add(new Vec3d(1.0, (double)n, 0.0));
            list.add(new Vec3d(0.0, (double)n, -1.0));
            list.add(new Vec3d(0.0, (double)n, 1.0));
        }
        else {
            list.add(new Vec3d(-1.0, (double)n, 0.0));
        }
        if (b) {
            list.add(new Vec3d(0.0, (double)(n - 1), 0.0));
        }
        return list;
    }
    
    public static boolean is32k(final ItemStack itemStack) {
        return EnchantmentHelper.getEnchantmentLevel(Enchantments.SHARPNESS, itemStack) >= 1000;
    }
    
    public static Vec3d getInterpolatedRenderPos(final Entity entity, final float n) {
        return getInterpolatedPos(entity, n).subtract(EntityUtil2.mc.getRenderManager().renderPosX, EntityUtil2.mc.getRenderManager().renderPosY, EntityUtil2.mc.getRenderManager().renderPosZ);
    }
    
    public static List<Vec3d> getUnsafeBlocksFromVec3d(final Vec3d vec3d, final int n, final boolean b) {
        final ArrayList<Vec3d> list = new ArrayList<Vec3d>();
        for (final Vec3d e : getOffsets(n, b)) {
            final Block getBlock = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(e.x, e.y, e.z)).getBlock();
            if (getBlock instanceof BlockAir || getBlock instanceof BlockLiquid || getBlock instanceof BlockTallGrass || getBlock instanceof BlockFire || getBlock instanceof BlockDeadBush || getBlock instanceof BlockSnow) {
                list.add(e);
            }
        }
        return list;
    }
    
    public static boolean isInWater(final Entity entity) {
        if (entity == null) {
            return false;
        }
        final double n = entity.posY + 0.01;
        for (int i = MathHelper.floor(entity.posX); i < MathHelper.ceil(entity.posX); ++i) {
            for (int j = MathHelper.floor(entity.posZ); j < MathHelper.ceil(entity.posZ); ++j) {
                if (EntityUtil2.mc.world.getBlockState(new BlockPos(i, (int)n, j)).getBlock() instanceof BlockLiquid) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static List<Vec3d> getUntrappedBlocks(final EntityPlayer entityPlayer, final boolean b, final boolean b2, final boolean b3, final boolean b4, final boolean b5) {
        final ArrayList<Vec3d> list = new ArrayList<Vec3d>();
        if (!b2 && getUnsafeBlocks((Entity)entityPlayer, 2, false).size() == 4) {
            list.addAll(getUnsafeBlocks((Entity)entityPlayer, 2, false));
        }
        for (int i = 0; i < getTrapOffsets(b, b2, b3, b4, b5).length; ++i) {
            final Vec3d e = getTrapOffsets(b, b2, b3, b4, b5)[i];
            final Block getBlock = EntityUtil2.mc.world.getBlockState(new BlockPos(entityPlayer.getPositionVector()).add(e.x, e.y, e.z)).getBlock();
            if (getBlock instanceof BlockAir || getBlock instanceof BlockLiquid || getBlock instanceof BlockTallGrass || getBlock instanceof BlockFire || getBlock instanceof BlockDeadBush || getBlock instanceof BlockSnow) {
                list.add(e);
            }
        }
        return list;
    }
    
    public static double[] calcLooking(final double n, final double n2, final double n3, final EntityPlayer entityPlayer) {
        final double n4 = entityPlayer.posX - n;
        final double n5 = entityPlayer.posY - n2;
        final double n6 = entityPlayer.posZ - n3;
        final double sqrt = Math.sqrt(n4 * n4 + n5 * n5 + n6 * n6);
        return new double[] { Math.atan2(n6 / sqrt, n4 / sqrt) * 180.0 / 3.141592653589793 + 90.0, Math.asin(n5 / sqrt) * 180.0 / 3.141592653589793 };
    }
    
    public static double getMovementSpeed() {
        if (!EntityUtil2.mc.player.isPotionActive(MobEffects.SPEED)) {
            return getBaseMovementSpeed();
        }
        return getBaseMovementSpeed() * 1.0 + 0.06 * (Objects.requireNonNull(EntityUtil2.mc.player.getActivePotionEffect(MobEffects.SPEED)).getAmplifier() + 1);
    }
    
    public static Boolean posEqualsBlock(final BlockPos blockPos, final Block obj) {
        return getBlockOnPos(blockPos).equals(obj);
    }
    
    public static boolean checkCollide() {
        return !EntityUtil2.mc.player.isSneaking() && (EntityUtil2.mc.player.getRidingEntity() == null || EntityUtil2.mc.player.getRidingEntity().fallDistance < 3.0f) && EntityUtil2.mc.player.fallDistance < 3.0f;
    }
    
    public static boolean isDead(final Entity entity) {
        return !isAlive(entity);
    }
    
    public static boolean isAboveWater(final Entity entity, final boolean b) {
        if (entity == null) {
            return false;
        }
        final double n = entity.posY - (b ? 0.03 : (isPlayer(entity) ? 0.2 : 0.5));
        for (int i = MathHelper.floor(entity.posX); i < MathHelper.ceil(entity.posX); ++i) {
            for (int j = MathHelper.floor(entity.posZ); j < MathHelper.ceil(entity.posZ); ++j) {
                if (EntityUtil2.mc.world.getBlockState(new BlockPos(i, MathHelper.floor(n), j)).getBlock() instanceof BlockLiquid) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean isHostileMob(final Entity entity) {
        return entity.isCreatureType(EnumCreatureType.MONSTER, false) && !isNeutralMob(entity);
    }
    
    public static List<Vec3d> getOffsetList(final int n, final boolean b) {
        final ArrayList<Vec3d> list = new ArrayList<Vec3d>();
        list.add(new Vec3d(-1.0, (double)n, 0.0));
        list.add(new Vec3d(1.0, (double)n, 0.0));
        list.add(new Vec3d(0.0, (double)n, -1.0));
        list.add(new Vec3d(0.0, (double)n, 1.0));
        if (b) {
            list.add(new Vec3d(0.0, (double)(n - 1), 0.0));
        }
        return list;
    }
    
    public static boolean isCrystalAtFeet(final EntityEnderCrystal entityEnderCrystal, final double n) {
        for (final EntityPlayer entityPlayer : EntityUtil2.mc.world.playerEntities) {
            if (EntityUtil2.mc.player.getDistanceSq((Entity)entityPlayer) <= n * n) {
                if (LuigiHack.friendManager.isFriend(entityPlayer)) {
                    continue;
                }
                for (final Vec3d vec3d : EntityUtil2.doubleLegOffsetList) {
                    if (new BlockPos(entityPlayer.getPositionVector()).add(vec3d.x, vec3d.y, vec3d.z) == entityEnderCrystal.getPosition()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public static Color getColor(final Entity entity, final int n, final int n2, final int n3, final int n4, final boolean b) {
        Color color = new Color(n / 255.0f, n2 / 255.0f, n3 / 255.0f, n4 / 255.0f);
        if (entity instanceof EntityPlayer && b && LuigiHack.friendManager.isFriend((EntityPlayer)entity)) {
            color = new Color(0.33333334f, 1.0f, 1.0f, n4 / 255.0f);
        }
        return color;
    }
    
    public static List<Vec3d> getTrapOffsetsList(final boolean b, final boolean b2, final boolean b3, final boolean b4, final boolean b5) {
        final ArrayList<Vec3d> list = new ArrayList<Vec3d>(getOffsetList(1, false));
        list.add(new Vec3d(0.0, 2.0, 0.0));
        if (b) {
            list.add(new Vec3d(0.0, 3.0, 0.0));
        }
        if (b2) {
            list.addAll(getOffsetList(2, false));
        }
        if (b3) {
            list.addAll(getOffsetList(0, false));
        }
        if (b4) {
            list.addAll(getOffsetList(-1, false));
            list.add(new Vec3d(0.0, -1.0, 0.0));
        }
        if (b5) {
            list.add(new Vec3d(0.0, -2.0, 0.0));
        }
        return list;
    }
    
    public static boolean isTrappedExtended(final int n, final EntityPlayer entityPlayer, final boolean b, final boolean b2, final boolean b3, final boolean b4, final boolean b5, final boolean b6, final boolean b7, final boolean b8) {
        return getUntrappedBlocksExtended(n, entityPlayer, b, b2, b3, b4, b5, b6, b7, b8).size() == 0;
    }
    
    public static boolean isMobAggressive(final Entity entity) {
        if (entity instanceof EntityPigZombie) {
            if (((EntityPigZombie)entity).isArmsRaised() || ((EntityPigZombie)entity).isAngry()) {
                return true;
            }
        }
        else {
            if (entity instanceof EntityWolf) {
                return ((EntityWolf)entity).isAngry() && !EntityUtil2.mc.player.equals((Object)((EntityWolf)entity).getOwner());
            }
            if (entity instanceof EntityEnderman) {
                return ((EntityEnderman)entity).isScreaming();
            }
        }
        return isHostileMob(entity);
    }
    
    public static boolean isBlockValid(final BlockPos blockPos) {
        return isBedrockHole(blockPos) || isObbyHole(blockPos) || isBothHole(blockPos);
    }
    
    public static double getDistance(final double n, final double n2, final double n3, final double n4, final double n5, final double n6) {
        final double n7 = n - n4;
        final double n8 = n2 - n5;
        final double n9 = n3 - n6;
        return MathHelper.sqrt(n7 * n7 + n8 * n8 + n9 * n9);
    }
    
    public static EntityPlayer getTarget2(final float n) {
        Object o = null;
        for (int size = EntityUtil.mc.world.playerEntities.size(), i = 0; i < size; ++i) {
            final EntityPlayer entityPlayer = EntityUtil.mc.world.playerEntities.get(i);
            if (!EntityUtil.isntValid((Entity)entityPlayer, (double)n)) {
                if (o == null) {
                    o = entityPlayer;
                }
                else if (EntityUtil.mc.player.getDistanceSq((Entity)entityPlayer) < EntityUtil.mc.player.getDistanceSq((Entity)o)) {
                    o = entityPlayer;
                }
            }
        }
        return (EntityPlayer)o;
    }
    
    public static Vec3d getInterpolatedAmount(final Entity entity, final double n, final double n2, final double n3) {
        return new Vec3d((entity.posX - entity.lastTickPosX) * n, (entity.posY - entity.lastTickPosY) * n2, (entity.posZ - entity.lastTickPosZ) * n3);
    }
    
    public static List<Vec3d> getUnsafeBlocks(final Entity entity, final int n, final boolean b) {
        return getUnsafeBlocksFromVec3d(entity.getPositionVector(), n, b);
    }
    
    public static Vec3d[] getUnsafeBlockArray(final Entity entity, final int n, final boolean b, final boolean b2) {
        final List<Vec3d> unsafeBlocks = getUnsafeBlocks(entity, n, b, b2);
        return unsafeBlocks.toArray(new Vec3d[unsafeBlocks.size()]);
    }
    
    public static Vec3d[] getUnsafeBlockArrayFromVec3d(final Vec3d vec3d, final int n, final boolean b, final boolean b2) {
        final List<Vec3d> unsafeBlocksFromVec3d = getUnsafeBlocksFromVec3d(vec3d, n, b, b2);
        return unsafeBlocksFromVec3d.toArray(new Vec3d[unsafeBlocksFromVec3d.size()]);
    }
    
    public static EntityLivingBase getTarget(final boolean b, final boolean b2, final boolean b3, final boolean b4, final boolean b5, final double n, final int n2) {
        EntityLivingBase entityLivingBase3 = null;
        if (n2 == 0) {
            entityLivingBase3 = (EntityLivingBase)EntityUtil2.mc.world.loadedEntityList.stream().filter(entity2 -> isValid(entity2, b, b2, b3, b4, b5, n)).min(Comparator.comparing(entity -> EntityUtil2.mc.player.getPositionVector().squareDistanceTo(entity.getPositionVector()))).orElse(null);
        }
        else if (n2 == 1) {
            entityLivingBase3 = (EntityLivingBase)EntityUtil2.mc.world.loadedEntityList.stream().filter(entity3 -> isValid(entity3, b, b2, b3, b4, b5, n)).map(entityLivingBase -> entityLivingBase).min(Comparator.comparing((Function<? super T, ? extends Comparable>)EntityLivingBase::getHealth)).orElse(null);
        }
        else if (n2 == 2) {
            entityLivingBase3 = (EntityLivingBase)EntityUtil2.mc.world.loadedEntityList.stream().filter(entity4 -> isValid(entity4, b, b2, b3, b4, b5, n)).map(entityLivingBase2 -> entityLivingBase2).max(Comparator.comparing((Function<? super T, ? extends Comparable>)EntityLivingBase::getHealth)).orElse(null);
        }
        return entityLivingBase3;
    }
    
    public static boolean isInHole(final Entity entity) {
        return isBlockValid(new BlockPos(entity.posX, entity.posY, entity.posZ));
    }
    
    public static BlockPos getEntityPosFloored(final Entity entity) {
        return new BlockPos(Math.floor(entity.posX), Math.floor(entity.posY), Math.floor(entity.posZ));
    }
    
    public static Vec3d interpolateEntity(final Entity entity, final float n) {
        return new Vec3d(entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * n, entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * n, entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * n);
    }
    
    public static BlockPos getPlayerPos(final EntityPlayer entityPlayer) {
        return new BlockPos(Math.floor(entityPlayer.posX), Math.floor(entityPlayer.posY), Math.floor(entityPlayer.posZ));
    }
    
    public static boolean isEntityMoving(final Entity entity) {
        if (entity == null) {
            return false;
        }
        if (entity instanceof EntityPlayer) {
            return EntityUtil2.mc.gameSettings.keyBindForward.isKeyDown() || EntityUtil2.mc.gameSettings.keyBindBack.isKeyDown() || EntityUtil2.mc.gameSettings.keyBindLeft.isKeyDown() || EntityUtil2.mc.gameSettings.keyBindRight.isKeyDown();
        }
        return entity.motionX != 0.0 || entity.motionY != 0.0 || entity.motionZ != 0.0;
    }
    
    public static Vec3d[] getOffsets(final int n, final boolean b, final boolean b2) {
        final List<Vec3d> offsetList = getOffsetList(n, b, b2);
        return offsetList.toArray(new Vec3d[offsetList.size()]);
    }
    
    public void setSpeed(final double n) {
        final EntityPlayerSP player = EntityUtil2.mc.player;
        player.motionX = -(Math.sin(getDirection()) * n);
        player.motionZ = Math.cos(getDirection()) * n;
    }
    
    public static double[] forward(final double n) {
        float moveForward = EntityUtil2.mc.player.movementInput.moveForward;
        float moveStrafe = EntityUtil2.mc.player.movementInput.moveStrafe;
        float n2 = EntityUtil2.mc.player.prevRotationYaw + (EntityUtil2.mc.player.rotationYaw - EntityUtil2.mc.player.prevRotationYaw) * EntityUtil2.mc.getRenderPartialTicks();
        if (moveForward != 0.0f) {
            if (moveStrafe > 0.0f) {
                n2 += ((moveForward > 0.0f) ? -45 : 45);
            }
            else if (moveStrafe < 0.0f) {
                n2 += ((moveForward > 0.0f) ? 45 : -45);
            }
            moveStrafe = 0.0f;
            if (moveForward > 0.0f) {
                moveForward = 1.0f;
            }
            else if (moveForward < 0.0f) {
                moveForward = -1.0f;
            }
        }
        final double sin = Math.sin(Math.toRadians(n2 + 90.0f));
        final double cos = Math.cos(Math.toRadians(n2 + 90.0f));
        return new double[] { moveForward * n * cos + moveStrafe * n * sin, moveForward * n * sin - moveStrafe * n * cos };
    }
    
    public static void mutliplyEntitySpeed(final Entity entity, final double n) {
        if (entity != null) {
            entity.motionX *= n;
            entity.motionZ *= n;
        }
    }
    
    public static List<Vec3d> targets(final Vec3d vec3d, final boolean b, final boolean b2, final boolean b3, final boolean b4, final boolean b5, final boolean b6) {
        final ArrayList<Vec3d> c = new ArrayList<Vec3d>();
        if (b5) {
            Collections.addAll(c, BlockUtil.convertVec3ds(vec3d, EntityUtil2.antiDropOffsetList));
        }
        if (b4) {
            Collections.addAll(c, BlockUtil.convertVec3ds(vec3d, EntityUtil2.platformOffsetList));
        }
        if (b3) {
            Collections.addAll(c, BlockUtil.convertVec3ds(vec3d, EntityUtil2.legOffsetList));
        }
        Collections.addAll(c, BlockUtil.convertVec3ds(vec3d, EntityUtil2.OffsetList));
        if (b2) {
            Collections.addAll(c, BlockUtil.convertVec3ds(vec3d, EntityUtil2.antiStepOffsetList));
        }
        else {
            final List<Vec3d> unsafeBlocksFromVec3d = getUnsafeBlocksFromVec3d(vec3d, 2, false);
            if (unsafeBlocksFromVec3d.size() == 4) {
                for (final Vec3d vec3d2 : unsafeBlocksFromVec3d) {
                    switch (BlockUtil.isPositionPlaceable(new BlockPos(vec3d).add(vec3d2.x, vec3d2.y, vec3d2.z), b6)) {
                        case -1:
                        case 1:
                        case 2: {
                            continue;
                        }
                        case 3: {
                            c.add(vec3d.add(vec3d2));
                            break;
                        }
                    }
                    if (b) {
                        Collections.addAll(c, BlockUtil.convertVec3ds(vec3d, EntityUtil2.antiScaffoldOffsetList));
                    }
                    return c;
                }
            }
        }
        if (b) {
            Collections.addAll(c, BlockUtil.convertVec3ds(vec3d, EntityUtil2.antiScaffoldOffsetList));
        }
        return c;
    }
    
    public static boolean isBedrockHole(final BlockPos blockPos) {
        final BlockPos[] array = { blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down() };
        for (int length = array.length, i = 0; i < length; ++i) {
            final IBlockState getBlockState = EntityUtil2.mc.world.getBlockState(array[i]);
            if (getBlockState.getBlock() == Blocks.AIR || getBlockState.getBlock() != Blocks.BEDROCK) {
                return false;
            }
        }
        return true;
    }
    
    public static List<Vec3d> getUntrappedBlocksExtended(final int n, final EntityPlayer entityPlayer, final boolean b, final boolean b2, final boolean b3, final boolean b4, final boolean b5, final boolean b6, final boolean b7, final boolean b8) {
        final ArrayList<Vec3d> list = new ArrayList<Vec3d>();
        if (n == 1) {
            list.addAll(targets(entityPlayer.getPositionVector(), b, b2, b3, b4, b5, b6));
        }
        else {
            int n2 = 1;
            for (final Vec3d vec3d : MathUtil.getBlockBlocks((Entity)entityPlayer)) {
                if (n2 > n) {
                    break;
                }
                list.addAll(targets(vec3d, !b7, b2, b3, b4, b5, b6));
                ++n2;
            }
        }
        final ArrayList<Vec3d> list2 = new ArrayList<Vec3d>();
        for (final Vec3d e : list) {
            if (BlockUtil.isPositionPlaceable(new BlockPos(e), b6) != -1) {
                continue;
            }
            list2.add(e);
        }
        final Iterator<Vec3d> iterator3 = list2.iterator();
        while (iterator3.hasNext()) {
            list.remove(iterator3.next());
        }
        return list;
    }
    
    public static double getBaseMovementSpeed() {
        if (EntityUtil2.mc.player.isSneaking()) {
            return 0.064755;
        }
        return EntityUtil2.mc.player.isSprinting() ? 0.2806 : 0.21585;
    }
    
    public static Vec3d getInterpolatedAmount(final Entity entity, final float n) {
        return getInterpolatedAmount(entity, n, n, n);
    }
    
    public static void attackEntityCrystal(final Entity entity) {
        EntityUtil2.mc.playerController.attackEntity((EntityPlayer)EntityUtil2.mc.player, entity);
    }
    
    public static Vec3d getInterpolatedPos(final Entity entity, final float n) {
        return new Vec3d(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ).add(getInterpolatedAmount(entity, n));
    }
    
    public static boolean rayTraceHitCheck(final Entity entity, final boolean b) {
        return !b || EntityUtil2.mc.player.canEntityBeSeen(entity);
    }
    
    public static EntityPlayer getClosestEnemy(final double n) {
        Entity entity = null;
        for (final EntityPlayer entityPlayer : EntityUtil2.mc.world.playerEntities) {
            if (isntValid((Entity)entityPlayer, n)) {
                continue;
            }
            if (entity == null) {
                entity = (Entity)entityPlayer;
            }
            else {
                if (EntityUtil2.mc.player.getDistanceSq((Entity)entityPlayer) >= EntityUtil2.mc.player.getDistanceSq(entity)) {
                    continue;
                }
                entity = (Entity)entityPlayer;
            }
        }
        return (EntityPlayer)entity;
    }
    
    public static boolean isObbyHole(final BlockPos blockPos) {
        final BlockPos[] array = { blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down() };
        for (int length = array.length, i = 0; i < length; ++i) {
            final IBlockState getBlockState = EntityUtil2.mc.world.getBlockState(array[i]);
            if (getBlockState.getBlock() == Blocks.AIR || getBlockState.getBlock() != Blocks.OBSIDIAN) {
                return false;
            }
        }
        return true;
    }
    
    public static void attackEntity(final Entity entity, final boolean b, final boolean b2) {
        if (b) {
            EntityUtil2.mc.player.connection.sendPacket((Packet)new CPacketUseEntity(entity));
        }
        else {
            EntityUtil2.mc.playerController.attackEntity((EntityPlayer)EntityUtil2.mc.player, entity);
        }
        if (b2) {
            EntityUtil2.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
    }
    
    public static boolean isAboveWater(final Entity entity) {
        return isAboveWater(entity, false);
    }
    
    public static double getRange(final Entity entity) {
        return EntityUtil2.mc.player.getPositionVector().add(0.0, (double)EntityUtil2.mc.player.eyeHeight, 0.0).distanceTo(entity.getPositionVector().add(0.0, entity.height / 2.0, 0.0));
    }
    
    public static Map<String, Integer> getTextRadarPlayers() {
        Map<String, Integer> sortByValue = new HashMap<String, Integer>();
        final DecimalFormat decimalFormat = new DecimalFormat("#.#");
        decimalFormat.setRoundingMode(RoundingMode.CEILING);
        final DecimalFormat decimalFormat2 = new DecimalFormat("#.#");
        decimalFormat2.setRoundingMode(RoundingMode.CEILING);
        final StringBuilder obj = new StringBuilder();
        final StringBuilder obj2 = new StringBuilder();
        for (final EntityPlayer entityPlayer : EntityUtil2.mc.world.playerEntities) {
            if (!entityPlayer.isInvisible()) {
                if (entityPlayer.getName().equals(EntityUtil2.mc.player.getName())) {
                    continue;
                }
                final int n = (int)getHealth((Entity)entityPlayer);
                final String format = decimalFormat.format(n);
                obj.append("\u00c2§");
                if (n >= 20) {
                    obj.append("a");
                }
                else if (n >= 10) {
                    obj.append("e");
                }
                else if (n >= 5) {
                    obj.append("6");
                }
                else {
                    obj.append("c");
                }
                obj.append(format);
                final int n2 = (int)EntityUtil2.mc.player.getDistance((Entity)entityPlayer);
                final String format2 = decimalFormat2.format(n2);
                obj2.append("\u00c2§");
                if (n2 >= 25) {
                    obj2.append("a");
                }
                else if (n2 > 10) {
                    obj2.append("6");
                }
                else {
                    obj2.append("c");
                }
                obj2.append(format2);
                sortByValue.put(String.valueOf(new StringBuilder().append(String.valueOf(obj)).append(" ").append(LuigiHack.friendManager.isFriend(entityPlayer) ? ChatFormatting.AQUA : ChatFormatting.RED).append(entityPlayer.getName()).append(" ").append(String.valueOf(obj2)).append(" \u00c2§f0")), (int)EntityUtil2.mc.player.getDistance((Entity)entityPlayer));
                obj.setLength(0);
                obj2.setLength(0);
            }
        }
        if (!sortByValue.isEmpty()) {
            sortByValue = (Map<String, Integer>)MathUtil.sortByValue((Map)sortByValue, false);
        }
        return sortByValue;
    }
    
    public static boolean onMovementInput() {
        return EntityUtil2.mc.player.movementInput.moveForward != 0.0f || EntityUtil2.mc.player.movementInput.moveStrafe != 0.0f;
    }
    
    public static boolean isntValid(final Entity entity, final double n) {
        return entity == null || isDead(entity) || entity.equals((Object)EntityUtil2.mc.player) || (entity instanceof EntityPlayer && LuigiHack.friendManager.isFriend(entity.getName())) || EntityUtil2.mc.player.getDistanceSq(entity) > MathUtil.square(n);
    }
    
    public static int toMode(final String s) {
        if (s.equalsIgnoreCase("Closest")) {
            return 0;
        }
        if (s.equalsIgnoreCase("Lowest Health")) {
            return 1;
        }
        if (s.equalsIgnoreCase("Highest Health")) {
            return 2;
        }
        throw new IllegalArgumentException(s);
    }
    
    public static Vec3d getInterpolatedAmount(final Entity entity, final Vec3d vec3d) {
        return getInterpolatedAmount(entity, vec3d.x, vec3d.y, vec3d.z);
    }
    
    public static boolean isTrapped(final EntityPlayer entityPlayer, final boolean b, final boolean b2, final boolean b3, final boolean b4, final boolean b5, final boolean b6) {
        return getUntrappedBlocks(entityPlayer, b, b2, b3, b4, b5).size() == 0;
    }
    
    public static boolean isProjectile(final Entity entity) {
        return entity instanceof EntityShulkerBullet || entity instanceof EntityFireball;
    }
    
    public static Block getBlockOnPos(final BlockPos blockPos) {
        return EntityUtil2.mc.world.getBlockState(blockPos).getBlock();
    }
    
    public static boolean isSafe(final Entity entity) {
        return isSafe(entity, 0, false);
    }
    
    public static void OffhandAttack(final Entity entity, final boolean b, final boolean b2) {
        if (b) {
            EntityUtil2.mc.player.connection.sendPacket((Packet)new CPacketUseEntity(entity));
        }
        else {
            EntityUtil2.mc.playerController.attackEntity((EntityPlayer)EntityUtil2.mc.player, entity);
        }
        if (b2) {
            EntityUtil2.mc.player.swingArm(EnumHand.OFF_HAND);
        }
    }
    
    public static BlockPos getRoundedBlockPos(final Entity entity) {
        return new BlockPos(MathUtil.roundVec(entity.getPositionVector(), 0));
    }
    
    public static boolean isSafe(final Entity entity, final int n, final boolean b) {
        return getUnsafeBlocks(entity, n, b).size() == 0;
    }
    
    public static boolean getSurroundWeakness(final Vec3d vec3d, final int n, final int n2) {
        switch (n) {
            case 1: {
                final BlockPos blockPos = new BlockPos(vec3d);
                if (!BlockUtil2.canBlockBeSeen((double)(blockPos.getX() - 2), (double)blockPos.getY(), (double)blockPos.getZ()) && Math.sqrt(EntityUtil2.mc.player.getDistanceSq((double)(blockPos.getX() - 2), (double)blockPos.getY(), (double)blockPos.getZ())) > 3.0) {
                    return false;
                }
                final Block getBlock = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(-2, 1, 0)).getBlock();
                final Block getBlock2;
                final Block getBlock3;
                if ((getBlock != Blocks.AIR && getBlock != Blocks.FIRE) || ((getBlock2 = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(-2, 0, 0)).getBlock()) != Blocks.AIR && getBlock2 != Blocks.FIRE) || ((getBlock3 = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(-2, -1, 0)).getBlock()) != Blocks.OBSIDIAN && getBlock3 != Blocks.BEDROCK)) {
                    break;
                }
                if (EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(-1, 0, 0)).getBlock() == Blocks.BEDROCK) {
                    break;
                }
                return true;
            }
            case 2: {
                final BlockPos blockPos2 = new BlockPos(vec3d);
                if (!BlockUtil2.canBlockBeSeen((double)(blockPos2.getX() + 2), (double)blockPos2.getY(), (double)blockPos2.getZ()) && Math.sqrt(EntityUtil2.mc.player.getDistanceSq((double)(blockPos2.getX() + 2), (double)blockPos2.getY(), (double)blockPos2.getZ())) > 3.0) {
                    return false;
                }
                final Block getBlock4 = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(2, 1, 0)).getBlock();
                final Block getBlock5;
                final Block getBlock6;
                if ((getBlock4 != Blocks.AIR && getBlock4 != Blocks.FIRE) || ((getBlock5 = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(2, 0, 0)).getBlock()) != Blocks.AIR && getBlock5 != Blocks.FIRE) || ((getBlock6 = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(2, -1, 0)).getBlock()) != Blocks.OBSIDIAN && getBlock6 != Blocks.BEDROCK)) {
                    break;
                }
                if (EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(1, 0, 0)).getBlock() == Blocks.BEDROCK) {
                    break;
                }
                return true;
            }
            case 3: {
                final BlockPos blockPos3 = new BlockPos(vec3d);
                if (!BlockUtil2.canBlockBeSeen((double)blockPos3.getX(), (double)blockPos3.getY(), (double)(blockPos3.getZ() - 2)) && Math.sqrt(EntityUtil2.mc.player.getDistanceSq((double)blockPos3.getX(), (double)blockPos3.getY(), (double)(blockPos3.getZ() - 2))) > 3.0) {
                    return false;
                }
                final Block getBlock7 = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, 1, -2)).getBlock();
                final Block getBlock8;
                final Block getBlock9;
                if ((getBlock7 != Blocks.AIR && getBlock7 != Blocks.FIRE) || ((getBlock8 = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, -2)).getBlock()) != Blocks.AIR && getBlock8 != Blocks.FIRE) || ((getBlock9 = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, -1, -2)).getBlock()) != Blocks.OBSIDIAN && getBlock9 != Blocks.BEDROCK)) {
                    break;
                }
                if (EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, -1)).getBlock() == Blocks.BEDROCK) {
                    break;
                }
                return true;
            }
            case 4: {
                final BlockPos blockPos4 = new BlockPos(vec3d);
                if (!BlockUtil2.canBlockBeSeen((double)blockPos4.getX(), (double)blockPos4.getY(), (double)(blockPos4.getZ() + 2)) && Math.sqrt(EntityUtil2.mc.player.getDistanceSq((double)blockPos4.getX(), (double)blockPos4.getY(), (double)(blockPos4.getZ() + 2))) > 3.0) {
                    return false;
                }
                final Block getBlock10 = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, 1, 2)).getBlock();
                final Block getBlock11;
                final Block getBlock12;
                if ((getBlock10 != Blocks.AIR && getBlock10 != Blocks.FIRE) || ((getBlock11 = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, 2)).getBlock()) != Blocks.AIR && getBlock11 != Blocks.FIRE) || ((getBlock12 = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, -1, 2)).getBlock()) != Blocks.OBSIDIAN && getBlock12 != Blocks.BEDROCK)) {
                    break;
                }
                if (EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, 1)).getBlock() == Blocks.BEDROCK) {
                    break;
                }
                return true;
            }
            case 5: {
                final BlockPos blockPos5 = new BlockPos(vec3d);
                if (!BlockUtil2.canBlockBeSeen((double)(blockPos5.getX() - 1), (double)blockPos5.getY(), (double)blockPos5.getZ()) && Math.sqrt(EntityUtil2.mc.player.getDistanceSq((double)(blockPos5.getX() - 1), (double)blockPos5.getY(), (double)blockPos5.getZ())) > 3.0) {
                    return false;
                }
                final Block getBlock13 = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(-1, 1, 0)).getBlock();
                if (getBlock13 != Blocks.AIR && getBlock13 != Blocks.FIRE) {
                    break;
                }
                if (EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(-1, 0, 0)).getBlock() == Blocks.BEDROCK) {
                    break;
                }
                return true;
            }
            case 6: {
                final BlockPos blockPos6 = new BlockPos(vec3d);
                if (!BlockUtil2.canBlockBeSeen((double)(blockPos6.getX() + 1), (double)blockPos6.getY(), (double)blockPos6.getZ()) && Math.sqrt(EntityUtil2.mc.player.getDistanceSq((double)(blockPos6.getX() + 1), (double)blockPos6.getY(), (double)blockPos6.getZ())) > 3.0) {
                    return false;
                }
                final Block getBlock14 = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(1, 1, 0)).getBlock();
                if (getBlock14 != Blocks.AIR && getBlock14 != Blocks.FIRE) {
                    break;
                }
                if (EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(1, 0, 0)).getBlock() == Blocks.BEDROCK) {
                    break;
                }
                return true;
            }
            case 7: {
                final BlockPos blockPos7 = new BlockPos(vec3d);
                if (!BlockUtil2.canBlockBeSeen((double)blockPos7.getX(), (double)blockPos7.getY(), (double)(blockPos7.getZ() - 1)) && Math.sqrt(EntityUtil2.mc.player.getDistanceSq((double)blockPos7.getX(), (double)blockPos7.getY(), (double)(blockPos7.getZ() - 1))) > 3.0) {
                    return false;
                }
                final Block getBlock15 = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, 1, -1)).getBlock();
                if (getBlock15 != Blocks.AIR && getBlock15 != Blocks.FIRE) {
                    break;
                }
                if (EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, -1)).getBlock() == Blocks.BEDROCK) {
                    break;
                }
                return true;
            }
            case 8: {
                final BlockPos blockPos8 = new BlockPos(vec3d);
                if (!BlockUtil2.canBlockBeSeen((double)blockPos8.getX(), (double)blockPos8.getY(), (double)(blockPos8.getZ() + 1)) && Math.sqrt(EntityUtil2.mc.player.getDistanceSq((double)blockPos8.getX(), (double)blockPos8.getY(), (double)(blockPos8.getZ() + 1))) > 3.0) {
                    return false;
                }
                final Block getBlock16 = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, 1, 1)).getBlock();
                if (getBlock16 != Blocks.AIR && getBlock16 != Blocks.FIRE) {
                    break;
                }
                if (EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, 1)).getBlock() == Blocks.BEDROCK) {
                    break;
                }
                return true;
            }
        }
        switch (n2) {
            case 1: {
                final Block getBlock17 = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(-2, 1, 0)).getBlock();
                final Block getBlock18;
                final Block getBlock19;
                if ((getBlock17 != Blocks.AIR && getBlock17 != Blocks.FIRE) || ((getBlock18 = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(-2, 0, 0)).getBlock()) != Blocks.AIR && getBlock18 != Blocks.FIRE) || ((getBlock19 = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(-2, -1, 0)).getBlock()) != Blocks.OBSIDIAN && getBlock19 != Blocks.BEDROCK)) {
                    break;
                }
                if (EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(-1, 0, 0)).getBlock() == Blocks.BEDROCK) {
                    break;
                }
                return true;
            }
            case 2: {
                final Block getBlock20 = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(2, 1, 0)).getBlock();
                final Block getBlock21;
                final Block getBlock22;
                if ((getBlock20 != Blocks.AIR && getBlock20 != Blocks.FIRE) || ((getBlock21 = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(2, 0, 0)).getBlock()) != Blocks.AIR && getBlock21 != Blocks.FIRE) || ((getBlock22 = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(2, -1, 0)).getBlock()) != Blocks.OBSIDIAN && getBlock22 != Blocks.BEDROCK)) {
                    break;
                }
                if (EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(1, 0, 0)).getBlock() == Blocks.BEDROCK) {
                    break;
                }
                return true;
            }
            case 3: {
                final Block getBlock23 = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, 1, -2)).getBlock();
                final Block getBlock24;
                final Block getBlock25;
                if ((getBlock23 != Blocks.AIR && getBlock23 != Blocks.FIRE) || ((getBlock24 = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, -2)).getBlock()) != Blocks.AIR && getBlock24 != Blocks.FIRE) || ((getBlock25 = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, -1, -2)).getBlock()) != Blocks.OBSIDIAN && getBlock25 != Blocks.BEDROCK)) {
                    break;
                }
                if (EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, -1)).getBlock() == Blocks.BEDROCK) {
                    break;
                }
                return true;
            }
            case 4: {
                final Block getBlock26 = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, 1, 2)).getBlock();
                final Block getBlock27;
                final Block getBlock28;
                if ((getBlock26 != Blocks.AIR && getBlock26 != Blocks.FIRE) || ((getBlock27 = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, 2)).getBlock()) != Blocks.AIR && getBlock27 != Blocks.FIRE) || ((getBlock28 = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, -1, 2)).getBlock()) != Blocks.OBSIDIAN && getBlock28 != Blocks.BEDROCK)) {
                    break;
                }
                if (EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, 1)).getBlock() == Blocks.BEDROCK) {
                    break;
                }
                return true;
            }
            case 5: {
                final Block getBlock29 = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(-1, 1, 0)).getBlock();
                if (getBlock29 != Blocks.AIR && getBlock29 != Blocks.FIRE) {
                    break;
                }
                if (EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(-1, 0, 0)).getBlock() == Blocks.BEDROCK) {
                    break;
                }
                return true;
            }
            case 6: {
                final Block getBlock30 = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(1, 1, 0)).getBlock();
                if (getBlock30 != Blocks.AIR && getBlock30 != Blocks.FIRE) {
                    break;
                }
                if (EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(1, 0, 0)).getBlock() == Blocks.BEDROCK) {
                    break;
                }
                return true;
            }
            case 7: {
                final Block getBlock31 = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, 1, -1)).getBlock();
                if (getBlock31 != Blocks.AIR && getBlock31 != Blocks.FIRE) {
                    break;
                }
                if (EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, -1)).getBlock() == Blocks.BEDROCK) {
                    break;
                }
                return true;
            }
            case 8: {
                final Block getBlock32 = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, 1, 1)).getBlock();
                if (getBlock32 != Blocks.AIR && getBlock32 != Blocks.FIRE) {
                    break;
                }
                if (EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, 1)).getBlock() == Blocks.BEDROCK) {
                    break;
                }
                return true;
            }
        }
        return false;
    }
    
    public static List<Vec3d> getUnsafeBlocksFromVec3d(final Vec3d vec3d, final int n, final boolean b, final boolean b2) {
        final ArrayList<Vec3d> list = new ArrayList<Vec3d>();
        for (final Vec3d e : getOffsets(n, b, b2)) {
            final Block getBlock = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(e.x, e.y, e.z)).getBlock();
            if (getBlock instanceof BlockAir || getBlock instanceof BlockLiquid || getBlock instanceof BlockTallGrass || getBlock instanceof BlockFire || getBlock instanceof BlockDeadBush || getBlock instanceof BlockSnow) {
                list.add(e);
            }
        }
        return list;
    }
    
    public static BlockPos getLocalPlayerPosFloored() {
        return new BlockPos(Math.floor(EntityUtil2.mc.player.posX), Math.floor(EntityUtil2.mc.player.posY), Math.floor(EntityUtil2.mc.player.posZ));
    }
    
    public static Vec3d[] getUnsafeBlockArray(final Entity entity, final int n, final boolean b) {
        final List<Vec3d> unsafeBlocks = getUnsafeBlocks(entity, n, b);
        return unsafeBlocks.toArray(new Vec3d[unsafeBlocks.size()]);
    }
    
    public static boolean isAboveBlock(final Entity entity, final BlockPos blockPos) {
        return entity.posY >= blockPos.getY();
    }
    
    public static List<Vec3d> getUnsafeBlocks(final Entity entity, final int n, final boolean b, final boolean b2) {
        return getUnsafeBlocksFromVec3d(entity.getPositionVector().add(0.0, 0.125, 0.0), n, b, b2);
    }
    
    public static List<BlockPos> getSphere(final BlockPos blockPos, final float n, final int n2, final boolean b, final boolean b2, final int n3) {
        final ArrayList<BlockPos> list = new ArrayList<BlockPos>();
        final int getX = blockPos.getX();
        final int getY = blockPos.getY();
        final int getZ = blockPos.getZ();
        for (int n4 = getX - (int)n; n4 <= getX + n; ++n4) {
            for (int n5 = getZ - (int)n; n5 <= getZ + n; ++n5) {
                for (int n6 = b2 ? (getY - (int)n) : getY; n6 < (b2 ? (getY + n) : ((float)(getY + n2))); ++n6) {
                    final double n7 = (getX - n4) * (getX - n4) + (getZ - n5) * (getZ - n5) + (b2 ? ((getY - n6) * (getY - n6)) : 0);
                    if (n7 < n * n && (!b || n7 >= (n - 1.0f) * (n - 1.0f))) {
                        list.add(new BlockPos(n4, n6 + n3, n5));
                    }
                }
            }
        }
        return list;
    }
    
    public static boolean isTrapped(final EntityPlayer entityPlayer, final boolean b, final boolean b2, final boolean b3, final boolean b4, final boolean b5) {
        return getUntrappedBlocks(entityPlayer, b, b2, b3, b4, b5).size() == 0;
    }
    
    public static boolean isPassive(final Entity entity) {
        return (!(entity instanceof EntityWolf) || !((EntityWolf)entity).isAngry()) && (entity instanceof EntityAgeable || entity instanceof EntityAmbientCreature || entity instanceof EntitySquid || (entity instanceof EntityIronGolem && ((EntityIronGolem)entity).getRevengeTarget() == null));
    }
    
    public static double getDistPlayerToBlock(final Entity entity, final BlockPos blockPos) {
        return getDistance(entity.posX, entity.posY, entity.posZ, blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }
    
    public static boolean isLiving(final Entity entity) {
        return entity instanceof EntityLivingBase;
    }
    
    public static BlockPos getPlayerPosWithEntity() {
        return new BlockPos((EntityUtil2.mc.player.getRidingEntity() != null) ? EntityUtil2.mc.player.getRidingEntity().posX : EntityUtil2.mc.player.posX, (EntityUtil2.mc.player.getRidingEntity() != null) ? EntityUtil2.mc.player.getRidingEntity().posY : EntityUtil2.mc.player.posY, (EntityUtil2.mc.player.getRidingEntity() != null) ? EntityUtil2.mc.player.getRidingEntity().posZ : EntityUtil2.mc.player.posZ);
    }
    
    public static boolean isFriendlyMob(final Entity entity) {
        return (entity.isCreatureType(EnumCreatureType.CREATURE, false) && !isNeutralMob(entity)) || entity.isCreatureType(EnumCreatureType.AMBIENT, false) || entity instanceof EntityVillager || entity instanceof EntityIronGolem || (isNeutralMob(entity) && !isMobAggressive(entity));
    }
    
    public static boolean isPlayer(final Entity entity) {
        return entity instanceof EntityPlayer;
    }
}
