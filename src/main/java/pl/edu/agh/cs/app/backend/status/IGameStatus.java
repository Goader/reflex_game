package pl.edu.agh.cs.app.backend.status;

import javafx.beans.property.BooleanProperty;
import pl.edu.agh.cs.app.backend.status.states.PressedStatus;

public interface IGameStatus {
    BooleanProperty getFullScreenProperty();

    void toggleFullScreen();

    boolean isChoiceHandled();

    void handleChoice();

    void pressed(PressedStatus pressed);

    boolean isSuccess();

    boolean isFailure();

    int getRoundTime();

    void setPressedTime(int pressedTime);

    void startRound();

    void endRound();

    boolean isRoundFinished();

    boolean isGameFinished();


}
