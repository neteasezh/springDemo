package com.wust.util;

import com.wust.bean.Ball;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    /**
     *
     * @param importingClassMetadata:注解信息
     * @param registry：使用它注册bean
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        System.out.println(importingClassMetadata.getAnnotationTypes());
        //导入具体的类
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Ball.class);
        //指定bean的名称，将其注入到IOC容器中
        registry.registerBeanDefinition("ball",rootBeanDefinition);
    }
}


