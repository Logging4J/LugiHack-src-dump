//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.manager;

import me.snow.luigihack.api.util.*;
import me.zero.alpine.fork.listener.*;
import java.util.concurrent.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.network.play.server.*;
import net.minecraft.world.*;
import me.snow.luigihack.*;
import me.snow.luigihack.api.event.*;
import me.snow.luigihack.api.event.events.*;
import java.util.*;
import net.minecraft.entity.*;

public class TotemPopManager2 implements Listenable, Util
{
    public static /* synthetic */ ConcurrentHashMap<EntityLivingBase, Integer> totemMap;
    
    public TotemPopManager2() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokespecial   java/lang/Object.<init>:()V
        //     4: iconst_1       
        //     5: anewarray       Lnet/minecraft/network/play/server/SPacketEntityStatus;
        //     8: astore_1       
        //     9: iconst_1       
        //    10: anewarray       Lnet/minecraft/entity/EntityLivingBase;
        //    13: astore_2       
        //    14: iconst_1       
        //    15: newarray        I
        //    17: astore_3       
        //    18: aload_0        
        //    19: new             Lme/zero/alpine/fork/listener/Listener;
        //    22: dup            
        //    23: aload_1        
        //    24: aload_2        
        //    25: aload_3        
        //    26: invokedynamic   BootstrapMethod #0, invoke:([Lnet/minecraft/network/play/server/SPacketEntityStatus;[Lnet/minecraft/entity/EntityLivingBase;[I)Lme/zero/alpine/fork/listener/EventHook;
        //    31: iconst_0       
        //    32: anewarray       Ljava/util/function/Predicate;
        //    35: checkcast       [Ljava/util/function/Predicate;
        //    38: invokespecial   me/zero/alpine/fork/listener/Listener.<init>:(Lme/zero/alpine/fork/listener/EventHook;[Ljava/util/function/Predicate;)V
        //    41: putfield        me/snow/luigihack/api/manager/TotemPopManager2.packetRecieveListener:Lme/zero/alpine/fork/listener/Listener;
        //    44: new             Ljava/util/concurrent/ConcurrentHashMap;
        //    47: dup            
        //    48: invokespecial   java/util/concurrent/ConcurrentHashMap.<init>:()V
        //    51: putstatic       me/snow/luigihack/api/manager/TotemPopManager2.totemMap:Ljava/util/concurrent/ConcurrentHashMap;
        //    54: getstatic       me/snow/luigihack/LuigiHack.dispatcher:Lme/zero/alpine/fork/bus/EventBus;
        //    57: aload_0        
        //    58: invokeinterface me/zero/alpine/fork/bus/EventBus.subscribe:(Lme/zero/alpine/fork/listener/Listenable;)V
        //    63: getstatic       net/minecraftforge/common/MinecraftForge.EVENT_BUS:Lnet/minecraftforge/fml/common/eventhandler/EventBus;
        //    66: aload_0        
        //    67: invokevirtual   net/minecraftforge/fml/common/eventhandler/EventBus.register:(Ljava/lang/Object;)V
        //    70: return         
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
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:713)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:549)
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
    
    @SubscribeEvent
    public void onTick(final TickEvent.ClientTickEvent clientTickEvent) {
        if (TotemPopManager2.mc.player == null || TotemPopManager2.mc.world == null) {
            TotemPopManager2.totemMap.clear();
            return;
        }
        update();
    }
    
    public static int getPops(final EntityLivingBase entityLivingBase) {
        if (TotemPopManager2.totemMap.containsKey(entityLivingBase)) {
            return TotemPopManager2.totemMap.get(entityLivingBase);
        }
        return 0;
    }
    
    private static void update() {
        for (final EntityLivingBase key : TotemPopManager2.totemMap.keySet()) {
            if (!TotemPopManager2.mc.world.loadedEntityList.contains(key)) {
                TotemPopManager2.totemMap.remove(key);
            }
        }
    }
    
    public static int getPops(final String anObject) {
        boolean b = false;
        Object o = null;
        for (final Entity entity : TotemPopManager2.mc.world.loadedEntityList) {
            if (entity instanceof EntityLivingBase && entity.getName().equals(anObject)) {
                b = true;
                o = entity;
                break;
            }
        }
        if (b && TotemPopManager2.totemMap.containsKey(o)) {
            return TotemPopManager2.totemMap.get(o);
        }
        return 0;
    }
}
