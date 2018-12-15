package org.andy.beans.async.example4.service.impl;

import org.andy.beans.async.example4.service.DemoAsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class DemoAsyncServiceImpl implements DemoAsyncService {
    private static final Logger logger = LoggerFactory.getLogger(DemoAsyncServiceImpl.class);

    /**
     * myTaskAsynPool即配置线程池的方法名，
     * 此处如果不写自定义线程池的方法名，会使用默认的线程池
     *
     * @return
     * @throws Exception
     */
    @Override
//    @Async("myTaskAsyncPool")
    @Async
    public void doTaskFour(int i) {
        logger.info("任务" + i + "开始执行");
        int a = 1 / 0;
        System.out.println(a);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("任务" + i + "执行完毕");
    }
}
