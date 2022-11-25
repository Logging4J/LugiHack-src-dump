//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util;

import net.minecraft.client.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.math.*;
import net.minecraft.util.*;
import net.minecraft.block.*;
import net.minecraft.init.*;
import java.util.*;
import net.minecraft.item.*;
import net.minecraft.world.*;
import net.minecraft.network.*;
import net.minecraft.network.play.client.*;
import net.minecraft.entity.projectile.*;
import net.minecraft.block.state.*;
import net.minecraft.client.multiplayer.*;

public class BlockInteractionHelper
{
    private static final /* synthetic */ Minecraft mc;
    
    public static void rotate(final float rotationYaw, final float rotationPitch) {
        Minecraft.getMinecraft().player.rotationYaw = rotationYaw;
        Minecraft.getMinecraft().player.rotationPitch = rotationPitch;
    }
    
    public static boolean hotbarSlotCheckEmpty(final ItemStack itemStack) {
        return itemStack != ItemStack.EMPTY;
    }
    
    public static float getPitch(final Entity entity) {
        final double n = entity.posX - BlockInteractionHelper.mc.player.posX;
        final double n2 = entity.posZ - BlockInteractionHelper.mc.player.posZ;
        return -MathHelper.wrapDegrees(BlockInteractionHelper.mc.player.rotationPitch - (float)(-Math.toDegrees(Math.atan((entity.posY - 1.6 + entity.getEyeHeight() - BlockInteractionHelper.mc.player.posY) / MathHelper.sqrt(n * n + n2 * n2)))));
    }
    
    public static EnumFacing getPlaceableSide(final BlockPos blockPos) {
        for (final EnumFacing enumFacing : EnumFacing.values()) {
            final BlockPos offset = blockPos.offset(enumFacing);
            if (BlockInteractionHelper.mc.world.getBlockState(offset).getBlock().canCollideCheck(BlockInteractionHelper.mc.world.getBlockState(offset), false) && !BlockInteractionHelper.mc.world.getBlockState(offset).getMaterial().isReplaceable()) {
                return enumFacing;
            }
        }
        return null;
    }
    
    public static void rotate(final double[] array) {
        Minecraft.getMinecraft().player.rotationYaw = (float)array[0];
        Minecraft.getMinecraft().player.rotationPitch = (float)array[1];
    }
    
    private static float[] getDirectionToEntity(final Entity entity) {
        return new float[] { getYaw(entity) + BlockInteractionHelper.mc.player.rotationYaw, getPitch(entity) + BlockInteractionHelper.mc.player.rotationPitch };
    }
    
