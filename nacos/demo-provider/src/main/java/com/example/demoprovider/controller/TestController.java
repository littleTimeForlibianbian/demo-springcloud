package com.example.demoprovider.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Bruce Lee
 * @Date: 2021/4/23
 * @Description:
 */
@RequestMapping("/aa")
@RestController
public class TestController {

    @RequestMapping("/test_provider")
    public String testProvider() {
        return "test-provide";
    }
}
