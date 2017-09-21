package test.andy.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.print.DocFlavor;
import java.util.concurrent.TimeUnit;

/**
 * @author: andy
 * @Date: 2017/9/7 10:36
 * @Description:
 */
@Repository
public class RedisDao {
    @Autowired
    private StringRedisTemplate template;

    public void setKey(String key, String value) {
        ValueOperations<String, String> ops = template.opsForValue();
        ops.set(key, value, 1, TimeUnit.MINUTES);
    }
    public String getValue(String key){
        ValueOperations<String,String> ops=template.opsForValue();
        return ops.get(key);
    }

}
