package luaj.lib.jse;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import luaj.ap;
import luaj.lib.OsLib;

public class JseOsLib extends OsLib {
    public static final int EXEC_ERROR = -3;
    public static final int EXEC_INTERRUPTED = -2;
    public static final int EXEC_IOEXCEPTION = 1;

    /* access modifiers changed from: protected */
    public String T() {
        try {
            return File.createTempFile(".gg.", ".tmp").getAbsolutePath();
        } catch (IOException unused) {
            return JseOsLib.super.T();
        }
    }

    /* access modifiers changed from: protected */
    public void a(String str) throws IOException {
        File file = new File(str);
        if (!file.exists()) {
            throw new IOException("No such file or directory");
        } else if (!file.delete()) {
            throw new IOException("Failed to delete");
        }
    }

    /* access modifiers changed from: protected */
    public void a(String str, String str2) throws IOException {
        File file = new File(str);
        if (!file.exists()) {
            throw new IOException("No such file or directory");
        } else if (!file.renameTo(new File(str2))) {
            throw new IOException("Failed to rename");
        }
    }

    /* access modifiers changed from: protected */
    public ap c(String str) {
        int i;
        try {
            i = new JseProcess(str, (InputStream) null, (OutputStream) this.c.e, (OutputStream) this.c.f).waitFor();
        } catch (IOException unused) {
            i = 1;
        } catch (InterruptedException unused2) {
            i = -2;
        } catch (Throwable unused3) {
            i = -3;
        }
        return i == 0 ? a(v, m("exit"), y) : a(u, m("signal"), d((long) i));
    }

    /* access modifiers changed from: protected */
    public String d(String str) {
        String str2 = System.getenv(str);
        return str2 != null ? str2 : System.getProperty(str);
    }
}
