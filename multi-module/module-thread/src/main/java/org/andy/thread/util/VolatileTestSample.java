package org.andy.thread.util;

import org.andy.thread.volatitle.GoodCase;

import java.util.Date;

/**
 *
 * volatile  实用例子
 * @author: haolin@pateo.com.cn
 * @date: 2018/9/19 14:24
 * @version: V1.0
 */
public class VolatileTestSample {
    /**
     * 非volatile变量，当前线程如果不刷新句柄，则永远不可见
     * 也就是说，o的句柄一直是在Cache中
     */
    private Object o = null;

    /**
     * 当变量为volatile时，另一根线程对其改动，会立即可见
     */
//    private volatile Object o = null;

    private boolean flag = false;

    public void methodA() {
        while (true) {
            if (o != null && flag == false) {
                System.out.println("Handler o detected changed."+new Date());
                flag = true;
                System.exit(0);
            }
        }
    }

    public void methodB() {
        o = new Object();
        System.out.println("Handler o changed!"+new Date());
    }

    public static void main(String[] args) {
        System.out.println("当前时间是："+new Date());
        VolatileTestSample volatileTestSample = new VolatileTestSample();
        Thread threadHandlerListener = new Thread(new Runnable() {
            @Override
            public void run() {
                volatileTestSample.methodA();
            }
        });
        threadHandlerListener.start();
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        volatileTestSample.methodB();
    }
}
