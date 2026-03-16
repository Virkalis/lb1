package org.swe.figures.Tools;

import javafx.scene.paint.Color;
import org.swe.figures.Model.Shape;

import java.util.function.Consumer;


public interface IToolFactory {
    DrawTool createTool(String mode, Color color, double lineWidth, boolean fill,
                       Consumer<Shape> onShapeReady);
}
