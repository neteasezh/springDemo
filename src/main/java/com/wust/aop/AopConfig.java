package com.wust.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @EnableAspectJAutoProxy ：开启aop注解
 * 相当于配置文件中的：
 * <aop:aspectj-autoproxy/>
 */
@EnableAspectJAutoProxy
@Configuration
public class AopConfig {
    @Bean
    public Calculator calculator(){
        return new Calculator();
    }

    @Bean
    public LogAspect logAspect(){
        return new LogAspect();
    }
}
