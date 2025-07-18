package luaj.lib.jse;

import java.lang.reflect.Array;
import luaj.ap;
import luaj.lib.VarArgFunction;
import luaj.lib.jse.CoerceLuaToJava;

abstract class JavaMember extends VarArgFunction {
    static final int METHOD_MODIFIERS_VARARGS = 128;
    final CoerceLuaToJava.Coercion[] fixedargs;
    private CoerceLuaToJava.Coercion vararg2;
    final CoerceLuaToJava.Coercion varargs;
    private Class varclass;

    protected JavaMember(Class[] clsArr, int i) {
        boolean z = (i & METHOD_MODIFIERS_VARARGS) != 0;
        int length = clsArr.length;
        this.fixedargs = new CoerceLuaToJava.Coercion[(z ? length - 1 : length)];
        int i2 = 0;
        while (true) {
            CoerceLuaToJava.Coercion[] coercionArr = this.fixedargs;
            if (i2 >= coercionArr.length) {
                break;
            }
            coercionArr[i2] = CoerceLuaToJava.getCoercion(clsArr[i2]);
            i2++;
        }
        this.varargs = z ? CoerceLuaToJava.getCoercion(clsArr[clsArr.length - 1]) : null;
        if (z) {
            Class<?> componentType = clsArr[clsArr.length - 1].getComponentType();
            this.varclass = componentType;
            this.vararg2 = CoerceLuaToJava.getCoercion(componentType);
        }
    }

    /* access modifiers changed from: protected */
    public Object[] convertArgs(ap apVar) {
        CoerceLuaToJava.Coercion[] coercionArr;
        if (this.varargs == null) {
            Object[] objArr = new Object[this.fixedargs.length];
            for (int i = 0; i < objArr.length; i++) {
                objArr[i] = this.fixedargs[i].coerce(apVar.c(i + 1));
            }
            return objArr;
        }
        int j_ = apVar.j_();
        Object[] objArr2 = new Object[(this.fixedargs.length + 1)];
        int i2 = 0;
        while (true) {
            coercionArr = this.fixedargs;
            if (i2 >= coercionArr.length) {
                break;
            }
            objArr2[i2] = coercionArr[i2].coerce(apVar.c(i2 + 1));
            i2++;
        }
        if (j_ != coercionArr.length + 1 || this.varargs.score(apVar.c(coercionArr.length + 1)) >= 256) {
            Object newInstance = Array.newInstance(this.varclass, Math.max(apVar.j_() - this.fixedargs.length, 0));
            for (int length = this.fixedargs.length; length < j_; length++) {
                Array.set(newInstance, length - this.fixedargs.length, this.vararg2.coerce(apVar.c(length + 1)));
            }
            objArr2[this.fixedargs.length] = newInstance;
        } else {
            CoerceLuaToJava.Coercion[] coercionArr2 = this.fixedargs;
            objArr2[coercionArr2.length] = this.varargs.coerce(apVar.c(coercionArr2.length + 1));
        }
        return objArr2;
    }

    /* access modifiers changed from: package-private */
    public int score(ap apVar) {
        CoerceLuaToJava.Coercion[] coercionArr;
        int j_ = apVar.j_();
        int length = j_ > this.fixedargs.length ? CoerceLuaToJava.SCORE_WRONG_TYPE * (j_ - this.fixedargs.length) : 0;
        int i = 0;
        while (true) {
            coercionArr = this.fixedargs;
            if (i >= coercionArr.length) {
                break;
            }
            length += coercionArr[i].score(apVar.c(i + 1));
            i++;
        }
        CoerceLuaToJava.Coercion coercion = this.varargs;
        if (coercion == null) {
            return length;
        }
        if (j_ == coercionArr.length + 1) {
            return length + Math.min(coercion.score(apVar.c(coercionArr.length + 1)), this.vararg2.score(apVar.c(this.fixedargs.length + 1)));
        }
        for (int length2 = coercionArr.length; length2 < j_; length2++) {
            length += this.vararg2.score(apVar.c(length2 + 1));
        }
        return length;
    }
}
