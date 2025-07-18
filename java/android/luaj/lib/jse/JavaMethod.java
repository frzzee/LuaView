package luaj.lib.jse;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import luaj.LuaFunction;
import luaj.LuaValue;
import luaj.ap;
import luaj.o;

class JavaMethod extends JavaMember {
    static final Map methods = Collections.synchronizedMap(new HashMap());
    final Method method;
    private final Class<?> returnType;

    static class Overload extends LuaFunction {
        final JavaMethod[] methods;

        Overload(JavaMethod[] javaMethodArr) {
            this.methods = javaMethodArr;
        }

        private LuaValue invokeBestMethod(Object obj, ap apVar) {
            JavaMethod javaMethod = null;
            int i = CoerceLuaToJava.SCORE_UNCOERCIBLE;
            int i2 = 0;
            while (true) {
                JavaMethod[] javaMethodArr = this.methods;
                if (i2 >= javaMethodArr.length) {
                    break;
                }
                int score = javaMethodArr[i2].score(apVar);
                if (score < i) {
                    i = score;
                    javaMethod = this.methods[i2];
                    if (i == 0) {
                        break;
                    }
                }
                i2++;
            }
            if (javaMethod == null) {
                LuaValue.f("no coercible public method");
            }
            javaMethod.setuservalue(this.uservalue);
            return javaMethod.invokeMethod(obj, apVar);
        }

        public LuaValue a(LuaValue luaValue) {
            return invokeBestMethod(this.uservalue.touserdata(), luaValue);
        }

        public LuaValue a(LuaValue luaValue, LuaValue luaValue2) {
            return invokeBestMethod(this.uservalue.touserdata(), LuaValue.b(luaValue, luaValue2));
        }

        public LuaValue a(LuaValue luaValue, LuaValue luaValue2, LuaValue luaValue3) {
            return invokeBestMethod(this.uservalue.touserdata(), LuaValue.a(luaValue, luaValue2, luaValue3));
        }

        public ap a(LuaValue[] luaValueArr) {
            return a_(LuaValue.c(luaValueArr));
        }

        public ap a_(ap apVar) {
            return invokeBestMethod(this.uservalue.touserdata(), apVar);
        }

        public LuaValue l() {
            return invokeBestMethod(this.uservalue.touserdata(), LuaValue.x);
        }
    }

    private JavaMethod(Method method2) {
        super(method2.getParameterTypes(), method2.getModifiers());
        this.method = method2;
        this.returnType = method2.getReturnType();
        try {
            if (!method2.isAccessible()) {
                method2.setAccessible(true);
            }
        } catch (SecurityException e) {
        }
    }

    static JavaMethod forMethod(Method method2) {
        Map map = methods;
        JavaMethod javaMethod = (JavaMethod) map.get(method2);
        if (javaMethod != null) {
            return javaMethod;
        }
        JavaMethod javaMethod2 = new JavaMethod(method2);
        JavaMethod javaMethod3 = javaMethod2;
        map.put(method2, javaMethod2);
        return javaMethod3;
    }

    static LuaFunction forMethods(JavaMethod[] javaMethodArr) {
        return new Overload(javaMethodArr);
    }

    public LuaValue a(LuaValue luaValue) {
        return invokeMethod(this.uservalue.touserdata(), luaValue);
    }

    public LuaValue a(LuaValue luaValue, LuaValue luaValue2) {
        return invokeMethod(this.uservalue.touserdata(), LuaValue.b(luaValue, luaValue2));
    }

    public LuaValue a(LuaValue luaValue, LuaValue luaValue2, LuaValue luaValue3) {
        return invokeMethod(this.uservalue.touserdata(), LuaValue.a(luaValue, luaValue2, luaValue3));
    }

    public ap a(LuaValue[] luaValueArr) {
        return a_(LuaValue.c(luaValueArr));
    }

    public ap a_(ap apVar) {
        return invokeMethod(this.uservalue.touserdata(), apVar);
    }

    /* access modifiers changed from: package-private */
    public LuaValue invokeMethod(Object obj, ap apVar) {
        Object[] convertArgs = convertArgs(apVar);
        try {
            if (this.returnType != Void.TYPE) {
                return CoerceJavaToLua.coerce(this.method.invoke(obj, convertArgs));
            }
            this.method.invoke(obj, convertArgs);
            return this.uservalue;
        } catch (InvocationTargetException e) {
            throw new o(e.getTargetException());
        } catch (Exception e2) {
            e2.printStackTrace();
            return LuaValue.f("coercion error " + e2);
        }
    }

    public LuaValue l() {
        return invokeMethod(this.uservalue.touserdata(), LuaValue.x);
    }
}
