package luaj.lib.jse;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import luaj.LuaValue;
import luaj.ap;
import luaj.lib.VarArgFunction;
import luaj.o;

class JavaConstructor extends JavaMember {
    static final Map constructors = Collections.synchronizedMap(new HashMap());
    final Constructor constructor;

    static class Overload extends VarArgFunction {
        final JavaConstructor[] constructors;

        public Overload(JavaConstructor[] javaConstructorArr) {
            this.constructors = javaConstructorArr;
        }

        public ap a_(ap apVar) {
            JavaConstructor javaConstructor = null;
            int i = CoerceLuaToJava.SCORE_UNCOERCIBLE;
            int i2 = 0;
            while (true) {
                JavaConstructor[] javaConstructorArr = this.constructors;
                if (i2 >= javaConstructorArr.length) {
                    break;
                }
                int score = javaConstructorArr[i2].score(apVar);
                if (score < i) {
                    i = score;
                    javaConstructor = this.constructors[i2];
                    if (i == 0) {
                        break;
                    }
                }
                i2++;
            }
            if (javaConstructor == null) {
                LuaValue.f("no coercible public method");
            }
            return javaConstructor.a_(apVar);
        }
    }

    private JavaConstructor(Constructor constructor2) {
        super(constructor2.getParameterTypes(), constructor2.getModifiers());
        this.constructor = constructor2;
    }

    static JavaConstructor forConstructor(Constructor constructor2) {
        Map map = constructors;
        JavaConstructor javaConstructor = (JavaConstructor) map.get(constructor2);
        if (javaConstructor != null) {
            return javaConstructor;
        }
        JavaConstructor javaConstructor2 = new JavaConstructor(constructor2);
        JavaConstructor javaConstructor3 = javaConstructor2;
        map.put(constructor2, javaConstructor2);
        return javaConstructor3;
    }

    public static LuaValue forConstructors(JavaConstructor[] javaConstructorArr) {
        return new Overload(javaConstructorArr);
    }

    public ap a_(ap apVar) {
        try {
            return new JavaInstance(this.constructor.newInstance(convertArgs(apVar)));
        } catch (InvocationTargetException e) {
            throw new o(e.getTargetException());
        } catch (Exception e2) {
            return LuaValue.f("coercion error " + e2);
        }
    }
}
