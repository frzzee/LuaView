package luaj.lib;

import android.app.Application;
import android.ext.Tools;
import android.toast.ToastParams;
import android.toast.ToastStrategy;
import android.toast.Toaster;
import android.toast.config.IToastStyle;
import android.toast.style.BlackToastStyle;
import android.toast.style.CustomToastStyle;
import android.toast.style.WhiteToastStyle;
import com.z.gauae.R;
import luaj.LuaTable;
import luaj.LuaValue;
import luaj.ap;

public class ToastLib extends TwoArgFunction {
    public LuaValue a(LuaValue modname, LuaValue env) {
        Toaster.init((Application) Tools.e().getApplicationContext());
        LuaTable table = new LuaTable();
        table.a("show", new show());
        table.a("showShort", new showShort());
        table.a("showLong", new showLong());
        table.a("crossPage", new crossPageShow());
        table.a("delay", new delayShowToast());
        table.a("white", new whiteShow());
        table.a("black", new blackShow());
        table.a("hint", new hintShow());
        table.a("warn", new warnShow());
        table.a("error", new errorShow());
        table.a("success", new successShow());
        table.a("setMode", new setStrategy());
        table.a("diyShow", new diyShow());
        table.a("cancel", new cancel());
        table.a("setStyle", new setStyle());
        table.a("setGravity", new setGravity());
        env.a("toast", table);
        return u;
    }

    static class show extends VarArgFunction {
        show() {
        }

        public ap a_(ap v) {
            LuaValue value = v.v(1);
            if (value.isuserdata()) {
                Object obj = value.checkuserdata();
                if (obj == null) {
                    Toaster.show("null");
                } else if (obj instanceof CharSequence) {
                    Toaster.show((CharSequence) obj);
                } else {
                    Toaster.show(obj);
                }
            } else {
                Toaster.show(value.d_());
            }
            return LuaValue.u;
        }

        public String d_() {
            return "toast.show(obj)Message prompt, the message retention time is automatically determined by the length of the displayed message, and the message style is the default style. Pass in a parameter, which supports string, other character sequences that inherit CharSequence, and other Object";
        }
    }

    static class showShort extends VarArgFunction {
        showShort() {
        }

        public ap a_(ap v) {
            LuaValue value = v.v(1);
            if (value.isuserdata()) {
                Object obj = value.checkuserdata();
                if (obj == null) {
                    Toaster.showShort("null");
                } else if (obj instanceof CharSequence) {
                    Toaster.showShort((CharSequence) obj);
                } else {
                    Toaster.showShort(obj);
                }
            } else {
                Toaster.showShort(value.d_());
            }
            return LuaValue.u;
        }

        public String d_() {
            return "toast.showShort(obj)Short message prompt, short stay time, message style is default style. Pass in a parameter, which supports string, other character sequences that inherit CharSequence, and other Object";
        }
    }

    static class showLong extends VarArgFunction {
        showLong() {
        }

        public ap a_(ap v) {
            LuaValue value = v.v(1);
            if (value.isuserdata()) {
                Object obj = value.checkuserdata();
                if (obj == null) {
                    Toaster.showLong("null");
                } else if (obj instanceof ToastParams) {
                    Toaster.showLong((ToastParams) obj);
                } else if (obj instanceof CharSequence) {
                    Toaster.showLong((CharSequence) obj);
                } else {
                    Toaster.showLong(obj);
                }
            } else {
                Toaster.showLong(value.d_());
            }
            return LuaValue.u;
        }

        public String d_() {
            return "toast.showLong(obj)Long message prompt, long stay time, the message style is the default style. Pass in a parameter, which supports string, other character sequences that inherit CharSequence, and other Object";
        }
    }

    static class crossPageShow extends VarArgFunction {
        crossPageShow() {
        }

        public ap a_(ap v) {
            ToastParams params = new ToastParams();
            LuaValue value = v.v(1);
            if (value.isuserdata()) {
                Object obj = value.checkuserdata();
                if (obj == null) {
                    params.text = "null";
                } else if (obj instanceof CharSequence) {
                    params.text = (CharSequence) obj;
                } else {
                    params.text = String.valueOf(obj);
                }
            } else {
                params.text = value.d_();
            }
            params.crossPageShow = true;
            Toaster.show(params);
            return LuaValue.u;
        }

        public String d_() {
            return "toast.crossPage(obj)For cross-page message prompts, the system's built-in message style will be used first. Pass in a parameter that supports string, other character sequences that inherit CharSequence, and other Object";
        }
    }

    static class delayShowToast extends VarArgFunction {
        delayShowToast() {
        }

