package luaj;

import android.content.Context;
import android.ext.Script;
import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import luaj.lib.ResourceFinder;
import luaj.lib.jse.CoerceJavaToLua;

public class LuaView extends View implements ResourceFinder {
    public final Globals globals = Script.instanceGlobals;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LuaView(Context context) {
        super(context);
        this.globals.g = this;
    }

    public InputStream c(String str) {
        InputStream inputStream;
        File file;
        String str2 = str;
        try {
            return getContext().getAssets().open(str2);
        } catch (IOException e) {
            IOException iOException = e;
            try {
                return (InputStream) getContext().getAssets().openXmlResourceParser(str2);
            } catch (Exception e2) {
                Exception exc = e2;
                try {
                    InputStream inputStream2 = inputStream;
                    new File(str2);
                    new FileInputStream(file);
                    return inputStream2;
                } catch (Exception e3) {
                    Exception exc2 = e3;
                    return null;
                }
            }
        }
    }

    public InputStream findResource(int i) {
        try {
            return getContext().getResources().openRawResource(i);
        } catch (Exception e) {
            Exception exc = e;
            return null;
        }
    }

    public void draw(Canvas canvas) {
        Canvas canvas2 = canvas;
        LuaValue j = this.globals.j("onDraw");
        if (!j.F()) {
            try {
                LuaValue a = j.a(CoerceJavaToLua.coerce(canvas2));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            super.draw(canvas2);
        }
    }

    public boolean f(int i, KeyEvent keyEvent) {
        Object obj;
        int i2 = i;
        KeyEvent keyEvent2 = keyEvent;
        LuaValue j = this.globals.j("onKeyDown");
        if (j.F()) {
            return super.onKeyDown(i2, keyEvent2);
        }
        LuaValue luaValue = j;
        try {
            new Integer(i2);
            return luaValue.a(CoerceJavaToLua.coerce(obj), CoerceJavaToLua.coerce(keyEvent2)).i_();
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        Object obj;
        int i2 = i;
        KeyEvent keyEvent2 = keyEvent;
        LuaValue j = this.globals.j("onKeyUp");
        if (j.F()) {
            return super.onKeyUp(i2, keyEvent2);
        }
        LuaValue luaValue = j;
        try {
            new Integer(i2);
            return luaValue.a(CoerceJavaToLua.coerce(obj), CoerceJavaToLua.coerce(keyEvent2)).i_();
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        MotionEvent motionEvent2 = motionEvent;
        LuaValue j = this.globals.j("onTouchEvent");
        if (j.F()) {
            return super.onTouchEvent(motionEvent2);
        }
        try {
            return j.a(CoerceJavaToLua.coerce(motionEvent2)).i_();
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public boolean onTrackballEvent(MotionEvent motionEvent) {
        MotionEvent motionEvent2 = motionEvent;
        LuaValue j = this.globals.j("onTrackballEvent");
        if (j.F()) {
            return super.onTrackballEvent(motionEvent2);
        }
        try {
            return j.a(CoerceJavaToLua.coerce(motionEvent2)).i_();
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public void onWindowFocusChanged(boolean z) {
        Object obj;
        boolean z2 = z;
        LuaValue j = this.globals.j("onWindowFocusChanged");
        if (!j.F()) {
            LuaValue luaValue = j;
            try {
                new Boolean(z2);
                LuaValue a = luaValue.a(CoerceJavaToLua.coerce(obj));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onWindowSystemUiVisibilityChanged(int i) {
        Object obj;
        int i2 = i;
        LuaValue j = this.globals.j("onWindowSystemUiVisibilityChanged");
        if (!j.F()) {
            LuaValue luaValue = j;
            try {
                new Integer(i2);
                LuaValue a = luaValue.a(CoerceJavaToLua.coerce(obj));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
