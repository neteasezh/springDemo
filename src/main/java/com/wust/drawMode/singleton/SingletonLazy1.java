package com.wust.drawMode.singleton;

/**
 * 懒汉式、线程安全
 */
public class SingletonLazy1 {
    private static SingletonLazy1 instance;
    private SingletonLazy1(){}
    public static synchronized SingletonLazy1 getInstance(){
        if(instance == null){
            instance = new SingletonLazy1();
        }
        return instance;
    }
}
