package com.example.demogateway;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.function.Predicate;

/**
 * @Author: Bruce Lee
 * @Date: 2021/4/25
 * @Description: 自定义断言
 */
@Component
public class MyRoutePredicateFactory extends AbstractRoutePredicateFactory<MyRoutePredicateFactory.Config> {


    private final int MAX_LENGTH = 4;

    public MyRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return new Predicate<ServerWebExchange>() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                return config.getLength() > MAX_LENGTH;
            }

            @Override
            public String toString() {
                return "config.getLength()：" + config.getLength();
            }
        };
    }

    public static class Config {

        private int length;

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }
    }
}
