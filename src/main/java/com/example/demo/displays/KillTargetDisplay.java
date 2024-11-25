package com.example.demo.displays;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class KillTargetDisplay
{

    private final Label target;
    private final int killsToAdvance;

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

    public void updateKillTarget(int kills)
    {
        if (killsToAdvance != 0)
        {
            int enemiesRemaining = killsToAdvance - kills;
            target.setText("Kills To Advance\n" + enemiesRemaining);
        }
    }

    public Label getKillTargetDisplay()
    {
        return target;
    }
}

