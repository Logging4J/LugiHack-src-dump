//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package net.jodah.typetools;

import java.lang.ref.*;
import java.util.*;
import sun.misc.*;
import java.security.*;
import java.lang.reflect.*;
import java.lang.invoke.*;

public final class TypeResolver
{
    private static final Map<Class<?>, Reference<Map<TypeVariable<?>, Type>>> TYPE_VARIABLE_CACHE;
    private static volatile boolean CACHE_ENABLED;
    private static boolean RESOLVES_LAMBDAS;
    private static Object JAVA_LANG_ACCESS;
    private static Method GET_CONSTANT_POOL;
    private static Method GET_CONSTANT_POOL_SIZE;
    private static Method GET_CONSTANT_POOL_METHOD_AT;
    private static final Map<String, Method> OBJECT_METHODS;
    private static final Map<Class<?>, Class<?>> PRIMITIVE_WRAPPERS;
    private static final Double JAVA_VERSION;
    
    private TypeResolver() {
    }
    
    public static void enableCache() {
        TypeResolver.CACHE_ENABLED = true;
    }
    
    public static void disableCache() {
        TypeResolver.TYPE_VARIABLE_CACHE.clear();
        TypeResolver.CACHE_ENABLED = false;
    }
    
    public static <T, S extends T> Class<?> resolveRawArgument(final Class<T> clazz, final Class<S> clazz2) {
        return resolveRawArgument(resolveGenericType(clazz, clazz2), clazz2);
    }
    
    public static Class<?> resolveRawArgument(final Type obj, final Class<?> clazz) {
        final Class<?>[] resolveRawArguments = resolveRawArguments(obj, clazz);
        if (resolveRawArguments == null) {
            return Unknown.class;
        }
        if (resolveRawArguments.length != 1) {
            throw new IllegalArgumentException("Expected 1 argument for generic type " + obj + " but found " + resolveRawArguments.length);
        }
        return resolveRawArguments[0];
    }
    
    public static <T, S extends T> Class<?>[] resolveRawArguments(final Class<T> clazz, final Class<S> clazz2) {
        return resolveRawArguments(resolveGenericType(clazz, clazz2), clazz2);
    }
    
    public static <T, S extends T> Type reify(final Class<T> clazz, final Class<S> clazz2) {
        return reify(resolveGenericType(clazz, clazz2), getTypeVariableMap(clazz2, null));
    }
    
    public static Type reify(final Type type, final Class<?> clazz) {
        return reify(type, getTypeVariableMap(clazz, null));
    }
    
    public static Type reify(final Type type) {
        return reify(type, new HashMap<TypeVariable<?>, Type>(0));
    }
    
    public static Class<?>[] resolveRawArguments(final Type type, final Class<?> clazz) {
        Class<?>[] array = null;
        Class<?> clazz2 = null;
        if (TypeResolver.RESOLVES_LAMBDAS && clazz.isSynthetic()) {
            final Class clazz3 = (Class)((type instanceof ParameterizedType && ((ParameterizedType)type).getRawType() instanceof Class) ? ((ParameterizedType)type).getRawType() : ((type instanceof Class) ? ((Class)type) : null));
            if (clazz3 != null && clazz3.isInterface()) {
                clazz2 = (Class<?>)clazz3;
            }
        }
        if (type instanceof ParameterizedType) {
            final Type[] actualTypeArguments = ((ParameterizedType)type).getActualTypeArguments();
            array = (Class<?>[])new Class[actualTypeArguments.length];
            for (int i = 0; i < actualTypeArguments.length; ++i) {
                array[i] = resolveRawClass(actualTypeArguments[i], clazz, clazz2);
            }
        }
        else if (type instanceof TypeVariable) {
            array = (Class<?>[])new Class[] { resolveRawClass(type, clazz, clazz2) };
        }
        else if (type instanceof Class) {
            final TypeVariable[] typeParameters = ((Class)type).getTypeParameters();
            array = (Class<?>[])new Class[typeParameters.length];
            for (int j = 0; j < typeParameters.length; ++j) {
                array[j] = resolveRawClass(typeParameters[j], clazz, clazz2);
            }
        }
        return array;
    }
    
