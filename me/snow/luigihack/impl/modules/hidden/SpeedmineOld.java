//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.hidden;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.block.state.*;
import net.minecraft.enchantment.*;
import net.minecraft.block.material.*;
import me.snow.luigihack.*;
import net.minecraft.network.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraft.network.play.client.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.util.*;
import me.snow.luigihack.api.event.events.*;
import java.awt.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.block.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;

public class SpeedmineOld extends Module
{
    public /* synthetic */ Setting<Boolean> silentSwitch;
    private final /* synthetic */ Timer timer;
    public /* synthetic */ Setting<Boolean> reset;
    public /* synthetic */ Setting<Boolean> doubleBreak;
    public /* synthetic */ Setting<Boolean> box;
    public /* synthetic */ Setting<Boolean> noBreakAnim;
    public /* synthetic */ Setting<Boolean> noTrace;
    public /* synthetic */ Setting<Boolean> webSwitch;
    public /* synthetic */ Setting<Boolean> render;
    public /* synthetic */ Setting<Float> damage;
    public /* synthetic */ Setting<Boolean> noDelay;
    private final /* synthetic */ Setting<Float> lineWidth;
    public /* synthetic */ Setting<Boolean> pickaxe;
    private /* synthetic */ EnumFacing lastFacing;
    public /* synthetic */ IBlockState currentBlockState;
    public /* synthetic */ Setting<Boolean> noSwing;
    public /* synthetic */ Setting<Mode> mode;
    public /* synthetic */ Setting<Boolean> noGapTrace;
    public /* synthetic */ Setting<Boolean> illegal;
    public /* synthetic */ BlockPos currentPos;
    private final /* synthetic */ Setting<Integer> boxAlpha;
    private /* synthetic */ BlockPos lastPos;
    public /* synthetic */ Setting<Boolean> tweaks;
    private static /* synthetic */ SpeedmineOld INSTANCE;
    public /* synthetic */ Setting<Boolean> allow;
    public /* synthetic */ Setting<Boolean> outline;
    private /* synthetic */ boolean isMining;
    private final /* synthetic */ Setting<Float> range;
    
    public static SpeedmineOld getInstance() {
        if (SpeedmineOld.INSTANCE == null) {
            SpeedmineOld.INSTANCE = new SpeedmineOld();
        }
        return SpeedmineOld.INSTANCE;
    }
    
    @Override
    public String getDisplayInfo() {
        return this.mode.currentEnumName();
    }
    
