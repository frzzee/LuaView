package luaj.lib.jse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class JseProcess {
    final Thread error;
    final Thread input;
    final Thread output;
    final Process process;

    private static final class CopyThread extends Thread {
        private final InputStream input;
        private final OutputStream output;
        private final InputStream ownedInput;
        private final OutputStream ownedOutput;

        public CopyThread(OutputStream outputStream, OutputStream outputStream2, InputStream inputStream, InputStream inputStream2) {
            this.output = outputStream;
            this.ownedOutput = outputStream2;
            this.ownedInput = inputStream;
            this.input = inputStream2;
        }

        public void run() {
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = this.input.read(bArr);
                    if (read < 0) {
                        break;
                    }
                    this.output.write(bArr, 0, read);
                }
                InputStream inputStream = this.ownedInput;
                if (inputStream != null) {
                    inputStream.close();
                }
                OutputStream outputStream = this.ownedOutput;
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Throwable th) {
                InputStream inputStream2 = this.ownedInput;
                if (inputStream2 != null) {
                    inputStream2.close();
                }
                OutputStream outputStream2 = this.ownedOutput;
                if (outputStream2 != null) {
                    outputStream2.close();
                }
                throw th;
            }
        }
    }

    private JseProcess(Process process2, InputStream inputStream, OutputStream outputStream, OutputStream outputStream2) {
        this.process = process2;
        Thread thread = null;
        this.input = inputStream == null ? null : copyBytes(inputStream, process2.getOutputStream(), (InputStream) null, process2.getOutputStream());
        this.output = outputStream == null ? null : copyBytes(process2.getInputStream(), outputStream, process2.getInputStream(), (OutputStream) null);
        this.error = outputStream2 != null ? copyBytes(process2.getErrorStream(), outputStream2, process2.getErrorStream(), (OutputStream) null) : thread;
    }

    public JseProcess(String str, InputStream inputStream, OutputStream outputStream, OutputStream outputStream2) throws IOException {
        this(Runtime.getRuntime().exec(str), inputStream, outputStream, outputStream2);
    }

    public JseProcess(String[] strArr, InputStream inputStream, OutputStream outputStream, OutputStream outputStream2) throws IOException {
        this(Runtime.getRuntime().exec(strArr), inputStream, outputStream, outputStream2);
    }

    private Thread copyBytes(InputStream inputStream, OutputStream outputStream, InputStream inputStream2, OutputStream outputStream2) {
        CopyThread copyThread = new CopyThread(outputStream, outputStream2, inputStream2, inputStream);
        copyThread.start();
        return copyThread;
    }

    public int exitValue() {
        return this.process.exitValue();
    }

    public int waitFor() throws InterruptedException {
        int waitFor = this.process.waitFor();
        Thread thread = this.input;
        if (thread != null) {
            thread.join();
        }
        Thread thread2 = this.output;
        if (thread2 != null) {
            thread2.join();
        }
        Thread thread3 = this.error;
        if (thread3 != null) {
            thread3.join();
        }
        this.process.destroy();
        return waitFor;
    }
}
