//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.misc;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraft.network.play.client.*;
import net.minecraft.init.*;
import net.minecraft.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class BuildHeight extends Module
{
    private final /* synthetic */ Setting<Integer> height;
    private final /* synthetic */ Setting<Boolean> crystals;
    
    public BuildHeight() {
        super("BuildHeight", "Allows you to place at build height", Category.MISC, true, false, false);
        this.height = (Setting<Integer>)this.register(new Setting("Height", (Object)255, (Object)0, (Object)255));
        this.crystals = (Setting<Boolean>)this.register(new Setting("CrystalOnly", (Object)false));
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        final CPacketPlayerTryUseItemOnBlock cPacketPlayerTryUseItemOnBlock;
        if (send.getStage() == 0 && send.getPacket() instanceof CPacketPlayerTryUseItemOnBlock && (cPacketPlayerTryUseItemOnBlock = (CPacketPlayerTryUseItemOnBlock)send.getPacket()).getPos().getY() >= (int)this.height.getValue() && (!(boolean)this.crystals.getValue() || BuildHeight.mc.player.getHeldItem(cPacketPlayerTryUseItemOnBlock.getHand()).getItem() == Items.END_CRYSTAL) && cPacketPlayerTryUseItemOnBlock.getDirection() == EnumFacing.UP) {
            cPacketPlayerTryUseItemOnBlock.placedBlockDirection = EnumFacing.DOWN;
        }
    }
}
