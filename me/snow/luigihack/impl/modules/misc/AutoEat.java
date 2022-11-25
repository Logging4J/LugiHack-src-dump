//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.misc;

import me.snow.luigihack.impl.modules.*;
import net.minecraft.item.*;
import net.minecraft.client.settings.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.*;
import net.minecraft.util.*;

public class AutoEat extends Module
{
    private /* synthetic */ int lastSlot;
    private /* synthetic */ boolean eating;
    
    private boolean isValid(final ItemStack itemStack, final int n) {
        return itemStack.getItem() instanceof ItemFood && 20 - n >= ((ItemFood)itemStack.getItem()).getHealAmount(itemStack);
    }
    
    public AutoEat() {
        super("AutoEat", "eats automatically for lazy ppl", Category.MISC, true, false, false);
    }
    
    @Override
    public void onUpdate() {
        if (this.eating && !AutoEat.mc.player.isHandActive()) {
            if (this.lastSlot != -1) {
                AutoEat.mc.player.inventory.currentItem = this.lastSlot;
                this.lastSlot = -1;
            }
            this.eating = false;
            KeyBinding.setKeyBindState(AutoEat.mc.gameSettings.keyBindUseItem.getKeyCode(), false);
            return;
        }
        if (this.eating) {
            return;
        }
        final FoodStats getFoodStats = AutoEat.mc.player.getFoodStats();
        if (this.isValid(AutoEat.mc.player.getHeldItemOffhand(), getFoodStats.getFoodLevel())) {
            AutoEat.mc.player.setActiveHand(EnumHand.OFF_HAND);
            this.eating = true;
            KeyBinding.setKeyBindState(AutoEat.mc.gameSettings.keyBindUseItem.getKeyCode(), true);
            AutoEat.mc.playerController.processRightClick((EntityPlayer)AutoEat.mc.player, (World)AutoEat.mc.world, EnumHand.MAIN_HAND);
        }
        else {
            for (int i = 0; i < 9; ++i) {
                if (this.isValid(AutoEat.mc.player.inventory.getStackInSlot(i), getFoodStats.getFoodLevel())) {
                    this.lastSlot = AutoEat.mc.player.inventory.currentItem;
                    AutoEat.mc.player.inventory.currentItem = i;
                    this.eating = true;
                    KeyBinding.setKeyBindState(AutoEat.mc.gameSettings.keyBindUseItem.getKeyCode(), true);
                    AutoEat.mc.playerController.processRightClick((EntityPlayer)AutoEat.mc.player, (World)AutoEat.mc.world, EnumHand.MAIN_HAND);
                    return;
                }
            }
        }
    }
}
