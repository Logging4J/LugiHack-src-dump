//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.player;

import me.snow.luigihack.impl.modules.*;
import net.minecraft.util.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.block.state.*;
import net.minecraft.block.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.network.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.init.*;
import net.minecraft.enchantment.*;
import net.minecraft.block.material.*;
import net.minecraft.entity.*;
import net.minecraft.potion.*;
import me.snow.luigihack.impl.modules.combat.autocrystal.*;
import me.snow.luigihack.*;
import net.minecraft.inventory.*;
import net.minecraft.network.play.client.*;
import me.snow.luigihack.api.util.skid.oyvey.*;
import me.snow.luigihack.api.event.events.*;
import java.awt.*;
import me.snow.luigihack.api.util.cc.*;
import net.minecraft.util.math.*;

public class Speedmine extends Module
{
    private /* synthetic */ EnumFacing mineFacing;
    public static /* synthetic */ Speedmine INSTANCE;
    public /* synthetic */ Setting<InventoryManagerCC.Switch> mineSwitch;
    private /* synthetic */ Setting<Double> range;
    private /* synthetic */ BlockPos minePosition;
    private /* synthetic */ Setting<Boolean> strictReMine;
    private /* synthetic */ int mineBreaks;
    public /* synthetic */ Setting<Boolean> rotate2;
    private final /* synthetic */ Setting<Integer> boxAlpha;
    private /* synthetic */ int previousHaste;
    private /* synthetic */ Setting<Boolean> strict;
    private /* synthetic */ Setting<Boolean> reset;
    private static /* synthetic */ float mineDamage;
    
    public void onEnable() {
        if (Speedmine.mc.player.isPotionActive(MobEffects.HASTE)) {
            this.previousHaste = Speedmine.mc.player.getActivePotionEffect(MobEffects.HASTE).getDuration();
        }
    }
    
    public float getBlockStrength(final IBlockState blockState, final BlockPos blockPos) {
        final float getBlockHardness = blockState.getBlockHardness((World)Speedmine.mc.world, blockPos);
        if (getBlockHardness < 0.0f) {
            return 0.0f;
        }
        if (!this.canHarvestBlock(blockState.getBlock(), blockPos)) {
            return this.getDigSpeed(blockState) / getBlockHardness / 100.0f;
        }
        return this.getDigSpeed(blockState) / getBlockHardness / 30.0f;
    }
    
    public boolean canHarvestBlock(final Block block, final BlockPos blockPos) {
        final IBlockState getBlockState = Speedmine.mc.world.getBlockState(blockPos);
        final IBlockState getActualState = getBlockState.getBlock().getActualState(getBlockState, (IBlockAccess)Speedmine.mc.world, blockPos);
        if (getActualState.getMaterial().isToolNotRequired()) {
            return true;
        }
        final ItemStack efficientItem = this.getEfficientItem(getActualState);
        final String harvestTool = block.getHarvestTool(getActualState);
        if (efficientItem.isEmpty() || harvestTool == null) {
            return Speedmine.mc.player.canHarvestBlock(getActualState);
        }
        final int harvestLevel = efficientItem.getItem().getHarvestLevel(efficientItem, harvestTool, (EntityPlayer)Speedmine.mc.player, getActualState);
        if (harvestLevel < 0) {
            return Speedmine.mc.player.canHarvestBlock(getActualState);
        }
        return harvestLevel >= block.getHarvestLevel(getActualState);
    }
    
