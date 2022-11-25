//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.player;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.init.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.*;

public class FastUse extends Module
{
    private /* synthetic */ Setting<Boolean> crystals;
    private /* synthetic */ Setting<Boolean> packetCrystal;
    private /* synthetic */ Setting<Boolean> enderc;
    private /* synthetic */ BlockPos mousePos;
    private /* synthetic */ Setting<Boolean> obby;
    private /* synthetic */ Setting<Boolean> strict;
    private /* synthetic */ Setting<Boolean> all;
    private /* synthetic */ Setting<Boolean> exp;
    private /* synthetic */ Setting<Boolean> minecart;
    
    public FastUse() {
        super("FastUse", "Allows you to use items faster", Module.Category.PLAYER, true, false, false);
        this.all = (Setting<Boolean>)this.register(new Setting("All", (Object)false));
        this.obby = (Setting<Boolean>)this.register(new Setting("Obsidian", (Object)false, p0 -> !(boolean)this.all.getValue()));
        this.enderc = (Setting<Boolean>)this.register(new Setting("EnderChest", (Object)false, p0 -> !(boolean)this.all.getValue()));
        this.crystals = (Setting<Boolean>)this.register(new Setting("Crystals", (Object)false, p0 -> !(boolean)this.all.getValue()));
        this.exp = (Setting<Boolean>)this.register(new Setting("Experience", (Object)false, p0 -> !(boolean)this.all.getValue()));
        this.minecart = (Setting<Boolean>)this.register(new Setting("Minecarts", (Object)false, p0 -> !(boolean)this.all.getValue()));
        this.packetCrystal = (Setting<Boolean>)this.register(new Setting("PacketCrystal", (Object)false));
        this.strict = (Setting<Boolean>)this.register(new Setting("Strict", (Object)false));
        this.mousePos = null;
    }
    
    public void onUpdate() {
        if ((boolean)this.strict.getValue() && FastUse.mc.player.ticksExisted % 2 == 0) {
            return;
        }
        if (fullNullCheck()) {
            return;
        }
        if (InventoryUtil.holdingItem((Class)ItemExpBottle.class) && (boolean)this.exp.getValue()) {
            FastUse.mc.rightClickDelayTimer = 0;
        }
        if (InventoryUtil.holdingItem((Class)BlockObsidian.class) && (boolean)this.obby.getValue()) {
            FastUse.mc.rightClickDelayTimer = 0;
        }
        if (InventoryUtil.holdingItem((Class)BlockEnderChest.class) && (boolean)this.enderc.getValue()) {
            FastUse.mc.rightClickDelayTimer = 0;
        }
        if (InventoryUtil.holdingItem((Class)ItemMinecart.class) && (boolean)this.minecart.getValue()) {
            FastUse.mc.rightClickDelayTimer = 0;
        }
        if (this.all.getValue()) {
            FastUse.mc.rightClickDelayTimer = 0;
        }
        if (InventoryUtil.holdingItem((Class)ItemEndCrystal.class) && ((boolean)this.crystals.getValue() || (boolean)this.all.getValue())) {
            FastUse.mc.rightClickDelayTimer = 0;
        }
        if ((boolean)this.packetCrystal.getValue() && FastUse.mc.gameSettings.keyBindUseItem.isKeyDown()) {
            int n = 0;
            if (FastUse.mc.player.getHeldItemOffhand().getItem() != Items.END_CRYSTAL) {
                n = 0;
            }
            final int n2 = n;
            if (n2 != 0 || FastUse.mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL) {
                final RayTraceResult objectMouseOver = FastUse.mc.objectMouseOver;
                if (objectMouseOver == null) {
                    return;
                }
                switch (objectMouseOver.typeOfHit) {
                    case MISS: {
                        this.mousePos = null;
                        break;
                    }
                    case BLOCK: {
                        this.mousePos = FastUse.mc.objectMouseOver.getBlockPos();
                        break;
                    }
                    case ENTITY: {
                        final Entity entityHit;
                        if (this.mousePos == null || (entityHit = objectMouseOver.entityHit) == null) {
                            break;
                        }
                        if (!this.mousePos.equals((Object)new BlockPos(entityHit.posX, entityHit.posY - 1.0, entityHit.posZ))) {
                            break;
                        }
                        FastUse.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(this.mousePos, EnumFacing.DOWN, (n2 != 0) ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
                        break;
                    }
                }
            }
        }
    }
}