    public static Type resolveGenericType(final Class<?> clazz, final Type type) {
        Class obj;
        if (type instanceof ParameterizedType) {
            obj = (Class)((ParameterizedType)type).getRawType();
        }
        else {
            obj = (Class)type;
        }
        if (clazz.equals(obj)) {
            return type;
        }
        if (clazz.isInterface()) {
            for (final Type type2 : obj.getGenericInterfaces()) {
                final Type resolveGenericType;
                if (type2 != null && !type2.equals(Object.class) && (resolveGenericType = resolveGenericType(clazz, type2)) != null) {
                    return resolveGenericType;
                }
            }
        }
        final Type genericSuperclass = obj.getGenericSuperclass();
        final Type resolveGenericType2;
        if (genericSuperclass != null && !genericSuperclass.equals(Object.class) && (resolveGenericType2 = resolveGenericType(clazz, genericSuperclass)) != null) {
            return resolveGenericType2;
        }
        return null;
    }
    
    public static Class<?> resolveRawClass(final Type type, final Class<?> clazz) {
        return resolveRawClass(type, clazz, null);
    }
    
    private static Class<?> resolveRawClass(Type type, final Class<?> clazz, final Class<?> clazz2) {
        if (type instanceof Class) {
            return (Class<?>)type;
        }
        if (type instanceof ParameterizedType) {
            return resolveRawClass(((ParameterizedType)type).getRawType(), clazz, clazz2);
        }
        if (type instanceof GenericArrayType) {
            return Array.newInstance(resolveRawClass(((GenericArrayType)type).getGenericComponentType(), clazz, clazz2), 0).getClass();
        }
        if (type instanceof TypeVariable) {
            final TypeVariable typeVariable = (TypeVariable)type;
            type = getTypeVariableMap(clazz, clazz2).get(typeVariable);
            type = ((type == null) ? resolveBound(typeVariable) : resolveRawClass(type, clazz, clazz2));
        }
        return (Class<?>)((type instanceof Class) ? ((Class)type) : Unknown.class);
    }
    
    private static Type reify(final Type type, final Map<TypeVariable<?>, Type> map) {
        if (type == null) {
            return null;
        }
        if (type instanceof Class) {
            return type;
        }
        return reify(type, map, new HashMap<ParameterizedType, ReifiedParameterizedType>());
    }
    
