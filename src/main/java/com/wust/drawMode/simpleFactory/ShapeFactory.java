package com.wust.drawMode.simpleFactory;

public class ShapeFactory {
    public Shape getShape(String type){
        if(type == null){
            return null;
        }
        if (type.equalsIgnoreCase("rectangle")) {
            return new Rectangle();
        }else if (type.equalsIgnoreCase("square")){
            return new Square();
        }
        return null;
    }
}
