//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.movement;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.api.event.events.*;
import me.snow.luigihack.impl.*;
import net.minecraft.block.material.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import me.snow.luigihack.api.util.*;

public class VanillaSpeed extends Module
{
    public /* synthetic */ Setting<Boolean> turnOff;
    public /* synthetic */ Setting<Boolean> Stepawa;
    public /* synthetic */ Setting<Double> speed;
    public /* synthetic */ Setting<Integer> stepHeight;
    public /* synthetic */ Setting<Boolean> vanilla;
    
    @SubscribeEvent
    public void onStep(final StepEvent stepEvent) {
        if (Feature.fullNullCheck()) {
            this.disable();
            return;
        }
        if (VanillaSpeed.mc.player.onGround && !VanillaSpeed.mc.player.isInsideOfMaterial(Material.WATER) && !VanillaSpeed.mc.player.isInsideOfMaterial(Material.LAVA) && VanillaSpeed.mc.player.collidedVertically && VanillaSpeed.mc.player.fallDistance == 0.0f && !VanillaSpeed.mc.gameSettings.keyBindJump.pressed && !VanillaSpeed.mc.player.isOnLadder() && (boolean)this.Stepawa.getValue()) {
            stepEvent.setHeight((float)(int)this.stepHeight.getValue());
            final double n = VanillaSpeed.mc.player.getEntityBoundingBox().minY - VanillaSpeed.mc.player.posY;
            if (n >= 0.625) {
                if (!(boolean)this.vanilla.getValue()) {
                    this.ncpStep(n);
                }
                if (this.turnOff.getValue()) {
                    this.disable();
                }
            }
        }
        else {
            stepEvent.setHeight(0.6f);
        }
    }
    
    private void ncpStep(final double n) {
        final double posX = VanillaSpeed.mc.player.posX;
        final double posZ = VanillaSpeed.mc.player.posZ;
        double posY = VanillaSpeed.mc.player.posY;
        if (n >= 1.1) {
            if (n < 1.6) {
                final double[] array = { 0.42, 0.33, 0.24, 0.083, -0.078 };
                for (int length = array.length, i = 0; i < length; ++i) {
                    VanillaSpeed.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(posX, posY += array[i], posZ, false));
                }
            }
            else if (n < 2.1) {
                final double[] array2 = { 0.425, 0.821, 0.699, 0.599, 1.022, 1.372, 1.652, 1.869 };
                for (int length2 = array2.length, j = 0; j < length2; ++j) {
                    VanillaSpeed.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(posX, posY + array2[j], posZ, false));
                }
            }
            else {
                final double[] array3 = { 0.425, 0.821, 0.699, 0.599, 1.022, 1.372, 1.652, 1.869, 2.019, 1.907 };
                for (int length3 = array3.length, k = 0; k < length3; ++k) {
                    VanillaSpeed.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(posX, posY + array3[k], posZ, false));
                }
            }
        }
        else {
            double n2 = 0.42;
            double n3 = 0.75;
            if (n != 1.0) {
                n2 *= n;
                n3 *= n;
                if (n2 > 0.425) {
                    n2 = 0.425;
                }
                if (n3 > 0.78) {
                    n3 = 0.78;
                }
                if (n3 < 0.49) {
                    n3 = 0.49;
                }
            }
            VanillaSpeed.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(posX, posY + n2, posZ, false));
            if (posY + n3 < posY + n) {
                VanillaSpeed.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(posX, posY + n3, posZ, false));
            }
        }
    }
    
    public VanillaSpeed() {
        super("VanillaSpeed", "ec.me", Module.Category.MOVEMENT, true, false, false);
        this.speed = (Setting<Double>)this.register(new Setting("Speed", (Object)1.0, (Object)1.0, (Object)20.0));
        this.Stepawa = (Setting<Boolean>)this.register(new Setting("Step", (Object)false));
        this.vanilla = (Setting<Boolean>)this.register(new Setting("Vanilla", (Object)false, p0 -> (boolean)this.Stepawa.getValue()));
        this.stepHeight = (Setting<Integer>)this.register(new Setting("Height", (Object)2, (Object)1, (Object)5, p0 -> (boolean)this.Stepawa.getValue()));
        this.turnOff = (Setting<Boolean>)this.register(new Setting("Disable", (Object)false, p0 -> (boolean)this.Stepawa.getValue()));
    }
    
    public void onUpdate() {
        if (VanillaSpeed.mc.player == null || VanillaSpeed.mc.world == null) {
            return;
        }
        final double[] directionSpeed = MathUtil.directionSpeed((double)this.speed.getValue() / 10.0);
        VanillaSpeed.mc.player.motionX = directionSpeed[0];
        VanillaSpeed.mc.player.motionZ = directionSpeed[1];
    }
}
