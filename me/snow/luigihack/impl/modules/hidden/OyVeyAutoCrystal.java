//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.hidden;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.entity.item.*;
import net.minecraft.network.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import net.minecraft.enchantment.*;
import net.minecraft.util.math.*;
import me.snow.luigihack.api.event.events.*;
import java.awt.*;
import me.snow.luigihack.api.util.skid.oyvey.*;
import net.minecraft.network.play.server.*;
import net.minecraft.init.*;
import me.snow.luigihack.*;
import java.util.stream.*;
import java.util.function.*;
import java.util.*;
import net.minecraft.item.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;

public class OyVeyAutoCrystal extends Module
{
    public /* synthetic */ Setting<Boolean> place;
    public /* synthetic */ Setting<SwingMode> swingMode;
    public /* synthetic */ Setting<Float> breakMaxSelfDamage;
    private /* synthetic */ float pitch;
    private /* synthetic */ boolean rotating;
    private final /* synthetic */ Setting<Integer> blue;
    private final /* synthetic */ Setting<Integer> attackFactor;
    private final /* synthetic */ Setting<Integer> cRed;
    private /* synthetic */ float yaw;
    public /* synthetic */ Setting<Boolean> suicide;
    private /* synthetic */ int predictWait;
    public /* synthetic */ Setting<Float> minArmor;
    public /* synthetic */ Setting<Float> facePlace;
    private final /* synthetic */ Timer preditTimer;
    public /* synthetic */ Setting<SwitchMode> switchmode;
    private /* synthetic */ int predict;
    private final /* synthetic */ Setting<Integer> cBlue;
    private final /* synthetic */ Setting<Integer> alpha;
    public /* synthetic */ Setting<Boolean> silentSwitch;
    public /* synthetic */ Setting<Boolean> autoswitch;
    public /* synthetic */ Setting<Boolean> box;
    private /* synthetic */ int crystalCount;
    private final /* synthetic */ Setting<Integer> cGreen;
    public /* synthetic */ Setting<Float> breakRange;
    private final /* synthetic */ Timer breakTimer;
    private /* synthetic */ EntityLivingBase target;
    public /* synthetic */ Setting<Float> placeRange;
    public /* synthetic */ Setting<Boolean> renderDmg;
    public /* synthetic */ Setting<Float> minDamage;
    private final /* synthetic */ Setting<Integer> cAlpha;
    /* synthetic */ EntityEnderCrystal crystal;
    public /* synthetic */ Setting<Boolean> facePlaceSword;
    public /* synthetic */ Setting<Float> breakWallRange;
    private final /* synthetic */ Setting<Integer> green;
    public /* synthetic */ Setting<Boolean> predicts;
    private /* synthetic */ BlockPos pos;
    public /* synthetic */ Setting<Boolean> explode;
    private /* synthetic */ int hotBarSlot;
    public /* synthetic */ Setting<Boolean> render;
    public /* synthetic */ Setting<Boolean> sound;
    private final /* synthetic */ Setting<Integer> boxAlpha;
    private /* synthetic */ boolean packetCalc;
    public /* synthetic */ Setting<Float> placeDelay;
    private /* synthetic */ EntityLivingBase realTarget;
    public /* synthetic */ Setting<Boolean> outline;
    public /* synthetic */ Setting<Boolean> rotate;
    private final /* synthetic */ Timer placeTimer;
    private /* synthetic */ boolean armorTarget;
    public /* synthetic */ Setting<Boolean> ignoreUseAmount;
    private final /* synthetic */ Setting<Float> lineWidth;
    public /* synthetic */ Setting<Integer> wasteAmount;
    public /* synthetic */ Setting<Float> breakMinDmg;
    private final /* synthetic */ Setting<Integer> red;
    private /* synthetic */ int predictPackets;
    private final /* synthetic */ Timer manualTimer;
    public /* synthetic */ Setting<Boolean> packetBreak;
    public /* synthetic */ Setting<Float> targetRange;
    public /* synthetic */ Setting<Boolean> opPlace;
    public /* synthetic */ Setting<Float> breakDelay;
    private /* synthetic */ boolean armor;
    
