//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.manager;

import me.snow.luigihack.impl.*;
import net.minecraft.entity.player.*;
import me.snow.luigihack.impl.modules.client.*;
import me.snow.luigihack.*;
import com.mojang.realmsclient.gui.*;
import me.snow.luigihack.impl.command.*;
import java.util.concurrent.*;
import java.util.*;
import java.util.function.*;

public class TotemPopManager extends Feature
{
    private /* synthetic */ Notifications notifications;
    private /* synthetic */ Map<EntityPlayer, Integer> poplist;
    private final /* synthetic */ Set<EntityPlayer> toAnnounce;
    
    public String pop(final EntityPlayer entityPlayer) {
        if (this.getTotemPops(entityPlayer) == 1) {
            if (!ModuleTools.getInstance().isEnabled()) {
                return String.valueOf(new StringBuilder().append(LuigiHack.commandManager.getClientMessage()).append(ChatFormatting.WHITE).append(entityPlayer.getName()).append(" popped ").append(ChatFormatting.GREEN).append(this.getTotemPops(entityPlayer)).append(ChatFormatting.WHITE).append(" Totem."));
            }
            switch (ModuleTools.getInstance().popNotifier.getValue()) {
                case FUTURE: {
                    return String.valueOf(new StringBuilder().append(ChatFormatting.RED).append("[Future] ").append(ChatFormatting.GREEN).append(entityPlayer.getName()).append(ChatFormatting.GRAY).append(" just popped ").append(ChatFormatting.GREEN).append(this.getTotemPops(entityPlayer)).append(ChatFormatting.GRAY).append(" totem."));
                }
                case PHOBOS: {
                    return String.valueOf(new StringBuilder().append(ChatFormatting.GOLD).append(entityPlayer.getName()).append(ChatFormatting.RED).append(" popped ").append(ChatFormatting.GOLD).append(this.getTotemPops(entityPlayer)).append(ChatFormatting.RED).append(" totem."));
                }
                case DOTGOD: {
                    return String.valueOf(new StringBuilder().append(ChatFormatting.DARK_PURPLE).append("[").append(ChatFormatting.LIGHT_PURPLE).append("DotGod.CC").append(ChatFormatting.DARK_PURPLE).append("] ").append(ChatFormatting.LIGHT_PURPLE).append(entityPlayer.getName()).append(" has popped ").append(ChatFormatting.RED).append(this.getTotemPops(entityPlayer)).append(ChatFormatting.LIGHT_PURPLE).append(" time in total!"));
                }
                case NONE: {
                    return String.valueOf(new StringBuilder().append(LuigiHack.commandManager.getClientMessage()).append(ChatFormatting.WHITE).append(entityPlayer.getName()).append(" popped ").append(ChatFormatting.GREEN).append(this.getTotemPops(entityPlayer)).append(ChatFormatting.WHITE).append(" Totem."));
                }
            }
        }
        else {
            if (!ModuleTools.getInstance().isEnabled()) {
                return String.valueOf(new StringBuilder().append(LuigiHack.commandManager.getClientMessage()).append(ChatFormatting.WHITE).append(entityPlayer.getName()).append(" popped ").append(ChatFormatting.GREEN).append(this.getTotemPops(entityPlayer)).append(ChatFormatting.WHITE).append(" Totems."));
            }
            switch (ModuleTools.getInstance().popNotifier.getValue()) {
                case FUTURE: {
                    return String.valueOf(new StringBuilder().append(ChatFormatting.RED).append("[Future] ").append(ChatFormatting.GREEN).append(entityPlayer.getName()).append(ChatFormatting.GRAY).append(" just popped ").append(ChatFormatting.GREEN).append(this.getTotemPops(entityPlayer)).append(ChatFormatting.GRAY).append(" totems."));
                }
                case PHOBOS: {
                    return String.valueOf(new StringBuilder().append(ChatFormatting.GOLD).append(entityPlayer.getName()).append(ChatFormatting.RED).append(" popped ").append(ChatFormatting.GOLD).append(this.getTotemPops(entityPlayer)).append(ChatFormatting.RED).append(" totems."));
                }
                case DOTGOD: {
                    return String.valueOf(new StringBuilder().append(ChatFormatting.DARK_PURPLE).append("[").append(ChatFormatting.LIGHT_PURPLE).append("DotGod.CC").append(ChatFormatting.DARK_PURPLE).append("] ").append(ChatFormatting.LIGHT_PURPLE).append(entityPlayer.getName()).append(" has popped ").append(ChatFormatting.RED).append(this.getTotemPops(entityPlayer)).append(ChatFormatting.LIGHT_PURPLE).append(" times in total!"));
                }
                case NONE: {
                    return String.valueOf(new StringBuilder().append(ChatFormatting.WHITE).append(entityPlayer.getName()).append(" popped ").append(ChatFormatting.GREEN).append(this.getTotemPops(entityPlayer)).append(ChatFormatting.WHITE).append(" Totems."));
                }
            }
        }
        return "";
    }
    
