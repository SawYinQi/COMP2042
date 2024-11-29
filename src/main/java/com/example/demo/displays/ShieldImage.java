package com.example.demo.displays;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * The ShieldImage class displays the shield image for the boss's shield.
 */
public class ShieldImage extends ImageView
{
	private static final String IMAGE_NAME = "/com/example/demo/images/shield.png";
	private static final int SHIELD_SIZE = 300;

	/**
	 * Constructs a ShieldImage object.
	 */
	public ShieldImage()
	{
		this.setImage(new Image(Objects.requireNonNull(getClass().getResource(IMAGE_NAME)).toExternalForm()));
		this.setVisible(false);
		this.setFitHeight(SHIELD_SIZE);
		this.setFitWidth(SHIELD_SIZE);
	}

	/**
	 * Set shield image visibility to true.
	 */
	public void showShield()
	{
		this.setVisible(true);
	}

	/**
	 * Set shield image visibility to false.
	 */
	public void hideShield()
	{
		this.setVisible(false);
	}
}
