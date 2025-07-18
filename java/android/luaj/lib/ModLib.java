package luaj.lib;

import android.content.Context;
import android.ext.LockFunction;
import android.ext.PrintStack;
import android.ext.Tools;
import android.ext.ar;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.view.View;
import android.xml2axml.Loader;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import luaj.Globals;
import luaj.LuaFunction;
import luaj.LuaString;
import luaj.LuaTable;
import luaj.LuaValue;
import luaj.ap;
import luaj.lib.jse.CoerceJavaToLua;
import luaj.o;
import luaj.z;

public class ModLib extends TwoArgFunction {
    public static Globals _G;
    public static String classpath;
    public static String[] dex = {"dex", "apk", "zip", "jar", "7z", "odex", "java"};
    public static ArrayList<DexClassLoader> dexes;
    public static String dexpath;
    static LuaFunction func = new VarArgFunction() {
        public ap a_(ap apVar) {
            return LuaValue.v;
        }
    };
    public static LuaTable ids;
    public static HashMap<String, Class<?>> imported;
    public static String jarpath;
    public static LuaTable mt = new LuaTable();
    public static String[] png = {"jpeg", "JPEG", "Jpeg", "jpg", "JPG", "Jpg", "png", "PNG", "Png", "gif", "GIF", "SVG", "svg", "XML", "xml"};
    public static String searchpath = "?.lua";

    static class CLASSINDEX extends VarArgFunction {
        CLASSINDEX() {
        }

        public ap a_(ap apVar) {
            z S = ModLib.mt.j("pak").O().S();
            while (S.a()) {
                LuaValue ImportExecute = ModLib.ImportExecute(S.c().d_() + apVar.r(2));
                if (!ImportExecute.F()) {
                    return ImportExecute;
                }
            }
            return LuaValue.u;
        }
    }

    static class GC extends VarArgFunction {
        GC() {
        }

        public ap a_(ap apVar) {
            ModLib.imported.clear();
            ModLib.dexes.clear();
            return LuaValue.u;
        }
    }

    static class Import extends VarArgFunction {
        Import() {
        }

        public ap a_(ap apVar) {
            LuaValue luaValue = u;
            for (int j_ = apVar.j_(); j_ > 0; j_--) {
                luaValue = apVar.c(j_);
                if (luaValue.J()) {
                    luaValue = loadClass(luaValue.y());
                }
            }
            return luaValue;
        }

        public LuaValue loadClass(String str) {
            if (!str.contains("*")) {
                return ModLib.ImportExecute(str);
            }
            ModLib.mt.j("pak").a(str.substring(0, str.length() - 1), 1);
            return u;
        }
    }

    static class InstanceOf extends VarArgFunction {
        InstanceOf() {
        }

        public ap a_(ap apVar) {
            if (apVar.j_() == 2) {
                Object checkuserdata = apVar.checkuserdata(1);
                Object checkuserdata2 = apVar.checkuserdata(2);
                return (!(checkuserdata instanceof Class) || !(checkuserdata2 instanceof Class)) ? ((checkuserdata instanceof Class) || !(checkuserdata2 instanceof Class)) ? LuaValue.b(checkuserdata.equals(checkuserdata2)) : LuaValue.b(((Class) checkuserdata2).isAssignableFrom(checkuserdata.getClass())) : LuaValue.b(((Class) checkuserdata2).isAssignableFrom((Class) checkuserdata));
            }
            throw new o("instanceof函数的参数必须两个");
        }
    }

