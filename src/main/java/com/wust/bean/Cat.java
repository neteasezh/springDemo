package com.wust.bean;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Cat {
    public Cat() {
        System.out.println("Cat Constructor...");
    }
    //对象创建并赋值之后调用
    @PostConstruct
    public void init(){
        System.out.println("Cat init...");
    }

    //容器销毁之前调用
    @PreDestroy
    public void destroy(){
        System.out.println("Cat destroy...");
    }
}
