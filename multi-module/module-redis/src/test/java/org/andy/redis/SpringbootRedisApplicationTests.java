package org.andy.redis;

import org.andy.redis.service.IRedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: andy
 * @Date: 2017/9/7 10:41
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRedisApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(SpringbootRedisApplicationTests.class);

    @Autowired
    private IRedisService redisService;

    @Test
    public void testRedis() {
        redisService.set("test", "andy1111");
        logger.info(redisService.get("test").toString());
    }
}
