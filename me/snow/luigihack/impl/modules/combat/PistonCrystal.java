//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.combat;

import me.snow.luigihack.impl.modules.*;
import net.minecraft.entity.player.*;
import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.api.manager.*;
import net.minecraft.entity.*;
import net.minecraft.network.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.item.*;
import net.minecraft.entity.item.*;
import java.util.*;
import me.snow.luigihack.api.util.skid.oyvey.*;
import net.minecraft.util.math.*;
import net.minecraft.network.play.client.*;
import net.minecraft.util.*;
import net.minecraft.block.*;
import me.snow.luigihack.api.util.*;

public class PistonCrystal extends Module
{
    private /* synthetic */ boolean isSneaking;
    private /* synthetic */ boolean enoughSpace;
    private /* synthetic */ EntityPlayer closestTarget;
    private /* synthetic */ int stage;
    public /* synthetic */ Setting<Boolean> rotate;
    private static /* synthetic */ boolean isSpoofingAngles;
    private /* synthetic */ boolean isHole;
    /* synthetic */ boolean brokenCrystalBug;
    private static /* synthetic */ double yaw;
    public /* synthetic */ Setting<Integer> blocksPerTick;
    public /* synthetic */ Setting<Integer> trapDelay;
    /* synthetic */ Double[][] sur_block;
    /* synthetic */ int[][] disp_surblock;
    private /* synthetic */ int[] slot_mat;
    /* synthetic */ boolean broken;
    private /* synthetic */ boolean hasMoved;
    private /* synthetic */ int[] delayTable;
    public /* synthetic */ Setting<Boolean> antiWeakness;
    public /* synthetic */ Setting<Integer> hitDelay;
    private /* synthetic */ boolean firstRun;
    public /* synthetic */ Setting<Integer> pistonDelay;
    private static /* synthetic */ double pitch;
    /* synthetic */ double[] coordsD;
    private /* synthetic */ int oldSlot;
    private /* synthetic */ structureTemp toPlace;
    private /* synthetic */ int delayTimeTicks;
    private /* synthetic */ boolean noMaterials;
    private static /* synthetic */ PistonCrystal instance;
    public /* synthetic */ Setting<Integer> crystalDelay;
    private /* synthetic */ int stuck;
    /* synthetic */ boolean brokenRedstoneTorch;
    public /* synthetic */ Setting<BreakModes> breakMode;
    public /* synthetic */ Setting<Boolean> blockPlayer;
    public /* synthetic */ Setting<Integer> startDelay;
    public /* synthetic */ Setting<Double> enemyRange;
    
    public static PistonCrystal getInstance() {
        if (PistonCrystal.instance == null) {
            PistonCrystal.instance = new PistonCrystal();
        }
        return PistonCrystal.instance;
    }
    
    private void breakCrystal(final Entity entity) {
        PistonCrystal.mc.playerController.attackEntity((EntityPlayer)PistonCrystal.mc.player, entity);
        PistonCrystal.mc.player.swingArm(EnumHand.MAIN_HAND);
    }
    
    private static void setYawAndPitch(final float n, final float n2) {
        PistonCrystal.yaw = n;
        PistonCrystal.pitch = n2;
        PistonCrystal.isSpoofingAngles = true;
    }
    
