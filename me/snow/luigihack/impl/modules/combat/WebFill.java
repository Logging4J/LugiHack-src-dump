//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.combat;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.entity.player.*;
import net.minecraft.block.*;
import net.minecraft.entity.*;
import me.snow.luigihack.impl.command.*;
import me.snow.luigihack.impl.modules.player.*;
import me.snow.luigihack.*;
import net.minecraft.util.math.*;
import java.util.*;
import me.snow.luigihack.api.util.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.util.*;

public class WebFill extends Module
{
    private /* synthetic */ boolean isSneaking;
    private /* synthetic */ int lastHotbarSlot;
    private final /* synthetic */ Setting<Boolean> raytrace;
    private final /* synthetic */ Setting<InventoryUtil.Switch> switchMode;
    private final /* synthetic */ Setting<Integer> blocksPerPlace;
    private /* synthetic */ boolean didPlace;
    private /* synthetic */ int placements;
    private final /* synthetic */ Setting<Integer> eventMode;
    public /* synthetic */ EntityPlayer target;
    private final /* synthetic */ Setting<Boolean> ylower;
    private final /* synthetic */ Setting<Double> range;
    private /* synthetic */ boolean switchedItem;
    private final /* synthetic */ Setting<Boolean> upperBody;
    private final /* synthetic */ Setting<Integer> delay;
    private final /* synthetic */ Timer timer;
    private final /* synthetic */ Setting<Boolean> info;
    private final /* synthetic */ Setting<Boolean> packet;
    private final /* synthetic */ Setting<Double> targetRange;
    private final /* synthetic */ Setting<Boolean> rotate;
    private final /* synthetic */ Setting<Boolean> lowerbody;
    private final /* synthetic */ Setting<Boolean> freecam;
    private /* synthetic */ boolean smartRotate;
    private final /* synthetic */ Setting<Double> speed;
    private final /* synthetic */ Setting<Boolean> disable;
    public static /* synthetic */ boolean isPlacing;
    private /* synthetic */ BlockPos startPos;
    private final /* synthetic */ Setting<Boolean> antiSelf;
    private final /* synthetic */ Setting<TargetMode> targetMode;
    
    @Override
    public void onDisable() {
        WebFill.isPlacing = false;
        this.isSneaking = EntityUtil.stopSneaking(this.isSneaking);
        this.switchItem(true);
    }
    
    private boolean switchItem(final boolean b) {
        final boolean[] switchItem = InventoryUtil.switchItem(b, this.lastHotbarSlot, this.switchedItem, (InventoryUtil.Switch)this.switchMode.getValue(), (Class)BlockWeb.class);
        this.switchedItem = switchItem[0];
        return switchItem[1];
    }
    
    @Override
    public void onTick() {
        if ((int)this.eventMode.getValue() == 3) {
            this.smartRotate = false;
            this.doTrap();
        }
    }
    
    @Override
    public String getDisplayInfo() {
        if ((boolean)this.info.getValue() && this.target != null) {
            return this.target.getName();
        }
        return null;
    }
    
    private boolean check() {
        WebFill.isPlacing = false;
        this.didPlace = false;
        this.placements = 0;
        final int hotbarBlock = InventoryUtil.findHotbarBlock((Class)BlockWeb.class);
        if (this.isOff()) {
            return true;
        }
        if ((boolean)this.disable.getValue() && !this.startPos.equals((Object)EntityUtil.getRoundedBlockPos((Entity)WebFill.mc.player))) {
            this.disable();
            return true;
        }
        if (hotbarBlock == -1) {
            if (this.switchMode.getValue() != InventoryUtil.Switch.NONE) {
                if (this.info.getValue()) {
                    Command.sendMessage(String.valueOf(new StringBuilder().append("<").append(this.getDisplayName()).append("> §cYou are out of Webs.")));
                }
                this.disable();
            }
            return true;
        }
        if (WebFill.mc.player.inventory.currentItem != this.lastHotbarSlot && WebFill.mc.player.inventory.currentItem != hotbarBlock) {
            this.lastHotbarSlot = WebFill.mc.player.inventory.currentItem;
        }
        this.switchItem(true);
        this.isSneaking = EntityUtil.stopSneaking(this.isSneaking);
        this.target = this.getTarget((double)this.targetRange.getValue(), this.targetMode.getValue() == TargetMode.UNTRAPPED);
        return this.target == null || (((Freecam)LuigiHack.moduleManager.getModuleByClass((Class)Freecam.class)).isEnabled() && !(boolean)this.freecam.getValue()) || !this.timer.passedMs((long)(int)this.delay.getValue()) || (this.switchMode.getValue() == InventoryUtil.Switch.NONE && WebFill.mc.player.inventory.currentItem != InventoryUtil.findHotbarBlock((Class)BlockWeb.class));
    }
    
