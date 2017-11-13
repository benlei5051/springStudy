package org.andy.thread.queue.PriorityQueue;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author: andy
 * @Date: 2017/11/2 17:29
 * @Description:
 */

// PriorityBlockingQueue是一个没有边界的队列，是一个带优先级的 队列，而不是先进先出队列。
// 它的排序规则和 java.util.PriorityQueue一样。
// 需要注意，PriorityBlockingQueue中允许插入null对象。
// 所有插入PriorityBlockingQueue的对象必须实现 java.lang.Comparable接口，队列优先级的排序规则就是按照我们对这个接口的实现来定义的。
// 另外，我们可以从PriorityBlockingQueue获得一个迭代器Iterator，但这个迭代器并不保证按照优先级顺序进行迭代。

public class PriorityQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        PriorityBlockingQueue<PriorityElement> queue = new PriorityBlockingQueue<>();
        for (int i = 0; i < 5; i++) {
            Random random=new Random();
            PriorityElement ele = new PriorityElement(random.nextInt(10));
            queue.put(ele);
        }
        while(!queue.isEmpty()){
            System.out.println(queue.take());
        }
    }
}
