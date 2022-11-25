//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack;

import net.minecraftforge.fml.common.*;
import me.zero.alpine.fork.bus.*;
import me.snow.luigihack.api.event.processor.*;
import me.snow.luigihack.api.util.cc.*;
import org.apache.logging.log4j.*;
import org.lwjgl.opengl.*;
import me.snow.luigihack.impl.modules.client.*;
import net.minecraftforge.fml.common.event.*;
import me.snow.luigihack.api.manager.*;
import java.awt.*;
import me.snow.luigihack.api.util.*;

@Mod(modid = "luigihack", name = "LuigiHack", version = "1.8.3")
public class LuigiHack
{
    public static /* synthetic */ NewRotationManager newrotationManager;
    public static /* synthetic */ FileManager fileManager;
    public static /* synthetic */ FriendManager friendManager;
    public static /* synthetic */ PotionManager potionManager;
    public static /* synthetic */ SafetyManager safetyManager;
    public static final /* synthetic */ Logger LOGGER;
    private final /* synthetic */ TrayIcon trayIcon;
    public static /* synthetic */ TimerManager timerManager;
    public /* synthetic */ SystemTray tray;
    private static /* synthetic */ boolean unloaded;
    public static /* synthetic */ EventBus dispatcher;
    public static /* synthetic */ CommandManager commandManager;
    public static /* synthetic */ ConfigManager configManager;
    public static /* synthetic */ RotationManager rotationManager;
    public static /* synthetic */ PacketManager packetManager;
    public static /* synthetic */ RotationManager2 rotationManager2;
    public static /* synthetic */ PlayerManager playerManager;
    public static /* synthetic */ EventProcessor EVENT_PROCESSOR;
    public static /* synthetic */ InventoryManager inventoryManager;
    public static /* synthetic */ NotificationManager notificationManager;
    public static /* synthetic */ ColorManager colorManager;
    public static /* synthetic */ SpeedManager speedManager;
    public static /* synthetic */ PositionManager positionManager;
    public static /* synthetic */ ModuleManager moduleManager;
    private final /* synthetic */ Image image;
    public static /* synthetic */ HoleManager holeManager;
    public static /* synthetic */ MovementManager movementManager;
    public static /* synthetic */ EventManager eventManager;
    public static /* synthetic */ WaypointManager waypointManager;
    public static /* synthetic */ TextManager textManager;
    public static /* synthetic */ ReloadManager reloadManager;
    public static /* synthetic */ InventoryManagerCC inventoryManagercc;
    public static /* synthetic */ ServerManager serverManager;
    public static /* synthetic */ NoStopManager baritoneManager;
    public static /* synthetic */ TotemPopManager totemPopManager;
    
    public static void unload(final boolean b) {
        LuigiHack.LOGGER.info("\n\nUnloading luigihack 1.0.0");
        if (b) {
            (LuigiHack.reloadManager = new ReloadManager()).init((LuigiHack.commandManager != null) ? LuigiHack.commandManager.getPrefix() : ".");
        }
        if (LuigiHack.baritoneManager != null) {
            LuigiHack.baritoneManager.stop();
        }
        onUnload();
        LuigiHack.eventManager = null;
        LuigiHack.holeManager = null;
        LuigiHack.dispatcher = null;
        LuigiHack.timerManager = null;
        LuigiHack.moduleManager = null;
        LuigiHack.totemPopManager = null;
        LuigiHack.serverManager = null;
        LuigiHack.colorManager = null;
        LuigiHack.textManager = null;
        LuigiHack.speedManager = null;
        LuigiHack.rotationManager2 = null;
        LuigiHack.rotationManager = null;
        LuigiHack.inventoryManagercc = null;
        LuigiHack.newrotationManager = null;
        LuigiHack.movementManager = null;
        LuigiHack.positionManager = null;
        LuigiHack.commandManager = null;
        LuigiHack.configManager = null;
        LuigiHack.fileManager = null;
        LuigiHack.friendManager = null;
        LuigiHack.potionManager = null;
        LuigiHack.inventoryManager = null;
        LuigiHack.notificationManager = null;
        LuigiHack.safetyManager = null;
        LuigiHack.LOGGER.info("luigihack unloaded!\n");
    }
    
    public LuigiHack() {
        this.image = Toolkit.getDefaultToolkit().getImage("resources/LuigiA.png");
        this.trayIcon = new TrayIcon(this.image, "LugiHack");
        this.tray = SystemTray.getSystemTray();
    }
    
