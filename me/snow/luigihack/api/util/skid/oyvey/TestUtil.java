//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util.skid.oyvey;

import net.minecraft.block.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;
import net.minecraft.init.*;
import net.minecraft.util.*;
import net.minecraft.network.*;
import java.awt.*;
import net.minecraft.entity.player.*;
import me.snow.luigihack.*;
import net.minecraft.client.model.*;
import net.minecraft.entity.*;
import java.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.util.math.*;
import me.snow.luigihack.api.util.*;

public class TestUtil
{
    private static /* synthetic */ boolean bind;
    private static /* synthetic */ float[] tickRates;
    private static /* synthetic */ boolean depth;
    public static /* synthetic */ List<Block> emptyBlocks;
    public static /* synthetic */ List<Block> rightclickableBlocks;
    private static final /* synthetic */ Minecraft mc;
    private static /* synthetic */ boolean clean;
    private static /* synthetic */ boolean texture;
    private static /* synthetic */ boolean override;
    
    private static void GLPost(final boolean b, final boolean b2, final boolean b3, final boolean b4, final boolean b5) {
        GlStateManager.depthMask(true);
        if (!b5) {
            GL11.glDisable(2848);
        }
        if (b4) {
            GL11.glEnable(2929);
        }
        if (b3) {
            GL11.glEnable(3553);
        }
        if (!b2) {
            GL11.glDisable(3042);
        }
        if (b) {
            GL11.glEnable(2896);
        }
    }
    
    public static boolean rayTracePlaceCheck(final BlockPos blockPos, final boolean b) {
        return rayTracePlaceCheck(blockPos, b, 1.0f);
    }
    
    public static Vec3d getInterpolatedAmount(final Entity entity, final float n) {
        return getInterpolatedAmount(entity, n, n, n);
    }
    
    public static Vec3d getInterpolatedPos(final Entity entity, final float n) {
        return new Vec3d(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ).add(getInterpolatedAmount(entity, n));
    }
    
    static {
        mc = Minecraft.getMinecraft();
        TestUtil.tickRates = new float[20];
        TestUtil.emptyBlocks = Arrays.asList(Blocks.AIR, (Block)Blocks.FLOWING_LAVA, (Block)Blocks.LAVA, (Block)Blocks.FLOWING_WATER, (Block)Blocks.WATER, Blocks.VINE, Blocks.SNOW_LAYER, (Block)Blocks.TALLGRASS, (Block)Blocks.FIRE);
        TestUtil.rightclickableBlocks = Arrays.asList((Block)Blocks.CHEST, Blocks.TRAPPED_CHEST, Blocks.ENDER_CHEST, Blocks.WHITE_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.SILVER_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.ANVIL, Blocks.WOODEN_BUTTON, Blocks.STONE_BUTTON, (Block)Blocks.UNPOWERED_COMPARATOR, (Block)Blocks.UNPOWERED_REPEATER, (Block)Blocks.POWERED_REPEATER, (Block)Blocks.POWERED_COMPARATOR, Blocks.OAK_FENCE_GATE, Blocks.SPRUCE_FENCE_GATE, Blocks.BIRCH_FENCE_GATE, Blocks.JUNGLE_FENCE_GATE, Blocks.DARK_OAK_FENCE_GATE, Blocks.ACACIA_FENCE_GATE, Blocks.BREWING_STAND, Blocks.DISPENSER, Blocks.DROPPER, Blocks.LEVER, Blocks.NOTEBLOCK, Blocks.JUKEBOX, (Block)Blocks.BEACON, Blocks.BED, Blocks.FURNACE, (Block)Blocks.OAK_DOOR, (Block)Blocks.SPRUCE_DOOR, (Block)Blocks.BIRCH_DOOR, (Block)Blocks.JUNGLE_DOOR, (Block)Blocks.ACACIA_DOOR, (Block)Blocks.DARK_OAK_DOOR, Blocks.CAKE, Blocks.ENCHANTING_TABLE, Blocks.DRAGON_EGG, (Block)Blocks.HOPPER, Blocks.REPEATING_COMMAND_BLOCK, Blocks.COMMAND_BLOCK, Blocks.CHAIN_COMMAND_BLOCK, Blocks.CRAFTING_TABLE);
        TestUtil.depth = GL11.glIsEnabled(2896);
        TestUtil.texture = GL11.glIsEnabled(3042);
        TestUtil.clean = GL11.glIsEnabled(3553);
        TestUtil.bind = GL11.glIsEnabled(2929);
        TestUtil.override = GL11.glIsEnabled(2848);
    }
    
