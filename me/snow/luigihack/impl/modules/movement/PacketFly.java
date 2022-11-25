//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.movement;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import java.util.*;
import net.minecraft.network.play.server.*;
import net.minecraft.util.math.*;
import me.snow.luigihack.mixin.mixins.accessors.*;
import net.minecraftforge.fml.common.eventhandler.*;
import java.util.concurrent.*;
import me.snow.luigihack.*;
import net.minecraft.network.*;
import net.minecraft.network.play.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.entity.*;
import me.snow.luigihack.api.event.events.*;
import me.snow.luigihack.api.util.*;
import org.lwjgl.input.*;

public class PacketFly extends Module
{
    public /* synthetic */ Setting<PacketFlyMode> phase;
    public /* synthetic */ Setting<Boolean> noPacketFlySlow;
    /* synthetic */ ArrayList<CPacketPlayer> packets;
    public /* synthetic */ Setting<Boolean> bounds;
    public /* synthetic */ Setting<Float> motion;
    /* synthetic */ int factorCounter;
    /* synthetic */ int jitterTicks;
    /* synthetic */ Map<Integer, TimeVec3d> posLooks;
    public /* synthetic */ Setting<Boolean> strict;
    /* synthetic */ float postYaw;
    /* synthetic */ double speedX;
    /* synthetic */ int antiKickTicks;
    public /* synthetic */ Setting<Limit> limit;
    /* synthetic */ double speedY;
    /* synthetic */ int hDelay;
    public /* synthetic */ Setting<Float> factor;
    public /* synthetic */ Setting<Boolean> boost;
    public /* synthetic */ Setting<Boolean> multiAxis;
    /* synthetic */ int vDelay;
    /* synthetic */ int teleportId;
    public /* synthetic */ Setting<Boolean> constrict;
    /* synthetic */ boolean oddJitter;
    /* synthetic */ float postPitch;
    public /* synthetic */ Setting<Bind> facrotize;
    public /* synthetic */ Setting<Float> speed;
    private static final /* synthetic */ Random random;
    public /* synthetic */ Setting<Boolean> jitter;
    /* synthetic */ int limitTicks;
    public /* synthetic */ Setting<Mode> packetMode;
    /* synthetic */ Timer intervalTimer;
    public /* synthetic */ Setting<AntiKick> antiKickMode;
    public /* synthetic */ Setting<Type> type;
    /* synthetic */ boolean limitStrict;
    /* synthetic */ double speedZ;
    /* synthetic */ CPacketPlayer.Position startingOutOfBoundsPos;
    
    public static double randomLimitedHorizontal() {
        final int nextInt = PacketFly.random.nextInt(10);
        if (PacketFly.random.nextBoolean()) {
            return nextInt;
        }
        return -nextInt;
    }
    
    private Vec3d getBoundsVec(final double n, final double n2, final double n3, final Mode mode) {
        switch (mode) {
            case UP: {
                return new Vec3d(PacketFly.mc.player.posX + n, ((boolean)this.bounds.getValue()) ? ((double)(this.strict.getValue() ? 255 : 256)) : (PacketFly.mc.player.posY + 420.0), PacketFly.mc.player.posZ + n3);
            }
            case PRESERVE: {
                return new Vec3d(((boolean)this.bounds.getValue()) ? (PacketFly.mc.player.posX + this.randomHorizontal()) : this.randomHorizontal(), ((boolean)this.strict.getValue()) ? Math.max(PacketFly.mc.player.posY, 2.0) : PacketFly.mc.player.posY, ((boolean)this.bounds.getValue()) ? (PacketFly.mc.player.posZ + this.randomHorizontal()) : this.randomHorizontal());
            }
            case LIMITJITTER: {
                return new Vec3d(PacketFly.mc.player.posX + (this.strict.getValue() ? n : randomLimitedHorizontal()), PacketFly.mc.player.posY + randomLimitedVertical(), PacketFly.mc.player.posZ + (this.strict.getValue() ? n3 : randomLimitedHorizontal()));
            }
            case BYPASS: {
                if (this.bounds.getValue()) {
                    final double n4 = n2 * 510.0;
                    return new Vec3d(PacketFly.mc.player.posX + n, PacketFly.mc.player.posY + ((n4 > ((PacketFly.mc.player.dimension == -1) ? 127 : 255)) ? (-n4) : ((n4 < 1.0) ? (-n4) : n4)), PacketFly.mc.player.posZ + n3);
                }
                return new Vec3d(PacketFly.mc.player.posX + ((n == 0.0) ? (PacketFly.random.nextBoolean() ? -10 : 10) : (n * 38.0)), PacketFly.mc.player.posY + n2, PacketFly.mc.player.posX + ((n3 == 0.0) ? (PacketFly.random.nextBoolean() ? -10 : 10) : (n3 * 38.0)));
            }
            case OBSCURE: {
                return new Vec3d(PacketFly.mc.player.posX + this.randomHorizontal(), Math.max(1.5, Math.min(PacketFly.mc.player.posY + n2, 253.5)), PacketFly.mc.player.posZ + this.randomHorizontal());
            }
            default: {
                return new Vec3d(PacketFly.mc.player.posX + n, ((boolean)this.bounds.getValue()) ? ((double)(((boolean)this.strict.getValue()) ? 1 : 0)) : (PacketFly.mc.player.posY - 1337.0), PacketFly.mc.player.posZ + n3);
            }
        }
    }
    
