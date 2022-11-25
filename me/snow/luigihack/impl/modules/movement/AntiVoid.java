//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.movement;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.*;
import com.mojang.realmsclient.gui.*;
import me.snow.luigihack.impl.command.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.client.entity.*;

public class AntiVoid extends Module
{
    public /* synthetic */ Setting<Mode> mode;
    
    public void onTick() {
        if (!AntiVoid.mc.player.isSpectator() && !((PacketFly)LuigiHack.moduleManager.getModuleByClass((Class)PacketFly.class)).isEnabled() && AntiVoid.mc.player.posY < 1.0) {
            Command.sendMessage(String.valueOf(new StringBuilder().append("[AntiVoid] ").append(ChatFormatting.RED).append("Player ").append(ChatFormatting.GREEN).append(AntiVoid.mc.player.getName()).append(ChatFormatting.RED).append(" is in the void!")));
            switch ((Mode)this.mode.getValue()) {
                case Solid: {
                    AntiVoid.mc.player.motionY = 0.0;
                    break;
                }
                case Launch: {
                    AntiVoid.mc.player.motionY = 0.5;
                    break;
                }
                case Glide: {
                    if (AntiVoid.mc.player.motionY < 0.0) {
                        final EntityPlayerSP player = AntiVoid.mc.player;
                        player.motionY /= 3.0;
                        break;
                    }
                    break;
                }
                case Rubberband: {
                    AntiVoid.mc.player.setVelocity(0.0, 0.0, 0.0);
                    AntiVoid.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(AntiVoid.mc.player.posX, AntiVoid.mc.player.posY + 10.0, AntiVoid.mc.player.posZ, false));
                    break;
                }
            }
        }
    }
    
    public AntiVoid() {
        super("AntiVoid", "Glitches you up from void.", Module.Category.MOVEMENT, false, false, false);
        this.mode = (Setting<Mode>)this.register(new Setting("Mode", (Object)Mode.Solid));
    }
    
    public String getDisplayInfo() {
        return String.valueOf(new StringBuilder().append(this.mode.getValue()).append(""));
    }
    
    public void onDisable() {
        AntiVoid.mc.player.moveVertical = 0.0f;
    }
    
    public enum Mode
    {
        Glide, 
        Solid, 
        Rubberband, 
        Launch;
    }
}
