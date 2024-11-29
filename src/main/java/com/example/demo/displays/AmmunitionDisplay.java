package com.example.demo.displays;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * The AmmunitionDisplay class is responsible for the display of ammunition.
 */
public class AmmunitionDisplay
{
    private final Label ammunition;

    /**
     * Constructs an AmmunitionDisplay at the specified x and y position.
     *
     * @param xPosition the x-coordinate for the display.
     * @param yPosition the y-coordinate for the display.
     */
    public AmmunitionDisplay(double xPosition, double yPosition)
    {
        ammunition = new Label();
        ammunition.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        ammunition.setTextFill(Color.DARKGREEN);
        ammunition.setLayoutX(xPosition);
        ammunition.setLayoutY(yPosition);
    }

    /**
     * Updates the ammunition count displayed.
     *
     * @param ammo the current ammunition count to be displayed.
     */
    public void updateAmmunition(int ammo)
    {
        ammunition.setText("Ammo:" + ammo);
    }

    /**
     * Returns label object.
     *
     * @return the Label object used for displaying ammunition count.
     */
    public Label getAmmunitionDisplay()
    {
        return ammunition;
    }
}

