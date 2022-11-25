//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util;

import me.snow.luigihack.*;
import org.lwjgl.opengl.*;
import org.lwjgl.*;
import java.nio.*;

public class ESPBuffer
{
    private static /* synthetic */ int texelSize;
    private /* synthetic */ int bufferPos;
    private final /* synthetic */ int heightFactor;
    private final /* synthetic */ int texture;
    private final /* synthetic */ int height;
    private final /* synthetic */ int width;
    private /* synthetic */ int genTextures;
    private static /* synthetic */ int mainSource;
    private /* synthetic */ int renderBuffer;
    private /* synthetic */ int frameBuffer;
    private /* synthetic */ float bufferFactor;
    private static /* synthetic */ int sampleRadius;
    private static /* synthetic */ int id;
    private static /* synthetic */ int diffuseSamper;
    private final /* synthetic */ int widthFactor;
    
    public ESPBuffer setBufferPos(final int bufferPos) {
        this.bufferPos = bufferPos;
        return this;
    }
    
    public static String getLogInfo(final int n) {
        return ARBShaderObjects.glGetInfoLogARB(n, ARBShaderObjects.glGetObjectParameteriARB(n, 35716));
    }
    
    private void bind() {
        if (ESPBuffer.id == -1) {
            ESPBuffer.id = ARBShaderObjects.glCreateProgramObjectARB();
            try {
                if (ESPBuffer.mainSource == -1) {
                    ESPBuffer.mainSource = applyShaderSource("#version 120\nuniform sampler2D DiffuseSamper;\nuniform vec2 TexelSize;\nuniform int SampleRadius;\nvoid main() {\nvec4 centerCol = texture2D(DiffuseSamper, gl_TexCoord[0].st);\nif (centerCol.a != 0.0F) {\ngl_FragColor = vec4(0, 0, 0, 0);\nreturn;\n}\nfloat closest = SampleRadius * 2.0F + 2.0F;\nfor (int xo = -SampleRadius; xo <= SampleRadius; xo++) {\nfor (int yo = -SampleRadius; yo <= SampleRadius; yo++) {\nvec4 currCol = texture2D(DiffuseSamper, gl_TexCoord[0].st + vec2(xo * TexelSize.x, yo * TexelSize.y));\nif (currCol.r != 0.0F || currCol.g != 0.0F || currCol.b != 0.0F || currCol.a != 0.0F) {\nfloat currentDist = sqrt(xo*xo*1.0f + yo*yo*1.0f);\nif (currentDist < closest) {\nclosest = currentDist;\n}\n}\n}\n}\ngl_FragColor = vec4(1, 1, 1, max(0, ((SampleRadius*1.0F) - (closest - 1)) / (SampleRadius*1.0F)));\n}", 35632);
                }
            }
            catch (Exception ex) {
                ESPBuffer.id = -1;
                ESPBuffer.mainSource = -1;
                ex.printStackTrace();
            }
            if (ESPBuffer.id != -1) {
                ARBShaderObjects.glAttachObjectARB(ESPBuffer.id, ESPBuffer.mainSource);
                ARBShaderObjects.glLinkProgramARB(ESPBuffer.id);
                if (ARBShaderObjects.glGetObjectParameteriARB(ESPBuffer.id, 35714) == 0) {
                    LuigiHack.getLogger().error(String.valueOf(new StringBuilder().append(getLogInfo(ESPBuffer.id)).append(" : 35714")));
                    return;
                }
                ARBShaderObjects.glValidateProgramARB(ESPBuffer.id);
                if (ARBShaderObjects.glGetObjectParameteriARB(ESPBuffer.id, 35715) == 0) {
                    LuigiHack.getLogger().error(String.valueOf(new StringBuilder().append(getLogInfo(ESPBuffer.id)).append(" : 35715")));
                    return;
                }
                ARBShaderObjects.glUseProgramObjectARB(0);
                ESPBuffer.diffuseSamper = ARBShaderObjects.glGetUniformLocationARB(ESPBuffer.id, (CharSequence)"DiffuseSamper");
                ESPBuffer.texelSize = ARBShaderObjects.glGetUniformLocationARB(ESPBuffer.id, (CharSequence)"TexelSize");
                ESPBuffer.sampleRadius = ARBShaderObjects.glGetUniformLocationARB(ESPBuffer.id, (CharSequence)"SampleRadius");
            }
        }
    }
    
    public static int applyShaderSource(final String s, final int n) {
        try {
            final int glCreateShaderObjectARB = ARBShaderObjects.glCreateShaderObjectARB(n);
            if (glCreateShaderObjectARB == 0) {
                return 0;
            }
            ARBShaderObjects.glShaderSourceARB(glCreateShaderObjectARB, (CharSequence)s);
            ARBShaderObjects.glCompileShaderARB(glCreateShaderObjectARB);
            if (ARBShaderObjects.glGetObjectParameteriARB(glCreateShaderObjectARB, 35713) == 0) {
                throw new RuntimeException(String.valueOf(new StringBuilder().append("Error creating shader: ").append(getLogInfo(glCreateShaderObjectARB))));
            }
            return glCreateShaderObjectARB;
        }
        catch (Exception ex) {
            ARBShaderObjects.glDeleteObjectARB(0);
            throw ex;
        }
    }
    
    public int getTexture() {
        return this.genTextures;
    }
    
    public void setBufferFactor(final float bufferFactor) {
        this.bufferFactor = bufferFactor;
    }
    
    public void deleteFramebuffer() {
        if (this.renderBuffer > -1) {
            EXTFramebufferObject.glDeleteRenderbuffersEXT(this.renderBuffer);
        }
        if (this.frameBuffer > -1) {
            EXTFramebufferObject.glDeleteFramebuffersEXT(this.frameBuffer);
        }
        if (this.genTextures > -1) {
            GL11.glDeleteTextures(this.genTextures);
        }
    }
    
