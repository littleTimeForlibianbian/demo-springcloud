package com.example.demogateway.filters;

import com.example.demogateway.predicates.MyRoutePredicateFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.AddRequestHeaderGatewayFilterFactory;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static org.springframework.cloud.gateway.support.GatewayToStringStyler.filterToStringCreator;

/**
 * @Author: Bruce Lee
 * @Date: 2021/4/26
 * @Description:
 */
@Component
public class MyGatewayFilterFactory extends AbstractGatewayFilterFactory<MyGatewayFilterFactory.Config> {
//    @Override
//    public GatewayFilter apply(Object config) {
//        return null;
//    }


    public MyGatewayFilterFactory() {
        super(Config.class);
    }


    @Override
    public GatewayFilter apply(MyGatewayFilterFactory.Config config) {
        System.out.println(config.getName());
        System.out.println(config.getValue());
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                //此处就将自己的功能设置到exchange中，然后将exchange传递非GatewayFilterChain调用链继续调用
                return chain.filter(exchange).then();
            }
        };
    }

    public static class Config{
        String name ;
        String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}
