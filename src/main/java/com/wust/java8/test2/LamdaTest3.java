package com.wust.java8.test2;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * java8内置四大函数式接口
 */
public class LamdaTest3 {
    /**
     * consumer - void accept(T t);//有参数无返回值
     */
    @Test
    public void test1(){
        Consumer<String> consumer = str -> System.out.println(str);
        consumer.accept("hello,world");
        consumer.accept("hello,China");
    }
    /**
     * Supplier接口 T get();无参数有返回值
     */
    @Test
    public void test2(){
        Supplier<Double> supplier = () -> Math.random() * 100;
        List<Double> ls = new ArrayList<>(16);
        for (int i = 0; i < 10; i++) {
            ls.add(supplier.get());
        }
        System.out.println(ls);
    }

    /**
     * Predicate断言型接口，boolean test(T t);
     */
    @Test
    public void test3(){
        Predicate<String> predicate = str -> str.length() >= 3;
        List<String> ls = Arrays.asList("hello", "ok", "haha", "fuu");
        List<String> strList = new ArrayList<>(4);
        for (String s : ls) {
            if(predicate.test(s)){
                strList.add(s);
            }
        }
        System.out.println(strList);
    }

    /**
     * Function<T,R>接口， R apply(T t);
     */
    @Test
    public void test4(){
        Function<Integer,Integer> f = i -> (int) Math.pow(i,2);
        System.out.println(f.apply(2));
        System.out.println(f.apply(3));
    }
}
