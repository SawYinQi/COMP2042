module com.example.demo
{
    requires javafx.controls;
    requires javafx.fxml;

    exports com.example.demo.controller;
    opens com.example.demo.controller to javafx.fxml;
    opens com.example.demo.levels to javafx.fxml;
    opens com.example.demo.displays to javafx.fxml;
    opens com.example.demo.managers to javafx.fxml;
    opens com.example.demo.entities.behaviors to javafx.fxml;
    opens com.example.demo.entities.destructibles to javafx.fxml;
    opens com.example.demo.entities.planes to javafx.fxml;
    opens com.example.demo.entities.projectiles to javafx.fxml;
}