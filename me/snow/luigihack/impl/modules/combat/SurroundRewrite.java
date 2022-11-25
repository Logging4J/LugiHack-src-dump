//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.combat;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.util.math.*;
import com.mojang.realmsclient.gui.*;
import me.snow.luigihack.impl.command.*;
import net.minecraft.entity.*;
import net.minecraft.block.*;
import me.snow.luigihack.impl.modules.combat.autocrystal.*;
import me.snow.luigihack.api.util.*;
import me.snow.luigihack.api.util.skid.oyvey.*;
import net.minecraft.util.*;
import java.util.*;
import net.minecraft.entity.player.*;
import me.snow.luigihack.*;
import net.minecraft.init.*;

public class SurroundRewrite extends Module
{
    private final /* synthetic */ Set<Vec3d> extendingBlocks;
    private final /* synthetic */ Setting<Boolean> helpingBlocks;
    private /* synthetic */ boolean didPlace;
    private final /* synthetic */ Setting<Boolean> antiPedo;
    private /* synthetic */ boolean offHand;
    private final /* synthetic */ Timer retryTimer;
    private final /* synthetic */ Setting<Integer> delay;
    private /* synthetic */ int isSafe;
    private /* synthetic */ int obbySlot;
    private static /* synthetic */ SurroundRewrite INSTANCE;
    private /* synthetic */ int lastHotbarSlot;
    private /* synthetic */ BlockPos startPos;
    private /* synthetic */ int extenders;
    /* synthetic */ Vec3d CenterPos;
    private final /* synthetic */ Setting<Boolean> attack;
    private final /* synthetic */ Setting<Boolean> allowEC;
    private /* synthetic */ int placements;
    private final /* synthetic */ Setting<Boolean> rotate;
    private final /* synthetic */ Map<BlockPos, Integer> retries;
    public static /* synthetic */ boolean isPlacing;
    private final /* synthetic */ Setting<Integer> retry;
    private final /* synthetic */ Setting<Center> center;
    private final /* synthetic */ Setting<Integer> extender;
    private final /* synthetic */ Setting<Boolean> info;
    private /* synthetic */ boolean isSneaking;
    private final /* synthetic */ Setting<Boolean> packet;
    private final /* synthetic */ Setting<Integer> blocksPerTick;
    private final /* synthetic */ Timer timer;
    
    @Override
    public void onDisable() {
        if (nullCheck()) {
            return;
        }
        super.onDisable();
        SurroundRewrite.isPlacing = false;
        this.isSneaking = EntityUtil.stopSneaking(this.isSneaking);
    }
    
    private boolean check() {
        if (nullCheck()) {
            return true;
        }
        final int hotbarBlock = InventoryUtil.findHotbarBlock((Class)BlockObsidian.class);
        final int hotbarBlock2 = InventoryUtil.findHotbarBlock((Class)BlockEnderChest.class);
        if (hotbarBlock == -1 && hotbarBlock2 == -1) {
            this.toggle();
        }
        this.offHand = InventoryUtil.isBlock(SurroundRewrite.mc.player.getHeldItemOffhand().getItem(), (Class)BlockObsidian.class);
        SurroundRewrite.isPlacing = false;
        this.didPlace = false;
        this.extenders = 1;
        this.placements = 0;
        this.obbySlot = InventoryUtil.findHotbarBlock((Class)BlockObsidian.class);
        final int hotbarBlock3 = InventoryUtil.findHotbarBlock((Class)BlockEnderChest.class);
        if (this.isOff()) {
            return true;
        }
        if (this.retryTimer.passedMs(2500L)) {
            this.retries.clear();
            this.retryTimer.reset();
        }
        if (this.obbySlot == -1 && !this.offHand && hotbarBlock3 == -1) {
            this.obbySlot = hotbarBlock3;
            if (!(boolean)this.allowEC.getValue() || hotbarBlock3 == -1) {
                Command.sendMessage(String.valueOf(new StringBuilder().append("Out of blocks, disabling ").append(ChatFormatting.RED).append("Surround")));
                this.disable();
                return true;
            }
        }
        if (this.obbySlot == -1 && !this.offHand && hotbarBlock3 == -1) {
            Command.sendMessage(String.valueOf(new StringBuilder().append("<").append(this.getDisplayName()).append("> ").append(ChatFormatting.RED).append("No Obsidian in hotbar disabling...")));
            this.disable();
            return true;
        }
        this.isSneaking = EntityUtil.stopSneaking(this.isSneaking);
        if (SurroundRewrite.mc.player.inventory.currentItem != this.lastHotbarSlot && SurroundRewrite.mc.player.inventory.currentItem != this.obbySlot && SurroundRewrite.mc.player.inventory.currentItem != hotbarBlock3) {
            this.lastHotbarSlot = SurroundRewrite.mc.player.inventory.currentItem;
        }
        if (!this.startPos.equals((Object)EntityUtil.getRoundedBlockPos((Entity)SurroundRewrite.mc.player))) {
            this.disable();
            return true;
        }
        return !this.timer.passedMs((long)(int)this.delay.getValue());
    }
    
