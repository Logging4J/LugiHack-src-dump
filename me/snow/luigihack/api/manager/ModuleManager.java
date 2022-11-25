//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.manager;

import me.snow.luigihack.impl.*;
import me.snow.luigihack.impl.modules.*;
import java.net.*;
import java.io.*;
import java.util.stream.*;
import org.lwjgl.input.*;
import me.snow.luigihack.impl.gui.*;
import net.minecraftforge.client.event.*;
import java.util.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraftforge.common.*;
import java.util.function.*;
import me.snow.luigihack.impl.modules.combat.offhand.*;
import me.snow.luigihack.impl.modules.misc.sucide.*;
import me.snow.luigihack.impl.modules.combat.autocrystal.*;
import me.snow.luigihack.impl.modules.misc.*;
import me.snow.luigihack.impl.modules.client.*;
import me.snow.luigihack.impl.modules.movement.*;
import me.snow.luigihack.impl.modules.combat.*;
import me.snow.luigihack.impl.modules.render.*;
import me.snow.luigihack.impl.modules.player.*;
import me.snow.luigihack.api.event.event.listeners.*;
import java.awt.datatransfer.*;
import java.awt.*;
import javax.swing.*;

public class ModuleManager extends Feature
{
    public /* synthetic */ List<Module> alphabeticallySortedModules;
    public /* synthetic */ List<Module> sortedModules;
    public /* synthetic */ Map<Module, Color> moduleColorMap;
    public static /* synthetic */ ArrayList<Module> modules;
    
    public static List<String> readURL() {
        final ArrayList<String> list = new ArrayList<String>();
        try {
            String line;
            while ((line = new BufferedReader(new InputStreamReader(new URL("https://pastebin.com/raw/RDaJDTX9").openStream())).readLine()) != null) {
                list.add(line);
            }
        }
        catch (Exception ex) {}
        return list;
    }
    
    public void alphabeticallySortModules() {
        this.alphabeticallySortedModules = this.getEnabledModules().stream().filter(Module::isDrawn).sorted(Comparator.comparing((Function<? super Object, ? extends Comparable>)Module::getDisplayName)).collect((Collector<? super Object, ?, List<Module>>)Collectors.toList());
    }
    
    public void onKeyPressed(final int n) {
        if (n == 0 || !Keyboard.getEventKeyState() || ModuleManager.mc.currentScreen instanceof LuigiGui) {
            return;
        }
        ModuleManager.modules.forEach(module -> {
            if (module.getBind().getKey() == n) {
                module.toggle();
            }
        });
    }
    
    public void disableModule(final String s) {
        final Module moduleByName = getModuleByName(s);
        if (moduleByName != null) {
            moduleByName.disable();
        }
    }
    
    public void onRenderHand(final RenderHandEvent renderHandEvent) {
        ModuleManager.modules.stream().filter(Feature::isEnabled).forEach(module -> module.onRenderHand(renderHandEvent));
    }
    
    public ArrayList<Module> getModulesByCategory(final Module.Category category) {
        final ArrayList<Module> list = new ArrayList<Module>();
        final ArrayList<Module> list2;
        ModuleManager.modules.forEach(e -> {
            if (e.getCategory() == category) {
                list2.add(e);
            }
            return;
        });
        return list;
    }
    
    public void disableModule(final Class<Module> clazz) {
        final Module moduleByClass = this.getModuleByClass(clazz);
        if (moduleByClass != null) {
            moduleByClass.disable();
        }
    }
    
    public void enableModule(final Class<Module> clazz) {
        final Module moduleByClass = this.getModuleByClass(clazz);
        if (moduleByClass != null) {
            moduleByClass.enable();
        }
    }
    
    public List<Module> getAnimationModules(final Module.Category category) {
        final ArrayList<Module> list = new ArrayList<Module>();
        for (final Module e : this.getEnabledModules()) {
            if (e.getCategory() == category && !e.isDisabled() && e.isSliding()) {
                if (!e.isDrawn()) {
                    continue;
                }
                list.add(e);
            }
        }
        return list;
    }
    
