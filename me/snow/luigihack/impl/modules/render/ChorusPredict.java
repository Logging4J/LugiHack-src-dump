//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.render;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.util.math.*;
import java.awt.*;
import me.snow.luigihack.api.util.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraft.network.play.server.*;
import net.minecraft.init.*;
import com.mojang.realmsclient.gui.*;
import me.snow.luigihack.impl.command.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class ChorusPredict extends Module
{
    private final /* synthetic */ Setting<Integer> alpha;
    private final /* synthetic */ Timer renderTimer;
    private final /* synthetic */ Setting<Integer> green;
    private final /* synthetic */ Setting<Integer> red;
    private final /* synthetic */ Setting<Integer> blue;
    private final /* synthetic */ Setting<Integer> renderDelay;
    private final /* synthetic */ Setting<Boolean> debug;
    private /* synthetic */ BlockPos pos;
    private final /* synthetic */ Setting<Integer> outlineAlpha;
    
    public ChorusPredict() {
        super("ChorusView", "Predicts Chorus", Module.Category.RENDER, true, false, false);
        this.renderTimer = new Timer();
        this.debug = (Setting<Boolean>)this.register(new Setting("Debug", (Object)true));
        this.renderDelay = (Setting<Integer>)this.register(new Setting("RenderDelay", (Object)4000, (Object)0, (Object)4000));
        this.red = (Setting<Integer>)this.register(new Setting("Red", (Object)255, (Object)0, (Object)255));
        this.green = (Setting<Integer>)this.register(new Setting("Green", (Object)255, (Object)0, (Object)255));
        this.blue = (Setting<Integer>)this.register(new Setting("Blue", (Object)255, (Object)0, (Object)255));
        this.alpha = (Setting<Integer>)this.register(new Setting("Alpha", (Object)150, (Object)0, (Object)255));
        this.outlineAlpha = (Setting<Integer>)this.register(new Setting("OL-Alpha", (Object)200, (Object)0, (Object)255));
    }
    
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (this.pos != null) {
            if (this.renderTimer.passed((double)(int)this.renderDelay.getValue())) {
                this.pos = null;
                return;
            }
            RenderUtil.drawBoxESP(this.pos, new Color((int)this.red.getValue(), (int)this.green.getValue(), (int)this.blue.getValue(), (int)this.outlineAlpha.getValue()), 1.5f, true, true, (int)this.alpha.getValue());
        }
    }
    
    @SubscribeEvent
    public void onPacketReceive(final PacketEvent.Receive receive) {
        if (receive.getPacket() instanceof SPacketSoundEffect) {
            final SPacketSoundEffect sPacketSoundEffect = (SPacketSoundEffect)receive.getPacket();
            if (sPacketSoundEffect.getSound() == SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT || sPacketSoundEffect.getSound() == SoundEvents.ENTITY_ENDERMEN_TELEPORT) {
                this.renderTimer.reset2();
                this.pos = new BlockPos(sPacketSoundEffect.getX(), sPacketSoundEffect.getY(), sPacketSoundEffect.getZ());
                if (this.debug.getValue()) {
                    Command.sendMessage(String.valueOf(new StringBuilder().append("A player chorused to: ").append(ChatFormatting.AQUA).append("X: ").append(this.pos.getX()).append(", Y: ").append(this.pos.getY()).append(", Z: ").append(this.pos.getZ())));
                }
            }
        }
    }
}
