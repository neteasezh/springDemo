package com.wust.drawMode.singleton;

public class SingletonDCL {
    private volatile static SingletonDCL instance;
    private SingletonDCL(){}
    private static SingletonDCL getInstance(){
        if(instance == null){
            synchronized (SingletonDCL.class){
                if(instance == null){
                    instance = new SingletonDCL();
                }
            }
        }
        return instance;
    }
}
