//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.render;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.impl.command.*;
import net.minecraft.entity.player.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import java.util.concurrent.*;
import me.snow.luigihack.api.event.events.*;
import me.snow.luigihack.impl.modules.client.*;
import java.awt.*;
import net.minecraft.util.math.*;
import net.minecraft.client.renderer.*;
import net.minecraft.entity.*;
import me.snow.luigihack.api.util.*;

public class LogoutSpots extends Module
{
    private final /* synthetic */ List<LogoutPos> spots;
    private final /* synthetic */ Setting<Boolean> coords;
    private final /* synthetic */ Setting<Float> scaling;
    private final /* synthetic */ Setting<Integer> blue;
    public /* synthetic */ Setting<Boolean> message;
    private final /* synthetic */ Setting<Boolean> colorSync;
    private final /* synthetic */ Setting<Boolean> notification;
    private final /* synthetic */ Setting<Integer> red;
    private final /* synthetic */ Setting<Float> factor;
    private final /* synthetic */ Setting<Integer> green;
    public /* synthetic */ Setting<Float> range;
    private final /* synthetic */ Setting<Boolean> scaleing;
    private final /* synthetic */ Setting<Integer> alpha;
    private final /* synthetic */ Setting<Boolean> rect;
    private final /* synthetic */ Setting<Boolean> smartScale;
    
    private double interpolate(final double n, final double n2, final float n3) {
        return n + (n2 - n) * n3;
    }
    
    @SubscribeEvent
    public void onConnection(final ConnectionEvent connectionEvent) {
        if (connectionEvent.getStage() == 0) {
            final EntityPlayer getPlayerEntityByUUID = LogoutSpots.mc.world.getPlayerEntityByUUID(connectionEvent.getUuid());
            if (getPlayerEntityByUUID != null && (boolean)this.message.getValue()) {
                Command.sendMessage(String.valueOf(new StringBuilder().append("§a").append(getPlayerEntityByUUID.getName()).append(" just logged in").append(this.coords.getValue() ? String.valueOf(new StringBuilder().append(" at (").append((int)getPlayerEntityByUUID.posX).append(", ").append((int)getPlayerEntityByUUID.posY).append(", ").append((int)getPlayerEntityByUUID.posZ).append(")!")) : "!")), (boolean)this.notification.getValue());
            }
            this.spots.removeIf(logoutPos -> logoutPos.getName().equalsIgnoreCase(connectionEvent.getName()));
        }
        else if (connectionEvent.getStage() == 1) {
            final EntityPlayer entity = connectionEvent.getEntity();
            final UUID uuid = connectionEvent.getUuid();
            final String name = connectionEvent.getName();
            if (this.message.getValue()) {
                Command.sendMessage(String.valueOf(new StringBuilder().append("§c").append(connectionEvent.getName()).append(" just logged out").append(this.coords.getValue() ? String.valueOf(new StringBuilder().append(" at (").append((int)entity.posX).append(", ").append((int)entity.posY).append(", ").append((int)entity.posZ).append(")!")) : "!")), (boolean)this.notification.getValue());
            }
            if (name != null && entity != null && uuid != null) {
                this.spots.add(new LogoutPos(name, uuid, entity));
            }
        }
    }
    
