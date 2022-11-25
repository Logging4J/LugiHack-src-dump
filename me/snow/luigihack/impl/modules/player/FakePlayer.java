//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.player;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.client.entity.*;
import net.minecraft.entity.item.*;
import me.snow.luigihack.api.util.skid.oyvey.*;
import net.minecraft.potion.*;
import net.minecraft.network.play.server.*;
import me.snow.luigihack.api.manager.*;
import me.snow.luigihack.*;
import me.snow.luigihack.api.event.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.enchantment.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;
import net.minecraft.init.*;
import java.util.*;
import com.mojang.authlib.*;
import net.minecraft.world.*;

public class FakePlayer extends Module
{
    public final /* synthetic */ Setting<Boolean> pop;
    public final /* synthetic */ Setting<Boolean> move;
    public final /* synthetic */ Setting<Boolean> inv;
    private /* synthetic */ EntityOtherPlayerMP fakePlayer;
    
    public FakePlayer() {
        super("FakePlayer", "Spawns in a fake player for testing purposes", Module.Category.PLAYER, true, false, false);
        this.inv = (Setting<Boolean>)this.register(new Setting("Copy Inventory", (Object)true));
        this.pop = (Setting<Boolean>)this.register(new Setting("Can Pop Totems", (Object)true));
        this.move = (Setting<Boolean>)this.register(new Setting("Can Move", (Object)true));
    }
    
