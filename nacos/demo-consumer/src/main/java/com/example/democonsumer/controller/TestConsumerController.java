package com.example.democonsumer.controller;

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

    @Value("${nacos.userName:lixc}")
    private String userName;


    private final RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    public TestConsumerController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping("/testReadContentFromNacos")
    public String testReadContentFromNacos() {
        return userName;
    }

    @RequestMapping("/testInvokeProvider")
    public String testInvokeProvider() {
//        纯restTemplate的方式，没有集成ribbon  也就是没有加上@LoadBanlanced注解
     /*   List<ServiceInstance> instances = discoveryClient.getInstances("nacos-provider");
        instances.stream().forEach((instance) -> System.out.println(instance.getScheme() + "://" + instance.getHost() + ":" + instance.getPort() + instance.getUri() + "\r\n"));
        ServiceInstance choose = choose(instances);
        String uri = choose.getUri().toString() + "/test_provider";
        System.out.println("要请求的路径：" + uri);
        return restTemplate.getForObject(uri, String.class);*/


//        加上注解的使用方式
        return restTemplate.getForObject("http://nacos-provider/test_provider", String.class);
    }


    private ServiceInstance choose(List<ServiceInstance> instances) {
        if (!CollectionUtils.isEmpty(instances)) {
            return instances.get(0);
        }
        return null;
    }
}
