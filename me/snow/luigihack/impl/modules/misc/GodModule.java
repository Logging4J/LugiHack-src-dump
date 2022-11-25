//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.misc;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.init.*;
import net.minecraft.entity.*;
import java.util.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.item.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraft.item.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.network.play.server.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.network.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;

public class GodModule extends Module
{
    public /* synthetic */ Setting<Boolean> render;
    private /* synthetic */ float yaw;
    public /* synthetic */ Setting<Boolean> rotate;
    private /* synthetic */ int highestID;
    public /* synthetic */ Setting<Boolean> entitycheck;
    private /* synthetic */ int rotationPacketsSpoofed;
    public /* synthetic */ Setting<Boolean> oneDot15;
    public /* synthetic */ Setting<Integer> attacks;
    public /* synthetic */ Setting<Boolean> checkPos;
    private /* synthetic */ float pitch;
    public /* synthetic */ Setting<Integer> delay;
    public /* synthetic */ Setting<Integer> rotations;
    public /* synthetic */ Setting<Boolean> antiIllegal;
    private /* synthetic */ boolean rotating;
    
    @Override
    public void onLogout() {
        this.resetFields();
    }
    
    @Override
    public void onToggle() {
        this.resetFields();
        if (GodModule.mc.world != null) {
            this.updateEntityID();
        }
    }
    
    private boolean checkItem(final ItemStack itemStack) {
        return itemStack.getItem() instanceof ItemBow || itemStack.getItem() instanceof ItemExpBottle || itemStack.getItem() == Items.STRING;
    }
    
    private void resetFields() {
        this.rotating = false;
        this.highestID = -1000000;
    }
    
    public void updateEntityID() {
        for (final Entity entity : GodModule.mc.world.loadedEntityList) {
            if (entity.getEntityId() <= this.highestID) {
                continue;
            }
            this.highestID = entity.getEntityId();
        }
    }
    
    private boolean checkPlayers() {
        if (this.antiIllegal.getValue()) {
            for (final EntityPlayer entityPlayer : GodModule.mc.world.playerEntities) {
                if (!this.checkItem(entityPlayer.getHeldItemMainhand()) && !this.checkItem(entityPlayer.getHeldItemOffhand())) {
                    continue;
                }
                return false;
            }
        }
        return true;
    }
    
    public void rotateTo(final BlockPos blockPos) {
        final float[] calcAngle = MathUtil.calcAngle(GodModule.mc.player.getPositionEyes(GodModule.mc.getRenderPartialTicks()), new Vec3d((Vec3i)blockPos));
        this.yaw = calcAngle[0];
        this.pitch = calcAngle[1];
        this.rotating = true;
    }
    
    @Override
    public void onUpdate() {
        if (this.render.getValue()) {
            for (final Entity entity : GodModule.mc.world.loadedEntityList) {
                if (!(entity instanceof EntityEnderCrystal)) {
                    continue;
                }
                entity.setCustomNameTag(String.valueOf(entity.entityId));
                entity.setAlwaysRenderNameTag(true);
            }
        }
    }
    
