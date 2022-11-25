//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.manager;

import me.snow.luigihack.impl.*;
import net.minecraft.util.*;
import java.util.*;
import org.lwjgl.opengl.*;
import java.awt.*;
import net.minecraft.client.renderer.*;
import net.minecraft.util.math.*;
import me.snow.luigihack.api.util.*;

public class WaypointManager extends Feature
{
    public /* synthetic */ Map<String, Waypoint> waypoints;
    
    static {
        WAYPOINT_RESOURCE = new ResourceLocation("textures/waypoint.png");
    }
    
    public WaypointManager() {
        this.waypoints = new HashMap<String, Waypoint>();
    }
    
    public static class Waypoint
    {
        public /* synthetic */ int x;
        public /* synthetic */ int alpha;
        public /* synthetic */ int blue;
        public /* synthetic */ int red;
        public /* synthetic */ String server;
        public /* synthetic */ String owner;
        public /* synthetic */ int green;
        public /* synthetic */ int z;
        public /* synthetic */ int y;
        public /* synthetic */ int dimension;
        
        public void render() {
            final double n = this.x - Util.mc.getRenderManager().renderPosX + 0.5;
            final double n2 = this.y - Util.mc.getRenderManager().renderPosY - 0.5;
            final double n3 = this.z - Util.mc.getRenderManager().renderPosZ + 0.5;
            float n4 = (float)(Util.mc.player.getDistance(n + Util.mc.getRenderManager().renderPosX, n2 + Util.mc.getRenderManager().renderPosY, n3 + Util.mc.getRenderManager().renderPosZ) / 4.0);
            if (n4 < 1.6f) {
                n4 = 1.6f;
            }
            GL11.glPushMatrix();
            GL11.glTranslatef((float)n, (float)n2 + 1.4f, (float)n3);
            GL11.glNormal3f(0.0f, 1.0f, 0.0f);
            GL11.glRotatef(-Util.mc.getRenderManager().playerViewY, 0.0f, 1.0f, 0.0f);
            GL11.glRotatef(Util.mc.getRenderManager().playerViewX, 1.0f, 0.0f, 0.0f);
            final float n5;
            GL11.glScalef(-(n5 = n4 / 25.0f), -n5, n5);
            GL11.glDisable(2896);
            GL11.glDisable(2929);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            final int n6 = Util.mc.fontRenderer.getStringWidth(this.owner) / 2;
            GL11.glPushMatrix();
            GL11.glPopMatrix();
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glScalef(0.5f, 0.5f, 0.5f);
            Util.mc.fontRenderer.drawStringWithShadow(this.owner, (float)(-n6), (float)(-(Util.mc.fontRenderer.FONT_HEIGHT + 7)), new Color(this.red, this.green, this.blue, this.alpha).getRGB());
            GL11.glScalef(1.0f, 1.0f, 1.0f);
            GlStateManager.enableTexture2D();
            GL11.glDisable(3042);
            GL11.glEnable(2896);
            GL11.glEnable(2929);
            GL11.glPopMatrix();
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        }
        
        public void renderBox() {
            GL11.glPushMatrix();
            GL11.glDisable(2896);
            GL11.glDisable(2929);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            RenderUtil.drawBlockOutline(new BlockPos(this.x, this.y, this.z), new Color(this.red, this.green, this.blue, this.alpha), 1.0f, true);
            GlStateManager.enableTexture2D();
            GL11.glDisable(3042);
            GL11.glEnable(2896);
            GL11.glEnable(2929);
            GL11.glPopMatrix();
        }
        
        public Waypoint(final String owner, final String server, final int dimension, final int x, final int y, final int z, final Color color) {
            this.owner = owner;
            this.server = server;
            this.dimension = dimension;
            this.x = x;
            this.y = y;
            this.z = z;
            this.red = color.getRed();
            this.green = color.getGreen();
            this.blue = color.getBlue();
            this.alpha = color.getAlpha();
        }
    }
}
