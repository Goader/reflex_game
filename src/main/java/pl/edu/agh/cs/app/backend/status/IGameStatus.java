package pl.edu.agh.cs.app.backend.status;

import javafx.beans.property.BooleanProperty;
import pl.edu.agh.cs.app.backend.status.states.PressedStatus;
import pl.edu.agh.cs.app.ui.game.panes.status.GameTimer;

public interface IGameStatus {
    BooleanProperty getFullScreenProperty();

    void toggleFullScreen();

    boolean isChoiceHandled();

    void handleChoice();

    void pressed(PressedStatus pressed);

    boolean isSuccess();

    boolean isFailure();

    int getRoundTime();

    void measureTime();

    void startRound();

    void endRound();

    boolean isRoundFinished();

    boolean isGameFinished();

    void runGameTimer();

    void setGameTimer(GameTimer timer);
}