    public static List<Vec3d> getUnsafeBlocksFromVec3d(final Vec3d vec3d, final int n) {
        final ArrayList<Vec3d> list = new ArrayList<Vec3d>(4);
        for (final Vec3d e : getOffsets(n)) {
            final Block getBlock = SurroundRewrite.mc.world.getBlockState(new BlockPos(vec3d).add(e.x, e.y, e.z)).getBlock();
            if (getBlock instanceof BlockAir || getBlock instanceof BlockLiquid || getBlock instanceof BlockTallGrass || getBlock instanceof BlockFire || getBlock instanceof BlockDeadBush || getBlock instanceof BlockSnow) {
                list.add(e);
            }
        }
        return list;
    }
    
    public static Vec3d[] getOffsets(final int n) {
        final List<Vec3d> offsetList = getOffsetList(n);
        return offsetList.toArray(new Vec3d[offsetList.size()]);
    }
    
    public static List<Vec3d> getUnsafeBlocks(final Entity entity, final int n) {
        return getUnsafeBlocksFromVec3d(entity.getPositionVector(), n);
    }
    
    private void processExtendingBlocks() {
        if (this.extendingBlocks.size() == 2 && this.extenders < (int)this.extender.getValue()) {
            final Vec3d[] array = new Vec3d[2];
            int n = 0;
            final Iterator<Vec3d> iterator = this.extendingBlocks.iterator();
            while (iterator.hasNext()) {
                array[n] = iterator.next();
                ++n;
            }
            if (this.attack.getValue()) {
                BlockUtil.doBreak(new BlockPos(this.areClose(array).x, this.areClose(array).y, this.areClose(array).z), (double)(float)AutoCrystal.getInstance().maxSelfBreak.getValue());
            }
            final int placements = this.placements;
            if (this.areClose(array) != null) {
                this.placeBlocks(this.areClose(array), EntityUtil2.getUnsafeBlockArrayFromVec3d(this.areClose(array), 0, (boolean)this.helpingBlocks.getValue()), true, false, true);
            }
            if (placements < this.placements) {
                this.extendingBlocks.clear();
            }
        }
        else if (this.extendingBlocks.size() > 2 || this.extenders >= (int)this.extender.getValue()) {
            this.extendingBlocks.clear();
        }
    }
    
