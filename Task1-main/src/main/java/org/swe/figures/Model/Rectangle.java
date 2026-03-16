package org.swe.figures.Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends Shape {
    public double width, height;
    public boolean fill;

    public Rectangle(Color color, double lineWidth, double startX, double startY,
                     double width, double height, boolean fill) {
        super(color, lineWidth, startX, startY, "Прямоугольник");
        this.width = width;
        this.height = height;
        this.fill = fill;
    }

    public Rectangle(String name) {
        super(name);
    }

    @Override
    public double area() {
        return width * height;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(color);
        gc.setFill(color);
        gc.setLineWidth(lineWidth);
        if (fill)
            gc.fillRect(startX, startY, width, height);
        else
            gc.strokeRect(startX, startY, width, height);
    }
}
