//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.render;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import java.awt.*;
import java.util.concurrent.atomic.*;
import net.minecraft.entity.player.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.renderer.*;
import me.snow.luigihack.api.shaders.impl.fill.*;
import me.snow.luigihack.api.shaders.impl.outline.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.entity.*;
import java.util.function.*;
import net.minecraft.entity.item.*;
import net.minecraft.world.*;

public class Shaders extends Module
{
    private /* synthetic */ Setting<Crystal1> crystal;
    public /* synthetic */ Setting<Integer> secondColorImgFillalpha;
    public /* synthetic */ Setting<Integer> colorESPblue;
    public /* synthetic */ Setting<Float> PI;
    public /* synthetic */ Setting<Float> stepSizeOutline;
    public /* synthetic */ Setting<Float> greenOutline;
    public /* synthetic */ Setting<Float> duplicateFill;
    public /* synthetic */ Setting<Integer> thirdColorImgFIllgreen;
    public /* synthetic */ Setting<Float> zoomFill;
    private /* synthetic */ Setting<Boolean> Fpreset;
    public /* synthetic */ Setting<Integer> alphaValue;
    public /* synthetic */ Setting<Float> blueOutline;
    public /* synthetic */ Setting<Float> radOutline;
    public /* synthetic */ Setting<Integer> maxEntities;
    public /* synthetic */ boolean renderCape;
    public /* synthetic */ Setting<Float> volumStepsFill;
    public /* synthetic */ Setting<Integer> BSTARTOutline;
    public /* synthetic */ Setting<Float> distfadingFill;
    public /* synthetic */ Setting<Integer> colorImgFillalpha;
    public /* synthetic */ Setting<Integer> colorImgOutlinegreen;
    public /* synthetic */ Setting<Integer> NUM_OCTAVESFill;
    public /* synthetic */ Setting<Integer> colorImgOutlineblue;
    public /* synthetic */ Setting<Integer> thirdColorImgOutlinered;
    public /* synthetic */ Setting<Integer> RSTARTFill;
    public /* synthetic */ Setting<Float> titleFill;
    public /* synthetic */ Setting<Integer> volumStepsOutline;
    public /* synthetic */ Setting<Float> moreGradientFill;
    public /* synthetic */ Setting<Float> saturationOutline;
    public /* synthetic */ Setting<Integer> iterationsFill;
    public /* synthetic */ Setting<Integer> thirdColorImgOutlinealpha;
    public /* synthetic */ Setting<Float> alphaFill;
    public /* synthetic */ Setting<Float> saturationFill;
    public /* synthetic */ Setting<Float> moreGradientOutline;
    public /* synthetic */ boolean renderTags;
    public /* synthetic */ Setting<Integer> redFill;
    public /* synthetic */ Setting<Integer> redOutline;
    public /* synthetic */ Setting<Integer> colorImgFillgreen;
    public /* synthetic */ Setting<Integer> secondColorImgFillblue;
    public /* synthetic */ Setting<Float> creepyFill;
    public /* synthetic */ Setting<Float> maxRange;
    public /* synthetic */ Setting<Integer> colorESPred;
    private /* synthetic */ Setting<Boolean> fadeOutline;
    public /* synthetic */ Setting<Integer> thirdColorImgFIllalpha;
    public /* synthetic */ Setting<Integer> colorESPgreen;
    private /* synthetic */ Setting<Boolean> rangeCheck;
    public /* synthetic */ Setting<Integer> colorImgOutlinealpha;
    private /* synthetic */ Setting<fillShadermode> fillShader;
    public /* synthetic */ Setting<Integer> WaveLenghtOutline;
    private /* synthetic */ Setting<Mob1> mob;
    public /* synthetic */ Setting<Integer> MaxIterOutline;
    public /* synthetic */ Setting<Float> creepyOutline;
    public /* synthetic */ Setting<Float> PIOutline;
    public /* synthetic */ Setting<Float> tauFill;
    public /* synthetic */ Setting<Integer> iterationsOutline;
    public /* synthetic */ Setting<Integer> colorImgFillblue;
    public /* synthetic */ Setting<Integer> GSTARTOutline;
    public /* synthetic */ Setting<Float> speedOutline;
    public /* synthetic */ Setting<Float> greenFill;
    public /* synthetic */ Setting<Integer> colorImgOutlinered;
    private /* synthetic */ Setting<Player1> player;
    public /* synthetic */ Setting<Integer> RSTARTOutline;
    public /* synthetic */ Setting<Float> titleOutline;
    public /* synthetic */ Setting<Float> distfadingOutline;
    public /* synthetic */ Setting<Float> radius;
    public /* synthetic */ Setting<Integer> GSTARTFill;
    public /* synthetic */ Setting<Integer> secondColorImgFillgreen;
    public /* synthetic */ Setting<Integer> WaveLenghtFIll;
    public /* synthetic */ Setting<Integer> thirdColorImgOutlinegreen;
    public /* synthetic */ Setting<Float> blueFill;
    public /* synthetic */ Setting<Integer> BSTARTFIll;
    private /* synthetic */ Setting<Boolean> default1;
    private /* synthetic */ Setting<Boolean> fadeFill;
    public /* synthetic */ Setting<Float> rad;
    public /* synthetic */ Setting<Integer> colorImgFillred;
    public /* synthetic */ Setting<Float> quality;
    public /* synthetic */ Setting<Integer> thirdColorImgFIllblue;
    public /* synthetic */ Setting<Float> stepSizeFill;
    public /* synthetic */ Setting<Integer> thirdColorImgOutlineblue;
    public /* synthetic */ Setting<Integer> NUM_OCTAVESOutline;
    public /* synthetic */ Setting<Integer> MaxIterFill;
    public /* synthetic */ Setting<Float> formuparam2Outline;
    public /* synthetic */ Setting<Float> formuparam2Fill;
    private /* synthetic */ Setting<glowESPmode> glowESP;
    public /* synthetic */ Setting<Integer> secondColorImgFillred;
    public /* synthetic */ Setting<Float> duplicateOutline;
    public /* synthetic */ Setting<Float> zoomOutline;
    public /* synthetic */ Setting<Float> tauOutline;
    public /* synthetic */ Setting<Integer> thirdColorImgFIllred;
    public /* synthetic */ Setting<Float> minRange;
    public /* synthetic */ Setting<Float> alphaOutline;
    public /* synthetic */ Setting<Float> speedFill;
    public /* synthetic */ Setting<Integer> colorESPalpha;
    
    void renderPlayersFill(final float n) {
        (boolean)this.rangeCheck.getValue();
        final double n2 = (float)this.minRange.getValue() * (float)this.minRange.getValue();
        final double n3 = (float)this.maxRange.getValue() * (float)this.maxRange.getValue();
        final AtomicInteger atomicInteger = new AtomicInteger();
        (int)this.maxEntities.getValue();
        try {
            final int n4;
            final boolean b;
            final double n5;
            final double n6;
            final double n7;
            Shaders.mc.world.loadedEntityList.stream().filter(entityPlayerSP -> {
                if (atomicInteger.getAndIncrement() > n4) {
                    return false;
                }
                else {
                    if (entityPlayerSP instanceof EntityPlayer) {
                        if ((this.player.getValue() == Player1.Fill || this.player.getValue() == Player1.Both) && (entityPlayerSP != Shaders.mc.player || Shaders.mc.gameSettings.thirdPersonView != 0)) {
                            return true;
                        }
                    }
                    else if (entityPlayerSP instanceof EntityCreature) {
                        if (this.mob.getValue() == Mob1.Fill || this.mob.getValue() == Mob1.Both) {
                            return true;
                        }
                    }
                    else if (entityPlayerSP instanceof EntityEnderCrystal && (this.crystal.getValue() == Crystal1.Fill || this.crystal.getValue() == Crystal1.Both)) {
                        return true;
                    }
                    return false;
                }
            }).filter(entity2 -> {
                if (!b) {
                    return true;
                }
                else {
                    Shaders.mc.player.getDistanceSq(entity2);
                    return n5 > n6 && n5 < n7;
                }
            }).forEach(entity -> Shaders.mc.getRenderManager().renderEntityStatic(entity, n, true));
        }
        catch (Exception ex) {}
    }
    