    public float getDigSpeed(final IBlockState blockState) {
        float destroySpeed = this.getDestroySpeed(blockState);
        if (destroySpeed > 1.0f) {
            final ItemStack efficientItem = this.getEfficientItem(blockState);
            final int getEnchantmentLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.EFFICIENCY, efficientItem);
            if (getEnchantmentLevel > 0 && !efficientItem.isEmpty()) {
                destroySpeed += (float)(StrictMath.pow(getEnchantmentLevel, 2.0) + 1.0);
            }
        }
        if (SpeedmineOld.mc.player.isPotionActive(MobEffects.HASTE)) {
            destroySpeed *= 1.0f + (SpeedmineOld.mc.player.getActivePotionEffect(MobEffects.HASTE).getAmplifier() + 1) * 0.2f;
        }
        if (SpeedmineOld.mc.player.isPotionActive(MobEffects.MINING_FATIGUE)) {
            float n = 0.0f;
            switch (SpeedmineOld.mc.player.getActivePotionEffect(MobEffects.MINING_FATIGUE).getAmplifier()) {
                case 0: {
                    n = 0.3f;
                    break;
                }
                case 1: {
                    n = 0.09f;
                    break;
                }
                case 2: {
                    n = 0.0027f;
                    break;
                }
                default: {
                    n = 8.1E-4f;
                    break;
                }
            }
            destroySpeed *= n;
        }
        if (SpeedmineOld.mc.player.isInsideOfMaterial(Material.WATER) && !EnchantmentHelper.getAquaAffinityModifier((EntityLivingBase)SpeedmineOld.mc.player)) {
            destroySpeed /= 5.0f;
        }
        if (!SpeedmineOld.mc.player.onGround) {
            destroySpeed /= 5.0f;
        }
        return (destroySpeed < 0.0f) ? 0.0f : destroySpeed;
    }
    
    private int getPickSlot() {
        for (int i = 0; i < 9; ++i) {
            if (SpeedmineOld.mc.player.inventory.getStackInSlot(i).getItem() == Items.DIAMOND_PICKAXE) {
                return i;
            }
        }
        return -1;
    }
    
    static {
        SpeedmineOld.INSTANCE = new SpeedmineOld();
    }
    
    @Override
    public void onTick() {
        if (this.currentPos != null) {
            if (SpeedmineOld.mc.player != null && SpeedmineOld.mc.player.getDistanceSq(this.currentPos) > MathUtil.square((float)this.range.getValue())) {
                this.currentPos = null;
                this.currentBlockState = null;
                return;
            }
            if (SpeedmineOld.mc.player != null && (boolean)this.silentSwitch.getValue() && this.timer.passedMs((long)(int)(2000.0f * LuigiHack.serverManager.getTpsFactor())) && this.getPickSlot() != -1) {
                SpeedmineOld.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(this.getPickSlot()));
            }
            if (!SpeedmineOld.mc.world.getBlockState(this.currentPos).equals(this.currentBlockState) || SpeedmineOld.mc.world.getBlockState(this.currentPos).getBlock() == Blocks.AIR) {
                this.currentPos = null;
                this.currentBlockState = null;
            }
            else if ((boolean)this.webSwitch.getValue() && this.currentBlockState.getBlock() == Blocks.WEB && SpeedmineOld.mc.player.getHeldItemMainhand().getItem() instanceof ItemPickaxe) {
                InventoryUtil.switchToHotbarSlot((Class)ItemSword.class, false);
            }
        }
    }
    
    public float getBlockStrength(final IBlockState blockState, final BlockPos blockPos) {
        final float getBlockHardness = blockState.getBlockHardness((World)SpeedmineOld.mc.world, blockPos);
        if (getBlockHardness < 0.0f) {
            return 0.0f;
        }
        if (!this.canHarvestBlock(blockState.getBlock(), blockPos)) {
            return this.getDigSpeed(blockState) / getBlockHardness / 100.0f;
        }
        return this.getDigSpeed(blockState) / getBlockHardness / 30.0f;
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        if (fullNullCheck()) {
            return;
        }
        if (send.getStage() == 0) {
            if ((boolean)this.noSwing.getValue() && send.getPacket() instanceof CPacketAnimation) {
                send.setCanceled(true);
            }
            final CPacketPlayerDigging cPacketPlayerDigging;
            if ((boolean)this.noBreakAnim.getValue() && send.getPacket() instanceof CPacketPlayerDigging && (cPacketPlayerDigging = (CPacketPlayerDigging)send.getPacket()) != null && cPacketPlayerDigging.getPosition() != null) {
                try {
                    final Iterator iterator = SpeedmineOld.mc.world.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(cPacketPlayerDigging.getPosition())).iterator();
                    while (iterator.hasNext()) {
                        if (!(iterator.next() instanceof EntityEnderCrystal)) {
                            continue;
                        }
                        this.showAnimation();
                        return;
                    }
                }
                catch (Exception ex) {}
                if (cPacketPlayerDigging.getAction().equals((Object)CPacketPlayerDigging.Action.START_DESTROY_BLOCK)) {
                    this.showAnimation(true, cPacketPlayerDigging.getPosition(), cPacketPlayerDigging.getFacing());
                }
                if (cPacketPlayerDigging.getAction().equals((Object)CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK)) {
                    this.showAnimation();
                }
            }
        }
    }
    
    public ItemStack getEfficientItem(final IBlockState blockState) {
        int n = -1;
        double n2 = 0.0;
        for (int i = 0; i < 9; ++i) {
            if (!SpeedmineOld.mc.player.inventory.getStackInSlot(i).isEmpty()) {
                float getDestroySpeed = SpeedmineOld.mc.player.inventory.getStackInSlot(i).getDestroySpeed(blockState);
                if (getDestroySpeed > 1.0f) {
                    if (EnchantmentHelper.getEnchantmentLevel(Enchantments.EFFICIENCY, SpeedmineOld.mc.player.inventory.getStackInSlot(i)) > 0) {
                        getDestroySpeed += (float)(StrictMath.pow(EnchantmentHelper.getEnchantmentLevel(Enchantments.EFFICIENCY, SpeedmineOld.mc.player.inventory.getStackInSlot(i)), 2.0) + 1.0);
                    }
                    if (getDestroySpeed > n2) {
                        n2 = getDestroySpeed;
                        n = i;
                    }
                }
            }
        }
        if (n != -1) {
            return SpeedmineOld.mc.player.inventory.getStackInSlot(n);
        }
        return SpeedmineOld.mc.player.inventory.getStackInSlot(SpeedmineOld.mc.player.inventory.currentItem);
    }
    
    @SubscribeEvent
    public void onBlockEvent(final BlockEvent blockEvent) {
        if (fullNullCheck()) {
            return;
        }
        if (blockEvent.getStage() == 3 && SpeedmineOld.mc.world.getBlockState(blockEvent.pos).getBlock() instanceof BlockEndPortalFrame) {
            SpeedmineOld.mc.world.getBlockState(blockEvent.pos).getBlock().setHardness(50.0f);
        }
        if (blockEvent.getStage() == 3 && (boolean)this.reset.getValue() && SpeedmineOld.mc.playerController.curBlockDamageMP > 0.1f) {
            SpeedmineOld.mc.playerController.isHittingBlock = true;
        }
        if (blockEvent.getStage() == 4 && (boolean)this.tweaks.getValue()) {
            if (BlockUtil.canBreak(blockEvent.pos)) {
                if (this.reset.getValue()) {
                    SpeedmineOld.mc.playerController.isHittingBlock = false;
                }
                switch ((Mode)this.mode.getValue()) {
                    case PACKET: {
                        if (this.currentPos == null) {
                            this.currentPos = blockEvent.pos;
                            this.currentBlockState = SpeedmineOld.mc.world.getBlockState(this.currentPos);
                            this.timer.reset();
                        }
                        SpeedmineOld.mc.player.swingArm(EnumHand.MAIN_HAND);
                        SpeedmineOld.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, blockEvent.pos, blockEvent.facing));
                        SpeedmineOld.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, blockEvent.pos, blockEvent.facing));
                        blockEvent.setCanceled(true);
                        break;
                    }
                    case DAMAGE: {
                        if (SpeedmineOld.mc.playerController.curBlockDamageMP < (float)this.damage.getValue()) {
                            break;
                        }
                        SpeedmineOld.mc.playerController.curBlockDamageMP = 1.0f;
                        break;
                    }
                    case INSTANT: {
                        SpeedmineOld.mc.player.swingArm(EnumHand.MAIN_HAND);
                        SpeedmineOld.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, blockEvent.pos, blockEvent.facing));
                        SpeedmineOld.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, blockEvent.pos, blockEvent.facing));
                        SpeedmineOld.mc.playerController.onPlayerDestroyBlock(blockEvent.pos);
                        SpeedmineOld.mc.world.setBlockToAir(blockEvent.pos);
                        break;
                    }
                }
            }
            final BlockPos add;
            if ((boolean)this.doubleBreak.getValue() && BlockUtil.canBreak(add = blockEvent.pos.add(0, 1, 0)) && SpeedmineOld.mc.player.getDistance((double)add.getX(), (double)add.getY(), (double)add.getZ()) <= 5.0) {
                SpeedmineOld.mc.player.swingArm(EnumHand.MAIN_HAND);
                SpeedmineOld.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, add, blockEvent.facing));
                SpeedmineOld.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, add, blockEvent.facing));
                SpeedmineOld.mc.playerController.onPlayerDestroyBlock(add);
                SpeedmineOld.mc.world.setBlockToAir(add);
            }
        }
    }
    
    @Override
    public void onRender3D(final Render3DEvent render3DEvent) {
        if ((boolean)this.render.getValue() && this.currentPos != null) {
            final Color color = new Color(this.timer.passedMs((long)(int)(2000.0f * LuigiHack.serverManager.getTpsFactor())) ? 0 : 255, this.timer.passedMs((long)(int)(2000.0f * LuigiHack.serverManager.getTpsFactor())) ? 255 : 0, 0, 255);
            RenderUtil.drawBoxESP(this.currentPos, color, false, color, (float)this.lineWidth.getValue(), (boolean)this.outline.getValue(), (boolean)this.box.getValue(), (int)this.boxAlpha.getValue(), false);
        }
    }
    
    @Override
    public void onUpdate() {
        if (fullNullCheck()) {
            return;
        }
        if (this.noDelay.getValue()) {
            SpeedmineOld.mc.playerController.blockHitDelay = 0;
        }
        if (this.isMining && this.lastPos != null && this.lastFacing != null && (boolean)this.noBreakAnim.getValue()) {
            SpeedmineOld.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, this.lastPos, this.lastFacing));
        }
        if ((boolean)this.reset.getValue() && SpeedmineOld.mc.gameSettings.keyBindUseItem.isKeyDown() && !(boolean)this.allow.getValue()) {
            SpeedmineOld.mc.playerController.isHittingBlock = false;
        }
    }
    
    public float getDestroySpeed(final IBlockState blockState) {
        float n = 1.0f;
        if (this.getEfficientItem(blockState) != null && !this.getEfficientItem(blockState).isEmpty()) {
            n *= this.getEfficientItem(blockState).getDestroySpeed(blockState);
        }
        return n;
    }
    
    public void showAnimation() {
        this.showAnimation(false, null, null);
    }
    
    public SpeedmineOld() {
        super("SpeedmineOld", "Speeds up mining.", Category.PLAYER, true, false, false);
        this.range = (Setting<Float>)this.register(new Setting("Range", (Object)10.0f, (Object)0.0f, (Object)50.0f));
        this.timer = new Timer();
        this.tweaks = (Setting<Boolean>)this.register(new Setting("Tweaks", (Object)true));
        this.mode = (Setting<Mode>)this.register(new Setting("Mode", (Object)Mode.PACKET, p0 -> (boolean)this.tweaks.getValue()));
        this.reset = (Setting<Boolean>)this.register(new Setting("Reset", (Object)true));
        this.damage = (Setting<Float>)this.register(new Setting("Damage", (Object)0.7f, (Object)0.0f, (Object)1.0f, p0 -> this.mode.getValue() == Mode.DAMAGE && (boolean)this.tweaks.getValue()));
        this.noBreakAnim = (Setting<Boolean>)this.register(new Setting("NoBreakAnim", (Object)false));
        this.noDelay = (Setting<Boolean>)this.register(new Setting("NoDelay", (Object)false));
        this.noSwing = (Setting<Boolean>)this.register(new Setting("NoSwing", (Object)false));
        this.noTrace = (Setting<Boolean>)this.register(new Setting("NoTrace", (Object)false));
        this.noGapTrace = (Setting<Boolean>)this.register(new Setting("NoGapTrace", (Object)false, p0 -> (boolean)this.noTrace.getValue()));
        this.allow = (Setting<Boolean>)this.register(new Setting("AllowMultiTask", (Object)false));
        this.pickaxe = (Setting<Boolean>)this.register(new Setting("Pickaxe", (Object)true, p0 -> (boolean)this.noTrace.getValue()));
        this.doubleBreak = (Setting<Boolean>)this.register(new Setting("DoubleBreak", (Object)false));
        this.webSwitch = (Setting<Boolean>)this.register(new Setting("WebSwitch", (Object)false));
        this.silentSwitch = (Setting<Boolean>)this.register(new Setting("SilentSwitch", (Object)false));
        this.illegal = (Setting<Boolean>)this.register(new Setting("IllegalMine", (Object)false));
        this.render = (Setting<Boolean>)this.register(new Setting("Render", (Object)false));
        this.box = (Setting<Boolean>)this.register(new Setting("Box", (Object)false, p0 -> (boolean)this.render.getValue()));
        this.boxAlpha = (Setting<Integer>)this.register(new Setting("BoxAlpha", (Object)85, (Object)0, (Object)255, p0 -> (boolean)this.box.getValue() && (boolean)this.render.getValue()));
        this.outline = (Setting<Boolean>)this.register(new Setting("Outline", (Object)true, p0 -> (boolean)this.render.getValue()));
        this.lineWidth = (Setting<Float>)this.register(new Setting("LineWidth", (Object)1.0f, (Object)0.1f, (Object)5.0f, p0 -> (boolean)this.outline.getValue() && (boolean)this.render.getValue()));
        this.isMining = false;
        this.lastPos = null;
        this.lastFacing = null;
        this.setInstance();
    }
    
    private void setInstance() {
        SpeedmineOld.INSTANCE = this;
    }
    
    private void showAnimation(final boolean isMining, final BlockPos lastPos, final EnumFacing lastFacing) {
        this.isMining = isMining;
        this.lastPos = lastPos;
        this.lastFacing = lastFacing;
    }
    
    public boolean canHarvestBlock(final Block block, final BlockPos blockPos) {
        final IBlockState getBlockState = SpeedmineOld.mc.world.getBlockState(blockPos);
        final IBlockState getActualState = getBlockState.getBlock().getActualState(getBlockState, (IBlockAccess)SpeedmineOld.mc.world, blockPos);
        if (getActualState.getMaterial().isToolNotRequired()) {
            return true;
        }
        final ItemStack efficientItem = this.getEfficientItem(getActualState);
        final String harvestTool = block.getHarvestTool(getActualState);
        if (efficientItem.isEmpty() || harvestTool == null) {
            return SpeedmineOld.mc.player.canHarvestBlock(getActualState);
        }
        final int harvestLevel = efficientItem.getItem().getHarvestLevel(efficientItem, harvestTool, (EntityPlayer)SpeedmineOld.mc.player, getActualState);
        if (harvestLevel < 0) {
            return SpeedmineOld.mc.player.canHarvestBlock(getActualState);
        }
        return harvestLevel >= block.getHarvestLevel(getActualState);
    }
    
    public enum Mode
    {
        INSTANT, 
        PACKET, 
        DAMAGE;
    }
}
