package luaj;

public class LuaUserdata extends LuaValue {
    public Object m_instance;
    public LuaValue m_metatable;

    public LuaUserdata(Object obj) {
        this.m_instance = obj;
    }

    public LuaUserdata(Object obj, LuaValue luaValue) {
        this.m_instance = obj;
        this.m_metatable = luaValue;
    }

    public void b(LuaValue luaValue, LuaValue luaValue2) {
        if (this.m_metatable == null || !LuaValue.d(this, luaValue, luaValue2)) {
            LuaValue.f("cannot set " + luaValue + " for userdata");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0014, code lost:
        r0 = r5.i();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean b(luaj.LuaValue r5) {
        /*
            r4 = this;
            boolean r0 = r5.raweq(r4)
            r1 = 1
            if (r0 == 0) goto L_0x0008
            return r1
        L_0x0008:
            luaj.LuaValue r0 = r4.m_metatable
            r2 = 0
            if (r0 == 0) goto L_0x0025
            boolean r0 = r5.isuserdata()
            if (r0 != 0) goto L_0x0014
            goto L_0x0025
        L_0x0014:
            luaj.LuaValue r0 = r5.i()
            if (r0 == 0) goto L_0x0023
            luaj.LuaValue r3 = r4.m_metatable
            boolean r5 = luaj.LuaValue.eqmtcall(r4, r3, r5, r0)
            if (r5 == 0) goto L_0x0023
            goto L_0x0024
        L_0x0023:
            r1 = 0
        L_0x0024:
            return r1
        L_0x0025:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: luaj.LuaUserdata.b(luaj.LuaValue):boolean");
    }

    public boolean c(LuaValue luaValue) {
        return luaValue.raweq(this);
    }

    public Object checkuserdata() {
        return this.m_instance;
    }

    public Object checkuserdata(Class cls) {
        return cls.isAssignableFrom(this.m_instance.getClass()) ? this.m_instance : h(cls.getName());
    }

    public String d_() {
        return String.valueOf(this.m_instance);
    }

    public int e_() {
        return 7;
    }

    public LuaValue eq(LuaValue luaValue) {
        return b(luaValue) ? LuaValue.v : LuaValue.w;
    }

    public boolean eqmt(LuaValue luaValue) {
        if (this.m_metatable == null || !luaValue.isuserdata()) {
            return false;
        }
        return LuaValue.eqmtcall(this, this.m_metatable, luaValue, luaValue.i());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LuaUserdata)) {
            return false;
        }
        return this.m_instance.equals(((LuaUserdata) obj).m_instance);
    }

    public String f_() {
        return "userdata";
    }

    public Object getObject(LuaValue luaValue) {
        if (luaValue.F()) {
            return null;
        }
        return luaValue.I() ? luaValue.h_() ? Integer.valueOf(luaValue.v()) : luaValue.E() ? Long.valueOf(luaValue.w()) : Double.valueOf(luaValue.x()) : luaValue.n_() ? Boolean.valueOf(luaValue.h()) : luaValue.N() ? luaValue.O() : luaValue.J() ? luaValue.y() : luaValue;
    }

    public int hashCode() {
        return this.m_instance.hashCode();
    }

    public LuaValue i() {
        return this.m_metatable;
    }

    public boolean isnil() {
        return this.m_instance == null;
    }

    public boolean isuserdata() {
        return true;
    }

    public boolean isuserdata(Class cls) {
        return cls.isAssignableFrom(this.m_instance.getClass());
    }

    public LuaValue not() {
        return this.m_instance == null ? v : w;
    }

    public Object optuserdata(Class cls, Object obj) {
        if (!cls.isAssignableFrom(this.m_instance.getClass())) {
            h(cls.getName());
        }
        return this.m_instance;
    }

    public Object optuserdata(Object obj) {
        return this.m_instance;
    }

    public boolean raweq(LuaUserdata luaUserdata) {
        return this == luaUserdata || (this.m_metatable == luaUserdata.m_metatable && this.m_instance.equals(luaUserdata.m_instance));
    }

    public boolean toboolean() {
        Object obj = this.m_instance;
        if (obj == null) {
            return false;
        }
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        if (obj instanceof LuaValue) {
            return ((LuaValue) obj).i_();
        }
        return true;
    }

    public Object touserdata() {
        return this.m_instance;
    }

    public Object touserdata(Class cls) {
        if (cls.isAssignableFrom(this.m_instance.getClass())) {
            return this.m_instance;
        }
        return null;
    }

    public Object userdata() {
        return this.m_instance;
    }

    public LuaValue v(LuaValue luaValue) {
        this.m_metatable = luaValue;
        return this;
    }

    public LuaValue w(LuaValue luaValue) {
        return this.m_metatable != null ? LuaValue.i(this, luaValue) : LuaValue.u;
    }
}
