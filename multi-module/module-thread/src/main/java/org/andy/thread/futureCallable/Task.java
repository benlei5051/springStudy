package org.andy.thread.futureCallable;

import java.util.concurrent.Callable;

/**
 * @author: andy
 * @Date: 2017/11/13 16:50
 * @Description:
 */
public class Task implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("子线程在进行计算");
        Thread.sleep(3000);
        int sum = 0;
        for (int i = 0; i < 100; i++)
            sum += i;
        return sum;
    }
}
