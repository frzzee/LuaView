package luaj;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class LuaBigNumber extends LuaNumber {
    public static BigDecimal MAX_INT = BigDecimal.valueOf(2147483647L);
    public static BigDecimal MAX_LONG = BigDecimal.valueOf(Long.MAX_VALUE);
    public static BigDecimal MAX_LONG_IN_DOUBLE = BigDecimal.valueOf(9007199254740992L);
    public static BigDecimal MIN_INT = BigDecimal.valueOf(-2147483648L);
    public static BigDecimal MIN_LONG = BigDecimal.valueOf(Long.MIN_VALUE);
    public static BigDecimal MIN_LONG_IN_DOUBLE = BigDecimal.valueOf(-9007199254740992L);
    public static int defaultScale = 20;
    final BigDecimal v;

    public LuaBigNumber(double d) {
        this.v = BigDecimal.valueOf(d);
    }

    public LuaBigNumber(int i) {
        this.v = BigDecimal.valueOf((long) i);
    }

    public LuaBigNumber(long j) {
        this.v = BigDecimal.valueOf(j);
    }

    public LuaBigNumber(String str) {
        this.v = new BigDecimal(str);
    }

    public LuaBigNumber(BigDecimal bigDecimal) {
        this.v = bigDecimal;
    }

    private String S() {
        BigDecimal bigDecimal = this.v;
        return bigDecimal != null ? bigDecimal.toString() : "null";
    }

    private LuaString T() {
        return LuaString.c(S());
    }

    public static LuaBigNumber bignumberOf(double d) {
        return new LuaBigNumber(d);
    }

    public static LuaBigNumber bignumberOf(int i) {
        return new LuaBigNumber(i);
    }

    public static LuaBigNumber bignumberOf(long j) {
        return new LuaBigNumber(j);
    }

    public static LuaBigNumber bignumberOf(String str) {
        return new LuaBigNumber(str);
    }

    public static LuaBigNumber bignumberOf(BigDecimal bigDecimal) {
        return new LuaBigNumber(bigDecimal);
    }

    public LuaString a(LuaString luaString) {
        return T();
    }

    public long a_(long j) {
        return this.v.longValue();
    }

    public double b(double d) {
        return this.v.doubleValue();
    }

    public boolean b(LuaValue luaValue) {
        BigDecimal bigDecimal = null;
        if (luaValue.isbignumber()) {
            bigDecimal = ((LuaBigNumber) luaValue).v;
        } else if (luaValue.I()) {
            bigDecimal = luaValue.n() ? BigDecimal.valueOf(luaValue.q()) : BigDecimal.valueOf(luaValue.o());
        }
        return bigDecimal == null ? LuaBigNumber.super.b(luaValue) : this.v.compareTo(bigDecimal) == 0;
    }

    public int b_(int i) {
        return this.v.intValue();
    }

    public String b_(String str) {
        return S();
    }

    public boolean c(LuaValue luaValue) {
        BigDecimal bigDecimal = null;
        if (luaValue.isbignumber()) {
            bigDecimal = ((LuaBigNumber) luaValue).v;
        } else if (luaValue.I()) {
            bigDecimal = luaValue.n() ? BigDecimal.valueOf(luaValue.q()) : BigDecimal.valueOf(luaValue.o());
        }
        return bigDecimal != null && this.v.compareTo(bigDecimal) == 0;
    }

    public BigDecimal checkbignum() {
        return this.v;
    }

    public LuaBigNumber checkbignumber() {
        return this;
    }

    public LuaValue d(LuaValue luaValue) {
        BigDecimal bigDecimal = null;
        if (luaValue.isbignumber()) {
            bigDecimal = ((LuaBigNumber) luaValue).v;
        } else if (luaValue.I()) {
            bigDecimal = luaValue.n() ? BigDecimal.valueOf(luaValue.q()) : BigDecimal.valueOf(luaValue.o());
        }
        return bigDecimal == null ? LuaBigNumber.super.d(luaValue) : new LuaBigNumber(this.v.add(bigDecimal));
    }

    public String d_() {
        return S();
    }

    public LuaValue e(LuaValue luaValue) {
        BigDecimal bigDecimal = null;
        if (luaValue.isbignumber()) {
            bigDecimal = ((LuaBigNumber) luaValue).v;
        } else if (luaValue.I()) {
            bigDecimal = luaValue.n() ? BigDecimal.valueOf(luaValue.q()) : BigDecimal.valueOf(luaValue.o());
        }
        return bigDecimal == null ? LuaBigNumber.super.e(luaValue) : new LuaBigNumber(this.v.subtract(bigDecimal));
    }

    public boolean equals(Object obj) {
        return (obj instanceof LuaBigNumber) && ((LuaBigNumber) obj).v.compareTo(this.v) == 0;
    }

    public LuaValue f(LuaValue luaValue) {
        BigDecimal bigDecimal = null;
        if (luaValue.isbignumber()) {
            bigDecimal = ((LuaBigNumber) luaValue).v;
        } else if (luaValue.I()) {
            bigDecimal = luaValue.n() ? BigDecimal.valueOf(luaValue.q()) : BigDecimal.valueOf(luaValue.o());
        }
        return bigDecimal == null ? LuaBigNumber.super.f(luaValue) : new LuaBigNumber(this.v.multiply(bigDecimal));
    }

    public LuaValue g(LuaValue luaValue) {
        if (!luaValue.I()) {
            return LuaBigNumber.super.g(luaValue);
        }
        if (!luaValue.q_()) {
            return LuaBigNumber.super.g(luaValue);
        }
        int p = luaValue.p();
        return p >= 0 ? new LuaBigNumber(this.v.pow(p)) : new LuaBigNumber(BigDecimal.ONE.divide(this.v.pow(-p), defaultScale, RoundingMode.HALF_UP));
    }

    public LuaValue h(LuaValue luaValue) {
        BigDecimal bigDecimal = null;
        if (luaValue.isbignumber()) {
            bigDecimal = ((LuaBigNumber) luaValue).v;
        } else if (luaValue.I()) {
            bigDecimal = luaValue.n() ? BigDecimal.valueOf(luaValue.q()) : BigDecimal.valueOf(luaValue.o());
        }
        return bigDecimal == null ? LuaBigNumber.super.h(luaValue) : new LuaBigNumber(this.v.divide(bigDecimal, defaultScale, RoundingMode.HALF_UP));
    }

    public int hashCode() {
        return this.v.hashCode();
    }

    public LuaValue i(LuaValue luaValue) {
        BigDecimal bigDecimal = null;
        if (luaValue.isbignumber()) {
            bigDecimal = ((LuaBigNumber) luaValue).v;
        } else if (luaValue.I()) {
            bigDecimal = luaValue.n() ? BigDecimal.valueOf(luaValue.q()) : BigDecimal.valueOf(luaValue.o());
        }
        return bigDecimal == null ? LuaBigNumber.super.i(luaValue) : new LuaBigNumber(this.v.divide(bigDecimal, 0, RoundingMode.FLOOR));
    }

    public boolean isConvertible() {
        return this.v.compareTo(MAX_LONG_IN_DOUBLE) <= 0 && this.v.compareTo(MIN_LONG_IN_DOUBLE) >= 0;
    }

    public boolean isbigint() {
        try {
            return new BigDecimal(this.v.toBigIntegerExact().toString()).compareTo(this.v) == 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isbignumber() {
        return true;
    }

    public LuaValue j(LuaValue luaValue) {
        BigDecimal bigDecimal = null;
        if (luaValue.isbignumber()) {
            bigDecimal = ((LuaBigNumber) luaValue).v;
        } else if (luaValue.I()) {
            bigDecimal = luaValue.n() ? BigDecimal.valueOf(luaValue.q()) : BigDecimal.valueOf(luaValue.o());
        }
        return bigDecimal == null ? LuaBigNumber.super.j(luaValue) : new LuaBigNumber(this.v.remainder(bigDecimal));
    }

    public LuaValue k(LuaValue luaValue) {
        if (isbigint()) {
            if (luaValue.isbignumber()) {
                if (((LuaBigNumber) luaValue).isbigint()) {
                    return new LuaBigNumber(this.v.toBigIntegerExact().and(((LuaBigNumber) luaValue).checkbignum().toBigInteger()).toString());
                }
            } else if (luaValue.n()) {
                return new LuaBigNumber(this.v.toBigIntegerExact().and(BigInteger.valueOf(luaValue.q())).toString());
            }
        }
        return LuaBigNumber.super.k(luaValue);
    }

    public LuaValue l(LuaValue luaValue) {
        if (isbigint()) {
            if (luaValue.isbignumber()) {
                if (((LuaBigNumber) luaValue).isbigint()) {
                    return new LuaBigNumber(this.v.toBigIntegerExact().or(((LuaBigNumber) luaValue).checkbignum().toBigInteger()).toString());
                }
            } else if (luaValue.n()) {
                return new LuaBigNumber(this.v.toBigIntegerExact().or(BigInteger.valueOf(luaValue.q())).toString());
            }
        }
        return LuaBigNumber.super.l(luaValue);
    }

    public LuaValue m(LuaValue luaValue) {
        if (isbigint()) {
            if (luaValue.isbignumber()) {
                if (((LuaBigNumber) luaValue).isbigint()) {
                    return new LuaBigNumber(this.v.toBigIntegerExact().xor(((LuaBigNumber) luaValue).checkbignum().toBigInteger()).toString());
                }
            } else if (luaValue.n()) {
                return new LuaBigNumber(this.v.toBigIntegerExact().xor(BigInteger.valueOf(luaValue.q())).toString());
            }
        }
        return LuaBigNumber.super.m(luaValue);
    }

    public LuaValue n(LuaValue luaValue) {
        if (!luaValue.I()) {
            return LuaBigNumber.super.n(luaValue);
        }
        if (!luaValue.q_()) {
            return LuaBigNumber.super.g(luaValue);
        }
        return new LuaBigNumber(this.v.toBigIntegerExact().shiftLeft(luaValue.p()).toString());
    }

    public boolean n() {
        return this.v.compareTo(MAX_LONG) <= 0 && this.v.compareTo(MIN_LONG) >= 0 && BigDecimal.valueOf(this.v.longValue()).compareTo(this.v) == 0;
    }

    public double o() {
        return this.v.doubleValue();
    }

    public LuaValue o(LuaValue luaValue) {
        if (!luaValue.I()) {
            return LuaBigNumber.super.o(luaValue);
        }
        if (!luaValue.q_()) {
            return LuaBigNumber.super.g(luaValue);
        }
        return new LuaBigNumber(this.v.toBigIntegerExact().shiftRight(luaValue.p()).toString());
    }

    public BigDecimal optbignum(double d) {
        return this.v;
    }

    public BigDecimal optbignum(int i) {
        return this.v;
    }

    public BigDecimal optbignum(long j) {
        return this.v;
    }

    public BigDecimal optbignum(String str) {
        return this.v;
    }

    public BigDecimal optbignum(BigDecimal bigDecimal) {
        return this.v;
    }

    public LuaBigNumber optbignumber(double d) {
        return this;
    }

    public LuaBigNumber optbignumber(int i) {
        return this;
    }

    public LuaBigNumber optbignumber(long j) {
        return this;
    }

    public LuaBigNumber optbignumber(String str) {
        return this;
    }

    public LuaBigNumber optbignumber(BigDecimal bigDecimal) {
        return this;
    }

    public int p() {
        return this.v.intValue();
    }

    public boolean p(LuaValue luaValue) {
        BigDecimal bigDecimal = null;
        if (luaValue.isbignumber()) {
            bigDecimal = ((LuaBigNumber) luaValue).v;
        } else if (luaValue.I()) {
            bigDecimal = luaValue.n() ? BigDecimal.valueOf(luaValue.q()) : BigDecimal.valueOf(luaValue.o());
        }
        return bigDecimal == null ? LuaBigNumber.super.p(luaValue) : this.v.compareTo(bigDecimal) < 0;
    }

    public long q() {
        return this.v.longValue();
    }

    public boolean q(LuaValue luaValue) {
        BigDecimal bigDecimal = null;
        if (luaValue.isbignumber()) {
            bigDecimal = ((LuaBigNumber) luaValue).v;
        } else if (luaValue.I()) {
            bigDecimal = luaValue.n() ? BigDecimal.valueOf(luaValue.q()) : BigDecimal.valueOf(luaValue.o());
        }
        return bigDecimal == null ? LuaBigNumber.super.q(luaValue) : this.v.compareTo(bigDecimal) <= 0;
    }

    public boolean q_() {
        return this.v.compareTo(MAX_INT) <= 0 && this.v.compareTo(MIN_INT) >= 0 && BigDecimal.valueOf((long) this.v.intValue()).compareTo(this.v) == 0;
    }

    public LuaValue r() {
        return new LuaBigNumber(this.v.negate());
    }

    public boolean r(LuaValue luaValue) {
        BigDecimal bigDecimal = null;
        if (luaValue.isbignumber()) {
            bigDecimal = ((LuaBigNumber) luaValue).v;
        } else if (luaValue.I()) {
            bigDecimal = luaValue.n() ? BigDecimal.valueOf(luaValue.q()) : BigDecimal.valueOf(luaValue.o());
        }
        return bigDecimal == null ? LuaBigNumber.super.r(luaValue) : this.v.compareTo(bigDecimal) > 0;
    }

    public LuaValue s() {
        return isbigint() ? new LuaBigNumber(this.v.toBigIntegerExact().not().toString()) : LuaBigNumber.super.s();
    }

    public boolean s(LuaValue luaValue) {
        BigDecimal bigDecimal = null;
        if (luaValue.isbignumber()) {
            bigDecimal = ((LuaBigNumber) luaValue).v;
        } else if (luaValue.I()) {
            bigDecimal = luaValue.n() ? BigDecimal.valueOf(luaValue.q()) : BigDecimal.valueOf(luaValue.o());
        }
        return bigDecimal == null ? LuaBigNumber.super.s(luaValue) : this.v.compareTo(bigDecimal) >= 0;
    }

    public LuaString t() {
        return T();
    }

    public char tochar() {
        return (char) this.v.intValue();
    }

    public float tofloat() {
        return this.v.floatValue();
    }

    public LuaValue u() {
        return T();
    }

    public int v() {
        return this.v.intValue();
    }

    public long w() {
        return this.v.longValue();
    }

    public double x() {
        return this.v.doubleValue();
    }

    public String y() {
        return String.valueOf(this.v);
    }

    public LuaString z() {
        return valueOf(String.valueOf(this.v));
    }
}
