//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.combat;

import net.minecraft.entity.item.*;

final class Threads extends Thread
{
    /* synthetic */ EntityEnderCrystal bestCrystal;
    
    public Threads() {
    }
    
    @Override
    public void run() {
        this.bestCrystal = CrystalAura.INSTANCE.getBestCrystal();
        CrystalAura.INSTANCE.staticEnderCrystal = this.bestCrystal;
    }
}
