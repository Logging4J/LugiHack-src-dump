//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.misc;

import me.snow.luigihack.impl.modules.*;
import net.minecraft.item.*;
import org.lwjgl.input.*;
import net.minecraft.network.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;

public class BoatPlace extends Module
{
    @Override
    public void onUpdate() {
        if (BoatPlace.mc.player.getHeldItemMainhand().getItem() instanceof ItemBoat && Mouse.isButtonDown(1)) {
            BoatPlace.mc.getConnection().sendPacket((Packet)new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
            BoatPlace.mc.getConnection().sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(BoatPlace.mc.objectMouseOver.getBlockPos(), EnumFacing.SOUTH, EnumHand.MAIN_HAND, 1.0f, 1.0f, 1.0f));
        }
    }
    
    public BoatPlace() {
        super("BoatPlace", "Tries to bypass Anti Boat placement", Category.MISC, true, false, false);
    }
}
