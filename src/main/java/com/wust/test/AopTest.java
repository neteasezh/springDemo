package com.wust.test;

import com.wust.aop.AopConfig;
import com.wust.aop.Calculator;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AopTest {
    @Test
    void test0(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);

        //在IOC容器中获得业务逻辑类，不能自己new业务逻辑类，否则会失效
        Calculator calculator = context.getBean(Calculator.class);
        calculator.div(20,5);

        context.close();
    }
}
