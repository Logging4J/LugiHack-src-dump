//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.mixin;

import net.minecraftforge.fml.relauncher.*;
import me.snow.luigihack.*;
import org.spongepowered.asm.launch.*;
import org.spongepowered.asm.mixin.*;
import java.util.*;

public class LuigiHackMixinLoader implements IFMLLoadingPlugin
{
    private static boolean isObfuscatedEnvironment;
    
    public LuigiHackMixinLoader() {
        LuigiHack.LOGGER.info("LuigiHack mixins initialized");
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.luigi.json");
        MixinEnvironment.getDefaultEnvironment().setObfuscationContext("searge");
        LuigiHack.LOGGER.info(MixinEnvironment.getDefaultEnvironment().getObfuscationContext());
    }
    
    public String[] getASMTransformerClass() {
        return new String[0];
    }
    
    public String getModContainerClass() {
        return null;
    }
    
    public String getSetupClass() {
        return null;
    }
    
    public void injectData(final Map<String, Object> map) {
        LuigiHackMixinLoader.isObfuscatedEnvironment = map.get("runtimeDeobfuscationEnabled");
    }
    
    public String getAccessTransformerClass() {
        return null;
    }
    
    static {
        LuigiHackMixinLoader.isObfuscatedEnvironment = false;
    }
}
