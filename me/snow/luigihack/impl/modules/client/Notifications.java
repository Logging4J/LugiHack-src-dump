//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.client;

import me.snow.luigihack.impl.modules.*;
import net.minecraft.entity.*;
import me.snow.luigihack.api.setting.*;
import java.util.concurrent.*;
import net.minecraft.entity.player.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.gui.*;
import me.snow.luigihack.impl.modules.misc.*;
import me.snow.luigihack.impl.command.*;
import net.minecraftforge.fml.common.eventhandler.*;
import com.mojang.realmsclient.gui.*;
import me.snow.luigihack.api.util.*;
import me.snow.luigihack.*;
import net.minecraft.entity.item.*;
import java.util.stream.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.client.entity.*;
import me.snow.luigihack.api.manager.*;
import net.minecraft.item.*;
import net.minecraft.nbt.*;
import java.util.*;
import java.awt.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraft.util.text.*;
import net.minecraft.util.*;

public class Notifications extends Module
{
    private final /* synthetic */ Set<Entity> llamas;
    public /* synthetic */ Setting<Boolean> burroww;
    private final /* synthetic */ Setting<Boolean> readfile;
    public /* synthetic */ Setting<Boolean> entitynotif;
    private final /* synthetic */ Set<Entity> donkeys;
    public /* synthetic */ Setting<Boolean> visualRange;
    private static /* synthetic */ Notifications INSTANCE;
    public /* synthetic */ Setting<Boolean> leaving;
    public /* synthetic */ Setting<Boolean> coords;
    public final /* synthetic */ Setting<Boolean> thirtytwokay;
    public /* synthetic */ Setting<Boolean> crash;
    public /* synthetic */ Setting<Boolean> Cows;
    public /* synthetic */ Setting<Boolean> Ghasts;
    private final /* synthetic */ ConcurrentHashMap<EntityPlayer, Integer> players2;
    public /* synthetic */ Setting<Boolean> Mules;
    public /* synthetic */ Setting<Boolean> Sound;
    public /* synthetic */ Timer totemAnnounce;
    private /* synthetic */ boolean hasAnnouncedWeakness;
    private final /* synthetic */ Set<Entity> mules;
    public /* synthetic */ Setting<Boolean> deathcorrdsnotif;
    public final /* synthetic */ Setting<Boolean> weakness;
    private final /* synthetic */ TrayIcon icon;
    private final /* synthetic */ Set<Entity> sheep;
    public /* synthetic */ Setting<Boolean> clearOnLogout;
    private /* synthetic */ boolean flag;
    public final /* synthetic */ Setting<Boolean> slowness;
    private final /* synthetic */ Set<EntityPlayer> sword;
    public /* synthetic */ Setting<Boolean> totemPops;
    public /* synthetic */ Setting<Boolean> list;
    public /* synthetic */ Setting<Boolean> Donkeys;
    public /* synthetic */ Setting<Boolean> Desktop;
    public /* synthetic */ Setting<Boolean> VisualRangeSound;
    private final /* synthetic */ Timer timer;
    /* synthetic */ List<Entity> burrowedPlayers;
    public /* synthetic */ Setting<Boolean> Sheep;
    public /* synthetic */ Setting<Boolean> pearlnotif;
    public /* synthetic */ Setting<Boolean> popUp;
    private /* synthetic */ boolean check;
    private /* synthetic */ boolean hasAnnouncedSlowness;
    private final /* synthetic */ Set<Entity> cow;
    public /* synthetic */ Setting<Boolean> totemNoti;
    private final /* synthetic */ Image image;
    private /* synthetic */ List<EntityPlayer> knownPlayers;
    public /* synthetic */ Setting<Boolean> Chat;
    public /* synthetic */ Setting<Boolean> moduleMessage;
    /* synthetic */ List<EntityPlayer> anti_spam;
    private final /* synthetic */ Set<Entity> ghasts;
    private static final /* synthetic */ List<String> modules;
    public /* synthetic */ Setting<Boolean> Llamas;
    public /* synthetic */ Setting<Integer> delay;
    
    @SubscribeEvent
    public void onDisplayDeathScreen(final GuiOpenEvent guiOpenEvent) {
        if ((boolean)this.deathcorrdsnotif.getValue() && guiOpenEvent.getGui() instanceof GuiGameOver && guiOpenEvent.getGui() instanceof GuiGameOver) {
            Command.sendMessage(String.format("You died at x %d y %d z %d", (int)AutoRespawn.mc.player.posX, (int)AutoRespawn.mc.player.posY, (int)AutoRespawn.mc.player.posZ));
        }
    }
    
