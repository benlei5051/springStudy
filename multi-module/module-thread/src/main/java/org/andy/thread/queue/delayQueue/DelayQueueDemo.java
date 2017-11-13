package org.andy.thread.queue.delayQueue;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: andy
 * @Date: 2017/11/2 17:06
 * @Description:
 */
public class DelayQueueDemo {
    static final int STUDENT_SIZE = 30;
    public static void main(String[] args) throws InterruptedException {
        Random r = new Random();
        //把所有学生看做一个延迟队列
        DelayQueue<Student> students = new DelayQueue<Student>();
        //构造一个线程池用来让学生们“做作业”
        ExecutorService exec = Executors.newFixedThreadPool(STUDENT_SIZE);
        for ( int i = 0; i < STUDENT_SIZE; i++) {
            //初始化学生的姓名和做题时间
            students.put( new Student( "学生" + (i + 1), 3000 + r.nextInt(10000)));
        }
        //开始做题
        while(! students.isEmpty()){
            exec.execute( students.take());
        }
        exec.shutdown();
    }
}
