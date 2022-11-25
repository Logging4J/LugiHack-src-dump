//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.misc;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.client.network.*;
import net.minecraft.scoreboard.*;
import me.snow.luigihack.*;

public class ExtraTab extends Module
{
    public /* synthetic */ Setting<Integer> size;
    public /* synthetic */ Setting<Boolean> frienddd;
    private static /* synthetic */ ExtraTab INSTANCE;
    
    static {
        ExtraTab.INSTANCE = new ExtraTab();
    }
    
    public ExtraTab() {
        super("ExtraTab", "Extends Tab.", Category.MISC, false, false, false);
        this.size = (Setting<Integer>)this.register(new Setting("Size", (Object)250, (Object)1, (Object)1000));
        this.frienddd = (Setting<Boolean>)this.register(new Setting("TabFriends", (Object)true));
        this.setInstance();
    }
    
    public static String getPlayerName(final NetworkPlayerInfo networkPlayerInfo) {
        String formatPlayerName = null;
        if (networkPlayerInfo.getDisplayName() != null) {
            networkPlayerInfo.getDisplayName().getFormattedText();
        }
        else {
            formatPlayerName = ScorePlayerTeam.formatPlayerName((Team)networkPlayerInfo.getPlayerTeam(), networkPlayerInfo.getGameProfile().getName());
        }
        final String s = formatPlayerName;
        if (LuigiHack.friendManager.isFriend(s)) {
            return String.format("%sb%s", "§", s);
        }
        return s;
    }
    
    private void setInstance() {
        ExtraTab.INSTANCE = this;
    }
    
    public static ExtraTab getINSTANCE() {
        if (ExtraTab.INSTANCE == null) {
            ExtraTab.INSTANCE = new ExtraTab();
        }
        return ExtraTab.INSTANCE;
    }
}
