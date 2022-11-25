//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.movement;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;
import net.minecraft.entity.item.*;
import me.snow.luigihack.api.util.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraft.world.chunk.*;
import net.minecraft.entity.passive.*;

public class EntitySpeed extends Module
{
    public /* synthetic */ Setting<Float> speed;
    private /* synthetic */ Setting<Boolean> wobble;
    public static /* synthetic */ EntitySpeed INSTANCE;
    private /* synthetic */ Setting<Boolean> flight;
    private /* synthetic */ Setting<Boolean> antiStuck;
    
    public void setEntitySpeed(final Entity entity, final double motionX, final double motionZ) {
        entity.motionX = motionX;
        entity.motionZ = motionZ;
    }
    
    public void moveForward(final Entity entity, final double n) {
        if (entity != null) {
            final MovementInput movementInput = EntitySpeed.mc.player.movementInput;
            double n2 = movementInput.moveForward;
            double n3 = movementInput.moveStrafe;
            final boolean b = n2 != 0.0;
            final boolean b2 = n3 != 0.0;
            float rotationYaw = EntitySpeed.mc.player.rotationYaw;
            if (!b && !b2) {
                this.setEntitySpeed(entity, 0.0, 0.0);
            }
            else {
                if (n2 != 0.0) {
                    if (n3 > 0.0) {
                        rotationYaw += ((n2 > 0.0) ? -45 : 45);
                    }
                    else if (n3 < 0.0) {
                        rotationYaw += ((n2 > 0.0) ? 45 : -45);
                    }
                    n3 = 0.0;
                    n2 = ((n2 > 0.0) ? 1.0 : -1.0);
                }
                final double cos;
                double n4 = n2 * n * (cos = Math.cos(Math.toRadians(rotationYaw + 90.0f))) + n3 * n * Math.sin(Math.toRadians(rotationYaw + 90.0f));
                double n5;
                if (this.isBorderingChunk(entity, n4, n5 = n2 * n * Math.sin(Math.toRadians(rotationYaw + 90.0f)) - n3 * n * cos)) {
                    n5 = 0.0;
                    n4 = 0.0;
                }
                this.setEntitySpeed(entity, n4, n5);
            }
        }
    }
    
    public void steerBoat(final EntityBoat entityBoat) {
        int n = 0;
        if (entityBoat == null) {
            return;
        }
        final boolean isKeyDown = EntitySpeed.mc.gameSettings.keyBindForward.isKeyDown();
        final boolean isKeyDown2 = EntitySpeed.mc.gameSettings.keyBindLeft.isKeyDown();
        final boolean isKeyDown3 = EntitySpeed.mc.gameSettings.keyBindRight.isKeyDown();
        final boolean isKeyDown4 = EntitySpeed.mc.gameSettings.keyBindBack.isKeyDown();
        if (!isKeyDown || !isKeyDown4) {
            entityBoat.motionY = 0.0;
        }
        if (EntitySpeed.mc.gameSettings.keyBindJump.isKeyDown()) {
            entityBoat.motionY += (float)this.speed.getValue() / 2.0f;
        }
        if (!isKeyDown && !isKeyDown2 && !isKeyDown3 && !isKeyDown4) {
            return;
        }
        if (isKeyDown2 && isKeyDown3) {
            n = (isKeyDown ? 0 : (isKeyDown4 ? 180 : -1));
        }
        else if (isKeyDown && isKeyDown4) {
            n = (isKeyDown2 ? -90 : (isKeyDown3 ? 90 : -1));
        }
        else {
            if (!isKeyDown2) {
                n = (isKeyDown3 ? 90 : 0);
            }
            if (isKeyDown) {
                n /= 2;
            }
            else if (isKeyDown4) {
                n = 180 - n / 2;
            }
        }
        if (n == -1) {
            return;
        }
        final float n2 = EntitySpeed.mc.player.rotationYaw + n;
        entityBoat.motionX = EntityUtil.getRelativeX(n2) * (float)this.speed.getValue();
        entityBoat.motionZ = EntityUtil.getRelativeZ(n2) * (float)this.speed.getValue();
    }
    
    public void steerEntity(final Entity entity) {
        if (!(boolean)this.flight.getValue()) {
            entity.motionY = -0.4;
        }
        if (this.flight.getValue()) {
            if (EntitySpeed.mc.gameSettings.keyBindJump.isKeyDown()) {
                entity.motionY = (float)this.speed.getValue();
            }
            else if (EntitySpeed.mc.gameSettings.keyBindForward.isKeyDown() || EntitySpeed.mc.gameSettings.keyBindBack.isKeyDown()) {
                entity.motionY = (this.wobble.getValue() ? Math.sin(EntitySpeed.mc.player.ticksExisted) : 0.0);
            }
        }
        this.moveForward(entity, (float)this.speed.getValue() * 3.8);
        if (entity instanceof EntityHorse) {
            entity.rotationYaw = EntitySpeed.mc.player.rotationYaw;
        }
    }
    
    public void onRender3D(final Render3DEvent render3DEvent) {
        final EntityBoat boat = this.getBoat();
        if (boat == null) {
            return;
        }
        boat.rotationYaw = EntitySpeed.mc.player.rotationYaw;
        boat.updateInputs(false, false, false, false);
    }
    
    public EntitySpeed() {
        super("EntitySpeed", "Abuses client-sided movement to change the speed of rideable entities", Module.Category.MOVEMENT, false, false, false);
        this.speed = (Setting<Float>)this.register(new Setting("Speed", (Object)1.0f, (Object)0.0f, (Object)4.0f));
        this.antiStuck = (Setting<Boolean>)this.register(new Setting("AntiStuck", (Object)true));
        this.flight = (Setting<Boolean>)this.register(new Setting("Flight", (Object)true));
        this.wobble = (Setting<Boolean>)this.register(new Setting("Wobble", (Object)true));
        this.setInstance();
    }
    
    public static EntitySpeed getInstance() {
        if (EntitySpeed.INSTANCE == null) {
            EntitySpeed.INSTANCE = new EntitySpeed();
        }
        return EntitySpeed.INSTANCE;
    }
    
    public EntityBoat getBoat() {
        if (EntitySpeed.mc.player.getRidingEntity() != null && EntitySpeed.mc.player.getRidingEntity() instanceof EntityBoat) {
            return (EntityBoat)EntitySpeed.mc.player.getRidingEntity();
        }
        return null;
    }
    
    public static EntitySpeed getINSTANCE() {
        if (EntitySpeed.INSTANCE == null) {
            EntitySpeed.INSTANCE = new EntitySpeed();
        }
        return EntitySpeed.INSTANCE;
    }
    
    public void setInstance() {
        EntitySpeed.INSTANCE = this;
    }
    
    public boolean isBorderingChunk(final Entity entity, final double n, final double n2) {
        return (boolean)this.antiStuck.getValue() && EntitySpeed.mc.world.getChunk((int)(entity.posX + n) >> 4, (int)(entity.posZ + n2) >> 4) instanceof EmptyChunk;
    }
    
    static {
        EntitySpeed.INSTANCE = new EntitySpeed();
    }
    
    public void onUpdate() {
        if (EntitySpeed.mc.world != null && EntitySpeed.mc.player.getRidingEntity() != null) {
            final Entity getRidingEntity = EntitySpeed.mc.player.getRidingEntity();
            if (getRidingEntity instanceof EntityPig || getRidingEntity instanceof AbstractHorse) {
                this.steerEntity(getRidingEntity);
            }
            else if (getRidingEntity instanceof EntityBoat) {
                this.steerBoat(this.getBoat());
            }
        }
    }
}
