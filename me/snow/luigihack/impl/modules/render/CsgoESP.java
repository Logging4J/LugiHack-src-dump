//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.render;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.monster.*;
import me.snow.luigihack.api.event.events.*;
import java.util.stream.*;
import net.minecraft.client.renderer.*;
import me.snow.luigihack.api.util.*;
import org.lwjgl.opengl.*;
import net.minecraft.entity.player.*;
import me.snow.luigihack.*;
import net.minecraft.entity.item.*;
import java.util.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.*;

public class CsgoESP extends Module
{
    private final /* synthetic */ Setting<Integer> Alpha;
    private final /* synthetic */ Setting<Integer> PRed;
    private final /* synthetic */ Setting<Boolean> items;
    private final /* synthetic */ Setting<Integer> PGreen;
    private final /* synthetic */ Setting<Boolean> animals;
    private final /* synthetic */ Setting<Integer> Red;
    private final /* synthetic */ Setting<Integer> Blue;
    private final /* synthetic */ Setting<Integer> Green;
    private final /* synthetic */ Setting<Boolean> orbs;
    private final /* synthetic */ Setting<Boolean> players;
    private final /* synthetic */ Setting<Boolean> crystals;
    private final /* synthetic */ Setting<Integer> PAlpha;
    private final /* synthetic */ Setting<Integer> LineWidth;
    private final /* synthetic */ Setting<Boolean> xpBottles;
    private final /* synthetic */ Setting<Boolean> monsters;
    private final /* synthetic */ Setting<Integer> PBlue;
    
    public CsgoESP() {
        super("CsgoESP", "Renders a nice ESP.", Module.Category.RENDER, false, false, false);
        this.players = (Setting<Boolean>)this.register(new Setting("Players", (Object)true));
        this.animals = (Setting<Boolean>)this.register(new Setting("Animals", (Object)true));
        this.monsters = (Setting<Boolean>)this.register(new Setting("Monsters", (Object)false));
        this.items = (Setting<Boolean>)this.register(new Setting("Items", (Object)false));
        this.xpBottles = (Setting<Boolean>)this.register(new Setting("XpBottles", (Object)false));
        this.crystals = (Setting<Boolean>)this.register(new Setting("Crystals", (Object)true));
        this.orbs = (Setting<Boolean>)this.register(new Setting("XpOrbs", (Object)false));
        this.LineWidth = (Setting<Integer>)this.register(new Setting("LineWidth", (Object)1, (Object)1, (Object)8));
        this.PRed = (Setting<Integer>)this.register(new Setting("Player Red", (Object)255, (Object)0, (Object)255));
        this.PGreen = (Setting<Integer>)this.register(new Setting("Player Green", (Object)255, (Object)0, (Object)255));
        this.PBlue = (Setting<Integer>)this.register(new Setting("Player Blue", (Object)255, (Object)0, (Object)255));
        this.PAlpha = (Setting<Integer>)this.register(new Setting("Player Alpha", (Object)255, (Object)0, (Object)255));
        this.Red = (Setting<Integer>)this.register(new Setting("Other Red", (Object)255, (Object)0, (Object)255));
        this.Green = (Setting<Integer>)this.register(new Setting("Other Green", (Object)255, (Object)0, (Object)255));
        this.Blue = (Setting<Integer>)this.register(new Setting("Other Blue", (Object)255, (Object)0, (Object)255));
        this.Alpha = (Setting<Integer>)this.register(new Setting("Other Alpha", (Object)255, (Object)0, (Object)255));
    }
    