    private static Type reify(final Type type, final Map<TypeVariable<?>, Type> map, final Map<ParameterizedType, ReifiedParameterizedType> map2) {
        if (type instanceof Class) {
            return type;
        }
        if (type instanceof ParameterizedType) {
            final ParameterizedType parameterizedType = (ParameterizedType)type;
            if (map2.containsKey(parameterizedType)) {
                final ReifiedParameterizedType reifiedParameterizedType = map2.get(type);
                reifiedParameterizedType.addReifiedTypeArgument((Type)reifiedParameterizedType);
                return (Type)reifiedParameterizedType;
            }
            final Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            final ReifiedParameterizedType reifiedParameterizedType2 = new ReifiedParameterizedType(parameterizedType);
            map2.put(parameterizedType, reifiedParameterizedType2);
            final Type[] array = actualTypeArguments;
            for (int length = array.length, i = 0; i < length; ++i) {
                final Type reify = reify(array[i], map, map2);
                if (reify != reifiedParameterizedType2) {
                    reifiedParameterizedType2.addReifiedTypeArgument(reify);
                }
            }
            return (Type)reifiedParameterizedType2;
        }
        else if (type instanceof GenericArrayType) {
            final GenericArrayType genericArrayType = (GenericArrayType)type;
            final Type genericComponentType = genericArrayType.getGenericComponentType();
            final Type reify2 = reify(genericArrayType.getGenericComponentType(), map, map2);
            if (genericComponentType == reify2) {
                return genericComponentType;
            }
            if (reify2 instanceof Class) {
                return Array.newInstance((Class<?>)reify2, 0).getClass();
            }
            throw new UnsupportedOperationException("Attempted to reify generic array type, whose generic component type could not be reified to some Class<?>. Handling for this case is not implemented");
        }
        else if (type instanceof TypeVariable) {
            final TypeVariable typeVariable = (TypeVariable)type;
            final Type type2 = map.get(typeVariable);
            if (type2 != null) {
                return reify(type2, map, map2);
            }
            return reify(typeVariable.getBounds()[0], map, map2);
        }
        else {
            if (!(type instanceof WildcardType)) {
                throw new UnsupportedOperationException("Reification of type with name '" + type.getTypeName() + "' and class name '" + type.getClass().getName() + "' is not implemented.");
            }
            final WildcardType wildcardType = (WildcardType)type;
            final Type[] upperBounds = wildcardType.getUpperBounds();
            final Type[] lowerBounds = wildcardType.getLowerBounds();
            if (upperBounds.length == 1 && lowerBounds.length == 0) {
                return reify(upperBounds[0], map, map2);
            }
            throw new UnsupportedOperationException("Attempted to reify wildcard type with name '" + wildcardType.getTypeName() + "' which has " + upperBounds.length + " upper bounds and " + lowerBounds.length + " lower bounds. Reification of wildcard types is only supported for the trivial case of exactly 1 upper bound and 0 lower bounds.");
        }
    }
    
    private static Map<TypeVariable<?>, Type> getTypeVariableMap(final Class<?> clazz, final Class<?> clazz2) {
        final Reference<Map<TypeVariable<?>, Type>> reference = TypeResolver.TYPE_VARIABLE_CACHE.get(clazz);
        Map<TypeVariable<?>, Type> referent = (reference != null) ? reference.get() : null;
        if (referent == null) {
            referent = new HashMap<TypeVariable<?>, Type>();
            if (clazz2 != null) {
                populateLambdaArgs(clazz2, clazz, referent);
            }
            populateSuperTypeArgs(clazz.getGenericInterfaces(), referent, clazz2 != null);
            Type type = clazz.getGenericSuperclass();
            for (Class<?> obj = clazz.getSuperclass(); obj != null && !Object.class.equals(obj); obj = obj.getSuperclass()) {
                if (type instanceof ParameterizedType) {
                    populateTypeArgs((ParameterizedType)type, referent, false);
                }
                populateSuperTypeArgs(obj.getGenericInterfaces(), referent, false);
                type = obj.getGenericSuperclass();
            }
            for (Class<?> enclosingClass = clazz; enclosingClass.isMemberClass(); enclosingClass = enclosingClass.getEnclosingClass()) {
                final Type genericSuperclass = enclosingClass.getGenericSuperclass();
                if (genericSuperclass instanceof ParameterizedType) {
                    populateTypeArgs((ParameterizedType)genericSuperclass, referent, clazz2 != null);
                }
            }
            if (TypeResolver.CACHE_ENABLED) {
                TypeResolver.TYPE_VARIABLE_CACHE.put(clazz, new WeakReference<Map<TypeVariable<?>, Type>>(referent));
            }
        }
        return referent;
    }
    
    private static void populateSuperTypeArgs(final Type[] array, final Map<TypeVariable<?>, Type> map, final boolean b) {
        for (final Type type : array) {
            if (type instanceof ParameterizedType) {
                final ParameterizedType parameterizedType = (ParameterizedType)type;
                if (!b) {
                    populateTypeArgs(parameterizedType, map, b);
                }
                final Type rawType = parameterizedType.getRawType();
                if (rawType instanceof Class) {
                    populateSuperTypeArgs(((Class)rawType).getGenericInterfaces(), map, b);
                }
                if (b) {
                    populateTypeArgs(parameterizedType, map, b);
                }
            }
            else if (type instanceof Class) {
                populateSuperTypeArgs(((Class)type).getGenericInterfaces(), map, b);
            }
        }
    }
    
