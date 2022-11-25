//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.manager;

import me.snow.luigihack.impl.*;
import me.snow.luigihack.api.setting.*;
import java.util.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.entity.player.*;

public class FriendManager extends Feature
{
    private final /* synthetic */ Map<String, UUID> friends;
    
    public FriendManager() {
        super("Friends");
        this.friends = new HashMap<String, UUID>();
    }
    
    public void onLoad() {
        this.friends.clear();
        this.clearSettings();
    }
    
    public Map<String, UUID> getFriends() {
        return this.friends;
    }
    
    public boolean isFriend(final String s) {
        return this.friends.get(s) != null;
    }
    
    public void saveFriends() {
        this.clearSettings();
        for (final Map.Entry<String, UUID> entry : this.friends.entrySet()) {
            this.register(new Setting(entry.getValue().toString(), entry.getKey()));
        }
    }
    
    public void addFriend(final String s) {
        final Friend friendByName = this.getFriendByName(s);
        if (friendByName != null) {
            this.friends.put(friendByName.getUsername(), friendByName.getUuid());
        }
    }
    
    public void removeFriend(final String s) {
        this.friends.remove(s);
    }
    
    public Friend getFriendByName(final String s) {
        final UUID uuidFromName = PlayerUtil.getUUIDFromName(s);
        if (uuidFromName != null) {
            return new Friend(s, uuidFromName);
        }
        return null;
    }
    
    public void addFriend(final Friend friend) {
        this.friends.put(friend.getUsername(), friend.getUuid());
    }
    
    public boolean isFriend(final EntityPlayer entityPlayer) {
        return this.isFriend(entityPlayer.getName());
    }
    
    public static class Friend
    {
        private final /* synthetic */ UUID uuid;
        private final /* synthetic */ String username;
        
        public UUID getUuid() {
            return this.uuid;
        }
        
        public Friend(final String username, final UUID uuid) {
            this.username = username;
            this.uuid = uuid;
        }
        
        public String getUsername() {
            return this.username;
        }
        
        @Override
        public boolean equals(final Object o) {
            return o instanceof Friend && ((Friend)o).getUsername().equals(this.username) && ((Friend)o).getUuid().equals(this.uuid);
        }
        
        @Override
        public int hashCode() {
            return this.username.hashCode() + this.uuid.hashCode();
        }
    }
}