    public static void GlPost() {
        GLPost(TestUtil.depth, TestUtil.texture, TestUtil.clean, TestUtil.bind, TestUtil.override);
    }
    
    public static void openBlock(final BlockPos blockPos) {
        for (final EnumFacing enumFacing : EnumFacing.values()) {
            if (TestUtil.emptyBlocks.contains(TestUtil.mc.world.getBlockState(blockPos.offset(enumFacing)).getBlock())) {
                TestUtil.mc.playerController.processRightClickBlock(TestUtil.mc.player, TestUtil.mc.world, blockPos, enumFacing.getOpposite(), new Vec3d((Vec3i)blockPos), EnumHand.MAIN_HAND);
                return;
            }
        }
    }
    
    public static boolean rayTracePlaceCheck(final BlockPos blockPos, final boolean b, final float n) {
        return !b || TestUtil.mc.world.rayTraceBlocks(new Vec3d(TestUtil.mc.player.posX, TestUtil.mc.player.posY + TestUtil.mc.player.getEyeHeight(), TestUtil.mc.player.posZ), new Vec3d((double)blockPos.getX(), (double)(blockPos.getY() + n), (double)blockPos.getZ()), false, true, false) == null;
    }
    
    private static void GLPre(final boolean b, final boolean b2, final boolean b3, final boolean b4, final boolean b5, final float n) {
        if (b) {
            GL11.glDisable(2896);
        }
        if (!b2) {
            GL11.glEnable(3042);
        }
        GL11.glLineWidth(n);
        if (b3) {
            GL11.glDisable(3553);
        }
        if (b4) {
            GL11.glDisable(2929);
        }
        if (!b5) {
            GL11.glEnable(2848);
        }
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GL11.glHint(3154, 4354);
        GlStateManager.depthMask(false);
    }
    
    public static boolean placeBlock(final BlockPos blockPos) {
        if (isBlockEmpty(blockPos)) {
            for (final EnumFacing enumFacing : EnumFacing.values()) {
                final Block getBlock = TestUtil.mc.world.getBlockState(blockPos.offset(enumFacing)).getBlock();
                final Vec3d vec3d = new Vec3d(blockPos.getX() + 0.5 + enumFacing.getXOffset() * 0.5, blockPos.getY() + 0.5 + enumFacing.getYOffset() * 0.5, blockPos.getZ() + 0.5 + enumFacing.getZOffset() * 0.5);
                if (!TestUtil.emptyBlocks.contains(getBlock) && TestUtil.mc.player.getPositionEyes(TestUtil.mc.getRenderPartialTicks()).distanceTo(vec3d) <= 4.25) {
                    final float[] array = { TestUtil.mc.player.rotationYaw, TestUtil.mc.player.rotationPitch };
                    if (TestUtil.rightclickableBlocks.contains(getBlock)) {
                        TestUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)TestUtil.mc.player, CPacketEntityAction.Action.START_SNEAKING));
                    }
                    TestUtil.mc.playerController.processRightClickBlock(TestUtil.mc.player, TestUtil.mc.world, blockPos.offset(enumFacing), enumFacing.getOpposite(), new Vec3d((Vec3i)blockPos), EnumHand.MAIN_HAND);
                    if (TestUtil.rightclickableBlocks.contains(getBlock)) {
                        TestUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)TestUtil.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
                    }
                    TestUtil.mc.player.swingArm(EnumHand.MAIN_HAND);
                    return true;
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
    
    public static float[][] getBipedRotations(final ModelBiped modelBiped) {
        return new float[][] { { modelBiped.bipedHead.rotateAngleX, modelBiped.bipedHead.rotateAngleY, modelBiped.bipedHead.rotateAngleZ }, { modelBiped.bipedRightArm.rotateAngleX, modelBiped.bipedRightArm.rotateAngleY, modelBiped.bipedRightArm.rotateAngleZ }, { modelBiped.bipedLeftArm.rotateAngleX, modelBiped.bipedLeftArm.rotateAngleY, modelBiped.bipedLeftArm.rotateAngleZ }, { modelBiped.bipedRightLeg.rotateAngleX, modelBiped.bipedRightLeg.rotateAngleY, modelBiped.bipedRightLeg.rotateAngleZ }, { modelBiped.bipedLeftLeg.rotateAngleX, modelBiped.bipedLeftLeg.rotateAngleY, modelBiped.bipedLeftLeg.rotateAngleZ } };
    }
    
