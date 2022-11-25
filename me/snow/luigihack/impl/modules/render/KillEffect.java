//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.render;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import java.util.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.effect.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import net.minecraft.init.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.util.*;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class KillEffect extends Module
{
    public /* synthetic */ Setting<Boolean> animals;
    public /* synthetic */ Setting<Boolean> smoke;
    public /* synthetic */ Setting<Boolean> mobs;
    public /* synthetic */ Setting<Integer> numbersThunder;
    public /* synthetic */ Setting<Boolean> firework;
    public /* synthetic */ Setting<Boolean> LOLBADD;
    public /* synthetic */ Setting<Boolean> totemPopSound;
    public /* synthetic */ Setting<Boolean> players;
    public /* synthetic */ Setting<Boolean> totemPop;
    public /* synthetic */ Setting<Integer> timeActive;
    public /* synthetic */ Setting<Boolean> thunder;
    public /* synthetic */ Setting<Boolean> fire;
    public /* synthetic */ Setting<Boolean> lightning;
    /* synthetic */ ArrayList<EntityPlayer> playersDead;
    public /* synthetic */ Setting<Boolean> sound;
    public /* synthetic */ Setting<Integer> numberSound;
    final /* synthetic */ Object sync;
    public /* synthetic */ Setting<Boolean> all;
    public /* synthetic */ Setting<Boolean> water;
    
    public KillEffect() {
        super("KillEffect", "When you kill something it spawns shit.", Module.Category.RENDER, true, false, false);
        this.thunder = (Setting<Boolean>)this.register(new Setting("Thunder", (Object)true));
        this.numbersThunder = (Setting<Integer>)this.register(new Setting("Number Thunder", (Object)1, (Object)1, (Object)10));
        this.sound = (Setting<Boolean>)this.register(new Setting("Sound", (Object)true));
        this.numberSound = (Setting<Integer>)this.register(new Setting("Number Sound", (Object)1, (Object)1, (Object)10));
        this.LOLBADD = (Setting<Boolean>)this.register(new Setting("_______________________________", (Object)false));
        this.timeActive = (Setting<Integer>)this.register(new Setting("TimeActive", (Object)20, (Object)0, (Object)50));
        this.lightning = (Setting<Boolean>)this.register(new Setting("lightning", (Object)true));
        this.totemPop = (Setting<Boolean>)this.register(new Setting("TotemPop", (Object)true));
        this.totemPopSound = (Setting<Boolean>)this.register(new Setting("TotemPopSound", (Object)false));
        this.firework = (Setting<Boolean>)this.register(new Setting("FireWork", (Object)false));
        this.fire = (Setting<Boolean>)this.register(new Setting("Fire", (Object)false));
        this.water = (Setting<Boolean>)this.register(new Setting("Water", (Object)false));
        this.smoke = (Setting<Boolean>)this.register(new Setting("Smoke", (Object)false));
        this.players = (Setting<Boolean>)this.register(new Setting("Players", (Object)true));
        this.animals = (Setting<Boolean>)this.register(new Setting("Animals", (Object)true));
        this.mobs = (Setting<Boolean>)this.register(new Setting("Mobs", (Object)true));
        this.all = (Setting<Boolean>)this.register(new Setting("All", (Object)true));
        this.playersDead = new ArrayList<EntityPlayer>();
        this.sync = new Object();
    }
    
    public void onEnable() {
        this.playersDead.clear();
    }
    
    public boolean shouldRenderParticle(final Entity entity) {
        return entity != KillEffect.mc.player && ((boolean)this.all.getValue() || (entity instanceof EntityPlayer && (boolean)this.players.getValue()) || entity instanceof EntityMob || (entity instanceof EntitySlime && (boolean)this.mobs.getValue()) || (entity instanceof EntityAnimal && (boolean)this.animals.getValue()));
    }
    
    public void totemPop(final Entity entity) {
        KillEffect.mc.effectRenderer.emitParticleAtEntity(entity, EnumParticleTypes.TOTEM, (int)this.timeActive.getValue());
        if (this.totemPopSound.getValue()) {
            KillEffect.mc.world.playSound(entity.posX, entity.posY, entity.posZ, SoundEvents.ITEM_TOTEM_USE, entity.getSoundCategory(), 1.0f, 1.0f, false);
        }
    }
    
    public void onUpdate() {
        if (KillEffect.mc.world == null) {
            this.playersDead.clear();
            return;
        }
        int i = 0;
        int j = 0;
        KillEffect.mc.world.playerEntities.forEach(e -> {
            if (this.playersDead.contains(e)) {
                if (e.getHealth() > 0.0f) {
                    this.playersDead.remove(e);
                }
            }
            else if (e.getHealth() == 0.0f) {
                if (this.thunder.getValue()) {
                    while (i < (int)this.numbersThunder.getValue()) {
                        KillEffect.mc.world.spawnEntity((Entity)new EntityLightningBolt((World)KillEffect.mc.world, e.posX, e.posY, e.posZ, true));
                        ++i;
                    }
                }
                if (this.sound.getValue()) {
                    while (j < (int)this.numberSound.getValue()) {
                        KillEffect.mc.player.playSound(SoundEvents.ENTITY_LIGHTNING_THUNDER, 0.5f, 1.0f);
                        ++j;
                    }
                }
                this.playersDead.add(e);
            }
        });
    }
    
    @SubscribeEvent
    public void onDeath(final LivingDeathEvent livingDeathEvent) {
        if (livingDeathEvent.getEntity() == KillEffect.mc.player) {
            return;
        }
        if (this.shouldRenderParticle(livingDeathEvent.getEntity())) {
            if (this.lightning.getValue()) {
                KillEffect.mc.world.addEntityToWorld(-999, (Entity)new EntityLightningBolt((World)KillEffect.mc.world, livingDeathEvent.getEntity().posX, livingDeathEvent.getEntity().posY, livingDeathEvent.getEntity().posZ, true));
            }
            if (this.totemPop.getValue()) {
                this.totemPop(livingDeathEvent.getEntity());
            }
            if (this.firework.getValue()) {
                KillEffect.mc.effectRenderer.emitParticleAtEntity(livingDeathEvent.getEntity(), EnumParticleTypes.FIREWORKS_SPARK, (int)this.timeActive.getValue());
            }
            if (this.fire.getValue()) {
                KillEffect.mc.effectRenderer.emitParticleAtEntity(livingDeathEvent.getEntity(), EnumParticleTypes.FLAME, (int)this.timeActive.getValue());
                KillEffect.mc.effectRenderer.emitParticleAtEntity(livingDeathEvent.getEntity(), EnumParticleTypes.DRIP_LAVA, (int)this.timeActive.getValue());
            }
            if (this.water.getValue()) {
                KillEffect.mc.effectRenderer.emitParticleAtEntity(livingDeathEvent.getEntity(), EnumParticleTypes.WATER_BUBBLE, (int)this.timeActive.getValue());
                KillEffect.mc.effectRenderer.emitParticleAtEntity(livingDeathEvent.getEntity(), EnumParticleTypes.WATER_DROP, (int)this.timeActive.getValue());
            }
            if (this.smoke.getValue()) {
                KillEffect.mc.effectRenderer.emitParticleAtEntity(livingDeathEvent.getEntity(), EnumParticleTypes.SMOKE_NORMAL, (int)this.timeActive.getValue());
            }
        }
    }
}
