package pl.edu.agh.cs.app.backend.status;

import pl.edu.agh.cs.app.backend.data.GameConfiguration;
import pl.edu.agh.cs.app.backend.status.states.PressedStatus;

public class SingleGameStatus extends AbstractGameStatus {
    private int timesFailed;

    public SingleGameStatus(GameConfiguration config) {
        super(config);
        timesFailed = 0;
    }

    @Override
    public void pressed(PressedStatus pressed) {
        super.pressed(pressed);
    }

    @Override
    public void endRound() {
        super.endRound();
        if (!pressed.isSuccess()) {
            timesFailed++;
            System.out.println(timesFailed);
            System.out.println(config.getFailsCount());
            if (timesFailed >= config.getFailsCount()) {
                endGame();
            }
        }
        roundTime -= config.getDeltaTime();
        if (roundTime <= 0) endGame();
    }
}
