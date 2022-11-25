//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.misc;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.item.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import net.minecraft.init.*;
import net.minecraft.util.*;
import net.minecraft.entity.player.*;
import java.util.*;

public class AutoFrameDupe extends Module
{
    public /* synthetic */ Setting<Integer> ticks;
    private /* synthetic */ int timeout_ticks;
    public /* synthetic */ Setting<Boolean> shulkersonly;
    public /* synthetic */ Setting<Integer> turns;
    public /* synthetic */ Setting<Integer> range;
    
    private int getShulkerSlot() {
        int n = -1;
        for (int i = 0; i < 9; ++i) {
            if (AutoFrameDupe.mc.player.inventory.getStackInSlot(i).getItem() instanceof ItemShulkerBox) {
                n = i;
            }
        }
        return n;
    }
    
    @Override
    public void onUpdate() {
        if (AutoFrameDupe.mc.player != null && AutoFrameDupe.mc.world != null) {
            if (this.shulkersonly.getValue()) {
                final int shulkerSlot = this.getShulkerSlot();
                if (shulkerSlot != -1) {
                    AutoFrameDupe.mc.player.inventory.currentItem = shulkerSlot;
                }
            }
            for (final Entity entity : AutoFrameDupe.mc.world.loadedEntityList) {
                if (entity instanceof EntityItemFrame && AutoFrameDupe.mc.player.getDistance(entity) <= (int)this.range.getValue()) {
                    if (this.timeout_ticks >= (int)this.ticks.getValue()) {
                        if (((EntityItemFrame)entity).getDisplayedItem().getItem() == Items.AIR && !AutoFrameDupe.mc.player.getHeldItemMainhand().isEmpty) {
                            AutoFrameDupe.mc.playerController.interactWithEntity((EntityPlayer)AutoFrameDupe.mc.player, entity, EnumHand.MAIN_HAND);
                        }
                        if (((EntityItemFrame)entity).getDisplayedItem().getItem() != Items.AIR) {
                            for (int i = 0; i < (int)this.turns.getValue(); ++i) {
                                AutoFrameDupe.mc.playerController.interactWithEntity((EntityPlayer)AutoFrameDupe.mc.player, entity, EnumHand.MAIN_HAND);
                            }
                            AutoFrameDupe.mc.playerController.attackEntity((EntityPlayer)AutoFrameDupe.mc.player, entity);
                            this.timeout_ticks = 0;
                        }
                    }
                    ++this.timeout_ticks;
                }
            }
        }
    }
    
    public AutoFrameDupe() {
        super("FrameDupe", "6b dupe.", Category.MISC, true, false, false);
        this.shulkersonly = (Setting<Boolean>)this.register(new Setting("Shulkers Only", (Object)true));
        this.range = (Setting<Integer>)this.register(new Setting("Range", (Object)5, (Object)0, (Object)6));
        this.turns = (Setting<Integer>)this.register(new Setting("Turns", (Object)1, (Object)0, (Object)3));
        this.ticks = (Setting<Integer>)this.register(new Setting("Ticks", (Object)10, (Object)1, (Object)20));
        this.timeout_ticks = 0;
    }
}
