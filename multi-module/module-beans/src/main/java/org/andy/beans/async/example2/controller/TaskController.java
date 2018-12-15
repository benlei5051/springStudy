/*------------------------------------------------------------------------------
 * BlockController
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/11/21 18:10
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 *---------------------------------------------------------------------------*/
package org.andy.beans.async.example2.controller;

import org.andy.beans.async.example2.bean.ResponseMsg;
import org.andy.beans.async.example2.service.TaskQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;


//前面铺垫了那么多，还是主要来说DeferredResult的；
//        和Callable一样，DeferredResult也是为了支持异步调用。
//        两者的主要差异，Sunny觉得主要在DeferredResult需要自己用线程来处理结果setResult，
//        而Callable的话不需要我们来维护一个结果处理线程。总体来说，Callable的话更为简单，
//        同样的也是因为简单，灵活性不够；相对地，DeferredResult更为复杂一些，
//        但是又极大的灵活性。在可以用Callable的时候，直接用Callable；
//        而遇到Callable没法解决的场景的时候，可以尝试使用DeferredResult。
//        这里Sunny将会设计两个DeferredResult使用场景。

@RestController
public class TaskController {

    private static final Logger log = LoggerFactory.getLogger(TaskController.class);

    //超时结果
    private static final ResponseMsg<String> OUT_OF_TIME_RESULT = new ResponseMsg<>(-1, "超时", "out of time");

    //超时时间
    private static final long OUT_OF_TIME = 3000L;

    @Autowired
    private TaskQueue taskQueue;


    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public DeferredResult<ResponseMsg<String>> getResult() {
        log.info("接收请求，开始处理...");
        //建立DeferredResult对象，设置超时时间，以及超时返回超时结果
        DeferredResult<ResponseMsg<String>> result = new DeferredResult<>(OUT_OF_TIME, OUT_OF_TIME_RESULT);

        result.onTimeout(() -> {
            log.info("调用超时");
        });

        result.onCompletion(() -> {
            log.info("调用完成");
        });

        //并发，加锁，将DeferredResult放入任务中，并将任务放入任务队列
        synchronized (taskQueue) {
            taskQueue.put(result);
        }

        log.info("接收任务线程完成并退出");
        return result;

    }

}
