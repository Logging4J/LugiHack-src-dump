//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util.gl;

import net.minecraft.client.*;
import net.minecraft.util.math.*;
import net.minecraft.client.gui.*;
import javax.vecmath.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;
import java.nio.*;

public class ProjectionUtil
{
    private static final /* synthetic */ Matrix4f modelMatrix;
    private static final /* synthetic */ Matrix4f projectionMatrix;
    private static final /* synthetic */ Minecraft mc;
    static /* synthetic */ Vec3d camPos;
    
    public static void updateMatrix() {
        if (ProjectionUtil.mc.getRenderViewEntity() == null) {
            return;
        }
        final Vec3d projectViewFromEntity = ActiveRenderInfo.projectViewFromEntity(ProjectionUtil.mc.getRenderViewEntity(), (double)ProjectionUtil.mc.getRenderPartialTicks());
        final Vec3d cameraPosition = ActiveRenderInfo.getCameraPosition();
        loadMatrix(ProjectionUtil.modelMatrix, 2982);
        loadMatrix(ProjectionUtil.projectionMatrix, 2983);
        ProjectionUtil.camPos = projectViewFromEntity.add(cameraPosition);
    }
    
    public static Vec3d toScaledScreenPos(final Vec3d vec3d) {
        final Vector4f transformedMatrix = getTransformedMatrix(vec3d);
        final ScaledResolution scaledResolution = new ScaledResolution(ProjectionUtil.mc);
        final int getScaledWidth = scaledResolution.getScaledWidth();
        final int getScaledHeight = scaledResolution.getScaledHeight();
        transformedMatrix.x = getScaledWidth / 2.0f + (0.5f * transformedMatrix.x * getScaledWidth + 0.5f);
        transformedMatrix.y = getScaledHeight / 2.0f - (0.5f * transformedMatrix.y * getScaledHeight + 0.5f);
        return new Vec3d((double)transformedMatrix.x, (double)transformedMatrix.y, isVisible(transformedMatrix, getScaledWidth, getScaledHeight) ? 0.0 : -1.0);
    }
    
    static {
        mc = Minecraft.getMinecraft();
        modelMatrix = new Matrix4f();
        projectionMatrix = new Matrix4f();
        ProjectionUtil.camPos = new Vec3d(0.0, 0.0, 0.0);
    }
    
    private static void loadMatrix(final Matrix4f matrix4f, final int n) {
        final FloatBuffer createDirectFloatBuffer = GLAllocation.createDirectFloatBuffer(16);
        GL11.glGetFloat(n, createDirectFloatBuffer);
        matrix4f.load(createDirectFloatBuffer);
    }
    
    private static boolean isVisible(final Vector4f vector4f, final int n, final int n2) {
        final double n3 = n;
        final double n4 = vector4f.x;
        if (n4 >= 0.0 && n4 <= n3) {
            final double n5 = n2;
            final double n6 = vector4f.y;
            return n6 >= 0.0 && n6 <= n5;
        }
        return false;
    }
    
    private static void transform(final Vector4f vector4f, final Matrix4f matrix4f) {
        final float x = vector4f.x;
        final float y = vector4f.y;
        final float z = vector4f.z;
        vector4f.x = x * matrix4f.m00 + y * matrix4f.m10 + z * matrix4f.m20 + matrix4f.m30;
        vector4f.y = x * matrix4f.m01 + y * matrix4f.m11 + z * matrix4f.m21 + matrix4f.m31;
        vector4f.z = x * matrix4f.m02 + y * matrix4f.m12 + z * matrix4f.m22 + matrix4f.m32;
        vector4f.w = x * matrix4f.m03 + y * matrix4f.m13 + z * matrix4f.m23 + matrix4f.m33;
    }
    
    private static Vector4f getTransformedMatrix(final Vec3d vec3d) {
        final Vec3d subtract = ProjectionUtil.camPos.subtract(vec3d);
        final Vector4f vector4f = new Vector4f((float)subtract.x, (float)subtract.y, (float)subtract.z, 1.0f);
        transform(vector4f, ProjectionUtil.modelMatrix);
        transform(vector4f, ProjectionUtil.projectionMatrix);
        if (vector4f.w > 0.0f) {
            final Vector4f vector4f2 = vector4f;
            vector4f2.x *= -100000.0f;
            final Vector4f vector4f3 = vector4f;
            vector4f3.y *= -100000.0f;
        }
        else {
            final float n = 1.0f / vector4f.w;
            final Vector4f vector4f4 = vector4f;
            vector4f4.x *= n;
            final Vector4f vector4f5 = vector4f;
            vector4f5.y *= n;
        }
        return vector4f;
    }
    
    public static Vec3d toScreenPos(final Vec3d vec3d) {
        final Vector4f transformedMatrix = getTransformedMatrix(vec3d);
        final int displayWidth = ProjectionUtil.mc.displayWidth;
        final int displayHeight = ProjectionUtil.mc.displayHeight;
        transformedMatrix.x = displayWidth / 2.0f + (0.5f * transformedMatrix.x * displayWidth + 0.5f);
        transformedMatrix.y = displayHeight / 2.0f - (0.5f * transformedMatrix.y * displayHeight + 0.5f);
        return new Vec3d((double)transformedMatrix.x, (double)transformedMatrix.y, isVisible(transformedMatrix, displayWidth, displayHeight) ? 0.0 : -1.0);
    }
}
