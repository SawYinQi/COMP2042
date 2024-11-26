package com.example.demo.displays;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AmmunitionDisplay
{
    private final Label ammunition;

    public AmmunitionDisplay(double xPosition, double yPosition)
    {
        ammunition = new Label();
        ammunition.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        ammunition.setTextFill(Color.DARKGREEN);
        ammunition.setLayoutX(xPosition);
        ammunition.setLayoutY(yPosition);
    }

    public void updateAmmunition(int ammo)
    {
        ammunition.setText("Ammo:" + ammo);
    }

    public Label getAmmunitionDisplay()
    {
        return ammunition;
    }
}
