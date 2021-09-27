package com.wust.config;

import com.wust.bean.Car;
import com.wust.bean.Cat;
import com.wust.bean.Dog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan("com.wust.bean")
@Configuration
public class SpringConfig3 {
    //initMethod:指定初始化方法
    //destroyMethod：指定销毁方法
    @Bean(initMethod = "init",destroyMethod = "destroy")
    public Car car(){
        return new Car();
    }

    @Bean
    public Dog dog(){
         return new Dog();
    }

    @Bean
    public Cat cat(){
        return new Cat();
    }
}
