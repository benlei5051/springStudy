package org.andy.microservice.spring.retry.service;

import lombok.extern.slf4j.Slf4j;
import org.andy.microservice.spring.retry.exception.RetryException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

/**
 *
 * @author: haolin@pateo.com.cn
 * @date: 2018/10/15 16:57
 * @version: V1.0
 */
@Service
@Slf4j
public class PayService {


    private int i = 1;

  /*  @Retryable的参数说明：

    value：抛出指定异常才会重试
    include：和value一样，默认为空，当exclude也为空时，默认所以异常
    exclude：指定不处理的异常
    maxAttempts：最大重试次数，默认3次
    backoff：重试等待策略，默认使用@Backoff，@Backoff的value默认为1000L，我们设置为2000L；multiplier（指定延迟倍数）默认为0，表示固定暂停1秒后进行重试，如果把multiplier设置为1.5，则第一次重试为2秒，第二次为3秒，第三次为4.5秒。*/

    @Retryable(include = {RetryException.class}, maxAttempts = 5, backoff = @Backoff(delay = 2000L, multiplier = 1.5))
    public String retry() {
        log.info("测试retry");
        //生产环境此处应该为调用第三方接口，判断接口返回code
        if (i == 30) {
            return i++ + "";
        }
        log.info("============" + i);
        RetryException retryException = RetryException.builder().code("9999").message("连接超时").build();
        throw retryException;
    }


    @Recover
    public String recover(RetryException e) {
        log.info(e.getMessage());
        return "6";
    }
}