    public void resetPops(final EntityPlayer entityPlayer) {
        this.setTotemPops(entityPlayer, 0);
    }
    
    public String getTotemPopString(final EntityPlayer entityPlayer) {
        return String.valueOf(new StringBuilder().append("§f").append((this.getTotemPops(entityPlayer) <= 0) ? "" : String.valueOf(new StringBuilder().append("-").append(this.getTotemPops(entityPlayer)).append(" "))));
    }
    
    public void onDeath(final EntityPlayer entityPlayer) {
        if (this.getTotemPops(entityPlayer) != 0 && !entityPlayer.equals((Object)TotemPopManager.mc.player) && this.notifications.isOn() && this.notifications.totemPops.getValue()) {
            int n = 0;
            final char[] charArray = entityPlayer.getName().toCharArray();
            for (int length = charArray.length, i = 0; i < length; ++i) {
                n = (n + charArray[i]) * 10;
            }
            Command.sendOverwriteMessage(this.death1(entityPlayer), n, this.notifications.totemNoti.getValue());
            this.toAnnounce.remove(entityPlayer);
        }
        this.resetPops(entityPlayer);
    }
    
    public void onOwnLogout(final boolean b) {
        if (b) {
            this.clearList();
        }
    }
    
    public void onUpdate() {
        if (this.notifications.totemAnnounce.passedMs(this.notifications.delay.getValue()) && this.notifications.isOn() && this.notifications.totemPops.getValue()) {
            for (final EntityPlayer entityPlayer : this.toAnnounce) {
                if (entityPlayer == null) {
                    continue;
                }
                int n = 0;
                final char[] charArray = entityPlayer.getName().toCharArray();
                for (int length = charArray.length, i = 0; i < length; ++i) {
                    n = (n + charArray[i]) * 10;
                }
                Command.sendOverwriteMessage(this.pop(entityPlayer), n, this.notifications.totemNoti.getValue());
                this.toAnnounce.remove(entityPlayer);
                this.notifications.totemAnnounce.reset();
                break;
            }
        }
    }
    
    public int getTotemPops(final EntityPlayer entityPlayer) {
        final Integer n = this.poplist.get(entityPlayer);
        if (n == null) {
            return 0;
        }
        return n;
    }
    
    public void onLogout() {
        this.onOwnLogout(this.notifications.clearOnLogout.getValue());
    }
    
    public TotemPopManager() {
        this.poplist = new ConcurrentHashMap<EntityPlayer, Integer>();
        this.toAnnounce = new HashSet<EntityPlayer>();
    }
    
    public void onTotemPop(final EntityPlayer entityPlayer) {
        this.popTotem(entityPlayer);
        if (!entityPlayer.equals((Object)TotemPopManager.mc.player)) {
            this.toAnnounce.add(entityPlayer);
            this.notifications.totemAnnounce.reset();
        }
    }
    
    public void setTotemPops(final EntityPlayer entityPlayer, final int i) {
        this.poplist.put(entityPlayer, i);
    }
    
    public void onLogout(final EntityPlayer entityPlayer, final boolean b) {
        if (b) {
            this.resetPops(entityPlayer);
        }
    }
    
