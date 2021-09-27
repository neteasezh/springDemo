package com.wust.aop;

public class Calculator {
    public int div(int i, int j){
        System.out.println("div("+i+","+j+")...");
        return i/j;
    }
}
