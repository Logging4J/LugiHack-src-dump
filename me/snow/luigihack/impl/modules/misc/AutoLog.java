//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.misc;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.*;
import net.minecraft.util.text.*;
import net.minecraft.network.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraft.network.play.server.*;
import net.minecraft.init.*;
import me.snow.luigihack.api.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class AutoLog extends Module
{
    private final /* synthetic */ Setting<Boolean> bed;
    private final /* synthetic */ Setting<Boolean> logout;
    private static /* synthetic */ AutoLog INSTANCE;
    private final /* synthetic */ Setting<Float> health;
    private final /* synthetic */ Setting<Float> range;
    
    static {
        AutoLog.INSTANCE = new AutoLog();
    }
    
    @Override
    public void onLogin() {
        if (this.isEnabled()) {
            this.disable();
            this.enable();
        }
    }
    
    public static AutoLog getInstance() {
        if (AutoLog.INSTANCE == null) {
            AutoLog.INSTANCE = new AutoLog();
        }
        return AutoLog.INSTANCE;
    }
    
    public AutoLog() {
        super("AutoLog", "Logs when in danger.", Category.MISC, false, false, false);
        this.health = (Setting<Float>)this.register(new Setting("Health", (Object)16.0f, (Object)0.1f, (Object)36.0f));
        this.bed = (Setting<Boolean>)this.register(new Setting("Beds", (Object)true));
        this.range = (Setting<Float>)this.register(new Setting("BedRange", (Object)6.0f, (Object)0.1f, (Object)36.0f, p0 -> (boolean)this.bed.getValue()));
        this.logout = (Setting<Boolean>)this.register(new Setting("LogoutOff", (Object)true));
        this.setInstance();
    }
    
    private void setInstance() {
        AutoLog.INSTANCE = this;
    }
    
    @Override
    public void onTick() {
        if (!nullCheck() && AutoLog.mc.player.getHealth() <= (float)this.health.getValue()) {
            LuigiHack.moduleManager.disableModule("AutoReconnect");
            AutoLog.mc.player.connection.sendPacket((Packet)new SPacketDisconnect((ITextComponent)new TextComponentString("AutoLogged")));
            if (this.logout.getValue()) {
                this.disable();
            }
        }
    }
    
    @SubscribeEvent
    public void onReceivePacket(final PacketEvent.Receive receive) {
        final SPacketBlockChange sPacketBlockChange;
        if (receive.getPacket() instanceof SPacketBlockChange && (boolean)this.bed.getValue() && (sPacketBlockChange = (SPacketBlockChange)receive.getPacket()).getBlockState().getBlock() == Blocks.BED && AutoLog.mc.player.getDistanceSqToCenter(sPacketBlockChange.getBlockPosition()) <= MathUtil.square((float)this.range.getValue())) {
            LuigiHack.moduleManager.disableModule("AutoReconnect");
            AutoLog.mc.player.connection.sendPacket((Packet)new SPacketDisconnect((ITextComponent)new TextComponentString("AutoLogged")));
            if (this.logout.getValue()) {
                this.disable();
            }
        }
    }
}
