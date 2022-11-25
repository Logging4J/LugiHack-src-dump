//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.combat;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import java.util.*;
import me.snow.luigihack.api.event.events.*;
import me.snow.luigihack.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.init.*;
import net.minecraft.util.*;
import net.minecraft.item.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.inventory.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;

public class Killaura extends Module
{
    public /* synthetic */ Setting<Boolean> stay;
    public /* synthetic */ Setting<Boolean> sneak;
    public /* synthetic */ Setting<Float> range;
    public /* synthetic */ Setting<Boolean> mobs;
    public static /* synthetic */ Entity target;
    public /* synthetic */ Setting<Boolean> projectiles;
    public /* synthetic */ Setting<Boolean> armorBreak;
    public /* synthetic */ Setting<Float> raytrace;
    public /* synthetic */ Setting<Boolean> delay;
    public /* synthetic */ Setting<Boolean> eating;
    public /* synthetic */ Setting<Boolean> vehicles;
    public /* synthetic */ Setting<Boolean> onlySharp;
    private final /* synthetic */ Timer timer;
    public /* synthetic */ Setting<Boolean> animals;
    public /* synthetic */ Setting<Boolean> packet;
    private final /* synthetic */ Setting<TargetMode> targetMode;
    public /* synthetic */ Setting<Boolean> autoSwitch;
    public /* synthetic */ Setting<Boolean> players;
    public /* synthetic */ Setting<Boolean> tps;
    public /* synthetic */ Setting<Float> health;
    public /* synthetic */ Setting<Boolean> rotate;
    
    @Override
    public void onTick() {
        if (!(boolean)this.rotate.getValue()) {
            this.doKillaura();
        }
    }
    
    private Entity getTarget() {
        Object o = null;
        double n = (float)this.range.getValue();
        double n2 = 36.0;
        for (final Entity entity : Killaura.mc.world.loadedEntityList) {
            if ((((boolean)this.players.getValue() && entity instanceof EntityPlayer) || ((boolean)this.animals.getValue() && EntityUtil.isPassive(entity)) || ((boolean)this.mobs.getValue() && EntityUtil.isMobAggressive(entity)) || ((boolean)this.vehicles.getValue() && EntityUtil.isVehicle(entity)) || ((boolean)this.projectiles.getValue() && EntityUtil.isProjectile(entity))) && (!(entity instanceof EntityLivingBase) || !EntityUtil.isntValid(entity, n))) {
                if (!Killaura.mc.player.canEntityBeSeen(entity) && !EntityUtil.canEntityFeetBeSeen(entity) && Killaura.mc.player.getDistanceSq(entity) > MathUtil.square((float)this.raytrace.getValue())) {
                    continue;
                }
                if (o == null) {
                    o = entity;
                    n = Killaura.mc.player.getDistanceSq(entity);
                    n2 = EntityUtil.getHealth(entity);
                }
                else {
                    if (entity instanceof EntityPlayer && DamageUtil.isArmorLow((EntityPlayer)entity, 18)) {
                        o = entity;
                        break;
                    }
                    if (this.targetMode.getValue() == TargetMode.SMART && EntityUtil.getHealth(entity) < (float)this.health.getValue()) {
                        o = entity;
                        break;
                    }
                    if (this.targetMode.getValue() != TargetMode.HEALTH && Killaura.mc.player.getDistanceSq(entity) < n) {
                        o = entity;
                        n = Killaura.mc.player.getDistanceSq(entity);
                        n2 = EntityUtil.getHealth(entity);
                    }
                    if (this.targetMode.getValue() != TargetMode.HEALTH) {
                        continue;
                    }
                    if (EntityUtil.getHealth(entity) >= n2) {
                        continue;
                    }
                    o = entity;
                    n = Killaura.mc.player.getDistanceSq(entity);
                    n2 = EntityUtil.getHealth(entity);
                }
            }
        }
        return (Entity)o;
    }
    
    @SubscribeEvent
    public void onUpdateWalkingPlayerEvent(final UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (updateWalkingPlayerEvent.getStage() == 0 && (boolean)this.rotate.getValue()) {
            if ((boolean)this.stay.getValue() && Killaura.target != null) {
                LuigiHack.rotationManager.lookAtEntity(Killaura.target);
            }
            this.doKillaura();
        }
    }
    
