//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.client;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.entity.*;
import org.lwjgl.opengl.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.block.*;
import net.minecraft.util.*;
import net.minecraft.item.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.renderer.entity.*;
import me.snow.luigihack.api.event.events.*;
import me.snow.luigihack.*;
import java.util.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.entity.*;

public class HudComponents extends Module
{
    public /* synthetic */ Setting<Integer> playerViewerX;
    public /* synthetic */ Setting<Boolean> renderXCarry;
    public /* synthetic */ Setting<Integer> holeX;
    public /* synthetic */ Setting<Integer> holeY;
    public /* synthetic */ Setting<Integer> invX;
    public /* synthetic */ Setting<Boolean> invtexture;
    public /* synthetic */ Setting<Compass> compass;
    public /* synthetic */ Setting<Integer> invH;
    public /* synthetic */ Setting<Integer> compassX;
    public /* synthetic */ Setting<Integer> invY;
    public /* synthetic */ Setting<Integer> compassY;
    public /* synthetic */ Setting<Integer> fineinvY;
    public /* synthetic */ Setting<Boolean> inventory;
    public /* synthetic */ Setting<Float> playerScale;
    public /* synthetic */ Setting<Boolean> playerViewer;
    private static final /* synthetic */ ResourceLocation box;
    public /* synthetic */ Setting<Integer> fineinvX;
    public /* synthetic */ Setting<Integer> playerViewerY;
    public /* synthetic */ Setting<Boolean> holeHud;
    public /* synthetic */ Setting<Integer> scale;
    
    private BlockPos traceToBlock(final float n, final float n2) {
        final Vec3d interpolateEntity = EntityUtil.interpolateEntity((Entity)HudComponents.mc.player, n);
        final Vec3d direction = MathUtil.direction(n2);
        return new BlockPos(interpolateEntity.x + direction.x, interpolateEntity.y, interpolateEntity.z + direction.z);
    }
    
    private static void preitemrender() {
        GL11.glPushMatrix();
        GL11.glDepthMask(true);
        GlStateManager.clear(256);
        GlStateManager.disableDepth();
        GlStateManager.enableDepth();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.scale(1.0f, 1.0f, 0.01f);
    }
    
    public void drawOverlay(final float n) {
        float n2 = 0.0f;
        switch (MathHelper.floor(HudComponents.mc.player.rotationYaw * 4.0f / 360.0f + 0.5) & 0x3) {
            case 1: {
                n2 = 90.0f;
                break;
            }
            case 2: {
                n2 = -180.0f;
                break;
            }
            case 3: {
                n2 = -90.0f;
                break;
            }
        }
        final BlockPos traceToBlock = this.traceToBlock(n, n2);
        final Block block = this.getBlock(traceToBlock);
        if (block != null && block != Blocks.AIR) {
            if (this.getBlockDamage(traceToBlock) != 0) {
                RenderUtil.drawRect((float)((int)this.holeX.getValue() + 16), (float)(int)this.holeY.getValue(), (float)((int)this.holeX.getValue() + 32), (float)((int)this.holeY.getValue() + 16), 1627324416);
            }
            this.drawBlock(block, (float)((int)this.holeX.getValue() + 16), (float)(int)this.holeY.getValue());
        }
        final BlockPos traceToBlock2 = this.traceToBlock(n, n2 - 180.0f);
        final Block block2 = this.getBlock(traceToBlock2);
        if (block2 != null && block2 != Blocks.AIR) {
            if (this.getBlockDamage(traceToBlock2) != 0) {
                RenderUtil.drawRect((float)((int)this.holeX.getValue() + 16), (float)((int)this.holeY.getValue() + 32), (float)((int)this.holeX.getValue() + 32), (float)((int)this.holeY.getValue() + 48), 1627324416);
            }
            this.drawBlock(block2, (float)((int)this.holeX.getValue() + 16), (float)((int)this.holeY.getValue() + 32));
        }
        final BlockPos traceToBlock3 = this.traceToBlock(n, n2 + 90.0f);
        final Block block3 = this.getBlock(traceToBlock3);
        if (block3 != null && block3 != Blocks.AIR) {
            if (this.getBlockDamage(traceToBlock3) != 0) {
                RenderUtil.drawRect((float)((int)this.holeX.getValue() + 32), (float)((int)this.holeY.getValue() + 16), (float)((int)this.holeX.getValue() + 48), (float)((int)this.holeY.getValue() + 32), 1627324416);
            }
            this.drawBlock(block3, (float)((int)this.holeX.getValue() + 32), (float)((int)this.holeY.getValue() + 16));
        }
        final BlockPos traceToBlock4 = this.traceToBlock(n, n2 - 90.0f);
        final Block block4 = this.getBlock(traceToBlock4);
        if (block4 != null && block4 != Blocks.AIR) {
            if (this.getBlockDamage(traceToBlock4) != 0) {
                RenderUtil.drawRect((float)(int)this.holeX.getValue(), (float)((int)this.holeY.getValue() + 16), (float)((int)this.holeX.getValue() + 16), (float)((int)this.holeY.getValue() + 32), 1627324416);
            }
            this.drawBlock(block4, (float)(int)this.holeX.getValue(), (float)((int)this.holeY.getValue() + 16));
        }
    }
    
