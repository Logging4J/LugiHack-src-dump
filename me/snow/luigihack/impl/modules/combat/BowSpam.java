//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.combat;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.item.*;
import net.minecraft.util.math.*;
import net.minecraft.network.*;
import net.minecraft.network.play.client.*;

public class BowSpam extends Module
{
    private final /* synthetic */ Setting<Integer> drawLength;
    
    public BowSpam() {
        super("BowSpam", "", Category.COMBAT, true, false, false);
        this.drawLength = (Setting<Integer>)this.register(new Setting("Draw Length", (Object)3, (Object)3, (Object)21));
    }
    
    @Override
    public void onUpdate() {
        if (BowSpam.mc.player.getHeldItemMainhand().getItem() instanceof ItemBow && BowSpam.mc.player.isHandActive() && BowSpam.mc.player.getItemInUseMaxCount() >= (int)this.drawLength.getValue()) {
            BowSpam.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, BowSpam.mc.player.getHorizontalFacing()));
            BowSpam.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(BowSpam.mc.player.getActiveHand()));
            BowSpam.mc.player.stopActiveHand();
        }
    }
}
