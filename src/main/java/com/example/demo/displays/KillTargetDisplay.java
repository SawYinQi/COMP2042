package com.example.demo.displays;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

/**
 * The KillTargetDisplay class displays the number of kills required to advance to the next level.
 */
public class KillTargetDisplay
{

    private final Label target;
    private final int killsToAdvance;

    /**
     * Constructs a KillTargetDisplay at the specified x and y position with the required number of kills to advance.
     *
     * @param x             the x-coordinate for the kill target display.
     * @param y             the y-coordinate for the kill target display.
     * @param killsToAdvance the total number of kills required to advance to the next level.
     */
    public KillTargetDisplay(double x, double y, int killsToAdvance)
    {
        this.killsToAdvance = killsToAdvance;
        target = new Label();
        target.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        target.setTextFill(Color.BLACK);
        target.setTextAlignment(TextAlignment.CENTER);
        target.setLayoutX(x);
        target.setLayoutY(y);
    }

    /**
     * Updates the kill target display based on the number of kills.
     *
     * @param kills the current number of kills achieved by the user.
     */
    public void updateKillTarget(int kills)
    {
        if (killsToAdvance != 0)
        {
            int enemiesRemaining = killsToAdvance - kills;
            target.setText("Kills To Advance\n" + enemiesRemaining);
        }
    }

    /**
     * Returns Label object.
     *
     * @return the Label object displaying the kill target information.
     */
    public Label getKillTargetDisplay()
    {
        return target;
    }
}

