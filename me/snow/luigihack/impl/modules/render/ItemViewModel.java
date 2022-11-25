//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.render;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;

public class ItemViewModel extends Module
{
    public /* synthetic */ Setting<Integer> swingspeed;
    public /* synthetic */ Setting<Float> mainX;
    public /* synthetic */ Setting<Float> offX;
    public /* synthetic */ Setting<Float> rotationZ;
    public /* synthetic */ Setting<Float> positionZ;
    public static /* synthetic */ ItemViewModel INSTANCE;
    public /* synthetic */ Setting<Integer> viewAlpha;
    public /* synthetic */ Setting<Float> offset;
    public /* synthetic */ Setting<Integer> fov;
    /* synthetic */ float fovOld;
    public /* synthetic */ Setting<Float> positionX;
    public /* synthetic */ Setting<Boolean> normalOffset;
    public /* synthetic */ Setting<Float> rotationY;
    public /* synthetic */ Setting<Float> sizeY;
    public /* synthetic */ Setting<Float> offY;
    public /* synthetic */ Setting<Float> rotationX;
    public /* synthetic */ Setting<Boolean> oldA;
    public /* synthetic */ Setting<Float> positionY;
    public /* synthetic */ Setting<Float> sizeX;
    public /* synthetic */ Setting<Boolean> changeswing;
    public /* synthetic */ Setting<Float> sizeZ;
    public /* synthetic */ Setting<Float> mainY;
    
    public void onUpdate() {
        if (this.normalOffset.getValue()) {
            ItemViewModel.mc.entityRenderer.itemRenderer.equippedProgressOffHand = (float)this.offset.getValue();
        }
        ItemViewModel.mc.gameSettings.fovSetting = (float)(int)this.fov.getValue();
        if ((boolean)this.oldA.getValue() && ItemViewModel.mc.entityRenderer.itemRenderer.prevEquippedProgressMainHand >= 0.9) {
            ItemViewModel.mc.entityRenderer.itemRenderer.equippedProgressMainHand = 1.0f;
            ItemViewModel.mc.entityRenderer.itemRenderer.itemStackMainHand = ItemViewModel.mc.player.getHeldItemMainhand();
        }
    }
    
    private void setInstance() {
        ItemViewModel.INSTANCE = this;
    }
    
    public static ItemViewModel getINSTANCE() {
        if (ItemViewModel.INSTANCE == null) {
            ItemViewModel.INSTANCE = new ItemViewModel();
        }
        return ItemViewModel.INSTANCE;
    }
    
    static {
        ItemViewModel.INSTANCE = new ItemViewModel();
    }
    
    public void onEnable() {
        this.fovOld = ItemViewModel.mc.gameSettings.fovSetting;
    }
    
    public ItemViewModel() {
        super("ItemViewModel", "Allows you to customize how items look in your hand", Module.Category.RENDER, false, false, false);
        this.fov = (Setting<Integer>)this.register(new Setting("Fov", (Object)130, (Object)90, (Object)180));
        this.normalOffset = (Setting<Boolean>)this.register(new Setting("OffNormal", (Object)false));
        this.viewAlpha = (Setting<Integer>)this.register(new Setting("Item Alpha", (Object)255, (Object)0, (Object)255));
        this.offset = (Setting<Float>)this.register(new Setting("Offset", (Object)0.0f, (Object)0.0f, (Object)1.0f, p0 -> (boolean)this.normalOffset.getValue()));
        this.offX = (Setting<Float>)this.register(new Setting("OffX", (Object)0.0f, (Object)(-1.0f), (Object)1.0f, p0 -> !(boolean)this.normalOffset.getValue()));
        this.offY = (Setting<Float>)this.register(new Setting("OffY", (Object)0.0f, (Object)(-1.0f), (Object)1.0f, p0 -> !(boolean)this.normalOffset.getValue()));
        this.mainX = (Setting<Float>)this.register(new Setting("MainX", (Object)0.3f, (Object)(-1.0f), (Object)1.0f));
        this.mainY = (Setting<Float>)this.register(new Setting("MainY", (Object)0.0f, (Object)(-1.0f), (Object)1.0f));
        this.sizeX = (Setting<Float>)this.register(new Setting("SizeX", (Object)1.0f, (Object)0.0f, (Object)2.0f));
        this.sizeY = (Setting<Float>)this.register(new Setting("SizeY", (Object)1.0f, (Object)0.0f, (Object)2.0f));
        this.sizeZ = (Setting<Float>)this.register(new Setting("SizeZ", (Object)1.0f, (Object)0.0f, (Object)2.0f));
        this.rotationX = (Setting<Float>)this.register(new Setting("RotationX", (Object)0.0f, (Object)0.0f, (Object)1.0f));
        this.rotationY = (Setting<Float>)this.register(new Setting("RotationY", (Object)0.0f, (Object)0.0f, (Object)1.0f));
        this.rotationZ = (Setting<Float>)this.register(new Setting("RotationZ", (Object)0.0f, (Object)0.0f, (Object)1.0f));
        this.positionX = (Setting<Float>)this.register(new Setting("PositionX", (Object)0.0f, (Object)(-2.0f), (Object)2.0f));
        this.positionY = (Setting<Float>)this.register(new Setting("PositionY", (Object)0.0f, (Object)(-2.0f), (Object)2.0f));
        this.positionZ = (Setting<Float>)this.register(new Setting("PositionZ", (Object)0.0f, (Object)(-2.0f), (Object)2.0f));
        this.oldA = (Setting<Boolean>)this.register(new Setting("OldAnimation", (Object)false));
        this.changeswing = (Setting<Boolean>)this.register(new Setting("SlowSwing", (Object)false));
        this.swingspeed = (Setting<Integer>)this.register(new Setting("SwingDelay", (Object)15, (Object)1, (Object)20, p0 -> (boolean)this.changeswing.getValue()));
        this.setInstance();
    }
    
    public void onDisable() {
        ItemViewModel.mc.gameSettings.fovSetting = this.fovOld;
    }
}
