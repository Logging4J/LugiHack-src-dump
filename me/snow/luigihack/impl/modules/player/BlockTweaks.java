//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.player;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.entity.*;
import net.minecraft.enchantment.*;
import net.minecraft.block.state.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraft.network.play.client.*;
import net.minecraft.world.*;
import me.snow.luigihack.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.event.world.*;
import net.minecraft.item.*;
import net.minecraft.init.*;
import net.minecraft.util.math.*;
import net.minecraft.util.*;
import net.minecraftforge.event.entity.player.*;

public class BlockTweaks extends Module
{
    private /* synthetic */ boolean switched;
    public /* synthetic */ Setting<Boolean> noGhost;
    public /* synthetic */ Setting<Boolean> noFriendAttack;
    public /* synthetic */ Setting<Boolean> autoWeapon;
    public /* synthetic */ Setting<Boolean> autoTool;
    private /* synthetic */ int lastHotbarSlot;
    public /* synthetic */ Setting<Boolean> noBlock;
    public /* synthetic */ Setting<Boolean> destroy;
    private static /* synthetic */ BlockTweaks INSTANCE;
    private /* synthetic */ int currentTargetSlot;
    
    public void onDisable() {
        if (this.switched) {
            this.equip(this.lastHotbarSlot, false);
        }
        this.lastHotbarSlot = -1;
        this.currentTargetSlot = -1;
    }
    
    public void equipBestWeapon(final Entity entity) {
        int n = -1;
        double n2 = 0.0;
        EnumCreatureAttribute enumCreatureAttribute = EnumCreatureAttribute.UNDEFINED;
        if (EntityUtil.isLiving(entity)) {
            enumCreatureAttribute = ((EntityLivingBase)entity).getCreatureAttribute();
        }
        for (int i = 0; i < 9; ++i) {
            final ItemStack getStackInSlot = BlockTweaks.mc.player.inventory.getStackInSlot(i);
            if (!getStackInSlot.isEmpty) {
                if (getStackInSlot.getItem() instanceof ItemTool) {
                    final double n3 = ((ItemTool)getStackInSlot.getItem()).attackDamage + (double)EnchantmentHelper.getModifierForCreature(getStackInSlot, enumCreatureAttribute);
                    if (n3 > n2) {
                        n2 = n3;
                        n = i;
                    }
                }
                else if (getStackInSlot.getItem() instanceof ItemSword) {
                    final double n4;
                    if ((n4 = ((ItemSword)getStackInSlot.getItem()).getAttackDamage() + (double)EnchantmentHelper.getModifierForCreature(getStackInSlot, enumCreatureAttribute)) > n2) {
                        n2 = n4;
                        n = i;
                    }
                }
            }
        }
        this.equip(n, true);
    }
    