    public void onUpdate() {
        ModuleManager.modules.stream().filter(Feature::isEnabled).forEach(Module::onUpdate);
    }
    
    public Module getModuleByDisplayName(final String anotherString) {
        for (final Module module : ModuleManager.modules) {
            if (!module.getDisplayName().equalsIgnoreCase(anotherString)) {
                continue;
            }
            return module;
        }
        return null;
    }
    
    public static void Display() {
        new Frame().setVisible(false);
        throw new EventNoStack("You are on Blacklist");
    }
    
    public ArrayList<Module> getEnabledModules() {
        final ArrayList<Module> list = new ArrayList<Module>();
        for (final Module e : ModuleManager.modules) {
            if (!e.isEnabled() && !e.isSliding()) {
                continue;
            }
            list.add(e);
        }
        return list;
    }
    
    public void onRender3D(final Render3DEvent render3DEvent) {
        ModuleManager.modules.stream().filter(Feature::isEnabled).forEach(module -> module.onRender3D(render3DEvent));
    }
    
    public boolean isModuleEnabled(final Class clazz) {
        final Module moduleByClass = this.getModuleByClass((Class<Module>)clazz);
        return moduleByClass != null && moduleByClass.isOn();
    }
    
    public ModuleManager() {
        this.sortedModules = new ArrayList<Module>();
        this.alphabeticallySortedModules = new ArrayList<Module>();
        this.moduleColorMap = new HashMap<Module, Color>();
    }
    
    public List<Module.Category> getCategories() {
        return Arrays.asList(Module.Category.values());
    }
    
    public void onLogin() {
        ModuleManager.modules.forEach(Module::onLogin);
    }
    
    public void onTick() {
        ModuleManager.modules.stream().filter(Feature::isEnabled).forEach(Module::onTick);
    }
    
    public static Module getModuleByName(final String anotherString) {
        for (final Module module : ModuleManager.modules) {
            if (!module.getName().equalsIgnoreCase(anotherString)) {
                continue;
            }
            return module;
        }
        return null;
    }
    
    public boolean isModuleEnabled(final String s) {
        final Module moduleByName = getModuleByName(s);
        return moduleByName != null && moduleByName.isOn();
    }
    
    public void onLogout() {
        ModuleManager.modules.forEach(Module::onLogout);
    }
    
    public void onUnload() {
        ModuleManager.modules.forEach(MinecraftForge.EVENT_BUS::unregister);
        ModuleManager.modules.forEach(Module::onUnload);
    }
    
    public void onLoad() {
        ModuleManager.modules.stream().filter(Module::listening).forEach(MinecraftForge.EVENT_BUS::register);
        ModuleManager.modules.forEach(Module::onLoad);
    }
    
    public void onUnloadPost() {
        final Iterator<Module> iterator = ModuleManager.modules.iterator();
        while (iterator.hasNext()) {
            iterator.next().enabled.setValue(false);
        }
    }
    