    public LogoutSpots() {
        super("LogoutSpots", "Renders LogoutSpots", Module.Category.RENDER, true, false, false);
        this.colorSync = (Setting<Boolean>)this.register(new Setting("Sync", (Object)false));
        this.red = (Setting<Integer>)this.register(new Setting("Red", (Object)255, (Object)0, (Object)255));
        this.green = (Setting<Integer>)this.register(new Setting("Green", (Object)0, (Object)0, (Object)255));
        this.blue = (Setting<Integer>)this.register(new Setting("Blue", (Object)0, (Object)0, (Object)255));
        this.alpha = (Setting<Integer>)this.register(new Setting("Alpha", (Object)255, (Object)0, (Object)255));
        this.scaleing = (Setting<Boolean>)this.register(new Setting("Scale", (Object)false));
        this.scaling = (Setting<Float>)this.register(new Setting("Size", (Object)4.0f, (Object)0.1f, (Object)20.0f));
        this.factor = (Setting<Float>)this.register(new Setting("Factor", (Object)0.3f, (Object)0.1f, (Object)1.0f, p0 -> (boolean)this.scaleing.getValue()));
        this.smartScale = (Setting<Boolean>)this.register(new Setting("SmartScale", (Object)Boolean.FALSE, p0 -> (boolean)this.scaleing.getValue()));
        this.rect = (Setting<Boolean>)this.register(new Setting("Rectangle", (Object)true));
        this.coords = (Setting<Boolean>)this.register(new Setting("Coords", (Object)true));
        this.notification = (Setting<Boolean>)this.register(new Setting("Notification", (Object)true));
        this.spots = new CopyOnWriteArrayList<LogoutPos>();
        this.range = (Setting<Float>)this.register(new Setting("Range", (Object)300.0f, (Object)50.0f, (Object)500.0f));
        this.message = (Setting<Boolean>)this.register(new Setting("Message", (Object)false));
    }
    
