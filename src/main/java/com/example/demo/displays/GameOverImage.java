package com.example.demo.displays;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class GameOverImage extends ImageView
{
	
	private static final String IMAGE_NAME = "/com/example/demo/images/gameover.png";
	private static final int HEIGHT = 500;
	private static final int WIDTH = 600;

	public GameOverImage(double xPosition, double yPosition)
	{
		this.setImage(new Image(Objects.requireNonNull(getClass().getResource(IMAGE_NAME)).toExternalForm()));
		this.setVisible(false);
		this.setFitHeight(HEIGHT);
		this.setFitWidth(WIDTH);
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
	}

	public void showGameOverImage()
	{
		this.setVisible(true);
	}

}
