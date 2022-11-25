//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.mixin.mixins;

import net.minecraft.item.*;
import net.minecraft.client.*;
import org.spongepowered.asm.mixin.*;
import javax.vecmath.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.init.*;
import me.snow.luigihack.impl.modules.client.*;
import net.minecraft.client.entity.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.eventhandler.*;
import org.lwjgl.util.glu.*;
import me.snow.luigihack.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.multiplayer.*;
import net.minecraft.entity.*;
import javax.annotation.*;
import net.minecraft.util.math.*;
import com.google.common.base.*;
import me.snow.luigihack.impl.modules.player.*;
import java.util.*;
import me.snow.luigihack.impl.modules.render.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ EntityRenderer.class })
public abstract class MixinEntityRenderer
{
    @Shadow
    public ItemStack itemActivationItem;
    @Shadow
    @Final
    public Minecraft mc;
    private boolean injection;
    @Final
    private int[] lightmapColors;
    
    public MixinEntityRenderer() {
        this.injection = true;
    }
    
    @Shadow
    public abstract void getMouseOver(final float p0);
    
    private Vector3f mix(final Vector3f vector3f, final Vector3f vector3f2, final float n) {
        return new Vector3f(vector3f.x * (1.0f - n) + vector3f2.x * n, vector3f.y * (1.0f - n) + vector3f2.y * n, vector3f.z * (1.0f - n) + vector3f.z * n);
    }
    
    @Inject(method = { "renderItemActivation" }, at = { @At("HEAD") }, cancellable = true)
    public void renderItemActivationHook(final CallbackInfo callbackInfo) {
        if (this.itemActivationItem != null && NoRender.getInstance().isOn() && (boolean)NoRender.getInstance().totemPops.getValue() && this.itemActivationItem.getItem() == Items.TOTEM_OF_UNDYING) {
            callbackInfo.cancel();
        }
    }
    
    @Inject(method = { "getMouseOver(F)V" }, at = { @At("HEAD") }, cancellable = true)
    public void getMouseOverHook(final float n, final CallbackInfo callbackInfo) {
        if (this.injection) {
            callbackInfo.cancel();
            this.injection = false;
            try {
                this.getMouseOver(n);
            }
            catch (Exception ex) {
                ex.printStackTrace();
                if (Notifications.getInstance().isOn()) {
                    Notifications.displayCrash(ex);
                }
            }
            this.injection = true;
        }
    }
    
    @Redirect(method = { "setupCameraTransform" }, at = @At(value = "FIELD", target = "Lnet/minecraft/client/entity/EntityPlayerSP;prevTimeInPortal:F"))
    public float prevTimeInPortalHook(final EntityPlayerSP entityPlayerSP) {
        if (NoRender.getInstance().isOn() && (boolean)NoRender.getInstance().nausea.getValue()) {
            return -3.4028235E38f;
        }
        return entityPlayerSP.prevTimeInPortal;
    }
    
    @Redirect(method = { "setupCameraTransform" }, at = @At(value = "INVOKE", target = "Lorg/lwjgl/util/glu/Project;gluPerspective(FFFF)V"))
    private void onSetupCameraTransform(final float n, final float n2, final float n3, final float n4) {
        final PerspectiveEvent perspectiveEvent = new PerspectiveEvent(this.mc.displayWidth / (float)this.mc.displayHeight);
        MinecraftForge.EVENT_BUS.post((Event)perspectiveEvent);
        Project.gluPerspective(n, perspectiveEvent.getAspect(), n3, n4);
    }
    
    @Redirect(method = { "renderWorldPass" }, at = @At(value = "INVOKE", target = "Lorg/lwjgl/util/glu/Project;gluPerspective(FFFF)V"))
    private void onRenderWorldPass(final float n, final float n2, final float n3, final float n4) {
        final PerspectiveEvent perspectiveEvent = new PerspectiveEvent(this.mc.displayWidth / (float)this.mc.displayHeight);
        MinecraftForge.EVENT_BUS.post((Event)perspectiveEvent);
        Project.gluPerspective(n, perspectiveEvent.getAspect(), n3, n4);
    }
    
    @Redirect(method = { "renderCloudsCheck" }, at = @At(value = "INVOKE", target = "Lorg/lwjgl/util/glu/Project;gluPerspective(FFFF)V"))
    private void onRenderCloudsCheck(final float n, final float n2, final float n3, final float n4) {
        final PerspectiveEvent perspectiveEvent = new PerspectiveEvent(this.mc.displayWidth / (float)this.mc.displayHeight);
        MinecraftForge.EVENT_BUS.post((Event)perspectiveEvent);
        Project.gluPerspective(n, perspectiveEvent.getAspect(), n3, n4);
    }
    
    @Inject(method = { "setupFog" }, at = { @At("TAIL") })
    private void fogHook(final int n, final float n2, final CallbackInfo callbackInfo) {
        if (((AntiFog)LuigiHack.moduleManager.getModuleByClass((Class)AntiFog.class)).isEnabled()) {
            GlStateManager.disableFog();
        }
    }
    
    @Inject(method = { "hurtCameraEffect" }, at = { @At("HEAD") }, cancellable = true)
    public void hurtCameraEffectHook(final float n, final CallbackInfo callbackInfo) {
        if (NoRender.getInstance().isOn() && (boolean)NoRender.getInstance().hurtcam.getValue()) {
            callbackInfo.cancel();
        }
    }
    
    @Redirect(method = { "getMouseOver" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/WorldClient;getEntitiesInAABBexcluding(Lnet/minecraft/entity/Entity;Lnet/minecraft/util/math/AxisAlignedBB;Lcom/google/common/base/Predicate;)Ljava/util/List;"))
    public List<Entity> getEntitiesInAABBexcludingHook(final WorldClient worldClient, @Nullable final Entity entity, final AxisAlignedBB axisAlignedBB, @Nullable final Predicate<? super Entity> predicate) {
        if (NoEntityTrace.getINSTANCE().isOn() && NoEntityTrace.getINSTANCE().noTrace) {
            return new ArrayList<Entity>();
        }
        return (List<Entity>)worldClient.getEntitiesInAABBexcluding(entity, axisAlignedBB, (Predicate)predicate);
    }
    
    @ModifyVariable(method = { "orientCamera" }, ordinal = 3, at = @At(value = "STORE", ordinal = 0), require = 1)
    public double changeCameraDistanceHook(final double n) {
        return (double)((ViewClip.getInstance().isEnabled() && (boolean)ViewClip.getInstance().extend.getValue()) ? ViewClip.getInstance().distance.getValue() : n);
    }
    
    @ModifyVariable(method = { "orientCamera" }, ordinal = 7, at = @At(value = "STORE", ordinal = 0), require = 1)
    public double orientCameraHook(final double n) {
        return (double)((ViewClip.getInstance().isEnabled() && (boolean)ViewClip.getInstance().extend.getValue()) ? ViewClip.getInstance().distance.getValue() : ((ViewClip.getInstance().isEnabled() && !(boolean)ViewClip.getInstance().extend.getValue()) ? 4.0 : n));
    }
}
