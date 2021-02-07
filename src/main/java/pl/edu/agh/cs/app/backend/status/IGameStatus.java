package pl.edu.agh.cs.app.backend.status;

import javafx.beans.property.BooleanProperty;
import pl.edu.agh.cs.app.backend.status.states.PressedStatus;

public interface IGameStatus {
    BooleanProperty getFullScreenProperty();

    void toggleFullScreen();

    void pressed(PressedStatus pressed);

    boolean isSuccess();

    boolean isFailure();

    void startRound();

    void endRound();

    boolean isRoundFinished();

    void endGame();

    boolean isGameFinished();
}
