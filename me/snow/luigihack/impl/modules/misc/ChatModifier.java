//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.misc;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.impl.gui.custom.*;
import me.snow.luigihack.api.util.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraft.network.play.server.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.network.play.client.*;
import java.text.*;
import me.snow.luigihack.impl.modules.client.*;
import java.util.*;
import net.minecraft.client.gui.*;
import net.minecraftforge.fml.common.*;
import me.snow.turok.task.*;
import net.minecraft.util.text.*;

public class ChatModifier extends Module
{
    public /* synthetic */ Setting<Double> yOffset;
    public /* synthetic */ Setting<Boolean> space;
    public /* synthetic */ Setting<Boolean> clean;
    public /* synthetic */ Setting<Boolean> rainbowTimeStamps;
    public /* synthetic */ Setting<Boolean> infinite;
    public static /* synthetic */ GuiNewChat guiChat;
    /* synthetic */ String[] randomAA;
    public static /* synthetic */ GuiChat guiChatSmooth;
    private final /* synthetic */ Timer timer;
    public /* synthetic */ Setting<Boolean> smoothChat;
    private static /* synthetic */ ChatModifier INSTANCE;
    public /* synthetic */ Setting<Suffix> suffix;
    public /* synthetic */ Setting<Double> vIncrements;
    public /* synthetic */ Setting<Boolean> all;
    public /* synthetic */ Setting<Double> vSpeed;
    public /* synthetic */ Setting<Double> xOffset;
    public /* synthetic */ Setting<TextUtil.Color> timeStamps;
    public /* synthetic */ Setting<Type> type;
    public /* synthetic */ Setting<String> customSuffix;
    public /* synthetic */ Setting<Double> vLength;
    public /* synthetic */ Setting<TextUtil.Color> bracket;
    
    @SubscribeEvent
    public void onPacketReceive(final PacketEvent.Receive receive) {
        if (receive.getStage() == 0 && this.timeStamps.getValue() != TextUtil.Color.NONE && receive.getPacket() instanceof SPacketChat) {
            if (!((SPacketChat)receive.getPacket()).isSystem()) {
                return;
            }
            final String getFormattedText = ((SPacketChat)receive.getPacket()).chatComponent.getFormattedText();
            ((SPacketChat)receive.getPacket()).chatComponent = (ITextComponent)new TextComponentString(String.valueOf(new StringBuilder().append(this.getTimeString(getFormattedText)).append(getFormattedText)));
        }
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        final StringBuilder obj = new StringBuilder();
        obj.append(this.convert_base(this.random_string(this.randomAA)));
        if (send.getStage() == 0 && send.getPacket() instanceof CPacketChatMessage) {
            final CPacketChatMessage cPacketChatMessage = (CPacketChatMessage)send.getPacket();
            String s = cPacketChatMessage.getMessage();
            if (s.startsWith("/")) {
                return;
            }
            switch ((Suffix)this.suffix.getValue()) {
                case EARTH: {
                    s = String.valueOf(new StringBuilder().append(s).append(" \u23d0 3\u1d00\u0280\u1d1b\u029c\u029c4\u1d04\u1d0b"));
                    break;
                }
                case PHOBOS: {
                    s = String.valueOf(new StringBuilder().append(s).append(" \u23d0 \u1d18\u029c\u1d0f\u0299\u1d0f\ua731"));
                    break;
                }
                case WEATHER: {
                    s = String.valueOf(new StringBuilder().append(s).append(" \u23d0 \u1d21\u1d07\u1d00\u1d1b\u029c\u1d07\u0280 \u2764 \u2744"));
                    break;
                }
                case LUIGIHACK: {
                    s = String.valueOf(new StringBuilder().append(s).append(" \u23d0 \u029f\u1d1c\u026a\u0262\u026a\u029c\u1d00\u1d04\u1d0b \u0e05\u2022\u03c9\u2022\u0e05 meow"));
                    break;
                }
                case NEWLUIGIHACK: {
                    s = String.valueOf(new StringBuilder().append(s).append(" \u23d0 \u029f\u1d1c\u026a\u0262\u026a\u029c\u1d00\u1d04\u1d0b+ \u262a"));
                    break;
                }
                case MUFFIN: {
                    s = String.valueOf(new StringBuilder().append(s).append(" \u30c4 \u2c98\u03c5\u2a0d\u2a0d\u2aef\ufb21"));
                    break;
                }
                case FakeCherry: {
                    s = String.valueOf(new StringBuilder().append(s).append(" : \u1d04\u029c\u1d07\u0280\u0280\u028f \u1d04\u029f\u026a\u1d07\u0274\u1d1b 4.0"));
                    break;
                }
                case CUSTOM: {
                    s = String.valueOf(new StringBuilder().append(s).append((String)this.customSuffix.getValue()));
                    break;
                }
                case Random: {
                    s = String.valueOf(new StringBuilder().append(s).append((Object)obj));
                    break;
                }
            }
            if (s.length() >= 256) {
                s = s.substring(0, 256);
            }
            cPacketChatMessage.message = s;
        }
    }
    
