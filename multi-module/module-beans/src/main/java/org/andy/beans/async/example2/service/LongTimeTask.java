/*------------------------------------------------------------------------------
 * LongTimeTask
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/11/21 11:25
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 *---------------------------------------------------------------------------*/
package org.andy.beans.async.example2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.TimeUnit;

//在我们是生产中，往往会遇到这样的情景，controller中调用的方法很多都是和第三方有关的，
//        例如JMS，定时任务，队列等，拿JMS来说，比如controller里面的服务需要从JMS中拿到返回值，
//        才能给客户端返回，而从JMS拿值这个过程也是异步的，这个时候，我们就可以通过Deferred来实现整个的异步调用。


@Component
public class LongTimeTask {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Async
    public void execute(DeferredResult<String> deferred){
        logger.info(Thread.currentThread().getName() + "进入 taskService 的 execute方法");
        try {
            // 模拟长时间任务调用
            TimeUnit.SECONDS.sleep(2);
            // 2s后给Deferred发送成功消息，告诉Deferred，我这边已经处理完了，可以返回给客户端了
            deferred.setResult("world");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}