    public Shaders() {
        super("Shaders", "Spawns in a fake player", Module.Category.RENDER, true, false, false);
        this.fillShader = (Setting<fillShadermode>)this.register(new Setting("Fill Shader", (Object)fillShadermode.None));
        this.glowESP = (Setting<glowESPmode>)this.register(new Setting("Glow ESP", (Object)glowESPmode.None));
        this.rangeCheck = (Setting<Boolean>)this.register(new Setting("Range Check", (Object)true));
        this.maxRange = (Setting<Float>)this.register(new Setting("Max Range", (Object)35.0f, (Object)10.0f, (Object)100.0f, p0 -> (boolean)this.rangeCheck.getValue()));
        this.minRange = (Setting<Float>)this.register(new Setting("Min range", (Object)0.0f, (Object)0.0f, (Object)5.0f, p0 -> (boolean)this.rangeCheck.getValue()));
        this.crystal = (Setting<Crystal1>)this.register(new Setting("Crystals", (Object)Crystal1.None));
        this.player = (Setting<Player1>)this.register(new Setting("Players", (Object)Player1.None));
        this.mob = (Setting<Mob1>)this.register(new Setting("Mobs", (Object)Mob1.None));
        this.default1 = (Setting<Boolean>)this.register(new Setting("Reset Setting", (Object)false));
        this.Fpreset = (Setting<Boolean>)this.register(new Setting("FutureRainbow Preset", (Object)false));
        this.fadeFill = (Setting<Boolean>)this.register(new Setting("Fade Fill", (Object)false, p0 -> this.fillShader.getValue() == fillShadermode.Astral || this.glowESP.getValue() == glowESPmode.Astral));
        this.fadeOutline = (Setting<Boolean>)this.register(new Setting("FadeOL Fill", (Object)false, p0 -> this.fillShader.getValue() == fillShadermode.Astral || this.glowESP.getValue() == glowESPmode.Astral));
        this.duplicateOutline = (Setting<Float>)this.register(new Setting("duplicateOutline", (Object)1.0f, (Object)0.0f, (Object)20.0f));
        this.duplicateFill = (Setting<Float>)this.register(new Setting("Duplicate Fill", (Object)1.0f, (Object)0.0f, (Object)5.0f));
        this.speedOutline = (Setting<Float>)this.register(new Setting("Speed Outline", (Object)10.0f, (Object)1.0f, (Object)100.0f));
        this.speedFill = (Setting<Float>)this.register(new Setting("Speed Fill", (Object)10.0f, (Object)1.0f, (Object)100.0f));
        this.quality = (Setting<Float>)this.register(new Setting("Shader Quality", (Object)0.6f, (Object)0.0f, (Object)20.0f));
        this.radius = (Setting<Float>)this.register(new Setting("Shader Radius", (Object)1.7f, (Object)0.0f, (Object)5.0f));
        this.rad = (Setting<Float>)this.register(new Setting("RAD Fill", (Object)0.75f, (Object)0.0f, (Object)5.0f, p0 -> this.fillShader.getValue() == fillShadermode.Circle));
        this.PI = (Setting<Float>)this.register(new Setting("PI Fill", (Object)3.1415927f, (Object)0.0f, (Object)10.0f, p0 -> this.fillShader.getValue() == fillShadermode.Circle));
        this.saturationFill = (Setting<Float>)this.register(new Setting("saturation", (Object)0.4f, (Object)0.0f, (Object)3.0f, p0 -> this.fillShader.getValue() == fillShadermode.Astral));
        this.distfadingFill = (Setting<Float>)this.register(new Setting("distfading", (Object)0.56f, (Object)0.0f, (Object)1.0f, p0 -> this.fillShader.getValue() == fillShadermode.Astral));
        this.titleFill = (Setting<Float>)this.register(new Setting("Tile", (Object)0.45f, (Object)0.0f, (Object)1.3f, p0 -> this.fillShader.getValue() == fillShadermode.Astral));
        this.stepSizeFill = (Setting<Float>)this.register(new Setting("Step Size", (Object)0.2f, (Object)0.0f, (Object)0.7f, p0 -> this.fillShader.getValue() == fillShadermode.Astral));
        this.volumStepsFill = (Setting<Float>)this.register(new Setting("Volum Steps", (Object)10.0f, (Object)0.0f, (Object)10.0f, p0 -> this.fillShader.getValue() == fillShadermode.Astral));
        this.zoomFill = (Setting<Float>)this.register(new Setting("Zoom", (Object)3.9f, (Object)0.0f, (Object)20.0f, p0 -> this.fillShader.getValue() == fillShadermode.Astral));
        this.formuparam2Fill = (Setting<Float>)this.register(new Setting("formuparam2", (Object)0.89f, (Object)0.0f, (Object)1.5f, p0 -> this.fillShader.getValue() == fillShadermode.Astral));
        this.saturationOutline = (Setting<Float>)this.register(new Setting("saturation", (Object)0.4f, (Object)0.0f, (Object)3.0f, p0 -> this.glowESP.getValue() == glowESPmode.Astral));
        this.maxEntities = (Setting<Integer>)this.register(new Setting("Max Entities", (Object)100, (Object)10, (Object)500));
        this.iterationsFill = (Setting<Integer>)this.register(new Setting("Iteration", (Object)4, (Object)3, (Object)20, p0 -> this.fillShader.getValue() == fillShadermode.Astral));
        this.MaxIterFill = (Setting<Integer>)this.register(new Setting("Max Iter", (Object)5, (Object)0, (Object)30, p0 -> this.fillShader.getValue() == fillShadermode.Aqua));
        this.NUM_OCTAVESFill = (Setting<Integer>)this.register(new Setting("NUM_OCTAVES", (Object)5, (Object)1, (Object)30, p0 -> this.fillShader.getValue() == fillShadermode.Smoke));
        this.BSTARTFIll = (Setting<Integer>)this.register(new Setting("BSTART", (Object)0, (Object)0, (Object)1000, p0 -> this.fillShader.getValue() == fillShadermode.RainbowCube));
        this.GSTARTFill = (Setting<Integer>)this.register(new Setting("GSTART", (Object)0, (Object)0, (Object)1000, p0 -> this.fillShader.getValue() == fillShadermode.RainbowCube));
        this.RSTARTFill = (Setting<Integer>)this.register(new Setting("RSTART", (Object)0, (Object)0, (Object)1000, p0 -> this.fillShader.getValue() == fillShadermode.RainbowCube));
        this.WaveLenghtFIll = (Setting<Integer>)this.register(new Setting("Wave Lenght", (Object)555, (Object)0, (Object)2000, p0 -> this.fillShader.getValue() == fillShadermode.RainbowCube));
        this.volumStepsOutline = (Setting<Integer>)this.register(new Setting("Volum Steps", (Object)10, (Object)0, (Object)10, p0 -> this.glowESP.getValue() == glowESPmode.Astral));
        this.iterationsOutline = (Setting<Integer>)this.register(new Setting("Iteration", (Object)4, (Object)3, (Object)20, p0 -> this.glowESP.getValue() == glowESPmode.Astral));
        this.MaxIterOutline = (Setting<Integer>)this.register(new Setting("Max Iter", (Object)5, (Object)0, (Object)30, p0 -> this.glowESP.getValue() == glowESPmode.Aqua));
        this.NUM_OCTAVESOutline = (Setting<Integer>)this.register(new Setting("NUM_OCTAVES", (Object)5, (Object)1, (Object)30, p0 -> this.glowESP.getValue() == glowESPmode.Smoke));
        this.BSTARTOutline = (Setting<Integer>)this.register(new Setting("BSTART", (Object)0, (Object)0, (Object)1000, p0 -> this.glowESP.getValue() == glowESPmode.RainbowCube));
        this.GSTARTOutline = (Setting<Integer>)this.register(new Setting("GSTART", (Object)0, (Object)0, (Object)1000, p0 -> this.glowESP.getValue() == glowESPmode.RainbowCube));
        this.RSTARTOutline = (Setting<Integer>)this.register(new Setting("RSTART", (Object)0, (Object)0, (Object)1000, p0 -> this.glowESP.getValue() == glowESPmode.RainbowCube));
        this.alphaValue = (Setting<Integer>)this.register(new Setting("Alpha Outline", (Object)255, (Object)0, (Object)255));
        this.WaveLenghtOutline = (Setting<Integer>)this.register(new Setting("Wave Lenght", (Object)555, (Object)0, (Object)2000, p0 -> this.glowESP.getValue() == glowESPmode.RainbowCube));
        this.redOutline = (Setting<Integer>)this.register(new Setting("Red", (Object)0, (Object)0, (Object)100, p0 -> this.glowESP.getValue() == glowESPmode.Astral));
        this.alphaFill = (Setting<Float>)this.register(new Setting("AlphaF", (Object)1.0f, (Object)0.0f, (Object)1.0f, p0 -> this.fillShader.getValue() == fillShadermode.Astral || this.fillShader.getValue() == fillShadermode.Smoke));
        this.blueFill = (Setting<Float>)this.register(new Setting("BlueF", (Object)0.0f, (Object)0.0f, (Object)5.0f, p0 -> this.fillShader.getValue() == fillShadermode.Astral));
        this.greenFill = (Setting<Float>)this.register(new Setting("GreenF", (Object)0.0f, (Object)0.0f, (Object)5.0f, p0 -> this.fillShader.getValue() == fillShadermode.Astral));
        this.redFill = (Setting<Integer>)this.register(new Setting("RedF", (Object)0, (Object)0, (Object)100, p0 -> this.fillShader.getValue() == fillShadermode.Astral));
        this.tauFill = (Setting<Float>)this.register(new Setting("TAU", (Object)6.2831855f, (Object)0.0f, (Object)20.0f, p0 -> this.fillShader.getValue() == fillShadermode.Aqua));
        this.creepyFill = (Setting<Float>)this.register(new Setting("Creepy", (Object)1.0f, (Object)0.0f, (Object)20.0f, p0 -> this.fillShader.getValue() == fillShadermode.Smoke));
        this.moreGradientFill = (Setting<Float>)this.register(new Setting("More Gradient", (Object)1.0f, (Object)0.0f, (Object)10.0, p0 -> this.fillShader.getValue() == fillShadermode.Smoke));
        this.distfadingOutline = (Setting<Float>)this.register(new Setting("distfading", (Object)0.56f, (Object)0.0f, (Object)1.0f, p0 -> this.glowESP.getValue() == glowESPmode.Astral));
        this.titleOutline = (Setting<Float>)this.register(new Setting("Tile", (Object)0.45f, (Object)0.0f, (Object)1.3f, p0 -> this.glowESP.getValue() == glowESPmode.Astral));
        this.stepSizeOutline = (Setting<Float>)this.register(new Setting("Step Size", (Object)0.19f, (Object)0.0f, (Object)0.7f, p0 -> this.glowESP.getValue() == glowESPmode.Astral));
        this.zoomOutline = (Setting<Float>)this.register(new Setting("Zoom", (Object)3.9f, (Object)0.0f, (Object)20.0f, p0 -> this.glowESP.getValue() == glowESPmode.Astral));
        this.formuparam2Outline = (Setting<Float>)this.register(new Setting("formuparam2", (Object)0.89f, (Object)0.0f, (Object)1.5f, p0 -> this.glowESP.getValue() == glowESPmode.Astral));
        this.alphaOutline = (Setting<Float>)this.register(new Setting("Alpha", (Object)1.0f, (Object)0.0f, (Object)1.0f, p0 -> this.glowESP.getValue() == glowESPmode.Astral || this.glowESP.getValue() == glowESPmode.Gradient));
        this.blueOutline = (Setting<Float>)this.register(new Setting("Blue", (Object)0.0f, (Object)0.0f, (Object)5.0f, p0 -> this.glowESP.getValue() == glowESPmode.Astral));
        this.greenOutline = (Setting<Float>)this.register(new Setting("Green", (Object)0.0f, (Object)0.0f, (Object)5.0f, p0 -> this.glowESP.getValue() == glowESPmode.Astral));
        this.tauOutline = (Setting<Float>)this.register(new Setting("TAU", (Object)6.2831855f, (Object)0.0f, (Object)20.0f, p0 -> this.glowESP.getValue() == glowESPmode.Aqua));
        this.creepyOutline = (Setting<Float>)this.register(new Setting("Gradient Creepy", (Object)1.0f, (Object)0.0f, (Object)20.0f, p0 -> this.glowESP.getValue() == glowESPmode.Gradient));
        this.moreGradientOutline = (Setting<Float>)this.register(new Setting("More Gradient", (Object)1.0f, (Object)0.0f, (Object)10.0f, p0 -> this.glowESP.getValue() == glowESPmode.Gradient));
        this.radOutline = (Setting<Float>)this.register(new Setting("RAD Outline", (Object)0.75f, (Object)0.0f, (Object)5.0f, p0 -> this.glowESP.getValue() == glowESPmode.Circle));
        this.PIOutline = (Setting<Float>)this.register(new Setting("PI Outline", (Object)3.1415927f, (Object)0.0f, (Object)10.0f, p0 -> this.glowESP.getValue() == glowESPmode.Circle));
        this.colorImgOutlinered = (Setting<Integer>)this.register(new Setting("colorImgOutline Red", (Object)0, (Object)0, (Object)255, p0 -> this.fillShader.getValue() == fillShadermode.RainbowCube || this.glowESP.getValue() == glowESPmode.RainbowCube));
        this.colorImgOutlinegreen = (Setting<Integer>)this.register(new Setting("colorImgOutline Green", (Object)0, (Object)0, (Object)255, p0 -> this.fillShader.getValue() == fillShadermode.RainbowCube || this.glowESP.getValue() == glowESPmode.RainbowCube));
        this.colorImgOutlineblue = (Setting<Integer>)this.register(new Setting("colorImgOutline Blue", (Object)0, (Object)0, (Object)255, p0 -> this.fillShader.getValue() == fillShadermode.RainbowCube || this.glowESP.getValue() == glowESPmode.RainbowCube));
        this.colorImgOutlinealpha = (Setting<Integer>)this.register(new Setting("colorImgOutline Alpha", (Object)255, (Object)0, (Object)255, p0 -> this.fillShader.getValue() == fillShadermode.RainbowCube || this.glowESP.getValue() == glowESPmode.RainbowCube));
        this.thirdColorImgOutlinered = (Setting<Integer>)this.register(new Setting("thirdColorImgOutline Red", (Object)0, (Object)0, (Object)255, p0 -> this.fillShader.getValue() == fillShadermode.Smoke || this.glowESP.getValue() == glowESPmode.Smoke));
        this.thirdColorImgOutlinegreen = (Setting<Integer>)this.register(new Setting("thirdColorImgOutline Green", (Object)0, (Object)0, (Object)255, p0 -> this.fillShader.getValue() == fillShadermode.Smoke || this.glowESP.getValue() == glowESPmode.Smoke));
        this.thirdColorImgOutlineblue = (Setting<Integer>)this.register(new Setting("thirdColorImgOutline Blue", (Object)0, (Object)0, (Object)255, p0 -> this.fillShader.getValue() == fillShadermode.Smoke || this.glowESP.getValue() == glowESPmode.Smoke));
        this.thirdColorImgOutlinealpha = (Setting<Integer>)this.register(new Setting("thirdColorImgOutline Alpha", (Object)255, (Object)0, (Object)255, p0 -> this.fillShader.getValue() == fillShadermode.Smoke || this.glowESP.getValue() == glowESPmode.Smoke));
        this.colorESPred = (Setting<Integer>)this.register(new Setting("GlowESP Red", (Object)0, (Object)0, (Object)255));
        this.colorESPgreen = (Setting<Integer>)this.register(new Setting("GlowESP Green", (Object)0, (Object)0, (Object)255));
        this.colorESPblue = (Setting<Integer>)this.register(new Setting("GlowESP Blue", (Object)0, (Object)0, (Object)255));
        this.colorESPalpha = (Setting<Integer>)this.register(new Setting("GlowESP Alpha", (Object)255, (Object)0, (Object)255));
        this.colorImgFillred = (Setting<Integer>)this.register(new Setting("FillShader Red", (Object)0, (Object)0, (Object)255));
        this.colorImgFillgreen = (Setting<Integer>)this.register(new Setting("FillShader Green", (Object)0, (Object)0, (Object)255));
        this.colorImgFillblue = (Setting<Integer>)this.register(new Setting("FillShader Blue", (Object)0, (Object)0, (Object)255));
        this.colorImgFillalpha = (Setting<Integer>)this.register(new Setting("FillShader Alpha", (Object)255, (Object)0, (Object)255));
        this.thirdColorImgFIllred = (Setting<Integer>)this.register(new Setting("SmokeImgFill Red", (Object)0, (Object)0, (Object)255, p0 -> this.fillShader.getValue() == fillShadermode.Smoke || this.glowESP.getValue() == glowESPmode.Smoke));
        this.thirdColorImgFIllgreen = (Setting<Integer>)this.register(new Setting("SmokeImgFill Green", (Object)0, (Object)0, (Object)255, p0 -> this.fillShader.getValue() == fillShadermode.Smoke || this.glowESP.getValue() == glowESPmode.Smoke));
        this.thirdColorImgFIllblue = (Setting<Integer>)this.register(new Setting("SmokeFImgill Blue", (Object)0, (Object)0, (Object)255, p0 -> this.fillShader.getValue() == fillShadermode.Smoke || this.glowESP.getValue() == glowESPmode.Smoke));
        this.thirdColorImgFIllalpha = (Setting<Integer>)this.register(new Setting("SmokeImgFill Alpha", (Object)255, (Object)0, (Object)255, p0 -> this.fillShader.getValue() == fillShadermode.Smoke || this.glowESP.getValue() == glowESPmode.Smoke));
        this.secondColorImgFillred = (Setting<Integer>)this.register(new Setting("SmokeFill Red", (Object)0, (Object)0, (Object)255, p0 -> this.fillShader.getValue() == fillShadermode.Smoke || this.glowESP.getValue() == glowESPmode.Smoke));
        this.secondColorImgFillgreen = (Setting<Integer>)this.register(new Setting("SmokeFill Green", (Object)0, (Object)0, (Object)255, p0 -> this.fillShader.getValue() == fillShadermode.Smoke || this.glowESP.getValue() == glowESPmode.Smoke));
        this.secondColorImgFillblue = (Setting<Integer>)this.register(new Setting("SmokeFill Blue", (Object)0, (Object)0, (Object)255, p0 -> this.fillShader.getValue() == fillShadermode.Smoke || this.glowESP.getValue() == glowESPmode.Smoke));
        this.secondColorImgFillalpha = (Setting<Integer>)this.register(new Setting("SmokeFill Alpha", (Object)255, (Object)0, (Object)255, p0 -> this.fillShader.getValue() == fillShadermode.Smoke || this.glowESP.getValue() == glowESPmode.Smoke));
        this.renderTags = true;
        this.renderCape = true;
    }
    
