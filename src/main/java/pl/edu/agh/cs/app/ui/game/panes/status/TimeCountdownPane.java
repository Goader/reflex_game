package pl.edu.agh.cs.app.ui.game.panes.status;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import pl.edu.agh.cs.app.backend.status.IGameStatus;

public class TimeCountdownPane extends VBox {
    public TimeCountdownPane(IGameStatus status) {
        Label label = new Label("Time left");
        label.setStyle("""
                -fx-font-size: 24px;
                -fx-font-weight: bold;
                -fx-text-fill: #ffffff;
                -fx-effect: dropshadow(gaussian, rgba(255,255,255,0.5), 1,1,1,1);""");
        this.getChildren().add(label);

        GameTimer gameTimer = new GameTimer(status);
        this.getChildren().add(gameTimer);
    }
}
