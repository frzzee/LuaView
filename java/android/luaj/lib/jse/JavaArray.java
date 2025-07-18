package luaj.lib.jse;

import java.lang.reflect.Array;
import luaj.LuaTable;
import luaj.LuaUserdata;
import luaj.LuaValue;
import luaj.ap;
import luaj.lib.OneArgFunction;

class JavaArray extends LuaUserdata {
    static final LuaValue CLASS = m("class");
    static final LuaValue LENGTH = m("length");
    static final LuaTable array_metatable;

    private static final class LenFunction extends OneArgFunction {
        public LuaValue a(LuaValue luaValue) {
            return LuaValue.d((long) Array.getLength(((LuaUserdata) luaValue).m_instance));
        }
    }

    static {
        LuaTable luaTable = new LuaTable();
        array_metatable = luaTable;
        luaTable.c(LuaValue.P, new LenFunction());
    }

    JavaArray(Object obj) {
        super(obj);
        v(array_metatable);
    }

    public ap A(LuaValue luaValue) {
        int length = Array.getLength(this.m_instance);
        int p = luaValue.F() ? 0 : luaValue.p() + 1;
        if (p >= length) {
            return LuaValue.u;
        }
        return LuaValue.c(new LuaValue[]{CoerceJavaToLua.coerce(Integer.valueOf(p)), CoerceJavaToLua.coerce(Array.get(this.m_instance, p))});
    }

    public void b(LuaValue luaValue, LuaValue luaValue2) {
        if (luaValue.h_()) {
            int p = luaValue.p() - 1;
            if (p >= 0 && p < Array.getLength(this.m_instance)) {
                Array.set(this.m_instance, p, CoerceLuaToJava.coerce(luaValue2, this.m_instance.getClass().getComponentType()));
            } else if (this.m_metatable == null || (I(E).F() && !d(this, luaValue, luaValue2))) {
                f("array index out of bounds");
            }
        } else {
            super.b(luaValue, luaValue2);
        }
    }

    public LuaValue w(LuaValue luaValue) {
        if (luaValue.equals(LENGTH)) {
            return d((long) Array.getLength(this.m_instance));
        }
        if (luaValue.equals(CLASS)) {
            return CoerceJavaToLua.coerce(this.m_instance.getClass());
        }
        if (!luaValue.h_()) {
            return super.w(luaValue);
        }
        int p = luaValue.p() - 1;
        return (p < 0 || p >= Array.getLength(this.m_instance)) ? u : CoerceJavaToLua.coerce(Array.get(this.m_instance, luaValue.p() - 1));
    }
}
