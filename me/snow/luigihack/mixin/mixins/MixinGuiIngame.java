//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.snow.luigihack.impl.gui.custom.*;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.client.gui.*;
import me.snow.luigihack.*;
import me.snow.luigihack.impl.modules.client.*;
import me.snow.luigihack.impl.modules.render.*;

@Mixin({ GuiIngame.class })
public class MixinGuiIngame extends Gui
{
    @Mutable
    @Shadow
    @Final
    public GuiNewChat persistantChatGUI;
    
    @Inject(method = { "<init>" }, at = { @At("RETURN") })
    public void init(final Minecraft minecraft, final CallbackInfo callbackInfo) {
        this.persistantChatGUI = (GuiNewChat)new GuiCustomNewChat(minecraft);
    }
    
    @Inject(method = { "renderPortal" }, at = { @At("HEAD") }, cancellable = true)
    protected void renderPortalHook(final float n, final ScaledResolution scaledResolution, final CallbackInfo callbackInfo) {
        if (NoRender.getInstance().isOn() && (boolean)NoRender.getInstance().portal.getValue()) {
            callbackInfo.cancel();
        }
    }
    
    @Inject(method = { "renderPumpkinOverlay" }, at = { @At("HEAD") }, cancellable = true)
    protected void renderPumpkinOverlayHook(final ScaledResolution scaledResolution, final CallbackInfo callbackInfo) {
        if (NoRender.getInstance().isOn() && (boolean)NoRender.getInstance().pumpkin.getValue()) {
            callbackInfo.cancel();
        }
    }
    
    @Inject(method = { "renderPotionEffects" }, at = { @At("HEAD") }, cancellable = true)
    protected void renderPotionEffectsHook(final ScaledResolution scaledResolution, final CallbackInfo callbackInfo) {
        if (LuigiHack.moduleManager != null && !(boolean)HUD.getInstance().potionIcons.getValue()) {
            callbackInfo.cancel();
        }
    }
    
    @Inject(method = { "renderAttackIndicator" }, at = { @At("HEAD") }, cancellable = true)
    public void onRenderAttackIndicator(final float n, final ScaledResolution scaledResolution, final CallbackInfo callbackInfo) {
        if (new Crosshair().isEnabled()) {
            callbackInfo.cancel();
        }
    }
}
