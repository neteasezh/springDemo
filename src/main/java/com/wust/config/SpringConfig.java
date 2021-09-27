package com.wust.config;

import com.wust.bean.Car;
import com.wust.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Configuration
//@ComponentScan("com.wust.bean")
public class SpringConfig {

    @Profile("dev")
    @Bean
    Car car1(){
        Car car = new Car();
        car.setName("宝马");
        return car;
    }

    @Profile("test")
    @Bean
    Car car2(){
        Car car = new Car();
        car.setName("奔驰");
        return car;
    }

    @Profile("prod")
    @Bean
    Car car3(){
        Car car = new Car();
        car.setName("奥迪");
        return car;
    }

    @Profile("default")
    @Bean
    Car car4(){
        Car car = new Car();
        car.setName("凯迪拉克");
        return car;
    }
}
