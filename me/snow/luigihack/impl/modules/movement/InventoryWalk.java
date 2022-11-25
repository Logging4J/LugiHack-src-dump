//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.movement;

import me.snow.luigihack.impl.modules.*;
import net.minecraft.client.settings.*;
import me.snow.luigihack.api.event.events.*;
import me.zero.alpine.fork.listener.*;
import net.minecraft.client.gui.*;
import org.lwjgl.input.*;
import net.minecraft.util.math.*;
import net.minecraft.client.entity.*;
import java.util.function.*;
import net.minecraftforge.client.settings.*;

public class InventoryWalk extends Module
{
    private static final /* synthetic */ KeyBinding[] MOVEMENT_KEYS;
    @EventHandler
    public /* synthetic */ Listener<GuiScreenEvent.Displayed> listener;
    
    public void onUpdate() {
        if (InventoryWalk.mc.currentScreen == null) {
            return;
        }
        if (InventoryWalk.mc.currentScreen instanceof GuiChat) {
            return;
        }
        final EntityPlayerSP player = InventoryWalk.mc.player;
        player.rotationYaw += (Keyboard.isKeyDown(205) ? 4.0f : (Keyboard.isKeyDown(203) ? -4.0f : 0.0f));
        InventoryWalk.mc.player.rotationPitch += (float)((Keyboard.isKeyDown(208) ? 4 : (Keyboard.isKeyDown(200) ? -4 : 0)) * 0.75);
        InventoryWalk.mc.player.rotationPitch = MathHelper.clamp(InventoryWalk.mc.player.rotationPitch, -90.0f, 90.0f);
        this.runCheck();
    }
    
    public InventoryWalk() {
        super("GUIMove", "Allows you to Move while in GUIs", Module.Category.MOVEMENT, true, false, false);
        this.listener = new Listener<GuiScreenEvent.Displayed>(p0 -> {
            if (InventoryWalk.mc.currentScreen != null) {
                if (!(InventoryWalk.mc.currentScreen instanceof GuiChat)) {
                    this.runCheck();
                }
            }
        }, (Predicate<GuiScreenEvent.Displayed>[])new Predicate[0]);
    }
    
    private void runCheck() {
        for (final KeyBinding keyBinding : InventoryWalk.MOVEMENT_KEYS) {
            if (Keyboard.isKeyDown(keyBinding.getKeyCode())) {
                if (keyBinding.getKeyConflictContext() != KeyConflictContext.UNIVERSAL) {
                    keyBinding.setKeyConflictContext((IKeyConflictContext)KeyConflictContext.UNIVERSAL);
                }
                KeyBinding.setKeyBindState(keyBinding.getKeyCode(), true);
            }
            else {
                KeyBinding.setKeyBindState(keyBinding.getKeyCode(), false);
            }
        }
    }
    
    static {
        MOVEMENT_KEYS = new KeyBinding[] { InventoryWalk.mc.gameSettings.keyBindForward, InventoryWalk.mc.gameSettings.keyBindRight, InventoryWalk.mc.gameSettings.keyBindBack, InventoryWalk.mc.gameSettings.keyBindLeft, InventoryWalk.mc.gameSettings.keyBindJump, InventoryWalk.mc.gameSettings.keyBindSprint };
    }
}
