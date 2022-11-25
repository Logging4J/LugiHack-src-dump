//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.render;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import java.awt.*;
import me.snow.luigihack.impl.modules.client.*;
import net.minecraft.entity.player.*;
import java.util.*;
import me.snow.luigihack.api.event.events.*;
import me.snow.luigihack.api.util.*;
import org.lwjgl.opengl.*;

public class BreadCrumbs extends Module
{
    public static /* synthetic */ Setting<Integer> red;
    public /* synthetic */ Color color;
    public static /* synthetic */ Setting<Integer> green;
    public static /* synthetic */ Setting<Integer> alpha;
    public static /* synthetic */ Setting<Float> width;
    public static /* synthetic */ Setting<Integer> length;
    public static /* synthetic */ ArrayList<double[]> vecs;
    public static /* synthetic */ Setting<Boolean> syncColor;
    public static /* synthetic */ Setting<Integer> blue;
    
    public static double M(final double n) {
        if (n == Double.longBitsToDouble(Double.doubleToLongBits(1.7931000183463725E308) ^ 0x7FEFEB11C3AAD037L)) {
            return n;
        }
        if (n < Double.longBitsToDouble(Double.doubleToLongBits(1.1859585260803721E308) ^ 0x7FE51C5AEE8AD07FL)) {
            return n * Double.longBitsToDouble(Double.doubleToLongBits(-12.527781766526259) ^ 0x7FD90E3969654F8FL);
        }
        return n;
    }
    
    public void onDisable() {
        BreadCrumbs.vecs.removeAll(BreadCrumbs.vecs);
    }
    
    public Color getCurrentColor() {
        return new Color((int)BreadCrumbs.red.getValue(), (int)BreadCrumbs.green.getValue(), (int)BreadCrumbs.blue.getValue(), (int)BreadCrumbs.alpha.getValue());
    }
    
    public BreadCrumbs() {
        super("BreadCrumbs", "Draws a small line behind you", Module.Category.RENDER, true, false, false);
        BreadCrumbs.length = (Setting<Integer>)this.register(new Setting("Length", (Object)4, (Object)1, (Object)40));
        BreadCrumbs.width = (Setting<Float>)this.register(new Setting("Width", (Object)Float.intBitsToFloat(Float.floatToIntBits(15.599429f) ^ 0x7EB99743), (Object)Float.intBitsToFloat(Float.floatToIntBits(2.076195f) ^ 0x7F04E061), (Object)Float.intBitsToFloat(Float.floatToIntBits(1.3190416f) ^ 0x7F08D65B)));
        BreadCrumbs.syncColor = (Setting<Boolean>)this.register(new Setting("Sync", (Object)false));
        BreadCrumbs.red = (Setting<Integer>)this.register(new Setting("Red", (Object)30, (Object)0, (Object)255));
        BreadCrumbs.green = (Setting<Integer>)this.register(new Setting("Green", (Object)167, (Object)0, (Object)255));
        BreadCrumbs.blue = (Setting<Integer>)this.register(new Setting("Blue", (Object)255, (Object)0, (Object)255));
        BreadCrumbs.alpha = (Setting<Integer>)this.register(new Setting("Alpha", (Object)255, (Object)0, (Object)255));
        BreadCrumbs.vecs = new ArrayList<double[]>();
    }
    
    public void onUpdate() {
        this.color = (BreadCrumbs.syncColor.getValue() ? Colors.INSTANCE.getCurrentColor() : this.getCurrentColor());
        try {
            final double renderPosX = BreadCrumbs.mc.getRenderManager().renderPosX;
            final double renderPosY = BreadCrumbs.mc.getRenderManager().renderPosY;
            final double renderPosZ = BreadCrumbs.mc.getRenderManager().renderPosZ;
            if (this.isEnabled()) {
                for (final EntityPlayer entityPlayer : BreadCrumbs.mc.world.playerEntities) {
                    if (!(entityPlayer instanceof EntityPlayer)) {
                        continue;
                    }
                    final EntityPlayer entityPlayer2 = entityPlayer;
                    final boolean b = entityPlayer2 == BreadCrumbs.mc.player;
                    double n = renderPosY + Double.longBitsToDouble(Double.doubleToLongBits(0.48965838138858014) ^ 0x7FDF56901B91AE07L);
                    if (BreadCrumbs.mc.player.isElytraFlying()) {
                        n -= Double.longBitsToDouble(Double.doubleToLongBits(29.56900080933637) ^ 0x7FC591AA097B7F4BL);
                    }
                    if (!b) {
                        continue;
                    }
                    BreadCrumbs.vecs.add(new double[] { renderPosX, n - entityPlayer2.height, renderPosZ });
                }
            }
        }
        catch (Exception ex) {}
        if (BreadCrumbs.vecs.size() > (int)BreadCrumbs.length.getValue()) {
            BreadCrumbs.vecs.remove(0);
        }
    }
    
    public void onRender3D(final Render3DEvent render3DEvent) {
        try {
            final double renderPosX = BreadCrumbs.mc.getRenderManager().renderPosX;
            final double renderPosY = BreadCrumbs.mc.getRenderManager().renderPosY;
            final double renderPosZ = BreadCrumbs.mc.getRenderManager().renderPosZ;
            final float n = this.color.getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.49987957f) ^ 0x7D80F037);
            final float n2 = this.color.getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.4340212f) ^ 0x7DA13807);
            final float n3 = this.color.getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.0131841665f) ^ 0x7F270267);
            if (this.isEnabled()) {
                RenderUtil.prepareGL();
                GL11.glPushMatrix();
                GL11.glEnable(2848);
                GL11.glLineWidth((float)BreadCrumbs.width.getValue());
                GL11.glBlendFunc(770, 771);
                GL11.glLineWidth((float)BreadCrumbs.width.getValue());
                GL11.glDepthMask(false);
                GL11.glBegin(3);
                final Iterator<double[]> iterator2;
                Iterator<double[]> iterator = iterator2 = BreadCrumbs.vecs.iterator();
                while (iterator.hasNext()) {
                    final double n4 = 0.0;
                    final double[] array = iterator2.next();
                    final double m = M(Math.hypot(array[0] - BreadCrumbs.mc.player.posX, array[1] - BreadCrumbs.mc.player.posY));
                    if (n4 > (int)BreadCrumbs.length.getValue()) {
                        iterator = iterator2;
                    }
                    else {
                        GL11.glColor4f(n, n2, n3, Float.intBitsToFloat(Float.floatToIntBits(14.099797f) ^ 0x7EE198C5) - (float)(m / (int)BreadCrumbs.length.getValue()));
                        iterator = iterator2;
                        GL11.glVertex3d(array[0] - renderPosX, array[1] - renderPosY, array[2] - renderPosZ);
                    }
                }
                GL11.glEnd();
                GL11.glDepthMask(true);
                GL11.glPopMatrix();
                RenderUtil.releaseGL();
            }
        }
        catch (Exception ex) {}
    }
}