    public TextComponentString getNotifierOff(final Module module) {
        if (ModuleTools.getInstance().isEnabled()) {
            switch ((ModuleTools.Notifier)ModuleTools.getInstance().notifier.getValue()) {
                case NEWLUIGI: {
                    return new TextComponentString(String.valueOf(new StringBuilder().append(ChatFormatting.GREEN).append("[LuigiHack] ").append(ChatFormatting.GRAY).append(module.getDisplayName()).append(" toggled ").append(ChatFormatting.RED).append("off").append(ChatFormatting.GRAY).append(".")));
                }
                case FUTURE: {
                    return new TextComponentString(String.valueOf(new StringBuilder().append(ChatFormatting.RED).append("[Future] ").append(ChatFormatting.GRAY).append(module.getDisplayName()).append(" toggled ").append(ChatFormatting.RED).append("off").append(ChatFormatting.GRAY).append(".")));
                }
                case SNOW: {
                    return new TextComponentString(String.valueOf(new StringBuilder().append(ChatFormatting.BLUE).append("[").append(ChatFormatting.AQUA).append("Snow").append(ChatFormatting.BLUE).append("] [").append(ChatFormatting.DARK_AQUA).append(module.getDisplayName()).append(ChatFormatting.BLUE).append("] ").append(ChatFormatting.RED).append("disabled")));
                }
                case MUFFIN: {
                    return new TextComponentString(String.valueOf(new StringBuilder().append(ChatFormatting.LIGHT_PURPLE).append("[").append(ChatFormatting.DARK_PURPLE).append("Muffin").append(ChatFormatting.LIGHT_PURPLE).append("] ").append(ChatFormatting.LIGHT_PURPLE).append(module.getDisplayName()).append(ChatFormatting.DARK_PURPLE).append(" was ").append(ChatFormatting.RED).append("disabled.")));
                }
                case BOLD: {
                    return new TextComponentString(String.valueOf(new StringBuilder().append(ChatFormatting.BOLD).append(module.getDisplayName()).append(ChatFormatting.RESET).append(ChatFormatting.RED).append(" disabled.")));
                }
                case ABYSS: {
                    return new TextComponentString(String.valueOf(new StringBuilder().append(TextUtil.coloredString("[Abyss] ", (TextUtil.Color)ModuleTools.getInstance().Abysscolorrr.getPlannedValue())).append(ChatFormatting.WHITE).append(module.getDisplayName()).append(ChatFormatting.RED).append(" OFF")));
                }
                case OYVEY: {
                    return new TextComponentString(String.valueOf(new StringBuilder().append(LuigiHack.commandManager.getClientMessage()).append(" ").append(ChatFormatting.RED).append(module.getDisplayName()).append(" disabled.")));
                }
            }
        }
        return new TextComponentString(String.valueOf(new StringBuilder().append(LuigiHack.commandManager.getClientMessage()).append(ChatFormatting.RESET).append(" ").append(module.getDisplayName()).append(ChatFormatting.RED).append(" disabled")));
    }
    