    private float getDamageMultiplied(final float n) {
        final int getId = OyVeyAutoCrystal.mc.world.getDifficulty().getId();
        return n * ((getId == 0) ? 0.0f : ((getId == 2) ? 1.0f : ((getId == 1) ? 0.5f : 1.5f)));
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public void onPacketReceive(final PacketEvent.Receive receive) {
        final SPacketSpawnObject sPacketSpawnObject;
        if (receive.getPacket() instanceof SPacketSpawnObject && (sPacketSpawnObject = (SPacketSpawnObject)receive.getPacket()).getType() == 51 && (boolean)this.predicts.getValue() && this.preditTimer.passedMs((long)this.attackFactor.getValue()) && (boolean)this.predicts.getValue() && (boolean)this.explode.getValue() && (boolean)this.packetBreak.getValue() && this.target != null) {
            if (!this.isPredicting(sPacketSpawnObject)) {
                return;
            }
            final CPacketUseEntity cPacketUseEntity = new CPacketUseEntity();
            cPacketUseEntity.entityId = sPacketSpawnObject.getEntityID();
            cPacketUseEntity.action = CPacketUseEntity.Action.ATTACK;
            OyVeyAutoCrystal.mc.player.connection.sendPacket((Packet)cPacketUseEntity);
        }
    }
    
    private void manualBreaker() {
        final RayTraceResult objectMouseOver;
        if (this.manualTimer.passedMs(200L) && OyVeyAutoCrystal.mc.gameSettings.keyBindUseItem.isKeyDown() && OyVeyAutoCrystal.mc.player.getHeldItemOffhand().getItem() != Items.GOLDEN_APPLE && OyVeyAutoCrystal.mc.player.inventory.getCurrentItem().getItem() != Items.GOLDEN_APPLE && OyVeyAutoCrystal.mc.player.inventory.getCurrentItem().getItem() != Items.BOW && OyVeyAutoCrystal.mc.player.inventory.getCurrentItem().getItem() != Items.EXPERIENCE_BOTTLE && (objectMouseOver = OyVeyAutoCrystal.mc.objectMouseOver) != null) {
            if (objectMouseOver.typeOfHit.equals((Object)RayTraceResult.Type.ENTITY)) {
                final Entity entityHit = objectMouseOver.entityHit;
                if (entityHit instanceof EntityEnderCrystal) {
                    if (this.packetBreak.getValue()) {
                        OyVeyAutoCrystal.mc.player.connection.sendPacket((Packet)new CPacketUseEntity(entityHit));
                    }
                    else {
                        OyVeyAutoCrystal.mc.playerController.attackEntity((EntityPlayer)OyVeyAutoCrystal.mc.player, entityHit);
                    }
                    this.manualTimer.reset();
                }
            }
            else if (objectMouseOver.typeOfHit.equals((Object)RayTraceResult.Type.BLOCK)) {
                for (final Entity entity : OyVeyAutoCrystal.mc.world.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(new BlockPos((double)OyVeyAutoCrystal.mc.objectMouseOver.getBlockPos().getX(), OyVeyAutoCrystal.mc.objectMouseOver.getBlockPos().getY() + 1.0, (double)OyVeyAutoCrystal.mc.objectMouseOver.getBlockPos().getZ())))) {
                    if (!(entity instanceof EntityEnderCrystal)) {
                        continue;
                    }
                    if (this.packetBreak.getValue()) {
                        OyVeyAutoCrystal.mc.player.connection.sendPacket((Packet)new CPacketUseEntity(entity));
                    }
                    else {
                        OyVeyAutoCrystal.mc.playerController.attackEntity((EntityPlayer)OyVeyAutoCrystal.mc.player, entity);
                    }
                    this.manualTimer.reset();
                }
            }
        }
    }
    
    @Override
    public void onEnable() {
        this.placeTimer.reset();
        this.breakTimer.reset();
        this.predictWait = 0;
        this.hotBarSlot = -1;
        this.pos = null;
        this.crystal = null;
        this.predict = 0;
        this.predictPackets = 1;
        this.target = null;
        this.packetCalc = false;
        this.realTarget = null;
        this.armor = false;
        this.armorTarget = false;
    }
    
