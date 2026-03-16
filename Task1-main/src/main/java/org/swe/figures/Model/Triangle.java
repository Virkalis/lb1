package org.swe.figures.Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Triangle extends Shape {
    public double[] xPoints;
    public double[] yPoints;
    public boolean fill;

    public Triangle(Color color, double lineWidth, double[] xPoints, double[] yPoints, boolean fill) {
        super(color, lineWidth, xPoints[0], yPoints[0], "Треугольник");
        this.xPoints = xPoints;
        this.yPoints = yPoints;
        this.fill = fill;
    }

    public Triangle(String name) {
        super(name);
    }

    @Override
    public double area() {
        return Math.abs((xPoints[0]*(yPoints[1]-yPoints[2]) +
                xPoints[1]*(yPoints[2]-yPoints[0]) +
                xPoints[2]*(yPoints[0]-yPoints[1])) / 2.0);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(color);
        gc.setFill(color);
        gc.setLineWidth(lineWidth);
        if (fill)
            gc.fillPolygon(xPoints, yPoints, 3);
        else
            gc.strokePolygon(xPoints, yPoints, 3);
    }
}
