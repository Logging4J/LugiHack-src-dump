//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.hidden;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import me.snow.luigihack.impl.command.*;
import me.snow.luigihack.impl.modules.combat.*;
import me.snow.luigihack.impl.modules.movement.*;

public class AutoConfig extends Module
{
    private static /* synthetic */ AutoConfig INSTANCE;
    private final /* synthetic */ Setting<Boolean> semistrict;
    private final /* synthetic */ Setting<Boolean> strict;
    private final /* synthetic */ Setting<Boolean> nonstrict;
    
    @Override
    public void onTick() {
        if (this.strict.getValue()) {
            SpeedRewrite.getInstance().mode.setValue((Object)SpeedRewrite.Mode.STRAFESTRICT);
            SpeedRewrite.getInstance().speed.setValue((Object)SpeedRewrite.BaseSpeed.NORMAL);
            Command.sendMessage("<Speed>set Strict");
            Step.getInstance().height.setValue((Object)1.0);
            Step.getInstance().mode.setValue((Object)Step.Mode.Normal);
            Step.getInstance().usetimer.setValue((Object)true);
            Step.getInstance().strict.setValue((Object)true);
            Command.sendMessage("<Step>set Strict");
            if (Criticals.getInstance().isOff()) {
                Criticals.getInstance().enable();
            }
            Criticals.getInstance().mode.setValue((Object)Criticals.Mode.NCPStrict);
            Command.sendMessage("<Criticals>set Strict");
            NoSlowDown.getInstance().superStrict.setValue((Object)true);
            Command.sendMessage("<NoSlowDown>set Strict");
            this.strict.setValue((Object)false);
        }
        if (this.nonstrict.getValue()) {
            SpeedRewrite.getInstance().mode.setValue((Object)SpeedRewrite.Mode.STRAFE);
            SpeedRewrite.getInstance().speed.setValue((Object)SpeedRewrite.BaseSpeed.NORMAL);
            Command.sendMessage("<Speed>set NonStrict");
            Step.getInstance().height.setValue((Object)2.0);
            Step.getInstance().mode.setValue((Object)Step.Mode.Vanilla);
            Step.getInstance().usetimer.setValue((Object)false);
            Step.getInstance().strict.setValue((Object)false);
            Command.sendMessage("<Step>set NonStrict");
            if (Criticals.getInstance().isOff()) {
                Criticals.getInstance().enable();
            }
            Criticals.getInstance().mode.setValue((Object)Criticals.Mode.PACKET);
            Command.sendMessage("<Criticals>set NonStrict");
            NoSlowDown.getInstance().superStrict.setValue((Object)false);
            Command.sendMessage("<NoSlowDown>set NonStrict");
            this.nonstrict.setValue((Object)false);
        }
    }
    
    public static AutoConfig getInstance() {
        if (AutoConfig.INSTANCE == null) {
            AutoConfig.INSTANCE = new AutoConfig();
        }
        return AutoConfig.INSTANCE;
    }
    
    public AutoConfig() {
        super("AutoConfig", "AutoConfig", Category.CLIENT, true, false, false);
        this.semistrict = (Setting<Boolean>)this.register(new Setting("SemiStrict", (Object)false));
        this.strict = (Setting<Boolean>)this.register(new Setting("Strict", (Object)false));
        this.nonstrict = (Setting<Boolean>)this.register(new Setting("NonStrict", (Object)false));
    }
}
