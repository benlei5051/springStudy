package org.andy.thread.threadLocal;

/**
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/8/15 13:42
 * @version: V1.0
 */
public class MyRunnable implements Runnable{
    private String threadLocal;
    @Override
    public void run() {
        System.out.println(threadLocal);
    }

    public String getThreadLocal() {
        return threadLocal;
    }

    public void setThreadLocal(String threadLocal) {
        this.threadLocal = threadLocal;
    }
}
