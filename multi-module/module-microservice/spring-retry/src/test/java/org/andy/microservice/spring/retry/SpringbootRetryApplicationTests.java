package org.andy.microservice.spring.retry;

import org.andy.microservice.spring.retry.service.PayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author: haolin@pateo.com.cn
 * @date: 2018/10/15 16:59
 * @version: V1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRetryApplicationTests {
    @Autowired
    private PayService payService;

    @Test
    public void payTest() throws Exception {
        String store = payService.retry();
        System.out.println("库存为：" + store);
    }

}

