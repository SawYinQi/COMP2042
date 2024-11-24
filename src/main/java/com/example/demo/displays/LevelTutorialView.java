package com.example.demo.displays;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class LevelTutorialView extends LevelView
{
    private final Group root;

    public LevelTutorialView(Group root, int heartsToDisplay)
    {
        super(root, heartsToDisplay);
        this.root = root;
    }

    public void showInstructions()
    {
        Label instructions = new Label("Instructions:\n" +
                "Press up key to move up\n" +
                "Press down key to mover down\n" +
                "Press space key to fire projectiles\n" +
                "Objective: Destroy EnemyPlane to go back to main menu");
        instructions.setFont(new Font("Arial", 24));
        instructions.setTextFill(Color.BLACK);
        instructions.setLayoutX(20);
        instructions.setLayoutY(570);
        root.getChildren().add(instructions);
    }

}
