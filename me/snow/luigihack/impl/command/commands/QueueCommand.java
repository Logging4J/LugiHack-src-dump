//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.command.commands;

import me.snow.luigihack.impl.command.*;
import com.google.gson.*;
import java.io.*;
import java.net.*;

public class QueueCommand extends Command
{
    public QueueCommand() {
        super("queue", new String[] { "priority, regular" });
    }
    
    public void execute(final String[] array) {
        if (array.length == 1 || array.length == 0) {
            sendMessage("ayo, specify the type! (priority/regular)");
            return;
        }
        final String s = "https://2bqueue.info/*";
        final String s2 = array[0];
        if (s2.equalsIgnoreCase("regular")) {
            try {
                final URLConnection openConnection = new URL(s).openConnection();
                openConnection.connect();
                sendMessage(String.valueOf(new StringBuilder().append("Regular queue currently have: ").append(new JsonParser().parse((Reader)new InputStreamReader((InputStream)openConnection.getContent())).getAsJsonObject().get("regular").getAsString())));
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else if (s2.equalsIgnoreCase("priority")) {
            try {
                final URLConnection openConnection2 = new URL(s).openConnection();
                openConnection2.connect();
                sendMessage(String.valueOf(new StringBuilder().append("Priority queue currently have: ").append(new JsonParser().parse((Reader)new InputStreamReader((InputStream)openConnection2.getContent())).getAsJsonObject().get("prio").getAsString())));
            }
            catch (IOException ex2) {
                ex2.printStackTrace();
            }
        }
    }
}
