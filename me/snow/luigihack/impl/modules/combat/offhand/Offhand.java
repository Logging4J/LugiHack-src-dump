//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.combat.offhand;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.init.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import net.minecraft.util.math.*;
import me.snow.luigihack.api.util.ca.util.*;
import java.util.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.item.*;
import me.snow.luigihack.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Offhand extends Module
{
    public /* synthetic */ Setting<Boolean> GapSwitch;
    private /* synthetic */ int timer;
    public /* synthetic */ Setting<Integer> fallDistanceHeight;
    public /* synthetic */ Setting<Boolean> CrystalCheck;
    public /* synthetic */ Setting<Boolean> Always;
    public /* synthetic */ Setting<Integer> cooldown;
    private /* synthetic */ Setting<OffHandMode> mode;
    public /* synthetic */ Setting<Boolean> GapOnSword;
    public /* synthetic */ Setting<Boolean> GapOnPick;
    public /* synthetic */ Setting<Boolean> fallDistance;
    public /* synthetic */ Setting<Integer> HoleHP;
    public /* synthetic */ Setting<Boolean> cancelMovement;
    public /* synthetic */ Setting<Integer> TotemHp;
    public /* synthetic */ Setting<Boolean> check32K;
    public /* synthetic */ Setting<Boolean> TotemOnElytra;
    
    public void swapItems(final int n) {
        if (n == -1 || (this.timer <= (int)this.cooldown.getValue() && Offhand.mc.player.inventory.getStackInSlot(n).getItem() != Items.TOTEM_OF_UNDYING)) {
            return;
        }
        this.timer = 0;
        Offhand.mc.playerController.windowClick(0, n, 0, ClickType.PICKUP, (EntityPlayer)Offhand.mc.player);
        Offhand.mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, (EntityPlayer)Offhand.mc.player);
        Offhand.mc.playerController.windowClick(0, n, 0, ClickType.PICKUP, (EntityPlayer)Offhand.mc.player);
        Offhand.mc.playerController.updateController();
    }
    
    @Override
    public String getDisplayInfo() {
        return String.valueOf(new StringBuilder().append(this.mode.getValue()).append(""));
    }
    
    @Override
    public void onUpdate() {
        if (nullCheck()) {
            return;
        }
        if (Offhand.mc.currentScreen instanceof GuiContainer) {
            return;
        }
        if (EntityUtilCa.getHealth((Entity)Offhand.mc.player) < (int)this.TotemHp.getValue()) {
            if (this.cancelMovement.getValue()) {
                StopPlayerMovement.toggle(true);
            }
            this.swapItems(this.getItemSlot(Items.TOTEM_OF_UNDYING));
            if (this.cancelMovement.getValue()) {
                StopPlayerMovement.toggle(false);
            }
        }
    }
    
    private boolean lethalToLocalCheck() {
        if (!(boolean)this.CrystalCheck.getValue()) {
            return true;
        }
        for (final Entity entity : Offhand.mc.world.loadedEntityList) {
            if (entity instanceof EntityEnderCrystal && Offhand.mc.player.getDistance(entity) <= 12.0f && CrystalUtilCa.calculateDamage(new BlockPos(entity.posX, entity.posY, entity.posZ), (Entity)Offhand.mc.player, false) >= Offhand.mc.player.getHealth()) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public void onTick() {
        if (nullCheck()) {
            return;
        }
        ++this.timer;
        if (Offhand.mc.currentScreen == null || Offhand.mc.currentScreen instanceof GuiInventory) {
            final float n = Offhand.mc.player.getHealth() + Offhand.mc.player.getAbsorptionAmount();
            if (((boolean)this.TotemOnElytra.getValue() && Offhand.mc.player.isElytraFlying()) || ((boolean)this.fallDistance.getValue() && Offhand.mc.player.fallDistance >= (int)this.fallDistanceHeight.getValue() && !Offhand.mc.player.isElytraFlying())) {
                this.swapItems(this.getItemSlot(Items.TOTEM_OF_UNDYING));
            }
            else {
                if ((n <= (int)this.TotemHp.getValue() && (!EntityUtilCa.isInHole((Entity)Offhand.mc.player) || n <= (int)this.HoleHP.getValue())) || !this.lethalToLocalCheck() || !this.Check32K()) {
                    this.swapItems(this.getItemSlot(Items.TOTEM_OF_UNDYING));
                    return;
                }
                if (this.mode.getValue() == OffHandMode.Crystal && (((!(boolean)this.GapOnSword.getValue() || !(Offhand.mc.player.getHeldItemMainhand().getItem() instanceof ItemSword)) && !(boolean)this.Always.getValue() && (!(boolean)this.GapOnPick.getValue() || !(Offhand.mc.player.getHeldItemMainhand().getItem() instanceof ItemPickaxe))) || !Offhand.mc.gameSettings.keyBindUseItem.isKeyDown() || !(boolean)this.GapSwitch.getValue())) {
                    this.swapItems(this.getItemSlot(Items.END_CRYSTAL));
                    return;
                }
                if ((((boolean)this.GapOnSword.getValue() && Offhand.mc.player.getHeldItemMainhand().getItem() instanceof ItemSword) || (boolean)this.Always.getValue() || ((boolean)this.GapOnPick.getValue() && Offhand.mc.player.getHeldItemMainhand().getItem() instanceof ItemPickaxe)) && Offhand.mc.gameSettings.keyBindUseItem.isKeyDown() && (boolean)this.GapSwitch.getValue()) {
                    this.swapItems(this.getItemSlot(Items.GOLDEN_APPLE));
                    if (Offhand.mc.player.getHeldItemMainhand().getItem() instanceof ItemPickaxe) {
                        Offhand.mc.playerController.isHittingBlock = true;
                    }
                    return;
                }
                if (this.mode.getValue() == OffHandMode.Totem) {
                    this.swapItems(this.getItemSlot(Items.TOTEM_OF_UNDYING));
                    return;
                }
                if (this.mode.getValue() == OffHandMode.Gapple) {
                    this.swapItems(this.getItemSlot(Items.GOLDEN_APPLE));
                    return;
                }
            }
            if (Offhand.mc.player.getHeldItemOffhand().getItem() == Items.AIR) {
                this.swapItems(this.getItemSlot(Items.TOTEM_OF_UNDYING));
            }
        }
    }
    
    private int getItemSlot(final Item item) {
        if (item == Offhand.mc.player.getHeldItemOffhand().getItem()) {
            return -1;
        }
        for (int i = 36; i >= 0; --i) {
            if (Offhand.mc.player.inventory.getStackInSlot(i).getItem() == item) {
                if (i < 9) {
                    if (item == Items.GOLDEN_APPLE) {
                        return -1;
                    }
                    i += 36;
                }
                return i;
            }
        }
        return -1;
    }
    
    public Offhand() {
        super("Offhand", "Allows you to switch up your Offhand.", Category.COMBAT, true, false, false);
        this.mode = (Setting<OffHandMode>)this.register(new Setting("Mode", (Object)OffHandMode.Totem));
        this.cancelMovement = (Setting<Boolean>)this.register(new Setting("CancelMovement", (Object)false));
        this.TotemHp = (Setting<Integer>)this.register(new Setting("TotemHP", (Object)12, (Object)0, (Object)36));
        this.HoleHP = (Setting<Integer>)this.register(new Setting("HoleHP", (Object)12, (Object)0, (Object)36));
        this.GapSwitch = (Setting<Boolean>)this.register(new Setting("GapSwap", (Object)false));
        this.GapOnSword = (Setting<Boolean>)this.register(new Setting("SwordGap", (Object)false, p0 -> (boolean)this.GapSwitch.getValue()));
        this.GapOnPick = (Setting<Boolean>)this.register(new Setting("PickGap", (Object)false, p0 -> (boolean)this.GapSwitch.getValue()));
        this.Always = (Setting<Boolean>)this.register(new Setting("Always", (Object)false, p0 -> (boolean)this.GapSwitch.getValue()));
        this.CrystalCheck = (Setting<Boolean>)this.register(new Setting("CrystalCheck", (Object)false));
        this.check32K = (Setting<Boolean>)this.register(new Setting("32KCheck", (Object)false));
        this.cooldown = (Setting<Integer>)this.register(new Setting("Cooldown", (Object)0, (Object)0, (Object)40));
        this.fallDistance = (Setting<Boolean>)this.register(new Setting("FallDistance", (Object)true));
        this.fallDistanceHeight = (Setting<Integer>)this.register(new Setting("Height", (Object)15, (Object)0, (Object)30, p0 -> (boolean)this.fallDistance.getValue()));
        this.TotemOnElytra = (Setting<Boolean>)this.register(new Setting("TotemOnElytra", (Object)false));
        this.timer = 0;
    }
    
    private boolean Check32K() {
        if (!(boolean)this.check32K.getValue() || Offhand.mc.world == null || Offhand.mc.player == null) {
            return true;
        }
        for (final Entity entity : Offhand.mc.world.loadedEntityList) {
            if (entity != Offhand.mc.player && LuigiHack.friendManager.isFriend(entity.getName()) && entity instanceof EntityPlayer && entity.getDistance((Entity)Offhand.mc.player) < 7.0f && EntityUtilCa.holding32k((EntityPlayer)entity)) {
                return true;
            }
        }
        return true;
    }
    
    public static class StopPlayerMovement
    {
        private static final /* synthetic */ StopPlayerMovement stopPlayerMovement;
        
        public static void toggle(final boolean b) {
            if (b) {
                LuigiHack.EVENT_PROCESSOR.addEventListener((Object)StopPlayerMovement.stopPlayerMovement);
            }
            else {
                LuigiHack.EVENT_PROCESSOR.removeEventListener((Object)StopPlayerMovement.stopPlayerMovement);
            }
        }
        
        static {
            stopPlayerMovement = new StopPlayerMovement();
        }
        
        @SubscribeEvent
        public void onMove(final MoveEvent moveEvent) {
            moveEvent.setCanceled(true);
        }
    }
}
