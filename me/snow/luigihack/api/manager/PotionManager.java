//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.manager;

import me.snow.luigihack.impl.*;
import net.minecraft.entity.player.*;
import java.util.concurrent.*;
import net.minecraft.client.resources.*;
import me.snow.luigihack.impl.modules.client.*;
import net.minecraft.potion.*;
import java.util.*;
import com.mojang.realmsclient.gui.*;

public class PotionManager extends Feature
{
    private final /* synthetic */ Map<EntityPlayer, PotionList> potions;
    
    public PotionManager() {
        this.potions = new ConcurrentHashMap<EntityPlayer, PotionList>();
    }
    
    public void onTotemPop(final EntityPlayer entityPlayer) {
        this.potions.put(entityPlayer, new PotionList());
    }
    
    public String getColoredPotionString(final PotionEffect potionEffect) {
        final String format = I18n.format(potionEffect.getPotion().getName(), new Object[0]);
        switch (format) {
            case "Jump Boost":
            case "Speed": {
                return String.valueOf(new StringBuilder().append("§b").append(this.getPotionString(potionEffect)));
            }
            case "Resistance":
            case "Strength": {
                return String.valueOf(new StringBuilder().append("§c").append(this.getPotionString(potionEffect)));
            }
            case "Wither":
            case "Slowness":
            case "Weakness": {
                return String.valueOf(new StringBuilder().append("§0").append(this.getPotionString(potionEffect)));
            }
            case "Absorption": {
                return String.valueOf(new StringBuilder().append("§9").append(this.getPotionString(potionEffect)));
            }
            case "Haste":
            case "Fire Resistance": {
                return String.valueOf(new StringBuilder().append("§6").append(this.getPotionString(potionEffect)));
            }
            case "Regeneration": {
                return String.valueOf(new StringBuilder().append("§d").append(this.getPotionString(potionEffect)));
            }
            case "Night Vision":
            case "Poison": {
                return String.valueOf(new StringBuilder().append("§a").append(this.getPotionString(potionEffect)));
            }
            default: {
                return String.valueOf(new StringBuilder().append("§f").append(this.getPotionString(potionEffect)));
            }
        }
    }
    
    public List<PotionEffect> getPlayerPotions(final EntityPlayer entityPlayer) {
        final PotionList list = this.potions.get(entityPlayer);
        List<PotionEffect> effects = new ArrayList<PotionEffect>();
        if (list != null) {
            effects = list.getEffects();
        }
        return effects;
    }
    
    public PotionEffect[] getImportantPotions(final EntityPlayer entityPlayer) {
        final PotionEffect[] array = new PotionEffect[3];
        for (final PotionEffect potionEffect : this.getPlayerPotions(entityPlayer)) {
            final String lowerCase = I18n.format(potionEffect.getPotion().getName(), new Object[0]).toLowerCase();
            switch (lowerCase) {
                case "strength": {
                    array[0] = potionEffect;
                    continue;
                }
                case "weakness": {
                    array[1] = potionEffect;
                    continue;
                }
                case "speed": {
                    array[2] = potionEffect;
                    continue;
                }
            }
        }
        return array;
    }
    
    public void updatePlayer() {
        final PotionList list = new PotionList();
        final Iterator<PotionEffect> iterator = PotionManager.mc.player.getActivePotionEffects().iterator();
        while (iterator.hasNext()) {
            list.addEffect(iterator.next());
        }
        this.potions.put((EntityPlayer)PotionManager.mc.player, list);
    }
    
    public String getTextRadarPotion(final EntityPlayer entityPlayer) {
        final PotionEffect[] importantPotions = this.getImportantPotions(entityPlayer);
        final PotionEffect potionEffect = importantPotions[0];
        final PotionEffect potionEffect2 = importantPotions[1];
        final PotionEffect potionEffect3 = importantPotions[2];
        return String.valueOf(new StringBuilder().append("").append((potionEffect != null) ? String.valueOf(new StringBuilder().append("§c S").append(potionEffect.getAmplifier() + 1).append(" ")) : "").append((potionEffect2 != null) ? "§8 W " : "").append((potionEffect3 != null) ? String.valueOf(new StringBuilder().append("§b S").append(potionEffect3.getAmplifier() + 1).append(" ")) : ""));
    }
    
    public void update() {
        this.updatePlayer();
        if (HUD.getInstance().isOn() && HUD.getInstance().textRadar.getValue() && Global.getInstance().potions.getValue()) {
            final ArrayList<EntityPlayer> list = new ArrayList<EntityPlayer>();
            for (final Map.Entry<EntityPlayer, PotionList> entry : this.potions.entrySet()) {
                boolean b = true;
                for (final EntityPlayer entityPlayer : PotionManager.mc.world.playerEntities) {
                    if (this.potions.get(entityPlayer) == null) {
                        final PotionList list2 = new PotionList();
                        final Iterator iterator3 = entityPlayer.getActivePotionEffects().iterator();
                        while (iterator3.hasNext()) {
                            list2.addEffect(iterator3.next());
                        }
                        this.potions.put(entityPlayer, list2);
                        b = false;
                    }
                    if (!entry.getKey().equals((Object)entityPlayer)) {
                        continue;
                    }
                    b = false;
                }
                if (!b) {
                    continue;
                }
                list.add(entry.getKey());
            }
            final Iterator<EntityPlayer> iterator4 = list.iterator();
            while (iterator4.hasNext()) {
                this.potions.remove(iterator4.next());
            }
        }
    }
    
    public String getTextRadarPotionWithDuration(final EntityPlayer entityPlayer) {
        final PotionEffect[] importantPotions = this.getImportantPotions(entityPlayer);
        final PotionEffect potionEffect = importantPotions[0];
        final PotionEffect obj = importantPotions[1];
        final PotionEffect potionEffect2 = importantPotions[2];
        return String.valueOf(new StringBuilder().append("").append((potionEffect != null) ? String.valueOf(new StringBuilder().append("§c S").append(potionEffect.getAmplifier() + 1).append(" ").append(Potion.getPotionDurationString(potionEffect, 1.0f))) : "").append((obj != null) ? String.valueOf(new StringBuilder().append("§8 W ").append(Potion.getPotionDurationString(obj, 1.0f))) : "").append((potionEffect2 != null) ? String.valueOf(new StringBuilder().append("§b S").append(potionEffect2.getAmplifier() + 1).append(" ").append(Potion.getPotionDurationString((PotionEffect)Objects.requireNonNull(obj), 1.0f))) : ""));
    }
    
    public List<PotionEffect> getOwnPotions() {
        return this.getPlayerPotions((EntityPlayer)PotionManager.mc.player);
    }
    
    public String getPotionString(final PotionEffect potionEffect) {
        return String.valueOf(new StringBuilder().append(I18n.format(potionEffect.getPotion().getName(), new Object[0])).append(" ").append(potionEffect.getAmplifier() + 1).append(" ").append(ChatFormatting.WHITE).append(Potion.getPotionDurationString(potionEffect, 1.0f)));
    }
    
    public void onLogout() {
        this.potions.clear();
    }
    
    public static class PotionList
    {
        private final /* synthetic */ List<PotionEffect> effects;
        
        public void addEffect(final PotionEffect potionEffect) {
            if (potionEffect != null) {
                this.effects.add(potionEffect);
            }
        }
        
        public PotionList() {
            this.effects = new ArrayList<PotionEffect>();
        }
        
        public List<PotionEffect> getEffects() {
            return this.effects;
        }
    }
}