    private boolean canPlaceCrystal(final BlockPos blockPos, final boolean b) {
        final BlockPos add = blockPos.add(0, 1, 0);
        final BlockPos add2 = blockPos.add(0, 2, 0);
        try {
            if (!(boolean)this.opPlace.getValue()) {
                if (OyVeyAutoCrystal.mc.world.getBlockState(blockPos).getBlock() != Blocks.BEDROCK && OyVeyAutoCrystal.mc.world.getBlockState(blockPos).getBlock() != Blocks.OBSIDIAN) {
                    return false;
                }
                if (OyVeyAutoCrystal.mc.world.getBlockState(add).getBlock() != Blocks.AIR || OyVeyAutoCrystal.mc.world.getBlockState(add2).getBlock() != Blocks.AIR) {
                    return false;
                }
                if (!b) {
                    return OyVeyAutoCrystal.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(add)).isEmpty() && OyVeyAutoCrystal.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(add2)).isEmpty();
                }
                final Iterator iterator = OyVeyAutoCrystal.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(add)).iterator();
                while (iterator.hasNext()) {
                    if (iterator.next() instanceof EntityEnderCrystal) {
                        continue;
                    }
                    return false;
                }
                final Iterator iterator2 = OyVeyAutoCrystal.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(add2)).iterator();
                while (iterator2.hasNext()) {
                    if (iterator2.next() instanceof EntityEnderCrystal) {
                        continue;
                    }
                    return false;
                }
            }
            else {
                if (OyVeyAutoCrystal.mc.world.getBlockState(blockPos).getBlock() != Blocks.BEDROCK && OyVeyAutoCrystal.mc.world.getBlockState(blockPos).getBlock() != Blocks.OBSIDIAN) {
                    return false;
                }
                if (OyVeyAutoCrystal.mc.world.getBlockState(add).getBlock() != Blocks.AIR) {
                    return false;
                }
                if (!b) {
                    return OyVeyAutoCrystal.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(add)).isEmpty();
                }
                final Iterator iterator3 = OyVeyAutoCrystal.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(add)).iterator();
                while (iterator3.hasNext()) {
                    if (iterator3.next() instanceof EntityEnderCrystal) {
                        continue;
                    }
                    return false;
                }
            }
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }
    
    private float calculateDamage(final double n, final double n2, final double n3, final Entity entity) {
        final double n4 = entity.getDistance(n, n2, n3) / 12.0;
        final Vec3d vec3d = new Vec3d(n, n2, n3);
        double n5 = 0.0;
        try {
            n5 = entity.world.getBlockDensity(vec3d, entity.getEntityBoundingBox());
        }
        catch (Exception ex) {}
        final double n6 = (1.0 - n4) * n5;
        final float n7 = (float)(int)((n6 * n6 + n6) / 2.0 * 7.0 * 12.0 + 1.0);
        double n8 = 1.0;
        if (entity instanceof EntityLivingBase) {
            n8 = this.getBlastReduction((EntityLivingBase)entity, this.getDamageMultiplied(n7), new Explosion((World)OyVeyAutoCrystal.mc.world, (Entity)null, n, n2, n3, 6.0f, false, true));
        }
        return (float)n8;
    }
    
    @Override
    public String getDisplayInfo() {
        if (this.realTarget != null) {
            return this.realTarget.getName();
        }
        return null;
    }
    
    private boolean isPredicting(final SPacketSpawnObject sPacketSpawnObject) {
        final BlockPos blockPos = new BlockPos(sPacketSpawnObject.getX(), sPacketSpawnObject.getY(), sPacketSpawnObject.getZ());
        if (OyVeyAutoCrystal.mc.player.getDistance(sPacketSpawnObject.getX(), sPacketSpawnObject.getY(), sPacketSpawnObject.getZ()) > (float)this.breakRange.getValue()) {
            return false;
        }
        if (!this.canSeePos(blockPos) && OyVeyAutoCrystal.mc.player.getDistance(sPacketSpawnObject.getX(), sPacketSpawnObject.getY(), sPacketSpawnObject.getZ()) > (float)this.breakWallRange.getValue()) {
            return false;
        }
        final double n = this.calculateDamage(sPacketSpawnObject.getX() + 0.5, sPacketSpawnObject.getY() + 1.0, sPacketSpawnObject.getZ() + 0.5, (Entity)this.target);
        if (EntityUtil.isInHole((Entity)OyVeyAutoCrystal.mc.player) && n >= 1.0) {
            return true;
        }
        final double n2 = this.calculateDamage(sPacketSpawnObject.getX() + 0.5, sPacketSpawnObject.getY() + 1.0, sPacketSpawnObject.getZ() + 0.5, (Entity)OyVeyAutoCrystal.mc.player);
        if (n2 + (this.suicide.getValue() ? 2.0 : 0.5) < OyVeyAutoCrystal.mc.player.getHealth() + OyVeyAutoCrystal.mc.player.getAbsorptionAmount() && n >= this.target.getAbsorptionAmount() + this.target.getHealth()) {
            return true;
        }
        this.armorTarget = false;
        for (final ItemStack itemStack : this.target.getArmorInventoryList()) {
            if (100 - (int)((1.0f - (itemStack.getMaxDamage() - (float)itemStack.getItemDamage()) / itemStack.getMaxDamage()) * 100.0f) > (float)this.minArmor.getValue()) {
                continue;
            }
            this.armorTarget = true;
        }
        return (n >= (float)this.breakMinDmg.getValue() && n2 <= (float)this.breakMaxSelfDamage.getValue()) || (EntityUtil.isInHole((Entity)this.target) && this.target.getHealth() + this.target.getAbsorptionAmount() <= (float)this.facePlace.getValue());
    }
    
    private boolean IsValidCrystal(final Entity entity) {
        if (entity == null) {
            return false;
        }
        if (!(entity instanceof EntityEnderCrystal)) {
            return false;
        }
        if (this.target == null) {
            return false;
        }
        if (entity.getDistance((Entity)OyVeyAutoCrystal.mc.player) > (float)this.breakRange.getValue()) {
            return false;
        }
        if (!OyVeyAutoCrystal.mc.player.canEntityBeSeen(entity) && entity.getDistance((Entity)OyVeyAutoCrystal.mc.player) > (float)this.breakWallRange.getValue()) {
            return false;
        }
        if (this.target.isDead || this.target.getHealth() + this.target.getAbsorptionAmount() <= 0.0f) {
            return false;
        }
        final double n = this.calculateDamage(entity.getPosition().getX() + 0.5, entity.getPosition().getY() + 1.0, entity.getPosition().getZ() + 0.5, (Entity)this.target);
        if (EntityUtil.isInHole((Entity)OyVeyAutoCrystal.mc.player) && n >= 1.0) {
            return true;
        }
        final double n2 = this.calculateDamage(entity.getPosition().getX() + 0.5, entity.getPosition().getY() + 1.0, entity.getPosition().getZ() + 0.5, (Entity)OyVeyAutoCrystal.mc.player);
        if (n2 + (this.suicide.getValue() ? 2.0 : 0.5) < OyVeyAutoCrystal.mc.player.getHealth() + OyVeyAutoCrystal.mc.player.getAbsorptionAmount() && n >= this.target.getAbsorptionAmount() + this.target.getHealth()) {
            return true;
        }
        this.armorTarget = false;
        for (final ItemStack itemStack : this.target.getArmorInventoryList()) {
            if (100 - (int)((1.0f - (itemStack.getMaxDamage() - (float)itemStack.getItemDamage()) / itemStack.getMaxDamage()) * 100.0f) > (float)this.minArmor.getValue()) {
                continue;
            }
            this.armorTarget = true;
        }
        return (n >= (float)this.breakMinDmg.getValue() && n2 <= (float)this.breakMaxSelfDamage.getValue()) || (EntityUtil.isInHole((Entity)this.target) && this.target.getHealth() + this.target.getAbsorptionAmount() <= (float)this.facePlace.getValue());
    }
    
    private float getBlastReduction(final EntityLivingBase entityLivingBase, final float n, final Explosion explosion) {
        if (entityLivingBase instanceof EntityPlayer) {
            final EntityPlayer entityPlayer = (EntityPlayer)entityLivingBase;
            final DamageSource causeExplosionDamage = DamageSource.causeExplosionDamage(explosion);
            final float getDamageAfterAbsorb = CombatRules.getDamageAfterAbsorb(n, (float)entityPlayer.getTotalArmorValue(), (float)entityPlayer.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue());
            int getEnchantmentModifierDamage = 0;
            try {
                getEnchantmentModifierDamage = EnchantmentHelper.getEnchantmentModifierDamage(entityPlayer.getArmorInventoryList(), causeExplosionDamage);
            }
            catch (Exception ex) {}
            float a = getDamageAfterAbsorb * (1.0f - MathHelper.clamp((float)getEnchantmentModifierDamage, 0.0f, 20.0f) / 25.0f);
            if (entityLivingBase.isPotionActive(MobEffects.RESISTANCE)) {
                a -= a / 4.0f;
            }
            return Math.max(a, 0.0f);
        }
        return CombatRules.getDamageAfterAbsorb(n, (float)entityLivingBase.getTotalArmorValue(), (float)entityLivingBase.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue());
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        if (send.getStage() == 0 && (boolean)this.rotate.getValue() && this.rotating && send.getPacket() instanceof CPacketPlayer) {
            final CPacketPlayer cPacketPlayer = (CPacketPlayer)send.getPacket();
            cPacketPlayer.yaw = this.yaw;
            cPacketPlayer.pitch = this.pitch;
            this.rotating = false;
        }
    }
    
    @Override
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (this.pos != null && (boolean)this.render.getValue() && this.target != null) {
            RenderUtil.drawBoxESP(this.pos, new Color((int)this.red.getValue(), (int)this.green.getValue(), (int)this.blue.getValue(), (int)this.alpha.getValue()), (boolean)this.outline.getValue(), new Color((int)this.cRed.getValue(), (int)this.cGreen.getValue(), (int)this.cBlue.getValue(), (int)this.cAlpha.getValue()), (float)this.lineWidth.getValue(), (boolean)this.outline.getValue(), (boolean)this.box.getValue(), (int)this.boxAlpha.getValue(), true);
            if (this.renderDmg.getValue()) {
                final double n = this.calculateDamage(this.pos.getX() + 0.5, this.pos.getY() + 1.0, this.pos.getZ() + 0.5, (Entity)this.target);
                RenderUtil2.drawText(this.pos, String.valueOf(new StringBuilder().append((Math.floor(n) == n) ? Integer.valueOf((int)n) : String.format("%.1f", n)).append("")));
            }
        }
    }
    
    private void rotateTo(final Entity entity) {
        if (this.rotate.getValue()) {
            final float[] calcAngle = MathUtil.calcAngle(OyVeyAutoCrystal.mc.player.getPositionEyes(OyVeyAutoCrystal.mc.getRenderPartialTicks()), entity.getPositionVector());
            this.yaw = calcAngle[0];
            this.pitch = calcAngle[1];
            this.rotating = true;
        }
    }
    
    @SubscribeEvent(priority = EventPriority.HIGH, receiveCanceled = true)
    public void onSoundPacket(final PacketEvent.Receive receive) {
        if (fullNullCheck()) {
            return;
        }
        if (receive.getPacket() instanceof SPacketSoundEffect && (boolean)this.sound.getValue()) {
            final SPacketSoundEffect sPacketSoundEffect = (SPacketSoundEffect)receive.getPacket();
            if (sPacketSoundEffect.getCategory() == SoundCategory.BLOCKS && sPacketSoundEffect.getSound() == SoundEvents.ENTITY_GENERIC_EXPLODE) {
                final ArrayList<Object> list = new ArrayList<Object>(OyVeyAutoCrystal.mc.world.loadedEntityList);
                for (int size = list.size(), i = 0; i < size; ++i) {
                    final Entity entity = list.get(i);
                    if (entity instanceof EntityEnderCrystal && entity.getDistanceSq(sPacketSoundEffect.getX(), sPacketSoundEffect.getY(), sPacketSoundEffect.getZ()) < 36.0) {
                        entity.setDead();
                    }
                }
            }
        }
    }
    
    EntityPlayer getTarget() {
        Object o = null;
        for (final EntityPlayer entityPlayer : OyVeyAutoCrystal.mc.world.playerEntities) {
            if (OyVeyAutoCrystal.mc.player != null && !OyVeyAutoCrystal.mc.player.isDead && !entityPlayer.isDead && entityPlayer != OyVeyAutoCrystal.mc.player && !LuigiHack.friendManager.isFriend(entityPlayer.getName())) {
                if (entityPlayer.getDistance((Entity)OyVeyAutoCrystal.mc.player) > 12.0f) {
                    continue;
                }
                this.armorTarget = false;
                for (final ItemStack itemStack : entityPlayer.getArmorInventoryList()) {
                    if (100 - (int)((1.0f - (itemStack.getMaxDamage() - (float)itemStack.getItemDamage()) / itemStack.getMaxDamage()) * 100.0f) > (float)this.minArmor.getValue()) {
                        continue;
                    }
                    this.armorTarget = true;
                }
                if (EntityUtil.isInHole((Entity)entityPlayer) && entityPlayer.getAbsorptionAmount() + entityPlayer.getHealth() > (float)this.facePlace.getValue() && !this.armorTarget && (float)this.minDamage.getValue() > 2.2f) {
                    continue;
                }
                if (o == null) {
                    o = entityPlayer;
                }
                else {
                    if (((EntityPlayer)o).getDistance((Entity)OyVeyAutoCrystal.mc.player) <= entityPlayer.getDistance((Entity)OyVeyAutoCrystal.mc.player)) {
                        continue;
                    }
                    o = entityPlayer;
                }
            }
        }
        return (EntityPlayer)o;
    }
    
    @Override
    public void onDisable() {
        this.rotating = false;
    }
    
    public OyVeyAutoCrystal() {
        super("CrystalAura", "skitty ac best ac", Category.COMBAT, true, false, false);
        this.placeTimer = new Timer();
        this.breakTimer = new Timer();
        this.preditTimer = new Timer();
        this.manualTimer = new Timer();
        this.attackFactor = (Setting<Integer>)this.register(new Setting("PredictDelay", (Object)0, (Object)0, (Object)200));
        this.red = (Setting<Integer>)this.register(new Setting("Red", (Object)0, (Object)0, (Object)255));
        this.green = (Setting<Integer>)this.register(new Setting("Green", (Object)255, (Object)0, (Object)255));
        this.blue = (Setting<Integer>)this.register(new Setting("Blue", (Object)0, (Object)0, (Object)255));
        this.alpha = (Setting<Integer>)this.register(new Setting("Alpha", (Object)255, (Object)0, (Object)255));
        this.boxAlpha = (Setting<Integer>)this.register(new Setting("BoxAlpha", (Object)125, (Object)0, (Object)255));
        this.lineWidth = (Setting<Float>)this.register(new Setting("LineWidth", (Object)1.0f, (Object)0.1f, (Object)5.0f));
        this.sound = (Setting<Boolean>)this.register(new Setting("Sequential", (Object)true));
        this.place = (Setting<Boolean>)this.register(new Setting("Place", (Object)true));
        this.placeDelay = (Setting<Float>)this.register(new Setting("PlaceDelay", (Object)4.0f, (Object)0.0f, (Object)300.0f));
        this.placeRange = (Setting<Float>)this.register(new Setting("PlaceRange", (Object)6.0f, (Object)0.1f, (Object)7.0f));
        this.explode = (Setting<Boolean>)this.register(new Setting("Break", (Object)true));
        this.packetBreak = (Setting<Boolean>)this.register(new Setting("PacketBreak", (Object)true));
        this.predicts = (Setting<Boolean>)this.register(new Setting("Predict", (Object)true));
        this.rotate = (Setting<Boolean>)this.register(new Setting("Rotate", (Object)true));
        this.breakDelay = (Setting<Float>)this.register(new Setting("BreakDelay", (Object)4.0f, (Object)0.0f, (Object)300.0f));
        this.breakRange = (Setting<Float>)this.register(new Setting("BreakRange", (Object)6.0f, (Object)0.1f, (Object)7.0f));
        this.breakWallRange = (Setting<Float>)this.register(new Setting("BreakWallRange", (Object)4.0f, (Object)0.1f, (Object)7.0f));
        this.opPlace = (Setting<Boolean>)this.register(new Setting("1.13 Place", (Object)false));
        this.suicide = (Setting<Boolean>)this.register(new Setting("AntiSuicide", (Object)true));
        this.autoswitch = (Setting<Boolean>)this.register(new Setting("AutoSwitch", (Object)true));
        this.switchmode = (Setting<SwitchMode>)this.register(new Setting("SwitchMode", (Object)SwitchMode.Silent, p0 -> (boolean)this.autoswitch.getValue()));
        this.silentSwitch = (Setting<Boolean>)this.register(new Setting("SilentSwitchHand", (Object)true, p0 -> this.switchmode.getValue() == SwitchMode.Silent));
        this.ignoreUseAmount = (Setting<Boolean>)this.register(new Setting("IgnoreUseAmount", (Object)true));
        this.wasteAmount = (Setting<Integer>)this.register(new Setting("UseAmount", (Object)4, (Object)1, (Object)5));
        this.facePlaceSword = (Setting<Boolean>)this.register(new Setting("FacePlaceSword", (Object)false));
        this.targetRange = (Setting<Float>)this.register(new Setting("TargetRange", (Object)10.0f, (Object)1.0f, (Object)12.0f));
        this.minDamage = (Setting<Float>)this.register(new Setting("MinDamage", (Object)2.0f, (Object)0.1f, (Object)20.0f));
        this.facePlace = (Setting<Float>)this.register(new Setting("FacePlaceHP", (Object)36.0f, (Object)0.0f, (Object)36.0f));
        this.breakMaxSelfDamage = (Setting<Float>)this.register(new Setting("BreakMaxSelf", (Object)8.0f, (Object)0.1f, (Object)12.0f));
        this.breakMinDmg = (Setting<Float>)this.register(new Setting("BreakMinDmg", (Object)2.0f, (Object)0.1f, (Object)7.0f));
        this.minArmor = (Setting<Float>)this.register(new Setting("MinArmor", (Object)4.0f, (Object)0.1f, (Object)80.0f));
        this.swingMode = (Setting<SwingMode>)this.register(new Setting("Swing", (Object)SwingMode.None));
        this.render = (Setting<Boolean>)this.register(new Setting("Render", (Object)true));
        this.renderDmg = (Setting<Boolean>)this.register(new Setting("RenderDmg", (Object)true));
        this.box = (Setting<Boolean>)this.register(new Setting("Box", (Object)true));
        this.outline = (Setting<Boolean>)this.register(new Setting("Outline", (Object)true));
        this.cRed = (Setting<Integer>)this.register(new Setting("OL-Red", (Object)0, (Object)0, (Object)255, p0 -> (boolean)this.outline.getValue()));
        this.cGreen = (Setting<Integer>)this.register(new Setting("OL-Green", (Object)0, (Object)0, (Object)255, p0 -> (boolean)this.outline.getValue()));
        this.cBlue = (Setting<Integer>)this.register(new Setting("OL-Blue", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.outline.getValue()));
        this.cAlpha = (Setting<Integer>)this.register(new Setting("OL-Alpha", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.outline.getValue()));
        this.yaw = 0.0f;
        this.pitch = 0.0f;
        this.rotating = false;
    }
    
    public static List<BlockPos> getSphere(final BlockPos blockPos, final float n, final int n2, final boolean b, final boolean b2, final int n3) {
        final ArrayList<BlockPos> list = new ArrayList<BlockPos>();
        final int getX = blockPos.getX();
        final int getY = blockPos.getY();
        final int getZ = blockPos.getZ();
        for (int n4 = getX - (int)n; n4 <= getX + n; ++n4) {
            for (int n5 = getZ - (int)n; n5 <= getZ + n; ++n5) {
                int n6 = b2 ? (getY - (int)n) : getY;
                while (true) {
                    float n8 = 0.0f;
                    if (b2) {
                        final float n7 = getY + n;
                    }
                    else {
                        n8 = (float)(getY + n2);
                    }
                    if (n6 >= n8) {
                        break;
                    }
                    final double n9 = (getX - n4) * (getX - n4) + (getZ - n5) * (getZ - n5) + (b2 ? ((getY - n6) * (getY - n6)) : 0);
                    if (n9 < n * n && (!b || n9 >= (n - 1.0f) * (n - 1.0f))) {
                        list.add(new BlockPos(n4, n6 + n3, n5));
                    }
                    ++n6;
                }
            }
        }
        return list;
    }
    
    private NonNullList<BlockPos> placePostions(final float n) {
        final NonNullList create = NonNullList.create();
        create.addAll((Collection)getSphere(new BlockPos(Math.floor(OyVeyAutoCrystal.mc.player.posX), Math.floor(OyVeyAutoCrystal.mc.player.posY), Math.floor(OyVeyAutoCrystal.mc.player.posZ)), n, (int)n, false, true, 0).stream().filter(blockPos -> this.canPlaceCrystal(blockPos, true)).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList()));
        return (NonNullList<BlockPos>)create;
    }
    
    public void onCrystal() {
        if (OyVeyAutoCrystal.mc.world == null || OyVeyAutoCrystal.mc.player == null) {
            return;
        }
        this.realTarget = null;
        this.manualBreaker();
        this.crystalCount = 0;
        if (!(boolean)this.ignoreUseAmount.getValue()) {
            for (final Entity entity2 : OyVeyAutoCrystal.mc.world.loadedEntityList) {
                if (entity2 instanceof EntityEnderCrystal) {
                    if (!this.IsValidCrystal(entity2)) {
                        continue;
                    }
                    boolean b = false;
                    if (this.calculateDamage(this.target.getPosition().getX() + 0.5, this.target.getPosition().getY() + 1.0, this.target.getPosition().getZ() + 0.5, (Entity)this.target) >= (double)(float)this.minDamage.getValue()) {
                        b = true;
                    }
                    if (!b) {
                        continue;
                    }
                    ++this.crystalCount;
                }
            }
        }
        this.hotBarSlot = -1;
        if (OyVeyAutoCrystal.mc.player.getHeldItemOffhand().getItem() != Items.END_CRYSTAL) {
            int n = (OyVeyAutoCrystal.mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL) ? OyVeyAutoCrystal.mc.player.inventory.currentItem : -1;
            if (n == -1) {
                for (int i = 0; i < 9; ++i) {
                    if (OyVeyAutoCrystal.mc.player.inventory.getStackInSlot(i).getItem() == Items.END_CRYSTAL) {
                        n = i;
                        this.hotBarSlot = i;
                        break;
                    }
                }
            }
            if (n == -1) {
                this.pos = null;
                this.target = null;
                return;
            }
        }
        if (OyVeyAutoCrystal.mc.player.getHeldItemOffhand().getItem() == Items.GOLDEN_APPLE && OyVeyAutoCrystal.mc.player.getHeldItemMainhand().getItem() != Items.END_CRYSTAL) {
            this.pos = null;
            this.target = null;
            return;
        }
        if (this.target == null) {
            this.target = (EntityLivingBase)this.getTarget();
        }
        if (this.target == null) {
            this.crystal = null;
            return;
        }
        if (this.target.getDistance((Entity)OyVeyAutoCrystal.mc.player) > 12.0f) {
            this.crystal = null;
            this.target = null;
        }
        this.crystal = (EntityEnderCrystal)OyVeyAutoCrystal.mc.world.loadedEntityList.stream().filter(this::IsValidCrystal).map(entityEnderCrystal -> entityEnderCrystal).min(Comparator.comparing(entity -> this.target.getDistance(entity))).orElse(null);
        if (this.crystal != null && (boolean)this.explode.getValue() && this.breakTimer.passedMs(((Float)this.breakDelay.getValue()).longValue())) {
            this.breakTimer.reset();
            if (this.packetBreak.getValue()) {
                this.rotateTo((Entity)this.crystal);
                OyVeyAutoCrystal.mc.player.connection.sendPacket((Packet)new CPacketUseEntity((Entity)this.crystal));
            }
            else {
                this.rotateTo((Entity)this.crystal);
                OyVeyAutoCrystal.mc.playerController.attackEntity((EntityPlayer)OyVeyAutoCrystal.mc.player, (Entity)this.crystal);
            }
            if (this.swingMode.getValue() == SwingMode.MainHand) {
                OyVeyAutoCrystal.mc.player.swingArm(EnumHand.MAIN_HAND);
            }
            else if (this.swingMode.getValue() == SwingMode.OffHand) {
                OyVeyAutoCrystal.mc.player.swingArm(EnumHand.OFF_HAND);
            }
        }
        if (this.placeTimer.passedMs(((Float)this.placeDelay.getValue()).longValue()) && (boolean)this.place.getValue()) {
            this.placeTimer.reset();
            double n2 = 0.5;
            for (final BlockPos pos : this.placePostions((float)this.placeRange.getValue())) {
                if (pos != null && this.target != null && OyVeyAutoCrystal.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(pos)).isEmpty() && this.target.getDistance((double)pos.getX(), (double)pos.getY(), (double)pos.getZ()) <= (float)this.targetRange.getValue() && !this.target.isDead) {
                    if (this.target.getHealth() + this.target.getAbsorptionAmount() <= 0.0f) {
                        continue;
                    }
                    final double n3 = this.calculateDamage(pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5, (Entity)this.target);
                    this.armor = false;
                    for (final ItemStack itemStack : this.target.getArmorInventoryList()) {
                        if (100 - (int)((1.0f - (itemStack.getMaxDamage() - (float)itemStack.getItemDamage()) / itemStack.getMaxDamage()) * 100.0f) > (float)this.minArmor.getValue()) {
                            continue;
                        }
                        this.armor = true;
                    }
                    Label_1211: {
                        if (n3 < (float)this.minDamage.getValue()) {
                            if (this.facePlaceSword.getValue()) {
                                if (this.target.getAbsorptionAmount() + this.target.getHealth() <= (float)this.facePlace.getValue()) {
                                    break Label_1211;
                                }
                            }
                            else if (!(OyVeyAutoCrystal.mc.player.getHeldItemMainhand().getItem() instanceof ItemSword) && this.target.getAbsorptionAmount() + this.target.getHealth() <= (float)this.facePlace.getValue()) {
                                break Label_1211;
                            }
                            if (this.facePlaceSword.getValue()) {
                                if (!this.armor) {
                                    continue;
                                }
                            }
                            else if (OyVeyAutoCrystal.mc.player.getHeldItemMainhand().getItem() instanceof ItemSword || !this.armor) {
                                continue;
                            }
                        }
                    }
                    final double n4;
                    if ((n4 = this.calculateDamage(pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5, (Entity)OyVeyAutoCrystal.mc.player)) + (this.suicide.getValue() ? 2.0 : 0.5) >= OyVeyAutoCrystal.mc.player.getHealth() + OyVeyAutoCrystal.mc.player.getAbsorptionAmount() && n4 >= n3 && n3 < this.target.getHealth() + this.target.getAbsorptionAmount()) {
                        continue;
                    }
                    if (n2 >= n3) {
                        continue;
                    }
                    this.pos = pos;
                    n2 = n3;
                }
            }
            if (n2 == 0.5) {
                this.pos = null;
                this.target = null;
                this.realTarget = null;
                return;
            }
            this.realTarget = this.target;
            if (this.hotBarSlot != -1 && (boolean)this.autoswitch.getValue() && !OyVeyAutoCrystal.mc.player.isPotionActive(MobEffects.WEAKNESS) && this.switchmode.getValue() == SwitchMode.Normal && !(boolean)this.silentSwitch.getValue()) {
                OyVeyAutoCrystal.mc.player.inventory.currentItem = this.hotBarSlot;
            }
            final int hotbarBlock = InventoryUtil.findHotbarBlock((Class)ItemEndCrystal.class);
            final int currentItem = OyVeyAutoCrystal.mc.player.inventory.currentItem;
            EnumHand getActiveHand = null;
            if (this.switchmode.getValue() == SwitchMode.Silent && hotbarBlock != -1) {
                if (OyVeyAutoCrystal.mc.player.isHandActive() && (boolean)this.silentSwitch.getValue()) {
                    getActiveHand = OyVeyAutoCrystal.mc.player.getActiveHand();
                }
                OyVeyAutoCrystal.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(hotbarBlock));
            }
            if (!(boolean)this.ignoreUseAmount.getValue()) {
                int intValue = (int)this.wasteAmount.getValue();
                if (this.crystalCount >= intValue) {
                    return;
                }
                if (n2 < (float)this.minDamage.getValue()) {
                    intValue = 1;
                }
                if (this.crystalCount < intValue && this.pos != null) {
                    this.rotateToPos(this.pos);
                    OyVeyAutoCrystal.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(this.pos, EnumFacing.UP, (OyVeyAutoCrystal.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
                }
            }
            else if (this.pos != null) {
                this.rotateToPos(this.pos);
                OyVeyAutoCrystal.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(this.pos, EnumFacing.UP, (OyVeyAutoCrystal.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
            }
            if (this.switchmode.getValue() == SwitchMode.Silent && hotbarBlock != -1) {
                if ((boolean)this.silentSwitch.getValue() && getActiveHand != null) {
                    OyVeyAutoCrystal.mc.player.setActiveHand(getActiveHand);
                }
                OyVeyAutoCrystal.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(currentItem));
            }
        }
    }
    
    private boolean canSeePos(final BlockPos blockPos) {
        return OyVeyAutoCrystal.mc.world.rayTraceBlocks(new Vec3d(OyVeyAutoCrystal.mc.player.posX, OyVeyAutoCrystal.mc.player.posY + OyVeyAutoCrystal.mc.player.getEyeHeight(), OyVeyAutoCrystal.mc.player.posZ), new Vec3d((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ()), false, true, false) == null;
    }
    
    private void rotateToPos(final BlockPos blockPos) {
        if (this.rotate.getValue()) {
            final float[] calcAngle = MathUtil.calcAngle(OyVeyAutoCrystal.mc.player.getPositionEyes(OyVeyAutoCrystal.mc.getRenderPartialTicks()), new Vec3d((double)(blockPos.getX() + 0.5f), (double)(blockPos.getY() - 0.5f), (double)(blockPos.getZ() + 0.5f)));
            this.yaw = calcAngle[0];
            this.pitch = calcAngle[1];
            this.rotating = true;
        }
    }
    
    @Override
    public void onTick() {
        this.onCrystal();
    }
    
    public enum SwingMode
    {
        OffHand, 
        None, 
        MainHand;
    }
    
    public enum SwitchMode
    {
        Normal, 
        Silent;
    }
}
