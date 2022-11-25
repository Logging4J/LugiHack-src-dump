//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.event.events;

import me.snow.luigihack.api.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.client.model.*;
import net.minecraft.entity.*;

@Cancelable
public class RenderLivingEntityEvent extends EventStage
{
    private final /* synthetic */ float limbSwingAmount;
    private final /* synthetic */ ModelBase modelBase;
    private final /* synthetic */ float scaleFactor;
    private final /* synthetic */ float ageInTicks;
    private final /* synthetic */ float limbSwing;
    private final /* synthetic */ float netHeadYaw;
    private final /* synthetic */ EntityLivingBase entityLivingBase;
    private final /* synthetic */ float headPitch;
    
    public RenderLivingEntityEvent(final ModelBase modelBase, final EntityLivingBase entityLivingBase, final float limbSwing, final float limbSwingAmount, final float ageInTicks, final float netHeadYaw, final float headPitch, final float scaleFactor) {
        this.modelBase = modelBase;
        this.entityLivingBase = entityLivingBase;
        this.limbSwing = limbSwing;
        this.limbSwingAmount = limbSwingAmount;
        this.ageInTicks = ageInTicks;
        this.netHeadYaw = netHeadYaw;
        this.headPitch = headPitch;
        this.scaleFactor = scaleFactor;
    }
    
    public float getNetHeadYaw() {
        return this.netHeadYaw;
    }
    
    public float getScaleFactor() {
        return this.scaleFactor;
    }
    
    public float getLimbSwing() {
        return this.limbSwing;
    }
    
    public EntityLivingBase getEntityLivingBase() {
        return this.entityLivingBase;
    }
    
    public float getHeadPitch() {
        return this.headPitch;
    }
    
    public ModelBase getModelBase() {
        return this.modelBase;
    }
    
    public float getAgeInTicks() {
        return this.ageInTicks;
    }
    
    public float getLimbSwingAmount() {
        return this.limbSwingAmount;
    }
}
