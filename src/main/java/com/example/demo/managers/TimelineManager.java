package com.example.demo.managers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class TimelineManager
{
    private final Timeline timeline;

    public TimelineManager(int interval, Runnable method)
    {
        this.timeline = new Timeline();
        initializeTimeline(interval, method);
    }

    private void initializeTimeline(int interval, Runnable method)
    {
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame gameLoop = new KeyFrame(Duration.millis(interval), e -> method.run());
        timeline.getKeyFrames().add(gameLoop);
    }

    public Timeline getTimeline()
    {
        return timeline;
    }

    public void play()
    {
        timeline.play();
    }

    public void stop()
    {
        timeline.stop();
    }
}
