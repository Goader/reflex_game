package pl.edu.agh.cs.app.ui.game.panes;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import pl.edu.agh.cs.app.backend.GameEngine;
import pl.edu.agh.cs.app.backend.status.IGameStatus;

public class ControlsPane extends VBox {
    private final Button nextRoundBtn;
    private final Button stopGameBtn;
    private final Button fullScreenBtn;
    private final Button writeStatisticsBtn;

    private final int spacing = 0;

    private final GameEngine engine;
    private final IGameStatus status;

    public ControlsPane(GameEngine engine) {
        super();
        this.setSpacing(spacing);

        this.engine = engine;
        this.status = engine.getStatus();

        nextRoundBtn = new Button();
        stopGameBtn = new Button();
        fullScreenBtn = new Button();
        writeStatisticsBtn = new Button();

        nextRoundBtn.setId("nextRound");
        stopGameBtn.setId("stopGame");
        fullScreenBtn.setId("fullScreen");
        writeStatisticsBtn.setId("writeStatistics");

        nextRoundBtn.setMinSize(96, 234);
        nextRoundBtn.setMaxSize(96, 234);
        stopGameBtn.setMinSize(96, 234);
        stopGameBtn.setMaxSize(96, 234);
        fullScreenBtn.setMinSize(96, 234);
        fullScreenBtn.setMaxSize(96, 234);
        writeStatisticsBtn.setMinSize(96, 234);
        writeStatisticsBtn.setMaxSize(96, 234);

        nextRoundBtn.setOnAction(e -> nextRoundOnClick());
        stopGameBtn.setOnAction(e -> stopGameOnClick());
        fullScreenBtn.setOnAction(e -> fullScreenOnClick());
        writeStatisticsBtn.setOnAction(e -> writeStatisticsOnClick());

        this.getStylesheets().add(getClass().getResource("/style/controls.css").toExternalForm());

        this.getChildren().addAll(nextRoundBtn, stopGameBtn, fullScreenBtn, writeStatisticsBtn);
    }

    private void nextRoundOnClick() {
        status.togglePause();
        if (!status.isPaused() && status.isRoundFinished() && !status.isGameFinished()) {
            Thread thread = new Thread(engine.getNextRoundTask());
            thread.setDaemon(true);
            thread.start();
        }
    }

    private void stopGameOnClick() {
        Platform.exit();
    }

    private void fullScreenOnClick() {
        status.toggleFullScreen();
    }

    private void writeStatisticsOnClick() {
        status.writeStatistics();
    }
}
