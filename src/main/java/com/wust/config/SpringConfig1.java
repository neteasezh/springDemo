package com.wust.config;

import com.wust.bean.Person;
import com.wust.util.WindowsCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Conditional(WindowsCondition.class)
@Configuration
public class SpringConfig1 {
    //@Conditional(WindowsCondition.class)
 /*   @Bean("bill")
    public Person person1(){
        return new Person("Bill Gates","62");
    }*/

   // @Conditional(LinuxCondition.class)
  /*  @Bean("linus")
    public Person person2(){
        return new Person("Linus","42");
    }*/
}
