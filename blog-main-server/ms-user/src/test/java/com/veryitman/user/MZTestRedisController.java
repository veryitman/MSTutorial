package com.veryitman.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("testredis")
@ApiIgnore //忽略该类的API，不会参与文档生成
public class MZTestRedisController {

    @Autowired(required = false)
    RedisTemplate redisTemplate;

    @GetMapping(value = "redisconn")
    public String redis() {
        RedisConnectionFactory connectionFactory = redisTemplate.getConnectionFactory();
        System.out.println(connectionFactory);

        return connectionFactory.toString();
    }

    @GetMapping(value = "hellokitty")
    public String tmp() {
        return "temp";
    }
}
