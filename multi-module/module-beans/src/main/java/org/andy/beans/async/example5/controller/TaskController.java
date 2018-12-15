/*------------------------------------------------------------------------------
 * TaskController
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/12/5 17:31
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 *---------------------------------------------------------------------------*/
package org.andy.beans.async.example5.controller;

import org.andy.beans.async.example5.bean.ResponseMsg;
import org.andy.beans.async.example5.bean.TaskSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class TaskController {

    private Logger log = LoggerFactory.getLogger(TaskController.class);

    //超时结果
    private static final ResponseMsg<String> OUT_OF_TIME_RESULT = new ResponseMsg<>(-1,"超时","out of time");

    //超时时间
    private static final long OUT_OF_TIME = 60000L;

    @Autowired
    private TaskSet taskSet;


//    onTimeout和onCompletion中都多了一个移除元素的操作，
//    这也就是每次调用结束，需要将集合中的DeferredResult对象移除，即集合中保存的都是等待请求结果的DeferredResult对象。
//  http://localhost:8082/configDemo/get
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public DeferredResult<ResponseMsg<String>> getResult(){

        log.info("接收请求，开始处理...");

        //建立DeferredResult对象，设置超时时间，以及超时返回超时结果
        DeferredResult<ResponseMsg<String>> result = new DeferredResult<>(OUT_OF_TIME, OUT_OF_TIME_RESULT);

        result.onTimeout(() -> {
            log.info("调用超时，移除任务，此时队列长度为{}",taskSet.getSet().size());

            synchronized (taskSet.getSet()) {

                taskSet.getSet().remove(result);
            }
        });

        result.onCompletion(() -> {
            log.info("调用完成，移除任务，此时队列长度为{}",taskSet.getSet().size());

            synchronized (taskSet.getSet()) {

                taskSet.getSet().remove(result);
            }
        });

        //并发，加锁
        synchronized (taskSet.getSet()) {

            taskSet.getSet().add(result);

        }
        log.info("加入任务集合，集合大小为:{}",taskSet.getSet().size());

        log.info("接收任务线程完成并退出");

        return result;

    }
}

