//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.event.events;

import me.snow.luigihack.api.event.*;
import net.minecraft.client.gui.*;
import net.minecraft.util.math.*;
import net.minecraft.client.renderer.*;
import me.snow.luigihack.api.util.*;

public class EventRender extends EventStage
{
    private final /* synthetic */ ScaledResolution res;
    private final /* synthetic */ Tessellator tessellator;
    private final /* synthetic */ Vec3d render_pos;
    
    public void set_translation(final Vec3d vec3d) {
        this.get_buffer_build().setTranslation(-vec3d.x, -vec3d.y, -vec3d.z);
    }
    
    public BufferBuilder get_buffer_build() {
        return this.tessellator.getBuffer();
    }
    
    public double get_screen_width() {
        return this.res.getScaledWidth_double();
    }
    
    public double get_screen_height() {
        return this.res.getScaledHeight_double();
    }
    
    public void reset_translation() {
        this.set_translation(this.render_pos);
    }
    
    public Vec3d get_render_pos() {
        return this.render_pos;
    }
    
    public EventRender(final Tessellator tessellator, final Vec3d render_pos) {
        this.res = new ScaledResolution(Util.mc);
        this.tessellator = tessellator;
        this.render_pos = render_pos;
    }
    
    public Tessellator get_tessellator() {
        return this.tessellator;
    }
}