    public static boolean isIntercepted(final BlockPos blockPos) {
        final Iterator<Entity> iterator = BlockInteractionHelper.mc.world.loadedEntityList.iterator();
        while (iterator.hasNext()) {
            if (new AxisAlignedBB(blockPos).intersects(iterator.next().getEntityBoundingBox())) {
                return true;
            }
        }
        return false;
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
    
    public static float[] getLegitRotations(final Vec3d vec3d) {
        final Vec3d eyesPos = getEyesPos();
        final double x = vec3d.x - eyesPos.x;
        final double y = vec3d.y - eyesPos.y;
        final double y2 = vec3d.z - eyesPos.z;
        return new float[] { Wrapper.getPlayer().rotationYaw + MathHelper.wrapDegrees((float)Math.toDegrees(Math.atan2(y2, x)) - 90.0f - Wrapper.getPlayer().rotationYaw), Wrapper.getPlayer().rotationPitch + MathHelper.wrapDegrees((float)(-Math.toDegrees(Math.atan2(y, Math.sqrt(x * x + y2 * y2)))) - Wrapper.getPlayer().rotationPitch) };
    }
    
    public static boolean checkForNeighbours(final BlockPos blockPos) {
        if (!hasNeighbour(blockPos)) {
            final EnumFacing[] values = EnumFacing.values();
            for (int length = values.length, i = 0; i < length; ++i) {
                if (hasNeighbour(blockPos.offset(values[i]))) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }
    
    public static List<BlockPos> getCircle(final BlockPos blockPos, final int n, final float n2, final boolean b) {
        final ArrayList<BlockPos> list = new ArrayList<BlockPos>();
        final int getX = blockPos.getX();
        final int getZ = blockPos.getZ();
        for (int n3 = getX - (int)n2; n3 <= getX + n2; ++n3) {
            for (int n4 = getZ - (int)n2; n4 <= getZ + n2; ++n4) {
                final double n5 = (getX - n3) * (getX - n3) + (getZ - n4) * (getZ - n4);
                if (n5 < n2 * n2 && (!b || n5 >= (n2 - 1.0f) * (n2 - 1.0f))) {
                    list.add(new BlockPos(n3, n, n4));
                }
            }
        }
        return list;
    }
    
    private static float wrapAngleTo180(float n) {
        for (n %= 360.0f; n >= 180.0f; n -= 360.0f) {}
        while (n < -180.0f) {
            n += 360.0f;
        }
        return n;
    }
    
    public static float getYaw(final Entity entity) {
        final double n = entity.posX - BlockInteractionHelper.mc.player.posX;
        final double n2 = entity.posZ - BlockInteractionHelper.mc.player.posZ;
        double degrees;
        if (n2 < 0.0 && n < 0.0) {
            degrees = 90.0 + Math.toDegrees(Math.atan(n2 / n));
        }
        else if (n2 < 0.0 && n > 0.0) {
            degrees = -90.0 + Math.toDegrees(Math.atan(n2 / n));
        }
        else {
            degrees = Math.toDegrees(-Math.atan(n / n2));
        }
        return MathHelper.wrapDegrees(-(BlockInteractionHelper.mc.player.rotationYaw - (float)degrees));
    }
    
    public static float[] getRotationNeededForBlock(final EntityPlayer entityPlayer, final BlockPos blockPos) {
        final double x = blockPos.getX() - entityPlayer.posX;
        final double y = blockPos.getY() + 0.5 - (entityPlayer.posY + entityPlayer.getEyeHeight());
        final double y2 = blockPos.getZ() - entityPlayer.posZ;
        return new float[] { (float)(Math.atan2(y2, x) * 180.0 / 3.141592653589793) - 90.0f, (float)(-(Math.atan2(y, Math.sqrt(x * x + y2 * y2)) * 180.0 / 3.141592653589793)) };
    }
    
    public static double[] calculateLookAt(final double n, final double n2, final double n3, final EntityPlayer entityPlayer) {
        final double n4 = entityPlayer.posX - n;
        final double n5 = entityPlayer.posY - n2;
        final double n6 = entityPlayer.posZ - n3;
        final double sqrt = Math.sqrt(n4 * n4 + n5 * n5 + n6 * n6);
        return new double[] { Math.atan2(n6 / sqrt, n4 / sqrt) * 180.0 / 3.141592653589793 + 90.0, Math.asin(n5 / sqrt) * 180.0 / 3.141592653589793 };
    }
    
    public static void placeBlockScaffold(final BlockPos blockPos) {
        final Vec3d vec3d = new Vec3d(Wrapper.getPlayer().posX, Wrapper.getPlayer().posY + Wrapper.getPlayer().getEyeHeight(), Wrapper.getPlayer().posZ);
        for (final EnumFacing enumFacing : EnumFacing.values()) {
            final BlockPos offset = blockPos.offset(enumFacing);
            final EnumFacing getOpposite = enumFacing.getOpposite();
            if (canBeClicked(offset)) {
                final Vec3d add = new Vec3d((Vec3i)offset).add(0.5, 0.5, 0.5).add(new Vec3d(getOpposite.getDirectionVec()).rotatePitch(0.5f));
                if (vec3d.squareDistanceTo(add) <= 18.0625) {
                    faceVectorPacketInstant(add);
                    processRightClickBlock(offset, getOpposite, add);
                    Wrapper.getPlayer().swingArm(EnumHand.MAIN_HAND);
                    BlockInteractionHelper.mc.rightClickDelayTimer = 4;
                    return;
                }
            }
        }
    }
    
    static {
        blackList = Arrays.asList(Blocks.ENDER_CHEST, (Block)Blocks.CHEST, Blocks.TRAPPED_CHEST, Blocks.CRAFTING_TABLE, Blocks.ANVIL, Blocks.BREWING_STAND, (Block)Blocks.HOPPER, Blocks.DROPPER, Blocks.DISPENSER);
        shulkerList = Arrays.asList(Blocks.WHITE_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.SILVER_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.BLACK_SHULKER_BOX);
        mc = Minecraft.getMinecraft();
    }
    
    public static boolean canBeClicked(final BlockPos blockPos) {
        return getBlock(blockPos).canCollideCheck(getState(blockPos), false);
    }
    
    public static boolean blockCheckNonBlock(final ItemStack itemStack) {
        return itemStack.getItem() instanceof ItemBlock;
    }
    
    public static BlockResistance getBlockResistance(final BlockPos blockPos) {
        if (BlockInteractionHelper.mc.world.isAirBlock(blockPos)) {
            return BlockResistance.Blank;
        }
        if (BlockInteractionHelper.mc.world.getBlockState(blockPos).getBlock().getBlockHardness(BlockInteractionHelper.mc.world.getBlockState(blockPos), (World)BlockInteractionHelper.mc.world, blockPos) != -1.0f && !BlockInteractionHelper.mc.world.getBlockState(blockPos).getBlock().equals(Blocks.OBSIDIAN) && !BlockInteractionHelper.mc.world.getBlockState(blockPos).getBlock().equals(Blocks.ANVIL) && !BlockInteractionHelper.mc.world.getBlockState(blockPos).getBlock().equals(Blocks.ENCHANTING_TABLE) && !BlockInteractionHelper.mc.world.getBlockState(blockPos).getBlock().equals(Blocks.ENDER_CHEST)) {
            return BlockResistance.Breakable;
        }
        if (BlockInteractionHelper.mc.world.getBlockState(blockPos).getBlock().equals(Blocks.OBSIDIAN) || BlockInteractionHelper.mc.world.getBlockState(blockPos).getBlock().equals(Blocks.ANVIL) || BlockInteractionHelper.mc.world.getBlockState(blockPos).getBlock().equals(Blocks.ENCHANTING_TABLE) || BlockInteractionHelper.mc.world.getBlockState(blockPos).getBlock().equals(Blocks.ENDER_CHEST)) {
            return BlockResistance.Resistant;
        }
        if (BlockInteractionHelper.mc.world.getBlockState(blockPos).getBlock().equals(Blocks.BEDROCK)) {
            return BlockResistance.Unbreakable;
        }
        return null;
    }
    
    public static void lookAtBlock(final BlockPos blockPos) {
        rotate(calculateLookAt(blockPos.getX(), blockPos.getY(), blockPos.getZ(), (EntityPlayer)Minecraft.getMinecraft().player));
    }
    
    public static void placeBlock(final BlockPos blockPos, final boolean b, final boolean b2, final boolean b3, final boolean b4, final boolean b5) {
        for (final EnumFacing enumFacing : EnumFacing.values()) {
            if (getBlockResistance(blockPos.offset(enumFacing)) != BlockResistance.Blank && !isIntercepted(blockPos)) {
                final Vec3d vec3d = new Vec3d(blockPos.getX() + 0.5 + enumFacing.getXOffset() * 0.5, blockPos.getY() + 0.5 + enumFacing.getYOffset() * 0.5, blockPos.getZ() + 0.5 + enumFacing.getZOffset() * 0.5);
                final float[] array = { BlockInteractionHelper.mc.player.rotationYaw, BlockInteractionHelper.mc.player.rotationPitch };
                if (b) {
                    BlockInteractionHelper.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation((float)Math.toDegrees(Math.atan2(vec3d.z - BlockInteractionHelper.mc.player.posZ, vec3d.x - BlockInteractionHelper.mc.player.posX)) - 90.0f, (float)(-Math.toDegrees(Math.atan2(vec3d.y - (BlockInteractionHelper.mc.player.posY + BlockInteractionHelper.mc.player.getEyeHeight()), Math.sqrt((vec3d.x - BlockInteractionHelper.mc.player.posX) * (vec3d.x - BlockInteractionHelper.mc.player.posX) + (vec3d.z - BlockInteractionHelper.mc.player.posZ) * (vec3d.z - BlockInteractionHelper.mc.player.posZ))))), BlockInteractionHelper.mc.player.onGround));
                }
                BlockInteractionHelper.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockInteractionHelper.mc.player, CPacketEntityAction.Action.START_SNEAKING));
                if (b3) {
                    BlockInteractionHelper.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos, b2 ? enumFacing : EnumFacing.UP, EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
                }
                else {
                    BlockInteractionHelper.mc.playerController.processRightClickBlock(BlockInteractionHelper.mc.player, BlockInteractionHelper.mc.world, blockPos.offset(enumFacing), b2 ? enumFacing.getOpposite() : EnumFacing.UP, new Vec3d((Vec3i)blockPos), EnumHand.MAIN_HAND);
                }
                if (b4) {
                    BlockInteractionHelper.mc.player.swingArm(EnumHand.MAIN_HAND);
                }
                BlockInteractionHelper.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockInteractionHelper.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
                if (b) {
                    BlockInteractionHelper.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(array[0], array[1], BlockInteractionHelper.mc.player.onGround));
                }
                if (b5) {
                    BlockInteractionHelper.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, blockPos.offset(enumFacing), enumFacing.getOpposite()));
                }
                return;
            }
        }
    }
    
    public static boolean isInterceptedByOther(final BlockPos blockPos) {
        for (final Entity entity : BlockInteractionHelper.mc.world.loadedEntityList) {
            if (entity.equals((Object)BlockInteractionHelper.mc.player)) {
                continue;
            }
            if (new AxisAlignedBB(blockPos).intersects(entity.getEntityBoundingBox())) {
                return true;
            }
        }
        return false;
    }
    
    public static float[] getDirectionToBlock(final int n, final int n2, final int n3, final EnumFacing enumFacing) {
        final EntityEgg entityEgg = new EntityEgg((World)BlockInteractionHelper.mc.world);
        entityEgg.posX = n + 0.5;
        entityEgg.posY = n2 + 0.5;
        entityEgg.posZ = n3 + 0.5;
        final EntityEgg entityEgg2 = entityEgg;
        entityEgg2.posX += enumFacing.getDirectionVec().getX() * 0.25;
        final EntityEgg entityEgg3 = entityEgg;
        entityEgg3.posY += enumFacing.getDirectionVec().getY() * 0.25;
        final EntityEgg entityEgg4 = entityEgg;
        entityEgg4.posZ += enumFacing.getDirectionVec().getZ() * 0.25;
        return getDirectionToEntity((Entity)entityEgg);
    }
    
    private static Vec3d getEyesPos() {
        return new Vec3d(Wrapper.getPlayer().posX, Wrapper.getPlayer().posY + Wrapper.getPlayer().getEyeHeight(), Wrapper.getPlayer().posZ);
    }
    
    private static IBlockState getState(final BlockPos blockPos) {
        return Wrapper.getWorld().getBlockState(blockPos);
    }
    
    private static void processRightClickBlock(final BlockPos blockPos, final EnumFacing enumFacing, final Vec3d vec3d) {
        getPlayerController().processRightClickBlock(Wrapper.getPlayer(), BlockInteractionHelper.mc.world, blockPos, enumFacing, vec3d, EnumHand.MAIN_HAND);
    }
    
    public static List<BlockPos> getSphereRain(final BlockPos blockPos, final float n, final int n2, final boolean b, final boolean b2, final int n3) {
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
    
    private static Block getBlock(final BlockPos blockPos) {
        return getState(blockPos).getBlock();
    }
    
    private static PlayerControllerMP getPlayerController() {
        return Minecraft.getMinecraft().playerController;
    }
    
    public static void faceVectorPacketInstant(final Vec3d vec3d) {
        final float[] legitRotations = getLegitRotations(vec3d);
        Wrapper.getPlayer().connection.sendPacket((Packet)new CPacketPlayer.Rotation(legitRotations[0], legitRotations[1], Wrapper.getPlayer().onGround));
    }
    
    private static boolean hasNeighbour(final BlockPos blockPos) {
        final EnumFacing[] values = EnumFacing.values();
        for (int length = values.length, i = 0; i < length; ++i) {
            if (!Wrapper.getWorld().getBlockState(blockPos.offset(values[i])).getMaterial().isReplaceable()) {
                return true;
            }
        }
        return false;
    }
    
    public enum BlockResistance
    {
        Blank, 
        Breakable, 
        Unbreakable, 
        Resistant;
    }
}
