package org.swe.figures;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import org.swe.figures.Model.IShapeFactory;
import org.swe.figures.Model.Shape;
import org.swe.figures.Model.ShapeFactory;
import org.swe.figures.Tools.*;
import org.swe.figures.View.CanvasOriginator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    @FXML private Canvas canvas;
    @FXML private ColorPicker colorPicker;
    @FXML private CheckBox fillCheckBox;
    @FXML private Slider lineWidthSlider;
    @FXML private ComboBox<String> modeComboBox;

    private GraphicsContext gc;
    private IToolFactory toolFactory;
    private IShapeFactory sf;
    private DrawTool currentTool;
    private List<Shape> shapes = new ArrayList<>();
    private Shape[] shapesForInit;
    private CanvasOriginator originator;

    @FXML
    public void initialize() {
        gc = canvas.getGraphicsContext2D();
        sf = new ShapeFactory();
        toolFactory = new ToolFactory(gc, sf);
        shapesForInit = sf.createAllShapes();
        
        // Инициализация Originator для сохранения/загрузки состояния
        originator = new CanvasOriginator(colorPicker, lineWidthSlider, fillCheckBox, sf);

        modeComboBox.getItems().add("Свободное рисование");
        for (Shape s : shapesForInit) {
            modeComboBox.getItems().add(s.getName());
        }
        modeComboBox.getSelectionModel().select("Свободное рисование");

        updateTool();

        modeComboBox.setOnAction(e -> updateTool());
        colorPicker.setOnAction(e -> updateTool());
        fillCheckBox.setOnAction(e -> updateTool());
        lineWidthSlider.valueProperty().addListener((obs, o, n) -> updateTool());

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> currentTool.onMousePressed(e));
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> currentTool.onMouseDragged(e));
        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> currentTool.onMouseReleased(e));
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> currentTool.onMouseClicked(e));
    }

    @FXML
    private void clear() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        shapes.clear();
    }


    private void updateTool() {
        String mode = modeComboBox.getValue();
        currentTool = toolFactory.createTool(
                mode,
                colorPicker.getValue(),
                lineWidthSlider.getValue(),
                fillCheckBox.isSelected(),
                shape -> {
                    shapes.add(shape);
                    shape.draw(gc);
                }
        );
    }

    public void undo(ActionEvent event) {
        if (!shapes.isEmpty()) {
            shapes.remove(shapes.size() - 1);
            redrawCanvas();
        }
    }
    
    @FXML
    public void saveCanvas(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Сохранить холст");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Рисунок (*.draw)", "*.draw")
        );
        
        File file = fileChooser.showSaveDialog(canvas.getScene().getWindow());
        if (file != null) {
            try {
                originator.setShapes(shapes);
                originator.saveToFile(file.getAbsolutePath());
                showAlert("Сохранение", "Холст успешно сохранен!");
            } catch (IOException e) {
                showAlert("Ошибка", "Не удалось сохранить холст: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
    
    @FXML
    public void loadCanvas(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Загрузить холст");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Рисунок (*.draw)", "*.draw")
        );
        
        File file = fileChooser.showOpenDialog(canvas.getScene().getWindow());
        if (file != null) {
            try {
                originator.loadFromFile(file.getAbsolutePath());
                shapes = originator.getShapes();
                redrawCanvas();
                showAlert("Загрузка", "Холст успешно загружен!");
            } catch (IOException | ClassNotFoundException e) {
                showAlert("Ошибка", "Не удалось загрузить холст: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
    
    private void redrawCanvas() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (Shape shape : shapes) {
            shape.draw(gc);
        }
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
