package pl.edu.agh.cs.app.ui.game.panes.status;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.util.Duration;
import pl.edu.agh.cs.app.backend.status.IGameStatus;

public class GameTimer extends Label {
    private final IGameStatus status;
    private int startTime;

    private final int everyXms;

    private Timeline timeline;

    public GameTimer(IGameStatus status) {
        super();
        this.status = status;
        this.setStyle("""
                -fx-font-size: 40px;
                -fx-font-weight: bold;
                -fx-text-fill: #ffffff;
                -fx-effect: dropshadow(gaussian, rgba(255,255,255,0.5), 1,1,1,1);""");
        startTime = 0;
        everyXms = 10;
        status.setGameTimer(this);
    }

    private void update(int time) {
        if (time > 0) {
            int seconds = time / 1000;
            int decimals = (time % 1000) / everyXms;
            final String finalTime = String.format("%d:%02ds", seconds, decimals);
            Platform.runLater(() -> this.setText(finalTime));
        }
        else Platform.runLater(() -> this.setText("0:00s"));
    }

    private Timeline getTimeline(long realStartTime) {
        return new Timeline(
                new KeyFrame(new Duration(0),
                        actionEvent -> update(startTime - (int) (System.currentTimeMillis() - realStartTime))),
                new KeyFrame(new Duration(everyXms))
        );
    }

    public void setStartTime() {
        startTime = status.getRoundTime();
        update(startTime);
    }

    public void run() {
        timeline = getTimeline(System.currentTimeMillis());
        timeline.setCycleCount(startTime / everyXms);
        timeline.play();
    }

    public void stop() {
        timeline.stop();
    }

    public void disable() {
        Platform.runLater(() -> this.setText("0:00s"));
    }
}