    @Override
    public void onUpdate() {
        if (this.readfile.getValue()) {
            if (!this.check) {
                Command.sendMessage("Loading File...");
                this.timer.reset();
                this.loadFile();
            }
            this.check = true;
        }
        if (this.check && this.timer.passedMs(750L)) {
            this.readfile.setValue((Object)false);
            this.check = false;
        }
        if (this.visualRange.getValue()) {
            final ArrayList<EntityPlayer> list = new ArrayList<EntityPlayer>(Notifications.mc.world.playerEntities);
            if (list.size() > 0) {
                for (final EntityPlayer entityPlayer : list) {
                    if (!entityPlayer.getName().equals(Notifications.mc.player.getName())) {
                        if (this.knownPlayers.contains(entityPlayer)) {
                            continue;
                        }
                        this.knownPlayers.add(entityPlayer);
                        if (LuigiHack.friendManager.isFriend(entityPlayer)) {
                            Command.sendMessage(String.valueOf(new StringBuilder().append("Your Friend ").append(ChatFormatting.AQUA).append(entityPlayer.getName()).append("§r entered your visual range").append(this.coords.getValue() ? String.valueOf(new StringBuilder().append(" at (").append((int)entityPlayer.posX).append(", ").append((int)entityPlayer.posY).append(", ").append((int)entityPlayer.posZ).append(")!")) : "!")), (boolean)this.popUp.getValue());
                        }
                        else {
                            Command.sendMessage(String.valueOf(new StringBuilder().append("Player ").append(ChatFormatting.RED).append(entityPlayer.getName()).append("§r").append(ChatFormatting.RED).append(" entered ").append(ChatFormatting.RESET).append("your visual range").append(this.coords.getValue() ? String.valueOf(new StringBuilder().append(" at (").append((int)entityPlayer.posX).append(", ").append((int)entityPlayer.posY).append(", ").append((int)entityPlayer.posZ).append(")!")) : "!")), (boolean)this.popUp.getValue());
                        }
                        if (this.VisualRangeSound.getValue()) {
                            Notifications.mc.player.playSound(SoundEvents.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                        }
                        return;
                    }
                }
            }
            if (this.knownPlayers.size() > 0) {
                for (final EntityPlayer o : this.knownPlayers) {
                    if (list.contains(o)) {
                        continue;
                    }
                    this.knownPlayers.remove(o);
                    if (this.leaving.getValue()) {
                        if (LuigiHack.friendManager.isFriend(o)) {
                            Command.sendMessage(String.valueOf(new StringBuilder().append("Your Friend ").append(ChatFormatting.AQUA).append(o.getName()).append("§r left your visual range").append(this.coords.getValue() ? String.valueOf(new StringBuilder().append(" at (").append((int)o.posX).append(", ").append((int)o.posY).append(", ").append((int)o.posZ).append(")!")) : "!")), (boolean)this.popUp.getValue());
                        }
                        else {
                            Command.sendMessage(String.valueOf(new StringBuilder().append("Player ").append(ChatFormatting.RED).append(o.getName()).append("§r").append(ChatFormatting.GREEN).append(" left ").append(ChatFormatting.RESET).append("your visual range").append(this.coords.getValue() ? String.valueOf(new StringBuilder().append(" at (").append((int)o.posX).append(", ").append((int)o.posY).append(", ").append((int)o.posZ).append(")!")) : "!")), (boolean)this.popUp.getValue());
                        }
                    }
                    return;
                }
            }
        }
        if (this.pearlnotif.getValue()) {
            if (Notifications.mc.world == null || Notifications.mc.player == null) {
                return;
            }
            Entity entity2 = null;
            for (final Entity entity3 : Notifications.mc.world.loadedEntityList) {
                if (entity3 instanceof EntityEnderPearl) {
                    entity2 = entity3;
                    break;
                }
            }
            if (entity2 == null) {
                this.flag = true;
                return;
            }
            EntityPlayerSP entityPlayerSP = null;
            for (final EntityPlayer entityPlayer2 : Notifications.mc.world.playerEntities) {
                if (entityPlayerSP == null) {
                    entityPlayerSP = (EntityPlayerSP)entityPlayer2;
                }
                else {
                    if (((EntityPlayer)entityPlayerSP).getDistance(entity2) <= entityPlayer2.getDistance(entity2)) {
                        continue;
                    }
                    entityPlayerSP = (EntityPlayerSP)entityPlayer2;
                }
            }
            if (entityPlayerSP == Notifications.mc.player) {
                this.flag = false;
            }
            if (entityPlayerSP != null && this.flag) {
                String string = entity2.getHorizontalFacing().toString();
                if (string.equals("west")) {
                    string = "east";
                }
                else if (string.equals("east")) {
                    string = "west";
                }
                Command.sendMessage(LuigiHack.friendManager.isFriend(((EntityPlayer)entityPlayerSP).getName()) ? String.valueOf(new StringBuilder().append(ChatFormatting.AQUA).append(((EntityPlayer)entityPlayerSP).getName()).append(ChatFormatting.DARK_GRAY).append(" has just thrown a pearl heading ").append(string).append("!")) : String.valueOf(new StringBuilder().append(ChatFormatting.RED).append(((EntityPlayer)entityPlayerSP).getName()).append(ChatFormatting.DARK_GRAY).append(" has just thrown a pearl heading ").append(string).append("!")));
                this.flag = false;
            }
        }
        if (this.burroww.getValue()) {
            if (Notifications.mc.player == null || Notifications.mc.world == null) {
                return;
            }
            for (final Entity entity4 : (List)Notifications.mc.world.loadedEntityList.stream().filter(entity -> entity instanceof EntityPlayer).collect(Collectors.toList())) {
                if (!(entity4 instanceof EntityPlayer)) {
                    continue;
                }
                if (!this.burrowedPlayers.contains(entity4) && this.isBurrowed(entity4)) {
                    this.burrowedPlayers.add(entity4);
                    Command.sendMessage(String.valueOf(new StringBuilder().append(ChatFormatting.RED).append(entity4.getName()).append(" has just burrowed!")));
                }
                else {
                    if (!this.burrowedPlayers.contains(entity4) || this.isBurrowed(entity4)) {
                        continue;
                    }
                    this.burrowedPlayers.remove(entity4);
                    Command.sendMessage(String.valueOf(new StringBuilder().append(ChatFormatting.GREEN).append(entity4.getName()).append(" is no longer burrowed!")));
                }
            }
        }
        if (this.weakness.getValue()) {
            if (Notifications.mc.player.isPotionActive(MobEffects.WEAKNESS) && !this.hasAnnouncedWeakness) {
                Command.sendMessage(String.valueOf(new StringBuilder().append("You now have ").append(ChatFormatting.RED).append(ChatFormatting.BOLD).append("Weakness").append(ChatFormatting.RESET).append("!")));
                this.hasAnnouncedWeakness = true;
            }
            if (!Notifications.mc.player.isPotionActive(MobEffects.WEAKNESS) && this.hasAnnouncedWeakness) {
                Command.sendMessage(String.valueOf(new StringBuilder().append("You no longer have ").append(ChatFormatting.RED).append(ChatFormatting.BOLD).append("Weakness").append(ChatFormatting.RESET).append("!")));
                this.hasAnnouncedWeakness = false;
            }
        }
        if (this.slowness.getValue()) {
            if (Notifications.mc.player.isPotionActive(MobEffects.SLOWNESS) && !this.hasAnnouncedSlowness) {
                Command.sendMessage(String.valueOf(new StringBuilder().append("You now have ").append(ChatFormatting.DARK_RED).append(ChatFormatting.BOLD).append("Slowness").append(ChatFormatting.RESET).append("!")));
                this.hasAnnouncedSlowness = true;
            }
            if (!Notifications.mc.player.isPotionActive(MobEffects.SLOWNESS) && this.hasAnnouncedSlowness) {
                Command.sendMessage(String.valueOf(new StringBuilder().append("You no longer have ").append(ChatFormatting.DARK_RED).append(ChatFormatting.BOLD).append("Slowness").append(ChatFormatting.RESET).append("!")));
                this.hasAnnouncedSlowness = false;
            }
        }
        if ((boolean)this.entitynotif.getValue() && (boolean)this.Ghasts.getValue()) {
            for (final Entity entity5 : Notifications.mc.world.getLoadedEntityList()) {
                if (entity5 instanceof EntityGhast) {
                    if (this.ghasts.contains(entity5)) {
                        continue;
                    }
                    if (this.Chat.getValue()) {
                        Command.sendMessage(String.valueOf(new StringBuilder().append("Ghast Detected at: ").append(Math.round((float)entity5.getPosition().getX())).append("x, ").append(Math.round((float)entity5.getPosition().getY())).append("y, ").append(Math.round((float)entity5.getPosition().getZ())).append("z.")));
                    }
                    this.ghasts.add(entity5);
                    if (!(boolean)this.Desktop.getValue()) {
                        continue;
                    }
                    this.icon.displayMessage("WeatherHack", String.valueOf(new StringBuilder().append("I found a ghast at: ").append(Math.round((float)entity5.getPosition().getX())).append("x, ").append(Math.round((float)entity5.getPosition().getY())).append("y, ").append(Math.round((float)entity5.getPosition().getZ())).append("z.")), TrayIcon.MessageType.INFO);
                    if (!(boolean)this.Sound.getValue()) {
                        continue;
                    }
                    Notifications.mc.player.playSound(SoundEvents.BLOCK_ANVIL_DESTROY, 1.0f, 1.0f);
                }
            }
        }
        if ((boolean)this.entitynotif.getValue() && (boolean)this.Donkeys.getValue()) {
            for (final Entity entity6 : Notifications.mc.world.getLoadedEntityList()) {
                if (entity6 instanceof EntityDonkey) {
                    if (this.donkeys.contains(entity6)) {
                        continue;
                    }
                    if (this.Chat.getValue()) {
                        Command.sendMessage(String.valueOf(new StringBuilder().append("Donkey Detected at: ").append(Math.round((float)entity6.getPosition().getX())).append("x, ").append(Math.round((float)entity6.getPosition().getY())).append("y, ").append(Math.round((float)entity6.getPosition().getZ())).append("z.")));
                    }
                    this.donkeys.add(entity6);
                    if (!(boolean)this.Desktop.getValue()) {
                        continue;
                    }
                    this.icon.displayMessage("WeatherHack", String.valueOf(new StringBuilder().append("I found a donkey at: ").append(Math.round((float)entity6.getPosition().getX())).append("x, ").append(Math.round((float)entity6.getPosition().getY())).append("y, ").append(Math.round((float)entity6.getPosition().getZ())).append("z.")), TrayIcon.MessageType.INFO);
                    if (!(boolean)this.Sound.getValue()) {
                        continue;
                    }
                    Notifications.mc.player.playSound(SoundEvents.BLOCK_ANVIL_DESTROY, 1.0f, 1.0f);
                }
            }
        }
        if ((boolean)this.entitynotif.getValue() && (boolean)this.Mules.getValue()) {
            for (final Entity entity7 : Notifications.mc.world.getLoadedEntityList()) {
                if (entity7 instanceof EntityMule) {
                    if (this.mules.contains(entity7)) {
                        continue;
                    }
                    if (this.Chat.getValue()) {
                        Command.sendMessage(String.valueOf(new StringBuilder().append("Mule Detected at: ").append(Math.round((float)entity7.getPosition().getX())).append("x, ").append(Math.round((float)entity7.getPosition().getY())).append("y, ").append(Math.round((float)entity7.getPosition().getZ())).append("z.")));
                    }
                    this.mules.add(entity7);
                    if (!(boolean)this.Desktop.getValue()) {
                        continue;
                    }
                    this.icon.displayMessage("WeatherHack", String.valueOf(new StringBuilder().append("I found a mule at: ").append(Math.round((float)entity7.getPosition().getX())).append("x, ").append(Math.round((float)entity7.getPosition().getY())).append("y, ").append(Math.round((float)entity7.getPosition().getZ())).append("z.")), TrayIcon.MessageType.INFO);
                    if (!(boolean)this.Sound.getValue()) {
                        continue;
                    }
                    Notifications.mc.player.playSound(SoundEvents.BLOCK_ANVIL_DESTROY, 1.0f, 1.0f);
                }
            }
        }
        if ((boolean)this.entitynotif.getValue() && (boolean)this.Llamas.getValue()) {
            for (final Entity entity8 : Notifications.mc.world.getLoadedEntityList()) {
                if (entity8 instanceof EntityLlama) {
                    if (this.llamas.contains(entity8)) {
                        continue;
                    }
                    if (this.Chat.getValue()) {
                        Command.sendMessage(String.valueOf(new StringBuilder().append("Llama Detected at: ").append(Math.round((float)entity8.getPosition().getX())).append("x, ").append(Math.round((float)entity8.getPosition().getY())).append("y, ").append(Math.round((float)entity8.getPosition().getZ())).append("z.")));
                    }
                    this.llamas.add(entity8);
                    if (!(boolean)this.Desktop.getValue()) {
                        continue;
                    }
                    this.icon.displayMessage("WeatherHack", String.valueOf(new StringBuilder().append("I found a llama at: ").append(Math.round((float)entity8.getPosition().getX())).append("x, ").append(Math.round((float)entity8.getPosition().getY())).append("y, ").append(Math.round((float)entity8.getPosition().getZ())).append("z.")), TrayIcon.MessageType.INFO);
                    if (!(boolean)this.Sound.getValue()) {
                        continue;
                    }
                    Notifications.mc.player.playSound(SoundEvents.BLOCK_ANVIL_DESTROY, 1.0f, 1.0f);
                }
            }
        }
        if ((boolean)this.entitynotif.getValue() && (boolean)this.Cows.getValue()) {
            for (final Entity entity9 : Notifications.mc.world.getLoadedEntityList()) {
                if (entity9 instanceof EntityCow) {
                    if (this.cow.contains(entity9)) {
                        continue;
                    }
                    if (this.Chat.getValue()) {
                        Command.sendMessage(String.valueOf(new StringBuilder().append("Cow Detected at: ").append(Math.round((float)entity9.getPosition().getX())).append("x, ").append(Math.round((float)entity9.getPosition().getY())).append("y, ").append(Math.round((float)entity9.getPosition().getZ())).append("z.")));
                    }
                    this.cow.add(entity9);
                    if (!(boolean)this.Desktop.getValue()) {
                        continue;
                    }
                    this.icon.displayMessage("WeatherHack", String.valueOf(new StringBuilder().append("I found a cow at: ").append(Math.round((float)entity9.getPosition().getX())).append("x, ").append(Math.round((float)entity9.getPosition().getY())).append("y, ").append(Math.round((float)entity9.getPosition().getZ())).append("z.")), TrayIcon.MessageType.INFO);
                    if (!(boolean)this.Sound.getValue()) {
                        continue;
                    }
                    Notifications.mc.player.playSound(SoundEvents.BLOCK_ANVIL_DESTROY, 1.0f, 1.0f);
                }
            }
        }
        if ((boolean)this.entitynotif.getValue() && (boolean)this.Sheep.getValue()) {
            for (final Entity entity10 : Notifications.mc.world.getLoadedEntityList()) {
                if (entity10 instanceof EntitySheep) {
                    if (this.sheep.contains(entity10)) {
                        continue;
                    }
                    if (this.Chat.getValue()) {
                        Command.sendMessage(String.valueOf(new StringBuilder().append("Sheep Detected at: ").append(Math.round((float)entity10.getPosition().getX())).append("x, ").append(Math.round((float)entity10.getPosition().getY())).append("y, ").append(Math.round((float)entity10.getPosition().getZ())).append("z.")));
                    }
                    this.sheep.add(entity10);
                    if (!(boolean)this.Desktop.getValue()) {
                        continue;
                    }
                    this.icon.displayMessage("WeatherHack", String.valueOf(new StringBuilder().append("I found a Sheep at: ").append(Math.round((float)entity10.getPosition().getX())).append("x, ").append(Math.round((float)entity10.getPosition().getY())).append("y, ").append(Math.round((float)entity10.getPosition().getZ())).append("z.")), TrayIcon.MessageType.INFO);
                    if (!(boolean)this.Sound.getValue()) {
                        continue;
                    }
                    Notifications.mc.player.playSound(SoundEvents.BLOCK_ANVIL_DESTROY, 1.0f, 1.0f);
                }
            }
        }
    }
    
    public void loadFile() {
        final Iterator<String> iterator = FileManager.readTextFileAllLines("luigihack/util/ModuleMessage_List.txt").iterator();
        Notifications.modules.clear();
        while (iterator.hasNext()) {
            final String s = iterator.next();
            if (s.replaceAll("\\s", "").isEmpty()) {
                continue;
            }
            Notifications.modules.add(s);
        }
    }
    
    private boolean is32k(final ItemStack itemStack) {
        if (itemStack.getItem() instanceof ItemSword) {
            final NBTTagList getEnchantmentTagList = itemStack.getEnchantmentTagList();
            for (int i = 0; i < getEnchantmentTagList.tagCount(); ++i) {
                if (getEnchantmentTagList.getCompoundTagAt(i).getShort("lvl") >= 32767) {
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public void onEnable() {
        this.knownPlayers = new ArrayList<EntityPlayer>();
        if (!this.check) {
            this.loadFile();
        }
        this.flag = true;
        this.players2.clear();
        this.anti_spam.clear();
    }
    
    public Notifications() {
        super("Notifications", "Sends Messages.", Category.CLIENT, true, false, false);
        this.players2 = new ConcurrentHashMap<EntityPlayer, Integer>();
        this.sword = Collections.newSetFromMap(new WeakHashMap<EntityPlayer, Boolean>());
        this.anti_spam = new ArrayList<EntityPlayer>();
        this.burrowedPlayers = new ArrayList<Entity>();
        this.hasAnnouncedWeakness = false;
        this.hasAnnouncedSlowness = false;
        this.ghasts = new HashSet<Entity>();
        this.donkeys = new HashSet<Entity>();
        this.mules = new HashSet<Entity>();
        this.llamas = new HashSet<Entity>();
        this.cow = new HashSet<Entity>();
        this.sheep = new HashSet<Entity>();
        this.image = Toolkit.getDefaultToolkit().getImage("resources/LuigiA.png");
        this.icon = new TrayIcon(this.image, "LuigiHack");
        this.timer = new Timer();
        this.totemPops = (Setting<Boolean>)this.register(new Setting("TotemPops", (Object)false));
        this.totemNoti = (Setting<Boolean>)this.register(new Setting("TotemNoti", (Object)Boolean.TRUE, p0 -> (boolean)this.totemPops.getValue()));
        this.delay = (Setting<Integer>)this.register(new Setting("Delay", (Object)2000, (Object)0, (Object)5000, p0 -> (boolean)this.totemPops.getValue(), "Delays messages."));
        this.clearOnLogout = (Setting<Boolean>)this.register(new Setting("LogoutClear", (Object)false));
        this.moduleMessage = (Setting<Boolean>)this.register(new Setting("ModuleMessage", (Object)true));
        this.list = (Setting<Boolean>)this.register(new Setting("List", (Object)Boolean.FALSE, p0 -> (boolean)this.moduleMessage.getValue()));
        this.readfile = (Setting<Boolean>)this.register(new Setting("LoadFile", (Object)false, p0 -> (boolean)this.moduleMessage.getValue()));
        this.visualRange = (Setting<Boolean>)this.register(new Setting("VisualRange", (Object)false));
        this.VisualRangeSound = (Setting<Boolean>)this.register(new Setting("VisualRangeSound", (Object)false));
        this.coords = (Setting<Boolean>)this.register(new Setting("Coords", (Object)Boolean.TRUE, p0 -> (boolean)this.visualRange.getValue()));
        this.leaving = (Setting<Boolean>)this.register(new Setting("Leaving", (Object)Boolean.FALSE, p0 -> (boolean)this.visualRange.getValue()));
        this.popUp = (Setting<Boolean>)this.register(new Setting("PopUpVisualRange", (Object)false));
        this.pearlnotif = (Setting<Boolean>)this.register(new Setting("PearlNotif", (Object)false));
        this.burroww = (Setting<Boolean>)this.register(new Setting("Burrow", (Object)false));
        this.deathcorrdsnotif = (Setting<Boolean>)this.register(new Setting("DeathCoords", (Object)false));
        this.weakness = (Setting<Boolean>)this.register(new Setting("Weakness", (Object)true));
        this.slowness = (Setting<Boolean>)this.register(new Setting("Slowness", (Object)true));
        this.thirtytwokay = (Setting<Boolean>)this.register(new Setting("32kAlert", (Object)false));
        this.crash = (Setting<Boolean>)this.register(new Setting("Crash", (Object)false));
        this.entitynotif = (Setting<Boolean>)this.register(new Setting("EntityNotifier", (Object)false));
        this.Chat = (Setting<Boolean>)this.register(new Setting("Chat", (Object)true, p0 -> (boolean)this.entitynotif.getValue()));
        this.Sound = (Setting<Boolean>)this.register(new Setting("Sound", (Object)true, p0 -> (boolean)this.entitynotif.getValue()));
        this.Desktop = (Setting<Boolean>)this.register(new Setting("DesktopNotifs", (Object)false, p0 -> (boolean)this.entitynotif.getValue()));
        this.Ghasts = (Setting<Boolean>)this.register(new Setting("Ghasts", (Object)true, p0 -> (boolean)this.entitynotif.getValue()));
        this.Cows = (Setting<Boolean>)this.register(new Setting("Cow", (Object)false, p0 -> (boolean)this.entitynotif.getValue()));
        this.Sheep = (Setting<Boolean>)this.register(new Setting("Sheep", (Object)false, p0 -> (boolean)this.entitynotif.getValue()));
        this.Donkeys = (Setting<Boolean>)this.register(new Setting("Donkeys", (Object)false, p0 -> (boolean)this.entitynotif.getValue()));
        this.Mules = (Setting<Boolean>)this.register(new Setting("Mules", (Object)false, p0 -> (boolean)this.entitynotif.getValue()));
        this.Llamas = (Setting<Boolean>)this.register(new Setting("Llamas", (Object)false, p0 -> (boolean)this.entitynotif.getValue()));
        this.totemAnnounce = new Timer();
        this.knownPlayers = new ArrayList<EntityPlayer>();
        this.setInstance();
        this.icon.setImageAutoSize(true);
        try {
            SystemTray.getSystemTray().add(this.icon);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private boolean isBurrowed(final Entity entity) {
        final BlockPos blockPos = new BlockPos(this.roundValueToCenter(entity.posX), entity.posY + 0.2, this.roundValueToCenter(entity.posZ));
        return Notifications.mc.world.getBlockState(blockPos).getBlock() == Blocks.OBSIDIAN || Notifications.mc.world.getBlockState(blockPos).getBlock() == Blocks.ENDER_CHEST;
    }
    
    static {
        fileName = "luigihack/util/ModuleMessage_List.txt";
        modules = new ArrayList<String>();
        Notifications.INSTANCE = new Notifications();
    }
    
    public TextComponentString getNotifierOn(final Module module) {
        if (ModuleTools.getInstance().isEnabled()) {
            switch ((ModuleTools.Notifier)ModuleTools.getInstance().notifier.getValue()) {
                case NEWLUIGI: {
                    return new TextComponentString(String.valueOf(new StringBuilder().append(ChatFormatting.GREEN).append("[LuigiHack] ").append(ChatFormatting.GRAY).append(module.getDisplayName()).append(" toggled ").append(ChatFormatting.GREEN).append("on").append(ChatFormatting.GRAY).append(".")));
                }
                case FUTURE: {
                    return new TextComponentString(String.valueOf(new StringBuilder().append(ChatFormatting.RED).append("[Future] ").append(ChatFormatting.GRAY).append(module.getDisplayName()).append(" toggled ").append(ChatFormatting.GREEN).append("on").append(ChatFormatting.GRAY).append(".")));
                }
                case SNOW: {
                    return new TextComponentString(String.valueOf(new StringBuilder().append(ChatFormatting.BLUE).append("[").append(ChatFormatting.AQUA).append("Snow").append(ChatFormatting.BLUE).append("] [").append(ChatFormatting.DARK_AQUA).append(module.getDisplayName()).append(ChatFormatting.BLUE).append("] ").append(ChatFormatting.GREEN).append("enabled")));
                }
                case MUFFIN: {
                    return new TextComponentString(String.valueOf(new StringBuilder().append(ChatFormatting.LIGHT_PURPLE).append("[").append(ChatFormatting.DARK_PURPLE).append("Muffin").append(ChatFormatting.LIGHT_PURPLE).append("] ").append(ChatFormatting.LIGHT_PURPLE).append(module.getDisplayName()).append(ChatFormatting.DARK_PURPLE).append(" was ").append(ChatFormatting.GREEN).append("enabled.")));
                }
                case ABYSS: {
                    return new TextComponentString(String.valueOf(new StringBuilder().append(TextUtil.coloredString("[Abyss] ", (TextUtil.Color)ModuleTools.getInstance().Abysscolorrr.getPlannedValue())).append(ChatFormatting.WHITE).append(module.getDisplayName()).append(ChatFormatting.GREEN).append(" ON")));
                }
                case BOLD: {
                    return new TextComponentString(String.valueOf(new StringBuilder().append(ChatFormatting.BOLD).append(module.getDisplayName()).append(ChatFormatting.RESET).append(ChatFormatting.GREEN).append(" enabled.")));
                }
                case OYVEY: {
                    return new TextComponentString(String.valueOf(new StringBuilder().append(LuigiHack.commandManager.getClientMessage()).append(" ").append(ChatFormatting.GREEN).append(module.getDisplayName()).append(" enabled.")));
                }
            }
        }
        return new TextComponentString(String.valueOf(new StringBuilder().append(LuigiHack.commandManager.getClientMessage()).append(ChatFormatting.RESET).append(" ").append(module.getDisplayName()).append(ChatFormatting.GREEN).append(" enabled")));
    }
    
    private void setInstance() {
        Notifications.INSTANCE = this;
    }
    
    public static Notifications getInstance() {
        if (Notifications.INSTANCE == null) {
            Notifications.INSTANCE = new Notifications();
        }
        return Notifications.INSTANCE;
    }
    
    @SubscribeEvent
    public void onToggleModule(final ClientEvent clientEvent) {
        if (!(boolean)this.moduleMessage.getValue()) {
            return;
        }
        final Module module;
        if (clientEvent.getStage() == 0 && !(module = (Module)clientEvent.getFeature()).equals(this) && (Notifications.modules.contains(module.getDisplayName()) || !(boolean)this.list.getValue())) {
            int n = 0;
            final char[] charArray = module.getDisplayName().toCharArray();
            for (int length = charArray.length, i = 0; i < length; ++i) {
                n = (n + charArray[i]) * 10;
            }
            Notifications.mc.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion((ITextComponent)this.getNotifierOff(module), n);
        }
        final Module module2;
        if (clientEvent.getStage() == 1 && (Notifications.modules.contains((module2 = (Module)clientEvent.getFeature()).getDisplayName()) || !(boolean)this.list.getValue())) {
            int n2 = 0;
            final char[] charArray2 = module2.getDisplayName().toCharArray();
            for (int length2 = charArray2.length, j = 0; j < length2; ++j) {
                n2 = (n2 + charArray2[j]) * 10;
            }
            Notifications.mc.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion((ITextComponent)this.getNotifierOn(module2), n2);
        }
    }
    
    public static void displayCrash(final Exception ex) {
        Command.sendMessage(String.valueOf(new StringBuilder().append("§cException caught: ").append(ex.getMessage())));
    }
    
    private double roundValueToCenter(final double a) {
        double n = (double)Math.round(a);
        if (n > a) {
            n -= 0.5;
        }
        else if (n <= a) {
            n += 0.5;
        }
        return n;
    }
    
    @Override
    public void onLoad() {
        this.check = true;
        this.loadFile();
        this.check = false;
    }
    
    @Override
    public void onTick() {
        if (fullNullCheck()) {
            return;
        }
        if (this.thirtytwokay.getValue()) {
            int n = 0;
            for (final EntityPlayer entityPlayer : Notifications.mc.world.playerEntities) {
                if (entityPlayer.equals((Object)Notifications.mc.player)) {
                    continue;
                }
                if (this.is32k(entityPlayer.getHeldItem(EnumHand.MAIN_HAND)) && !this.sword.contains(entityPlayer)) {
                    Command.sendMessage(String.valueOf(new StringBuilder().append(ChatFormatting.RED).append(entityPlayer.getDisplayNameString()).append(" is holding a 32k")));
                    this.sword.add(entityPlayer);
                }
                if (this.is32k(entityPlayer.getHeldItem(EnumHand.MAIN_HAND))) {
                    if (n > 0) {
                        return;
                    }
                    ++n;
                }
                if (!this.sword.contains(entityPlayer)) {
                    continue;
                }
                if (this.is32k(entityPlayer.getHeldItem(EnumHand.MAIN_HAND))) {
                    continue;
                }
                Command.sendMessage(String.valueOf(new StringBuilder().append(ChatFormatting.GREEN).append(entityPlayer.getDisplayNameString()).append(" is no longer holding a 32k")));
                this.sword.remove(entityPlayer);
            }
        }
    }
}