    public static boolean canPlaceBlock(final BlockPos blockPos) {
        if (isBlockEmpty(blockPos)) {
            for (final EnumFacing enumFacing : EnumFacing.values()) {
                if (!TestUtil.emptyBlocks.contains(TestUtil.mc.world.getBlockState(blockPos.offset(enumFacing)).getBlock())) {
                    if (TestUtil.mc.player.getPositionEyes(TestUtil.mc.getRenderPartialTicks()).distanceTo(new Vec3d(blockPos.getX() + 0.5 + enumFacing.getXOffset() * 0.5, blockPos.getY() + 0.5 + enumFacing.getYOffset() * 0.5, blockPos.getZ() + 0.5 + enumFacing.getZOffset() * 0.5)) <= 4.25) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public static boolean canSeeBlock(final BlockPos blockPos) {
        return TestUtil.mc.player != null && TestUtil.mc.world.rayTraceBlocks(new Vec3d(TestUtil.mc.player.posX, TestUtil.mc.player.posY + TestUtil.mc.player.getEyeHeight(), TestUtil.mc.player.posZ), new Vec3d((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ()), false, true, false) == null;
    }
    
    public static Vec3d getInterpolatedAmount(final Entity entity, final double n, final double n2, final double n3) {
        return new Vec3d((entity.posX - entity.lastTickPosX) * n, (entity.posY - entity.lastTickPosY) * n2, (entity.posZ - entity.lastTickPosZ) * n3);
    }
    
    public static void GLPre(final float n) {
        TestUtil.depth = GL11.glIsEnabled(2896);
        TestUtil.texture = GL11.glIsEnabled(3042);
        TestUtil.clean = GL11.glIsEnabled(3553);
        TestUtil.bind = GL11.glIsEnabled(2929);
        TestUtil.override = GL11.glIsEnabled(2848);
        GLPre(TestUtil.depth, TestUtil.texture, TestUtil.clean, TestUtil.bind, TestUtil.override, n);
    }
    
    public static boolean isBlockEmpty(final BlockPos blockPos) {
        try {
            if (TestUtil.emptyBlocks.contains(TestUtil.mc.world.getBlockState(blockPos).getBlock())) {
                final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos);
                final Iterator<Entity> iterator = TestUtil.mc.world.loadedEntityList.iterator();
                while (iterator.hasNext()) {
                    final Entity entity;
                    if ((entity = iterator.next()) instanceof EntityLivingBase && axisAlignedBB.intersects(entity.getEntityBoundingBox())) {
                        return false;
                    }
                }
                return true;
            }
        }
        catch (Exception ex) {}
        return false;
    }
    
    public static float getTickRate() {
        float n = 0.0f;
        float n2 = 0.0f;
        for (final float n3 : TestUtil.tickRates) {
            if (n3 > 0.0f) {
                n2 += n3;
                ++n;
            }
        }
        return MathHelper.clamp(n2 / n, 0.0f, 20.0f);
    }
    
    public static void placeCrystalOnBlock(final BlockPos blockPos, final EnumHand enumHand) {
        final RayTraceResult rayTraceBlocks = TestUtil.mc.world.rayTraceBlocks(new Vec3d(TestUtil.mc.player.posX, TestUtil.mc.player.posY + TestUtil.mc.player.getEyeHeight(), TestUtil.mc.player.posZ), new Vec3d(blockPos.getX() + 0.5, blockPos.getY() - 0.5, blockPos.getZ() + 0.5));
        TestUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos, (rayTraceBlocks == null || rayTraceBlocks.sideHit == null) ? EnumFacing.UP : rayTraceBlocks.sideHit, enumHand, 0.0f, 0.0f, 0.0f));
    }
    
    public static Vec3d getInterpolatedRenderPos(final Entity entity, final float n) {
        return getInterpolatedPos(entity, n).subtract(EntityUtil.mc.getRenderManager().renderPosX, EntityUtil.mc.getRenderManager().renderPosY, EntityUtil.mc.getRenderManager().renderPosZ);
    }
}
