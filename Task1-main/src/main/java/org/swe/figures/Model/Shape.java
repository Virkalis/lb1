package org.swe.figures.Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Shape {
    protected Color color;
    protected double lineWidth;
    protected String name;

    protected double startX;
    protected double startY;

    public Shape(Color color, double lineWidth, double startX, double startY, String name) {
        this.color = color;
        this.lineWidth = lineWidth;
        this.startX = startX;
        this.startY = startY;
        this.name = name;
    }

    public Shape(String name) {
        this.name = name;
    }

    public abstract double area();
    public abstract void draw(GraphicsContext gc);

    public String getName() { return name; }
    public double getStartX() { return startX; }
    public double getStartY() { return startY; }
    public Color getColor() { return color; }
    public double getLineWidth() { return lineWidth; }
}
