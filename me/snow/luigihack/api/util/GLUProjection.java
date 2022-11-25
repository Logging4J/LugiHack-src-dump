//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util;

import java.nio.*;
import org.lwjgl.*;
import org.lwjgl.util.glu.*;
import org.lwjgl.util.vector.*;

public final class GLUProjection
{
    private /* synthetic */ double tra;
    private /* synthetic */ float fovX;
    private /* synthetic */ Vector3D lookVec;
    private /* synthetic */ double tla;
    private /* synthetic */ Line lb;
    private /* synthetic */ Vector3D[] frustum;
    private /* synthetic */ double heightScale;
    private /* synthetic */ double bra;
    private /* synthetic */ float fovY;
    private /* synthetic */ double displayWidth;
    private /* synthetic */ double bla;
    private /* synthetic */ FloatBuffer projection;
    private /* synthetic */ IntBuffer viewport;
    private /* synthetic */ Line tb;
    private /* synthetic */ double displayHeight;
    private /* synthetic */ Vector3D frustumPos;
    private /* synthetic */ Line rb;
    private /* synthetic */ double widthScale;
    private /* synthetic */ Line bb;
    private /* synthetic */ FloatBuffer modelview;
    private static /* synthetic */ GLUProjection instance;
    private final /* synthetic */ FloatBuffer coords;
    private /* synthetic */ Vector3D viewVec;
    private /* synthetic */ Vector3D[] invFrustum;
    
    private GLUProjection() {
        this.coords = BufferUtils.createFloatBuffer(3);
    }
    
    public Vector3D[] getFrustum() {
        return this.frustum;
    }
    
    public Vector3D getLookVector() {
        return this.lookVec;
    }
    
    public static GLUProjection getInstance() {
        if (GLUProjection.instance == null) {
            GLUProjection.instance = new GLUProjection();
        }
        return GLUProjection.instance;
    }
    
    public Vector3D getRotationVector(final double n, final double n2) {
        final double cos = Math.cos(-n * 0.01745329238474369 - 3.141592653589793);
        final double sin = Math.sin(-n * 0.01745329238474369 - 3.141592653589793);
        final double n3 = -Math.cos(-n2 * 0.01745329238474369);
        return new Vector3D(sin * n3, Math.sin(-n2 * 0.01745329238474369), cos * n3);
    }
    
    public Vector3D[] getFrustum(final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final double n7, final double n8) {
        final double n9 = 2.0 * Math.tan(Math.toRadians(n6 / 2.0)) * n7;
        final double n10 = n9 * n8;
        final Vector3D snormalize = this.getRotationVector(n4, n5).snormalize();
        final Vector3D snormalize2 = this.getRotationVector(n4, n5 - 90.0).snormalize();
        final Vector3D snormalize3 = this.getRotationVector(n4 + 90.0, 0.0).snormalize();
        final Vector3D add = snormalize.add(new Vector3D(n, n2, n3));
        final Vector3D vector3D = new Vector3D(add.x * n7, add.y * n7, add.z * n7);
        return new Vector3D[] { new Vector3D(vector3D.x + snormalize2.x * n9 / 2.0 - snormalize3.x * n10 / 2.0, vector3D.y + snormalize2.y * n9 / 2.0 - snormalize3.y * n10 / 2.0, vector3D.z + snormalize2.z * n9 / 2.0 - snormalize3.z * n10 / 2.0), new Vector3D(vector3D.x - snormalize2.x * n9 / 2.0 - snormalize3.x * n10 / 2.0, vector3D.y - snormalize2.y * n9 / 2.0 - snormalize3.y * n10 / 2.0, vector3D.z - snormalize2.z * n9 / 2.0 - snormalize3.z * n10 / 2.0), new Vector3D(vector3D.x - snormalize2.x * n9 / 2.0 + snormalize3.x * n10 / 2.0, vector3D.y - snormalize2.y * n9 / 2.0 + snormalize3.y * n10 / 2.0, vector3D.z - snormalize2.z * n9 / 2.0 + snormalize3.z * n10 / 2.0), new Vector3D(vector3D.x + snormalize2.x * n9 / 2.0 + snormalize3.x * n10 / 2.0, vector3D.y + snormalize2.y * n9 / 2.0 + snormalize3.y * n10 / 2.0, vector3D.z + snormalize2.z * n9 / 2.0 + snormalize3.z * n10 / 2.0) };
    }
    
    public float getFovX() {
        return this.fovX;
    }
    
