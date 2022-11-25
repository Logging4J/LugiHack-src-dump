//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.player;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.init.*;
import me.snow.luigihack.api.util.skid.oyvey.*;
import net.minecraft.network.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;

public class SilentAutoXP extends Module
{
    /* synthetic */ int oldRotations;
    /* synthetic */ int xpSlot;
    /* synthetic */ int oldSlot;
    public final /* synthetic */ Setting<Boolean> rotatetofeet;
    public final /* synthetic */ Setting<Bind> xpBind;
    
    public void onEnable() {
        this.oldRotations = (int)SilentAutoXP.mc.player.rotationPitch;
        this.oldSlot = SilentAutoXP.mc.player.inventory.currentItem;
    }
    
    public SilentAutoXP() {
        super("SilentXP", "Switches to Experience Bottles and Throws them at your feet to mend your armor", Module.Category.PLAYER, true, false, false);
        this.rotatetofeet = (Setting<Boolean>)this.register(new Setting("Rotate to Feet", (Object)true));
        this.xpBind = (Setting<Bind>)this.register(new Setting("XpBind", (Object)new Bind(-1)));
    }
    
    public void useExp() {
        this.oldRotations = (int)SilentAutoXP.mc.player.rotationPitch;
        if (InventoryUtil2.find(Items.EXPERIENCE_BOTTLE) >= 0) {
            this.oldSlot = SilentAutoXP.mc.player.inventory.currentItem;
            this.xpSlot = InventoryUtil2.find(Items.EXPERIENCE_BOTTLE);
            SilentAutoXP.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(this.xpSlot));
            if (this.rotatetofeet.getValue()) {
                SilentAutoXP.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(SilentAutoXP.mc.player.cameraYaw, 90.0f, SilentAutoXP.mc.player.onGround));
            }
            SilentAutoXP.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
            SilentAutoXP.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(this.oldSlot));
            if (this.rotatetofeet.getValue()) {
                SilentAutoXP.mc.player.rotationPitch = (float)this.oldRotations;
            }
        }
    }
    
    public void onUpdate() {
        if (((Bind)this.xpBind.getValue()).isDown()) {
            this.useExp();
        }
    }
}
