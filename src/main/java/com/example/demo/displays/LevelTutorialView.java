package com.example.demo.displays;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class LevelTutorialView extends LevelView
{
    private final Group root;

    public LevelTutorialView(Group root, int heartsToDisplay, int kills)
    {
        super(root, heartsToDisplay, kills);
        this.root = root;
    }

    public void showInstructions()
    {
        Label instructions = new Label("""
                Instructions:
                Press up key to move up.
                Press down key to mover down.
                Press space key to fire projectiles.
                Press p key to pause game, press again to resume.
                Objective: Destroy EnemyPlane to go back to main menu.""");
        instructions.setFont(new Font("Arial", 24));
        instructions.setTextFill(Color.BLACK);
        instructions.setLayoutX(20);
        instructions.setLayoutY(550);
        root.getChildren().add(instructions);
    }

}