    public Killaura() {
        super("Killaura", "Kills aura.", Category.COMBAT, true, false, false);
        this.timer = new Timer();
        this.range = (Setting<Float>)this.register(new Setting("Range", (Object)6.0f, (Object)0.1f, (Object)7.0f));
        this.raytrace = (Setting<Float>)this.register(new Setting("WallRange", (Object)6.0f, (Object)0.1f, (Object)7.0f, "Wall Range."));
        this.targetMode = (Setting<TargetMode>)this.register(new Setting("Target", (Object)TargetMode.CLOSEST));
        this.health = (Setting<Float>)this.register(new Setting("Health", (Object)6.0f, (Object)0.1f, (Object)36.0f, p0 -> this.targetMode.getValue() == TargetMode.SMART));
        this.autoSwitch = (Setting<Boolean>)this.register(new Setting("AutoSwitch", (Object)false));
        this.delay = (Setting<Boolean>)this.register(new Setting("Delay", (Object)true));
        this.rotate = (Setting<Boolean>)this.register(new Setting("Rotate", (Object)true));
        this.stay = (Setting<Boolean>)this.register(new Setting("Stay", (Object)Boolean.TRUE, p0 -> (boolean)this.rotate.getValue()));
        this.armorBreak = (Setting<Boolean>)this.register(new Setting("ArmorBreak", (Object)false));
        this.eating = (Setting<Boolean>)this.register(new Setting("While-Eating", (Object)true));
        this.onlySharp = (Setting<Boolean>)this.register(new Setting("Axe/Sword", (Object)true));
        this.players = (Setting<Boolean>)this.register(new Setting("Players", (Object)true));
        this.mobs = (Setting<Boolean>)this.register(new Setting("Monsters", (Object)false));
        this.animals = (Setting<Boolean>)this.register(new Setting("Animals", (Object)false));
        this.vehicles = (Setting<Boolean>)this.register(new Setting("Entities", (Object)false));
        this.projectiles = (Setting<Boolean>)this.register(new Setting("Projectiles", (Object)false));
        this.tps = (Setting<Boolean>)this.register(new Setting("TPS-Sync", (Object)true));
        this.packet = (Setting<Boolean>)this.register(new Setting("Packet-Attack", (Object)false));
        this.sneak = (Setting<Boolean>)this.register(new Setting("Sneak", (Object)false));
    }
    
    @Override
    public String getDisplayInfo() {
        if (Killaura.target instanceof EntityPlayer) {
            return String.valueOf(new StringBuilder().append(Killaura.target.getName()).append(""));
        }
        return null;
    }
    
    private void doKillaura() {
        if ((boolean)this.onlySharp.getValue() && !EntityUtil.holdingWeapon((EntityPlayer)Killaura.mc.player)) {
            Killaura.target = null;
            return;
        }
        if (!this.timer.passedMs((long)(this.delay.getValue() ? ((int)(DamageUtil.getCooldownByWeapon((EntityPlayer)Killaura.mc.player) * (this.tps.getValue() ? LuigiHack.serverManager.getTpsFactor() : 1.0f))) : 0)) || (!(boolean)this.eating.getValue() && Killaura.mc.player.isHandActive() && (!Killaura.mc.player.getHeldItemOffhand().getItem().equals(Items.SHIELD) || Killaura.mc.player.getActiveHand() != EnumHand.OFF_HAND))) {
            return;
        }
        if (this.targetMode.getValue() != TargetMode.FOCUS || Killaura.target == null || Killaura.mc.player.getDistanceSq(Killaura.target) >= MathUtil.square((float)this.range.getValue()) || (!Killaura.mc.player.canEntityBeSeen(Killaura.target) && !EntityUtil.canEntityFeetBeSeen(Killaura.target) && Killaura.mc.player.getDistanceSq(Killaura.target) >= MathUtil.square((float)this.raytrace.getValue()))) {
            Killaura.target = this.getTarget();
        }
        if (Killaura.target == null) {
            return;
        }
        final int hotbarBlock;
        if ((boolean)this.autoSwitch.getValue() && (hotbarBlock = InventoryUtil.findHotbarBlock((Class)ItemSword.class)) != -1) {
            InventoryUtil.switchToHotbarSlot(hotbarBlock, false);
        }
        if (this.rotate.getValue()) {
            LuigiHack.rotationManager.lookAtEntity(Killaura.target);
        }
        if (this.armorBreak.getValue()) {
            Killaura.mc.playerController.windowClick(Killaura.mc.player.inventoryContainer.windowId, 9, Killaura.mc.player.inventory.currentItem, ClickType.SWAP, (EntityPlayer)Killaura.mc.player);
            EntityUtil.attackEntity(Killaura.target, (boolean)this.packet.getValue(), true);
            Killaura.mc.playerController.windowClick(Killaura.mc.player.inventoryContainer.windowId, 9, Killaura.mc.player.inventory.currentItem, ClickType.SWAP, (EntityPlayer)Killaura.mc.player);
            EntityUtil.attackEntity(Killaura.target, (boolean)this.packet.getValue(), true);
        }
        else {
            final boolean isSneaking = Killaura.mc.player.isSneaking();
            final boolean isSprinting = Killaura.mc.player.isSprinting();
            if (this.sneak.getValue()) {
                if (isSneaking) {
                    Killaura.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Killaura.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
                }
                if (isSprinting) {
                    Killaura.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Killaura.mc.player, CPacketEntityAction.Action.STOP_SPRINTING));
                }
            }
            EntityUtil.attackEntity(Killaura.target, (boolean)this.packet.getValue(), true);
            if (this.sneak.getValue()) {
                if (isSprinting) {
                    Killaura.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Killaura.mc.player, CPacketEntityAction.Action.START_SPRINTING));
                }
                if (isSneaking) {
                    Killaura.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Killaura.mc.player, CPacketEntityAction.Action.START_SNEAKING));
                }
            }
        }
        this.timer.reset();
    }
    
    public enum TargetMode
    {
        CLOSEST, 
        SMART, 
        FOCUS, 
        HEALTH;
    }
}
