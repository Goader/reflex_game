package pl.edu.agh.cs.app.backend.status;

import javafx.application.Platform;
import pl.edu.agh.cs.app.backend.data.GameConfiguration;

public class SingleGameStatus extends AbstractGameStatus {
    private int timesFailed;

    public SingleGameStatus(GameConfiguration config) {
        super(config);
        timesFailed = 0;
    }

    @Override
    public void endRound() {
        super.endRound();
        if (!pressed.isSuccess()) {
            timesFailed++;
            if (timesFailed >= config.getFailsCount()) {
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
}
