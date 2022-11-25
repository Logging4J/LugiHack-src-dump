//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.mixin.mixins;

import net.minecraft.entity.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.culling.*;
import net.minecraft.util.math.*;
import org.spongepowered.asm.mixin.*;

@Mixin({ Render.class })
public class MixinRender<T extends Entity>
{
    @Overwrite
    public boolean shouldRender(final T t, final ICamera camera, final double n, final double n2, final double n3) {
        try {
            AxisAlignedBB grow = t.getRenderBoundingBox().grow(0.5);
            if ((grow.hasNaN() || grow.getAverageEdgeLength() == 0.0) && t != null) {
                grow = new AxisAlignedBB(t.posX - 2.0, t.posY - 2.0, t.posZ - 2.0, t.posX + 2.0, t.posY + 2.0, t.posZ + 2.0);
            }
            return t.isInRangeToRender3d(n, n2, n3) && (t.ignoreFrustumCheck || camera.isBoundingBoxInFrustum(grow));
        }
        catch (Exception ex) {
            return false;
        }
    }
}
