package org.swe.figures.Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Shape {
    public double radius;
    public boolean fill;

    public Circle(Color color, double lineWidth, double centerX, double centerY, double radius, boolean fill) {
        super(color, lineWidth, centerX, centerY, "Окружность/круг");
        this.radius = radius;
        this.fill = fill;
    }

    public Circle(String name) {
        super(name);
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public void draw(GraphicsContext gc) {
        double topLeftX = startX - radius;
        double topLeftY = startY - radius;
        double diameter = radius * 2;

        gc.setStroke(color);
        gc.setFill(color);
        gc.setLineWidth(lineWidth);

        if (fill)
            gc.fillOval(topLeftX, topLeftY, diameter, diameter);
        else
            gc.strokeOval(topLeftX, topLeftY, diameter, diameter);
    }
}
