//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation.mapping.common;

import org.spongepowered.tools.obfuscation.mapping.*;
import javax.annotation.processing.*;
import org.spongepowered.asm.obfuscation.mapping.common.*;
import com.google.common.collect.*;

public abstract class MappingProvider implements IMappingProvider
{
    protected final Messager messager;
    protected final Filer filer;
    protected final BiMap<String, String> packageMap;
    protected final BiMap<String, String> classMap;
    protected final BiMap<MappingField, MappingField> fieldMap;
    protected final BiMap<MappingMethod, MappingMethod> methodMap;
    
    public MappingProvider(final Messager messager, final Filer filer) {
        this.packageMap = (BiMap<String, String>)HashBiMap.create();
        this.classMap = (BiMap<String, String>)HashBiMap.create();
        this.fieldMap = (BiMap<MappingField, MappingField>)HashBiMap.create();
        this.methodMap = (BiMap<MappingMethod, MappingMethod>)HashBiMap.create();
        this.messager = messager;
        this.filer = filer;
    }
    
    @Override
    public void clear() {
        this.packageMap.clear();
        this.classMap.clear();
        this.fieldMap.clear();
        this.methodMap.clear();
    }
    
    @Override
    public boolean isEmpty() {
        return this.packageMap.isEmpty() && this.classMap.isEmpty() && this.fieldMap.isEmpty() && this.methodMap.isEmpty();
    }
    
    @Override
    public MappingMethod getMethodMapping(final MappingMethod mappingMethod) {
        return (MappingMethod)this.methodMap.get((Object)mappingMethod);
    }
    
    @Override
    public MappingField getFieldMapping(final MappingField mappingField) {
        return (MappingField)this.fieldMap.get((Object)mappingField);
    }
    
    @Override
    public String getClassMapping(final String s) {
        return (String)this.classMap.get((Object)s);
    }
    
    @Override
    public String getPackageMapping(final String s) {
        return (String)this.packageMap.get((Object)s);
    }
}