    private static void populateTypeArgs(final ParameterizedType parameterizedType, final Map<TypeVariable<?>, Type> map, final boolean b) {
        if (parameterizedType.getRawType() instanceof Class) {
            final TypeVariable[] typeParameters = ((Class)parameterizedType.getRawType()).getTypeParameters();
            final Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            if (parameterizedType.getOwnerType() != null) {
                final Type ownerType = parameterizedType.getOwnerType();
                if (ownerType instanceof ParameterizedType) {
                    populateTypeArgs((ParameterizedType)ownerType, map, b);
                }
            }
            for (int i = 0; i < actualTypeArguments.length; ++i) {
                final TypeVariable typeVariable = typeParameters[i];
                final Type type = actualTypeArguments[i];
                if (type instanceof Class) {
                    map.put(typeVariable, type);
                }
                else if (type instanceof GenericArrayType) {
                    map.put(typeVariable, type);
                }
                else if (type instanceof ParameterizedType) {
                    map.put(typeVariable, type);
                }
                else if (type instanceof TypeVariable) {
                    final TypeVariable<Class<T>> typeVariable2 = (TypeVariable<Class<T>>)type;
                    if (b) {
                        final TypeVariable<Class<T>> typeVariable3 = map.get(typeVariable);
                        if (typeVariable3 != null) {
                            map.put(typeVariable2, typeVariable3);
                            continue;
                        }
                    }
                    Type resolveBound = map.get(typeVariable2);
                    if (resolveBound == null) {
                        resolveBound = resolveBound(typeVariable2);
                    }
                    map.put(typeVariable, resolveBound);
                }
            }
        }
    }
    
    public static Type resolveBound(final TypeVariable<?> typeVariable) {
        final Type[] bounds = typeVariable.getBounds();
        if (bounds.length == 0) {
            return Unknown.class;
        }
        Type resolveBound = bounds[0];
        if (resolveBound instanceof TypeVariable) {
            resolveBound = resolveBound((TypeVariable<?>)resolveBound);
        }
        return (resolveBound == Object.class) ? Unknown.class : resolveBound;
    }
    
    private static void populateLambdaArgs(final Class<?> clazz, final Class<?> clazz2, final Map<TypeVariable<?>, Type> map) {
        if (TypeResolver.RESOLVES_LAMBDAS) {
            for (final Method method : clazz.getMethods()) {
                if (!isDefaultMethod(method) && !Modifier.isStatic(method.getModifiers()) && !method.isBridge()) {
                    final Method method2 = TypeResolver.OBJECT_METHODS.get(method.getName());
                    if (method2 == null || !Arrays.equals(method.getTypeParameters(), method2.getTypeParameters())) {
                        final Type genericReturnType = method.getGenericReturnType();
                        final Type[] genericParameterTypes = method.getGenericParameterTypes();
                        final Member memberRef = getMemberRef(clazz2);
                        if (memberRef == null) {
                            return;
                        }
                        if (genericReturnType instanceof TypeVariable) {
                            final Class<?> wrapPrimitives = wrapPrimitives((memberRef instanceof Method) ? ((Method)memberRef).getReturnType() : ((Constructor<?>)memberRef).getDeclaringClass());
                            if (!wrapPrimitives.equals(Void.class)) {
                                map.put((TypeVariable<?>)genericReturnType, wrapPrimitives);
                            }
                        }
                        final Class<?>[] array = (memberRef instanceof Method) ? ((Method)memberRef).getParameterTypes() : ((Constructor<?>)memberRef).getParameterTypes();
                        int n = 0;
                        if (genericParameterTypes.length > 0 && genericParameterTypes[0] instanceof TypeVariable && genericParameterTypes.length == array.length + 1) {
                            map.put((TypeVariable<?>)genericParameterTypes[0], memberRef.getDeclaringClass());
                            n = 1;
                        }
                        int n2 = 0;
                        if (genericParameterTypes.length < array.length) {
                            n2 = array.length - genericParameterTypes.length;
                        }
                        for (int n3 = 0; n3 + n2 < array.length; ++n3) {
                            if (genericParameterTypes[n3] instanceof TypeVariable) {
                                map.put((TypeVariable<?>)genericParameterTypes[n3 + n], wrapPrimitives(array[n3 + n2]));
                            }
                        }
                        return;
                    }
                }
            }
        }
    }
    