    @SubscribeEvent
    public void onRenderGameOverlay(final RenderGameOverlayEvent renderGameOverlayEvent) {
        if (renderGameOverlayEvent.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
            if (Shaders.mc.world == null || Shaders.mc.player == null) {
                return;
            }
            GlStateManager.pushMatrix();
            this.renderTags = false;
            this.renderCape = false;
            final Color color = new Color((int)this.colorImgFillred.getValue(), (int)this.colorImgFillgreen.getValue(), (int)this.colorImgFillblue.getValue(), (int)this.colorImgFillalpha.getValue());
            final Color color2 = new Color((int)this.colorESPred.getValue(), (int)this.colorESPgreen.getValue(), (int)this.colorESPblue.getValue(), (int)this.colorESPalpha.getValue());
            final Color color3 = new Color((int)this.secondColorImgFillred.getValue(), (int)this.secondColorImgFillgreen.getValue(), (int)this.secondColorImgFillblue.getValue(), (int)this.secondColorImgFillalpha.getValue());
            final Color color4 = new Color((int)this.thirdColorImgOutlinered.getValue(), (int)this.thirdColorImgOutlinegreen.getValue(), (int)this.thirdColorImgOutlineblue.getValue(), (int)this.thirdColorImgOutlinealpha.getValue());
            final Color color5 = new Color((int)this.thirdColorImgFIllred.getValue(), (int)this.thirdColorImgFIllgreen.getValue(), (int)this.thirdColorImgFIllblue.getValue(), (int)this.thirdColorImgFIllalpha.getValue());
            final Color color6 = new Color((int)this.colorImgOutlinered.getValue(), (int)this.colorImgOutlinegreen.getValue(), (int)this.colorImgOutlineblue.getValue(), (int)this.colorImgOutlinealpha.getValue());
            if (this.glowESP.getValue() != glowESPmode.None && this.fillShader.getValue() != fillShadermode.None) {
                this.getFill();
                switch ((fillShadermode)this.fillShader.getValue()) {
                    case Astral: {
                        FlowShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersFill(renderGameOverlayEvent.getPartialTicks());
                        FlowShader.INSTANCE.stopDraw(Color.WHITE, 1.0f, 1.0f, (float)this.duplicateFill.getValue(), (float)this.redFill.getValue(), (float)this.greenFill.getValue(), (float)this.blueFill.getValue(), (float)this.alphaFill.getValue(), (int)this.iterationsFill.getValue(), (float)this.formuparam2Fill.getValue(), (float)this.zoomFill.getValue(), (float)this.volumStepsFill.getValue(), (float)this.stepSizeFill.getValue(), (float)this.titleFill.getValue(), (float)this.distfadingFill.getValue(), (float)this.saturationFill.getValue(), 0.0f, (int)(((boolean)this.fadeFill.getValue()) ? 1 : 0));
                        FlowShader.INSTANCE.update((double)((float)this.speedFill.getValue() / 1000.0f));
                        break;
                    }
                    case Aqua: {
                        AquaShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersFill(renderGameOverlayEvent.getPartialTicks());
                        AquaShader.INSTANCE.stopDraw(color, 1.0f, 1.0f, (float)this.duplicateFill.getValue(), (int)this.MaxIterFill.getValue(), (double)(float)this.tauFill.getValue());
                        AquaShader.INSTANCE.update((double)((float)this.speedFill.getValue() / 1000.0f));
                        break;
                    }
                    case Smoke: {
                        SmokeShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersFill(renderGameOverlayEvent.getPartialTicks());
                        SmokeShader.INSTANCE.stopDraw(Color.WHITE, 1.0f, 1.0f, (float)this.duplicateFill.getValue(), color, color3, color5, (int)this.NUM_OCTAVESFill.getValue());
                        SmokeShader.INSTANCE.update((double)((float)this.speedFill.getValue() / 1000.0f));
                        break;
                    }
                    case RainbowCube: {
                        RainbowCubeShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersFill(renderGameOverlayEvent.getPartialTicks());
                        RainbowCubeShader.INSTANCE.stopDraw(Color.WHITE, 1.0f, 1.0f, (float)this.duplicateFill.getValue(), color, (int)this.WaveLenghtFIll.getValue(), (int)this.RSTARTFill.getValue(), (int)this.GSTARTFill.getValue(), (int)this.BSTARTFIll.getValue());
                        RainbowCubeShader.INSTANCE.update((double)((float)this.speedFill.getValue() / 1000.0f));
                        break;
                    }
                    case Gradient: {
                        GradientShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersFill(renderGameOverlayEvent.getPartialTicks());
                        GradientShader.INSTANCE.stopDraw(color2, 1.0f, 1.0f, (float)this.duplicateFill.getValue(), (float)this.moreGradientFill.getValue(), (float)this.creepyFill.getValue(), (float)this.alphaFill.getValue(), (int)this.NUM_OCTAVESFill.getValue());
                        GradientShader.INSTANCE.update((double)((float)this.speedFill.getValue() / 1000.0f));
                        break;
                    }
                    case Fill: {
                        FillShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersFill(renderGameOverlayEvent.getPartialTicks());
                        FillShader.INSTANCE.stopDraw(color);
                        FillShader.INSTANCE.update((double)((float)this.speedFill.getValue() / 1000.0f));
                        break;
                    }
                    case Circle: {
                        CircleShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersFill(renderGameOverlayEvent.getPartialTicks());
                        CircleShader.INSTANCE.stopDraw((float)this.duplicateFill.getValue(), color, (Float)this.PI.getValue(), (Float)this.rad.getValue());
                        CircleShader.INSTANCE.update((double)((float)this.speedFill.getValue() / 1000.0f));
                        break;
                    }
                    case Phobos: {
                        PhobosShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersFill(renderGameOverlayEvent.getPartialTicks());
                        PhobosShader.INSTANCE.stopDraw(color, 1.0f, 1.0f, (float)this.duplicateFill.getValue(), (int)this.MaxIterFill.getValue(), (double)(float)this.tauFill.getValue());
                        PhobosShader.INSTANCE.update((double)((float)this.speedFill.getValue() / 1000.0f));
                        break;
                    }
                }
                switch ((glowESPmode)this.glowESP.getValue()) {
                    case Color: {
                        GlowShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersOutline(renderGameOverlayEvent.getPartialTicks());
                        GlowShader.INSTANCE.stopDraw(color2, (float)this.radius.getValue(), (float)this.quality.getValue(), false, (int)this.alphaValue.getValue());
                        break;
                    }
                    case RainbowCube: {
                        RainbowCubeOutlineShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersOutline(renderGameOverlayEvent.getPartialTicks());
                        RainbowCubeOutlineShader.INSTANCE.stopDraw(color2, (float)this.radius.getValue(), (float)this.quality.getValue(), false, (int)this.alphaValue.getValue(), (float)this.duplicateOutline.getValue(), color6, (int)this.WaveLenghtOutline.getValue(), (int)this.RSTARTOutline.getValue(), (int)this.GSTARTOutline.getValue(), (int)this.BSTARTOutline.getValue());
                        RainbowCubeOutlineShader.INSTANCE.update((double)((float)this.speedOutline.getValue() / 1000.0f));
                        break;
                    }
                    case Gradient: {
                        GradientOutlineShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersOutline(renderGameOverlayEvent.getPartialTicks());
                        GradientOutlineShader.INSTANCE.stopDraw(color2, (float)this.radius.getValue(), (float)this.quality.getValue(), false, (int)this.alphaValue.getValue(), (float)this.duplicateOutline.getValue(), (float)this.moreGradientOutline.getValue(), (float)this.creepyOutline.getValue(), (float)this.alphaOutline.getValue(), (int)this.NUM_OCTAVESOutline.getValue());
                        GradientOutlineShader.INSTANCE.update((double)((float)this.speedOutline.getValue() / 1000.0f));
                        break;
                    }
                    case Astral: {
                        AstralOutlineShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersOutline(renderGameOverlayEvent.getPartialTicks());
                        AstralOutlineShader.INSTANCE.stopDraw(color2, (float)this.radius.getValue(), (float)this.quality.getValue(), false, (int)this.alphaValue.getValue(), (float)this.duplicateOutline.getValue(), (float)this.redOutline.getValue(), (float)this.greenOutline.getValue(), (float)this.blueOutline.getValue(), (float)this.alphaOutline.getValue(), (int)this.iterationsOutline.getValue(), (float)this.formuparam2Outline.getValue(), (float)this.zoomOutline.getValue(), (float)(int)this.volumStepsOutline.getValue(), (float)this.stepSizeOutline.getValue(), (float)this.titleOutline.getValue(), (float)this.distfadingOutline.getValue(), (float)this.saturationOutline.getValue(), 0.0f, (int)(((boolean)this.fadeOutline.getValue()) ? 1 : 0));
                        AstralOutlineShader.INSTANCE.update((double)((float)this.speedOutline.getValue() / 1000.0f));
                        break;
                    }
                    case Aqua: {
                        AquaOutlineShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersOutline(renderGameOverlayEvent.getPartialTicks());
                        AquaOutlineShader.INSTANCE.stopDraw(color2, (float)this.radius.getValue(), (float)this.quality.getValue(), false, (int)this.alphaValue.getValue(), (float)this.duplicateOutline.getValue(), (int)this.MaxIterOutline.getValue(), (double)(float)this.tauOutline.getValue());
                        AquaOutlineShader.INSTANCE.update((double)((float)this.speedOutline.getValue() / 1000.0f));
                        break;
                    }
                    case Circle: {
                        CircleOutlineShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersOutline(renderGameOverlayEvent.getPartialTicks());
                        CircleOutlineShader.INSTANCE.stopDraw(color2, (float)this.radius.getValue(), (float)this.quality.getValue(), false, (int)this.alphaValue.getValue(), (float)this.duplicateOutline.getValue(), (float)this.PIOutline.getValue(), (float)this.radOutline.getValue());
                        CircleOutlineShader.INSTANCE.update((double)((float)this.speedOutline.getValue() / 1000.0f));
                        break;
                    }
                    case Smoke: {
                        SmokeOutlineShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersOutline(renderGameOverlayEvent.getPartialTicks());
                        SmokeOutlineShader.INSTANCE.stopDraw(color2, (float)this.radius.getValue(), (float)this.quality.getValue(), false, (int)this.alphaValue.getValue(), (float)this.duplicateOutline.getValue(), color3, color4, (int)this.NUM_OCTAVESOutline.getValue());
                        SmokeOutlineShader.INSTANCE.update((double)((float)this.speedOutline.getValue() / 1000.0f));
                        break;
                    }
                }
            }
            else {
                switch ((glowESPmode)this.glowESP.getValue()) {
                    case Color: {
                        GlowShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersOutline(renderGameOverlayEvent.getPartialTicks());
                        GlowShader.INSTANCE.stopDraw(color2, (float)this.radius.getValue(), (float)this.quality.getValue(), false, (int)this.alphaValue.getValue());
                        break;
                    }
                    case RainbowCube: {
                        RainbowCubeOutlineShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersOutline(renderGameOverlayEvent.getPartialTicks());
                        RainbowCubeOutlineShader.INSTANCE.stopDraw(color2, (float)this.radius.getValue(), (float)this.quality.getValue(), false, (int)this.alphaValue.getValue(), (float)this.duplicateOutline.getValue(), color6, (int)this.WaveLenghtOutline.getValue(), (int)this.RSTARTOutline.getValue(), (int)this.GSTARTOutline.getValue(), (int)this.BSTARTOutline.getValue());
                        RainbowCubeOutlineShader.INSTANCE.update((double)((float)this.speedOutline.getValue() / 1000.0f));
                        break;
                    }
                    case Gradient: {
                        GradientOutlineShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersOutline(renderGameOverlayEvent.getPartialTicks());
                        GradientOutlineShader.INSTANCE.stopDraw(color2, (float)this.radius.getValue(), (float)this.quality.getValue(), false, (int)this.alphaValue.getValue(), (float)this.duplicateOutline.getValue(), (float)this.moreGradientOutline.getValue(), (float)this.creepyOutline.getValue(), (float)this.alphaOutline.getValue(), (int)this.NUM_OCTAVESOutline.getValue());
                        GradientOutlineShader.INSTANCE.update((double)((float)this.speedOutline.getValue() / 1000.0f));
                        break;
                    }
                    case Astral: {
                        AstralOutlineShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersOutline(renderGameOverlayEvent.getPartialTicks());
                        AstralOutlineShader.INSTANCE.stopDraw(color2, (float)this.radius.getValue(), (float)this.quality.getValue(), false, (int)this.alphaValue.getValue(), (float)this.duplicateOutline.getValue(), (float)this.redOutline.getValue(), (float)this.greenOutline.getValue(), (float)this.blueOutline.getValue(), (float)this.alphaOutline.getValue(), (int)this.iterationsOutline.getValue(), (float)this.formuparam2Outline.getValue(), (float)this.zoomOutline.getValue(), (float)(int)this.volumStepsOutline.getValue(), (float)this.stepSizeOutline.getValue(), (float)this.titleOutline.getValue(), (float)this.distfadingOutline.getValue(), (float)this.saturationOutline.getValue(), 0.0f, (int)(((boolean)this.fadeOutline.getValue()) ? 1 : 0));
                        AstralOutlineShader.INSTANCE.update((double)((float)this.speedOutline.getValue() / 1000.0f));
                        break;
                    }
                    case Aqua: {
                        AquaOutlineShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersOutline(renderGameOverlayEvent.getPartialTicks());
                        AquaOutlineShader.INSTANCE.stopDraw(color2, (float)this.radius.getValue(), (float)this.quality.getValue(), false, (int)this.alphaValue.getValue(), (float)this.duplicateOutline.getValue(), (int)this.MaxIterOutline.getValue(), (double)(float)this.tauOutline.getValue());
                        AquaOutlineShader.INSTANCE.update((double)((float)this.speedOutline.getValue() / 1000.0f));
                        break;
                    }
                    case Circle: {
                        CircleOutlineShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersOutline(renderGameOverlayEvent.getPartialTicks());
                        CircleOutlineShader.INSTANCE.stopDraw(color2, (float)this.radius.getValue(), (float)this.quality.getValue(), false, (int)this.alphaValue.getValue(), (float)this.duplicateOutline.getValue(), (float)this.PIOutline.getValue(), (float)this.radOutline.getValue());
                        CircleOutlineShader.INSTANCE.update((double)((float)this.speedOutline.getValue() / 1000.0f));
                        break;
                    }
                    case Smoke: {
                        SmokeOutlineShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersOutline(renderGameOverlayEvent.getPartialTicks());
                        SmokeOutlineShader.INSTANCE.stopDraw(color2, (float)this.radius.getValue(), (float)this.quality.getValue(), false, (int)this.alphaValue.getValue(), (float)this.duplicateOutline.getValue(), color3, color4, (int)this.NUM_OCTAVESOutline.getValue());
                        SmokeOutlineShader.INSTANCE.update((double)((float)this.speedOutline.getValue() / 1000.0f));
                        break;
                    }
                }
                switch ((fillShadermode)this.fillShader.getValue()) {
                    case Astral: {
                        FlowShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersFill(renderGameOverlayEvent.getPartialTicks());
                        FlowShader.INSTANCE.stopDraw(Color.WHITE, 1.0f, 1.0f, (float)this.duplicateFill.getValue(), (float)this.redFill.getValue(), (float)this.greenFill.getValue(), (float)this.blueFill.getValue(), (float)this.alphaFill.getValue(), (int)this.iterationsFill.getValue(), (float)this.formuparam2Fill.getValue(), (float)this.zoomFill.getValue(), (float)this.volumStepsFill.getValue(), (float)this.stepSizeFill.getValue(), (float)this.titleFill.getValue(), (float)this.distfadingFill.getValue(), (float)this.saturationFill.getValue(), 0.0f, (int)(((boolean)this.fadeFill.getValue()) ? 1 : 0));
                        FlowShader.INSTANCE.update((double)((float)this.speedFill.getValue() / 1000.0f));
                        break;
                    }
                    case Aqua: {
                        AquaShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersFill(renderGameOverlayEvent.getPartialTicks());
                        AquaShader.INSTANCE.stopDraw(color, 1.0f, 1.0f, (float)this.duplicateFill.getValue(), (int)this.MaxIterFill.getValue(), (double)(float)this.tauFill.getValue());
                        AquaShader.INSTANCE.update((double)((float)this.speedFill.getValue() / 1000.0f));
                        break;
                    }
                    case Smoke: {
                        SmokeShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersFill(renderGameOverlayEvent.getPartialTicks());
                        SmokeShader.INSTANCE.stopDraw(Color.WHITE, 1.0f, 1.0f, (float)this.duplicateFill.getValue(), color, color3, color5, (int)this.NUM_OCTAVESFill.getValue());
                        SmokeShader.INSTANCE.update((double)((float)this.speedFill.getValue() / 1000.0f));
                        break;
                    }
                    case RainbowCube: {
                        RainbowCubeShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersFill(renderGameOverlayEvent.getPartialTicks());
                        RainbowCubeShader.INSTANCE.stopDraw(Color.WHITE, 1.0f, 1.0f, (float)this.duplicateFill.getValue(), color, (int)this.WaveLenghtFIll.getValue(), (int)this.RSTARTFill.getValue(), (int)this.GSTARTFill.getValue(), (int)this.BSTARTFIll.getValue());
                        RainbowCubeShader.INSTANCE.update((double)((float)this.speedFill.getValue() / 1000.0f));
                        break;
                    }
                    case Gradient: {
                        GradientShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersFill(renderGameOverlayEvent.getPartialTicks());
                        GradientShader.INSTANCE.stopDraw(color2, 1.0f, 1.0f, (float)this.duplicateFill.getValue(), (float)this.moreGradientFill.getValue(), (float)this.creepyFill.getValue(), (float)this.alphaFill.getValue(), (int)this.NUM_OCTAVESFill.getValue());
                        GradientShader.INSTANCE.update((double)((float)this.speedFill.getValue() / 1000.0f));
                        break;
                    }
                    case Fill: {
                        FillShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersFill(renderGameOverlayEvent.getPartialTicks());
                        FillShader.INSTANCE.stopDraw(color);
                        FillShader.INSTANCE.update((double)((float)this.speedFill.getValue() / 1000.0f));
                        break;
                    }
                    case Circle: {
                        CircleShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersFill(renderGameOverlayEvent.getPartialTicks());
                        CircleShader.INSTANCE.stopDraw((float)this.duplicateFill.getValue(), color, (Float)this.PI.getValue(), (Float)this.rad.getValue());
                        CircleShader.INSTANCE.update((double)((float)this.speedFill.getValue() / 1000.0f));
                        break;
                    }
                    case Phobos: {
                        PhobosShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersFill(renderGameOverlayEvent.getPartialTicks());
                        PhobosShader.INSTANCE.stopDraw(color, 1.0f, 1.0f, (float)this.duplicateFill.getValue(), (int)this.MaxIterFill.getValue(), (double)(float)this.tauFill.getValue());
                        PhobosShader.INSTANCE.update((double)((float)this.speedFill.getValue() / 1000.0f));
                        break;
                    }
                }
            }
            this.renderTags = true;
            this.renderCape = true;
            GlStateManager.popMatrix();
        }
    }
    