    public void onDisable() {
        this.spots.clear();
    }
    
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (!this.spots.isEmpty()) {
            synchronized (this.spots) {
                Color currentColor = null;
                final AxisAlignedBB axisAlignedBB;
                this.spots.forEach(logoutPos -> {
                    if (logoutPos.getEntity() != null) {
                        RenderUtil.interpolateAxis(logoutPos.getEntity().getEntityBoundingBox());
                        if (this.colorSync.getValue()) {
                            currentColor = Colors.INSTANCE.getCurrentColor();
                        }
                        else {
                            // new(java.awt.Color.class)
                            new Color((int)this.red.getValue(), (int)this.green.getValue(), (int)this.blue.getValue(), (int)this.alpha.getValue());
                        }
                        RenderUtil.drawBlockOutline(axisAlignedBB, currentColor, 1.0f);
                        this.renderNameTag(logoutPos.getName(), this.interpolate(logoutPos.getEntity().lastTickPosX, logoutPos.getEntity().posX, render3DEvent.getPartialTicks()) - LogoutSpots.mc.getRenderManager().renderPosX, this.interpolate(logoutPos.getEntity().lastTickPosY, logoutPos.getEntity().posY, render3DEvent.getPartialTicks()) - LogoutSpots.mc.getRenderManager().renderPosY, this.interpolate(logoutPos.getEntity().lastTickPosZ, logoutPos.getEntity().posZ, render3DEvent.getPartialTicks()) - LogoutSpots.mc.getRenderManager().renderPosZ, render3DEvent.getPartialTicks(), logoutPos.getX(), logoutPos.getY(), logoutPos.getZ());
                    }
                });
            }
        }
    }
    
    public void onLogout() {
        this.spots.clear();
    }
    
    private void renderNameTag(final String str, final double n, final double n2, final double n3, final float n4, final double n5, final double n6, final double n7) {
        final double n8 = n2 + 0.7;
        final Entity getRenderViewEntity = LogoutSpots.mc.getRenderViewEntity();
        assert getRenderViewEntity != null;
        final double posX = getRenderViewEntity.posX;
        final double posY = getRenderViewEntity.posY;
        final double posZ = getRenderViewEntity.posZ;
        getRenderViewEntity.posX = this.interpolate(getRenderViewEntity.prevPosX, getRenderViewEntity.posX, n4);
        getRenderViewEntity.posY = this.interpolate(getRenderViewEntity.prevPosY, getRenderViewEntity.posY, n4);
        getRenderViewEntity.posZ = this.interpolate(getRenderViewEntity.prevPosZ, getRenderViewEntity.posZ, n4);
        final String value = String.valueOf(new StringBuilder().append(str).append(" XYZ: ").append((int)n5).append(", ").append((int)n6).append(", ").append((int)n7));
        final double getDistance = getRenderViewEntity.getDistance(n + LogoutSpots.mc.getRenderManager().viewerPosX, n8 + LogoutSpots.mc.getRenderManager().viewerPosY, n3 + LogoutSpots.mc.getRenderManager().viewerPosZ);
        final int n9 = this.renderer.getStringWidth(value) / 2;
        double n10 = (0.0018 + (float)this.scaling.getValue() * (getDistance * (float)this.factor.getValue())) / 1000.0;
        if (getDistance <= 8.0 && (boolean)this.smartScale.getValue()) {
            n10 = 0.0245;
        }
        if (!(boolean)this.scaleing.getValue()) {
            n10 = (float)this.scaling.getValue() / 100.0;
        }
        GlStateManager.pushMatrix();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.enablePolygonOffset();
        GlStateManager.doPolygonOffset(1.0f, -1500000.0f);
        GlStateManager.disableLighting();
        GlStateManager.translate((float)n, (float)n8 + 1.4f, (float)n3);
        GlStateManager.rotate(-LogoutSpots.mc.getRenderManager().playerViewY, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(LogoutSpots.mc.getRenderManager().playerViewX, (LogoutSpots.mc.gameSettings.thirdPersonView == 2) ? -1.0f : 1.0f, 0.0f, 0.0f);
        GlStateManager.scale(-n10, -n10, n10);
        GlStateManager.disableDepth();
        GlStateManager.enableBlend();
        GlStateManager.enableBlend();
        if (this.rect.getValue()) {
            RenderUtil.drawRect((float)(-n9 - 2), (float)(-(this.renderer.getFontHeight() + 1)), n9 + 2.0f, 1.5f, 1426063360);
        }
        GlStateManager.disableBlend();
        this.renderer.drawStringWithShadow(value, (float)(-n9), (float)(-(this.renderer.getFontHeight() - 1)), ((boolean)this.colorSync.getValue()) ? Colors.INSTANCE.getCurrentColorHex() : ColorUtil.toRGBA(new Color((int)this.red.getValue(), (int)this.green.getValue(), (int)this.blue.getValue(), (int)this.alpha.getValue())));
        getRenderViewEntity.posX = posX;
        getRenderViewEntity.posY = posY;
        getRenderViewEntity.posZ = posZ;
        GlStateManager.enableDepth();
        GlStateManager.disableBlend();
        GlStateManager.disablePolygonOffset();
        GlStateManager.doPolygonOffset(1.0f, 1500000.0f);
        GlStateManager.popMatrix();
    }
    
    public void onUpdate() {
        if (!fullNullCheck()) {
            this.spots.removeIf(logoutPos -> LogoutSpots.mc.player.getDistanceSq((Entity)logoutPos.getEntity()) >= MathUtil.square((float)this.range.getValue()));
        }
    }
    
    private static class LogoutPos
    {
        private final /* synthetic */ double y;
        private final /* synthetic */ UUID uuid;
        private final /* synthetic */ String name;
        private final /* synthetic */ double x;
        private final /* synthetic */ double z;
        private final /* synthetic */ EntityPlayer entity;
        
        public UUID getUuid() {
            return this.uuid;
        }
        
        public EntityPlayer getEntity() {
            return this.entity;
        }
        
        public double getY() {
            return this.y;
        }
        
        public String getName() {
            return this.name;
        }
        
        public double getZ() {
            return this.z;
        }
        
        public LogoutPos(final String name, final UUID uuid, final EntityPlayer entity) {
            this.name = name;
            this.uuid = uuid;
            this.entity = entity;
            this.x = entity.posX;
            this.y = entity.posY;
            this.z = entity.posZ;
        }
        
        public double getX() {
            return this.x;
        }
    }
}
