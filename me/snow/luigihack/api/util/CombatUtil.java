//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util;

import net.minecraft.client.*;
import net.minecraft.entity.player.*;
import net.minecraft.network.*;
import java.util.stream.*;
import net.minecraft.network.play.client.*;
import net.minecraft.item.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;
import net.minecraft.enchantment.*;
import net.minecraft.init.*;
import net.minecraft.world.*;
import net.minecraft.inventory.*;
import java.util.*;
import net.minecraft.block.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.projectile.*;
import java.util.concurrent.*;
import me.snow.luigihack.*;

public class CombatUtil
{
    public static final /* synthetic */ List<Block> shulkerList;
    public static final /* synthetic */ List<Block> blackList;
    private static final /* synthetic */ List<Integer> invalidSlots;
    private static /* synthetic */ Minecraft mc;
    
    public static float calculateDamage(final BlockPos blockPos, final Entity entity) {
        return calculateDamage(blockPos.getX() + 0.5, blockPos.getY() + 1, blockPos.getZ() + 0.5, entity);
    }
    
    public static boolean checkSelf(final EntityEnderCrystal entityEnderCrystal, final double n, final boolean b) {
        final boolean b2 = calculateDamage((Entity)entityEnderCrystal, (Entity)CombatUtil.mc.player) > CombatUtil.mc.player.getHealth() + CombatUtil.mc.player.getAbsorptionAmount();
        final boolean b3 = calculateDamage((Entity)entityEnderCrystal, (Entity)CombatUtil.mc.player) > n;
        return (!b || !b2) && !b3;
    }
    
    public static Vec3d interpolateEntity(final Entity entity) {
        return new Vec3d(entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * CombatUtil.mc.getRenderPartialTicks(), entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * CombatUtil.mc.getRenderPartialTicks(), entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * CombatUtil.mc.getRenderPartialTicks());
    }
    
    public static EntityPlayer getTarget(final float n) {
        Object o = null;
        for (int size = CombatUtil.mc.world.playerEntities.size(), i = 0; i < size; ++i) {
            final EntityPlayer entityPlayer = CombatUtil.mc.world.playerEntities.get(i);
            if (!EntityUtil.isntValid((Entity)entityPlayer, n)) {
                if (o == null) {
                    o = entityPlayer;
                }
                else if (CombatUtil.mc.player.getDistanceSq((Entity)entityPlayer) < CombatUtil.mc.player.getDistanceSq((Entity)o)) {
                    o = entityPlayer;
                }
            }
        }
        return (EntityPlayer)o;
    }
    