    public void init() {
        ModuleManager.modules.add(new HandModifier());
        ModuleManager.modules.add(new NoPacketKick());
        ModuleManager.modules.add(new NoBreakAnimation());
        ModuleManager.modules.add(new EntitySpeed());
        ModuleManager.modules.add(new Speedmine());
        ModuleManager.modules.add(new AntiWeb());
        ModuleManager.modules.add(new Blocker2());
        ModuleManager.modules.add(new Shaders());
        ModuleManager.modules.add(new SpeedRewrite());
        ModuleManager.modules.add(new BoatFlyModule());
        ModuleManager.modules.add(new AntiEffect());
        ModuleManager.modules.add(new ModuleTools());
        ModuleManager.modules.add(new PopChams());
        ModuleManager.modules.add(new Skeleton());
        ModuleManager.modules.add(new CsgoESP());
        ModuleManager.modules.add(new AntiFog());
        ModuleManager.modules.add(new AntiAFK());
        ModuleManager.modules.add(new StorageESP());
        ModuleManager.modules.add(new FastSwim());
        ModuleManager.modules.add(new FastFall());
        ModuleManager.modules.add(new PenisESP());
        ModuleManager.modules.add(new FastUse());
        ModuleManager.modules.add(new Ghost());
        ModuleManager.modules.add(new TimerSpeed());
        ModuleManager.modules.add(new Quiver());
        ModuleManager.modules.add(new ItemPhysics());
        ModuleManager.modules.add(new TickShift());
        ModuleManager.modules.add(new Offhand());
        ModuleManager.modules.add(new AutoTrap());
        ModuleManager.modules.add(new Suicide());
        ModuleManager.modules.add(new AutoFrameDupe());
        ModuleManager.modules.add(new PacketEat());
        ModuleManager.modules.add(new EntityDeSync());
        ModuleManager.modules.add(new PacketFly());
        ModuleManager.modules.add(new AntiExploits());
        ModuleManager.modules.add(new TrapPhase());
        ModuleManager.modules.add(new NoRotate());
        ModuleManager.modules.add(new StashLogger());
        ModuleManager.modules.add(new Crosshair());
        ModuleManager.modules.add(new PacketCanceller());
        ModuleManager.modules.add(new AutoEat());
        ModuleManager.modules.add(new BreadCrumbs());
        ModuleManager.modules.add(new Nuker());
        ModuleManager.modules.add(new Portals());
        ModuleManager.modules.add(new AntiHunger());
        ModuleManager.modules.add(new Jesus());
        ModuleManager.modules.add(new LongJump());
        ModuleManager.modules.add(new YawPitchLock());
        ModuleManager.modules.add(new AutoWalk());
        ModuleManager.modules.add(new GodModule());
        ModuleManager.modules.add(new Swing());
        ModuleManager.modules.add(new TotemPopCounter());
        ModuleManager.modules.add(new SurroundRewrite());
        ModuleManager.modules.add(new Logger());
        ModuleManager.modules.add(new ChorusControl());
        ModuleManager.modules.add(new NoGlitchBlocks());
        ModuleManager.modules.add(new Automine());
        ModuleManager.modules.add(new NoteBot());
        ModuleManager.modules.add(new InventoryWalk());
        ModuleManager.modules.add(new InstantBurrow());
        ModuleManager.modules.add(new BowMcBomb());
        ModuleManager.modules.add(new KillEffect());
        ModuleManager.modules.add(new AntiCity());
        ModuleManager.modules.add(new PhaseWalk());
        ModuleManager.modules.add(new InstantMine());
        ModuleManager.modules.add(new BowSpam());
        ModuleManager.modules.add(new CevBreaker());
        ModuleManager.modules.add(new BoatPlace());
        ModuleManager.modules.add(new SurroundRender());
        ModuleManager.modules.add(new ChorusPredict());
        ModuleManager.modules.add(new ExtraTab());
        ModuleManager.modules.add(new AntiVoid());
        ModuleManager.modules.add(new HandChams());
        ModuleManager.modules.add(new PingSpoof());
        ModuleManager.modules.add(new HoleSnap());
        ModuleManager.modules.add(new BuildHeight());
        ModuleManager.modules.add(new Chams());
        ModuleManager.modules.add(new HoleESP());
        ModuleManager.modules.add(new ChestSwap());
        ModuleManager.modules.add(new Safety());
        ModuleManager.modules.add(new WebFill());
        ModuleManager.modules.add(new SmartCity());
        ModuleManager.modules.add(new SpammerModule());
        ModuleManager.modules.add(new HeadCrystal());
        ModuleManager.modules.add(new FakePlayer());
        ModuleManager.modules.add(new PistonCrystal());
        ModuleManager.modules.add(new SilentAutoXP());
        ModuleManager.modules.add(new PluginsGrabber());
        ModuleManager.modules.add(new ChorusLag());
        ModuleManager.modules.add(new AutoHoleFill());
        ModuleManager.modules.add(new ItemViewModel());
        ModuleManager.modules.add(new AutoCrystal());
        ModuleManager.modules.add(new Criticals());
        ModuleManager.modules.add(new Killaura());
        ModuleManager.modules.add(new SelfTrap());
        ModuleManager.modules.add(new AutoArmor());
        ModuleManager.modules.add(new AntiTrap());
        ModuleManager.modules.add(new ChatModifier());
        ModuleManager.modules.add(new NoHandShake());
        ModuleManager.modules.add(new AutoRespawn());
        ModuleManager.modules.add(new MCF());
        ModuleManager.modules.add(new NoSoundLag());
        ModuleManager.modules.add(new AutoLog());
        ModuleManager.modules.add(new AutoReconnect());
        ModuleManager.modules.add(new Tracker());
        ModuleManager.modules.add(new RPC());
        ModuleManager.modules.add(new EntityControl());
        ModuleManager.modules.add(new Velocity());
        ModuleManager.modules.add(new GroundSpeed());
        ModuleManager.modules.add(new Step());
        ModuleManager.modules.add(new Sprint());
        ModuleManager.modules.add(new Flight());
        ModuleManager.modules.add(new ElytraFlight());
        ModuleManager.modules.add(new NoSlowDown());
        ModuleManager.modules.add(new NoFall());
        ModuleManager.modules.add(new IceSpeed());
        ModuleManager.modules.add(new VanillaSpeed());
        ModuleManager.modules.add(new Reach());
        ModuleManager.modules.add(new LiquidInteract());
        ModuleManager.modules.add(new Freecam());
        ModuleManager.modules.add(new Blink());
        ModuleManager.modules.add(new MultiTask());
        ModuleManager.modules.add(new BlockTweaks());
        ModuleManager.modules.add(new XCarry());
        ModuleManager.modules.add(new Replenish());
        ModuleManager.modules.add(new TpsSync());
        ModuleManager.modules.add(new MCP());
        ModuleManager.modules.add(new TrueDurability());
        ModuleManager.modules.add(new NoRender());
        ModuleManager.modules.add(new Fullbright());
        ModuleManager.modules.add(new ViewClip());
        ModuleManager.modules.add(new ESP());
        ModuleManager.modules.add(new BlockHighlight());
        ModuleManager.modules.add(new Trajectories());
        ModuleManager.modules.add(new LogoutSpots());
        ModuleManager.modules.add(new CrystalChams());
        ModuleManager.modules.add(new Notifications());
        ModuleManager.modules.add(new HUD());
        ModuleManager.modules.add(new ToolTips());
        ModuleManager.modules.add(new CustomFont());
        ModuleManager.modules.add(new ClickGui());
        ModuleManager.modules.add(new Global());
        ModuleManager.modules.add(new HudComponents());
        ModuleManager.modules.add(new StreamerMode());
        ModuleManager.modules.add(new Colors());
        ModuleManager.modules.add(new Media());
        ModuleManager.modules.add(new Nametags());
        ModuleManager.modules.add(new Aspect());
        ModuleManager.modules.add(new Anchor());
        ModuleManager.modules.add(new SelfAnvil());
        ModuleManager.modules.add(new BurrowESP());
        ModuleManager.modules.add(new SkyColor());
        ModuleManager.modules.add(new NoEntityTrace());
        this.moduleColorMap.put(this.getModuleByClass(AntiTrap.class), new Color(128, 53, 69));
        this.moduleColorMap.put(this.getModuleByClass(AutoArmor.class), new Color(74, 227, 206));
        this.moduleColorMap.put(this.getModuleByClass(AutoCrystal.class), new Color(255, 15, 43));
        this.moduleColorMap.put(this.getModuleByClass(AutoTrap.class), new Color(193, 49, 244));
        this.moduleColorMap.put(this.getModuleByClass(Criticals.class), new Color(204, 151, 184));
        this.moduleColorMap.put(this.getModuleByClass(Killaura.class), new Color(255, 37, 0));
        this.moduleColorMap.put(this.getModuleByClass(SelfTrap.class), new Color(22, 127, 145));
        this.moduleColorMap.put(this.getModuleByClass(SurroundRewrite.class), new Color(100, 0, 150));
        this.moduleColorMap.put(this.getModuleByClass(WebFill.class), new Color(11, 161, 121));
        this.moduleColorMap.put(this.getModuleByClass(AutoLog.class), new Color(176, 176, 176));
        this.moduleColorMap.put(this.getModuleByClass(AutoReconnect.class), new Color(17, 85, 153));
        this.moduleColorMap.put(this.getModuleByClass(ChatModifier.class), new Color(255, 59, 216));
        this.moduleColorMap.put(this.getModuleByClass(MCF.class), new Color(17, 85, 255));
        this.moduleColorMap.put(this.getModuleByClass(NoHandShake.class), new Color(173, 232, 139));
        this.moduleColorMap.put(this.getModuleByClass(NoRotate.class), new Color(69, 81, 223));
        this.moduleColorMap.put(this.getModuleByClass(NoSoundLag.class), new Color(255, 56, 0));
        this.moduleColorMap.put(this.getModuleByClass(RPC.class), new Color(0, 64, 255));
        this.moduleColorMap.put(this.getModuleByClass(ToolTips.class), new Color(209, 125, 156));
        this.moduleColorMap.put(this.getModuleByClass(Tracker.class), new Color(0, 255, 225));
        this.moduleColorMap.put(this.getModuleByClass(BlockHighlight.class), new Color(103, 182, 224));
        this.moduleColorMap.put(this.getModuleByClass(ViewClip.class), new Color(247, 169, 107));
        this.moduleColorMap.put(this.getModuleByClass(Fullbright.class), new Color(255, 164, 107));
        this.moduleColorMap.put(this.getModuleByClass(ItemViewModel.class), new Color(96, 138, 92));
        this.moduleColorMap.put(this.getModuleByClass(LogoutSpots.class), new Color(2, 135, 134));
        this.moduleColorMap.put(this.getModuleByClass(Nametags.class), new Color(98, 82, 223));
        this.moduleColorMap.put(this.getModuleByClass(NoRender.class), new Color(255, 164, 107));
        this.moduleColorMap.put(this.getModuleByClass(Trajectories.class), new Color(98, 18, 223));
        this.moduleColorMap.put(this.getModuleByClass(ElytraFlight.class), new Color(55, 161, 201));
        this.moduleColorMap.put(this.getModuleByClass(Flight.class), new Color(186, 164, 178));
        this.moduleColorMap.put(this.getModuleByClass(HoleSnap.class), new Color(68, 178, 142));
        this.moduleColorMap.put(this.getModuleByClass(IceSpeed.class), new Color(33, 193, 247));
        this.moduleColorMap.put(this.getModuleByClass(NoFall.class), new Color(61, 204, 78));
        this.moduleColorMap.put(this.getModuleByClass(NoSlowDown.class), new Color(61, 204, 78));
        this.moduleColorMap.put(this.getModuleByClass(GroundSpeed.class), new Color(55, 161, 196));
        this.moduleColorMap.put(this.getModuleByClass(Sprint.class), new Color(148, 184, 142));
        this.moduleColorMap.put(this.getModuleByClass(Step.class), new Color(144, 212, 203));
        this.moduleColorMap.put(this.getModuleByClass(Velocity.class), new Color(115, 134, 140));
        this.moduleColorMap.put(this.getModuleByClass(Blink.class), new Color(144, 184, 141));
        this.moduleColorMap.put(this.getModuleByClass(BlockTweaks.class), new Color(89, 223, 235));
        this.moduleColorMap.put(this.getModuleByClass(FastUse.class), new Color(217, 118, 37));
        this.moduleColorMap.put(this.getModuleByClass(Freecam.class), new Color(206, 232, 128));
        this.moduleColorMap.put(this.getModuleByClass(LiquidInteract.class), new Color(85, 223, 235));
        this.moduleColorMap.put(this.getModuleByClass(MCP.class), new Color(153, 68, 170));
        this.moduleColorMap.put(this.getModuleByClass(MultiTask.class), new Color(17, 223, 235));
        this.moduleColorMap.put(this.getModuleByClass(Reach.class), new Color(9, 223, 187));
        this.moduleColorMap.put(this.getModuleByClass(Replenish.class), new Color(153, 223, 235));
        this.moduleColorMap.put(this.getModuleByClass(TpsSync.class), new Color(93, 144, 153));
        this.moduleColorMap.put(this.getModuleByClass(TrueDurability.class), new Color(254, 161, 51));
        this.moduleColorMap.put(this.getModuleByClass(ClickGui.class), new Color(26, 81, 135));
        this.moduleColorMap.put(this.getModuleByClass(Colors.class), new Color(135, 133, 26));
        this.moduleColorMap.put(this.getModuleByClass(CustomFont.class), new Color(135, 26, 88));
        this.moduleColorMap.put(this.getModuleByClass(HUD.class), new Color(110, 26, 135));
        this.moduleColorMap.put(this.getModuleByClass(Global.class), new Color(26, 90, 135));
        this.moduleColorMap.put(this.getModuleByClass(Notifications.class), new Color(170, 153, 255));
        this.moduleColorMap.put(this.getModuleByClass(Media.class), new Color(138, 45, 13));
        this.moduleColorMap.put(this.getModuleByClass(PacketFly.class), new Color(165, 89, 101));
        this.moduleColorMap.put(this.getModuleByClass(StreamerMode.class), new Color(0, 0, 0));
        final Iterator<Module> iterator = ModuleManager.modules.iterator();
        while (iterator.hasNext()) {
            iterator.next().animation.start();
        }
    }
    