    @SubscribeEvent
    public void onReceive(final PacketEvent.Receive receive) {
        if (receive.getPacket() instanceof SPacketPlayerPosLook && this.isEnabled() && !fullNullCheck()) {
            if (!(PacketFly.mc.currentScreen instanceof GuiDownloadTerrain)) {
                final SPacketPlayerPosLook sPacketPlayerPosLook = (SPacketPlayerPosLook)receive.getPacket();
                if (PacketFly.mc.player.isEntityAlive()) {
                    if (this.teleportId <= 0) {
                        this.teleportId = ((SPacketPlayerPosLook)receive.getPacket()).getTeleportId();
                    }
                    else if (PacketFly.mc.world.isBlockLoaded(new BlockPos(PacketFly.mc.player.posX, PacketFly.mc.player.posY, PacketFly.mc.player.posZ), false) && this.type.getValue() != Type.SETBACK) {
                        if (this.type.getValue() == Type.DESYNC) {
                            this.posLooks.remove(sPacketPlayerPosLook.getTeleportId());
                            receive.setCanceled(true);
                            if (this.type.getValue() == Type.SLOW) {
                                PacketFly.mc.player.setPosition(sPacketPlayerPosLook.getX(), sPacketPlayerPosLook.getY(), sPacketPlayerPosLook.getZ());
                            }
                            return;
                        }
                        if (this.posLooks.containsKey(sPacketPlayerPosLook.getTeleportId())) {
                            final TimeVec3d timeVec3d = this.posLooks.get(sPacketPlayerPosLook.getTeleportId());
                            if (timeVec3d.x == sPacketPlayerPosLook.getX() && timeVec3d.y == sPacketPlayerPosLook.getY() && timeVec3d.z == sPacketPlayerPosLook.getZ()) {
                                this.posLooks.remove(sPacketPlayerPosLook.getTeleportId());
                                receive.setCanceled(true);
                                if (this.type.getValue() == Type.SLOW) {
                                    PacketFly.mc.player.setPosition(sPacketPlayerPosLook.getX(), sPacketPlayerPosLook.getY(), sPacketPlayerPosLook.getZ());
                                }
                                return;
                            }
                        }
                    }
                }
                ((ISPacketPlayerPosLook)sPacketPlayerPosLook).setYaw(PacketFly.mc.player.rotationYaw);
                ((ISPacketPlayerPosLook)sPacketPlayerPosLook).setPitch(PacketFly.mc.player.rotationPitch);
                sPacketPlayerPosLook.getFlags().remove(SPacketPlayerPosLook.EnumFlags.X_ROT);
                sPacketPlayerPosLook.getFlags().remove(SPacketPlayerPosLook.EnumFlags.Y_ROT);
                this.teleportId = sPacketPlayerPosLook.getTeleportId();
            }
            else {
                this.teleportId = 0;
            }
        }
    }
    
