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

    public ControlsPane(GameEngine engine, IGameStatus status) {
        super();
        this.setSpacing(spacing);

        this.engine = engine;
        this.status = status;

        // TODO the appearance will change, so the names are only temporary
        nextRoundBtn = new Button("next");
        stopGameBtn = new Button("stop");
        fullScreenBtn = new Button("full");
        writeStatisticsBtn = new Button("stat");

        // TODO onaction
        stopGameBtn.setOnAction(e -> stopGameOnClick());
        fullScreenBtn.setOnAction(e -> fullScreenOnClick());

        this.getChildren().addAll(nextRoundBtn, stopGameBtn, fullScreenBtn, writeStatisticsBtn);
    }

    private void stopGameOnClick() {
        Platform.exit();
    }

    private void fullScreenOnClick() {
        status.toggleFullScreen();
    }
    // TODO onclick actions
}
