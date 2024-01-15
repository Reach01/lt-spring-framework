package com.lutong.service;

import com.lutong.spring.*;

/**
 * @Description
 * @Date 2024/1/5 19:51
 * @Author lut
 * @Version 1.0.0
 */
@Component("userService")
@Scope("singleton")
public class UserService implements InitializingBean, BeanNameAware {

    /*@Autowire
    private OrderService orderService;*/

    @LutongValue("lutong")
    private String value;

    private String beanName;

    public void test() {
        System.out.println("UserService test()...");
        System.out.println("name: " + value);
        System.out.println("beanName: " + beanName);
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("userService 初始化...");
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }
}
