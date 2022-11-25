//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.client;

import me.snow.luigihack.impl.modules.*;
import net.minecraft.item.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.util.*;
import java.awt.*;
import java.util.function.*;
import com.mojang.realmsclient.gui.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.snow.luigihack.*;
import net.minecraft.client.renderer.*;
import net.minecraft.init.*;
import me.snow.luigihack.api.event.events.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.client.*;
import me.snow.luigihack.impl.modules.misc.*;
import net.minecraft.client.gui.*;
import net.minecraft.potion.*;
import java.text.*;
import java.util.*;
import me.snow.luigihack.api.manager.*;
import me.snow.luigihack.impl.modules.combat.autocrystal.*;
import me.snow.luigihack.impl.modules.combat.*;
import me.snow.luigihack.impl.modules.player.*;

public class HUD extends Module
{
    private static final /* synthetic */ ItemStack exp;
    public /* synthetic */ Setting<Boolean> colorSync;
    private final /* synthetic */ Setting<Boolean> potions;
    private final /* synthetic */ Timer moduleTimer;
    private final /* synthetic */ Setting<Boolean> durability;
    private final /* synthetic */ Setting<Boolean> fps;
    private final /* synthetic */ Setting<Boolean> speed;
    private static /* synthetic */ HUD INSTANCE;
    private final /* synthetic */ Setting<Greeter> greeter;
    private final /* synthetic */ Setting<Boolean> ItemTextInfoRW;
    public /* synthetic */ Map<Integer, Integer> colorMap;
    public /* synthetic */ Setting<Integer> animationHorizontalTime;
    private final /* synthetic */ Setting<Boolean> direction;
    private final /* synthetic */ Setting<Float> watermarkYawa;
    private final /* synthetic */ Setting<Boolean> altPotionsColors;
    private final /* synthetic */ Setting<Boolean> itemInfo;
    private static final /* synthetic */ ItemStack totem2;
    public /* synthetic */ Setting<Integer> rainbowBrightness;
    private final /* synthetic */ Setting<Float> serveripYY;
    private final /* synthetic */ Setting<Boolean> coords;
    public /* synthetic */ Setting<Integer> hudRed;
    private static final /* synthetic */ ResourceLocation csgoHitmarker;
    private final /* synthetic */ Setting<Boolean> arrayList;
    private final /* synthetic */ Timer timer;
    private static final /* synthetic */ ItemStack gapples;
    private final /* synthetic */ Setting<Boolean> percent;
    private final /* synthetic */ Setting<Boolean> alphabeticalSorting;
    private final /* synthetic */ Setting<Boolean> renderingUp;
    private final /* synthetic */ Setting<Boolean> arrayListSideLine;
    private /* synthetic */ boolean shouldIncrement;
    private final /* synthetic */ Setting<LagNotify> lag;
    private final /* synthetic */ Setting<String> spoofGreeter;
    public /* synthetic */ Setting<Integer> factor;
    private final /* synthetic */ Setting<WaterMark> watermark;
    private final /* synthetic */ Setting<Boolean> modeVer;
    public /* synthetic */ Setting<Integer> hudBlue;
    private final /* synthetic */ Setting<Boolean> totems;
    private final /* synthetic */ Setting<Boolean> tps;
    private final /* synthetic */ Setting<Boolean> serverip;
    private final /* synthetic */ Setting<Integer> arryListYawa;
    private final /* synthetic */ Setting<Boolean> pvp;
    public /* synthetic */ Setting<Boolean> rainbow;
    private static final /* synthetic */ ItemStack totem;
    public /* synthetic */ Setting<Integer> animationVerticalTime;
    private final /* synthetic */ Setting<Boolean> armor;
    public /* synthetic */ Setting<Integer> rainbowSaturation;
    public /* synthetic */ Setting<Boolean> potionIcons;
    private static final /* synthetic */ ItemStack crystals;
    public /* synthetic */ Setting<Boolean> textRadar;
    private /* synthetic */ int color;
    public /* synthetic */ Setting<Integer> rainbowSpeed;
    public /* synthetic */ Setting<Boolean> shadow;
    public final /* synthetic */ Setting<Boolean> flowingArrayList;
    private /* synthetic */ int hitMarkerTimer;
    private /* synthetic */ Map<String, Integer> players;
    private final /* synthetic */ Setting<String> customWatermark;
    public /* synthetic */ Setting<Boolean> time;
    private static final /* synthetic */ ResourceLocation codHitmarker;
    private final /* synthetic */ Setting<Boolean> ping;
    public /* synthetic */ Setting<Boolean> rolling;
    private final /* synthetic */ Setting<Float> serveripXX;
    public /* synthetic */ Setting<Integer> hudGreen;
    private final /* synthetic */ Map<Potion, Color> potionColorMap;
    public /* synthetic */ Map<Module, Float> moduleProgressMap;
    
    public static HUD getInstance() {
        if (HUD.INSTANCE == null) {
            HUD.INSTANCE = new HUD();
        }
        return HUD.INSTANCE;
    }
    
    public void renderItemTextInfoShort() {
        int sum = Util.mc.player.inventory.mainInventory.stream().filter(itemStack -> itemStack.getItem() == Items.END_CRYSTAL).mapToInt(ItemStack::getCount).sum();
        int sum2 = Util.mc.player.inventory.mainInventory.stream().filter(itemStack2 -> itemStack2.getItem() == Items.GOLDEN_APPLE).mapToInt(ItemStack::getCount).sum();
        int sum3 = HUD.mc.player.inventory.mainInventory.stream().filter(itemStack3 -> itemStack3.getItem() == Items.TOTEM_OF_UNDYING).mapToInt(ItemStack::getCount).sum();
        if (HUD.mc.player.getHeldItemOffhand().getItem() == Items.TOTEM_OF_UNDYING) {
            sum3 += HUD.mc.player.getHeldItemOffhand().getCount();
        }
        if (HUD.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) {
            sum += HUD.mc.player.getHeldItemOffhand().getCount();
        }
        if (HUD.mc.player.getHeldItemOffhand().getItem() == Items.GOLDEN_APPLE) {
            sum2 += HUD.mc.player.getHeldItemOffhand().getCount();
        }
        int sum4 = HUD.mc.player.inventory.mainInventory.stream().filter(itemStack4 -> itemStack4.getItem() == Items.EXPERIENCE_BOTTLE).mapToInt(ItemStack::getCount).sum();
        if (HUD.mc.player.getHeldItemOffhand().getItem() == Items.EXPERIENCE_BOTTLE) {
            sum4 += HUD.mc.player.getHeldItemOffhand().getCount();
        }
        this.renderer.drawString(String.valueOf(new StringBuilder().append("T ").append(ChatFormatting.WHITE).append(sum3)), 2.0f, 250.0f, this.color, true);
        this.renderer.drawString(String.valueOf(new StringBuilder().append("C ").append(ChatFormatting.WHITE).append(sum)), 2.0f, 260.0f, this.color, true);
        this.renderer.drawString(String.valueOf(new StringBuilder().append("X ").append(ChatFormatting.WHITE).append(sum4)), 2.0f, 270.0f, this.color, true);
        this.renderer.drawString(String.valueOf(new StringBuilder().append("G ").append(ChatFormatting.WHITE).append(sum2)), 2.0f, 280.0f, this.color, true);
    }
    
    @SubscribeEvent
    public void onModuleToggle(final ClientEvent clientEvent) {
        if (clientEvent.getFeature() instanceof Module) {
            if (clientEvent.getStage() == 0) {
                for (float n = 0.0f; n <= this.renderer.getStringWidth(((Module)clientEvent.getFeature()).getDisplayName()); n += this.renderer.getStringWidth(((Module)clientEvent.getFeature()).getDisplayName()) / 500.0f) {
                    if (this.moduleTimer.passedMs(1L)) {
                        this.moduleProgressMap.put((Module)clientEvent.getFeature(), this.renderer.getStringWidth(((Module)clientEvent.getFeature()).getDisplayName()) - n);
                    }
                    this.timer.reset();
                }
            }
            else if (clientEvent.getStage() == 1) {
                for (float n2 = 0.0f; n2 <= this.renderer.getStringWidth(((Module)clientEvent.getFeature()).getDisplayName()); n2 += this.renderer.getStringWidth(((Module)clientEvent.getFeature()).getDisplayName()) / 500.0f) {
                    if (this.moduleTimer.passedMs(1L)) {
                        this.moduleProgressMap.put((Module)clientEvent.getFeature(), this.renderer.getStringWidth(((Module)clientEvent.getFeature()).getDisplayName()) - n2);
                    }
                    this.timer.reset();
                }
            }
        }
    }
    
