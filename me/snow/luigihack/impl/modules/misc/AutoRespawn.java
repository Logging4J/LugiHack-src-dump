//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.misc;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.gui.*;
import me.snow.luigihack.impl.command.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class AutoRespawn extends Module
{
    public /* synthetic */ Setting<Boolean> antiDeathScreen;
    public /* synthetic */ Setting<Boolean> deathCoords;
    public /* synthetic */ Setting<Boolean> respawn;
    
    public AutoRespawn() {
        super("AutoRespawn", "Respawns you when you die.", Category.MISC, true, false, false);
        this.antiDeathScreen = (Setting<Boolean>)this.register(new Setting("AntiDeathScreen", (Object)true));
        this.deathCoords = (Setting<Boolean>)this.register(new Setting("DeathCoords", (Object)false));
        this.respawn = (Setting<Boolean>)this.register(new Setting("Respawn", (Object)true));
    }
    
    @SubscribeEvent
    public void onDisplayDeathScreen(final GuiOpenEvent guiOpenEvent) {
        if (guiOpenEvent.getGui() instanceof GuiGameOver) {
            if ((boolean)this.deathCoords.getValue() && guiOpenEvent.getGui() instanceof GuiGameOver) {
                Command.sendMessage(String.format("You died at x %d y %d z %d", (int)AutoRespawn.mc.player.posX, (int)AutoRespawn.mc.player.posY, (int)AutoRespawn.mc.player.posZ));
            }
            if (((boolean)this.respawn.getValue() && AutoRespawn.mc.player.getHealth() <= 0.0f) || ((boolean)this.antiDeathScreen.getValue() && AutoRespawn.mc.player.getHealth() > 0.0f)) {
                guiOpenEvent.setCanceled(true);
                AutoRespawn.mc.player.respawnPlayer();
            }
        }
    }
}
