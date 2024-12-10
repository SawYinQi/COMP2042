module com.example.demo
{
    requires javafx.controls;
    requires javafx.fxml;

    exports com.example.demo.controller;
    opens com.example.demo.controller to javafx.fxml;
}