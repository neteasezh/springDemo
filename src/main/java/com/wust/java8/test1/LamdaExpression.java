package com.wust.java8.test1;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LamdaExpression {

    @Test
    public void test1(){
        Comparator<Integer> comparator = (x,y)-> Integer.compare(x,y);

        List<Integer> ls = Arrays.asList(1, 4, 6, 2, 3, 5);

        ls.sort(comparator);

        System.out.println(ls);
    }

    private List<User> users = Arrays.asList(
            new User(38,"张三",5000, User.Status.BUSY),
            new User(45,"李四",6000, User.Status.FREE),
            new User(16,"王五",3000, User.Status.BUSY),
            new User(23,"赵六",7000, User.Status.BUSY),
            new User(8,"田七",8000, User.Status.FREE)
    );
    /**
     * 方法一：策略设计模式
     */
    @Test
    public void test2(){
        //user.getAge() >= 35
        List<User> users1 = filter(new FilterByAge());
        System.out.println(users1);
        //user.getSalary() > 5000
        List<User> users2 = filter(new FilterBySalary());
        System.out.println(users2);
    }

    public List<User> filter(MyPredicate<User> myPredicate){
        List<User> ls = new ArrayList<>();
        for (User user : users) {
            if(myPredicate.filter(user)){
                ls.add(user);
            }
        }
        return ls;
    }

    /**
     * 升级为方法二：匿名内部类
     */
    @Test
    public void test3(){
        //年纪小于35
        List<User> users1 = filter(new MyPredicate<User>() {
            @Override
            public boolean filter(User user) {
                return user.getAge() < 38;
            }
        });
        System.out.println(users1);

        //薪资小于6000
        List<User> users2 = filter(new MyPredicate<User>() {
            @Override
            public boolean filter(User user) {
                return user.getSalary() < 6000;
            }
        });
        System.out.println(users2);
    }

    /**
     * 升级为方法三：Lamda表达式
     */
    @Test
    public void test4(){
        List<User> users1 = filter(u -> u.getAge() >= 40);
        System.out.println(users1);
        List<User> users2 = filter(u -> u.getSalary() >= 7000);
        System.out.println(users2);
    }

    /**
     * 升级为方法四：stream流
     */
    @Test
    public void test5(){
        users.stream().filter(u -> u.getSalary() < 8000).forEach(System.out::println);
        users.stream().filter(u -> u.getAge() > 40).map(u -> u.getName()).forEach(System.out::println);
    }
}
