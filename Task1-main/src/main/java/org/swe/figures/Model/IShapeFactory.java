package org.swe.figures.Model;

import javafx.scene.paint.Color;


public interface IShapeFactory {
    Shape[] createAllShapes();
    Shape createShape(String shapeType, Color color, double lineWidth, boolean fill, double[] array);
}
