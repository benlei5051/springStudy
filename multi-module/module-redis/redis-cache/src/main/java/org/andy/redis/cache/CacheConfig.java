package org.andy.redis.cache;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author: andy
 * @Date: 2017/9/25 17:02
 * @Description:
 */
@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {

//    三个主要的注解 Cacheable (最常用的注解，用于标注需要缓存方法)、CacheEvict(用于仅清除缓存)、CachePut(用于仅存放缓存)

    /**
     * 这个功能和@Cacheable(value="books",key = "'test'+#isbn")中的key属性一样的效果
     * 在redis中生成的key为org.andy.cache.dao.impl.BookRepositoryImpl-getByIsbn-isbn1234
     * @return
     */
    /*@Bean
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName() + "-");
            sb.append(method.getName() + "-");
            for (Object obj : params) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }*/

    @Bean
    public KeyGenerator simpleKey(){
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName()+":");
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }

    @Bean
    public KeyGenerator objectId(){
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params){
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName()+":");
                try {
//                    当function(Book book)上有@Cacheable时，它的key值，我只需要取book的isbn的值作为key
                    sb.append(params[0].getClass().getMethod("getIsbn", null).invoke(params[0], null).toString());
                }catch (NoSuchMethodException no){
                    no.printStackTrace();
                }catch(IllegalAccessException il){
                    il.printStackTrace();
                }catch(InvocationTargetException iv){
                    iv.printStackTrace();
                }
                return sb.toString();
            }
        };
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
        //设置缓存过期时间（秒）
        rcm.setDefaultExpiration(300);
        rcm.setUsePrefix(true);
        return rcm;
    }
    @Bean
    public RedisTemplate<String, String> redisTemplate(
            RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }
}
