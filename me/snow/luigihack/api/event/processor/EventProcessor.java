//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.event.processor;

import java.lang.reflect.*;
import org.jetbrains.annotations.*;
import java.lang.annotation.*;
import java.util.*;

public final class EventProcessor
{
    private final /* synthetic */ List<Listener> events;
    
    private EventPriority getPriority(@NotNull final Method method) {
        return method.getAnnotation(CommitEvent.class).priority();
    }
    
    public final void removeEventListener(@NotNull final Object o) {
        this.events.removeIf(listener -> listener.object == o);
    }
    
    private void getEvents(@NotNull final Object p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //     4: astore_2       
        //     5: aload_2        
        //     6: invokevirtual   java/lang/Class.getDeclaredMethods:()[Ljava/lang/reflect/Method;
        //     9: invokestatic    java/util/Arrays.stream:([Ljava/lang/Object;)Ljava/util/stream/Stream;
        //    12: invokeinterface java/util/stream/Stream.spliterator:()Ljava/util/Spliterator;
        //    17: aload_0        
        //    18: aload_1        
        //    19: invokedynamic   BootstrapMethod #1, accept:(Lme/snow/luigihack/api/event/processor/EventProcessor;Ljava/lang/Object;)Ljava/util/function/Consumer;
        //    24: invokeinterface java/util/Spliterator.forEachRemaining:(Ljava/util/function/Consumer;)V
        //    29: return         
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
    
    public final boolean postEvent(@NotNull final Event event) {
        final Throwable t;
        this.events.spliterator().forEachRemaining(listener -> {
            if (listener.event == event.getClass()) {
                listener.method.setAccessible(true);
                try {
                    listener.method.invoke(listener.object, event);
                }
                catch (IllegalAccessException | InvocationTargetException ex) {
                    t.printStackTrace();
                }
            }
            return;
        });
        return true;
    }
    
    public final void addEventListener(@NotNull final Object o) throws IllegalArgumentException {
        this.getEvents(o);
    }
    
    public EventProcessor() {
        this.events = new ArrayList<Listener>();
    }
}
