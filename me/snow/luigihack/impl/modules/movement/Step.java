//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.movement;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.entity.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import java.text.*;

public class Step extends Module
{
    private static /* synthetic */ Step INSTANCE;
    public final /* synthetic */ Setting<Double> height;
    public final /* synthetic */ Setting<Mode> mode;
    private /* synthetic */ int ticks;
    public final /* synthetic */ Setting<Boolean> noLiquids;
    public final /* synthetic */ Setting<Boolean> strict;
    public final /* synthetic */ Setting<Boolean> usetimer;
    
    public String getDisplayInfo() {
        return this.mode.currentEnumName();
    }
    
    public static double[] forward(final double n) {
        float moveForward = Step.mc.player.movementInput.moveForward;
        float moveStrafe = Step.mc.player.movementInput.moveStrafe;
        float n2 = Step.mc.player.prevRotationYaw + (Step.mc.player.rotationYaw - Step.mc.player.prevRotationYaw) * Step.mc.getRenderPartialTicks();
        if (moveForward != 0.0f) {
            if (moveStrafe > 0.0f) {
                n2 += ((moveForward > 0.0f) ? -45 : 45);
            }
            else if (moveStrafe < 0.0f) {
                n2 += ((moveForward > 0.0f) ? 45 : -45);
            }
            moveStrafe = 0.0f;
            if (moveForward > 0.0f) {
                moveForward = 1.0f;
            }
            else if (moveForward < 0.0f) {
                moveForward = -1.0f;
            }
        }
        final double sin = Math.sin(Math.toRadians(n2 + 90.0f));
        final double cos = Math.cos(Math.toRadians(n2 + 90.0f));
        return new double[] { moveForward * n * cos + moveStrafe * n * sin, moveForward * n * sin - moveStrafe * n * cos };
    }
    