        public ap a_(ap v) {
            LuaValue value = v.v(1);
            long time = v.a(2, 2000);
            if (value.isuserdata()) {
                Object obj = value.checkuserdata();
                if (obj == null) {
                    Toaster.delayedShow("null", time);
                } else if (obj instanceof CharSequence) {
                    Toaster.delayedShow((CharSequence) obj, time);
                } else {
                    Toaster.delayedShow(obj, time);
                }
            } else {
                Toaster.delayedShow(value.d_(), time);
            }
            return LuaValue.u;
        }

        public String d_() {
            return "toast.delay(obj,time)Delayed message prompt, the message style is the default style. Two parameters are passed in. The first parameter supports string, other character sequences that inherit CharSequence, and other Objects. The second parameter is the delay time in milliseconds.";
        }
    }

    public static void show(ap v, IToastStyle<?> style) {
        ToastParams params = new ToastParams();
        LuaValue value = v.v(1);
        if (value.isuserdata()) {
            Object obj = value.checkuserdata();
            if (obj == null) {
                params.text = "null";
            } else if (obj instanceof CharSequence) {
                params.text = (CharSequence) obj;
            } else {
                params.text = String.valueOf(obj);
            }
        } else {
            params.text = value.d_();
        }
        params.style = style;
        Toaster.show(params);
    }

    static class whiteShow extends VarArgFunction {
        whiteShow() {
        }

        public ap a_(ap v) {
            ToastLib.show(v, new WhiteToastStyle());
            return LuaValue.u;
        }

        public String d_() {
            return "toast.white(obj)A white message prompt is effective locally. Pass in a parameter, which supports string, other character sequences that inherit CharSequence, and other Object";
        }
    }

    static class blackShow extends VarArgFunction {
        blackShow() {
        }

        public ap a_(ap v) {
            ToastLib.show(v, new BlackToastStyle());
            return LuaValue.u;
        }

        public String d_() {
            return "toast.black(obj)A black message prompt is effective locally. Pass in a parameter, which supports string, other character sequences that inherit CharSequence, and other Object";
        }
    }

    static class hintShow extends VarArgFunction {
        hintShow() {
        }

        public ap a_(ap v) {
            ToastLib.show(v, new CustomToastStyle(R.layout.mode_toast_hint));
            return LuaValue.u;
        }

        public String d_() {
            return "toast.hint(obj)Tips: This is only effective locally. Pass in a parameter that supports string, other character sequences that inherit CharSequence, and other Object";
        }
    }

    static class warnShow extends VarArgFunction {
        warnShow() {
        }

        public ap a_(ap v) {
            ToastLib.show(v, new CustomToastStyle(R.layout.mode_toast_warn));
            return LuaValue.u;
        }

        public String d_() {
            return "toast.warn(obj)Warning, local effect. Pass in a parameter, which supports string, other character sequences that inherit CharSequence, and other Object";
        }
    }

    static class errorShow extends VarArgFunction {
        errorShow() {
        }

        public ap a_(ap v) {
            ToastLib.show(v, new CustomToastStyle(R.layout.mode_toast_error));
            return LuaValue.u;
        }

        public String d_() {
            return "toast.error(obj)Error message, local effect. Pass in a parameter, which supports string, other character sequences that inherit CharSequence, and other Object";
        }
    }

    static class successShow extends VarArgFunction {
        successShow() {
        }

        public ap a_(ap v) {
            ToastLib.show(v, new CustomToastStyle(R.layout.mode_toast_success));
            return LuaValue.u;
        }

        public String d_() {
            return "toast.success(obj)Success prompt, correct prompt, local effect. Pass in a parameter, which supports string, other character sequences that inherit CharSequence, and other Object";
        }
    }

    static class setStrategy extends VarArgFunction {
        setStrategy() {
        }

        public ap a_(ap v) {
            int i = v.o(1);
            if (!(i == 1 || i == 0)) {
                i = 0;
            }
            Toaster.setStrategy(new ToastStrategy(i));
            return LuaValue.u;
        }

        public String d_() {
            return "toast.setMode(int mode)Switch the message display mode, which takes effect globally. Enter 1 for queue message display mode, and messages will be displayed one by one; enter 0 for instant messages, and only the latest one will be displayed.";
        }
    }