    public void renderLag() {
        final int scaledWidth = this.renderer.scaledWidth;
        if (LuigiHack.serverManager.isServerNotResponding()) {
            final String value = String.valueOf(new StringBuilder().append((this.lag.getValue() == LagNotify.GRAY) ? "§7" : "§c").append("Server not responding: ").append(MathUtil.round(LuigiHack.serverManager.serverRespondingTime() / 1000.0f, 1)).append("s."));
            this.renderer.drawString(value, scaledWidth / 2.0f - this.renderer.getStringWidth(value) / 2.0f + 2.0f, 20.0f, ((boolean)this.rolling.getValue() && (boolean)this.rainbow.getValue()) ? ((int)this.colorMap.get(20)) : this.color, true);
        }
    }
    
    public void renderCrystalHud() {
        int sum = HUD.mc.player.inventory.mainInventory.stream().filter(itemStack -> itemStack.getItem() == Items.END_CRYSTAL).mapToInt(ItemStack::getCount).sum();
        if (HUD.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) {
            sum += HUD.mc.player.getHeldItemOffhand().getCount();
        }
        if (sum > 0) {
            GlStateManager.enableTexture2D();
            GlStateManager.enableDepth();
            RenderUtil.itemRender.zLevel = 200.0f;
            RenderUtil.itemRender.renderItemAndEffectIntoGUI(HUD.crystals, 0, 37);
            RenderUtil.itemRender.renderItemOverlayIntoGUI(HUD.mc.fontRenderer, HUD.crystals, 0, 37, "");
            RenderUtil.itemRender.zLevel = 0.0f;
            GlStateManager.enableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            this.renderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(sum).append("")), 10.0f, 47.0f, 16777215);
            GlStateManager.enableDepth();
            GlStateManager.disableLighting();
        }
    }
    
    public HUD() {
        super("HUD", "HUD Elements rendered on your screen", Category.CLIENT, true, false, false);
        this.renderingUp = (Setting<Boolean>)this.register(new Setting("RenderingUp", (Object)Boolean.FALSE, "Orientation of the HUD-Elements."));
        this.watermark = (Setting<WaterMark>)this.register(new Setting("Logo", (Object)WaterMark.NONE, "WaterMark"));
        this.watermarkYawa = (Setting<Float>)this.register(new Setting("WatermarkPosY", (Object)2.0f, (Object)0.0f, (Object)20.0f));
        this.customWatermark = (Setting<String>)this.register(new Setting("WatermarkName", (Object)"sweet client 0.0.4"));
        this.modeVer = (Setting<Boolean>)this.register(new Setting("Version", (Object)Boolean.FALSE, p0 -> this.watermark.getValue() != WaterMark.NONE));
        this.arrayList = (Setting<Boolean>)this.register(new Setting("ActiveModules", (Object)Boolean.FALSE, "Lists the active modules."));
        this.arrayListSideLine = (Setting<Boolean>)this.register(new Setting("ActiveModules Line", (Object)Boolean.FALSE, p0 -> (boolean)this.arrayList.getValue()));
        this.arryListYawa = (Setting<Integer>)this.register(new Setting("ActiveModules PosY", (Object)0, (Object)0, (Object)20, p0 -> (boolean)this.arrayList.getValue() && (boolean)this.renderingUp.getValue()));
        this.alphabeticalSorting = (Setting<Boolean>)this.register(new Setting("AlphabeticalSorting", (Object)Boolean.FALSE, p0 -> (boolean)this.arrayList.getValue()));
        this.ping = (Setting<Boolean>)this.register(new Setting("Ping", (Object)Boolean.FALSE, "Your response time to the server."));
        this.tps = (Setting<Boolean>)this.register(new Setting("TPS", (Object)Boolean.FALSE, "Ticks per second of the server."));
        this.fps = (Setting<Boolean>)this.register(new Setting("FPS", (Object)Boolean.FALSE, "Your frames per second."));
        this.coords = (Setting<Boolean>)this.register(new Setting("Coords", (Object)Boolean.FALSE, "Your current coordinates"));
        this.direction = (Setting<Boolean>)this.register(new Setting("Direction", (Object)Boolean.FALSE, "The Direction you are facing."));
        this.speed = (Setting<Boolean>)this.register(new Setting("Speed", (Object)Boolean.FALSE, "Your Speed"));
        this.potions = (Setting<Boolean>)this.register(new Setting("Potions", (Object)Boolean.FALSE, "Active potion effects"));
        this.altPotionsColors = (Setting<Boolean>)this.register(new Setting("AltPotionColors", (Object)Boolean.FALSE, p0 -> (boolean)this.potions.getValue()));
        this.armor = (Setting<Boolean>)this.register(new Setting("Armor", (Object)Boolean.FALSE, "ArmorHUD"));
        this.durability = (Setting<Boolean>)this.register(new Setting("Durability", (Object)Boolean.FALSE, "Durability"));
        this.percent = (Setting<Boolean>)this.register(new Setting("Percent", (Object)Boolean.TRUE, p0 -> (boolean)this.armor.getValue()));
        this.totems = (Setting<Boolean>)this.register(new Setting("Totems", (Object)Boolean.FALSE, "TotemHUD"));
        this.itemInfo = (Setting<Boolean>)this.register(new Setting("ItemInfo", (Object)Boolean.FALSE, "ItemInfo"));
        this.pvp = (Setting<Boolean>)this.register(new Setting("PvpInfo", (Object)true));
        this.ItemTextInfoRW = (Setting<Boolean>)this.register(new Setting("ItemTextInfoRewrite", (Object)true));
        this.greeter = (Setting<Greeter>)this.register(new Setting("Welcomer", (Object)Greeter.NONE, "Greets you."));
        this.spoofGreeter = (Setting<String>)this.register(new Setting("GreeterName", (Object)"3arthqu4ke", p0 -> this.greeter.getValue() == Greeter.CUSTOM));
        this.lag = (Setting<LagNotify>)this.register(new Setting("Lag", (Object)LagNotify.GRAY, "Lag Notifier"));
        this.serverip = (Setting<Boolean>)this.register(new Setting("Server ip", (Object)false));
        this.serveripXX = (Setting<Float>)this.register(new Setting("Server ip PosYX", (Object)2.0f, (Object)0.0f, (Object)1000.0f, p0 -> (boolean)this.serverip.getValue()));
        this.serveripYY = (Setting<Float>)this.register(new Setting("Server ip PosY", (Object)10.0f, (Object)0.0f, (Object)1000.0f, p0 -> (boolean)this.serverip.getValue()));
        this.timer = new Timer();
        this.moduleTimer = new Timer();
        this.potionColorMap = new HashMap<Potion, Color>();
        this.colorSync = (Setting<Boolean>)this.register(new Setting("Sync", (Object)Boolean.FALSE, "Universal colors for HUD."));
        this.rainbow = (Setting<Boolean>)this.register(new Setting("Rainbow", (Object)Boolean.FALSE, "Rainbow HUD."));
        this.flowingArrayList = (Setting<Boolean>)this.register(new Setting("Static Rainbow", (Object)true));
        this.factor = (Setting<Integer>)this.register(new Setting("Factor", (Object)1, (Object)0, (Object)20, p0 -> (boolean)this.rainbow.getValue()));
        this.rolling = (Setting<Boolean>)this.register(new Setting("Rolling", (Object)Boolean.FALSE, p0 -> (boolean)this.rainbow.getValue()));
        this.rainbowSpeed = (Setting<Integer>)this.register(new Setting("RSpeed", (Object)20, (Object)0, (Object)100, p0 -> (boolean)this.rainbow.getValue()));
        this.rainbowSaturation = (Setting<Integer>)this.register(new Setting("Saturation", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.rainbow.getValue()));
        this.rainbowBrightness = (Setting<Integer>)this.register(new Setting("Brightness", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.rainbow.getValue()));
        this.potionIcons = (Setting<Boolean>)this.register(new Setting("PotionIcons", (Object)Boolean.TRUE, "Draws Potion Icons."));
        this.shadow = (Setting<Boolean>)this.register(new Setting("NoShadow", (Object)Boolean.TRUE, "Draws the text with a shadow."));
        this.animationHorizontalTime = (Setting<Integer>)this.register(new Setting("AnimationHTime", (Object)500, (Object)1, (Object)1000, p0 -> (boolean)this.arrayList.getValue()));
        this.animationVerticalTime = (Setting<Integer>)this.register(new Setting("AnimationVTime", (Object)50, (Object)1, (Object)500, p0 -> (boolean)this.arrayList.getValue()));
        this.textRadar = (Setting<Boolean>)this.register(new Setting("TextRadar", (Object)Boolean.FALSE, "A TextRadar"));
        this.time = (Setting<Boolean>)this.register(new Setting("Time", (Object)Boolean.FALSE, "The time"));
        this.hudRed = (Setting<Integer>)this.register(new Setting("Red", (Object)36, (Object)0, (Object)255, p0 -> !(boolean)this.rainbow.getValue()));
        this.hudGreen = (Setting<Integer>)this.register(new Setting("Green", (Object)150, (Object)0, (Object)255, p0 -> !(boolean)this.rainbow.getValue()));
        this.hudBlue = (Setting<Integer>)this.register(new Setting("Blue", (Object)190, (Object)0, (Object)255, p0 -> !(boolean)this.rainbow.getValue()));
        this.moduleProgressMap = new HashMap<Module, Float>();
        this.colorMap = new HashMap<Integer, Integer>();
        this.players = new HashMap<String, Integer>();
        this.setInstance();
        this.potionColorMap.put(MobEffects.SPEED, new Color(124, 175, 198));
        this.potionColorMap.put(MobEffects.SLOWNESS, new Color(90, 108, 129));
        this.potionColorMap.put(MobEffects.HASTE, new Color(217, 192, 67));
        this.potionColorMap.put(MobEffects.MINING_FATIGUE, new Color(74, 66, 23));
        this.potionColorMap.put(MobEffects.STRENGTH, new Color(147, 36, 35));
        this.potionColorMap.put(MobEffects.INSTANT_HEALTH, new Color(67, 10, 9));
        this.potionColorMap.put(MobEffects.INSTANT_DAMAGE, new Color(67, 10, 9));
        this.potionColorMap.put(MobEffects.JUMP_BOOST, new Color(34, 255, 76));
        this.potionColorMap.put(MobEffects.NAUSEA, new Color(85, 29, 74));
        this.potionColorMap.put(MobEffects.REGENERATION, new Color(205, 92, 171));
        this.potionColorMap.put(MobEffects.RESISTANCE, new Color(153, 69, 58));
        this.potionColorMap.put(MobEffects.FIRE_RESISTANCE, new Color(228, 154, 58));
        this.potionColorMap.put(MobEffects.WATER_BREATHING, new Color(46, 82, 153));
        this.potionColorMap.put(MobEffects.INVISIBILITY, new Color(127, 131, 146));
        this.potionColorMap.put(MobEffects.BLINDNESS, new Color(31, 31, 35));
        this.potionColorMap.put(MobEffects.NIGHT_VISION, new Color(31, 31, 161));
        this.potionColorMap.put(MobEffects.HUNGER, new Color(88, 118, 83));
        this.potionColorMap.put(MobEffects.WEAKNESS, new Color(72, 77, 72));
        this.potionColorMap.put(MobEffects.POISON, new Color(78, 147, 49));
        this.potionColorMap.put(MobEffects.WITHER, new Color(53, 42, 39));
        this.potionColorMap.put(MobEffects.HEALTH_BOOST, new Color(248, 125, 35));
        this.potionColorMap.put(MobEffects.ABSORPTION, new Color(37, 82, 165));
        this.potionColorMap.put(MobEffects.SATURATION, new Color(248, 36, 35));
        this.potionColorMap.put(MobEffects.GLOWING, new Color(148, 160, 97));
        this.potionColorMap.put(MobEffects.LEVITATION, new Color(206, 255, 255));
        this.potionColorMap.put(MobEffects.LUCK, new Color(51, 153, 0));
        this.potionColorMap.put(MobEffects.UNLUCK, new Color(192, 164, 77));
    }
    
    public Map<String, Integer> getTextRadarPlayers() {
        return (Map<String, Integer>)EntityUtil.getTextRadarPlayers();
    }
    
    public void renderTotemHUD2() {
        int sum = HUD.mc.player.inventory.mainInventory.stream().filter(itemStack -> itemStack.getItem() == Items.TOTEM_OF_UNDYING).mapToInt(ItemStack::getCount).sum();
        if (HUD.mc.player.getHeldItemOffhand().getItem() == Items.TOTEM_OF_UNDYING) {
            sum += HUD.mc.player.getHeldItemOffhand().getCount();
        }
        if (sum > 0) {
            GlStateManager.enableTexture2D();
            GlStateManager.enableDepth();
            RenderUtil.itemRender.zLevel = 200.0f;
            RenderUtil.itemRender.renderItemAndEffectIntoGUI(HUD.totem2, 0, 20);
            RenderUtil.itemRender.renderItemOverlayIntoGUI(HUD.mc.fontRenderer, HUD.totem2, 0, 20, "");
            RenderUtil.itemRender.zLevel = 0.0f;
            GlStateManager.enableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            this.renderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(sum).append("")), 10.0f, 30.0f, 16777215);
            GlStateManager.enableDepth();
            GlStateManager.disableLighting();
        }
    }
    
    @Override
    public void onUpdate() {
        for (final Module module : LuigiHack.moduleManager.sortedModules) {
            if (module.isDisabled() && module.arrayListOffset == 0.0f) {
                module.sliding = true;
            }
        }
        if (this.timer.passedMs(0L)) {
            this.players = this.getTextRadarPlayers();
            this.timer.reset();
        }
        if (this.shouldIncrement) {
            ++this.hitMarkerTimer;
        }
        if (this.hitMarkerTimer == 10) {
            this.hitMarkerTimer = 0;
            this.shouldIncrement = false;
        }
    }
    
    static {
        box = new ResourceLocation("textures/gui/container/shulker_box.png");
        totem = new ItemStack(Items.TOTEM_OF_UNDYING);
        totem2 = new ItemStack(Items.TOTEM_OF_UNDYING);
        crystals = new ItemStack(Items.END_CRYSTAL);
        gapples = new ItemStack(Items.GOLDEN_APPLE);
        exp = new ItemStack(Items.EXPERIENCE_BOTTLE);
        codHitmarker = new ResourceLocation("earthhack", "cod_hitmarker");
        COD_EVENT = new SoundEvent(HUD.codHitmarker);
        csgoHitmarker = new ResourceLocation("earthhack", "csgo_hitmarker");
        CSGO_EVENT = new SoundEvent(HUD.csgoHitmarker);
        HUD.INSTANCE = new HUD();
    }
    
    @Override
    public void onRender2D(final Render2DEvent render2DEvent) {
        if (fullNullCheck()) {
            return;
        }
        final Color color = this.colorSync.getValue() ? Colors.INSTANCE.getCurrentColor() : new Color((int)getInstance().hudRed.getValue(), (int)getInstance().hudGreen.getValue(), (int)getInstance().hudBlue.getValue());
        final int n = 101 - (int)this.rainbowSpeed.getValue();
        final float hue = this.colorSync.getValue() ? Colors.INSTANCE.hue : (System.currentTimeMillis() % (360 * n) / (360.0f * n));
        final int scaledWidth = this.renderer.scaledWidth;
        final int scaledHeight = this.renderer.scaledHeight;
        float n2 = hue;
        for (int i = 0; i <= scaledHeight; ++i) {
            if (this.colorSync.getValue()) {
                this.colorMap.put(i, Color.HSBtoRGB(n2, (int)Colors.INSTANCE.rainbowSaturation.getValue() / 255.0f, (int)Colors.INSTANCE.rainbowBrightness.getValue() / 255.0f));
            }
            else {
                this.colorMap.put(i, Color.HSBtoRGB(n2, (int)this.rainbowSaturation.getValue() / 255.0f, (int)this.rainbowBrightness.getValue() / 255.0f));
            }
            n2 += 1.0f / scaledHeight * (int)this.factor.getValue();
        }
        GlStateManager.pushMatrix();
        if ((boolean)this.rainbow.getValue() && !(boolean)this.rolling.getValue()) {
            this.color = (this.colorSync.getValue() ? Colors.INSTANCE.getCurrentColorHex() : Color.HSBtoRGB(hue, (int)this.rainbowSaturation.getValue() / 255.0f, (int)this.rainbowBrightness.getValue() / 255.0f));
        }
        else if (!(boolean)this.rainbow.getValue()) {
            this.color = (this.colorSync.getValue() ? Colors.INSTANCE.getCurrentColorHex() : ColorUtil.toRGBA((int)this.hudRed.getValue(), (int)this.hudGreen.getValue(), (int)this.hudBlue.getValue()));
        }
        if (this.pvp.getValue()) {
            this.renderPvpInfo();
        }
        if (this.serverip.getValue()) {
            final String s = Minecraft.getMinecraft().isSingleplayer() ? "singleplayer".toLowerCase() : HUD.mc.getCurrentServerData().serverIP.toLowerCase();
            if (getInstance().rainbow.getValue()) {
                this.renderer.drawString(String.valueOf(new StringBuilder().append("IP: ").append(s)), (float)this.serveripXX.getValue(), (float)this.serveripYY.getValue(), ((boolean)this.rolling.getValue() && (boolean)this.rainbow.getValue()) ? ((int)this.colorMap.get(2)) : this.color, true);
            }
            else {
                this.renderer.drawString(String.valueOf(new StringBuilder().append("IP: ").append(s)), (float)this.serveripXX.getValue(), (float)this.serveripYY.getValue(), ((boolean)this.flowingArrayList.getValue()) ? ColorUtil.staticRainbow(0.89f, color) : this.color, true);
            }
        }
        switch ((WaterMark)this.watermark.getValue()) {
            case LUIGI: {
                if (getInstance().rainbow.getValue()) {
                    this.renderer.drawString(String.valueOf(new StringBuilder().append("LuigiHack ").append(this.modeVer.getValue() ? "1.8.3" : "")), 2.0f, (float)this.watermarkYawa.getValue(), ((boolean)this.rolling.getValue() && (boolean)this.rainbow.getValue()) ? ((int)this.colorMap.get(2)) : this.color, true);
                    break;
                }
                this.renderer.drawString(String.valueOf(new StringBuilder().append("LuigiHack ").append(this.modeVer.getValue() ? "1.8.3" : "")), 2.0f, (float)this.watermarkYawa.getValue(), ((boolean)this.flowingArrayList.getValue()) ? ColorUtil.staticRainbow(0.89f, color) : this.color, true);
                break;
            }
            case WHALE: {
                if (getInstance().rainbow.getValue()) {
                    this.renderer.drawString(String.valueOf(new StringBuilder().append("Whale ").append(this.modeVer.getValue() ? "1.8.3" : "")), 2.0f, (float)this.watermarkYawa.getValue(), ((boolean)this.rolling.getValue() && (boolean)this.rainbow.getValue()) ? ((int)this.colorMap.get(2)) : this.color, true);
                    break;
                }
                this.renderer.drawString(String.valueOf(new StringBuilder().append("Whale ").append(this.modeVer.getValue() ? "1.8.3" : "")), 2.0f, (float)this.watermarkYawa.getValue(), ((boolean)this.flowingArrayList.getValue()) ? ColorUtil.staticRainbow(0.89f, color) : this.color, true);
                break;
            }
            case WhiteVersion: {
                if (getInstance().rainbow.getValue()) {
                    this.renderer.drawString(String.valueOf(new StringBuilder().append("LuigiHack ").append(this.modeVer.getValue() ? String.valueOf(new StringBuilder().append(ChatFormatting.WHITE).append("1.8.3")) : "")), 2.0f, (float)this.watermarkYawa.getValue(), ((boolean)this.rolling.getValue() && (boolean)this.rainbow.getValue()) ? ((int)this.colorMap.get(2)) : this.color, true);
                    break;
                }
                this.renderer.drawString(String.valueOf(new StringBuilder().append("LuigiHack ").append(this.modeVer.getValue() ? String.valueOf(new StringBuilder().append(ChatFormatting.WHITE).append("1.8.3")) : "")), 2.0f, (float)this.watermarkYawa.getValue(), ((boolean)this.flowingArrayList.getValue()) ? ColorUtil.staticRainbow(0.89f, color) : this.color, true);
                break;
            }
            case CUSTOM: {
                if (getInstance().rainbow.getValue()) {
                    this.renderer.drawString(String.valueOf(new StringBuilder().append((String)this.customWatermark.getValue()).append(" ").append(this.modeVer.getValue() ? "1.8.3" : "")), 2.0f, (float)this.watermarkYawa.getValue(), ((boolean)this.rolling.getValue() && (boolean)this.rainbow.getValue()) ? ((int)this.colorMap.get(2)) : this.color, true);
                    break;
                }
                this.renderer.drawString(String.valueOf(new StringBuilder().append((String)this.customWatermark.getValue()).append(" ").append(this.modeVer.getValue() ? "1.8.3" : "")), 2.0f, (float)this.watermarkYawa.getValue(), ((boolean)this.flowingArrayList.getValue()) ? ColorUtil.staticRainbow(0.89f, color) : this.color, true);
                break;
            }
        }
        if (this.textRadar.getValue()) {
            this.drawTextRadar((ToolTips.getInstance().isOff() || !(boolean)ToolTips.getInstance().shulkerSpy.getValue() || !(boolean)ToolTips.getInstance().render.getValue()) ? 0 : ToolTips.getInstance().getTextRadarY());
        }
        int n3 = (int)(this.renderingUp.getValue() ? this.arryListYawa.getValue() : ((HUD.mc.currentScreen instanceof GuiChat) ? 14 : 0));
        if (this.arrayList.getValue()) {
            if (this.renderingUp.getValue()) {
                final String str = this.arrayListSideLine.getValue() ? "|" : "";
                for (int j = 0; j < (this.alphabeticalSorting.getValue() ? LuigiHack.moduleManager.alphabeticallySortedModules.size() : LuigiHack.moduleManager.sortedModules.size()); ++j) {
                    final Module module = this.alphabeticalSorting.getValue() ? LuigiHack.moduleManager.alphabeticallySortedModules.get(j) : LuigiHack.moduleManager.sortedModules.get(j);
                    final String value = String.valueOf(new StringBuilder().append(str).append(module.getDisplayName()).append("§7").append((module.getDisplayInfo() != null) ? String.valueOf(new StringBuilder().append(" [§f").append(module.getDisplayInfo()).append("§7]")) : ""));
                    if (getInstance().rainbow.getValue()) {
                        this.renderer.drawString(value, scaledWidth - 2 - this.renderer.getStringWidth(value) + (((int)this.animationHorizontalTime.getValue() == 1) ? 0.0f : module.arrayListOffset), (float)(2 + n3 * 10), ((boolean)this.rolling.getValue() && (boolean)this.rainbow.getValue()) ? ((int)this.colorMap.get(MathUtil.clamp(2 + n3 * 10, 0, scaledHeight))) : this.color, true);
                    }
                    else {
                        this.renderer.drawString(value, scaledWidth - 2 - this.renderer.getStringWidth(value) + (((int)this.animationHorizontalTime.getValue() == 1) ? 0.0f : module.arrayListOffset), (float)(2 + n3 * 10), ((boolean)this.flowingArrayList.getValue()) ? ColorUtil.staticRainbow((n3 + 1) * 0.89f, color) : this.color, true);
                    }
                    ++n3;
                }
            }
            else {
                final String str2 = this.arrayListSideLine.getValue() ? "|" : "";
                for (int k = 0; k < (this.alphabeticalSorting.getValue() ? LuigiHack.moduleManager.alphabeticallySortedModules.size() : LuigiHack.moduleManager.sortedModules.size()); ++k) {
                    final Module module2 = this.alphabeticalSorting.getValue() ? LuigiHack.moduleManager.alphabeticallySortedModules.get(LuigiHack.moduleManager.alphabeticallySortedModules.size() - 1 - k) : LuigiHack.moduleManager.sortedModules.get(k);
                    final String value2 = String.valueOf(new StringBuilder().append(str2).append(module2.getDisplayName()).append("§7").append((module2.getDisplayInfo() != null) ? String.valueOf(new StringBuilder().append(" [§f").append(module2.getDisplayInfo()).append("§7]")) : ""));
                    final TextManager renderer = this.renderer;
                    final float n4 = scaledWidth - 2 - this.renderer.getStringWidth(value2) + (((int)this.animationHorizontalTime.getValue() == 1) ? 0.0f : module2.arrayListOffset);
                    n3 += 10;
                    if (getInstance().rainbow.getValue()) {
                        renderer.drawString(value2, n4, (float)(scaledHeight - n3), ((boolean)this.rolling.getValue() && (boolean)this.rainbow.getValue()) ? ((int)this.colorMap.get(MathUtil.clamp(scaledHeight - n3, 0, scaledHeight))) : this.color, true);
                    }
                    else {
                        this.renderer.drawString(value2, n4, (float)(scaledHeight - n3), ((boolean)this.flowingArrayList.getValue()) ? ColorUtil.staticRainbow((n3 + 1) * 0.89f, color) : this.color, true);
                    }
                }
            }
        }
        int n5 = this.renderingUp.getValue() ? ((HUD.mc.currentScreen instanceof GuiChat) ? 0 : 0) : 0;
        if (this.renderingUp.getValue()) {
            if (this.potions.getValue()) {
                for (final PotionEffect potionEffect : LuigiHack.potionManager.getOwnPotions()) {
                    final String s2 = this.altPotionsColors.getValue() ? LuigiHack.potionManager.getPotionString(potionEffect) : LuigiHack.potionManager.getColoredPotionString(potionEffect);
                    final TextManager renderer2 = this.renderer;
                    final float n6 = (float)(scaledWidth - (this.renderer.getStringWidth(s2) + 2));
                    final int n7 = scaledHeight - 2;
                    n5 += 10;
                    renderer2.drawString(s2, n6, (float)(n7 - n5), ((boolean)this.altPotionsColors.getValue()) ? this.potionColorMap.get(potionEffect.getPotion()).getRGB() : this.color, true);
                }
            }
            if (this.speed.getValue()) {
                final String value3 = String.valueOf(new StringBuilder().append("Speed §f").append(LuigiHack.speedManager.getSpeedKpH()).append(" km/h"));
                final TextManager renderer3 = this.renderer;
                final float n8 = (float)(scaledWidth - (this.renderer.getStringWidth(value3) + 2));
                final int n9 = scaledHeight - 2;
                n5 += 10;
                if (getInstance().rainbow.getValue()) {
                    renderer3.drawString(value3, n8, (float)(n9 - n5), ((boolean)this.rolling.getValue() && (boolean)this.rainbow.getValue()) ? ((int)this.colorMap.get(scaledHeight - n5)) : this.color, true);
                }
                else {
                    renderer3.drawString(value3, n8, (float)(n9 - n5), ((boolean)this.flowingArrayList.getValue()) ? ColorUtil.staticRainbow((n3 + 1) * 0.89f, color) : this.color, true);
                }
            }
            if (this.time.getValue()) {
                final String value4 = String.valueOf(new StringBuilder().append("Time §f").append(new SimpleDateFormat("h:mm a").format(new Date())));
                final TextManager renderer4 = this.renderer;
                final float n10 = (float)(scaledWidth - (this.renderer.getStringWidth(value4) + 2));
                final int n11 = scaledHeight - 2;
                n5 += 10;
                if (getInstance().rainbow.getValue()) {
                    renderer4.drawString(value4, n10, (float)(n11 - n5), ((boolean)this.rolling.getValue() && (boolean)this.rainbow.getValue()) ? ((int)this.colorMap.get(scaledHeight - n5)) : this.color, true);
                }
                else {
                    renderer4.drawString(value4, n10, (float)(n11 - n5), ((boolean)this.flowingArrayList.getValue()) ? ColorUtil.staticRainbow((n3 + 1) * 0.89f, color) : this.color, true);
                }
            }
            if (this.durability.getValue()) {
                final int l = HUD.mc.player.getHeldItemMainhand().getMaxDamage() - HUD.mc.player.getHeldItemMainhand().getItemDamage();
                if (l > 0) {
                    final String value5 = String.valueOf(new StringBuilder().append("Durability §a").append(l));
                    final TextManager renderer5 = this.renderer;
                    final float n12 = (float)(scaledWidth - (this.renderer.getStringWidth(value5) + 2));
                    final int n13 = scaledHeight - 2;
                    n5 += 10;
                    if (getInstance().rainbow.getValue()) {
                        renderer5.drawString(value5, n12, (float)(n13 - n5), ((boolean)this.rolling.getValue() && (boolean)this.rainbow.getValue()) ? ((int)this.colorMap.get(scaledHeight - n5)) : this.color, true);
                    }
                    else {
                        renderer5.drawString(value5, n12, (float)(n13 - n5), ((boolean)this.flowingArrayList.getValue()) ? ColorUtil.staticRainbow((n3 + 1) * 0.89f, color) : this.color, true);
                    }
                }
            }
            if (this.tps.getValue()) {
                final String value6 = String.valueOf(new StringBuilder().append("TPS §f").append(LuigiHack.serverManager.getTPS()));
                final TextManager renderer6 = this.renderer;
                final float n14 = (float)(scaledWidth - (this.renderer.getStringWidth(value6) + 2));
                final int n15 = scaledHeight - 2;
                n5 += 10;
                if (getInstance().rainbow.getValue()) {
                    renderer6.drawString(value6, n14, (float)(n15 - n5), ((boolean)this.rolling.getValue() && (boolean)this.rainbow.getValue()) ? ((int)this.colorMap.get(scaledHeight - n5)) : this.color, true);
                }
                else {
                    renderer6.drawString(value6, n14, (float)(n15 - n5), ((boolean)this.flowingArrayList.getValue()) ? ColorUtil.staticRainbow((n3 + 1) * 0.89f, color) : this.color, true);
                }
            }
            final String value7 = String.valueOf(new StringBuilder().append("FPS §f").append(Minecraft.debugFPS));
            final String value8 = String.valueOf(new StringBuilder().append("Ping §f").append(LuigiHack.serverManager.getPing()));
            if (this.renderer.getStringWidth(value8) > this.renderer.getStringWidth(value7)) {
                if (this.ping.getValue()) {
                    final TextManager renderer7 = this.renderer;
                    final float n16 = (float)(scaledWidth - (this.renderer.getStringWidth(value8) + 2));
                    final int n17 = scaledHeight - 2;
                    n5 += 10;
                    if (getInstance().rainbow.getValue()) {
                        renderer7.drawString(value8, n16, (float)(n17 - n5), ((boolean)this.rolling.getValue() && (boolean)this.rainbow.getValue()) ? ((int)this.colorMap.get(scaledHeight - n5)) : this.color, true);
                    }
                    else {
                        renderer7.drawString(value8, n16, (float)(n17 - n5), ((boolean)this.flowingArrayList.getValue()) ? ColorUtil.staticRainbow((n3 + 1) * 0.89f, color) : this.color, true);
                    }
                }
                if (this.fps.getValue()) {
                    final TextManager renderer8 = this.renderer;
                    final float n18 = (float)(scaledWidth - (this.renderer.getStringWidth(value7) + 2));
                    final int n19 = scaledHeight - 2;
                    n5 += 10;
                    if (getInstance().rainbow.getValue()) {
                        renderer8.drawString(value7, n18, (float)(n19 - n5), ((boolean)this.rolling.getValue() && (boolean)this.rainbow.getValue()) ? ((int)this.colorMap.get(scaledHeight - n5)) : this.color, true);
                    }
                    else {
                        renderer8.drawString(value7, n18, (float)(n19 - n5), ((boolean)this.flowingArrayList.getValue()) ? ColorUtil.staticRainbow((n3 + 1) * 0.89f, color) : this.color, true);
                    }
                }
            }
            else {
                if (this.fps.getValue()) {
                    final TextManager renderer9 = this.renderer;
                    final float n20 = (float)(scaledWidth - (this.renderer.getStringWidth(value7) + 2));
                    final int n21 = scaledHeight - 2;
                    n5 += 10;
                    if (getInstance().rainbow.getValue()) {
                        renderer9.drawString(value7, n20, (float)(n21 - n5), ((boolean)this.rolling.getValue() && (boolean)this.rainbow.getValue()) ? ((int)this.colorMap.get(scaledHeight - n5)) : this.color, true);
                    }
                    else {
                        renderer9.drawString(value7, n20, (float)(n21 - n5), ((boolean)this.flowingArrayList.getValue()) ? ColorUtil.staticRainbow((n3 + 1) * 0.89f, color) : this.color, true);
                    }
                }
                if (this.ping.getValue()) {
                    final TextManager renderer10 = this.renderer;
                    final float n22 = (float)(scaledWidth - (this.renderer.getStringWidth(value8) + 2));
                    final int n23 = scaledHeight - 2;
                    n5 += 10;
                    if (getInstance().rainbow.getValue()) {
                        renderer10.drawString(value8, n22, (float)(n23 - n5), ((boolean)this.rolling.getValue() && (boolean)this.rainbow.getValue()) ? ((int)this.colorMap.get(scaledHeight - n5)) : this.color, true);
                    }
                    else {
                        renderer10.drawString(value8, n22, (float)(n23 - n5), ((boolean)this.flowingArrayList.getValue()) ? ColorUtil.staticRainbow((n3 + 1) * 0.89f, color) : this.color, true);
                    }
                }
            }
        }
        else {
            if (this.potions.getValue()) {
                for (final PotionEffect potionEffect2 : LuigiHack.potionManager.getOwnPotions()) {
                    final String s3 = this.altPotionsColors.getValue() ? LuigiHack.potionManager.getPotionString(potionEffect2) : LuigiHack.potionManager.getColoredPotionString(potionEffect2);
                    this.renderer.drawString(s3, (float)(scaledWidth - (this.renderer.getStringWidth(s3) + 2)), (float)(2 + n5++ * 10), ((boolean)this.altPotionsColors.getValue()) ? this.potionColorMap.get(potionEffect2.getPotion()).getRGB() : this.color, true);
                }
            }
            if (this.speed.getValue()) {
                final String value9 = String.valueOf(new StringBuilder().append("Speed §f").append(LuigiHack.speedManager.getSpeedKpH()).append(" km/h"));
                if (getInstance().rainbow.getValue()) {
                    this.renderer.drawString(value9, (float)(scaledWidth - (this.renderer.getStringWidth(value9) + 2)), (float)(2 + n5++ * 10), ((boolean)this.rolling.getValue() && (boolean)this.rainbow.getValue()) ? ((int)this.colorMap.get(2 + n5 * 10)) : this.color, true);
                }
                else {
                    this.renderer.drawString(value9, (float)(scaledWidth - (this.renderer.getStringWidth(value9) + 2)), (float)(2 + n5++ * 10), ((boolean)this.flowingArrayList.getValue()) ? ColorUtil.staticRainbow((n3 + 1) * 0.89f, color) : this.color, true);
                }
            }
            if (this.time.getValue()) {
                final String value10 = String.valueOf(new StringBuilder().append("Time §f").append(new SimpleDateFormat("h:mm a").format(new Date())));
                if (getInstance().rainbow.getValue()) {
                    this.renderer.drawString(value10, (float)(scaledWidth - (this.renderer.getStringWidth(value10) + 2)), (float)(2 + n5++ * 10), ((boolean)this.rolling.getValue() && (boolean)this.rainbow.getValue()) ? ((int)this.colorMap.get(2 + n5 * 10)) : this.color, true);
                }
                else {
                    this.renderer.drawString(value10, (float)(scaledWidth - (this.renderer.getStringWidth(value10) + 2)), (float)(2 + n5++ * 10), ((boolean)this.flowingArrayList.getValue()) ? ColorUtil.staticRainbow((n3 + 1) * 0.89f, color) : this.color, true);
                }
            }
            if (this.durability.getValue()) {
                final int m = HUD.mc.player.getHeldItemMainhand().getMaxDamage() - HUD.mc.player.getHeldItemMainhand().getItemDamage();
                if (m > 0) {
                    final String value11 = String.valueOf(new StringBuilder().append("Durability §a").append(m));
                    if (getInstance().rainbow.getValue()) {
                        this.renderer.drawString(value11, (float)(scaledWidth - (this.renderer.getStringWidth(value11) + 2)), (float)(2 + n5++ * 10), ((boolean)this.rolling.getValue() && (boolean)this.rainbow.getValue()) ? ((int)this.colorMap.get(2 + n5 * 10)) : this.color, true);
                    }
                    else {
                        this.renderer.drawString(value11, (float)(scaledWidth - (this.renderer.getStringWidth(value11) + 2)), (float)(2 + n5++ * 10), ((boolean)this.flowingArrayList.getValue()) ? ColorUtil.staticRainbow((n3 + 1) * 0.89f, color) : this.color, true);
                    }
                }
            }
            if (this.tps.getValue()) {
                final String value12 = String.valueOf(new StringBuilder().append("TPS §f").append(LuigiHack.serverManager.getTPS()));
                if (getInstance().rainbow.getValue()) {
                    this.renderer.drawString(value12, (float)(scaledWidth - (this.renderer.getStringWidth(value12) + 2)), (float)(2 + n5++ * 10), ((boolean)this.rolling.getValue() && (boolean)this.rainbow.getValue()) ? ((int)this.colorMap.get(2 + n5 * 10)) : this.color, true);
                }
                else {
                    this.renderer.drawString(value12, (float)(scaledWidth - (this.renderer.getStringWidth(value12) + 2)), (float)(2 + n5++ * 10), ((boolean)this.flowingArrayList.getValue()) ? ColorUtil.staticRainbow((n3 + 1) * 0.89f, color) : this.color, true);
                }
            }
            final String value13 = String.valueOf(new StringBuilder().append("FPS §f").append(Minecraft.debugFPS));
            final String value14 = String.valueOf(new StringBuilder().append("Ping §f").append(LuigiHack.serverManager.getPing()));
            if (this.renderer.getStringWidth(value14) > this.renderer.getStringWidth(value13)) {
                if (this.ping.getValue()) {
                    if (getInstance().rainbow.getValue()) {
                        this.renderer.drawString(value14, (float)(scaledWidth - (this.renderer.getStringWidth(value14) + 2)), (float)(2 + n5++ * 10), ((boolean)this.rolling.getValue() && (boolean)this.rainbow.getValue()) ? ((int)this.colorMap.get(2 + n5 * 10)) : this.color, true);
                    }
                    else {
                        this.renderer.drawString(value14, (float)(scaledWidth - (this.renderer.getStringWidth(value14) + 2)), (float)(2 + n5++ * 10), ((boolean)this.flowingArrayList.getValue()) ? ColorUtil.staticRainbow((n3 + 1) * 0.89f, color) : this.color, true);
                    }
                }
                if (this.fps.getValue()) {
                    if (getInstance().rainbow.getValue()) {
                        this.renderer.drawString(value13, (float)(scaledWidth - (this.renderer.getStringWidth(value13) + 2)), (float)(2 + n5++ * 10), ((boolean)this.rolling.getValue() && (boolean)this.rainbow.getValue()) ? ((int)this.colorMap.get(2 + n5 * 10)) : this.color, true);
                    }
                    else {
                        this.renderer.drawString(value13, (float)(scaledWidth - (this.renderer.getStringWidth(value13) + 2)), (float)(2 + n5++ * 10), ((boolean)this.flowingArrayList.getValue()) ? ColorUtil.staticRainbow((n3 + 1) * 0.89f, color) : this.color, true);
                    }
                }
            }
            else {
                if (this.fps.getValue()) {
                    if (getInstance().rainbow.getValue()) {
                        this.renderer.drawString(value13, (float)(scaledWidth - (this.renderer.getStringWidth(value13) + 2)), (float)(2 + n5++ * 10), ((boolean)this.rolling.getValue() && (boolean)this.rainbow.getValue()) ? ((int)this.colorMap.get(2 + n5 * 10)) : this.color, true);
                    }
                    else {
                        this.renderer.drawString(value13, (float)(scaledWidth - (this.renderer.getStringWidth(value13) + 2)), (float)(2 + n5++ * 10), ((boolean)this.flowingArrayList.getValue()) ? ColorUtil.staticRainbow((n3 + 1) * 0.89f, color) : this.color, true);
                    }
                }
                if (this.ping.getValue()) {
                    if (getInstance().rainbow.getValue()) {
                        this.renderer.drawString(value14, (float)(scaledWidth - (this.renderer.getStringWidth(value14) + 2)), (float)(2 + n5++ * 10), ((boolean)this.rolling.getValue() && (boolean)this.rainbow.getValue()) ? ((int)this.colorMap.get(2 + n5 * 10)) : this.color, true);
                    }
                    else {
                        this.renderer.drawString(value14, (float)(scaledWidth - (this.renderer.getStringWidth(value14) + 2)), (float)(2 + n5++ * 10), ((boolean)this.flowingArrayList.getValue()) ? ColorUtil.staticRainbow((n3 + 1) * 0.89f, color) : this.color, true);
                    }
                }
            }
        }
        final boolean equals = HUD.mc.world.getBiome(HUD.mc.player.getPosition()).getBiomeName().equals("Hell");
        final int i2 = (int)HUD.mc.player.posX;
        final int i3 = (int)HUD.mc.player.posY;
        final int i4 = (int)HUD.mc.player.posZ;
        final float n24 = equals ? 8.0f : 0.125f;
        final int i5 = (int)(HUD.mc.player.posX * n24);
        final int i6 = (int)(HUD.mc.player.posZ * n24);
        if (this.renderingUp.getValue()) {
            LuigiHack.notificationManager.handleNotifications(scaledHeight - (n5 + 16));
        }
        else {
            LuigiHack.notificationManager.handleNotifications(scaledHeight - (n3 + 16));
        }
        int n25 = (HUD.mc.currentScreen instanceof GuiChat) ? 14 : 0;
        final String value15 = String.valueOf(new StringBuilder().append("XYZ §f").append(i2).append(", ").append(i3).append(", ").append(i4).append(" §r[§f").append(i5).append(", ").append(i6).append("§r]"));
        final String value16 = String.valueOf(new StringBuilder().append(this.direction.getValue() ? String.valueOf(new StringBuilder().append(LuigiHack.rotationManager.getDirection4D(false)).append(" ")) : "").append(this.coords.getValue() ? value15 : "").append(""));
        final TextManager renderer11 = this.renderer;
        n25 += 10;
        final float n26 = (float)(scaledHeight - n25);
        int n27;
        if ((boolean)this.rolling.getValue() && (boolean)this.rainbow.getValue()) {
            final Map<Integer, Integer> colorMap = this.colorMap;
            n25 += 10;
            n27 = colorMap.get(scaledHeight - n25);
        }
        else {
            n27 = this.color;
        }
        renderer11.drawString(value16, 2.0f, n26, n27, true);
        if (this.armor.getValue()) {
            this.renderArmorHUD((boolean)this.percent.getValue());
        }
        if (this.totems.getValue()) {
            this.renderTotemHUD();
        }
        if (this.greeter.getValue() != Greeter.NONE) {
            this.renderGreeter();
        }
        if (this.lag.getValue() != LagNotify.NONE) {
            this.renderLag();
        }
        GlStateManager.popMatrix();
        if (this.itemInfo.getValue()) {
            this.renderTotemHUD2();
            this.renderCrystalHud();
            this.renderExpHud();
            this.renderGapsHud();
        }
        if (this.ItemTextInfoRW.getValue()) {
            this.renderItemTextInfoShort();
        }
    }
    
    private void setInstance() {
        HUD.INSTANCE = this;
    }
    
    public void renderTotemHUD() {
        final int scaledWidth = this.renderer.scaledWidth;
        final int scaledHeight = this.renderer.scaledHeight;
        int sum = HUD.mc.player.inventory.mainInventory.stream().filter(itemStack -> itemStack.getItem() == Items.TOTEM_OF_UNDYING).mapToInt(ItemStack::getCount).sum();
        if (HUD.mc.player.getHeldItemOffhand().getItem() == Items.TOTEM_OF_UNDYING) {
            sum += HUD.mc.player.getHeldItemOffhand().getCount();
        }
        if (sum > 0) {
            GlStateManager.enableTexture2D();
            final int n = scaledWidth / 2;
            final int n2 = scaledHeight - 55 - ((HUD.mc.player.isInWater() && HUD.mc.playerController.gameIsSurvivalOrAdventure()) ? 10 : 0);
            final int n3 = n - 189 + 180 + 2;
            GlStateManager.enableDepth();
            RenderUtil.itemRender.zLevel = 200.0f;
            RenderUtil.itemRender.renderItemAndEffectIntoGUI(HUD.totem, n3, n2);
            RenderUtil.itemRender.renderItemOverlayIntoGUI(HUD.mc.fontRenderer, HUD.totem, n3, n2, "");
            RenderUtil.itemRender.zLevel = 0.0f;
            GlStateManager.enableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            this.renderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(sum).append("")), (float)(n3 + 19 - 2 - this.renderer.getStringWidth(String.valueOf(new StringBuilder().append(sum).append("")))), (float)(n2 + 9), 16777215);
            GlStateManager.enableDepth();
            GlStateManager.disableLighting();
        }
    }
    
    public void renderGapsHud() {
        int sum = HUD.mc.player.inventory.mainInventory.stream().filter(itemStack -> itemStack.getItem() == Items.GOLDEN_APPLE).mapToInt(ItemStack::getCount).sum();
        if (HUD.mc.player.getHeldItemOffhand().getItem() == Items.GOLDEN_APPLE) {
            sum += HUD.mc.player.getHeldItemOffhand().getCount();
        }
        if (sum > 0) {
            GlStateManager.enableTexture2D();
            GlStateManager.enableDepth();
            RenderUtil.itemRender.zLevel = 200.0f;
            RenderUtil.itemRender.renderItemAndEffectIntoGUI(HUD.gapples, 0, 69);
            RenderUtil.itemRender.renderItemOverlayIntoGUI(HUD.mc.fontRenderer, HUD.gapples, 0, 69, "");
            RenderUtil.itemRender.zLevel = 0.0f;
            GlStateManager.enableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            this.renderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(sum).append("")), 10.0f, 79.0f, 16777215);
            GlStateManager.enableDepth();
            GlStateManager.disableLighting();
        }
    }
    
    public void renderExpHud() {
        int sum = HUD.mc.player.inventory.mainInventory.stream().filter(itemStack -> itemStack.getItem() == Items.EXPERIENCE_BOTTLE).mapToInt(ItemStack::getCount).sum();
        if (HUD.mc.player.getHeldItemOffhand().getItem() == Items.EXPERIENCE_BOTTLE) {
            sum += HUD.mc.player.getHeldItemOffhand().getCount();
        }
        if (sum > 0) {
            GlStateManager.enableTexture2D();
            GlStateManager.enableDepth();
            RenderUtil.itemRender.zLevel = 200.0f;
            RenderUtil.itemRender.renderItemAndEffectIntoGUI(HUD.exp, 0, 54);
            RenderUtil.itemRender.renderItemOverlayIntoGUI(HUD.mc.fontRenderer, HUD.exp, 10, 54, "");
            RenderUtil.itemRender.zLevel = 0.0f;
            GlStateManager.enableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            this.renderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(sum).append("")), 10.0f, 64.0f, 16777215);
            GlStateManager.enableDepth();
            GlStateManager.disableLighting();
        }
    }
    
    public void renderPvpInfo() {
        if (((AutoCrystal)LuigiHack.moduleManager.getModuleByClass((Class)AutoCrystal.class)).isEnabled()) {
            this.renderer.drawString(String.valueOf(new StringBuilder().append(ChatFormatting.GREEN).append("CA")), 2.0f, 210.0f, this.color, true);
        }
        if (((AutoTrap)LuigiHack.moduleManager.getModuleByClass((Class)AutoTrap.class)).isEnabled()) {
            this.renderer.drawString(String.valueOf(new StringBuilder().append(ChatFormatting.GREEN).append("Trap")), 2.0f, 220.0f, this.color, true);
        }
        if (((SurroundRewrite)LuigiHack.moduleManager.getModuleByClass((Class)SurroundRewrite.class)).isEnabled()) {
            this.renderer.drawString(String.valueOf(new StringBuilder().append(ChatFormatting.GREEN).append("SU")), 2.0f, 230.0f, this.color, true);
        }
        if (((InstantMine)LuigiHack.moduleManager.getModuleByClass((Class)InstantMine.class)).isEnabled()) {
            this.renderer.drawString(String.valueOf(new StringBuilder().append(ChatFormatting.GREEN).append("Mine")), 2.0f, 240.0f, this.color, true);
        }
        if (((AutoCrystal)LuigiHack.moduleManager.getModuleByClass((Class)AutoCrystal.class)).isDisabled()) {
            this.renderer.drawString(String.valueOf(new StringBuilder().append(ChatFormatting.DARK_RED).append("CA")), 2.0f, 210.0f, this.color, true);
        }
        if (((AutoTrap)LuigiHack.moduleManager.getModuleByClass((Class)AutoTrap.class)).isDisabled()) {
            this.renderer.drawString(String.valueOf(new StringBuilder().append(ChatFormatting.DARK_RED).append("Trap")), 2.0f, 220.0f, this.color, true);
        }
        if (((SurroundRewrite)LuigiHack.moduleManager.getModuleByClass((Class)SurroundRewrite.class)).isDisabled()) {
            this.renderer.drawString(String.valueOf(new StringBuilder().append(ChatFormatting.DARK_RED).append("SU")), 2.0f, 230.0f, this.color, true);
        }
        if (((InstantMine)LuigiHack.moduleManager.getModuleByClass((Class)InstantMine.class)).isDisabled()) {
            this.renderer.drawString(String.valueOf(new StringBuilder().append(ChatFormatting.DARK_RED).append("Mine")), 2.0f, 240.0f, this.color, true);
        }
    }
    
    public void renderArmorHUD(final boolean b) {
        final int scaledWidth = this.renderer.scaledWidth;
        final int scaledHeight = this.renderer.scaledHeight;
        GlStateManager.enableTexture2D();
        final int n = scaledWidth / 2;
        int n2 = 0;
        final int n3 = scaledHeight - 55 - ((HUD.mc.player.isInWater() && HUD.mc.playerController.gameIsSurvivalOrAdventure()) ? 10 : 0);
        for (final ItemStack itemStack : HUD.mc.player.inventory.armorInventory) {
            ++n2;
            if (itemStack.isEmpty()) {
                continue;
            }
            final int n4 = n - 90 + (9 - n2) * 20 + 2;
            GlStateManager.enableDepth();
            RenderUtil.itemRender.zLevel = 200.0f;
            RenderUtil.itemRender.renderItemAndEffectIntoGUI(itemStack, n4, n3);
            RenderUtil.itemRender.renderItemOverlayIntoGUI(HUD.mc.fontRenderer, itemStack, n4, n3, "");
            RenderUtil.itemRender.zLevel = 0.0f;
            GlStateManager.enableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            final String s = (itemStack.getCount() > 1) ? String.valueOf(new StringBuilder().append(itemStack.getCount()).append("")) : "";
            this.renderer.drawStringWithShadow(s, (float)(n4 + 19 - 2 - this.renderer.getStringWidth(s)), (float)(n3 + 9), 16777215);
            if (!b) {
                continue;
            }
            final float n5 = (itemStack.getMaxDamage() - (float)itemStack.getItemDamage()) / itemStack.getMaxDamage();
            final float n6 = 1.0f - n5;
            final int n7 = 100 - (int)(n6 * 100.0f);
            this.renderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(n7).append("")), (float)(n4 + 8 - this.renderer.getStringWidth(String.valueOf(new StringBuilder().append(n7).append(""))) / 2), (float)(n3 - 11), ColorUtil.toRGBA((int)(n6 * 255.0f), (int)(n5 * 255.0f), 0));
        }
        GlStateManager.enableDepth();
        GlStateManager.disableLighting();
    }
    
    public void drawTextRadar(final int n) {
        if (!this.players.isEmpty()) {
            int i = this.renderer.getFontHeight() + 7 + n;
            final Iterator<Map.Entry<String, Integer>> iterator = this.players.entrySet().iterator();
            while (iterator.hasNext()) {
                final String value = String.valueOf(new StringBuilder().append(iterator.next().getKey()).append(" "));
                final int n2 = this.renderer.getFontHeight() + 1;
                this.renderer.drawString(value, 2.0f, (float)i, ((boolean)this.rolling.getValue() && (boolean)this.rainbow.getValue()) ? ((int)this.colorMap.get(i)) : this.color, true);
                i += n2;
            }
        }
    }
    
    public void renderGreeter() {
        final int scaledWidth = this.renderer.scaledWidth;
        final String str = "";
        String s = null;
        switch ((Greeter)this.greeter.getValue()) {
            case TIME: {
                s = String.valueOf(new StringBuilder().append(str).append(MathUtil.getTimeOfDay()).append(HUD.mc.player.getDisplayNameString()));
                break;
            }
            case LONG: {
                s = String.valueOf(new StringBuilder().append(str).append("Welcome to LuigiHack ").append(HUD.mc.player.getDisplayNameString()).append(" "));
                break;
            }
            case CHRISTMAS: {
                s = String.valueOf(new StringBuilder().append(str).append("Merry Christmas ").append(HUD.mc.player.getDisplayNameString()).append(" :^)"));
                break;
            }
            case CUSTOM: {
                s = String.valueOf(new StringBuilder().append(str).append((String)this.spoofGreeter.getValue()));
                break;
            }
            default: {
                s = String.valueOf(new StringBuilder().append(str).append("Welcome ").append(HUD.mc.player.getDisplayNameString()));
                break;
            }
        }
        final Color color = this.colorSync.getValue() ? Colors.INSTANCE.getCurrentColor() : new Color((int)getInstance().hudRed.getValue(), (int)getInstance().hudGreen.getValue(), (int)getInstance().hudBlue.getValue());
        if (getInstance().rainbow.getValue()) {
            this.renderer.drawString(s, scaledWidth / 2.0f - this.renderer.getStringWidth(s) / 2.0f + 2.0f, 2.0f, ((boolean)this.rolling.getValue() && (boolean)this.rainbow.getValue()) ? ((int)this.colorMap.get(2)) : this.color, true);
        }
        else {
            this.renderer.drawString(s, scaledWidth / 2.0f - this.renderer.getStringWidth(s) / 2.0f + 2.0f, 2.0f, ((boolean)this.flowingArrayList.getValue()) ? ColorUtil.staticRainbow(0.89f, color) : this.color, true);
        }
    }
    
    public enum Greeter
    {
        NAME, 
        CHRISTMAS, 
        CUSTOM, 
        TIME, 
        LONG, 
        NONE;
    }
    
    public enum WaterMark
    {
        WhiteVersion, 
        WHALE, 
        CUSTOM, 
        NONE, 
        LUIGI;
    }
    
    public enum LagNotify
    {
        GRAY, 
        NONE, 
        RED;
    }
    
    public enum Sound
    {
        CSGO, 
        COD, 
        NONE;
    }
}
