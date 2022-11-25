//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.manager;

import me.snow.luigihack.impl.*;
import me.snow.luigihack.impl.command.*;
import me.snow.luigihack.impl.command.commands.*;
import java.util.*;

public class CommandManager extends Feature
{
    private /* synthetic */ String clientMessage;
    private final /* synthetic */ ArrayList<Command> commands;
    private /* synthetic */ String prefix;
    
    public void executeCommand(final String s) {
        final String[] split = s.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        final String substring = split[0].substring(1);
        final String[] removeElement = removeElement(split, 0);
        for (int i = 0; i < removeElement.length; ++i) {
            if (removeElement[i] != null) {
                removeElement[i] = strip(removeElement[i]);
            }
        }
        for (final Command command : this.commands) {
            if (!command.getName().equalsIgnoreCase(substring)) {
                continue;
            }
            command.execute(split);
            return;
        }
        Command.sendMessage("Unknown command. try 'help' for a list of commands.");
    }
    
    public String getPrefix() {
        return this.prefix;
    }
    
    public CommandManager() {
        super("Command");
        this.commands = new ArrayList<Command>();
        this.clientMessage = "<LuigiHack.eu>";
        this.prefix = ".";
        this.commands.add(new BindCommand());
        this.commands.add(new ModuleCommand());
        this.commands.add(new OpenFolderCommand());
        this.commands.add(new PrefixCommand());
        this.commands.add(new ConfigCommand());
        this.commands.add(new CoordsCommand());
        this.commands.add(new FriendCommand());
        this.commands.add(new HelpCommand());
        this.commands.add(new ReloadCommand());
        this.commands.add(new UnloadCommand());
        this.commands.add(new ReloadSoundCommand());
        this.commands.add(new PeekCommand());
        this.commands.add(new BookCommand());
        this.commands.add(new ClearRamCommand());
        this.commands.add(new CrashCommand());
        this.commands.add(new HistoryCommand());
        this.commands.add(new BaritoneNoStop());
        this.commands.add(new QueueCommand());
    }
    
    public void setClientMessage(final String clientMessage) {
        this.clientMessage = clientMessage;
    }
    
    private static String strip(final String s) {
        if (s.startsWith("\"") && s.endsWith("\"")) {
            return s.substring("\"".length(), s.length() - "\"".length());
        }
        return s;
    }
    
    public void setPrefix(final String prefix) {
        this.prefix = prefix;
    }
    
    public Command getCommandByName(final String anObject) {
        for (final Command command : this.commands) {
            if (!command.getName().equals(anObject)) {
                continue;
            }
            return command;
        }
        return null;
    }
    
    public static String[] removeElement(final String[] a, final int n) {
        final LinkedList<String> list = new LinkedList<String>();
        for (int i = 0; i < a.length; ++i) {
            if (i != n) {
                list.add(a[i]);
            }
        }
        return list.toArray(a);
    }
    
    public ArrayList<Command> getCommands() {
        return this.commands;
    }
    
    public String getClientMessage() {
        return this.clientMessage;
    }
}