    static class diyShow extends VarArgFunction {
        diyShow() {
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v33, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v4, resolved type: android.view.View} */
        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public luaj.ap a_(luaj.ap r21) {
            /*
                r20 = this;
                r1 = r21
                java.lang.String r0 = "hint"
                r2 = 0
                r3 = 0
                r4 = 0
                r5 = 1
                boolean r6 = r1.isuserdata(r5)     // Catch:{ Exception -> 0x00f0 }
                r7 = 6
                r8 = 5
                r9 = 3
                r10 = 4
                r11 = 0
                r12 = 2
                if (r6 == 0) goto L_0x0029
                java.lang.Object r0 = r1.checkuserdata(r5)     // Catch:{ Exception -> 0x00f0 }
                boolean r6 = r0 instanceof android.view.View     // Catch:{ Exception -> 0x00f0 }
                if (r6 == 0) goto L_0x0022
                r6 = r0
                android.view.View r6 = (android.view.View) r6     // Catch:{ Exception -> 0x00f0 }
                r2 = r6
                goto L_0x00a3
            L_0x0022:
                java.lang.String r5 = "所传布局类型错误"
                luaj.LuaString r5 = luaj.LuaValue.m(r5)     // Catch:{ Exception -> 0x00f0 }
                return r5
            L_0x0029:
                java.lang.String r6 = r1.c(r5, r0)     // Catch:{ Exception -> 0x00f0 }
                int r13 = r6.hashCode()     // Catch:{ Exception -> 0x00f0 }
                switch(r13) {
                    case -1867169789: goto L_0x006f;
                    case 799375: goto L_0x0065;
                    case 823146: goto L_0x005b;
                    case 1127844: goto L_0x0051;
                    case 3202695: goto L_0x0049;
                    case 3641990: goto L_0x003f;
                    case 96784904: goto L_0x0035;
                    default: goto L_0x0034;
                }     // Catch:{ Exception -> 0x00f0 }
            L_0x0034:
                goto L_0x0079
            L_0x0035:
                java.lang.String r0 = "error"
                boolean r0 = r6.equals(r0)     // Catch:{ Exception -> 0x00f0 }
                if (r0 == 0) goto L_0x0034
                r0 = r7
                goto L_0x007a
            L_0x003f:
                java.lang.String r0 = "warn"
                boolean r0 = r6.equals(r0)     // Catch:{ Exception -> 0x00f0 }
                if (r0 == 0) goto L_0x0034
                r0 = r10
                goto L_0x007a
            L_0x0049:
                boolean r0 = r6.equals(r0)     // Catch:{ Exception -> 0x00f0 }
                if (r0 == 0) goto L_0x0034
                r0 = r11
                goto L_0x007a
            L_0x0051:
                java.lang.String r0 = "警告"
                boolean r0 = r6.equals(r0)     // Catch:{ Exception -> 0x00f0 }
                if (r0 == 0) goto L_0x0034
                r0 = r8
                goto L_0x007a
            L_0x005b:
                java.lang.String r0 = "提示"
                boolean r0 = r6.equals(r0)     // Catch:{ Exception -> 0x00f0 }
                if (r0 == 0) goto L_0x0034
                r0 = r5
                goto L_0x007a
            L_0x0065:
                java.lang.String r0 = "成功"
                boolean r0 = r6.equals(r0)     // Catch:{ Exception -> 0x00f0 }
                if (r0 == 0) goto L_0x0034
                r0 = r9
                goto L_0x007a
            L_0x006f:
                java.lang.String r0 = "success"
                boolean r0 = r6.equals(r0)     // Catch:{ Exception -> 0x00f0 }
                if (r0 == 0) goto L_0x0034
                r0 = r12
                goto L_0x007a
            L_0x0079:
                r0 = -1
            L_0x007a:
                switch(r0) {
                    case 0: goto L_0x008a;
                    case 1: goto L_0x008a;
                    case 2: goto L_0x0086;
                    case 3: goto L_0x0086;
                    case 4: goto L_0x0082;
                    case 5: goto L_0x0082;
                    case 6: goto L_0x007e;
                    default: goto L_0x007d;
                }     // Catch:{ Exception -> 0x00f0 }
            L_0x007d:
                goto L_0x008e
            L_0x007e:
                int r0 = com.z.gauae.R.layout.mode_toast_error     // Catch:{ Exception -> 0x00f0 }
                r4 = r0
                goto L_0x008e
            L_0x0082:
                int r0 = com.z.gauae.R.layout.mode_toast_warn     // Catch:{ Exception -> 0x00f0 }
                r4 = r0
                goto L_0x008e
            L_0x0086:
                int r0 = com.z.gauae.R.layout.mode_toast_success     // Catch:{ Exception -> 0x00f0 }
                r4 = r0
                goto L_0x008e
            L_0x008a:
                int r0 = com.z.gauae.R.layout.mode_toast_hint     // Catch:{ Exception -> 0x00f0 }
                r4 = r0
            L_0x008e:
                android.content.Context r0 = android.ext.Tools.e()     // Catch:{ Exception -> 0x00f0 }
                if (r0 == 0) goto L_0x0099
                android.content.Context r13 = r0.getApplicationContext()     // Catch:{ Exception -> 0x00f0 }
                r0 = r13
            L_0x0099:
                android.view.LayoutInflater r13 = android.view.LayoutInflater.from(r0)     // Catch:{ Exception -> 0x00f0 }
                r14 = 0
                android.view.View r13 = r13.inflate(r4, r14)     // Catch:{ Exception -> 0x00f0 }
                r2 = r13
            L_0x00a3:
                android.toast.style.VCustomToastStyle r0 = new android.toast.style.VCustomToastStyle     // Catch:{ Exception -> 0x00f0 }
                r6 = 17
                int r15 = r1.d(r10, r6)     // Catch:{ Exception -> 0x00f0 }
                int r16 = r1.d(r8, r11)     // Catch:{ Exception -> 0x00f0 }
                int r17 = r1.d(r7, r11)     // Catch:{ Exception -> 0x00f0 }
                r7 = 0
                r11 = 7
                double r13 = r1.a(r11, r7)     // Catch:{ Exception -> 0x00f0 }
                float r11 = (float) r13     // Catch:{ Exception -> 0x00f0 }
                r13 = 8
                double r7 = r1.a(r13, r7)     // Catch:{ Exception -> 0x00f0 }
                float r7 = (float) r7     // Catch:{ Exception -> 0x00f0 }
                r13 = r0
                r14 = r2
                r18 = r11
                r19 = r7
                r13.<init>(r14, r15, r16, r17, r18, r19)     // Catch:{ Exception -> 0x00f0 }
                boolean r3 = r1.a(r9, r5)     // Catch:{ Exception -> 0x00f0 }
                if (r3 == 0) goto L_0x00e5
                android.toast.Toaster.setStyle(r0)     // Catch:{ Exception -> 0x00f0 }
                int r3 = r1.d(r10, r6)     // Catch:{ Exception -> 0x00f0 }
                android.toast.Toaster.setGravity(r3)     // Catch:{ Exception -> 0x00f0 }
                java.lang.String r3 = ""
                java.lang.String r3 = r1.c(r12, r3)     // Catch:{ Exception -> 0x00f0 }
                android.toast.Toaster.show(r3)     // Catch:{ Exception -> 0x00f0 }
                goto L_0x00ec
            L_0x00e5:
                luaj.ap r3 = r1.e_(r12)     // Catch:{ Exception -> 0x00f0 }
                luaj.lib.ToastLib.show(r3, r0)     // Catch:{ Exception -> 0x00f0 }
            L_0x00ec:
                luaj.LuaValue r0 = luaj.LuaValue.u
                return r0
            L_0x00f0:
                r0 = move-exception
                java.lang.String r2 = "缺少Id为0x0102000b的TextView"
                luaj.LuaString r2 = luaj.LuaValue.m(r2)
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: luaj.lib.ToastLib.diyShow.a_(luaj.ap):luaj.ap");
        }

        public String d_() {
            return "toast.diyShow(view,text,isGlobals,gravity,xoffset,yoffset,horizontalMargin,verticalMargin)Custom View message prompt, effective globally or locally. \n view is a custom layout, View type, must be passed, the View layout must contain a TextView, this TextView must have an ID setting of Id=0x0102000b;\n text is the message content, String type, if not passed, the message content is determined by the view\n whether isGlobals is set to global toast";
        }
    }

    static class cancel extends VarArgFunction {
        cancel() {
        }

        public ap a_(ap v) {
            Toaster.cancel();
            return LuaValue.u;
        }

        public String d_() {
            return "toast.cancel()Cancel all message prompts, effective globally";
        }
    }

    static class setStyle extends VarArgFunction {
        setStyle() {
        }

        public ap a_(ap v) {
            try {
                CustomToastStyle sc = new CustomToastStyle(R.layout.mode_toast_hint);
                Object obj = v.optuserdata(1, sc);
                if (obj instanceof IToastStyle) {
                    Toaster.setStyle((IToastStyle) obj);
                } else {
                    Toaster.setStyle(sc);
                }
                return LuaValue.u;
            } catch (Exception e) {
                return LuaValue.m(e.toString());
            }
        }

        public String d_() {
            return "toast.setStyle(style)Set a globally effective message style. The style is of IToastStyle type or its subclass";
        }
    }

    static class setGravity extends VarArgFunction {
        setGravity() {
        }

        public ap a_(ap v) {
            Toaster.setGravity(v.d(1, 17), v.d(2, 0), v.d(3, 0), (float) v.a(4, 0.0d), (float) v.a(5, 0.0d));
            return LuaValue.u;
        }

        public String d_() {
            return "toast.setGravity(gravity,xoffset,yoffset,horizontalMargin,verticalMargin)Set a globally effective message display position. gravity is the display center of gravity, int type, defaults to Gravity.CENTER when not passed\n xoffset is the horizontal offset (screen x-axis direction) relative to the center of gravity, int type, defaults to 0 when not passed\n yoffset is the vertical offset relative to the center of gravity, int type, defaults to 0 when not passed\n horizontalMargin is the horizontal offset relative to the center of gravity, int type, defaults to 0 when not passed";
        }
    }
}
