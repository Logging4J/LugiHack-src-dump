//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.movement;

import me.snow.luigihack.impl.modules.*;
import net.minecraft.block.*;
import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.impl.modules.player.*;
import me.snow.luigihack.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import java.util.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.entity.*;

public class FastFall extends Module
{
    /* synthetic */ List<Block> incelBlocks;
    public /* synthetic */ Setting<Double> speed;
    public /* synthetic */ Setting<Double> height;
    public /* synthetic */ Setting<Boolean> noLag;
    
    boolean shouldReturn() {
        return FastFall.mc.player.isElytraFlying() || isClipping() || EntityUtil.isInLiquid() || FastFall.mc.player.isOnLadder() || FastFall.mc.player.capabilities.isFlying || FastFall.mc.player.motionY > 0.0 || FastFall.mc.gameSettings.keyBindJump.isKeyDown() || FastFall.mc.player.isEntityInsideOpaqueBlock() || FastFall.mc.player.noClip || !FastFall.mc.player.onGround || Freecam.getInstance().isEnabled() || ((PacketFly)LuigiHack.moduleManager.getModuleByClass((Class)PacketFly.class)).isEnabled() || ((LongJump)LuigiHack.moduleManager.getModuleByClass((Class)LongJump.class)).isEnabled() || ((Flight)LuigiHack.moduleManager.getModuleByClass((Class)Flight.class)).isEnabled() || ((ElytraFlight)LuigiHack.moduleManager.getModuleByClass((Class)ElytraFlight.class)).isEnabled();
    }
    
    public void onUpdate() {
        if (fullNullCheck() || this.shouldReturn()) {
            return;
        }
        if ((boolean)this.noLag.getValue() && LuigiHack.packetManager.caughtPlayerPosLook()) {
            return;
        }
        final RayTraceResult rayTraceBlocks = FastFall.mc.world.rayTraceBlocks(FastFall.mc.player.getPositionVector(), new Vec3d(FastFall.mc.player.posX, FastFall.mc.player.posY - (double)this.height.getValue(), FastFall.mc.player.posZ), false, false, false);
        if (rayTraceBlocks != null && rayTraceBlocks.typeOfHit == RayTraceResult.Type.BLOCK && FastFall.mc.world.getBlockState(new BlockPos(FastFall.mc.player.posX, FastFall.mc.player.posY - 0.1, FastFall.mc.player.posZ)).getBlock() != this.incelBlocks) {
            FastFall.mc.player.motionY = -(double)this.speed.getValue();
        }
    }
    
    public FastFall() {
        super("FastFall", "Fast fall", Module.Category.MOVEMENT, true, false, false);
        this.speed = (Setting<Double>)this.register(new Setting("Speed", (Object)3.0, (Object)0.1, (Object)10.0));
        this.height = (Setting<Double>)this.register(new Setting("Height", (Object)5.0, (Object)0.1, (Object)20.0));
        this.noLag = (Setting<Boolean>)this.register(new Setting("NoLag", (Object)true));
        this.incelBlocks = Arrays.asList(Blocks.BED, Blocks.SLIME_BLOCK);
    }
    
    public static boolean isClipping() {
        return !PlayerUtil.mc.world.getCollisionBoxes((Entity)PlayerUtil.mc.player, PlayerUtil.mc.player.getEntityBoundingBox()).isEmpty();
    }
}
