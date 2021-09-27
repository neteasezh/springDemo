package com.wust.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

//@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    /**
     * 各个bean初始化之前调用
     * @param bean
     * @param beanName
     * @return 可以直接返回参数中的bean，也可以对bean进行包装后返回
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("BeforeInitialization..." + beanName);
        return bean;
    }

    /**
     * 各个bean初始化之后调用
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("AfterInitialization..." + beanName);
        return bean;
    }
}