    static {
        MODNAME = "LuigiHack";
        MODVER = "1.8.3";
        MODID = "luigihack";
        LOGGER = LogManager.getLogger("LuigiHack");
        LuigiHack.unloaded = false;
    }
    
    @Mod.EventHandler
    public void init(final FMLInitializationEvent fmlInitializationEvent) {
        Display.setTitle("LuigiHack - 1.8.3 | https://discord.gg/EtTGvzt3nS");
        load();
    }
    
    public static void load() {
        LuigiHack.LOGGER.info("\n\nLoading luigihack");
        LuigiHack.unloaded = false;
        if (LuigiHack.reloadManager != null) {
            LuigiHack.reloadManager.unload();
            LuigiHack.reloadManager = null;
        }
        LuigiHack.EVENT_PROCESSOR = new EventProcessor();
        LuigiHack.dispatcher = new me.zero.alpine.fork.bus.EventManager();
        LuigiHack.baritoneManager = new NoStopManager();
        LuigiHack.totemPopManager = new TotemPopManager();
        LuigiHack.timerManager = new TimerManager();
        LuigiHack.packetManager = new PacketManager();
        LuigiHack.serverManager = new ServerManager();
        LuigiHack.colorManager = new ColorManager();
        LuigiHack.textManager = new TextManager();
        LuigiHack.moduleManager = new ModuleManager();
        LuigiHack.speedManager = new SpeedManager();
        LuigiHack.rotationManager = new RotationManager();
        LuigiHack.rotationManager2 = new RotationManager2();
        LuigiHack.movementManager = new MovementManager();
        LuigiHack.positionManager = new PositionManager();
        LuigiHack.commandManager = new CommandManager();
        LuigiHack.eventManager = new EventManager();
        LuigiHack.playerManager = new PlayerManager();
        LuigiHack.configManager = new ConfigManager();
        LuigiHack.fileManager = new FileManager();
        LuigiHack.friendManager = new FriendManager();
        LuigiHack.potionManager = new PotionManager();
        LuigiHack.inventoryManager = new InventoryManager();
        LuigiHack.newrotationManager = new NewRotationManager();
        LuigiHack.holeManager = new HoleManager();
        LuigiHack.inventoryManagercc = new InventoryManagerCC();
        LuigiHack.notificationManager = new NotificationManager();
        LuigiHack.safetyManager = new SafetyManager();
        LuigiHack.waypointManager = new WaypointManager();
        LuigiHack.LOGGER.info("Initialized Management");
        LuigiHack.moduleManager.init();
        LuigiHack.LOGGER.info("Modules loaded.");
        LuigiHack.configManager.init();
        LuigiHack.eventManager.init();
        LuigiHack.LOGGER.info("EventManager loaded.");
        LuigiHack.textManager.init(true);
        LuigiHack.moduleManager.onLoad();
        LuigiHack.totemPopManager.init();
        if (((RPC)LuigiHack.moduleManager.getModuleByClass((Class)RPC.class)).isEnabled()) {
            DiscordPresence.start();
        }
        LuigiHack.LOGGER.info("luigihack initialized!\n");
    }
    
    public static String getVersion() {
        return "1.8.3";
    }
    
    public static Logger getLogger() {
        return LuigiHack.LOGGER;
    }
    
    public PlayerManager getPlayerManager() {
        return LuigiHack.playerManager;
    }
    
    public static void reload() {
        unload(false);
        load();
    }
    
    @Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent fmlPreInitializationEvent) {
        new ClassManager();
        this.trayIcon.setImageAutoSize(true);
        this.trayIcon.setToolTip("Welcome Thanks for using Luigi Hack");
        try {
            this.tray.add(this.trayIcon);
        }
        catch (AWTException ex) {
            ex.printStackTrace();
        }
        this.trayIcon.displayMessage("Welcome", "Welcome Thanks for using LuigiHack", TrayIcon.MessageType.NONE);
        WorldUtil.Load();
    }
    
    public static void onUnload() {
        if (!LuigiHack.unloaded) {
            LuigiHack.eventManager.onUnload();
            LuigiHack.moduleManager.onUnload();
            LuigiHack.configManager.saveConfig(LuigiHack.configManager.config.replaceFirst("luigihack/", ""));
            LuigiHack.moduleManager.onUnloadPost();
            LuigiHack.timerManager.unload();
            LuigiHack.unloaded = true;
        }
    }
}
