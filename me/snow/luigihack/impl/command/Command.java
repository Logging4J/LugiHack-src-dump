//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.command;

import me.snow.luigihack.impl.*;
import me.snow.luigihack.*;
import net.minecraft.util.text.*;
import java.util.regex.*;

public abstract class Command extends Feature
{
    protected /* synthetic */ String[] commands;
    protected /* synthetic */ String name;
    
    public static void sendOverwriteMessage(final String s, final int n, final boolean b) {
        Command.mc.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion((ITextComponent)new TextComponentString(s), n);
        if (b) {
            LuigiHack.notificationManager.addNotification(s, 3000L);
        }
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    public static void sendSilentMessage(final String s, final boolean b) {
        if (nullCheck()) {
            return;
        }
        Command.mc.player.sendMessage((ITextComponent)new ChatMessage(s));
        if (b) {
            LuigiHack.notificationManager.addNotification(s, 3000L);
        }
    }
    
    public Command(final String name, final String[] commands) {
        super(name);
        this.name = name;
        this.commands = commands;
    }
    
    public String[] getCommands() {
        return this.commands;
    }
    
    public static void sendRainbowMessage(final String str) {
        final StringBuilder obj = new StringBuilder(str);
        obj.insert(0, "§+");
        Command.mc.player.sendMessage((ITextComponent)new ChatMessage(String.valueOf(obj)));
    }
    
    public static void sendMessage(final String str, final boolean b) {
        sendSilentMessage(String.valueOf(new StringBuilder().append(LuigiHack.commandManager.getClientMessage()).append(" §r").append(str)));
        if (b) {
            LuigiHack.notificationManager.addNotification(str, 3000L);
        }
    }
    
    public static void sendMessage(final String str) {
        sendSilentMessage(String.valueOf(new StringBuilder().append(LuigiHack.commandManager.getClientMessage()).append(" §r").append(str)));
    }
    
    public abstract void execute(final String[] p0);
    
    public static String getCommandPrefix() {
        return LuigiHack.commandManager.getPrefix();
    }
    
    public Command(final String name) {
        super(name);
        this.name = name;
        this.commands = new String[] { "" };
    }
    
    public static void sendSilentMessage(final String s) {
        if (nullCheck()) {
            return;
        }
        Command.mc.player.sendMessage((ITextComponent)new ChatMessage(s));
    }
    
    public static class ChatMessage extends TextComponentBase
    {
        private final /* synthetic */ String text;
        
        public String getUnformattedComponentText() {
            return this.text;
        }
        
        public ITextComponent createCopy() {
            return (ITextComponent)new ChatMessage(this.text);
        }
        
        public ChatMessage(final String input) {
            final Matcher matcher = Pattern.compile("&[0123456789abcdefrlosmk]").matcher(input);
            final StringBuffer sb = new StringBuffer();
            while (matcher.find()) {
                matcher.appendReplacement(sb, String.valueOf(new StringBuilder().append("§").append(matcher.group().substring(1))));
            }
            matcher.appendTail(sb);
            this.text = sb.toString();
        }
    }
}