    public String death1(final EntityPlayer entityPlayer) {
        if (this.getTotemPops(entityPlayer) == 1) {
            if (!ModuleTools.getInstance().isEnabled()) {
                return String.valueOf(new StringBuilder().append(LuigiHack.commandManager.getClientMessage()).append(ChatFormatting.WHITE).append(entityPlayer.getName()).append(" died after popping ").append(ChatFormatting.GREEN).append(this.getTotemPops(entityPlayer)).append(ChatFormatting.WHITE).append(" Totem!"));
            }
            switch (ModuleTools.getInstance().popNotifier.getValue()) {
                case FUTURE: {
                    return String.valueOf(new StringBuilder().append(ChatFormatting.RED).append("[Future] ").append(ChatFormatting.GREEN).append(entityPlayer.getName()).append(ChatFormatting.GRAY).append(" died after popping ").append(ChatFormatting.GREEN).append(this.getTotemPops(entityPlayer)).append(ChatFormatting.GRAY).append(" totem."));
                }
                case PHOBOS: {
                    return String.valueOf(new StringBuilder().append(ChatFormatting.GOLD).append(entityPlayer.getName()).append(ChatFormatting.RED).append(" died after popping ").append(ChatFormatting.GOLD).append(this.getTotemPops(entityPlayer)).append(ChatFormatting.RED).append(" totem."));
                }
                case DOTGOD: {
                    return String.valueOf(new StringBuilder().append(ChatFormatting.DARK_PURPLE).append("[").append(ChatFormatting.LIGHT_PURPLE).append("DotGod.CC").append(ChatFormatting.DARK_PURPLE).append("] ").append(ChatFormatting.LIGHT_PURPLE).append(entityPlayer.getName()).append(" died after popping ").append(ChatFormatting.GREEN).append(this.getTotemPops(entityPlayer)).append(ChatFormatting.LIGHT_PURPLE).append(" time!"));
                }
                case NONE: {
                    return String.valueOf(new StringBuilder().append(LuigiHack.commandManager.getClientMessage()).append(ChatFormatting.WHITE).append(entityPlayer.getName()).append(" died after popping ").append(ChatFormatting.GREEN).append(this.getTotemPops(entityPlayer)).append(ChatFormatting.WHITE).append(" Totem!"));
                }
            }
        }
        else {
            if (!ModuleTools.getInstance().isEnabled()) {
                return String.valueOf(new StringBuilder().append(LuigiHack.commandManager.getClientMessage()).append(ChatFormatting.WHITE).append(entityPlayer.getName()).append(" died after popping ").append(ChatFormatting.GREEN).append(this.getTotemPops(entityPlayer)).append(ChatFormatting.WHITE).append(" Totems!"));
            }
            switch (ModuleTools.getInstance().popNotifier.getValue()) {
                case FUTURE: {
                    return String.valueOf(new StringBuilder().append(ChatFormatting.RED).append("[Future] ").append(ChatFormatting.GREEN).append(entityPlayer.getName()).append(ChatFormatting.GRAY).append(" died after popping ").append(ChatFormatting.GREEN).append(this.getTotemPops(entityPlayer)).append(ChatFormatting.GRAY).append(" totems."));
                }
                case PHOBOS: {
                    return String.valueOf(new StringBuilder().append(ChatFormatting.GOLD).append(entityPlayer.getName()).append(ChatFormatting.RED).append(" died after popping ").append(ChatFormatting.GOLD).append(this.getTotemPops(entityPlayer)).append(ChatFormatting.RED).append(" totems."));
                }
                case DOTGOD: {
                    return String.valueOf(new StringBuilder().append(ChatFormatting.DARK_PURPLE).append("[").append(ChatFormatting.LIGHT_PURPLE).append("DotGod.CC").append(ChatFormatting.DARK_PURPLE).append("] ").append(ChatFormatting.LIGHT_PURPLE).append(entityPlayer.getName()).append(" died after popping ").append(ChatFormatting.GREEN).append(this.getTotemPops(entityPlayer)).append(ChatFormatting.LIGHT_PURPLE).append(" times!"));
                }
                case NONE: {
                    return String.valueOf(new StringBuilder().append(LuigiHack.commandManager.getClientMessage()).append(ChatFormatting.WHITE).append(entityPlayer.getName()).append(" died after popping ").append(ChatFormatting.GREEN).append(this.getTotemPops(entityPlayer)).append(ChatFormatting.WHITE).append(" Totems!"));
                }
            }
        }
        return null;
    }
    
    public void clearList() {
        this.poplist = new ConcurrentHashMap<EntityPlayer, Integer>();
    }
    
    public void popTotem(final EntityPlayer key) {
        this.poplist.merge(key, 1, Integer::sum);
    }
    
    public void init() {
        this.notifications = (Notifications)LuigiHack.moduleManager.getModuleByClass((Class)Notifications.class);
    }
}
