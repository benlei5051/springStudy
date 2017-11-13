package org.andy.thread.threadWait;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * @author: andy
 * @Date: 2017/9/7 11:02
 * @Description:
 */
public class CountDownLatchDemo {
    private static final Logger logger= LoggerFactory.getLogger(CountDownLatchDemo.class);
    private static final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws InterruptedException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CountDownLatch latch=new CountDownLatch(2);//两个工人的协作
        Worker worker1=new Worker("zhang san", 5000, latch);
        Worker worker2=new Worker("li si", 8000, latch);
        logger.info(latch.getCount()+"-------------线程数");
        worker1.start();//
        worker2.start();//
        latch.await();//等待所有工人完成工作
        logger.info("all work done at {}",sdf.format(new Date()));
    }


    static class Worker extends Thread{
        String workerName;
        int workTime;
        CountDownLatch latch;
        public Worker(String workerName ,int workTime ,CountDownLatch latch){
            this.workerName=workerName;
            this.workTime=workTime;
            this.latch=latch;
        }
        public void run(){
            System.out.println("Worker "+workerName+" do work begin at "+sdf.format(new Date()));
            doWork();//工作了
            System.out.println("Worker "+workerName+" do work complete at "+sdf.format(new Date()));
            latch.countDown();//工人完成工作，计数器减一


        }

        private void doWork(){
            try {
                Thread.sleep(workTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
