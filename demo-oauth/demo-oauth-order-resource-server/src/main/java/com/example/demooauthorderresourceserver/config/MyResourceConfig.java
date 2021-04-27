package com.example.demooauthorderresourceserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @Author: Bruce Lee
 * @Date: 2021/4/27
 * @Description:
 */
@Configuration
@EnableResourceServer
public class MyResourceConfig extends ResourceServerConfigurerAdapter {
    /**
     * 资源服务器的安全配置
     *
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/order/selectOrderInfoById/**").access("#oauth2.hasScope('read')")
                .and()
                .authorizeRequests().antMatchers("/order/saveOrder/**").access("#oauth2.hasScope('write')")
                .and()
                .authorizeRequests().antMatchers("/public").permitAll();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        //资源服务器的资源id，必须与认证服务器授权的资源id一致
        resources.resourceId("order-provider");
    }
}
