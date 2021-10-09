package com.wust.java8.repeatAnnotation;

import java.lang.reflect.Method;

public class RepeatAnnotationTest {
    @MyAnnotations({@MyAnnotation("hello"),@MyAnnotation("world")})
    public void test(){

    }

    public static void main(String[] args) throws NoSuchMethodException {
        Class<RepeatAnnotationTest> clazz = RepeatAnnotationTest.class;
        Method method = clazz.getMethod("test");

        MyAnnotations annotation = method.getAnnotation(MyAnnotations.class);
        MyAnnotation[] value = annotation.value();
        for (MyAnnotation myAnnotation : value) {
            System.out.println(myAnnotation.value());
        }

    }
}
