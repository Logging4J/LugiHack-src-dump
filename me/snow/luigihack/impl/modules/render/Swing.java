//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.render;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.util.*;
import net.minecraft.item.*;

public class Swing extends Module
{
    private /* synthetic */ Setting<Hand> hand;
    
    public void onUpdate() {
        if (Swing.mc.world == null) {
            return;
        }
        if (((Hand)this.hand.getValue()).equals(Hand.OFFHAND)) {
            Swing.mc.player.swingingHand = EnumHand.OFF_HAND;
        }
        if (((Hand)this.hand.getValue()).equals(Hand.MAINHAND)) {
            Swing.mc.player.swingingHand = EnumHand.MAIN_HAND;
        }
        if (((Hand)this.hand.getValue()).equals(Hand.PACKETSWING) && Swing.mc.player.getHeldItemMainhand().getItem() instanceof ItemSword && Swing.mc.entityRenderer.itemRenderer.prevEquippedProgressMainHand >= 0.9) {
            Swing.mc.entityRenderer.itemRenderer.equippedProgressMainHand = 1.0f;
            Swing.mc.entityRenderer.itemRenderer.itemStackMainHand = Swing.mc.player.getHeldItemMainhand();
        }
    }
    
    public Swing() {
        super("Swing", "Changes the hand you swing with", Module.Category.RENDER, false, false, false);
        this.hand = (Setting<Hand>)this.register(new Setting("Hand", (Object)Hand.OFFHAND));
    }
    
    public enum Hand
    {
        PACKETSWING, 
        MAINHAND, 
        OFFHAND;
    }
}
