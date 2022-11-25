//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util;

public class Timer
{
    private /* synthetic */ long current;
    /* synthetic */ long startTime;
    private /* synthetic */ long lastMS;
    /* synthetic */ long delay;
    private /* synthetic */ long time;
    /* synthetic */ boolean paused;
    
    public boolean hasReached(final long n) {
        return System.currentTimeMillis() - this.current >= n;
    }
    
    public static void resetTimer() {
        Util.mc.timer.tickLength = 50.0f;
    }
    
    public long getMs(final long n) {
        return n / 1000000L;
    }
    
    public void resetDelay() {
        this.startTime = System.currentTimeMillis();
    }
    
    public Timer() {
        this.time = -1L;
        this.lastMS = 0L;
        this.startTime = System.currentTimeMillis();
        this.delay = 0L;
        this.paused = false;
        this.current = -1L;
    }
    
    public boolean passed(final double n) {
        return System.currentTimeMillis() - this.current >= n;
    }
    
    public boolean passedNS(final long n) {
        return System.nanoTime() - this.time >= n;
    }
    
    public boolean passedD(final double n) {
        return this.getMs(System.nanoTime() - this.time) >= (long)(n * 3.0);
    }
    
    public void reset2() {
        this.current = System.currentTimeMillis();
    }
    
    public boolean passedDs(final double n) {
        return this.passedMs((long)n * 100L);
    }
    
    public boolean isPassed() {
        return !this.paused && System.currentTimeMillis() - this.startTime >= this.delay;
    }
    
    public boolean sleep(final long n) {
        if (System.nanoTime() / 1000000L - n >= n) {
            this.reset();
            return true;
        }
        return false;
    }
    
    public boolean passedMs(final long n) {
        return this.passedNS(this.convertToNS(n));
    }
    
    public static void setTimer(final float n) {
        Util.mc.timer.tickLength = 50.0f / n;
    }
    
    public long getPassedTimeMs() {
        return this.getMs(System.nanoTime() - this.time);
    }
    
    public void setMs(final long n) {
        this.time = System.nanoTime() - this.convertToNS(n);
    }
    
    public void setTimePassed(final long n) {
        this.time = System.nanoTime() - n * 1000000L;
    }
    
    public boolean hasPassed(final double n) {
        return System.currentTimeMillis() - this.time >= n;
    }
    
    public boolean passedDms(final double n) {
        return this.passedMs((long)n * 10L);
    }
    
    public long convertToNS(final long n) {
        return n * 1000000L;
    }
    
    public void setPaused(final boolean paused) {
        this.paused = paused;
    }
    
    public Timer reset() {
        this.time = System.nanoTime();
        return this;
    }
    
    public void setDelay(final long delay) {
        this.delay = delay;
    }
    
    public boolean isPaused() {
        return this.paused;
    }
    
    public boolean passedS(final double n) {
        return this.passedMs((long)n * 1000L);
    }
}
