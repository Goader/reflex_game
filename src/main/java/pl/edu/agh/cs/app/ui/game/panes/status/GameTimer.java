package pl.edu.agh.cs.app.ui.game.panes.status;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.util.Duration;
import pl.edu.agh.cs.app.backend.data.GameConfiguration;
import pl.edu.agh.cs.app.backend.status.IGameStatus;
import pl.edu.agh.cs.app.backend.status.MultiGameStatus;

public class GameTimer extends Label {
    private final IGameStatus status;
    private final GameConfiguration config;
    private int startTime;

    private int everyXms;
    private int significantDecimals;

    public GameTimer(IGameStatus status, GameConfiguration config) {
        super();
        this.status = status;
        this.config = config;
        this.setStyle("""
                -fx-font-size: 40px;
                -fx-font-weight: bold;
                -fx-text-fill: #ffffff;
                -fx-effect: dropshadow(gaussian, rgba(255,255,255,0.5), 1,1,1,1);""");
        startTime = 0;
        everyXms = 10;
        significantDecimals = 2;
        status.setGameTimer(this);
    }

    private void update(int time) {
        if (time > 0) {
            String seconds = String.valueOf(time / 1000);
            String decimals = String.valueOf((time % 1000) / everyXms);
            if (decimals.length() < significantDecimals)
                decimals += "0".repeat(significantDecimals - decimals.length());
            String finalDecimals = decimals;  // needed for lambda declaration
            Platform.runLater(() -> this.setText(seconds + ":" + finalDecimals + "s"));
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
        Timeline timeline = getTimeline(System.currentTimeMillis());
        // TODO SOLVE THIS PROBLEM, ALSO FOR THE SITUATION WHERE USER SELECTS SMTH
        int activeTime = startTime;
        if (config.isMultiMode()) activeTime = Math.min(activeTime, ((MultiGameStatus) status).getTimeComputerPressed());
        timeline.setCycleCount(startTime / everyXms);
        timeline.play();
    }
}
