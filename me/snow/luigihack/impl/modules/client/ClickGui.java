//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.client;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.client.settings.*;
import me.snow.luigihack.api.util.*;
import me.snow.luigihack.impl.gui.*;
import net.minecraft.client.gui.*;
import me.snow.luigihack.api.event.events.*;
import me.snow.luigihack.*;
import me.snow.luigihack.impl.command.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class ClickGui extends Module
{
    public /* synthetic */ Setting<Integer> moBlue;
    public /* synthetic */ Setting<Float> fov;
    public /* synthetic */ Setting<Boolean> futuregear;
    public /* synthetic */ Setting<Integer> textRed;
    public /* synthetic */ Setting<Integer> d_green;
    public /* synthetic */ Setting<Integer> ABlue;
    public /* synthetic */ Setting<Boolean> rainbowRolling;
    public /* synthetic */ Setting<Integer> sidered;
    public /* synthetic */ Setting<Integer> topRed;
    public /* synthetic */ Setting<Integer> gradiantred;
    public /* synthetic */ Setting<Boolean> outline2;
    public /* synthetic */ Setting<Integer> b_red;
    public /* synthetic */ Setting<Integer> topGreen;
    public /* synthetic */ Setting<Integer> d_blue;
    public /* synthetic */ Setting<Integer> ARed;
    public /* synthetic */ Setting<Integer> green;
    public /* synthetic */ Setting<String> close;
    public /* synthetic */ Setting<Integer> hoverAlpha;
    public /* synthetic */ Setting<Integer> moRed;
    public /* synthetic */ Setting<Integer> AGreen;
    public /* synthetic */ Setting<Integer> textRed2;
    public /* synthetic */ Setting<Boolean> TcolorSync;
    public /* synthetic */ Setting<Boolean> centerAAA;
    public /* synthetic */ Setting<Integer> particleLength;
    public /* synthetic */ Setting<Boolean> frameSettings;
    public /* synthetic */ Setting<Integer> d_alpha;
    public /* synthetic */ Setting<Integer> particlegreen;
    public /* synthetic */ Setting<Integer> b_alpha;
    public /* synthetic */ Setting<Integer> textBlue;
    public /* synthetic */ Setting<Integer> frameRed;
    public /* synthetic */ Setting<Integer> d_red;
    public /* synthetic */ Setting<Boolean> PBlack;
    public /* synthetic */ Setting<Page> setting;
    public /* synthetic */ Setting<Integer> topAlpha;
    public /* synthetic */ Setting<Integer> frameBlue;
    public /* synthetic */ Setting<Integer> textAlpha2;
    public /* synthetic */ Setting<Integer> particlered;
    public /* synthetic */ Setting<Integer> o_red;
    public /* synthetic */ Setting<Integer> textBlue2;
    public /* synthetic */ Setting<Integer> sideblue;
    public /* synthetic */ Setting<Integer> textGreen2;
    public /* synthetic */ Setting<Boolean> toparrow;
    public /* synthetic */ Setting<Integer> o_alpha;
    public /* synthetic */ Setting<Integer> topBlue;
    public /* synthetic */ Setting<Boolean> openCloseChange;
    public /* synthetic */ Setting<String> open;
    public /* synthetic */ Setting<Integer> moGreen;
    public /* synthetic */ Setting<Integer> b_green;
    public /* synthetic */ Setting<Integer> frameAlpha;
    public /* synthetic */ Setting<Integer> sidealpha;
    public /* synthetic */ Setting<Boolean> particles;
    public /* synthetic */ Setting<Boolean> snowing;
    public /* synthetic */ Setting<Integer> moAlpha;
    public /* synthetic */ Setting<Integer> red;
    public /* synthetic */ Setting<Boolean> sideSettings;
    public /* synthetic */ Setting<Integer> gradiantalpha;
    public /* synthetic */ Setting<Boolean> outline;
    public /* synthetic */ Setting<Integer> textGreen;
    public /* synthetic */ Setting<Boolean> PPhobos;
    public /* synthetic */ Setting<Boolean> PRed;
    public /* synthetic */ Setting<Integer> textAlpha;
    public /* synthetic */ Setting<Integer> frameGreen;
    private static /* synthetic */ ClickGui INSTANCE;
    public /* synthetic */ Setting<Boolean> gradiant;
    public /* synthetic */ Setting<String> moduleButton;
    public /* synthetic */ Setting<Float> outlineThickness;
    public /* synthetic */ Setting<Boolean> dark;
    public /* synthetic */ Setting<Integer> o_blue;
    public /* synthetic */ Setting<Boolean> customFov;
    public /* synthetic */ Setting<Integer> gradiantblue;
    public /* synthetic */ Setting<Boolean> moduleOutline;
    public /* synthetic */ Setting<Boolean> colorSync;
    public /* synthetic */ Setting<String> prefix;
    public /* synthetic */ Setting<Integer> b_blue;
    public /* synthetic */ Setting<Integer> blue;
    public /* synthetic */ Setting<Integer> sidegreen;
    public /* synthetic */ Setting<Integer> particleblue;
    public /* synthetic */ Setting<Integer> o_green;
    public /* synthetic */ Setting<Integer> alpha;
    public /* synthetic */ Setting<Integer> gradiantgreen;
    
    public static ClickGui getInstance() {
        if (ClickGui.INSTANCE == null) {
            ClickGui.INSTANCE = new ClickGui();
        }
        return ClickGui.INSTANCE;
    }
    
    @Override
    public void onUpdate() {
        if (this.customFov.getValue()) {
            ClickGui.mc.gameSettings.setOptionFloatValue(GameSettings.Options.FOV, (float)this.fov.getValue());
        }
    }
    
    @Override
    public void onEnable() {
        Util.mc.displayGuiScreen((GuiScreen)new LuigiGui());
    }
    
    public ClickGui() {
        super("ClickGui", "Opens the ClickGui", Category.CLIENT, true, false, false);
        this.prefix = (Setting<String>)this.register(new Setting("Prefix", (Object)".").setRenderName(true));
        this.colorSync = (Setting<Boolean>)this.register(new Setting("CSync", (Object)false));
        this.TcolorSync = (Setting<Boolean>)this.register(new Setting("TopCSync", (Object)false));
        this.rainbowRolling = (Setting<Boolean>)this.register(new Setting("RollingRainbow", (Object)false, p0 -> (boolean)this.colorSync.getValue() && (boolean)Colors.INSTANCE.rainbow.getValue()));
        this.setting = (Setting<Page>)this.register(new Setting("Settings", (Object)Page.Main));
        this.red = (Setting<Integer>)this.register(new Setting("Red", (Object)36, (Object)0, (Object)255, p0 -> this.setting.getValue() == Page.Main));
        this.green = (Setting<Integer>)this.register(new Setting("Green", (Object)150, (Object)0, (Object)255, p0 -> this.setting.getValue() == Page.Main));
        this.blue = (Setting<Integer>)this.register(new Setting("Blue", (Object)190, (Object)0, (Object)255, p0 -> this.setting.getValue() == Page.Main));
        this.hoverAlpha = (Setting<Integer>)this.register(new Setting("Alpha", (Object)130, (Object)0, (Object)255, p0 -> this.setting.getValue() == Page.Main));
        this.b_red = (Setting<Integer>)this.register(new Setting("BackgroundRed", (Object)0, (Object)0, (Object)255, p0 -> this.setting.getValue() == Page.Main));
        this.b_green = (Setting<Integer>)this.register(new Setting("BackgroundGreen", (Object)0, (Object)0, (Object)255, p0 -> this.setting.getValue() == Page.Main));
        this.b_blue = (Setting<Integer>)this.register(new Setting("BackgroundBlue", (Object)0, (Object)0, (Object)255, p0 -> this.setting.getValue() == Page.Main));
        this.b_alpha = (Setting<Integer>)this.register(new Setting("BackgroundAlpha", (Object)75, (Object)0, (Object)255, p0 -> this.setting.getValue() == Page.Main));
        this.d_red = (Setting<Integer>)this.register(new Setting("DisabledRed", (Object)127, (Object)0, (Object)255, p0 -> this.setting.getValue() == Page.Main));
        this.d_green = (Setting<Integer>)this.register(new Setting("DisabledGreen", (Object)127, (Object)0, (Object)255, p0 -> this.setting.getValue() == Page.Main));
        this.d_blue = (Setting<Integer>)this.register(new Setting("DisabledBlue", (Object)127, (Object)0, (Object)255, p0 -> this.setting.getValue() == Page.Main));
        this.d_alpha = (Setting<Integer>)this.register(new Setting("DisabledAlpha", (Object)0, (Object)0, (Object)255, p0 -> this.setting.getValue() == Page.Main));
        this.alpha = (Setting<Integer>)this.register(new Setting("HoverAlpha", (Object)240, (Object)0, (Object)255, p0 -> this.setting.getValue() == Page.Main));
        this.topRed = (Setting<Integer>)this.register(new Setting("TopRed", (Object)36, (Object)0, (Object)255, p0 -> this.setting.getValue() == Page.Main));
        this.topGreen = (Setting<Integer>)this.register(new Setting("TopGreen", (Object)150, (Object)0, (Object)255, p0 -> this.setting.getValue() == Page.Main));
        this.topBlue = (Setting<Integer>)this.register(new Setting("TopBlue", (Object)190, (Object)0, (Object)255, p0 -> this.setting.getValue() == Page.Main));
        this.topAlpha = (Setting<Integer>)this.register(new Setting("TopAlpha", (Object)100, (Object)0, (Object)255, p0 -> this.setting.getValue() == Page.Main));
        this.centerAAA = (Setting<Boolean>)this.register(new Setting("TitleCenter", (Object)false, p0 -> this.setting.getValue() == Page.Misc));
        this.openCloseChange = (Setting<Boolean>)this.register(new Setting("Open/Close", (Object)true, p0 -> this.setting.getValue() == Page.Misc));
        this.open = (Setting<String>)this.register(new Setting("Open:", (Object)"+", p0 -> this.setting.getValue() == Page.Misc && (boolean)this.openCloseChange.getValue()).setRenderName(true));
        this.close = (Setting<String>)this.register(new Setting("Close:", (Object)"-", p0 -> this.setting.getValue() == Page.Misc && (boolean)this.openCloseChange.getValue()).setRenderName(true));
        this.moduleButton = (Setting<String>)this.register(new Setting("Buttons:", (Object)"", p0 -> this.setting.getValue() == Page.Misc && !(boolean)this.openCloseChange.getValue()).setRenderName(true));
        this.futuregear = (Setting<Boolean>)this.register(new Setting("FutureGear", (Object)false, p0 -> this.setting.getValue() == Page.Misc));
        this.toparrow = (Setting<Boolean>)this.register(new Setting("TopArrow", (Object)false, p0 -> this.setting.getValue() == Page.Misc));
        this.customFov = (Setting<Boolean>)this.register(new Setting("CustomFov", (Object)false, p0 -> this.setting.getValue() == Page.Misc));
        this.fov = (Setting<Float>)this.register(new Setting("Fov", (Object)150.0f, (Object)(-180.0f), (Object)180.0f, p0 -> (boolean)this.customFov.getValue() && this.setting.getValue() == Page.Misc));
        this.ARed = (Setting<Integer>)this.register(new Setting("StringGearRed", (Object)160, (Object)0, (Object)255, p0 -> this.setting.getValue() == Page.Misc));
        this.AGreen = (Setting<Integer>)this.register(new Setting("StringGearGreen", (Object)160, (Object)0, (Object)255, p0 -> this.setting.getValue() == Page.Misc));
        this.ABlue = (Setting<Integer>)this.register(new Setting("StringGearBlue", (Object)160, (Object)0, (Object)255, p0 -> this.setting.getValue() == Page.Misc));
        this.sideSettings = (Setting<Boolean>)this.register(new Setting("SideSetting", (Object)false, p0 -> this.setting.getValue() == Page.Line));
        this.sidered = (Setting<Integer>)this.register(new Setting("SideRed", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.sideSettings.getValue() && this.setting.getValue() == Page.Line));
        this.sidegreen = (Setting<Integer>)this.register(new Setting("SideGreen", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.sideSettings.getValue() && this.setting.getValue() == Page.Line));
        this.sideblue = (Setting<Integer>)this.register(new Setting("SideBlue", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.sideSettings.getValue() && this.setting.getValue() == Page.Line));
        this.sidealpha = (Setting<Integer>)this.register(new Setting("SideAlpha", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.sideSettings.getValue() && this.setting.getValue() == Page.Line));
        this.frameSettings = (Setting<Boolean>)this.register(new Setting("FrameSetting", (Object)false, p0 -> this.setting.getValue() == Page.Line));
        this.frameRed = (Setting<Integer>)this.register(new Setting("FrameRed", (Object)160, (Object)0, (Object)255, p0 -> (boolean)this.frameSettings.getValue() && this.setting.getValue() == Page.Line));
        this.frameGreen = (Setting<Integer>)this.register(new Setting("FrameGreen", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.frameSettings.getValue() && this.setting.getValue() == Page.Line));
        this.frameBlue = (Setting<Integer>)this.register(new Setting("FrameBlue", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.frameSettings.getValue() && this.setting.getValue() == Page.Line));
        this.frameAlpha = (Setting<Integer>)this.register(new Setting("FrameAlpha", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.frameSettings.getValue() && this.setting.getValue() == Page.Line));
        this.outline = (Setting<Boolean>)this.register(new Setting("OutlineOld", (Object)false, p0 -> this.setting.getValue() == Page.Line));
        this.outline2 = (Setting<Boolean>)this.register(new Setting("Outline2", (Object)false, p0 -> this.setting.getValue() == Page.Line));
        this.outlineThickness = (Setting<Float>)this.register(new Setting("LineThickness", (Object)1.0f, (Object)0.5f, (Object)5.0f, p0 -> (boolean)this.outline2.getValue() && this.setting.getValue() == Page.Line));
        this.o_red = (Setting<Integer>)this.register(new Setting("OutlineRed", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.outline2.getValue() && this.setting.getValue() == Page.Line));
        this.o_green = (Setting<Integer>)this.register(new Setting("OutlineGreen", (Object)0, (Object)0, (Object)255, p0 -> (boolean)this.outline2.getValue() && this.setting.getValue() == Page.Line));
        this.o_blue = (Setting<Integer>)this.register(new Setting("OutlineBlue", (Object)0, (Object)0, (Object)255, p0 -> (boolean)this.outline2.getValue() && this.setting.getValue() == Page.Line));
        this.o_alpha = (Setting<Integer>)this.register(new Setting("OutlineAlpha", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.outline2.getValue() && this.setting.getValue() == Page.Line));
        this.moduleOutline = (Setting<Boolean>)this.register(new Setting("Module Outline", (Object)true, p0 -> this.setting.getValue() == Page.Line));
        this.moRed = (Setting<Integer>)this.register(new Setting("Module OutlineRed", (Object)0, (Object)0, (Object)255, p0 -> (boolean)this.moduleOutline.getValue() && this.setting.getValue() == Page.Line));
        this.moGreen = (Setting<Integer>)this.register(new Setting("Module OutlineGreen", (Object)0, (Object)0, (Object)255, p0 -> (boolean)this.moduleOutline.getValue() && this.setting.getValue() == Page.Line));
        this.moBlue = (Setting<Integer>)this.register(new Setting("Module OutlineBlue", (Object)0, (Object)0, (Object)255, p0 -> (boolean)this.moduleOutline.getValue() && this.setting.getValue() == Page.Line));
        this.moAlpha = (Setting<Integer>)this.register(new Setting("Module OutlineAlpha", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.moduleOutline.getValue() && this.setting.getValue() == Page.Line));
        this.snowing = (Setting<Boolean>)this.register(new Setting("Snowing", (Object)true, p0 -> this.setting.getValue() == Page.BackGround));
        this.dark = (Setting<Boolean>)this.register(new Setting("BackGround Dark", (Object)false, p0 -> this.setting.getValue() == Page.BackGround));
        this.gradiant = (Setting<Boolean>)this.register(new Setting("Gradiant", (Object)false, p0 -> this.setting.getValue() == Page.BackGround));
        this.gradiantred = (Setting<Integer>)this.register(new Setting("GradiantRed", (Object)36, (Object)0, (Object)255, p0 -> (boolean)this.gradiant.getValue() && this.setting.getValue() == Page.BackGround));
        this.gradiantgreen = (Setting<Integer>)this.register(new Setting("GradiantGreen", (Object)150, (Object)0, (Object)255, p0 -> (boolean)this.gradiant.getValue() && this.setting.getValue() == Page.BackGround));
        this.gradiantblue = (Setting<Integer>)this.register(new Setting("GradiantBlue", (Object)190, (Object)0, (Object)255, p0 -> (boolean)this.gradiant.getValue() && this.setting.getValue() == Page.BackGround));
        this.gradiantalpha = (Setting<Integer>)this.register(new Setting("GradiantAlpha", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.gradiant.getValue() && this.setting.getValue() == Page.BackGround));
        this.particles = (Setting<Boolean>)this.register(new Setting("Particles", (Object)true, p0 -> this.setting.getValue() == Page.BackGround));
        this.particleLength = (Setting<Integer>)this.register(new Setting("ParticleLength", (Object)150, (Object)0, (Object)300, p0 -> (boolean)this.particles.getValue() && this.setting.getValue() == Page.BackGround));
        this.particlered = (Setting<Integer>)this.register(new Setting("ParticleRed", (Object)36, (Object)0, (Object)255, p0 -> (boolean)this.particles.getValue() && this.setting.getValue() == Page.BackGround));
        this.particlegreen = (Setting<Integer>)this.register(new Setting("ParticleGreen", (Object)150, (Object)0, (Object)255, p0 -> (boolean)this.particles.getValue() && this.setting.getValue() == Page.BackGround));
        this.particleblue = (Setting<Integer>)this.register(new Setting("ParticleBlue", (Object)190, (Object)0, (Object)255, p0 -> (boolean)this.particles.getValue() && this.setting.getValue() == Page.BackGround));
        this.textRed = (Setting<Integer>)this.register(new Setting("EnabledTextRed", (Object)255, (Object)0, (Object)255, p0 -> this.setting.getValue() == Page.Text));
        this.textGreen = (Setting<Integer>)this.register(new Setting("EnabledTextGreen", (Object)255, (Object)0, (Object)255, p0 -> this.setting.getValue() == Page.Text));
        this.textBlue = (Setting<Integer>)this.register(new Setting("EnabledTextBlue", (Object)255, (Object)0, (Object)255, p0 -> this.setting.getValue() == Page.Text));
        this.textAlpha = (Setting<Integer>)this.register(new Setting("EnabledTextAlpha", (Object)255, (Object)0, (Object)255, p0 -> this.setting.getValue() == Page.Text));
        this.textRed2 = (Setting<Integer>)this.register(new Setting("DisabledTextRed", (Object)160, (Object)0, (Object)255, p0 -> this.setting.getValue() == Page.Text));
        this.textGreen2 = (Setting<Integer>)this.register(new Setting("DisabledTextGreen", (Object)160, (Object)0, (Object)255, p0 -> this.setting.getValue() == Page.Text));
        this.textBlue2 = (Setting<Integer>)this.register(new Setting("DisabledTextBlue", (Object)160, (Object)0, (Object)255, p0 -> this.setting.getValue() == Page.Text));
        this.textAlpha2 = (Setting<Integer>)this.register(new Setting("DisabledTextAlpha", (Object)255, (Object)0, (Object)255, p0 -> this.setting.getValue() == Page.Text));
        this.PRed = (Setting<Boolean>)this.register(new Setting("PresetRed", (Object)false, p0 -> this.setting.getValue() == Page.Preset));
        this.PBlack = (Setting<Boolean>)this.register(new Setting("PresetBlack", (Object)false, p0 -> this.setting.getValue() == Page.Preset));
        this.PPhobos = (Setting<Boolean>)this.register(new Setting("PresetDefault", (Object)false, p0 -> this.setting.getValue() == Page.Preset));
        this.setInstance();
    }
    
    @SubscribeEvent
    public void onSettingChange(final ClientEvent clientEvent) {
        if (clientEvent.getStage() == 2 && clientEvent.getSetting().getFeature().equals(this)) {
            if (clientEvent.getSetting().equals(this.prefix)) {
                LuigiHack.commandManager.setPrefix((String)this.prefix.getPlannedValue());
                Command.sendMessage(String.valueOf(new StringBuilder().append("Prefix set to §a").append(LuigiHack.commandManager.getPrefix())));
            }
            LuigiHack.colorManager.setColor((int)this.red.getPlannedValue(), (int)this.green.getPlannedValue(), (int)this.blue.getPlannedValue(), (int)this.hoverAlpha.getPlannedValue());
        }
    }
    
    private void setInstance() {
        ClickGui.INSTANCE = this;
    }
    
    @Override
    public void onLoad() {
        if (this.colorSync.getValue()) {
            LuigiHack.colorManager.setColor(Colors.INSTANCE.getCurrentColor().getRed(), Colors.INSTANCE.getCurrentColor().getGreen(), Colors.INSTANCE.getCurrentColor().getBlue(), (int)this.hoverAlpha.getValue());
        }
        else {
            LuigiHack.colorManager.setColor((int)this.red.getValue(), (int)this.green.getValue(), (int)this.blue.getValue(), (int)this.hoverAlpha.getValue());
        }
        LuigiHack.commandManager.setPrefix((String)this.prefix.getValue());
    }
    
    @Override
    public void onDisable() {
        if (ClickGui.mc.currentScreen instanceof LuigiGui) {
            Util.mc.displayGuiScreen((GuiScreen)null);
        }
    }
    
    @Override
    public void onTick() {
        if (!(ClickGui.mc.currentScreen instanceof LuigiGui)) {
            this.disable();
        }
        if (this.PRed.getValue()) {
            this.colorSync.setValue((Object)false);
            this.TcolorSync.setValue((Object)false);
            this.red.setValue((Object)225);
            this.green.setValue((Object)0);
            this.blue.setValue((Object)0);
            this.hoverAlpha.setValue((Object)130);
            this.b_red.setValue((Object)0);
            this.b_green.setValue((Object)0);
            this.b_blue.setValue((Object)0);
            this.b_alpha.setValue((Object)85);
            this.d_red.setValue((Object)0);
            this.d_green.setValue((Object)0);
            this.d_blue.setValue((Object)0);
            this.d_alpha.setValue((Object)31);
            this.alpha.setValue((Object)240);
            this.topRed.setValue((Object)255);
            this.topGreen.setValue((Object)0);
            this.topBlue.setValue((Object)0);
            this.topAlpha.setValue((Object)100);
            this.centerAAA.setValue((Object)true);
            this.openCloseChange.setValue((Object)false);
            this.open.setValue((Object)"+");
            this.close.setValue((Object)"-");
            this.moduleButton.setValue((Object)"");
            this.futuregear.setValue((Object)false);
            this.toparrow.setValue((Object)false);
            this.customFov.setValue((Object)false);
            this.ARed.setValue((Object)160);
            this.AGreen.setValue((Object)160);
            this.ABlue.setValue((Object)160);
            this.sideSettings.setValue((Object)false);
            this.sidered.setValue((Object)255);
            this.sidegreen.setValue((Object)0);
            this.sideblue.setValue((Object)0);
            this.sidealpha.setValue((Object)255);
            this.frameSettings.setValue((Object)false);
            this.frameRed.setValue((Object)255);
            this.frameGreen.setValue((Object)0);
            this.frameBlue.setValue((Object)0);
            this.frameAlpha.setValue((Object)255);
            this.outline.setValue((Object)false);
            this.outline2.setValue((Object)true);
            this.outlineThickness.setValue((Object)1.0f);
            this.o_red.setValue((Object)255);
            this.o_green.setValue((Object)0);
            this.o_blue.setValue((Object)0);
            this.o_alpha.setValue((Object)255);
            this.moduleOutline.setValue((Object)false);
            this.moRed.setValue((Object)0);
            this.moGreen.setValue((Object)0);
            this.moBlue.setValue((Object)0);
            this.moAlpha.setValue((Object)255);
            this.snowing.setValue((Object)true);
            this.dark.setValue((Object)false);
            this.gradiant.setValue((Object)true);
            this.gradiantred.setValue((Object)255);
            this.gradiantgreen.setValue((Object)0);
            this.gradiantblue.setValue((Object)0);
            this.gradiantalpha.setValue((Object)255);
            this.particles.setValue((Object)true);
            this.particleLength.setValue((Object)86);
            this.particlered.setValue((Object)255);
            this.particlegreen.setValue((Object)0);
            this.particleblue.setValue((Object)0);
            this.textRed.setValue((Object)255);
            this.textGreen.setValue((Object)255);
            this.textBlue.setValue((Object)255);
            this.textAlpha.setValue((Object)255);
            this.textRed2.setValue((Object)255);
            this.textGreen2.setValue((Object)255);
            this.textBlue2.setValue((Object)255);
            this.textAlpha2.setValue((Object)255);
            if (CustomFont.getInstance().isOff()) {
                CustomFont.getInstance().enable();
            }
            CustomFont.getInstance().fontName.setValue((Object)"Dialog");
            CustomFont.getInstance().fontSize.setValue((Object)18);
            CustomFont.getInstance().fontStyle.setValue((Object)0);
            CustomFont.getInstance().antiAlias.setValue((Object)true);
            CustomFont.getInstance().fractionalMetrics.setValue((Object)true);
            CustomFont.getInstance().shadow.setValue((Object)false);
            this.PRed.setValue((Object)false);
        }
        if (this.PBlack.getValue()) {
            this.colorSync.setValue((Object)false);
            this.TcolorSync.setValue((Object)false);
            this.red.setValue((Object)255);
            this.green.setValue((Object)36);
            this.blue.setValue((Object)130);
            this.hoverAlpha.setValue((Object)130);
            this.b_red.setValue((Object)14);
            this.b_green.setValue((Object)14);
            this.b_blue.setValue((Object)14);
            this.b_alpha.setValue((Object)255);
            this.d_red.setValue((Object)26);
            this.d_green.setValue((Object)48);
            this.d_blue.setValue((Object)90);
            this.d_alpha.setValue((Object)9);
            this.alpha.setValue((Object)240);
            this.topRed.setValue((Object)31);
            this.topGreen.setValue((Object)31);
            this.topBlue.setValue((Object)31);
            this.topAlpha.setValue((Object)255);
            this.centerAAA.setValue((Object)false);
            this.openCloseChange.setValue((Object)false);
            this.open.setValue((Object)"+");
            this.close.setValue((Object)"-");
            this.moduleButton.setValue((Object)"");
            this.futuregear.setValue((Object)false);
            this.toparrow.setValue((Object)false);
            this.customFov.setValue((Object)false);
            this.ARed.setValue((Object)255);
            this.AGreen.setValue((Object)255);
            this.ABlue.setValue((Object)255);
            this.sideSettings.setValue((Object)false);
            this.sidered.setValue((Object)255);
            this.sidegreen.setValue((Object)0);
            this.sideblue.setValue((Object)0);
            this.sidealpha.setValue((Object)255);
            this.frameSettings.setValue((Object)false);
            this.frameRed.setValue((Object)255);
            this.frameGreen.setValue((Object)0);
            this.frameBlue.setValue((Object)0);
            this.frameAlpha.setValue((Object)255);
            this.outline.setValue((Object)false);
            this.outline2.setValue((Object)false);
            this.outlineThickness.setValue((Object)1.0f);
            this.o_red.setValue((Object)255);
            this.o_green.setValue((Object)0);
            this.o_blue.setValue((Object)0);
            this.o_alpha.setValue((Object)255);
            this.moduleOutline.setValue((Object)false);
            this.moRed.setValue((Object)0);
            this.moGreen.setValue((Object)0);
            this.moBlue.setValue((Object)0);
            this.moAlpha.setValue((Object)255);
            this.snowing.setValue((Object)false);
            this.dark.setValue((Object)false);
            this.gradiant.setValue((Object)true);
            this.gradiantred.setValue((Object)156);
            this.gradiantgreen.setValue((Object)153);
            this.gradiantblue.setValue((Object)151);
            this.gradiantalpha.setValue((Object)255);
            this.particles.setValue((Object)false);
            this.particleLength.setValue((Object)150);
            this.particlered.setValue((Object)36);
            this.particlegreen.setValue((Object)150);
            this.particleblue.setValue((Object)190);
            this.textRed.setValue((Object)255);
            this.textGreen.setValue((Object)255);
            this.textBlue.setValue((Object)255);
            this.textAlpha.setValue((Object)255);
            this.textRed2.setValue((Object)255);
            this.textGreen2.setValue((Object)255);
            this.textBlue2.setValue((Object)255);
            this.textAlpha2.setValue((Object)255);
            if (CustomFont.getInstance().isOff()) {
                CustomFont.getInstance().enable();
            }
            CustomFont.getInstance().fontName.setValue((Object)"Tahoma");
            CustomFont.getInstance().fontSize.setValue((Object)18);
            CustomFont.getInstance().fontStyle.setValue((Object)0);
            CustomFont.getInstance().antiAlias.setValue((Object)true);
            CustomFont.getInstance().fractionalMetrics.setValue((Object)true);
            CustomFont.getInstance().shadow.setValue((Object)false);
            this.PBlack.setValue((Object)false);
        }
        if (this.PPhobos.getValue()) {
            this.colorSync.setValue((Object)false);
            this.TcolorSync.setValue((Object)false);
            this.red.setValue((Object)36);
            this.green.setValue((Object)150);
            this.blue.setValue((Object)190);
            this.hoverAlpha.setValue((Object)130);
            this.b_red.setValue((Object)0);
            this.b_green.setValue((Object)0);
            this.b_blue.setValue((Object)0);
            this.b_alpha.setValue((Object)75);
            this.d_red.setValue((Object)127);
            this.d_green.setValue((Object)127);
            this.d_blue.setValue((Object)127);
            this.d_alpha.setValue((Object)0);
            this.alpha.setValue((Object)240);
            this.topRed.setValue((Object)36);
            this.topGreen.setValue((Object)150);
            this.topBlue.setValue((Object)190);
            this.topAlpha.setValue((Object)100);
            this.centerAAA.setValue((Object)false);
            this.openCloseChange.setValue((Object)true);
            this.open.setValue((Object)"+");
            this.close.setValue((Object)"-");
            this.moduleButton.setValue((Object)"");
            this.futuregear.setValue((Object)false);
            this.toparrow.setValue((Object)false);
            this.customFov.setValue((Object)false);
            this.ARed.setValue((Object)160);
            this.AGreen.setValue((Object)160);
            this.ABlue.setValue((Object)160);
            this.sideSettings.setValue((Object)false);
            this.sidered.setValue((Object)255);
            this.sidegreen.setValue((Object)255);
            this.sideblue.setValue((Object)255);
            this.sidealpha.setValue((Object)255);
            this.frameSettings.setValue((Object)false);
            this.frameRed.setValue((Object)160);
            this.frameGreen.setValue((Object)255);
            this.frameBlue.setValue((Object)255);
            this.frameAlpha.setValue((Object)255);
            this.outline.setValue((Object)false);
            this.outline2.setValue((Object)false);
            this.outlineThickness.setValue((Object)1.0f);
            this.o_red.setValue((Object)255);
            this.o_green.setValue((Object)0);
            this.o_blue.setValue((Object)0);
            this.o_alpha.setValue((Object)255);
            this.moduleOutline.setValue((Object)true);
            this.moRed.setValue((Object)0);
            this.moGreen.setValue((Object)0);
            this.moBlue.setValue((Object)0);
            this.moAlpha.setValue((Object)255);
            this.snowing.setValue((Object)true);
            this.dark.setValue((Object)false);
            this.gradiant.setValue((Object)false);
            this.gradiantred.setValue((Object)36);
            this.gradiantgreen.setValue((Object)150);
            this.gradiantblue.setValue((Object)190);
            this.gradiantalpha.setValue((Object)255);
            this.particles.setValue((Object)true);
            this.particleLength.setValue((Object)150);
            this.particlered.setValue((Object)36);
            this.particlegreen.setValue((Object)150);
            this.particleblue.setValue((Object)190);
            this.textRed.setValue((Object)255);
            this.textGreen.setValue((Object)255);
            this.textBlue.setValue((Object)255);
            this.textAlpha.setValue((Object)255);
            this.textRed2.setValue((Object)160);
            this.textGreen2.setValue((Object)160);
            this.textBlue2.setValue((Object)160);
            this.textAlpha2.setValue((Object)255);
            if (CustomFont.getInstance().isOff()) {
                CustomFont.getInstance().enable();
            }
            CustomFont.getInstance().fontName.setValue((Object)"Arial");
            CustomFont.getInstance().fontSize.setValue((Object)18);
            CustomFont.getInstance().fontStyle.setValue((Object)0);
            CustomFont.getInstance().antiAlias.setValue((Object)true);
            CustomFont.getInstance().fractionalMetrics.setValue((Object)true);
            CustomFont.getInstance().shadow.setValue((Object)false);
            this.PPhobos.setValue((Object)false);
        }
    }
    
    static {
        ClickGui.INSTANCE = new ClickGui();
    }
    
    private enum Page
    {
        BackGround, 
        Line, 
        Preset, 
        Main, 
        Text, 
        Misc;
    }
    
    public enum rainbowMode
    {
        Static, 
        Sideway;
    }
    
    public enum rainbowModeArray
    {
        Up, 
        Static;
    }
}
