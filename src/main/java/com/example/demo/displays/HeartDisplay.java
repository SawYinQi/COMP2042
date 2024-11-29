package com.example.demo.displays;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.Objects;

/**
 * The HeartDisplay class is responsible for the display of hearts representing player lives.
 */
public class HeartDisplay
{
	
	private static final String HEART_IMAGE_NAME = "/com/example/demo/images/heart.png";
	private static final int HEART_HEIGHT = 50;
	private HBox container;
	private final double containerXPosition;
	private final double containerYPosition;
	private final int numberOfHeartsToDisplay;

	/**
	 * Constructs a HeartDisplay at the specified x and y position with a specified number of hearts.
	 *
	 * @param xPosition       the x-coordinate for the heart display container.
	 * @param yPosition       the y-coordinate for the heart display container.
	 * @param heartsToDisplay the initial number of hearts to display.
	 */
	public HeartDisplay(double xPosition, double yPosition, int heartsToDisplay)
	{
		this.containerXPosition = xPosition;
		this.containerYPosition = yPosition;
		this.numberOfHeartsToDisplay = heartsToDisplay;
		initializeContainer();
		initializeHearts();
	}

	/**
	 * Initializes the container for the heart display.
	 */
	private void initializeContainer()
	{
		container = new HBox();
		container.setLayoutX(containerXPosition);
		container.setLayoutY(containerYPosition);		
	}

	/**
	 * Initializes the heart within the container.
	 */
	private void initializeHearts()
	{
		for (int i = 0; i < numberOfHeartsToDisplay; i++)
		{
			ImageView heart = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(HEART_IMAGE_NAME)).toExternalForm()));
			heart.setFitHeight(HEART_HEIGHT);
			heart.setPreserveRatio(true);
			container.getChildren().add(heart);
		}
	}

	/**
	 * Removes a single heart from the display.
	 */
	public void removeHeart()
	{
		if (!container.getChildren().isEmpty())
			container.getChildren().removeFirst();
	}

	/**
	 * Return the Hbox object.
	 *
	 * @return the Hbox object containing the heart icons.
	 */
	public HBox getContainer()
	{
		return container;
	}
}
