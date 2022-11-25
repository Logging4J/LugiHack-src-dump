//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.movement;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.api.util.skid.oyvey.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraft.network.play.server.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.snow.luigihack.impl.command.*;
import me.snow.luigihack.*;
import java.util.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.init.*;
import net.minecraft.util.math.*;

public class HoleSnap extends Module
{
    private final /* synthetic */ Setting<Boolean> SpeedCheck;
    /* synthetic */ Timer timer;
    private /* synthetic */ Setting<Boolean> motionstop;
    private final /* synthetic */ Setting<Float> range2;
    public /* synthetic */ Setting<Float> timerfactor;
    public /* synthetic */ Setting<Mode> mode;
    private /* synthetic */ int ticks;
    /* synthetic */ HoleUtilSafety.Hole holes;
    private final /* synthetic */ Setting<Float> range;
    
    @SubscribeEvent
    public void onPacketReceive(final PacketEvent.Receive receive) {
        if (this.isDisabled()) {
            return;
        }
        if (receive.getPacket() instanceof SPacketPlayerPosLook) {
            this.disable();
        }
    }
    
    public void onEnable() {
        if (this.mode.getValue() == Mode.Motion && (boolean)this.motionstop.getValue()) {
            HoleSnap.mc.player.motionX = 0.0;
            HoleSnap.mc.player.motionZ = 0.0;
        }
        if ((boolean)this.SpeedCheck.getValue() && SpeedRewrite.getInstance().isOn()) {
            SpeedRewrite.getInstance().disable();
            Command.sendMessage("<HoleSnap> Disable Speed");
        }
        if (fullNullCheck()) {
            return;
        }
        this.timer.reset();
        this.holes = null;
    }
    
    public void onDisable() {
        this.timer.reset();
        this.holes = null;
        HoleSnap.mc.timer.tickLength = 50.0f;
    }
    
    public void onTick() {
        if (this.mode.getValue() == Mode.Instant) {
            final BlockPos blockPos2 = (BlockPos)LuigiHack.holeManager.calcHoles().stream().min(Comparator.comparing(blockPos -> HoleSnap.mc.player.getDistance((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ()))).orElse(null);
            if (blockPos2 != null) {
                if (HoleSnap.mc.player.getDistance((double)blockPos2.getX(), (double)blockPos2.getY(), (double)blockPos2.getZ()) < (float)this.range.getValue() + 1.5) {
                    HoleSnap.mc.player.setPosition(blockPos2.getX() + 0.5, (double)blockPos2.getY(), blockPos2.getZ() + 0.5);
                    HoleSnap.mc.player.setPosition(blockPos2.getX() + 0.5, (double)blockPos2.getY(), blockPos2.getZ() + 0.5);
                    Command.sendMessage("Accepting Teleport");
                }
                else {
                    Command.sendMessage("Out of range. disabling HoleSnap");
                }
            }
            else {
                Command.sendMessage("Unable to find hole, disabling HoleSnap");
            }
            this.disable();
        }
        if (this.mode.getValue() == Mode.Motion) {
            if (fullNullCheck()) {
                return;
            }
            if (EntityUtil.isInLiquid()) {
                this.disable();
                return;
            }
            HoleSnap.mc.timer.tickLength = 50.0f / (float)this.timerfactor.getValue();
            this.holes = RotationUtil.getTargetHoleVec3D((double)(float)this.range2.getValue());
            if (this.holes == null) {
                Command.sendMessage("Unable to find hole, disabling HoleSnap");
                this.disable();
                return;
            }
            if (this.timer.passedMs(500L)) {
                this.disable();
                return;
            }
            if (HoleUtilSafety.isObbyHole(RotationUtil.getPlayerPos()) || HoleUtilSafety.isBedrockHoles(RotationUtil.getPlayerPos())) {
                this.disable();
                return;
            }
            if (HoleSnap.mc.world.getBlockState(this.holes.pos1).getBlock() != Blocks.AIR) {
                this.disable();
                return;
            }
            final BlockPos pos1 = this.holes.pos1;
            final Vec3d getPositionVector = HoleSnap.mc.player.getPositionVector();
            final Vec3d vec3d = new Vec3d(pos1.getX() + 0.5, HoleSnap.mc.player.posY, pos1.getZ() + 0.5);
            final double radians = Math.toRadians(RotationUtil.getRotationTo(getPositionVector, vec3d).x);
            final double distanceTo = getPositionVector.distanceTo(vec3d);
            final double n = HoleSnap.mc.player.onGround ? (-Math.min(0.2805, distanceTo / 2.0)) : (-EntityUtil.getMaxSpeed() + 0.02);
            HoleSnap.mc.player.motionX = -Math.sin(radians) * n;
            HoleSnap.mc.player.motionZ = Math.cos(radians) * n;
        }
    }
    
    public HoleSnap() {
        super("HoleSnap", "Teleport to Hole", Module.Category.MOVEMENT, true, false, false);
        this.range = (Setting<Float>)this.register(new Setting("Range", (Object)0.5f, (Object)0.1f, (Object)5.0f, p0 -> this.mode.getValue() == Mode.Instant));
        this.range2 = (Setting<Float>)this.register(new Setting("Motion Range", (Object)4.0f, (Object)0.1f, (Object)10.0f, p0 -> this.mode.getValue() == Mode.Motion));
        this.mode = (Setting<Mode>)this.register(new Setting("SnapMode", (Object)Mode.Motion));
        this.SpeedCheck = (Setting<Boolean>)this.register(new Setting("Disable Speed", (Object)true, p0 -> this.mode.getValue() == Mode.Motion));
        this.timerfactor = (Setting<Float>)this.register(new Setting("Timer", (Object)2.0f, (Object)1.0f, (Object)5.0f, p0 -> this.mode.getValue() == Mode.Motion));
        this.motionstop = (Setting<Boolean>)this.register(new Setting("StopMotion", (Object)true, p0 -> this.mode.getValue() == Mode.Motion));
        this.timer = new Timer();
        this.ticks = 0;
    }
    
    public enum Mode
    {
        Motion, 
        Instant;
    }
}
