package com.lunz.fin.config.conf.redis;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;
import java.time.Duration;

@EnableCaching
@Configuration
public class RedisConfig extends CachingConfigurerSupport {
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(
            RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        //fastjson的序列化器
       FastJsonRedisSerializer serializer = new FastJsonRedisSerializer(Object.class);
        //Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);
        //设置序列化器
        // value值的序列化采用fastJsonRedisSerializer
        template.setValueSerializer(serializer);
        template.setHashValueSerializer(serializer);
        // key的序列化采用StringRedisSerializer
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());

        template.setConnectionFactory(redisConnectionFactory);

        return template;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(
            RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        StringRedisTemplate template = new StringRedisTemplate();
        //fastjson的序列化器
        FastJsonRedisSerializer serializer = new FastJsonRedisSerializer(Object.class);
        //Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);

        //设置序列化器
        template.setConnectionFactory(redisConnectionFactory);
        // 设置value的序列化规则和 key的序列化规则
        template.setValueSerializer(serializer);
        template.setKeySerializer(serializer);

        template.setHashKeySerializer(serializer);
        template.setHashValueSerializer(serializer);

        template.setDefaultSerializer(serializer);
        template.setEnableDefaultSerializer(true);
        template.afterPropertiesSet();
        return template;
    }


    /**
     * 自定义CacheManager
     */
    @Bean
    @Primary
    public CacheManager cacheManager(RedisTemplate<Object, Object> redisTemplate) {
        //全局redis缓存过期时间
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofDays(1));
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        redisCacheConfiguration=redisCacheConfiguration.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(fastJsonRedisSerializer));
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisTemplate.getConnectionFactory());
        return new RedisCacheManager(redisCacheWriter, redisCacheConfiguration);
    }



}