    public Projection project(final double n, final double n2, final double n3, final ClampMode clampMode, final boolean b) {
        if (this.viewport == null || this.modelview == null || this.projection == null) {
            return new Projection(0.0, 0.0, Projection.Type.FAIL);
        }
        final Vector3D vector3D = new Vector3D(n, n2, n3);
        final boolean[] doFrustumCheck = this.doFrustumCheck(this.frustum, this.frustumPos, n, n2, n3);
        if (doFrustumCheck[0] || doFrustumCheck[1] || doFrustumCheck[2] || doFrustumCheck[3]) {
            final boolean b2 = vector3D.sub(this.frustumPos).dot(this.viewVec) <= 0.0;
            final boolean[] doFrustumCheck2 = this.doFrustumCheck(this.invFrustum, this.frustumPos, n, n2, n3);
            final boolean b3 = doFrustumCheck2[0] || doFrustumCheck2[1] || doFrustumCheck2[2] || doFrustumCheck2[3];
            if ((b && !b3) || (b3 && clampMode != ClampMode.NONE)) {
                if ((b && !b3) || clampMode == ClampMode.DIRECT) {
                    if (!GLU.gluProject((float)n, (float)n2, (float)n3, this.modelview, this.projection, this.viewport, this.coords)) {
                        return new Projection(0.0, 0.0, Projection.Type.FAIL);
                    }
                    double n4;
                    double n5;
                    if (b2) {
                        n4 = this.displayWidth * this.widthScale - this.coords.get(0) * this.widthScale - this.displayWidth * this.widthScale / 2.0;
                        n5 = this.displayHeight * this.heightScale - (this.displayHeight - this.coords.get(1)) * this.heightScale - this.displayHeight * this.heightScale / 2.0;
                    }
                    else {
                        n4 = this.coords.get(0) * this.widthScale - this.displayWidth * this.widthScale / 2.0;
                        n5 = (this.displayHeight - this.coords.get(1)) * this.heightScale - this.displayHeight * this.heightScale / 2.0;
                    }
                    final Vector3D snormalize = new Vector3D(n4, n5, 0.0).snormalize();
                    final double x = snormalize.x;
                    final Line line = new Line(this.displayWidth * this.widthScale / 2.0, this.displayHeight * this.heightScale / 2.0, 0.0, x, snormalize.y, 0.0);
                    double degrees = Math.toDegrees(Math.acos(snormalize.y / Math.sqrt(snormalize.x * snormalize.x + snormalize.y * snormalize.y)));
                    if (x < 0.0) {
                        degrees = 360.0 - degrees;
                    }
                    new Vector3D(0.0, 0.0, 0.0);
                    final Vector3D vector3D2 = (degrees >= this.bra && degrees < this.tra) ? this.rb.intersect(line) : ((degrees >= this.tra && degrees < this.tla) ? this.tb.intersect(line) : ((degrees >= this.tla && degrees < this.bla) ? this.lb.intersect(line) : this.bb.intersect(line)));
                    return new Projection(vector3D2.x, vector3D2.y, b3 ? Projection.Type.OUTSIDE : Projection.Type.INVERTED);
                }
                else {
                    if (clampMode != ClampMode.ORTHOGONAL) {
                        return new Projection(0.0, 0.0, Projection.Type.FAIL);
                    }
                    if (!GLU.gluProject((float)n, (float)n2, (float)n3, this.modelview, this.projection, this.viewport, this.coords)) {
                        return new Projection(0.0, 0.0, Projection.Type.FAIL);
                    }
                    double n6 = this.coords.get(0) * this.widthScale;
                    double n7 = (this.displayHeight - this.coords.get(1)) * this.heightScale;
                    if (b2) {
                        n6 = this.displayWidth * this.widthScale - n6;
                        n7 = this.displayHeight * this.heightScale - n7;
                    }
                    if (n6 < 0.0) {
                        n6 = 0.0;
                    }
                    else if (n6 > this.displayWidth * this.widthScale) {
                        n6 = this.displayWidth * this.widthScale;
                    }
                    if (n7 < 0.0) {
                        return new Projection(n6, 0.0, Projection.Type.OUTSIDE);
                    }
                    if (n7 <= this.displayHeight * this.heightScale) {
                        return new Projection(n6, n7, Projection.Type.OUTSIDE);
                    }
                    return new Projection(n6, this.displayHeight * this.heightScale, Projection.Type.OUTSIDE);
                }
            }
            else {
                if (!GLU.gluProject((float)n, (float)n2, (float)n3, this.modelview, this.projection, this.viewport, this.coords)) {
                    return new Projection(0.0, 0.0, Projection.Type.FAIL);
                }
                final double n8 = this.coords.get(0) * this.widthScale;
                final double n9 = (this.displayHeight - this.coords.get(1)) * this.heightScale;
                if (!b2) {
                    return new Projection(n8, n9, b3 ? Projection.Type.OUTSIDE : Projection.Type.INVERTED);
                }
                return new Projection(this.displayWidth * this.widthScale - n8, this.displayHeight * this.heightScale - n9, b3 ? Projection.Type.OUTSIDE : Projection.Type.INVERTED);
            }
        }
        else {
            if (!GLU.gluProject((float)n, (float)n2, (float)n3, this.modelview, this.projection, this.viewport, this.coords)) {
                return new Projection(0.0, 0.0, Projection.Type.FAIL);
            }
            return new Projection(this.coords.get(0) * this.widthScale, (this.displayHeight - this.coords.get(1)) * this.heightScale, Projection.Type.INSIDE);
        }
    }
    
