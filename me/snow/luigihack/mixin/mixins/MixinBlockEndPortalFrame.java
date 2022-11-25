//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.mixin.mixins;

import net.minecraft.block.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.block.state.*;
import net.minecraft.world.*;
import net.minecraft.util.math.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.snow.luigihack.impl.modules.movement.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ BlockEndPortalFrame.class })
public class MixinBlockEndPortalFrame
{
    @Shadow
    @Final
    protected static AxisAlignedBB AABB_BLOCK;
    
    @Inject(method = { "getBoundingBox" }, at = { @At("HEAD") }, cancellable = true)
    public void getBoundingBox(final IBlockState blockState, final IBlockAccess blockAccess, final BlockPos blockPos, final CallbackInfoReturnable<AxisAlignedBB> callbackInfoReturnable) {
        if (NoSlowDown.getInstance().isOn() && (boolean)NoSlowDown.getInstance().endPortal.getValue()) {
            callbackInfoReturnable.setReturnValue((Object)new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0));
        }
    }
}
