//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.render;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.entity.player.*;
import me.snow.luigihack.api.event.events.*;
import java.util.function.*;
import net.minecraft.init.*;
import me.snow.luigihack.*;
import net.minecraft.entity.*;
import java.awt.*;
import me.snow.luigihack.api.util.*;
import java.util.*;
import net.minecraft.util.math.*;

public class BurrowESP extends Module
{
    private final /* synthetic */ Setting<Boolean> cOutline;
    private final /* synthetic */ Setting<Boolean> name;
    private final /* synthetic */ Setting<Integer> outlineRed;
    private final /* synthetic */ Setting<Integer> outlineGreen;
    private final /* synthetic */ Setting<Integer> boxAlpha;
    private final /* synthetic */ Setting<Float> outlineWidth;
    private final /* synthetic */ Map<EntityPlayer, BlockPos> burrowedPlayers;
    private final /* synthetic */ Setting<Integer> boxBlue;
    private final /* synthetic */ Setting<Boolean> outline;
    private final /* synthetic */ Setting<Integer> outlineAlpha;
    private final /* synthetic */ Setting<Integer> outlineBlue;
    private final /* synthetic */ Setting<Integer> boxGreen;
    private final /* synthetic */ Setting<Integer> boxRed;
    private final /* synthetic */ Setting<Boolean> box;
    
    public void onUpdate() {
        if (fullNullCheck()) {
            return;
        }
        this.burrowedPlayers.clear();
        this.getPlayers();
    }
    
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (!this.burrowedPlayers.isEmpty()) {
            this.burrowedPlayers.entrySet().forEach(this::lambda$onRender3D$8);
        }
    }
    
    private boolean isBurrowed(final EntityPlayer entityPlayer) {
        final BlockPos blockPos = new BlockPos(Math.floor(entityPlayer.posX), Math.floor(entityPlayer.posY + 0.2), Math.floor(entityPlayer.posZ));
        return BurrowESP.mc.world.getBlockState(blockPos).getBlock() == Blocks.ENDER_CHEST || BurrowESP.mc.world.getBlockState(blockPos).getBlock() == Blocks.OBSIDIAN || BurrowESP.mc.world.getBlockState(blockPos).getBlock() == Blocks.CHEST || BurrowESP.mc.world.getBlockState(blockPos).getBlock() == Blocks.ANVIL;
    }
    
    private void getPlayers() {
        for (final EntityPlayer entityPlayer : BurrowESP.mc.world.playerEntities) {
            if (entityPlayer != BurrowESP.mc.player && !LuigiHack.friendManager.isFriend(entityPlayer.getName()) && EntityUtil.isLiving((Entity)entityPlayer)) {
                if (!this.isBurrowed(entityPlayer)) {
                    continue;
                }
                this.burrowedPlayers.put(entityPlayer, new BlockPos(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ));
            }
        }
    }
    
    public void onEnable() {
        this.burrowedPlayers.clear();
    }
    
    private void renderBurrowedBlock(final BlockPos blockPos) {
        RenderUtil.drawBoxESP(blockPos, new Color((int)this.boxRed.getValue(), (int)this.boxGreen.getValue(), (int)this.boxBlue.getValue(), (int)this.boxAlpha.getValue()), true, new Color((int)this.outlineRed.getValue(), (int)this.outlineGreen.getValue(), (int)this.outlineBlue.getValue(), (int)this.outlineAlpha.getValue()), (float)this.outlineWidth.getValue(), (boolean)this.outline.getValue(), (boolean)this.box.getValue(), (int)this.boxAlpha.getValue(), true);
    }
    
    public BurrowESP() {
        super("BurrowESP", "Shows gay people.", Module.Category.RENDER, true, false, false);
        this.name = (Setting<Boolean>)this.register(new Setting("Name", (Object)false));
        this.box = (Setting<Boolean>)new Setting("Box", (Object)true);
        this.boxRed = (Setting<Integer>)this.register(new Setting("BoxRed", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.box.getValue()));
        this.boxGreen = (Setting<Integer>)this.register(new Setting("BoxGreen", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.box.getValue()));
        this.boxBlue = (Setting<Integer>)this.register(new Setting("BoxBlue", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.box.getValue()));
        this.boxAlpha = (Setting<Integer>)this.register(new Setting("BoxAlpha", (Object)125, (Object)0, (Object)255, p0 -> (boolean)this.box.getValue()));
        this.outline = (Setting<Boolean>)this.register(new Setting("Outline", (Object)true));
        this.outlineWidth = (Setting<Float>)this.register(new Setting("OutlineWidth", (Object)1.0f, (Object)0.0f, (Object)5.0f, p0 -> (boolean)this.outline.getValue()));
        this.cOutline = (Setting<Boolean>)this.register(new Setting("CustomOutline", (Object)false, p0 -> (boolean)this.outline.getValue()));
        this.outlineRed = (Setting<Integer>)this.register(new Setting("OutlineRed", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.cOutline.getValue()));
        this.outlineGreen = (Setting<Integer>)this.register(new Setting("OutlineGreen", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.cOutline.getValue()));
        this.outlineBlue = (Setting<Integer>)this.register(new Setting("OutlineBlue", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.cOutline.getValue()));
        this.outlineAlpha = (Setting<Integer>)this.register(new Setting("OutlineAlpha", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.cOutline.getValue()));
        this.burrowedPlayers = new HashMap<EntityPlayer, BlockPos>();
    }
    
    private void lambda$onRender3D$8(final Map.Entry entry) {
        this.renderBurrowedBlock(entry.getValue());
        if (this.name.getValue()) {
            RenderUtil.drawText(new AxisAlignedBB((BlockPos)entry.getValue()), ((EntityPlayer)entry.getKey()).getGameProfile().getName());
        }
    }
}
