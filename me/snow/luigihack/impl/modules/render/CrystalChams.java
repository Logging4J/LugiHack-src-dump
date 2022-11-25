//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.render;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.util.*;
import net.minecraft.entity.item.*;
import me.snow.luigihack.impl.modules.client.*;
import me.snow.luigihack.api.util.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import java.awt.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraft.network.play.server.*;
import net.minecraft.entity.*;
import net.minecraftforge.fml.common.eventhandler.*;
import java.util.*;
import java.util.concurrent.*;

public class CrystalChams extends Module
{
    public /* synthetic */ Setting<Boolean> wireframeThroughWalls;
    public /* synthetic */ Setting<Integer> hiddenGreen;
    public /* synthetic */ Setting<Integer> speed;
    public /* synthetic */ Setting<Boolean> glint;
    public /* synthetic */ Setting<Float> scale;
    public /* synthetic */ Setting<Integer> hiddenRed;
    public /* synthetic */ Setting<Integer> hiddenBlue;
    public /* synthetic */ Setting<Integer> saturation;
    public /* synthetic */ Setting<Boolean> animateScale;
    public /* synthetic */ Setting<Float> lineWidth;
    public /* synthetic */ Setting<Boolean> throughWalls;
    public /* synthetic */ Setting<Integer> alpha;
    public /* synthetic */ Map<EntityEnderCrystal, Float> scaleMap;
    public /* synthetic */ Setting<Boolean> xqz;
    public /* synthetic */ Setting<Boolean> rainbow;
    public /* synthetic */ Setting<Boolean> wireframe;
    public /* synthetic */ Setting<Boolean> colorSync;
    public static /* synthetic */ CrystalChams INSTANCE;
    public /* synthetic */ Setting<Integer> hiddenAlpha;
    public /* synthetic */ Setting<Integer> brightness;
    public /* synthetic */ Setting<Integer> green;
    public /* synthetic */ Setting<Integer> blue;
    public /* synthetic */ Setting<Boolean> chams;
    public /* synthetic */ Setting<Integer> red;
    
    public void onRenderModel(final RenderEntityModelEvent renderEntityModelEvent) {
        if (renderEntityModelEvent.getStage() != 0 || !(renderEntityModelEvent.entity instanceof EntityEnderCrystal) || !(boolean)this.wireframe.getValue()) {
            return;
        }
        final Color color = this.colorSync.getValue() ? Colors.INSTANCE.getCurrentColor() : EntityUtil.getColor(renderEntityModelEvent.entity, (int)this.red.getValue(), (int)this.green.getValue(), (int)this.blue.getValue(), (int)this.alpha.getValue(), false);
        CrystalChams.mc.gameSettings.fancyGraphics = false;
        CrystalChams.mc.gameSettings.gammaSetting = 10000.0f;
        GL11.glPushMatrix();
        GL11.glPushAttrib(1048575);
        GL11.glPolygonMode(1032, 6913);
        GL11.glDisable(3553);
        GL11.glDisable(2896);
        if (this.wireframeThroughWalls.getValue()) {
            GL11.glDisable(2929);
        }
        GL11.glEnable(2848);
        GL11.glEnable(3042);
        GlStateManager.blendFunc(770, 771);
        GlStateManager.color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
        GlStateManager.glLineWidth((float)this.lineWidth.getValue());
        renderEntityModelEvent.modelBase.render(renderEntityModelEvent.entity, renderEntityModelEvent.limbSwing, renderEntityModelEvent.limbSwingAmount, renderEntityModelEvent.age, renderEntityModelEvent.headYaw, renderEntityModelEvent.headPitch, renderEntityModelEvent.scale);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }
    
    static {
        RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
    }
    
    @SubscribeEvent
    public void onReceivePacket(final PacketEvent.Receive receive) {
        if (receive.getPacket() instanceof SPacketDestroyEntities) {
            final int[] getEntityIDs = ((SPacketDestroyEntities)receive.getPacket()).getEntityIDs();
            for (int length = getEntityIDs.length, i = 0; i < length; ++i) {
                final Entity getEntityByID = CrystalChams.mc.world.getEntityByID(getEntityIDs[i]);
                if (getEntityByID instanceof EntityEnderCrystal) {
                    this.scaleMap.remove(getEntityByID);
                }
            }
        }
    }
    
