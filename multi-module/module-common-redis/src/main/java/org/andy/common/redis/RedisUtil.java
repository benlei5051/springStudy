package org.andy.common.redis;

import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
@Log4j
public class RedisUtil {

    @SuppressWarnings("rawtypes")
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    public void expire(final String key,long time){
        if (exists(key)) {
            redisTemplate.expire(key,time,TimeUnit.SECONDS);
        }
    }

    /**
     * 批量删除key
     *
     * @param pattern
     */
    @SuppressWarnings("unchecked")
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }

    public void removeWithPrefix(final String prefix, final String key) {
        remove(prefix+key);
    }
    /**
     * 删除对应的value
     *
     * @param key
     */
    @SuppressWarnings("unchecked")
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    @SuppressWarnings("unchecked")
    public boolean exists(final String prefix, final String key) {
        return redisTemplate.hasKey(prefix+key);
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    public Object getWithPrefix(final String prefix, final String key) {
        return get(prefix+key);
    }
    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public Object get(final String key) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    public boolean setWithPrefix(final String prefix, final String key, Object value) {
        return set(prefix+key, value);
    }
    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    @SuppressWarnings("unchecked")
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            log.warn("set redis key error", e);
        }
        return result;
    }

    public boolean setWithPrefixWithExpire(final String prefix, final String key, Object value, Long expireTime) {
        return set(prefix+key,value,expireTime);
    }


    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    @SuppressWarnings("unchecked")
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value, expireTime, TimeUnit.SECONDS);
//            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            log.warn("set redis key error", e);
        }
        return result;
    }

    /**
     * delete the object stored in redis
     * @param prefix
     * @param key
     * @return
     */
    public boolean deleteWithPrefix(final String prefix, final String key) {
        return delete(prefix+key);
    }
    public boolean delete(final String key) {
        boolean result = true;
        try {
            redisTemplate.delete(key);
        } catch (Exception e) {
            result = false;
            log.warn("delete redis key error", e);
        }
        return result;
    }

    public boolean delete(final Object[] keys) {
        boolean result = true;
        try {
            redisTemplate.delete(keys);
        } catch (Exception e) {
            result = false;
            log.warn("delete redis key error", e);
        }
        return result;
    }

    public Long increament(String key) {
        return redisTemplate.opsForValue().increment(key, 1);
    }


    public Long incrementMapValue(String key, String hashKey) {
        return redisTemplate.opsForHash().increment(key, hashKey, 1);
    }

    public void setObjectMapValue(String key, String hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    public Object getObjectMapValue(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    public Long deleteMapValue(String key, String... hashKey) {
        return redisTemplate.opsForHash().delete(key, hashKey);
    }

    public boolean existHashKey(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey) != null;
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
     * 更新缓存的存活周期，单位秒
     * @param key
     * @param ttl
     */
    public void refreshTtl(String key,long ttl){ redisTemplate.expire(key,ttl,TimeUnit.SECONDS);}

    /**
     * 获得分布式锁
     * @param key 分布式锁的 键值
     * @param ttl 分布式锁的有效期，防止锁不释放
     * @return 是否成功获得锁
     */
    public Boolean lock(String key, long ttl) {
        ValueOperations oper = redisTemplate.opsForValue();
        // redis cluster模式 暂时不支持事务...
        // 所以setIfAbsent后，如果执行expire失败会导致set不会过期，锁一直不释放
        // 所以如果存在时，检查下ttl是否为-1, 为-1表示没有设置过期，
        // 则 判断是否已经过期，过期了就重新获得锁
        Boolean absent = oper.setIfAbsent(key, System.currentTimeMillis());
        if (absent) {
            redisTemplate.expire(key, ttl, TimeUnit.SECONDS);
        } else {
            if (ttl(key) == -1) {
                long time = (long) oper.get(key);

                long duration = (System.currentTimeMillis() - time) / 1000;
                if (duration > ttl) {
                    oper.set(key, System.currentTimeMillis(), ttl, TimeUnit.SECONDS);
                    absent = true;
                }
            }
        }

        return absent;
    }

    /**
     * 获得整个redis Hash Map
     * @param key
     * @return
     */
    public Map<String, Object> getHash(String key) {
        return getHash(key, "*");
    }

    /**
     * 获得　hash-key匹配pattern的 redis Hash Map
     * @param key
     * @param pattern
     * @return
     */
    public Map<String, Object> getHash(String key, String pattern) {

        if (StringUtils.isBlank(pattern)) {
            pattern = "*";
        }
        Map<String, Object> entries = new HashMap<>();
        HashOperations<String, String, Object> hashOper = redisTemplate.opsForHash();

        ScanOptions.ScanOptionsBuilder builder = new ScanOptions.ScanOptionsBuilder();
        ScanOptions options = builder.count(1000).match(pattern).build();

        Cursor<Map.Entry<String, Object>> cursor = hashOper.scan(key, options);
        while (cursor.hasNext()) {
            Map.Entry<String, Object> next = cursor.next();
            entries.put(next.getKey(), next.getValue());
        }

        return entries;
    }

    public Set<String> keys(String keyPattern) {

        return redisTemplate.keys(keyPattern);
    }
}