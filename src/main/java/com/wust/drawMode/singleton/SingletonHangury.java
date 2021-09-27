package com.wust.drawMode.singleton;

public class SingletonHangury {
    private static SingletonHangury instance = new SingletonHangury();
    private SingletonHangury(){}
    public static SingletonHangury getInstance(){
        return instance;
    }

}