    static {
        WebFill.isPlacing = false;
    }
    
    @Override
    public void onUpdate() {
        if ((int)this.eventMode.getValue() == 1) {
            this.smartRotate = false;
            this.doTrap();
        }
    }
    
    private EntityPlayer getTarget(final double a, final boolean b) {
        EntityPlayer entityPlayer = null;
        double n = Math.pow(a, 2.0) + 1.0;
        for (final EntityPlayer entityPlayer2 : WebFill.mc.world.playerEntities) {
            if (!EntityUtil.isntValid((Entity)entityPlayer2, a) && (!b || !entityPlayer2.isInWeb) && (!EntityUtil.getRoundedBlockPos((Entity)WebFill.mc.player).equals((Object)EntityUtil.getRoundedBlockPos((Entity)entityPlayer2)) || !(boolean)this.antiSelf.getValue())) {
                if (LuigiHack.speedManager.getPlayerSpeed(entityPlayer2) > (double)this.speed.getValue()) {
                    continue;
                }
                if (entityPlayer == null) {
                    entityPlayer = entityPlayer2;
                    n = WebFill.mc.player.getDistanceSq((Entity)entityPlayer2);
                }
                else {
                    if (WebFill.mc.player.getDistanceSq((Entity)entityPlayer2) >= n) {
                        continue;
                    }
                    entityPlayer = entityPlayer2;
                    n = WebFill.mc.player.getDistanceSq((Entity)entityPlayer2);
                }
            }
        }
        return entityPlayer;
    }
    
    private List<Vec3d> getPlacements() {
        final ArrayList<Vec3d> list = new ArrayList<Vec3d>();
        final Vec3d getPositionVector = this.target.getPositionVector();
        if (this.ylower.getValue()) {
            list.add(getPositionVector.add(0.0, -1.0, 0.0));
        }
        if (this.lowerbody.getValue()) {
            list.add(getPositionVector);
        }
        if (this.upperBody.getValue()) {
            list.add(getPositionVector.add(0.0, 1.0, 0.0));
        }
        return list;
    }
    
