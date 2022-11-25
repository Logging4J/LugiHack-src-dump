//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.manager;

import me.snow.luigihack.impl.*;
import java.util.concurrent.atomic.*;
import me.snow.luigihack.impl.modules.client.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.player.*;
import java.util.*;
import me.snow.luigihack.impl.modules.combat.autocrystal.*;
import java.util.concurrent.*;

public class SafetyManager extends Feature implements Runnable
{
    private final /* synthetic */ AtomicBoolean SAFE;
    private final /* synthetic */ Timer syncTimer;
    
    public SafetyManager() {
        this.syncTimer = new Timer();
        this.SAFE = new AtomicBoolean(false);
    }
    
    public String getSafetyString() {
        if (this.SAFE.get()) {
            return "§aSecure";
        }
        return "§cUnsafe";
    }
    
    public boolean isSafe() {
        return this.SAFE.get();
    }
    
    public void doSafetyCheck() {
        if (!Feature.fullNullCheck()) {
            boolean newValue = true;
            final EntityPlayer entityPlayer = Global.getInstance().safety.getValue() ? EntityUtil.getClosestEnemy(18.0) : null;
            if (Global.getInstance().safety.getValue() && entityPlayer == null) {
                this.SAFE.set(true);
                return;
            }
            for (final Entity entity : new ArrayList<Entity>(SafetyManager.mc.world.loadedEntityList)) {
                if (entity instanceof EntityEnderCrystal && DamageUtil.calculateDamage(entity, (Entity)SafetyManager.mc.player) > 4.0) {
                    if (entityPlayer != null && entityPlayer.getDistanceSq(entity) >= 40.0) {
                        continue;
                    }
                    newValue = false;
                    break;
                }
            }
            if (newValue) {
                for (final BlockPos blockPos : BlockUtil.possiblePlacePositions(4.0f, false, Global.getInstance().oneDot15.getValue())) {
                    if (DamageUtil.calculateDamage(blockPos, (Entity)SafetyManager.mc.player) > 4.0) {
                        if (entityPlayer != null && entityPlayer.getDistanceSq(blockPos) >= 40.0) {
                            continue;
                        }
                        newValue = false;
                        break;
                    }
                }
            }
            this.SAFE.set(newValue);
        }
    }
    
    @Override
    public void run() {
        if (AutoCrystal.getInstance().isOff() || AutoCrystal.getInstance().threadMode.getValue() == AutoCrystal.ThreadMode.NONE) {
            this.doSafetyCheck();
        }
    }
    
    public void onUpdate() {
        this.run();
    }
    
    public ScheduledExecutorService getService() {
        final ScheduledExecutorService singleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        singleThreadScheduledExecutor.scheduleAtFixedRate(this, 0L, Global.getInstance().safetyCheck.getValue(), TimeUnit.MILLISECONDS);
        return singleThreadScheduledExecutor;
    }
}
