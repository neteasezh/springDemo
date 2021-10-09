package com.wust.java8.stream;

import com.wust.java8.test1.User;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

public class StreamTest3 {
    /**
     * reduce and collect
     */

    private List<User> users = Arrays.asList(
            new User(38,"张三",5000, User.Status.BUSY),
            new User(45,"李四",6000, User.Status.FREE),
            new User(16,"王五",3000, User.Status.BUSY),
            new User(23,"赵六",7000, User.Status.BUSY),
            new User(8,"田七",8000, User.Status.FREE)
    );

    //1、reduce终止操作
    @Test
    public void test1(){
        //map:提取 + reduce:规约

        //1、工资求和
        Integer res = users.stream().map(User::getSalary).reduce(0, (x, y) -> x + y);
        System.out.println(res);

        Optional<Integer> reduce = users.stream().map(User::getAge).reduce(BinaryOperator.maxBy(Integer::compare));
        Optional<Integer> r = users.stream().map(User::getAge).reduce(BinaryOperator.minBy(Integer::compareTo));

        System.out.println(reduce.get());
        System.out.println(r.get());
    }

    //2、collect 操作
    @Test
    public void test2(){
        List<String> l1 = users.stream().map(User::getName).collect(Collectors.toList());
        System.out.println(l1);

        HashSet<String> s1 = users.stream().map(User::getName).collect(Collectors.toCollection(HashSet::new));
        System.out.println(s1);

        String s = users.stream().map(User::getName).collect(Collectors.joining(",", "{", "}"));
        System.out.println(s);

        Double avg = users.stream().collect(Collectors.averagingInt(User::getAge));
        System.out.println(avg);

        Long count = users.stream().collect(Collectors.counting());
        System.out.println(count);

        Map<User.Status, List<User>> map = users.stream().collect(Collectors.groupingBy(User::getStatus));
        System.out.println(map);

        Map<User.Status, Map<String, List<User>>> mapMap = users.stream().collect(Collectors.groupingBy(User::getStatus, Collectors.groupingBy(u -> {
            if (u.getAge() <= 18) {
                return "青年";
            } else if (u.getAge() <= 30) {
                return "中年";
            } else {
                return "老年";
            }
        })));
        System.out.println(mapMap);
    }

}
