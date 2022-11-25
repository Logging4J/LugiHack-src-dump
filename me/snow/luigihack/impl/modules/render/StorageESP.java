//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.render;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import net.minecraft.item.*;
import net.minecraft.tileentity.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraft.util.math.*;
import me.snow.luigihack.impl.modules.client.*;
import java.awt.*;
import me.snow.luigihack.api.util.*;
import java.util.*;

public class StorageESP extends Module
{
    private final /* synthetic */ Setting<Boolean> shulker;
    private final /* synthetic */ Setting<Boolean> colorSync;
    private final /* synthetic */ Setting<Boolean> hopper;
    private final /* synthetic */ Setting<Boolean> furnace;
    private final /* synthetic */ Setting<Boolean> cart;
    private final /* synthetic */ Setting<Float> range;
    private final /* synthetic */ Setting<Float> lineWidth;
    private final /* synthetic */ Setting<Boolean> outline;
    private final /* synthetic */ Setting<Boolean> dispenser;
    private final /* synthetic */ Setting<Boolean> chest;
    private final /* synthetic */ Setting<Boolean> echest;
    private final /* synthetic */ Setting<Boolean> box;
    private final /* synthetic */ Setting<Boolean> frame;
    private final /* synthetic */ Setting<Integer> boxAlpha;
    
    private int getEntityColor(final Entity entity) {
        if (entity instanceof EntityMinecartChest) {
            return ColorUtil.Colors.ORANGE;
        }
        if (entity instanceof EntityItemFrame && ((EntityItemFrame)entity).getDisplayedItem().getItem() instanceof ItemShulkerBox) {
            return ColorUtil.Colors.YELLOW;
        }
        if (entity instanceof EntityItemFrame && !(((EntityItemFrame)entity).getDisplayedItem().getItem() instanceof ItemShulkerBox)) {
            return ColorUtil.Colors.ORANGE;
        }
        return -1;
    }
    
    public StorageESP() {
        super("StorageESP", "Highlights Containers.", Module.Category.RENDER, false, false, false);
        this.range = (Setting<Float>)this.register(new Setting("Range", (Object)50.0f, (Object)1.0f, (Object)300.0f));
        this.colorSync = (Setting<Boolean>)this.register(new Setting("Sync", (Object)false));
        this.chest = (Setting<Boolean>)this.register(new Setting("Chest", (Object)true));
        this.dispenser = (Setting<Boolean>)this.register(new Setting("Dispenser", (Object)false));
        this.shulker = (Setting<Boolean>)this.register(new Setting("Shulker", (Object)true));
        this.echest = (Setting<Boolean>)this.register(new Setting("Ender Chest", (Object)true));
        this.furnace = (Setting<Boolean>)this.register(new Setting("Furnace", (Object)false));
        this.hopper = (Setting<Boolean>)this.register(new Setting("Hopper", (Object)false));
        this.cart = (Setting<Boolean>)this.register(new Setting("Minecart", (Object)false));
        this.frame = (Setting<Boolean>)this.register(new Setting("Item Frame", (Object)false));
        this.box = (Setting<Boolean>)this.register(new Setting("Box", (Object)true));
        this.boxAlpha = (Setting<Integer>)this.register(new Setting("BoxAlpha", (Object)70, (Object)0, (Object)255, p0 -> (boolean)this.box.getValue()));
        this.outline = (Setting<Boolean>)this.register(new Setting("Outline", (Object)true));
        this.lineWidth = (Setting<Float>)this.register(new Setting("LineWidth", (Object)1.0f, (Object)0.1f, (Object)5.0f, p0 -> (boolean)this.outline.getValue()));
    }
    
    private int getTileEntityColor(final TileEntity tileEntity) {
        if (tileEntity instanceof TileEntityChest) {
            return ColorUtil.Colors.ORANGE;
        }
        if (tileEntity instanceof TileEntityShulkerBox) {
            return ColorUtil.Colors.MAGENTA;
        }
        if (tileEntity instanceof TileEntityEnderChest) {
            return ColorUtil.Colors.PURPLE;
        }
        if (tileEntity instanceof TileEntityFurnace) {
            return ColorUtil.Colors.LIGHT_GRAY;
        }
        if (tileEntity instanceof TileEntityHopper) {
            return ColorUtil.Colors.GRAY;
        }
        if (tileEntity instanceof TileEntityDispenser) {
            return ColorUtil.Colors.LIGHT_GRAY;
        }
        return -1;
    }
    
    public void onRender3D(final Render3DEvent render3DEvent) {
        final HashMap<BlockPos, Integer> hashMap = new HashMap<BlockPos, Integer>();
        for (final TileEntity tileEntity : StorageESP.mc.world.loadedTileEntityList) {
            final BlockPos getPos;
            if (((tileEntity instanceof TileEntityChest && (boolean)this.chest.getValue()) || (tileEntity instanceof TileEntityDispenser && (boolean)this.dispenser.getValue()) || (tileEntity instanceof TileEntityShulkerBox && (boolean)this.shulker.getValue()) || (tileEntity instanceof TileEntityEnderChest && (boolean)this.echest.getValue()) || (tileEntity instanceof TileEntityFurnace && (boolean)this.furnace.getValue()) || (tileEntity instanceof TileEntityHopper && (boolean)this.hopper.getValue())) && StorageESP.mc.player.getDistanceSq(getPos = tileEntity.getPos()) <= MathUtil.square((float)this.range.getValue())) {
                final int tileEntityColor;
                if ((tileEntityColor = this.getTileEntityColor(tileEntity)) == -1) {
                    continue;
                }
                hashMap.put(getPos, tileEntityColor);
            }
        }
        for (final Entity entity : StorageESP.mc.world.loadedEntityList) {
            final BlockPos getPosition;
            if (((entity instanceof EntityItemFrame && (boolean)this.frame.getValue()) || (entity instanceof EntityMinecartChest && (boolean)this.cart.getValue())) && StorageESP.mc.player.getDistanceSq(getPosition = entity.getPosition()) <= MathUtil.square((float)this.range.getValue())) {
                final int entityColor;
                if ((entityColor = this.getEntityColor(entity)) == -1) {
                    continue;
                }
                hashMap.put(getPosition, entityColor);
            }
        }
        for (final Map.Entry<BlockPos, Integer> entry : hashMap.entrySet()) {
            final BlockPos blockPos = entry.getKey();
            final int intValue = entry.getValue();
            RenderUtil.drawBoxESP(blockPos, ((boolean)this.colorSync.getValue()) ? Colors.INSTANCE.getCurrentColor() : new Color(intValue), false, new Color(intValue), (float)this.lineWidth.getValue(), (boolean)this.outline.getValue(), (boolean)this.box.getValue(), (int)this.boxAlpha.getValue(), false);
        }
    }
}