    private static boolean isDefaultMethod(final Method method) {
        return TypeResolver.JAVA_VERSION >= 1.8 && method.isDefault();
    }
    
    private static Member getMemberRef(final Class<?> clazz) {
        Object invoke;
        try {
            invoke = TypeResolver.GET_CONSTANT_POOL.invoke(TypeResolver.JAVA_LANG_ACCESS, clazz);
        }
        catch (Exception ex) {
            return null;
        }
        Member member = null;
        for (int i = getConstantPoolSize(invoke) - 1; i >= 0; --i) {
            final Member constantPoolMethod = getConstantPoolMethodAt(invoke, i);
            if (constantPoolMethod != null && (!(constantPoolMethod instanceof Constructor) || !constantPoolMethod.getDeclaringClass().getName().equals("java.lang.invoke.SerializedLambda"))) {
                if (!constantPoolMethod.getDeclaringClass().isAssignableFrom(clazz)) {
                    member = constantPoolMethod;
                    if (!(constantPoolMethod instanceof Method)) {
                        break;
                    }
                    if (!isAutoBoxingMethod((Method)constantPoolMethod)) {
                        break;
                    }
                }
            }
        }
        return member;
    }
    
    private static boolean isAutoBoxingMethod(final Method method) {
        final Class<?>[] parameterTypes = method.getParameterTypes();
        return method.getName().equals("valueOf") && parameterTypes.length == 1 && parameterTypes[0].isPrimitive() && wrapPrimitives(parameterTypes[0]).equals(method.getDeclaringClass());
    }
    
    private static Class<?> wrapPrimitives(final Class<?> clazz) {
        return clazz.isPrimitive() ? TypeResolver.PRIMITIVE_WRAPPERS.get(clazz) : clazz;
    }
    
    private static int getConstantPoolSize(final Object obj) {
        try {
            return (int)TypeResolver.GET_CONSTANT_POOL_SIZE.invoke(obj, new Object[0]);
        }
        catch (Exception ex) {
            return 0;
        }
    }
    
