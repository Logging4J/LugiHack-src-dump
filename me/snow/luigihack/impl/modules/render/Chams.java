//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.render;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Chams extends Module
{
    public /* synthetic */ Setting<Integer> Fred;
    public /* synthetic */ Setting<Boolean> solidawa;
    public /* synthetic */ Setting<Integer> blue;
    public /* synthetic */ Setting<Integer> green;
    private static /* synthetic */ Chams INSTANCE;
    public final /* synthetic */ Setting<Float> alpha;
    public /* synthetic */ Setting<Integer> Fblue;
    public final /* synthetic */ Setting<Float> lineWidth;
    public /* synthetic */ Setting<Boolean> wireframeawa;
    public /* synthetic */ Setting<Integer> Fgreen;
    public /* synthetic */ Setting<Boolean> colorsync;
    public /* synthetic */ Setting<Integer> red;
    public /* synthetic */ Setting<Boolean> playerModel;
    
    private void setInstance() {
        Chams.INSTANCE = this;
    }
    
    static {
        Chams.INSTANCE = new Chams();
    }
    
    public Chams() {
        super("Chams", "Draws a wireframe esp around other players.", Module.Category.RENDER, false, false, false);
        this.colorsync = (Setting<Boolean>)this.register(new Setting("ColorSync", (Object)Boolean.FALSE));
        this.red = (Setting<Integer>)this.register(new Setting("Red", (Object)168, (Object)0, (Object)255));
        this.green = (Setting<Integer>)this.register(new Setting("Green", (Object)0, (Object)0, (Object)255));
        this.blue = (Setting<Integer>)this.register(new Setting("Blue", (Object)232, (Object)0, (Object)255));
        this.alpha = (Setting<Float>)this.register(new Setting("PAlpha", (Object)60.0f, (Object)0.1f, (Object)255.0f));
        this.lineWidth = (Setting<Float>)this.register(new Setting("PLineWidth", (Object)1.0f, (Object)0.1f, (Object)3.0f));
        this.solidawa = (Setting<Boolean>)this.register(new Setting("Solid", (Object)Boolean.FALSE));
        this.wireframeawa = (Setting<Boolean>)this.register(new Setting("WireFrame", (Object)Boolean.FALSE));
        this.playerModel = (Setting<Boolean>)this.register(new Setting("PlayerModel", (Object)Boolean.FALSE, p0 -> (boolean)this.wireframeawa.getValue()));
        this.Fred = (Setting<Integer>)this.register(new Setting("FriendRed", (Object)0, (Object)0, (Object)255));
        this.Fgreen = (Setting<Integer>)this.register(new Setting("FriendPGreen", (Object)191, (Object)0, (Object)255));
        this.Fblue = (Setting<Integer>)this.register(new Setting("FriendPBlue", (Object)255, (Object)0, (Object)255));
        this.setInstance();
    }
    
    @SubscribeEvent
    public void onRenderPlayerEvent(final RenderPlayerEvent.Pre pre) {
        pre.getEntityPlayer().hurtTime = 0;
    }
    
    public static Chams getInstance() {
        if (Chams.INSTANCE == null) {
            Chams.INSTANCE = new Chams();
        }
        return Chams.INSTANCE;
    }
}
