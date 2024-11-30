package com.example.demo.entities.destructibles;

import javafx.scene.image.*;

import java.util.Objects;

/**
 * The ActiveActor class is an abstract base class for game entities
 * that can move or be updated dynamically.
 */
public abstract class ActiveActor extends ImageView
{
	
	private static final String IMAGE_LOCATION = "/com/example/demo/images/";

	/**
	 * Constructs an ActiveActor with a specified image, size, and initial x and y position.
	 *
	 * @param imageName    the name of the image file relative to the image path.
	 * @param imageHeight  the height of the image.
	 * @param initialXPos  the initial x-coordinate position of the actor.
	 * @param initialYPos  the initial y-coordinate position of the actor.
	 */
	public ActiveActor(String imageName, int imageHeight, double initialXPos, double initialYPos)
	{
		this.setImage(new Image(Objects.requireNonNull(getClass().getResource(IMAGE_LOCATION + imageName)).toExternalForm()));
		this.setLayoutX(initialXPos);
		this.setLayoutY(initialYPos);
		this.setFitHeight(imageHeight);
		this.setPreserveRatio(true);
	}

	/**
	 * Abstract method to be implemented in its subclass for updating the position of the actor.
	 */
	public abstract void updatePosition();

	/**
	 * Abstract method to be implemented in its subclass for updating actor state.
	 */
	public abstract void updateActor();

	/**
	 * Moves the actor by a specified horizontal and vertical displacement.
	 *
	 * @param horizontalMove the horizontal displacement.
	 * @param verticalMove   the vertical displacement.
	 */
	protected void move(double horizontalMove, double verticalMove)
	{
		this.setTranslateX(getTranslateX() + horizontalMove);
		this.setTranslateY(getTranslateY() + verticalMove);
	}
}
