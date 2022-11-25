//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.mixin.mixins;

import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import javax.annotation.*;
import org.spongepowered.asm.mixin.*;
import org.lwjgl.input.*;
import me.snow.luigihack.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.crash.*;
import org.spongepowered.asm.mixin.injection.*;
import me.snow.luigihack.impl.modules.render.*;
import net.minecraft.client.entity.*;
import net.minecraft.client.multiplayer.*;
import me.snow.luigihack.impl.modules.player.*;

@Mixin({ Minecraft.class })
public abstract class MixinMinecraft
{
    @Shadow
    public abstract void displayGuiScreen(@Nullable final GuiScreen p0);
    
    @Inject(method = { "runTickKeyboard" }, at = { @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;currentScreen:Lnet/minecraft/client/gui/GuiScreen;", ordinal = 0) }, locals = LocalCapture.CAPTURE_FAILSOFT)
    private void onRunTickKeyboard(final CallbackInfo callbackInfo, final int n) {
        if (Keyboard.getEventKeyState() && LuigiHack.moduleManager != null) {
            LuigiHack.moduleManager.onKeyPressed(n);
        }
    }
    
    @Redirect(method = { "run" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;displayCrashReport(Lnet/minecraft/crash/CrashReport;)V"))
    public void displayCrashReportHook(final Minecraft minecraft, final CrashReport crashReport) {
        this.unload();
    }
    
    @Redirect(method = { "runTick" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/WorldClient;doVoidFogParticles(III)V"))
    public void doVoidFogParticlesHook(final WorldClient worldClient, final int n, final int n2, final int n3) {
        NoRender.getInstance().doVoidFogParticles(n, n2, n3);
    }
    
    @Inject(method = { "shutdown" }, at = { @At("HEAD") })
    public void shutdownHook(final CallbackInfo callbackInfo) {
        this.unload();
    }
    
    private void unload() {
        System.out.println("Shutting down: saving configuration");
        LuigiHack.onUnload();
        System.out.println("Configuration saved.");
    }
    
    @Redirect(method = { "sendClickBlockToController" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;isHandActive()Z"))
    private boolean isHandActiveWrapper(final EntityPlayerSP entityPlayerSP) {
        return !MultiTask.getInstance().isOn() && entityPlayerSP.isHandActive();
    }
    
    @Redirect(method = { "rightClickMouse" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/PlayerControllerMP;getIsHittingBlock()Z", ordinal = 0))
    private boolean isHittingBlockHook(final PlayerControllerMP playerControllerMP) {
        return !MultiTask.getInstance().isOn() && playerControllerMP.getIsHittingBlock();
    }
    
    @Inject(method = { "middleClickMouse" }, at = { @At("HEAD") }, cancellable = true)
    public void middleClickMouse(final CallbackInfo callbackInfo) {
        if (LuigiHack.moduleManager.isModuleEnabled((Class)MCP.class)) {
            callbackInfo.cancel();
        }
    }
}
