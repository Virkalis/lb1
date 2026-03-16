package org.swe.figures.Tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class FreeDrawTool implements DrawTool {
    private final GraphicsContext gc;
    private final Color color;
    private final double lineWidth;
    private final boolean fill;

    public FreeDrawTool(GraphicsContext gc, Color color, double lineWidth, boolean fill) {
        this.gc = gc;
        this.color = color;
        this.lineWidth = lineWidth;
        this.fill = fill;
    }

    @Override
    public void onMousePressed(MouseEvent e) {
        gc.setStroke(color);
        gc.setFill(color);
        gc.setLineWidth(lineWidth);
        gc.beginPath();
        gc.moveTo(e.getX(), e.getY());
    }

    @Override
    public void onMouseDragged(MouseEvent e) {
        gc.lineTo(e.getX(), e.getY());
        gc.stroke();
    }

    @Override
    public void onMouseReleased(MouseEvent e) {
        if (fill) gc.fill();
        gc.closePath();
    }

    @Override
    public void onMouseClicked(MouseEvent e) {
    }

    @Override
    public void drawHelperPoints() {
    }
}
