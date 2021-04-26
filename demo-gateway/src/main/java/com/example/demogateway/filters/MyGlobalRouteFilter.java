package com.example.demogateway.filters;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author: Bruce Lee
 * @Date: 2021/4/25
 * @Description: 全局过滤器
 */
@Component
public class MyGlobalRouteFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        long currentDate = System.currentTimeMillis();
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            long now = System.currentTimeMillis();
            System.out.println("耗时：" + (now - currentDate));
        }));
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
