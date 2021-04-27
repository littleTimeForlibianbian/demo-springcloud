package com.example.demooauthorderresourceserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: Bruce Lee
 * @Date: 2021/4/27
 * @Description:
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public ResourceServerTokenServices resourceServerTokenServices() {
        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        remoteTokenServices.setClientId("order_app");
        remoteTokenServices.setClientSecret("test");
        //调用此url去验证token
        remoteTokenServices.setCheckTokenEndpointUrl("http://auth-server/oauth/check_token");
        remoteTokenServices.setRestTemplate(restTemplate);
        return remoteTokenServices;
    }
}
