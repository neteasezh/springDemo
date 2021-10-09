package com.wust.java8.repeatAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.CONSTRUCTOR, ElementType.PARAMETER,ElementType.TYPE_PARAMETER})
public @interface MyAnnotations {
    MyAnnotation[] value();
}