    public boolean[] doFrustumCheck(final Vector3D[] array, final Vector3D vector3D, final double n, final double n2, final double n3) {
        final Vector3D vector3D2 = new Vector3D(n, n2, n3);
        return new boolean[] { this.crossPlane(new Vector3D[] { vector3D, array[3], array[0] }, vector3D2), this.crossPlane(new Vector3D[] { vector3D, array[0], array[1] }, vector3D2), this.crossPlane(new Vector3D[] { vector3D, array[1], array[2] }, vector3D2), this.crossPlane(new Vector3D[] { vector3D, array[2], array[3] }, vector3D2) };
    }
    
    public boolean crossPlane(final Vector3D[] array, final Vector3D vector3D) {
        final Vector3D vector3D2 = new Vector3D(0.0, 0.0, 0.0);
        final Vector3D snormalize = array[1].sub(array[0]).cross(array[2].sub(array[0])).snormalize();
        return snormalize.dot(vector3D) + vector3D2.sub(snormalize).dot(array[2]) >= 0.0;
    }
    
    public float getFovY() {
        return this.fovY;
    }
    
    public void updateMatrices(final IntBuffer viewport, final FloatBuffer modelview, final FloatBuffer projection, final double widthScale, final double heightScale) {
        this.viewport = viewport;
        this.modelview = modelview;
        this.projection = projection;
        this.widthScale = widthScale;
        this.heightScale = heightScale;
        final float n = this.fovY = (float)Math.toDegrees(Math.atan(1.0 / this.projection.get(5)) * 2.0);
        this.displayWidth = this.viewport.get(2);
        this.displayHeight = this.viewport.get(3);
        this.fovX = (float)Math.toDegrees(2.0 * Math.atan(this.displayWidth / this.displayHeight * Math.tan(Math.toRadians(this.fovY) / 2.0)));
        final Vector3D vector3D = new Vector3D(this.modelview.get(0), this.modelview.get(1), this.modelview.get(2));
        final Vector3D vector3D2 = new Vector3D(this.modelview.get(4), this.modelview.get(5), this.modelview.get(6));
        final Vector3D vector3D3 = new Vector3D(this.modelview.get(8), this.modelview.get(9), this.modelview.get(10));
        final Vector3D vector3D4 = new Vector3D(0.0, 1.0, 0.0);
        final Vector3D vector3D5 = new Vector3D(1.0, 0.0, 0.0);
        double n2 = Math.toDegrees(Math.atan2(vector3D5.cross(vector3D).length(), vector3D5.dot(vector3D))) + 180.0;
        if (vector3D3.x < 0.0) {
            n2 = 360.0 - n2;
        }
        final double n3 = ((-vector3D3.y > 0.0 && n2 >= 90.0 && n2 < 270.0) || (vector3D3.y > 0.0 && (n2 < 90.0 || n2 >= 270.0))) ? Math.toDegrees(Math.atan2(vector3D4.cross(vector3D2).length(), vector3D4.dot(vector3D2))) : (-Math.toDegrees(Math.atan2(vector3D4.cross(vector3D2).length(), vector3D4.dot(vector3D2))));
        this.lookVec = this.getRotationVector(n2, n3);
        final Matrix4f matrix4f = new Matrix4f();
        matrix4f.load(this.modelview.asReadOnlyBuffer());
        matrix4f.invert();
        this.frustumPos = new Vector3D(matrix4f.m30, matrix4f.m31, matrix4f.m32);
        this.frustum = this.getFrustum(this.frustumPos.x, this.frustumPos.y, this.frustumPos.z, n2, n3, n, 1.0, this.displayWidth / this.displayHeight);
        this.invFrustum = this.getFrustum(this.frustumPos.x, this.frustumPos.y, this.frustumPos.z, n2 - 180.0, -n3, n, 1.0, this.displayWidth / this.displayHeight);
        this.viewVec = this.getRotationVector(n2, n3).normalized();
        this.bra = Math.toDegrees(Math.acos(this.displayHeight * heightScale / Math.sqrt(this.displayWidth * widthScale * this.displayWidth * widthScale + this.displayHeight * heightScale * this.displayHeight * heightScale)));
        this.bla = 360.0 - this.bra;
        this.tra = this.bla - 180.0;
        this.tla = this.bra + 180.0;
        this.rb = new Line(this.displayWidth * this.widthScale, 0.0, 0.0, 0.0, 1.0, 0.0);
        this.tb = new Line(0.0, 0.0, 0.0, 1.0, 0.0, 0.0);
        this.lb = new Line(0.0, 0.0, 0.0, 0.0, 1.0, 0.0);
        this.bb = new Line(0.0, this.displayHeight * this.heightScale, 0.0, 1.0, 0.0, 0.0);
    }
    
