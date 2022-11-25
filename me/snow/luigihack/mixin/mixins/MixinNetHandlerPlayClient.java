//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.network.*;
import net.minecraft.network.play.server.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.entity.player.*;
import net.minecraftforge.common.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.snow.luigihack.*;
import net.minecraft.entity.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ NetHandlerPlayClient.class })
public class MixinNetHandlerPlayClient
{
    @Inject(method = { "handleEntityMetadata" }, at = { @At("RETURN") }, cancellable = true)
    private void handleEntityMetadataHook(final SPacketEntityMetadata sPacketEntityMetadata, final CallbackInfo callbackInfo) {
        final Entity getEntityByID;
        final EntityPlayer entityPlayer;
        if (Util.mc.world != null && (getEntityByID = Util.mc.world.getEntityByID(sPacketEntityMetadata.getEntityId())) instanceof EntityPlayer && (entityPlayer = (EntityPlayer)getEntityByID).getHealth() <= 0.0f) {
            MinecraftForge.EVENT_BUS.post((Event)new DeathEvent(entityPlayer));
            if (LuigiHack.totemPopManager != null) {
                LuigiHack.totemPopManager.onDeath(entityPlayer);
            }
        }
    }
}
