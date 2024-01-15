package com.lutong.service;

import com.lutong.spring.BeanPostProcessor;
import com.lutong.spring.Component;

/**
 * @Description
 * @Date 2024/1/15 13:43
 * @Author lut
 * @Version 1.0.0
 */
@Component
public class LutongBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        //if(beanName.equals("userService")){
            System.out.println(beanName + " 后置处理");
        //}
        return bean;
    }
}
