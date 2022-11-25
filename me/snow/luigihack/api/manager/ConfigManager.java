//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.manager;

import me.snow.luigihack.api.util.*;
import me.snow.luigihack.impl.*;
import java.nio.file.attribute.*;
import java.nio.file.*;
import java.util.stream.*;
import me.snow.luigihack.*;
import me.snow.luigihack.api.setting.*;
import com.google.gson.*;
import java.io.*;
import java.util.*;
import me.snow.luigihack.impl.modules.*;

public class ConfigManager implements Util
{
    public /* synthetic */ boolean loadingConfig;
    public /* synthetic */ boolean savingConfig;
    public /* synthetic */ ArrayList<Feature> features;
    public /* synthetic */ String config;
    
    public void saveSettings(final Feature feature) throws IOException {
        new JsonObject();
        final File file = new File(String.valueOf(new StringBuilder().append(this.config).append(this.getDirectory(feature))));
        if (!file.exists()) {
            file.mkdir();
        }
        final Path value;
        if (!Files.exists(value = Paths.get(String.valueOf(new StringBuilder().append(this.config).append(this.getDirectory(feature)).append(feature.getName()).append(".json")), new String[0]), new LinkOption[0])) {
            Files.createFile(value, (FileAttribute<?>[])new FileAttribute[0]);
        }
        final String json = new GsonBuilder().setPrettyPrinting().create().toJson((JsonElement)this.writeSettings(feature));
        final BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(value, new OpenOption[0])));
        bufferedWriter.write(json);
        bufferedWriter.close();
    }
    
    public String loadCurrentConfig() {
        final File source = new File("luigihack/currentconfig.txt");
        String nextLine = "config";
        try {
            if (source.exists()) {
                final Scanner scanner = new Scanner(source);
                while (scanner.hasNextLine()) {
                    nextLine = scanner.nextLine();
                }
                scanner.close();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return nextLine;
    }
    
    public void saveCurrentConfig() {
        final File file = new File("luigihack/currentconfig.txt");
        try {
            if (file.exists()) {
                final FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(this.config.replaceAll("/", "").replaceAll("luigihack", ""));
                fileWriter.close();
            }
            else {
                file.createNewFile();
                final FileWriter fileWriter2 = new FileWriter(file);
                fileWriter2.write(this.config.replaceAll("/", "").replaceAll("luigihack", ""));
                fileWriter2.close();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void loadConfig(final String s) {
        this.loadingConfig = true;
        this.config = (Arrays.stream((Object[])Objects.requireNonNull((T[])new File("luigihack").listFiles())).filter(File::isDirectory).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList()).contains(new File(String.valueOf(new StringBuilder().append("luigihack/").append(s).append("/")))) ? String.valueOf(new StringBuilder().append("luigihack/").append(s).append("/")) : "luigihack/config/");
        LuigiHack.friendManager.onLoad();
        for (final Feature feature : this.features) {
            try {
                this.loadSettings(feature);
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        this.saveCurrentConfig();
        this.loadingConfig = false;
    }
    
    private static void loadFile(final JsonObject jsonObject, final Feature feature) {
        for (final Map.Entry<String, V> entry : jsonObject.entrySet()) {
            final String name = entry.getKey();
            final JsonElement jsonElement = (JsonElement)entry.getValue();
            if (feature instanceof FriendManager) {
                try {
                    LuigiHack.friendManager.addFriend(new FriendManager.Friend(jsonElement.getAsString(), UUID.fromString(name)));
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            else {
                boolean b = false;
                for (final Setting setting : feature.getSettings()) {
                    if (!name.equals(setting.getName())) {
                        continue;
                    }
                    try {
                        setValueFromJson(feature, setting, jsonElement);
                    }
                    catch (Exception ex2) {
                        ex2.printStackTrace();
                    }
                    b = true;
                }
                if (b) {
                    continue;
                }
                continue;
            }
        }
    }
    
    private void loadSettings(final Feature feature) throws IOException {
        final Path value = Paths.get(String.valueOf(new StringBuilder().append(this.config).append(this.getDirectory(feature)).append(feature.getName()).append(".json")), new String[0]);
        if (!Files.exists(value, new LinkOption[0])) {
            return;
        }
        this.loadPath(value, feature);
    }
    
    public void resetConfig(final boolean b, final String s) {
        final Iterator<Feature> iterator = this.features.iterator();
        while (iterator.hasNext()) {
            iterator.next().reset();
        }
        if (b) {
            this.saveConfig(s);
        }
    }
    
    public static void setValueFromJson(final Feature feature, final Setting setting, final JsonElement jsonElement) {
        final String type = setting.getType();
        switch (type) {
            case "Boolean": {
                setting.setValue(jsonElement.getAsBoolean());
                break;
            }
            case "Double": {
                setting.setValue(jsonElement.getAsDouble());
                break;
            }
            case "Float": {
                setting.setValue(jsonElement.getAsFloat());
                break;
            }
            case "Integer": {
                setting.setValue(jsonElement.getAsInt());
                break;
            }
            case "String": {
                setting.setValue(jsonElement.getAsString().replace("_", " "));
                break;
            }
            case "Bind": {
                setting.setValue(new Bind.BindConverter().doBackward(jsonElement));
                break;
            }
            case "Enum": {
                try {
                    final Enum doBackward = new EnumConverter(((Enum)setting.getValue()).getClass()).doBackward(jsonElement);
                    setting.setValue((doBackward == null) ? setting.getDefaultValue() : doBackward);
                }
                catch (Exception ex) {}
                break;
            }
            default: {
                LuigiHack.LOGGER.error(String.valueOf(new StringBuilder().append("Unknown Setting type for: ").append(feature.getName()).append(" : ").append(setting.getName())));
                break;
            }
        }
    }
    
    private void loadPath(final Path path, final Feature feature) throws IOException {
        final InputStream inputStream = Files.newInputStream(path, new OpenOption[0]);
        try {
            loadFile(new JsonParser().parse((Reader)new InputStreamReader(inputStream)).getAsJsonObject(), feature);
        }
        catch (IllegalStateException ex) {
            LuigiHack.LOGGER.error(String.valueOf(new StringBuilder().append("Bad Config File for: ").append(feature.getName()).append(". Resetting...")));
            loadFile(new JsonObject(), feature);
        }
        inputStream.close();
    }
    
    public ConfigManager() {
        this.features = new ArrayList<Feature>();
        this.config = "luigihack/config/";
    }
    
    public void init() {
        final ArrayList<Feature> features = this.features;
        final ModuleManager moduleManager = LuigiHack.moduleManager;
        features.addAll(ModuleManager.modules);
        this.features.add(LuigiHack.friendManager);
        this.loadConfig(this.loadCurrentConfig());
        LuigiHack.LOGGER.info("Config loaded.");
    }
    
    public JsonObject writeSettings(final Feature feature) {
        final JsonObject jsonObject = new JsonObject();
        final JsonParser jsonParser = new JsonParser();
        for (final Setting<Enum<?>> setting : feature.getSettings()) {
            if (setting.isEnumSetting()) {
                jsonObject.add(setting.getName(), new EnumConverter(setting.getValue().getClass()).doForward(setting.getValue()));
            }
            else {
                if (setting.isStringSetting()) {
                    setting.setValue((Enum<?>)((String)setting.getValue()).replace(" ", "_"));
                }
                try {
                    jsonObject.add(setting.getName(), jsonParser.parse(setting.getValueAsString()));
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return jsonObject;
    }
    
    public void saveConfig(final String str) {
        this.savingConfig = true;
        this.config = String.valueOf(new StringBuilder().append("luigihack/").append(str).append("/"));
        final File file = new File(this.config);
        if (!file.exists()) {
            file.mkdir();
        }
        LuigiHack.friendManager.saveFriends();
        for (final Feature feature : this.features) {
            try {
                this.saveSettings(feature);
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        this.saveCurrentConfig();
        this.savingConfig = false;
    }
    
    public String getDirectory(final Feature feature) {
        String value = "";
        if (feature instanceof Module) {
            value = String.valueOf(new StringBuilder().append(value).append(((Module)feature).getCategory().getName()).append("/"));
        }
        return value;
    }
}
