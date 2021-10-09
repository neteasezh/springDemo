package com.wust.java8.test2;

@FunctionalInterface
public interface MyFunction<T> {
    int operate(T x, T y);
}
