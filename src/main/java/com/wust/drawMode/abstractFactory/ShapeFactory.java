package com.wust.drawMode.abstractFactory;

import com.wust.drawMode.simpleFactory.Rectangle;
import com.wust.drawMode.simpleFactory.Shape;
import com.wust.drawMode.simpleFactory.Square;

public class ShapeFactory extends AbstractFactory {
    @Override
    public Color getColor(String color) {
        return null;
    }

    @Override
    public Shape getShape(String shape) {
        if(shape == null){
            return null;
        }
        if (shape.equalsIgnoreCase("rectangle")) {
            return new Rectangle();
        }else if (shape.equalsIgnoreCase("square")){
            return new Square();
        }
        return null;
    }
}
