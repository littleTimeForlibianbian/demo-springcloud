package com.example.demooauthserver.config.indb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

/**
 * @Author: Bruce Lee
 * @Date: 2021/4/28
 * @Description:
 */
@Configuration
public class RedisDataSourceConfig {
    @Value("${spring.redis.host:localhost}")
    private String hostName;
    @Value("${spring.redis.port:6379}")
    private int port;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        /*RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(hostName);
        redisStandaloneConfiguration.setPort(port);
        return new LettuceConnectionFactory(redisStandaloneConfiguration);*/
        return new LettuceConnectionFactory(hostName, port);
    }
}
