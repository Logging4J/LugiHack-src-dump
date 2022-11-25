//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util.skid.oyvey;

import net.minecraft.enchantment.*;
import net.minecraft.potion.*;
import java.util.stream.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.entity.*;
import javax.annotation.*;
import net.minecraft.block.*;
import net.minecraft.entity.player.*;
import net.minecraft.init.*;
import java.util.*;
import net.minecraft.network.*;
import net.minecraft.network.play.client.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.block.state.*;
import me.snow.luigihack.api.util.*;
import me.snow.luigihack.*;
import net.minecraft.entity.item.*;

public class CrystalUtil implements Util
{
    public static float getBlastReduction(final EntityLivingBase entityLivingBase, float a, final Explosion explosion) {
        a = CombatRules.getDamageAfterAbsorb(a, (float)entityLivingBase.getTotalArmorValue(), (float)entityLivingBase.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue());
        float n = 0.0f;
        try {
            n = (float)EnchantmentHelper.getEnchantmentModifierDamage(entityLivingBase.getArmorInventoryList(), DamageSource.causeExplosionDamage(explosion));
        }
        catch (Exception ex) {}
        a *= 1.0f - MathHelper.clamp(n, 0.0f, 20.0f) / 25.0f;
        final PotionEffect getActivePotionEffect = entityLivingBase.getActivePotionEffect(MobEffects.RESISTANCE);
        if (entityLivingBase.isPotionActive(MobEffects.RESISTANCE) && getActivePotionEffect != null) {
            a = a * (25.0f - (getActivePotionEffect.getAmplifier() + 1) * 5.0f) / 25.0f;
        }
        a = Math.max(a, 0.0f);
        return a;
    }
    
