//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.turok;

import me.snow.turok.task.*;
import me.snow.turok.draw.*;

public class Turok
{
    private /* synthetic */ Font font_manager;
    
    public void resize(final int n, final int n2, final float n3, final String s) {
        GL.resize(n, n2, n3, "end");
    }
    
    public Font get_font_manager() {
        return this.font_manager;
    }
    
    public void resize(final int n, final int n2, final float n3) {
        GL.resize(n, n2, n3);
    }
    
    public Turok(final String s) {
    }
}
