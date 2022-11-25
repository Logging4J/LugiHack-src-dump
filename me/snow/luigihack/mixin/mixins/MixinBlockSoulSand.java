//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.world.*;
import net.minecraft.util.math.*;
import net.minecraft.block.state.*;
import net.minecraft.entity.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.snow.luigihack.impl.modules.movement.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ BlockSoulSand.class })
public class MixinBlockSoulSand extends Block
{
    public MixinBlockSoulSand() {
        super(Material.SAND, MapColor.BROWN);
    }
    
    @Inject(method = { "onEntityCollision" }, at = { @At("HEAD") }, cancellable = true)
    public void onEntityCollisionHook(final World world, final BlockPos blockPos, final IBlockState blockState, final Entity entity, final CallbackInfo callbackInfo) {
        if (NoSlowDown.getInstance().isOn() && (boolean)NoSlowDown.getInstance().soulSand.getValue()) {
            callbackInfo.cancel();
        }
    }
}
