package com.example.demo.displays;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * The PauseImage class displays the pause image for the game's paused state.
 */
public class PauseImage extends ImageView
{
    private static final String IMAGE_NAME = "/com/example/demo/images/pause.png";
    private static final int pauseImageSize = 100;

    /**
     * Constructs a PauseImage at the specified x and y position.
     *
     * @param xPosition the x-coordinate for the pause image.
     * @param yPosition the y-coordinate for the pause image.
     */
    public PauseImage(double xPosition, double yPosition)
    {
        this.setImage(new Image(Objects.requireNonNull(getClass().getResource(IMAGE_NAME)).toExternalForm()));
        this.setFitWidth(pauseImageSize);
        this.setFitHeight(pauseImageSize);
        this.setLayoutX(xPosition);
        this.setLayoutY(yPosition);
        this.setVisible(false);
    }

    /**
     * Set pause image visibility to true.
     */
    public void showPauseImage()
    {
        this.setVisible(true);
    }

    /**
     * Set pause image visibility to false.
     */
    public void hidePauseImage()
    {
        this.setVisible(false);
    }
}
