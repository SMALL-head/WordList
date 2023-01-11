package com.zyc.wordlistsp.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;
import java.time.Duration;

@Configuration
public class RedisCacheConfig {

    @Bean
    public RedisCacheManager prefixSearchCacheManager(RedisConnectionFactory redisConnectionFactory) {
        // 配置RedisCacheConfiguration以供下一步的工厂创建使用
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().
            entryTtl(Duration.ofDays(1)).
            serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()));
        // 设置工厂和配置类后生成RedisCacheManager
        return RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(redisCacheConfiguration).build();
    }

    @Bean
    public KeyGenerator searchPrefixKeyGenerator() {
        return (target, method, params) -> {
            StringBuilder paramName = new StringBuilder();
            for (Object s : params) {
                paramName.append(s);
            }
            return method.getName() + paramName;
        };
    }
}
