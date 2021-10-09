package com.wust.java8.test1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class User {
    private Integer age;
    private String name;
    private Integer salary;
    private Status status;

    public enum Status{
        BUSY,
        FREE
    }
    public User(Integer age) {
        this.age = age;
        System.out.println("the constructor method contains a parameter");
    }

    public User(Integer age, String name) {
        this.age = age;
        this.name = name;
        System.out.println("the constructor method contains two parameters");
    }

    public static void say(String x){
        System.out.println(x);
    }
}
