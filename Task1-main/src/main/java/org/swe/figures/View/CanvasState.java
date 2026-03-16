package org.swe.figures.View;

import javafx.scene.paint.Color;
import org.swe.figures.Model.Shape;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class CanvasState implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private final List<SerializableShape> shapes;
    private final String currentColorString;
    private final double currentLineWidth;
    private final boolean currentFillState;
    
    public CanvasState(List<Shape> shapes, Color currentColor, double currentLineWidth, boolean currentFillState) {
        this.shapes = new ArrayList<>();
        for (Shape shape : shapes) {
            this.shapes.add(SerializableShape.fromShape(shape));
        }
        this.currentColorString = currentColor.toString();
        this.currentLineWidth = currentLineWidth;
        this.currentFillState = currentFillState;
    }
    
    public List<SerializableShape> getShapes() {
        return new ArrayList<>(shapes);
    }
    
    public Color getCurrentColor() {
        return Color.web(currentColorString.replace("0x", "#"));
    }
    
    public double getCurrentLineWidth() {
        return currentLineWidth;
    }
    
    public boolean getCurrentFillState() {
        return currentFillState;
    }
}
