package luaj.lib.jse;

import luaj.LuaValue;
import luaj.lib.MathLib;
import luaj.lib.TwoArgFunction;

public class JseMathLib extends MathLib {

    static final class acos extends MathLib.UnaryOp {
        acos() {
        }

        /* access modifiers changed from: protected */
        public double a(double d) {
            return Math.acos(d);
        }
    }

    static final class asin extends MathLib.UnaryOp {
        asin() {
        }

        /* access modifiers changed from: protected */
        public double a(double d) {
            return Math.asin(d);
        }
    }

    static final class atan2 extends TwoArgFunction {
        atan2() {
        }

        public LuaValue a(LuaValue luaValue, LuaValue luaValue2) {
            return c(Math.atan2(luaValue.x(), luaValue2.b(1.0d)));
        }
    }

    static final class cosh extends MathLib.UnaryOp {
        cosh() {
        }

        /* access modifiers changed from: protected */
        public double a(double d) {
            return Math.cosh(d);
        }
    }

    static final class exp extends MathLib.UnaryOp {
        exp() {
        }

        /* access modifiers changed from: protected */
        public double a(double d) {
            return Math.exp(d);
        }
    }

    static final class log extends TwoArgFunction {
        log() {
        }

        public LuaValue a(LuaValue luaValue, LuaValue luaValue2) {
            double log = Math.log(luaValue.x());
            double b = luaValue2.b(2.718281828459045d);
            if (b != 2.718281828459045d) {
                log /= Math.log(b);
            }
            return c(log);
        }
    }

    static final class pow extends MathLib.BinaryOp {
        pow() {
        }

        /* access modifiers changed from: protected */
        public double a(double d, double d2) {
            return Math.pow(d, d2);
        }
    }

    static final class sinh extends MathLib.UnaryOp {
        sinh() {
        }

        /* access modifiers changed from: protected */
        public double a(double d) {
            return Math.sinh(d);
        }
    }

    static final class tanh extends MathLib.UnaryOp {
        tanh() {
        }

        /* access modifiers changed from: protected */
        public double a(double d) {
            return Math.tanh(d);
        }
    }

    public LuaValue a(LuaValue luaValue, LuaValue luaValue2) {
        JseMathLib.super.a(luaValue, luaValue2);
        LuaValue j = luaValue2.j("math");
        j.a("acos", new acos());
        j.a("asin", new asin());
        atan2 atan22 = new atan2();
        j.a("atan", atan22);
        j.a("atan2", atan22);
        j.a("cosh", new cosh());
        j.a("exp", new exp());
        j.a("log", new log());
        j.a("pow", new pow());
        j.a("sinh", new sinh());
        j.a("tanh", new tanh());
        return j;
    }

    public double dpow_lib(double d, double d2) {
        return Math.pow(d, d2);
    }
}
