package com.wust.config;

import com.wust.bean.User;
import com.wust.util.MyImportBeanDefinitionRegistrar;
import com.wust.util.MyImportSelector;
import com.wust.util.UserFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


//@Import({User.class,MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
@Configuration
public class SpringConfig2 {
    @Bean
    public UserFactoryBean userFactoryBean(){
        return new UserFactoryBean();
    }
}
