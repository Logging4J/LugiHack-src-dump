//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.render;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import java.util.*;
import net.minecraft.entity.player.*;
import me.snow.luigihack.api.event.events.*;
import me.snow.luigihack.api.util.*;
import org.lwjgl.opengl.*;
import me.snow.luigihack.*;
import net.minecraft.client.renderer.*;
import me.snow.luigihack.impl.modules.client.*;
import java.awt.*;
import net.minecraft.util.math.*;
import java.util.function.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.model.*;

public class Skeleton extends Module
{
    private final /* synthetic */ Setting<Integer> alpha;
    private final /* synthetic */ Setting<Float> lineWidth;
    private final /* synthetic */ Setting<Boolean> invisibles;
    private static final /* synthetic */ HashMap<EntityPlayer, float[][]> entities;
    
    private void drawSkeleton(final Render3DEvent render3DEvent, final EntityPlayer key) {
        if (!BlockUtil.isPosInFov(new BlockPos(key.posX, key.posY, key.posZ))) {
            return;
        }
        if (key.isInvisible() && !(boolean)this.invisibles.getValue()) {
            return;
        }
        final float[][] array = Skeleton.entities.get(key);
        if (array != null && key.isEntityAlive() && !key.isDead && key != Skeleton.mc.player && !key.isPlayerSleeping()) {
            GL11.glPushMatrix();
            GL11.glEnable(2848);
            GL11.glLineWidth((float)this.lineWidth.getValue());
            if (LuigiHack.friendManager.isFriend(key.getName())) {
                GlStateManager.color(0.0f, 191.0f, 230.0f, (float)(int)this.alpha.getValue());
            }
            else {
                final Color currentColor = Colors.INSTANCE.getCurrentColor();
                GlStateManager.color(currentColor.getRed() / 255.0f, currentColor.getGreen() / 255.0f, currentColor.getBlue() / 255.0f, (float)(int)this.alpha.getValue());
            }
            final Vec3d vec3 = this.getVec3(render3DEvent, key);
            GL11.glTranslated(vec3.x - Skeleton.mc.getRenderManager().renderPosX, vec3.y - Skeleton.mc.getRenderManager().renderPosY, vec3.z - Skeleton.mc.getRenderManager().renderPosZ);
            final float n = key.prevRenderYawOffset + (key.renderYawOffset - key.prevRenderYawOffset) * render3DEvent.getPartialTicks();
            GL11.glRotatef(-n, 0.0f, 1.0f, 0.0f);
            GL11.glTranslated(0.0, 0.0, key.isSneaking() ? -0.235 : 0.0);
            final float n2 = key.isSneaking() ? 0.6f : 0.75f;
            GL11.glPushMatrix();
            GL11.glTranslated(-0.125, (double)n2, 0.0);
            if (array[3][0] != 0.0f) {
                GL11.glRotatef(array[3][0] * 57.295776f, 1.0f, 0.0f, 0.0f);
            }
            if (array[3][1] != 0.0f) {
                GL11.glRotatef(array[3][1] * 57.295776f, 0.0f, 1.0f, 0.0f);
            }
            if (array[3][2] != 0.0f) {
                GL11.glRotatef(array[3][2] * 57.295776f, 0.0f, 0.0f, 1.0f);
            }
            GL11.glBegin(3);
            GL11.glVertex3d(0.0, 0.0, 0.0);
            GL11.glVertex3d(0.0, (double)(-n2), 0.0);
            GL11.glEnd();
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glTranslated(0.125, (double)n2, 0.0);
            if (array[4][0] != 0.0f) {
                GL11.glRotatef(array[4][0] * 57.295776f, 1.0f, 0.0f, 0.0f);
            }
            if (array[4][1] != 0.0f) {
                GL11.glRotatef(array[4][1] * 57.295776f, 0.0f, 1.0f, 0.0f);
            }
            if (array[4][2] != 0.0f) {
                GL11.glRotatef(array[4][2] * 57.295776f, 0.0f, 0.0f, 1.0f);
            }
            GL11.glBegin(3);
            GL11.glVertex3d(0.0, 0.0, 0.0);
            GL11.glVertex3d(0.0, (double)(-n2), 0.0);
            GL11.glEnd();
            GL11.glPopMatrix();
            GL11.glTranslated(0.0, 0.0, key.isSneaking() ? 0.25 : 0.0);
            GL11.glPushMatrix();
            GL11.glTranslated(0.0, key.isSneaking() ? -0.05 : 0.0, key.isSneaking() ? -0.01725 : 0.0);
            GL11.glPushMatrix();
            GL11.glTranslated(-0.375, n2 + 0.55, 0.0);
            if (array[1][0] != 0.0f) {
                GL11.glRotatef(array[1][0] * 57.295776f, 1.0f, 0.0f, 0.0f);
            }
            if (array[1][1] != 0.0f) {
                GL11.glRotatef(array[1][1] * 57.295776f, 0.0f, 1.0f, 0.0f);
            }
            if (array[1][2] != 0.0f) {
                GL11.glRotatef(-array[1][2] * 57.295776f, 0.0f, 0.0f, 1.0f);
            }
            GL11.glBegin(3);
            GL11.glVertex3d(0.0, 0.0, 0.0);
            GL11.glVertex3d(0.0, -0.5, 0.0);
            GL11.glEnd();
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glTranslated(0.375, n2 + 0.55, 0.0);
            if (array[2][0] != 0.0f) {
                GL11.glRotatef(array[2][0] * 57.295776f, 1.0f, 0.0f, 0.0f);
            }
            if (array[2][1] != 0.0f) {
                GL11.glRotatef(array[2][1] * 57.295776f, 0.0f, 1.0f, 0.0f);
            }
            if (array[2][2] != 0.0f) {
                GL11.glRotatef(-array[2][2] * 57.295776f, 0.0f, 0.0f, 1.0f);
            }
            GL11.glBegin(3);
            GL11.glVertex3d(0.0, 0.0, 0.0);
            GL11.glVertex3d(0.0, -0.5, 0.0);
            GL11.glEnd();
            GL11.glPopMatrix();
            GL11.glRotatef(n - key.rotationYawHead, 0.0f, 1.0f, 0.0f);
            GL11.glPushMatrix();
            GL11.glTranslated(0.0, n2 + 0.55, 0.0);
            if (array[0][0] != 0.0f) {
                GL11.glRotatef(array[0][0] * 57.295776f, 1.0f, 0.0f, 0.0f);
            }
            GL11.glBegin(3);
            GL11.glVertex3d(0.0, 0.0, 0.0);
            GL11.glVertex3d(0.0, 0.3, 0.0);
            GL11.glEnd();
            GL11.glPopMatrix();
            GL11.glPopMatrix();
            GL11.glRotatef(key.isSneaking() ? 25.0f : 0.0f, 1.0f, 0.0f, 0.0f);
            GL11.glTranslated(0.0, key.isSneaking() ? -0.16175 : 0.0, key.isSneaking() ? -0.48025 : 0.0);
            GL11.glPushMatrix();
            GL11.glTranslated(0.0, (double)n2, 0.0);
            GL11.glBegin(3);
            GL11.glVertex3d(-0.125, 0.0, 0.0);
            GL11.glVertex3d(0.125, 0.0, 0.0);
            GL11.glEnd();
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glTranslated(0.0, (double)n2, 0.0);
            GL11.glBegin(3);
            GL11.glVertex3d(0.0, 0.0, 0.0);
            GL11.glVertex3d(0.0, 0.55, 0.0);
            GL11.glEnd();
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glTranslated(0.0, n2 + 0.55, 0.0);
            GL11.glBegin(3);
            GL11.glVertex3d(-0.375, 0.0, 0.0);
            GL11.glVertex3d(0.375, 0.0, 0.0);
            GL11.glEnd();
            GL11.glPopMatrix();
            GL11.glPopMatrix();
        }
    }
    
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (fullNullCheck()) {
            return;
        }
        this.startEnd(true);
        GL11.glEnable(2903);
        GL11.glDisable(2848);
        Skeleton.entities.keySet().removeIf(this::doesntContain);
        Skeleton.mc.world.playerEntities.forEach(entityPlayer -> this.drawSkeleton(render3DEvent, entityPlayer));
        Gui.drawRect(0, 0, 0, 0, 0);
        this.startEnd(false);
    }
    
    private void startEnd(final boolean b) {
        if (b) {
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GL11.glEnable(2848);
            GlStateManager.disableDepth();
            GlStateManager.disableTexture2D();
            GL11.glHint(3154, 4354);
        }
        else {
            GlStateManager.disableBlend();
            GlStateManager.enableTexture2D();
            GL11.glDisable(2848);
            GlStateManager.enableDepth();
            GlStateManager.popMatrix();
        }
        GlStateManager.depthMask(!b);
    }
    
    public Skeleton() {
        super("Skeleton", "Draws a skeleton inside the player.", Module.Category.RENDER, false, false, false);
        this.lineWidth = (Setting<Float>)this.register(new Setting("LineWidth", (Object)1.0f, (Object)0.1f, (Object)5.0f));
        this.invisibles = (Setting<Boolean>)this.register(new Setting("Invisibles", (Object)false));
        this.alpha = (Setting<Integer>)this.register(new Setting("Alpha", (Object)255, (Object)0, (Object)255));
    }
    
    private Vec3d getVec3(final Render3DEvent render3DEvent, final EntityPlayer entityPlayer) {
        final float partialTicks = render3DEvent.getPartialTicks();
        return new Vec3d(entityPlayer.lastTickPosX + (entityPlayer.posX - entityPlayer.lastTickPosX) * partialTicks, entityPlayer.lastTickPosY + (entityPlayer.posY - entityPlayer.lastTickPosY) * partialTicks, entityPlayer.lastTickPosZ + (entityPlayer.posZ - entityPlayer.lastTickPosZ) * partialTicks);
    }
    
    static {
        entities = new HashMap<EntityPlayer, float[][]>();
    }
    
    private boolean doesntContain(final EntityPlayer entityPlayer) {
        return !Skeleton.mc.world.playerEntities.contains(entityPlayer);
    }
    
    public static void addEntity(final EntityPlayer key, final ModelPlayer modelPlayer) {
        Skeleton.entities.put(key, new float[][] { { modelPlayer.bipedHead.rotateAngleX, modelPlayer.bipedHead.rotateAngleY, modelPlayer.bipedHead.rotateAngleZ }, { modelPlayer.bipedRightArm.rotateAngleX, modelPlayer.bipedRightArm.rotateAngleY, modelPlayer.bipedRightArm.rotateAngleZ }, { modelPlayer.bipedLeftArm.rotateAngleX, modelPlayer.bipedLeftArm.rotateAngleY, modelPlayer.bipedLeftArm.rotateAngleZ }, { modelPlayer.bipedRightLeg.rotateAngleX, modelPlayer.bipedRightLeg.rotateAngleY, modelPlayer.bipedRightLeg.rotateAngleZ }, { modelPlayer.bipedLeftLeg.rotateAngleX, modelPlayer.bipedLeftLeg.rotateAngleY, modelPlayer.bipedLeftLeg.rotateAngleZ } });
    }
}
