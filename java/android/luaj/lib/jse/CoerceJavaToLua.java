package luaj.lib.jse;

import java.util.HashMap;
import java.util.Map;
import luaj.LuaDouble;
import luaj.LuaLong;
import luaj.LuaString;
import luaj.LuaValue;

public class CoerceJavaToLua {
    static final Map COERCIONS;
    static final Coercion arrayCoercion = new ArrayCoercion();
    static final Coercion instanceCoercion = new InstanceCoercion();
    static final Coercion luaCoercion = new LuaCoercion();

    private static final class ArrayCoercion implements Coercion {
        public LuaValue coerce(Object obj) {
            return new JavaArray(obj);
        }
    }

    private static final class BoolCoercion implements Coercion {
        public LuaValue coerce(Object obj) {
            return ((Boolean) obj).booleanValue() ? LuaValue.v : LuaValue.w;
        }
    }

    private static final class CharCoercion implements Coercion {
        public LuaValue coerce(Object obj) {
            return LuaLong.b((long) ((Character) obj).charValue());
        }
    }

    private static final class ClassCoercion implements Coercion {
        public LuaValue coerce(Object obj) {
            return JavaClass.forClass((Class) obj);
        }
    }

    interface Coercion {
        LuaValue coerce(Object obj);
    }

    private static final class DoubleCoercion implements Coercion {
        public LuaValue coerce(Object obj) {
            return LuaDouble.a(((Number) obj).doubleValue());
        }
    }

    private static final class InstanceCoercion implements Coercion {
        public LuaValue coerce(Object obj) {
            return new JavaInstance(obj);
        }
    }

    private static final class IntCoercion implements Coercion {
        public LuaValue coerce(Object obj) {
            return LuaLong.b((long) ((Number) obj).intValue());
        }
    }

    private static final class LuaCoercion implements Coercion {
        public LuaValue coerce(Object obj) {
            return (LuaValue) obj;
        }
    }

    private static final class StringCoercion implements Coercion {
        public LuaValue coerce(Object obj) {
            return LuaString.c(obj.toString());
        }
    }

    static {
        HashMap hashMap = new HashMap();
        COERCIONS = hashMap;
        BoolCoercion boolCoercion = new BoolCoercion();
        IntCoercion intCoercion = new IntCoercion();
        CharCoercion charCoercion = new CharCoercion();
        DoubleCoercion doubleCoercion = new DoubleCoercion();
        StringCoercion stringCoercion = new StringCoercion();
        ClassCoercion classCoercion = new ClassCoercion();
        hashMap.put(Boolean.class, boolCoercion);
        hashMap.put(Byte.class, intCoercion);
        hashMap.put(Character.class, charCoercion);
        hashMap.put(Short.class, intCoercion);
        hashMap.put(Integer.class, intCoercion);
        hashMap.put(Long.class, doubleCoercion);
        hashMap.put(Float.class, doubleCoercion);
        hashMap.put(Double.class, doubleCoercion);
        hashMap.put(String.class, stringCoercion);
        hashMap.put(Class.class, classCoercion);
    }

    public static LuaValue coerce(Object obj) {
        if (obj == null) {
            return LuaValue.u;
        }
        Class<?> cls = obj.getClass();
        Map map = COERCIONS;
        Coercion coercion = (Coercion) map.get(cls);
        if (coercion == null) {
            coercion = cls.isArray() ? arrayCoercion : obj instanceof LuaValue ? luaCoercion : instanceCoercion;
            map.put(cls, coercion);
        }
        return coercion.coerce(obj);
    }
  }
