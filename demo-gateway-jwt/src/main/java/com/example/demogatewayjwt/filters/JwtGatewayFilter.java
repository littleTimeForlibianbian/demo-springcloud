package com.example.demogatewayjwt.filters;

import com.example.demogatewayjwt.util.TokenGenerator;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author: Bruce Lee
 * @Date: 2021/4/26
 * @Description:
 */
@Component
public class JwtGatewayFilter implements GlobalFilter, Ordered {


    @Value("${com.demo.example.nocheck_path}")
    private String notCheckPath;

    /**
     * 执行过滤规则
     *
     * @param exchange 环境/请求相关参数
     * @param chain    过滤器调用链
     * @return
     */
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String notCheckPath = "/sso-provider/";
        //首先判断请求的路径，如果包含一些公共资源或者静态资源，则不进行token验证
        //根据资源路径进行映射，写一个静态资源的配置文件正则表达式，判断当前路径是否能够匹配，if(match)    return chain.filter(exchange);
        String rawPath = exchange.getRequest().getURI().getRawPath();
        if (rawPath.startsWith(notCheckPath)) {
            System.out.println("非检查路径：" + rawPath);
            return chain.filter(exchange);
        }
        System.out.println("需检查路径：" + rawPath);
        //进行token处理
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders header = request.getHeaders();
        String token = header.getFirst("Authorization");
        String username = TokenGenerator.getUsername(token);
        System.out.println(username);
        if (StringUtils.isEmpty(username)) {
            return null;
        }        //2验证token
        return chain.filter(exchange);
    }


    /**
     * 设置执行顺序
     *
     * @return
     */
    public int getOrder() {
        return 0;
    }
}