    public String getTimeString(final String s) {
        final String format = new SimpleDateFormat("k:mm").format(new Date());
        if (this.rainbowTimeStamps.getValue()) {
            final StringBuilder obj = new StringBuilder(String.valueOf(new StringBuilder().append("<").append(format).append(">").append(this.space.getValue() ? " " : "")));
            obj.insert(0, "§+");
            if (!s.contains(Global.getInstance().getRainbowCommandMessage())) {
                obj.append("§r");
            }
            return String.valueOf(obj);
        }
        return String.valueOf(new StringBuilder().append((this.bracket.getValue() == TextUtil.Color.NONE) ? "" : TextUtil.coloredString("<", (TextUtil.Color)this.bracket.getValue())).append(TextUtil.coloredString(format, (TextUtil.Color)this.timeStamps.getValue())).append((this.bracket.getValue() == TextUtil.Color.NONE) ? "" : TextUtil.coloredString(">", (TextUtil.Color)this.bracket.getValue())).append(this.space.getValue() ? " " : "").append("§r"));
    }
    
    public String convert_base(final String s) {
        return smoth(s);
    }
    
    @Override
    public void onLogin() {
        if (this.isEnabled()) {
            this.disable();
            this.enable();
        }
    }
    
    public String random_string(final String[] array) {
        return array[new Random().nextInt(array.length)];
    }
    
    @Override
    public void onEnable() {
        ChatModifier.guiChatSmooth = new GuiChat(ChatModifier.mc);
        ObfuscationReflectionHelper.setPrivateValue((Class)GuiIngame.class, (Object)ChatModifier.mc.ingameGUI, (Object)ChatModifier.guiChatSmooth, new String[] { "persistantChatGUI" });
    }
    
    @SubscribeEvent
    public void onChatPacketReceive(final PacketEvent.Receive receive) {
        if (receive.getStage() == 0) {
            receive.getPacket();
        }
    }
    
    static {
        ChatModifier.INSTANCE = new ChatModifier();
    }
    
    @Override
    public void onDisable() {
        ChatModifier.guiChat = new GuiNewChat(ChatModifier.mc);
        ObfuscationReflectionHelper.setPrivateValue((Class)GuiIngame.class, (Object)ChatModifier.mc.ingameGUI, (Object)ChatModifier.guiChat, new String[] { "persistantChatGUI" });
    }
    
    public static ChatModifier getInstance() {
        if (ChatModifier.INSTANCE == null) {
            ChatModifier.INSTANCE = new ChatModifier();
        }
        return ChatModifier.INSTANCE;
    }
    
    private void setInstance() {
        ChatModifier.INSTANCE = this;
    }
    
    public static String smoth(final String s) {
        return Font.smoth(s);
    }
    
