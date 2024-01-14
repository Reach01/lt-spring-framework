package com.lutong.test;

import com.lutong.AppConfig;
import com.lutong.service.OrderService;
import com.lutong.service.UserService;
import com.lutong.spring.ApplicationContext;

/**
 * @Description
 * @Date 2024/1/5 19:38
 * @Author lut
 * @Version 1.0.0
 */
public class TestSpring {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ApplicationContext(AppConfig.class);
        UserService userService = (UserService) applicationContext.getBean("userService");
        UserService userService1 = (UserService) applicationContext.getBean("userService");
        UserService userService2 = (UserService) applicationContext.getBean("userService");
        OrderService orderService = (OrderService) applicationContext.getBean("orderService");
        System.out.println(userService);
        System.out.println(userService1);
        System.out.println(userService2);
        System.out.println(orderService);
        userService.test();
    }
}
