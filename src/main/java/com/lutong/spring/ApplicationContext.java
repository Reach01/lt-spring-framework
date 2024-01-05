package com.lutong.spring;

import java.io.File;
import java.net.URL;

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
            // 获取@ComponentScan注解
            ComponentScan componentAnnotation = (ComponentScan) appConfig.getAnnotation(ComponentScan.class);
            String path = componentAnnotation.value();

            path = path.replace(".", "/");

            ClassLoader classLoader = ApplicationContext.class.getClassLoader();
            URL url = classLoader.getResource(path);
            File file = new File(url.getFile());
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File f : files) {
                    String absolutePath = f.getAbsolutePath();
                    absolutePath = absolutePath.substring(absolutePath.indexOf("com"), absolutePath.indexOf(".class"));
                    absolutePath = absolutePath.replace("\\", ".");

                    try {
                        Class<?> clazz = classLoader.loadClass(absolutePath);
                        if (clazz.isAnnotationPresent(Component.class)) {

                            if (clazz.isAnnotationPresent(Scope.class)) {

                            }
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public Object getBean(String beanName){
        return null;
    }
}
