package luaj;

import android.util.Log;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ExeCommand {
    /* access modifiers changed from: private */
    public boolean bRunning;
    private boolean bSynchronous;
    /* access modifiers changed from: private */
    public BufferedReader errorResult;
    ReadWriteLock lock;
    private DataOutputStream os;
    /* access modifiers changed from: private */
    public Process process;
    /* access modifiers changed from: private */
    public StringBuffer result;
    /* access modifiers changed from: private */
    public BufferedReader successResult;

    public ExeCommand(boolean synchronous) {
        this.bRunning = false;
        this.lock = new ReentrantReadWriteLock();
        this.result = new StringBuffer();
        this.bSynchronous = synchronous;
    }

    public ExeCommand() {
        this.bRunning = false;
        this.lock = new ReentrantReadWriteLock();
        this.result = new StringBuffer();
        this.bSynchronous = true;
    }

    public boolean isRunning() {
        return this.bRunning;
    }

    public String getResult() {
        Lock readLock = this.lock.readLock();
        readLock.lock();
        try {
            Log.i("auto", "getResult");
            return new String(this.result);
        } finally {
            readLock.unlock();
        }
    }

    public ExeCommand run(String command, final int maxTime) {
        Log.i("auto", "run command:" + command + ",maxtime:" + maxTime);
        if (!(command == null || command.length() == 0)) {
            try {
                this.process = Runtime.getRuntime().exec("sh");
                this.bRunning = true;
                this.successResult = new BufferedReader(new InputStreamReader(this.process.getInputStream()));
                this.errorResult = new BufferedReader(new InputStreamReader(this.process.getErrorStream()));
                this.os = new DataOutputStream(this.process.getOutputStream());
                try {
                    this.os.write(command.getBytes());
                    this.os.writeBytes("\n");
                    this.os.flush();
                    this.os.writeBytes("exit\n");
                    this.os.flush();
                    this.os.close();
                    if (maxTime > 0) {
                        new Thread(new Runnable() {
                            public void run() {
                                try {
                                    Thread.sleep((long) maxTime);
                                } catch (Exception e) {
                                }
                                try {
                                    Log.i("auto", "exitValue Stream over" + ExeCommand.this.process.exitValue());
                                } catch (IllegalThreadStateException e2) {
                                    Log.i("auto", "take maxTime,forced to destroy process");
                                    ExeCommand.this.process.destroy();
                                }
                            }
                        }).start();
                    }
                    final Thread t1 = new Thread(new Runnable() {
                        public void run() {
                            Lock writeLock = ExeCommand.this.lock.writeLock();
                            while (true) {
                                try {
                                    String line = ExeCommand.this.successResult.readLine();
                                    if (line != null) {
                                        writeLock.lock();
                                        ExeCommand.this.result.append(line + "\n");
                                        writeLock.unlock();
                                    } else {
                                        try {
                                            ExeCommand.this.successResult.close();
                                            Log.i("auto", "read InputStream over");
                                            return;
                                        } catch (Exception e) {
                                            Log.i("auto", "close InputStream exception:" + e.toString());
                                            return;
                                        }
                                    }
                                } catch (Exception e2) {
                                    Log.i("auto", "read InputStream exception:" + e2.toString());
                                    try {
                                        ExeCommand.this.successResult.close();
                                        Log.i("auto", "read InputStream over");
                                        return;
                                    } catch (Exception e3) {
                                        Log.i("auto", "close InputStream exception:" + e3.toString());
                                        return;
                                    }
                                } catch (Throwable th) {
                                    try {
                                        ExeCommand.this.successResult.close();
                                        Log.i("auto", "read InputStream over");
                                    } catch (Exception e4) {
                                        Log.i("auto", "close InputStream exception:" + e4.toString());
                                    }
                                    throw th;
                                }
                            }
                        }
                    });
                    t1.start();
                    final Thread t2 = new Thread(new Runnable() {
                        public void run() {
                            Lock writeLock = ExeCommand.this.lock.writeLock();
                            while (true) {
                                try {
                                    String line = ExeCommand.this.errorResult.readLine();
                                    if (line != null) {
                                        writeLock.lock();
                                        ExeCommand.this.result.append(line + "\n");
                                        writeLock.unlock();
                                    } else {
                                        try {
                                            ExeCommand.this.errorResult.close();
                                            Log.i("auto", "read ErrorStream over");
                                            return;
                                        } catch (Exception e) {
                                            Log.i("auto", "read ErrorStream exception:" + e.toString());
                                            return;
                                        }
                                    }
                                } catch (Exception e2) {
                                    Log.i("auto", "read ErrorStream exception:" + e2.toString());
                                    try {
                                        ExeCommand.this.errorResult.close();
                                        Log.i("auto", "read ErrorStream over");
                                        return;
                                    } catch (Exception e3) {
                                        Log.i("auto", "read ErrorStream exception:" + e3.toString());
                                        return;
                                    }
                                } catch (Throwable th) {
                                    try {
                                        ExeCommand.this.errorResult.close();
                                        Log.i("auto", "read ErrorStream over");
                                    } catch (Exception e4) {
                                        Log.i("auto", "read ErrorStream exception:" + e4.toString());
                                    }
                                    throw th;
                                }
                            }
                        }
                    });
                    t2.start();
                    Thread t3 = new Thread(new Runnable() {
                        public void run() {
                            String str;
                            String str2;
                            try {
                                t1.join();
                                t2.join();
                                ExeCommand.this.process.waitFor();
                            } catch (Exception e) {
                            } finally {
                                boolean unused = ExeCommand.this.bRunning = false;
                                str = "auto";
                                str2 = "run command process end";
                                Log.i(str, str2);
                            }
                        }
                    });
                    t3.start();
                    if (this.bSynchronous) {
                        Log.i("auto", "run is go to end");
                        t3.join();
                        Log.i("auto", "run is end");
                    }
                } catch (Exception e) {
                    Log.i("auto", "run command process exception:" + e.toString());
                }
            } catch (Exception e2) {
            }
        }
        return this;
    }
}
