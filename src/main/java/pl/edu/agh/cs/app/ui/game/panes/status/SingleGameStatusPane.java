package pl.edu.agh.cs.app.ui.game.panes.status;

import javafx.scene.layout.HBox;
import pl.edu.agh.cs.app.backend.status.SingleGameStatus;

public class SingleGameStatusPane extends HBox {
    private final StatusBox livesLeft;

    public SingleGameStatusPane(int spacing, int boxSpacing, SingleGameStatus status) {
        super(spacing);

        livesLeft = new StatusBox(boxSpacing, "Lives left", status.livesLeftBinding().asString());

        this.getChildren().add(livesLeft);
    }
}