    public PacketFly() {
        super("PacketFly", "Uses packets to allow you to fly and move.", Module.Category.MOVEMENT, true, false, false);
        this.type = (Setting<Type>)this.register(new Setting("Type", (Object)Type.FAST));
        this.packetMode = (Setting<Mode>)this.register(new Setting("PacketMode", (Object)Mode.UP));
        this.strict = (Setting<Boolean>)this.register(new Setting("Strict", (Object)true));
        this.bounds = (Setting<Boolean>)this.register(new Setting("Bounds", (Object)true));
        this.phase = (Setting<PacketFlyMode>)this.register(new Setting("PacketFly", (Object)PacketFlyMode.NCP));
        this.multiAxis = (Setting<Boolean>)this.register(new Setting("MultiAxis", (Object)false));
        this.noPacketFlySlow = (Setting<Boolean>)this.register(new Setting("NoPacketFlySlow", (Object)false));
        this.speed = (Setting<Float>)this.register(new Setting("Speed", (Object)1.0f, (Object)0.1f, (Object)5.0f));
        this.factor = (Setting<Float>)this.register(new Setting("Factor", (Object)1.0f, (Object)0.1f, (Object)10.0f, p0 -> this.type.getValue() == Type.FACTOR || this.type.getValue() == Type.DESYNC));
        this.antiKickMode = (Setting<AntiKick>)this.register(new Setting("AntiKick", (Object)AntiKick.NORMAL));
        this.limit = (Setting<Limit>)this.register(new Setting("Limit", (Object)Limit.NONE));
        this.constrict = (Setting<Boolean>)this.register(new Setting("Constrict", (Object)false));
        this.jitter = (Setting<Boolean>)this.register(new Setting("Jitter", (Object)false));
        this.boost = (Setting<Boolean>)this.register(new Setting("Timer", (Object)false));
        this.facrotize = (Setting<Bind>)this.register(new Setting("Snap", (Object)new Bind(0), p0 -> this.type.getValue() == Type.FACTOR));
        this.motion = (Setting<Float>)this.register(new Setting("Distance", (Object)5.0f, (Object)0.1f, (Object)20.0f, p0 -> this.type.getValue() == Type.FACTOR));
        this.packets = new ArrayList<CPacketPlayer>();
        this.posLooks = new ConcurrentHashMap<Integer, TimeVec3d>();
        this.antiKickTicks = 0;
        this.vDelay = 0;
        this.hDelay = 0;
        this.limitStrict = false;
        this.limitTicks = 0;
        this.jitterTicks = 0;
        this.oddJitter = false;
        this.speedX = 0.0;
        this.speedY = 0.0;
        this.speedZ = 0.0;
        this.postYaw = -400.0f;
        this.postPitch = -400.0f;
        this.factorCounter = 0;
        this.intervalTimer = new Timer();
    }
    
    @SubscribeEvent
    public void onMove(final MoveEvent moveEvent) {
        if (this.type.getValue() != Type.SETBACK && this.teleportId <= 0) {
            return;
        }
        if (!this.isEnabled() || fullNullCheck()) {
            return;
        }
        if (this.type.getValue() != Type.SLOW) {
            moveEvent.setX(this.speedX);
            moveEvent.setY(this.speedY);
            moveEvent.setZ(this.speedZ);
        }
        if ((this.phase.getValue() != PacketFlyMode.NONE && this.phase.getValue() == PacketFlyMode.VANILLA) || this.checkCollisionBox()) {
            PacketFly.mc.player.noClip = true;
        }
    }
    
    private void cleanPosLooks() {
        this.posLooks.forEach((n, timeVec3d) -> {
            if (System.currentTimeMillis() - timeVec3d.getTime() > TimeUnit.SECONDS.toMillis(30L)) {
                this.posLooks.remove(n);
            }
        });
    }
    
    public static double randomLimitedVertical() {
        int nextInt = PacketFly.random.nextInt(22);
        nextInt += 70;
        if (PacketFly.random.nextBoolean()) {
            return nextInt;
        }
        return -nextInt;
    }
    
    public void onDisable() {
        if (nullCheck()) {
            return;
        }
        if (PacketFly.mc.player != null) {
            PacketFly.mc.player.setVelocity(0.0, 0.0, 0.0);
        }
        LuigiHack.timerManager.reset();
    }
    
    public String getDisplayInfo() {
        switch ((Type)this.type.getValue()) {
            case FACTOR: {
                return "Factor";
            }
            case SETBACK: {
                return "SetBack";
            }
            case FAST: {
                return "Fast";
            }
            case SLOW: {
                return "Slow";
            }
            case DESYNC: {
                return "Desync";
            }
            default: {
                return null;
            }
        }
    }
    
