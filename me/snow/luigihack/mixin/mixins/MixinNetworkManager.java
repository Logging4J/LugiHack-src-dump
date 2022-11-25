//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.network.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.eventhandler.*;
import org.spongepowered.asm.mixin.injection.*;
import io.netty.channel.*;
import me.snow.luigihack.impl.modules.player.*;

@Mixin({ NetworkManager.class })
public class MixinNetworkManager
{
    @Inject(method = { "sendPacket(Lnet/minecraft/network/Packet;)V" }, at = { @At("HEAD") }, cancellable = true)
    private void onSendPacketPre(final Packet<?> packet, final CallbackInfo callbackInfo) {
        final PacketEvent.Send send = new PacketEvent.Send(0, (Packet)packet);
        MinecraftForge.EVENT_BUS.post((Event)send);
        if (send.isCanceled()) {
            callbackInfo.cancel();
        }
    }
    
    @Inject(method = { "sendPacket(Lnet/minecraft/network/Packet;)V" }, at = { @At("RETURN") }, cancellable = true)
    private void onSendPacketPost(final Packet<?> packet, final CallbackInfo callbackInfo) {
        final PacketEvent.Send send = new PacketEvent.Send(1, (Packet)packet);
        MinecraftForge.EVENT_BUS.post((Event)send);
        if (send.isCanceled()) {
            callbackInfo.cancel();
        }
    }
    
    @Inject(method = { "channelRead0" }, at = { @At("HEAD") }, cancellable = true)
    private void onChannelReadPre(final ChannelHandlerContext channelHandlerContext, final Packet<?> packet, final CallbackInfo callbackInfo) {
        final PacketEvent.Receive receive = new PacketEvent.Receive(0, (Packet)packet);
        MinecraftForge.EVENT_BUS.post((Event)receive);
        if (receive.isCanceled()) {
            callbackInfo.cancel();
        }
    }
    
    @Inject(method = { "exceptionCaught" }, at = { @At("HEAD") }, cancellable = true)
    private void exceptionCaught(final ChannelHandlerContext channelHandlerContext, final Throwable t, final CallbackInfo callbackInfo) {
        if (NoPacketKick.getInstance().isEnabled()) {
            NoPacketKick.sendWarning(t);
            callbackInfo.cancel();
        }
    }
}
