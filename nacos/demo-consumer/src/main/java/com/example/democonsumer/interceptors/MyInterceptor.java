package com.example.democonsumer.interceptors;

import org.springframework.cloud.client.loadbalancer.RestTemplateCustomizer;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: Bruce Lee
 * @Date: 2021/4/25
 * @Description:
 */
public class MyInterceptor implements RestTemplateCustomizer {

    @Override
    public void customize(RestTemplate restTemplate) {
//        restTemplate.getInterceptors()
    }
}
