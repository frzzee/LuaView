package luaj.lib.jse;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import luaj.LuaTable;
import luaj.LuaUserdata;
import luaj.LuaValue;
import luaj.ap;
import luaj.o;

class JavaInstance extends LuaUserdata {
    static final LuaValue CLASS = m("class");
    private static final int TYPE_CLASS = 3;
    private static final int TYPE_GETFILED = 1;
    private static final int TYPE_GETTER = 4;
    private static final int TYPE_GETVALUE = 5;
    private static final int TYPE_METHOD = 2;
    private static final int TYPE_SETFIELD = 6;
    private static final int TYPE_SETLISTENER = 9;
    private static final int TYPE_SETTER = 7;
    private static final int TYPE_SETVALUE = 8;
    private static HashMap<JavaInstance, HashMap<LuaValue, LuaValue>> values = new HashMap<>();
    JavaClass jclass;
    private HashMap<LuaValue, LuaValue> vs;

    JavaInstance(Object obj) {
        super(obj);
    }

    private boolean javaSetListener(String str, LuaValue luaValue) {
        JavaMethod method = this.jclass.getMethod(CoerceJavaToLua.coerce("setOn" + str.substring(TYPE_METHOD) + "Listener"));
        if (method == null) {
            return false;
        }
        LuaTable luaTable = new LuaTable();
        luaTable.a(str, luaValue);
        Class[] parameterTypes = method.method.getParameterTypes();
        method.setuservalue(this);
        method.a((LuaValue) LuajavaLib.createProxy(parameterTypes[0], luaTable));
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:5:0x0021  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public luaj.ap A(luaj.LuaValue r10) {
        /*
            r9 = this;
            java.lang.Object r0 = r9.m_instance
            boolean r0 = r0 instanceof java.util.Map
            r1 = 2
            r2 = 0
            r3 = 1
            if (r0 == 0) goto L_0x0047
            java.lang.Object r0 = r9.m_instance
            java.util.Map r0 = (java.util.Map) r0
            java.util.Set r4 = r0.keySet()
            java.lang.Class<java.lang.Object> r5 = java.lang.Object.class
            java.lang.Object r5 = luaj.lib.jse.CoerceLuaToJava.coerce(r10, r5)
            java.util.Iterator r6 = r4.iterator()
        L_0x001b:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x0046
            java.lang.Object r7 = r6.next()
            if (r5 == 0) goto L_0x002f
            boolean r8 = r5.equals(r7)
            if (r8 == 0) goto L_0x002e
            goto L_0x002f
        L_0x002e:
            goto L_0x001b
        L_0x002f:
            luaj.LuaValue[] r1 = new luaj.LuaValue[r1]
            luaj.LuaValue r6 = luaj.lib.jse.CoerceJavaToLua.coerce(r7)
            r1[r2] = r6
            java.lang.Object r2 = r0.get(r7)
            luaj.LuaValue r2 = luaj.lib.jse.CoerceJavaToLua.coerce(r2)
            r1[r3] = r2
            luaj.ap r1 = luaj.LuaValue.c(r1)
            return r1
        L_0x0046:
            goto L_0x0082
        L_0x0047:
            java.lang.Object r0 = r9.m_instance
            boolean r0 = r0 instanceof java.util.List
            if (r0 == 0) goto L_0x0082
            java.lang.Object r0 = r9.m_instance
            java.util.List r0 = (java.util.List) r0
            boolean r4 = r10.F()
            if (r4 == 0) goto L_0x0059
            r4 = r2
            goto L_0x005e
        L_0x0059:
            int r4 = r10.p()
            int r4 = r4 + r3
        L_0x005e:
            int r5 = r0.size()
            if (r4 < r5) goto L_0x0067
            luaj.LuaValue r1 = luaj.LuaValue.u
            return r1
        L_0x0067:
            luaj.LuaValue[] r1 = new luaj.LuaValue[r1]
            java.lang.Integer r5 = java.lang.Integer.valueOf(r4)
            luaj.LuaValue r5 = luaj.lib.jse.CoerceJavaToLua.coerce(r5)
            r1[r2] = r5
            java.lang.Object r2 = r0.get(r4)
            luaj.LuaValue r2 = luaj.lib.jse.CoerceJavaToLua.coerce(r2)
            r1[r3] = r2
            luaj.ap r1 = luaj.LuaValue.c(r1)
            return r1
        L_0x0082:
            luaj.ap r0 = super.A(r10)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: luaj.lib.jse.JavaInstance.A(luaj.LuaValue):luaj.ap");
    }

    public LuaValue K() {
        return this.m_instance instanceof Map ? CoerceJavaToLua.coerce(Integer.valueOf(((Map) this.m_instance).size())) : this.m_instance instanceof List ? CoerceJavaToLua.coerce(Integer.valueOf(((List) this.m_instance).size())) : super.K();
    }

    public LuaValue _getField(LuaValue luaValue) {
        if (this.jclass == null) {
            this.jclass = JavaClass.forClass(this.m_instance.getClass());
        }
        Field field = this.jclass.getField(luaValue);
        if (field == null) {
            return w(luaValue);
        }
        try {
            return CoerceJavaToLua.coerce(field.get(this.m_instance));
        } catch (Exception e) {
            throw new o(e);
        }
    }

    public LuaValue a(LuaValue luaValue) {
        if (!luaValue.N()) {
            return super.a(luaValue);
        }
        LuaValue luaValue2 = LuaValue.u;
        while (true) {
            ap A = luaValue.A(luaValue2);
            ap apVar = A;
            if (A.f(1)) {
                return this;
            }
            luaValue2 = apVar.g();
            b(luaValue2, apVar.c(TYPE_METHOD));
        }
    }

    public ap a_(ap apVar) {
        if (apVar.j_() == 1) {
            LuaValue g = apVar.g();
            if (g.N()) {
                LuaValue luaValue = LuaValue.u;
                while (true) {
                    ap A = g.A(luaValue);
                    ap apVar2 = A;
                    if (A.f(1)) {
                        return this;
                    }
                    luaValue = apVar2.g();
                    b(luaValue, apVar2.c(TYPE_METHOD));
                }
            }
        }
        return super.a_(apVar);
    }

    public void b(LuaValue luaValue, LuaValue luaValue2) {
        Field field;
        if (this.jclass == null) {
            this.jclass = JavaClass.forClass(this.m_instance.getClass());
        }
        int i = 0;
        Integer num = this.jclass.setTypeCache.get(luaValue);
        if (num != null) {
            i = num.intValue();
        }
        if ((i == 0 || i == TYPE_SETFIELD) && (field = this.jclass.getField(luaValue)) != null) {
            if (i == 0) {
                this.jclass.setTypeCache.put(luaValue, Integer.valueOf(TYPE_SETFIELD));
            }
            try {
                field.set(this.m_instance, CoerceLuaToJava.coerce(luaValue2, field.getType()));
            } catch (Exception e) {
                throw new o(e);
            }
        } else {
            if (i == 0 || i == TYPE_SETTER) {
                LuaValue luaValue3 = this.jclass.setterCache.get(luaValue);
                if (luaValue3 == null) {
                    String d_ = luaValue.d_();
                    if (Character.isLowerCase(d_.charAt(0))) {
                        d_ = Character.toUpperCase(d_.charAt(0)) + d_.substring(1);
                    }
                    luaValue3 = this.jclass.getMethod(CoerceJavaToLua.coerce("set" + d_));
                }
                if (luaValue3 != null) {
                    if (i == 0) {
                        this.jclass.setterCache.put(luaValue, luaValue3);
                        this.jclass.setTypeCache.put(luaValue, Integer.valueOf(TYPE_SETTER));
                    }
                    luaValue3.setuservalue(this);
                    luaValue3.a(luaValue2);
                    return;
                }
            }
            if (i == 0 || i == TYPE_SETLISTENER) {
                String d_2 = luaValue.d_();
                if (d_2.length() > TYPE_METHOD && d_2.substring(0, TYPE_METHOD).equals("on") && luaValue2.B() && javaSetListener(d_2, luaValue2)) {
                    if (i == 0) {
                        this.jclass.setTypeCache.put(luaValue, Integer.valueOf(TYPE_SETLISTENER));
                        return;
                    }
                    return;
                }
            }
            if (i == 0 || i == TYPE_SETVALUE) {
                if (this.m_instance instanceof Map) {
                    Map map = (Map) this.m_instance;
                    if (i == 0) {
                        this.jclass.setTypeCache.put(luaValue, Integer.valueOf(TYPE_SETVALUE));
                    }
                    CoerceJavaToLua.coerce(map.put(CoerceLuaToJava.coerce(luaValue, Object.class), CoerceLuaToJava.coerce(luaValue2, Object.class)));
                    return;
                } else if (this.m_instance instanceof List) {
                    if (i == 0) {
                        this.jclass.setTypeCache.put(luaValue, Integer.valueOf(TYPE_SETVALUE));
                    }
                    CoerceJavaToLua.coerce(((List) this.m_instance).set(luaValue.v(), CoerceLuaToJava.coerce(luaValue2, Object.class)));
                    return;
                }
            }
            if (this.vs == null) {
                if (values.containsKey(this)) {
                    this.vs = values.get(this);
                } else {
                    HashMap<LuaValue, LuaValue> hashMap = new HashMap<>();
                    this.vs = hashMap;
                    values.put(this, hashMap);
                }
            }
            this.vs.put(luaValue, luaValue2);
        }
    }

    public LuaValue w(LuaValue luaValue) {
        Class innerClass;
        Field field;
        LuaValue method;
        if (this.jclass == null) {
            this.jclass = JavaClass.forClass(this.m_instance.getClass());
        }
        LuaValue luaValue2 = this.jclass.finalValueCache.get(luaValue);
        if (luaValue2 != null) {
            return luaValue2;
        }
        int i = 0;
        Integer num = this.jclass.typeCache.get(luaValue);
        if (num != null) {
            i = num.intValue();
        }
        if ((i == 0 || i == TYPE_METHOD) && (method = this.jclass.getMethod(luaValue)) != null) {
            if (i == 0) {
                this.jclass.typeCache.put(luaValue, Integer.valueOf(TYPE_METHOD));
            }
            method.setuservalue(this);
            return method;
        } else if ((i == 0 || i == 1) && (field = this.jclass.getField(luaValue)) != null) {
            try {
                return CoerceJavaToLua.coerce(field.get(this.m_instance));
            } catch (Exception e) {
                throw new o(e);
            }
        } else if ((i == 0 || i == TYPE_CLASS) && (this.m_instance instanceof Class) && (innerClass = this.jclass.getInnerClass(luaValue)) != null) {
            if (i == 0) {
                this.jclass.typeCache.put(luaValue, Integer.valueOf(TYPE_CLASS));
            }
            JavaClass forClass = JavaClass.forClass(innerClass);
            if (Modifier.isStatic(innerClass.getModifiers())) {
                this.jclass.finalValueCache.put(luaValue, forClass);
            }
            return forClass;
        } else {
            if (i == 0 || i == TYPE_GETTER) {
                LuaValue luaValue3 = this.jclass.getterCache.get(luaValue);
                if (luaValue3 == null) {
                    String d_ = luaValue.d_();
                    if (d_.equals("class")) {
                        return CoerceJavaToLua.coerce(this.m_instance.getClass());
                    }
                    if (Character.isLowerCase(d_.charAt(0))) {
                        d_ = Character.toUpperCase(d_.charAt(0)) + d_.substring(1);
                    }
                    luaValue3 = this.jclass.getMethod(CoerceJavaToLua.coerce("get" + d_));
                    if (luaValue3 == null) {
                        luaValue3 = this.jclass.getMethod(CoerceJavaToLua.coerce("is" + d_));
                    }
                }
                if (luaValue3 != null) {
                    if (i == 0) {
                        this.jclass.getterCache.put(luaValue, luaValue3);
                        this.jclass.typeCache.put(luaValue, Integer.valueOf(TYPE_GETTER));
                    }
                    luaValue3.setuservalue(this);
                    return luaValue3.l();
                }
            }
            if (i == 0 || i == TYPE_GETVALUE) {
                if (this.m_instance instanceof Map) {
                    Map map = (Map) this.m_instance;
                    if (i == 0) {
                        this.jclass.typeCache.put(luaValue, Integer.valueOf(TYPE_GETVALUE));
                    }
                    return CoerceJavaToLua.coerce(map.get(CoerceLuaToJava.coerce(luaValue, Object.class)));
                } else if (this.m_instance instanceof List) {
                    if (i == 0) {
                        this.jclass.typeCache.put(luaValue, Integer.valueOf(TYPE_GETVALUE));
                    }
                    return CoerceJavaToLua.coerce(((List) this.m_instance).get(luaValue.v()));
                }
            }
            HashMap<LuaValue, LuaValue> hashMap = this.vs;
            if (hashMap == null) {
                HashMap<JavaInstance, HashMap<LuaValue, LuaValue>> hashMap2 = values;
                if (hashMap2.containsKey(this)) {
                    HashMap<LuaValue, LuaValue> hashMap3 = hashMap2.get(this);
                    this.vs = hashMap3;
                    if (hashMap3 != null && hashMap3.containsKey(luaValue)) {
                        return this.vs.get(luaValue);
                    }
                }
            } else if (hashMap.containsKey(luaValue)) {
                return this.vs.get(luaValue);
            }
            HashMap<JavaInstance, HashMap<LuaValue, LuaValue>> hashMap4 = values;
            if (hashMap4.containsKey(this.jclass)) {
                JavaClass javaClass = this.jclass;
                HashMap hashMap5 = hashMap4.get(javaClass);
                if (javaClass != null && hashMap5.containsKey(luaValue)) {
                    return (LuaValue) hashMap5.get(luaValue);
                }
            }
            if (!luaValue.b(CLASS)) {
                return super.w(luaValue);
            }
            this.jclass.finalValueCache.put(luaValue, this.jclass);
            return this.jclass;
        }
    }
}
