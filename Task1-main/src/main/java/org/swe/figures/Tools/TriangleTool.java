package org.swe.figures.Tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.swe.figures.Model.IShapeFactory;
import org.swe.figures.Model.Shape;

import java.util.function.Consumer;

public class TriangleTool extends ShapeTool {
    public TriangleTool(GraphicsContext gc, Color color, double lineWidth, boolean fill,
                        Consumer<Shape> onShapeReady, IShapeFactory shapeFactory) {
        super(gc, color, lineWidth, fill, onShapeReady, shapeFactory, "Треугольник");
    }

    @Override
    protected int requiredPoints() {
        return 3;
    }
}