    public static class Projection
    {
        private final /* synthetic */ double x;
        private final /* synthetic */ double y;
        private final /* synthetic */ Type t;
        
        public Type getType() {
            return this.t;
        }
        
        public boolean isType(final Type type) {
            return this.t == type;
        }
        
        public double getY() {
            return this.y;
        }
        
        public Projection(final double x, final double y, final Type t) {
            this.x = x;
            this.y = y;
            this.t = t;
        }
        
        public double getX() {
            return this.x;
        }
        
        public enum Type
        {
            FAIL, 
            INVERTED, 
            INSIDE, 
            OUTSIDE;
        }
    }
    
    public static class Vector3D
    {
        public /* synthetic */ double z;
        public /* synthetic */ double x;
        public /* synthetic */ double y;
        
        public Vector3D sub(final double n, final double n2, final double n3) {
            return new Vector3D(this.x - n, this.y - n2, this.z - n3);
        }
        
        public Vector3D sadd(final Vector3D vector3D) {
            this.x += vector3D.x;
            this.y += vector3D.y;
            this.z += vector3D.z;
            return this;
        }
        
        public double dot(final Vector3D vector3D) {
            return this.x * vector3D.x + this.y * vector3D.y + this.z * vector3D.z;
        }
        
        @Override
        public String toString() {
            return String.valueOf(new StringBuilder().append("(X: ").append(this.x).append(" Y: ").append(this.y).append(" Z: ").append(this.z).append(")"));
        }
        
        public Vector3D scross(final Vector3D vector3D) {
            this.x = this.y * vector3D.z - this.z * vector3D.y;
            this.y = this.z * vector3D.x - this.x * vector3D.z;
            this.z = this.x * vector3D.y - this.y * vector3D.x;
            return this;
        }
        
        public Vector3D add(final Vector3D vector3D) {
            return new Vector3D(this.x + vector3D.x, this.y + vector3D.y, this.z + vector3D.z);
        }
        
        public Vector3D cross(final Vector3D vector3D) {
            return new Vector3D(this.y * vector3D.z - this.z * vector3D.y, this.z * vector3D.x - this.x * vector3D.z, this.x * vector3D.y - this.y * vector3D.x);
        }
        
        public Vector3D add(final double n, final double n2, final double n3) {
            return new Vector3D(this.x + n, this.y + n2, this.z + n3);
        }
        
        public Vector3D ssub(final double n, final double n2, final double n3) {
            this.x -= n;
            this.y -= n2;
            this.z -= n3;
            return this;
        }
        
        public Vector3D sub(final Vector3D vector3D) {
            return new Vector3D(this.x - vector3D.x, this.y - vector3D.y, this.z - vector3D.z);
        }
        
        public Vector3D snormalize() {
            final double sqrt = Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
            this.x /= sqrt;
            this.y /= sqrt;
            this.z /= sqrt;
            return this;
        }
        
        public Vector3D smul(final double n) {
            this.x *= n;
            this.y *= n;
            this.z *= n;
            return this;
        }
        
        public Vector3D mul(final double n) {
            return new Vector3D(this.x * n, this.y * n, this.z * n);
        }
        
        public Vector3D normalized() {
            final double sqrt = Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
            return new Vector3D(this.x / sqrt, this.y / sqrt, this.z / sqrt);
        }
        
        public Vector3D sdiv(final double n) {
            this.x /= n;
            this.y /= n;
            this.z /= n;
            return this;
        }
        
        public double length() {
            return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
        }
        
