package luaj.lib.jse;

import luaj.Globals;
import luaj.LuaTable;
import luaj.LuaValue;
import luaj.a.t;
import luaj.ap;
import luaj.f;
import luaj.lib.Bit32Lib;
import luaj.lib.DebugLib;
import luaj.lib.PackageLib;
import luaj.lib.TableLib;
import luaj.lib.Utf8Lib;

public class JsePlatform {
    public static Globals debugGlobals() {
        Globals standardGlobals = standardGlobals();
        standardGlobals.F(new DebugLib());
        return standardGlobals;
    }

    public static ap luaMain(LuaValue luaValue, String[] strArr) {
        Globals standardGlobals = standardGlobals();
        int length = strArr.length;
        LuaValue[] luaValueArr = new LuaValue[strArr.length];
        for (int i = 0; i < length; i++) {
            luaValueArr[i] = LuaValue.m(strArr[i]);
        }
        LuaTable listOf = LuaValue.listOf(luaValueArr);
        listOf.a("n", length);
        standardGlobals.a("arg", listOf);
        luaValue.K(standardGlobals);
        return luaValue.a_(LuaValue.c(luaValueArr));
    }

    public static Globals standardGlobals() {
        Globals globals = new Globals();
        globals.F(new JseBaseLib());
        globals.F(new PackageLib());
        globals.F(new Bit32Lib());
        globals.F(new TableLib());
        globals.F(new JseStringLib());
        globals.F(new JseMathLib());
        globals.F(new JseIoLib());
        globals.F(new JseOsLib());
        globals.F(new LuajavaLib());
        globals.F(new DebugLib());
        globals.F(new Utf8Lib());
        f.a(globals);
        t.a(globals);
        return globals;
    }
}