    public static float[] calcAngle(final Vec3d vec3d, final Vec3d vec3d2) {
        final double x = vec3d2.x - vec3d.x;
        final double y = (vec3d2.y - vec3d.y) * -1.0;
        final double y2 = vec3d2.z - vec3d.z;
        return new float[] { (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(y2, x)) - 90.0), (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(y, MathHelper.sqrt(x * x + y2 * y2)))) };
    }
    
    public static void betterRotate(final BlockPos blockPos, final EnumFacing enumFacing, final boolean b) {
        float n = 0.0f;
        float n2 = 0.0f;
        float n3 = 0.0f;
        switch (getPlaceSide(blockPos)) {
            case UP: {
                n = 0.5f;
                n3 = 0.5f;
                n2 = 0.0f;
                break;
            }
            case DOWN: {
                n = 0.5f;
                n3 = 0.5f;
                n2 = -0.5f;
                break;
            }
            case NORTH: {
                n3 = 0.5f;
                n2 = -0.5f;
                n = -0.5f;
                break;
            }
            case EAST: {
                n3 = 0.5f;
                n2 = -0.5f;
                n = 0.5f;
                break;
            }
            case SOUTH: {
                n3 = 0.5f;
                n2 = -0.5f;
                n = 0.5f;
                break;
            }
            case WEST: {
                n3 = -0.5f;
                n2 = -0.5f;
                n = 0.5f;
                break;
            }
        }
        final float[] legitRotations = getLegitRotations(getHitAddition(n3, n2, n, blockPos, enumFacing));
        CombatUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(legitRotations[0], legitRotations[1], CombatUtil.mc.player.onGround));
    }
    
    public static Object getClosestValidCrystal(final EntityPlayer entityPlayer, final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final boolean b, final boolean b2, final boolean b3) {
        if (entityPlayer == null) {
            return null;
        }
        for (final Entity next : (List)CombatUtil.mc.world.loadedEntityList.stream().filter(entity -> entity instanceof EntityEnderCrystal).filter(entity3 -> CombatUtil.mc.player.getDistanceSq(entity3) <= n4 * n4).sorted(Comparator.comparingDouble(entity2 -> CombatUtil.mc.player.getDistanceSq(entity2))).map(entityEnderCrystal -> entityEnderCrystal).collect(Collectors.toList())) {
            if ((!b || rayTraceRangeCheck(next, n5)) && calculateDamage(next, (Entity)entityPlayer) >= n3 && checkSelf((EntityEnderCrystal)next, n, b2)) {
                if (!checkFriends((EntityEnderCrystal)next, n2, n6, b3)) {
                    continue;
                }
                return next;
            }
        }
        return null;
    }
    
    public static boolean placeBlock(final BlockPos blockPos, final boolean b, final boolean b2, final boolean b3, final boolean b4, final boolean b5, final int currentItem) {
        if (!checkCanPlace(blockPos)) {
            return false;
        }
        final EnumFacing placeSide = getPlaceSide(blockPos);
        final BlockPos offset = blockPos.offset(placeSide);
        final EnumFacing getOpposite = placeSide.getOpposite();
        if (!CombatUtil.mc.world.getBlockState(offset).getBlock().canCollideCheck(CombatUtil.mc.world.getBlockState(offset), false)) {
            return false;
        }
        if (b4) {
            if (b5) {
                CombatUtil.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(currentItem));
            }
            else if (CombatUtil.mc.player.inventory.currentItem != currentItem) {
                CombatUtil.mc.player.inventory.currentItem = currentItem;
            }
        }
        boolean b6 = false;
        if (CombatUtil.blackList.contains(CombatUtil.mc.world.getBlockState(offset).getBlock()) || CombatUtil.shulkerList.contains(CombatUtil.mc.world.getBlockState(offset).getBlock())) {
            CombatUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)CombatUtil.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            b6 = true;
        }
        final Vec3d hitVector = getHitVector(offset, getOpposite);
        if (b2) {
            final float[] legitRotations = getLegitRotations(hitVector);
            CombatUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(legitRotations[0], legitRotations[1], CombatUtil.mc.player.onGround));
        }
        final EnumHand enumHand = b ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND;
        CombatUtil.mc.playerController.processRightClickBlock(CombatUtil.mc.player, CombatUtil.mc.world, offset, getOpposite, hitVector, enumHand);
        CombatUtil.mc.player.connection.sendPacket((Packet)new CPacketAnimation(enumHand));
        if (b6) {
            CombatUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)CombatUtil.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
        }
        return true;
    }
    
    public static boolean rayTraceRangeCheck(final BlockPos blockPos, final double n, final double n2) {
        return CombatUtil.mc.world.rayTraceBlocks(new Vec3d(CombatUtil.mc.player.posX, CombatUtil.mc.player.posY + CombatUtil.mc.player.getEyeHeight(), CombatUtil.mc.player.posZ), new Vec3d((double)blockPos.getX(), blockPos.getY() + n2, (double)blockPos.getZ()), false, true, false) == null || CombatUtil.mc.player.getDistanceSq(blockPos) <= n * n;
    }
    
    public static boolean checkSelf(final BlockPos blockPos, final double n, final boolean b) {
        final boolean b2 = calculateDamage(blockPos, (Entity)CombatUtil.mc.player) > CombatUtil.mc.player.getHealth() + CombatUtil.mc.player.getAbsorptionAmount();
        final boolean b3 = calculateDamage(blockPos, (Entity)CombatUtil.mc.player) > n;
        return (!b || !b2) && !b3;
    }
    
    public static BlockPos getFlooredPosition(final Entity entity) {
        return new BlockPos(Math.floor(entity.posX), Math.floor(entity.posY), Math.floor(entity.posZ));
    }
    
    public static int getItemDamage(final ItemStack itemStack) {
        return itemStack.getMaxDamage() - itemStack.getItemDamage();
    }
    
    public static boolean checkFriends(final EntityEnderCrystal entityEnderCrystal, final double n, final double n2, final boolean b) {
        for (final EntityPlayer entityPlayer : CombatUtil.mc.world.playerEntities) {
            if (CombatUtil.mc.player.getDistanceSq((Entity)entityPlayer) > n2 * n2) {
                continue;
            }
            if (calculateDamage((Entity)entityEnderCrystal, (Entity)entityPlayer) > n) {
                return false;
            }
            if (calculateDamage((Entity)entityEnderCrystal, (Entity)entityPlayer) <= entityPlayer.getHealth() + entityPlayer.getAbsorptionAmount()) {
                continue;
            }
            if (!b) {
                continue;
            }
            return false;
        }
        return true;
    }
    
    private static EnumFacing getPlaceSide(final BlockPos blockPos) {
        EnumFacing enumFacing = null;
        for (final EnumFacing enumFacing2 : EnumFacing.values()) {
            final BlockPos offset = blockPos.offset(enumFacing2);
            if (CombatUtil.mc.world.getBlockState(offset).getBlock().canCollideCheck(CombatUtil.mc.world.getBlockState(offset), false)) {
                if (!CombatUtil.mc.world.getBlockState(offset).getMaterial().isReplaceable()) {
                    enumFacing = enumFacing2;
                }
            }
        }
        return enumFacing;
    }
    
    public static boolean rayTraceRangeCheck(final Entity entity, final double n) {
        return !CombatUtil.mc.player.canEntityBeSeen(entity) || CombatUtil.mc.player.getDistanceSq(entity) <= n * n;
    }
    
    public static int getSafetyFactor(final BlockPos blockPos) {
        return 0;
    }
    
    public static BlockPos getClosestValidPos(final EntityPlayer entityPlayer, final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final boolean b, final boolean b2, final boolean b3, final boolean b4) {
        double n7 = -1.0;
        BlockPos blockPos2 = null;
        if (entityPlayer == null) {
            return null;
        }
        final List<BlockPos> sphere = getSphere(new BlockPos((Vec3i)getFlooredPosition((Entity)CombatUtil.mc.player)), (float)n4, (int)n4, false, true, 0);
        sphere.sort(Comparator.comparing(blockPos -> CombatUtil.mc.player.getDistanceSq(blockPos)));
        for (final BlockPos blockPos3 : sphere) {
            final double n8;
            if (canPlaceCrystal(blockPos3, n4, n5, b) && (!b || rayTraceRangeCheck(blockPos3, n5, 0.0)) && (n8 = calculateDamage(blockPos3, (Entity)entityPlayer)) >= n3 && checkFriends(blockPos3, n2, n6, b3)) {
                if (!checkSelf(blockPos3, n, b2)) {
                    continue;
                }
                if (n8 > 15.0) {
                    return blockPos3;
                }
                if (n8 <= n7) {
                    continue;
                }
                n7 = n8;
                blockPos2 = blockPos3;
            }
        }
        return blockPos2;
    }
    
    public static Vec3d getHitAddition(final double n, final double n2, final double n3, final BlockPos blockPos, final EnumFacing enumFacing) {
        return new Vec3d((Vec3i)blockPos).add(0.5, 0.5, 0.5).add(new Vec3d(enumFacing.getDirectionVec()).scale(0.5));
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
    
    public static boolean isHoldingCrystal(final boolean b) {
        if (b) {
            return CombatUtil.mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL;
        }
        return CombatUtil.mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL || CombatUtil.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL;
    }
    
    public static boolean checkFriends(final BlockPos blockPos, final double n, final double n2, final boolean b) {
        for (final EntityPlayer entityPlayer : CombatUtil.mc.world.playerEntities) {
            if (CombatUtil.mc.player.getDistanceSq((Entity)entityPlayer) > n2 * n2) {
                continue;
            }
            if (calculateDamage(blockPos, (Entity)entityPlayer) > n) {
                return false;
            }
            if (calculateDamage(blockPos, (Entity)entityPlayer) <= entityPlayer.getHealth() + entityPlayer.getAbsorptionAmount()) {
                continue;
            }
            if (!b) {
                continue;
            }
            return false;
        }
        return true;
    }
    
    public static Map<BlockPos, Double> mapBlockDamage(final EntityPlayer entityPlayer, final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final boolean b, final boolean b2, final boolean b3) {
        final HashMap<BlockPos, Double> hashMap = new HashMap<BlockPos, Double>();
        for (final BlockPos key : getSphere(new BlockPos((Vec3i)getFlooredPosition((Entity)CombatUtil.mc.player)), (float)n4, (int)n4, false, true, 0)) {
            if (canPlaceCrystal(key, n4, n5, b) && checkFriends(key, n2, n6, b3) && checkSelf(key, n, b2) && (!b || rayTraceRangeCheck(key, n5, 0.0))) {
                final double d;
                if ((d = calculateDamage(key, (Entity)entityPlayer)) < n3) {
                    continue;
                }
                hashMap.put(key, d);
            }
        }
        return hashMap;
    }
    
    public static float calculateDamage(final Entity entity, final Entity entity2) {
        return calculateDamage(entity.posX, entity.posY, entity.posZ, entity2);
    }
    
    public static boolean passesOffhandCheck(final double n, final Item item, final boolean b) {
        final double n2 = CombatUtil.mc.player.getHealth() + CombatUtil.mc.player.getAbsorptionAmount();
        if (!b) {
            if (findItemSlot(item) != -1) {
                return n2 >= n;
            }
        }
        else if (findCrapple() != -1) {
            return n2 >= n;
        }
        return false;
    }
    
    public static List<BlockPos> getDisc(final BlockPos blockPos, final float n) {
        final ArrayList<BlockPos> list = new ArrayList<BlockPos>();
        final int getX = blockPos.getX();
        final int getY = blockPos.getY();
        final int getZ = blockPos.getZ();
        for (int n2 = getX - (int)n; n2 <= getX + n; ++n2) {
            for (int n3 = getZ - (int)n; n3 <= getZ + n; ++n3) {
                if ((getX - n2) * (getX - n2) + (getZ - n3) * (getZ - n3) < (double)(n * n)) {
                    list.add(new BlockPos(n2, getY, n3));
                }
            }
        }
        return list;
    }
    
    public static int findCrapple() {
        if (CombatUtil.mc.player == null) {
            return -1;
        }
        for (int i = 0; i < CombatUtil.mc.player.inventoryContainer.getInventory().size(); ++i) {
            final ItemStack itemStack;
            if (!CombatUtil.invalidSlots.contains(i) && !(itemStack = (ItemStack)CombatUtil.mc.player.inventoryContainer.getInventory().get(i)).isEmpty() && itemStack.getItem().equals(Items.GOLDEN_APPLE) && itemStack.getItemDamage() != 1) {
                return i;
            }
        }
        return -1;
    }
    
    public static boolean isHard(final Block block) {
        return block == Blocks.OBSIDIAN || block == Blocks.BEDROCK;
    }
    
    public static float getBlastReduction(final EntityLivingBase entityLivingBase, final float n, final Explosion explosion) {
        if (entityLivingBase instanceof EntityPlayer) {
            final EntityPlayer entityPlayer = (EntityPlayer)entityLivingBase;
            final DamageSource causeExplosionDamage = DamageSource.causeExplosionDamage(explosion);
            final float getDamageAfterAbsorb = CombatRules.getDamageAfterAbsorb(n, (float)entityPlayer.getTotalArmorValue(), (float)entityPlayer.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue());
            int getEnchantmentModifierDamage = 0;
            try {
                getEnchantmentModifierDamage = EnchantmentHelper.getEnchantmentModifierDamage(entityPlayer.getArmorInventoryList(), causeExplosionDamage);
            }
            catch (Exception ex) {}
            float a = getDamageAfterAbsorb * (1.0f - MathHelper.clamp((float)getEnchantmentModifierDamage, 0.0f, 20.0f) / 25.0f);
            if (entityLivingBase.isPotionActive(MobEffects.RESISTANCE)) {
                a -= a / 4.0f;
            }
            return Math.max(a, 0.0f);
        }
        return CombatRules.getDamageAfterAbsorb(n, (float)entityLivingBase.getTotalArmorValue(), (float)entityLivingBase.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue());
    }
    
    public static boolean isPosValid(final EntityPlayer entityPlayer, final BlockPos blockPos, final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final boolean b, final boolean b2, final boolean b3) {
        return blockPos != null && isHard(CombatUtil.mc.world.getBlockState(blockPos).getBlock()) && canPlaceCrystal(blockPos, n4, n5, b) && checkFriends(blockPos, n2, n6, b3) && checkSelf(blockPos, n, b2) && calculateDamage(blockPos, (Entity)entityPlayer) >= n3 && (!b || rayTraceRangeCheck(blockPos, n5, 0.0));
    }
    
    public static int findItemSlot(final Item obj) {
        if (CombatUtil.mc.player == null) {
            return -1;
        }
        for (int i = 0; i < CombatUtil.mc.player.inventoryContainer.getInventory().size(); ++i) {
            final ItemStack itemStack;
            if (!CombatUtil.invalidSlots.contains(i) && !(itemStack = (ItemStack)CombatUtil.mc.player.inventoryContainer.getInventory().get(i)).isEmpty() && itemStack.getItem().equals(obj)) {
                return i;
            }
        }
        return -1;
    }
    
    public static boolean canSeeBlock(final BlockPos blockPos) {
        return CombatUtil.mc.world.rayTraceBlocks(new Vec3d(CombatUtil.mc.player.posX, CombatUtil.mc.player.posY + CombatUtil.mc.player.getEyeHeight(), CombatUtil.mc.player.posZ), new Vec3d((double)blockPos.getX(), (double)(blockPos.getY() + 1.0f), (double)blockPos.getZ()), false, true, false) == null;
    }
    
    public static float calculateDamage(final double n, final double n2, final double n3, final Entity entity) {
        final float n4 = 12.0f;
        final double n5 = entity.getDistance(n, n2, n3) / n4;
        final Vec3d vec3d = new Vec3d(n, n2, n3);
        double n6 = 0.0;
        try {
            n6 = entity.world.getBlockDensity(vec3d, entity.getEntityBoundingBox());
        }
        catch (Exception ex) {}
        final double n7 = (1.0 - n5) * n6;
        final float n8 = (float)(int)((n7 * n7 + n7) / 2.0 * 7.0 * n4 + 1.0);
        double n9 = 1.0;
        if (entity instanceof EntityLivingBase) {
            n9 = getBlastReduction((EntityLivingBase)entity, getDamageMultiplied(n8), new Explosion((World)Minecraft.getMinecraft().world, (Entity)null, n, n2, n3, 6.0f, false, true));
        }
        return (float)n9;
    }
    
    private static Vec3d getHitVector(final BlockPos blockPos, final EnumFacing enumFacing) {
        return new Vec3d((Vec3i)blockPos).add(0.5, 0.5, 0.5).add(new Vec3d(enumFacing.getDirectionVec()).scale(0.5));
    }
    
    public static boolean requiredDangerSwitch(final double n) {
        return (int)CombatUtil.mc.world.loadedEntityList.stream().filter(entity -> entity instanceof EntityEnderCrystal).filter(entity3 -> CombatUtil.mc.player.getDistance(entity3) <= n).filter(entity2 -> calculateDamage(entity2.posX, entity2.posY, entity2.posZ, (Entity)CombatUtil.mc.player) >= CombatUtil.mc.player.getHealth() + CombatUtil.mc.player.getAbsorptionAmount()).count() > 0;
    }
    
    public static void switchOffhandTotemNotStrict() {
        final int itemSlot = findItemSlot(Items.TOTEM_OF_UNDYING);
        if (itemSlot != -1) {
            CombatUtil.mc.playerController.windowClick(CombatUtil.mc.player.inventoryContainer.windowId, itemSlot, 0, ClickType.PICKUP, (EntityPlayer)CombatUtil.mc.player);
            CombatUtil.mc.playerController.windowClick(CombatUtil.mc.player.inventoryContainer.windowId, 45, 0, ClickType.PICKUP, (EntityPlayer)CombatUtil.mc.player);
            CombatUtil.mc.playerController.windowClick(CombatUtil.mc.player.inventoryContainer.windowId, itemSlot, 0, ClickType.PICKUP, (EntityPlayer)CombatUtil.mc.player);
            CombatUtil.mc.playerController.updateController();
        }
    }
    
    static {
        blackList = Arrays.asList((Block)Blocks.TALLGRASS, Blocks.ENDER_CHEST, (Block)Blocks.CHEST, Blocks.TRAPPED_CHEST, Blocks.CRAFTING_TABLE, Blocks.ANVIL, Blocks.BREWING_STAND, (Block)Blocks.HOPPER, Blocks.DROPPER, Blocks.DISPENSER, Blocks.TRAPDOOR);
        shulkerList = Arrays.asList(Blocks.WHITE_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.SILVER_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.BLACK_SHULKER_BOX);
        CombatUtil.mc = Minecraft.getMinecraft();
        cityOffsets = new Vec3d[] { new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, -1.0), new Vec3d(2.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 2.0), new Vec3d(-2.0, 0.0, 0.0), new Vec3d(0.0, 0.0, -2.0) };
        invalidSlots = Arrays.asList(0, 5, 6, 7, 8);
    }
    
    public static boolean canLegPlace(final EntityPlayer entityPlayer, final double n) {
        int n2 = 0;
        int n3 = 0;
        for (final Vec3d vec3d : HoleUtil.awacityOffsets) {
            final BlockPos add = getFlooredPosition((Entity)entityPlayer).add(vec3d.x, vec3d.y, vec3d.z);
            if (CombatUtil.mc.world.getBlockState(add).getBlock() == Blocks.OBSIDIAN || CombatUtil.mc.world.getBlockState(add).getBlock() == Blocks.BEDROCK) {
                ++n2;
            }
            if (CombatUtil.mc.player.getDistanceSq(add) >= n * n) {
                ++n3;
            }
        }
        return n2 == 4 && n3 >= 1;
    }
    
    public static int getVulnerability(final EntityPlayer entityPlayer, final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final double n7, final double n8, final int n9, final boolean b, final boolean b2, final boolean b3, final boolean b4, final boolean b5) {
        if (isInCity(entityPlayer, n, n2, true, true, true, false) && b) {
            return 5;
        }
        if (getClosestValidPos(entityPlayer, n4, n5, n6, n2, n3, n7, b2, b4, b5, true) != null) {
            return 4;
        }
        if (entityPlayer.getHealth() + entityPlayer.getAbsorptionAmount() <= n8) {
            return 3;
        }
        if (isArmorLow(entityPlayer, n9, true) && b3) {
            return 2;
        }
        return 0;
    }
    
    public static void switchOffhandStrict(final int n, final int n2) {
        switch (n2) {
            case 0: {
                CombatUtil.mc.playerController.windowClick(CombatUtil.mc.player.inventoryContainer.windowId, n, 0, ClickType.PICKUP, (EntityPlayer)CombatUtil.mc.player);
                break;
            }
            case 1: {
                CombatUtil.mc.playerController.windowClick(CombatUtil.mc.player.inventoryContainer.windowId, 45, 0, ClickType.PICKUP, (EntityPlayer)CombatUtil.mc.player);
                break;
            }
            case 2: {
                CombatUtil.mc.playerController.windowClick(CombatUtil.mc.player.inventoryContainer.windowId, n, 0, ClickType.PICKUP, (EntityPlayer)CombatUtil.mc.player);
                CombatUtil.mc.playerController.updateController();
                break;
            }
        }
    }
    
    public static float[] getLegitRotations(final Vec3d vec3d) {
        final Vec3d vec3d2 = new Vec3d(CombatUtil.mc.player.posX, CombatUtil.mc.player.posY + CombatUtil.mc.player.getEyeHeight(), CombatUtil.mc.player.posZ);
        final double x = vec3d.x - vec3d2.x;
        final double y = vec3d.y - vec3d2.y;
        final double y2 = vec3d.z - vec3d2.z;
        return new float[] { CombatUtil.mc.player.rotationYaw + MathHelper.wrapDegrees((float)Math.toDegrees(Math.atan2(y2, x)) - 90.0f - CombatUtil.mc.player.rotationYaw), CombatUtil.mc.player.rotationPitch + MathHelper.wrapDegrees((float)(-Math.toDegrees(Math.atan2(y, Math.sqrt(x * x + y2 * y2)))) - CombatUtil.mc.player.rotationPitch) };
    }
    
    public static void switchOffhandNonStrict(final int n) {
        CombatUtil.mc.playerController.windowClick(CombatUtil.mc.player.inventoryContainer.windowId, n, 0, ClickType.PICKUP, (EntityPlayer)CombatUtil.mc.player);
        CombatUtil.mc.playerController.windowClick(CombatUtil.mc.player.inventoryContainer.windowId, 45, 0, ClickType.PICKUP, (EntityPlayer)CombatUtil.mc.player);
        CombatUtil.mc.playerController.windowClick(CombatUtil.mc.player.inventoryContainer.windowId, n, 0, ClickType.PICKUP, (EntityPlayer)CombatUtil.mc.player);
        CombatUtil.mc.playerController.updateController();
    }
    
    public static boolean checkCanPlace(final BlockPos blockPos) {
        if (!(CombatUtil.mc.world.getBlockState(blockPos).getBlock() instanceof BlockAir) && !(CombatUtil.mc.world.getBlockState(blockPos).getBlock() instanceof BlockLiquid)) {
            return false;
        }
        for (final Entity entity : CombatUtil.mc.world.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(blockPos))) {
            if (!(entity instanceof EntityItem) && !(entity instanceof EntityXPOrb)) {
                if (entity instanceof EntityArrow) {
                    continue;
                }
                return false;
            }
        }
        return getPlaceSide(blockPos) != null;
    }
    
    public static boolean isArmorLow(final EntityPlayer entityPlayer, final int n, final boolean b) {
        for (final ItemStack itemStack : entityPlayer.inventory.armorInventory) {
            if (itemStack == null) {
                return true;
            }
            if (!b) {
                continue;
            }
            if (getItemDamage(itemStack) >= n) {
                continue;
            }
            return true;
        }
        return false;
    }
    
    public static boolean isInCity(final EntityPlayer entityPlayer, final double n, final double n2, final boolean b, final boolean b2, final boolean b3, final boolean b4) {
        final BlockPos blockPos = new BlockPos(entityPlayer.getPositionVector());
        for (final EnumFacing enumFacing : EnumFacing.values()) {
            if (enumFacing != EnumFacing.UP) {
                if (enumFacing != EnumFacing.DOWN) {
                    final BlockPos offset = blockPos.offset(enumFacing);
                    final BlockPos offset2 = offset.offset(enumFacing);
                    if ((CombatUtil.mc.world.getBlockState(offset).getBlock() == Blocks.AIR && ((CombatUtil.mc.world.getBlockState(offset2).getBlock() == Blocks.AIR && isHard(CombatUtil.mc.world.getBlockState(offset2.up()).getBlock())) || !b) && !b4) || (CombatUtil.mc.player.getDistanceSq(offset2) <= n2 * n2 && CombatUtil.mc.player.getDistanceSq((Entity)entityPlayer) <= n * n && isHard(CombatUtil.mc.world.getBlockState(blockPos.up(3)).getBlock())) || !b2) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public static BlockPos getClosestValidPosMultiThread(final EntityPlayer entityPlayer, final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final boolean b, final boolean b2, final boolean b3) {
        final CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<Object>();
        BlockPos pos = null;
        final Iterator<BlockPos> iterator = getSphere(new BlockPos(entityPlayer.getPositionVector()), 13.0f, 13, false, true, 0).iterator();
        while (iterator.hasNext()) {
            final ValidPosThread e = new ValidPosThread(entityPlayer, iterator.next(), n, n2, n3, n4, n5, n6, b, b2, b3);
            list.add(e);
            e.start();
        }
        boolean noneMatch;
        do {
            for (final ValidPosThread validPosThread2 : list) {
                if (validPosThread2.isInterrupted()) {
                    if (!validPosThread2.isValid) {
                        continue;
                    }
                    pos = validPosThread2.pos;
                }
            }
            noneMatch = list.stream().noneMatch(validPosThread -> validPosThread.isValid && validPosThread.isInterrupted());
        } while (pos == null && !noneMatch);
        LuigiHack.LOGGER.info((pos == null) ? "pos was null" : pos.toString());
        return pos;
    }
    
    public static int findItemSlotDamage1(final Item obj) {
        if (CombatUtil.mc.player == null) {
            return -1;
        }
        for (int i = 0; i < CombatUtil.mc.player.inventoryContainer.getInventory().size(); ++i) {
            final ItemStack itemStack;
            if (!CombatUtil.invalidSlots.contains(i) && !(itemStack = (ItemStack)CombatUtil.mc.player.inventoryContainer.getInventory().get(i)).isEmpty() && itemStack.getItem().equals(obj) && itemStack.getItemDamage() == 1) {
                return i;
            }
        }
        return -1;
    }
    
    public static float getDamageMultiplied(final float n) {
        final int getId = Minecraft.getMinecraft().world.getDifficulty().getId();
        return n * ((getId == 0) ? 0.0f : ((getId == 2) ? 1.0f : ((getId == 1) ? 0.5f : 1.5f)));
    }
    
    public static boolean canPlaceCrystal(final BlockPos blockPos, final double n, final double n2, final boolean b) {
        final BlockPos up = blockPos.up();
        final BlockPos up2 = up.up();
        final AxisAlignedBB expand = new AxisAlignedBB(up).expand(0.0, 1.0, 0.0);
        return ((CombatUtil.mc.world.getBlockState(blockPos).getBlock() == Blocks.OBSIDIAN || CombatUtil.mc.world.getBlockState(blockPos).getBlock() == Blocks.BEDROCK) && CombatUtil.mc.world.getBlockState(up).getBlock() == Blocks.AIR && CombatUtil.mc.world.getBlockState(up2).getBlock() == Blocks.AIR && CombatUtil.mc.world.getEntitiesWithinAABB((Class)Entity.class, expand).isEmpty() && CombatUtil.mc.player.getDistanceSq(blockPos) <= n * n && !b) || rayTraceRangeCheck(blockPos, n2, 0.0);
    }
    
    public static class ValidPosThread extends Thread
    {
        public /* synthetic */ CombatPosInfo info;
        /* synthetic */ double wallsRange;
        /* synthetic */ EntityPlayer player;
        /* synthetic */ double friendRange;
        /* synthetic */ double minDamage;
        public /* synthetic */ boolean isValid;
        /* synthetic */ double maxFriendDamage;
        /* synthetic */ BlockPos pos;
        /* synthetic */ boolean antiSuicide;
        /* synthetic */ double placeRange;
        public /* synthetic */ float damage;
        /* synthetic */ boolean rayTrace;
        /* synthetic */ boolean antiFriendPop;
        /* synthetic */ double maxSelfDamage;
        
        @Override
        public void run() {
            if (CombatUtil.mc.player.getDistanceSq(this.pos) <= this.placeRange * this.placeRange && CombatUtil.canPlaceCrystal(this.pos, this.placeRange, this.wallsRange, this.rayTrace) && CombatUtil.checkFriends(this.pos, this.maxFriendDamage, this.friendRange, this.antiFriendPop) && CombatUtil.checkSelf(this.pos, this.maxSelfDamage, this.antiSuicide)) {
                this.damage = CombatUtil.calculateDamage(this.pos, (Entity)this.player);
                if (this.damage >= this.minDamage && (!this.rayTrace || CombatUtil.rayTraceRangeCheck(this.pos, this.wallsRange, 0.0))) {
                    this.isValid = true;
                    this.info = new CombatPosInfo(this.player, this.pos, this.damage);
                    LuigiHack.LOGGER.info("Pos was valid.");
                    return;
                }
            }
            this.isValid = false;
            this.info = new CombatPosInfo(this.player, this.pos, -1.0f);
            LuigiHack.LOGGER.info("Pos was invalid.");
        }
        
        public ValidPosThread(final EntityPlayer player, final BlockPos pos, final double maxSelfDamage, final double maxFriendDamage, final double minDamage, final double placeRange, final double wallsRange, final double friendRange, final boolean rayTrace, final boolean antiSuicide, final boolean antiFriendPop) {
            super("Break");
            this.pos = pos;
            this.maxSelfDamage = maxSelfDamage;
            this.maxFriendDamage = maxFriendDamage;
            this.minDamage = minDamage;
            this.placeRange = placeRange;
            this.wallsRange = wallsRange;
            this.friendRange = friendRange;
            this.rayTrace = rayTrace;
            this.antiSuicide = antiSuicide;
            this.antiFriendPop = antiFriendPop;
            this.player = player;
        }
    }
    
    public static class CombatPosInfo
    {
        public /* synthetic */ EntityPlayer player;
        public /* synthetic */ BlockPos pos;
        public /* synthetic */ float damage;
        
        public CombatPosInfo(final EntityPlayer player, final BlockPos pos, final float damage) {
            this.pos = pos;
            this.damage = damage;
            this.player = player;
        }
    }
}
