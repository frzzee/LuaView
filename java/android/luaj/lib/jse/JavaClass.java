package luaj.lib.jse;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import luaj.LuaValue;
import luaj.ap;
import luaj.lib.jse.CoerceJavaToLua;
import luaj.lib.jse.CoerceLuaToJava;

class JavaClass extends JavaInstance implements CoerceJavaToLua.Coercion {
    static final LuaValue NEW = m("new");
    static final HashMap<LuaValue, LuaValue> classMethods = new HashMap<>();
    static final Map classes = Collections.synchronizedMap(new HashMap());
    Map fields;
    final HashMap<LuaValue, LuaValue> finalValueCache = new HashMap<>();
    final HashMap<LuaValue, LuaValue> getterCache = new HashMap<>();
    Map innerclasses;
    Map methods;
    final HashMap<LuaValue, Integer> setTypeCache = new HashMap<>();
    final HashMap<LuaValue, LuaValue> setterCache = new HashMap<>();
    final HashMap<LuaValue, Integer> typeCache = new HashMap<>();

    static {
        for (Method method : Class.class.getMethods()) {
            classMethods.put(LuaValue.m(method.getName()), JavaMethod.forMethod(method));
        }
    }

    JavaClass(Class cls) {
        super(cls);
        this.jclass = this;
    }

    static JavaClass forClass(Class cls) {
        Map map = classes;
        JavaClass javaClass = (JavaClass) map.get(cls);
        if (javaClass != null) {
            return javaClass;
        }
        JavaClass javaClass2 = new JavaClass(cls);
        JavaClass javaClass3 = javaClass2;
        map.put(cls, javaClass2);
        return javaClass3;
    }

    public LuaValue a(LuaValue luaValue) {
        Class cls = (Class) touserdata();
        return cls.isPrimitive() ? CoerceJavaToLua.coerce(CoerceLuaToJava.coerce(luaValue, cls)) : luaValue.N() ? cls.isInterface() ? LuajavaLib.createProxy(cls, luaValue) : Map.class.isAssignableFrom(cls) ? CoerceJavaToLua.coerce(new CoerceLuaToJava.MapCoercion(cls).coerce(luaValue)) : List.class.isAssignableFrom(cls) ? CoerceJavaToLua.coerce(new CoerceLuaToJava.ListCoercion(cls).coerce(luaValue)) : CoerceJavaToLua.coerce(new CoerceLuaToJava.ArrayCoercion(cls).coerce(luaValue)) : w(NEW).a(luaValue);
    }

    public LuaValue a(LuaValue luaValue, LuaValue luaValue2) {
        return w(NEW).a(luaValue, luaValue2);
    }

    public LuaValue a(LuaValue luaValue, LuaValue luaValue2, LuaValue luaValue3) {
        return w(NEW).a(luaValue, luaValue2, luaValue3);
    }

    public ap a_(ap apVar) {
        if (apVar.j_() == 1) {
            Class cls = (Class) touserdata();
            LuaValue g = apVar.g();
            if (cls.isPrimitive()) {
                return CoerceJavaToLua.coerce(CoerceLuaToJava.coerce(g, cls));
            }
            if (g.N()) {
                return cls.isInterface() ? LuajavaLib.createProxy(cls, g) : Map.class.isAssignableFrom(cls) ? CoerceJavaToLua.coerce(new CoerceLuaToJava.MapCoercion(cls).coerce(g)) : List.class.isAssignableFrom(cls) ? CoerceJavaToLua.coerce(new CoerceLuaToJava.ListCoercion(cls).coerce(g)) : CoerceJavaToLua.coerce(new CoerceLuaToJava.ArrayCoercion(cls).coerce(g));
            }
        }
        return w(NEW).a_(apVar);
    }

    public LuaValue coerce(Object obj) {
        return this;
    }

    public LuaValue getConstructor() {
        return getMethod(NEW);
    }

    /* access modifiers changed from: package-private */
    public Field getField(LuaValue luaValue) {
        if (this.fields == null) {
            HashMap hashMap = new HashMap();
            Field[] fields2 = ((Class) this.m_instance).getFields();
            for (Field field : fields2) {
                if (Modifier.isPublic(field.getModifiers())) {
                    hashMap.put(LuaValue.m(field.getName()), field);
                    try {
                        if (!field.isAccessible()) {
                            field.setAccessible(true);
                        }
                    } catch (SecurityException e) {
                    }
                }
            }
            this.fields = hashMap;
        }
        return (Field) this.fields.get(luaValue);
    }

    /* access modifiers changed from: package-private */
    public Class getInnerClass(LuaValue luaValue) {
        if (this.innerclasses == null) {
            HashMap hashMap = new HashMap();
            Class[] classes2 = ((Class) this.m_instance).getClasses();
            for (Class cls : classes2) {
                String name = cls.getName();
                hashMap.put(LuaValue.m(name.substring(Math.max(name.lastIndexOf(36), name.lastIndexOf(46)) + 1)), cls);
            }
            this.innerclasses = hashMap;
        }
        return (Class) this.innerclasses.get(luaValue);
    }

    /* access modifiers changed from: package-private */
    public LuaValue getMethod(LuaValue luaValue) {
        if (this.methods == null) {
            HashMap hashMap = new HashMap();
            Method[] methods2 = ((Class) this.m_instance).getMethods();
            for (Method method : methods2) {
                if (Modifier.isPublic(method.getModifiers())) {
                    String name = method.getName();
                    List list = (List) hashMap.get(name);
                    if (list == null) {
                        ArrayList arrayList = new ArrayList();
                        list = arrayList;
                        hashMap.put(name, arrayList);
                    }
                    list.add(JavaMethod.forMethod(method));
                }
            }
            HashMap hashMap2 = new HashMap();
            Constructor[] constructors = ((Class) this.m_instance).getConstructors();
            ArrayList arrayList2 = new ArrayList();
            for (int i = 0; i < constructors.length; i++) {
                if (Modifier.isPublic(constructors[i].getModifiers())) {
                    arrayList2.add(JavaConstructor.forConstructor(constructors[i]));
                }
            }
            switch (arrayList2.size()) {
                case 0:
                    break;
                case 1:
                    hashMap2.put(NEW, arrayList2.get(0));
                    break;
                default:
                    hashMap2.put(NEW, JavaConstructor.forConstructors((JavaConstructor[]) arrayList2.toArray(new JavaConstructor[arrayList2.size()])));
                    break;
            }
            hashMap2.putAll(classMethods);
            for (Map.Entry entry : hashMap.entrySet()) {
                List list2 = (List) entry.getValue();
                hashMap2.put(LuaValue.m((String) entry.getKey()), list2.size() == 1 ? list2.get(0) : JavaMethod.forMethods((JavaMethod[]) list2.toArray(new JavaMethod[list2.size()])));
            }
            this.methods = hashMap2;
        }
        return (LuaValue) this.methods.get(luaValue);
    }

    public LuaValue l() {
        return w(NEW).l();
    }

    public LuaValue w(LuaValue luaValue) {
        return luaValue.I() ? CoerceJavaToLua.coerce(Array.newInstance((Class) touserdata(), luaValue.p())) : super.w(luaValue);
    }
}
