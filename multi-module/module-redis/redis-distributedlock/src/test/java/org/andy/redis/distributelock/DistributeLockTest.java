package org.andy.redis.distributelock;

import org.andy.redis.distributelock.DistributeLockApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: andy
 * @Date: 2018/7/24 17:09
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DistributeLockApplication.class)
public class DistributeLockTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testRedisTransaction() {
        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
        redisTemplate.setEnableTransactionSupport(true);
        //在未提交之前是获取不到值得，同时再次循环报错
        redisTemplate.watch("multiTest");
        redisTemplate.multi();
        valueOperations.set("multiTest",1);
        valueOperations.increment("multiTest",2);
        Object o = valueOperations.get("multiTest");
        int a =4/0;
        List list = redisTemplate.exec();
        System.out.println(list);
        System.out.println(o);

    }
    @Test
    public void testGet() {
        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
        Object o = valueOperations.get("multiTest");
        System.out.println(o);
    }

    @Test
    public void testTransaction() {
        SessionCallback<Object> sessionCallback=new SessionCallback<Object>(){
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                operations.multi();
                operations.delete("test");
                operations.opsForValue().set("test","2");
                int a=1/0;
                Object val=operations.exec();
                return val;
            }
        };
        redisTemplate.execute(sessionCallback);
    }
}
