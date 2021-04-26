package com.example.demoprovider.controller;

import org.springframework.web.bind.annotation.RequestHeader;
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


    @RequestMapping("/testPrintHeader")
    public String testPrintHeader(@RequestHeader(name = "X-Requested-With") String header) {
        return header;
    }
}
