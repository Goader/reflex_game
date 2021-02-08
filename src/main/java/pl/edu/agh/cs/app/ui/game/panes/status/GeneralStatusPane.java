package pl.edu.agh.cs.app.ui.game.panes.status;

import javafx.beans.binding.Bindings;
import javafx.scene.layout.HBox;
import pl.edu.agh.cs.app.backend.status.IGameStatus;

public class GeneralStatusPane extends HBox {
    private final StatusBox playerPoints;
    private final StatusBox averageReactionTime;

    public GeneralStatusPane(int spacing, int boxSpacing, IGameStatus status) {
        super(spacing);

        playerPoints = new StatusBox(boxSpacing, "Your points", status.getPlayerPointsProperty().asString());
        averageReactionTime = new StatusBox(boxSpacing, "Average reaction time",
                Bindings.concat(
                        status.averageReactionProperty().divide(1000).asString(),
                        ":",
                        status.averageReactionProperty().subtract(
                                status.averageReactionProperty().divide(1000).multiply(1000)
                        ).divide(10).asString("%02d"),
                        "s"
                )
        );

        this.getChildren().addAll(playerPoints, averageReactionTime);
    }

}
