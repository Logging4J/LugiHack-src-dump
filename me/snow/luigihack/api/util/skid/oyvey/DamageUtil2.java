//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util.skid.oyvey;

import net.minecraft.entity.player.*;
import net.minecraft.world.*;
import me.snow.luigihack.api.util.*;
import java.util.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;
import net.minecraft.enchantment.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraft.potion.*;

public class DamageUtil2 implements Util
{
    public static EntityPlayer getTarget(final float n) {
        Object o = null;
        for (int size = DamageUtil2.mc.world.playerEntities.size(), i = 0; i < size; ++i) {
            final EntityPlayer entityPlayer = DamageUtil2.mc.world.playerEntities.get(i);
            if (!EntityUtil2.isntValid((Entity)entityPlayer, n)) {
                if (o == null) {
                    o = entityPlayer;
                }
                else if (DamageUtil2.mc.player.getDistanceSq((Entity)entityPlayer) < DamageUtil2.mc.player.getDistanceSq((Entity)o)) {
                    o = entityPlayer;
                }
            }
        }
        return (EntityPlayer)o;
    }
    
    public static int getCooldownByWeapon(final EntityPlayer entityPlayer) {
        final Item getItem = entityPlayer.getHeldItemMainhand().getItem();
        if (getItem instanceof ItemSword) {
            return 600;
        }
        if (getItem instanceof ItemPickaxe) {
            return 850;
        }
        if (getItem == Items.IRON_AXE) {
            return 1100;
        }
        if (getItem == Items.STONE_HOE) {
            return 500;
        }
        if (getItem == Items.IRON_HOE) {
            return 350;
        }
        if (getItem == Items.WOODEN_AXE || getItem == Items.STONE_AXE) {
            return 1250;
        }
        if (getItem instanceof ItemSpade || getItem == Items.GOLDEN_AXE || getItem == Items.DIAMOND_AXE || getItem == Items.WOODEN_HOE || getItem == Items.GOLDEN_HOE) {
            return 1000;
        }
        return 250;
    }
    
    public static float getDamageMultiplied(final float n) {
        final int getId = DamageUtil2.mc.world.getDifficulty().getId();
        return n * ((getId == 0) ? 0.0f : ((getId == 2) ? 1.0f : ((getId == 1) ? 0.5f : 1.5f)));
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
            n9 = getBlastReduction((EntityLivingBase)entity, getDamageMultiplied(n8), new Explosion((World)DamageUtil2.mc.world, (Entity)null, n, n2, n3, 6.0f, false, true));
        }
        return (float)n9;
    }
    
    public static float calculateDamage(final BlockPos blockPos, final Entity entity, final boolean b, final int n) {
        return calculateDamage(blockPos.getX() + 0.5, blockPos.getY() + 1, blockPos.getZ() + 0.5, entity, b, n);
    }
    
    public static float calculateDamage(final double n, final double n2, final double n3, final Entity entity, final boolean b, final int n4) {
        Vec3d vec3d;
        AxisAlignedBB axisAlignedBB;
        if (b) {
            vec3d = MathUtil.extrapolatePlayerPosition((EntityPlayer)entity, n4);
            axisAlignedBB = entity.boundingBox.offset(-entity.posX, -entity.posY, -entity.posZ).offset(vec3d);
        }
        else {
            axisAlignedBB = entity.boundingBox;
            vec3d = entity.getPositionVector();
        }
        final Vec3d vec3d2 = new Vec3d(n, n2, n3);
        final double n5 = vec3d.distanceTo(vec3d2) / 12.0;
        double n6 = 0.0;
        try {
            n6 = entity.world.getBlockDensity(vec3d2, axisAlignedBB);
        }
        catch (Exception ex) {}
        final double n7 = (1.0 - n5) * n6;
        final float n8 = (float)((n7 * n7 + n7) / 2.0 * 7.0 * 12.0 + 1.0);
        double n9 = 1.0;
        if (entity instanceof EntityLivingBase) {
            n9 = getBlastReduction((EntityLivingBase)entity, getDamageMultiplied(n8), new Explosion((World)DamageUtil2.mc.world, (Entity)null, n, n2, n3, 6.0f, false, true));
        }
        return (float)n9;
    }
    
    public static float calculateDamage(final Vec3d vec3d, final Entity entity, final boolean b, final int n) {
        return calculateDamage(vec3d.x, vec3d.y, vec3d.z, entity, b, n);
    }
    
    public static float getDamageInPercent(final ItemStack itemStack) {
        return getItemDamage(itemStack) / itemStack.getMaxDamage() * 100.0f;
    }
    
    public static float calculateDamage(final Vec3d vec3d, final Entity entity) {
        return calculateDamage(vec3d.x, vec3d.y, vec3d.z, entity);
    }
    
    public static boolean isArmorLow(final EntityPlayer entityPlayer, final int n) {
        for (final ItemStack itemStack : entityPlayer.inventory.armorInventory) {
            if (itemStack == null) {
                return true;
            }
            if (getItemDamage(itemStack) >= n) {
                continue;
            }
            return true;
        }
        return false;
    }
    
    public static int getRoundedDamage(final ItemStack itemStack) {
        return (int)getDamageInPercent(itemStack);
    }
    
    public static boolean hasDurability(final ItemStack itemStack) {
        final Item getItem = itemStack.getItem();
        return getItem instanceof ItemArmor || getItem instanceof ItemSword || getItem instanceof ItemTool || getItem instanceof ItemShield;
    }
    
    public static float calculateDamage(final Entity entity, final Entity entity2) {
        return calculateDamage(entity.posX, entity.posY, entity.posZ, entity2);
    }
    
    public static int getItemDamage(final ItemStack itemStack) {
        return itemStack.getMaxDamage() - itemStack.getItemDamage();
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
    
    public static boolean canTakeDamage(final boolean b) {
        return !DamageUtil2.mc.player.capabilities.isCreativeMode && !b;
    }
    
    public static boolean isNaked(final EntityPlayer entityPlayer) {
        for (final ItemStack itemStack : entityPlayer.inventory.armorInventory) {
            if (itemStack != null) {
                if (itemStack.isEmpty()) {
                    continue;
                }
                return false;
            }
        }
        return true;
    }
    
    public static float calculateDamage(final BlockPos blockPos, final Entity entity) {
        return calculateDamage(blockPos.getX() + 0.5, blockPos.getY() + 1, blockPos.getZ() + 0.5, entity);
    }
    
    public static float calculateDamage(final Entity entity, final Entity entity2, final boolean b, final int n) {
        return calculateDamage(entity.posX, entity.posY, entity.posZ, entity2, b, n);
    }
    
    public static boolean canBreakWeakness(final EntityPlayer entityPlayer) {
        int getAmplifier = 0;
        final PotionEffect getActivePotionEffect = DamageUtil2.mc.player.getActivePotionEffect(MobEffects.STRENGTH);
        if (getActivePotionEffect != null) {
            getAmplifier = getActivePotionEffect.getAmplifier();
        }
        return !DamageUtil2.mc.player.isPotionActive(MobEffects.WEAKNESS) || getAmplifier >= 1 || DamageUtil2.mc.player.getHeldItemMainhand().getItem() instanceof ItemSword || DamageUtil2.mc.player.getHeldItemMainhand().getItem() instanceof ItemPickaxe || DamageUtil2.mc.player.getHeldItemMainhand().getItem() instanceof ItemAxe || DamageUtil2.mc.player.getHeldItemMainhand().getItem() instanceof ItemSpade;
    }
}
