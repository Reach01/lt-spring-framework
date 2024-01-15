package com.lutong.service;

import com.lutong.spring.Component;

/**
 * @Description
 * @Date 2024/1/5 20:28
 * @Author lut
 * @Version 1.0.0
 */
@Component
public class OrderService implements OrderInterface {

    @Override
    public void test() {
        System.out.println("orderService test()...");
    }
}
