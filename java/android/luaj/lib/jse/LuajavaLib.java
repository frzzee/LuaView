package luaj.lib.jse;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import luaj.LuaTable;
import luaj.LuaUserdata;
import luaj.LuaValue;
import luaj.ap;
import luaj.lib.VarArgFunction;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LuajavaLib extends VarArgFunction {
    static final int ASTABLE = 7;
    static final int BINDCLASS = 1;
    static final int CASTOF = 12;
    static final int CREATEPROXY = 4;
    static final int GETMETHODS = 10;
    static final int INIT = 0;
    static final int INSTANCEOF = 11;
    static final int LOADDEX = 6;
    static final int LOADLIB = 5;
    static final int METHODS = 9;
    static final int METHOD_MODIFIERS_VARARGS = 128;
    static final String[] NAMES = {"bindClass", "newInstance", "new", "createProxy", "loadLib", "loadDex", "astable", "toArray", "methods", "getMethods", "instanceOf", "castOf"};
    static final int NEW = 3;
    static final int NEWINSTANCE = 2;
    static final int TOARRAY = 8;

    private static final class ProxyInvocationHandler implements InvocationHandler {
        private final LuaValue lobj;

        public ProxyInvocationHandler(LuaValue luaValue) {
            this.lobj = luaValue;
        }

        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            LuaValue j = this.lobj.B() ? this.lobj : this.lobj.j(method.getName());
            if (j == null || j.F()) {
                return String.valueOf(obj.getClass());
            }
            boolean z = (method.getModifiers() & LuajavaLib.METHOD_MODIFIERS_VARARGS) != 0 ? true : LuajavaLib.INIT;
            int length = objArr != null ? objArr.length : LuajavaLib.INIT;
            LuaValue[] luaValueArr = new LuaValue[1];
            luaValueArr[LuajavaLib.INIT] = LuaValue.x;
            LuaValue[] luaValueArr2 = luaValueArr;
            if (!z) {
                luaValueArr2 = new LuaValue[length];
                for (int i = LuajavaLib.INIT; i < length; i++) {
                    luaValueArr2[i] = CoerceJavaToLua.coerce(objArr[i]);
                }
            } else if (objArr != null) {
                int i2 = length - 1;
                Object obj2 = objArr[i2];
                int length2 = Array.getLength(obj2);
                luaValueArr2 = new LuaValue[(i2 + length2)];
                for (int i3 = LuajavaLib.INIT; i3 < i2; i3++) {
                    luaValueArr2[i3] = CoerceJavaToLua.coerce(objArr[i3]);
                }
                for (int i4 = LuajavaLib.INIT; i4 < length2; i4++) {
                    luaValueArr2[i4 + i2] = CoerceJavaToLua.coerce(Array.get(obj2, i4));
                }
            }
            try {
                return CoerceLuaToJava.coerce(j.a(luaValueArr2).g(), method.getReturnType());
            } catch (Exception e) {
                return CoerceLuaToJava.coerce(LuaValue.u, method.getReturnType());
            }
        }
    }

    public static Class VgetClass(ap apVar, int i) {
        Class cls;
        try {
            Class<?> cls2 = Class.forName("java.lang.Class");
            if (apVar.isuserdata(i)) {
                return apVar.checkuserdata(i).getClass();
            }
            if (!apVar.i(i)) {
                return cls2;
            }
            try {
                return Class.forName(apVar.r(i));
            } catch (Exception e) {
                return cls;
            }
        } catch (ClassNotFoundException e2) {
            throw new NoClassDefFoundError(e2.getMessage());
        }
    }

    public static LuaValue asTable(Object obj) {
        LuaTable luaTable = new LuaTable();
        if (obj.getClass().isArray()) {
            int length = Array.getLength(obj);
            for (int i = INIT; i <= length - 1; i++) {
                luaTable.a(i + 1, asTable(Array.get(obj, i)));
            }
        } else if (obj instanceof Collection) {
            int i2 = 1;
            for (Object asTable : (Collection) obj) {
                luaTable.a(i2, asTable(asTable));
                i2++;
            }
        } else if (obj instanceof Map) {
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                luaTable.b(CoerceJavaToLua.coerce(entry.getKey()), asTable(entry.getValue()));
            }
        } else if (obj instanceof JSONObject) {
            JSONObject jSONObject = (JSONObject) obj;
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                try {
                    luaTable.a(next, asTable(jSONObject.get(next)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else if (!(obj instanceof JSONArray)) {
            return CoerceJavaToLua.coerce(obj);
        } else {
            JSONArray jSONArray = (JSONArray) obj;
            int length2 = jSONArray.length();
            for (int i3 = INIT; i3 < length2; i3++) {
                try {
                    luaTable.a(i3, asTable(jSONArray.get(i3)));
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return luaTable;
    }

    public static LuaValue asTable(boolean z, Object obj) {
        if (z) {
            return asTable(obj);
        }
        LuaTable luaTable = new LuaTable();
        if (obj.getClass().isArray()) {
            int length = Array.getLength(obj);
            for (int i = INIT; i <= length - 1; i++) {
                luaTable.a(i + 1, CoerceJavaToLua.coerce(Array.get(obj, i)));
            }
        } else if (obj instanceof Collection) {
            for (Object coerce : (Collection) obj) {
                luaTable.a(1 + 1, CoerceJavaToLua.coerce(coerce));
            }
        } else if (obj instanceof Map) {
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                luaTable.b(CoerceJavaToLua.coerce(entry.getKey()), CoerceJavaToLua.coerce(entry.getValue()));
            }
        } else if (obj instanceof JSONObject) {
            JSONObject jSONObject = (JSONObject) obj;
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                try {
                    luaTable.a(next, CoerceJavaToLua.coerce(jSONObject.get(next)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else if (!(obj instanceof JSONArray)) {
            return CoerceJavaToLua.coerce(obj);
        } else {
            JSONArray jSONArray = (JSONArray) obj;
            int length2 = jSONArray.length();
            for (int i2 = INIT; i2 < length2; i2++) {
                try {
                    luaTable.a(i2, CoerceJavaToLua.coerce(jSONArray.get(i2)));
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return luaTable;
    }

    public static JavaClass bindClassForName(String str) throws ClassNotFoundException {
        return JavaClass.forClass(Class.forName(str));
    }

    public static ap castOf(ap apVar) {
        try {
            return CoerceJavaToLua.coerce(VgetClass(apVar, 1).cast(VgetClass(apVar, NEWINSTANCE)));
        } catch (Exception e) {
            return LuaValue.u;
        }
    }

    public static LuaUserdata createProxy(Class cls, LuaValue luaValue) {
        Class[] clsArr = new Class[1];
        clsArr[INIT] = cls;
        return LuaValue.userdataOf(Proxy.newProxyInstance(cls.getClassLoader(), clsArr, new ProxyInvocationHandler(luaValue)));
    }

    public static ap instanceOf(ap apVar) {
        Class VgetClass = VgetClass(apVar, 1);
        Class VgetClass2 = VgetClass(apVar, NEWINSTANCE);
        boolean isAssignableFrom = VgetClass.isAssignableFrom(VgetClass2);
        if (isAssignableFrom) {
            return LuaValue.b(LuaValue.b(isAssignableFrom), LuaValue.m(new StringBuffer().append(new StringBuffer().append(new StringBuffer().append(VgetClass.toString()).append("是").toString()).append(VgetClass2.toString()).toString()).append("的父类").toString()));
        }
        boolean isAssignableFrom2 = VgetClass2.isAssignableFrom(VgetClass);
        return isAssignableFrom2 ? LuaValue.b(LuaValue.b(isAssignableFrom2), LuaValue.m(new StringBuffer().append(new StringBuffer().append(new StringBuffer().append(VgetClass2.toString()).append("是").toString()).append(VgetClass.toString()).toString()).append("的父类").toString())) : LuaValue.b(LuaValue.b(isAssignableFrom2), LuaValue.m(new StringBuffer().append(new StringBuffer().append(new StringBuffer().append(VgetClass2.toString()).append("与").toString()).append(VgetClass.toString()).toString()).append("没有继承与被继承关系").toString()));
    }

    public static LuaValue toArray(LuaTable luaTable, String str) {
        int i = INIT;
        int L = luaTable.L();
        if (str.equals("int")) {
            int[] iArr = new int[L];
            while (i < L) {
                iArr[i] = luaTable.c_(i + 1).v();
                i++;
            }
            return CoerceJavaToLua.coerce(iArr);
        } else if (str.equals("boolean")) {
            boolean[] zArr = new boolean[L];
            while (i < L) {
                zArr[i] = luaTable.c_(i + 1).h();
                i++;
            }
            return CoerceJavaToLua.coerce(zArr);
        } else if (str.equals("long")) {
            long[] jArr = new long[L];
            while (i < L) {
                jArr[i] = luaTable.c_(i + 1).w();
                i++;
            }
            return CoerceJavaToLua.coerce(jArr);
        } else if (str.equals("double")) {
            double[] dArr = new double[L];
            while (i < L) {
                dArr[i] = luaTable.c_(i + 1).x();
                i++;
            }
            return CoerceJavaToLua.coerce(dArr);
        } else if (str.equals("float")) {
            float[] fArr = new float[L];
            while (i < L) {
                fArr[i] = (float) luaTable.c_(i + 1).x();
                i++;
            }
            return CoerceJavaToLua.coerce(fArr);
        } else if (str.equals("char")) {
            char[] cArr = new char[L];
            while (i < L) {
                cArr[i] = (char) luaTable.c_(i + 1).v();
                i++;
            }
            return CoerceJavaToLua.coerce(cArr);
        } else if (str.equals("byte")) {
            byte[] bArr = new byte[L];
            while (i < L) {
                bArr[i] = (byte) luaTable.c_(i + 1).v();
                i++;
            }
            return CoerceJavaToLua.coerce(bArr);
        } else if (str.equals("string")) {
            String[] strArr = new String[L];
            while (i < L) {
                strArr[i] = luaTable.c_(i + 1).y();
                i++;
            }
            return CoerceJavaToLua.coerce(strArr);
        } else if (str.equals("short")) {
            short[] sArr = new short[L];
            while (i < L) {
                sArr[i] = (short) luaTable.c_(i + 1).v();
                i++;
            }
            return CoerceJavaToLua.coerce(sArr);
        } else if (str.equals("object")) {
            Object[] objArr = new Object[L];
            while (i < L) {
                objArr[i] = luaTable.c_(i + 1).checkuserdata();
                i++;
            }
            return CoerceJavaToLua.coerce(objArr);
        } else {
            LuaValue[] luaValueArr = new LuaValue[L];
            while (i < L) {
                luaValueArr[i] = luaTable.c_(i + 1).v(1);
                i++;
            }
            return CoerceJavaToLua.coerce(luaValueArr);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:43:0x01a8, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x01ae, code lost:
        throw new luaj.o(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x01af, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x01b9, code lost:
        throw new luaj.o(r0.getTargetException());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x01ba, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x01bb, code lost:
        throw r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x01af A[ExcHandler: InvocationTargetException (r0v2 'e' java.lang.reflect.InvocationTargetException A[CUSTOM_DECLARE]), Splitter:B:1:0x0002] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x01ba A[ExcHandler: o (r0v1 'e' luaj.o A[CUSTOM_DECLARE]), Splitter:B:1:0x0002] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public luaj.ap a_(luaj.ap r7) {
        /*
            r6 = this;
            java.lang.String r0 = "luajava"
            int r1 = r6.aj     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            r2 = 2
            r3 = 1
            switch(r1) {
                case 0: goto L_0x011f;
                case 1: goto L_0x0111;
                case 2: goto L_0x00e6;
                case 3: goto L_0x00e6;
                case 4: goto L_0x00a6;
                case 5: goto L_0x0082;
                case 6: goto L_0x005a;
                case 7: goto L_0x0050;
                case 8: goto L_0x0040;
                case 9: goto L_0x0019;
                case 10: goto L_0x0019;
                case 11: goto L_0x0013;
                case 12: goto L_0x000d;
                default: goto L_0x0009;
            }     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
        L_0x0009:
            luaj.o r0 = new luaj.o     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            goto L_0x0191
        L_0x000d:
            luaj.ap r0 = castOf(r7)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            goto L_0x01bc
        L_0x0013:
            luaj.ap r0 = instanceOf(r7)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            goto L_0x01bc
        L_0x0019:
            luaj.LuaTable r0 = new luaj.LuaTable     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            r0.<init>()     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            r2 = 1
            java.lang.String r2 = r7.r(r2)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            java.lang.Class r2 = java.lang.Class.forName(r2)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            java.lang.reflect.Method[] r2 = r2.getMethods()     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
        L_0x002b:
            int r3 = r2.length     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            if (r1 >= r3) goto L_0x01bc
            int r3 = r1 + 1
            r4 = r2[r1]     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            java.lang.String r4 = r4.toString()     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            luaj.LuaString r4 = luaj.LuaValue.m(r4)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            r0.b(r3, r4)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            int r1 = r1 + 1
            goto L_0x002b
        L_0x0040:
            r0 = 1
            luaj.LuaTable r0 = r7.t(r0)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            r1 = 2
            java.lang.String r1 = r7.r(r1)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            luaj.LuaValue r0 = toArray(r0, r1)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            goto L_0x01bc
        L_0x0050:
            java.lang.Object r0 = r7.checkuserdata(r3)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            luaj.LuaValue r0 = asTable(r0)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            goto L_0x01bc
        L_0x005a:
            r0 = 1
            java.lang.String r2 = r7.r(r0)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            r0 = 2
            java.lang.String r3 = r7.r(r0)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            r0 = 3
            java.lang.Object r0 = r7.checkuserdata(r0)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            android.content.Context r0 = (android.content.Context) r0     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            dalvik.system.DexClassLoader r4 = new dalvik.system.DexClassLoader     // Catch:{ Exception -> 0x007d, InvocationTargetException -> 0x01af, o -> 0x01ba }
            r1 = 0
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ Exception -> 0x007d, InvocationTargetException -> 0x01af, o -> 0x01ba }
            java.lang.ClassLoader r0 = r0.getClassLoader()     // Catch:{ Exception -> 0x007d, InvocationTargetException -> 0x01af, o -> 0x01ba }
            r4.<init>(r2, r3, r1, r0)     // Catch:{ Exception -> 0x007d, InvocationTargetException -> 0x01af, o -> 0x01ba }
            luaj.LuaValue r0 = luaj.lib.jse.CoerceJavaToLua.coerce(r4)     // Catch:{ Exception -> 0x007d, InvocationTargetException -> 0x01af, o -> 0x01ba }
            goto L_0x01bc
        L_0x007d:
            r0 = move-exception
            luaj.LuaValue r0 = luaj.LuaValue.u     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            goto L_0x01bc
        L_0x0082:
            java.lang.String r0 = r7.r(r3)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            java.lang.String r1 = r7.r(r2)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            java.lang.Class r2 = r6.classForName(r0)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            r3 = 0
            java.lang.Class[] r4 = new java.lang.Class[r3]     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            java.lang.reflect.Method r4 = r2.getMethod(r1, r4)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            java.lang.Object r3 = r4.invoke(r2, r3)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            boolean r5 = r3 instanceof luaj.LuaValue     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            if (r5 == 0) goto L_0x00a3
            r5 = r3
            luaj.LuaValue r5 = (luaj.LuaValue) r5     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            return r5
        L_0x00a3:
            luaj.LuaValue r5 = u     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            return r5
        L_0x00a6:
            int r0 = r7.j_()     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            int r0 = r0 - r3
            if (r0 <= 0) goto L_0x00de
            int r1 = r0 + 1
            luaj.LuaTable r1 = r7.t(r1)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            java.lang.Class[] r2 = new java.lang.Class[r0]     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            r3 = 0
        L_0x00b6:
            if (r3 >= r0) goto L_0x00c7
            int r4 = r3 + 1
            java.lang.String r4 = r7.r(r4)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            java.lang.Class r4 = r6.classForName(r4)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            r2[r3] = r4     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            int r3 = r3 + 1
            goto L_0x00b6
        L_0x00c7:
            luaj.lib.jse.LuajavaLib$ProxyInvocationHandler r3 = new luaj.lib.jse.LuajavaLib$ProxyInvocationHandler     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            r3.<init>(r1)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            java.lang.Class r4 = r6.getClass()     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            java.lang.ClassLoader r4 = r4.getClassLoader()     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            java.lang.Object r4 = java.lang.reflect.Proxy.newProxyInstance(r4, r2, r3)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            luaj.LuaUserdata r0 = luaj.LuaValue.userdataOf(r4)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            goto L_0x01bc
        L_0x00de:
            luaj.o r1 = new luaj.o     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            java.lang.String r2 = "no interfaces"
            r1.<init>(r2)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            throw r1     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
        L_0x00e6:
            luaj.LuaValue r0 = r7.v(r3)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            int r1 = r6.aj     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            if (r1 != r2) goto L_0x00f7
            java.lang.String r1 = r0.d_()     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            java.lang.Class r1 = r6.classForName(r1)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            goto L_0x00ff
        L_0x00f7:
            java.lang.Class<java.lang.Class> r1 = java.lang.Class.class
            java.lang.Object r1 = r0.checkuserdata(r1)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            java.lang.Class r1 = (java.lang.Class) r1     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
        L_0x00ff:
            luaj.ap r2 = r7.e_(r2)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            luaj.lib.jse.JavaClass r3 = luaj.lib.jse.JavaClass.forClass(r1)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            luaj.LuaValue r3 = r3.getConstructor()     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            luaj.ap r0 = r3.a_(r2)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            goto L_0x01bc
        L_0x0111:
            java.lang.String r0 = r7.r(r3)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            java.lang.Class r0 = r6.classForName(r0)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            luaj.lib.jse.JavaClass r0 = luaj.lib.jse.JavaClass.forClass(r0)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            goto L_0x01bc
        L_0x011f:
            luaj.LuaValue r1 = r7.c(r2)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            luaj.LuaTable r2 = new luaj.LuaTable     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            r2.<init>()     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            java.lang.Class r4 = r6.getClass()     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            java.lang.String[] r5 = NAMES     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            r6.a(r2, r4, r5, r3)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            r1.a(r0, r2)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            java.lang.String r3 = "package"
            luaj.LuaValue r3 = r1.j(r3)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            java.lang.String r4 = "loaded"
            luaj.LuaValue r3 = r3.j(r4)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            r3.a(r0, r2)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            java.lang.String r0 = "byte"
            java.lang.Class r3 = java.lang.Byte.TYPE     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            luaj.lib.jse.JavaClass r3 = luaj.lib.jse.JavaClass.forClass(r3)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            r1.a(r0, r3)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            java.lang.String r0 = "char"
            java.lang.Class r3 = java.lang.Character.TYPE     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            luaj.lib.jse.JavaClass r3 = luaj.lib.jse.JavaClass.forClass(r3)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            r1.a(r0, r3)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            java.lang.String r0 = "short"
            java.lang.Class r3 = java.lang.Short.TYPE     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            luaj.lib.jse.JavaClass r3 = luaj.lib.jse.JavaClass.forClass(r3)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            r1.a(r0, r3)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            java.lang.String r0 = "int"
            java.lang.Class r3 = java.lang.Integer.TYPE     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            luaj.lib.jse.JavaClass r3 = luaj.lib.jse.JavaClass.forClass(r3)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            r1.a(r0, r3)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            java.lang.String r0 = "long"
            java.lang.Class r3 = java.lang.Long.TYPE     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            luaj.lib.jse.JavaClass r3 = luaj.lib.jse.JavaClass.forClass(r3)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            r1.a(r0, r3)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            java.lang.String r0 = "float"
            java.lang.Class r3 = java.lang.Float.TYPE     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            luaj.lib.jse.JavaClass r3 = luaj.lib.jse.JavaClass.forClass(r3)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            r1.a(r0, r3)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            java.lang.String r0 = "double"
            java.lang.Class r3 = java.lang.Double.TYPE     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            luaj.lib.jse.JavaClass r3 = luaj.lib.jse.JavaClass.forClass(r3)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            r1.a(r0, r3)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            return r2
        L_0x0191:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            r1.<init>()     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            java.lang.String r2 = "not yet supported: "
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            java.lang.StringBuilder r1 = r1.append(r6)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            java.lang.String r1 = r1.toString()     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            r0.<init>(r1)     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
            throw r0     // Catch:{ o -> 0x01ba, InvocationTargetException -> 0x01af, Exception -> 0x01a8 }
        L_0x01a8:
            r0 = move-exception
            luaj.o r1 = new luaj.o
            r1.<init>(r0)
            throw r1
        L_0x01af:
            r0 = move-exception
            luaj.o r1 = new luaj.o
            java.lang.Throwable r2 = r0.getTargetException()
            r1.<init>(r2)
            throw r1
        L_0x01ba:
            r0 = move-exception
            throw r0
        L_0x01bc:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: luaj.lib.jse.LuajavaLib.a_(luaj.ap):luaj.ap");
    }

    /* access modifiers changed from: protected */
    public Class<?> classForName(String str) throws ClassNotFoundException {
        return Class.forName(str);
    }
}