    private void sendPackets(final double n, final double n2, final double n3, final Mode mode, final boolean b, final boolean b2) {
        final Vec3d vec3d = new Vec3d(PacketFly.mc.player.posX + n, PacketFly.mc.player.posY + n2, PacketFly.mc.player.posZ + n3);
        final Vec3d boundsVec = this.getBoundsVec(n, n2, n3, mode);
        final CPacketPlayer.Position e = new CPacketPlayer.Position(vec3d.x, vec3d.y, vec3d.z, PacketFly.mc.player.onGround);
        this.packets.add((CPacketPlayer)e);
        PacketFly.mc.player.connection.sendPacket((Packet)e);
        if (this.limit.getValue() != Limit.NONE && this.limitTicks == 0) {
            return;
        }
        final CPacketPlayer.Position e2 = new CPacketPlayer.Position(boundsVec.x, boundsVec.y, boundsVec.z, PacketFly.mc.player.onGround);
        this.packets.add((CPacketPlayer)e2);
        PacketFly.mc.player.connection.sendPacket((Packet)e2);
        if (b) {
            ++this.teleportId;
            if (b2) {
                PacketFly.mc.player.connection.sendPacket((Packet)new CPacketConfirmTeleport(this.teleportId - 1));
            }
            PacketFly.mc.player.connection.sendPacket((Packet)new CPacketConfirmTeleport(this.teleportId));
            this.posLooks.put(this.teleportId, new TimeVec3d(vec3d.x, vec3d.y, vec3d.z, System.currentTimeMillis()));
            if (b2) {
                PacketFly.mc.player.connection.sendPacket((Packet)new CPacketConfirmTeleport(this.teleportId + 1));
            }
        }
    }
    
    public void onUpdate() {
        if (fullNullCheck()) {
            return;
        }
        if (PacketFly.mc.currentScreen instanceof GuiDisconnected || PacketFly.mc.currentScreen instanceof GuiMainMenu || PacketFly.mc.currentScreen instanceof GuiMultiplayer || PacketFly.mc.currentScreen instanceof GuiDownloadTerrain) {
            this.disable();
        }
        if (this.boost.getValue()) {
            LuigiHack.timerManager.setTimer(1.088f);
        }
        else {
            LuigiHack.timerManager.reset();
        }
    }
    
    private boolean checkCollisionBox() {
        return !PacketFly.mc.world.getCollisionBoxes((Entity)PacketFly.mc.player, PacketFly.mc.player.getEntityBoundingBox().expand(0.0, 0.0, 0.0)).isEmpty() || !PacketFly.mc.world.getCollisionBoxes((Entity)PacketFly.mc.player, PacketFly.mc.player.getEntityBoundingBox().offset(0.0, 2.0, 0.0).contract(0.0, 1.99, 0.0)).isEmpty();
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        if (send.getPacket() instanceof CPacketPlayer && !(send.getPacket() instanceof CPacketPlayer.Position) && this.isEnabled() && !fullNullCheck()) {
            send.setCanceled(true);
        }
        if (send.getPacket() instanceof CPacketPlayer && this.isEnabled() && !fullNullCheck()) {
            final CPacketPlayer cPacketPlayer = (CPacketPlayer)send.getPacket();
            if (this.packets.contains(cPacketPlayer)) {
                this.packets.remove(cPacketPlayer);
                return;
            }
            send.setCanceled(true);
        }
    }
    
    public void onEnable() {
        if (fullNullCheck()) {
            this.disable();
            return;
        }
        this.packets.clear();
        this.posLooks.clear();
        this.teleportId = 0;
        this.vDelay = 0;
        this.hDelay = 0;
        this.postYaw = -400.0f;
        this.postPitch = -400.0f;
        this.antiKickTicks = 0;
        this.limitTicks = 0;
        this.jitterTicks = 0;
        this.speedX = 0.0;
        this.speedY = 0.0;
        this.speedZ = 0.0;
        this.oddJitter = false;
        this.startingOutOfBoundsPos = null;
        this.startingOutOfBoundsPos = new CPacketPlayer.Position(this.randomHorizontal(), 1.0, this.randomHorizontal(), PacketFly.mc.player.onGround);
        this.packets.add((CPacketPlayer)this.startingOutOfBoundsPos);
        PacketFly.mc.getConnection().sendPacket((Packet)this.startingOutOfBoundsPos);
    }
    