    public void renderInventory() {
        if (this.invtexture.getValue()) {
            this.boxrender((int)this.invX.getValue() + (int)this.fineinvX.getValue(), (int)this.invY.getValue() + (int)this.fineinvY.getValue());
        }
        this.itemrender((NonNullList<ItemStack>)HudComponents.mc.player.inventory.mainInventory, (int)this.invX.getValue() + (int)this.fineinvX.getValue(), (int)this.invY.getValue() + (int)this.fineinvY.getValue());
    }
    
    private double getX(final double a) {
        return Math.sin(a) * ((int)this.scale.getValue() * 10);
    }
    
    private void itemrender(final NonNullList<ItemStack> list, final int n, final int n2) {
        for (int i = 0; i < list.size() - 9; ++i) {
            final int n3 = n + i % 9 * 18 + 8;
            final int n4 = n2 + i / 9 * 18 + 18;
            final ItemStack itemStack = (ItemStack)list.get(i + 9);
            preitemrender();
            HudComponents.mc.getRenderItem().zLevel = 501.0f;
            RenderUtil.itemRender.renderItemAndEffectIntoGUI(itemStack, n3, n4);
            RenderUtil.itemRender.renderItemOverlayIntoGUI(HudComponents.mc.fontRenderer, itemStack, n3, n4, (String)null);
            HudComponents.mc.getRenderItem().zLevel = 0.0f;
            postitemrender();
        }
        if (this.renderXCarry.getValue()) {
            for (int j = 1; j < 5; ++j) {
                final int n5 = n + (j + 4) % 9 * 18 + 8;
                final ItemStack getStack = HudComponents.mc.player.inventoryContainer.inventorySlots.get(j).getStack();
                if (getStack != null && !getStack.isEmpty) {
                    preitemrender();
                    HudComponents.mc.getRenderItem().zLevel = 501.0f;
                    RenderUtil.itemRender.renderItemAndEffectIntoGUI(getStack, n5, n2 + 1);
                    RenderUtil.itemRender.renderItemOverlayIntoGUI(HudComponents.mc.fontRenderer, getStack, n5, n2 + 1, (String)null);
                    HudComponents.mc.getRenderItem().zLevel = 0.0f;
                    postitemrender();
                }
            }
        }
    }
    
