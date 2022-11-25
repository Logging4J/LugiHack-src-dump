//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.misc;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.network.play.server.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import me.snow.luigihack.api.util.*;
import java.util.*;
import net.minecraft.init.*;
import com.google.common.collect.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraft.util.*;
import me.snow.luigihack.impl.modules.combat.autocrystal.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class NoSoundLag extends Module
{
    private static /* synthetic */ NoSoundLag instance;
    private static final /* synthetic */ Set<SoundEvent> BLACKLIST;
    public /* synthetic */ Setting<Boolean> armor;
    public /* synthetic */ Setting<Boolean> crystals;
    public /* synthetic */ Setting<Float> soundRange;
    
    public static void removeEntities(final SPacketSoundEffect sPacketSoundEffect, final float n) {
        final BlockPos blockPos = new BlockPos(sPacketSoundEffect.getX(), sPacketSoundEffect.getY(), sPacketSoundEffect.getZ());
        final ArrayList<Entity> list = new ArrayList<Entity>();
        if (fullNullCheck()) {
            return;
        }
        for (final Entity e : NoSoundLag.mc.world.loadedEntityList) {
            if (e instanceof EntityEnderCrystal) {
                if (e.getDistanceSq(blockPos) > MathUtil.square(n)) {
                    continue;
                }
                list.add(e);
            }
        }
        final Iterator<Entity> iterator2 = list.iterator();
        while (iterator2.hasNext()) {
            iterator2.next().setDead();
        }
    }
    
    static {
        BLACKLIST = Sets.newHashSet((Object[])new SoundEvent[] { SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, SoundEvents.ITEM_ARMOR_EQIIP_ELYTRA, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, SoundEvents.ITEM_ARMOR_EQUIP_IRON, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER });
    }
    
    @SubscribeEvent
    public void onPacketReceived(final PacketEvent.Receive receive) {
        if (receive != null && receive.getPacket() != null && NoSoundLag.mc.player != null && NoSoundLag.mc.world != null && receive.getPacket() instanceof SPacketSoundEffect) {
            final SPacketSoundEffect sPacketSoundEffect = (SPacketSoundEffect)receive.getPacket();
            if ((boolean)this.crystals.getValue() && sPacketSoundEffect.getCategory() == SoundCategory.BLOCKS && sPacketSoundEffect.getSound() == SoundEvents.ENTITY_GENERIC_EXPLODE && (AutoCrystal.getInstance().isOff() || (!(boolean)AutoCrystal.getInstance().sound.getValue() && AutoCrystal.getInstance().threadMode.getValue() != AutoCrystal.ThreadMode.SOUND))) {
                removeEntities(sPacketSoundEffect, (float)this.soundRange.getValue());
            }
            if (NoSoundLag.BLACKLIST.contains(sPacketSoundEffect.getSound()) && (boolean)this.armor.getValue()) {
                receive.setCanceled(true);
            }
        }
    }
    
    public NoSoundLag() {
        super("NoSoundLag", "Prevents Lag through sound spam.", Category.MISC, true, false, false);
        this.crystals = (Setting<Boolean>)this.register(new Setting("Crystals", (Object)true));
        this.armor = (Setting<Boolean>)this.register(new Setting("Armor", (Object)true));
        this.soundRange = (Setting<Float>)this.register(new Setting("SoundRange", (Object)12.0f, (Object)0.0f, (Object)12.0f));
        NoSoundLag.instance = this;
    }
    
    public static NoSoundLag getInstance() {
        if (NoSoundLag.instance == null) {
            NoSoundLag.instance = new NoSoundLag();
        }
        return NoSoundLag.instance;
    }
}
