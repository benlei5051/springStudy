package org.andy.thread.threadPool.sample;

/**
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/8/16 16:06
 * @version: V1.0
 */
public class MyThread extends Thread {
    /*@Override
    public void run() {
        for (int x = 0; x < 100; x++) {
            System.out.println(Thread.currentThread().getName() + ":" + x);
        }
    }*/
    private int taskNum;
    public MyThread() {

    }

    public MyThread(int num) {
        this.taskNum = num;
    }

    @Override
    public void run() {
        System.out.println("正在执行task "+taskNum);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task "+taskNum+"执行完毕");
    }
}
