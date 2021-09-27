package com.wust.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource({"classpath:/person.properties"})
@Component
@Data
public class User {
    /**@Value使用set方法进行赋值，等同于
     * <bean id="" class="">
     *    <property name="" value=""/>
     * </bean>
     * 1、基本数值
     * 2、SPEL表达式，#{2*5}
     * 3、从配置文件中取值或者获取环境参数中的值${os.name}
     */
    //@Value("张三")
    @Value("${person.name}")
    private String name;
    @Value("#{20+4}")
    private Integer age;
    @Value("${os.name}")
    private String os;
}
