package com.lutong.spring;

import java.beans.Introspector;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Date 2024/1/5 19:37
 * @Author lut
 * @Version 1.0.0
 */
public class ApplicationContext {

    private Class appConfig;

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();
    private Map<String, Object> singletonMap = new HashMap<>();
    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    public ApplicationContext(Class appConfig) {
        this.appConfig = appConfig;
        // 1. 扫描
        scan(appConfig);
        // 2. 创建单例bean
        createSingletonBean();
        // 3. 依赖注入

    }

    private void createSingletonBean() {
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = entry.getValue();
            if(beanDefinition.getScope().equals("singleton")){
                Object bean = createBean(beanName, beanDefinition);
                singletonMap.put(beanName, bean);
            }
        }
    }

    private void scan(Class appConfig) {
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
                            if(BeanPostProcessor.class.isAssignableFrom(clazz)){
                                beanPostProcessors.add((BeanPostProcessor) clazz.getConstructor().newInstance());
                                continue;
                            }
                            String scope = "singleton";
                            if(clazz.isAnnotationPresent(Scope.class)){
                                scope = clazz.getAnnotation(Scope.class).value();
                            }
                            String beanName = clazz.getAnnotation(Component.class).value();
                            // 如果是空，则默认取类名小写首字母作为beanName
                            if("".equals(beanName)){
                                beanName = Introspector.decapitalize(clazz.getSimpleName());
                            }
                            BeanDefinition beanDefinition = new BeanDefinition(beanName, scope, clazz);
                            beanDefinitionMap.put(beanName, beanDefinition);
                        }
                    } catch (ClassNotFoundException | NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private Object createBean(String beanName, BeanDefinition beanDefinition) {
        Class clazz = beanDefinition.getClazz();
        try {
            Object bean = clazz.getConstructor().newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Autowire.class)) {
                    field.setAccessible(true);
                    field.set(bean, getBean(field.getName()));
                }
            }
            if (bean instanceof InitializingBean) {
                ((InitializingBean) bean).afterPropertiesSet();
            }
            for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
                beanPostProcessor.postProcessAfterInitialization(bean, beanName);
            }
            return bean;
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public Object getBean(String beanName){
        if(!beanDefinitionMap.containsKey(beanName)){
            throw new RuntimeException("没有beanDefinition");
        }
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if(beanDefinition.getScope().equals("singleton")){
            Object bean = singletonMap.get(beanName);
            if(bean == null){
                bean = createBean(beanName, beanDefinition);
                singletonMap.put(beanName, bean);
            }
            return bean;
        }
        return createBean(beanName, beanDefinition);
    }
}
