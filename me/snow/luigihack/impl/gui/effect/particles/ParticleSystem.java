//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.gui.effect.particles;

import net.minecraft.client.gui.*;
import javax.vecmath.*;
import me.snow.luigihack.impl.modules.client.*;
import java.awt.*;
import me.snow.luigihack.api.util.*;

public final class ParticleSystem
{
    private final /* synthetic */ Particle[] particles;
    private /* synthetic */ ScaledResolution scaledResolution;
    
    public void setScaledResolution(final ScaledResolution scaledResolution) {
        this.scaledResolution = scaledResolution;
    }
    
    public ParticleSystem(final ScaledResolution scaledResolution) {
        this.particles = new Particle[200];
        this.scaledResolution = scaledResolution;
        for (int i = 0; i < 200; ++i) {
            this.particles[i] = new Particle(new Vector2f((float)(Math.random() * scaledResolution.getScaledWidth()), (float)(Math.random() * scaledResolution.getScaledHeight())));
        }
    }
    
    public ScaledResolution getScaledResolution() {
        return this.scaledResolution;
    }
    
    public void render(final int n, final int n2) {
        if (!(boolean)ClickGui.getInstance().particles.getValue()) {
            return;
        }
        for (int i = 0; i < 200; ++i) {
            final Particle particle = this.particles[i];
            for (int j = 1; j < 200; ++j) {
                if (i != j) {
                    final Particle particle2 = this.particles[j];
                    final Vector2f vector2f = new Vector2f(particle.getPos());
                    vector2f.sub((Tuple2f)particle2.getPos());
                    final float length = vector2f.length();
                    final Color color = ClickGui.getInstance().colorSync.getValue() ? Colors.INSTANCE.getCurrentColor() : new Color((int)ClickGui.getInstance().particlered.getValue(), (int)ClickGui.getInstance().particlegreen.getValue(), (int)ClickGui.getInstance().particleblue.getValue(), (int)ClickGui.getInstance().alpha.getValue());
                    final int n3 = (int)ClickGui.getInstance().particleLength.getValue() / ((this.scaledResolution.getScaleFactor() <= 1) ? 3 : this.scaledResolution.getScaleFactor());
                    if (length < n3) {
                        final int n4;
                        if ((n4 = (int)map(length, n3, 0.0, 0.0, 127.0)) > 8) {
                            RenderUtil.drawLine(particle.getPos().x + particle.getSize() / 2.0f, particle.getPos().y + particle.getSize() / 2.0f, particle2.getPos().x + particle2.getSize() / 2.0f, particle2.getPos().y + particle2.getSize() / 2.0f, 1.0f, Particle.changeAlpha(ColorUtil.toRGBA(color.getRed(), color.getGreen(), color.getBlue()), n4));
                        }
                    }
                }
            }
            particle.render(n, n2);
        }
    }
    
    public void update() {
        for (int i = 0; i < 200; ++i) {
            final Particle particle = this.particles[i];
            if (this.scaledResolution != null) {
                final boolean b = particle.getPos().x > this.scaledResolution.getScaledWidth() || particle.getPos().x < 0.0f;
                int n = 0;
                if (particle.getPos().y <= this.scaledResolution.getScaledHeight() && particle.getPos().y >= 0.0f) {
                    n = 0;
                }
                final int n2 = n;
                if (b || n2 != 0) {
                    particle.respawn(this.scaledResolution);
                }
            }
            particle.update();
        }
    }
    
    public static double map(double n, final double n2, final double n3, final double n4, final double n5) {
        n = (n - n2) / (n3 - n2);
        return n4 + n * (n5 - n4);
    }
}
