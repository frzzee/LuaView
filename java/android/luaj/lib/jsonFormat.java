package luaj.lib;

import luaj.LuaValue;
import luaj.ap;

final class jsonFormat extends VarArgFunction {
    private static String SPACE = "  ";

    jsonFormat() {
    }

    public ap a_(ap apVar) {
        return LuaValue.m(setFormat(apVar.r(1)));
    }

    public static String setFormat(String str) {
        StringBuffer stringBuffer;
        String str2 = str;
        new StringBuffer();
        StringBuffer stringBuffer2 = stringBuffer;
        int length = str2.length();
        int i = 0;
        char c = (char) 0;
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str2.charAt(i2);
            if (charAt == '[' || charAt == '{') {
                if (i2 - 1 > 0 && str2.charAt(i2 - 1) == ':') {
                    StringBuffer append = stringBuffer2.append(10);
                    StringBuffer append2 = stringBuffer2.append(indent(i));
                }
                StringBuffer append3 = stringBuffer2.append(charAt);
                StringBuffer append4 = stringBuffer2.append(10);
                i++;
                StringBuffer append5 = stringBuffer2.append(indent(i));
            } else if (charAt == ']' || charAt == '}') {
                StringBuffer append6 = stringBuffer2.append(10);
                i--;
                StringBuffer append7 = stringBuffer2.append(indent(i));
                StringBuffer append8 = stringBuffer2.append(charAt);
                if (i2 + 1 < length && str2.charAt(i2 + 1) != ',') {
                    StringBuffer append9 = stringBuffer2.append(10);
                }
            } else if (charAt == ',') {
                StringBuffer append10 = stringBuffer2.append(charAt);
                StringBuffer append11 = stringBuffer2.append(10);
                StringBuffer append12 = stringBuffer2.append(indent(i));
            } else {
                StringBuffer append13 = stringBuffer2.append(charAt);
            }
        }
        return stringBuffer2.toString();
    }

    private static String indent(int i) {
        StringBuffer stringBuffer;
        int i2 = i;
        new StringBuffer();
        StringBuffer stringBuffer2 = stringBuffer;
        for (int i3 = 0; i3 < i2; i3++) {
            StringBuffer append = stringBuffer2.append(SPACE);
        }
        return stringBuffer2.toString();
    }
}
