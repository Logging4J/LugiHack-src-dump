//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.misc;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import java.util.*;
import net.minecraft.network.*;
import net.minecraft.inventory.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraft.network.play.client.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;

public class AntiAFK extends Module
{
    private final /* synthetic */ Setting<Boolean> swing;
    private final /* synthetic */ Setting<Boolean> turn;
    private final /* synthetic */ Setting<Boolean> window;
    private final /* synthetic */ Random random;
    private final /* synthetic */ Setting<Boolean> tabcomplete;
    private final /* synthetic */ Setting<Boolean> move;
    private final /* synthetic */ Setting<Boolean> sneak;
    private final /* synthetic */ Setting<Boolean> jump;
    private final /* synthetic */ Setting<Boolean> stats;
    private final /* synthetic */ Setting<Boolean> dig;
    private final /* synthetic */ Setting<Boolean> interact;
    private final /* synthetic */ Setting<Boolean> msgs;
    private final /* synthetic */ Setting<Boolean> swap;
    
    @Override
    public void onUpdate() {
        if (AntiAFK.mc.player.ticksExisted % 45 == 0 && (boolean)this.swing.getValue()) {
            AntiAFK.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
        if (AntiAFK.mc.player.ticksExisted % 20 == 0 && (boolean)this.turn.getValue()) {
            AntiAFK.mc.player.rotationYaw = (float)(this.random.nextInt(360) - 180);
        }
        if (AntiAFK.mc.player.ticksExisted % 60 == 0 && (boolean)this.jump.getValue() && AntiAFK.mc.player.onGround) {
            AntiAFK.mc.player.jump();
        }
        if (AntiAFK.mc.player.ticksExisted % 50 == 0 && (boolean)this.sneak.getValue() && !AntiAFK.mc.player.isSneaking()) {
            AntiAFK.mc.player.movementInput.sneak = true;
        }
        if (AntiAFK.mc.player.ticksExisted % 52.5 == 0.0 && (boolean)this.sneak.getValue() && AntiAFK.mc.player.isSneaking()) {
            AntiAFK.mc.player.movementInput.sneak = false;
        }
        final BlockPos getBlockPos;
        if (AntiAFK.mc.player.ticksExisted % 30 == 0 && (boolean)this.interact.getValue() && !AntiAFK.mc.world.isAirBlock(getBlockPos = AntiAFK.mc.objectMouseOver.getBlockPos())) {
            AntiAFK.mc.playerController.clickBlock(getBlockPos, AntiAFK.mc.objectMouseOver.sideHit);
        }
        if (AntiAFK.mc.player.ticksExisted % 80 == 0 && (boolean)this.tabcomplete.getValue() && !AntiAFK.mc.player.isDead) {
            AntiAFK.mc.player.connection.sendPacket((Packet)new CPacketTabComplete(String.valueOf(new StringBuilder().append("/").append(UUID.randomUUID().toString().replace('-', 'v'))), AntiAFK.mc.player.getPosition(), false));
        }
        if (AntiAFK.mc.player.ticksExisted % 200 == 0 && (boolean)this.msgs.getValue() && !AntiAFK.mc.player.isDead) {
            AntiAFK.mc.player.sendChatMessage(String.valueOf(new StringBuilder().append("LuigiHack AntiAFK ").append(this.random.nextInt())));
        }
        if (AntiAFK.mc.player.ticksExisted % 300 == 0 && (boolean)this.stats.getValue() && !AntiAFK.mc.player.isDead) {
            AntiAFK.mc.player.sendChatMessage("/stats");
        }
        if (AntiAFK.mc.player.ticksExisted % 125 == 0 && (boolean)this.window.getValue() && !AntiAFK.mc.player.isDead) {
            AntiAFK.mc.player.connection.sendPacket((Packet)new CPacketClickWindow(1, 1, 1, ClickType.CLONE, new ItemStack(Blocks.OBSIDIAN), (short)1));
        }
        if (AntiAFK.mc.player.ticksExisted % 70 == 0 && (boolean)this.swap.getValue() && !AntiAFK.mc.player.isDead) {
            AntiAFK.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.SWAP_HELD_ITEMS, AntiAFK.mc.player.getPosition(), EnumFacing.DOWN));
        }
        if (AntiAFK.mc.player.ticksExisted % 50 == 0 && (boolean)this.dig.getValue()) {
            AntiAFK.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, AntiAFK.mc.player.getPosition(), EnumFacing.DOWN));
        }
        if (AntiAFK.mc.player.ticksExisted % 150 == 0 && (boolean)this.move.getValue()) {
            AntiAFK.mc.gameSettings.keyBindForward.pressed = true;
            AntiAFK.mc.gameSettings.keyBindBack.pressed = true;
            AntiAFK.mc.gameSettings.keyBindRight.pressed = true;
            AntiAFK.mc.gameSettings.keyBindLeft.pressed = true;
        }
    }
    
    public AntiAFK() {
        super("AntiAFK", "Attempts to stop the server from kicking u when ur afk.", Category.MISC, true, false, false);
        this.swing = (Setting<Boolean>)this.register(new Setting("Swing", (Object)true));
        this.turn = (Setting<Boolean>)this.register(new Setting("Turn", (Object)true));
        this.jump = (Setting<Boolean>)this.register(new Setting("Jump", (Object)true));
        this.sneak = (Setting<Boolean>)this.register(new Setting("Sneak", (Object)true));
        this.interact = (Setting<Boolean>)this.register(new Setting("InteractBlock", (Object)false));
        this.tabcomplete = (Setting<Boolean>)this.register(new Setting("TabComplete", (Object)true));
        this.msgs = (Setting<Boolean>)this.register(new Setting("ChatMsgs", (Object)true));
        this.stats = (Setting<Boolean>)this.register(new Setting("Stats", (Object)true));
        this.window = (Setting<Boolean>)this.register(new Setting("WindowClick", (Object)true));
        this.swap = (Setting<Boolean>)this.register(new Setting("ItemSwap", (Object)true));
        this.dig = (Setting<Boolean>)this.register(new Setting("HitBlock", (Object)true));
        this.move = (Setting<Boolean>)this.register(new Setting("Move", (Object)true));
        this.random = new Random();
    }
}