    private boolean placeBlocks(final Vec3d vec3d, final Vec3d[] array, final boolean b, final boolean b2, final boolean b3) {
        for (final Vec3d vec3d2 : array) {
            boolean placeBlocks = true;
            final BlockPos add = new BlockPos(vec3d).add(vec3d2.x, vec3d2.y, vec3d2.z);
            switch (BlockUtil.isPositionPlaceable(add, false)) {
                case 1: {
                    if (this.retries.get(add) == null || this.retries.get(add) < (int)this.retry.getValue()) {
                        this.placeBlock(add);
                        this.retries.put(add, (this.retries.get(add) == null) ? 1 : (this.retries.get(add) + 1));
                        this.retryTimer.reset();
                        break;
                    }
                    if ((int)this.extender.getValue() <= 0 || b3) {
                        break;
                    }
                    if (this.extenders >= (int)this.extender.getValue()) {
                        break;
                    }
                    this.placeBlocks(SurroundRewrite.mc.player.getPositionVector().add(vec3d2), EntityUtil2.getUnsafeBlockArrayFromVec3d(SurroundRewrite.mc.player.getPositionVector().add(vec3d2), 0, true), b, false, true);
                    this.extendingBlocks.add(vec3d2);
                    ++this.extenders;
                    break;
                }
                case 2: {
                    if (!b) {
                        break;
                    }
                    placeBlocks = this.placeBlocks(vec3d, BlockUtil.getHelpingBlocks(vec3d2), false, true, true);
                }
                case 3: {
                    if (placeBlocks) {
                        this.placeBlock(add);
                    }
                    if (!b2) {
                        break;
                    }
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean isSafe2(final Entity entity, final int n) {
        return getUnsafeBlocks(entity, n).size() == 0;
    }
    
    public static List<Vec3d> getOffsetList(final int n) {
        final ArrayList<Vec3d> list = new ArrayList<Vec3d>(4);
        list.add(new Vec3d(-1.0, (double)n, 0.0));
        list.add(new Vec3d(1.0, (double)n, 0.0));
        list.add(new Vec3d(0.0, (double)n, -1.0));
        list.add(new Vec3d(0.0, (double)n, 1.0));
        return list;
    }
    
    @Override
    public String getDisplayInfo() {
        if (!(boolean)this.info.getValue()) {
            return null;
        }
        switch (this.isSafe) {
            case 0: {
                return String.valueOf(new StringBuilder().append(ChatFormatting.RED).append("Unsafe"));
            }
            case 1: {
                return String.valueOf(new StringBuilder().append(ChatFormatting.YELLOW).append("Safe"));
            }
            default: {
                return String.valueOf(new StringBuilder().append(ChatFormatting.GREEN).append("Safe"));
            }
        }
    }
    
    private void placeBlock(final BlockPos blockPos) {
        if (this.placements < (int)this.blocksPerTick.getValue()) {
            final int currentItem = SurroundRewrite.mc.player.inventory.currentItem;
            final int hotbarBlock = InventoryUtil.findHotbarBlock((Class)BlockObsidian.class);
            final int hotbarBlock2 = InventoryUtil.findHotbarBlock((Class)BlockEnderChest.class);
            if (hotbarBlock == -1 && hotbarBlock2 == -1) {
                this.toggle();
            }
            SurroundRewrite.isPlacing = true;
            SurroundRewrite.mc.player.inventory.currentItem = ((hotbarBlock == -1) ? hotbarBlock2 : hotbarBlock);
            SurroundRewrite.mc.playerController.updateController();
            this.isSneaking = BlockUtil.placeBlock(blockPos, this.offHand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, (boolean)this.rotate.getValue(), (boolean)this.packet.getValue(), this.isSneaking);
            SurroundRewrite.mc.player.inventory.currentItem = currentItem;
            SurroundRewrite.mc.playerController.updateController();
            this.didPlace = true;
            ++this.placements;
        }
    }
    
    public static SurroundRewrite getInstance() {
        if (SurroundRewrite.INSTANCE == null) {
            SurroundRewrite.INSTANCE = new SurroundRewrite();
        }
        return SurroundRewrite.INSTANCE;
    }
    
    public static Vec3d[] getUnsafeBlockArray(final Entity entity, final int n) {
        final List<Vec3d> unsafeBlocks = getUnsafeBlocks(entity, n);
        return unsafeBlocks.toArray(new Vec3d[unsafeBlocks.size()]);
    }
    
    @Override
    public void onTick() {
        this.doFeetPlace();
    }
    
    public SurroundRewrite() {
        super("Surround", "awa surround", Category.COMBAT, true, false, false);
        this.packet = (Setting<Boolean>)this.register(new Setting("Packets", (Object)false));
        this.center = (Setting<Center>)this.register(new Setting("TP Center", (Object)Center.None));
        this.rotate = (Setting<Boolean>)this.register(new Setting("Rotate", (Object)false));
        this.delay = (Setting<Integer>)this.register(new Setting("Delay/Place", (Object)0, (Object)0, (Object)250));
        this.blocksPerTick = (Setting<Integer>)this.register(new Setting("Block/Place", (Object)12, (Object)1, (Object)20));
        this.allowEC = (Setting<Boolean>)this.register(new Setting("AllowEChests", (Object)true));
        this.info = (Setting<Boolean>)this.register(new Setting("DisplayInfo", (Object)false));
        this.helpingBlocks = (Setting<Boolean>)this.register(new Setting("HelpingBlocks", (Object)true));
        this.antiPedo = (Setting<Boolean>)this.register(new Setting("AlwaysHelp", (Object)false));
        this.attack = (Setting<Boolean>)this.register(new Setting("Attack", (Object)false));
        this.retry = (Setting<Integer>)this.register(new Setting("Retry", (Object)4, (Object)1, (Object)15));
        this.extender = (Setting<Integer>)this.register(new Setting("Extend", (Object)1, (Object)0, (Object)4));
        this.CenterPos = Vec3d.ZERO;
        SurroundRewrite.INSTANCE = this;
        this.timer = new Timer();
        this.retryTimer = new Timer();
        this.didPlace = false;
        this.placements = 0;
        this.extendingBlocks = new HashSet<Vec3d>();
        this.extenders = 1;
        this.obbySlot = -1;
        this.offHand = false;
        this.retries = new HashMap<BlockPos, Integer>();
    }
    
    static {
        SurroundRewrite.isPlacing = false;
    }
    
    private Vec3d areClose(final Vec3d[] array) {
        int n = 0;
        for (final Vec3d vec3d : array) {
            final Vec3d[] unsafeBlockArray = EntityUtil2.getUnsafeBlockArray((Entity)SurroundRewrite.mc.player, 0, true);
            for (int length2 = unsafeBlockArray.length, j = 0; j < length2; ++j) {
                if (vec3d.equals((Object)unsafeBlockArray[j])) {
                    ++n;
                }
            }
        }
        if (n == 2) {
            return SurroundRewrite.mc.player.getPositionVector().add(array[0].add(array[1]));
        }
        return null;
    }
    
    @Override
    public void onEnable() {
        if (fullNullCheck()) {
            this.disable();
        }
        super.onEnable();
        this.lastHotbarSlot = SurroundRewrite.mc.player.inventory.currentItem;
        this.startPos = EntityUtil.getRoundedBlockPos((Entity)SurroundRewrite.mc.player);
        this.CenterPos = EntityUtil.getCenter(SurroundRewrite.mc.player.posX, SurroundRewrite.mc.player.posY, SurroundRewrite.mc.player.posZ);
        if (!EntityUtil.isPlayerSafe((EntityPlayer)SurroundRewrite.mc.player)) {
            switch ((Center)this.center.getValue()) {
                case Instant: {
                    LuigiHack.positionManager.setPositionPacket(this.startPos.getX() + 0.5, (double)this.startPos.getY(), this.startPos.getZ() + 0.5, true, true, true);
                }
                case NCP: {
                    LuigiHack.movementManager.setMotion((this.CenterPos.x - SurroundRewrite.mc.player.posX) / 2.0, SurroundRewrite.mc.player.motionY, (this.CenterPos.z - SurroundRewrite.mc.player.posZ) / 2.0);
                    break;
                }
            }
        }
        this.retries.clear();
        this.retryTimer.reset();
    }
    
    private void doFeetPlace() {
        if (this.check()) {
            return;
        }
        if (!EntityUtil2.isSafe((Entity)SurroundRewrite.mc.player, 0, true)) {
            this.isSafe = 0;
            this.placeBlocks(SurroundRewrite.mc.player.getPositionVector(), EntityUtil2.getUnsafeBlockArray((Entity)SurroundRewrite.mc.player, 0, true), true, false, false);
        }
        else if (!EntityUtil2.isSafe((Entity)SurroundRewrite.mc.player, -1, false)) {
            this.isSafe = 1;
            this.placeBlocks(SurroundRewrite.mc.player.getPositionVector(), EntityUtil2.getUnsafeBlockArray((Entity)SurroundRewrite.mc.player, -1, false), false, false, true);
        }
        else {
            this.isSafe = 2;
        }
        this.processExtendingBlocks();
        if (this.didPlace) {
            this.timer.reset();
        }
        int n = 0;
        if (SurroundRewrite.mc.world.getBlockState(new BlockPos(SurroundRewrite.mc.player.getPositionVector())).getBlock() != Blocks.ENDER_CHEST) {
            n = 0;
        }
        int n2 = n;
        if (SurroundRewrite.mc.player.posY - (int)SurroundRewrite.mc.player.posY < 0.7) {
            n2 = 0;
        }
        if (!this.isSafe2((Entity)SurroundRewrite.mc.player, (n2 != 0) ? 1 : 0)) {
            this.placeBlocks(SurroundRewrite.mc.player.getPositionVector(), getUnsafeBlockArray((Entity)SurroundRewrite.mc.player, (int)((n2 != 0) ? 1 : 0)), (boolean)this.helpingBlocks.getValue(), false, false);
        }
        else if (!this.isSafe2((Entity)SurroundRewrite.mc.player, (n2 != 0) ? 0 : -1) && (boolean)this.antiPedo.getValue()) {
            this.placeBlocks(SurroundRewrite.mc.player.getPositionVector(), getUnsafeBlockArray((Entity)SurroundRewrite.mc.player, (n2 != 0) ? 0 : -1), false, false, true);
        }
    }
    
    public static Vec3d[] getUnsafeBlockArrayFromVec3d(final Vec3d vec3d, final int n) {
        final List<Vec3d> unsafeBlocksFromVec3d = getUnsafeBlocksFromVec3d(vec3d, n);
        return unsafeBlocksFromVec3d.toArray(new Vec3d[unsafeBlocksFromVec3d.size()]);
    }
    
    public enum Center
    {
        None, 
        NCP, 
        Instant;
    }
}
