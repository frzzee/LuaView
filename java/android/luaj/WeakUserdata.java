package luaj;

import java.lang.ref.WeakReference;
import luaj.WeakTable;

final class WeakUserdata extends WeakTable.WeakValue {
    private final LuaValue mt;
    private final WeakReference ob;

    private WeakTable.WeakUserdata(LuaValue luaValue) {
        super(luaValue);
        this.ob = new WeakReference(luaValue.touserdata());
        this.mt = luaValue.i();
    }

    public LuaValue ac() {
        Object obj = this.ref.get();
        if (obj != null) {
            return (LuaValue) obj;
        }
        Object obj2 = this.ob.get();
        if (obj2 == null) {
            return null;
        }
        LuaUserdata userdataOf = LuaValue.userdataOf(obj2, this.mt);
        this.ref = new WeakReference(userdataOf);
        return userdataOf;
    }
}
