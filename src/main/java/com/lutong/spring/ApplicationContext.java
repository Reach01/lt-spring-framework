package com.lutong.spring;

import java.lang.annotation.Annotation;

/**
 * @Description
 * @Date 2024/1/5 19:37
 * @Author lut
 * @Version 1.0.0
 */
public class ApplicationContext {

    private Class appConfig;

    public ApplicationContext(Class appConfig) {
        this.appConfig = appConfig;
        // 1. 扫描
        if (appConfig.isAnnotationPresent(ComponentScan.class)) {
            ComponentScan componentAnnotation = (ComponentScan) appConfig.getAnnotation(ComponentScan.class);
            String path = componentAnnotation.value();
            System.out.println(path);
        }
    }

    public Object getBean(String beanName){
        return null;
    }
}
