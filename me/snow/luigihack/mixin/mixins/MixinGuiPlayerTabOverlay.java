//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.gui.*;
import java.util.*;
import net.minecraft.client.network.*;
import me.snow.luigihack.impl.modules.misc.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ GuiPlayerTabOverlay.class })
public class MixinGuiPlayerTabOverlay
{
    @Redirect(method = { "renderPlayerlist" }, at = @At(value = "INVOKE", target = "Ljava/util/List;subList(II)Ljava/util/List;", remap = false))
    public List<NetworkPlayerInfo> subListHook(final List<NetworkPlayerInfo> list, final int n, final int n2) {
        return list.subList(n, ExtraTab.getINSTANCE().isEnabled() ? Math.min((int)ExtraTab.getINSTANCE().size.getValue(), list.size()) : n2);
    }
    
    @Inject(method = { "getPlayerName" }, at = { @At("HEAD") }, cancellable = true)
    public void getPlayerName(final NetworkPlayerInfo networkPlayerInfo, final CallbackInfoReturnable<String> callbackInfoReturnable) {
        if (ExtraTab.getINSTANCE().frienddd.getValue()) {
            callbackInfoReturnable.cancel();
            callbackInfoReturnable.setReturnValue((Object)ExtraTab.getPlayerName(networkPlayerInfo));
        }
    }
}
