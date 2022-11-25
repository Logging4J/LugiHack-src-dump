//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.block.state.*;
import net.minecraft.entity.player.*;
import me.snow.luigihack.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.eventhandler.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.snow.luigihack.impl.modules.misc.*;
import net.minecraft.item.*;
import net.minecraft.init.*;
import net.minecraft.world.*;
import net.minecraft.block.*;
import net.minecraft.entity.*;
import me.snow.luigihack.impl.modules.player.*;
import net.minecraft.block.material.*;
import net.minecraft.client.entity.*;
import net.minecraft.client.multiplayer.*;
import net.minecraft.util.math.*;
import net.minecraft.util.*;
import net.minecraft.client.*;
import me.snow.luigihack.api.event.events.*;

@Mixin({ PlayerControllerMP.class })
public abstract class MixinPlayerControllerMP
{
    @Shadow
    public int currentPlayerItem;
    
    @Redirect(method = { "onPlayerDamageBlock" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/block/state/IBlockState;getPlayerRelativeBlockHardness(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)F"))
    public float getPlayerRelativeBlockHardnessHook(final IBlockState blockState, final EntityPlayer entityPlayer, final World world, final BlockPos blockPos) {
        return blockState.getPlayerRelativeBlockHardness(entityPlayer, world, blockPos) * ((TpsSync.getInstance().isOn() && (boolean)TpsSync.getInstance().mining.getValue()) ? (1.0f / LuigiHack.serverManager.getTpsFactor()) : 1.0f);
    }
    
    @Shadow
    public abstract void syncCurrentPlayItem();
    
    @Inject(method = { "clickBlock" }, at = { @At("HEAD") }, cancellable = true)
    private void clickBlockHook(final BlockPos blockPos, final EnumFacing enumFacing, final CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        MinecraftForge.EVENT_BUS.post((Event)new BlockEvent(3, blockPos, enumFacing));
    }
    
    @Inject(method = { "onPlayerDamageBlock" }, at = { @At("HEAD") }, cancellable = true)
    private void onPlayerDamageBlockHook(final BlockPos blockPos, final EnumFacing enumFacing, final CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        MinecraftForge.EVENT_BUS.post((Event)new BlockEvent(4, blockPos, enumFacing));
    }
    
    @Inject(method = { "onStoppedUsingItem" }, at = { @At("HEAD") }, cancellable = true)
    public void onStoppedUsingItem(final EntityPlayer entityPlayer, final CallbackInfo callbackInfo) {
        if (entityPlayer.getHeldItem(entityPlayer.getActiveHand()).getItem() instanceof ItemFood && ((PacketEat)LuigiHack.moduleManager.getModuleByClass((Class)PacketEat.class)).isEnabled()) {
            this.syncCurrentPlayItem();
            entityPlayer.stopActiveHand();
            callbackInfo.cancel();
        }
    }
    
    @Inject(method = { "getBlockReachDistance" }, at = { @At("RETURN") }, cancellable = true)
    private void getReachDistanceHook(final CallbackInfoReturnable<Float> callbackInfoReturnable) {
        if (Reach.getInstance().isOn()) {
            final float floatValue = (float)callbackInfoReturnable.getReturnValue();
            callbackInfoReturnable.setReturnValue((Object)(Reach.getInstance().override.getValue() ? Reach.getInstance().reach.getValue() : Float.valueOf(floatValue + (float)Reach.getInstance().add.getValue())));
        }
    }
    
    @Redirect(method = { "processRightClickBlock" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemBlock;canPlaceBlockOnSide(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/EnumFacing;Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/item/ItemStack;)Z"))
    public boolean canPlaceBlockOnSideHook(final ItemBlock itemBlock, final World world, BlockPos offset, EnumFacing up, final EntityPlayer entityPlayer, final ItemStack itemStack) {
        final Block getBlock = world.getBlockState(offset).getBlock();
        if (getBlock == Blocks.SNOW_LAYER && getBlock.isReplaceable((IBlockAccess)world, offset)) {
            up = EnumFacing.UP;
        }
        else if (!getBlock.isReplaceable((IBlockAccess)world, offset)) {
            offset = offset.offset(up);
        }
        final IBlockState getBlockState = world.getBlockState(offset);
        final AxisAlignedBB getCollisionBoundingBox = itemBlock.block.getDefaultState().getCollisionBoundingBox((IBlockAccess)world, offset);
        if (getCollisionBoundingBox != Block.NULL_AABB && !world.checkNoEntityCollision(getCollisionBoundingBox.offset(offset), (Entity)null)) {
            if (BlockTweaks.getINSTANCE().isOff() || !(boolean)BlockTweaks.getINSTANCE().noBlock.getValue()) {
                return false;
            }
        }
        else if (getBlockState.getMaterial() == Material.CIRCUITS && itemBlock.block == Blocks.ANVIL) {
            return true;
        }
        return getBlockState.getBlock().isReplaceable((IBlockAccess)world, offset) && itemBlock.block.canPlaceBlockOnSide(world, offset, up);
    }
    
    @Inject(method = { "processRightClickBlock" }, at = { @At("HEAD") }, cancellable = true)
    public void processRightClickBlock(final EntityPlayerSP entityPlayerSP, final WorldClient worldClient, final BlockPos blockPos, final EnumFacing enumFacing, final Vec3d vec3d, final EnumHand enumHand, final CallbackInfoReturnable<EnumActionResult> callbackInfoReturnable) {
        final ProcessRightClickBlockEvent processRightClickBlockEvent = new ProcessRightClickBlockEvent(blockPos, enumHand, Minecraft.getMinecraft().player.getHeldItem(enumHand));
        MinecraftForge.EVENT_BUS.post((Event)processRightClickBlockEvent);
        if (processRightClickBlockEvent.isCanceled()) {
            callbackInfoReturnable.cancel();
        }
    }
}
