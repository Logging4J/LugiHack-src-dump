//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.manager;

import me.snow.luigihack.impl.*;
import net.minecraft.util.math.*;
import me.snow.luigihack.api.util.*;
import me.snow.luigihack.impl.modules.client.*;

public class NoStopManager extends Feature
{
    private /* synthetic */ boolean stopped;
    private /* synthetic */ boolean running;
    private /* synthetic */ BlockPos lastPos;
    private /* synthetic */ String prefix;
    private /* synthetic */ boolean sentMessage;
    private final /* synthetic */ Timer timer;
    private /* synthetic */ BlockPos pos;
    
    public NoStopManager() {
        this.timer = new Timer();
    }
    
    public void start(final int n, final int n2, final int n3) {
        this.pos = new BlockPos(n, n2, n3);
        this.sentMessage = false;
        this.running = true;
    }
    
    public void setPrefix(final String prefix) {
        this.prefix = prefix;
    }
    
    public void stop() {
        if (this.running) {
            if (NoStopManager.mc.player != null) {
                NoStopManager.mc.player.sendChatMessage(String.valueOf(new StringBuilder().append(this.prefix).append("stop")));
            }
            this.running = false;
        }
    }
    
    public void sendMessage() {
        NoStopManager.mc.player.sendChatMessage(String.valueOf(new StringBuilder().append(this.prefix).append("goto ").append(this.pos.getX()).append(" ").append(this.pos.getY()).append(" ").append(this.pos.getZ())));
    }
    
    public void onUpdateWalkingPlayer() {
        if (fullNullCheck()) {
            this.stop();
            return;
        }
        if (this.running && this.pos != null) {
            final BlockPos getPosition = NoStopManager.mc.player.getPosition();
            if (getPosition.equals((Object)this.pos)) {
                BlockUtil.debugPos("<Baritone> Arrived at Position: ", this.pos);
                this.running = false;
                return;
            }
            if (getPosition.equals((Object)this.lastPos)) {
                if (this.stopped && this.timer.passedS(Global.getInstance().baritoneTimeOut.getValue())) {
                    this.sendMessage();
                    this.stopped = false;
                    return;
                }
                if (!this.stopped) {
                    this.stopped = true;
                    this.timer.reset();
                }
            }
            else {
                this.lastPos = getPosition;
                this.stopped = false;
            }
            if (!this.sentMessage) {
                this.sendMessage();
                this.sentMessage = true;
            }
        }
    }
}