    private void breakCrystalPiston(final Entity entity) {
        if (this.antiWeakness.getValue()) {
            PistonCrystal.mc.player.inventory.currentItem = this.slot_mat[4];
        }
        if (this.rotate.getValue()) {
            this.lookAtPacket(entity.posX, entity.posY, entity.posZ, (EntityPlayer)PistonCrystal.mc.player);
        }
        if (((BreakModes)this.breakMode.getValue()).equals(BreakModes.swing)) {
            this.breakCrystal(entity);
            PistonCrystal.mc.player.connection.sendPacket((Packet)new CPacketUseEntity(entity));
            PistonCrystal.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
        if (this.rotate.getValue()) {
            resetRotation();
        }
    }
    
    public PistonCrystal() {
        super("PistonCrystal", "Use Pistons and Crystals to pvp.", Category.COMBAT, true, false, false);
        this.rotate = (Setting<Boolean>)this.register(new Setting("Rotate", (Object)true));
        this.blockPlayer = (Setting<Boolean>)this.register(new Setting("TrapPlayer", (Object)false));
        this.antiWeakness = (Setting<Boolean>)this.register(new Setting("AntiWeakness", (Object)false));
        this.enemyRange = (Setting<Double>)this.register(new Setting("Range", (Object)6.0, (Object)0.0, (Object)6.0));
        this.blocksPerTick = (Setting<Integer>)this.register(new Setting("BlocksPerTick", (Object)4, (Object)0, (Object)20));
        this.startDelay = (Setting<Integer>)this.register(new Setting("StartDelay", (Object)0, (Object)0, (Object)20));
        this.trapDelay = (Setting<Integer>)this.register(new Setting("TrapDelay", (Object)1, (Object)0, (Object)20));
        this.pistonDelay = (Setting<Integer>)this.register(new Setting("PistonDelay", (Object)1, (Object)0, (Object)20));
        this.crystalDelay = (Setting<Integer>)this.register(new Setting("CrystalDelay", (Object)2, (Object)0, (Object)20));
        this.hitDelay = (Setting<Integer>)this.register(new Setting("HitDelay", (Object)6, (Object)0, (Object)20));
        this.breakMode = (Setting<BreakModes>)this.register(new Setting("Break Mode", (Object)BreakModes.swing));
        this.isSneaking = false;
        this.firstRun = false;
        this.noMaterials = false;
        this.hasMoved = false;
        this.isHole = true;
        this.enoughSpace = true;
        this.oldSlot = -1;
        this.disp_surblock = new int[][] { { 1, 0, 0 }, { -1, 0, 0 }, { 0, 0, 1 }, { 0, 0, -1 } };
        this.stuck = 0;
        PistonCrystal.instance = this;
    }
    
    private boolean trapPlayer() {
        int n = 0;
        int n2 = 0;
        if (this.toPlace.to_place.size() <= 0 || this.toPlace.supportBlock <= 0) {
            this.stage = ((this.stage == 0) ? 1 : this.stage);
            return true;
        }
        do {
            final BlockPos blockPos = new BlockPos((Vec3d)this.toPlace.to_place.get(n));
            if (this.placeBlock(new BlockPos(this.closestTarget.getPositionVector()).add(blockPos.getX(), blockPos.getY(), blockPos.getZ()), 0, 0.0, 0.0)) {
                ++n2;
            }
            if (n2 != (int)this.blocksPerTick.getValue()) {
                continue;
            }
            return false;
        } while (++n < this.toPlace.supportBlock);
        this.stage = ((this.stage == 0) ? 1 : this.stage);
        return true;
    }
    
    private boolean createStructure() {
        final structureTemp toPlace = new structureTemp(Double.MAX_VALUE, 0, null);
        int n = 0;
        final int[] array = { (int)PistonCrystal.mc.player.posX, (int)PistonCrystal.mc.player.posY, (int)PistonCrystal.mc.player.posZ };
        if (array[1] - this.closestTarget.posY > -1.0) {
            for (final Double[] array2 : this.sur_block) {
                final double n2 = 0.0;
                final double[] array3 = { array2[0], array2[1] + 1.0, array2[2] };
                final BlockPos blockPos = new BlockPos(array3[0], array3[1], array3[2]);
                final double getDistance = PistonCrystal.mc.player.getDistance(array3[0], array3[1], array3[2]);
                if (n2 < toPlace.distance && (blockPos.getY() != array[1] || array[0] != blockPos.getX() || (Math.abs(array[2] - blockPos.getZ()) > 3 && array[2] != blockPos.getZ()) || Math.abs(array[0] - blockPos.getX()) > 3)) {
                    ++array2[1];
                    if (this.get_block(array3[0], array3[1], array3[2]) instanceof BlockAir) {
                        final double[] array4 = { array3[0] + this.disp_surblock[n][0], array3[1], array3[2] + this.disp_surblock[n][2] };
                        final Block get_block = this.get_block(array4[0], array4[1], array4[2]);
                        if ((get_block instanceof BlockAir || get_block instanceof BlockPistonBase) && this.someoneInCoords(array4[0], array4[1], array4[2])) {
                            boolean b = false;
                            Label_0681: {
                                if (this.rotate.getValue()) {
                                    if ((int)array4[0] == array[0]) {
                                        if (this.closestTarget.posZ > PistonCrystal.mc.player.posZ == this.closestTarget.posZ > array4[2]) {
                                            if (Math.abs((int)this.closestTarget.posZ - (int)PistonCrystal.mc.player.posZ) != 1) {
                                                break Label_0681;
                                            }
                                        }
                                    }
                                    else if ((int)array4[2] == array[2] && ((this.closestTarget.posX > PistonCrystal.mc.player.posX == this.closestTarget.posX > array4[0] && Math.abs((int)this.closestTarget.posX - (int)PistonCrystal.mc.player.posX) != 1) || (Math.abs((int)this.closestTarget.posX - (int)PistonCrystal.mc.player.posX) > 1 && array4[0] > this.closestTarget.posX == array[0] > this.closestTarget.posX))) {
                                        break Label_0681;
                                    }
                                }
                                b = true;
                            }
                            if (b) {
                                int n3 = 0;
                                Label_0872: {
                                    if (this.rotate.getValue()) {
                                        if (array[0] == (int)this.closestTarget.posX || array[2] == (int)this.closestTarget.posZ) {
                                            if (PistonCrystal.mc.player.getDistance(array3[0], array3[1], array3[2]) > 3.5 && array[0] != (int)array3[0]) {
                                                if (array[2] != (int)array3[2]) {
                                                    break Label_0872;
                                                }
                                            }
                                        }
                                        else if (array[0] == (int)array4[0] && Math.abs((int)this.closestTarget.posZ - (int)PistonCrystal.mc.player.posZ) != 1 && (array[2] != (int)array4[2] || Math.abs((int)this.closestTarget.posZ - (int)PistonCrystal.mc.player.posZ) == 1)) {
                                            break Label_0872;
                                        }
                                    }
                                    n3 = 1;
                                }
                                final int n4;
                                if ((n4 = n3) != 0) {
                                    int[] array5 = null;
                                    for (final int[] array6 : this.disp_surblock) {
                                        final double[] array7 = { array2[0] + this.disp_surblock[n][0] + array6[0], array2[1], array2[2] + this.disp_surblock[n][2] + array6[2] };
                                        final int[] array8 = { (int)array7[0], (int)array7[1], (int)array7[2] };
                                        final int[] array9 = { (int)array3[0], (int)array3[1], (int)array3[2] };
                                        if (this.get_block(array7[0], array7[1], array7[2]) instanceof BlockAir && (array8[0] != array9[0] || array8[1] != array9[1] || array9[2] != array8[2]) && this.someoneInCoords(array7[0], array7[1], array7[2])) {
                                            array5 = array6;
                                            break;
                                        }
                                    }
                                    if (array5 != null) {
                                        final ArrayList<Vec3d> list = new ArrayList<Vec3d>();
                                        int n5 = 0;
                                        if (this.get_block(array2[0] + this.disp_surblock[n][0], array2[1] - 1.0, array2[2] + this.disp_surblock[n][2]) instanceof BlockAir) {
                                            list.add(new Vec3d((double)(this.disp_surblock[n][0] * 2), (double)this.disp_surblock[n][1], (double)(this.disp_surblock[n][2] * 2)));
                                            ++n5;
                                        }
                                        if (this.get_block(array2[0] + this.disp_surblock[n][0] + array5[0], array2[1] - 1.0, array2[2] + this.disp_surblock[n][2] + array5[2]) instanceof BlockAir) {
                                            list.add(new Vec3d((double)(this.disp_surblock[n][0] * 2 + array5[0]), (double)this.disp_surblock[n][1], (double)(this.disp_surblock[n][2] * 2 + array5[2])));
                                            ++n5;
                                        }
                                        list.add(new Vec3d((double)(this.disp_surblock[n][0] * 2), (double)(this.disp_surblock[n][1] + 1), (double)(this.disp_surblock[n][2] * 2)));
                                        list.add(new Vec3d((double)this.disp_surblock[n][0], (double)(this.disp_surblock[n][1] + 1), (double)this.disp_surblock[n][2]));
                                        list.add(new Vec3d((double)(this.disp_surblock[n][0] * 2 + array5[0]), (double)(this.disp_surblock[n][1] + 1), (double)(this.disp_surblock[n][2] * 2 + array5[2])));
                                        float n6;
                                        float n7;
                                        if (this.disp_surblock[n][0] != 0) {
                                            n6 = (this.rotate.getValue() ? (this.disp_surblock[n][0] / 2.0f) : ((float)this.disp_surblock[n][0]));
                                            n7 = (this.rotate.getValue() ? ((PistonCrystal.mc.player.getDistanceSq(array4[0], array4[1], array4[2] + 0.5) > PistonCrystal.mc.player.getDistanceSq(array4[0], array4[1], array4[2] - 0.5)) ? -0.5f : 0.5f) : ((float)this.disp_surblock[n][2]));
                                        }
                                        else {
                                            n7 = (this.rotate.getValue() ? (this.disp_surblock[n][2] / 2.0f) : ((float)this.disp_surblock[n][2]));
                                            n6 = (this.rotate.getValue() ? ((PistonCrystal.mc.player.getDistanceSq(array4[0] + 0.5, array4[1], array4[2]) > PistonCrystal.mc.player.getDistanceSq(array4[0] - 0.5, array4[1], array4[2])) ? -0.5f : 0.5f) : ((float)this.disp_surblock[n][0]));
                                        }
                                        toPlace.replaceValues(getDistance, n5, list, -1, n6, n7);
                                    }
                                }
                            }
                        }
                    }
                }
                ++n;
            }
            if (toPlace.to_place != null) {
                if (this.blockPlayer.getValue()) {
                    final Vec3d vec3d = toPlace.to_place.get(toPlace.supportBlock + 1);
                    final int[] array10 = { (int)(-vec3d.x), (int)vec3d.y, (int)(-vec3d.z) };
                    toPlace.to_place.add(0, new Vec3d(0.0, 2.0, 0.0));
                    toPlace.to_place.add(0, new Vec3d((double)array10[0], (double)(array10[1] + 1), (double)array10[2]));
                    toPlace.to_place.add(0, new Vec3d((double)array10[0], (double)array10[1], (double)array10[2]));
                    final structureTemp structureTemp = toPlace;
                    structureTemp.supportBlock += 3;
                }
                this.toPlace = toPlace;
                return true;
            }
        }
        return false;
    }
    
    private Block get_block(final double n, final double n2, final double n3) {
        return PistonCrystal.mc.world.getBlockState(new BlockPos(n, n2, n3)).getBlock();
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        final Packet packet = send.getPacket();
        if (packet instanceof CPacketPlayer && PistonCrystal.isSpoofingAngles) {
            ((CPacketPlayer)packet).yaw = (float)PistonCrystal.yaw;
            ((CPacketPlayer)packet).pitch = (float)PistonCrystal.pitch;
        }
    }
    
    private boolean getMaterialsSlot() {
        if (PistonCrystal.mc.player.getHeldItemOffhand().getItem() instanceof ItemEndCrystal) {
            this.slot_mat[2] = 11;
        }
        for (int i = 0; i < 9; ++i) {
            final ItemStack getStackInSlot = PistonCrystal.mc.player.inventory.getStackInSlot(i);
            if (getStackInSlot != ItemStack.EMPTY) {
                if (getStackInSlot.getItem() instanceof ItemEndCrystal) {
                    this.slot_mat[2] = i;
                }
                else if ((boolean)this.antiWeakness.getValue() && getStackInSlot.getItem() instanceof ItemSword) {
                    this.slot_mat[4] = i;
                }
                else if (getStackInSlot.getItem() instanceof ItemBlock) {
                    final Block getBlock = ((ItemBlock)getStackInSlot.getItem()).getBlock();
                    if (getBlock instanceof BlockObsidian) {
                        this.slot_mat[0] = i;
                    }
                    else if (getBlock instanceof BlockPistonBase) {
                        this.slot_mat[1] = i;
                    }
                    else if (getBlock instanceof BlockRedstoneTorch || getBlock.translationKey.equals("blockRedstone")) {
                        this.slot_mat[3] = i;
                    }
                }
            }
        }
        int n = 0;
        final int[] slot_mat = this.slot_mat;
        for (int length = slot_mat.length, j = 0; j < length; ++j) {
            if (slot_mat[j] != -1) {
                ++n;
            }
        }
        return n == 4 + (((boolean)this.antiWeakness.getValue()) ? 1 : 0);
    }
    
    private void lookAtPacket(final double n, final double n2, final double n3, final EntityPlayer entityPlayer) {
        final double[] calculateLook = calculateLookAt(n, n2, n3, entityPlayer);
        setYawAndPitch((float)calculateLook[0], (float)calculateLook[1]);
    }
    
    @Override
    public void onUpdate() {
        if (PistonCrystal.mc.player == null) {
            this.disable();
            return;
        }
        if (this.firstRun) {
            this.closestTarget = EntityUtil2.getTargetDouble((double)this.enemyRange.getValue());
            if (this.closestTarget == null) {
                return;
            }
            this.firstRun = false;
            if (this.getMaterialsSlot()) {
                if (this.is_in_hole()) {
                    this.enoughSpace = this.createStructure();
                }
                else {
                    this.isHole = false;
                }
            }
            else {
                this.noMaterials = true;
            }
        }
        else {
            if (this.delayTable == null) {
                return;
            }
            if (this.delayTimeTicks < this.delayTable[this.stage]) {
                ++this.delayTimeTicks;
                return;
            }
            this.delayTimeTicks = 0;
        }
        if (this.noMaterials || !this.isHole || !this.enoughSpace || this.hasMoved) {
            this.disable();
            return;
        }
        if (this.trapPlayer()) {
            if (this.stage == 1) {
                this.placeBlock(this.compactBlockPos(this.stage), this.stage, this.toPlace.offsetX, this.toPlace.offsetZ);
                ++this.stage;
            }
            else if (this.stage == 2) {
                final BlockPos compactBlockPos = this.compactBlockPos(this.stage - 1);
                if (!(this.get_block(compactBlockPos.getX(), compactBlockPos.getY(), compactBlockPos.getZ()) instanceof BlockPistonBase)) {
                    --this.stage;
                }
                else if (this.placeBlock(this.compactBlockPos(this.stage), this.stage, this.toPlace.offsetX, this.toPlace.offsetZ)) {
                    ++this.stage;
                }
            }
            else if (this.stage == 3) {
                for (final Entity entity : PistonCrystal.mc.world.loadedEntityList) {
                    if (entity instanceof EntityEnderCrystal && (int)entity.posX == (int)this.toPlace.to_place.get(this.toPlace.supportBlock + 1).x) {
                        if ((int)entity.posZ != (int)this.toPlace.to_place.get(this.toPlace.supportBlock + 1).z) {
                            continue;
                        }
                        --this.stage;
                        break;
                    }
                }
                if (this.stage == 3) {
                    this.placeBlock(this.compactBlockPos(this.stage), this.stage, this.toPlace.offsetX, this.toPlace.offsetZ);
                    ++this.stage;
                }
            }
            else if (this.stage == 4) {
                this.breakCrystal();
            }
        }
    }
    
    public static double[] calculateLookAt(final double n, final double n2, final double n3, final EntityPlayer entityPlayer) {
        final double n4 = entityPlayer.posX - n;
        final double n5 = entityPlayer.posY - n2;
        final double n6 = entityPlayer.posZ - n3;
        final double sqrt = Math.sqrt(n4 * n4 + n5 * n5 + n6 * n6);
        return new double[] { Math.atan2(n6 / sqrt, n4 / sqrt) * 180.0 / 3.141592653589793 + 90.0, Math.asin(n5 / sqrt) * 180.0 / 3.141592653589793 };
    }
    
    private static void resetRotation() {
        if (PistonCrystal.isSpoofingAngles) {
            PistonCrystal.yaw = PistonCrystal.mc.player.rotationYaw;
            PistonCrystal.pitch = PistonCrystal.mc.player.rotationPitch;
            PistonCrystal.isSpoofingAngles = false;
        }
    }
    
    @Override
    public void onDisable() {
        if (PistonCrystal.mc.player == null) {
            return;
        }
        if (this.isSneaking) {
            PistonCrystal.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)PistonCrystal.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
            this.isSneaking = false;
        }
        if (this.oldSlot != PistonCrystal.mc.player.inventory.currentItem && this.oldSlot != -1) {
            PistonCrystal.mc.player.inventory.currentItem = this.oldSlot;
            this.oldSlot = -1;
        }
        this.noMaterials = false;
        this.firstRun = true;
    }
    
