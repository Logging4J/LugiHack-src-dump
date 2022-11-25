//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.manager;

import java.util.*;
import me.snow.luigihack.impl.notifications.*;
import me.snow.luigihack.impl.modules.client.*;
import me.snow.luigihack.*;

public class NotificationManager
{
    private final /* synthetic */ ArrayList<Notification> notifications;
    
    public ArrayList<Notification> getNotifications() {
        return this.notifications;
    }
    
    public void handleNotifications(int n) {
        for (int i = 0; i < this.getNotifications().size(); ++i) {
            this.getNotifications().get(i).onDraw(n);
            n -= ((HUD)LuigiHack.moduleManager.getModuleByClass((Class)HUD.class)).renderer.getFontHeight() + 5;
        }
    }
    
    public NotificationManager() {
        this.notifications = new ArrayList<Notification>();
    }
    
    public void addNotification(final String s, final long n) {
        this.getNotifications().add(new Notification(s, n));
    }
}
