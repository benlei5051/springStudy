package org.andy.thread.tickets;

/**
 * @author: andy
 * @Date: 2017/11/14 16:14
 * @Description:
 */

class MyThread implements Runnable {
    int ticketCon = 1000;
    boolean flag=true;
    @Override
    public void run() {
        while(flag){
            synchronized (this) {
                if (ticketCon > 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ticketCon--;
                    System.out.println(Thread.currentThread().getName() + "卖了1张票，剩余票数为:" + ticketCon);
                }else{
                    flag=false;
                }
            }
        }


    }

}

public class TicketsRunnable {

    public static void main(String[] args) {
        MyThread mt = new MyThread();
        Thread th1 = new Thread(mt, "窗口1");
        Thread th2 = new Thread(mt, "窗口2");
        Thread th3 = new Thread(mt, "窗口3");
       // th3.setPriority(Thread.MAX_PRIORITY);   //线程优先级
        th2.start();
        th1.start();
        th3.start();

    }

}

