package org.andy.thread.queue.arrayQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author: andy
 * @Date: 2017/11/2 16:56
 * @Description:
 */
public class ArrayBlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        arrayBlockingQueue();
    }

    //ArrayBlockingQueue是一个有边界的阻塞队列，它的内部实现是一个数组。
    // 有边界的意思是它的容量是有限的，我们必须在其初始化的时候指定它的容量大小，容量大小一旦指定就不可改变。
    public static void arrayBlockingQueue() throws InterruptedException {
        BlockingQueue<String> queue = new ArrayBlockingQueue(1024);
        queue.put("1");
        String object = queue.take();
        System.out.println(object);

    }
}
