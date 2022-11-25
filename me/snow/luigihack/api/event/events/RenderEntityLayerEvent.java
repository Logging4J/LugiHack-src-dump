//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.event.events;

import me.snow.luigihack.api.event.*;
import net.minecraft.client.renderer.entity.layers.*;
import net.minecraft.entity.*;

public class RenderEntityLayerEvent extends EventStage
{
    public /* synthetic */ LayerRenderer<?> layer;
    public /* synthetic */ EntityLivingBase entity;
    
    public LayerRenderer<?> component2() {
        return this.layer;
    }
    
    public EntityLivingBase getEntity() {
        return this.entity;
    }
    
    public void setLayer(final LayerRenderer<?> layer) {
        this.layer = layer;
    }
    
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RenderEntityLayerEvent)) {
            return false;
        }
        final RenderEntityLayerEvent renderEntityLayerEvent = (RenderEntityLayerEvent)o;
        return this.entity == renderEntityLayerEvent.entity && this.layer == renderEntityLayerEvent.layer;
    }
    
    public static RenderEntityLayerEvent copy$default(final RenderEntityLayerEvent renderEntityLayerEvent, EntityLivingBase entity, LayerRenderer layer, final int n, final Object o) {
        if ((n & 0x1) != 0x0) {
            entity = renderEntityLayerEvent.entity;
        }
        if ((n & 0x2) != 0x0) {
            layer = renderEntityLayerEvent.layer;
        }
        return renderEntityLayerEvent.copy(entity, (LayerRenderer<?>)layer);
    }
    
    public LayerRenderer<?> getLayer() {
        return this.layer;
    }
    
    public EntityLivingBase component1() {
        return this.entity;
    }
    
    public int hashCode() {
        return this.entity.hashCode() * 31 + this.layer.hashCode();
    }
    
    public RenderEntityLayerEvent copy(final EntityLivingBase entityLivingBase, final LayerRenderer<?> layerRenderer) {
        return new RenderEntityLayerEvent(entityLivingBase, layerRenderer);
    }
    
    public RenderEntityLayerEvent(final EntityLivingBase entity, final LayerRenderer<?> layer) {
        this.entity = entity;
        this.layer = layer;
    }
    
    public void setEntity(final EntityLivingBase entity) {
        this.entity = entity;
    }
    
    public String toString() {
        return String.valueOf(new StringBuilder().append("RenderEntityLayerEvent(entity=").append(this.entity).append(", layer=").append(this.layer).append(')'));
    }
}
