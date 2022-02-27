/**
 * 可以参考源码 RedisAutoConfiguration 的自动配置。
 */

package com.veryitman.user.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class MSRedisConfig {

    /**
     * RedisTemplate则可以用来操作对象，RedisTemplate采用的序列化方案是JdkSerializationRedisSerializer；
     * 无论是 StringRedisTemplate 还是 RedisTemplate，操作 Redis 的方法都是一致的；
     * StringRedisTemplate 和 RedisTemplate 都是通过 opsForValue、opsForZSet 或者 opsForSet 等方法首先获取一个操作对象，再使用该操作对象完成数据的读写；
     */
    @Bean
    @ConditionalOnMissingBean(name = {"redisTemplate"})
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(redisConnectionFactory);

        // 在使用注解@Bean返回RedisTemplate的时候，同时配置hashKey与hashValue的序列化方式
        // key采用String的序列化方式
        template.setKeySerializer(keySerializer());
        // value序列化方式采用jackson，默认使用 JdkSerializationRedisSerializer
        template.setValueSerializer(valueSerializer());
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(keySerializer());
        // hash的value序列化方式采用jackson
        template.setHashValueSerializer(valueSerializer());

        template.afterPropertiesSet();

        return template;
    }

    /**
     * StringRedisTemplate是RedisTemplate的子类；
     * StringRedisTemplate中的问和value都是字符串，采用的序列化方案是StringRedisSerializer；
     */
    @Bean
    @ConditionalOnMissingBean(name = {"stringRedisTemplate"})
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(redisConnectionFactory);
        return stringRedisTemplate;
    }

    /**
     * key 序列化。
     */
    private RedisSerializer<String> keySerializer() {
        return new StringRedisSerializer();
    }

    /**
     * value 序列化。
     */
    private RedisSerializer<Object> valueSerializer() {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer;
        jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 该方法已经过时，使用 activateDefaultTyping 替代
        //om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        ObjectMapper.DefaultTyping defaultTyping = ObjectMapper.DefaultTyping.NON_FINAL;
        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, defaultTyping, JsonTypeInfo.As.PROPERTY);

        jackson2JsonRedisSerializer.setObjectMapper(om);
        return jackson2JsonRedisSerializer;
    }
}
