package com.example.demooauthorderresourceserver.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Bruce Lee
 * @Date: 2021/4/27
 * @Description:
 */
@RequestMapping("/order")
@RestController
public class OrderController {

    @RequestMapping("/selectOrderInfoById/{id}")
    public String selectOrderInfoById(@PathVariable("id") int id) {
        return "selectOrderInfoById :" + id;
    }

    @RequestMapping("/saveOrder")
    public String saveOrder() {
        return "saveOrder success";
    }
}
