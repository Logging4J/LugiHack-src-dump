//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.movement;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class FastSwim extends Module
{
    public /* synthetic */ Setting<Double> waterVertical;
    public /* synthetic */ Setting<Double> waterHorizontal;
    public /* synthetic */ Setting<Double> lavaVertical;
    public /* synthetic */ Setting<Double> lavaHorizontal;
    
    public FastSwim() {
        super("FastSwim", "Swim fast", Module.Category.MOVEMENT, true, false, false);
        this.waterHorizontal = (Setting<Double>)this.register(new Setting("WaterHorizontal", (Object)3.0, (Object)1.0, (Object)20.0));
        this.waterVertical = (Setting<Double>)this.register(new Setting("WaterVertical", (Object)3.0, (Object)1.0, (Object)20.0));
        this.lavaHorizontal = (Setting<Double>)this.register(new Setting("LavaHorizontal", (Object)4.0, (Object)1.0, (Object)20.0));
        this.lavaVertical = (Setting<Double>)this.register(new Setting("LavaVertical", (Object)4.0, (Object)1.0, (Object)20.0));
    }
    
    @SubscribeEvent
    public void onMove(final MoveEvent moveEvent) {
        if (FastSwim.mc.player.isInLava() && !FastSwim.mc.player.onGround) {
            moveEvent.setX(moveEvent.getX() * (double)this.lavaHorizontal.getValue());
            moveEvent.setZ(moveEvent.getZ() * (double)this.lavaHorizontal.getValue());
            moveEvent.setY(moveEvent.getY() * (double)this.lavaVertical.getValue());
        }
        else if (FastSwim.mc.player.isInWater() && !FastSwim.mc.player.onGround) {
            moveEvent.setX(moveEvent.getX() * (double)this.waterHorizontal.getValue());
            moveEvent.setZ(moveEvent.getZ() * (double)this.waterHorizontal.getValue());
            moveEvent.setY(moveEvent.getY() * (double)this.waterVertical.getValue());
        }
    }
}