    public static List<BlockPos> possiblePlacePositions(final float n, final boolean b, final boolean b2) {
        final NonNullList create = NonNullList.create();
        create.addAll((Collection)getSphere(PlayerUtil.getPlayerPos(), n, (int)n, false, true, 0).stream().filter(blockPos -> CrystalUtil.mc.world.getBlockState(blockPos).getBlock() != Blocks.AIR).filter(blockPos2 -> canPlaceCrystal(blockPos2, b, b2)).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList()));
        return (List<BlockPos>)create;
    }
    
    public static EnumActionResult placeCrystal(final BlockPos blockPos) {
        blockPos.offset(EnumFacing.DOWN);
        final double n = blockPos.getX() + 0.5 - CrystalUtil.mc.player.posX;
        final double n2 = blockPos.getY() + 0.5 - CrystalUtil.mc.player.posY - 0.5 - CrystalUtil.mc.player.getEyeHeight();
        final double n3 = blockPos.getZ() + 0.5 - CrystalUtil.mc.player.posZ;
        final Vec3d vectorForRotation = getVectorForRotation(-getDirection2D(n2, Math.sqrt(n * n + n3 * n3)), getDirection2D(n3, n) - 90.0);
        if (((ItemStack)CrystalUtil.mc.player.inventory.offHandInventory.get(0)).getItem().getClass().equals(Item.getItemById(426).getClass())) {
            return CrystalUtil.mc.playerController.processRightClickBlock(CrystalUtil.mc.player, CrystalUtil.mc.world, blockPos.offset(EnumFacing.DOWN), EnumFacing.UP, vectorForRotation, EnumHand.OFF_HAND);
        }
        if (InventoryUtil2.pickItem(426, false) != -1) {
            InventoryUtil2.setSlot(InventoryUtil2.pickItem(426, false));
            return CrystalUtil.mc.playerController.processRightClickBlock(CrystalUtil.mc.player, CrystalUtil.mc.world, blockPos.offset(EnumFacing.DOWN), EnumFacing.UP, vectorForRotation, EnumHand.MAIN_HAND);
        }
        return EnumActionResult.FAIL;
    }
    
    public static double calculateXOffset(final AxisAlignedBB axisAlignedBB, double n, final AxisAlignedBB axisAlignedBB2) {
        if (axisAlignedBB.maxY > axisAlignedBB2.minY && axisAlignedBB.minY < axisAlignedBB2.maxY && axisAlignedBB.maxZ > axisAlignedBB2.minZ && axisAlignedBB.minZ < axisAlignedBB2.maxZ) {
            if (n > 0.0 && axisAlignedBB.maxX <= axisAlignedBB2.minX) {
                final double n2 = axisAlignedBB2.minX - 0.3 - axisAlignedBB.maxX;
                if (n2 < n) {
                    n = n2;
                }
            }
            else {
                final double n3;
                if (n < 0.0 && axisAlignedBB.minX >= axisAlignedBB2.maxX && (n3 = axisAlignedBB2.maxX + 0.3 - axisAlignedBB.minX) > n) {
                    n = n3;
                }
            }
        }
        return n;
    }
    
    public static List<BlockPos> getSphere(final Vec3d vec3d, final double n, final double n2, final boolean b, final boolean b2, final int n3) {
        final ArrayList<BlockPos> list = new ArrayList<BlockPos>();
        final int n4 = (int)vec3d.x;
        final int n5 = (int)vec3d.y;
        final int n6 = (int)vec3d.z;
        for (int n7 = n4 - (int)n; n7 <= n4 + n; ++n7) {
            for (int n8 = n6 - (int)n; n8 <= n6 + n; ++n8) {
                for (int n9 = b2 ? (n5 - (int)n) : n5; n9 < (b2 ? (n5 + n) : (n5 + n2)); ++n9) {
                    final double n10 = (n4 - n7) * (n4 - n7) + (n6 - n8) * (n6 - n8) + (b2 ? ((n5 - n9) * (n5 - n9)) : 0);
                    if (n10 < n * n && (!b || n10 >= (n - 1.0) * (n - 1.0))) {
                        list.add(new BlockPos(n7, n9 + n3, n8));
                    }
                }
            }
        }
        return list;
    }
    
    static {
        CRYSTAL_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 2.0, 1.0);
    }
    
    public static List<Block> getBlocks() {
        return Arrays.asList(Blocks.OBSIDIAN, Blocks.BEDROCK, Blocks.COMMAND_BLOCK, Blocks.BARRIER, Blocks.ENCHANTING_TABLE, Blocks.ENDER_CHEST, Blocks.END_PORTAL_FRAME, (Block)Blocks.BEACON, Blocks.ANVIL);
    }
    
    public static float getExplosionDamage(final Entity entity, final Vec3d vec3d, float n, final boolean b) {
        final Vec3d vec3d2 = new Vec3d(entity.posX, entity.posY, entity.posZ);
        if (entity.isImmuneToExplosions()) {
            return 0.0f;
        }
        final double n2 = vec3d2.distanceTo(vec3d) / (n *= 2.0f);
        double n3 = 0.0;
        final AxisAlignedBB offset = entity.getEntityBoundingBox().offset(entity.getPositionVector().subtract(vec3d2));
        final Vec3d vec3d3 = new Vec3d(1.0 / ((offset.maxX - offset.minX) * 2.0 + 1.0), 1.0 / ((offset.maxY - offset.minY) * 2.0 + 1.0), 1.0 / ((offset.maxZ - offset.minZ) * 2.0 + 1.0));
        final double n4 = (1.0 - Math.floor(1.0 / vec3d3.x) * vec3d3.x) / 2.0;
        final double n5 = (1.0 - Math.floor(1.0 / vec3d3.z) * vec3d3.z) / 2.0;
        if (vec3d3.x >= 0.0 && vec3d3.y >= 0.0 && vec3d3.z >= 0.0) {
            int n6 = 0;
            int n7 = 0;
            for (double n8 = 0.0; n8 <= 1.0; n8 += vec3d3.x) {
                for (double n9 = 0.0; n9 <= 1.0; n9 += vec3d3.y) {
                    for (double n10 = 0.0; n10 <= 1.0; n10 += vec3d3.z) {
                        if (!rayTraceSolidCheck(new Vec3d(n4 + offset.minX + (offset.maxX - offset.minX) * n8, offset.minY + (offset.maxY - offset.minY) * n9, n5 + offset.minZ + (offset.maxZ - offset.minZ) * n10), vec3d, b)) {
                            ++n6;
                        }
                        ++n7;
                    }
                }
            }
            n3 = n6 / (double)n7;
        }
        final double n11 = (1.0 - n2) * n3;
        float blastReduction = (float)(int)((n11 * n11 + n11) / 2.0 * 7.0 * n + 1.0);
        if (entity instanceof EntityLivingBase) {
            blastReduction = getBlastReduction((EntityLivingBase)entity, getDamageFromDifficulty(blastReduction), new Explosion((World)CrystalUtil.mc.world, (Entity)null, vec3d.x, vec3d.y, vec3d.z, n / 2.0f, false, true));
        }
        return blastReduction;
    }
    
    protected static final Vec3d getVectorForRotation(final double n, final double n2) {
        final float cos = MathHelper.cos((float)(-n2 * 0.01745329238474369 - 3.1415927410125732));
        final float sin = MathHelper.sin((float)(-n2 * 0.01745329238474369 - 3.1415927410125732));
        final float n3 = -MathHelper.cos((float)(-n * 0.01745329238474369));
        return new Vec3d((double)(sin * n3), (double)MathHelper.sin((float)(-n * 0.01745329238474369)), (double)(cos * n3));
    }
    
    public static double getDamage(final Vec3d vec3d, @Nullable final Entity entity) {
        final Object o = (entity == null) ? CrystalUtil.mc.player : entity;
        final float n = 6.0f * 2.0f;
        final double n2;
        final double n4;
        final double n5;
        final double n6;
        final double n3;
        if (!((Entity)o).isImmuneToExplosions() && (n2 = ((Entity)o).getDistance(vec3d.x, vec3d.y, vec3d.z) / n) <= 1.0 && (n3 = MathHelper.sqrt((n4 = ((Entity)o).posX - vec3d.x) * n4 + (n5 = ((Entity)o).posY + ((Entity)o).getEyeHeight() - vec3d.y) * n5 + (n6 = ((Entity)o).posZ - vec3d.z) * n6)) != 0.0) {
            final double n7 = (1.0 - n2) * CrystalUtil.mc.world.getBlockDensity(vec3d, ((Entity)o).getEntityBoundingBox());
            return (int)((n7 * n7 + n7) / 2.0 * 7.0 * n + 1.0);
        }
        return 0.0;
    }
    
    public static double calculateZOffset(final AxisAlignedBB axisAlignedBB, double n, final AxisAlignedBB axisAlignedBB2) {
        if (axisAlignedBB.maxX > axisAlignedBB2.minX && axisAlignedBB.minX < axisAlignedBB2.maxX && axisAlignedBB.maxY > axisAlignedBB2.minY && axisAlignedBB.minY < axisAlignedBB2.maxY) {
            if (n > 0.0 && axisAlignedBB.maxZ <= axisAlignedBB2.minZ) {
                final double n2 = axisAlignedBB2.minZ - 0.3 - axisAlignedBB.maxZ;
                if (n2 < n) {
                    n = n2;
                }
            }
            else {
                final double n3;
                if (n < 0.0 && axisAlignedBB.minZ >= axisAlignedBB2.maxZ && (n3 = axisAlignedBB2.maxZ + 0.3 - axisAlignedBB.minZ) > n) {
                    n = n3;
                }
            }
        }
        return n;
    }
    
    public static List<BlockPos> getSphere(final BlockPos blockPos, final float n, final int n2, final boolean b, final boolean b2, final int n3) {
        final ArrayList<BlockPos> list = new ArrayList<BlockPos>();
        final int getX = blockPos.getX();
        final int getY = blockPos.getY();
        final int getZ = blockPos.getZ();
        for (int n4 = getX - (int)n; n4 <= getX + n; ++n4) {
            for (int n5 = getZ - (int)n; n5 <= getZ + n; ++n5) {
                int n6 = b2 ? (getY - (int)n) : getY;
                while (true) {
                    final float n7 = (float)n6;
                    float n9 = 0.0f;
                    if (b2) {
                        final float n8 = getY + n;
                    }
                    else {
                        n9 = (float)(getY + n2);
                    }
                    if (n7 >= n9) {
                        break;
                    }
                    final double n10 = (getX - n4) * (getX - n4) + (getZ - n5) * (getZ - n5) + (b2 ? ((getY - n6) * (getY - n6)) : 0);
                    if (n10 < n * n && (!b || n10 >= (n - 1.0f) * (n - 1.0f))) {
                        list.add(new BlockPos(n4, n6 + n3, n5));
                    }
                    ++n6;
                }
            }
        }
        return list;
    }
    
    public static List<BlockPos> getSphere(final float n, final boolean b, final boolean b2) {
        final ArrayList<BlockPos> list = new ArrayList<BlockPos>();
        for (int n2 = CrystalUtil.mc.player.getPosition().getX() - (int)n; n2 <= CrystalUtil.mc.player.getPosition().getX() + n; ++n2) {
            for (int n3 = CrystalUtil.mc.player.getPosition().getZ() - (int)n; n3 <= CrystalUtil.mc.player.getPosition().getZ() + n; ++n3) {
                for (int n4 = b ? (CrystalUtil.mc.player.getPosition().getY() - (int)n) : CrystalUtil.mc.player.getPosition().getY(); n4 < CrystalUtil.mc.player.getPosition().getY() + n; ++n4) {
                    final double n5 = (CrystalUtil.mc.player.getPosition().getX() - n2) * (CrystalUtil.mc.player.getPosition().getX() - n2) + (CrystalUtil.mc.player.getPosition().getZ() - n3) * (CrystalUtil.mc.player.getPosition().getZ() - n3) + (b ? ((CrystalUtil.mc.player.getPosition().getY() - n4) * (CrystalUtil.mc.player.getPosition().getY() - n4)) : 0);
                    if (n5 < n * n && (!b2 || n5 >= (n - Double.longBitsToDouble(Double.doubleToLongBits(638.4060856917202) ^ 0x7F73F33FA9DAEA7FL)) * (n - Double.longBitsToDouble(Double.doubleToLongBits(13.015128470890444) ^ 0x7FDA07BEEB3F6D07L)))) {
                        list.add(new BlockPos(n2, n4, n3));
                    }
                }
            }
        }
        return list;
    }
    
    public static float calculateDamage(final Entity entity, final Entity entity2, final boolean b) {
        return getExplosionDamage(entity2, new Vec3d(entity.posX, entity.posY, entity.posZ), 6.0f, b);
    }
    
    public static float getDamageFromDifficulty(final float b) {
        switch (CrystalUtil.mc.world.getDifficulty()) {
            case PEACEFUL: {
                return 0.0f;
            }
            case EASY: {
                return Math.min(b / 2.0f + 1.0f, b);
            }
            case HARD: {
                return b * 3.0f / 2.0f;
            }
            default: {
                return b;
            }
        }
    }
    
    public static float getDamageMultiplied(final float n) {
        final int getId = CrystalUtil.mc.world.getDifficulty().getId();
        return n * ((getId == 0) ? 0.0f : ((getId == 2) ? 1.0f : ((getId == 1) ? 0.5f : 1.5f)));
    }
    
    public static boolean canPlace(final BlockPos blockPos) {
        return CrystalUtil.mc.world.getBlockState(blockPos.offset(EnumFacing.DOWN)).getBlock() instanceof BlockEmptyDrops && CrystalUtil.mc.world.getBlockState(blockPos.offset(EnumFacing.DOWN)).getBlock() instanceof BlockObsidian && CrystalUtil.mc.world.checkNoEntityCollision(new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 2.0, 1.0).offset(blockPos), (Entity)null);
    }
    
    public static Boolean getArmorBreaker(final EntityPlayer entityPlayer, final float n) {
        for (final ItemStack itemStack : entityPlayer.getArmorInventoryList()) {
            if (itemStack == null || itemStack.getItem() == Items.AIR) {
                return true;
            }
            if (n < (itemStack.getMaxDamage() - itemStack.getItemDamage()) / (float)itemStack.getMaxDamage() * 100.0f) {
                continue;
            }
            if (itemStack.stackSize >= 2) {
                continue;
            }
            return true;
        }
        return false;
    }
    
    public static float calculateDamage(final double n, final double n2, final double n3, final Entity entity) {
        return calculateDamage(n, n2, n3, entity, new Vec3d(entity.posX, entity.posY, entity.posZ));
    }
    
    public static boolean placeCrystalSilent(final BlockPos blockPos) {
        blockPos.offset(EnumFacing.DOWN);
        final double n = blockPos.getX() + 0.5 - CrystalUtil.mc.player.posX;
        final double n2 = blockPos.getY() + 0.5 - CrystalUtil.mc.player.posY - 0.5 - CrystalUtil.mc.player.getEyeHeight();
        final double n3 = blockPos.getZ() + 0.5 - CrystalUtil.mc.player.posZ;
        final double direction2D = getDirection2D(n3, n);
        final double direction2D2 = getDirection2D(n2, Math.sqrt(n * n + n3 * n3));
        final int pickItem = InventoryUtil2.pickItem(426, false);
        if (pickItem == -1 && ((ItemStack)CrystalUtil.mc.player.inventory.offHandInventory.get(0)).getItem() != Items.END_CRYSTAL) {
            return false;
        }
        getVectorForRotation(-direction2D2, direction2D - 90.0);
        if (((ItemStack)CrystalUtil.mc.player.inventory.offHandInventory.get(0)).getItem() == Items.END_CRYSTAL) {
            CrystalUtil.mc.getConnection().sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos.offset(EnumFacing.DOWN), EnumFacing.UP, EnumHand.OFF_HAND, 0.0f, 0.0f, 0.0f));
        }
        else if (InventoryUtil2.pickItem(426, false) != -1) {
            CrystalUtil.mc.getConnection().sendPacket((Packet)new CPacketHeldItemChange(pickItem));
            CrystalUtil.mc.getConnection().sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos.offset(EnumFacing.DOWN), EnumFacing.UP, EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
        }
        return true;
    }
    
    public static void placeCrystalOnBlock(final BlockPos blockPos, final EnumHand enumHand, final boolean b, final boolean b2, final boolean b3) {
        final RayTraceResult rayTraceBlocks = BlockUtil.mc.world.rayTraceBlocks(new Vec3d(BlockUtil.mc.player.posX, BlockUtil.mc.player.posY + BlockUtil.mc.player.getEyeHeight(), BlockUtil.mc.player.posZ), new Vec3d(blockPos.getX() + 0.5, blockPos.getY() - 0.5, blockPos.getZ() + 0.5));
        final EnumFacing enumFacing = (rayTraceBlocks == null || rayTraceBlocks.sideHit == null) ? EnumFacing.UP : rayTraceBlocks.sideHit;
        final int currentItem = BlockUtil.mc.player.inventory.currentItem;
        final int itemHotbar = InventoryUtil2.getItemHotbar(Items.END_CRYSTAL);
        if (enumHand == EnumHand.MAIN_HAND && b3 && itemHotbar != -1 && itemHotbar != BlockUtil.mc.player.inventory.currentItem) {
            BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(itemHotbar));
        }
        BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos, enumFacing, enumHand, 0.0f, 0.0f, 0.0f));
        if (enumHand == EnumHand.MAIN_HAND && b3 && itemHotbar != -1 && itemHotbar != BlockUtil.mc.player.inventory.currentItem) {
            BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(currentItem));
        }
        if (b) {
            BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketAnimation(b2 ? enumHand : EnumHand.MAIN_HAND));
        }
    }
    
    public static float calculateDamage(final BlockPos blockPos, final Entity entity, final boolean b) {
        return getExplosionDamage(entity, new Vec3d(blockPos.getX() + 0.5, (double)(blockPos.getY() + 1), blockPos.getZ() + 0.5), 6.0f, b);
    }
    
    public static boolean rayTraceSolidCheck(Vec3d vec3d, final Vec3d vec3d2, final boolean b) {
        if (!Double.isNaN(vec3d.x) && !Double.isNaN(vec3d.y) && !Double.isNaN(vec3d.z) && !Double.isNaN(vec3d2.x) && !Double.isNaN(vec3d2.y) && !Double.isNaN(vec3d2.z)) {
            int floor = MathHelper.floor(vec3d.x);
            int floor2 = MathHelper.floor(vec3d.y);
            int floor3 = MathHelper.floor(vec3d.z);
            final int floor4 = MathHelper.floor(vec3d2.x);
            final int floor5 = MathHelper.floor(vec3d2.y);
            final int floor6 = MathHelper.floor(vec3d2.z);
            final BlockPos blockPos = new BlockPos(floor, floor2, floor3);
            final IBlockState getBlockState = CrystalUtil.mc.world.getBlockState(blockPos);
            final Block getBlock = getBlockState.getBlock();
            if (getBlockState.getCollisionBoundingBox((IBlockAccess)CrystalUtil.mc.world, blockPos) != Block.NULL_AABB && getBlock.canCollideCheck(getBlockState, false) && (getBlocks().contains(getBlock) || !b)) {
                return true;
            }
            final double n = vec3d2.x - vec3d.x;
            final double n2 = vec3d2.y - vec3d.y;
            final double n3 = vec3d2.z - vec3d.z;
            int n4 = 200;
            while (n4-- >= 0) {
                if (Double.isNaN(vec3d.x) || Double.isNaN(vec3d.y) || Double.isNaN(vec3d.z)) {
                    return false;
                }
                if (floor == floor4 && floor2 == floor5 && floor3 == floor6) {
                    return false;
                }
                boolean b2 = true;
                boolean b3 = true;
                boolean b4 = true;
                double n5 = 999.0;
                double n6 = 999.0;
                double n7 = 999.0;
                double n8 = 999.0;
                double n9 = 999.0;
                double n10 = 999.0;
                if (floor4 > floor) {
                    n5 = floor + 1.0;
                }
                else if (floor4 < floor) {
                    n5 = floor;
                }
                else {
                    b2 = false;
                }
                if (floor5 > floor2) {
                    n6 = floor2 + 1.0;
                }
                else if (floor5 < floor2) {
                    n6 = floor2;
                }
                else {
                    b3 = false;
                }
                if (floor6 > floor3) {
                    n7 = floor3 + 1.0;
                }
                else if (floor6 < floor3) {
                    n7 = floor3;
                }
                else {
                    b4 = false;
                }
                if (b2) {
                    n8 = (n5 - vec3d.x) / n;
                }
                if (b3) {
                    n9 = (n6 - vec3d.y) / n2;
                }
                if (b4) {
                    n10 = (n7 - vec3d.z) / n3;
                }
                if (n8 == 0.0) {
                    n8 = -1.0E-4;
                }
                if (n9 == 0.0) {
                    n9 = -1.0E-4;
                }
                if (n10 == 0.0) {
                    n10 = -1.0E-4;
                }
                EnumFacing enumFacing;
                if (n8 < n9 && n8 < n10) {
                    enumFacing = ((floor4 > floor) ? EnumFacing.WEST : EnumFacing.EAST);
                    vec3d = new Vec3d(n5, vec3d.y + n2 * n8, vec3d.z + n3 * n8);
                }
                else if (n9 < n10) {
                    enumFacing = ((floor5 > floor2) ? EnumFacing.DOWN : EnumFacing.UP);
                    vec3d = new Vec3d(vec3d.x + n * n9, n6, vec3d.z + n3 * n9);
                }
                else {
                    enumFacing = ((floor6 > floor3) ? EnumFacing.NORTH : EnumFacing.SOUTH);
                    vec3d = new Vec3d(vec3d.x + n * n10, vec3d.y + n2 * n10, n7);
                }
                final IBlockState getBlockState2;
                final Block getBlock2;
                if (!(getBlock2 = (getBlockState2 = CrystalUtil.mc.world.getBlockState(new BlockPos(floor = MathHelper.floor(vec3d.x) - ((enumFacing == EnumFacing.EAST) ? 1 : 0), floor2 = MathHelper.floor(vec3d.y) - ((enumFacing == EnumFacing.UP) ? 1 : 0), floor3 = MathHelper.floor(vec3d.z) - ((enumFacing == EnumFacing.SOUTH) ? 1 : 0)))).getBlock()).canCollideCheck(getBlockState2, false)) {
                    continue;
                }
                if (!getBlocks().contains(getBlock2) && b) {
                    continue;
                }
                return true;
            }
        }
        return false;
    }
    
    public static EntityPlayer getTarget(final float n) {
        Entity entity = null;
        for (final EntityPlayer entityPlayer : new ArrayList<EntityPlayer>(CrystalUtil.mc.world.playerEntities)) {
            if (CrystalUtil.mc.player.getDistanceSq((Entity)entityPlayer) > MathUtil.square(n)) {
                continue;
            }
            if (entityPlayer == CrystalUtil.mc.player) {
                continue;
            }
            if (LuigiHack.friendManager.isFriend(entityPlayer.getName())) {
                continue;
            }
            if (entityPlayer.isDead) {
                continue;
            }
            if (entityPlayer.getHealth() <= Float.intBitsToFloat(Float.floatToIntBits(1.2784752E38f) ^ 0x7EC05D13)) {
                continue;
            }
            if (entity == null) {
                entity = (Entity)entityPlayer;
            }
            else {
                if (CrystalUtil.mc.player.getDistanceSq((Entity)entityPlayer) >= CrystalUtil.mc.player.getDistanceSq(entity)) {
                    continue;
                }
                entity = (Entity)entityPlayer;
            }
        }
        return (EntityPlayer)entity;
    }
    
    public static Entity getPredictedPosition(final Entity entity, final double n) {
        if (n == 0.0) {
            return entity;
        }
        EntityPlayer entityPlayer = null;
        final double a = entity.posX - entity.lastTickPosX;
        double a2 = entity.posY - entity.lastTickPosY;
        final double a3 = entity.posZ - entity.lastTickPosZ;
        boolean b = false;
        boolean b2 = false;
        final double sqrt = Math.sqrt(Math.pow(a, 2.0) + Math.pow(a3, 2.0) + Math.pow(a2, 2.0));
        if (sqrt > 0.1) {
            b = true;
        }
        if (!b) {
            return entity;
        }
        if (sqrt > 0.31) {
            b2 = true;
        }
        for (int n2 = 0; n2 < n; ++n2) {
            if (entityPlayer == null) {
                if (isOnGround(0.0, 0.0, 0.0, entity)) {
                    a2 = (b2 ? 0.4 : -0.07840015258789);
                }
                else {
                    a2 = (a2 - 0.08) * 0.9800000190734863;
                }
                entityPlayer = placeValue(a, a2, a3, (EntityPlayer)entity);
            }
            else {
                if (isOnGround(0.0, 0.0, 0.0, (Entity)entityPlayer)) {
                    a2 = (b2 ? 0.4 : -0.07840015258789);
                }
                else {
                    a2 = (a2 - 0.08) * 0.9800000190734863;
                }
                entityPlayer = placeValue(a, a2, a3, entityPlayer);
            }
        }
        return (Entity)entityPlayer;
    }
    
    public static boolean isOnGround(final double n, double calculateYOffset, final double n2, final Entity entity) {
        try {
            final double n3 = calculateYOffset;
            final List getCollisionBoxes = CrystalUtil.mc.world.getCollisionBoxes(entity, entity.getEntityBoundingBox().expand(n, calculateYOffset, n2));
            if (calculateYOffset != 0.0) {
                for (int size = getCollisionBoxes.size(), i = 0; i < size; ++i) {
                    calculateYOffset = getCollisionBoxes.get(i).calculateYOffset(entity.getEntityBoundingBox(), calculateYOffset);
                }
            }
            return n3 != calculateYOffset && n3 < 0.0;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public static EntityPlayer placeValue(double calculateXOffset, double calculateYOffset, double calculateZOffset, final EntityPlayer entityPlayer) {
        final List getCollisionBoxes = CrystalUtil.mc.world.getCollisionBoxes((Entity)entityPlayer, entityPlayer.getEntityBoundingBox().expand(calculateXOffset, calculateYOffset, calculateZOffset));
        if (calculateYOffset != 0.0) {
            for (int size = getCollisionBoxes.size(), i = 0; i < size; ++i) {
                calculateYOffset = getCollisionBoxes.get(i).calculateYOffset(entityPlayer.getEntityBoundingBox(), calculateYOffset);
            }
            if (calculateYOffset != 0.0) {
                entityPlayer.setEntityBoundingBox(entityPlayer.getEntityBoundingBox().offset(0.0, calculateYOffset, 0.0));
            }
        }
        if (calculateXOffset != 0.0) {
            for (int size2 = getCollisionBoxes.size(), j = 0; j < size2; ++j) {
                calculateXOffset = calculateXOffset(entityPlayer.getEntityBoundingBox(), calculateXOffset, getCollisionBoxes.get(j));
            }
            if (calculateXOffset != 0.0) {
                entityPlayer.setEntityBoundingBox(entityPlayer.getEntityBoundingBox().offset(calculateXOffset, 0.0, 0.0));
            }
        }
        if (calculateZOffset != 0.0) {
            for (int size3 = getCollisionBoxes.size(), k = 0; k < size3; ++k) {
                calculateZOffset = calculateZOffset(entityPlayer.getEntityBoundingBox(), calculateZOffset, getCollisionBoxes.get(k));
            }
            if (calculateZOffset != 0.0) {
                entityPlayer.setEntityBoundingBox(entityPlayer.getEntityBoundingBox().offset(0.0, 0.0, calculateZOffset));
            }
        }
        return entityPlayer;
    }
    
    public static EnumActionResult doPlace(final BlockPos blockPos) {
        final double n = blockPos.getX() + 0.5 - CrystalUtil.mc.player.posX;
        final double n2 = blockPos.getY() - 1 + 0.5 - CrystalUtil.mc.player.posY - 0.5 - CrystalUtil.mc.player.getEyeHeight();
        final double n3 = blockPos.getZ() + 0.5 - CrystalUtil.mc.player.posZ;
        return CrystalUtil.mc.playerController.processRightClickBlock(CrystalUtil.mc.player, CrystalUtil.mc.world, blockPos.offset(EnumFacing.DOWN), EnumFacing.UP, getVectorForRotation(-getDirection2D(n2, Math.sqrt(n * n + n3 * n3)), getDirection2D(n3, n) - 90.0), CrystalUtil.mc.player.getActiveHand());
    }
    
    public static boolean canPlaceCrystal(final BlockPos blockPos, final boolean b, final boolean b2) {
        final BlockPos add = blockPos.add(0, 1, 0);
        final BlockPos add2 = blockPos.add(0, 2, 0);
        try {
            if (!b2) {
                if (CrystalUtil.mc.world.getBlockState(blockPos).getBlock() != Blocks.BEDROCK && CrystalUtil.mc.world.getBlockState(blockPos).getBlock() != Blocks.OBSIDIAN) {
                    return false;
                }
                if (CrystalUtil.mc.world.getBlockState(add).getBlock() != Blocks.AIR || CrystalUtil.mc.world.getBlockState(add2).getBlock() != Blocks.AIR) {
                    return false;
                }
                if (!b) {
                    return CrystalUtil.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(add)).isEmpty() && CrystalUtil.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(add2)).isEmpty();
                }
                final Iterator iterator = CrystalUtil.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(add)).iterator();
                while (iterator.hasNext()) {
                    if (iterator.next() instanceof EntityEnderCrystal) {
                        continue;
                    }
                    return false;
                }
                final Iterator iterator2 = CrystalUtil.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(add2)).iterator();
                while (iterator2.hasNext()) {
                    if (iterator2.next() instanceof EntityEnderCrystal) {
                        continue;
                    }
                    return false;
                }
            }
            else {
                if (CrystalUtil.mc.world.getBlockState(blockPos).getBlock() != Blocks.BEDROCK && CrystalUtil.mc.world.getBlockState(blockPos).getBlock() != Blocks.OBSIDIAN) {
                    return false;
                }
                if (CrystalUtil.mc.world.getBlockState(add).getBlock() != Blocks.AIR) {
                    return false;
                }
                if (!b) {
                    return CrystalUtil.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(add)).isEmpty();
                }
                final Iterator iterator3 = CrystalUtil.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(add)).iterator();
                while (iterator3.hasNext()) {
                    if (iterator3.next() instanceof EntityEnderCrystal) {
                        continue;
                    }
                    return false;
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    
    public static float calculateDamage(final double n, final double n2, final double n3, final Entity entity, final Vec3d vec3d) {
        final float n4 = 12.0f;
        final double n5 = (1.0 - getRange(vec3d, n, n2, n3) / n4) * entity.world.getBlockDensity(new Vec3d(n, n2, n3), entity.getEntityBoundingBox());
        final float n6 = (float)(int)((n5 * n5 + n5) / 2.0 * 7.0 * n4 + 1.0);
        double n7 = 1.0;
        if (entity instanceof EntityLivingBase) {
            n7 = getBlastReduction((EntityLivingBase)entity, getDamageMultiplied(n6), new Explosion((World)CrystalUtil.mc.world, (Entity)null, n, n2, n3, 6.0f, false, true));
        }
        return (float)n7;
    }
    
    public static boolean canSeePos(final BlockPos blockPos) {
        return CrystalUtil.mc.world.rayTraceBlocks(new Vec3d(CrystalUtil.mc.player.posX, CrystalUtil.mc.player.posY + CrystalUtil.mc.player.getEyeHeight(), CrystalUtil.mc.player.posZ), new Vec3d((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ()), false, true, false) == null;
    }
    
    protected static final double getDirection2D(final double n, final double n2) {
        double n3;
        if (n2 == 0.0) {
            n3 = ((n > 0.0) ? 90.0 : -90.0);
        }
        else {
            n3 = Math.atan(n / n2) * 57.2957796;
            if (n2 < 0.0) {
                double n4 = 0.0;
                if (n > 0.0) {
                    n4 = n3 + 180.0;
                }
                else if (n < 0.0) {}
                n3 = n4;
            }
        }
        return n3;
    }
    
    public static double getRange(final Vec3d vec3d, final double n, final double n2, final double n3) {
        final double n4 = vec3d.x - n;
        final double n5 = vec3d.y - n2;
        final double n6 = vec3d.z - n3;
        return Math.sqrt(n4 * n4 + n5 * n5 + n6 * n6);
    }
    
    public static void placeCrystalOnBlock(final BlockPos blockPos, final EnumHand enumHand, final boolean b, final boolean b2) {
        final RayTraceResult rayTraceBlocks = BlockUtil.mc.world.rayTraceBlocks(new Vec3d(BlockUtil.mc.player.posX, BlockUtil.mc.player.posY + BlockUtil.mc.player.getEyeHeight(), BlockUtil.mc.player.posZ), new Vec3d(blockPos.getX() + 0.5, blockPos.getY() - 0.5, blockPos.getZ() + 0.5));
        final EnumFacing enumFacing = (rayTraceBlocks == null || rayTraceBlocks.sideHit == null) ? EnumFacing.UP : rayTraceBlocks.sideHit;
        final int currentItem = BlockUtil.mc.player.inventory.currentItem;
        InventoryUtil2.getItemHotbar(Items.END_CRYSTAL);
        BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos, enumFacing, enumHand, 0.0f, 0.0f, 0.0f));
        if (b) {
            BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketAnimation(b2 ? enumHand : EnumHand.MAIN_HAND));
        }
    }
}
