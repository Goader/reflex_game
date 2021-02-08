package pl.edu.agh.cs.app.backend.status;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import pl.edu.agh.cs.app.backend.data.GameConfiguration;

public class SingleGameStatus extends AbstractGameStatus {
    private IntegerProperty timesFailed;

    public SingleGameStatus(GameConfiguration config) {
        super(config);
        timesFailed = new SimpleIntegerProperty();
        timesFailed.set(0);
    }

    @Override
    public void endRound() {
        super.endRound();
        if (!pressed.isSuccess()) {
            final int instantTimesFailed = timesFailed.get() + 1;
            Platform.runLater(() -> timesFailed.set(instantTimesFailed));
            if (instantTimesFailed >= config.getFailsCount()) {
                endGame();
            }
        }
        else {
            Platform.runLater(() -> playerPoints.set(playerPoints.get() + 1));
            // TODO reaction time
        }
        roundTime -= config.getDeltaTime();
        if (roundTime <= 0) endGame();
    }

    public NumberBinding livesLeftBinding() {
        return Bindings.subtract(config.getFailsCount(), timesFailed);
    }
}
