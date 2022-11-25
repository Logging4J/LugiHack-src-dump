//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.mixin.mixins;

import net.minecraft.entity.item.*;
import net.minecraft.client.renderer.entity.*;
import java.util.*;
import net.minecraft.client.*;
import net.minecraft.util.*;
import me.snow.luigihack.impl.modules.render.*;
import net.minecraft.client.renderer.block.model.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.util.math.*;
import net.minecraft.block.material.*;
import org.lwjgl.opengl.*;
import net.minecraft.item.*;
import net.minecraft.client.renderer.*;
import net.minecraft.entity.*;
import net.minecraftforge.client.*;

@Mixin({ RenderEntityItem.class })
public abstract class MixinRenderEntityItem extends MixinRenderer<EntityItem>
{
    @Shadow
    @Final
    private RenderItem itemRenderer;
    @Shadow
    @Final
    private Random random;
    private Minecraft mc;
    private long tick;
    private double rotation;
    
    public MixinRenderEntityItem() {
        this.mc = Minecraft.getMinecraft();
    }
    
    @Shadow
    public abstract int getModelCount(final ItemStack p0);
    
    @Shadow
    public abstract boolean shouldSpreadItems();
    
    @Shadow
    public abstract boolean shouldBob();
    
    @Shadow
    protected abstract ResourceLocation getEntityTexture(final EntityItem p0);
    
    private double formPositiv(final float n) {
        return (n > 0.0f) ? n : ((double)(-n));
    }
    
    @Overwrite
    private int transformModelCount(final EntityItem entityItem, final double n, final double n2, final double n3, final float n4, final IBakedModel bakedModel) {
        if (ItemPhysics.INSTANCE.isEnabled()) {
            final ItemStack getItem = entityItem.getItem();
            if (getItem.getItem() == null) {
                return 0;
            }
            final boolean isAmbientOcclusion = bakedModel.isAmbientOcclusion();
            final int getModelCount = this.getModelCount(getItem);
            GlStateManager.translate((float)n, (float)n2 + 0.0f + 0.1f, (float)n3);
            final float n5 = 0.0f;
            if (isAmbientOcclusion || (this.mc.getRenderManager().options != null && this.mc.getRenderManager().options.fancyGraphics)) {
                GlStateManager.rotate(n5, 0.0f, 1.0f, 0.0f);
            }
            if (!isAmbientOcclusion) {
                GlStateManager.translate(-0.0f * (getModelCount - 1) * 0.5f, -0.0f * (getModelCount - 1) * 0.5f, -0.046875f * (getModelCount - 1) * 0.5f);
            }
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
            return getModelCount;
        }
        else {
            final ItemStack getItem2 = entityItem.getItem();
            if (getItem2.getItem() == null) {
                return 0;
            }
            final boolean isGui3d = bakedModel.isGui3d();
            final int getModelCount2 = this.getModelCount(getItem2);
            GlStateManager.translate((float)n, (float)n2 + (this.shouldBob() ? (MathHelper.sin((entityItem.getAge() + n4) / 10.0f + entityItem.hoverStart) * 0.1f + 0.1f) : 0.0f) + 0.25f * bakedModel.getItemCameraTransforms().getTransform(ItemCameraTransforms.TransformType.GROUND).scale.y, (float)n3);
            if (isGui3d || this.renderManager.options != null) {
                GlStateManager.rotate(((entityItem.getAge() + n4) / 20.0f + entityItem.hoverStart) * 57.295776f, 0.0f, 1.0f, 0.0f);
            }
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
            return getModelCount2;
        }
    }
    
