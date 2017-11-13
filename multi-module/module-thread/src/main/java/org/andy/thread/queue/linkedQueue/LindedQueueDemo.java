package org.andy.thread.queue.linkedQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author: andy
 * @Date: 2017/11/2 17:17
 * @Description:
 */
// LinkedBlockingQueue阻塞队列大小的配置是可选的，
// 如果我们初始化时指定一个大小，它就是有边界的，
// 如果不指定，它就是无边界的。说是无边界，其实是采用了默认大小为Integer.MAX_VALUE的容量 。它的内部实现是一个链表。
// 和ArrayBlockingQueue一样，LinkedBlockingQueue 也是以先进先出的方式存储数据，最新插入的对象是尾部，最新移出的对象是头部。

public class LindedQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> bounded   = new LinkedBlockingQueue<String>(1024);
        bounded.put("Value");
        String value = bounded.take();
        System.out.println(value);
    }
}
