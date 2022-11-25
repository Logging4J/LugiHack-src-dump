//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.manager;

import me.snow.luigihack.impl.*;
import me.snow.luigihack.api.util.*;
import java.text.*;
import me.snow.luigihack.impl.modules.client.*;
import net.minecraft.client.network.*;
import java.util.*;

public class ServerManager extends Feature
{
    private /* synthetic */ String serverBrand;
    private final /* synthetic */ Timer timer;
    private final /* synthetic */ float[] tpsCounts;
    private /* synthetic */ long lastUpdate;
    private final /* synthetic */ DecimalFormat format;
    private /* synthetic */ float TPS;
    
    public float getTpsFactor() {
        return 20.0f / this.TPS;
    }
    
    public long serverRespondingTime() {
        return this.timer.getPassedTimeMs();
    }
    
    public String getServerBrand() {
        return this.serverBrand;
    }
    
    public boolean isServerNotResponding() {
        return this.timer.passedMs(Global.getInstance().respondTime.getValue());
    }
    
    public ServerManager() {
        this.tpsCounts = new float[10];
        this.format = new DecimalFormat("##.00#");
        this.timer = new Timer();
        this.TPS = 20.0f;
        this.lastUpdate = -1L;
        this.serverBrand = "";
    }
    
    public void update() {
        final long currentTimeMillis = System.currentTimeMillis();
        if (this.lastUpdate == -1L) {
            this.lastUpdate = currentTimeMillis;
            return;
        }
        float n = (currentTimeMillis - this.lastUpdate) / 20.0f;
        if (n == 0.0f) {
            n = 50.0f;
        }
        float n2;
        if ((n2 = 1000.0f / n) > 20.0f) {
            n2 = 20.0f;
        }
        System.arraycopy(this.tpsCounts, 0, this.tpsCounts, 1, this.tpsCounts.length - 1);
        this.tpsCounts[0] = n2;
        double n3 = 0.0;
        final float[] tpsCounts = this.tpsCounts;
        for (int length = tpsCounts.length, i = 0; i < length; ++i) {
            n3 += tpsCounts[i];
        }
        double number;
        if ((number = n3 / this.tpsCounts.length) > 20.0) {
            number = 20.0;
        }
        this.TPS = Float.parseFloat(this.format.format(number));
        this.lastUpdate = currentTimeMillis;
    }
    
    public int getPing() {
        if (fullNullCheck()) {
            return 0;
        }
        try {
            return Objects.requireNonNull(ServerManager.mc.getConnection()).getPlayerInfo(ServerManager.mc.getConnection().getGameProfile().getId()).getResponseTime();
        }
        catch (Exception ex) {
            return 0;
        }
    }
    
    public float getTPS() {
        return this.TPS;
    }
    
    public void setServerBrand(final String serverBrand) {
        this.serverBrand = serverBrand;
    }
    
    @Override
    public void reset() {
        Arrays.fill(this.tpsCounts, 20.0f);
        this.TPS = 20.0f;
    }
    
    public void onPacketReceived() {
        this.timer.reset();
    }
}
