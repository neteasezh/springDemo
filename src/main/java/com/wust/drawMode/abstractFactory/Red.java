package com.wust.drawMode.abstractFactory;

public class Red implements Color {

    @Override
    public void fill() {
        System.out.println("Red::fill()");
    }
}
