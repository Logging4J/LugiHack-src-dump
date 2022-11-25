//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.player;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.util.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraft.network.play.client.*;
import net.minecraft.init.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.network.*;

public class NoBreakAnimation extends Module
{
    public final /* synthetic */ Setting<Boolean> onlyPickaxe;
    private /* synthetic */ BlockPos lastPos;
    private /* synthetic */ boolean isMining;
    private /* synthetic */ EnumFacing lastFacing;
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        if (this.onlyPickaxe.getValue()) {
            if (send.getPacket() instanceof CPacketPlayerDigging && NoBreakAnimation.mc.player.getHeldItemMainhand().getItem() == Items.DIAMOND_PICKAXE) {
                final CPacketPlayerDigging cPacketPlayerDigging = (CPacketPlayerDigging)send.getPacket();
                for (final Entity entity : NoBreakAnimation.mc.world.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(cPacketPlayerDigging.getPosition()))) {
                    if (entity instanceof EntityEnderCrystal) {
                        this.resetMining();
                        return;
                    }
                    if (entity instanceof EntityLivingBase) {
                        this.resetMining();
                        return;
                    }
                }
                if (cPacketPlayerDigging.getAction().equals((Object)CPacketPlayerDigging.Action.START_DESTROY_BLOCK)) {
                    this.isMining = true;
                    this.setMiningInfo(cPacketPlayerDigging.getPosition(), cPacketPlayerDigging.getFacing());
                }
                if (cPacketPlayerDigging.getAction().equals((Object)CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK)) {
                    this.resetMining();
                }
            }
        }
        else if (send.getPacket() instanceof CPacketPlayerDigging) {
            final CPacketPlayerDigging cPacketPlayerDigging2 = (CPacketPlayerDigging)send.getPacket();
            for (final Entity entity2 : NoBreakAnimation.mc.world.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(cPacketPlayerDigging2.getPosition()))) {
                if (entity2 instanceof EntityEnderCrystal) {
                    this.resetMining();
                    return;
                }
                if (entity2 instanceof EntityLivingBase) {
                    this.resetMining();
                    return;
                }
            }
            if (cPacketPlayerDigging2.getAction().equals((Object)CPacketPlayerDigging.Action.START_DESTROY_BLOCK)) {
                this.isMining = true;
                this.setMiningInfo(cPacketPlayerDigging2.getPosition(), cPacketPlayerDigging2.getFacing());
            }
            if (cPacketPlayerDigging2.getAction().equals((Object)CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK)) {
                this.resetMining();
            }
        }
    }
    
    public NoBreakAnimation() {
        super("NoBreakAnimation", "Prevents serverside break animations.", Module.Category.PLAYER, true, false, false);
        this.isMining = false;
        this.lastPos = null;
        this.lastFacing = null;
        this.onlyPickaxe = (Setting<Boolean>)this.register(new Setting("OnlyPickaxe", (Object)false));
    }
    
    public void resetMining() {
        this.isMining = false;
        this.lastPos = null;
        this.lastFacing = null;
    }
    
    public void onUpdate() {
        if (!NoBreakAnimation.mc.gameSettings.keyBindAttack.isKeyDown()) {
            this.resetMining();
            return;
        }
        if (this.isMining && this.lastPos != null && this.lastFacing != null) {
            NoBreakAnimation.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, this.lastPos, this.lastFacing));
        }
    }
    
    private void setMiningInfo(final BlockPos lastPos, final EnumFacing lastFacing) {
        this.lastPos = lastPos;
        this.lastFacing = lastFacing;
    }
}
