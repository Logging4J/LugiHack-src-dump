//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.movement;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.client.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.client.entity.*;

public class Flight extends Module
{
    private final /* synthetic */ Setting<Float> speed;
    private final /* synthetic */ Setting<FlightMode> mode;
    private final /* synthetic */ Setting<Boolean> motionstop;
    
    public void onDisable() {
        switch ((FlightMode)this.mode.getValue()) {
            case VANILLA: {
                Flight.mc.player.capabilities.isFlying = false;
                Flight.mc.player.capabilities.setFlySpeed(0.05f);
                if (Flight.mc.player.capabilities.isCreativeMode) {
                    return;
                }
                Flight.mc.player.capabilities.allowFlying = false;
                break;
            }
        }
        if (this.motionstop.getValue()) {
            Flight.mc.player.motionX = 0.0;
            Flight.mc.player.motionZ = 0.0;
        }
    }
    
    public Flight() {
        super("Flight", "Flight.", Module.Category.MOVEMENT, true, false, false);
        this.speed = (Setting<Float>)this.register(new Setting("Speed", (Object)10.0f, (Object)0.0f, (Object)50.0f));
        this.mode = (Setting<FlightMode>)this.register(new Setting("Mode", (Object)FlightMode.VANILLA));
        this.motionstop = (Setting<Boolean>)this.register(new Setting("StopMotion", (Object)true));
    }
    
    public void onUpdate() {
        switch ((FlightMode)this.mode.getValue()) {
            case STATIC: {
                Flight.mc.player.capabilities.isFlying = false;
                Flight.mc.player.motionX = 0.0;
                Flight.mc.player.motionY = 0.0;
                Flight.mc.player.motionZ = 0.0;
                Flight.mc.player.jumpMovementFactor = (float)this.speed.getValue();
                if (Flight.mc.gameSettings.keyBindJump.isKeyDown()) {
                    final EntityPlayerSP player = Flight.mc.player;
                    player.motionY += (float)this.speed.getValue();
                }
                if (Flight.mc.gameSettings.keyBindSneak.isKeyDown()) {
                    final EntityPlayerSP player2 = Flight.mc.player;
                    player2.motionY -= (float)this.speed.getValue();
                    break;
                }
                break;
            }
            case VANILLA: {
                Flight.mc.player.capabilities.setFlySpeed((float)this.speed.getValue() / 100.0f);
                Flight.mc.player.capabilities.isFlying = true;
                if (Flight.mc.player.capabilities.isCreativeMode) {
                    return;
                }
                Flight.mc.player.capabilities.allowFlying = true;
                break;
            }
            case PACKET: {
                final boolean isKeyDown = Flight.mc.gameSettings.keyBindForward.isKeyDown();
                final boolean isKeyDown2 = Flight.mc.gameSettings.keyBindLeft.isKeyDown();
                final boolean isKeyDown3 = Flight.mc.gameSettings.keyBindRight.isKeyDown();
                final boolean isKeyDown4 = Flight.mc.gameSettings.keyBindBack.isKeyDown();
                int n;
                if (isKeyDown2 && isKeyDown3) {
                    n = (isKeyDown ? 0 : (isKeyDown4 ? 180 : -1));
                }
                else if (isKeyDown && isKeyDown4) {
                    n = (isKeyDown2 ? -90 : (isKeyDown3 ? 90 : -1));
                }
                else {
                    n = (isKeyDown2 ? -90 : (isKeyDown3 ? 90 : 0));
                    if (isKeyDown) {
                        n /= 2;
                    }
                    else if (isKeyDown4) {
                        n = 180 - n / 2;
                    }
                }
                if (n != -1 && (isKeyDown || isKeyDown2 || isKeyDown3 || isKeyDown4)) {
                    final float n2 = Flight.mc.player.rotationYaw + n;
                    Flight.mc.player.motionX = EntityUtil.getRelativeX(n2) * 0.20000000298023224;
                    Flight.mc.player.motionZ = EntityUtil.getRelativeZ(n2) * 0.20000000298023224;
                }
                Flight.mc.player.motionY = 0.0;
                Flight.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(Flight.mc.player.posX + Flight.mc.player.motionX, Flight.mc.player.posY + (Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown() ? 0.0622 : 0.0) - (Minecraft.getMinecraft().gameSettings.keyBindSneak.isKeyDown() ? 0.0622 : 0.0), Flight.mc.player.posZ + Flight.mc.player.motionZ, Flight.mc.player.rotationYaw, Flight.mc.player.rotationPitch, false));
                Flight.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(Flight.mc.player.posX + Flight.mc.player.motionX, Flight.mc.player.posY - 42069.0, Flight.mc.player.posZ + Flight.mc.player.motionZ, Flight.mc.player.rotationYaw, Flight.mc.player.rotationPitch, true));
                break;
            }
        }
    }
    
    public double[] moveLooking() {
        return new double[] { Flight.mc.player.rotationYaw * 360.0f / 360.0f * 180.0f / 180.0f, 0.0 };
    }
    
    public String getDisplayInfo() {
        switch ((FlightMode)this.mode.getValue()) {
            case VANILLA: {
                return "Vanilla";
            }
            case STATIC: {
                return "Static";
            }
            case PACKET: {
                return "Packet";
            }
            default: {
                return null;
            }
        }
    }
    
    public void onEnable() {
        if (Flight.mc.player == null) {
            return;
        }
        switch ((FlightMode)this.mode.getValue()) {
            case VANILLA: {
                Flight.mc.player.capabilities.isFlying = true;
                if (Flight.mc.player.capabilities.isCreativeMode) {
                    return;
                }
                Flight.mc.player.capabilities.allowFlying = true;
                break;
            }
        }
    }
    
    public enum FlightMode
    {
        VANILLA, 
        PACKET, 
        STATIC;
    }
}
