//package com.example.demooauthorderresourceservergateway.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
//import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
//import org.springframework.web.client.RestTemplate;
//
///**
// * @Author: Bruce Lee
// * @Date: 2021/4/28
// * @Description:
// */
///*@Configuration
//@EnableWebSecurity*/
//public class GatewayResourcesConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Bean
//    public ResourceServerTokenServices resourceServerTokenServices() {
//        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
//        remoteTokenServices.setClientId("gateway_app");
//        remoteTokenServices.setClientSecret("test");
//        //调用此url去验证token
//        remoteTokenServices.setCheckTokenEndpointUrl("http://auth-server/oauth/check_token");
//        remoteTokenServices.setRestTemplate(restTemplate);
//        return remoteTokenServices;
//    }
//
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        resources.resourceId("gateway_app");
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        super.configure(web);
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
//    }
//
//    //TODO  待调整
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/order/selectOrderInfoById/**").access("#oauth2.hasScope('read')")
//                .and()
//                .authorizeRequests().antMatchers("/order/saveOrder/**").access("#oauth2.hasScope('write')")
//                .and()
//                .authorizeRequests().antMatchers("/public").permitAll();
//    }
//}
