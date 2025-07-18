package luaj.lib.jse;

import luaj.lib.StringLib;

public class JseStringLib extends StringLib {
    /* access modifiers changed from: protected */
    public String format(String str, double d) {
        try {
            return String.format(str, new Object[]{Double.valueOf(d)});
        } catch (Throwable unused) {
            return JseStringLib.super.format(str, d);
        }
    }
}
