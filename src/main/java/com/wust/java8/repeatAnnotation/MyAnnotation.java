package com.wust.java8.repeatAnnotation;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.METHOD,ElementType.CONSTRUCTOR, ElementType.PARAMETER,ElementType.TYPE_PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(MyAnnotations.class)
public @interface MyAnnotation {
    String value();
}
