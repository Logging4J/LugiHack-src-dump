//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.event.events;

import me.snow.luigihack.api.event.*;

public class RenderItemEvent extends EventStage
{
    /* synthetic */ double offHandScaleZ;
    /* synthetic */ double mainZ;
    /* synthetic */ double offHandScaleX;
    /* synthetic */ double offRotX;
    /* synthetic */ double offY;
    /* synthetic */ double mainRotZ;
    /* synthetic */ double offRotZ;
    /* synthetic */ double mainX;
    /* synthetic */ double offZ;
    /* synthetic */ double offHandScaleY;
    /* synthetic */ double mainHandScaleY;
    /* synthetic */ double offRotY;
    /* synthetic */ double mainRotX;
    /* synthetic */ double mainY;
    /* synthetic */ double mainRotY;
    /* synthetic */ double mainHandScaleX;
    /* synthetic */ double offX;
    /* synthetic */ double mainHandScaleZ;
    
    public double getMainRotZ() {
        return this.mainRotZ;
    }
    
    public double getOffZ() {
        return this.offZ;
    }
    
    public void setMainHandScaleZ(final double mainHandScaleZ) {
        this.mainHandScaleZ = mainHandScaleZ;
    }
    
    public double getMainY() {
        return this.mainY;
    }
    
    public void setOffHandScaleZ(final double offHandScaleZ) {
        this.offHandScaleZ = offHandScaleZ;
    }
    
    public void setMainRotY(final double mainRotY) {
        this.mainRotY = mainRotY;
    }
    
    public void setMainRotZ(final double mainRotZ) {
        this.mainRotZ = mainRotZ;
    }
    
    public void setOffX(final double offX) {
        this.offX = offX;
    }
    
    public RenderItemEvent(final double mainX, final double mainY, final double mainZ, final double offX, final double offY, final double offZ, final double mainRotX, final double mainRotY, final double mainRotZ, final double offRotX, final double offRotY, final double offRotZ, final double mainHandScaleX, final double mainHandScaleY, final double mainHandScaleZ, final double offHandScaleX, final double offHandScaleY, final double offHandScaleZ) {
        this.mainX = mainX;
        this.mainY = mainY;
        this.mainZ = mainZ;
        this.offX = offX;
        this.offY = offY;
        this.offZ = offZ;
        this.mainRotX = mainRotX;
        this.mainRotY = mainRotY;
        this.mainRotZ = mainRotZ;
        this.offRotX = offRotX;
        this.offRotY = offRotY;
        this.offRotZ = offRotZ;
        this.mainHandScaleX = mainHandScaleX;
        this.mainHandScaleY = mainHandScaleY;
        this.mainHandScaleZ = mainHandScaleZ;
        this.offHandScaleX = offHandScaleX;
        this.offHandScaleY = offHandScaleY;
        this.offHandScaleZ = offHandScaleZ;
    }
    
    public double getMainHandScaleZ() {
        return this.mainHandScaleZ;
    }
    
    public void setOffRotY(final double offRotY) {
        this.offRotY = offRotY;
    }
    
    public double getMainRotX() {
        return this.mainRotX;
    }
    
    public void setMainHandScaleX(final double mainHandScaleX) {
        this.mainHandScaleX = mainHandScaleX;
    }
    
    public void setOffRotX(final double offRotX) {
        this.offRotX = offRotX;
    }
    
    public void setOffRotZ(final double offRotZ) {
        this.offRotZ = offRotZ;
    }
    
    public double getOffX() {
        return this.offX;
    }
    
    public double getOffHandScaleZ() {
        return this.offHandScaleZ;
    }
    
    public double getOffHandScaleY() {
        return this.offHandScaleY;
    }
    
    public void setOffY(final double offY) {
        this.offY = offY;
    }
    
    public void setOffHandScaleY(final double offHandScaleY) {
        this.offHandScaleY = offHandScaleY;
    }
    
    public void setMainHandScaleY(final double mainHandScaleY) {
        this.mainHandScaleY = mainHandScaleY;
    }
    
    public double getMainX() {
        return this.mainX;
    }
    
    public void setMainRotX(final double mainRotX) {
        this.mainRotX = mainRotX;
    }
    
    public double getMainHandScaleY() {
        return this.mainHandScaleY;
    }
    
    public double getMainHandScaleX() {
        return this.mainHandScaleX;
    }
    
    public double getOffY() {
        return this.offY;
    }
    
    public double getMainRotY() {
        return this.mainRotY;
    }
    
    public double getOffRotX() {
        return this.offRotX;
    }
    
    public void setMainX(final double mainX) {
        this.mainX = mainX;
    }
    
    public double getMainZ() {
        return this.mainZ;
    }
    
    public double getOffHandScaleX() {
        return this.offHandScaleX;
    }
    
    public double getOffRotY() {
        return this.offRotY;
    }
    
    public void setMainY(final double mainY) {
        this.mainY = mainY;
    }
    
    public double getOffRotZ() {
        return this.offRotZ;
    }
    
    public void setMainZ(final double mainZ) {
        this.mainZ = mainZ;
    }
    
    public void setOffZ(final double offZ) {
        this.offZ = offZ;
    }
    
    public void setOffHandScaleX(final double offHandScaleX) {
        this.offHandScaleX = offHandScaleX;
    }
}
