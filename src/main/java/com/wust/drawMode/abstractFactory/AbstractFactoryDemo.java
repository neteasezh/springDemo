package com.wust.drawMode.abstractFactory;

import com.wust.drawMode.simpleFactory.Shape;

public class AbstractFactoryDemo {
    public static void main(String[] args) {
        AbstractFactory shape = FactoryProducer.getFactory("shape");
        AbstractFactory color = FactoryProducer.getFactory("color");

        Shape rectangle = shape.getShape("rectangle");
        rectangle.draw();
        Shape square = shape.getShape("square");
        square.draw();

        Color red = color.getColor("red");
        red.fill();
        Color green = color.getColor("green");
        green.fill();
    }
}
