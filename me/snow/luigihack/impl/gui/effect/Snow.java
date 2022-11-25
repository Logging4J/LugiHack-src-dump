//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.gui.effect;

import net.minecraft.client.gui.*;
import me.snow.luigihack.api.util.*;
import java.util.*;

public class Snow
{
    private /* synthetic */ int _y;
    private /* synthetic */ int _size;
    private /* synthetic */ int _fallingSpeed;
    private /* synthetic */ int _x;
    
    public void Update(final ScaledResolution scaledResolution) {
        RenderUtil.drawRect((float)this.getX(), (float)this.getY(), (float)(this.getX() + this._size), (float)(this.getY() + this._size), -1714829883);
        this.setY(this.getY() + this._fallingSpeed);
        if (this.getY() > scaledResolution.getScaledHeight() + 10 || this.getY() < -10) {
            this.setY(-10);
            final Random random = new Random();
            this._fallingSpeed = random.nextInt(5) + 1;
            this._size = random.nextInt(4) + 1;
        }
    }
    
    public Snow(final int x, final int y, final int fallingSpeed, final int size) {
        this._x = x;
        this._y = y;
        this._fallingSpeed = fallingSpeed;
        this._size = size;
    }
    
    public int getX() {
        return this._x;
    }
    
    public void setX(final int x) {
        this._x = x;
    }
    
    public void setY(final int y) {
        this._y = y;
    }
    
    public int getY() {
        return this._y;
    }
}
