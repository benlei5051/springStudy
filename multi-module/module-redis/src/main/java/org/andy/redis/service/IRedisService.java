package org.andy.redis.service;

import java.util.List;
import java.util.Set;

/**
 * @author: andy
 * @Date: 2017/9/25 17:45
 * @Description:
 */
public interface IRedisService {

    public void set(String key, Object value);

    public Object get(String key);

    public void del(String key);

    public void set(String key, Object value,long timeout);

    public void persist(String key);

    public long ttl(String key);

    public Set<String> keys(String pattern);

}