    public WebFill() {
        super("WebFill", "Traps other players in webs", Category.COMBAT, true, false, false);
        this.delay = (Setting<Integer>)this.register(new Setting("Delay/Place", (Object)50, (Object)0, (Object)250));
        this.blocksPerPlace = (Setting<Integer>)this.register(new Setting("Block/Place", (Object)8, (Object)1, (Object)30));
        this.targetRange = (Setting<Double>)this.register(new Setting("TargetRange", (Object)10.0, (Object)0.0, (Object)20.0));
        this.range = (Setting<Double>)this.register(new Setting("PlaceRange", (Object)6.0, (Object)0.0, (Object)10.0));
        this.targetMode = (Setting<TargetMode>)this.register(new Setting("Target", (Object)TargetMode.CLOSEST));
        this.switchMode = (Setting<InventoryUtil.Switch>)this.register(new Setting("Switch", (Object)InventoryUtil.Switch.NORMAL));
        this.rotate = (Setting<Boolean>)this.register(new Setting("Rotate", (Object)true));
        this.raytrace = (Setting<Boolean>)this.register(new Setting("Raytrace", (Object)false));
        this.speed = (Setting<Double>)this.register(new Setting("Speed", (Object)30.0, (Object)0.0, (Object)30.0));
        this.upperBody = (Setting<Boolean>)this.register(new Setting("Upper", (Object)false));
        this.lowerbody = (Setting<Boolean>)this.register(new Setting("Lower", (Object)true));
        this.ylower = (Setting<Boolean>)this.register(new Setting("Y-1", (Object)false));
        this.antiSelf = (Setting<Boolean>)this.register(new Setting("AntiSelf", (Object)false));
        this.eventMode = (Setting<Integer>)this.register(new Setting("Updates", (Object)3, (Object)1, (Object)3));
        this.freecam = (Setting<Boolean>)this.register(new Setting("Freecam", (Object)false));
        this.info = (Setting<Boolean>)this.register(new Setting("Info", (Object)false));
        this.disable = (Setting<Boolean>)this.register(new Setting("TSelfMove", (Object)false));
        this.packet = (Setting<Boolean>)this.register(new Setting("Packet", (Object)false));
        this.timer = new Timer();
        this.didPlace = false;
        this.placements = 0;
        this.smartRotate = false;
        this.startPos = null;
    }
    
    private void placeList(final List<Vec3d> list) {
        list.sort((vec3d, vec3d3) -> Double.compare(WebFill.mc.player.getDistanceSq(vec3d3.x, vec3d3.y, vec3d3.z), WebFill.mc.player.getDistanceSq(vec3d.x, vec3d.y, vec3d.z)));
        list.sort(Comparator.comparingDouble(vec3d2 -> vec3d2.y));
        for (final Vec3d vec3d4 : list) {
            final BlockPos blockPos = new BlockPos(vec3d4);
            final int positionPlaceable = BlockUtil.isPositionPlaceable(blockPos, (boolean)this.raytrace.getValue());
            if (positionPlaceable == 3 || positionPlaceable == 1) {
                if ((boolean)this.antiSelf.getValue() && MathUtil.areVec3dsAligned(WebFill.mc.player.getPositionVector(), vec3d4)) {
                    continue;
                }
                this.placeBlock(blockPos);
            }
        }
    }
    
    private void doTrap() {
        if (this.check()) {
            return;
        }
        this.doWebTrap();
        if (this.didPlace) {
            this.timer.reset();
        }
    }
    
    @SubscribeEvent
    public void onUpdateWalkingPlayer(final UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (updateWalkingPlayerEvent.getStage() == 0 && (int)this.eventMode.getValue() == 2) {
            this.smartRotate = ((boolean)this.rotate.getValue() && (int)this.blocksPerPlace.getValue() == 1);
            this.doTrap();
        }
    }
    
    private void placeBlock(final BlockPos blockPos) {
        if (this.placements < (int)this.blocksPerPlace.getValue() && WebFill.mc.player.getDistanceSq(blockPos) <= MathUtil.square((double)this.range.getValue()) && this.switchItem(false)) {
            WebFill.isPlacing = true;
            this.isSneaking = (this.smartRotate ? BlockUtil.placeBlockSmartRotate(blockPos, EnumHand.MAIN_HAND, true, (boolean)this.packet.getValue(), this.isSneaking) : BlockUtil.placeBlock(blockPos, EnumHand.MAIN_HAND, (boolean)this.rotate.getValue(), (boolean)this.packet.getValue(), this.isSneaking));
            this.didPlace = true;
            ++this.placements;
        }
    }
    
    @Override
    public void onEnable() {
        if (fullNullCheck()) {
            return;
        }
        this.startPos = EntityUtil.getRoundedBlockPos((Entity)WebFill.mc.player);
        this.lastHotbarSlot = WebFill.mc.player.inventory.currentItem;
    }
    
    private void doWebTrap() {
        this.placeList(this.getPlacements());
    }
    
    public enum TargetMode
    {
        UNTRAPPED, 
        CLOSEST;
    }
}
