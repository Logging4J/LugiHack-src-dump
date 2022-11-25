//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.mixin.mixins;

import java.util.*;
import org.spongepowered.asm.mixin.*;
import me.snow.luigihack.impl.modules.misc.*;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.client.gui.*;
import me.snow.luigihack.*;
import me.snow.luigihack.impl.modules.client.*;
import java.awt.*;
import net.minecraft.client.*;

@Mixin({ GuiNewChat.class })
public class MixinGuiNewChat extends Gui
{
    @Shadow
    @Final
    public List<ChatLine> drawnChatLines;
    private ChatLine chatLine;
    
    @Redirect(method = { "drawChat" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiNewChat;drawRect(IIIII)V"))
    private void drawRectHook(final int n, final int n2, final int n3, final int n4, final int n5) {
        Gui.drawRect(n, n2, n3, n4, (ChatModifier.getInstance().isOn() && (boolean)ChatModifier.getInstance().clean.getValue()) ? 0 : n5);
    }
    
    @Redirect(method = { "drawChat" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/FontRenderer;drawStringWithShadow(Ljava/lang/String;FFI)I"))
    private int drawStringWithShadow(final FontRenderer fontRenderer, final String s, final float n, final float n2, final int n3) {
        if (s.contains("§+")) {
            LuigiHack.textManager.drawRainbowString(s, n, n2, Color.HSBtoRGB(Colors.INSTANCE.hue, 1.0f, 1.0f), 100.0f, true);
        }
        else {
            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(s, n, n2, n3);
        }
        return 0;
    }
    
    @Redirect(method = { "setChatLine" }, at = @At(value = "INVOKE", target = "Ljava/util/List;size()I", ordinal = 0, remap = false))
    public int drawnChatLinesSize(final List<ChatLine> list) {
        return (ChatModifier.getInstance().isOn() && (boolean)ChatModifier.getInstance().infinite.getValue()) ? -2147483647 : list.size();
    }
    
    @Redirect(method = { "setChatLine" }, at = @At(value = "INVOKE", target = "Ljava/util/List;size()I", ordinal = 2, remap = false))
    public int chatLinesSize(final List<ChatLine> list) {
        return (ChatModifier.getInstance().isOn() && (boolean)ChatModifier.getInstance().infinite.getValue()) ? -2147483647 : list.size();
    }
}
