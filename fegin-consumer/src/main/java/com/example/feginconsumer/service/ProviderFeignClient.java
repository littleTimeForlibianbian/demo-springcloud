package com.example.feginconsumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: Bruce Lee
 * @Date: 2021/4/25
 * @Description:
 */
@FeignClient(name = "nacos-provider")
public interface ProviderFeignClient {

    @RequestMapping("/test_provider")
    String testProvider();
}
