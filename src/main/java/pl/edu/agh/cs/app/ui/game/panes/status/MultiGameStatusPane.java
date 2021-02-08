package pl.edu.agh.cs.app.ui.game.panes.status;

import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.scene.layout.HBox;
import pl.edu.agh.cs.app.backend.status.MultiGameStatus;

public class MultiGameStatusPane extends HBox {
    private final StatusBox roundsLeft;
    private final StatusBox computerPoints;

    public MultiGameStatusPane(int spacing, int boxSpacing, MultiGameStatus status) {
        super(spacing);

        roundsLeft = new StatusBox(boxSpacing, "Rounds left",
                status.playedRoundsProperty().multiply(-1).add(status.getRoundsToPlay()).asString());
        // the above binding means: roundsToPlay - playedRounds
        computerPoints = new StatusBox(boxSpacing, "Computer's points",
                status.getComputerPointsProperty().asString());

        this.getChildren().addAll(roundsLeft, computerPoints);
    }
}
