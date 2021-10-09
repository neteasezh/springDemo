package com.wust.java8.test2;

import com.wust.java8.test1.User;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class LamdaTest2 {

    private List<User> users = Arrays.asList(
            new User(38,"张三",5000, User.Status.BUSY),
            new User(45,"李四",6000, User.Status.FREE),
            new User(16,"王五",3000, User.Status.BUSY),
            new User(23,"赵六",7000, User.Status.BUSY),
            new User(8,"田七",8000, User.Status.FREE)
    );

    @Test
    public void test1(){
        MyFunction<Integer> f1 = (x,y) -> x + y;
        System.out.println(f1.operate(1, 3));

        MyFunction<Integer> f2 = (x, y) -> x * y;
        System.out.println(f2.operate(2,5));
    }

    public <T> void hello(T t){
        System.out.println(t);
    }
    @Test
    public void test2(){
        hello(1);
        hello("hello,world");
    }

    @Test
    public void test3(){
        users.stream().sorted((u1,u2)->{
           if(u1.getAge() == u2.getAge()){
               return -Integer.compare(u1.getSalary(),u2.getSalary());
           }else{
               return Integer.compare(u1.getAge(),u2.getAge());
           }
        }).forEach(System.out::println);
    }

    @Test
    public void test4(){
        StringFunction f1 = str -> str.toUpperCase();
        String s = "hello,world";
        System.out.println(f1.operate(s));

        StringFunction f2 = str -> str.substring(str.lastIndexOf(",") + 1);
        System.out.println(f2.operate(s));
    }
}

