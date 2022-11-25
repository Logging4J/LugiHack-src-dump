//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.combat;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.impl.*;
import net.minecraft.init.*;
import me.snow.luigihack.impl.command.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.entity.*;
import me.snow.luigihack.api.util.skid.oyvey.*;
import net.minecraft.network.*;
import net.minecraft.util.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.player.*;
import net.minecraft.network.play.client.*;
import net.minecraft.util.math.*;

public class TrapPhase extends Module
{
    public /* synthetic */ Setting<Boolean> faceObsidian;
    public /* synthetic */ Setting<Float> offset;
    private /* synthetic */ EnumHand oldhand;
    private /* synthetic */ int oldslot;
    public /* synthetic */ Setting<Boolean> silentSwitch;
    public /* synthetic */ Setting<Boolean> packetPlace;
    
    @Override
    public void onTick() {
        if (Feature.nullCheck()) {
            return;
        }
        final int blockHotbar = InventoryUtil.getBlockHotbar(Blocks.IRON_TRAPDOOR);
        final int blockHotbar2 = InventoryUtil.getBlockHotbar(Blocks.OBSIDIAN);
        if (this.faceObsidian.getValue()) {
            if (blockHotbar == -1 || blockHotbar2 == -1) {
                Command.sendMessage("Cannot find materials! disabling");
                this.disable();
                return;
            }
        }
        else if (blockHotbar == -1) {
            Command.sendMessage("Cannot find materials! disabling");
            this.disable();
            return;
        }
        final BlockPos[] array = { new BlockPos(1, 0, 0), new BlockPos(-1, 0, 0), new BlockPos(0, 0, 1), new BlockPos(0, 0, -1) };
        final BlockPos entityPos = EntityUtil.getEntityPos((Entity)TrapPhase.mc.player);
        BlockPos blockPos = null;
        final BlockPos[] array2 = array;
        for (int length = array2.length, i = 0; i < length; ++i) {
            final BlockPos add = entityPos.add((Vec3i)array2[i]);
            if (this.entityCheck(add)) {
                if (!BlockUtil2.getBlock(add).equals(Blocks.AIR)) {
                    blockPos = add;
                }
            }
        }
        if (blockPos == null) {
            Command.sendMessage("Cannot find space! disabling");
            this.disable();
            return;
        }
        this.setItem(blockHotbar);
        final double posX = TrapPhase.mc.player.posX;
        final double posY = TrapPhase.mc.player.posY;
        final double posZ = TrapPhase.mc.player.posZ;
        TrapPhase.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(posX, posY + (float)this.offset.getValue(), posZ, TrapPhase.mc.player.onGround));
        EnumFacing enumFacing = null;
        for (final EnumFacing enumFacing2 : EnumFacing.values()) {
            if (blockPos.add(enumFacing2.getDirectionVec()).equals((Object)entityPos)) {
                enumFacing = enumFacing2;
                break;
            }
        }
        if (this.faceObsidian.getValue()) {
            BlockUtil2.rightClickBlock1(blockPos, enumFacing, new Vec3d(0.5, 0.8, 0.5), (boolean)this.packetPlace.getValue());
            TrapPhase.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(posX, posY, posZ, TrapPhase.mc.player.onGround));
            this.setItem(blockHotbar2);
            TrapPhase.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(posX, posY - 0.8, posZ, false));
            BlockUtil2.rightClickBlock2(entityPos, EnumFacing.UP, (boolean)this.packetPlace.getValue());
            TrapPhase.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(posX, posY, posZ, false));
            this.restoreItem();
            this.disable();
        }
        else {
            BlockUtil2.rightClickBlock1(blockPos, enumFacing, new Vec3d(0.5, 0.8, 0.5), (boolean)this.packetPlace.getValue());
            TrapPhase.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(posX, posY, posZ, TrapPhase.mc.player.onGround));
            this.restoreItem();
            this.disable();
        }
    }
    
    public void setItem(final int currentItem) {
        if (this.silentSwitch.getValue()) {
            this.oldhand = null;
            if (TrapPhase.mc.player.isHandActive()) {
                this.oldhand = TrapPhase.mc.player.getActiveHand();
            }
            this.oldslot = TrapPhase.mc.player.inventory.currentItem;
            TrapPhase.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(currentItem));
        }
        else {
            TrapPhase.mc.player.inventory.currentItem = currentItem;
            TrapPhase.mc.playerController.updateController();
        }
    }
    
    public boolean entityCheck(final BlockPos blockPos) {
        return TrapPhase.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(blockPos), entity -> entity instanceof EntityEnderCrystal || entity instanceof EntityPlayer).isEmpty();
    }
    
    public TrapPhase() {
        super("TrapPhase", "Lags you into a block instantly.", Category.COMBAT, true, false, true);
        this.offset = (Setting<Float>)this.register(new Setting("Offset", (Object)0.2f, (Object)0.0f, (Object)1.0f));
        this.silentSwitch = (Setting<Boolean>)this.register(new Setting("SilentSwitch", (Object)true));
        this.packetPlace = (Setting<Boolean>)this.register(new Setting("PacketPlace", (Object)true));
        this.faceObsidian = (Setting<Boolean>)this.register(new Setting("FaceObsidian", (Object)false));
        this.oldhand = null;
        this.oldslot = -1;
    }
    
    public void restoreItem() {
        if (this.oldslot != -1 && (boolean)this.silentSwitch.getValue()) {
            if (this.oldhand != null) {
                TrapPhase.mc.player.setActiveHand(this.oldhand);
            }
            TrapPhase.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(this.oldslot));
            this.oldslot = -1;
            this.oldhand = null;
        }
    }
}
