/*------------------------------------------------------------------------------
 * HelloService
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2018/11/21 11:06
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 *---------------------------------------------------------------------------*/
package org.andy.beans.async.example3.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HelloService {

    public String sayHello() {
        log.info(Thread.currentThread().getName() + " 进入sayHello方法");
        return "hello";
    }
}
