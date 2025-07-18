package luaj.lib.jse;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import luaj.LuaValue;
import luaj.lib.BaseLib;

public class JseBaseLib extends BaseLib {
    public LuaValue a(LuaValue luaValue, LuaValue luaValue2) {
        JseBaseLib.super.a(luaValue, luaValue2);
        luaValue2.c().STDIN = System.in;
        return luaValue2;
    }

    public InputStream c(String str) {
        File file = new File(str);
        if (!file.exists()) {
            return JseBaseLib.super.c(str);
        }
        try {
            return new BufferedInputStream(new FileInputStream(file));
        } catch (IOException unused) {
            return null;
        }
    }
}
