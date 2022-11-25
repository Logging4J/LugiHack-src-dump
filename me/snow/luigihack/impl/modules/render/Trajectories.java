//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.render;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import org.lwjgl.opengl.*;
import me.snow.luigihack.api.event.events.*;
import me.snow.luigihack.impl.modules.client.*;
import java.awt.*;
import net.minecraft.util.*;
import net.minecraft.init.*;
import net.minecraft.client.renderer.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.*;
import org.lwjgl.util.glu.*;
import net.minecraft.item.*;
import net.minecraft.client.entity.*;
import java.util.*;
import com.google.common.base.*;

public class Trajectories extends Module
{
    public /* synthetic */ Setting<Boolean> colorSync;
    public /* synthetic */ Setting<Integer> green;
    public /* synthetic */ Setting<Integer> blue;
    public /* synthetic */ Setting<Integer> alpha;
    public /* synthetic */ Setting<Integer> red;
    
    public void enableGL3D(final float n) {
        GL11.glDisable(3008);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glEnable(2884);
        Trajectories.mc.entityRenderer.disableLightmap();
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
        GL11.glLineWidth(n);
    }
    
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (Trajectories.mc.world != null && Trajectories.mc.player != null) {
            final Color color = this.colorSync.getValue() ? Colors.INSTANCE.getCurrentColor() : new Color((int)this.red.getValue(), (int)this.green.getValue(), (int)this.blue.getValue(), (int)this.alpha.getValue());
            Trajectories.mc.getRenderManager();
            final double n = Trajectories.mc.player.lastTickPosX + (Trajectories.mc.player.posX - Trajectories.mc.player.lastTickPosX) * render3DEvent.getPartialTicks();
            final double n2 = Trajectories.mc.player.lastTickPosY + (Trajectories.mc.player.posY - Trajectories.mc.player.lastTickPosY) * render3DEvent.getPartialTicks();
            final double n3 = Trajectories.mc.player.lastTickPosZ + (Trajectories.mc.player.posZ - Trajectories.mc.player.lastTickPosZ) * render3DEvent.getPartialTicks();
            Trajectories.mc.player.getHeldItem(EnumHand.MAIN_HAND);
            if (Trajectories.mc.gameSettings.thirdPersonView == 0 && (Trajectories.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemBow || Trajectories.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemFishingRod || Trajectories.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemEnderPearl || Trajectories.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemEgg || Trajectories.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemSnowball || Trajectories.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemExpBottle)) {
                GL11.glPushMatrix();
                final Item getItem = Trajectories.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem();
                double n4 = n - MathHelper.cos(Trajectories.mc.player.rotationYaw / 180.0f * 3.1415927f) * 0.16f;
                double n5 = n2 + Trajectories.mc.player.getEyeHeight() - 0.1000000014901161;
                double n6 = n3 - MathHelper.sin(Trajectories.mc.player.rotationYaw / 180.0f * 3.1415927f) * 0.16f;
                final double n7 = -MathHelper.sin(Trajectories.mc.player.rotationYaw / 180.0f * 3.1415927f) * MathHelper.cos(Trajectories.mc.player.rotationPitch / 180.0f * 3.1415927f) * ((getItem instanceof ItemBow) ? 1.0 : 0.4);
                final double n8 = -MathHelper.sin(Trajectories.mc.player.rotationPitch / 180.0f * 3.1415927f) * ((getItem instanceof ItemBow) ? 1.0 : 0.4);
                final double n9 = MathHelper.cos(Trajectories.mc.player.rotationYaw / 180.0f * 3.1415927f) * MathHelper.cos(Trajectories.mc.player.rotationPitch / 180.0f * 3.1415927f) * ((getItem instanceof ItemBow) ? 1.0 : 0.4);
                final float n10 = (72000 - Trajectories.mc.player.getItemInUseCount()) / 20.0f;
                float n11 = (n10 * n10 + n10 * 2.0f) / 3.0f;
                if (n11 > 1.0f) {
                    n11 = 1.0f;
                }
                final float sqrt = MathHelper.sqrt(n7 * n7 + n8 * n8 + n9 * n9);
                final double n12 = n7 / sqrt;
                final double n13 = n8 / sqrt;
                final double n14 = n9 / sqrt;
                final float n15 = (getItem instanceof ItemBow) ? (n11 * 2.0f) : ((getItem instanceof ItemFishingRod) ? 1.25f : ((Trajectories.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() == Items.EXPERIENCE_BOTTLE) ? 0.9f : 1.0f));
                double n16 = n12 * (n15 * ((getItem instanceof ItemFishingRod) ? 0.75f : ((Trajectories.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() == Items.EXPERIENCE_BOTTLE) ? 0.75f : 1.5f)));
                double n17 = n13 * (n15 * ((getItem instanceof ItemFishingRod) ? 0.75f : ((Trajectories.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() == Items.EXPERIENCE_BOTTLE) ? 0.75f : 1.5f)));
                double n18 = n14 * (n15 * ((getItem instanceof ItemFishingRod) ? 0.75f : ((Trajectories.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() == Items.EXPERIENCE_BOTTLE) ? 0.75f : 1.5f)));
                this.enableGL3D(2.0f);
                GlStateManager.color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, (int)this.alpha.getValue() / 255.0f);
                GL11.glEnable(2848);
                final float n19 = (float)((getItem instanceof ItemBow) ? 0.3 : 0.25);
                int n20 = 0;
                EntityPlayerSP entityPlayerSP = null;
                RayTraceResult rayTraceResult = null;
                while (n20 == 0 && n5 > 0.0) {
                    final Vec3d vec3d = new Vec3d(n4, n5, n6);
                    final Vec3d vec3d2 = new Vec3d(n4 + n16, n5 + n17, n6 + n18);
                    final RayTraceResult rayTraceBlocks = Trajectories.mc.world.rayTraceBlocks(vec3d, vec3d2, false, true, false);
                    if (rayTraceBlocks != null && rayTraceBlocks.typeOfHit != RayTraceResult.Type.MISS) {
                        rayTraceResult = rayTraceBlocks;
                        n20 = 1;
                    }
                    for (final Entity entity : this.getEntitiesWithinAABB(new AxisAlignedBB(n4 - n19, n5 - n19, n6 - n19, n4 + n19, n5 + n19, n6 + n19).offset(n16, n17, n18).expand(1.0, 1.0, 1.0))) {
                        if (entity.canBeCollidedWith() && entity != Trajectories.mc.player) {
                            final RayTraceResult calculateIntercept = entity.getEntityBoundingBox().expand(0.30000001192092896, 0.30000001192092896, 0.30000001192092896).calculateIntercept(vec3d, vec3d2);
                            if (calculateIntercept == null) {
                                continue;
                            }
                            n20 = 1;
                            entityPlayerSP = (EntityPlayerSP)entity;
                            rayTraceResult = calculateIntercept;
                        }
                    }
                    if (entityPlayerSP != null) {
                        GlStateManager.color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, (int)this.alpha.getValue() / 255.0f);
                    }
                    n4 += n16;
                    n5 += n17;
                    n6 += n18;
                    n16 *= 0.9900000095367432;
                    final double n21 = n17 * 0.9900000095367432;
                    n18 *= 0.9900000095367432;
                    n17 = n21 - ((getItem instanceof ItemBow) ? 0.05 : 0.03);
                    this.drawLine3D(n4 - n, n5 - n2, n6 - n3);
                }
                if (rayTraceResult != null && rayTraceResult.typeOfHit == RayTraceResult.Type.BLOCK) {
                    GlStateManager.translate(n4 - n, n5 - n2, n6 - n3);
                    final int getIndex = rayTraceResult.sideHit.getIndex();
                    if (getIndex == 2) {
                        GlStateManager.rotate(90.0f, 1.0f, 0.0f, 0.0f);
                    }
                    else if (getIndex == 3) {
                        GlStateManager.rotate(90.0f, 1.0f, 0.0f, 0.0f);
                    }
                    else if (getIndex == 4) {
                        GlStateManager.rotate(90.0f, 0.0f, 0.0f, 1.0f);
                    }
                    else if (getIndex == 5) {
                        GlStateManager.rotate(90.0f, 0.0f, 0.0f, 1.0f);
                    }
                    final Cylinder cylinder = new Cylinder();
                    GlStateManager.rotate(-90.0f, 1.0f, 0.0f, 0.0f);
                    cylinder.setDrawStyle(100011);
                    if (entityPlayerSP != null) {
                        GlStateManager.color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, (int)this.alpha.getValue() / 255.0f);
                        GL11.glLineWidth(2.5f);
                        cylinder.draw(0.5f, 0.5f, 0.0f, (int)9, 1);
                        GL11.glLineWidth(0.1f);
                        GlStateManager.color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, (int)this.alpha.getValue() / 255.0f);
                    }
                    cylinder.draw(0.5f, 0.2f, 0.0f, (int)9, 1);
                }
                this.disableGL3D();
                GL11.glPopMatrix();
            }
        }
    }
    
    public Trajectories() {
        super("Trajectories", "Draws trajectories.", Module.Category.RENDER, false, false, false);
        this.colorSync = (Setting<Boolean>)this.register(new Setting("CSync", (Object)false));
        this.red = (Setting<Integer>)this.register(new Setting("Red", (Object)0, (Object)0, (Object)255));
        this.green = (Setting<Integer>)this.register(new Setting("Green", (Object)255, (Object)0, (Object)255));
        this.blue = (Setting<Integer>)this.register(new Setting("Blue", (Object)0, (Object)0, (Object)255));
        this.alpha = (Setting<Integer>)this.register(new Setting("Alpha", (Object)170, (Object)0, (Object)255));
    }
    
    public void drawLine3D(final double n, final double n2, final double n3) {
        GL11.glVertex3d(n, n2, n3);
    }
    
    public void disableGL3D() {
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDisable(3042);
        GL11.glEnable(3008);
        GL11.glDepthMask(true);
        GL11.glCullFace(1029);
        GL11.glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glHint(3155, 4352);
    }
    
    private List<Entity> getEntitiesWithinAABB(final AxisAlignedBB axisAlignedBB) {
        final ArrayList<Entity> list = new ArrayList<Entity>();
        final int floor = MathHelper.floor((axisAlignedBB.minX - 2.0) / 16.0);
        final int floor2 = MathHelper.floor((axisAlignedBB.maxX + 2.0) / 16.0);
        final int floor3 = MathHelper.floor((axisAlignedBB.minZ - 2.0) / 16.0);
        final int floor4 = MathHelper.floor((axisAlignedBB.maxZ + 2.0) / 16.0);
        for (int i = floor; i <= floor2; ++i) {
            for (int j = floor3; j <= floor4; ++j) {
                if (Trajectories.mc.world.getChunkProvider().getLoadedChunk(i, j) != null) {
                    Trajectories.mc.world.getChunk(i, j).getEntitiesWithinAABBForEntity((Entity)Trajectories.mc.player, axisAlignedBB, (List)list, (Predicate)null);
                }
            }
        }
        return list;
    }
}
