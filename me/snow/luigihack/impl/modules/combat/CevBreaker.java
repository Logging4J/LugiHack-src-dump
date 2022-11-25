//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.combat;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.impl.modules.player.*;
import me.snow.luigihack.*;
import net.minecraft.block.*;
import net.minecraft.init.*;
import me.snow.luigihack.impl.command.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import me.snow.luigihack.api.util.skid.cr.*;
import me.snow.luigihack.api.util.skid.oyvey.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.entity.item.*;
import java.util.*;

public class CevBreaker extends Module
{
    /* synthetic */ int civCounter;
    public /* synthetic */ Setting<Float> range;
    /* synthetic */ int progress;
    /* synthetic */ int sleep;
    /* synthetic */ boolean flag;
    /* synthetic */ boolean breakFlag;
    /* synthetic */ Entity currentEntity;
    
    @Override
    public void onEnable() {
        if (InstantMine.getInstance().isOn()) {
            InstantMine.getInstance().disable();
        }
        this.findTarget();
        this.progress = 0;
        this.breakFlag = false;
        this.flag = false;
        this.civCounter = 0;
        this.sleep = 0;
        super.onEnable();
    }
    
    public void findTarget() {
        this.currentEntity = (Entity)CevBreaker.mc.world.loadedEntityList.stream().filter(entity -> entity != CevBreaker.mc.player && entity instanceof EntityLivingBase && entity.getDistance((Entity)CevBreaker.mc.player) < (double)(float)this.range.getValue() && !LuigiHack.friendManager.isFriend(entity.getName())).findFirst().orElse(null);
    }
    
    private int findMaterials(final Block block) {
        for (int i = 0; i < 9; ++i) {
            if (CevBreaker.mc.player.inventory.getStackInSlot(i).getItem() instanceof ItemBlock && ((ItemBlock)CevBreaker.mc.player.inventory.getStackInSlot(i).getItem()).getBlock() == block) {
                return i;
            }
        }
        return -1;
    }
    
    @Override
    public void onDisable() {
        if (InstantMine.getInstance().isOff()) {
            InstantMine.getInstance().enable();
        }
        super.onDisable();
    }
    
    @Override
    public void onTick() {
        final int item = this.findItem(Items.DIAMOND_PICKAXE);
        final int item2 = this.findItem(Items.END_CRYSTAL);
        final int materials = this.findMaterials(Blocks.OBSIDIAN);
        final BlockPos[] a = { new BlockPos(0, 0, 1), new BlockPos(0, 1, 1), new BlockPos(0, 2, 1), new BlockPos(0, 2, 0) };
        final int slot = InventoryUtilsCr.getSlot();
        if (item == -1 || item2 == -1 || materials == -1) {
            Command.sendMessage("Pix or Crystal or Obsidian No Material");
            this.disable();
            return;
        }
        if (this.currentEntity == null || this.currentEntity.getDistance((Entity)CevBreaker.mc.player) > (double)(float)this.range.getValue()) {
            this.findTarget();
        }
        if (this.currentEntity != null) {
            final Entity currentEntity = this.currentEntity;
            if (currentEntity instanceof EntityPlayer && !LuigiHack.friendManager.isFriend(currentEntity.getName())) {
                if (item2 == -1 || item2 != -1 || !((ItemStack)CevBreaker.mc.player.inventory.offHandInventory.get(0)).getItem().getClass().equals(Item.getItemById(426).getClass())) {}
                if (this.sleep > 0) {
                    --this.sleep;
                }
                else {
                    currentEntity.move(MoverType.SELF, 0.0, -2.0, 0.0);
                    switch (this.progress) {
                        case 0: {
                            final BlockPos blockPos = new BlockPos(currentEntity);
                            for (final BlockPos blockPos2 : a) {
                                if (Arrays.asList(a).indexOf(blockPos2) != -1 && this.civCounter < 1) {
                                    this.flag = true;
                                    InventoryUtilsCr.setSlot(materials);
                                }
                                else {
                                    InventoryUtilsCr.setSlot(materials);
                                }
                                final BlockUtilsCr placeable = BlockUtilsCr.isPlaceable(blockPos.add((Vec3i)blockPos2), 0.0, true);
                                if (placeable != null) {
                                    placeable.doPlace(true);
                                }
                            }
                            InventoryUtilsCr.setSlot(item2);
                            CrystalUtil.placeCrystal(new BlockPos(currentEntity.posX, currentEntity.posY + 3.0, currentEntity.posZ));
                            ++this.progress;
                            break;
                        }
                        case 1: {
                            InventoryUtilsCr.setSlot(item);
                            CevBreaker.mc.playerController.onPlayerDamageBlock(new BlockPos(currentEntity).add(0, 2, 0), EnumFacing.UP);
                            CevBreaker.mc.getConnection().sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, new BlockPos(currentEntity).add(0, 2, 0), EnumFacing.UP));
                            if (CevBreaker.mc.world.isAirBlock(new BlockPos(currentEntity).add(0, 2, 0))) {
                                for (final Entity entity : CevBreaker.mc.world.loadedEntityList) {
                                    if (currentEntity.getDistance(entity) <= (double)(float)this.range.getValue()) {
                                        if (!(entity instanceof EntityEnderCrystal)) {
                                            continue;
                                        }
                                        CevBreaker.mc.playerController.attackEntity((EntityPlayer)CevBreaker.mc.player, entity);
                                    }
                                }
                                this.breakFlag = true;
                            }
                            if (this.civCounter < 1) {
                                CevBreaker.mc.playerController.onPlayerDamageBlock(new BlockPos(currentEntity).add(0, 2, 0), EnumFacing.UP);
                                this.sleep += 30;
                            }
                            ++this.progress;
                            break;
                        }
                        case 2: {
                            int n = 0;
                            for (final Entity entity2 : CevBreaker.mc.world.loadedEntityList) {
                                if (currentEntity.getDistance(entity2) <= (double)(float)this.range.getValue()) {
                                    if (!(entity2 instanceof EntityEnderCrystal)) {
                                        continue;
                                    }
                                    CevBreaker.mc.playerController.attackEntity((EntityPlayer)CevBreaker.mc.player, entity2);
                                    ++n;
                                }
                            }
                            if (n != 0 && !this.flag) {
                                break;
                            }
                            ++this.progress;
                            break;
                        }
                        case 3: {
                            BlockUtilsCr.doPlace(BlockUtilsCr.isPlaceable(new BlockPos(currentEntity.posX, currentEntity.posY + 2.0, currentEntity.posZ), 0.0, true), true);
                            InventoryUtilsCr.setSlot(materials);
                            this.progress = 0;
                            ++this.civCounter;
                            break;
                        }
                    }
                }
                InventoryUtilsCr.setSlot(slot);
                return;
            }
            InventoryUtilsCr.setSlot(slot);
        }
    }
    
    private int findItem(final Item item) {
        if (item == Items.END_CRYSTAL && CevBreaker.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) {
            return 999;
        }
        for (int i = 0; i < 9; ++i) {
            if (CevBreaker.mc.player.inventory.getStackInSlot(i).getItem() == item) {
                return i;
            }
        }
        return -1;
    }
    
    public CevBreaker() {
        super("CevBreaker", "CevBreaker skid from crberry", Category.COMBAT, true, false, false);
        this.range = (Setting<Float>)this.register(new Setting("Range", (Object)4.9f, (Object)0.0f, (Object)6.0f));
        this.progress = 0;
    }
}
