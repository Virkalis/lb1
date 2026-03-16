package org.swe.figures.View;

import javafx.scene.control.*;
import javafx.scene.paint.Color;
import org.swe.figures.Model.IShapeFactory;
import org.swe.figures.Model.Shape;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class CanvasOriginator {
    private List<Shape> shapes;
    private Color currentColor;
    private double currentLineWidth;
    private boolean currentFillState;
    private IShapeFactory shapeFactory;

    private ColorPicker colorPicker;
    private Slider lineWidthSlider;
    private CheckBox fillCheckBox;
    
    public CanvasOriginator(ColorPicker colorPicker, Slider lineWidthSlider, CheckBox fillCheckBox, IShapeFactory shapeFactory) {
        this.shapes = new ArrayList<>();
        this.colorPicker = colorPicker;
        this.lineWidthSlider = lineWidthSlider;
        this.fillCheckBox = fillCheckBox;
        this.shapeFactory = shapeFactory;
    }
    
    public void setShapes(List<Shape> shapes) {
        this.shapes = new ArrayList<>(shapes);
    }
    
    public List<Shape> getShapes() {
        return new ArrayList<>(shapes);
    }

    public CanvasState saveState() {
        currentColor = colorPicker.getValue();
        currentLineWidth = lineWidthSlider.getValue();
        currentFillState = fillCheckBox.isSelected();
        return new CanvasState(shapes, currentColor, currentLineWidth, currentFillState);
    }

    public void restoreState(CanvasState state) {
        this.shapes = new ArrayList<>();
        for (SerializableShape serializableShape : state.getShapes()) {
            this.shapes.add(serializableShape.toShape(shapeFactory));
        }
        
        colorPicker.setValue(state.getCurrentColor());
        lineWidthSlider.setValue(state.getCurrentLineWidth());
        fillCheckBox.setSelected(state.getCurrentFillState());
    }

    public void saveToFile(String fileName) throws IOException {
        CanvasState state = saveState();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(state);
        }
    }

    public void loadFromFile(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            CanvasState state = (CanvasState) ois.readObject();
            restoreState(state);
        }
    }
}