    private void create() {
        this.frameBuffer = EXTFramebufferObject.glGenFramebuffersEXT();
        this.genTextures = GL11.glGenTextures();
        this.renderBuffer = EXTFramebufferObject.glGenRenderbuffersEXT();
        GL11.glBindTexture(3553, this.genTextures);
        GL11.glTexParameterf(3553, 10241, 9729.0f);
        GL11.glTexParameterf(3553, 10240, 9729.0f);
        GL11.glTexParameterf(3553, 10242, 10496.0f);
        GL11.glTexParameterf(3553, 10243, 10496.0f);
        GL11.glBindTexture(3553, 0);
        GL11.glBindTexture(3553, this.genTextures);
        GL11.glTexImage2D(3553, 0, 32856, this.width, this.height, 0, 6408, 5121, (ByteBuffer)null);
        EXTFramebufferObject.glBindFramebufferEXT(36160, this.frameBuffer);
        EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064, 3553, this.genTextures, 0);
    }
    
    public ESPBuffer(final int texture, final int width, final int height, final int widthFactor, final int heightFactor, final float bufferFactor, final int bufferPos) {
        this.renderBuffer = -1;
        this.genTextures = -1;
        this.frameBuffer = -1;
        this.texture = texture;
        this.width = width;
        this.height = height;
        this.widthFactor = widthFactor;
        this.heightFactor = heightFactor;
        this.bufferFactor = bufferFactor;
        this.bufferPos = bufferPos;
        this.create();
        this.bind();
    }
    
    static {
        MAIN = "#version 120\nuniform sampler2D DiffuseSamper;\nuniform vec2 TexelSize;\nuniform int SampleRadius;\nvoid main() {\nvec4 centerCol = texture2D(DiffuseSamper, gl_TexCoord[0].st);\nif (centerCol.a != 0.0F) {\ngl_FragColor = vec4(0, 0, 0, 0);\nreturn;\n}\nfloat closest = SampleRadius * 2.0F + 2.0F;\nfor (int xo = -SampleRadius; xo <= SampleRadius; xo++) {\nfor (int yo = -SampleRadius; yo <= SampleRadius; yo++) {\nvec4 currCol = texture2D(DiffuseSamper, gl_TexCoord[0].st + vec2(xo * TexelSize.x, yo * TexelSize.y));\nif (currCol.r != 0.0F || currCol.g != 0.0F || currCol.b != 0.0F || currCol.a != 0.0F) {\nfloat currentDist = sqrt(xo*xo*1.0f + yo*yo*1.0f);\nif (currentDist < closest) {\nclosest = currentDist;\n}\n}\n}\n}\ngl_FragColor = vec4(1, 1, 1, max(0, ((SampleRadius*1.0F) - (closest - 1)) / (SampleRadius*1.0F)));\n}";
        ESPBuffer.mainSource = -1;
        ESPBuffer.sampleRadius = -1;
        ESPBuffer.texelSize = -1;
        ESPBuffer.id = -1;
        ESPBuffer.diffuseSamper = -1;
    }
    
    public void setBufferFactor() {
        if (this.frameBuffer != -1 && this.renderBuffer != -1 && ESPBuffer.id != -1) {
            EXTFramebufferObject.glBindFramebufferEXT(36160, this.frameBuffer);
            GL11.glClear(16640);
            ARBShaderObjects.glUseProgramObjectARB(ESPBuffer.id);
            ARBShaderObjects.glUniform1iARB(ESPBuffer.diffuseSamper, 0);
            GL13.glActiveTexture(33984);
            GL11.glEnable(3553);
            GL11.glBindTexture(3553, this.texture);
            final FloatBuffer floatBuffer = BufferUtils.createFloatBuffer(2);
            floatBuffer.position(0);
            floatBuffer.put(1.0f / this.width * this.bufferFactor);
            floatBuffer.put(1.0f / this.height * this.bufferFactor);
            floatBuffer.flip();
            ARBShaderObjects.glUniform2ARB(ESPBuffer.texelSize, floatBuffer);
            final IntBuffer intBuffer = BufferUtils.createIntBuffer(1);
            intBuffer.position(0);
            intBuffer.put(this.bufferPos);
            intBuffer.flip();
            ARBShaderObjects.glUniform1ARB(ESPBuffer.sampleRadius, intBuffer);
            GL11.glPushMatrix();
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 768);
            GL11.glBegin(9);
            GL11.glTexCoord2d(0.0, 1.0);
            GL11.glVertex2d(0.0, 0.0);
            GL11.glTexCoord2d(0.0, 0.0);
            GL11.glVertex2d(0.0, (double)(this.heightFactor * 2));
            GL11.glTexCoord2d(1.0, 0.0);
            GL11.glVertex2d((double)(this.widthFactor * 2), (double)(this.heightFactor * 2));
            GL11.glTexCoord2d(1.0, 0.0);
            GL11.glVertex2d((double)(this.widthFactor * 2), (double)(this.heightFactor * 2));
            GL11.glTexCoord2d(1.0, 1.0);
            GL11.glVertex2d((double)(this.widthFactor * 2), 0.0);
            GL11.glTexCoord2d(0.0, 1.0);
            GL11.glVertex2d(0.0, 0.0);
            GL11.glEnd();
            GL11.glDisable(3042);
            GL11.glPopMatrix();
            ARBShaderObjects.glUseProgramObjectARB(0);
            return;
        }
        throw new RuntimeException("Invalid IDs");
    }
}