    @SubscribeEvent
    public void onPush(final PushEvent pushEvent) {
        if (pushEvent.getStage() == 1 && this.isEnabled()) {
            pushEvent.setCanceled(true);
        }
    }
    
    public double randomHorizontal() {
        final int n = PacketFly.random.nextInt(((boolean)this.bounds.getValue()) ? 80 : ((this.packetMode.getValue() == Mode.OBSCURE) ? ((PacketFly.mc.player.ticksExisted % 2 == 0) ? 480 : 100) : 29000000)) + (this.bounds.getValue() ? 5 : 500);
        if (PacketFly.random.nextBoolean()) {
            return n;
        }
        return -n;
    }
    
    @SubscribeEvent
    public void onPlayerWalkingUpdate(final UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (fullNullCheck() || !this.isEnabled()) {
            this.disable();
            return;
        }
        if (PacketFly.mc.player.ticksExisted % 20 == 0) {
            this.cleanPosLooks();
        }
        PacketFly.mc.player.setVelocity(0.0, 0.0, 0.0);
        if (this.teleportId <= 0 && this.type.getValue() != Type.SETBACK) {
            this.startingOutOfBoundsPos = new CPacketPlayer.Position(this.randomHorizontal(), 1.0, this.randomHorizontal(), PacketFly.mc.player.onGround);
            this.packets.add((CPacketPlayer)this.startingOutOfBoundsPos);
            PacketFly.mc.player.connection.sendPacket((Packet)this.startingOutOfBoundsPos);
            return;
        }
        final boolean checkCollisionBox = this.checkCollisionBox();
        this.speedX = 0.0;
        this.speedY = 0.0;
        this.speedZ = 0.0;
        if (PacketFly.mc.gameSettings.keyBindJump.isKeyDown() && (this.hDelay < 1 || ((boolean)this.multiAxis.getValue() && checkCollisionBox))) {
            this.speedY = ((PacketFly.mc.player.ticksExisted % ((this.type.getValue() == Type.SETBACK || this.type.getValue() == Type.SLOW || this.limit.getValue() == Limit.STRICT) ? 10 : 20) == 0) ? ((this.antiKickMode.getValue() != AntiKick.NONE) ? -0.032 : 0.062) : 0.062);
            this.antiKickTicks = 0;
            this.vDelay = 5;
        }
        else if (PacketFly.mc.gameSettings.keyBindSneak.isKeyDown() && (this.hDelay < 1 || ((boolean)this.multiAxis.getValue() && checkCollisionBox))) {
            this.speedY = -0.062;
            this.antiKickTicks = 0;
            this.vDelay = 5;
        }
        if (((boolean)this.multiAxis.getValue() && checkCollisionBox) || !PacketFly.mc.gameSettings.keyBindSneak.isKeyDown() || !PacketFly.mc.gameSettings.keyBindJump.isKeyDown()) {
            if (EntityUtil.isMoving()) {
                final double[] directionSpeed = MathUtil.directionSpeed(((checkCollisionBox && this.phase.getValue() == PacketFlyMode.NCP) ? (this.noPacketFlySlow.getValue() ? (this.multiAxis.getValue() ? 0.0465 : 0.062) : 0.031) : 0.26) * (float)this.speed.getValue());
                if ((directionSpeed[0] != 0.0 || directionSpeed[1] != 0.0) && (this.vDelay < 1 || ((boolean)this.multiAxis.getValue() && checkCollisionBox))) {
                    this.speedX = directionSpeed[0];
                    this.speedZ = directionSpeed[1];
                    this.hDelay = 5;
                }
            }
            if (this.antiKickMode.getValue() != AntiKick.NONE && (this.limit.getValue() == Limit.NONE || this.limitTicks != 0)) {
                if (this.antiKickTicks < ((this.packetMode.getValue() == Mode.BYPASS && !(boolean)this.bounds.getValue()) ? 1 : 3)) {
                    ++this.antiKickTicks;
                }
                else {
                    this.antiKickTicks = 0;
                    if (this.antiKickMode.getValue() != AntiKick.LIMITED || !checkCollisionBox) {
                        double speedY = 0.0;
                        if (this.antiKickMode.getValue() != AntiKick.STRICT) {
                            speedY = -0.04;
                        }
                        this.speedY = speedY;
                    }
                }
            }
        }
        if (checkCollisionBox && ((this.phase.getValue() == PacketFlyMode.NCP && PacketFly.mc.player.moveForward != 0.0) || (PacketFly.mc.player.moveStrafing != 0.0 && this.speedY != 0.0))) {
            this.speedY /= 2.5;
        }
        if (this.limit.getValue() != Limit.NONE) {
            if (this.limitTicks == 0) {
                this.speedX = 0.0;
                this.speedY = 0.0;
                this.speedZ = 0.0;
            }
            else if (this.limitTicks == 2 && (boolean)this.jitter.getValue()) {
                if (this.oddJitter) {
                    this.speedX = 0.0;
                    this.speedY = 0.0;
                    this.speedZ = 0.0;
                }
                this.oddJitter = !this.oddJitter;
            }
        }
        else if ((boolean)this.jitter.getValue() && this.jitterTicks == 7) {
            this.speedX = 0.0;
            this.speedY = 0.0;
            this.speedZ = 0.0;
        }
        switch ((Type)this.type.getValue()) {
            case FAST: {
                PacketFly.mc.player.setVelocity(this.speedX, this.speedY, this.speedZ);
                this.sendPackets(this.speedX, this.speedY, this.speedZ, (Mode)this.packetMode.getValue(), true, false);
                break;
            }
            case SLOW: {
                this.sendPackets(this.speedX, this.speedY, this.speedZ, (Mode)this.packetMode.getValue(), true, false);
                break;
            }
            case SETBACK: {
                PacketFly.mc.player.setVelocity(this.speedX, this.speedY, this.speedZ);
                this.sendPackets(this.speedX, this.speedY, this.speedZ, (Mode)this.packetMode.getValue(), false, false);
                break;
            }
            case FACTOR:
            case DESYNC: {
                float n = (float)this.factor.getValue();
                if (((Bind)this.facrotize.getValue()).getKey() != -1 && Keyboard.isKeyDown(((Bind)this.facrotize.getValue()).getKey()) && this.intervalTimer.passedMs(3500L)) {
                    this.intervalTimer.reset();
                    n = (float)this.motion.getValue();
                }
                int n2 = (int)Math.floor(n);
                ++this.factorCounter;
                if (this.factorCounter > (int)(20.0 / ((n - (double)n2) * 20.0))) {
                    ++n2;
                    this.factorCounter = 0;
                }
                for (int i = 1; i <= n2; ++i) {
                    PacketFly.mc.player.setVelocity(this.speedX * i, this.speedY * i, this.speedZ * i);
                    this.sendPackets(this.speedX * i, this.speedY * i, this.speedZ * i, (Mode)this.packetMode.getValue(), true, false);
                }
                this.speedX = PacketFly.mc.player.motionX;
                this.speedY = PacketFly.mc.player.motionY;
                this.speedZ = PacketFly.mc.player.motionZ;
                break;
            }
        }
        --this.vDelay;
        --this.hDelay;
        if ((boolean)this.constrict.getValue() && (this.limit.getValue() == Limit.NONE || this.limitTicks > 1)) {
            PacketFly.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PacketFly.mc.player.posX, PacketFly.mc.player.posY, PacketFly.mc.player.posZ, false));
        }
        ++this.limitTicks;
        ++this.jitterTicks;
        if (this.limitTicks > ((this.limit.getValue() == Limit.STRICT) ? (this.limitStrict ? 1 : 2) : 3)) {
            this.limitTicks = 0;
            boolean limitStrict = false;
            if (this.limitStrict) {
                limitStrict = false;
            }
            this.limitStrict = limitStrict;
        }
        if (this.jitterTicks > 7) {
            this.jitterTicks = 0;
        }
    }
    
    static {
        random = new Random();
    }
    
    public enum Limit
    {
        STRONG, 
        STRICT, 
        NONE;
    }
    
    private enum AntiKick
    {
        STRICT, 
        LIMITED, 
        NONE, 
        NORMAL;
    }
    
    public enum Mode
    {
        BYPASS, 
        DOWN, 
        LIMITJITTER, 
        OBSCURE, 
        UP, 
        PRESERVE;
    }
    
    public enum Type
    {
        SETBACK, 
        FACTOR, 
        SLOW, 
        FAST, 
        DESYNC;
    }
    
    public enum PacketFlyMode
    {
        NONE, 
        VANILLA, 
        NCP;
    }
}