    public ChatModifier() {
        super("ChatTweaks", "Modifies your chat", Category.MISC, true, false, false);
        this.timer = new Timer();
        this.suffix = (Setting<Suffix>)this.register(new Setting("Suffix", (Object)Suffix.NONE, "Your Suffix."));
        this.customSuffix = (Setting<String>)this.register(new Setting("", (Object)" | LuigiHack", p0 -> this.suffix.getValue() == Suffix.CUSTOM));
        this.clean = (Setting<Boolean>)this.register(new Setting("CleanChat", (Object)Boolean.FALSE, "Cleans your chat"));
        this.infinite = (Setting<Boolean>)this.register(new Setting("Infinite", (Object)Boolean.FALSE, "Makes your chat infinite."));
        this.timeStamps = (Setting<TextUtil.Color>)this.register(new Setting("Time", (Object)TextUtil.Color.NONE));
        this.rainbowTimeStamps = (Setting<Boolean>)this.register(new Setting("RainbowTimeStamps", (Object)Boolean.FALSE, p0 -> this.timeStamps.getValue() != TextUtil.Color.NONE));
        this.bracket = (Setting<TextUtil.Color>)this.register(new Setting("Bracket", (Object)TextUtil.Color.WHITE, p0 -> this.timeStamps.getValue() != TextUtil.Color.NONE));
        this.space = (Setting<Boolean>)this.register(new Setting("Space", (Object)Boolean.TRUE, p0 -> this.timeStamps.getValue() != TextUtil.Color.NONE));
        this.all = (Setting<Boolean>)this.register(new Setting("All", (Object)Boolean.FALSE, p0 -> this.timeStamps.getValue() != TextUtil.Color.NONE));
        this.smoothChat = (Setting<Boolean>)this.register(new Setting("SmoothChat", (Object)false));
        this.xOffset = (Setting<Double>)this.register(new Setting("XOffset", (Object)0.0, (Object)0.0, (Object)600, p0 -> (boolean)this.smoothChat.getValue()));
        this.yOffset = (Setting<Double>)this.register(new Setting("YOffset", (Object)0.0, (Object)0.0, (Object)30.0, p0 -> (boolean)this.smoothChat.getValue()));
        this.vSpeed = (Setting<Double>)this.register(new Setting("VSpeed", (Object)30.0, (Object)1.0, (Object)100.0, p0 -> (boolean)this.smoothChat.getValue()));
        this.vLength = (Setting<Double>)this.register(new Setting("VLength", (Object)10.0, (Object)5.0, (Object)100.0, p0 -> (boolean)this.smoothChat.getValue()));
        this.vIncrements = (Setting<Double>)this.register(new Setting("VIncrements", (Object)1.0, (Object)1.0, (Object)5.0, p0 -> (boolean)this.smoothChat.getValue()));
        this.type = (Setting<Type>)this.register(new Setting("Type", (Object)Type.HORIZONTAL, p0 -> (boolean)this.smoothChat.getValue()));
        this.randomAA = new String[] { " \u23d0 3\u1d00\u0280\u1d1b\u029c\u029c4\u1d04\u1d0b", " \u23d0 \u1d18\u029c\u1d0f\u0299\u1d0f\ua731", " \u23d0 \u1d21\u1d07\u1d00\u1d1b\u029c\u1d07\u0280 \u2764 \u2744", " \u23d0 \u029f\u1d1c\u026a\u0262\u026a\u029c\u1d00\u1d04\u1d0b \u0e05\u2022\u03c9\u2022\u0e05 meow", " \u23d0 \u029f\u1d1c\u026a\u0262\u026a\u029c\u1d00\u1d04\u1d0b+ \u262a", " : \u1d04\u0280\u1d07\u1d18\u1d07 \u1d04\u029f\u026a\u1d07\u0274\u1d1b \u1d18\u029f\u1d1c\ua731 \u03b20.9", " : s\u1d1b\u0280\u1d00\u1d21\u0299\u1d07\u0280\u0280\u028f \u029c\u1d00\u1d04\u1d0b \u03b20.2" };
        this.setInstance();
    }
    
    public enum Type
    {
        HORIZONTAL, 
        VERTICAL;
    }
    
    public enum Suffix
    {
        NEWLUIGIHACK, 
        MUFFIN, 
        Random, 
        EARTH, 
        LUIGIHACK, 
        FakeCherry, 
        PHOBOS, 
        CUSTOM, 
        WEATHER, 
        NONE;
    }
}
