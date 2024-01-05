package com.lutong.spring;

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
    }

    public Object getBean(String beanName){
        return null;
    }
}
