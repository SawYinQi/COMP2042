module com.example.demo
{
    requires javafx.controls;
    requires javafx.fxml;

    exports com.example.demo;
    exports com.example.demo.controller;
    opens com.example.demo.controller to javafx.fxml;
    opens com.example.demo to javafx.fxml;
    opens com.example.demo.entities to javafx.fxml;
    opens com.example.demo.levels to javafx.fxml;
    opens com.example.demo.displays to javafx.fxml;
}