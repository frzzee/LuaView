package luaj.lib;

import android.ext.ar;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import luaj.LuaTable;
import luaj.LuaValue;
import luaj.ap;
import luaj.lib.jse.CoerceJavaToLua;
import luaj.lib.jse.CoerceLuaToJava;
import luaj.o;
import luaj.z;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.android.AndroidClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import net.bytebuddy.implementation.bind.annotation.This;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

public class ProxyUtils {

    public static class Interceptor {
        private LuaValue obj;

        public Interceptor(LuaValue luaValue) {
            this.obj = luaValue;
        }

        @RuntimeType
        public Object intercept(@This Object obj2, @AllArguments Object[] objArr, @Origin Method method, @SuperCall Callable<Object> callable) throws Exception {
            LuaValue[] luaValueArr;
            LuaValue luaValue = this.obj;
            if (luaValue == null) {
                return callable.call();
            }
            LuaValue j = luaValue.j(method.getName());
            if (j == null || j.F()) {
                return callable.call();
            }
            int i = 0;
            boolean z = (method.getModifiers() & 128) != 0;
            int length = objArr != null ? objArr.length : 0;
            if (objArr == null) {
                return callable.call();
            }
            if (z) {
                int i2 = length - 1;
                Object obj3 = objArr[i2];
                int length2 = Array.getLength(obj3);
                luaValueArr = new LuaValue[(i2 + length2 + 1)];
                for (int i3 = 0; i3 < i2; i3++) {
                    luaValueArr[i3] = CoerceJavaToLua.coerce(objArr[i3]);
                }
                while (i < length2) {
                    luaValueArr[i + i2] = CoerceJavaToLua.coerce(Array.get(obj3, i));
                    i++;
                }
            } else {
                luaValueArr = new LuaValue[(length + 1)];
                while (i < length) {
                    luaValueArr[i] = CoerceJavaToLua.coerce(objArr[i]);
                    i++;
                }
            }
            luaValueArr[luaValueArr.length - 1] = CoerceJavaToLua.coerce(obj2);
            try {
                LuaValue g = j.a(luaValueArr).g();
                callable.call();
                return CoerceLuaToJava.coerce(g, method.getReturnType());
            } catch (Exception unused) {
                return callable.call();
            }
        }
    }

    public static ap a_(ap apVar) {
        if (apVar.j_() - 1 > 0) {
            try {
                LuaTable t = apVar.t(3);
                Class<?> cls = Class.forName(apVar.c(2, "java.lang.Object"));
                String c = apVar.c(1, cls.getName() + "$" + ModLib.generateRandomString(10));
                LuaValue coerce = CoerceJavaToLua.coerce(createProxy(cls, c, t));
                ModLib.ImportExecute(c);
                return coerce;
            } catch (Exception e) {
                throw new o(e);
            }
        } else {
            throw new o("no method");
        }
    }

    public static Class<?> createProxy(Class<?> cls, String str, LuaValue luaValue) throws IllegalAccessException, InstantiationException {
        z S = luaValue.O().S();
        ElementMatcher.Junction junction = null;
        while (S.a()) {
            String y = S.c().y();
            LuaValue d = S.d();
            if (junction == null) {
                if (d.B()) {
                    junction = ElementMatchers.named(y);
                }
            } else if (d.B()) {
                junction = junction.or(ElementMatchers.named(y));
            }
        }
        if (junction != null) {
            ar arVar = ar.d;
            Class<?> loaded = new ByteBuddy().subclass(cls).name(str).method(junction).intercept(MethodDelegation.to(new Interceptor(luaValue))).make().load(arVar.getClassLoader(), new AndroidClassLoadingStrategy.Wrapping(arVar.getDir("generated", 0))).getLoaded();
            ModLib.imported.put(str, loaded);
            ModLib.At(loaded.getName(), CoerceJavaToLua.coerce(loaded));
            return loaded;
        }
        throw new o("no proxy required");
    }
}