        public Vector3D sadd(final double n, final double n2, final double n3) {
            this.x += n;
            this.y += n2;
            this.z += n3;
            return this;
        }
        
        public Vector3D(final double x, final double y, final double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
        
        public Vector3D ssub(final Vector3D vector3D) {
            this.x -= vector3D.x;
            this.y -= vector3D.y;
            this.z -= vector3D.z;
            return this;
        }
        
        public Vector3D div(final double n) {
            return new Vector3D(this.x / n, this.y / n, this.z / n);
        }
    }
    
    public static class Line
    {
        public /* synthetic */ Vector3D direction;
        public /* synthetic */ Vector3D sourcePoint;
        
        public Vector3D intersect(final Line line) {
            final double x = this.sourcePoint.x;
            final double x2 = this.direction.x;
            final double x3 = line.sourcePoint.x;
            final double x4 = line.direction.x;
            final double y = this.sourcePoint.y;
            final double y2 = this.direction.y;
            final double y3 = line.sourcePoint.y;
            final double y4 = line.direction.y;
            final double n = -(x * y4 - x3 * y4 - x4 * (y - y3));
            final double n2 = x2 * y4 - x4 * y2;
            if (n2 == 0.0) {
                return this.intersectXZ(line);
            }
            final double n3 = n / n2;
            final Vector3D vector3D = new Vector3D(0.0, 0.0, 0.0);
            vector3D.x = this.sourcePoint.x + this.direction.x * n3;
            vector3D.y = this.sourcePoint.y + this.direction.y * n3;
            vector3D.z = this.sourcePoint.z + this.direction.z * n3;
            return vector3D;
        }
        
        public Vector3D intersectPlane(final Vector3D vector3D, final Vector3D vector3D2) {
            final Vector3D vector3D3 = new Vector3D(this.sourcePoint.x, this.sourcePoint.y, this.sourcePoint.z);
            vector3D3.sadd(this.direction.mul(vector3D.sub(this.sourcePoint).dot(vector3D2) / this.direction.dot(vector3D2)));
            if (this.direction.dot(vector3D2) == 0.0) {
                return null;
            }
            return vector3D3;
        }
        
        public Line(final double x, final double y, final double z, final double x2, final double y2, final double z2) {
            this.sourcePoint = new Vector3D(0.0, 0.0, 0.0);
            this.direction = new Vector3D(0.0, 0.0, 0.0);
            this.sourcePoint.x = x;
            this.sourcePoint.y = y;
            this.sourcePoint.z = z;
            this.direction.x = x2;
            this.direction.y = y2;
            this.direction.z = z2;
        }
        
        private Vector3D intersectYZ(final Line line) {
            final double y = this.sourcePoint.y;
            final double y2 = this.direction.y;
            final double y3 = line.sourcePoint.y;
            final double y4 = line.direction.y;
            final double z = this.sourcePoint.z;
            final double z2 = this.direction.z;
            final double z3 = line.sourcePoint.z;
            final double z4 = line.direction.z;
            final double n = -(y * z4 - y3 * z4 - y4 * (z - z3));
            final double n2 = y2 * z4 - y4 * z2;
            if (n2 == 0.0) {
                return null;
            }
            final double n3 = n / n2;
            final Vector3D vector3D = new Vector3D(0.0, 0.0, 0.0);
            vector3D.x = this.sourcePoint.x + this.direction.x * n3;
            vector3D.y = this.sourcePoint.y + this.direction.y * n3;
            vector3D.z = this.sourcePoint.z + this.direction.z * n3;
            return vector3D;
        }
        
        private Vector3D intersectXZ(final Line line) {
            final double x = this.sourcePoint.x;
            final double x2 = this.direction.x;
            final double x3 = line.sourcePoint.x;
            final double x4 = line.direction.x;
            final double z = this.sourcePoint.z;
            final double z2 = this.direction.z;
            final double z3 = line.sourcePoint.z;
            final double z4 = line.direction.z;
            final double n = -(x * z4 - x3 * z4 - x4 * (z - z3));
            final double n2 = x2 * z4 - x4 * z2;
            if (n2 == 0.0) {
                return this.intersectYZ(line);
            }
            final double n3 = n / n2;
            final Vector3D vector3D = new Vector3D(0.0, 0.0, 0.0);
            vector3D.x = this.sourcePoint.x + this.direction.x * n3;
            vector3D.y = this.sourcePoint.y + this.direction.y * n3;
            vector3D.z = this.sourcePoint.z + this.direction.z * n3;
            return vector3D;
        }
    }
    
    public enum ClampMode
    {
        NONE, 
        ORTHOGONAL, 
        DIRECT;
    }
}
