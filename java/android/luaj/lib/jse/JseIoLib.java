package luaj.lib.jse;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import luaj.LuaString;
import luaj.lib.IoLib;
import luaj.o;

public class JseIoLib extends IoLib {

    private final class FileImpl extends IoLib.File {
        private boolean closed;
        private final RandomAccessFile file;
        private final InputStream is;
        private boolean nobuffer;
        private final OutputStream os;

        public FileImpl(JseIoLib jseIoLib, InputStream inputStream) {
            this((RandomAccessFile) null, inputStream, (OutputStream) null);
        }

        public FileImpl(JseIoLib jseIoLib, OutputStream outputStream) {
            this((RandomAccessFile) null, (InputStream) null, outputStream);
        }

        public FileImpl(JseIoLib jseIoLib, RandomAccessFile randomAccessFile) {
            this(randomAccessFile, (InputStream) null, (OutputStream) null);
        }

        public FileImpl(RandomAccessFile randomAccessFile, InputStream inputStream, OutputStream outputStream) {
            super(JseIoLib.this);
            this.closed = false;
            this.nobuffer = false;
            this.file = randomAccessFile;
            if (inputStream == null) {
                inputStream = null;
            } else if (!inputStream.markSupported()) {
                inputStream = new BufferedInputStream(inputStream);
            }
            this.is = inputStream;
            this.os = outputStream;
        }

        public void S() throws IOException {
            OutputStream outputStream = this.os;
            if (outputStream != null) {
                outputStream.flush();
            }
        }

        public boolean T() {
            return this.file == null;
        }

        public void U() throws IOException {
            this.closed = true;
            RandomAccessFile randomAccessFile = this.file;
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
        }

        public boolean V() {
            return this.closed;
        }

        public int W() throws IOException {
            RandomAccessFile randomAccessFile = this.file;
            if (randomAccessFile != null) {
                return (int) (randomAccessFile.length() - this.file.getFilePointer());
            }
            return -1;
        }

        public int X() throws IOException {
            InputStream inputStream = this.is;
            if (inputStream != null) {
                inputStream.mark(1);
                int read = this.is.read();
                this.is.reset();
                return read;
            }
            RandomAccessFile randomAccessFile = this.file;
            if (randomAccessFile != null) {
                long filePointer = randomAccessFile.getFilePointer();
                int read2 = this.file.read();
                this.file.seek(filePointer);
                return read2;
            }
            JseIoLib.notimplemented();
            return 0;
        }

        public int a(byte[] bArr, int i, int i2) throws IOException {
            RandomAccessFile randomAccessFile = this.file;
            if (randomAccessFile != null) {
                return randomAccessFile.read(bArr, i, i2);
            }
            InputStream inputStream = this.is;
            if (inputStream != null) {
                return inputStream.read(bArr, i, i2);
            }
            JseIoLib.notimplemented();
            return i2;
        }

        public int af() throws IOException {
            InputStream inputStream = this.is;
            if (inputStream != null) {
                return inputStream.read();
            }
            RandomAccessFile randomAccessFile = this.file;
            if (randomAccessFile != null) {
                return randomAccessFile.read();
            }
            JseIoLib.notimplemented();
            return 0;
        }

        public int c(String str, int i) throws IOException {
            if (this.file != null) {
                if ("set".equals(str)) {
                    this.file.seek((long) i);
                } else if ("end".equals(str)) {
                    RandomAccessFile randomAccessFile = this.file;
                    randomAccessFile.seek(randomAccessFile.length() + ((long) i));
                } else {
                    RandomAccessFile randomAccessFile2 = this.file;
                    randomAccessFile2.seek(randomAccessFile2.getFilePointer() + ((long) i));
                }
                return (int) this.file.getFilePointer();
            }
            JseIoLib.notimplemented();
            return 0;
        }

        public void d(String str, int i) {
            this.nobuffer = "no".equals(str);
        }

        public String d_() {
            StringBuilder sb = new StringBuilder();
            sb.append("file (");
            sb.append(this.closed ? "closed" : String.valueOf(hashCode()));
            sb.append(")");
            return sb.toString();
        }