    static class JavaCompiler extends VarArgFunction {
        JavaCompiler() {
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: java.lang.Object[]} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v15, resolved type: java.lang.Object[]} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public luaj.ap a_(luaj.ap r20) {
            /*
                r19 = this;
                android.ext.ar r1 = android.ext.ar.d
                java.lang.String r2 = "com.sun.tools.javac.Main"
                java.lang.Class r2 = java.lang.Class.forName(r2)     // Catch:{ ClassNotFoundException -> 0x01d0, NoSuchMethodException -> 0x01ce, IllegalAccessException -> 0x01cc, InvocationTargetException -> 0x01ca, FileNotFoundException -> 0x01c8 }
                java.lang.String r3 = "compile"
                r4 = 2
                java.lang.Class[] r5 = new java.lang.Class[r4]     // Catch:{ ClassNotFoundException -> 0x01d0, NoSuchMethodException -> 0x01ce, IllegalAccessException -> 0x01cc, InvocationTargetException -> 0x01ca, FileNotFoundException -> 0x01c8 }
                java.lang.Class<java.lang.String[]> r6 = java.lang.String[].class
                r7 = 0
                r5[r7] = r6     // Catch:{ ClassNotFoundException -> 0x01d0, NoSuchMethodException -> 0x01ce, IllegalAccessException -> 0x01cc, InvocationTargetException -> 0x01ca, FileNotFoundException -> 0x01c8 }
                java.lang.Class<java.io.PrintWriter> r6 = java.io.PrintWriter.class
                r8 = 1
                r5[r8] = r6     // Catch:{ ClassNotFoundException -> 0x01d0, NoSuchMethodException -> 0x01ce, IllegalAccessException -> 0x01cc, InvocationTargetException -> 0x01ca, FileNotFoundException -> 0x01c8 }
                java.lang.reflect.Method r3 = r2.getDeclaredMethod(r3, r5)     // Catch:{ ClassNotFoundException -> 0x01d0, NoSuchMethodException -> 0x01ce, IllegalAccessException -> 0x01cc, InvocationTargetException -> 0x01ca, FileNotFoundException -> 0x01c8 }
                java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException -> 0x01d0, NoSuchMethodException -> 0x01ce, IllegalAccessException -> 0x01cc, InvocationTargetException -> 0x01ca, FileNotFoundException -> 0x01c8 }
                r5.<init>()     // Catch:{ ClassNotFoundException -> 0x01d0, NoSuchMethodException -> 0x01ce, IllegalAccessException -> 0x01cc, InvocationTargetException -> 0x01ca, FileNotFoundException -> 0x01c8 }
                java.io.File r6 = new java.io.File     // Catch:{ ClassNotFoundException -> 0x01d0, NoSuchMethodException -> 0x01ce, IllegalAccessException -> 0x01cc, InvocationTargetException -> 0x01ca, FileNotFoundException -> 0x01c8 }
                java.lang.String r9 = luaj.lib.ModLib.jarpath     // Catch:{ ClassNotFoundException -> 0x01d0, NoSuchMethodException -> 0x01ce, IllegalAccessException -> 0x01cc, InvocationTargetException -> 0x01ca, FileNotFoundException -> 0x01c8 }
                r6.<init>(r9)     // Catch:{ ClassNotFoundException -> 0x01d0, NoSuchMethodException -> 0x01ce, IllegalAccessException -> 0x01cc, InvocationTargetException -> 0x01ca, FileNotFoundException -> 0x01c8 }
                boolean r9 = r6.exists()     // Catch:{ ClassNotFoundException -> 0x01d0, NoSuchMethodException -> 0x01ce, IllegalAccessException -> 0x01cc, InvocationTargetException -> 0x01ca, FileNotFoundException -> 0x01c8 }
                java.lang.String r10 = "log.txt"
                if (r9 == 0) goto L_0x017a
                java.io.File[] r9 = r6.listFiles()     // Catch:{ ClassNotFoundException -> 0x01d0, NoSuchMethodException -> 0x01ce, IllegalAccessException -> 0x01cc, InvocationTargetException -> 0x01ca, FileNotFoundException -> 0x01c8 }
                if (r9 == 0) goto L_0x0175
                int r11 = r9.length     // Catch:{ ClassNotFoundException -> 0x01d0, NoSuchMethodException -> 0x01ce, IllegalAccessException -> 0x01cc, InvocationTargetException -> 0x01ca, FileNotFoundException -> 0x01c8 }
                r12 = 0
            L_0x0037:
                if (r12 >= r11) goto L_0x004c
                r13 = r9[r12]     // Catch:{ ClassNotFoundException -> 0x01d0, NoSuchMethodException -> 0x01ce, IllegalAccessException -> 0x01cc, InvocationTargetException -> 0x01ca, FileNotFoundException -> 0x01c8 }
                java.lang.String r14 = "::"
                java.lang.StringBuilder r14 = r5.append(r14)     // Catch:{ ClassNotFoundException -> 0x01d0, NoSuchMethodException -> 0x01ce, IllegalAccessException -> 0x01cc, InvocationTargetException -> 0x01ca, FileNotFoundException -> 0x01c8 }
                java.lang.String r15 = r13.getAbsolutePath()     // Catch:{ ClassNotFoundException -> 0x01d0, NoSuchMethodException -> 0x01ce, IllegalAccessException -> 0x01cc, InvocationTargetException -> 0x01ca, FileNotFoundException -> 0x01c8 }
                r14.append(r15)     // Catch:{ ClassNotFoundException -> 0x01d0, NoSuchMethodException -> 0x01ce, IllegalAccessException -> 0x01cc, InvocationTargetException -> 0x01ca, FileNotFoundException -> 0x01c8 }
                int r12 = r12 + 1
                goto L_0x0037
            L_0x004c:
                r11 = r20
                java.lang.String r12 = r11.r(r8)     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                if (r12 == 0) goto L_0x0165
                java.io.File r13 = new java.io.File     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                r13.<init>(r12)     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                r6 = r13
                boolean r13 = r6.exists()     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                if (r13 == 0) goto L_0x0162
                java.util.ArrayList r13 = new java.util.ArrayList     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                r13.<init>()     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                java.lang.String r14 = ".java"
                luaj.lib.ModLib.listFile(r13, r6, r14)     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                int r14 = r13.size()     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                int r14 = r14 + 4
                java.lang.String[] r14 = new java.lang.String[r14]     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                java.lang.String r15 = "-classpath"
                r14[r7] = r15     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                int r15 = r5.length()     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                java.lang.String r15 = r5.substring(r4, r15)     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                r14[r8] = r15     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                java.lang.String r15 = "-d"
                r14[r4] = r15     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                java.lang.String r15 = luaj.lib.ModLib.classpath     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                r8 = 3
                r14[r8] = r15     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                r15 = 0
            L_0x008a:
                int r8 = r13.size()     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                if (r15 >= r8) goto L_0x009e
                int r8 = r15 + 4
                java.lang.Object r17 = r13.get(r15)     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                java.lang.String r17 = (java.lang.String) r17     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                r14[r8] = r17     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                int r15 = r15 + 1
                r8 = 3
                goto L_0x008a
            L_0x009e:
                java.lang.Object[] r8 = new java.lang.Object[r4]     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                r8[r7] = r14     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                java.io.PrintWriter r15 = new java.io.PrintWriter     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                r7.<init>()     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                r18 = r5
                java.lang.String r5 = luaj.lib.ModLib.dexpath     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                java.lang.StringBuilder r5 = r7.append(r5)     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                java.lang.StringBuilder r5 = r5.append(r10)     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                java.lang.String r5 = r5.toString()     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                r4.<init>(r5)     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                r15.<init>(r4)     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                r4 = 1
                r8[r4] = r15     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                java.lang.Object r4 = r3.invoke(r2, r8)     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                if (r4 == 0) goto L_0x017e
                r5 = r4
                java.lang.Integer r5 = (java.lang.Integer) r5     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                int r5 = r5.intValue()     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                if (r5 != 0) goto L_0x017e
                java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                r5.<init>()     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                java.lang.String r7 = luaj.lib.ModLib.dexpath     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                java.lang.StringBuilder r5 = r5.append(r7)     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                long r7 = java.lang.System.currentTimeMillis()     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                java.lang.StringBuilder r5 = r5.append(r7)     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                java.lang.String r7 = ".dex"
                java.lang.StringBuilder r5 = r5.append(r7)     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                java.lang.String r5 = r5.toString()     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                r7 = 3
                java.lang.String[] r7 = new java.lang.String[r7]     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                java.lang.String r8 = "--dex"
                r15 = 0
                r7[r15] = r8     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                r8.<init>()     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                java.lang.String r15 = "--output="
                java.lang.StringBuilder r8 = r8.append(r15)     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                java.lang.StringBuilder r8 = r8.append(r5)     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                java.lang.String r8 = r8.toString()     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                r15 = 1
                r7[r15] = r8     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                java.lang.String r8 = luaj.lib.ModLib.classpath     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                r15 = 2
                r7[r15] = r8     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                java.lang.String r8 = "com.android.dx.command.Main"
                java.lang.Class r8 = java.lang.Class.forName(r8)     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                r2 = r8
                java.lang.String r8 = "main"
                r14 = 1
                java.lang.Class[] r15 = new java.lang.Class[r14]     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                java.lang.Class<java.lang.String[]> r14 = java.lang.String[].class
                r16 = 0
                r15[r16] = r14     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                java.lang.reflect.Method r8 = r2.getDeclaredMethod(r8, r15)     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                r3 = r8
                r8 = 1
                java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                r8[r16] = r7     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                r3.invoke(r2, r8)     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                java.io.File r8 = new java.io.File     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                r8.<init>(r5)     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                r6 = r8
                boolean r8 = r6.exists()     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                if (r8 == 0) goto L_0x017e
                dalvik.system.DexClassLoader r8 = new dalvik.system.DexClassLoader     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                java.io.File r10 = r1.getCacheDir()     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                java.lang.String r10 = r10.getAbsolutePath()     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                r14 = 0
                java.lang.ClassLoader r15 = r1.getClassLoader()     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                r8.<init>(r5, r10, r14, r15)     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                java.util.ArrayList<dalvik.system.DexClassLoader> r10 = luaj.lib.ModLib.dexes     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                boolean r10 = r10.contains(r8)     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                if (r10 != 0) goto L_0x015d
                java.util.ArrayList<dalvik.system.DexClassLoader> r10 = luaj.lib.ModLib.dexes     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                r10.add(r8)     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
            L_0x015d:
                luaj.LuaString r10 = luaj.LuaValue.m(r5)     // Catch:{ ClassNotFoundException -> 0x0173, NoSuchMethodException -> 0x0171, IllegalAccessException -> 0x016e, InvocationTargetException -> 0x016b, FileNotFoundException -> 0x0168 }
                return r10
            L_0x0162:
                r18 = r5
                goto L_0x017e
            L_0x0165:
                r18 = r5
                goto L_0x017e
            L_0x0168:
                r0 = move-exception
                goto L_0x01d3
            L_0x016b:
                r0 = move-exception
                goto L_0x01d3
            L_0x016e:
                r0 = move-exception
                goto L_0x01d3
            L_0x0171:
                r0 = move-exception
                goto L_0x01d3
            L_0x0173:
                r0 = move-exception
                goto L_0x01d3
            L_0x0175:
                r11 = r20
                r18 = r5
                goto L_0x017e
            L_0x017a:
                r11 = r20
                r18 = r5
            L_0x017e:
                r2 = 0
                java.io.File r3 = new java.io.File     // Catch:{ IOException -> 0x01b5 }
                java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x01b5 }
                r4.<init>()     // Catch:{ IOException -> 0x01b5 }
                java.lang.String r5 = luaj.lib.ModLib.dexpath     // Catch:{ IOException -> 0x01b5 }
                java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ IOException -> 0x01b5 }
                java.lang.StringBuilder r4 = r4.append(r10)     // Catch:{ IOException -> 0x01b5 }
                java.lang.String r4 = r4.toString()     // Catch:{ IOException -> 0x01b5 }
                r3.<init>(r4)     // Catch:{ IOException -> 0x01b5 }
                boolean r4 = r3.exists()     // Catch:{ IOException -> 0x01b5 }
                if (r4 == 0) goto L_0x01b2
                java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ IOException -> 0x01b5 }
                r4.<init>(r3)     // Catch:{ IOException -> 0x01b5 }
                r2 = r4
                int r4 = r2.available()     // Catch:{ IOException -> 0x01b5 }
                byte[] r4 = new byte[r4]     // Catch:{ IOException -> 0x01b5 }
                r2.read(r4)     // Catch:{ IOException -> 0x01b5 }
                luaj.LuaString r5 = luaj.LuaValue.valueOf(r4)     // Catch:{ IOException -> 0x01b5 }
                return r5
            L_0x01b2:
                luaj.LuaValue r4 = luaj.LuaValue.u     // Catch:{ IOException -> 0x01b5 }
                return r4
            L_0x01b5:
                r0 = move-exception
                r3 = r0
                if (r2 == 0) goto L_0x01c2
                r2.close()     // Catch:{ IOException -> 0x01bd }
                goto L_0x01c2
            L_0x01bd:
                r0 = move-exception
                r4 = r0
                r4.printStackTrace()
            L_0x01c2:
                luaj.o r4 = new luaj.o
                r4.<init>(r3)
                throw r4
            L_0x01c8:
                r0 = move-exception
                goto L_0x01d1
            L_0x01ca:
                r0 = move-exception
                goto L_0x01d1
            L_0x01cc:
                r0 = move-exception
                goto L_0x01d1
            L_0x01ce:
                r0 = move-exception
                goto L_0x01d1
            L_0x01d0:
                r0 = move-exception
            L_0x01d1:
                r11 = r20
            L_0x01d3:
                r2 = r0
                luaj.o r3 = new luaj.o
                r3.<init>(r2)
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: luaj.lib.ModLib.JavaCompiler.a_(luaj.ap):luaj.ap");
        }
    }

    static class LockLog extends VarArgFunction {
        LockLog() {
        }

        public ap a_(ap apVar) {
            try {
                return LockFunction.LockLog(apVar.n(1), apVar.d(2, LuaValue.u));
            } catch (Exception e) {
                return LuaValue.m(e.toString());
            }
        }
    }

    static class LockMain extends VarArgFunction {
        LockMain() {
        }

        public ap a_(ap apVar) {
            try {
                return LockFunction.LockMain(apVar.n(1), apVar.d(2, LuaValue.u));
            } catch (Exception e) {
                return LuaValue.m(e.toString());
            }
        }
    }

    static class LockUi extends VarArgFunction {
        LockUi() {
        }

        public ap a_(ap apVar) {
            try {
                return LockFunction.LockUi(apVar.n(1), apVar.d(2, LuaValue.u));
            } catch (Exception e) {
                return LuaValue.m(e.toString());
            }
        }
    }

    static class LockWrite extends VarArgFunction {
        LockWrite() {
        }

        public ap a_(ap apVar) {
            try {
                return LockFunction.LockWrite(apVar.n(1), apVar.d(2, LuaValue.u));
            } catch (Exception e) {
                return LuaValue.m(e.toString());
            }
        }
    }

    static class MethodProxy extends VarArgFunction {
        MethodProxy() {
        }

        public ap a_(ap apVar) {
            if (apVar.j_() - 1 > 0) {
                LuaTable t = apVar.t(2);
                Class cls = (Class) apVar.checkuserdata(1);
                try {
                    return CoerceJavaToLua.coerce(ProxyUtils.createProxy(cls, apVar.c(3, cls.getName() + "$" + ModLib.generateRandomString(10)), t));
                } catch (IllegalAccessException | InstantiationException e) {
                    throw new o(e);
                }
            } else {
                throw new o("no method");
            }
        }
    }

    static class classcompile extends VarArgFunction {
        classcompile() {
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v2, resolved type: java.lang.Object[]} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public luaj.ap a_(luaj.ap r11) {
            /*
                r10 = this;
                android.ext.ar r0 = android.ext.ar.d
                java.lang.String r1 = "com.android.dx.command.Main"
                java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ Exception -> 0x0092 }
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0092 }
                r2.<init>()     // Catch:{ Exception -> 0x0092 }
                java.lang.String r3 = luaj.lib.ModLib.dexpath     // Catch:{ Exception -> 0x0092 }
                java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ Exception -> 0x0092 }
                long r3 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x0092 }
                java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ Exception -> 0x0092 }
                java.lang.String r3 = ".dex"
                java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ Exception -> 0x0092 }
                java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x0092 }
                r3 = 3
                java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ Exception -> 0x0092 }
                java.lang.String r4 = "--dex"
                r5 = 0
                r3[r5] = r4     // Catch:{ Exception -> 0x0092 }
                java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0092 }
                r4.<init>()     // Catch:{ Exception -> 0x0092 }
                java.lang.String r6 = "--output="
                java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ Exception -> 0x0092 }
                java.lang.StringBuilder r4 = r4.append(r2)     // Catch:{ Exception -> 0x0092 }
                java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x0092 }
                r6 = 1
                r3[r6] = r4     // Catch:{ Exception -> 0x0092 }
                r4 = 2
                java.lang.String r7 = luaj.lib.ModLib.classpath     // Catch:{ Exception -> 0x0092 }
                java.lang.String r7 = r11.c(r6, r7)     // Catch:{ Exception -> 0x0092 }
                r3[r4] = r7     // Catch:{ Exception -> 0x0092 }
                java.lang.String r4 = "main"
                java.lang.Class[] r7 = new java.lang.Class[r6]     // Catch:{ Exception -> 0x0092 }
                java.lang.Class<java.lang.String[]> r8 = java.lang.String[].class
                r7[r5] = r8     // Catch:{ Exception -> 0x0092 }
                java.lang.reflect.Method r4 = r1.getDeclaredMethod(r4, r7)     // Catch:{ Exception -> 0x0092 }
                java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch:{ Exception -> 0x0092 }
                r6[r5] = r3     // Catch:{ Exception -> 0x0092 }
                r4.invoke(r1, r6)     // Catch:{ Exception -> 0x0092 }
                java.io.File r5 = new java.io.File     // Catch:{ Exception -> 0x0092 }
                r5.<init>(r2)     // Catch:{ Exception -> 0x0092 }
                boolean r6 = r5.exists()     // Catch:{ Exception -> 0x0092 }
                if (r6 == 0) goto L_0x008e
                dalvik.system.DexClassLoader r6 = new dalvik.system.DexClassLoader     // Catch:{ Exception -> 0x0092 }
                java.io.File r7 = r0.getCacheDir()     // Catch:{ Exception -> 0x0092 }
                java.lang.String r7 = r7.getAbsolutePath()     // Catch:{ Exception -> 0x0092 }
                r8 = 0
                java.lang.ClassLoader r9 = r0.getClassLoader()     // Catch:{ Exception -> 0x0092 }
                r6.<init>(r2, r7, r8, r9)     // Catch:{ Exception -> 0x0092 }
                java.util.ArrayList<dalvik.system.DexClassLoader> r7 = luaj.lib.ModLib.dexes     // Catch:{ Exception -> 0x0092 }
                boolean r7 = r7.contains(r6)     // Catch:{ Exception -> 0x0092 }
                if (r7 != 0) goto L_0x0089
                java.util.ArrayList<dalvik.system.DexClassLoader> r7 = luaj.lib.ModLib.dexes     // Catch:{ Exception -> 0x0092 }
                r7.add(r6)     // Catch:{ Exception -> 0x0092 }
            L_0x0089:
                luaj.LuaString r7 = luaj.LuaValue.m(r2)     // Catch:{ Exception -> 0x0092 }
                return r7
            L_0x008e:
                luaj.LuaValue r1 = luaj.LuaValue.u
                return r1
            L_0x0092:
                r1 = move-exception
                luaj.o r2 = new luaj.o
                r2.<init>(r1)
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: luaj.lib.ModLib.classcompile.a_(luaj.ap):luaj.ap");
        }
    }

    static class compile extends VarArgFunction {
        compile() {
        }

        public ap a_(ap apVar) {
            String findFile = ModLib.findFile(apVar.r(1), ModLib.dex);
            ar arVar = ar.d;
            DexClassLoader dexClassLoader = new DexClassLoader(findFile, arVar.getCacheDir().getAbsolutePath(), (String) null, arVar.getClassLoader());
            if (!ModLib.dexes.contains(dexClassLoader)) {
                ModLib.dexes.add(dexClassLoader);
            } else {
                ModLib.dexes.remove(dexClassLoader);
                ModLib.dexes.add(dexClassLoader);
            }
            return LuaValue.u;
        }
    }

    static class copy extends VarArgFunction {
        copy() {
        }

        public ap a_(ap apVar) {
            try {
                Tools.copy(apVar.r(1), apVar.r(2));
                return LuaValue.v;
            } catch (IOException e) {
                e.printStackTrace();
                return LuaValue.m(e.toString());
            }
        }
    }

    static class loadBitmap extends VarArgFunction {
        loadBitmap() {
        }

        public ap a_(ap apVar) {
            Bitmap bitmap = null;
            if (apVar.j_() == 1) {
                if (apVar.isuserdata(1)) {
                    Object checkuserdata = apVar.checkuserdata(1);
                    if (checkuserdata instanceof Drawable) {
                        bitmap = ImageUtils.DrawableToBitmap((Drawable) checkuserdata);
                    } else if (checkuserdata instanceof View) {
                        bitmap = ImageUtils.getViewImage((View) checkuserdata);
                    }
                } else if (apVar.i(1)) {
                    String r = apVar.r(1);
                    String findFile = ModLib.findFile(r, ModLib.png);
                    if (findFile != null) {
                        bitmap = ImageUtils.getFileImage(findFile);
                    }
                    if (bitmap == null && (bitmap = ImageUtils.getUrlImage(r, new LuaTable())) == null) {
                        bitmap = ImageUtils.getTextImage(apVar.s(1));
                    }
                }
            } else if (apVar.j_() == 2) {
                if (apVar.i(1) && apVar.i(2)) {
                    bitmap = ImageUtils.getTextImage(apVar.r(1), apVar.r(2));
                } else if (apVar.i(1) && apVar.istable(2)) {
                    bitmap = ImageUtils.getUrlImage(apVar.r(1), apVar.t(2));
                }
            }
            return bitmap != null ? CoerceJavaToLua.coerce(bitmap) : LuaValue.u;
        }
    }

    static class loadDrawable extends VarArgFunction {
        loadDrawable() {
        }

        public ap a_(ap apVar) {
            Drawable drawable = null;
            Context e = Tools.e();
            try {
                String r = apVar.r(1);
                String findFile = ModLib.findFile(r, ModLib.png);
                if (findFile != null) {
                    drawable = Loader.loadXmlDrawable(e, findFile);
                }
                if (drawable == null) {
                    drawable = Loader.loadXmlDrawable(e, r);
                }
                return drawable != null ? CoerceJavaToLua.coerce(drawable) : LuaValue.u;
            } catch (Exception e2) {
                throw new o(e2);
            }
        }
    }

    static class loadXml extends VarArgFunction {
        loadXml() {
        }

        public ap a_(ap apVar) {
            try {
                HashMap hashMap = new HashMap();
                View loadXmlView = Loader.loadXmlView(Tools.e(), apVar.r(1), hashMap);
                LuaTable a = apVar.a(2, ModLib._G);
                if (!hashMap.isEmpty()) {
                    for (Map.Entry entry : hashMap.entrySet()) {
                        a.a((String) entry.getKey(), CoerceJavaToLua.coerce(entry.getValue()));
                    }
                }
                return b(CoerceJavaToLua.coerce(loadXmlView), a);
            } catch (Exception e) {
                return b(u, LuaString.c(String.valueOf(new PrintStack(e))));
            }
        }
    }

    static class randomString extends VarArgFunction {
        randomString() {
        }

        public ap a_(ap apVar) {
            return LuaValue.m(ModLib.generateRandomString(apVar.d(1, 10)));
        }
    }

    static class unLockLog extends VarArgFunction {
        unLockLog() {
        }

        public ap a_(ap apVar) {
            try {
                return LockFunction.unLockLog(apVar.n(1), apVar.d(2, LuaValue.u));
            } catch (Exception e) {
                return LuaValue.m(e.toString());
            }
        }
    }

    static class unLockMain extends VarArgFunction {
        unLockMain() {
        }

        public ap a_(ap apVar) {
            try {
                return LockFunction.unLockMain(apVar.n(1), apVar.d(2, LuaValue.u));
            } catch (Exception e) {
                return LuaValue.m(e.toString());
            }
        }
    }

    static class unLockUi extends VarArgFunction {
        unLockUi() {
        }

        public ap a_(ap apVar) {
            try {
                return LockFunction.unLockUi(apVar.a(1, ModLib.func), apVar.d(2, LuaValue.u));
            } catch (Exception e) {
                return LuaValue.m(e.toString());
            }
        }
    }

    static class unLockWrite extends VarArgFunction {
        unLockWrite() {
        }

        public ap a_(ap apVar) {
            try {
                return LockFunction.unLockWrite(apVar.a(1, ModLib.func), apVar.d(2, LuaValue.u));
            } catch (Exception e) {
                return LuaValue.m(e.toString());
            }
        }
    }

    public static void At(String str, LuaValue luaValue) {
        String[] split = str.split("[.]");
        if (split.length == 0) {
            return;
        }
        if (split.length == 1) {
            _G.a(split[0], luaValue);
            return;
        }
        LuaValue[] luaValueArr = new LuaValue[split.length];
        luaValueArr[0] = _G;
        for (int i = 1; i <= split.length - 1; i++) {
            LuaTable j = luaValueArr[i - 1].j(split[i - 1]);
            luaValueArr[i] = (j == null || j.F() || j.isnoneornil(1)) ? new LuaTable() : j;
            luaValueArr[i - 1].a(split[i - 1], luaValueArr[i]);
        }
        for (int i2 = 0; i2 < split.length - 1; i2++) {
            luaValueArr[i2].a(split[i2], luaValueArr[i2 + 1]);
        }
        luaValueArr[luaValueArr.length - 1].a(split[split.length - 1], luaValue);
        _G.a(split[split.length - 1], luaValue);
    }

    public static LuaValue ImportExecute(String str) {
        if (!dexes.isEmpty()) {
            int size = dexes.size() - 1;
            while (size >= 0) {
                try {
                    Class loadClass = dexes.get(size).loadClass(str);
                    imported.put(str, loadClass);
                    LuaValue coerce = CoerceJavaToLua.coerce(loadClass);
                    At(loadClass.getName(), coerce);
                    return coerce;
                } catch (Exception e) {
                    e.printStackTrace();
                    size--;
                }
            }
        }
        if (imported.isEmpty() || !imported.containsKey(str)) {
            try {
                Class<?> cls = Class.forName(str);
                imported.put(str, cls);
                LuaValue coerce2 = CoerceJavaToLua.coerce(cls);
                At(cls.getName(), coerce2);
                return coerce2;
            } catch (ClassNotFoundException e2) {
                e2.printStackTrace();
                return LuaValue.u;
            }
        } else {
            Class cls2 = imported.get(str);
            if (cls2 != null) {
                LuaValue coerce3 = CoerceJavaToLua.coerce(cls2);
                At(cls2.getName(), coerce3);
                return coerce3;
            }
            try {
                Class<?> cls3 = Class.forName(str);
                imported.put(str, cls3);
                LuaValue coerce4 = CoerceJavaToLua.coerce(cls3);
                At(cls3.getName(), coerce4);
                return coerce4;
            } catch (ClassNotFoundException e3) {
                e3.printStackTrace();
                return LuaValue.u;
            }
        }
    }

    public static String findFile(String str, String[] strArr) {
        try {
            Field declaredField = Globals.class.getDeclaredField("r");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(_G);
            if (obj != null && (obj instanceof String)) {
                searchpath = (((String) obj) + "/?.lua") + ";" + searchpath;
            }
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        String replace = str.trim().replace("//", "/").replace(";;", ";").replace("?.lua?.lua", "?.lua");
        if (!replace.startsWith("/") && replace.contains("/")) {
            replace = "/" + replace;
        }
        File file = new File(replace);
        if (file.exists() && file.isFile()) {
            return replace;
        }
        String[] strArr2 = (String[]) new HashSet(Arrays.asList(searchpath.split(";"))).toArray(new String[0]);
        StringBuilder sb = new StringBuilder();
        for (String str2 : strArr) {
            for (String str3 : strArr2) {
                sb.append(str3);
                sb.append(";");
                String replace2 = str3.replace("?.lua", replace);
                File file2 = new File(replace2);
                if (file2.exists() && file2.isFile()) {
                    return replace2;
                }
                String replace3 = str3.replace("?.lua", replace + "." + str2);
                File file3 = new File(replace3);
                if (file3.exists() && file3.isFile()) {
                    return replace3;
                }
            }
        }
        searchpath = sb.toString();
        return null;
    }

    public static String generateRandomString(int i) {
        StringBuilder sb = new StringBuilder(i);
        Random random = new Random();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".charAt(random.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".length())));
        }
        return sb.toString();
    }

    static void listFile(ArrayList<String> arrayList, File file, String str) {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File listFile : listFiles) {
                    listFile(arrayList, listFile, str);
                }
                return;
            }
            return;
        }
        String absolutePath = file.getAbsolutePath();
        if (absolutePath.endsWith(str)) {
            arrayList.add(absolutePath);
        }
    }

    public LuaValue a(LuaValue luaValue, LuaValue luaValue2) {
        _G = luaValue2.c();
        imported = new HashMap<>();
        dexes = new ArrayList<>();
        LuaTable luaTable = new LuaTable();
        ids = luaTable;
        luaTable.a("id", 2135949312);
        Context e = Tools.e();
        _G.a("context", CoerceJavaToLua.coerce(e));
        _G.a("activity", CoerceJavaToLua.coerce(ar.d));
        File l = Tools.l();
        String absolutePath = l.getAbsolutePath();
        String str = null;
        String str2 = null;
        File parentFile = l.getParentFile();
        if (parentFile != null && parentFile.exists()) {
            str = parentFile.getAbsolutePath();
            parentFile = parentFile.getParentFile();
            if (parentFile != null && parentFile.exists()) {
                str2 = parentFile.getAbsolutePath();
            }
        }
        String absolutePath2 = Environment.getExternalStorageDirectory().getAbsolutePath();
        String absolutePath3 = e.getExternalFilesDir((String) null).getAbsolutePath();
        String absolutePath4 = e.getExternalCacheDir().getAbsolutePath();
        String str3 = "";
        if (str2 != null) {
            str3 = str3 + str2 + "/?.lua;" + str2 + "/files/?.lua;" + str2 + "cache/?.lua;";
        }
        String str4 = str3 + absolutePath + "/?.lua;";
        if (str != null) {
            str4 = str4 + str + "/?.lua;";
        }
        String str5 = str4 + absolutePath2 + "/MT2/?.lua;" + absolutePath2 + "/MT2/apks/?.lua;" + absolutePath2 + "/Notes/?.lua;" + absolutePath2 + "/Download/?.lua;" + absolutePath2 + "/Download/Browser/?.lua;" + absolutePath3 + "/?.lua;" + absolutePath3.replace("files", "") + "?.lua;" + absolutePath4 + "/?.lua;" + absolutePath4.replace("cache", "") + "?.lua;" + absolutePath2 + "/Android/data/com.tencent.mobileqq/Tencent/QQfile_recv/?.lua;" + absolutePath2 + "/tencent/QQfile_recv/?.lua;";
        searchpath = str5;
        _G.j("package").c("path", "?.lua;" + str5);
        _G.j("luajava").a("ids", ids);
        _G.a("instanceof", new InstanceOf());
        _G.a("loadbitmap", new loadBitmap());
        _G.a("loaddrawable", new loadDrawable());
        _G.a("compile", new compile());
        _G.a("import", new Import());
        _G.a("gc", new GC());
        _G.a("copyFile", new copy());
        LuaTable luaTable2 = new LuaTable();
        luaTable2.a("Log", new LockLog());
        luaTable2.a("Main", new LockMain());
        luaTable2.a("Ui", new LockUi());
        luaTable2.a("Write", new LockWrite());
        luaTable2.a("unLog", new unLockLog());
        luaTable2.a("unMain", new unLockMain());
        luaTable2.a("unUi", new unLockUi());
        luaTable2.a("unWrite", new unLockWrite());
        _G.a("Lock", luaTable2);
        Globals globals = _G;
        globals.j("load").C().a(new LuaValue[]{LuaValue.m("local str_mt = debug.getmetatable(\"string\")\nif str_mt then\n    debug.setmetatable(\"string\", str_mt)\nend\n\nlocal ids = luajava.ids\nlocal _G=_G\nlocal insert = table.insert\nlocal new = luajava.new\nlocal bindClass = luajava.bindClass\nlocal ltrs={}\n\nlocal File = bindClass(\"java.io.File\")\nlocal ViewGroup=bindClass(\"android.view.ViewGroup\")\nlocal String=bindClass(\"java.lang.String\")\nlocal Gravity=bindClass(\"android.view.Gravity\")\nlocal OnClickListener=bindClass(\"android.view.View$OnClickListener\")\nlocal TypedValue=luajava.bindClass(\"android.util.TypedValue\")\nlocal BitmapDrawable=luajava.bindClass(\"android.graphics.drawable.BitmapDrawable\")\n\nimport \"luaj.lib.ImageUtils\"\nimport \"com.util.LuaDrawable\"\nimport \"com.util.LuaBitmapDrawable\"\nimport \"android.widget.ArrayListAdapter\"\nimport \"android.widget.ArrayPageAdapter\"\nimport \"android.widget.AdapterView\"\nimport \"android.widget.LinearLayout\"\nimport \"android.widget.LinearLayout$LayoutParams\"\n\nlocal ScaleType=bindClass(\"android.widget.ImageView$ScaleType\")\nlocal TruncateAt=bindClass(\"android.text.TextUtils$TruncateAt\")\nlocal scaleTypes=ScaleType.values()\nlocal android_R=bindClass(\"android.R\")\nandroid={R=android_R}\n\nlocal Context=bindClass \"android.content.Context\"\nlocal DisplayMetrics=bindClass \"android.util.DisplayMetrics\"\n\nlocal wm =context.getSystemService(Context.WINDOW_SERVICE);\nlocal outMetrics = DisplayMetrics();\nwm.getDefaultDisplay().getMetrics(outMetrics);\nlocal W = outMetrics.widthPixels;\nlocal H = outMetrics.heightPixels;\n\nlocal function alyloader(path)\n    local alypath=string.gsub(package.path,\"%.lua;\",\".aly;\")\n    local path,msg=package.searchpath(path,alypath)\n    if msg then\n        return msg\n    end\n    local f=io.open(path)\n    local s=f:read(\"*a\")\n    f:close()\n    if string.sub(s,1,4)==\"\\27Lua\" then\n        return assert(loadfile(path)),path\n      else\n        local f,st=loadstring(\"return \"..s, string.match(path,\"[^/]+/[^/]+$\"),\"bt\")\n        if st then\n            error(string.gsub(st,\"%b[]\",path,1),2)\n        end\n        return f,st\n    end\nend\ntable.insert(package.searchers,alyloader)\n\nlocal dm=context.getResources().getDisplayMetrics()\nlocal id=0x7f500000\nlocal toint={\n    --android:drawingCacheQuality\n    auto=0,\n    low=1,\n    high=2,\n\n    --android:importantForAccessibility\n    auto=0,\n    yes=1,\n    no=2,\n\n    --android:layerType\n    none=0,\n    software=1,\n    hardware=2,\n\n    --android:layoutDirection\n    ltr=0,\n    rtl=1,\n    inherit=2,\n    locale=3,\n\n    --android:scrollbarStyle\n    insideOverlay=0x0,\n    insideInset=0x01000000,\n    outsideOverlay=0x02000000,\n    outsideInset=0x03000000,\n\n    --android:visibility\n    visible=0,\n    invisible=4,\n    gone=8,\n\n    wrap_content=-2,\n    fill_parent=-1,\n    match_parent=-1,\n    wrap=-2,\n    fill=-1,\n    match=-1,\n\n    --android:autoLink\n    none=0x00,\n    web=0x01,\n    email=0x02,\n    phon=0x04,\n    map=0x08,\n    all=0x0f,\n\n    --android:orientation\n    vertical=1,\n    horizontal= 0,\n\n    --android:gravity\n    axis_clip = 8,\n    axis_pull_after = 4,\n    axis_pull_before = 2,\n    axis_specified = 1,\n    axis_x_shift = 0,\n    axis_y_shift = 4,\n    bottom = 80,\n    center = 17,\n    center_horizontal = 1,\n    center_vertical = 16,\n    clip_horizontal = 8,\n    clip_vertical = 128,\n    display_clip_horizontal = 0x01000000,\n    display_clip_vertical = 0x10000000,\n    --fill = 119,\n    fill_horizontal = 7,\n    fill_vertical = 112,\n    horizontal_gravity_mask = 7,\n    left = 3,\n    no_gravity = 0,\n    relative_horizontal_gravity_mask = 8388615,\n    relative_layout_direction = 8388608,\n    right = 5,\n    start = 8388611,\n    top = 48,\n    vertical_gravity_mask = 112,\n    [\"end\"] = 8388613,\n\n    --android:textAlignment\n    inherit=0,\n    gravity=1,\n    textStart=2,\n    textEnd=3,\n    textCenter=4,\n    viewStart=5,\n    viewEnd=6,\n\n    --android:inputType\n    none=0x00000000,\n    text=0x00000001,\n    textCapCharacters=0x00001001,\n    textCapWords=0x00002001,\n    textCapSentences=0x00004001,\n    textAutoCorrect=0x00008001,\n    textAutoComplete=0x00010001,\n    textMultiLine=0x00020001,\n    textImeMultiLine=0x00040001,\n    textNoSuggestions=0x00080001,\n    textUri=0x00000011,\n    textEmailAddress=0x00000021,\n    textEmailSubject=0x00000031,\n    textShortMessage=0x00000041,\n    textLongMessage=0x00000051,\n    textPersonName=0x00000061,\n    textPostalAddress=0x00000071,\n    textPassword=0x00000081,\n    textVisiblePassword=0x00000091,\n    textWebEditText=0x000000a1,\n    textFilter=0x000000b1,\n    textPhonetic=0x000000c1,\n    textWebEmailAddress=0x000000d1,\n    textWebPassword=0x000000e1,\n    number=0x00000002,\n    numberSigned=0x00001002,\n    numberDecimal=0x00002002,\n    numberPassword=0x00000012,\n    phone=0x00000003,\n    datetime=0x00000004,\n    date=0x00000014,\n    time=0x00000024,\n\n    --android:imeOptions\n    normal=0x00000000,\n    actionUnspecified=0x00000000,\n    actionNone=0x00000001,\n    actionGo=0x00000002,\n    actionSearch=0x00000003,\n    actionSend=0x00000004,\n    actionNext=0x00000005,\n    actionDone=0x00000006,\n    actionPrevious=0x00000007,\n    flagNoFullscreen=0x2000000,\n    flagNavigatePrevious=0x4000000,\n    flagNavigateNext=0x8000000,\n    flagNoExtractUi=0x10000000,\n    flagNoAccessoryAction=0x20000000,\n    flagNoEnterAction=0x40000000,\n    flagForceAscii=0x80000000,\n\n    --android:textStyle\n    normal=0,\n    bold=1,\n    italic=2,\n    bold_italic=3,\n}\n\nlocal scaleType={\n    --android:scaleType\n    matrix=0,\n    fitXY=1,\n    fitStart=2,\n    fitCenter=3,\n    fitEnd=4,\n    center=5,\n    centerCrop=6,\n    centerInside=7,\n}\n\nlocal rules={\n    layout_above=2,\n    layout_alignBaseline=4,\n    layout_alignBottom=8,\n    layout_alignEnd=19,\n    layout_alignLeft=5,\n    layout_alignParentBottom=12,\n    layout_alignParentEnd=21,\n    layout_alignParentLeft=9,\n    layout_alignParentRight=11,\n    layout_alignParentStart=20,\n    layout_alignParentTop=10,\n    layout_alignRight=7,\n    layout_alignStart=18,\n    layout_alignTop=6,\n    layout_alignWithParentIfMissing=0,\n    layout_below=3,\n    layout_centerHorizontal=14,\n    layout_centerInParent=13,\n    layout_centerVertical=15,\n    layout_toEndOf=17,\n    layout_toLeftOf=0,\n    layout_toRightOf=1,\n    layout_toStartOf=16\n}\n\nlocal types={\n    px=0,\n    dp=1,\n    sp=2,\n    pt=3,\n    [\"in\"]=4,\n    mm=5\n}\n\nlocal function checkType(v)\n    local n,ty=string.match(v,\"^(%-?[%.%d]+)(%a%a)$\")\n    return tonumber(n),types[ty]\nend\n\nlocal function checkPercent(v)\n    local n,ty=string.match(v,\"^(%-?[%.%d]+)%%([wh])$\")\n    if ty==nil then\n        return nil\n      elseif ty==\"w\" then\n        return tonumber(n)*W/100\n      elseif ty==\"h\" then\n        return tonumber(n)*H/100\n    end\nend\n\nlocal function split(s,t)\n    local idx=1\n    local l=#s\n    return function()\n        local i=s:find(t,idx)\n        if idx>=l then\n            return nil\n        end\n        if i==nil then\n            i=l+1\n        end\n        local sub=s:sub(idx,i-1)\n        idx=i+1\n        return sub\n    end\nend\n\nlocal function checkint(s)\n    local ret=0\n    for n in split(s,\"|\") do\n        if toint[n] then\n            ret=ret | toint[n]\n          else\n            return nil\n        end\n    end\n    return ret\nend\n\nlocal function checkNumber(var)\n    if type(var) == \"string\" then\n        if var==\"true\" then\n            return true\n          elseif var==\"false\" then\n            return false\n        end\n\n        if toint[var] then\n            return toint[var]\n        end\n\n        local p=checkPercent(var)\n        if p then\n            return p\n        end\n\n        local i=checkint(var)\n        if i then\n            return i\n        end\n\n        local h=string.match(var,\"^#(%x+)$\")\n        if h then\n            local c=tonumber(h,16)\n            if c then\n                if #h<=6 then\n                    return c-0x1000000\n                  elseif #h<=8 then\n                    if c>0x7fffffff then\n                        return c-0x100000000\n                      else\n                        return c\n                    end\n                end\n            end\n        end\n\n        local n,ty=checkType(var)\n        if ty then\n            return TypedValue.applyDimension(ty,n,dm)\n        end\n    end\n    -- return var\nend\n\nlocal function checkValue(var)\n    return tonumber(var) or checkNumber(var) or var\nend\n\nlocal function checkValues(...)\n    local vars={...}\n    for n=1,#vars do\n        vars[n]=checkValue(vars[n])\n    end\n    return table.unpack(vars)\nend\n\nlocal function getattr(s)\n    return android_R.attr[s]\nend\n\nlocal function checkattr(s)\n    local e,s=pcall(getattr,s)\n    if e then\n        return s\n    end\n    return nil\nend\n\nlocal function getIdentifier(name)\n    return context.getResources().getIdentifier(name,null,null)\nend\n\nimport \"luaj.lib.ModLib\"\nlocal function localpath(path)\n    return ModLib.findFile(path,ModLib.png)\nend\n\nlocal function dump2 (t)\n    local _t={}\n    table.insert(_t,tostring(t))\n    table.insert(_t,\"\\t{\")\n    for k,v in pairs(t) do\n        if type(v)==\"table\" then\n            table.insert(_t,\"\\t\\t\"..tostring(k)..\"={\"..tostring(v[1])..\" ...}\")\n          else\n            table.insert(_t,\"\\t\\t\"..tostring(k)..\"=\"..tostring(v))\n        end\n    end\n    table.insert(_t,\"\\t}\")\n    t=table.concat(_t,\"\\n\")\n    return t\nend\n\nlocal ver = luajava.bindClass(\"android.os.Build\").VERSION.SDK_INT;\nlocal function setBackground(view,bg)\n    if ver<16 then\n        view.setBackgroundDrawable(bg)\n      else\n        view.setBackground(bg)\n    end\nend\n\nlocal function setTextStyle(view, v)\n    local face = checkValue(v) or -1\n    local currentTypeface = view.getTypeface()\n    local newTypeface = Typeface.create(currentTypeface, face)\n    view.setTypeface(newTypeface)\nend\n\nimport \"luaj.lib.ModLib\"\nimport \"java.lang.String\"\nimport \"android.graphics.Typeface\"\nlocal ttf = String({\"ttf\", \"TTF\", \"Ttf\", \"otf\", \"Otf\", \"OTF\"})\nlocal function setTextFont(view, v)\n    if type(v) == \"string\" then\n        if v:sub(1,1)==\"?\" then\n            view.setTypeface(Typeface.createFromAsset(context.getAssets(),v:sub(2,-1)))\n          else\n            local p = ModLib.findFile(v,ttf)\n            if not p then\n                v = \"/system/fonts/\"..v\n                p = ModLib.findFile(v,ttf)\n                if not p then\n                    v = v..\".ttf\"\n                    p = ModLib.findFile(v,ttf)\n                end\n            end\n            if p then\n                view.setTypeface(Typeface.createFromFile(p))\n            end\n        end\n    end\nend\n\nlocal function setTextAppearance(view, v)\n    local resid = getIdentifier(v:sub(2,-1))\n    view.setTextAppearance(context, resid)\nend\n\nlocal function setattribute(root,view,params,k,v,ids, _t)\n    if k==\"layout_x\" then\n        params.x=checkValue(v)\n      elseif k==\"layout_y\" then\n        params.y=checkValue(v)\n      elseif k==\"layout_weight\" then\n        params.weight=checkValue(v)\n      elseif k==\"layout_gravity\" then\n        params.gravity=checkValue(v)\n      elseif k==\"layout_marginStart\" then\n        params.setMarginStart(checkValue(v))\n      elseif k==\"layout_marginEnd\" then\n        params.setMarginEnd(checkValue(v))\n      elseif rules[k] and (v==true or v==\"true\") then\n        params.addRule(rules[k])\n      elseif rules[k] then\n        params.addRule(rules[k],ids[v])\n      elseif k==\"items\" then --创建列表项目\n        if type(v)==\"table\" then\n            if view.adapter then\n                view.adapter.addAll(v)\n              else\n                local adapter=ArrayListAdapter(context,android_R.layout.simple_list_item_1, String(v))\n                view.setAdapter(adapter)\n            end\n          elseif type(v)==\"function\" then\n            if view.adapter then\n                view.adapter.addAll(v())\n              else\n                local adapter=ArrayListAdapter(context,android_R.layout.simple_list_item_1, String(v()))\n                view.setAdapter(adapter)\n            end\n          elseif type(v)==\"string\" then\n            local v=rawget(root,v) or rawget(_G,v)\n            if view.adapter then\n                view.adapter.addAll(v())\n              else\n                local adapter=ArrayListAdapter(context,android_R.layout.simple_list_item_1, String(v()))\n                view.setAdapter(adapter)\n            end\n        end\n      elseif k==\"pages\" and type(v)==\"table\" then --创建页项目\n        local ps={}\n        for n,o in ipairs(v) do\n            local tp=type(o)\n            if tp==\"string\" or tp==\"table\" then\n                table.insert(ps,loadlayout(o,root))\n              else\n                table.insert(ps,o)\n            end\n        end\n        local adapter=ArrayPageAdapter(View(ps))\n        view.setAdapter(adapter)\n      elseif k==\"textSize\" then\n        if tonumber(v) then\n            view.setTextSize(tonumber(v))\n          elseif type(v)==\"string\" then\n            local n,ty=checkType(v)\n            if ty then\n                view.setTextSize(ty,n)\n              else\n                view.setTextSize(v)\n            end\n          else\n            view.setTextSize(v)\n        end\n      elseif k==\"textAppearance\" then\n        setTextAppearance(view, v)\n        if _t then --修正字体设置错误\n            if _t.font then\n                setTextFont(view, _t.font)\n            end\n            if _t.textStyle then\n                setTextStyle(view, _t.textStyle)\n            end\n        end\n      elseif k==\"textStyle\" then\n        setTextStyle(view, v)\n      elseif k==\"font\" then\n        setTextFont(view, v)\n      elseif k==\"ellipsize\" then\n        view.setEllipsize(TruncateAt[string.upper(v)])\n      elseif k==\"url\" then\n        view.loadUrl(url)\n      elseif k==\"src\" then\n        if v:find(\"^%?\") then\n            view.setImageResource(getIdentifier(v:sub(2,-1)))\n          elseif (not v:find(\"^/\")) then\n            if v:sub(1,5)==\"<?xml\" then\n                view.setImageDrawable(loaddrawable(v))\n              elseif v:find(\".xml\") then\n                view.setImageDrawable(loaddrawable(v:sub(1,-5)))\n              else\n                view.setImageBitmap(loadbitmap(v))\n            end\n          elseif v:find(\".xml\") then\n            view.setImageDrawable(loaddrawable(v:sub(1,-5)))\n          else\n            view.setImageBitmap(loadbitmap(v))\n        end\n      elseif k==\"scaleType\" then\n        view.setScaleType(scaleTypes[scaleType[v]])\n      elseif k==\"background\" then\n        if type(v)==\"string\" then\n            if v:find(\"^%?\") then\n                view.setBackgroundResource(getIdentifier(v:sub(2,-1)))\n              elseif v:find(\"^#\") then\n                view.setBackgroundColor(checkNumber(v))\n              elseif rawget(root,v) or rawget(_G,v) then\n                v=rawget(root,v) or rawget(_G,v)\n                if type(v)==\"function\" then\n                    setBackground(view,LuaDrawable(context,v))\n                  elseif type(v)==\"userdata\" then\n                    setBackground(view,v)\n                end\n              else\n                if (not v:find(\"^/\")) then\n                    if v:sub(1,5)==\"<?xml\" then\n                        setBackground(view,loaddrawable(v))\n                      elseif v:find(\".xml\") then\n                        setBackground(view,loaddrawable(v:sub(1,-5)))\n                      else\n                        setBackground(view,LuaBitmapDrawable(context,localpath(v)))\n                    end\n                  elseif v:find(\"%.9%.png\") then\n                    setBackground(view,NineBitmapDrawable(loadbitmap(v)))\n                  elseif v:find(\".xml\") then\n                    setBackground(view,loaddrawable(v:sub(1,-5)))\n                  else\n                    setBackground(view,LuaBitmapDrawable(context,v))\n                end\n            end\n          elseif type(v)==\"userdata\" then\n            setBackground(view,v)\n          elseif type(v)==\"number\" then\n            setBackground(view,v)\n        end\n      elseif k==\"onClick\" then\n        local listener\n        if type(v)==\"function\" then\n            listener=OnClickListener{onClick=v}\n          elseif type(v)==\"userdata\" then\n            listener=v\n          elseif type(v)==\"string\" then\n            if ltrs[v] then\n                listener=ltrs[v]\n              else\n                local l=rawget(root,v) or rawget(_G,v)\n                if type(l)==\"function\" then\n                    listener=OnClickListener{onClick=l}\n                  elseif type(l)==\"userdata\" then\n                    listener=l\n                  else\n                    listener=OnClickListener{onClick=function(a)(root[v] or _G[v])(a)end}\n                end\n                ltrs[v]=listener\n            end\n        end\n        view.setOnClickListener(listener)\n      elseif k==\"password\" and (v==\"true\" or v==true) then\n        view.setInputType(0x81)\n      elseif type(k)==\"string\" and not(k:find(\"layout_\")) and not(k:find(\"padding\")) and k~=\"style\" then --设置属性\n        k=string.gsub(k,\"^(%w)\",function(s)return string.upper(s)end)\n        if k==\"Text\" or k==\"Title\" or k==\"Subtitle\" or k==\"Hint\" then\n            view[\"set\"..k](v)\n          elseif ((not k:find(\"^On\")) and (not k:find(\"^Tag\")) and (type(v)==\"table\")) then\n            view[\"set\"..k](checkValues(table.unpack(v)))\n          else\n            view[\"set\"..k](checkValue(v))\n        end\n    end\nend\n\nlocal function setstyle(c,t,root,view,params,ids)\n    local mt=getmetatable(t)\n    if not mt then return end\n    if not mt.__index then\n        return end\n    local m=mt.__index\n    if c[m] then\n        return\n    end\n    c[m]=true\n    for k,v in pairs(m) do\n        if not rawget(c,k) then\n            pcall(setattribute,root,view,params,k,v,ids,t)\n        end\n        c[k]=true\n    end\n    setstyle(c,m,root,view,params,ids)\nend\n\nfunction loadlayout(t,root,group)\n    if type(t)==\"string\" then\n        t=require(t)\n      elseif type(t)~=\"table\" then\n        error(string.format(\"loadlayout error: Fist value Must be a table, checked import layout.\",2))\n    end\n    root=root or _G\n    local view,style\n\n    if t.style then\n        if t.style:find(\"^%?\") then\n            style=getIdentifier(t.style:sub(2,-1))\n          else\n            local st,sty=pcall(require,t.style)\n            if st then\n                setmetatable(t,{__index=sty})\n              else\n                style=checkattr(t.style)\n            end\n        end\n    end\n    if not t[1] then\n        error(string.format(\"loadlayout error: Fist value Must be a Class, checked import package.\\n\\tat %s\",dump2(t)),2)\n    end\n\n    if style then\n        view = t[1](context,nil,style)\n      else\n        view = t[1](context) --创建view\n    end\n\n    local child = tostring(t[1]):match(\"class([!-z_ ]+)\"):gsub(\" \",\"\")\n    local _,VLayout = pcall(luajava.bindClass,child..\"$LayoutParams\")\n    local params = ViewGroup.LayoutParams(checkValue(t.layout_width) or -2,checkValue(t.layout_height) or -2) --设置layout属性\n    if _ then\n        _,params=pcall(VLayout,checkValue(t.layout_width) or -2,checkValue(t.layout_height) or -2) --设置layout属性\n        if not _ then\n            if group then\n                params=group(checkValue(t.layout_width) or -2,checkValue(t.layout_height) or -2) --设置layout属性\n              else\n                params=ViewGroup.LayoutParams(checkValue(t.layout_width) or -2,checkValue(t.layout_height) or -2) --设置layout属性\n            end\n        end\n      else\n        if group then\n            params=group(checkValue(t.layout_width) or -2,checkValue(t.layout_height) or -2) --设置layout属性\n          else\n            params=ViewGroup.LayoutParams(checkValue(t.layout_width) or -2,checkValue(t.layout_height) or -2) --设置layout属性\n        end\n    end\n\n    if t.layout_margin or t.layout_marginStart or t.layout_marginEnd or t.layout_marginLeft or t.layout_marginTop or t.layout_marginRight or t.layout_marginBottom then\n        local mar = params\n        import \"android.view.ViewGroup$MarginLayoutParams\"\n        if not instanceof(mar,android.view.ViewGroup$MarginLayoutParams) then\n            params = MarginLayoutParams(mar)\n            pcall(params.setMargins,checkValues( t.layout_marginLeft or t.layout_margin or 0,t.layout_marginTop or t.layout_margin or 0,t.layout_marginRight or t.layout_margin or 0,t.layout_marginBottom or t.layout_margin or 0))\n          else\n            pcall(params.setMargins,checkValues( t.layout_marginLeft or t.layout_margin or 0,t.layout_marginTop or t.layout_margin or 0,t.layout_marginRight or t.layout_margin or 0,t.layout_marginBottom or t.layout_margin or 0))\n        end\n    end\n\n    if t.padding and type(t.padding)==\"table\" then\n        view.setPadding(checkValues(table.unpack(t.padding)))\n      elseif t.padding or t.paddingLeft or t.paddingTop or t.paddingRight or t.paddingBottom then\n        view.setPadding(checkValues(t.paddingLeft or t.padding or 0, t.paddingTop or t.padding or 0, t.paddingRight or t.padding or 0, t.paddingBottom or t.padding or 0))\n    end\n    if t.paddingStart or t.paddingEnd then\n        view.setPaddingRelative(checkValues(t.paddingStart or t.padding or 0, t.paddingTop or t.padding or 0, t.paddingEnd or t.padding or 0, t.paddingBottom or t.padding or 0))\n    end\n\n    local c={}\n    setmetatable(c,{__index=t})\n    setstyle(c,t,root,view,params,ids)\n\n    for k,v in pairs(t) do\n        if tonumber(k) and (type(v)==\"table\" or type(v)==\"string\") then\n            if instanceof(view,AdapterView) then\n                if type(v)==\"string\" then\n                    v=require(v)\n                end\n              else\n                local child = v[1]\n                if child then\n                    child = tostring(child):match(\"class([!-z_ ]+)\")\n                    if child then\n                        child = child:gsub(\" \",\"\")\n                    end\n                end\n                if not child then child = \"android.widget.LinearLayout\" end\n                local _,VLayout = pcall(luajava.bindClass,child..\"$LayoutParams\")\n                if not _ then\n                    local vvvv = loadlayout(v,root,params.class)\n                    view.addView(vvvv)\n                  else\n                    local vvvv = loadlayout(v,root,nil)\n                    view.addView(vvvv)\n                end\n            end\n          elseif k==\"id\" then\n            rawset(root,v,view)\n            local id=ids.id\n            ids.id=ids.id+1\n            view.setId(id)\n            ids[v]=id\n          else\n            local e,s=pcall(setattribute,root,view,params,k,v,ids,t)\n            if not e then\n                local _,i=s:find(\":%d+:\")\n                s=s:sub(i or 1,-1)\n                error(string.format(\"loadlayout error %s \\n\\tat %s\\n\\tat  key=%s value=%s\\n\\tat %s\",s,view.toString(),k,v,dump2(t)),2)\n            end\n        end\n    end\n    view.setLayoutParams(params)\n    return view\nend"), LuaValue.m("loadlayout"), LuaValue.u, globals}).n(1).Y();
        Context context = e;
        Globals globals2 = _G;
        globals2.j("load").C().a(new LuaValue[]{LuaValue.m("debug.setmetatable(string,string)  function loadmenu(menu,t,root,n)\n  local ids = luajava.ids\n  root=root or _G\n  n=n or 0\n  for k,v in ipairs(t) do\n    local id=ids.id\n    ids.id=ids.id+1\n    if v[1]== MenuItem then\n      local item=menu.add(v.group or 0,id,v.order or 0,v.title)\n      if v.id then\n        rawset(root,v.id,item)\n        ids[v.id]=id\n      end\n      if k<=n then\n        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)\n      end\n      if v.icon then\n        item.setIcon(BitmapDrawable(loadbitmap(v.icon)))\n      end\n      if v.enabled==false then\n        item.setEnabled(v.enabled)\n      end\n      if v.visible==false then\n        item.setVisible(v.visible)\n      end\n      if type(v.func)==\"function\" then\n        item.onMenuItemClick = v.func\n      end\n     elseif v[1]== SubMenu then\n      local item=menu.addSubMenu(v.group or 0,id,v.order or 0,v.title)\n      loadmenu(item,v,root)\n    end\n  end\nend\n"), LuaValue.m("loadmenu"), LuaValue.u, globals2}).n(1).Y();
        File file = new File(absolutePath + "/source/class/");
        if (!file.exists()) {
            file.mkdirs();
        } else {
            ScriptUtils.deleteFiles(file);
            file.delete();
            file.mkdirs();
        }
        classpath = absolutePath + "/source/class/";
        File file2 = file;
        _G.j("luajava").c("classpath", classpath);
        File file3 = new File(absolutePath + "/source/dex/");
        if (!file3.exists()) {
            file3.mkdirs();
        } else {
            ScriptUtils.deleteFiles(file3);
            file3.delete();
            file3.mkdirs();
        }
        dexpath = absolutePath + "/source/dex/";
        File file4 = file3;
        File file5 = new File(absolutePath + "/source/jar/");
        if (!file5.exists()) {
            file5.mkdirs();
        } else {
            ScriptUtils.deleteFiles(file5);
            file5.delete();
            file5.mkdirs();
        }
        jarpath = absolutePath + "/source/jar/";
        File file6 = parentFile;
        _G.j("luajava").c("jarpath", jarpath);
        _G.j("luajava").c("logpath", dexpath + "log.txt");
        Tools.a("android", new File(file5, "android.jar"));
        Tools.a("luaj", new File(file5, "luaj.jar"));
        _G.a("javac", new JavaCompiler());
        _G.a("todex", new classcompile());
        mt.a("pak", new LuaTable());
        mt.b(LuaValue.D, new CLASSINDEX());
        _G.v(mt);
        _G.j("luajava").a("methodProxy", new MethodProxy());
        _G.j("string").a("random", new randomString());
        _G.F(new ZipStreamLib());
        _G.F(new AppLib());
        _G.F(new FileLib());
        _G.a("loadXml", new loadXml());
        return null;
    }
}
