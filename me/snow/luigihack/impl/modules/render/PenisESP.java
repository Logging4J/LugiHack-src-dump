//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.render;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.entity.player.*;
import org.lwjgl.opengl.*;
import org.lwjgl.util.glu.*;
import me.snow.luigihack.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraft.client.renderer.*;
import java.util.*;

public class PenisESP extends Module
{
    private /* synthetic */ Setting<Boolean> self;
    private /* synthetic */ Setting<Boolean> friends;
    private /* synthetic */ Setting<Boolean> others;
    private /* synthetic */ Setting<Float> selfLength;
    private /* synthetic */ Setting<Float> friendLength;
    private /* synthetic */ Setting<Float> othersLength;
    private /* synthetic */ Setting<Float> penisSize;
    
    public void esp(final EntityPlayer entityPlayer, final double n, final double n2, final double n3) {
        if (!this.shouldRenderPenis(entityPlayer)) {
            return;
        }
        GL11.glDisable(2896);
        GL11.glDisable(3553);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(2929);
        GL11.glEnable(2848);
        GL11.glDepthMask(true);
        GL11.glLineWidth(1.0f);
        GL11.glTranslated(n, n2, n3);
        GL11.glRotatef(-entityPlayer.rotationYaw, 0.0f, entityPlayer.height, 0.0f);
        GL11.glTranslated(-n, -n2, -n3);
        GL11.glTranslated(n, n2 + entityPlayer.height / 2.0f - 0.22499999403953552, n3);
        GL11.glColor4f(1.38f, 0.55f, 2.38f, 1.0f);
        GL11.glRotated((double)(entityPlayer.isSneaking() ? 35 : 0), 1.0, 0.0, 0.0);
        GL11.glTranslated(0.0, 0.0, 0.07500000298023224);
        final Cylinder cylinder = new Cylinder();
        cylinder.setDrawStyle(100013);
        cylinder.draw(0.1f * (float)this.penisSize.getValue(), 0.11f, this.getPenisLength(entityPlayer), 25, 20);
        GL11.glTranslated(0.0, 0.0, -0.12500000298023223);
        GL11.glTranslated(-0.09000000074505805, 0.0, 0.0);
        final Sphere sphere = new Sphere();
        sphere.setDrawStyle(100013);
        sphere.draw(0.14f * (float)this.penisSize.getValue(), 10, 20);
        GL11.glTranslated(0.16000000149011612, 0.0, 0.0);
        final Sphere sphere2 = new Sphere();
        sphere2.setDrawStyle(100013);
        sphere2.draw(0.14f * (float)this.penisSize.getValue(), 10, 20);
        GL11.glColor4f(1.35f, 0.0f, 0.0f, 1.0f);
        GL11.glTranslated(-0.07000000074505806, 0.0, this.getPenisLength(entityPlayer) + 0.189999952316284);
        final Sphere sphere3 = new Sphere();
        sphere3.setDrawStyle(100013);
        sphere3.draw(0.13f * (float)this.penisSize.getValue(), 15, 20);
        GL11.glDepthMask(true);
        GL11.glDisable(2848);
        GL11.glEnable(2929);
        GL11.glDisable(3042);
        GL11.glEnable(2896);
        GL11.glEnable(3553);
    }
    
    private float getPenisLength(final EntityPlayer entityPlayer) {
        if (entityPlayer.entityUniqueID == PenisESP.mc.player.entityUniqueID) {
            return (float)this.selfLength.getValue();
        }
        if (LuigiHack.friendManager.isFriend(entityPlayer)) {
            return (float)this.friendLength.getValue();
        }
        return (float)this.othersLength.getValue();
    }
    
    public PenisESP() {
        super("Penis", "Renders a penis on ur screen", Module.Category.RENDER, true, false, false);
        this.self = (Setting<Boolean>)this.register(new Setting("Self", (Object)true));
        this.selfLength = (Setting<Float>)this.register(new Setting("SelfLength", (Object)1.0f, (Object)0.1f, (Object)5.0f, p0 -> (boolean)this.self.getValue()));
        this.friends = (Setting<Boolean>)this.register(new Setting("Friends", (Object)true));
        this.friendLength = (Setting<Float>)this.register(new Setting("FriendsLength", (Object)0.8f, (Object)0.1f, (Object)5.0f, p0 -> (boolean)this.friends.getValue()));
        this.others = (Setting<Boolean>)this.register(new Setting("Others", (Object)true));
        this.othersLength = (Setting<Float>)this.register(new Setting("OthersLength", (Object)0.4f, (Object)0.1f, (Object)5.0f, p0 -> (boolean)this.others.getValue()));
        this.penisSize = (Setting<Float>)this.register(new Setting("Scale", (Object)1.5f, (Object)0.1f, (Object)5.0f));
    }
    
    private boolean shouldRenderPenis(final EntityPlayer entityPlayer) {
        if (entityPlayer.entityUniqueID == PenisESP.mc.player.entityUniqueID) {
            return (boolean)this.self.getValue();
        }
        if (LuigiHack.friendManager.isFriend(entityPlayer)) {
            return (boolean)this.friends.getValue();
        }
        return (boolean)this.others.getValue();
    }
    
    public void onRender3D(final Render3DEvent render3DEvent) {
        for (final EntityPlayer next : PenisESP.mc.world.loadedEntityList) {
            if (next instanceof EntityPlayer) {
                final EntityPlayer entityPlayer = next;
                final double n = entityPlayer.lastTickPosX + (entityPlayer.posX - entityPlayer.lastTickPosX) * PenisESP.mc.timer.renderPartialTicks;
                PenisESP.mc.getRenderManager();
                final double n2 = n - PenisESP.mc.getRenderManager().renderPosX;
                final double n3 = entityPlayer.lastTickPosY + (entityPlayer.posY - entityPlayer.lastTickPosY) * PenisESP.mc.timer.renderPartialTicks;
                PenisESP.mc.getRenderManager();
                final double n4 = n3 - PenisESP.mc.getRenderManager().renderPosY;
                final double n5 = entityPlayer.lastTickPosZ + (entityPlayer.posZ - entityPlayer.lastTickPosZ) * PenisESP.mc.timer.renderPartialTicks;
                PenisESP.mc.getRenderManager();
                final double n6 = n5 - PenisESP.mc.getRenderManager().renderPosZ;
                GL11.glPushMatrix();
                RenderHelper.disableStandardItemLighting();
                this.esp(entityPlayer, n2, n4, n6);
                RenderHelper.enableStandardItemLighting();
                GL11.glPopMatrix();
            }
        }
    }
}
