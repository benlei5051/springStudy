/*------------------------------------------------------------------------------
 * ThreadPoolTaskTest
 * @Description: 数据库锁---乐观锁    悲观锁是  select * from status where id = 1 for update;
 * 悲观锁定：对并发冲突采取一种悲观的态度,认为相关业务的并发冲突度高，把本事务拟将更新的行在查询时就加锁，从而阻止其它事务更新这些行；
 *  在SELECT语句后加上FOR UPDATE
 * 也可以在select子句后用for update nowait
 *
 *
 *
 * @author: haolin@pateo.com.cn
 * @date: 2018/12/14 10:17
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 *---------------------------------------------------------------------------*/
package org.andy.database.lock.example4;

import org.andy.database.lock.example4.service.StatusRepositoryManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;



@RunWith(SpringRunner.class)
@SpringBootTest
public class ThreadPoolTaskTest {

    @Autowired
    private StatusRepositoryManager manager;

    @Test
    public void test() throws InterruptedException{
        ExecutorService pools = Executors.newFixedThreadPool(10);
        final CountDownLatch latch=new CountDownLatch(1);
        for(int i=0; i<5; i++){
            pools.submit(new Runnable() {

                @Override
                public void run() {
                    try {
                        //一直阻塞当前线程，直到计时器的值为0
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Boolean lock = manager.updateByVersion();
                    if(lock){
                        System.out.println(Thread.currentThread().getName()+"获取到锁了!");
                        System.out.println("执行任务!");
                    }else{
                        System.out.println(Thread.currentThread().getName()+"没有获取到锁，不执行任务!");
                    }
                }
            });
        }
        latch.countDown();
        pools.shutdown();
        TimeUnit.SECONDS.sleep(30);
    }
}