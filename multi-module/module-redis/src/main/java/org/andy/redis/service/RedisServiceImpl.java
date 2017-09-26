package org.andy.redis.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author: andy
 * @Date: 2017/9/25 17:46
 * @Description:
 */
@Service
public class RedisServiceImpl implements  IRedisService{
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    protected static final ObjectMapper MAPPER = new ObjectMapper();
    /**
     * 普通设置键值对
     * @param key
     * @param value
     */
    public void set(String key, Object value) {
        ValueOperations<String,Object> vo = redisTemplate.opsForValue();
        vo.set(key,value);
    }

    /**
     * 获取
     * @param key
     * @return Object
     */
    public Object get(String key) {
        ValueOperations<String,Object> vo = redisTemplate.opsForValue();
        return vo.get(key);
    }

    /**
     * 删除
     * @param key
     */
    public void del(String key){
        redisTemplate.delete(key);
    }

    /**
     * 设置的对象拥有过期时间，过期自动删除(单位秒)
     * @param key
     * @param value
     * @param timeout
     */
    public void set(String key, Object value,long timeout){
        redisTemplate.opsForValue().set(key,value,timeout,TimeUnit.SECONDS);
    }

    /**
     * 删除数据的过期时间设置（将TTL设置成了-1）
     * @param key
     */
    public void persist(String key){
        redisTemplate.persist(key);
    }

    /**
     * 获取对象的TTL（生存时间）配置
     * @param key
     * @return ttl,单位秒，如果没设置，返回-1
     */
    public long ttl(String key){
        return redisTemplate.getExpire(key);
    }

    /**
     * 根据patter获取所有符合的Key，例如*就是返回所有
     * @param pattern
     * @return
     */
    public Set<String> keys(String pattern){
        return redisTemplate.keys(pattern);
    }
}