    private boolean is_in_hole() {
        this.sur_block = new Double[][] { { this.closestTarget.posX + 1.0, this.closestTarget.posY, this.closestTarget.posZ }, { this.closestTarget.posX - 1.0, this.closestTarget.posY, this.closestTarget.posZ }, { this.closestTarget.posX, this.closestTarget.posY, this.closestTarget.posZ + 1.0 }, { this.closestTarget.posX, this.closestTarget.posY, this.closestTarget.posZ - 1.0 } };
        return !(this.get_block(this.sur_block[0][0], this.sur_block[0][1], this.sur_block[0][2]) instanceof BlockAir) && !(this.get_block(this.sur_block[1][0], this.sur_block[1][1], this.sur_block[1][2]) instanceof BlockAir) && !(this.get_block(this.sur_block[2][0], this.sur_block[2][1], this.sur_block[2][2]) instanceof BlockAir) && !(this.get_block(this.sur_block[3][0], this.sur_block[3][1], this.sur_block[3][2]) instanceof BlockAir);
    }
    
    public void breakCrystal() {
        Entity entity = null;
        for (final Entity next : PistonCrystal.mc.world.loadedEntityList) {
            if (next instanceof EntityEnderCrystal) {
                if ((next.posX != (int)next.posX && (int)next.posX != (int)this.closestTarget.posX) || ((int)((int)next.posX - 0.1) != (int)this.closestTarget.posX && (int)((int)next.posX + 0.1) != (int)this.closestTarget.posX) || (int)next.posZ != (int)this.closestTarget.posZ) {
                    if ((next.posZ != (int)next.posZ && (int)next.posZ != (int)this.closestTarget.posZ) || ((int)((int)next.posZ - 0.1) != (int)this.closestTarget.posZ && (int)((int)next.posZ + 0.1) != (int)this.closestTarget.posZ)) {
                        continue;
                    }
                    if ((int)next.posX != (int)this.closestTarget.posX) {
                        continue;
                    }
                }
                entity = next;
            }
        }
        if (this.broken && entity == null) {
            this.stuck = 0;
            this.stage = 0;
            this.broken = false;
        }
        if (entity != null) {
            this.breakCrystalPiston(entity);
            this.broken = true;
        }
        else if (++this.stuck >= 35) {
            boolean b = false;
            for (final Entity entity2 : PistonCrystal.mc.world.loadedEntityList) {
                if (entity2 instanceof EntityEnderCrystal && (int)entity2.posX == (int)this.toPlace.to_place.get(this.toPlace.supportBlock + 1).x) {
                    if ((int)entity2.posZ != (int)this.toPlace.to_place.get(this.toPlace.supportBlock + 1).z) {
                        continue;
                    }
                    b = true;
                    break;
                }
            }
            if (!b) {
                final BlockPos blockPos = new BlockPos((Vec3d)this.toPlace.to_place.get(this.toPlace.supportBlock + 2));
                final BlockPos add = new BlockPos(this.closestTarget.getPositionVector()).add(blockPos.getX(), blockPos.getY(), blockPos.getZ());
                if (this.brokenRedstoneTorch && this.get_block(add.getX(), add.getY(), add.getZ()) instanceof BlockAir) {
                    this.stage = 1;
                    this.brokenRedstoneTorch = false;
                }
                else {
                    final EnumFacing placeableSide = BlockUtil2.getPlaceableSide(add);
                    if (placeableSide != null) {
                        if (this.rotate.getValue()) {
                            BlockUtil2.faceVectorPacketInstant(new Vec3d((Vec3i)add.offset(placeableSide)).add(0.5, 1.0, 0.5).add(new Vec3d(placeableSide.getOpposite().getDirectionVec()).scale(0.5)));
                        }
                        PistonCrystal.mc.player.swingArm(EnumHand.MAIN_HAND);
                        PistonCrystal.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, add, placeableSide));
                        PistonCrystal.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, add, placeableSide));
                        this.brokenRedstoneTorch = true;
                    }
                }
            }
            else {
                boolean b2 = false;
                for (final Entity entity3 : PistonCrystal.mc.world.loadedEntityList) {
                    if (entity3 instanceof EntityEnderCrystal && (int)entity3.posX == (int)this.toPlace.to_place.get(this.toPlace.supportBlock + 1).x) {
                        if ((int)entity3.posZ != (int)this.toPlace.to_place.get(this.toPlace.supportBlock + 1).z) {
                            continue;
                        }
                        b2 = true;
                        break;
                    }
                }
                this.stuck = 0;
                this.stage = 0;
                this.brokenCrystalBug = false;
                if (b2) {
                    this.breakCrystalPiston(null);
                    this.brokenCrystalBug = true;
                }
            }
        }
    }
    
    private boolean placeBlock(final BlockPos blockPos, final int n, final double n2, final double n3) {
        final Block getBlock = PistonCrystal.mc.world.getBlockState(blockPos).getBlock();
        final EnumFacing placeableSide = BlockUtil2.getPlaceableSide(blockPos);
        if (!(getBlock instanceof BlockAir) && !(getBlock instanceof BlockLiquid)) {
            return false;
        }
        if (placeableSide == null) {
            return false;
        }
        final BlockPos offset = blockPos.offset(placeableSide);
        final EnumFacing getOpposite = placeableSide.getOpposite();
        if (!BlockUtil.canBeClicked(offset)) {
            return false;
        }
        final Vec3d add = new Vec3d((Vec3i)offset).add(0.5 + n2, 1.0, 0.5 + n3).add(new Vec3d(getOpposite.getDirectionVec()).scale(0.5));
        final Block getBlock2 = PistonCrystal.mc.world.getBlockState(offset).getBlock();
        if (PistonCrystal.mc.player.inventory.getStackInSlot(this.slot_mat[n]) != ItemStack.EMPTY) {
            if (PistonCrystal.mc.player.inventory.currentItem != this.slot_mat[n]) {
                PistonCrystal.mc.player.inventory.currentItem = ((this.slot_mat[n] == 11) ? PistonCrystal.mc.player.inventory.currentItem : this.slot_mat[n]);
                final int currentItem = PistonCrystal.mc.player.inventory.currentItem;
            }
            if ((!this.isSneaking && BlockUtil.blackList.contains(getBlock2)) || BlockUtil.shulkerList.contains(getBlock2)) {
                PistonCrystal.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)PistonCrystal.mc.player, CPacketEntityAction.Action.START_SNEAKING));
                this.isSneaking = true;
            }
            if ((boolean)this.rotate.getValue() || n == 1) {
                Vec3d vec3d = add;
                if (!(boolean)this.rotate.getValue() && n == 1) {
                    vec3d = new Vec3d(PistonCrystal.mc.player.posX + n2, PistonCrystal.mc.player.posY, PistonCrystal.mc.player.posZ + n3);
                }
                BlockUtil2.faceVectorPacketInstant(vec3d);
            }
            EnumHand enumHand = EnumHand.MAIN_HAND;
            if (this.slot_mat[n] == 11) {
                enumHand = EnumHand.OFF_HAND;
            }
            PistonCrystal.mc.playerController.processRightClickBlock(PistonCrystal.mc.player, PistonCrystal.mc.world, offset, getOpposite, add, enumHand);
            PistonCrystal.mc.player.swingArm(enumHand);
            return true;
        }
        return false;
    }
    
    private boolean someoneInCoords(final double n, final double n2, final double n3) {
        final int n4 = (int)n;
        final int n5 = (int)n2;
        final int n6 = (int)n3;
        for (final EntityPlayer entityPlayer : PistonCrystal.mc.world.playerEntities) {
            if ((int)entityPlayer.posX == n4 && (int)entityPlayer.posZ == n6 && (int)entityPlayer.posY >= n5 - 1) {
                if ((int)entityPlayer.posY > n5 + 1) {
                    continue;
                }
                return false;
            }
        }
        return true;
    }
    
    public BlockPos compactBlockPos(final int n) {
        final BlockPos blockPos = new BlockPos((Vec3d)this.toPlace.to_place.get(this.toPlace.supportBlock + n - 1));
        return new BlockPos(this.closestTarget.getPositionVector()).add(blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }
    
    @Override
    public void onEnable() {
        this.coordsD = new double[3];
        this.delayTable = new int[] { (int)this.startDelay.getValue(), (int)this.trapDelay.getValue(), (int)this.pistonDelay.getValue(), (int)this.crystalDelay.getValue(), (int)this.hitDelay.getValue() };
        this.toPlace = new structureTemp(0.0, 0, null);
        this.firstRun = true;
        this.isHole = true;
        this.brokenRedstoneTorch = false;
        this.brokenCrystalBug = false;
        this.broken = false;
        this.hasMoved = false;
        this.slot_mat = new int[] { -1, -1, -1, -1, -1 };
        this.stuck = 0;
        this.delayTimeTicks = 0;
        this.stage = 0;
        if (PistonCrystal.mc.player == null) {
            this.disable();
            return;
        }
        this.oldSlot = PistonCrystal.mc.player.inventory.currentItem;
    }
    
    private enum BreakModes
    {
        packet, 
        swing;
    }
    
    static class structureTemp
    {
        public /* synthetic */ int supportBlock;
        public /* synthetic */ int direction;
        public /* synthetic */ List<Vec3d> to_place;
        public /* synthetic */ float offsetZ;
        public /* synthetic */ float offsetX;
        public /* synthetic */ double distance;
        
        public structureTemp(final double distance, final int supportBlock, final List<Vec3d> to_place) {
            this.distance = distance;
            this.supportBlock = supportBlock;
            this.to_place = to_place;
            this.direction = -1;
        }
        
        public void replaceValues(final double distance, final int supportBlock, final List<Vec3d> to_place, final int direction, final float offsetX, final float offsetZ) {
            this.distance = distance;
            this.supportBlock = supportBlock;
            this.to_place = to_place;
            this.direction = direction;
            this.offsetX = offsetX;
            this.offsetZ = offsetZ;
        }
    }
}