        public void e(LuaString luaString) throws IOException {
            OutputStream outputStream = this.os;
            if (outputStream != null) {
                outputStream.write(luaString.b, luaString.c, luaString.d);
            } else {
                RandomAccessFile randomAccessFile = this.file;
                if (randomAccessFile != null) {
                    randomAccessFile.write(luaString.b, luaString.c, luaString.d);
                } else {
                    JseIoLib.notimplemented();
                }
            }
            if (this.nobuffer) {
                S();
            }
        }
    }

    private final class StdinFile extends IoLib.File {
        public StdinFile() {
            super(JseIoLib.this);
        }

        public void S() throws IOException {
        }

        public boolean T() {
            return true;
        }

        public void U() throws IOException {
        }

        public boolean V() {
            return false;
        }

        public int W() throws IOException {
            return -1;
        }

        public int X() throws IOException, EOFException {
            JseIoLib.this.e.STDIN.mark(1);
            int read = JseIoLib.this.e.STDIN.read();
            JseIoLib.this.e.STDIN.reset();
            return read;
        }

        public int a(byte[] bArr, int i, int i2) throws IOException {
            return JseIoLib.this.e.STDIN.read(bArr, i, i2);
        }

        public int af() throws IOException, EOFException {
            return JseIoLib.this.e.STDIN.read();
        }

        public int c(String str, int i) throws IOException {
            return 0;
        }

        public void d(String str, int i) {
        }

        public String d_() {
            return "file (" + hashCode() + ")";
        }

        public void e(LuaString luaString) throws IOException {
        }
    }

    private final class StdoutFile extends IoLib.File {
        private final int file_type;

        public StdoutFile(int i) {
            super(JseIoLib.this);
            this.file_type = i;
        }

        private final PrintStream ah() {
            return this.file_type == 2 ? JseIoLib.this.e.f : JseIoLib.this.e.e;
        }

        public void S() throws IOException {
            ah().flush();
        }

        public boolean T() {
            return true;
        }

        public void U() throws IOException {
        }

        public boolean V() {
            return false;
        }

        public int W() throws IOException {
            return 0;
        }

        public int X() throws IOException, EOFException {
            return 0;
        }

        public int a(byte[] bArr, int i, int i2) throws IOException {
            return 0;
        }

        public int af() throws IOException, EOFException {
            return 0;
        }

        public int c(String str, int i) throws IOException {
            return 0;
        }

        public void d(String str, int i) {
        }

        public String d_() {
            return "file (" + hashCode() + ")";
        }

        public void e(LuaString luaString) throws IOException {
            ah().write(luaString.b, luaString.c, luaString.d);
        }
    }

    /* access modifiers changed from: private */
    public static void notimplemented() {
        throw new o("not implemented");
    }

    /* access modifiers changed from: protected */
    public IoLib.File U() throws IOException {
        return new StdoutFile(1);
    }

    /* access modifiers changed from: protected */
    public IoLib.File V() throws IOException {
        return new StdoutFile(2);
    }

    /* access modifiers changed from: protected */
    public IoLib.File W() throws IOException {
        File createTempFile = File.createTempFile(".luaj", "bin");
        createTempFile.deleteOnExit();
        return new FileImpl(this, new RandomAccessFile(createTempFile, "rw"));
    }

    /* access modifiers changed from: protected */
    public IoLib.File openFile(String str, boolean z, boolean z2, boolean z3, boolean z4) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(str, z ? "r" : "rw");
        if (z2) {
            randomAccessFile.seek(randomAccessFile.length());
        } else if (!z) {
            randomAccessFile.setLength(0);
        }
        return new FileImpl(this, randomAccessFile);
    }

    /* access modifiers changed from: protected */
    public IoLib.File openProgram(String str, String str2) throws IOException {
        Process exec = Runtime.getRuntime().exec(str);
        return "w".equals(str2) ? new FileImpl(this, exec.getOutputStream()) : new FileImpl(this, exec.getInputStream());
    }

    /* access modifiers changed from: protected */
    public IoLib.File wrapStdin() throws IOException {
        return new StdinFile();
    }
}
