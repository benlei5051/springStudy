package org.andy.thread.threadLocal;

/**
 * @author: andy
 * @Date: 2017/11/14 10:55
 * @Description:
 *
 * ThreadLocal是解决线程安全问题一个很好的思路，
 * 它通过为每个线程提供一个独立的变量副本解决了变量并发访问的冲突问题。
 * 在很多情况下，ThreadLocal比直接使用synchronized同步机制解决线程安全问题更简单，
 * 更方便，且结果程序拥有更高的并发性。
 * ThreadLocal声明为static，（被static声明的变量，原本在内存中保存一份），也会为各个线程间提供独立的变量副本
 *
 * @author jh
 *
 */
public class ThreadLocalDemo {
    private static ThreadLocal<Long> longLocal = new ThreadLocal<Long>();   //只会绑定当前线程的值，线程之间互不干扰
    private static ThreadLocal<String> stringLocal = new ThreadLocal<String>();


    public void set() {
        longLocal.set(Thread.currentThread().getId());
        stringLocal.set(Thread.currentThread().getName());
    }

    public long getLong() {
        return longLocal.get();
    }

    public String getString() {
        return stringLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
        final ThreadLocalDemo test = new ThreadLocalDemo();

        test.set();//如果没有先set，程序会直接报错
        System.out.println(test.getLong());
        System.out.println(test.getString());


        Thread thread1 = new Thread(){
            public void run() {
                test.set();
                System.out.println(ThreadLocalDemo.longLocal.get());
                System.out.println(ThreadLocalDemo.stringLocal.get());
//                System.out.println(test.getLong());
//                System.out.println(test.getString());
            };
        };
        thread1.start();
        thread1.join();

//        System.out.println(test.getLong());
        System.out.println(ThreadLocalDemo.longLocal.get());
        System.out.println(ThreadLocalDemo.stringLocal.get());
//        System.out.println(test.getString());
    }
}