    public void onTick() {
        if (this.Fpreset.getValue()) {
            this.fillShader.setValue((Object)fillShadermode.None);
            this.glowESP.setValue((Object)glowESPmode.Gradient);
            this.player.setValue((Object)Player1.Outline);
            this.crystal.setValue((Object)Crystal1.Outline);
            this.duplicateOutline.setValue((Object)2.0f);
            this.speedOutline.setValue((Object)30.0f);
            this.quality.setValue((Object)0.6f);
            this.radius.setValue((Object)1.7f);
            this.creepyOutline.setValue((Object)1.0f);
            this.moreGradientOutline.setValue((Object)1.0f);
            this.Fpreset.setValue((Object)false);
        }
        if (this.default1.getValue()) {
            this.fillShader.setValue((Object)fillShadermode.None);
            this.glowESP.setValue((Object)glowESPmode.None);
            this.rangeCheck.setValue((Object)true);
            this.maxRange.setValue((Object)35.0f);
            this.minRange.setValue((Object)0.0f);
            this.crystal.setValue((Object)Crystal1.None);
            this.player.setValue((Object)Player1.None);
            this.mob.setValue((Object)Mob1.None);
            this.fadeFill.setValue((Object)false);
            this.fadeOutline.setValue((Object)false);
            this.duplicateOutline.setValue((Object)1.0f);
            this.duplicateFill.setValue((Object)1.0f);
            this.speedOutline.setValue((Object)10.0f);
            this.speedFill.setValue((Object)10.0f);
            this.quality.setValue((Object)1.0f);
            this.radius.setValue((Object)1.0f);
            this.rad.setValue((Object)0.75f);
            this.PI.setValue((Object)3.1415927f);
            this.saturationFill.setValue((Object)0.4f);
            this.distfadingFill.setValue((Object)0.56f);
            this.titleFill.setValue((Object)0.45f);
            this.stepSizeFill.setValue((Object)0.2f);
            this.volumStepsFill.setValue((Object)10.0f);
            this.zoomFill.setValue((Object)3.9f);
            this.formuparam2Fill.setValue((Object)0.89f);
            this.saturationOutline.setValue((Object)0.4f);
            this.maxEntities.setValue((Object)100);
            this.iterationsFill.setValue((Object)4);
            this.redFill.setValue((Object)0);
            this.MaxIterFill.setValue((Object)5);
            this.NUM_OCTAVESFill.setValue((Object)5);
            this.BSTARTFIll.setValue((Object)0);
            this.GSTARTFill.setValue((Object)0);
            this.RSTARTFill.setValue((Object)0);
            this.WaveLenghtFIll.setValue((Object)555);
            this.volumStepsOutline.setValue((Object)10);
            this.iterationsOutline.setValue((Object)4);
            this.MaxIterOutline.setValue((Object)5);
            this.NUM_OCTAVESOutline.setValue((Object)5);
            this.BSTARTOutline.setValue((Object)0);
            this.GSTARTOutline.setValue((Object)0);
            this.RSTARTOutline.setValue((Object)0);
            this.alphaValue.setValue((Object)255);
            this.WaveLenghtOutline.setValue((Object)555);
            this.redOutline.setValue((Object)0);
            this.alphaFill.setValue((Object)1.0f);
            this.blueFill.setValue((Object)0.0f);
            this.greenFill.setValue((Object)0.0f);
            this.tauFill.setValue((Object)6.2831855f);
            this.creepyFill.setValue((Object)1.0f);
            this.moreGradientFill.setValue((Object)1.0f);
            this.distfadingOutline.setValue((Object)0.56f);
            this.titleOutline.setValue((Object)0.45f);
            this.stepSizeOutline.setValue((Object)0.19f);
            this.zoomOutline.setValue((Object)3.9f);
            this.formuparam2Outline.setValue((Object)0.89f);
            this.alphaOutline.setValue((Object)1.0f);
            this.blueOutline.setValue((Object)0.0f);
            this.greenOutline.setValue((Object)0.0f);
            this.tauOutline.setValue((Object)0.0f);
            this.creepyOutline.setValue((Object)1.0f);
            this.moreGradientOutline.setValue((Object)1.0f);
            this.radOutline.setValue((Object)0.75f);
            this.PIOutline.setValue((Object)3.1415927f);
            this.default1.setValue((Object)false);
        }
    }
    
