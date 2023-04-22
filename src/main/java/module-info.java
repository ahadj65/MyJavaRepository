module com.example.demo3forsdk20 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.demo3forsdk20 to javafx.fxml;
    exports com.example.demo3forsdk20;
    exports com.example.demo3forsdk20.model;
    opens com.example.demo3forsdk20.model to javafx.fxml;
}