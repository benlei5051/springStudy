package org.andy.redis.common;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.awt.print.Book;

/**
 * @author: andy
 * @Date: 2017/12/27 15:07
 * @Description:
 * 引入spring-boot-starter-data-redis 工程，会同时实例化stringRedisTemplate和RedisTemplate，故自己手动new RedisTemplate后，它会自动
 * 执行afterPropertiesSet方法。并且stringRedisTemplate也会去执行afterPropertiesSet方法
 * 方法名要是改为redisTemplate(请看RedisConfiguration源码)，只会实例化我自己的StringRedisTemplate。
 */

/**

 */
@Configuration
@ComponentScan
@PropertySource(value = {
        "classpath:/redis.properties"
}, ignoreResourceNotFound = true)
public class RedisAutoConfig {

    /**
     * 方式一序列化
     *
     * @param factory
     * @return
     */
    @Primary
    @Bean(name = "redisTemplate")
    public RedisTemplate redisTemplate1(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer("type");
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);

        return template;
    }

    /**
     * 方式二序列化
     *
     * @param factory
     * @return
     */
    @Profile("dev")
    @Bean(name = "redisTemplate")
    public RedisTemplate redisTemplate2(RedisConnectionFactory factory) {
        RedisTemplate<String, Book> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // 使用Jackson2JsonRedisSerialize 替换默认序列化
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Book>(Book.class);
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        return redisTemplate;
    }

    /**
     * 方式三序列化
     *
     * @param factory
     * @return
     */
    @Profile("prod")
    @Bean(name = "redisTemplate")
    public RedisTemplate redisTemplate3(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        return template;
    }

}