    public void onUpdate() {
        if (fullNullCheck()) {
            return;
        }
        for (final Entity entity : CrystalChams.mc.world.loadedEntityList) {
            if (!(entity instanceof EntityEnderCrystal)) {
                continue;
            }
            if (!this.scaleMap.containsKey(entity)) {
                this.scaleMap.put((EntityEnderCrystal)entity, 3.125E-4f);
            }
            else {
                this.scaleMap.put((EntityEnderCrystal)entity, this.scaleMap.get(entity) + 3.125E-4f);
            }
            if (this.scaleMap.get(entity) < 0.0625f * (float)this.scale.getValue()) {
                continue;
            }
            this.scaleMap.remove(entity);
        }
    }
    
    public CrystalChams() {
        super("CrystalChams", "Modifies crystal rendering in different ways", Module.Category.RENDER, true, false, false);
        this.animateScale = (Setting<Boolean>)this.register(new Setting("AnimateScale", (Object)false));
        this.chams = (Setting<Boolean>)this.register(new Setting("Chams", (Object)false));
        this.throughWalls = (Setting<Boolean>)this.register(new Setting("ThroughWalls", (Object)true));
        this.wireframeThroughWalls = (Setting<Boolean>)this.register(new Setting("WireThroughWalls", (Object)true));
        this.glint = (Setting<Boolean>)this.register(new Setting("Glint", (Object)Boolean.FALSE, p0 -> (boolean)this.chams.getValue()));
        this.wireframe = (Setting<Boolean>)this.register(new Setting("Wireframe", (Object)false));
        this.scale = (Setting<Float>)this.register(new Setting("Scale", (Object)1.0f, (Object)0.1f, (Object)10.0f));
        this.lineWidth = (Setting<Float>)this.register(new Setting("LineWidth", (Object)1.0f, (Object)0.1f, (Object)3.0f));
        this.colorSync = (Setting<Boolean>)this.register(new Setting("Sync", (Object)false));
        this.rainbow = (Setting<Boolean>)this.register(new Setting("Rainbow", (Object)false));
        this.saturation = (Setting<Integer>)this.register(new Setting("Saturation", (Object)50, (Object)0, (Object)100, p0 -> (boolean)this.rainbow.getValue()));
        this.brightness = (Setting<Integer>)this.register(new Setting("Brightness", (Object)100, (Object)0, (Object)100, p0 -> (boolean)this.rainbow.getValue()));
        this.speed = (Setting<Integer>)this.register(new Setting("Speed", (Object)40, (Object)1, (Object)100, p0 -> (boolean)this.rainbow.getValue()));
        this.xqz = (Setting<Boolean>)this.register(new Setting("XQZ", (Object)Boolean.FALSE, p0 -> !(boolean)this.rainbow.getValue() && (boolean)this.throughWalls.getValue()));
        this.red = (Setting<Integer>)this.register(new Setting("Red", (Object)0, (Object)0, (Object)255, p0 -> !(boolean)this.rainbow.getValue()));
        this.green = (Setting<Integer>)this.register(new Setting("Green", (Object)255, (Object)0, (Object)255, p0 -> !(boolean)this.rainbow.getValue()));
        this.blue = (Setting<Integer>)this.register(new Setting("Blue", (Object)0, (Object)0, (Object)255, p0 -> !(boolean)this.rainbow.getValue()));
        this.alpha = (Setting<Integer>)this.register(new Setting("Alpha", (Object)255, (Object)0, (Object)255));
        this.hiddenRed = (Setting<Integer>)this.register(new Setting("Hidden Red", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.xqz.getValue() && !(boolean)this.rainbow.getValue()));
        this.hiddenGreen = (Setting<Integer>)this.register(new Setting("Hidden Green", (Object)0, (Object)0, (Object)255, p0 -> (boolean)this.xqz.getValue() && !(boolean)this.rainbow.getValue()));
        this.hiddenBlue = (Setting<Integer>)this.register(new Setting("Hidden Blue", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.xqz.getValue() && !(boolean)this.rainbow.getValue()));
        this.hiddenAlpha = (Setting<Integer>)this.register(new Setting("Hidden Alpha", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.xqz.getValue() && !(boolean)this.rainbow.getValue()));
        this.scaleMap = new ConcurrentHashMap<EntityEnderCrystal, Float>();
        CrystalChams.INSTANCE = this;
    }
}
