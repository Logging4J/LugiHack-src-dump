//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.client;

import me.snow.luigihack.impl.modules.*;
import net.minecraftforge.fml.common.gameevent.*;
import me.snow.luigihack.api.util.skid.oyvey.*;
import net.minecraftforge.fml.common.eventhandler.*;
import com.mojang.realmsclient.gui.*;

public class Safety extends Module
{
    /* synthetic */ SafetyMode safety;
    
    public Safety() {
        super("Safety", "safeeeeeee", Category.CLIENT, true, false, false);
    }
    
    @SubscribeEvent
    public void onUpdate(final TickEvent.ClientTickEvent clientTickEvent) {
        if (NullUtils.nullCheck()) {
            return;
        }
        if (Safety.mc.world.getBlockState(Safety.mc.player.getPosition()).getMaterial().isSolid()) {
            this.safety = SafetyMode.Safe;
            return;
        }
        if (HoleUtilSafety.isHole(Safety.mc.player.getPosition())) {
            this.safety = SafetyMode.Safe;
            return;
        }
        this.safety = SafetyMode.Unsafe;
    }
    
    @Override
    public String getDisplayInfo() {
        return (this.safety != null) ? this.safety.toString() : "";
    }
    
    enum SafetyMode
    {
        Unsafe(ChatFormatting.RED), 
        Safe(ChatFormatting.GREEN);
        
        /* synthetic */ ChatFormatting color;
        
        private SafetyMode(final ChatFormatting color) {
            this.color = color;
        }
        
        @Override
        public String toString() {
            return String.valueOf(new StringBuilder().append(this.color.toString()).append(super.toString()));
        }
    }
}
