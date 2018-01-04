package org.andy.redis.auto;



import org.andy.common.redis.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: andy
 * @Date: 2018/1/4 11:11
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRedisApplicationTests {

    @Autowired
    private RedisUtil redisUtil;

    private static final Logger logger = LoggerFactory.getLogger(SpringbootRedisApplicationTests.class);


    @Test
    public void testRedis() {
        redisUtil.set("test", "andy1111");
        logger.info(redisUtil.get("test").toString());
    }
}