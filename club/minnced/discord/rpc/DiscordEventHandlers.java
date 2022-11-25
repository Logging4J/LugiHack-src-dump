//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package club.minnced.discord.rpc;

import java.util.*;
import com.sun.jna.*;

public class DiscordEventHandlers extends Structure
{
    private static final List<String> FIELD_ORDER;
    public OnReady ready;
    public OnStatus disconnected;
    public OnStatus errored;
    public OnGameUpdate joinGame;
    public OnGameUpdate spectateGame;
    public OnJoinRequest joinRequest;
    
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DiscordEventHandlers)) {
            return false;
        }
        final DiscordEventHandlers discordEventHandlers = (DiscordEventHandlers)o;
        return Objects.equals(this.ready, discordEventHandlers.ready) && Objects.equals(this.disconnected, discordEventHandlers.disconnected) && Objects.equals(this.errored, discordEventHandlers.errored) && Objects.equals(this.joinGame, discordEventHandlers.joinGame) && Objects.equals(this.spectateGame, discordEventHandlers.spectateGame) && Objects.equals(this.joinRequest, discordEventHandlers.joinRequest);
    }
    
    public int hashCode() {
        return Objects.hash(this.ready, this.disconnected, this.errored, this.joinGame, this.spectateGame, this.joinRequest);
    }
    
    protected List<String> getFieldOrder() {
        return DiscordEventHandlers.FIELD_ORDER;
    }
    
    static {
        FIELD_ORDER = Collections.unmodifiableList((List<? extends String>)Arrays.asList("ready", "disconnected", "errored", "joinGame", "spectateGame", "joinRequest"));
    }
    
    public interface OnJoinRequest extends Callback
    {
        void accept(final DiscordUser p0);
    }
    
    public interface OnGameUpdate extends Callback
    {
        void accept(final String p0);
    }
    
    public interface OnStatus extends Callback
    {
        void accept(final int p0, final String p1);
    }
    
    public interface OnReady extends Callback
    {
        void accept(final DiscordUser p0);
    }
}