    public void drawPlayer(final EntityPlayer entityPlayer, final int n, final int n2) {
        GlStateManager.pushMatrix();
        GlStateManager.color(1.0f, 1.0f, 1.0f);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.enableAlpha();
        GlStateManager.shadeModel(7424);
        GlStateManager.enableAlpha();
        GlStateManager.enableDepth();
        GlStateManager.rotate(0.0f, 0.0f, 5.0f, 0.0f);
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)((int)this.playerViewerX.getValue() + 25), (float)((int)this.playerViewerY.getValue() + 25), 50.0f);
        GlStateManager.scale(-50.0f * (float)this.playerScale.getValue(), 50.0f * (float)this.playerScale.getValue(), 50.0f * (float)this.playerScale.getValue());
        GlStateManager.rotate(180.0f, 0.0f, 0.0f, 1.0f);
        GlStateManager.rotate(135.0f, 0.0f, 1.0f, 0.0f);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate(-135.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(-(float)Math.atan((int)this.playerViewerY.getValue() / 40.0f) * 20.0f, 1.0f, 0.0f, 0.0f);
        GlStateManager.translate(0.0f, 0.0f, 0.0f);
        final RenderManager getRenderManager = HudComponents.mc.getRenderManager();
        getRenderManager.setPlayerViewY(180.0f);
        getRenderManager.setRenderShadow(false);
        try {
            getRenderManager.renderEntity((Entity)entityPlayer, 0.0, 0.0, 0.0, 0.0f, 1.0f, false);
        }
        catch (Exception ex) {}
        getRenderManager.setRenderShadow(true);
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
        GlStateManager.depthFunc(515);
        GlStateManager.resetColor();
        GlStateManager.disableDepth();
        GlStateManager.popMatrix();
    }
    
    @Override
    public void onRender2D(final Render2DEvent render2DEvent) {
        if (fullNullCheck()) {
            return;
        }
        if (this.playerViewer.getValue()) {
            this.drawPlayer();
        }
        if (this.compass.getValue() != Compass.NONE) {
            this.drawCompass();
        }
        if (this.holeHud.getValue()) {
            this.drawOverlay(render2DEvent.partialTicks);
        }
        if (this.inventory.getValue()) {
            this.renderInventory();
        }
    }
    
    private void drawBlock(final Block block, final float n, final float n2) {
        final ItemStack itemStack = new ItemStack(block);
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        RenderHelper.enableGUIStandardItemLighting();
        GlStateManager.translate(n, n2, 0.0f);
        HudComponents.mc.getRenderItem().zLevel = 501.0f;
        HudComponents.mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, 0, 0);
        HudComponents.mc.getRenderItem().zLevel = 0.0f;
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableBlend();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.popMatrix();
    }
    
    public static EntityPlayer getClosestEnemy() {
        Object o = null;
        for (final EntityPlayer entityPlayer : HudComponents.mc.world.playerEntities) {
            if (entityPlayer == HudComponents.mc.player) {
                continue;
            }
            if (LuigiHack.friendManager.isFriend(entityPlayer)) {
                continue;
            }
            if (o == null) {
                o = entityPlayer;
            }
            else {
                if (HudComponents.mc.player.getDistanceSq((Entity)entityPlayer) >= HudComponents.mc.player.getDistanceSq((Entity)o)) {
                    continue;
                }
                o = entityPlayer;
            }
        }
        return (EntityPlayer)o;
    }
    
    private double getY(final double a) {
        return Math.cos(a) * Math.sin(Math.toRadians(MathHelper.clamp(HudComponents.mc.player.rotationPitch + 30.0f, -90.0f, 90.0f))) * ((int)this.scale.getValue() * 10);
    }
    
    public void drawCompass() {
        final ScaledResolution scaledResolution = new ScaledResolution(HudComponents.mc);
        if (this.compass.getValue() == Compass.LINE) {
            final float wrap = MathUtil.wrap(HudComponents.mc.player.rotationYaw);
            RenderUtil.drawRect((float)(int)this.compassX.getValue(), (float)(int)this.compassY.getValue(), (float)((int)this.compassX.getValue() + 100), (float)((int)this.compassY.getValue() + this.renderer.getFontHeight()), 1963986960);
            RenderUtil.glScissor((float)(int)this.compassX.getValue(), (float)(int)this.compassY.getValue(), (float)((int)this.compassX.getValue() + 100), (float)((int)this.compassY.getValue() + this.renderer.getFontHeight()), scaledResolution);
            GL11.glEnable(3089);
            final float wrap2 = MathUtil.wrap((float)(Math.atan2(0.0 - HudComponents.mc.player.posZ, 0.0 - HudComponents.mc.player.posX) * 180.0 / 3.141592653589793) - 90.0f);
            RenderUtil.drawLine((int)this.compassX.getValue() - wrap + 50.0f + wrap2, (float)((int)this.compassY.getValue() + 2), (int)this.compassX.getValue() - wrap + 50.0f + wrap2, (float)((int)this.compassY.getValue() + this.renderer.getFontHeight() - 2), 2.0f, -61424);
            RenderUtil.drawLine((int)this.compassX.getValue() - wrap + 50.0f + 45.0f, (float)((int)this.compassY.getValue() + 2), (int)this.compassX.getValue() - wrap + 50.0f + 45.0f, (float)((int)this.compassY.getValue() + this.renderer.getFontHeight() - 2), 2.0f, -1);
            RenderUtil.drawLine((int)this.compassX.getValue() - wrap + 50.0f - 45.0f, (float)((int)this.compassY.getValue() + 2), (int)this.compassX.getValue() - wrap + 50.0f - 45.0f, (float)((int)this.compassY.getValue() + this.renderer.getFontHeight() - 2), 2.0f, -1);
            RenderUtil.drawLine((int)this.compassX.getValue() - wrap + 50.0f + 135.0f, (float)((int)this.compassY.getValue() + 2), (int)this.compassX.getValue() - wrap + 50.0f + 135.0f, (float)((int)this.compassY.getValue() + this.renderer.getFontHeight() - 2), 2.0f, -1);
            RenderUtil.drawLine((int)this.compassX.getValue() - wrap + 50.0f - 135.0f, (float)((int)this.compassY.getValue() + 2), (int)this.compassX.getValue() - wrap + 50.0f - 135.0f, (float)((int)this.compassY.getValue() + this.renderer.getFontHeight() - 2), 2.0f, -1);
            this.renderer.drawStringWithShadow("n", (int)this.compassX.getValue() - wrap + 50.0f + 180.0f - this.renderer.getStringWidth("n") / 2.0f, (float)(int)this.compassY.getValue(), -1);
            this.renderer.drawStringWithShadow("n", (int)this.compassX.getValue() - wrap + 50.0f - 180.0f - this.renderer.getStringWidth("n") / 2.0f, (float)(int)this.compassY.getValue(), -1);
            this.renderer.drawStringWithShadow("e", (int)this.compassX.getValue() - wrap + 50.0f - 90.0f - this.renderer.getStringWidth("e") / 2.0f, (float)(int)this.compassY.getValue(), -1);
            this.renderer.drawStringWithShadow("s", (int)this.compassX.getValue() - wrap + 50.0f - this.renderer.getStringWidth("s") / 2.0f, (float)(int)this.compassY.getValue(), -1);
            this.renderer.drawStringWithShadow("w", (int)this.compassX.getValue() - wrap + 50.0f + 90.0f - this.renderer.getStringWidth("w") / 2.0f, (float)(int)this.compassY.getValue(), -1);
            RenderUtil.drawLine((float)((int)this.compassX.getValue() + 50), (float)((int)this.compassY.getValue() + 1), (float)((int)this.compassX.getValue() + 50), (float)((int)this.compassY.getValue() + this.renderer.getFontHeight() - 1), 2.0f, -7303024);
            GL11.glDisable(3089);
        }
        else {
            final double n = (int)this.compassX.getValue();
            final double n2 = (int)this.compassY.getValue();
            for (final Direction direction : Direction.values()) {
                final double posOnCompass = getPosOnCompass(direction);
                this.renderer.drawStringWithShadow(direction.name(), (float)(n + this.getX(posOnCompass)), (float)(n2 + this.getY(posOnCompass)), (direction == Direction.N) ? -65536 : -1);
            }
        }
    }
    
    private void boxrender(final int n, final int n2) {
        preboxrender();
        HudComponents.mc.renderEngine.bindTexture(HudComponents.box);
        RenderUtil.drawTexturedRect(n, n2, 0, 0, 176, 16, 500);
        RenderUtil.drawTexturedRect(n, n2 + 16, 0, 16, 176, 54 + (int)this.invH.getValue(), 500);
        RenderUtil.drawTexturedRect(n, n2 + 16 + 54, 0, 160, 176, 8, 500);
        postboxrender();
    }
    
    private static void postitemrender() {
        GlStateManager.scale(1.0f, 1.0f, 1.0f);
        RenderHelper.disableStandardItemLighting();
        GlStateManager.enableAlpha();
        GlStateManager.disableBlend();
        GlStateManager.disableLighting();
        GlStateManager.scale(0.5, 0.5, 0.5);
        GlStateManager.disableDepth();
        GlStateManager.enableDepth();
        GlStateManager.scale(2.0f, 2.0f, 2.0f);
        GL11.glPopMatrix();
    }
    
    private BlockPos traceToBlock(final float n, final float n2, final Entity entity) {
        final Vec3d interpolateEntity = EntityUtil.interpolateEntity(entity, n);
        final Vec3d direction = MathUtil.direction(n2);
        return new BlockPos(interpolateEntity.x + direction.x, interpolateEntity.y, interpolateEntity.z + direction.z);
    }
    
    static {
        box = new ResourceLocation("textures/gui/container/shulker_box.png");
    }
    
    private static void preboxrender() {
        GL11.glPushMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.disableAlpha();
        GlStateManager.clear(256);
        GlStateManager.enableBlend();
        GlStateManager.color(255.0f, 255.0f, 255.0f, 255.0f);
    }
    
    private static double getPosOnCompass(final Direction direction) {
        return Math.toRadians(MathHelper.wrapDegrees(HudComponents.mc.player.rotationYaw)) + direction.ordinal() * 1.5707963267948966;
    }
    
    private int getBlockDamage(final BlockPos blockPos) {
        for (final DestroyBlockProgress destroyBlockProgress : HudComponents.mc.renderGlobal.damagedBlocks.values()) {
            if (destroyBlockProgress.getPosition().getX() == blockPos.getX() && destroyBlockProgress.getPosition().getY() == blockPos.getY() && destroyBlockProgress.getPosition().getZ() == blockPos.getZ()) {
                return destroyBlockProgress.getPartialBlockDamage();
            }
        }
        return 0;
    }
    
    private static void postboxrender() {
        GlStateManager.disableBlend();
        GlStateManager.disableDepth();
        GlStateManager.disableLighting();
        GlStateManager.enableDepth();
        GlStateManager.enableAlpha();
        GlStateManager.popMatrix();
        GL11.glPopMatrix();
    }
    
    public static void drawCompleteImage(final int n, final int n2, final int n3, final int n4) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)n, (float)n2, 0.0f);
        GL11.glBegin(7);
        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex3f(0.0f, 0.0f, 0.0f);
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex3f(0.0f, (float)n4, 0.0f);
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex3f((float)n3, (float)n4, 0.0f);
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex3f((float)n3, 0.0f, 0.0f);
        GL11.glEnd();
        GL11.glPopMatrix();
    }
    
    public void drawOverlay(final float n, final Entity entity, final int n2, final int n3) {
        float n4 = 0.0f;
        switch (MathHelper.floor(entity.rotationYaw * 4.0f / 360.0f + 0.5) & 0x3) {
            case 1: {
                n4 = 90.0f;
                break;
            }
            case 2: {
                n4 = -180.0f;
                break;
            }
            case 3: {
                n4 = -90.0f;
                break;
            }
        }
        final BlockPos traceToBlock = this.traceToBlock(n, n4, entity);
        final Block block = this.getBlock(traceToBlock);
        if (block != null && block != Blocks.AIR) {
            if (this.getBlockDamage(traceToBlock) != 0) {
                RenderUtil.drawRect((float)(n2 + 16), (float)n3, (float)(n2 + 32), (float)(n3 + 16), 1627324416);
            }
            this.drawBlock(block, (float)(n2 + 16), (float)n3);
        }
        final BlockPos traceToBlock2 = this.traceToBlock(n, n4 - 180.0f, entity);
        final Block block2 = this.getBlock(traceToBlock2);
        if (block2 != null && block2 != Blocks.AIR) {
            if (this.getBlockDamage(traceToBlock2) != 0) {
                RenderUtil.drawRect((float)(n2 + 16), (float)(n3 + 32), (float)(n2 + 32), (float)(n3 + 48), 1627324416);
            }
            this.drawBlock(block2, (float)(n2 + 16), (float)(n3 + 32));
        }
        final BlockPos traceToBlock3 = this.traceToBlock(n, n4 + 90.0f, entity);
        final Block block3 = this.getBlock(traceToBlock3);
        if (block3 != null && block3 != Blocks.AIR) {
            if (this.getBlockDamage(traceToBlock3) != 0) {
                RenderUtil.drawRect((float)(n2 + 32), (float)(n3 + 16), (float)(n2 + 48), (float)(n3 + 32), 1627324416);
            }
            this.drawBlock(block3, (float)(n2 + 32), (float)(n3 + 16));
        }
        final BlockPos traceToBlock4 = this.traceToBlock(n, n4 - 90.0f, entity);
        final Block block4 = this.getBlock(traceToBlock4);
        if (block4 != null && block4 != Blocks.AIR) {
            if (this.getBlockDamage(traceToBlock4) != 0) {
                RenderUtil.drawRect((float)n2, (float)(n3 + 16), (float)(n2 + 16), (float)(n3 + 32), 1627324416);
            }
            this.drawBlock(block4, (float)n2, (float)(n3 + 16));
        }
    }
    
    private Block getBlock(final BlockPos blockPos) {
        final Block getBlock = HudComponents.mc.world.getBlockState(blockPos).getBlock();
        if (getBlock == Blocks.BEDROCK || getBlock == Blocks.OBSIDIAN) {
            return getBlock;
        }
        return Blocks.AIR;
    }
    
    public void drawPlayer() {
        final EntityPlayerSP player = HudComponents.mc.player;
        GlStateManager.pushMatrix();
        GlStateManager.color(1.0f, 1.0f, 1.0f);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.enableAlpha();
        GlStateManager.shadeModel(7424);
        GlStateManager.enableAlpha();
        GlStateManager.enableDepth();
        GlStateManager.rotate(0.0f, 0.0f, 5.0f, 0.0f);
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)((int)this.playerViewerX.getValue() + 25), (float)((int)this.playerViewerY.getValue() + 25), 50.0f);
        GlStateManager.scale(-50.0f * (float)this.playerScale.getValue(), 50.0f * (float)this.playerScale.getValue(), 50.0f * (float)this.playerScale.getValue());
        GlStateManager.rotate(180.0f, 0.0f, 0.0f, 1.0f);
        GlStateManager.rotate(135.0f, 0.0f, 1.0f, 0.0f);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate(-135.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(-(float)Math.atan((int)this.playerViewerY.getValue() / 40.0f) * 20.0f, 1.0f, 0.0f, 0.0f);
        GlStateManager.translate(0.0f, 0.0f, 0.0f);
        final RenderManager getRenderManager = HudComponents.mc.getRenderManager();
        getRenderManager.setPlayerViewY(180.0f);
        getRenderManager.setRenderShadow(false);
        try {
            getRenderManager.renderEntity((Entity)player, 0.0, 0.0, 0.0, 0.0f, 1.0f, false);
        }
        catch (Exception ex) {}
        getRenderManager.setRenderShadow(true);
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
        GlStateManager.depthFunc(515);
        GlStateManager.resetColor();
        GlStateManager.disableDepth();
        GlStateManager.popMatrix();
    }
    
    public HudComponents() {
        super("HudComponents", "HudComponents", Category.CLIENT, false, false, true);
        this.inventory = (Setting<Boolean>)this.register(new Setting("Inventory", (Object)false));
        this.invtexture = (Setting<Boolean>)this.register(new Setting("InventoryBackGround", (Object)false, p0 -> (boolean)this.inventory.getValue()));
        this.invX = (Setting<Integer>)this.register(new Setting("InvX", (Object)564, (Object)0, (Object)1000, p0 -> (boolean)this.inventory.getValue()));
        this.invY = (Setting<Integer>)this.register(new Setting("InvY", (Object)467, (Object)0, (Object)1000, p0 -> (boolean)this.inventory.getValue()));
        this.fineinvX = (Setting<Integer>)this.register(new Setting("InvFineX", (Object)0, p0 -> (boolean)this.inventory.getValue()));
        this.fineinvY = (Setting<Integer>)this.register(new Setting("InvFineY", (Object)0, p0 -> (boolean)this.inventory.getValue()));
        this.renderXCarry = (Setting<Boolean>)this.register(new Setting("RenderXCarry", (Object)false, p0 -> (boolean)this.inventory.getValue()));
        this.invH = (Setting<Integer>)this.register(new Setting("InvH", (Object)3, p0 -> (boolean)this.inventory.getValue()));
        this.holeHud = (Setting<Boolean>)this.register(new Setting("HoleHUD", (Object)false));
        this.holeX = (Setting<Integer>)this.register(new Setting("HoleX", (Object)279, (Object)0, (Object)1000, p0 -> (boolean)this.holeHud.getValue()));
        this.holeY = (Setting<Integer>)this.register(new Setting("HoleY", (Object)485, (Object)0, (Object)1000, p0 -> (boolean)this.holeHud.getValue()));
        this.compass = (Setting<Compass>)this.register(new Setting("Compass", (Object)Compass.NONE));
        this.compassX = (Setting<Integer>)this.register(new Setting("CompX", (Object)472, (Object)0, (Object)1000, p0 -> this.compass.getValue() != Compass.NONE));
        this.compassY = (Setting<Integer>)this.register(new Setting("CompY", (Object)424, (Object)0, (Object)1000, p0 -> this.compass.getValue() != Compass.NONE));
        this.scale = (Setting<Integer>)this.register(new Setting("Scale", (Object)3, (Object)0, (Object)10, p0 -> this.compass.getValue() != Compass.NONE));
        this.playerViewer = (Setting<Boolean>)this.register(new Setting("PlayerViewer", (Object)false));
        this.playerViewerX = (Setting<Integer>)this.register(new Setting("PlayerX", (Object)752, (Object)0, (Object)1000, p0 -> (boolean)this.playerViewer.getValue()));
        this.playerViewerY = (Setting<Integer>)this.register(new Setting("PlayerY", (Object)497, (Object)0, (Object)1000, p0 -> (boolean)this.playerViewer.getValue()));
        this.playerScale = (Setting<Float>)this.register(new Setting("PlayerScale", (Object)1.0f, (Object)0.1f, (Object)2.0f, p0 -> (boolean)this.playerViewer.getValue()));
    }
    
    private enum Direction
    {
        S, 
        W, 
        E, 
        N;
    }
    
    public enum Compass
    {
        LINE, 
        CIRCLE, 
        NONE;
    }
}