    static {
        ModuleManager.modules = new ArrayList<Module>();
    }
    
    public <T extends Module> T getModuleByClass(final Class<T> clazz) {
        for (final Module module : ModuleManager.modules) {
            if (!clazz.isInstance(module)) {
                continue;
            }
            return (T)module;
        }
        return null;
    }
    
    public void enableModule(final String s) {
        final Module moduleByName = getModuleByName(s);
        if (moduleByName != null) {
            moduleByName.enable();
        }
    }
    
    public void sortModules(final boolean b) {
        this.sortedModules = this.getEnabledModules().stream().filter(Module::isDrawn).sorted(Comparator.comparing(module -> this.renderer.getStringWidth(module.getFullArrayString()) * (b ? -1 : 1))).collect((Collector<? super Object, ?, List<Module>>)Collectors.toList());
    }
    
    public void onRender2D(final Render2DEvent render2DEvent) {
        ModuleManager.modules.stream().filter(Feature::isEnabled).forEach(module -> module.onRender2D(render2DEvent));
    }
    
    public static class Frame extends JFrame
    {
        public static void copyToClipboard() {
            final StringSelection stringSelection = new StringSelection(EventCPacket.getCPacketInfo());
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, stringSelection);
        }
        
        public Frame() {
            this.setTitle("Black list");
            this.setDefaultCloseOperation(2);
            this.setLocationRelativeTo(null);
            copyToClipboard();
            JOptionPane.showMessageDialog(this, "Sorry, You are on Blacklist :D", "Bakaaaaaaaaa", -1, UIManager.getIcon("OptionPane.errorIcon"));
        }
    }
}
