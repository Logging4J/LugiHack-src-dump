//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.entity.*;
import net.minecraft.client.*;
import net.minecraft.world.*;
import net.minecraft.client.network.*;
import net.minecraft.stats.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.snow.luigihack.api.util.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.util.math.*;
import me.snow.luigihack.impl.modules.movement.*;
import me.snow.luigihack.*;
import me.snow.luigihack.impl.modules.misc.*;
import net.minecraft.entity.*;
import me.snow.luigihack.api.event.events.*;

@Mixin(value = { EntityPlayerSP.class }, priority = 9998)
public abstract class MixinEntityPlayerSP extends AbstractClientPlayer
{
    public MixinEntityPlayerSP(final Minecraft minecraft, final World world, final NetHandlerPlayClient netHandlerPlayClient, final StatisticsManager statisticsManager, final RecipeBook recipeBook) {
        super(world, netHandlerPlayClient.getGameProfile());
    }
    
    @Inject(method = { "sendChatMessage" }, at = { @At("HEAD") }, cancellable = true)
    public void sendChatMessage(final String s, final CallbackInfo callbackInfo) {
        MinecraftForge.EVENT_BUS.post((Event)new ChatEvent(s));
    }
    
    @Redirect(method = { "onLivingUpdate" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;setSprinting(Z)V", ordinal = 2))
    public void onLivingUpdate(final EntityPlayerSP entityPlayerSP, final boolean b) {
        if (Sprint.getInstance().isOn() && Sprint.getInstance().mode.getValue() == Sprint.Mode.RAGE && (Util.mc.player.movementInput.moveForward != 0.0f || Util.mc.player.movementInput.moveStrafe != 0.0f)) {
            entityPlayerSP.setSprinting(true);
        }
        else {
            entityPlayerSP.setSprinting(b);
        }
    }
    
    @Inject(method = { "pushOutOfBlocks" }, at = { @At("HEAD") }, cancellable = true)
    private void pushOutOfBlocksHook(final double n, final double n2, final double n3, final CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        final PushEvent pushEvent = new PushEvent(1);
        MinecraftForge.EVENT_BUS.post((Event)pushEvent);
        if (pushEvent.isCanceled()) {
            callbackInfoReturnable.setReturnValue((Object)false);
        }
    }
    
    @Inject(method = { "onUpdateWalkingPlayer" }, at = { @At("HEAD") }, cancellable = true)
    private void preMotion(final CallbackInfo callbackInfo) {
        final UpdateWalkingPlayerEvent updateWalkingPlayerEvent = new UpdateWalkingPlayerEvent(0);
        MinecraftForge.EVENT_BUS.post((Event)updateWalkingPlayerEvent);
        if (updateWalkingPlayerEvent.isCanceled()) {
            callbackInfo.cancel();
        }
    }
    
    @Redirect(method = { "onUpdateWalkingPlayer" }, at = @At(value = "FIELD", target = "net/minecraft/util/math/AxisAlignedBB.minY:D"))
    private double minYHook(final AxisAlignedBB axisAlignedBB) {
        if (GroundSpeed.getInstance().isOn() && GroundSpeed.getInstance().mode.getValue() == GroundSpeed.Mode.VANILLA && GroundSpeed.getInstance().changeY) {
            GroundSpeed.getInstance().changeY = false;
            return GroundSpeed.getInstance().minY;
        }
        return axisAlignedBB.minY;
    }
    
    @Inject(method = { "onUpdateWalkingPlayer" }, at = { @At("RETURN") })
    private void postMotion(final CallbackInfo callbackInfo) {
        MinecraftForge.EVENT_BUS.post((Event)new UpdateWalkingPlayerEvent(1));
    }
    
    @Inject(method = { "Lnet/minecraft/client/entity/EntityPlayerSP;setServerBrand(Ljava/lang/String;)V" }, at = { @At("HEAD") })
    public void getBrand(final String serverBrand, final CallbackInfo callbackInfo) {
        if (LuigiHack.serverManager != null) {
            LuigiHack.serverManager.setServerBrand(serverBrand);
        }
    }
    
    @Redirect(method = { "onLivingUpdate" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;closeScreen()V"))
    public void closeScreenHook(final EntityPlayerSP entityPlayerSP) {
        if (!Portals.getInstance().isOn() || !(boolean)Portals.getInstance().portalChat.getValue()) {
            entityPlayerSP.closeScreen();
        }
    }
    
    @Redirect(method = { "move" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/AbstractClientPlayer;move(Lnet/minecraft/entity/MoverType;DDD)V"))
    public void move(final AbstractClientPlayer abstractClientPlayer, final MoverType moverType, final double n, final double n2, final double n3) {
        final MoveEvent moveEvent = new MoveEvent(0, moverType, n, n2, n3);
        MinecraftForge.EVENT_BUS.post((Event)moveEvent);
        if (!moveEvent.isCanceled()) {
            super.move(moveEvent.getType(), moveEvent.getX(), moveEvent.getY(), moveEvent.getZ());
        }
    }
}
