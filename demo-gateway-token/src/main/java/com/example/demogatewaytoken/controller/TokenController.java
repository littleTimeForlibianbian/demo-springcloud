package com.example.demogatewaytoken.controller;

import com.example.demogatewaytoken.util.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Bruce Lee
 * @Date: 2021/4/26
 * @Description:
 */

@RestController
@RequestMapping("/sso/token")
public class TokenController {

    @RequestMapping("/generatorToken")
    public String generatorToken(@RequestParam(name = "userName") String userName,
                                 @RequestParam(name = "password") String password) {
        String token = "";
        if (loginWithUserNameAndPassword(userName, password)) {
            token = TokenGenerator.generator(userName);
        }
        //先去走登录认证的接口，登录认证通过以后，再进行token的生成  正确的格式应该是json格式带有返回码和返回信息的
        return token;
    }

    private boolean loginWithUserNameAndPassword(String userName, String password) {
        return true;
    }
}