    Predicate<Boolean> getFill() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     4: aload_0        
        //     5: getfield        me/snow/luigihack/impl/modules/render/Shaders.colorImgFillred:Lme/snow/luigihack/api/setting/Setting;
        //     8: invokevirtual   me/snow/luigihack/api/setting/Setting.getValue:()Ljava/lang/Object;
        //    11: checkcast       Ljava/lang/Integer;
        //    14: invokevirtual   java/lang/Integer.intValue:()I
        //    17: aload_0        
        //    18: getfield        me/snow/luigihack/impl/modules/render/Shaders.colorImgFillgreen:Lme/snow/luigihack/api/setting/Setting;
        //    21: invokevirtual   me/snow/luigihack/api/setting/Setting.getValue:()Ljava/lang/Object;
        //    24: checkcast       Ljava/lang/Integer;
        //    27: invokevirtual   java/lang/Integer.intValue:()I
        //    30: aload_0        
        //    31: getfield        me/snow/luigihack/impl/modules/render/Shaders.colorImgFillblue:Lme/snow/luigihack/api/setting/Setting;
        //    34: invokevirtual   me/snow/luigihack/api/setting/Setting.getValue:()Ljava/lang/Object;
        //    37: checkcast       Ljava/lang/Integer;
        //    40: invokevirtual   java/lang/Integer.intValue:()I
        //    43: aload_0        
        //    44: getfield        me/snow/luigihack/impl/modules/render/Shaders.colorImgFillalpha:Lme/snow/luigihack/api/setting/Setting;
        //    47: invokevirtual   me/snow/luigihack/api/setting/Setting.getValue:()Ljava/lang/Object;
        //    50: checkcast       Ljava/lang/Integer;
        //    53: invokevirtual   java/lang/Integer.intValue:()I
        //    56: invokespecial   java/awt/Color.<init>:(IIII)V
        //    59: astore_1       
        //    60: new             Ljava/awt/Color;
        //    63: dup            
        //    64: aload_0        
        //    65: getfield        me/snow/luigihack/impl/modules/render/Shaders.colorESPred:Lme/snow/luigihack/api/setting/Setting;
        //    68: invokevirtual   me/snow/luigihack/api/setting/Setting.getValue:()Ljava/lang/Object;
        //    71: checkcast       Ljava/lang/Integer;
        //    74: invokevirtual   java/lang/Integer.intValue:()I
        //    77: aload_0        
        //    78: getfield        me/snow/luigihack/impl/modules/render/Shaders.colorESPgreen:Lme/snow/luigihack/api/setting/Setting;
        //    81: invokevirtual   me/snow/luigihack/api/setting/Setting.getValue:()Ljava/lang/Object;
        //    84: checkcast       Ljava/lang/Integer;
        //    87: invokevirtual   java/lang/Integer.intValue:()I
        //    90: aload_0        
        //    91: getfield        me/snow/luigihack/impl/modules/render/Shaders.colorESPblue:Lme/snow/luigihack/api/setting/Setting;
        //    94: invokevirtual   me/snow/luigihack/api/setting/Setting.getValue:()Ljava/lang/Object;
        //    97: checkcast       Ljava/lang/Integer;
        //   100: invokevirtual   java/lang/Integer.intValue:()I
        //   103: aload_0        
        //   104: getfield        me/snow/luigihack/impl/modules/render/Shaders.colorESPalpha:Lme/snow/luigihack/api/setting/Setting;
        //   107: invokevirtual   me/snow/luigihack/api/setting/Setting.getValue:()Ljava/lang/Object;
        //   110: checkcast       Ljava/lang/Integer;
        //   113: invokevirtual   java/lang/Integer.intValue:()I
        //   116: invokespecial   java/awt/Color.<init>:(IIII)V
        //   119: astore_2       
        //   120: new             Ljava/awt/Color;
        //   123: dup            
        //   124: aload_0        
        //   125: getfield        me/snow/luigihack/impl/modules/render/Shaders.secondColorImgFillred:Lme/snow/luigihack/api/setting/Setting;
        //   128: invokevirtual   me/snow/luigihack/api/setting/Setting.getValue:()Ljava/lang/Object;
        //   131: checkcast       Ljava/lang/Integer;
        //   134: invokevirtual   java/lang/Integer.intValue:()I
        //   137: aload_0        
        //   138: getfield        me/snow/luigihack/impl/modules/render/Shaders.secondColorImgFillgreen:Lme/snow/luigihack/api/setting/Setting;
        //   141: invokevirtual   me/snow/luigihack/api/setting/Setting.getValue:()Ljava/lang/Object;
        //   144: checkcast       Ljava/lang/Integer;
        //   147: invokevirtual   java/lang/Integer.intValue:()I
        //   150: aload_0        
        //   151: getfield        me/snow/luigihack/impl/modules/render/Shaders.secondColorImgFillblue:Lme/snow/luigihack/api/setting/Setting;
        //   154: invokevirtual   me/snow/luigihack/api/setting/Setting.getValue:()Ljava/lang/Object;
        //   157: checkcast       Ljava/lang/Integer;
        //   160: invokevirtual   java/lang/Integer.intValue:()I
        //   163: aload_0        
        //   164: getfield        me/snow/luigihack/impl/modules/render/Shaders.secondColorImgFillalpha:Lme/snow/luigihack/api/setting/Setting;
        //   167: invokevirtual   me/snow/luigihack/api/setting/Setting.getValue:()Ljava/lang/Object;
        //   170: checkcast       Ljava/lang/Integer;
        //   173: invokevirtual   java/lang/Integer.intValue:()I
        //   176: invokespecial   java/awt/Color.<init>:(IIII)V
        //   179: astore_3       
        //   180: new             Ljava/awt/Color;
        //   183: dup            
        //   184: aload_0        
        //   185: getfield        me/snow/luigihack/impl/modules/render/Shaders.thirdColorImgOutlinered:Lme/snow/luigihack/api/setting/Setting;
        //   188: invokevirtual   me/snow/luigihack/api/setting/Setting.getValue:()Ljava/lang/Object;
        //   191: checkcast       Ljava/lang/Integer;
        //   194: invokevirtual   java/lang/Integer.intValue:()I
        //   197: aload_0        
        //   198: getfield        me/snow/luigihack/impl/modules/render/Shaders.thirdColorImgOutlinegreen:Lme/snow/luigihack/api/setting/Setting;
        //   201: invokevirtual   me/snow/luigihack/api/setting/Setting.getValue:()Ljava/lang/Object;
        //   204: checkcast       Ljava/lang/Integer;
        //   207: invokevirtual   java/lang/Integer.intValue:()I
        //   210: aload_0        
        //   211: getfield        me/snow/luigihack/impl/modules/render/Shaders.thirdColorImgOutlineblue:Lme/snow/luigihack/api/setting/Setting;
        //   214: invokevirtual   me/snow/luigihack/api/setting/Setting.getValue:()Ljava/lang/Object;
        //   217: checkcast       Ljava/lang/Integer;
        //   220: invokevirtual   java/lang/Integer.intValue:()I
        //   223: aload_0        
        //   224: getfield        me/snow/luigihack/impl/modules/render/Shaders.thirdColorImgOutlinealpha:Lme/snow/luigihack/api/setting/Setting;
        //   227: invokevirtual   me/snow/luigihack/api/setting/Setting.getValue:()Ljava/lang/Object;
        //   230: checkcast       Ljava/lang/Integer;
        //   233: invokevirtual   java/lang/Integer.intValue:()I
        //   236: invokespecial   java/awt/Color.<init>:(IIII)V
        //   239: astore          4
        //   241: new             Ljava/awt/Color;
        //   244: dup            
        //   245: aload_0        
        //   246: getfield        me/snow/luigihack/impl/modules/render/Shaders.thirdColorImgFIllred:Lme/snow/luigihack/api/setting/Setting;
        //   249: invokevirtual   me/snow/luigihack/api/setting/Setting.getValue:()Ljava/lang/Object;
        //   252: checkcast       Ljava/lang/Integer;
        //   255: invokevirtual   java/lang/Integer.intValue:()I
        //   258: aload_0        
        //   259: getfield        me/snow/luigihack/impl/modules/render/Shaders.thirdColorImgFIllgreen:Lme/snow/luigihack/api/setting/Setting;
        //   262: invokevirtual   me/snow/luigihack/api/setting/Setting.getValue:()Ljava/lang/Object;
        //   265: checkcast       Ljava/lang/Integer;
        //   268: invokevirtual   java/lang/Integer.intValue:()I
        //   271: aload_0        
        //   272: getfield        me/snow/luigihack/impl/modules/render/Shaders.thirdColorImgFIllblue:Lme/snow/luigihack/api/setting/Setting;
        //   275: invokevirtual   me/snow/luigihack/api/setting/Setting.getValue:()Ljava/lang/Object;
        //   278: checkcast       Ljava/lang/Integer;
        //   281: invokevirtual   java/lang/Integer.intValue:()I
        //   284: aload_0        
        //   285: getfield        me/snow/luigihack/impl/modules/render/Shaders.thirdColorImgFIllalpha:Lme/snow/luigihack/api/setting/Setting;
        //   288: invokevirtual   me/snow/luigihack/api/setting/Setting.getValue:()Ljava/lang/Object;
        //   291: checkcast       Ljava/lang/Integer;
        //   294: invokevirtual   java/lang/Integer.intValue:()I
        //   297: invokespecial   java/awt/Color.<init>:(IIII)V
        //   300: astore          5
        //   302: new             Ljava/awt/Color;
        //   305: dup            
        //   306: aload_0        
        //   307: getfield        me/snow/luigihack/impl/modules/render/Shaders.colorImgOutlinered:Lme/snow/luigihack/api/setting/Setting;
        //   310: invokevirtual   me/snow/luigihack/api/setting/Setting.getValue:()Ljava/lang/Object;
        //   313: checkcast       Ljava/lang/Integer;
        //   316: invokevirtual   java/lang/Integer.intValue:()I
        //   319: aload_0        
        //   320: getfield        me/snow/luigihack/impl/modules/render/Shaders.colorImgOutlinegreen:Lme/snow/luigihack/api/setting/Setting;
        //   323: invokevirtual   me/snow/luigihack/api/setting/Setting.getValue:()Ljava/lang/Object;
        //   326: checkcast       Ljava/lang/Integer;
        //   329: invokevirtual   java/lang/Integer.intValue:()I
        //   332: aload_0        
        //   333: getfield        me/snow/luigihack/impl/modules/render/Shaders.colorImgOutlineblue:Lme/snow/luigihack/api/setting/Setting;
        //   336: invokevirtual   me/snow/luigihack/api/setting/Setting.getValue:()Ljava/lang/Object;
        //   339: checkcast       Ljava/lang/Integer;
        //   342: invokevirtual   java/lang/Integer.intValue:()I
        //   345: aload_0        
        //   346: getfield        me/snow/luigihack/impl/modules/render/Shaders.colorImgOutlinealpha:Lme/snow/luigihack/api/setting/Setting;
        //   349: invokevirtual   me/snow/luigihack/api/setting/Setting.getValue:()Ljava/lang/Object;
        //   352: checkcast       Ljava/lang/Integer;
        //   355: invokevirtual   java/lang/Integer.intValue:()I
        //   358: invokespecial   java/awt/Color.<init>:(IIII)V
        //   361: astore          6
        //   363: invokedynamic   BootstrapMethod #69, test:()Ljava/util/function/Predicate;
        //   368: astore          7
        //   370: getstatic       me/snow/luigihack/impl/modules/render/Shaders$1.$SwitchMap$me$snow$luigihack$impl$modules$render$Shaders$fillShadermode:[I
        //   373: aload_0        
        //   374: getfield        me/snow/luigihack/impl/modules/render/Shaders.fillShader:Lme/snow/luigihack/api/setting/Setting;
        //   377: invokevirtual   me/snow/luigihack/api/setting/Setting.getValue:()Ljava/lang/Object;
        //   380: checkcast       Lme/snow/luigihack/impl/modules/render/Shaders$fillShadermode;
        //   383: invokevirtual   me/snow/luigihack/impl/modules/render/Shaders$fillShadermode.ordinal:()I
        //   386: iaload         
        //   387: tableswitch {
        //                2: 432
        //                3: 467
        //                4: 503
        //                5: 542
        //                6: 578
        //                7: 613
        //                8: 710
        //                9: 746
        //          default: 779
        //        }
        //   432: aload_0        
        //   433: invokedynamic   BootstrapMethod #70, test:(Lme/snow/luigihack/impl/modules/render/Shaders;)Ljava/util/function/Predicate;
        //   438: astore          7
        //   440: getstatic       me/snow/luigihack/api/shaders/impl/fill/FlowShader.INSTANCE:Lme/snow/luigihack/api/shaders/impl/fill/FlowShader;
        //   443: aload_0        
        //   444: getfield        me/snow/luigihack/impl/modules/render/Shaders.speedFill:Lme/snow/luigihack/api/setting/Setting;
        //   447: invokevirtual   me/snow/luigihack/api/setting/Setting.getValue:()Ljava/lang/Object;
        //   450: checkcast       Ljava/lang/Float;
        //   453: invokevirtual   java/lang/Float.floatValue:()F
        //   456: ldc_w           1000.0
        //   459: fdiv           
        //   460: f2d            
        //   461: invokevirtual   me/snow/luigihack/api/shaders/impl/fill/FlowShader.update:(D)V
        //   464: goto            779
        //   467: aload_0        
        //   468: aload_1        
        //   469: invokedynamic   BootstrapMethod #71, test:(Lme/snow/luigihack/impl/modules/render/Shaders;Ljava/awt/Color;)Ljava/util/function/Predicate;
        //   474: astore          7
        //   476: getstatic       me/snow/luigihack/api/shaders/impl/fill/AquaShader.INSTANCE:Lme/snow/luigihack/api/shaders/impl/fill/AquaShader;
        //   479: aload_0        
        //   480: getfield        me/snow/luigihack/impl/modules/render/Shaders.speedFill:Lme/snow/luigihack/api/setting/Setting;
        //   483: invokevirtual   me/snow/luigihack/api/setting/Setting.getValue:()Ljava/lang/Object;
        //   486: checkcast       Ljava/lang/Float;
        //   489: invokevirtual   java/lang/Float.floatValue:()F
        //   492: ldc_w           1000.0
        //   495: fdiv           
        //   496: f2d            
        //   497: invokevirtual   me/snow/luigihack/api/shaders/impl/fill/AquaShader.update:(D)V
        //   500: goto            779
        //   503: aload_0        
        //   504: aload_1        
        //   505: aload_3        
        //   506: aload           5
        //   508: invokedynamic   BootstrapMethod #72, test:(Lme/snow/luigihack/impl/modules/render/Shaders;Ljava/awt/Color;Ljava/awt/Color;Ljava/awt/Color;)Ljava/util/function/Predicate;
        //   513: astore          7
        //   515: getstatic       me/snow/luigihack/api/shaders/impl/fill/SmokeShader.INSTANCE:Lme/snow/luigihack/api/shaders/impl/fill/SmokeShader;
        //   518: aload_0        
        //   519: getfield        me/snow/luigihack/impl/modules/render/Shaders.speedFill:Lme/snow/luigihack/api/setting/Setting;
        //   522: invokevirtual   me/snow/luigihack/api/setting/Setting.getValue:()Ljava/lang/Object;
        //   525: checkcast       Ljava/lang/Float;
        //   528: invokevirtual   java/lang/Float.floatValue:()F
        //   531: ldc_w           1000.0
        //   534: fdiv           
        //   535: f2d            
        //   536: invokevirtual   me/snow/luigihack/api/shaders/impl/fill/SmokeShader.update:(D)V
        //   539: goto            779
        //   542: aload_0        
        //   543: aload_1        
        //   544: invokedynamic   BootstrapMethod #73, test:(Lme/snow/luigihack/impl/modules/render/Shaders;Ljava/awt/Color;)Ljava/util/function/Predicate;
        //   549: astore          7
        //   551: getstatic       me/snow/luigihack/api/shaders/impl/fill/RainbowCubeShader.INSTANCE:Lme/snow/luigihack/api/shaders/impl/fill/RainbowCubeShader;
        //   554: aload_0        
        //   555: getfield        me/snow/luigihack/impl/modules/render/Shaders.speedFill:Lme/snow/luigihack/api/setting/Setting;
        //   558: invokevirtual   me/snow/luigihack/api/setting/Setting.getValue:()Ljava/lang/Object;
        //   561: checkcast       Ljava/lang/Float;
        //   564: invokevirtual   java/lang/Float.floatValue:()F
        //   567: ldc_w           1000.0
        //   570: fdiv           
        //   571: f2d            
        //   572: invokevirtual   me/snow/luigihack/api/shaders/impl/fill/RainbowCubeShader.update:(D)V
        //   575: goto            779
        //   578: aload_0        
        //   579: invokedynamic   BootstrapMethod #74, test:(Lme/snow/luigihack/impl/modules/render/Shaders;)Ljava/util/function/Predicate;
        //   584: astore          7
        //   586: getstatic       me/snow/luigihack/api/shaders/impl/fill/GradientShader.INSTANCE:Lme/snow/luigihack/api/shaders/impl/fill/GradientShader;
        //   589: aload_0        
        //   590: getfield        me/snow/luigihack/impl/modules/render/Shaders.speedFill:Lme/snow/luigihack/api/setting/Setting;
        //   593: invokevirtual   me/snow/luigihack/api/setting/Setting.getValue:()Ljava/lang/Object;
        //   596: checkcast       Ljava/lang/Float;
        //   599: invokevirtual   java/lang/Float.floatValue:()F
        //   602: ldc_w           1000.0
        //   605: fdiv           
        //   606: f2d            
        //   607: invokevirtual   me/snow/luigihack/api/shaders/impl/fill/GradientShader.update:(D)V
        //   610: goto            779
        //   613: new             Ljava/awt/Color;
        //   616: dup            
        //   617: aload_0        
        //   618: getfield        me/snow/luigihack/impl/modules/render/Shaders.colorImgFillred:Lme/snow/luigihack/api/setting/Setting;
        //   621: invokevirtual   me/snow/luigihack/api/setting/Setting.getValue:()Ljava/lang/Object;
        //   624: checkcast       Ljava/lang/Integer;
        //   627: invokevirtual   java/lang/Integer.intValue:()I
        //   630: aload_0        
        //   631: getfield        me/snow/luigihack/impl/modules/render/Shaders.colorImgFillgreen:Lme/snow/luigihack/api/setting/Setting;
        //   634: invokevirtual   me/snow/luigihack/api/setting/Setting.getValue:()Ljava/lang/Object;
        //   637: checkcast       Ljava/lang/Integer;
        //   640: invokevirtual   java/lang/Integer.intValue:()I
        //   643: aload_0        
        //   644: getfield        me/snow/luigihack/impl/modules/render/Shaders.colorImgFillblue:Lme/snow/luigihack/api/setting/Setting;
        //   647: invokevirtual   me/snow/luigihack/api/setting/Setting.getValue:()Ljava/lang/Object;
        //   650: checkcast       Ljava/lang/Integer;
        //   653: invokevirtual   java/lang/Integer.intValue:()I
        //   656: aload_0        
        //   657: getfield        me/snow/luigihack/impl/modules/render/Shaders.colorImgFillalpha:Lme/snow/luigihack/api/setting/Setting;
        //   660: invokevirtual   me/snow/luigihack/api/setting/Setting.getValue:()Ljava/lang/Object;
        //   663: checkcast       Ljava/lang/Integer;
        //   666: invokevirtual   java/lang/Integer.intValue:()I
        //   669: invokespecial   java/awt/Color.<init>:(IIII)V
        //   672: astore          8
        //   674: aload           8
        //   676: invokedynamic   BootstrapMethod #75, test:(Ljava/awt/Color;)Ljava/util/function/Predicate;
        //   681: astore          7
        //   683: getstatic       me/snow/luigihack/api/shaders/impl/fill/FillShader.INSTANCE:Lme/snow/luigihack/api/shaders/impl/fill/FillShader;
        //   686: aload_0        
        //   687: getfield        me/snow/luigihack/impl/modules/render/Shaders.speedFill:Lme/snow/luigihack/api/setting/Setting;
        //   690: invokevirtual   me/snow/luigihack/api/setting/Setting.getValue:()Ljava/lang/Object;
        //   693: checkcast       Ljava/lang/Float;
        //   696: invokevirtual   java/lang/Float.floatValue:()F
        //   699: ldc_w           1000.0
        //   702: fdiv           
        //   703: f2d            
        //   704: invokevirtual   me/snow/luigihack/api/shaders/impl/fill/FillShader.update:(D)V
        //   707: goto            779
        //   710: aload_0        
        //   711: aload_1        
        //   712: invokedynamic   BootstrapMethod #76, test:(Lme/snow/luigihack/impl/modules/render/Shaders;Ljava/awt/Color;)Ljava/util/function/Predicate;
        //   717: astore          7
        //   719: getstatic       me/snow/luigihack/api/shaders/impl/fill/CircleShader.INSTANCE:Lme/snow/luigihack/api/shaders/impl/fill/CircleShader;
        //   722: aload_0        
        //   723: getfield        me/snow/luigihack/impl/modules/render/Shaders.speedFill:Lme/snow/luigihack/api/setting/Setting;
        //   726: invokevirtual   me/snow/luigihack/api/setting/Setting.getValue:()Ljava/lang/Object;
        //   729: checkcast       Ljava/lang/Float;
        //   732: invokevirtual   java/lang/Float.floatValue:()F
        //   735: ldc_w           1000.0
        //   738: fdiv           
        //   739: f2d            
        //   740: invokevirtual   me/snow/luigihack/api/shaders/impl/fill/CircleShader.update:(D)V
        //   743: goto            779
        //   746: aload_0        
        //   747: aload_1        
        //   748: invokedynamic   BootstrapMethod #77, test:(Lme/snow/luigihack/impl/modules/render/Shaders;Ljava/awt/Color;)Ljava/util/function/Predicate;
        //   753: astore          7
        //   755: getstatic       me/snow/luigihack/api/shaders/impl/fill/PhobosShader.INSTANCE:Lme/snow/luigihack/api/shaders/impl/fill/PhobosShader;
        //   758: aload_0        
        //   759: getfield        me/snow/luigihack/impl/modules/render/Shaders.speedFill:Lme/snow/luigihack/api/setting/Setting;
        //   762: invokevirtual   me/snow/luigihack/api/setting/Setting.getValue:()Ljava/lang/Object;
        //   765: checkcast       Ljava/lang/Float;
        //   768: invokevirtual   java/lang/Float.floatValue:()F
        //   771: ldc_w           1000.0
        //   774: fdiv           
        //   775: f2d            
        //   776: invokevirtual   me/snow/luigihack/api/shaders/impl/fill/PhobosShader.update:(D)V
        //   779: aload           7
        //   781: areturn        
        //    Signature:
        //  ()Ljava/util/function/Predicate<Ljava/lang/Boolean;>;
        //    StackMapTable: 00 09 FF 01 B0 00 08 07 00 02 07 04 24 07 04 24 07 04 24 07 04 24 07 04 24 07 04 24 07 04 D2 00 00 22 23 26 23 22 FB 00 60 23 20
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Could not infer any expression.
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:374)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:344)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.Decompiler.decompile(Decompiler.java:70)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompile(Deobfuscator3000.java:538)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompileAndDeobfuscate(Deobfuscator3000.java:552)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.processMod(Deobfuscator3000.java:510)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.lambda$21(Deobfuscator3000.java:329)
        //     at java.base/java.lang.Thread.run(Thread.java:833)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    void renderPlayersOutline(final float n) {
        (boolean)this.rangeCheck.getValue();
        final double n2 = (float)this.minRange.getValue() * (float)this.minRange.getValue();
        final double n3 = (float)this.maxRange.getValue() * (float)this.maxRange.getValue();
        final AtomicInteger atomicInteger = new AtomicInteger();
        (int)this.maxEntities.getValue();
        Shaders.mc.world.addEntityToWorld(-1000, (Entity)new EntityXPOrb((World)Shaders.mc.world, Shaders.mc.player.posX, Shaders.mc.player.posY + 1000000.0, Shaders.mc.player.posZ, 1));
        final int n4;
        final boolean b;
        final double n5;
        final double n6;
        final double n7;
        Shaders.mc.world.loadedEntityList.stream().filter(entityPlayerSP -> {
            if (atomicInteger.getAndIncrement() > n4) {
                return false;
            }
            else {
                if (entityPlayerSP instanceof EntityPlayer) {
                    if ((this.player.getValue() == Player1.Outline || this.player.getValue() == Player1.Both) && (entityPlayerSP != Shaders.mc.player || Shaders.mc.gameSettings.thirdPersonView != 0)) {
                        return true;
                    }
                }
                else if (entityPlayerSP instanceof EntityCreature) {
                    if (this.mob.getValue() == Mob1.Outline || this.mob.getValue() == Mob1.Both) {
                        return true;
                    }
                }
                else if (entityPlayerSP instanceof EntityEnderCrystal && (this.crystal.getValue() == Crystal1.Outline || this.crystal.getValue() == Crystal1.Both)) {
                    return true;
                }
                return false;
            }
        }).filter(entity2 -> {
            if (!b) {
                return true;
            }
            else {
                Shaders.mc.player.getDistanceSq(entity2);
                return (n5 > n6 && n5 < n7) || entity2.getEntityId() == -1000;
            }
        }).forEach(entity -> Shaders.mc.getRenderManager().renderEntityStatic(entity, n, true));
        Shaders.mc.world.removeEntityFromWorld(-1000);
    }
    
    public enum Player1
    {
        Fill, 
        Outline, 
        None, 
        Both;
    }
    
    public enum Crystal1
    {
        Both, 
        Outline, 
        None, 
        Fill;
    }
    
    public enum glowESPmode
    {
        RainbowCube, 
        Circle, 
        Smoke, 
        Astral, 
        Aqua, 
        None, 
        Gradient, 
        Color;
    }
    
    public enum fillShadermode
    {
        Circle, 
        RainbowCube, 
        Fill, 
        Gradient, 
        Aqua, 
        Smoke, 
        Phobos, 
        None, 
        Astral;
    }
    
    public enum Mob1
    {
        None, 
        Outline, 
        Fill, 
        Both;
    }
}
