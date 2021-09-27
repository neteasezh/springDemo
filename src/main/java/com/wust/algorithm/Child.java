package com.wust.algorithm;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Child extends Parent {
    private Integer num;

    public Child(Integer i) {
        this.num = i;
    }

    @Override
    public void refreshBeanFactory() {
        System.out.println("child");
    }

    public static void main(String[] args) throws Exception {
        Child o = new Child(1);
//        o.refresh();
        Class<? extends Child> clazz = o.getClass();
        Method refresh = clazz.getMethod("refresh");
        refresh.invoke(o,null);
        Class<?> superclass = clazz.getSuperclass();
        System.out.println(superclass.getName());

        Field field = clazz.getDeclaredField("num");
        field.setAccessible(true);

        System.out.println(field.get(o));
        field.set(o,3);
        System.out.println(o.num);
    }
}
