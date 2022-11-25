//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.event.events;

import me.snow.luigihack.api.event.*;
import net.minecraft.item.*;
import net.minecraft.entity.player.*;

public class EatFoodFinishEvent extends EventStage
{
    private /* synthetic */ ItemStack item;
    private /* synthetic */ EntityPlayer player;
    
    public EatFoodFinishEvent(final ItemStack item, final EntityPlayer player) {
        this.item = item;
        this.player = player;
    }
    
    public EntityPlayer getPlayer() {
        return this.player;
    }
    
    public void setItem(final ItemStack item) {
        this.item = item;
    }
    
    public ItemStack getItem() {
        return this.item;
    }
    
    public void setPlayer(final EntityPlayer player) {
        this.player = player;
    }
}
