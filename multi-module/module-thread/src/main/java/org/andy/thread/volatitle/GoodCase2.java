package org.andy.thread.volatitle;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: andy
 * @Date: 2017/11/14 11:35
 * @Description:
 */
public class GoodCase2 {
    static AtomicInteger count=new AtomicInteger(0);
    public static class AddThread implements Runnable{
        @Override
        public void run() {
                count.getAndIncrement();
        }
    }
    public static void AtomicIntShow(){
        System.out.println("AtomicIntShow() enter");
        ExecutorService threadpool =   Executors.newFixedThreadPool(10);

        for(int k=0;k<100;k++){
            threadpool.submit(new AddThread());
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

         /* output
          * AtomicIntShow() enter
          * result of acumulated sum=100000
          * AtomicIntShow() exit
          */

        System.out.println("result of acumulated sum="+count);
        threadpool.shutdown();
        System.out.println("AtomicIntShow() exit");
        return ;

    }
    public static void main(String[] args){
        GoodCase2.AtomicIntShow();
    }
}
