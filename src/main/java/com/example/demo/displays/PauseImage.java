package com.example.demo.displays;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class PauseImage extends ImageView
{
    private static final String IMAGE_NAME = "/com/example/demo/images/pause.png";
    private static final int pauseImageSize = 100;

    public PauseImage(double xPosition, double yPosition)
    {
        this.setImage(new Image(Objects.requireNonNull(getClass().getResource(IMAGE_NAME)).toExternalForm()));
        this.setFitWidth(pauseImageSize);
        this.setFitHeight(pauseImageSize);
        this.setLayoutX(xPosition);
        this.setLayoutY(yPosition);
        this.setVisible(false);
    }

    public void showPauseImage()
    {
        this.setVisible(true);
    }

    public void hidePauseImage()
    {
        this.setVisible(false);
    }
}
