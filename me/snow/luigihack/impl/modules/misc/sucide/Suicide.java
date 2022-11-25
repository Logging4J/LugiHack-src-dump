//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.misc.sucide;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.impl.modules.misc.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import me.snow.luigihack.impl.command.*;

public class Suicide extends Module
{
    private final /* synthetic */ Setting<SuicideMode> mode;
    
    @Override
    public void onTick() {
        if (SpammerModule.fullNullCheck()) {
            return;
        }
        if (this.mode.getValue() == SuicideMode.Suicide) {
            SpammerModule.mc.player.connection.sendPacket((Packet)new CPacketChatMessage("/kill"));
        }
        if (this.mode.getValue() == SuicideMode.None) {
            Command.sendMessage("<Suicide> If you want to kill yourself change SuicideMode. Don't kill yourself by mistake ");
        }
        this.disable();
    }
    
    public Suicide() {
        super("Suicide", "Message", Category.MISC, true, false, false);
        this.mode = (Setting<SuicideMode>)this.register(new Setting("SuicideMode", (Object)SuicideMode.None));
    }
}