    public void onTick() {
        if (Step.mc.world == null || Step.mc.player == null) {
            return;
        }
        if ((boolean)this.noLiquids.getValue() && (Step.mc.player.isInWater() || Step.mc.player.isInLava())) {
            return;
        }
        if (this.mode.getValue() == Mode.Normal) {
            if (this.ticks == 0) {
                EntityUtil.resetTimer();
            }
            else {
                --this.ticks;
            }
            final double[] forward = MotionUtil.forward(0.1);
            boolean b = false;
            boolean b2 = false;
            boolean b3 = false;
            boolean b4 = false;
            if (Step.mc.world.getCollisionBoxes((Entity)Step.mc.player, Step.mc.player.getEntityBoundingBox().offset(forward[0], 2.6, forward[1])).isEmpty() && !Step.mc.world.getCollisionBoxes((Entity)Step.mc.player, Step.mc.player.getEntityBoundingBox().offset(forward[0], 2.4, forward[1])).isEmpty()) {
                b = true;
            }
            if (Step.mc.world.getCollisionBoxes((Entity)Step.mc.player, Step.mc.player.getEntityBoundingBox().offset(forward[0], 2.1, forward[1])).isEmpty() && !Step.mc.world.getCollisionBoxes((Entity)Step.mc.player, Step.mc.player.getEntityBoundingBox().offset(forward[0], 1.9, forward[1])).isEmpty()) {
                b2 = true;
            }
            if (Step.mc.world.getCollisionBoxes((Entity)Step.mc.player, Step.mc.player.getEntityBoundingBox().offset(forward[0], 1.6, forward[1])).isEmpty() && !Step.mc.world.getCollisionBoxes((Entity)Step.mc.player, Step.mc.player.getEntityBoundingBox().offset(forward[0], 1.4, forward[1])).isEmpty()) {
                b3 = true;
            }
            if (Step.mc.world.getCollisionBoxes((Entity)Step.mc.player, Step.mc.player.getEntityBoundingBox().offset(forward[0], 1.0, forward[1])).isEmpty() && !Step.mc.world.getCollisionBoxes((Entity)Step.mc.player, Step.mc.player.getEntityBoundingBox().offset(forward[0], 0.6, forward[1])).isEmpty()) {
                b4 = true;
            }
            if (Step.mc.player.collidedHorizontally && (Step.mc.player.moveForward != 0.0f || Step.mc.player.moveStrafing != 0.0f) && Step.mc.player.onGround) {
                if (b4 && (double)this.height.getValue() >= 1.0) {
                    if (this.strict.getValue()) {
                        final double[] array = { 0.42, 0.753, 1.0 };
                        for (int i = 0; i < array.length; ++i) {
                            Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + array[i], Step.mc.player.posZ, Step.mc.player.onGround));
                        }
                    }
                    else {
                        final double[] array2 = { 0.42, 0.753 };
                        for (int j = 0; j < array2.length; ++j) {
                            Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + array2[j], Step.mc.player.posZ, Step.mc.player.onGround));
                        }
                    }
                    if (this.usetimer.getValue()) {
                        EntityUtil.setTimer(0.38f);
                    }
                    Step.mc.player.setPosition(Step.mc.player.posX, Step.mc.player.posY + 1.0, Step.mc.player.posZ);
                    this.ticks = 1;
                }
                if (b3 && (double)this.height.getValue() >= 1.5) {
                    final double[] array3 = { 0.42, 0.75, 1.0, 1.16, 1.23, 1.2 };
                    for (int k = 0; k < array3.length; ++k) {
                        Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + array3[k], Step.mc.player.posZ, Step.mc.player.onGround));
                    }
                    if (this.usetimer.getValue()) {
                        EntityUtil.setTimer(0.38f);
                    }
                    Step.mc.player.setPosition(Step.mc.player.posX, Step.mc.player.posY + 1.5, Step.mc.player.posZ);
                    this.ticks = 1;
                }
                if (b2 && (double)this.height.getValue() >= 2.0) {
                    final double[] array4 = { 0.42, 0.78, 0.63, 0.51, 0.9, 1.21, 1.45, 1.43 };
                    for (int l = 0; l < array4.length; ++l) {
                        Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + array4[l], Step.mc.player.posZ, Step.mc.player.onGround));
                    }
                    if (this.usetimer.getValue()) {
                        EntityUtil.setTimer(0.38f);
                    }
                    Step.mc.player.setPosition(Step.mc.player.posX, Step.mc.player.posY + 2.0, Step.mc.player.posZ);
                    this.ticks = 2;
                }
                if (b && (double)this.height.getValue() >= 2.5) {
                    final double[] array5 = { 0.425, 0.821, 0.699, 0.599, 1.022, 1.372, 1.652, 1.869, 2.019, 1.907 };
                    for (int n = 0; n < array5.length; ++n) {
                        Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + array5[n], Step.mc.player.posZ, Step.mc.player.onGround));
                    }
                    if (this.usetimer.getValue()) {
                        EntityUtil.setTimer(0.2f);
                    }
                    Step.mc.player.setPosition(Step.mc.player.posX, Step.mc.player.posY + 2.5, Step.mc.player.posZ);
                    this.ticks = 2;
                }
            }
        }
        if (this.mode.getValue() == Mode.Vanilla) {
            Step.mc.player.stepHeight = Float.parseFloat(new DecimalFormat("#").format(this.height.getValue()));
        }
    }
    
    public Step() {
        super("Step", "Allows you to walk up blocks as if they were stairs", Module.Category.MOVEMENT, true, false, false);
        this.height = (Setting<Double>)this.register(new Setting("Height", (Object)2.0, (Object)1.0, (Object)10.0));
        this.mode = (Setting<Mode>)this.register(new Setting("Mode", (Object)Mode.Vanilla));
        this.usetimer = (Setting<Boolean>)this.register(new Setting("Timer", (Object)true));
        this.strict = (Setting<Boolean>)this.register(new Setting("Strict", (Object)true));
        this.noLiquids = (Setting<Boolean>)this.register(new Setting("No Liquids", (Object)true));
        this.ticks = 0;
    }
    
    public void onDisable() {
        Step.mc.player.stepHeight = 0.6f;
    }
    
    public static Step getInstance() {
        if (Step.INSTANCE == null) {
            Step.INSTANCE = new Step();
        }
        return Step.INSTANCE;
    }
    
    public enum Mode
    {
        Vanilla, 
        Normal;
    }
}
