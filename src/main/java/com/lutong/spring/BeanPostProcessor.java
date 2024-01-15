package com.lutong.spring;

/**
 * @Description
 * @Date 2024/1/15 13:41
 * @Author lut
 * @Version 1.0.0
 */
public interface BeanPostProcessor {

    default Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    default Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }
}
