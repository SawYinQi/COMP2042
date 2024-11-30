package com.example.demo.managers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * The TimeLineManager class manages the timeline of specific classes,
 * allowing it to execute specified methods at defined intervals.
 */
public class TimelineManager
{
    private final Timeline timeline;

    /**
     * Constructs a TimelineManager with a specified interval and method to be executed.
     *
     * @param interval  the interval in milliseconds for the timeline's keyframe.
     * @param method    the Runnable method to be executed at each keyframe.
     */
    public TimelineManager(int interval, Runnable method)
    {
        this.timeline = new Timeline();
        initializeTimeline(interval, method);
    }

    /**
     * Initializes the timeline with the specified interval and Runnable method.
     *
     * @param interval  the interval in milliseconds for the timeline's keyframe.
     * @param method    the Runnable method to be executed at each keyframe.
     */
    private void initializeTimeline(int interval, Runnable method)
    {
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame gameLoop = new KeyFrame(Duration.millis(interval), e -> method.run());
        timeline.getKeyFrames().add(gameLoop);
    }

    /**
     * Returns the specific Timeline instance.
     *
     * @return the Timeline instance.
     */
    public Timeline getTimeline()
    {
        return timeline;
    }

    /**
     * Starts timeline.
     */
    public void play()
    {
        timeline.play();
    }


    /**
     * Stops timeline.
     */
    public void stop()
    {
        timeline.stop();
    }
}