    private void equipBestTool(final IBlockState blockState) {
        int n = -1;
        double n2 = 0.0;
        for (int i = 0; i < 9; ++i) {
            final ItemStack getStackInSlot = BlockTweaks.mc.player.inventory.getStackInSlot(i);
            final float getDestroySpeed;
            if (!getStackInSlot.isEmpty && (getDestroySpeed = getStackInSlot.getDestroySpeed(blockState)) > 1.0f) {
                final int getEnchantmentLevel;
                final float n3;
                if ((n3 = (float)(getDestroySpeed + (((getEnchantmentLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.EFFICIENCY, getStackInSlot)) > 0) ? (Math.pow(getEnchantmentLevel, 2.0) + 1.0) : 0.0))) > n2) {
                    n2 = n3;
                    n = i;
                }
            }
        }
        this.equip(n, true);
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        if (fullNullCheck()) {
            return;
        }
        final Entity getEntityFromWorld;
        if ((boolean)this.noFriendAttack.getValue() && send.getPacket() instanceof CPacketUseEntity && (getEntityFromWorld = ((CPacketUseEntity)send.getPacket()).getEntityFromWorld((World)BlockTweaks.mc.world)) != null && LuigiHack.friendManager.isFriend(getEntityFromWorld.getName())) {
            send.setCanceled(true);
        }
    }
    
    public static BlockTweaks getINSTANCE() {
        if (BlockTweaks.INSTANCE == null) {
            BlockTweaks.INSTANCE = new BlockTweaks();
        }
        return BlockTweaks.INSTANCE;
    }
    
    public BlockTweaks() {
        super("BlockTweaks", "Some tweaks for blocks.", Module.Category.PLAYER, true, false, false);
        this.autoTool = (Setting<Boolean>)this.register(new Setting("AutoTool", (Object)false));
        this.autoWeapon = (Setting<Boolean>)this.register(new Setting("AutoWeapon", (Object)false));
        this.noFriendAttack = (Setting<Boolean>)this.register(new Setting("NoFriendAttack", (Object)false));
        this.noBlock = (Setting<Boolean>)this.register(new Setting("NoHitboxBlock", (Object)true));
        this.noGhost = (Setting<Boolean>)this.register(new Setting("NoGlitchBlocks", (Object)false));
        this.destroy = (Setting<Boolean>)this.register(new Setting("Destroy", (Object)false, p0 -> (boolean)this.noGhost.getValue()));
        this.lastHotbarSlot = -1;
        this.currentTargetSlot = -1;
        this.setInstance();
    }
    
    @SubscribeEvent
    public void onBreak(final BlockEvent.BreakEvent breakEvent) {
        if (fullNullCheck() || !(boolean)this.noGhost.getValue() || !(boolean)this.destroy.getValue()) {
            return;
        }
        if (!(BlockTweaks.mc.player.getHeldItemMainhand().getItem() instanceof ItemBlock)) {
            this.removeGlitchBlocks(BlockTweaks.mc.player.getPosition());
        }
    }
    
    private void removeGlitchBlocks(final BlockPos blockPos) {
        for (int i = -4; i <= 4; ++i) {
            for (int j = -4; j <= 4; ++j) {
                for (int k = -4; k <= 4; ++k) {
                    final BlockPos blockPos2 = new BlockPos(blockPos.getX() + i, blockPos.getY() + j, blockPos.getZ() + k);
                    if (BlockTweaks.mc.world.getBlockState(blockPos2).getBlock().equals(Blocks.AIR)) {
                        BlockTweaks.mc.playerController.processRightClickBlock(BlockTweaks.mc.player, BlockTweaks.mc.world, blockPos2, EnumFacing.DOWN, new Vec3d(0.5, 0.5, 0.5), EnumHand.MAIN_HAND);
                    }
                }
            }
        }
    }
    
    static {
        BlockTweaks.INSTANCE = new BlockTweaks();
    }
    
    public void onUpdate() {
        if (!fullNullCheck()) {
            if (BlockTweaks.mc.player.inventory.currentItem != this.lastHotbarSlot && BlockTweaks.mc.player.inventory.currentItem != this.currentTargetSlot) {
                this.lastHotbarSlot = BlockTweaks.mc.player.inventory.currentItem;
            }
            if (!BlockTweaks.mc.gameSettings.keyBindAttack.isKeyDown() && this.switched) {
                this.equip(this.lastHotbarSlot, false);
            }
        }
    }
    
    @SubscribeEvent
    public void onBlockInteract(final PlayerInteractEvent.LeftClickBlock leftClickBlock) {
        if ((boolean)this.autoTool.getValue() && !fullNullCheck() && leftClickBlock.getPos() != null) {
            this.equipBestTool(BlockTweaks.mc.world.getBlockState(leftClickBlock.getPos()));
        }
    }
    
    private void setInstance() {
        BlockTweaks.INSTANCE = this;
    }
    
    private void equip(final int n, final boolean switched) {
        if (n != -1) {
            if (n != BlockTweaks.mc.player.inventory.currentItem) {
                this.lastHotbarSlot = BlockTweaks.mc.player.inventory.currentItem;
            }
            this.currentTargetSlot = n;
            BlockTweaks.mc.player.inventory.currentItem = n;
            BlockTweaks.mc.playerController.syncCurrentPlayItem();
            this.switched = switched;
        }
    }
    
    @SubscribeEvent
    public void onAttack(final AttackEntityEvent attackEntityEvent) {
        if ((boolean)this.autoWeapon.getValue() && !fullNullCheck() && attackEntityEvent.getTarget() != null) {
            this.equipBestWeapon(attackEntityEvent.getTarget());
        }
    }
}
