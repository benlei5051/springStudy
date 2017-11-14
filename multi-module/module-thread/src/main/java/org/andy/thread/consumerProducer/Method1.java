package org.andy.thread.consumerProducer;

import java.util.PriorityQueue;

/**
 * @author: andy
 * @Date: 2017/11/14 14:34
 * @Description:
 *
 * 使用object对象中的notify和wait方法
 */
public class Method1 {
    private int queueSize = 10;
    //初始值，当queue的容量大于10时，会自动增加容积，PriorityQueue里面的队列值会自动排序
    public PriorityQueue<Integer> queue = new PriorityQueue<Integer>(queueSize);

    public static void main(String[] args) {
        Method1 test = new Method1();

        Producer producer = test.new Producer();
        Consumer consumer = test.new Consumer();
        producer.start();
        consumer.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*Iterator it =test.queue.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
        for(int i=0;i<test.queue.size();i++){   //取一个值，size就会减少一个值，故不能用
            System.out.println(test.queue.poll());
        }*/


    }

    class Consumer extends Thread {
        @Override
        public void run() {
            consume();
        }

        private void consume() {
            while (true) {
                synchronized (queue) {
                    while (queue.size() == 0) {
                        try {
                            System.out.println("队为空,等待数据");
                            queue.wait();
                        } catch (InterruptedException e) {
                            queue.notify();
                        }
                    }
                    queue.poll();  //每次移走队首元素
                    queue.notify();
                    System.out.println("从队列取走一个元素，队列剩余:" + queue.size() + "个元素");
                }
            }
        }
    }

    class Producer extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    if (queue.size() == queueSize) {
                        try {
                            System.out.println("队列满,等待有空余空间");
                            queue.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    queue.offer(1);
                    queue.notify();
                    System.out.println("向队列中插入一个元素，队列剩余空间:" + (queueSize - queue.size()));
                }
            }
        }
    }
}
