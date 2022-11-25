//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.mixin.mixins.accessors;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.renderer.*;
import org.spongepowered.asm.mixin.gen.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.entity.*;

@Mixin({ EntityRenderer.class })
public interface IEntityRenderer
{
    @Invoker("orientCamera")
    void orientCam(final float p0);
    
    @Invoker("applyBobbing")
    void viewBob(final float p0);
    
    @Accessor("lightmapColors")
    int[] getLightmapColors();
    
    @Accessor("lightmapTexture")
    DynamicTexture getLightmapTexture();
    
    @Accessor("torchFlickerX")
    float getTorchFlickerX();
    
    @Accessor("bossColorModifier")
    float getBossColorModifier();
    
    @Accessor("bossColorModifierPrev")
    float getBossColorModifierPrev();
    
    @Invoker("getNightVisionBrightness")
    float invokeGetNightVisionBrightness(final EntityLivingBase p0, final float p1);
    
    @Invoker("setupCameraTransform")
    void invokeSetupCameraTransform(final float p0, final int p1);
    
    @Accessor("rendererUpdateCount")
    int getRendererUpdateCount();
    
    @Accessor("rainXCoords")
    float[] getRainXCoords();
    
    @Accessor("rainYCoords")
    float[] getRainYCoords();
}
