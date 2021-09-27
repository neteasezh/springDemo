package com.wust.test;

import com.wust.bean.*;
import com.wust.config.SpringConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
/*    @Test
    void test0(){
        //AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        //1.创建容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //2.设置环境
        context.getEnvironment().setActiveProfiles("prod","test");
        //3.注册主配置类
        context.register(SpringConfig.class);
        //4.启动刷新容器
        context.refresh();

        String[] names = context.getBeanNamesForType(Car.class);
        for (String name : names) {
            System.out.println(name);
        }
        context.close();
    }


    public ApplicationContext getContext(Class clazz){
        ApplicationContext context = new AnnotationConfigApplicationContext(clazz);
        return context;
    }
    void print(ApplicationContext context){
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
    }*/

    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Person person = (Person) context.getBean("person");
    }


}
