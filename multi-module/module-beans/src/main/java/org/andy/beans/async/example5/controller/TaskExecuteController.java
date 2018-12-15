/*------------------------------------------------------------------------------
 * TaskExecuteController
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/12/5 17:34
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 *---------------------------------------------------------------------------*/
package org.andy.beans.async.example5.controller;

import org.andy.beans.async.example5.bean.ResponseMsg;
import org.andy.beans.async.example5.bean.TaskSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Set;

@RestController
public class TaskExecuteController {

    private static final Logger log = LoggerFactory.getLogger(TaskExecuteController.class);

    @Autowired
    private TaskSet taskSet;
//http://localhost:8082/configDemo/set/bbb
    @RequestMapping(value = "/set/{result}",method = RequestMethod.GET)
    public String setResult(@PathVariable("result") String result){

        ResponseMsg<String> res = new ResponseMsg<>(0,"success",result);

        log.info("结果处理开始，得到输入结果为:{}",res);

        Set<DeferredResult<ResponseMsg<String>>> set = taskSet.getSet();



        synchronized (set){

            set.forEach((deferredResult)->{deferredResult.setResult(res);});

        }

        return "Successfully set result: " + result;
    }
}
