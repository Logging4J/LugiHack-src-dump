//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.combat;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraft.entity.player.*;
import java.util.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.snow.luigihack.api.util.*;
import me.snow.luigihack.*;
import net.minecraft.network.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.util.math.*;

public class AntiTrap extends Module
{
    private final /* synthetic */ Setting<InventoryUtil.Switch> switchMode;
    public /* synthetic */ Setting<Rotate> rotate;
    private /* synthetic */ boolean switchedItem;
    public /* synthetic */ Setting<Boolean> sortY;
    private final /* synthetic */ Vec3d[] surroundTargets;
    private /* synthetic */ int lastHotbarSlot;
    private final /* synthetic */ Timer timer;
    private /* synthetic */ boolean offhand;
    public static /* synthetic */ Set<BlockPos> placedPos;
    private final /* synthetic */ Setting<Integer> coolDown;
    
    private boolean switchItem(final boolean b) {
        if (this.offhand) {
            return true;
        }
        final boolean[] switchItemToItem = InventoryUtil.switchItemToItem(b, this.lastHotbarSlot, this.switchedItem, (InventoryUtil.Switch)this.switchMode.getValue(), Items.END_CRYSTAL);
        this.switchedItem = switchItemToItem[0];
        return switchItemToItem[1];
    }
    
    static {
        AntiTrap.placedPos = new HashSet<BlockPos>();
    }
    
    public void doAntiTrap() {
        this.offhand = (AntiTrap.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL);
        if (!this.offhand && InventoryUtil.findHotbarBlock((Class)ItemEndCrystal.class) == -1) {
            this.disable();
            return;
        }
        this.lastHotbarSlot = AntiTrap.mc.player.inventory.currentItem;
        final ArrayList<Object> c = new ArrayList<Object>();
        Collections.addAll(c, BlockUtil.convertVec3ds(AntiTrap.mc.player.getPositionVector(), this.surroundTargets));
        if (EntityUtil.getClosestEnemy(6.0) != null) {
            final EntityPlayer entityPlayer;
            c.sort((vec3d2, vec3d3) -> Double.compare(entityPlayer.getDistanceSq(vec3d3.x, vec3d3.y, vec3d3.z), entityPlayer.getDistanceSq(vec3d2.x, vec3d2.y, vec3d2.z)));
            if (this.sortY.getValue()) {
                c.sort(Comparator.comparingDouble(vec3d -> vec3d.y));
            }
        }
        final Iterator<Vec3d> iterator = c.iterator();
        while (iterator.hasNext()) {
            final BlockPos blockPos = new BlockPos((Vec3d)iterator.next());
            if (!BlockUtil.canPlaceCrystal(blockPos)) {
                continue;
            }
            this.placeCrystal(blockPos);
            this.disable();
            break;
        }
    }
    
    @SubscribeEvent
    public void onUpdateWalkingPlayer(final UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (!fullNullCheck() && updateWalkingPlayerEvent.getStage() == 0) {
            this.doAntiTrap();
        }
    }
    
    @Override
    public void onEnable() {
        if (fullNullCheck() || !this.timer.passedMs((long)(int)this.coolDown.getValue())) {
            this.disable();
            return;
        }
        this.lastHotbarSlot = AntiTrap.mc.player.inventory.currentItem;
    }
    
    @Override
    public void onDisable() {
        if (fullNullCheck()) {
            return;
        }
        this.switchItem(true);
    }
    
    private void placeCrystal(final BlockPos blockPos) {
        if (AntiTrap.mc.player.getHeldItemMainhand().getItem() != Items.END_CRYSTAL && !this.offhand && !this.switchItem(false)) {
            this.disable();
            return;
        }
        final RayTraceResult rayTraceBlocks = AntiTrap.mc.world.rayTraceBlocks(new Vec3d(AntiTrap.mc.player.posX, AntiTrap.mc.player.posY + AntiTrap.mc.player.getEyeHeight(), AntiTrap.mc.player.posZ), new Vec3d(blockPos.getX() + 0.5, blockPos.getY() - 0.5, blockPos.getZ() + 0.5));
        final EnumFacing enumFacing = (rayTraceBlocks == null || rayTraceBlocks.sideHit == null) ? EnumFacing.UP : rayTraceBlocks.sideHit;
        final float[] calcAngle = MathUtil.calcAngle(AntiTrap.mc.player.getPositionEyes(AntiTrap.mc.getRenderPartialTicks()), new Vec3d((double)(blockPos.getX() + 0.5f), (double)(blockPos.getY() - 0.5f), (double)(blockPos.getZ() + 0.5f)));
        switch ((Rotate)this.rotate.getValue()) {
            case NORMAL: {
                LuigiHack.rotationManager.setPlayerRotations(calcAngle[0], calcAngle[1]);
                break;
            }
            case PACKET: {
                AntiTrap.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(calcAngle[0], (float)MathHelper.normalizeAngle((int)calcAngle[1], 360), AntiTrap.mc.player.onGround));
                break;
            }
        }
        AntiTrap.placedPos.add(blockPos);
        AntiTrap.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos, enumFacing, this.offhand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
        AntiTrap.mc.player.swingArm(EnumHand.MAIN_HAND);
        this.timer.reset();
    }
    
    public AntiTrap() {
        super("AntiTrap", "Places a crystal to prevent you getting trapped.", Category.COMBAT, true, false, false);
        this.coolDown = (Setting<Integer>)this.register(new Setting("CoolDown", (Object)400, (Object)0, (Object)1000));
        this.switchMode = (Setting<InventoryUtil.Switch>)this.register(new Setting("Switch", (Object)InventoryUtil.Switch.NORMAL));
        this.surroundTargets = new Vec3d[] { new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, -1.0), new Vec3d(1.0, 0.0, -1.0), new Vec3d(1.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, -1.0), new Vec3d(-1.0, 0.0, 1.0), new Vec3d(1.0, 1.0, 0.0), new Vec3d(0.0, 1.0, 1.0), new Vec3d(-1.0, 1.0, 0.0), new Vec3d(0.0, 1.0, -1.0), new Vec3d(1.0, 1.0, -1.0), new Vec3d(1.0, 1.0, 1.0), new Vec3d(-1.0, 1.0, -1.0), new Vec3d(-1.0, 1.0, 1.0) };
        this.timer = new Timer();
        this.rotate = (Setting<Rotate>)this.register(new Setting("Rotate", (Object)Rotate.NORMAL));
        this.sortY = (Setting<Boolean>)this.register(new Setting("SortY", (Object)true));
        this.lastHotbarSlot = -1;
    }
    
    public enum Rotate
    {
        PACKET, 
        NORMAL, 
        NONE;
    }
}
