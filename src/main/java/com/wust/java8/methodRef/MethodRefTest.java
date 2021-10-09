package com.wust.java8.methodRef;

import com.wust.java8.test1.User;
import org.junit.jupiter.api.Test;

import java.util.function.*;

public class MethodRefTest {
    /**
     * 方法引用：
     * 1、类名+静态方法名
     * 2、实例对象 + 实例方法
     * 3、类名 + 实例方法
     */

    //1、实例对象 + 实例方法
    @Test
    public void test1(){
        User user = new User(10, "zhangsan", 20000, User.Status.BUSY);
        //lamda表达式
        Supplier<Integer> supplier = () -> user.getAge();
        System.out.println(supplier.get());
        //当lamda表达式是调用实例对象的一个方法时，lamda表达式可以用方法引用替换
        Supplier<Integer> s = user::getSalary;
        System.out.println(s.get());
    }

    //2、类名 + 静态方法名
    @Test
    public void test2(){
        //lamada表达式
        Consumer<String> consumer = (x) -> User.say(x);
        consumer.accept("hello,world");
        //类名 + 静态方法名的方法引用
        Consumer<String> c = User::say;
        c.accept("hello,China");
    }

    //3、类名 + 实例方法
    @Test
    public void test3(){
        //Lamada表达式
        BiPredicate<String,String> predicate = (x,y) -> x.equals(y);
        System.out.println(predicate.test("hlleo", "hello"));
        //类名 + 实例方法 方法引用
        //当一个参数作为方法的调用者，另一个参数作为被调用方法的参数时，可以使用类名 + 实例方法
        //的方法引用
        BiPredicate<String,String> p = String::equals;
        System.out.println(p.test("hello","hello"));

        User u1 = new User(10, "zhangsan", 20000, User.Status.BUSY);
        User u2 = new User(10, "zhangsan", 20000, User.Status.BUSY);
        BiPredicate<User,User> bp = User::equals;
        System.out.println(bp.test(u1,u2));
    }

    /**
     * 构造器引用
     */
    @Test
    public void test4(){
        //lamda表达式
        Function<Integer, User> function = (x) -> new User(x);
        System.out.println(function.apply(10));
        //构造器引用:当lamada体内是构造一个对象时使用
        //User::new会根据函数式接口中的接方法里的参数列表来决定使用哪个构造函数
        //例如下面这个是调用的User中的带一个参数的构造方法
        Function<Integer,User> f = User::new;
        System.out.println(f.apply(10));

        //调用包含两个参数的构造方法
        BiFunction<Integer,String,User> bf = User::new;
        System.out.println(bf.apply(10,"lisi"));
    }

    /**
     * 数组引用
     */
    @Test
    public void test5(){
        //lamda表达式
        Function<Integer,String[]> function = (x) -> new String[x];
        String[] strs = function.apply(10);
        System.out.println(strs.length);

        //数组引用：当构造一个数组时是使用
        Function<Integer,String[]> f = String[]::new;
        String[] str = f.apply(20);
        System.out.println(str.length);
    }

}