    @SubscribeEvent
    public void onPacketReceive(final PacketEvent.Receive receive) {
        if (this.pop.getValue()) {
            try {
                if (receive.getPacket() instanceof SPacketDestroyEntities) {
                    final int[] getEntityIDs = ((SPacketDestroyEntities)receive.getPacket()).getEntityIDs();
                    for (int length = getEntityIDs.length, i = 0; i < length; ++i) {
                        final Entity getEntityByID = FakePlayer.mc.world.getEntityByID(getEntityIDs[i]);
                        if (getEntityByID instanceof EntityEnderCrystal) {
                            if (getEntityByID.getDistanceSq((Entity)this.fakePlayer) < 144.0) {
                                final float n = this.fakePlayer.getAbsorptionAmount() - DamageUtil2.calculateDamage(getEntityByID.getPositionVector(), (Entity)this.fakePlayer);
                                final boolean b = n < 0.0f;
                                final float n2 = this.fakePlayer.getHealth() + n;
                                if (b && n2 > 0.0f) {
                                    this.fakePlayer.setHealth(n2);
                                    this.fakePlayer.setAbsorptionAmount(0.0f);
                                }
                                else if (n2 > 0.0f) {
                                    this.fakePlayer.setAbsorptionAmount(n);
                                }
                                else {
                                    this.fakePlayer.setHealth(2.0f);
                                    this.fakePlayer.setAbsorptionAmount(8.0f);
                                    this.fakePlayer.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 5));
                                    this.fakePlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 1));
                                    try {
                                        FakePlayer.mc.player.connection.handleEntityStatus(new SPacketEntityStatus((Entity)this.fakePlayer, (byte)35));
                                    }
                                    catch (Exception ex) {}
                                    if (TotemPopManager2.totemMap.containsKey(this.fakePlayer)) {
                                        final int j = TotemPopManager2.totemMap.get(this.fakePlayer) + 1;
                                        LuigiHack.dispatcher.post(new EventTotemPop(EnumStages.PRE, (EntityLivingBase)this.fakePlayer, j));
                                        TotemPopManager2.totemMap.remove(this.fakePlayer);
                                        TotemPopManager2.totemMap.put(this.fakePlayer, j);
                                    }
                                    else {
                                        LuigiHack.dispatcher.post(new EventTotemPop(EnumStages.PRE, (EntityLivingBase)this.fakePlayer, 1));
                                        TotemPopManager2.totemMap.put(this.fakePlayer, 1);
                                    }
                                }
                                this.fakePlayer.hurtTime = 5;
                            }
                        }
                    }
                }
            }
            catch (Exception ex2) {}
        }
    }
    
    public void travel(final float n, final float n2, final float n3) {
        final double posY = this.fakePlayer.posY;
        float n4 = 0.8f;
        float n5 = 0.02f;
        float n6 = (float)EnchantmentHelper.getDepthStriderModifier((EntityLivingBase)this.fakePlayer);
        if (n6 > 3.0f) {
            n6 = 3.0f;
        }
        if (!this.fakePlayer.onGround) {
            n6 *= 0.5f;
        }
        if (n6 > 0.0f) {
            n4 += (0.54600006f - n4) * n6 / 3.0f;
            n5 += (this.fakePlayer.getAIMoveSpeed() - n5) * n6 / 4.0f;
        }
        this.fakePlayer.moveRelative(n, n2, n3, n5);
        this.fakePlayer.move(MoverType.SELF, this.fakePlayer.motionX, this.fakePlayer.motionY, this.fakePlayer.motionZ);
        final EntityOtherPlayerMP fakePlayer = this.fakePlayer;
        fakePlayer.motionX *= n4;
        final EntityOtherPlayerMP fakePlayer2 = this.fakePlayer;
        fakePlayer2.motionY *= 0.800000011920929;
        final EntityOtherPlayerMP fakePlayer3 = this.fakePlayer;
        fakePlayer3.motionZ *= n4;
        if (!this.fakePlayer.hasNoGravity()) {
            final EntityOtherPlayerMP fakePlayer4 = this.fakePlayer;
            fakePlayer4.motionY -= 0.02;
        }
        if (this.fakePlayer.collidedHorizontally && this.fakePlayer.isOffsetPositionInLiquid(this.fakePlayer.motionX, this.fakePlayer.motionY + 0.6000000238418579 - this.fakePlayer.posY + posY, this.fakePlayer.motionZ)) {
            this.fakePlayer.motionY = 0.30000001192092896;
        }
    }
    
    public void onTick() {
        if (this.fakePlayer != null) {
            final Random random = new Random();
            this.fakePlayer.moveForward = FakePlayer.mc.player.moveForward + random.nextInt(5) / 10.0f;
            this.fakePlayer.moveStrafing = FakePlayer.mc.player.moveStrafing + random.nextInt(5) / 10.0f;
            if (this.move.getValue()) {
                this.travel(this.fakePlayer.moveStrafing, this.fakePlayer.moveVertical, this.fakePlayer.moveForward);
            }
        }
    }
    
    private void doPop(final Entity entity) {
        FakePlayer.mc.effectRenderer.emitParticleAtEntity(entity, EnumParticleTypes.TOTEM, 30);
        FakePlayer.mc.world.playSound(entity.posX, entity.posY, entity.posZ, SoundEvents.ITEM_TOTEM_USE, entity.getSoundCategory(), 1.0f, 1.0f, false);
    }
    
    public void onLogout() {
        this.disable();
    }
    
    public void onEnable() {
        if (nullCheck()) {
            return;
        }
        this.fakePlayer = new EntityOtherPlayerMP((World)FakePlayer.mc.world, new GameProfile(UUID.fromString("fdee323e-7f0c-4c15-8d1c-0f277442342a"), "Fit"));
        this.fakePlayer.copyLocationAndAnglesFrom((Entity)FakePlayer.mc.player);
        this.fakePlayer.rotationYawHead = FakePlayer.mc.player.rotationYawHead;
        if (this.inv.getValue()) {
            this.fakePlayer.inventory.copyInventory(FakePlayer.mc.player.inventory);
        }
        FakePlayer.mc.world.addEntityToWorld(-100, (Entity)this.fakePlayer);
    }
    
    public void onDisable() {
        try {
            if (nullCheck()) {
                return;
            }
            FakePlayer.mc.world.removeEntity((Entity)this.fakePlayer);
        }
        catch (Exception ex) {}
    }
    
    public void onUpdate() {
        if (nullCheck()) {
            this.setEnabled(false);
        }
    }
}
