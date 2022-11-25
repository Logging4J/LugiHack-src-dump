//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.misc;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.player.*;
import me.snow.luigihack.*;
import me.snow.luigihack.impl.command.*;
import me.snow.luigihack.impl.modules.client.*;
import net.minecraft.entity.*;
import net.minecraftforge.fml.common.gameevent.*;
import org.lwjgl.input.*;
import me.snow.luigihack.impl.gui.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class MCF extends Module
{
    private final /* synthetic */ Setting<Boolean> sendmsg;
    private final /* synthetic */ Setting<Boolean> keyboard;
    private final /* synthetic */ Setting<Bind> key;
    private /* synthetic */ boolean clicked;
    private final /* synthetic */ Setting<Boolean> middleClick;
    
    private void onClick() {
        final RayTraceResult objectMouseOver = MCF.mc.objectMouseOver;
        final Entity entityHit;
        if (objectMouseOver != null && objectMouseOver.typeOfHit == RayTraceResult.Type.ENTITY && (entityHit = objectMouseOver.entityHit) instanceof EntityPlayer) {
            if (LuigiHack.friendManager.isFriend(entityHit.getName())) {
                LuigiHack.friendManager.removeFriend(entityHit.getName());
                Command.sendMessage(String.valueOf(new StringBuilder().append("§c").append(entityHit.getName()).append("§r unfriended.")));
                if (this.sendmsg.getValue()) {}
            }
            else {
                LuigiHack.friendManager.addFriend(entityHit.getName());
                Command.sendMessage(String.valueOf(new StringBuilder().append("§b").append(entityHit.getName()).append("§r friended.")));
                if (this.sendmsg.getValue()) {
                    MCF.mc.player.sendChatMessage(String.valueOf(new StringBuilder().append("/msg ").append(entityHit.getName()).append(" You got added to ").append(HUD.mc.player.getDisplayNameString()).append("'s friends list.")));
                }
            }
        }
        this.clicked = true;
    }
    
    public MCF() {
        super("MCF", "Middleclick Friends.", Category.MISC, true, false, false);
        this.middleClick = (Setting<Boolean>)this.register(new Setting("MiddleClick", (Object)true));
        this.keyboard = (Setting<Boolean>)this.register(new Setting("Keyboard", (Object)false));
        this.sendmsg = (Setting<Boolean>)this.register(new Setting("Send Msgs", (Object)true));
        this.key = (Setting<Bind>)this.register(new Setting("KeyBind", (Object)new Bind(-1), p0 -> (boolean)this.keyboard.getValue()));
    }
    
    @Override
    public void onUpdate() {
        if (Mouse.isButtonDown(2)) {
            if (!this.clicked && (boolean)this.middleClick.getValue() && MCF.mc.currentScreen == null) {
                this.onClick();
            }
            this.clicked = true;
        }
        else {
            this.clicked = false;
        }
    }
    
    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public void onKeyInput(final InputEvent.KeyInputEvent keyInputEvent) {
        if ((boolean)this.keyboard.getValue() && Keyboard.getEventKeyState() && !(MCF.mc.currentScreen instanceof LuigiGui) && ((Bind)this.key.getValue()).getKey() == Keyboard.getEventKey()) {
            this.onClick();
        }
    }
}
