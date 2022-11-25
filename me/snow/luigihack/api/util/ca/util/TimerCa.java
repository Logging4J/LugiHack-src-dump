//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util.ca.util;

public class TimerCa
{
    private /* synthetic */ long time;
    
    public boolean passedDs(final double n) {
        return this.passedMs((long)n * 100L);
    }
    
    public boolean passedMs(final long n) {
        return this.passedNS(this.convertToNS(n));
    }
    
    public TimerCa() {
        this.time = -1L;
    }
    
    public boolean passedDms(final double n) {
        return this.passedMs((long)n * 10L);
    }
    
    public TimerCa reset() {
        this.time = System.nanoTime();
        return this;
    }
    
    public long getPassedTimeMs() {
        return this.getMs(System.nanoTime() - this.time);
    }
    
    public boolean passedS(final double n) {
        return this.passedMs((long)n * 1000L);
    }
    
    public long convertToNS(final long n) {
        return n * 1000000L;
    }
    
    public void setMs(final long n) {
        this.time = System.nanoTime() - this.convertToNS(n);
    }
    
    public long getMs(final long n) {
        return n / 1000000L;
    }
    
    public boolean passedNS(final long n) {
        return System.nanoTime() - this.time >= n;
    }
}
