package com.wust.java8.stream;

import com.wust.java8.test1.User;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class StreamTest2 {

    /**
     * 查找与匹配：终止操作
     * findAny
     * findFirst
     * allMatch
     * anyMatch
     * noneMatch
     * max
     * min
     * count
     */
    private List<User> users = Arrays.asList(
            new User(38,"张三",5000, User.Status.BUSY),
            new User(45,"李四",6000, User.Status.FREE),
            new User(16,"王五",3000, User.Status.BUSY),
            new User(23,"赵六",7000, User.Status.BUSY),
            new User(8,"田七",8000, User.Status.FREE)
    );
    @Test
    public void test(){
        //查找出年龄大于16的任意一个User
        Optional<User> op = users.stream().filter(user -> user.getAge() > 16).findAny();
        System.out.println(op.get());

        //查找出薪资高于5000的第一个user
        Optional<User> first = users.stream().filter(user -> user.getSalary() > 5000).sorted((u1,u2)->u2.getSalary().compareTo(u1.getSalary())).findFirst();
        System.out.println(first.get());

        //判断年龄是否都匹配某一值
        boolean b = users.stream().allMatch(user -> user.getAge() >= 8);
        boolean bb = users.stream().allMatch(user -> user.getAge() >= 16);
        System.out.println(b);
        System.out.println(bb);

        //判断年龄是否能匹配某一值
        boolean b1 = users.stream().anyMatch(user -> user.getAge() > 16);
        boolean b2 = users.stream().anyMatch(user -> user.getAge() < 8);
        System.out.println(b1);
        System.out.println(b2);

        //判断年龄是否不匹配任何值
        boolean b3 = users.stream().noneMatch(user -> user.getAge() < 8);
        boolean b4 = users.stream().noneMatch(user -> user.getAge() < 16);
        System.out.println(b3);
        System.out.println(b4);

        //返回流中元素的个数
        long count = users.stream().count();
        System.out.println(count);

        //返回最大年龄
        Optional<Integer> max = users.stream().map(User::getAge).max(Integer::compare);
        System.out.println(max.get());

        //返回最小年龄
        Optional<Integer> min = users.stream().map(User::getAge).min(Integer::compare);
        System.out.println(min.get());
    }
}