    public static boolean isanimals(final Entity entity) {
        return (!(entity instanceof EntityWolf) || !((EntityWolf)entity).isAngry()) && (entity instanceof EntityAgeable || entity instanceof EntityAmbientCreature || entity instanceof EntitySquid || (entity instanceof EntityIronGolem && ((EntityIronGolem)entity).getRevengeTarget() == null));
    }
    
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (CsgoESP.mc.getRenderManager().options == null) {
            return;
        }
        final boolean b = CsgoESP.mc.getRenderManager().options.thirdPersonView == 2;
        final float playerViewY = CsgoESP.mc.getRenderManager().playerViewY;
        for (final Entity entity : (List)CsgoESP.mc.world.loadedEntityList.stream().filter(entityPlayerSP -> CsgoESP.mc.player != entityPlayerSP).collect(Collectors.toList())) {
            LuigiTessellator.prepareGL();
            GlStateManager.pushMatrix();
            final Vec3d interpolatedPos = EntityUtil.getInterpolatedPos(entity, render3DEvent.getPartialTicks());
            GlStateManager.translate(interpolatedPos.x - CsgoESP.mc.getRenderManager().renderPosX, interpolatedPos.y - CsgoESP.mc.getRenderManager().renderPosY, interpolatedPos.z - CsgoESP.mc.getRenderManager().renderPosZ);
            GlStateManager.glNormal3f(0.0f, 1.0f, 0.0f);
            GlStateManager.rotate(-playerViewY, 0.0f, 1.0f, 0.0f);
            GlStateManager.rotate((float)(b ? -1 : 1), 1.0f, 0.0f, 0.0f);
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 0.5f);
            GL11.glLineWidth((int)this.LineWidth.getValue() * 6.0f / CsgoESP.mc.player.getDistance(entity));
            GL11.glEnable(2848);
            if (entity instanceof EntityPlayer && (boolean)this.players.getValue()) {
                if (LuigiHack.friendManager.isFriend(entity.getName())) {
                    GL11.glColor4f(0.0f, 1.0f, 1.0f, 0.7f);
                }
                else {
                    GL11.glColor4f((int)this.PRed.getValue() / 255.0f, (float)this.PGreen.getValue() / 255.0f, (float)this.PBlue.getValue() / 255.0f, (float)this.PAlpha.getValue() / 255.0f);
                }
                GL11.glBegin(2);
                GL11.glVertex2d((double)(-entity.width), 0.0);
                GL11.glVertex2d((double)(-entity.width), (double)(entity.height / 3.0f));
                GL11.glVertex2d((double)(-entity.width), 0.0);
                GL11.glVertex2d((double)(-entity.width / 3.0f * 2.0f), 0.0);
                GL11.glEnd();
                GL11.glBegin(2);
                GL11.glVertex2d((double)(-entity.width), (double)entity.height);
                GL11.glVertex2d((double)(-entity.width / 3.0f * 2.0f), (double)entity.height);
                GL11.glVertex2d((double)(-entity.width), (double)entity.height);
                GL11.glVertex2d((double)(-entity.width), (double)(entity.height / 3.0f * 2.0f));
                GL11.glEnd();
                GL11.glBegin(2);
                GL11.glVertex2d((double)entity.width, (double)entity.height);
                GL11.glVertex2d((double)(entity.width / 3.0f * 2.0f), (double)entity.height);
                GL11.glVertex2d((double)entity.width, (double)entity.height);
                GL11.glVertex2d((double)entity.width, (double)(entity.height / 3.0f * 2.0f));
                GL11.glEnd();
                GL11.glBegin(2);
                GL11.glVertex2d((double)entity.width, 0.0);
                GL11.glVertex2d((double)(entity.width / 3.0f * 2.0f), 0.0);
                GL11.glVertex2d((double)entity.width, 0.0);
                GL11.glVertex2d((double)entity.width, (double)(entity.height / 3.0f));
                GL11.glEnd();
            }
            GL11.glColor4f((int)this.Red.getValue() / 255.0f, (int)this.Green.getValue() / 255.0f, (int)this.Blue.getValue() / 255.0f, (int)this.Alpha.getValue() / 255.0f);
            if (isanimals(entity) && (boolean)this.animals.getValue()) {
                GL11.glBegin(2);
                GL11.glVertex2d((double)(-entity.width), 0.0);
                GL11.glVertex2d((double)(-entity.width), (double)(entity.height / 3.0f));
                GL11.glVertex2d((double)(-entity.width), 0.0);
                GL11.glVertex2d((double)(-entity.width / 3.0f * 2.0f), 0.0);
                GL11.glEnd();
                GL11.glBegin(2);
                GL11.glVertex2d((double)(-entity.width), (double)entity.height);
                GL11.glVertex2d((double)(-entity.width / 3.0f * 2.0f), (double)entity.height);
                GL11.glVertex2d((double)(-entity.width), (double)entity.height);
                GL11.glVertex2d((double)(-entity.width), (double)(entity.height / 3.0f * 2.0f));
                GL11.glEnd();
                GL11.glBegin(2);
                GL11.glVertex2d((double)entity.width, (double)entity.height);
                GL11.glVertex2d((double)(entity.width / 3.0f * 2.0f), (double)entity.height);
                GL11.glVertex2d((double)entity.width, (double)entity.height);
                GL11.glVertex2d((double)entity.width, (double)(entity.height / 3.0f * 2.0f));
                GL11.glEnd();
                GL11.glBegin(2);
                GL11.glVertex2d((double)entity.width, 0.0);
                GL11.glVertex2d((double)(entity.width / 3.0f * 2.0f), 0.0);
                GL11.glVertex2d((double)entity.width, 0.0);
                GL11.glVertex2d((double)entity.width, (double)(entity.height / 3.0f));
                GL11.glEnd();
            }
            if (isMonster(entity) && (boolean)this.monsters.getValue()) {
                GL11.glBegin(2);
                GL11.glVertex2d((double)(-entity.width), 0.0);
                GL11.glVertex2d((double)(-entity.width), (double)(entity.height / 3.0f));
                GL11.glVertex2d((double)(-entity.width), 0.0);
                GL11.glVertex2d((double)(-entity.width / 3.0f * 2.0f), 0.0);
                GL11.glEnd();
                GL11.glBegin(2);
                GL11.glVertex2d((double)(-entity.width), (double)entity.height);
                GL11.glVertex2d((double)(-entity.width / 3.0f * 2.0f), (double)entity.height);
                GL11.glVertex2d((double)(-entity.width), (double)entity.height);
                GL11.glVertex2d((double)(-entity.width), (double)(entity.height / 3.0f * 2.0f));
                GL11.glEnd();
                GL11.glBegin(2);
                GL11.glVertex2d((double)entity.width, (double)entity.height);
                GL11.glVertex2d((double)(entity.width / 3.0f * 2.0f), (double)entity.height);
                GL11.glVertex2d((double)entity.width, (double)entity.height);
                GL11.glVertex2d((double)entity.width, (double)(entity.height / 3.0f * 2.0f));
                GL11.glEnd();
                GL11.glBegin(2);
                GL11.glVertex2d((double)entity.width, 0.0);
                GL11.glVertex2d((double)(entity.width / 3.0f * 2.0f), 0.0);
                GL11.glVertex2d((double)entity.width, 0.0);
                GL11.glVertex2d((double)entity.width, (double)(entity.height / 3.0f));
                GL11.glEnd();
            }
            if (entity instanceof EntityItem && (boolean)this.items.getValue()) {
                GL11.glBegin(2);
                GL11.glVertex2d((double)(-entity.width), 0.0);
                GL11.glVertex2d((double)(-entity.width), (double)(entity.height / 3.0f));
                GL11.glVertex2d((double)(-entity.width), 0.0);
                GL11.glVertex2d((double)(-entity.width / 3.0f * 2.0f), 0.0);
                GL11.glEnd();
                GL11.glBegin(2);
                GL11.glVertex2d((double)(-entity.width), (double)entity.height);
                GL11.glVertex2d((double)(-entity.width / 3.0f * 2.0f), (double)entity.height);
                GL11.glVertex2d((double)(-entity.width), (double)entity.height);
                GL11.glVertex2d((double)(-entity.width), (double)(entity.height / 3.0f * 2.0f));
                GL11.glEnd();
                GL11.glBegin(2);
                GL11.glVertex2d((double)entity.width, (double)entity.height);
                GL11.glVertex2d((double)(entity.width / 3.0f * 2.0f), (double)entity.height);
                GL11.glVertex2d((double)entity.width, (double)entity.height);
                GL11.glVertex2d((double)entity.width, (double)(entity.height / 3.0f * 2.0f));
                GL11.glEnd();
                GL11.glBegin(2);
                GL11.glVertex2d((double)entity.width, 0.0);
                GL11.glVertex2d((double)(entity.width / 3.0f * 2.0f), 0.0);
                GL11.glVertex2d((double)entity.width, 0.0);
                GL11.glVertex2d((double)entity.width, (double)(entity.height / 3.0f));
                GL11.glEnd();
            }
            if (entity instanceof EntityExpBottle && (boolean)this.xpBottles.getValue()) {
                GL11.glBegin(2);
                GL11.glVertex2d((double)(-entity.width), 0.0);
                GL11.glVertex2d((double)(-entity.width), (double)(entity.height / 3.0f));
                GL11.glVertex2d((double)(-entity.width), 0.0);
                GL11.glVertex2d((double)(-entity.width / 3.0f * 2.0f), 0.0);
                GL11.glEnd();
                GL11.glBegin(2);
                GL11.glVertex2d((double)(-entity.width), (double)entity.height);
                GL11.glVertex2d((double)(-entity.width / 3.0f * 2.0f), (double)entity.height);
                GL11.glVertex2d((double)(-entity.width), (double)entity.height);
                GL11.glVertex2d((double)(-entity.width), (double)(entity.height / 3.0f * 2.0f));
                GL11.glEnd();
                GL11.glBegin(2);
                GL11.glVertex2d((double)entity.width, (double)entity.height);
                GL11.glVertex2d((double)(entity.width / 3.0f * 2.0f), (double)entity.height);
                GL11.glVertex2d((double)entity.width, (double)entity.height);
                GL11.glVertex2d((double)entity.width, (double)(entity.height / 3.0f * 2.0f));
                GL11.glEnd();
                GL11.glBegin(2);
                GL11.glVertex2d((double)entity.width, 0.0);
                GL11.glVertex2d((double)(entity.width / 3.0f * 2.0f), 0.0);
                GL11.glVertex2d((double)entity.width, 0.0);
                GL11.glVertex2d((double)entity.width, (double)(entity.height / 3.0f));
                GL11.glEnd();
            }
            if (entity instanceof EntityEnderCrystal && (boolean)this.crystals.getValue()) {
                GL11.glBegin(2);
                GL11.glVertex2d((double)(-entity.width / 2.0f), 0.0);
                GL11.glVertex2d((double)(-entity.width / 2.0f), (double)(entity.height / 3.0f));
                GL11.glVertex2d((double)(-entity.width / 2.0f), 0.0);
                GL11.glVertex2d((double)(-entity.width / 3.0f * 2.0f / 2.0f), 0.0);
                GL11.glEnd();
                GL11.glBegin(2);
                GL11.glVertex2d((double)(-entity.width / 2.0f), (double)entity.height);
                GL11.glVertex2d((double)(-entity.width / 3.0f * 2.0f / 2.0f), (double)entity.height);
                GL11.glVertex2d((double)(-entity.width / 2.0f), (double)entity.height);
                GL11.glVertex2d((double)(-entity.width / 2.0f), (double)(entity.height / 3.0f * 2.0f));
                GL11.glEnd();
                GL11.glBegin(2);
                GL11.glVertex2d((double)(entity.width / 2.0f), (double)entity.height);
                GL11.glVertex2d((double)(entity.width / 3.0f * 2.0f / 2.0f), (double)entity.height);
                GL11.glVertex2d((double)(entity.width / 2.0f), (double)entity.height);
                GL11.glVertex2d((double)(entity.width / 2.0f), (double)(entity.height / 3.0f * 2.0f));
                GL11.glEnd();
                GL11.glBegin(2);
                GL11.glVertex2d((double)(entity.width / 2.0f), 0.0);
                GL11.glVertex2d((double)(entity.width / 3.0f * 2.0f / 2.0f), 0.0);
                GL11.glVertex2d((double)(entity.width / 2.0f), 0.0);
                GL11.glVertex2d((double)(entity.width / 2.0f), (double)(entity.height / 3.0f));
                GL11.glEnd();
            }
            if (entity instanceof EntityXPOrb && (boolean)this.orbs.getValue()) {
                GL11.glBegin(2);
                GL11.glVertex2d((double)(-entity.width / 2.0f), 0.0);
                GL11.glVertex2d((double)(-entity.width / 2.0f), (double)(entity.height / 3.0f));
                GL11.glVertex2d((double)(-entity.width / 2.0f), 0.0);
                GL11.glVertex2d((double)(-entity.width / 3.0f * 2.0f / 2.0f), 0.0);
                GL11.glEnd();
                GL11.glBegin(2);
                GL11.glVertex2d((double)(-entity.width / 2.0f), (double)entity.height);
                GL11.glVertex2d((double)(-entity.width / 3.0f * 2.0f / 2.0f), (double)entity.height);
                GL11.glVertex2d((double)(-entity.width / 2.0f), (double)entity.height);
                GL11.glVertex2d((double)(-entity.width / 2.0f), (double)(entity.height / 3.0f * 2.0f));
                GL11.glEnd();
                GL11.glBegin(2);
                GL11.glVertex2d((double)(entity.width / 2.0f), (double)entity.height);
                GL11.glVertex2d((double)(entity.width / 3.0f * 2.0f / 2.0f), (double)entity.height);
                GL11.glVertex2d((double)(entity.width / 2.0f), (double)entity.height);
                GL11.glVertex2d((double)(entity.width / 2.0f), (double)(entity.height / 3.0f * 2.0f));
                GL11.glEnd();
                GL11.glBegin(2);
                GL11.glVertex2d((double)(entity.width / 2.0f), 0.0);
                GL11.glVertex2d((double)(entity.width / 3.0f * 2.0f / 2.0f), 0.0);
                GL11.glVertex2d((double)(entity.width / 2.0f), 0.0);
                GL11.glVertex2d((double)(entity.width / 2.0f), (double)(entity.height / 3.0f));
                GL11.glEnd();
            }
            LuigiTessellator.releaseGL();
            GlStateManager.popMatrix();
        }
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }
    
    public static boolean isMonster(final Entity entity) {
        return entity.isCreatureType(EnumCreatureType.MONSTER, false);
    }
}
