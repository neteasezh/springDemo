package com.wust.util;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;
import org.springframework.stereotype.Component;

import java.util.Set;
public class MyImportSelector implements ImportSelector {
    /**
     *
     * @param importingClassMetadata:注解信息(各个类中使用的注解信息都被封装在里面)
     * @return 以全类名的方式返回，@Import注解配合这个方法将被返回的类将被注册到IOC容器中，
     * 注意不能返回null，否则会报空指针异常，但是可以返回空数组
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        System.out.println(importingClassMetadata.getAnnotationTypes());
        return new String[]{"com.wust.bean.Color"};
    }
}
