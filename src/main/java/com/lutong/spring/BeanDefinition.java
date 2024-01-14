package com.lutong.spring;

/**
 * @Descriptions
 * @Author lut
 * @Date 2024/1/14 20:39
 * @Version 1.0.0
 **/
public class BeanDefinition {

    private String beanName;
    /**
     * singleton
     * prototype
     */
    private String scope;

    private Class clazz;

    public BeanDefinition() {
    }

    public BeanDefinition(String beanName, String scope, Class clazz) {
        this.beanName = beanName;
        this.scope = scope;
        this.clazz = clazz;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }
}
