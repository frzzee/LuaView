package luaj.lib;

import luaj.Globals;
import luaj.LuaTable;
import luaj.LuaThread;
import luaj.LuaValue;
import luaj.ap;

public class CoroutineLib extends TwoArgFunction {
    static int coroutine_count = 0;
    Globals globals;

    final class create extends LibFunction {
        private final CoroutineLib this$0;

        public create(CoroutineLib coroutineLib) {
            this.this$0 = coroutineLib;
        }

        static CoroutineLib access$0(create create) {
            return create.this$0;
        }

        public LuaValue a(LuaValue luaValue) {
            return new LuaThread(this.this$0.globals, luaValue.C());
        }
    }

    static final class resume extends VarArgFunction {
        public ap a_(ap apVar) {
            return apVar.u(1).resume(apVar.e_(2));
        }
    }

    final class running extends VarArgFunction {
        private final CoroutineLib this$0;

        public running(CoroutineLib coroutineLib) {
            this.this$0 = coroutineLib;
        }

        static CoroutineLib access$0(running running) {
            return running.this$0;
        }

        public ap a_(ap apVar) {
            LuaThread luaThread = this.this$0.globals.h;
            return LuaValue.b(luaThread, LuaValue.b(luaThread.isMainThread()));
        }
    }

    static final class status extends LibFunction {
        public LuaValue a(LuaValue luaValue) {
            return LuaValue.m(luaValue.R().getStatus());
        }
    }

    final class wrap extends LibFunction {
        private final CoroutineLib this$0;

        public wrap(CoroutineLib coroutineLib) {
            this.this$0 = coroutineLib;
        }

        static CoroutineLib access$0(wrap wrap) {
            return wrap.this$0;
        }

        public LuaValue a(LuaValue luaValue) {
            return new wrapper(new LuaThread(this.this$0.globals, luaValue.C()));
        }
    }

    static final class wrapper extends VarArgFunction {
        final LuaThread luathread;

        wrapper(LuaThread luaThread) {
            this.luathread = luaThread;
        }

        public ap a_(ap apVar) {
            ap resume = this.luathread.resume(apVar);
            return resume.g().i_() ? resume.e_(2) : LuaValue.f(resume.c(2).d_());
        }
    }

    final class yield extends VarArgFunction {
        private final CoroutineLib this$0;

        public yield(CoroutineLib coroutineLib) {
            this.this$0 = coroutineLib;
        }

        static CoroutineLib access$0(yield yield) {
            return yield.this$0;
        }

        public ap a_(ap apVar) {
            return this.this$0.globals.yield(apVar);
        }
    }

    public LuaValue a(LuaValue luaValue, LuaValue luaValue2) {
        this.globals = luaValue2.c();
        LuaTable luaTable = new LuaTable();
        luaTable.a("create", new create(this));
        luaTable.a("resume", new resume());
        luaTable.a("running", new running(this));
        luaTable.a("status", new status());
        luaTable.a("yield", new yield(this));
        luaTable.a("wrap", new wrap(this));
        luaValue2.a("coroutine", luaTable);
        if (!luaValue2.j("package").F()) {
            luaValue2.j("package").j("loaded").a("coroutine", luaTable);
        }
        return luaTable;
    }
}