    private static Member getConstantPoolMethodAt(final Object obj, final int i) {
        try {
            return (Member)TypeResolver.GET_CONSTANT_POOL_METHOD_AT.invoke(obj, i);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    static {
        TYPE_VARIABLE_CACHE = Collections.synchronizedMap(new WeakHashMap<Class<?>, Reference<Map<TypeVariable<?>, Type>>>());
        TypeResolver.CACHE_ENABLED = true;
        OBJECT_METHODS = new HashMap<String, Method>();
        JAVA_VERSION = Double.parseDouble(System.getProperty("java.specification.version", "0"));
        try {
            final Unsafe unsafe = AccessController.doPrivileged((PrivilegedExceptionAction<Unsafe>)new PrivilegedExceptionAction<Unsafe>() {
                @Override
                public Unsafe run() throws Exception {
                    final Field declaredField = Unsafe.class.getDeclaredField("theUnsafe");
                    declaredField.setAccessible(true);
                    return (Unsafe)declaredField.get(null);
                }
            });
            Class<?> clazz;
            AccessMaker accessMaker;
            if (TypeResolver.JAVA_VERSION < 9.0) {
                clazz = Class.forName("sun.misc.SharedSecrets");
                accessMaker = new AccessMaker() {
                    @Override
                    public void makeAccessible(final AccessibleObject accessibleObject) {
                        accessibleObject.setAccessible(true);
                    }
                };
            }
            else if (TypeResolver.JAVA_VERSION < 12.0) {
                try {
                    clazz = Class.forName("jdk.internal.misc.SharedSecrets");
                }
                catch (ClassNotFoundException ex) {
                    clazz = Class.forName("jdk.internal.access.SharedSecrets");
                }
                accessMaker = new AccessMaker() {
                    final /* synthetic */ long val$overrideFieldOffset = unsafe.objectFieldOffset(AccessibleObject.class.getDeclaredField("override"));
                    
                    @Override
                    public void makeAccessible(final AccessibleObject o) {
                        unsafe.putBoolean(o, this.val$overrideFieldOffset, true);
                    }
                };
            }
            else {
                clazz = Class.forName("jdk.internal.access.SharedSecrets");
                final Field declaredField = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
                accessMaker = new AccessMaker() {
                    final /* synthetic */ MethodHandle val$overrideSetter = ((MethodHandles.Lookup)unsafe.getObject(unsafe.staticFieldBase(declaredField), unsafe.staticFieldOffset(declaredField))).findSetter(AccessibleObject.class, "override", Boolean.TYPE);
                    
                    @Override
                    public void makeAccessible(final AccessibleObject accessibleObject) throws Throwable {
                        this.val$overrideSetter.invokeWithArguments(accessibleObject, true);
                    }
                };
            }
            final Method method = clazz.getMethod("getJavaLangAccess", (Class[])new Class[0]);
            accessMaker.makeAccessible(method);
            TypeResolver.JAVA_LANG_ACCESS = method.invoke(null, new Object[0]);
            TypeResolver.GET_CONSTANT_POOL = TypeResolver.JAVA_LANG_ACCESS.getClass().getMethod("getConstantPool", Class.class);
            final Class<?> forName = Class.forName((TypeResolver.JAVA_VERSION < 9.0) ? "sun.reflect.ConstantPool" : "jdk.internal.reflect.ConstantPool");
            TypeResolver.GET_CONSTANT_POOL_SIZE = forName.getDeclaredMethod("getSize", (Class<?>[])new Class[0]);
            TypeResolver.GET_CONSTANT_POOL_METHOD_AT = forName.getDeclaredMethod("getMethodAt", Integer.TYPE);
            accessMaker.makeAccessible(TypeResolver.GET_CONSTANT_POOL);
            accessMaker.makeAccessible(TypeResolver.GET_CONSTANT_POOL_SIZE);
            accessMaker.makeAccessible(TypeResolver.GET_CONSTANT_POOL_METHOD_AT);
            TypeResolver.GET_CONSTANT_POOL_SIZE.invoke(TypeResolver.GET_CONSTANT_POOL.invoke(TypeResolver.JAVA_LANG_ACCESS, Object.class), new Object[0]);
            for (final Method method2 : Object.class.getDeclaredMethods()) {
                TypeResolver.OBJECT_METHODS.put(method2.getName(), method2);
            }
            TypeResolver.RESOLVES_LAMBDAS = true;
        }
        catch (Throwable t) {}
        final HashMap<Class<?>, Class<?>> m = new HashMap<Class<?>, Class<?>>();
        m.put(Boolean.TYPE, Boolean.class);
        m.put(Byte.TYPE, Byte.class);
        m.put(Character.TYPE, Character.class);
        m.put(Double.TYPE, Double.class);
        m.put(Float.TYPE, Float.class);
        m.put(Integer.TYPE, Integer.class);
        m.put(Long.TYPE, Long.class);
        m.put(Short.TYPE, Short.class);
        m.put(Void.TYPE, Void.class);
        PRIMITIVE_WRAPPERS = Collections.unmodifiableMap((Map<?, ?>)m);
    }
    
    public static final class Unknown
    {
        private Unknown() {
        }
    }
    
    private interface AccessMaker
    {
        void makeAccessible(final AccessibleObject p0) throws Throwable;
    }
}
