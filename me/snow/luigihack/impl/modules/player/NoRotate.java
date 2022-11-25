//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.player;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraft.network.play.server.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class NoRotate extends Module
{
    public NoRotate() {
        super("NoRotate", "No ROtate.", Module.Category.PLAYER, true, false, false);
    }
    
    @SubscribeEvent
    public void onPacketReceive(final PacketEvent.Receive receive) {
        if (nullCheck()) {
            return;
        }
        if (receive.getPacket() instanceof SPacketPlayerPosLook) {
            final SPacketPlayerPosLook sPacketPlayerPosLook = (SPacketPlayerPosLook)receive.getPacket();
            sPacketPlayerPosLook.yaw = NoRotate.mc.player.rotationYaw;
            sPacketPlayerPosLook.pitch = NoRotate.mc.player.rotationPitch;
        }
    }
}
