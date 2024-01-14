package com.lutong.service;

import com.lutong.spring.Autowire;
import com.lutong.spring.Component;
import com.lutong.spring.Scope;

/**
 * @Description
 * @Date 2024/1/5 19:51
 * @Author lut
 * @Version 1.0.0
 */
@Component("userService")
@Scope("singleton")
public class UserService {

    @Autowire
    private OrderService orderService;

    public void test() {
        System.out.println("lutong spring framework dev success!");
        System.out.println(orderService);
    }
}
