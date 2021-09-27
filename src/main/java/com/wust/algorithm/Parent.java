package com.wust.algorithm;

public abstract class Parent {
    public abstract void refreshBeanFactory();
    public void refresh(){
        refreshBeanFactory();
        System.out.println("parent");
    }
}
