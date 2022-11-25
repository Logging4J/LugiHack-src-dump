//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.player;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.block.*;
import net.minecraft.util.math.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.init.*;
import java.util.*;
import java.awt.*;
import me.snow.luigihack.api.util.*;
import me.snow.luigihack.api.event.events.*;

public class InstantMine extends Module
{
    public final /* synthetic */ Setting<Integer> FinishBlue;
    private /* synthetic */ EnumFacing facing;
    private /* synthetic */ Setting<Boolean> ghostHand;
    public /* synthetic */ Setting<Integer> delay;
    public final /* synthetic */ Setting<Integer> BreakRed;
    private /* synthetic */ boolean cancelStart;
    private static /* synthetic */ InstantMine INSTANCE;
    public final /* synthetic */ Setting<Integer> BreakGreen;
    private final /* synthetic */ List<Block> godBlocks;
    public final /* synthetic */ Setting<Integer> FinishRed;
    public final /* synthetic */ Setting<Integer> BreakBlue;
    private final /* synthetic */ Timer breakSuccess;
    public static /* synthetic */ BlockPos breakPos;
    public final /* synthetic */ Setting<Integer> FinishGreen;
    private /* synthetic */ Timer breakTimer;
    private /* synthetic */ Setting<Boolean> render;
    private /* synthetic */ boolean empty;
    
    public String getDisplayInfo() {
        return this.ghostHand.getValue() ? "Silent" : "Normal";
    }
    
    private void setInstance() {
        InstantMine.INSTANCE = this;
    }
    
    @SubscribeEvent
    public void onBlockEvent(final BlockEvent blockEvent) {
        if (fullNullCheck()) {
            return;
        }
        if (BlockUtil.canBreak(blockEvent.pos)) {
            this.empty = false;
            this.cancelStart = false;
            InstantMine.breakPos = blockEvent.pos;
            this.breakSuccess.reset();
            this.facing = blockEvent.facing;
            if (InstantMine.breakPos != null) {
                InstantMine.mc.player.swingArm(EnumHand.MAIN_HAND);
                InstantMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, InstantMine.breakPos, this.facing));
                this.cancelStart = true;
                InstantMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, InstantMine.breakPos, this.facing));
                blockEvent.setCanceled(true);
            }
        }
    }
    
    public void onUpdate() {
        if (fullNullCheck()) {
            return;
        }
        if (!this.breakTimer.passedMs((long)(int)this.delay.getValue())) {
            try {
                InstantMine.mc.playerController.blockHitDelay = 0;
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            return;
        }
        this.breakTimer.reset();
        if (this.cancelStart && !this.godBlocks.contains(InstantMine.mc.world.getBlockState(InstantMine.breakPos).getBlock())) {
            if ((boolean)this.ghostHand.getValue() && InventoryUtil.getItemHotbar(Items.DIAMOND_PICKAXE) != -1) {
                final int currentItem = InstantMine.mc.player.inventory.currentItem;
                if (InstantMine.mc.world.getBlockState(InstantMine.breakPos).getBlock() == Blocks.OBSIDIAN) {
                    if (this.breakSuccess.passedMs(1234L)) {
                        InstantMine.mc.player.inventory.currentItem = InventoryUtil.getItemHotbar(Items.DIAMOND_PICKAXE);
                        InstantMine.mc.playerController.updateController();
                        InstantMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, InstantMine.breakPos, this.facing));
                        InstantMine.mc.player.inventory.currentItem = currentItem;
                        InstantMine.mc.playerController.updateController();
                    }
                }
                else {
                    InstantMine.mc.player.inventory.currentItem = InventoryUtil.getItemHotbar(Items.DIAMOND_PICKAXE);
                    InstantMine.mc.playerController.updateController();
                    InstantMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, InstantMine.breakPos, this.facing));
                    InstantMine.mc.player.inventory.currentItem = currentItem;
                    InstantMine.mc.playerController.updateController();
                }
            }
            else {
                InstantMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, InstantMine.breakPos, this.facing));
            }
        }
    }
    
    static {
        InstantMine.INSTANCE = new InstantMine();
    }
    
    public InstantMine() {
        super("InstantMine", "InstantMine", Module.Category.PLAYER, true, false, false);
        this.breakSuccess = new Timer();
        this.delay = (Setting<Integer>)this.register(new Setting("Delay", (Object)65, (Object)0, (Object)500));
        this.ghostHand = (Setting<Boolean>)this.register(new Setting("SilentSwitch", (Object)true));
        this.render = (Setting<Boolean>)this.register(new Setting("Render", (Object)true));
        this.BreakRed = (Setting<Integer>)this.register(new Setting("BreakRed", (Object)255, (Object)0, (Object)255));
        this.BreakGreen = (Setting<Integer>)this.register(new Setting("BreakGreen", (Object)0, (Object)0, (Object)255));
        this.BreakBlue = (Setting<Integer>)this.register(new Setting("BreakBlue", (Object)0, (Object)0, (Object)255));
        this.FinishRed = (Setting<Integer>)this.register(new Setting("FinishRed", (Object)0, (Object)0, (Object)255));
        this.FinishGreen = (Setting<Integer>)this.register(new Setting("FinishGreen", (Object)255, (Object)0, (Object)255));
        this.FinishBlue = (Setting<Integer>)this.register(new Setting("FinishBlue", (Object)0, (Object)0, (Object)255));
        this.godBlocks = Arrays.asList(Blocks.AIR, (Block)Blocks.FLOWING_LAVA, (Block)Blocks.LAVA, (Block)Blocks.FLOWING_WATER, (Block)Blocks.WATER, Blocks.BEDROCK);
        this.cancelStart = false;
        this.empty = false;
        this.setInstance();
        this.breakTimer = new Timer();
    }
    
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (fullNullCheck()) {
            return;
        }
        if ((boolean)this.render.getValue() && this.cancelStart) {
            if (this.godBlocks.contains(InstantMine.mc.world.getBlockState(InstantMine.breakPos).getBlock())) {
                this.empty = true;
            }
            final Color color = new Color(this.empty ? ((int)this.FinishRed.getValue()) : ((int)this.BreakRed.getValue()), this.empty ? ((int)this.FinishGreen.getValue()) : ((int)this.BreakGreen.getValue()), this.empty ? ((int)this.FinishBlue.getValue()) : ((int)this.BreakBlue.getValue()), 255);
            RenderUtil.drawBoxESP(InstantMine.breakPos, color, false, color, 1.0f, true, true, 55, false);
        }
    }
    
    public static InstantMine getInstance() {
        if (InstantMine.INSTANCE == null) {
            InstantMine.INSTANCE = new InstantMine();
        }
        return InstantMine.INSTANCE;
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        if (fullNullCheck()) {
            return;
        }
        if (send.getPacket() instanceof CPacketPlayerDigging && ((CPacketPlayerDigging)send.getPacket()).getAction() == CPacketPlayerDigging.Action.START_DESTROY_BLOCK) {
            send.setCanceled(this.cancelStart);
        }
    }
}
