//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.gui.effect.particles;

import net.minecraft.client.gui.*;
import java.util.concurrent.*;
import org.lwjgl.input.*;
import me.snow.luigihack.impl.modules.client.*;
import java.awt.*;
import me.snow.luigihack.api.util.*;
import javax.vecmath.*;

public class Particle
{
    private /* synthetic */ Vector2f acceleration;
    private final /* synthetic */ int maxAlpha;
    private /* synthetic */ Vector2f velocity;
    private /* synthetic */ Vector2f pos;
    private /* synthetic */ float size;
    private /* synthetic */ int alpha;
    
    public void setAcceleration(final Vector2f acceleration) {
        this.acceleration = acceleration;
    }
    
    public void setVelocity(final Vector2f velocity) {
        this.velocity = velocity;
    }
    
    public void setPos(final Vector2f pos) {
        this.pos = pos;
    }
    
    public Vector2f getVelocity() {
        return this.velocity;
    }
    
    public int getAlpha() {
        return this.alpha;
    }
    
    public void respawn(final ScaledResolution scaledResolution) {
        this.pos = new Vector2f((float)(Math.random() * scaledResolution.getScaledWidth()), (float)(Math.random() * scaledResolution.getScaledHeight()));
    }
    
    public Particle(final Vector2f pos) {
        this.pos = pos;
        final int n = -1;
        final int n2 = 1;
        this.velocity = new Vector2f(n + ThreadLocalRandom.current().nextFloat() * (n2 - n), n + ThreadLocalRandom.current().nextFloat() * (n2 - n));
        this.acceleration = new Vector2f(0.0f, 0.35f);
        this.alpha = 0;
        this.maxAlpha = ThreadLocalRandom.current().nextInt(32, 192);
        this.size = 0.5f + ThreadLocalRandom.current().nextFloat() * 1.5f;
    }
    
    public Vector2f getAcceleration() {
        return this.acceleration;
    }
    
    public void render(final int n, final int n2) {
        if (Mouse.isButtonDown(0)) {
            final float a = n - this.pos.getX();
            final float a2 = n2 - this.pos.getY();
            if (Math.abs(a) < 50.0f && Math.abs(a2) < 50.0f) {
                this.acceleration.setX(this.acceleration.getX() + a * 0.0015f);
                this.acceleration.setY(this.acceleration.getY() + a2 * 0.0015f);
            }
        }
        final Color color = ClickGui.getInstance().colorSync.getValue() ? Colors.INSTANCE.getCurrentColor() : new Color((int)ClickGui.getInstance().particlered.getValue(), (int)ClickGui.getInstance().particlegreen.getValue(), (int)ClickGui.getInstance().particleblue.getValue(), (int)ClickGui.getInstance().alpha.getValue());
        RenderUtil.drawRect(this.pos.x, this.pos.y, this.pos.x + this.size, this.pos.y + this.size, changeAlpha(ColorUtil.toRGBA(color.getRed(), color.getGreen(), color.getBlue()), this.alpha));
    }
    
    public void setSize(final float size) {
        this.size = size;
    }
    
    public static int changeAlpha(int n, final int n2) {
        return n2 << 24 | (n &= 0xFFFFFF);
    }
    
    public Vector2f getPos() {
        return this.pos;
    }
    
    public float getSize() {
        return this.size;
    }
    
    public void update() {
        if (this.alpha < this.maxAlpha) {
            this.alpha += 8;
        }
        if (this.acceleration.getX() > 0.35f) {
            this.acceleration.setX(this.acceleration.getX() * 0.975f);
        }
        else if (this.acceleration.getX() < -0.35f) {
            this.acceleration.setX(this.acceleration.getX() * 0.975f);
        }
        if (this.acceleration.getY() > 0.35f) {
            this.acceleration.setY(this.acceleration.getY() * 0.975f);
        }
        else if (this.acceleration.getY() < -0.35f) {
            this.acceleration.setY(this.acceleration.getY() * 0.975f);
        }
        this.pos.add((Tuple2f)this.acceleration);
        this.pos.add((Tuple2f)this.velocity);
    }
    
    public void setAlpha(final int alpha) {
        this.alpha = alpha;
    }
}
