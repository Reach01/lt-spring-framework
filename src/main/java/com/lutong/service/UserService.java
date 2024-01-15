package com.lutong.service;

import com.lutong.spring.Autowire;
import com.lutong.spring.Component;
import com.lutong.spring.InitializingBean;
import com.lutong.spring.Scope;

/**
 * @Description
 * @Date 2024/1/5 19:51
 * @Author lut
 * @Version 1.0.0
 */
@Component("userService")
@Scope("singleton")
public class UserService implements InitializingBean {

    /*@Autowire
    private OrderService orderService;*/

    @LutongValue("lutong")
    private String name;

    public void test() {
        System.out.println("UserService test()...");
        System.out.println("name: " + name);
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("userService 初始化...");
    }
}