    public GodModule() {
        super("GodModule", "Wow", Category.MISC, true, false, false);
        this.rotations = (Setting<Integer>)this.register(new Setting("Spoofs", (Object)1, (Object)1, (Object)20));
        this.rotate = (Setting<Boolean>)this.register(new Setting("Rotate", (Object)false));
        this.render = (Setting<Boolean>)this.register(new Setting("Render", (Object)false));
        this.antiIllegal = (Setting<Boolean>)this.register(new Setting("AntiIllegal", (Object)true));
        this.checkPos = (Setting<Boolean>)this.register(new Setting("CheckPos", (Object)true));
        this.oneDot15 = (Setting<Boolean>)this.register(new Setting("1.15", (Object)false));
        this.entitycheck = (Setting<Boolean>)this.register(new Setting("EntityCheck", (Object)false));
        this.attacks = (Setting<Integer>)this.register(new Setting("Attacks", (Object)1, (Object)1, (Object)10));
        this.delay = (Setting<Integer>)this.register(new Setting("Delay", (Object)0, (Object)0, (Object)50));
        this.yaw = 0.0f;
        this.pitch = 0.0f;
        this.highestID = -100000;
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onSendPacket(final PacketEvent.Send send) {
        if (send.getStage() == 0 && send.getPacket() instanceof CPacketPlayerTryUseItemOnBlock) {
            final CPacketPlayerTryUseItemOnBlock cPacketPlayerTryUseItemOnBlock = (CPacketPlayerTryUseItemOnBlock)send.getPacket();
            if (GodModule.mc.player.getHeldItem(cPacketPlayerTryUseItemOnBlock.hand).getItem() instanceof ItemEndCrystal) {
                if (((boolean)this.checkPos.getValue() && !BlockUtil.canPlaceCrystal(cPacketPlayerTryUseItemOnBlock.position, (boolean)this.entitycheck.getValue(), (boolean)this.oneDot15.getValue())) || this.checkPlayers()) {
                    return;
                }
                this.updateEntityID();
                for (int i = 1; i < (int)this.attacks.getValue(); ++i) {
                    this.attackID(cPacketPlayerTryUseItemOnBlock.position, this.highestID + i);
                }
            }
        }
        if (send.getStage() == 0 && this.rotating && (boolean)this.rotate.getValue() && send.getPacket() instanceof CPacketPlayer) {
            final CPacketPlayer cPacketPlayer = (CPacketPlayer)send.getPacket();
            cPacketPlayer.yaw = this.yaw;
            cPacketPlayer.pitch = this.pitch;
            ++this.rotationPacketsSpoofed;
            if (this.rotationPacketsSpoofed >= (int)this.rotations.getValue()) {
                this.rotating = false;
                this.rotationPacketsSpoofed = 0;
            }
        }
    }
    
    private void attackID(final BlockPos blockPos, final int n) {
        final Entity getEntityByID = GodModule.mc.world.getEntityByID(n);
        if (getEntityByID == null || getEntityByID instanceof EntityEnderCrystal) {
            new AttackThread(n, blockPos, (int)this.delay.getValue(), this).start();
        }
    }
    
    private void checkID(final int highestID) {
        if (highestID > this.highestID) {
            this.highestID = highestID;
        }
    }
    
    @SubscribeEvent
    public void onPacketReceive(final PacketEvent.Receive receive) {
        if (receive.getPacket() instanceof SPacketSpawnObject) {
            this.checkID(((SPacketSpawnObject)receive.getPacket()).getEntityID());
        }
        else if (receive.getPacket() instanceof SPacketSpawnExperienceOrb) {
            this.checkID(((SPacketSpawnExperienceOrb)receive.getPacket()).getEntityID());
        }
        else if (receive.getPacket() instanceof SPacketSpawnPlayer) {
            this.checkID(((SPacketSpawnPlayer)receive.getPacket()).getEntityID());
        }
        else if (receive.getPacket() instanceof SPacketSpawnGlobalEntity) {
            this.checkID(((SPacketSpawnGlobalEntity)receive.getPacket()).getEntityId());
        }
        else if (receive.getPacket() instanceof SPacketSpawnPainting) {
            this.checkID(((SPacketSpawnPainting)receive.getPacket()).getEntityID());
        }
        else if (receive.getPacket() instanceof SPacketSpawnMob) {
            this.checkID(((SPacketSpawnMob)receive.getPacket()).getEntityID());
        }
    }
    
    public static class AttackThread extends Thread
    {
        private final /* synthetic */ int delay;
        private final /* synthetic */ GodModule godModule;
        private final /* synthetic */ BlockPos pos;
        private final /* synthetic */ int id;
        
        public AttackThread(final int id, final BlockPos pos, final int delay) {
            this.id = id;
            this.pos = pos;
            this.delay = delay;
        }
        
        @Override
        public void run() {
            try {
                this.wait(this.delay);
                final CPacketUseEntity cPacketUseEntity = new CPacketUseEntity();
                cPacketUseEntity.entityId = this.id;
                cPacketUseEntity.action = CPacketUseEntity.Action.ATTACK;
                this.godModule.rotateTo(this.pos.up());
                Util.mc.player.connection.sendPacket((Packet)cPacketUseEntity);
                Util.mc.player.connection.sendPacket((Packet)new CPacketAnimation(EnumHand.MAIN_HAND));
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
