//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.event.events;

import me.snow.luigihack.api.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.entity.*;
import net.minecraft.client.model.*;

@Cancelable
public class RenderEntityModelEvent extends EventStage
{
    public /* synthetic */ Entity entity;
    public /* synthetic */ float headPitch;
    public /* synthetic */ ModelBase modelBase;
    public /* synthetic */ float limbSwingAmount;
    public /* synthetic */ float limbSwing;
    public /* synthetic */ float scale;
    public /* synthetic */ float headYaw;
    public /* synthetic */ float age;
    
    public RenderEntityModelEvent(final int n, final ModelBase modelBase, final Entity entity, final float limbSwing, final float limbSwingAmount, final float age, final float headYaw, final float headPitch, final float scale) {
        super(n);
        this.modelBase = modelBase;
        this.entity = entity;
        this.limbSwing = limbSwing;
        this.limbSwingAmount = limbSwingAmount;
        this.age = age;
        this.headYaw = headYaw;
        this.headPitch = headPitch;
        this.scale = scale;
    }
}
