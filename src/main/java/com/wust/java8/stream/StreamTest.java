package com.wust.java8.stream;

import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest {
    /**
     * 流的操作步骤：
     * 1、创建流
     * 2、中间操作
     * 3、终止操作
     */

    //1、创建流
    @Test
    public void test1(){
        //1、集合的stream方法
        List<Integer> ls = new ArrayList<>();
        Stream<Integer> stream = ls.stream();

        //2、数组流
        int[] arr = {1,2,5,6,6};
        IntStream stream1 = Arrays.stream(arr);

        //3、通过Stream创建流
        Stream stream2 = Stream.of(1, "hello", 3, 5);

        //4、创建无限流
        //4.1、通过iterator
        Stream<Integer> iterate = Stream.iterate(0, x -> x + 2);
        iterate.limit(10).forEach(System.out::println);
        //4.2、通过generator
        Stream<Double> stream3 = Stream.generate(Math::random);
        stream3.limit(10).forEach(System.out::println);
    }
}
