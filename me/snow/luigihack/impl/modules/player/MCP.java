//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.player;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.init.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import org.lwjgl.input.*;

public class MCP extends Module
{
    private final /* synthetic */ Setting<Boolean> antiFriend;
    private final /* synthetic */ Setting<Boolean> skyonly;
    private final /* synthetic */ Setting<Mode> mode;
    private /* synthetic */ boolean clicked;
    private final /* synthetic */ Setting<Boolean> skyonly2;
    
    private void throwPearl() {
        final RayTraceResult objectMouseOver;
        if ((boolean)this.antiFriend.getValue() && (objectMouseOver = MCP.mc.objectMouseOver) != null && objectMouseOver.typeOfHit == RayTraceResult.Type.ENTITY && objectMouseOver.entityHit instanceof EntityPlayer) {
            return;
        }
        if ((boolean)this.skyonly.getValue() && MCP.mc.player.rotationPitch > 0.0f) {
            return;
        }
        final RayTraceResult objectMouseOver2;
        if ((boolean)this.skyonly2.getValue() && (objectMouseOver2 = MCP.mc.objectMouseOver) != null && objectMouseOver2.typeOfHit == RayTraceResult.Type.BLOCK) {
            return;
        }
        final int hotbarBlock = InventoryUtil.findHotbarBlock((Class)ItemEnderPearl.class);
        final boolean b = MCP.mc.player.getHeldItemOffhand().getItem() == Items.ENDER_PEARL;
        if (hotbarBlock != -1 || b) {
            final int currentItem = MCP.mc.player.inventory.currentItem;
            if (!b) {
                InventoryUtil.switchToHotbarSlot(hotbarBlock, false);
            }
            MCP.mc.playerController.processRightClick((EntityPlayer)MCP.mc.player, (World)MCP.mc.world, b ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND);
            if (!b) {
                InventoryUtil.switchToHotbarSlot(currentItem, false);
            }
        }
    }
    
    public MCP() {
        super("MCP", "Throws a pearl", Module.Category.PLAYER, false, false, false);
        this.mode = (Setting<Mode>)this.register(new Setting("Mode", (Object)Mode.MIDDLECLICK));
        this.antiFriend = (Setting<Boolean>)this.register(new Setting("AntiFriend", (Object)true));
        this.skyonly = (Setting<Boolean>)this.register(new Setting("AboveHorizon", (Object)false));
        this.skyonly2 = (Setting<Boolean>)this.register(new Setting("Skyonly", (Object)false));
    }
    
    public void onTick() {
        if (this.mode.getValue() == Mode.MIDDLECLICK) {
            if (Mouse.isButtonDown(2)) {
                if (!this.clicked) {
                    this.throwPearl();
                }
                this.clicked = true;
            }
            else {
                this.clicked = false;
            }
        }
    }
    
    public void onEnable() {
        if (!fullNullCheck() && this.mode.getValue() == Mode.TOGGLE) {
            this.throwPearl();
            this.disable();
        }
    }
    
    public enum Mode
    {
        TOGGLE, 
        MIDDLECLICK;
    }
}
