//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.mixin.mixins;

import net.minecraft.client.gui.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.snow.luigihack.*;
import org.spongepowered.asm.mixin.injection.*;
import me.snow.luigihack.impl.modules.client.*;
import net.minecraft.client.*;

@Mixin({ FontRenderer.class })
public abstract class MixinFontRenderer
{
    @Shadow
    protected abstract int renderString(final String p0, final float p1, final float p2, final int p3, final boolean p4);
    
    @Shadow
    protected abstract void renderStringAtPos(final String p0, final boolean p1);
    
    @Inject(method = { "drawString(Ljava/lang/String;FFIZ)I" }, at = { @At("HEAD") }, cancellable = true)
    public void renderStringHook(final String s, final float n, final float n2, final int n3, final boolean b, final CallbackInfoReturnable<Integer> callbackInfoReturnable) {
        if (CustomFont.getInstance().isOn() && (boolean)CustomFont.getInstance().full.getValue() && LuigiHack.textManager != null) {
            callbackInfoReturnable.setReturnValue((Object)(int)LuigiHack.textManager.drawString(s, n, n2, n3, b));
        }
    }
    
    @Redirect(method = { "drawString(Ljava/lang/String;FFIZ)I" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/FontRenderer;renderString(Ljava/lang/String;FFIZ)I"))
    public int renderStringHook(final FontRenderer fontRenderer, final String s, final float n, final float n2, final int n3, final boolean b) {
        if (LuigiHack.moduleManager != null && (boolean)HUD.getInstance().shadow.getValue() && b) {
            return this.renderString(s, n - 0.5f, n2 - 0.5f, n3, true);
        }
        return this.renderString(s, n, n2, n3, b);
    }
    
    @Redirect(method = { "renderString(Ljava/lang/String;FFIZ)I" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/FontRenderer;renderStringAtPos(Ljava/lang/String;Z)V"))
    public void renderStringAtPosHook(final FontRenderer fontRenderer, final String s, final boolean b) {
        if (Media.getInstance().isOn()) {
            this.renderStringAtPos(s.replace(Minecraft.getMinecraft().getSession().getUsername(), Media.getInstance().ownName.getValueAsString()), b);
        }
        else {
            this.renderStringAtPos(s, b);
        }
    }
}
