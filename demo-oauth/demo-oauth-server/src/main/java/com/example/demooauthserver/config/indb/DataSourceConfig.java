package com.example.demooauthserver.config.indb;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.lang.reflect.InvocationTargetException;
import java.sql.Driver;

/**
 * @Author: Bruce Lee
 * @Date: 2021/4/27
 * @Description:
 */
//@Data
//@Configuration
//@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourceConfig {
    private String userName;
    private String password;
    private String url;
    private String driver;

    @Bean(name = "myDataSource")
    public SimpleDriverDataSource dataSource() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        Class<?> aClass = this.getClass().getClassLoader().loadClass(driver);
        Object o = aClass.getConstructor(null).newInstance();
        dataSource.setDriver((Driver) o);
        return dataSource;
    }



}
