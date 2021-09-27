package com.wust.drawMode.abstractFactory;

import com.wust.drawMode.simpleFactory.Shape;

public abstract class AbstractFactory {
    public abstract Color getColor(String color);
    public abstract Shape getShape(String shape);
}
