package org.swe.figures.Tools;

import javafx.scene.input.MouseEvent;

public interface DrawTool {
    void onMousePressed(MouseEvent e);
    void onMouseDragged(MouseEvent e);
    void onMouseReleased(MouseEvent e);
    void onMouseClicked(MouseEvent e);
    void drawHelperPoints();
}
