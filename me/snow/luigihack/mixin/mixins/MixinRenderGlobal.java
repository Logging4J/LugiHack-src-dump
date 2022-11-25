//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.renderer.*;
import me.snow.luigihack.impl.modules.movement.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.tileentity.*;
import net.minecraft.util.math.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraftforge.common.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraftforge.fml.common.eventhandler.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ RenderGlobal.class })
public abstract class MixinRenderGlobal
{
    @Redirect(method = { "setupTerrain" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/ChunkRenderContainer;initialize(DDD)V"))
    public void initializeHook(final ChunkRenderContainer chunkRenderContainer, final double n, final double n2, final double n3) {
        double startY = n2;
        if (GroundSpeed.getInstance().isOn() && (boolean)GroundSpeed.getInstance().noShake.getValue() && GroundSpeed.getInstance().mode.getValue() != GroundSpeed.Mode.INSTANT && GroundSpeed.getInstance().antiShake) {
            startY = GroundSpeed.getInstance().startY;
        }
        chunkRenderContainer.initialize(n, startY, n3);
    }
    
    @Redirect(method = { "renderEntities" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/RenderManager;setRenderPosition(DDD)V"))
    public void setRenderPositionHook(final RenderManager renderManager, final double n, final double n2, final double n3) {
        double startY = n2;
        if (GroundSpeed.getInstance().isOn() && (boolean)GroundSpeed.getInstance().noShake.getValue() && GroundSpeed.getInstance().mode.getValue() != GroundSpeed.Mode.INSTANT && GroundSpeed.getInstance().antiShake) {
            startY = GroundSpeed.getInstance().startY;
        }
        renderManager.setRenderPosition(n, TileEntityRendererDispatcher.staticPlayerY = startY, n3);
    }
    
    @Redirect(method = { "drawSelectionBox" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/AxisAlignedBB;offset(DDD)Lnet/minecraft/util/math/AxisAlignedBB;"))
    public AxisAlignedBB offsetHook(final AxisAlignedBB axisAlignedBB, final double n, final double n2, final double n3) {
        if (GroundSpeed.getInstance().isOn() && (boolean)GroundSpeed.getInstance().noShake.getValue() && GroundSpeed.getInstance().mode.getValue() != GroundSpeed.Mode.INSTANT) {
            GroundSpeed.getInstance();
        }
        return axisAlignedBB.offset(n, n2, n3);
    }
    
    @Inject(method = { "sendBlockBreakProgress" }, at = { @At("HEAD") })
    public void sendBlockBreakProgress(final int n, final BlockPos blockPos, final int n2, final CallbackInfo callbackInfo) {
        MinecraftForge.EVENT_BUS.post((Event)new BlockBreakingEvent(blockPos, n, n2));
    }
}
