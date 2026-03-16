module org.swe.figures {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.swe.figures to javafx.fxml;
    exports org.swe.figures;
}