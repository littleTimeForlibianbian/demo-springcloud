package com.example.feginconsumer.controller;

import com.example.feginconsumer.service.ProviderFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Author: Bruce Lee
 * @Date: 2021/4/23
 * @Description:
 */
@RestController
@RefreshScope
public class TestConsumerController {

    @Autowired
    private ProviderFeignClient feignClient;

    @RequestMapping("/testInvokeProvider")
    public String testInvokeProvider() {
        return feignClient.testProvider();
    }

}