    @Overwrite
    public void doRender(final EntityItem entityItem, final double n, final double n2, final double n3, final float n4, final float n5) {
        if (ItemPhysics.INSTANCE.isEnabled()) {
            this.rotation = (System.nanoTime() - this.tick) / 3000000.0 * 1.0;
            if (!this.mc.inGameHasFocus) {
                this.rotation = 0.0;
            }
            final ItemStack getItem = entityItem.getItem();
            if (getItem.getItem() != null) {
                this.random.setSeed(187L);
                boolean b = false;
                if (TextureMap.LOCATION_BLOCKS_TEXTURE != null) {
                    this.mc.getRenderManager().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
                    this.mc.getRenderManager().renderEngine.getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).setBlurMipmap(false, false);
                    b = true;
                }
                GlStateManager.enableRescaleNormal();
                GlStateManager.alphaFunc(516, 0.1f);
                GlStateManager.enableBlend();
                GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
                GlStateManager.pushMatrix();
                final IBakedModel getItemModel = this.itemRenderer.getItemModelMesher().getItemModel(getItem);
                final int transformModelCount = this.transformModelCount(entityItem, n, n2, n3, n5, getItemModel);
                final BlockPos blockPos = new BlockPos((Entity)entityItem);
                if (entityItem.rotationPitch > 360.0f) {
                    entityItem.rotationPitch = 0.0f;
                }
                if (entityItem != null && !Double.isNaN(entityItem.posX) && !Double.isNaN(entityItem.posY) && !Double.isNaN(entityItem.posZ) && entityItem.world != null) {
                    if (entityItem.onGround) {
                        if (entityItem.rotationPitch != 0.0f && entityItem.rotationPitch != 90.0f && entityItem.rotationPitch != 180.0f && entityItem.rotationPitch != 270.0f) {
                            final double formPositiv = this.formPositiv(entityItem.rotationPitch);
                            final double formPositiv2 = this.formPositiv(entityItem.rotationPitch - 90.0f);
                            final double formPositiv3 = this.formPositiv(entityItem.rotationPitch - 180.0f);
                            final double formPositiv4 = this.formPositiv(entityItem.rotationPitch - 270.0f);
                            if (formPositiv <= formPositiv2 && formPositiv <= formPositiv3 && formPositiv <= formPositiv4) {
                                if (entityItem.rotationPitch < 0.0f) {
                                    entityItem.rotationPitch += (float)this.rotation;
                                }
                                else {
                                    entityItem.rotationPitch -= (float)this.rotation;
                                }
                            }
                            if (formPositiv2 < formPositiv && formPositiv2 <= formPositiv3 && formPositiv2 <= formPositiv4) {
                                if (entityItem.rotationPitch - 90.0f < 0.0f) {
                                    entityItem.rotationPitch += (float)this.rotation;
                                }
                                else {
                                    entityItem.rotationPitch -= (float)this.rotation;
                                }
                            }
                            if (formPositiv3 < formPositiv2 && formPositiv3 < formPositiv && formPositiv3 <= formPositiv4) {
                                if (entityItem.rotationPitch - 180.0f < 0.0f) {
                                    entityItem.rotationPitch += (float)this.rotation;
                                }
                                else {
                                    entityItem.rotationPitch -= (float)this.rotation;
                                }
                            }
                            if (formPositiv4 < formPositiv2 && formPositiv4 < formPositiv3 && formPositiv4 < formPositiv) {
                                if (entityItem.rotationPitch - 270.0f < 0.0f) {
                                    entityItem.rotationPitch += (float)this.rotation;
                                }
                                else {
                                    entityItem.rotationPitch -= (float)this.rotation;
                                }
                            }
                        }
                    }
                    else {
                        final BlockPos blockPos2 = new BlockPos((Entity)entityItem);
                        blockPos2.add(0, 1, 0);
                        if (entityItem.isInsideOfMaterial(Material.WATER) | entityItem.world.getBlockState(blockPos2).getMaterial() == Material.WATER | entityItem.world.getBlockState(blockPos).getMaterial() == Material.WATER | entityItem.isInWater()) {
                            entityItem.rotationPitch += (float)(this.rotation / 4.0);
                        }
                        else {
                            entityItem.rotationPitch += (float)(this.rotation * 2.0);
                        }
                    }
                }
                GL11.glRotatef(entityItem.rotationYaw, 0.0f, 1.0f, 0.0f);
                GL11.glRotatef(entityItem.rotationPitch + 90.0f, 1.0f, 0.0f, 0.0f);
                for (int i = 0; i < transformModelCount; ++i) {
                    if (getItemModel.isAmbientOcclusion()) {
                        GlStateManager.pushMatrix();
                        GlStateManager.scale((float)ItemPhysics.INSTANCE.Scaling.getValue(), (float)ItemPhysics.INSTANCE.Scaling.getValue(), (float)ItemPhysics.INSTANCE.Scaling.getValue());
                        this.itemRenderer.renderItem(getItem, getItemModel);
                        GlStateManager.popMatrix();
                    }
                    else {
                        GlStateManager.pushMatrix();
                        if (i > 0 && this.shouldSpreadItems()) {
                            GlStateManager.translate(0.0f, 0.0f, 0.046875f * i);
                        }
                        this.itemRenderer.renderItem(getItem, getItemModel);
                        if (!this.shouldSpreadItems()) {
                            GlStateManager.translate(0.0f, 0.0f, 0.046875f);
                        }
                        GlStateManager.popMatrix();
                    }
                }
                GlStateManager.popMatrix();
                GlStateManager.disableRescaleNormal();
                GlStateManager.disableBlend();
                this.mc.getRenderManager().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
                if (b) {
                    this.mc.getRenderManager().renderEngine.getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).restoreLastBlurMipmap();
                }
            }
            return;
        }
        final ItemStack getItem2 = entityItem.getItem();
        this.random.setSeed(getItem2.isEmpty() ? 187 : (Item.getIdFromItem(getItem2.getItem()) + getItem2.getMetadata()));
        boolean b2 = false;
        if (this.bindEntityTexture(entityItem)) {
            this.renderManager.renderEngine.getTexture(this.getEntityTexture(entityItem)).setBlurMipmap(false, false);
            b2 = true;
        }
        GlStateManager.enableRescaleNormal();
        GlStateManager.alphaFunc(516, 0.1f);
        GlStateManager.enableBlend();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.pushMatrix();
        final IBakedModel getItemModelWithOverrides = this.itemRenderer.getItemModelWithOverrides(getItem2, entityItem.world, (EntityLivingBase)null);
        final int transformModelCount2 = this.transformModelCount(entityItem, n, n2, n3, n5, getItemModelWithOverrides);
        final boolean isGui3d = getItemModelWithOverrides.isGui3d();
        if (!isGui3d) {
            GlStateManager.translate(-0.0f * (transformModelCount2 - 1) * 0.5f, -0.0f * (transformModelCount2 - 1) * 0.5f, -0.09375f * (transformModelCount2 - 1) * 0.5f);
        }
        if (this.renderOutlines) {
            GlStateManager.enableColorMaterial();
            GlStateManager.enableOutlineMode(this.getTeamColor(entityItem));
        }
        for (int j = 0; j < transformModelCount2; ++j) {
            if (isGui3d) {
                GlStateManager.pushMatrix();
                if (j > 0) {
                    final float n6 = (this.random.nextFloat() * 2.0f - 1.0f) * 0.15f;
                    final float n7 = (this.random.nextFloat() * 2.0f - 1.0f) * 0.15f;
                    GlStateManager.translate(this.shouldSpreadItems() ? n6 : 0.0f, this.shouldSpreadItems() ? n7 : 0.0f, (this.random.nextFloat() * 2.0f - 1.0f) * 0.15f);
                }
                this.itemRenderer.renderItem(getItem2, ForgeHooksClient.handleCameraTransforms(getItemModelWithOverrides, ItemCameraTransforms.TransformType.GROUND, false));
                GlStateManager.popMatrix();
            }
            else {
                GlStateManager.pushMatrix();
                if (j > 0) {
                    GlStateManager.translate((this.random.nextFloat() * 2.0f - 1.0f) * 0.15f * 0.5f, (this.random.nextFloat() * 2.0f - 1.0f) * 0.15f * 0.5f, 0.0f);
                }
                this.itemRenderer.renderItem(getItem2, ForgeHooksClient.handleCameraTransforms(getItemModelWithOverrides, ItemCameraTransforms.TransformType.GROUND, false));
                GlStateManager.popMatrix();
                GlStateManager.translate(0.0f, 0.0f, 0.09375f);
            }
        }
        if (this.renderOutlines) {
            GlStateManager.disableOutlineMode();
            GlStateManager.disableColorMaterial();
        }
        GlStateManager.popMatrix();
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableBlend();
        this.bindEntityTexture(entityItem);
        if (b2) {
            this.renderManager.renderEngine.getTexture(this.getEntityTexture(entityItem)).restoreLastBlurMipmap();
        }
    }
}