    @SubscribeEvent
    public void onBlockEvent(final BlockEvent blockEvent) {
        if (BlockUtilCC.isBreakable(blockEvent.pos) && !Speedmine.mc.player.capabilities.isCreativeMode && !blockEvent.pos.equals((Object)this.minePosition)) {
            this.minePosition = blockEvent.pos;
            this.mineFacing = blockEvent.facing;
            Speedmine.mineDamage = 0.0f;
            this.mineBreaks = 0;
            if (this.minePosition != null && this.mineFacing != null) {
                Speedmine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, this.minePosition, this.mineFacing));
                Speedmine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, this.minePosition, EnumFacing.UP));
            }
        }
    }
    
    public float getDigSpeed(final IBlockState blockState) {
        float destroySpeed = this.getDestroySpeed(blockState);
        if (destroySpeed > 1.0f) {
            final ItemStack efficientItem = this.getEfficientItem(blockState);
            final int getEnchantmentLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.EFFICIENCY, efficientItem);
            if (getEnchantmentLevel > 0 && !efficientItem.isEmpty()) {
                destroySpeed += (float)(StrictMath.pow(getEnchantmentLevel, 2.0) + 1.0);
            }
        }
        if (Speedmine.mc.player.isPotionActive(MobEffects.HASTE)) {
            destroySpeed *= 1.0f + (Speedmine.mc.player.getActivePotionEffect(MobEffects.HASTE).getAmplifier() + 1) * 0.2f;
        }
        if (Speedmine.mc.player.isPotionActive(MobEffects.MINING_FATIGUE)) {
            float n = 0.0f;
            switch (Speedmine.mc.player.getActivePotionEffect(MobEffects.MINING_FATIGUE).getAmplifier()) {
                case 0: {
                    n = 0.3f;
                    break;
                }
                case 1: {
                    n = 0.09f;
                    break;
                }
                case 2: {
                    n = 0.0027f;
                    break;
                }
                default: {
                    n = 8.1E-4f;
                    break;
                }
            }
            destroySpeed *= n;
        }
        if (Speedmine.mc.player.isInsideOfMaterial(Material.WATER) && !EnchantmentHelper.getAquaAffinityModifier((EntityLivingBase)Speedmine.mc.player)) {
            destroySpeed /= 5.0f;
        }
        if (!Speedmine.mc.player.onGround) {
            destroySpeed /= 5.0f;
        }
        return (destroySpeed < 0.0f) ? 0.0f : destroySpeed;
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        if (send.getPacket() instanceof CPacketHeldItemChange && (boolean)this.strict.getValue()) {
            Speedmine.mineDamage = 0.0f;
        }
    }
    
    public void onDisable() {
        if (Speedmine.mc.player.isPotionActive(MobEffects.HASTE)) {
            Speedmine.mc.player.removePotionEffect(MobEffects.HASTE);
        }
        if (this.previousHaste > 0) {
            Speedmine.mc.player.addPotionEffect(new PotionEffect(MobEffects.HASTE, this.previousHaste));
        }
        this.minePosition = null;
        this.mineFacing = null;
        Speedmine.mineDamage = 0.0f;
        this.mineBreaks = 0;
    }
    
    public void onUpdate() {
        if (!Speedmine.mc.player.capabilities.isCreativeMode) {
            if (this.minePosition != null) {
                final double distanceToCenter = BlockUtilCC.getDistanceToCenter((EntityPlayer)Speedmine.mc.player, this.minePosition);
                if ((this.mineBreaks >= 2 && (boolean)this.strictReMine.getValue()) || distanceToCenter > (double)this.range.getValue()) {
                    this.minePosition = null;
                    this.mineFacing = null;
                    Speedmine.mineDamage = 0.0f;
                    this.mineBreaks = 0;
                }
            }
            if (this.minePosition != null && !Speedmine.mc.world.isAirBlock(this.minePosition)) {
                if (Speedmine.mineDamage >= 1.0f && !AutoCrystal.getInstance().rotating) {
                    final int currentItem = Speedmine.mc.player.inventory.currentItem;
                    final int n = LuigiHack.inventoryManagercc.searchSlot(this.getEfficientItem(Speedmine.mc.world.getBlockState(this.minePosition)).getItem(), InventoryManagerCC.InventoryRegion.HOTBAR) + 36;
                    if (this.strict.getValue()) {
                        Speedmine.mc.player.connection.sendPacket((Packet)new CPacketClickWindow(Speedmine.mc.player.inventoryContainer.windowId, n, Speedmine.mc.player.inventory.currentItem, ClickType.SWAP, Speedmine.mc.player.openContainer.slotClick(n, Speedmine.mc.player.inventory.currentItem, ClickType.SWAP, (EntityPlayer)Speedmine.mc.player), Speedmine.mc.player.openContainer.getNextTransactionID(Speedmine.mc.player.inventory)));
                    }
                    else {
                        LuigiHack.inventoryManagercc.switchToItem(this.getEfficientItem(Speedmine.mc.world.getBlockState(this.minePosition)).getItem(), (InventoryManagerCC.Switch)this.mineSwitch.getValue());
                    }
                    Speedmine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, this.minePosition, this.mineFacing));
                    Speedmine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, this.minePosition, EnumFacing.UP));
                    if (this.strict.getValue()) {
                        Speedmine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, this.minePosition, this.mineFacing));
                    }
                    Speedmine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, this.minePosition, this.mineFacing));
                    if (currentItem != -1) {
                        if (this.strict.getValue()) {
                            final short getNextTransactionID = Speedmine.mc.player.openContainer.getNextTransactionID(Speedmine.mc.player.inventory);
                            Speedmine.mc.player.connection.sendPacket((Packet)new CPacketClickWindow(Speedmine.mc.player.inventoryContainer.windowId, n, Speedmine.mc.player.inventory.currentItem, ClickType.SWAP, Speedmine.mc.player.openContainer.slotClick(n, Speedmine.mc.player.inventory.currentItem, ClickType.SWAP, (EntityPlayer)Speedmine.mc.player), getNextTransactionID));
                            Speedmine.mc.player.connection.sendPacket((Packet)new CPacketConfirmTransaction(Speedmine.mc.player.inventoryContainer.windowId, getNextTransactionID, true));
                        }
                        else {
                            LuigiHack.inventoryManagercc.switchToSlot(currentItem, InventoryManagerCC.Switch.PACKET);
                        }
                    }
                    Speedmine.mineDamage = 0.0f;
                    ++this.mineBreaks;
                }
                if (!AutoCrystal.getInstance().rotating && Speedmine.mineDamage > 0.95 && (boolean)this.rotate2.getValue()) {
                    BlockUtil2.rotatePacket((double)this.minePosition.getX(), (double)this.minePosition.getY(), (double)this.minePosition.getZ());
                }
                Speedmine.mineDamage += this.getBlockStrength(Speedmine.mc.world.getBlockState(this.minePosition), this.minePosition);
            }
        }
    }
    
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (!Speedmine.mc.player.capabilities.isCreativeMode && this.minePosition != null && !Speedmine.mc.world.isAirBlock(this.minePosition)) {
            final AxisAlignedBB getSelectedBoundingBox = Speedmine.mc.world.getBlockState(this.minePosition).getSelectedBoundingBox((World)Speedmine.mc.world, this.minePosition);
            final Vec3d getCenter = getSelectedBoundingBox.getCenter();
            RenderUtilCC.drawBox(new RenderBuilderCC().position(new AxisAlignedBB(getCenter.x, getCenter.y, getCenter.z, getCenter.x, getCenter.y, getCenter.z).grow((getSelectedBoundingBox.minX - getSelectedBoundingBox.maxX) * 0.5 * MathHelper.clamp(Speedmine.mineDamage, 0.0f, 1.0f), (getSelectedBoundingBox.minY - getSelectedBoundingBox.maxY) * 0.5 * MathHelper.clamp(Speedmine.mineDamage, 0.0f, 1.0f), (getSelectedBoundingBox.minZ - getSelectedBoundingBox.maxZ) * 0.5 * MathHelper.clamp(Speedmine.mineDamage, 0.0f, 1.0f))).color((Speedmine.mineDamage >= 0.95) ? new Color(0, 255, 0, (int)this.boxAlpha.getValue()) : new Color(255, 0, 0, (int)this.boxAlpha.getValue())).box(RenderBuilderCC.Box.BOTH).setup().line(1.5f).cull(false).shade(false).alpha(false).depth(true).blend().texture());
        }
    }
    
    public ItemStack getEfficientItem(final IBlockState blockState) {
        int n = -1;
        double n2 = 0.0;
        for (int i = 0; i < 9; ++i) {
            if (!Speedmine.mc.player.inventory.getStackInSlot(i).isEmpty()) {
                float getDestroySpeed = Speedmine.mc.player.inventory.getStackInSlot(i).getDestroySpeed(blockState);
                if (getDestroySpeed > 1.0f) {
                    if (EnchantmentHelper.getEnchantmentLevel(Enchantments.EFFICIENCY, Speedmine.mc.player.inventory.getStackInSlot(i)) > 0) {
                        getDestroySpeed += (float)(StrictMath.pow(EnchantmentHelper.getEnchantmentLevel(Enchantments.EFFICIENCY, Speedmine.mc.player.inventory.getStackInSlot(i)), 2.0) + 1.0);
                    }
                    if (getDestroySpeed > n2) {
                        n2 = getDestroySpeed;
                        n = i;
                    }
                }
            }
        }
        if (n != -1) {
            return Speedmine.mc.player.inventory.getStackInSlot(n);
        }
        return Speedmine.mc.player.inventory.getStackInSlot(Speedmine.mc.player.inventory.currentItem);
    }
    
    public float getDestroySpeed(final IBlockState blockState) {
        float n = 1.0f;
        if (this.getEfficientItem(blockState) != null && !this.getEfficientItem(blockState).isEmpty()) {
            n *= this.getEfficientItem(blockState).getDestroySpeed(blockState);
        }
        return n;
    }
    
    public Speedmine() {
        super("Speedmine", "Mines faster.", Module.Category.PLAYER, true, false, false);
        this.range = (Setting<Double>)this.register(new Setting("Range", (Object)6.0, (Object)0.0, (Object)6.0));
        this.rotate2 = (Setting<Boolean>)this.register(new Setting("Rotate", (Object)true));
        this.reset = (Setting<Boolean>)this.register(new Setting("Reset", (Object)false));
        this.mineSwitch = (Setting<InventoryManagerCC.Switch>)this.register(new Setting("SwitchMode", (Object)InventoryManagerCC.Switch.PACKET));
        this.strict = (Setting<Boolean>)this.register(new Setting("Strict", (Object)false));
        this.strictReMine = (Setting<Boolean>)this.register(new Setting("LimitRetry", (Object)true));
        this.boxAlpha = (Setting<Integer>)this.register(new Setting("BoxAlpha", (Object)60, (Object)0, (Object)255));
        Speedmine.INSTANCE = this;
    }
}
