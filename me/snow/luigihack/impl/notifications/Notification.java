//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.notifications;

import me.snow.luigihack.impl.modules.client.*;
import me.snow.luigihack.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import me.snow.luigihack.api.util.*;

public class Notification
{
    private /* synthetic */ float width;
    private final /* synthetic */ Timer timer;
    private /* synthetic */ long disableTime;
    private /* synthetic */ String text;
    
    public Notification(final String text, final long disableTime) {
        this.timer = new Timer();
        this.text = text;
        this.disableTime = disableTime;
        this.width = (float)((HUD)LuigiHack.moduleManager.getModuleByClass((Class)HUD.class)).renderer.getStringWidth(text);
        this.timer.reset();
    }
    
    public void onDraw(final int n) {
        final ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        if (this.timer.passedMs(this.disableTime)) {
            LuigiHack.notificationManager.getNotifications().remove(this);
        }
        RenderUtil.drawRect(scaledResolution.getScaledWidth() - 4 - this.width, (float)n, (float)(scaledResolution.getScaledWidth() - 2), (float)(n + ((HUD)LuigiHack.moduleManager.getModuleByClass((Class)HUD.class)).renderer.getFontHeight() + 3), 1962934272);
        ((HUD)LuigiHack.moduleManager.getModuleByClass((Class)HUD.class)).renderer.drawString(this.text, scaledResolution.getScaledWidth() - this.width - 3.0f, (float)(n + 2), -1, true);
    }
}
