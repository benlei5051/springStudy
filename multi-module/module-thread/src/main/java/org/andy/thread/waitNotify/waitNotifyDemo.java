package org.andy.thread.waitNotify;

/**
 * @author: andy
 * @Date: 2017/11/14 10:37
 * @Description:
 */
public class waitNotifyDemo {

    public static Object object = new Object();

    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        thread1.setName("aaa");
        Thread2 thread2 = new Thread2();
        thread2.setName("bbb");

        thread1.start();

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程");
        thread2.start();
    }

    static class Thread1 extends Thread{
        @Override
        public void run() {
            synchronized (object) {
                try {
                    System.out.println("-----------");
                    object.wait();
                } catch (InterruptedException e) {
                }
                System.out.println("线程"+Thread.currentThread().getName()+"获取到了锁");
            }
        }
    }

    static class Thread2 extends Thread{
        @Override
        public void run() {
            synchronized (object) {
                System.out.println("---");
                object.notify();
                System.out.println("线程"+Thread.currentThread().getName()+"调用了object.notify()");
            }
            System.out.println("线程"+Thread.currentThread().getName()+"释放了锁");
        }
    }
}
