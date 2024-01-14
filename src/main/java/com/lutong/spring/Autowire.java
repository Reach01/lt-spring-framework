package com.lutong.spring;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Descriptions
 * @Author lut
 * @Date 2024/1/14 21:33
 * @Version 1.0.0
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Autowire {

}
